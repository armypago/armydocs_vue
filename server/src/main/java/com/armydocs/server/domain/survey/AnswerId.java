package com.armydocs.server.domain.survey;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class AnswerId implements Serializable {

    @EqualsAndHashCode.Include
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long uid;

    @EqualsAndHashCode.Include
    @Column(name = "question_item_id", nullable = false, updatable = false)
    private Long qid;

    @Builder
    public AnswerId(Long uid, Long qid) {
        this.uid = uid;
        this.qid = qid;
    }
}
