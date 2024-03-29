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
    public AuthTokens login(final OAuthLoginParams params) {
        final OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        final Member member = findOrCreateMember(oAuthInfoResponse);
        final AuthTokens authTokens = authTokensGenerator.generate(member.getId());
        member.setAuthRefreshToken(authTokens.getRefreshToken());
        return authTokens;
    }

    private Member findOrCreateMember(final OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findBySocialId(oAuthInfoResponse.getSocialId())
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Member newMember(final OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .socialId(oAuthInfoResponse.getSocialId())
                .profileImage(oAuthInfoResponse.getProfileImage())
                .name(oAuthInfoResponse.getName())
                .email(oAuthInfoResponse.getEmail())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        return memberRepository.save(member);
    }
}