/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import bingoserver.parameters.ParamGroups;

/**
 *
 * @author guilherme
 */
public abstract class ResponseWithParams extends Response {

    private final ParamGroups paramGroups;

    public ResponseWithParams(ParamGroups paramGroups) {
        this.paramGroups = paramGroups;
    }

    protected ParamGroups getParamGroups() {
        return paramGroups;
    }
}
