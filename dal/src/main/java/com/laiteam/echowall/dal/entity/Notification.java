package com.laiteam.echowall.dal.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@Table(name = "notification")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long sender_id;
    private Long reception_id;
    private Long post_id;
    private String deep_link;
    @ManyToOne
    @JoinColumn(name="notification_type", referencedColumnName = "id")
    private NotificationType notification_type;
    @CreationTimestamp
    private Date create_date;
    private Boolean is_read;
    private Boolean is_deleted;

}
