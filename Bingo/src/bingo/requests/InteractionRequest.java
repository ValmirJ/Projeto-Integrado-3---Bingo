/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.requests;

import bingo.interactions.Interactor;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class InteractionRequest {

    private Class<? extends Interactor> interactor;
    private JSONObject requestJson;
    String requestData;

    InteractionRequest(String requestData) {
        this.requestData = requestData;
    }

    protected InteractionRequest(String requestData, Class<? extends Interactor> interactor, JSONObject object) {
        this.requestData = requestData;
        this.interactor = interactor;
        this.requestJson = object;
    }

    public Class<? extends Interactor> getInteractorClass() {
        return interactor;
    }

    public JSONObject getRequestJson() {
        return requestJson;
    }
}
