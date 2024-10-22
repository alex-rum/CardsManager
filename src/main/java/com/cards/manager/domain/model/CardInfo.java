package com.cards.manager.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CardInfo {

    @NotBlank
    private String id;
    @Valid
    private UserName userName;
    @Valid
    private CardDetails cardDetails;
}
