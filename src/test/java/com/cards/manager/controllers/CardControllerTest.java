package com.cards.manager.controllers;

import com.cards.manager.BaseTest;
import com.cards.manager.controllers.dto.CardInfoRequestDto;
import com.cards.manager.domain.entity.CardInfoEntity;
import com.cards.manager.domain.model.CardDetails;
import com.cards.manager.repositories.CardInfoRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.EnabledIf;



import static com.cards.manager.TestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CardControllerTest extends BaseTest {

    private static final String CARDS_URL = "/api/cards";
    private static final String CARDS_ID_URL = CARDS_URL + "/{id}";

    @Autowired
    private CardInfoRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void createCard_success() throws Exception {
        final CardInfoRequestDto request = aCardInfoRequestDto().build();

        mockMvc.perform(post(CARDS_URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated())
        ;
        assertCardInfoEntitySaved(request);
    }

    @Test
    public void getCard_success() throws Exception {
        final CardInfoEntity entity = aCardInfoEntityBuilder().build();
        prepareCardInfoEntity(entity);

        mockMvc.perform(get(CARDS_ID_URL, entity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(entity.getId())))
                .andExpect(jsonPath("$.cardDetails", notNullValue()))
                .andExpect(jsonPath("$.cardDetails.cvv", is(CVV)))
        ;
    }

    @Test
    public void getCard_notFound() throws Exception {
        mockMvc.perform(get(CARDS_ID_URL, "nonExistentId"))
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @EnabledIf(
            expression = "${aspect.cardRemove.enabled}",
            loadContext = true
    )
    public void getCard_deletedAfterRead() throws Exception {
        final CardInfoEntity entity = aCardInfoEntityBuilder().build();
        prepareCardInfoEntity(entity);

        mockMvc.perform(get(CARDS_ID_URL, entity.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get(CARDS_ID_URL, entity.getId()))
                .andExpect(status().isNotFound())
        ;
    }

    private void assertCardInfoEntitySaved(CardInfoRequestDto request) {
        final CardInfoEntity entity = repository.findById(request.getId()).get();
        final CardDetails cardDetailsRequest = getCardDetailsFromEncryptedString(request.getCardDetails());
        final CardDetails cardDetailsEntity = getCardDetailsFromEncryptedString(entity.getCardDetails());
        assertThat(entity.getId()).isEqualTo(request.getId());
        assertThat(cardDetailsEntity.getPan()).isEqualTo(cardDetailsRequest.getPan());
        assertThat(cardDetailsEntity.getCvv()).isEqualTo(cardDetailsRequest.getCvv());
    }

    private void prepareCardInfoEntity(CardInfoEntity cardInfoEntity) {
        repository.save(cardInfoEntity);
    }

    @SneakyThrows
    private CardDetails getCardDetailsFromEncryptedString(String encryptedCardDetails) {
        return objectMapper.readValue(encryptedCardDetails, CardDetails.class);
    }
}
