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
public class InvalidRequest extends Request {

    protected InvalidRequest(String requestRawData) {
        super(requestRawData);
    }

    @Override
    public boolean valid() {
        return false;
    }
}
