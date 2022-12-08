package com.nttdata.bootcoinselling.repository;

import com.nttdata.bootcoinselling.document.BootCoinSellingTicket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BootCoinSellingTicketRepository extends ReactiveMongoRepository<BootCoinSellingTicket, String> {
}
