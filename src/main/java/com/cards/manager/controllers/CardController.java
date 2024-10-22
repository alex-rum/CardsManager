package com.cards.manager.controllers;

import com.cards.manager.controllers.dto.CardInfoRequestDto;
import com.cards.manager.controllers.dto.CardInfoResponseDto;
import com.cards.manager.services.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/cards")
public class CardController {
    private final CardService cardService;
    private final CardInfoConverter cardInfoConverter;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCard(@Valid @RequestBody CardInfoRequestDto cardInfoRequest) {
        cardService.createCard(cardInfoConverter.toModel(cardInfoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardInfoResponseDto> getCard(@PathVariable("id") String id) {
        return ResponseEntity.ok(cardInfoConverter.toDto(cardService.getCard(id)));
    }
}
