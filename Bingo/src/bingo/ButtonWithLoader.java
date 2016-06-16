/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author guilherme
 */
public class ButtonWithLoader extends JButton {
    private String originalText;
    private Icon loadIcon;
    private boolean loaderVisible = false;
    
    public void showLoader() {
        if (loaderVisible) {
            return;
        }
        
        loaderVisible = true;
        originalText = getText();
        setText("");
        setIcon(getLoadIcon());
        setEnabled(false);
    }
    
    public void hideLoader() {
        if (!loaderVisible) {
            return;
        }
        loaderVisible = false;
        setText(originalText);
        setIcon(null);
        setEnabled(true);
    }
    
    private Icon getLoadIcon() {
        if (loadIcon == null) {
            loadIcon = new ImageIcon("src/assets/loading.gif");
        }
        
        return loadIcon;
    }
}
