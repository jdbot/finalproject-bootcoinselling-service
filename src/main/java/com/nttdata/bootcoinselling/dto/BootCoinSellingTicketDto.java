package com.nttdata.bootcoinselling.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Data
public class BootCoinSellingTicketDto {

    private String bootCoinPurchaseId;
    private String sellerAccount;
    private String bootCoinBuyerPurseId;
    private String bootCoinSellerPurseId;
    private float amountToPay;
    private float soldBootCoinAmount;

}
