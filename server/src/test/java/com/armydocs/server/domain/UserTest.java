package com.armydocs.server.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserTest {

    @Autowired
    EntityManager em;

    @Test
    public void basicCRUD(){
        // create
        Unit unit = Unit.builder().name("계룡대 근무지원단").build();
        User user = User.builder()
                .serialNumber("00-12341234")
                .name("준영").phoneNumber("010-1234-5678").Unit(unit).build();
        em.persist(user);

        User findUser = em.find(User.class, user.getId());

        assertEquals(findUser, user);

        // update
        Unit changeUnit = Unit.builder().name("계룡대 근무지원단").build();
        String changeName = "최준영";
        String changePhoneNumber = "010-5678-1234";

        user.changeName(changeName);
        user.changePhoneNumber(changePhoneNumber);
        user.changeUnit(changeUnit);

        User updatedUser = em.find(User.class, user.getId());
        assertEquals(updatedUser.getName(), changeName);
        assertEquals(updatedUser.getPhoneNumber(), changePhoneNumber);
        assertEquals(updatedUser.getUnit(), changeUnit);
    }
}