/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import bingo.network.ClientManager;

/**
 *
 * @author 15174782
 */
public class FormManager {
    private TelaInicial telaInicial;
    private TelaJogo telaJogo;
    private TelaResultado telaResultado;
    private TelaSalas telaSalas;
    private ClientManager clientManager;

    public TelaInicial getTelaInicial() {
        if(this.telaInicial == null)
            this.telaInicial = new TelaInicial(clientManager);
        
        return telaInicial;
    }

    public TelaJogo getTelaJogo() {
        if(this.telaJogo == null)
            this.telaJogo = new TelaJogo();
        
        return telaJogo;
    }

    public TelaResultado getTelaResultado() {
        if(this.telaResultado == null)
            this.telaResultado = new TelaResultado();
        
        return telaResultado;
    }

    public TelaSalas getTelaSalas() {
        if(this.telaSalas == null)
            this.telaSalas = new TelaSalas(this.telaInicial.getRa());
        
        return telaSalas;
    }
    
    public FormManager(ClientManager cl) {
        this.clientManager = cl;
    }
}
