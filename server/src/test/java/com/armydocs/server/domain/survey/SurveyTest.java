package com.armydocs.server.domain.survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SurveyTest {

    @Autowired
    EntityManager em;

    @Test
    public void basicCRUD(){
        Survey survey = getSurvey();
        em.persist(survey);
        String description = "Survey to improve feed quality";
        LocalDateTime changedDateTime = survey.getSurveyPeriod().getEndDate().minusYears(10);

        Survey findSurvey = em.find(Survey.class, survey.getId());

        assertEquals(findSurvey.getTitle(), survey.getTitle());
        assertEquals(findSurvey.getDescription(), survey.getDescription());
        assertEquals(findSurvey.getSurveyPeriod(), survey.getSurveyPeriod());

        survey.changeDescription(description);
        survey.getSurveyPeriod().changeEndDate(changedDateTime);

        assertEquals(findSurvey.getDescription(), description);
        assertEquals(findSurvey.getSurveyPeriod().getEndDate(), changedDateTime);
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