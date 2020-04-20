package com.armydocs.server.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UnitTest {

    @Autowired
    EntityManager em;

    @Test
    public void basicCrud(){
        // create
        Unit unit = Unit.builder().name("계룡대 근무지원단").build();
        Unit childUnit = Unit.builder().name("지원연대").build();
        Unit grandChildUnit = Unit.builder().name("관리대대").build();
        Unit grandChildUnit2 = Unit.builder().name("헌병대대").build();
        unit.addChildUnit(childUnit);
        childUnit.addChildUnit(grandChildUnit);
        childUnit.addChildUnit(grandChildUnit2);
        em.persist(unit);

        Unit findUnit = em.find(Unit.class, unit.getId());
        Unit findChildUnit = findUnit.getChildUnits().get(0);
        Unit findGrandChildUnit = findChildUnit.getChildUnits().get(0);

        assertEquals(findUnit.getName(), unit.getName());
        assertEquals(findChildUnit.getName(), childUnit.getName());
        assertEquals(findGrandChildUnit.getName(), grandChildUnit.getName());

        // remove
        findChildUnit.removeChildUnit(grandChildUnit2);
        assertEquals(findChildUnit.getChildUnits().size(), 1);

        // update
        unit.changeName("자근단");
        Unit updatedUnit = em.find(Unit.class, unit.getId());

        assertEquals(updatedUnit.getName(), "자근단");
    }
}