package com.forestry.sopcompliance.data.remote.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by fimansya on 5/9/2017.
 */

@Root(name = "soap12:Body", strict = false)
public class LoginRequestBody {

    @Element(name = "LoginToSMF",required = false)
    private LoginRequestData loginRequestData;

    public LoginRequestData getLoginRequestData() {
        return loginRequestData;
    }

    public void setLoginRequestData(LoginRequestData loginRequestData) {
        this.loginRequestData = loginRequestData;
    }

}
