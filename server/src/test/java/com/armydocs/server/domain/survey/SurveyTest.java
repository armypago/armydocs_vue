package com.armydocs.server.domain.survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

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

        assertThat(findSurvey.getTitle()).isEqualTo(survey.getTitle());
        assertThat(findSurvey.getDescription()).isEqualTo(survey.getDescription());
        assertThat(findSurvey.getSurveyPeriod()).isEqualTo(survey.getSurveyPeriod());

        survey.changeDescription(description);
        survey.getSurveyPeriod().changeEndDate(changedDateTime);

        assertThat(findSurvey.getDescription()).isEqualTo(description);
        assertThat(findSurvey.getSurveyPeriod().getEndDate()).isEqualTo(changedDateTime);
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