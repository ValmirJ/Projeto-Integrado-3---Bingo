/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author guilherme
 */
class DbConnectionBuilder {

    private static final String DB_CONNECTION_FILE = "db.json";

    public static Connection getNewConnection() throws Exception {
        JSONObject connParams = connectionParams();
        MysqlDataSource dataSource = new MysqlDataSource();

        //""
        dataSource.setUrl((String) connParams.get("url"));
        dataSource.setUser((String) connParams.get("user"));
        dataSource.setPassword((String) connParams.get("password"));

        Connection conn = dataSource.getConnection();
        conn.setReadOnly(true);

        return conn;
    }

    private static JSONObject connectionParams() throws Exception {
        File f = new File(DB_CONNECTION_FILE);

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }

                return (JSONObject) JSONValue.parse(sb.toString());

            } catch (IOException ex) {
                Logger.getLogger(DbConnectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(DbConnectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            String path = f.getAbsolutePath();
            String log = "\n\n-----------------------------------------------------------------------\n"
                    + "O arquivo com as informaçoes de conexao com o banco nao foi encontrado!\n"
                    + "Copie o arquivo\n"
                    + path + ".sample\n"
                    + " para \n"
                    + path + "\n"
                    + "e coloque as informaçoes de conexao.\n"
                    + "Se você ainda não possui as cartelas no DB, crie um schema e carregue o dump incluso na pasta sql."
                    + "\n\n-----------------------------------------------------------------------\n";

            Logger.getLogger(DbConnectionBuilder.class.getName()).log(Level.WARNING, log);
            throw ex;
        }
    }
}
