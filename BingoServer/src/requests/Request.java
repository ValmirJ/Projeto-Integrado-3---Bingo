/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

/**
 *
 * @author guilherme
 */
public abstract class Request {

    private String requestRawData;

    protected Request(String requestRawData) {
        this.requestRawData = requestRawData;
    }

    public String getRawData() {
        return requestRawData;
    }

    public abstract boolean valid();
}
