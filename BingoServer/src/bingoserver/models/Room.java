/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.models;

import java.util.ArrayList;

/**
 *
 * @author valmir.massoni
 */
public class Room implements Cloneable {
    private int id;
    private boolean started;
    private ArrayList<Integer> sortedNumbers;
    private UserCard[] userCards;
    private int amountUsers;
    
    public int getId() {
        return this.id;
    }
    public boolean getStateRoom() {
        return this.started;
    }
    public void startRoom() {
        this.started = true;
    }
    public void addSortedNumber(int number) {
        this.sortedNumbers.add(number);
    }
    
    public void addUserCard(UserCard userCard) {
        this.userCards[this.amountUsers] = userCard;
        this.amountUsers++;
    }
    
    public boolean removeUserCard(UserCard userCard) {
        int i;
        for(i = 0; i < this.userCards.length || this.userCards[i].equals(userCard); i++)
        {}
        if(i == this.userCards.length)
            return false;
        
        for(; i < this.userCards.length; i++) {
            userCards[i] = userCards[i+1];
        }
        this.amountUsers--;
        return true;
    }
 
    public Room (int id, UserCard userCard, int sizeOfRoom) {
        this.userCards = new UserCard[sizeOfRoom];
        this.started = false;
        this.userCards[0] = userCard;
        this.amountUsers = 1;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if(this == obj)
            return true;
        if(!(obj instanceof Room))
            return false;
        
        Room other = (Room) obj;
        if(this.id != other.id)
            return false;
        
        if(this.started != other.started)
            return false;
        
        if(!(this.sortedNumbers.equals(other.sortedNumbers)))
            return false;
        
        if(this.amountUsers != other.amountUsers)
            return false;
        
        for(int i = 0; i < this.amountUsers; i++)
            if(!(this.userCards[i].equals(other.userCards[i])))
                return false;
        
        return true;
    }
    @Override 
    public String toString() {
        String str = "Sala " + this.id + "\n\n";
        str += "Numeros Sorteados: \n";
        for(int nr : this.sortedNumbers)
            str += nr + ", ";
        
        str += "Usuários / Cartela\n";
        for(UserCard uc : this.userCards)
            str += uc.toString();
        
        return str;
    }
    @Override
    public int hashCode() {
        int r = super.hashCode();
        r = r * 7 + Integer.hashCode(this.id);
        r = r * 7 + Boolean.hashCode(this.started);
        r = r * 7 + Integer.hashCode(this.amountUsers);
        r = r * 7 + this.sortedNumbers.hashCode();
        for (UserCard uc : this.userCards)
            r = r * 7 + uc.hashCode();
        
        return r;
    }
    
    public Room(Room other) throws Exception {
        if(other == null)
            throw new Exception("Object cannot be null");
        
        this.id = other.id;
        this.started = other.started;
        this.amountUsers = other.amountUsers;
        this.sortedNumbers = (ArrayList<Integer>)other.sortedNumbers.clone();
        this.userCards = other.userCards;    
    }
    
    @Override
    public Object clone() {
        Room other = null;
        try {
            other = new Room(this);
        }
        catch(Exception e){}
        
        return other;
     }
    
}
