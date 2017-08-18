package com.forestry.sopcompliance.data.remote.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by fimansya on 5/9/2017.
 */


@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace( prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
        @Namespace( prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
        @Namespace( prefix = "soap12", reference = "http://www.w3.org/2003/05/soap-envelope")
})
public class LoginRequestEnvelope {

    @Element(name = "soap12:Body", required = false)
    private LoginRequestBody body;

    public LoginRequestBody getBody() {
        return body;
    }

    public void setBody(LoginRequestBody body) {
        this.body = body;
    }

}
