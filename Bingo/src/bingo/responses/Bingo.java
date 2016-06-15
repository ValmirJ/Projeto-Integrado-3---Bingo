/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.responses;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class Bingo extends Response {
    private List<Integer> numbers;
    
    public Bingo(List<Integer> numbers) {
        this.numbers = numbers;
    }
    
    @Override
    public JSONObject responseJson() {
        JSONObject obj = new JSONObject();
        obj.put("type", "bingo");
        
        JSONArray numbersJson = new JSONArray();
        for (Integer i : numbers) {
            numbersJson.add(i);
        }
        
        obj.put("numbers", numbers);
        
        return obj;
    }
    
}
