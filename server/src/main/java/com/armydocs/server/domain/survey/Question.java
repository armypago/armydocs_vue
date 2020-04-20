package com.armydocs.server.domain.survey;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(updatable = false)
    private SurveyType surveyType;
    private String content;
    private int sequence;

    @OneToMany(mappedBy = "question", cascade = ALL, orphanRemoval = true)
    private List<QuestionItem> questionItems = new ArrayList<>();

    @Builder
    public Question(Survey survey, int sequence, SurveyType surveyType, String content) {
        this.survey = survey;
        this.sequence = sequence;
        this.surveyType = surveyType;
        this.content = content;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void changeSequence(int sequence){
        this.sequence = sequence;
    }

    public void addQuestionItem(QuestionItem item){
        item.changeQuestion(this);
        this.questionItems.add(item);
    }

    public void removeQuestionItem(QuestionItem item){
        this.questionItems.remove(item);
    }
}
