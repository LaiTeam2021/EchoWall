package com.laiteam.developerforfun.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class PostApi {
    private final PostService postService;

    @Autowired
    public PostApi(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(path = "/post/{postId}", method = GET)
    public Optional<Post> findById(@PathVariable Long postId) {
        return postService.findById(postId);
    }
}
