package com.laiteam.echowall.httpservice.api;

import com.laiteam.echowall.common.exception.InvalidRequestException;
import com.laiteam.echowall.httpservice.response.PostResponse;
import com.laiteam.echowall.service.PostService;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostApi {

    private final PostService postService;

    @Autowired
    public PostApi(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/post/{id}")
    public ResponseEntity<?> getPostDetail(@PathVariable Long id) throws Throwable {
        return postService.getPostDetail(id)
          .map(it -> ResponseEntity.ok(PostResponse.convert(it)))
          .orElseThrow((Supplier<Throwable>) () -> new InvalidRequestException(
            "Can't find this post"));
    }

}
