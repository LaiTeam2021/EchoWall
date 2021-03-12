package com.laiteam.developerforfun.topics.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topic")
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
public class Topic {
    @Id
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    private String name;
    @Column(name = "name_cn")
    private String nameCN;
}
