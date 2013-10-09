package printer;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public enum Format {
    XMl("XML"),
    JSON("JSON");

    private final String val;

    Format(String val) {
        this.val = val;
    }

    public String val() {
        return val;
    }
}
