package com.nttdata.bootcoinselling.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcoinselling.dto.BootCoinPaymentDto;
import com.nttdata.bootcoinselling.dto.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendPaymentToSellerAccount(PaymentDto paymentDto) throws JsonProcessingException {
        LOGGER.info("Sending payment to seller's account");
        String paymentDtoString = objectMapper.writeValueAsString(paymentDto);
        kafkaTemplate.send("pay-seller",paymentDtoString);
    }

    public void sendBootCoinPayment(BootCoinPaymentDto bootCoinPaymentDto) throws JsonProcessingException {
        LOGGER.info("Sending boot coins to buyer's purse or taking boot coins from seller's purse");
        String bootCoinPaymentDtoString = objectMapper.writeValueAsString(bootCoinPaymentDto);
        kafkaTemplate.send("coin-payment",bootCoinPaymentDtoString);
    }

    public void sendConfirmationToPurchase(String bootCoinPurchaseId) {
        LOGGER.info("Sending confirmation of purchase");
        kafkaTemplate.send("purchase-confirmation",bootCoinPurchaseId);
    }

}
