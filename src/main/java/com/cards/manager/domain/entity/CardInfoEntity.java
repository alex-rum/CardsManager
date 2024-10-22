package com.cards.manager.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@ToString(exclude = "cardDetails")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash
public class CardInfoEntity {

    @Id
    private String id;
    private String cardDetails;
    private String firstName;
    private String lastName;
}
