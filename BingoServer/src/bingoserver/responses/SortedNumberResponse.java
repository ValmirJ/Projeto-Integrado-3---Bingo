/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class SortedNumberResponse extends TimeProgressResponse {

    private final int number;

    public SortedNumberResponse(int time, int number) {
        super(time);
        this.number = number;
    }

    @Override
    public JSONObject responseJson() {
        JSONObject base = super.responseJson();

        base.put("type", "new-number");
        base.put("number", number);

        return base;
    }
}
