package com.cards.manager.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@Getter
@ToString(exclude = {"cvv"})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDetailsResponseDto {
    @NotBlank
    private String pan;
    @NotBlank
    private String cvv;
}
