package com.linkedIn.api.controller;

import com.linkedIn.api.model.LinkedInLiteProfilePojo;
import com.linkedIn.api.service.AccessToken;
import com.linkedIn.api.service.LinkedInService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

import static com.linkedIn.api.utils.Constants.IRENE_MAIN_PAGE;
import static com.linkedIn.api.utils.Constants.THREE_LEGGED_TOKEN_GEN_ENDPOINT;

/**
 * Main controller called by spring-boot to handle OAuth actions at
 * http://localhost:8989 (Default)
 */

@Controller
public final class MainController {
    private final RestTemplate restTemplate;
    private Logger logger = Logger.getLogger(MainController.class.getName());

    private final AccessToken accessToken;
    private final LinkedInService linkedInService;

    @Value("${SERVER_URL}")
    private String SERVER_URL;

    public MainController(RestTemplate restTemplate, AccessToken accessToken, LinkedInService linkedInService) {
        this.restTemplate = restTemplate;
        this.accessToken = accessToken;
        this.linkedInService = linkedInService;
    }

    @GetMapping("/")
    public String mainPage(final Model model) {
        model.addAttribute("auth_url", SERVER_URL + THREE_LEGGED_TOKEN_GEN_ENDPOINT);
        if (accessToken.isExpired()) {
            model.addAttribute("output", "Invalid or expired token");
            return IRENE_MAIN_PAGE;
        }
        model.addAttribute("output", accessToken.toString());
        LinkedInLiteProfilePojo profile = linkedInService.getProfileInfo();
        model.addAttribute("profileData", profile.toString());
        model.addAttribute("profileImage", linkedInService.getDisplayImage());
        linkedInService.createShare(profile, "Hello world! This token will expire on " + accessToken.getExpirationDate().toString());

        return IRENE_MAIN_PAGE;
    }

}
