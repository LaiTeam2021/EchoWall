package com.laiteam.developerforfun.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
  List<Favorite> findByUserId(Long userId);

  List<Favorite> findByPostId(Long postId);
}
