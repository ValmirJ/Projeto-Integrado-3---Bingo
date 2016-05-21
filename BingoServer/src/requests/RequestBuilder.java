/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import java.util.regex.Pattern;

/**
 *
 * @author guilherme
 */
public class RequestBuilder {

    private final Pattern messagePattern;

    public RequestBuilder() {
        String pattern = "";
        this.messagePattern = Pattern.compile(pattern);
    }

    public Request buildRequestForMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
