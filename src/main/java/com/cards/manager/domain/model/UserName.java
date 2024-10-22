package com.cards.manager.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserName {

    private String firstName;
    private String lastName;
}
