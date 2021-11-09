package com.tietoevry;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class HTMLfileCreator {
    public static String map2HTML(List<Map<String,String>> list) throws Exception{
        StringBuilder html = new StringBuilder(
                " <!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<body>\n"+
                        "<table>");

        for(String key :list.get(0).keySet()){
            html.append("<th>" + key + "</th>");
        }


        for(Map<String,String> map: list){
            html.append("\n<tr>");
            for (Map.Entry<String,String> entry : map.entrySet()){
                if(entry.getKey()=="timestamp"){
                    long timestamp=Long.parseLong(entry.getValue());
                    java.util.Date time=new java.util.Date((long)timestamp);
                    html.append("<td>" + time + "</td>");
                }else{
                    html.append("<td>" + entry.getValue() + "</td>");
                }
            }
            html.append("</tr>\n");
        }

        html.append("</table>\n"+
                    "</body>\n"+
                    "</html>\n");

        return html.toString();
    }


    public static void write2HTML(String filepath,String htmlString){
        FileWriter fWriter = null;
        BufferedWriter writer = null;
        try {
            fWriter = new FileWriter(filepath);
            writer = new BufferedWriter(fWriter);
            writer.write(htmlString);
            writer.newLine(); //this is not actually needed for html files - can make your code more readable though
            writer.close(); //make sure you close the writer object
        } catch (Exception e) {
            //catch any exceptions here
        }
    }


}
