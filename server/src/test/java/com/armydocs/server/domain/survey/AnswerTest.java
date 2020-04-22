package com.armydocs.server.domain.survey;

import com.armydocs.server.domain.Unit;
import com.armydocs.server.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class AnswerTest {

    @Autowired
    EntityManager em;

    @Test
    public void saveAnswer(){
        Survey survey = getSurvey();
        em.persist(survey);
        Question question = getQuestion(survey);
        em.persist(question);
        User user = getUser();
        em.persist(user);

        AnswerId id = AnswerId.builder()
                .qid(question.getId())
                .uid(user.getId()).build();
        Answer answer = Answer.builder()
                .id(id).answerSeq(1).answerContent("육류").build();

        User answerUser = em.find(User.class, answer.getId().getUid());
        Question answerQuestion = em.find(Question.class, answer.getId().getQid());

        assertThat(answerUser).isEqualTo(user);
        assertThat(answerQuestion).isEqualTo(question);
        assertThat(answer.getAnswerContent()).isEqualTo("육류");
    }

    private User getUser(){
        Unit unit = Unit.builder().name("계룡대 근무지원단").build();
        return User.builder()
                .serialNumber("00-12341234")
                .name("준영").phoneNumber("010-1234-5678").Unit(unit).build();
    }

    private Question getQuestion(Survey survey) {
        Question question = Question.builder().survey(survey)
                .surveyType(SurveyType.SUBJECTIVE)
                .content("제일 좋아하는 급식은?").build();
        List<String> food = Arrays.asList("육류", "어류", "조류");
        food.forEach(f -> question.addQuestionItem(QuestionItem.builder().content(f).build()));
        return question;
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