package com.laiteam.developerforfun.post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> findById(Long id);

    Optional<List<Post>> findByUserId(Long userId);
}
