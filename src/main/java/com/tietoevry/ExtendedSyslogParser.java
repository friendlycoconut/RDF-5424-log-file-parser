package com.tietoevry;

import org.apache.flume.Event;
import org.apache.flume.source.SyslogParser;

import java.nio.charset.Charset;
import java.util.*;

public class ExtendedSyslogParser extends SyslogParser {


    public long extendedParseRfc5424Date(String msg){
        return parseRfc5424Date(msg);
    }

    public List<Map<String,String>> sortParsedRfc5424Messages (List<String> messages, Charset charset, List<Event> eventsList, List<Map<String,String>> headersList){
        for (String msg : messages) {
            Set<String> keepFields = new HashSet<String>();
            Event event = parseMessage(msg, charset, keepFields);
            eventsList.add(event);
        }

        for(Event eventList: eventsList){
            headersList.add(eventList.getHeaders());
        }



        headersList.sort(Comparator.comparing(
                m -> m.get("timestamp"),
                Comparator.nullsLast(Comparator.naturalOrder()))
        );
        return headersList;
    }

}