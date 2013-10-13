package printer;

import printer.impl.PrinterJSON;
import printer.impl.PrinterXML;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class Format {

    public static HashMap<String, Printer> printerMap;
    private String name;
    private Printer printer;

    public Format(String name) {
        assignMap();
        this.name = name;
        this.printer = printerMap.get(name);
    }
    public Format(){
        assignMap();
    }

    public Printer getPrinter() {
        return printer;
    }

    private static void assignMap() {
        printerMap = new HashMap<String, Printer>();
        printerMap.put("XML", new PrinterXML());
        printerMap.put("JSON", new PrinterJSON());
    }
}
