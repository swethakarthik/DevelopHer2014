package com.springapp.mvc;

import com.google.gson.Gson;
import com.hackday.LinkedInClient;
import com.hackday.Member;
import com.hackday.MemberGeetanjali;
import com.hackday.MemberSwetha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("/manage")

public class ManagerController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        System.out.print("Hello getIntro");

        String str = fetchFromDb();

        model.addAttribute("message", str);

       // System.out.println(str);
        return "hello";
    }

    public static String fetchFromDb(){
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8090/manageCandidate/rest/");

        ClientResponse responseJson = webResource.path("/manager/44645743").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);


        return responseJson.getEntity(String.class);
        //System.out.println("Candidate created" + responseJson.getStatus());
    }

    public static void main(String[] str) {
        System.out.println(fetchFromDb());
//        Gson gson=new Gson();
//        System.out.println(str);
//        System.out.println(gson.toJson(str).toString());
    }
}



