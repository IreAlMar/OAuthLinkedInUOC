package com.linkedIn.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.linkedIn.api.model.LinkedInLiteProfilePojo;
import com.linkedIn.api.utils.Constants;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LinkedInService {

    private static String ME_ENDPOINT = "https://api.linkedin.com/v2/me?" + Constants.TOKEN_URL_PARAMETER_NAME + "=";
    private static String CREATE_SHARE_ENDPOINT = "https://api.linkedin.com/v2/ugcPosts?" + Constants.TOKEN_URL_PARAMETER_NAME + "=";
    private static String  DISPLAY_IMAGE = "https://api.linkedin.com/v2/me?projection=(id,localizedFirstName,localizedLastName,profilePicture(displayImage~:playableStreams))&" + Constants.TOKEN_URL_PARAMETER_NAME + "=";

    private final RestTemplate restTemplate;
    private final AccessToken accessToken;

    public LinkedInService(RestTemplate restTemplate, AccessToken accessToken) {
        this.restTemplate = restTemplate;
        this.accessToken = accessToken;
    }

    public LinkedInLiteProfilePojo getProfileInfo(){
        return restTemplate.getForObject(buildAuthenticatedUrl(ME_ENDPOINT, accessToken.getAccessToken()), LinkedInLiteProfilePojo.class);
    }

    public String getDisplayImage(){
        String response = restTemplate.getForObject(buildAuthenticatedUrl(DISPLAY_IMAGE, accessToken.getAccessToken()), String.class);
        JSONArray arrayResponse = ((JSONArray) JsonPath.read(response, "$..identifiers[0].identifier"));
        return arrayResponse.get(arrayResponse.size() -1 ).toString();
    }

    public String createShare(LinkedInLiteProfilePojo profile, String content){
        return restTemplate.postForObject(buildAuthenticatedUrl(CREATE_SHARE_ENDPOINT, accessToken.getAccessToken()), createShareBody(profile.getId(), content), String.class);
    }

    private String buildAuthenticatedUrl(final String  url, final String token) {
        return url + token;
    }

    private String createShareBody(String userId, String content){
        return "{\n" +
                "    \"author\": \"urn:li:person:"+ userId +"\",\n" +
                "    \"lifecycleState\": \"PUBLISHED\",\n" +
                "    \"specificContent\": {\n" +
                "        \"com.linkedin.ugc.ShareContent\": {\n" +
                "            \"shareCommentary\": {\n" +
                "                \"text\": \""+ content +"\"\n" +
                "            },\n" +
                "            \"shareMediaCategory\": \"NONE\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"visibility\": {\n" +
                "        \"com.linkedin.ugc.MemberNetworkVisibility\": \"PUBLIC\"\n" +
                "    }\n" +
                "}";
    }
}
