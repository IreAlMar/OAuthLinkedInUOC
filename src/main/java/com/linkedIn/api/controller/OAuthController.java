package com.linkedIn.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkedIn.api.service.AccessToken;
import com.linkedIn.api.model.AccessTokenPojo;
import com.linkedIn.api.service.LinkedInOAuthHelper;
import com.linkedIn.api.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class OAuthController {

    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;
    @Value("${redirect_uri}")
    private String redirectUri;
    @Value("${scope}")
    private String scope;
    @Value("${client_url}")
    private String clientUrl;

    private final AccessToken storedAccessToken;

    private Logger logger = Logger.getLogger(OAuthController.class.getName());

    public OAuthController(AccessToken accessToken) {
        this.storedAccessToken = accessToken;
    }

    @GetMapping(value = "/auth-code-grant")
    public RedirectView externalLoginServerCallingUsWithACode(@RequestParam(name = "code") final String code) throws Exception {
        RedirectView redirectView = new RedirectView();

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        logger.log(Level.INFO, "Authorization code not empty, trying to generate a 3-legged OAuth token.");

        HttpEntity request = LinkedInOAuthHelper.buildAccessToken3LeggedRequest(code, redirectUri, clientId, clientSecret);
        String response = restTemplate.postForObject(Constants.REQUEST_TOKEN_URL, request, String.class);

        AccessTokenPojo accessTokenResponse = objectMapper.readValue(response, AccessTokenPojo.class);
        storedAccessToken.setToken(accessTokenResponse.getAccessToken(), Integer.parseInt(accessTokenResponse.getExpiresIn()));
        
        logger.log(Level.INFO, "Generated Access token.");

        redirectView.setUrl(clientUrl);
        return redirectView;
    }

    @GetMapping(value = "/login")
    public RedirectView redirectToExternalLogin() throws Exception {
        RedirectView redirectView = new RedirectView();

        final String secretState = "secret" + new Random().nextInt(999_999);
        final String authorizationUrl = LinkedInOAuthHelper.createAuthorizationUrl(secretState, redirectUri, clientId, prepareScope(scope));

        redirectView.setUrl(authorizationUrl);

        return redirectView;
    }

    private String prepareScope(String scope) {
        return scope.trim().replace(" ", "").replace(",", " ");
    }
}
