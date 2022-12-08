package com.nttdata.bootcoinselling.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Data
public class BootCoinPaymentDto {

    private String bootCoinPurseId;
    private float bootCoinAmount;
}
