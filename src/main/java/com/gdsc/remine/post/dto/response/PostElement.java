package com.gdsc.remine.post.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gdsc.remine.member.domain.Member;
import com.gdsc.remine.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostElement {
    private String name;
    private String profileImage;
    private LocalDateTime createdDate;
    private String content;
    private Boolean isLike;

    public PostElement(Post post) {
        final Member member = post.getMember();
        this.name = member.getName();
        this.profileImage = member.getProfileImage();
        this.createdDate = post.getCreatedDate();
        this.content = post.getContent();
    }
}
