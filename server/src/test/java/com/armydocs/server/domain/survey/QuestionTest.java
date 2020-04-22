package com.armydocs.server.domain.survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class QuestionTest {

    @Autowired
    EntityManager em;

    @Test
    void saveQuestion() {
        Survey survey = getSurvey();
        em.persist(survey);
        Question question = getQuestion(survey, "제일 좋아하는 급식은?");
        List<String> food = Arrays.asList("육류", "어류", "조류");
        food.forEach(f -> question.addQuestionItem(QuestionItem.builder().content(f).build()));
        em.persist(question);

        Question findQuestion = em.find(Question.class, question.getId());
        QuestionItem findQuestionItem = findQuestion.getQuestionItems().get(1);
        findQuestion.removeQuestionItem(findQuestionItem);

        assertThat(findQuestion.getContent()).isEqualTo(question.getContent());
        assertThat(findQuestion.getSurvey()).isEqualTo(survey);
        assertThat(findQuestion.getContent()).isEqualTo(question.getContent());
        assertThat(findQuestion.getSurveyType()).isEqualTo(question.getSurveyType());
        assertThat(findQuestion.getQuestionItems().size()).isEqualTo(2);
        assertThat(findQuestion.getQuestionItems().get(1).getContent()).isEqualTo("조류");
    }

    @Test
    void changeSequence(){
        Survey survey = getSurvey();
        em.persist(survey);
        List<Question> questions = new ArrayList<>();
        questions.add(getQuestion(survey, "제일 좋아하는 과일은?"));
        questions.add(getQuestion(survey, "제일 좋아시는 반찬은?"));
        questions.add(getQuestion(survey, "제일 좋아하는 음료는?"));
        questions.forEach(q -> em.persist(q));

        questions.get(0).changeSequence(2);
        questions.get(1).changeSequence(0);
        questions.get(2).changeSequence(1);

        assertThat(questions.get(0).getSequence()).isEqualTo(2);
        assertThat(questions.get(1).getSequence()).isEqualTo(0);
        assertThat(questions.get(2).getSequence()).isEqualTo(1);
    }

    @Test
    void changeContent(){
        Survey survey = getSurvey();
        em.persist(survey);
        Question question = getQuestion(survey, "제일 좋아하는 급식은?");
        em.persist(question);

        Question findQuestion = em.find(Question.class, question.getId());
        findQuestion.changeContent("제일 싫어하는 음식은?");
    }

    private Question getQuestion(Survey survey, String content) {
        return Question.builder().survey(survey)
                    .sequence(0)
                    .surveyType(SurveyType.SUBJECTIVE)
                    .content(content).build();
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