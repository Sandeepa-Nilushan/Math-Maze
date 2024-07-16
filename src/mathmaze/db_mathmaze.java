/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mathmaze;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SANDEEPA
 */
public class db_mathmaze {
    
    private static String servername = "localhost";
    private static String username = "root";
    private static String dbname  = "mathmaze";
    private static Integer portnumber  = 3308;
    private static String password = "";
    
    public static Connection getConnection()
    {
        Connection math = null;
        
        MysqlDataSource datasource = new MysqlDataSource();
        
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
        
        try{
            math = datasource.getConnection();
        }catch (SQLException ex){
            Logger.getLogger(" Get Connection -> " + db_mathmaze.class.getName()).log(Level.SEVERE, null, ex);
        }
        return math;
    }
}
