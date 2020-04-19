package com.armydocs.server.domain.survey;

import com.armydocs.server.exception.InvaildTimeToChangeError;
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
    public void saveSurveyAndRead(){
        Survey survey = getSurvey();

        em.persist(survey);
        Survey findSurvey = em.find(Survey.class, survey.getId());

        assertEquals(findSurvey.getTitle(), survey.getTitle());
        assertEquals(findSurvey.getDescription(), survey.getDescription());
        assertEquals(findSurvey.getSurveyPeriod(), survey.getSurveyPeriod());
    }

    @Test
    public void changePeriodAndDescription(){
        Survey survey = getSurvey();
        em.persist(survey);
        String description = "Survey to improve feed quality";
        LocalDateTime changedDateTime = survey.getSurveyPeriod().getEndDate().minusYears(10);

        survey.changeDescription(description);
        survey.getSurveyPeriod().changeEndDate(changedDateTime);
        Survey findSurvey = em.find(Survey.class, survey.getId());

        assertEquals(findSurvey.getDescription(), description);
        assertEquals(findSurvey.getSurveyPeriod().getEndDate(), changedDateTime);
    }

    @Test
    public void changeDescriptionAfterSurveyEnded(){
        SurveyPeriod pastPeriod = getPeriod(LocalDateTime.now().minusDays(10));
        Survey survey = getSurvey(pastPeriod);
        em.persist(survey);

        Survey findSurvey = em.find(Survey.class, survey.getId());

        assertThrows(InvaildTimeToChangeError.class, ()-> {
            findSurvey.changeDescription("설문조사 설명을 수정하겠습니다.");
        });
    }

    private Survey getSurvey() {
        return getSurvey(getPeriod(LocalDateTime.now().plusDays(10)));
    }

    private Survey getSurvey(SurveyPeriod period) {
        return Survey.builder()
                .title("급식 만족도 조사")
                .description("급식 질 개선을 위한 설문조사입니다.")
                .surveyPeriod(period).build();
    }

    private SurveyPeriod getPeriod(LocalDateTime time) {
        return SurveyPeriod.builder()
                .startDate(time.minusHours(3))
                .endDate(time.plusHours(3))
                .build();
    }
}