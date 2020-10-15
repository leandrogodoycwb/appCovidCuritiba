/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casoschecker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Saulo Jr
 */
public class FactoryJBDC {

    public static Connection conexao() {
        Connection connec = null;
        try {
            String path = System.getProperty("user.dir");
            String url = "jdbc:sqlite:" + path + "/database/bank.db";
            connec = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connec;
    }
}
