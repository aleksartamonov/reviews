package parser.factory;

import parser.PageParser;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
public interface PageParserFactory {
    PageParser getParser();
}
