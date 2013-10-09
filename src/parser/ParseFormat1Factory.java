package parser;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class ParseFormat1Factory implements PageParserFactory {

    @Override
    public PageParser getParser() {
        return new PageParserFormat1();
    }
}
