package com.armydocs.server.domain.survey;

import com.armydocs.server.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Answer extends BaseTimeEntity {

    @EmbeddedId
    private AnswerId id;

    private int answerSeq;
    private String answerContent;

    @Builder
    public Answer(AnswerId id, int answerSeq, String answerContent) {
        this.id = id;
        this.answerSeq = answerSeq;
        this.answerContent = answerContent;
    }

    public void changeAnswerSeq(int answerSeq){
        this.answerSeq = answerSeq;
    }

    public void changeAnswerContent(String answerContent){
        this.answerContent = answerContent;
    }
}
