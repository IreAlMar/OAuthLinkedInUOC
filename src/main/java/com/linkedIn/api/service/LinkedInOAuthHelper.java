package com.linkedIn.api.service;

import com.linkedIn.api.utils.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.linkedIn.api.utils.Constants.*;


/**
 * LinkedIn 3-Legged OAuth Service
 */
public final class LinkedInOAuthHelper {


    static public String createAuthorizationUrl(final String state, final String redirectUri, final String apiKey, final String scope) throws UnsupportedEncodingException {
        String authoriztaionUrl = AUTHORIZE_URL
                + "?response_type=code&client_id="
                + apiKey
                + "&redirect_uri="
                + redirectUri
                + "&state="
                + state
                + "&scope="
                + URLEncoder.encode(scope, String.valueOf(StandardCharsets.UTF_8));
        return authoriztaionUrl;
    }


    public static HttpEntity buildAccessToken3LeggedRequest(final String code, final String redirectUri, final String apiKey, final String apiSecret) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add(GRANT_TYPE, Constants.GrantType.AUTHORIZATION_CODE.getGrantType());
        parameters.add(CODE, code);
        parameters.add(REDIRECT_URI, redirectUri);
        parameters.add(CLIENT_ID, apiKey);
        parameters.add(CLIENT_SECRET, apiSecret);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(HttpHeaders.USER_AGENT, USER_AGENT_OAUTH_VALUE);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
        return request;
    }

}
