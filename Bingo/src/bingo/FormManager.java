/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

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

    public JFrame getTelaInicial() {
        if(this.telaInicial == null)
            this.telaInicial = new TelaInicial();
        
        return telaInicial;
    }

    public JFrame getTelaJogo() {
        if(this.telaJogo == null)
            this.telaJogo = new TelaJogo();
        
        return telaJogo;
    }

    public JFrame getTelaResultado() {
        if(this.telaResultado == null)
            this.telaResultado = new TelaResultado();
        
        return telaResultado;
    }

    public JFrame getTelaSalas() {
        if(this.telaSalas == null)
            this.telaSalas = new TelaSalas();
        
        return telaSalas;
    }
}
