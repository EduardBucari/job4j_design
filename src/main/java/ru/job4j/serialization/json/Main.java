package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonIdNumberXML = new JSONObject("{\"idNumberXML\":\"123-45\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("piton developer");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final ITProgrammerXML itProgrammerXML = new ITProgrammerXML(
                false, 8, new IdNumberXML("123-45"), "java developer", "Free");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oracleCertification", itProgrammerXML.isOracleCertification());
        jsonObject.put("monthOfPractice", itProgrammerXML.getMonthOfPractice());
        jsonObject.put("idNumber", jsonIdNumberXML);
        jsonObject.put("statuses", jsonStatuses);
    }
}
