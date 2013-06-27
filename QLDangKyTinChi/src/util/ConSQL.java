/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.jca.GetInstance;

/**
 *
 * @author MrOnly
 */
public class ConSQL {

    private static String ServerName, Port, DatabaseName, Username, Password;

    private static void getInfo() {
        try {
            FileReader fr = new FileReader("src\\util\\Default.txt");
            BufferedReader bf = new BufferedReader(fr);
            String value = bf.readLine();
            setServerName(value);
            value = bf.readLine();
            setPort(value);
            value = bf.readLine();
            setUsername(value);
            value = bf.readLine();
            setPassword(value);
            value = bf.readLine();
            setDatabaseName(value);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static Connection connection = null;

    public static Connection getConnectToMSSQL() {
        try {
            getInfo();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://" + ServerName + ":" + Port + ";User=" + Username + ";Password=" + Password + ";DatabaseName=" + DatabaseName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }
    public static void main(String[] args) {
        System.out.println(getConnectToMSSQL());
    }
    
    
    
     public static String getServerName() {
        return ServerName;
    }

    public static void setServerName(String ServerName) {
        ConSQL.ServerName = ServerName;
    }

    public static String getPort() {
        return Port;
    }

    public static void setPort(String Port) {
        ConSQL.Port = Port;
    }

    public static String getDatabaseName() {
        return DatabaseName;
    }

    public static void setDatabaseName(String DatabaseName) {
        ConSQL.DatabaseName = DatabaseName;
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String Username) {
        ConSQL.Username = Username;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String Password) {
        ConSQL.Password = Password;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ConSQL.connection = connection;
    }
}
