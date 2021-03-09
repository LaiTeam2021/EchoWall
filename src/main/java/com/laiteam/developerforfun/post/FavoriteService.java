package com.laiteam.developerforfun.post;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
  Optional<Favorite> findById(Long id);

  Optional<List<Favorite>> findByUserId(Long userId);

  Optional<List<Favorite>> findByPostId(Long postId);

  Favorite save(Favorite favorite);

  void deleteById(Long id);
}
