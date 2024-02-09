package com.gdsc.remine.login.oauth.dto.request;

import com.gdsc.remine.member.domain.OAuthProvider;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();

    MultiValueMap<String, String> makeBody();
}
