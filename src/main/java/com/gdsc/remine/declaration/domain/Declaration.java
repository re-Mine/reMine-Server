package com.gdsc.remine.declaration.domain;

import com.gdsc.remine.global.BaseEntity;
import com.gdsc.remine.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
public class Declaration extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String audioFileURL;

    private String content;

    @Builder(builderMethodName = "of")
    private Declaration(
            final Member member,
            final String audioFileURL,
            final String content
    ) {
        this.member = member;
        this.audioFileURL = audioFileURL;
        this.content = content;
    }
}
