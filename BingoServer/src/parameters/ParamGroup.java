/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parameters;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class ParamGroup {
    // Relembre dos comandos. Ex:
    // sala<id>usuarios<ra,ra,ra...>
    // a funcao dessa classe é cuidar tanto do <id>
    // quanto do <ra,ra,ra,ra,...>
    // Note que ela reqpresenta apenas um único grupo. Não múltiplos.

    // A ideia é que haja um <?,?,..> onde cada ? é um valor da lista values.
    private List<String> values = new ArrayList<>();

    public static ParamGroup createFromString(String group) {
        ArrayList<String> tempElements = new ArrayList<>();
        // Será recebido em group um valor do tipo:
        // <15096134,abcde,lolwut>
        // E deve ser retornado uma instancia desse ParamGroup com a lista preenchida de tal forma que
        // values.get(0) => "15096134"
        // values.get(1) => "abcde"
        // values.get(2) => "lolwut"

        // return new ParamGroup(tempElements);
        //
        // Caso haja algum erro nos parametros:
        // Como paratros no seguinte formato
        // "<hu3hu3,,>"
        // "hh,hh,>
        // "<>"
        return null;
    }

    public ParamGroup(List<String> values) {
        this.values.addAll(values);
    }

    public ParamGroup(String... values) {
        for (String e : values) {
            this.values.add(e);
        }
    }

    public String getParam(int i) {
        return values.get(i);
    }

    public String toString() {
        // nesse método retornar toda a lista values no formato:
        // 1 elemento:  <?>
        // 2 elementos: <?,?>
        // 3 elementos: <?,?,?>
        // N elementos: <?,?,?,...>
        // Inclusive o "<" e o ">"

        return "";
    }
}
