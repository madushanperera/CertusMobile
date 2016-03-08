package app.certus.com.SessionManage;

import java.util.HashMap;

/**
 * Created by shanaka on 3/3/16.
 */
public class Session {

    private HashMap<String, Object> sessionMap;

    public Session() {
        this.sessionMap = new HashMap<>();
    }

    public Object getAttribute(String name) {
        return this.sessionMap.get(name);
    }

    public void setAttribute(String name, Object value) {
        this.sessionMap.put(name, value);
    }

    public void removeAttribute(String name) {
        this.sessionMap.remove(name);
    }

    public void invalidateSession() {
        this.sessionMap.clear();
    }
}
