package com.armydocs.server.domain.survey;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AnswerId implements Serializable {

    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Long uid;

    @EqualsAndHashCode.Include
    @Column(name = "question_item_id")
    private Long qid;

    @Builder
    public AnswerId(Long uid, Long qid) {
        this.uid = uid;
        this.qid = qid;
    }
}
