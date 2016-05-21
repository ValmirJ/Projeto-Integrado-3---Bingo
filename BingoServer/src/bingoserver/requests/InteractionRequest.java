/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.requests;

import bingoserver.interactions.UserInteractor;
import bingoserver.parameters.ParamGroups;

/**
 *
 * @author guilherme
 */
public class InteractionRequest extends Request {

    private Class<? extends UserInteractor> interactor;
    private ParamGroups params;

    protected InteractionRequest(String requestRawData) {
        super(requestRawData);
    }

    protected InteractionRequest(String requestRawData, Class<? extends UserInteractor> interactor, ParamGroups params) {
        this(requestRawData);
        this.interactor = interactor;
        this.params = params;
    }

    public Class<? extends UserInteractor> getInteractorClass() {
        return interactor;
    }

    public ParamGroups getParams() {
        return params;
    }

    @Override
    public boolean valid() {
        return true;
    }
}
