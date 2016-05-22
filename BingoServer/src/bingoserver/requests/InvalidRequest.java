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
public class InvalidRequest extends Request {

    protected InvalidRequest(String requestRawData) {
        super(requestRawData);
    }

    @Override
    public boolean valid() {
        return false;
    }

    @Override
    public boolean equals(Object another) {
        if (!(another instanceof InvalidRequest)) {
            return false;
        }

        return super.equals(another);
    }

    public String toString() {
        return "Invalid Request: " + getRawData();
    }
}
