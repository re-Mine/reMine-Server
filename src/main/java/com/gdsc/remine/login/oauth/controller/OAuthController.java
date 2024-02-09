package com.gdsc.remine.login.oauth.controller;

import com.gdsc.remine.global.api_payload.ApiResponse;
import com.gdsc.remine.login.jwt.dto.response.AuthTokens;
import com.gdsc.remine.login.oauth.dto.request.GoogleLoginParams;
import com.gdsc.remine.login.oauth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/google")
    public ApiResponse<AuthTokens> loginKakao(@RequestBody GoogleLoginParams params) {
        return ApiResponse.onSuccess(oAuthLoginService.login(params));
    }
}
