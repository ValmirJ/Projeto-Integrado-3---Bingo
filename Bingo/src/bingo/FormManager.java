/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import bingo.network.ClientManager;
import javax.swing.JFrame;

/**
 *
 * @author 15174782
 */
public class FormManager {
    private TelaInicial telaInicial;
    private TelaJogo telaJogo;
    private TelaResultado telaResultado;
    private TelaSalas telaSalas;
    private SalaDeEspera telaSalaDeEspera;
    private ClientManager clientManager;

    public TelaInicial getTelaInicial() {
        if(this.telaInicial == null)
            this.telaInicial = new TelaInicial(clientManager);
        
        return telaInicial;
    }

    public TelaJogo getTelaJogo() {
        if(this.telaJogo == null)
            this.telaJogo = new TelaJogo(clientManager);
        
        return telaJogo;
    }

    public TelaResultado getTelaResultado() {
        if(this.telaResultado == null)
            this.telaResultado = new TelaResultado(clientManager);
        
        return telaResultado;
    }

    public TelaSalas getTelaSalas() {
        if(this.telaSalas == null)
            this.telaSalas = new TelaSalas(this.clientManager, this.telaInicial.getRa());
        
        return telaSalas;
    }
  
    public SalaDeEspera getTelaSalaDeEspera() {
        if(this.telaSalaDeEspera == null)
            this.telaSalaDeEspera = new SalaDeEspera(this.clientManager);
        
        return this.telaSalaDeEspera;
    }
    
    public FormManager(ClientManager cl) {
        this.clientManager = cl;
    }
    
    public Tela getCurrentForm() {
        if(this.telaInicial != null && this.telaInicial.isVisible())
            return this.telaInicial;
        
        if(this.telaSalas != null && this.telaSalas.isVisible())
            return this.telaSalas;
        
        if(this.telaSalaDeEspera != null && this.telaSalaDeEspera.isVisible())
            return this.telaSalaDeEspera;
        
        if(this.telaJogo != null && this.telaJogo.isVisible())
            return this.telaJogo;
        
        if(this.telaResultado != null && this.telaResultado.isVisible())
            return this.telaResultado;
        
        return null;
    }
    
    public void hideCurrent() {
        JFrame form  = (JFrame) getCurrentForm();
        if (form != null) {
            form.setVisible(false);
        }
    }
}
