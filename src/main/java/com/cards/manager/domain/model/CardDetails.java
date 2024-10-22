package com.cards.manager.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"pan", "cvv"})
public class CardDetails {
    @NotBlank
    private String pan;
    private String cvv;
}
