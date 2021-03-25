package com.laiteam.echowall.dal.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "gender_type")
public class GenderType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String gender;
}