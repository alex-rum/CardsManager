package com.cards.manager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cards.manager.controllers.dto.CardDetailsResponseDto;
import com.cards.manager.controllers.dto.CardInfoRequestDto;
import com.cards.manager.controllers.dto.CardInfoResponseDto;
import com.cards.manager.controllers.dto.UserNameDto;
import com.cards.manager.domain.model.CardDetails;
import com.cards.manager.domain.model.CardInfo;
import com.cards.manager.domain.model.UserName;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.util.Optional.ofNullable;


@Component
@RequiredArgsConstructor
public class CardInfoConverter {

    private final ObjectMapper objectMapper;

    public CardInfo toModel(@NonNull CardInfoRequestDto dto) {
        final UserNameDto userName = dto.getFullName();
        return CardInfo.builder()
                .id(dto.getId())
                .userName(UserName.builder()
                        .firstName(ofNullable(userName).map(UserNameDto::getFirstName).orElse(null))
                        .lastName(ofNullable(userName).map(UserNameDto::getLastName).orElse(null))
                        .build())
                .cardDetails(getDecryptedCardDetails(dto.getCardDetails()))
                .build();
    }

    public CardInfoResponseDto toDto(@NonNull CardInfo model) {
        final UserName userName = model.getUserName();
        return CardInfoResponseDto.builder()
                .id(model.getId())
                .fullName(UserNameDto.builder()
                        .firstName(ofNullable(userName).map(UserName::getFirstName).orElse(null))
                        .lastName(ofNullable(userName).map(UserName::getLastName).orElse(null))
                        .build())
                .cardDetails(CardDetailsResponseDto.builder()
                        .cvv(model.getCardDetails().getCvv())
                        .pan(model.getCardDetails().getPan())
                        .build())
                .build();
    }

    private CardDetails getDecryptedCardDetails(@NonNull String cardDetails) {
        try {
            return objectMapper.readValue(cardDetails, CardDetails.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Card details string cannot be transformed to Json object", e);
        }
    }
}
