import com.google.gson.annotations.Expose;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class Identifier {
    @Expose HashMap<String,String> identityMap;

    public Identifier() {
        this.identityMap = new HashMap<String, String>();
    }

    public void addIdentity(String key,String value){
        this.identityMap.put(key,value);
    }
}
