package com.gdsc.remine.declaration.dto.response;

import com.gdsc.remine.declaration.domain.Declaration;
import com.gdsc.remine.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationElement {
    private LocalDateTime createdDate;
    private String content;
    private String name;
    private String profileImage;

    public DeclarationElement(Declaration declaration) {
        this.createdDate = declaration.getCreatedDate();
        this.content = declaration.getContent();

        final Member member = declaration.getMember();
        this.name = member.getName();
        this.profileImage = member.getProfileImage();
    }
}
