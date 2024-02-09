package com.gdsc.remine.login.oauth.service;

import com.gdsc.remine.login.oauth.api.OAuthApiClient;
import com.gdsc.remine.login.oauth.dto.request.OAuthLoginParams;
import com.gdsc.remine.login.oauth.dto.response.OAuthInfoResponse;
import com.gdsc.remine.member.domain.OAuthProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }

    public void unlink(OAuthProvider oAuthProvider, String socialAccessToken) {
        final OAuthApiClient oAuthApiClient = clients.get(oAuthProvider);
        oAuthApiClient.requestUnlink(socialAccessToken);
    }
}
