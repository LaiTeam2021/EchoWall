package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.Post;

import java.util.Optional;

public interface SearchService {
    Optional<Post> findByTitleContaining(String searchText);
}

