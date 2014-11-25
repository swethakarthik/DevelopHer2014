package com.hackday;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class LinkedInClient
{

    public String search(String fname, String lname, String prevEmp, String currentEmp)
    {
        String url =
                String.format("https://api.linkedin.com/v1/people-search?first-name=%s&last-name=%s&company-name=%s&current-company=%s&sort=connections",
                        fname,
                        lname, prevEmp,
                        currentEmp);

        OAuthRequest request = new OAuthRequest(Verb.GET, url);
        getService().signRequest(getToken(), request);
        Response response = request.send();
        return response.getBody();
    }

    public String getMemberDetails(String id)
    {
        OAuthRequest request =
                new OAuthRequest(Verb.GET, "http://api.linkedin.com/v1/people/id=" + id + ":(id,email-address,first-name,last-name,industry,positions,site-standard-profile-request,skills,educations,picture-url)?format=json");
        getService().signRequest(getToken(), request);
        Response response = request.send();
        return response.getBody();
    }


    public String postMessage(Member fromMember,
                              Member toMemberId,
                              String sender,
                              String recipient,
                              String url)
    {

        String msg =
                String.format("Hi %s, I am the Director of the Ads Engineering Group at Linkedin." +
                                "I am very impressed with your profile and would like to have a chat with you " +
                                "about an opportunity we have in our team; please go ahead and fill out the below form %s, if you " +
                                "would be interested in learning more about it. I look forward to you reply!",
                        toMemberId.firstName,
                        url

                );

        String jsonS =
                String.format("{\"recipients\":{\"values\":[{\"person\": {\"_path\": \"/people/%s\"}}]},\"subject\":\"Knock Knock\",\"body\":\"%s\"}",
                        recipient,
                        msg);

        OAuthRequest request =
                new OAuthRequest(Verb.POST, "http://api.linkedin.com/v1/people/~/mailbox");
        request.addPayload(jsonS);
        request.addHeader("content-type", "application/json");
        getService().signRequest(getToken(), request);
        Response response = request.send();
        return response.getBody();
    }

    private OAuthService getService()
    {
        return new ServiceBuilder().provider(LinkedInApi.class)
                .apiKey("7gklm04sx1vp")
                .apiSecret("wLiNuG3tJkyrz1FQ")
                .build();
    }

    private Token getToken()
    {
        // Swetha's token
        //return new Token("e29f534a-bce0-42c8-bec7-16b14ae29a98", "e45faf3d-3212-49af-9acb-9dc79f22b05d");

        // Yawen's token
        return new Token("b0bcef58-a255-4829-9e78-3d4b0b05ceaf", "efe7012f-c02b-4177-9468-269b4065b001");
    }

    public static void main(String[] args)
    {
        int YawenMemberId=57145094;
        String YawenMemberIdStr1="eo24dMcYmK";
        String YawenMemberIdStr2="aRUt8iTxqT";
        String YawenMemberIdStr3="0FXcJQ1tV3";

        LinkedInClient lc = new LinkedInClient();
        String search = lc.search("Yawen", "Wei", "", "");
        System.out.println();
        String memberDetails = lc.getMemberDetails(String.valueOf(YawenMemberIdStr1));
        System.out.println(memberDetails);
    }
}
