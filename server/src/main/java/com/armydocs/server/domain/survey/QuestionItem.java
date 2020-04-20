package com.armydocs.server.domain.survey;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class QuestionItem {

    @Id @GeneratedValue
    private int seq;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private String content;

    @Builder
    public QuestionItem(String content) {
        this.content = content;
    }

    public void changeQuestion(Question question){
        if(this.question != null){
            this.question = question;
        }
    }
}
