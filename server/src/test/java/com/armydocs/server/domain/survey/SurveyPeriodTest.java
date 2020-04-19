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
class SurveyPeriodTest {

    @Autowired
    EntityManager em;

    @Test
    public void changePeriod(){
        SurveyPeriod period = getPeriod(LocalDateTime.now().plusDays(10));
        period.changeEndDate(period.getEndDate().plusDays(1));
        period.changeStartDate(period.getStartDate().plusDays(1));
    }

    @Test
    public void changePeriodWhileInProgress(){
        SurveyPeriod periodInProgress = getPeriod(LocalDateTime.now());

        assertThrows(InvaildTimeToChangeError.class, ()-> {
            periodInProgress.changeEndDate(periodInProgress.getEndDate().plusDays(1));
        });
    }

    @Test
    public void changePeriodAfterEnded(){
        SurveyPeriod pastPeriod = getPeriod(LocalDateTime.now().minusDays(10));

        assertThrows(InvaildTimeToChangeError.class, ()-> {
            pastPeriod.changeEndDate(pastPeriod.getEndDate().plusDays(10));
        });
    }

    private SurveyPeriod getPeriod(LocalDateTime time) {
        return SurveyPeriod.builder()
                .startDate(time.minusHours(3))
                .endDate(time.plusHours(3))
                .build();
    }
}