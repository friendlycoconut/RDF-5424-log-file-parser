import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.tietoevry.ExtendedSyslogParser;
import org.apache.flume.Event;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ExtendedSyslogParser parser = new ExtendedSyslogParser();
        DateTimeFormatter jodaParser = ISODateTimeFormat.dateTimeParser();

        Charset charset = Charsets.UTF_8;
        List<String> messages = Lists.newArrayList();
        List<Event> eventsList = Lists.newArrayList();
        List<Map<String,String>> headersList = Lists.newArrayList();

        messages.add("<34>1 2003-10-11T22:14:15.003Z mymachine.example.com su - " +
                "ID47 - BOM'su root' failed for lonvick on /dev/pts/8");
        messages.add("<165>1 2003-08-24T05:14:15.000003-07:00 192.0.2.1 myproc " +
                "8710 - - %% It's time to make the do-nuts.");

        for (String msg : messages) {
            Set<String> keepFields = new HashSet<String>();
            Event event = parser.parseMessage(msg, charset, keepFields);
            eventsList.add(event);
            System.out.println(event);
            System.out.println(event.getHeaders());
            System.out.println('\n');
        }

        for(Event eventList: eventsList){
            headersList.add(eventList.getHeaders());
        }



        headersList.sort(Comparator.comparing(
                m -> m.get("timestamp"),
                Comparator.nullsLast(Comparator.naturalOrder()))
        );

        System.out.println(headersList);

        PrintWriter pw = new PrintWriter(new FileWriter("test.html"));
        pw.println("<TABLE BORDER><TR><TH>Number<TH>Square of Number</TR>");
        for (Map<String,String> header: headersList) {
            int square = i * i;
            pw.println("<TR><TD>" + i + "<TD>" + square);
        }
        pw.println("</TABLE>");
        pw.close();
    }
}
