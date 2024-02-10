package com.gdsc.remine.login.oauth.api;

import com.gdsc.remine.login.oauth.dto.request.OAuthLoginParams;
import com.gdsc.remine.login.oauth.dto.response.GoogleInfoResponse;
import com.gdsc.remine.login.oauth.dto.response.GoogleTokens;
import com.gdsc.remine.login.oauth.dto.response.OAuthInfoResponse;
import com.gdsc.remine.member.domain.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GoogleApiClient implements OAuthApiClient {

    private static final String GRANT_TYPE = "authorization_code";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.google.token-uri}")
    private String tokenUri;

    @Value("${spring.security.google.resource-uri}")
    private String resourceUri;

    private final RestTemplate restTemplate;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.GOOGLE;
    }

    @Override
    public String requestAccessToken(OAuthLoginParams params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final MultiValueMap<String, String> body = params.makeBody();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("grant_type", GRANT_TYPE);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        final GoogleTokens googleTokens = restTemplate.postForObject(tokenUri, request, GoogleTokens.class);

        assert googleTokens != null;
        System.out.println("access token : " + googleTokens.getAccessToken());
        return googleTokens.getAccessToken();
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> request = new HttpEntity<>(httpHeaders);
        System.out.println(restTemplate.exchange(resourceUri, HttpMethod.GET, request, String.class).getBody());

        return restTemplate.exchange(resourceUri, HttpMethod.GET, request, GoogleInfoResponse.class).getBody();
    }
}
