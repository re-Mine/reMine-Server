package com.gdsc.remine.post.dto.response;

import com.gdsc.remine.comment.domain.Comment;
import com.gdsc.remine.comment.dto.response.CommentElement;
import com.gdsc.remine.global.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponse {
    private String profileImage;
    private String name;
    private LocalDateTime createdDate;
    private String content;
    private Boolean isLike;
    private PageInfo commentPageInfo;
    private List<CommentElement> commentList;

    public PostDetailResponse(
            final String name,
            final String profileImage,
            final LocalDateTime createdDate,
            final String content,
            final Boolean isLike) {
        this.name = name;
        this.profileImage = profileImage;
        this.createdDate = createdDate;
        this.content = content;
        this.isLike = isLike;
    }

    public void setCommentPage(Page<Comment> commentPage) {
        this.commentPageInfo = new PageInfo(commentPage);
        this.commentList = commentPage.getContent().stream().map(
                CommentElement::from
        ).collect(Collectors.toList());
    }
}
