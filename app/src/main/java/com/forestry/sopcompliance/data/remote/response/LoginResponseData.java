package com.forestry.sopcompliance.data.remote.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by fimansya on 5/9/2017.
 */

@Root(name = "LoginToSMFResponse", strict = false)
@Namespace(reference = "http://Smf.EmployeeLogin/")
public class LoginResponseData {

    @Element(name = "LoginToSMFResult", required = false)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
