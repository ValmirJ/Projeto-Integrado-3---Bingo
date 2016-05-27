/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.requests;

import bingoserver.interactions.UserInteractor;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class InteractionRequest extends Request {

    private Class<? extends UserInteractor> interactor;
    private JSONObject requestJson;

    InteractionRequest(String requestRawData) {
        super(requestRawData);
    }

    InteractionRequest(String requestRawData, Class<? extends UserInteractor> interactor, JSONObject object) {
        super(requestRawData);
        this.interactor = interactor;
        this.requestJson = object;
    }

    public Class<? extends UserInteractor> getInteractorClass() {
        return interactor;
    }

    public JSONObject getRequestJson() {
        return requestJson;
    }

    @Override
    public boolean valid() {
        return true;
    }
}
