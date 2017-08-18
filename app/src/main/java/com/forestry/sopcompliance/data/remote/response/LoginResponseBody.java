package com.forestry.sopcompliance.data.remote.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by fimansya on 5/9/2017.
 */

@Root(name = "Body", strict = false)
public class LoginResponseBody {

    @Element(name = "LoginToSMFResponse", required = false)
    private LoginResponseData data;

    public LoginResponseData getData() {
        return data;
    }

    public void setData(LoginResponseData data) {
        this.data = data;
    }
}

