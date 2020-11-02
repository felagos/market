package com.fl.market.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseDTO {

    private long purchaseId;

    @NotNull(message = "Required field")
    @NotEmpty(message = "Not empty field")
    private String clientId;
    private LocalDateTime date;
    private String paymentMethod;
    private String comment;
    private String state;
    private List<PurchaseItemDTO> items;

}
