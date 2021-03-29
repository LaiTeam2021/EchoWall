package com.laiteam.echowall.dal.repository;

import com.laiteam.echowall.dal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitleContaining(String searchContent);
}
