package printer;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class Format {

    static HashMap<String,Printer> printerMap;// = new HashMap<String, Printer>();
    String name;
    Printer printer;

    public Format(String name) {
        assignMap();
        this.name = name;
        this.printer = printerMap.get(name);
    }

    public String getName() {
        return this.name;
    }
    public Printer getPrinter() {
        return printer;
    }

    private void assignMap(){
        printerMap = new HashMap<String, Printer>();
        this.printerMap.put("XML",new PrinterXML());
        this.printerMap.put("JSON",new PrinterJSON());
    }
}
