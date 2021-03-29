package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.Post;
import java.util.Optional;

public interface PostService {

    Optional<Post> getPostDetail(Long postId);
}
