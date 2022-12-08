package com.nttdata.bootcoinselling.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.bootcoinselling.document.BootCoinSellingTicket;
import com.nttdata.bootcoinselling.dto.BootCoinPaymentDto;
import com.nttdata.bootcoinselling.dto.PaymentDto;
import com.nttdata.bootcoinselling.repository.BootCoinSellingTicketRepository;
import com.nttdata.bootcoinselling.service.BootCoinSellingTicketService;
import com.nttdata.bootcoinselling.service.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinSellingTicketServiceImpl implements BootCoinSellingTicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootCoinSellingTicketServiceImpl.class);

    private final Producer producer;

    @Autowired
    BootCoinSellingTicketRepository bootCoinSellingTicketRepository;

    @Autowired
    public BootCoinSellingTicketServiceImpl(Producer producer) {
        this.producer = producer;
    }

    @Override
    public Flux<BootCoinSellingTicket> findAll() {
        return bootCoinSellingTicketRepository.findAll();
    }

    @Override
    public Mono<BootCoinSellingTicket> register(BootCoinSellingTicket bootCoinSellingTicket) {
        return bootCoinSellingTicketRepository.save(bootCoinSellingTicket);
    }

    @Override
    public Mono<BootCoinSellingTicket> update(BootCoinSellingTicket bootCoinSellingTicket) {
        return bootCoinSellingTicketRepository.save(bootCoinSellingTicket);
    }

    @Override
    public Mono<Void> delete(String id) {
        return bootCoinSellingTicketRepository.deleteById(id);
    }

    @Override
    public Mono<BootCoinSellingTicket> findById(String id) {
        return bootCoinSellingTicketRepository.findById(id);
    }

    @Override
    public Mono<BootCoinSellingTicket> payTicket(String id) {
        return findById(id).filter(ticket -> !ticket.isPayed()).flatMap(ticket -> {
            ticket.setPayed(true);
            try {
                producer.sendConfirmationToPurchase(ticket.getBootCoinPurchaseId());
                BootCoinPaymentDto bootCoinPaymentDtoBuyer = new BootCoinPaymentDto(ticket.getBootCoinBuyerPurseId(),ticket.getSoldBootCoinAmount());
                producer.sendBootCoinPayment(bootCoinPaymentDtoBuyer);
                BootCoinPaymentDto bootCoinPaymentDtoSeller = new BootCoinPaymentDto(ticket.getBootCoinSellerPurseId(),ticket.getSoldBootCoinAmount()*-1);
                producer.sendBootCoinPayment(bootCoinPaymentDtoSeller);
                PaymentDto paymentDto = new PaymentDto(ticket.getSellerAccount(), ticket.getAmountToPay());
                producer.sendPaymentToSellerAccount(paymentDto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return update(ticket);
        });
    }
}
