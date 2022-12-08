package com.nttdata.bootcoinselling.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bootcoinsellingtickets")
public class BootCoinSellingTicket {

    @Id
    private String id;

    private String bootCoinPurchaseId;
    private String sellerAccount;
    private String bootCoinBuyerPurseId;
    private String bootCoinSellerPurseId;
    private float amountToPay;
    private float soldBootCoinAmount;
    private boolean payed;
    /*private String bootCoinSellerPurseId;

    private String bootCoinPurchaseId;

    private float paymentAmount;

    private String sellerAccount;*/
}
