package com.gdsc.remine.login.oauth.api;

import com.gdsc.remine.login.oauth.dto.request.OAuthLoginParams;
import com.gdsc.remine.login.oauth.dto.response.OAuthInfoResponse;
import com.gdsc.remine.member.domain.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();

    String requestAccessToken(OAuthLoginParams params);

    OAuthInfoResponse requestOauthInfo(String accessToken);

    void requestUnlink(String socialAccessToken);
}
