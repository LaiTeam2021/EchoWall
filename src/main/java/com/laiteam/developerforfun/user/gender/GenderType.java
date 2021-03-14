package com.laiteam.developerforfun.user.gender;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "gender_type")
public class GenderType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(columnDefinition = "bigserial")
    private Long id;
    private String gender;
}