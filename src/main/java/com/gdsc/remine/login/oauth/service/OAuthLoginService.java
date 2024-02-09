package com.gdsc.remine.login.oauth.service;

import com.gdsc.remine.comment.domain.repository.CommentRepository;
import com.gdsc.remine.like.domain.repository.LikeRepository;
import com.gdsc.remine.login.jwt.dto.response.AuthTokens;
import com.gdsc.remine.login.jwt.util.AuthTokensGenerator;
import com.gdsc.remine.login.oauth.dto.request.OAuthLoginParams;
import com.gdsc.remine.login.oauth.dto.response.OAuthInfoResponse;
import com.gdsc.remine.member.domain.Member;
import com.gdsc.remine.member.domain.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    @Transactional
    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        final Member member = memberRepository.findBySocialId(oAuthInfoResponse.getSocialId())
                .orElseGet(() -> newMember(oAuthInfoResponse));
        member.setSocialAccessToken(oAuthInfoResponse.getAccessToken());
        return member.getId();
    }

    private Member newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .socialId(oAuthInfoResponse.getSocialId())
                .name(oAuthInfoResponse.getNickname())
                .profileImage(oAuthInfoResponse.getProfileImage())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .socialAccessToken(oAuthInfoResponse.getAccessToken())
                .build();

        return memberRepository.save(member);
    }
}
