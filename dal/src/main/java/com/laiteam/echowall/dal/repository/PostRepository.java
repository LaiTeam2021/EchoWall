package com.laiteam.echowall.dal.repository;

import com.laiteam.echowall.dal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long postId);
}
