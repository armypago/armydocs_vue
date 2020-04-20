package com.armydocs.server.domain;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Unit {

    @Id @GeneratedValue
    private Long id;

    private int hierarchy;
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_unit_id")
    private Unit parentUnit;

    @OneToMany(mappedBy = "parentUnit", cascade = ALL)
    private List<Unit> childUnits = new ArrayList<>();

    @Builder
    public Unit(String name){
        hierarchy = 0;
        this.name = name;
    }

    private void changeParentUnit(Unit parentUnit){
        this.parentUnit = parentUnit;
        hierarchy = parentUnit.getHierarchy();
    }

    public void addChildUnit(Unit childUnit){
        childUnit.changeParentUnit(this);
        childUnits.add(childUnit);
    }

    public void removeChildUnit(Unit childUnit){
        childUnits.remove(childUnit);
    }

    public void changeName(String name){
        this.name = name;
    }
}
