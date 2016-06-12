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
   
    private FormManager fm = this.getFormManager();
    private TelaInicial telaInicial = this.fm.getTelaInicial();
    private TelaSalas telaSalas;
    
    public void perform(JSONObject params ) throws Exception {
        String typeReturned = (String) params.get("type");
        if(typeReturned.equals("ra-em-uso"))
            telaInicial.showRaAlreadyUsed();
        else {
            if(typeReturned.equals("ra-invalido"))
                telaInicial.showInvalidRa();
            else {
                if(typeReturned.equals("logado-no-jogo")) {
                    telaInicial.setVisible(false);
                    this.telaSalas = fm.getTelaSalas();
                    this.telaSalas.setVisible(true);
                }
            }
        }
    }
}
