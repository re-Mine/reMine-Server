package com.gdsc.remine.login.oauth.dto.request;

import com.gdsc.remine.member.domain.OAuthProvider;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Data
@NoArgsConstructor
public class GoogleLoginParams implements OAuthLoginParams {
    private String code;
    private String scope;
    private String authuser;
    private String prompt;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.GOOGLE;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", URLDecoder.decode(code, StandardCharsets.UTF_8));
        return body;
    }
}
