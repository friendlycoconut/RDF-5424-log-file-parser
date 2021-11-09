package com.tietoevry;

import org.apache.flume.source.SyslogParser;

public class ExtendedSyslogParser extends SyslogParser {


    public long extendedParseRfc5424Date(String msg){
        return parseRfc5424Date(msg);
    }

}