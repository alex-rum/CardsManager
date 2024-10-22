package com.cards.manager.services;

import com.cards.manager.domain.mappers.CardInfoEntityMapper;
import com.cards.manager.domain.model.CardInfo;
import com.cards.manager.repositories.CardInfoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CardService {

    private final CardInfoRepository cardInfoRepository;
    private final CardInfoEntityMapper mapper;

    public void createCard(@NonNull CardInfo cardInfo) {
        if (cardInfoRepository.existsById(cardInfo.getId())) {
            return;
        }
        cardInfoRepository.save(mapper.toEntity(cardInfo));
    }

    public CardInfo getCard(@NonNull String id) {
        return cardInfoRepository.findById(id)
                .map(mapper::toCardInfoModel)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Card not found for id %s", id)));
    }
}
