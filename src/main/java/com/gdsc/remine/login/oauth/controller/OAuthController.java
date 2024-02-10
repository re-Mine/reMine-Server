package com.gdsc.remine.login.oauth.controller;

import com.gdsc.remine.global.api_payload.ApiResponse;
import com.gdsc.remine.global.query_string.QueryStringArgResolver;
import com.gdsc.remine.login.jwt.dto.response.AuthTokens;
import com.gdsc.remine.login.oauth.dto.request.GoogleLoginParams;
import com.gdsc.remine.login.oauth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/login/oauth2/code/google")
    public ApiResponse<AuthTokens> loginKakao(
            @QueryStringArgResolver final GoogleLoginParams params
    ) {
        return ApiResponse.onSuccess(oAuthLoginService.login(params));
    }
}
