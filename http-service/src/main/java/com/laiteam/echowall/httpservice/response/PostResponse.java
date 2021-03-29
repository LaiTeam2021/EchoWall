package com.laiteam.echowall.httpservice.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.laiteam.echowall.dal.entity.Location;
import com.laiteam.echowall.dal.entity.Post;
import com.laiteam.echowall.dal.entity.PostType;
import com.laiteam.echowall.httpservice.utils.BeanUtils;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostResponse {

    private ProfileResponse author;
    private String title;
    private String context;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;
    private Location location;
    private PostType postType;

    public static PostResponse convert(Post post) {
        PostResponse response = BeanUtils.copyProperties(post, PostResponse.class);
        response.author = ProfileResponse.convert(post.getProfile());
        return response;
    }
}
