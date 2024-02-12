package com.gdsc.remine.login.oauth.api;

import com.gdsc.remine.login.oauth.dto.request.OAuthLoginParams;
import com.gdsc.remine.login.oauth.dto.response.OAuthInfoResponse;
import com.gdsc.remine.login.oauth.dto.response.SocialTokens;
import com.gdsc.remine.member.domain.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();

    SocialTokens requestAccessToken(OAuthLoginParams params);

    OAuthInfoResponse requestOauthInfo(SocialTokens socialTokens);
}
