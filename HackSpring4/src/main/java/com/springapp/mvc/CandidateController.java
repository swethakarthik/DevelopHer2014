package com.springapp.mvc;

import com.hackday.LinkedInClient;
import com.hackday.MemberGeetanjali;
import com.hackday.MemberSwetha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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

        return "hello";
    }
}

//    @RequestMapping(value = "/intro")
//    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody static ModelAndView getIntro()
//    {
//        System.out.print("Hello getIntro");
////        LinkedInClient lc=new LinkedInClient();
////        MemberSwetha m1 = new MemberSwetha();
////        MemberGeetanjali m2 = new MemberGeetanjali();
////        lc.postMessage(m1,m2,"I7Im5VzJrW","ZwE5qwMdwI","www.linkedin.com/profile/view?id=44645743");
//        return new ModelAndView();
//    }
//
//    public static void main(String[] args) {
//        //getCandidates();
//    }
//

