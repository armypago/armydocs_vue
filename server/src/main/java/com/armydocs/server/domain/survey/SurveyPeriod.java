package com.armydocs.server.domain.survey;

import com.armydocs.server.exception.InvaildTimeToChangeError;
import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class SurveyPeriod {

    @EqualsAndHashCode.Include
    private LocalDateTime startDate;

    @EqualsAndHashCode.Include
    private LocalDateTime endDate;

    @Builder
    public SurveyPeriod(LocalDateTime startDate, LocalDateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void changeStartDate(LocalDateTime startDate){
        validateChangeableDateTime();
        this.startDate = startDate;
    }

    public void changeEndDate(LocalDateTime endDate){
        validateChangeableDateTime();
        this.endDate = endDate;
    }

    public void validateChangeableDateTime(){
        if(LocalDateTime.now().isAfter(this.endDate)){
            throw new InvaildTimeToChangeError("Survey is closed.");
        }
        if(LocalDateTime.now().isAfter(this.startDate)){
            throw new InvaildTimeToChangeError("Survey is already in progress.");
        }
    }
}
