package com.tietoevry;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.apache.flume.Event;

import java.awt.*;
import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        List<String> messages = Lists.newArrayList();
        ExtendedSyslogParser parser = new ExtendedSyslogParser();
        Charset charset = Charsets.UTF_8;
        List<Event> eventsList = Lists.newArrayList();
        List<Map<String,String>> headersList = Lists.newArrayList();

        messages = ParserFileReader.parserFileReaderMethod("src/main/resources/syslog_example.log",messages);

        headersList = parser.sortParsedRfc5424Messages(messages,charset,eventsList,headersList);

        System.out.println(headersList);

        String HTMLcode = HTMLfileCreator.map2HTML(headersList);
        HTMLfileCreator.write2HTML("src/main/resources/syslog_example_parsed.html",HTMLcode);

        File htmlFile = new File("src/main/resources/syslog_example_parsed.html");
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
}
