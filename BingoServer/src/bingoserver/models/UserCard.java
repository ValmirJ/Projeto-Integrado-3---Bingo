/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.models;

/**
 *
 * @author valmir
 */
public class UserCard {
    private User user;
    private BingoCard card;
    
    public UserCard(User user, BingoCard card) throws Exception {
        if(user == null)
            throw new Exception("User cannot be null");
        if(card == null)
            throw new Exception("BingoCard cannot be null");
        this.user = user;
        this.card = card;
    }
    public User getUser() {
        return this.user;
    } 
    public BingoCard getCard() {
        return this.card;
    }
    
    @Override
    public int hashCode() {
        int r = super.hashCode();
        r = r * 7 + this.user.hashCode();
        r = r * 7 + this.card.hashCode();
        
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
        
        return true;   
    }
    @Override
    public String toString(){
        String str = this.user.toString();
        str += this.card.toString();
        
        return str;
    }
}
