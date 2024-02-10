package com.gdsc.remine.login.oauth.dto.response;

import com.gdsc.remine.member.domain.OAuthProvider;

public interface OAuthInfoResponse {

    String getSocialId();

    String getProfileImage();

    String getName();

    String getEmail();

    OAuthProvider getOAuthProvider();
}
