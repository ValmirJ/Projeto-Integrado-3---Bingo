/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author guilherme
 */
public class Card {
    private Integer[][] numbers;
    
    public Card() {
        numbers = new Integer[5][5];
    }
    
    public void setNumber(int lin, int col, int number) {
        if (lin == 2 && col == 2) {
            return;
        }
        
        if (lin < 0 || lin > 5) {
            return;
        }
        
        if (col < 0 || col > 5) {
            return;
        }
        
        numbers[lin][col] = number;
    }
    
    public Integer getNumber(int lin, int col) {
        if (lin == 2 && col == 2) {
            return null;
        }
        
        if (lin < 0 || lin > 5) {
            return null;
        }
        
        if (col < 0 || col > 5) {
            return null;
        }
        
        return numbers[lin][col];
    }
}
