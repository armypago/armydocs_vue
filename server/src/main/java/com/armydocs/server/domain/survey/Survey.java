package com.armydocs.server.domain.survey;

import com.armydocs.server.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Survey extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @Embedded
    private SurveyPeriod surveyPeriod;

    @Builder
    public Survey(String title, String description, SurveyPeriod surveyPeriod) {
        this.title = title;
        this.description = description;
        this.surveyPeriod = surveyPeriod;
    }

    public void changeDescription(String description){
        this.description = description;
    }
}
