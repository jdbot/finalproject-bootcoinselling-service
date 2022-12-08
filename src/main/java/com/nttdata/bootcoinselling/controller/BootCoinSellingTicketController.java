package com.nttdata.bootcoinselling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.bootcoinselling.document.BootCoinSellingTicket;
import com.nttdata.bootcoinselling.service.BootCoinSellingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller of BootCoinSellingTicket.
 */
@RestController
@RequestMapping("/bootcoinsellingticket")
public class BootCoinSellingTicketController {

    @Autowired
    private BootCoinSellingTicketService bootCoinSellingTicketService;

    //Method to get all the BootCoinSellingTickets
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<BootCoinSellingTicket> findAll() {
        return bootCoinSellingTicketService.findAll();
    }

    //Method to insert a new BootCoinPurchase
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<BootCoinSellingTicket> register(@RequestBody BootCoinSellingTicket bootCoinSellingTicket) {
        return  bootCoinSellingTicketService.register(bootCoinSellingTicket);
    }

    //Method to update a BootCoinPurchase
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<BootCoinSellingTicket> modify(@RequestBody BootCoinSellingTicket bootCoinSellingTicket) {
        return  bootCoinSellingTicketService.update(bootCoinSellingTicket);
    }

    //Method to get a BootCoinPurchase by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BootCoinSellingTicket> findById(@PathVariable("id") String id) {
        return bootCoinSellingTicketService.findById(id);
    }

    //Method to delete a BootCoinPurchase
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return bootCoinSellingTicketService.delete(id);
    }

    //Method to pay a selling ticket
    @PutMapping("/payTicket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BootCoinSellingTicket> payTicket(@PathVariable("id") String id) throws JsonProcessingException {
        return  bootCoinSellingTicketService.payTicket(id);
    }
}
