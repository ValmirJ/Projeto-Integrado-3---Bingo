/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parameters;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class ParamGroups {

    // Relembre dos comandos. Ex:
    // sala<id>usuarios<ra,ra,ra...>
    //
    // Cada mensagem pode ter multíplos ParamGroup.
    //
    // Essa classe deve armazenar cada grupo de parametros de uma mensagem.
    // Note ela não tem conhecimento da mensagem inteira.
    //
    // Portanto para uma Dada a mensagem:
    //
    // sala<id>usuarios<ra,ra,ra...>
    //
    // ela tem a reponsabilidade de armazenar vários ParamGroup em ordem:
    // paramGroups.get(0) => ParamGroup
    // paramGroups.get(1) => ParamGroup
    //
    // Sendo que ao executar:
    //
    // paramGroups.get(0).toString() => "<id>"
    // paramGroups.get(1).toString() => "<ra,ra,ra,...>"
    private List<ParamGroup> paramGroups = new ArrayList<>();

    public ParamGroups(List<ParamGroup> paramGroups) {
        this.paramGroups.addAll(paramGroups);
    }

    public List<ParamGroup> getParamGroups() {
        return paramGroups;
    }
}
