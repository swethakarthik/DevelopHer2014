package com.hackday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ywei on 11/14/14.
 */
public class CandidatePage {
    public static String PAGENAME="candidate_page";
    public static String MODULENAME="pageBean";
    public List<Member> candidateList;

    public CandidatePage() {
        candidateList=new ArrayList<Member>();
    }
}
