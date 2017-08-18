package com.forestry.sopcompliance.data.remote.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by fimansya on 5/9/2017.
 */

@Root(name = "LoginToSMF", strict = false)
@Namespace(reference = "http://Smf.EmployeeLogin/")
public class LoginRequestData {

    @Element(name = "UIDLogin", required = false)
    private String UIDLogin;

    public String getUIDLogin() {
        return UIDLogin;
    }

    public void setUIDLogin(String UIDLogin) {
        this.UIDLogin = UIDLogin;
    }

    @Element(name = "password", required = false)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Element(name = "IDLocation", required = false)
    private String IDLocation;

    public String getIDLocation() {
        return IDLocation;
    }

    public void setIDLocation(String IDLocation) {
        this.IDLocation = IDLocation;
    }

}
