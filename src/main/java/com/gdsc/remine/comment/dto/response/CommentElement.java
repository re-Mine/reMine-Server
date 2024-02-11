package com.gdsc.remine.comment.dto.response;

import com.gdsc.remine.comment.domain.Comment;
import com.gdsc.remine.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentElement {
    private String profileImage;
    private String name;
    private Long commentId;
    private LocalDateTime createdDate;
    private String content;

    public static CommentElement from(final Comment comment) {
        final Member member = comment.getMember();
        return new CommentElement(
                member.getProfileImage(),
                member.getName(),
                comment.getId(),
                comment.getCreatedDate(),
                comment.getContent()
        );
    }
}
