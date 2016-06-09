/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.models;

import java.util.ArrayList;

/**
 *
 * @author valmir
 */
public class UserCard implements Cloneable {
    private User user;
    private BingoCard card;
    private ArrayList<Integer> playedNumbers;
    private boolean roomOwner;

    public boolean isRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(boolean roomOwner) {
        this.roomOwner = roomOwner;
    }
    
    public UserCard(User user, BingoCard card) throws Exception {
        if(user == null)
            throw new Exception("User cannot be null");
        if(card == null)
            throw new Exception("BingoCard cannot be null");
        this.user = user;
        this.card = card;
        this.roomOwner = false;
    }
    public User getUser() {
        return this.user;
    } 
    public BingoCard getCard() {
        return this.card;
    }
    public void addPlayedNumber(int nr) {
        this.playedNumbers.add(nr);
    }
    public ArrayList<Integer> getPlayedNumbers() {
        return (ArrayList<Integer>) this.playedNumbers.clone();
    }
    
    @Override
    public int hashCode() {
        int r = super.hashCode();
        r = r * 7 + this.user.hashCode();
        r = r * 7 + this.card.hashCode();
        r = r * 7 + this.playedNumbers.hashCode();
        
        return r;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(!(obj instanceof UserCard))
            return false;
        
        UserCard other = (UserCard) obj;
        if(!(this.user.equals(other.user)))
            return false;
        if(!(this.card.equals(other.card)))
            return false;
        if(!(this.playedNumbers.equals(other.playedNumbers)))
            return false;
        
        return true;   
    }
    @Override
    public String toString(){
        String str = this.user.toString();
        str += "Números Jogados: \n";
        for(int nr : this.playedNumbers)
            str += nr + ", ";
        
        str += this.card.toString();
        
        return str;
    }
    
    public UserCard(UserCard other) throws Exception {
        if(other == null)
            throw new Exception("UserCard cannot be null");
        
        this.card = other.card;
        this.user = other.user;
        this.playedNumbers = (ArrayList<Integer>)other.playedNumbers.clone();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        UserCard other = null;
        try {
            other = new UserCard(this);
        }
        catch(Exception e) {}
        return other;
    }
}
