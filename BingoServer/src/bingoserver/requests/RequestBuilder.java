/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.requests;

import bingoserver.interactions.ConnectUser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author guilherme
 */
public class RequestBuilder {

    private final Pattern messagePattern;

    public RequestBuilder() {
        // Apenas uma ideia.
        // Nao testei, isso pode estar errado.
        String pattern = "\\A(\\w+(<([^>]+))?)+\\z";

        this.messagePattern = Pattern.compile(pattern);
    }

    public String toString() {
        return "RequestBuilder";
    }

    public Request buildRequestForMessage(String message) {
        // Os valores aceitaveis para message sao todos aqueles que os clientes
        // podem enviar para nosso servidor.
        //
        // TODO:
        // 1. Verificar a sintaxe da mensagem.
        //
        // 2. Obter a lista de parametros:
        // 2.1 Criar um ParamGroup para cada <id> ou <ra,ra,ra...>
        // 2.2 Criar um ParamGroups com todos os ParamGroup encontrados.
        //
        // 3. Identificar a classe do Interactor correspondente.
        //
        // Retornar um InteractionRequest se houve sucesso nos passos acima.
        // OU
        // return new InvalidRequest(message);
        // Se houve alguma falha.

        // Exemplo:
        Matcher m = messagePattern.matcher(message);

        if (m.matches()) {
            return new InteractionRequest(message, ConnectUser.class, null);
        }

        return new InvalidRequest(message);
    }
}
