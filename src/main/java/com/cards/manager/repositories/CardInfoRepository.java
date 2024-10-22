package com.cards.manager.repositories;

import com.cards.manager.domain.entity.CardInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardInfoRepository extends CrudRepository<CardInfoEntity, String> {
}
