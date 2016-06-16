/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.awt.Component;
import javax.swing.JFrame;

/**
 *
 * @author guilherme
 */
public class MyJFrame extends JFrame {

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b == false) {
            Component[] components = getContentPane().getComponents();
            
            for (Component component : components) {
                if (component instanceof ButtonWithLoader) {
                    ((ButtonWithLoader) component).hideLoader();
                }
            }
        }
    }
    
    public void hideLoaders() {
        Component[] components = getContentPane().getComponents();
            
        for (Component component : components) {
            if (component instanceof ButtonWithLoader) {
                ((ButtonWithLoader) component).hideLoader();
            }
        }
    }
}
