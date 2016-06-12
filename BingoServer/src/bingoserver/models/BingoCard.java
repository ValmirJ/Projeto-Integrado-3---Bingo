/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author valmir.massoni
 */
public class BingoCard {

    private final int idCard;
    private final int numbers[][];

    public int getIdCard() {
        return this.idCard;
    }

    public int[][] getNumbers() {
        return this.numbers.clone();
    }

    public BingoCard(int id, int[][] numbers) throws Exception {
        if (numbers.length != 5) {
            throw new Exception("Cartela inválida");
        }

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].length != 5) {
                throw new Exception("Cartela inválida");
            }
        }

        this.numbers = numbers.clone();
        this.idCard = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BingoCard)) {
            return false;
        }

        BingoCard another = (BingoCard) obj;
        if (this.idCard != another.idCard) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String str = "BingoCard id: " + this.idCard + "\n";

        for (int[] line : numbers) {
            for (int n : line) {
                if (n < 10) {
                    str += n + "  ";
                } else {
                    str += n + " ";
                }
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public int hashCode() {
        int r = super.hashCode();
        r = r * 7 + new Integer(this.idCard).hashCode();

        for (int[] line : this.numbers) {
            for (int n : line) {
                r = r * 7 + new Integer(n).hashCode();
            }
        }
        return r;
    }

    public JSONArray asJson() {
        JSONArray json = new JSONArray();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                if (i != 2 && j != 2) {
                    JSONObject numberJson = new JSONObject();

                    numberJson.put("row", i);
                    numberJson.put("col", j);
                    numberJson.put("value", numbers[i][j]);

                    json.add(numberJson);
                }
            }
        }

        return json;
    }
}
