package logger;

import parser.impl.PageParserFormat1;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
public class Logger {
    // global static LOG for Logging all bad things
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PageParserFormat1.class);
}
