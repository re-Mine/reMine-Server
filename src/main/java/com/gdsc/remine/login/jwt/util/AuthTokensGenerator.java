package com.gdsc.remine.login.jwt.util;

import com.gdsc.remine.global.api_payload.exception.GeneralException;
import com.gdsc.remine.login.jwt.dto.response.AuthTokens;
import com.gdsc.remine.member.domain.Member;
import com.gdsc.remine.member.domain.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

import static com.gdsc.remine.global.api_payload.code.status.ErrorStatus.INVALID_MEMBER_ID;

@Component
@RequiredArgsConstructor
public class AuthTokensGenerator {
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 5;        // 5 Hour
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7 Day

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public AuthTokens generate(Long memberId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String subject = memberId.toString();
        String accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredAt);

        return AuthTokens.of(accessToken, refreshToken, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    private Long extractMemberId(String accessToken) {
        return Long.valueOf(jwtTokenProvider.extractSubject(accessToken));
    }

    public Long getLoginMemberId() {
        HttpServletRequest httpServletRequest
                = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        final String accessToken = httpServletRequest.getHeader("Authorization");
        return extractMemberId(accessToken);
    }

    public Member getLoginMember() {
        final Long loginId = getLoginMemberId();
        return memberRepository.findById(loginId)
                .orElseThrow(() -> new GeneralException(INVALID_MEMBER_ID));
    }
}