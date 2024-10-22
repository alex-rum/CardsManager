package com.cards.manager.domain.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cards.manager.domain.entity.CardInfoEntity;
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
public class CardInfoEntityMapper {

    private final ObjectMapper objectMapper;

    public CardInfoEntity toEntity(@NonNull CardInfo model) {
        final UserName userName = model.getUserName();
        return CardInfoEntity.builder()
                .id(model.getId())
                .firstName(ofNullable(userName).map(UserName::getFirstName).orElse(null))
                .lastName(ofNullable(userName).map(UserName::getLastName).orElse(null))
                .cardDetails(getEncryptedCardDetails(model.getCardDetails()))
                .build();
    }

    public CardInfo toCardInfoModel(@NonNull CardInfoEntity entity) {
        return CardInfo.builder()
                .id(entity.getId())
                .userName(UserName.builder()
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .build())
                .cardDetails(getDecryptedCardDetails(entity.getCardDetails()))
                .build();
    }

    private String getEncryptedCardDetails(@NonNull CardDetails cardDetails) {
        try {
            return objectMapper.writeValueAsString(cardDetails);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Card details object cannot be transformed to String", e);
        }
    }

    private CardDetails getDecryptedCardDetails(@NonNull String cardDetails) {
        try {
            return objectMapper.readValue(cardDetails, CardDetails.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Card details string cannot be transformed to Json object", e);
        }
    }
}
