package com.armydocs.server.domain.survey;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Answer {

    @EmbeddedId
    private AnswerId id;

    private Long answerSeq;
    private String answerContent;

    @Builder
    public Answer(AnswerId id) {
        this.id = id;
    }

    public void changeAnswer(){
        
    }
}
