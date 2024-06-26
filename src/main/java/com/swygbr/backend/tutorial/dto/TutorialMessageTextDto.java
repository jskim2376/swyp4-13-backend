package com.swygbr.backend.tutorial.dto;

import java.util.Objects;

import com.swygbr.backend.tutorial.domain.TutorialMessageTextEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageTextActorType;
import com.swygbr.backend.tutorial.enums.TutorialMessageTextType;

public record TutorialMessageTextDto(TutorialMessageTextActorType actorType, TutorialMessageTextType textType,
    String content) {
  public static TutorialMessageTextDto fromEntity(TutorialMessageTextEntity entity) {
    if (Objects.isNull(entity)) {
      return null;
    }
    return new TutorialMessageTextDto(entity.getActorType(), entity.getTextType(), entity.getContent());
  }
}
