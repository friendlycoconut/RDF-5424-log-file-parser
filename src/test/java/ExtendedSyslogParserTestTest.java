import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.tietoevry.ExtendedSyslogParser;
import org.apache.flume.Event;
import org.apache.flume.source.SyslogParser;
import org.apache.flume.source.SyslogUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ExtendedSyslogParserTestTest {

    @Test
    public void testRfc5424DateParsing() {
        final String[] examples = {
                "1985-04-12T23:20:50.52Z", "1985-04-12T19:20:50.52-04:00",
                "2003-10-11T22:14:15.003Z", "2003-08-24T05:14:15.000003-07:00",
                "2012-04-13T11:11:11-08:00", "2012-04-13T08:08:08.0001+00:00",
                "2012-04-13T08:08:08.251+00:00"
        };

        ExtendedSyslogParser syslogParser = new ExtendedSyslogParser();
        DateTimeFormatter jodaParser = ISODateTimeFormat.dateTimeParser();

        for (String ex : examples) {
            Assertions.assertEquals(jodaParser.parseMillis(ex), syslogParser.extendedParseRfc5424Date(ex));

        }
    }

    @Test
    public void testMessageParsing() {
        SyslogParser parser = new SyslogParser();
        Charset charset = Charsets.UTF_8;
        List<String> messages = Lists.newArrayList();

        // supported examples from RFC 5424
        messages.add("<34>1 2003-10-11T22:14:15.003Z mymachine.example.com su - " +
                "ID47 - BOM'su root' failed for lonvick on /dev/pts/8");
        messages.add("<165>1 2003-08-24T05:14:15.000003-07:00 192.0.2.1 myproc " +
                "8710 - - %% It's time to make the do-nuts.");

        // test with default keepFields = false
        for (String msg : messages) {
            Set<String> keepFields = new HashSet<String>();
            Event event = parser.parseMessage(msg, charset, keepFields);
            Assertions.assertNull(event.getHeaders().get(SyslogUtils.EVENT_STATUS));
        }
    }

    }