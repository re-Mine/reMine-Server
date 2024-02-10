package com.gdsc.remine.member.domain;

import com.gdsc.remine.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import static jakarta.persistence.EnumType.STRING;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private OAuthProvider oAuthProvider;

    private String name;

    private String profileImage;

    private String email;

    private String socialId;

    @Builder
    public Member(OAuthProvider oAuthProvider, String name, String profileImage, String email, String socialId) {
        this.oAuthProvider = oAuthProvider;
        this.name = name;
        this.profileImage = profileImage;
        this.email = email;
        this.socialId = socialId;
    }
}
