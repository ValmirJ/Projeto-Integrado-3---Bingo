/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author valmir.massoni
 */
public class BingoCard implements Cloneable {
    private int idCard;
    private int numbers[][] = new int[5][5];
    
    
    public void setIdCard(int id) {
        this.idCard = id;
    }
    public int getIdCard() {
        return this.idCard;
    }
    public void setNumbers(int[][] numbers) throws Exception {
        if(numbers.length < 5 || numbers[0].length < 5)
            throw new Exception("Cartela inválida");
        
        for(int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < numbers[0].length; i++) {
                this.numbers[i][j] = numbers[i][j];
            }
        }
    }
    public int[][] getNumbers() {
        return this.numbers;
    }
    
    public BingoCard(int id, int[][] numbers) throws Exception {
        this.setIdCard(id);
        this.setNumbers(numbers);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(!(obj instanceof BingoCard))
            return false;
        
        BingoCard another = (BingoCard) obj;
        if(this.idCard != another.idCard)
            return false;
        
        return true;
    }
    
    @Override
    public String toString() {
       String str = "";
        for(int[] line : numbers){
            for(int n : line) {
                str += n + " ";
            }
            str += "\n";
        } 
        return str;
    }
    @Override
    public int hashCode() {
        int r = super.hashCode();
        r = r * 7 + new Integer(this.idCard).hashCode();
        
        for(int[] line : this.numbers) {
            for(int n : line)
                r = r * 7 + new Integer(n).hashCode();
        }
        return r;
    }

    public BingoCard(BingoCard another) throws Exception {
        if(another == null)
            throw new Exception("Objeto não pode ser nulo");
         
        this.idCard = another.idCard;
        this.setNumbers(another.getNumbers());
    }
    
    @Override
    public Object clone() {
        BingoCard newBingoCard = null;
        try {
            newBingoCard = new BingoCard(this);
        }
        catch(Exception e) {}
        
        return newBingoCard;
    }
}
