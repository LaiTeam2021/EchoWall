package com.laiteam.developerforfun.post;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Getter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long userId;
    private String title;
    private String context;
    private Long locationId;
    private Long postTypeId;
    private boolean isDeleted;
    private String createDate;
}
