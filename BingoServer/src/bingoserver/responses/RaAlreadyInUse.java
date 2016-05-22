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
public class RaAlreadyInUse extends ResponseWithParams {

    public RaAlreadyInUse(ParamGroups paramGroups) {
        super(paramGroups);
    }

    @Override
    public String responseData() {
        return "ra" + getParamGroups().getParamGroup(0).toString() + "already_in_use";
    }

}
