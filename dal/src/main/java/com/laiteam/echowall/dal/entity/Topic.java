package com.laiteam.echowall.dal.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "topic")
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
public class Topic {
    @Id
    private Long id;
    @CreationTimestamp
    private Timestamp createDate;
    private String name;
    @Column(name = "name_cn")
    private String nameCN;
}
