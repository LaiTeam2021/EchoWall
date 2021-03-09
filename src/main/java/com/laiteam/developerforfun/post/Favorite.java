package com.laiteam.developerforfun.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "favorite")
public class Favorite {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;
  private Long postId;
  @CreationTimestamp
  private Timestamp createDate;

  public Favorite() {
  }
}
