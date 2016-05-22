/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.models;

/**
 *
 * @author valmir.massoni
 */
public class User {
    private int id;
    private String ra;
    
    public User(int id, String ra) {
        this.id = id;
        this.ra = ra;
    }
    
    public int getId() {
        return this.id;
    }
    public String getRa() {
        return this.ra;
    }
            
    @Override
    public int hashCode() {
        int r = super.hashCode();
        r = r * 7 + this.ra.hashCode();
        r = r * 7 +  Integer.hashCode(id);
        
        return r;
    }
    @Override 
    public String toString() {
        String str = "Usuário: " + this.id + "\n";
        str += "RA: " + this.ra;
        
        return str;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(!(obj instanceof User))
            return false;
        
        User other = (User) obj;
        
        if(this.id != other.id)
            return false;
        if(!this.ra.equals(other.ra))
            return false;
        
        return true;
    }
        
}
