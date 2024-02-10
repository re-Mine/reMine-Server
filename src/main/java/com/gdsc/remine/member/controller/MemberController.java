package com.gdsc.remine.member.controller;

import com.gdsc.remine.global.api_payload.ApiResponse;
import com.gdsc.remine.member.dto.response.MyPageResponse;
import com.gdsc.remine.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("")
    public ApiResponse<MyPageResponse> getMyPage() {
        return ApiResponse.onSuccess(memberService.getMyPage());
    }
}
