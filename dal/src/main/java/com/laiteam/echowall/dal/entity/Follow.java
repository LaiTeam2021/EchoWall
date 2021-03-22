package com.laiteam.echowall.dal.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@Table(name = "follow")
@IdClass(FollowId.class)
@EqualsAndHashCode(of = {"userId, followId"})
@AllArgsConstructor
@NoArgsConstructor
public class Follow implements Serializable {
    @Id
    private Long userId;

    @Id
    private Long followId;

    @CreationTimestamp
    private Timestamp create_date;
}
