package hack.manage.core;

        import org.scribe.builder.ServiceBuilder;
        import org.scribe.builder.api.LinkedInApi;
        import org.scribe.model.OAuthRequest;
        import org.scribe.model.Response;
        import org.scribe.model.Token;
        import org.scribe.model.Verb;
        import org.scribe.oauth.OAuthService;

        import java.util.ArrayList;

public class LinkedinClient
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
                new OAuthRequest(Verb.GET, "http://api.linkedin.com/v1/people/~"+":(id,first-name,last-name,industry,positions,site-standard-profile-request,skills,educations,picture-url,email-address)?format=json");
        getService().signRequest(getToken(), request);
        Response response = request.send();
        return response.getBody();
    }

    public String getMyMemberDetails()
    {
        OAuthRequest request =
                new OAuthRequest(Verb.GET, "http://api.linkedin.com/v1/people/~" + ":(id,first-name,last-name,industry,positions,site-standard-profile-request,skills,educations,email-address)?format=json");
        getService().signRequest(getToken(), request);
        Response response = request.send();
        return response.getBody();
    }

    public String postMessage1(Member fromMember,
                              Member toMemberId,
                              String jobPoster,
                              String message,
                              String jobId,
                              double strength)
    {

        String msg =
                String.format("Hi Recuiter, I would like to recommend %s (%s) for jobId %s; %s   LinkedIn relationship confidence factor between %s and %s is %s",
                        toMemberId.firstName,
                        "<a href='www.google.com'> www.google.com </a>",
                        jobId,
                        message,
                        fromMember.firstName,
                        toMemberId.firstName,
                        strength
                );

        String jsonS =
                String.format("{\"recipients\":{\"values\":[{\"person\": {\"_path\": \"/people/%s\"}}]},\"subject\":\"Recommendation for job %s \",\"body\":\"%s\"}",
                        jobPoster,
                        jobId,
                        msg);

        OAuthRequest request =
                new OAuthRequest(Verb.POST, "http://api.linkedin.com/v1/people/~/mailbox");
        request.addPayload(jsonS);
        request.addHeader("content-type", "application/json");
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

    return new Token("b0bcef58-a255-4829-9e78-3d4b0b05ceaf",
                     "efe7012f-c02b-4177-9468-269b4065b001");
//        return new Token("e29f534a-bce0-42c8-bec7-16b14ae29a98",
//                "e45faf3d-3212-49af-9acb-9dc79f22b05d");

//        return new Token("3faee411-7b4a-4fcd-974e-4ca6df415b5a",
//                "573df0b8-9d4b-418a-aa23-c04a49bac1dc");


    }




//    public String getMemberProfile(long memberProfileID)
//    {
//        String url =
//                String.format("https://api.linkedin.com/v1/people-search?first-name=%s&last-name=%s&company-name=%s&current-company=%s&sort=connections",
//                        fname,
//                        lname, prevEmp,
//                        currentEmp);
//
//        OAuthRequest request = new OAuthRequest(Verb.GET, url);
//        getService().signRequest(getToken(), request);
//        Response response = request.send();
//        return response.getBody();
//    }

    public static void main(String[] args)
    {
        LinkedinClient lc = new LinkedinClient();

//        MemberSwetha m1 = new MemberSwetha();
//        MemberGeetanjali m2 = new MemberGeetanjali();
//
//        lc.postMessage(m1,m2,"I7Im5VzJrW","ZwE5qwMdwI","www.linkedin.com/profile/view?id=44645743");
        //   String search = lc.search("geetanjali", "gupta", "", "");
       // System.out.println(search);
    String memberDetails = lc.getMemberDetails("eo24dMcYmK");
    System.out.println(memberDetails);

//        public String postMessage(Member fromMember,
//            Member toMemberId,
//            String jobPoster,
//            String message,
//            String jobId,
//        double strength)

       // String m1 = lc.getMemberDetails("Swetha");
   //     Member mem1 = JsonParser.getMember("Ss");

//        String     id;
//        String     firstName;
//        String     lastName;
//        String     industry;
//        ProfileUrl siteStandardProfileRequest;
//        Skills     skills;
//        Education  educations;
//        Position   positions;
//
//
//        Member m1 = new Member();
//        m1.id="I7Im5VzJrW";
//        m1.firstName="Swetha";
//        m1.lastName="Karthik";
//        m1.industry="Computer Software";
//        ProfileUrl url = new ProfileUrl();
//        url.url="https://www.linkedin.com/profile/view?id=44645743";
//        m1.siteStandardProfileRequest=url;
//
//        Position p1 =  new Position();
//        p1._total=5;
//        p1.values=null;

//


//        id = String.valueOf(57145094);
//        String firstName = "Yawen";
//        String lastName = "Wei";
//        String imageUrl = "https://media.licdn.com/mpr/mprx/0_r9mLMB8-68ttKfx7tKS9Mq3fX65KrEa7tNssMzKfRilTfaJfyrYdcvX3oeLmPSf_AzuRntaV5ukl";
//        String siteStandardProfileRequest = new ProfileUrl("https://www.linkedin.com/profile/view?id=57145094");


    }
}