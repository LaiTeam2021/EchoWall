package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.Post;
import com.laiteam.echowall.dal.repository.PostRepository;
import com.laiteam.echowall.service.PostService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> getPostDetail(Long postId) {
        return Optional.ofNullable(postRepository.findPostById(postId));
    }
}
