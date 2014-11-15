package com.hackday;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ywei on 11/14/14.
 */

public class JsonParser {
    public static Member getMemberByName(String name) {

        Gson gson = new Gson();
        Member member = null;
        try {
            String path = "/" + name + ".json";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            InputStream is = JSONParser.class.getResourceAsStream(path);

            //convert the json string back to object
            member = gson.fromJson(is.toString(), Member.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

    public static Member getMemberByJson(String json) {

        Gson gson = new Gson();
        Member member = null;
        try {
            //convert the json string back to object
            member = gson.fromJson(json, Member.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

}
