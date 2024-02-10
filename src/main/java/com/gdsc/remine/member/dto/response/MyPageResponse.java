package com.gdsc.remine.member.dto.response;

import com.gdsc.remine.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {
    private String name;
    private String email;
    private String profileImage;

    @Builder(builderMethodName = "from")
    public MyPageResponse(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.profileImage = member.getProfileImage();
    }
}
