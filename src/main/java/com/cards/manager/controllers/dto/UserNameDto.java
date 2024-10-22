package com.cards.manager.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserNameDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
