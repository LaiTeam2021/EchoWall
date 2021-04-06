package com.laiteam.echowall.dal.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "notification_type")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class NotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String type;
}