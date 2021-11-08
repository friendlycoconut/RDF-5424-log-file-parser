package com.tietoevry;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.apache.flume.Event;
import org.apache.flume.source.SyslogParser;
import org.apache.flume.source.SyslogUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Main extends  SyslogParser {

    public static void main(String[] args) {
        // write your code here


        final String[] examples = {
                "1985-04-12T23:20:50.52Z", "1985-04-12T19:20:50.52-04:00",
                "2003-10-11T22:14:15.003Z", "2003-08-24T05:14:15.000003-07:00",
                "2012-04-13T11:11:11-08:00", "2012-04-13T08:08:08.0001+00:00",
                "2012-04-13T08:08:08.251+00:00"
        };

        Main parser = new Main();
        DateTimeFormatter jodaParser = ISODateTimeFormat.dateTimeParser();

        for (String ex : examples) {

                 System.out.print(
                   new String(String.valueOf(parser.parseRfc5424Date(ex))) + '\n') ;
        }




        Charset charset = Charsets.UTF_8;
        List<String> messages = Lists.newArrayList();

        messages.add("<34>1 2003-10-11T22:14:15.003Z mymachine.example.com su - " +
                "ID47 - BOM'su root' failed for lonvick on /dev/pts/8");
        messages.add("<165>1 2003-08-24T05:14:15.000003-07:00 192.0.2.1 myproc " +
                "8710 - - %% It's time to make the do-nuts.");

        for (String msg : messages) {
            Set<String> keepFields = new HashSet<String>();
            Event event = parser.parseMessage(msg, charset, keepFields);


            System.out.println(event.getHeaders());
            System.out.println('\n');
        }


        Compara





        String parseMsg = new String();
parseMsg = "<165>1 2003-10-11T22:14:15.003Z mymachine.example.com evntslog - ID47 [exampleSDID@32473 iut=\"3\" eventSource=\"Application\" eventID=\"1011\"] BOMAn application event log entry..";

        Main parserEntity =  new Main();


 parserEntity.parseRfc5424Date(new String(parseMsg));

    }


}



class ExtendedSyslogParser extends SyslogParser{

    String parseMsg = new String();


}