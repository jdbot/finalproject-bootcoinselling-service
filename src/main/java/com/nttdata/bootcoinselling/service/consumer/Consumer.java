package com.nttdata.bootcoinselling.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcoinselling.document.BootCoinSellingTicket;
import com.nttdata.bootcoinselling.dto.BootCoinSellingTicketDto;
import com.nttdata.bootcoinselling.repository.BootCoinSellingTicketRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public Consumer(ObjectMapper objectMapper, ModelMapper modelMapper) {
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }
    @Autowired
    private BootCoinSellingTicketRepository bootCoinSellingTicketRepository;

    @KafkaListener(topics = "bootcoin-sell" , groupId = "default")
    public void receiveBootCoinPurchase(String message) throws JsonProcessingException {
        BootCoinSellingTicketDto ticketDto = objectMapper.readValue(message,BootCoinSellingTicketDto.class);
        BootCoinSellingTicket ticket = modelMapper.map(ticketDto, BootCoinSellingTicket.class);//new BootCoinPurchase(null,purchaseDto.getBootCoinPurchaseAmount() , false, purchaseDto.getBootCoinPurseId());
        bootCoinSellingTicketRepository.save(ticket).subscribe();
    }
}
