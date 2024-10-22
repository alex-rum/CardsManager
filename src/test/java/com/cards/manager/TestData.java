package com.cards.manager;


import com.cards.manager.controllers.dto.CardInfoRequestDto;
import com.cards.manager.controllers.dto.UserNameDto;
import com.cards.manager.domain.entity.CardInfoEntity;

import java.util.UUID;


public class TestData {
    public static final String FIRST_NAME = "Ivan";
    public static final String LAST_NAME = "Ivanov";
    public static final String CVV = "123";
    public static final String CARD_DETAILS_ENCRYPTED = "{\"pan\":\"5555555555554444\",\"cvv\":\"123\"}";

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static CardInfoRequestDto.CardInfoRequestDtoBuilder aCardInfoRequestDto() {
        return CardInfoRequestDto.builder()
                .id(generateId())
                .fullName(UserNameDto.builder()
                        .firstName(FIRST_NAME)
                        .lastName(LAST_NAME)
                        .build())
                .cardDetails(CARD_DETAILS_ENCRYPTED)
                ;
    }

    public static CardInfoEntity.CardInfoEntityBuilder aCardInfoEntityBuilder() {
        return CardInfoEntity.builder()
                .id(generateId())
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cardDetails(CARD_DETAILS_ENCRYPTED)
                ;
    }
}
