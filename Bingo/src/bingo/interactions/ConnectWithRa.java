/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import bingo.FormManager;
import bingo.TelaInicial;
import bingo.TelaSalas;
import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class ConnectWithRa extends Interactor{
   
    private TelaInicial telaInicial;
    private TelaSalas telaSalas;
    
    public ConnectWithRa() {
        super();
    }
    
    public void perform(JSONObject params ) throws Exception {
        telaInicial = this.getFormManager().getTelaInicial();
        String typeReturned = (String) params.get("type");
        if(typeReturned.equals("ra-em-uso"))
            telaInicial.showRaAlreadyUsed();
        else {
            if(typeReturned.equals("ra-invalido"))
                telaInicial.showInvalidRa();
            else {
                    getFormManager().hideCurrent();
                    this.telaSalas = this.getFormManager().getTelaSalas();
                    this.telaSalas.setVisible(true);
            }
        }
    }
}
