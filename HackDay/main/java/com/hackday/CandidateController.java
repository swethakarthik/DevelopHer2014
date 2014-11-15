package com.hackday;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.hackday.JsonParser;
import com.hackday.LinkedInClient;
import com.hackday.Member;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ywei on 11/14/14.
 */
@Controller
public class CandidateController extends AbstractController
{
    @RequestMapping(value = "/candidate", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody static ModelAndView getCandidates()
    public String doGet(final Model model, final HttpServletRequest request)
            throws IOException
    {
        // LinkedInClient api = new LinkedInClient();
        // Member mem1 = JsonParser.getMemberByJson(api.getMemberDetails("eo24dMcYmK"));

        System.out.print("Hello");
        Member memberYawen = new MemberYawen();
        Member memberSwetha = new MemberSwetha();

        CandidatePage page=new CandidatePage();
        page.candidateList.add(memberSwetha);
        page.candidateList.add(memberYawen);

        ModelAndView mv=new ModelAndView(CandidatePage.PAGENAME, CandidatePage.MODULENAME, page);
        return CandidatePage.PAGENAME;
    }

    @RequestMapping(value = "/candidate", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody static ModelAndView getIntro()
    {
        LinkedInClient lc=new LinkedInClient();
        MemberSwetha m1 = new MemberSwetha();
        MemberGeetanjali m2 = new MemberGeetanjali();
        lc.postMessage(m1,m2,"I7Im5VzJrW","ZwE5qwMdwI","www.linkedin.com/profile/view?id=44645743");
        return new ModelAndView();
    }

    public static void main(String[] args) {
        //getCandidates();
    }

    protected ModelAndView handleRequestInternal(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse)
            throws java.lang.Exception
    {
        return new ModelAndView();
    }

}

