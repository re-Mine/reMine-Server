package com.gdsc.remine.member.service;

import com.gdsc.remine.login.jwt.util.AuthTokensGenerator;
import com.gdsc.remine.member.domain.Member;
import com.gdsc.remine.member.domain.repository.MemberRepository;
import com.gdsc.remine.member.dto.response.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public MyPageResponse getMyPage() {
        final Member loginMember = authTokensGenerator.getLoginMember();
        return MyPageResponse
                .from()
                .member(loginMember)
                .build();
    }
}
