package com.cards.manager.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Getter
@ToString(exclude = "cardDetails")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardInfoRequestDto {
    @NotBlank
    private String id;
    @Valid
    private UserNameDto fullName;
    @NotNull
    private String cardDetails;
}
