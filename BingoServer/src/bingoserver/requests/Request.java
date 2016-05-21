/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.requests;

/**
 *
 * @author guilherme
 */
public abstract class Request {

    private final String requestRawData;

    protected Request(String requestRawData) {
        this.requestRawData = requestRawData;
    }

    public String getRawData() {
        return requestRawData;
    }

    public abstract boolean valid();

    @Override
    public boolean equals(Object another) {
        if (another == this) {
            return true;
        }

        if (!(another instanceof Request)) {
            return false;
        }

        Request req = (Request) another;

        return requestRawData.equals(req.requestRawData);
    }

    @Override
    public String toString() {
        return "Request for: " + requestRawData;
    }

    @Override
    public int hashCode() {
        int h = super.hashCode();
        h = h * requestRawData.hashCode();
        return h;
    }
}
