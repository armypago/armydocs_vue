package com.armydocs.server.domain.survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

@Transactional
@SpringBootTest
class AnswerTest {

    @Autowired
    EntityManager em;

    @Test
    public void saveAnswer(){
        Survey survey = getSurvey();
        Question question = Question.builder().survey(survey)
                .surveyType(SurveyType.SUBJECTIVE)
                .content("제일 좋아하는 급식은?").build();

        em.persist(survey);

    }

    private Survey getSurvey() {
        SurveyPeriod period = SurveyPeriod.builder()
                .startDate(LocalDateTime.now().minusHours(10))
                .endDate(LocalDateTime.now().plusHours(10))
                .build();
        return Survey.builder()
                .title("급식 만족도 조사")
                .description("급식 질 개선을 위한 설문조사입니다.")
                .surveyPeriod(period).build();
    }
}