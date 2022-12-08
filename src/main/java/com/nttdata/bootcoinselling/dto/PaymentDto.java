package com.nttdata.bootcoinselling.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Data
public class PaymentDto {

    private String accountId;
    private float paymentAmount;

}
