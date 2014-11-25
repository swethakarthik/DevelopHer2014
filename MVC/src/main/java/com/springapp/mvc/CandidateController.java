package com.springapp.mvc;

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

@Controller
@RequestMapping("/intro")

public class CandidateController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Email sent!");

        System.out.print("Hello getIntro");
        LinkedInClient lc=new LinkedInClient();
        MemberSwetha m1 = new MemberSwetha();
        MemberGeetanjali m2 = new MemberGeetanjali();
        lc.postMessage(m1,m2,"I7Im5VzJrW","ZwE5qwMdwI","www.linkedin.com/profile/view?id=44645743");

        writeToDb(m1, m2);
        return "hello";
    }

    public static void writeToDb(Member sender, Member receiver){
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8090/manageCandidate/rest/");
        Form form = new Form();
        form.add("managerId", sender.getId());
        form.add("candidateId", receiver.getId());
        form.add("emailId", receiver.getEmail());
        form.add("isArchive", "false");
        form.add("candidateName", receiver.getFirstName()+" "+receiver.getLastName());
        ClientResponse responseJson = webResource.path("/CandidateCreation/create").type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, form);
        System.out.println("Candidate created" + responseJson.getStatus());
    }

    public static void main(String[] args) {
        //writeToDb();
    }
}



