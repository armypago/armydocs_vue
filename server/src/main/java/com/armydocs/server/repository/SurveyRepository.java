package com.armydocs.server.repository;

import com.armydocs.server.domain.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
