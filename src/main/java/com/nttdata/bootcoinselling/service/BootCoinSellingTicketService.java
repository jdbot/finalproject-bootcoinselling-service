package com.nttdata.bootcoinselling.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.bootcoinselling.document.BootCoinSellingTicket;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootCoinSellingTicketService {

    public Flux<BootCoinSellingTicket> findAll();

    public Mono<BootCoinSellingTicket> register(BootCoinSellingTicket bootCoinSellingTicket);

    public Mono<BootCoinSellingTicket> update(BootCoinSellingTicket bootCoinSellingTicket);

    public Mono<Void> delete(String id);

    public Mono<BootCoinSellingTicket> findById(String id);

    public Mono<BootCoinSellingTicket> payTicket(String id) throws JsonProcessingException;

}
