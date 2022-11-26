package com.evanemran.kickoff.globals;

import com.evanemran.kickoff.models.User;

public class GlobalUser {
    User user;
    private static final GlobalUser globalUser = new GlobalUser();
    public static GlobalUser getInstance() {
        return globalUser;
    }
    private GlobalUser() {
    }
    public void setData(User newUser) {
        this.user = newUser;
    }
    public User getData() {
        return user;
    }
}
