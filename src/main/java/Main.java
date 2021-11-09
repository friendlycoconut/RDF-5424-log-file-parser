import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.tietoevry.ExtendedSyslogParser;
import com.tietoevry.HTMLfileCreator;
import com.tietoevry.ParserFileReader;
import org.apache.flume.Event;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;

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
