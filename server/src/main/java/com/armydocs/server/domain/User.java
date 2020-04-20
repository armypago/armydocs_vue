package com.armydocs.server.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        columnNames={"serialNumber", "phoneNumber"})})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String serialNumber;
    private String phoneNumber;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "unit_id")
    private Unit Unit;

    @Builder
    public User(
            String name, String serialNumber, String phoneNumber, Unit Unit){

        this.name = name;
        this.serialNumber = serialNumber;
        this.phoneNumber = phoneNumber;
        this.Unit = Unit;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changePhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void changeUnit(Unit Unit){
        this.Unit = Unit;
    }
}
