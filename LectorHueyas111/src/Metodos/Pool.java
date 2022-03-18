/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.sql.Driver;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author cosl9
 */
public class Pool {
    
    
    
    public DataSource dataSource;
    public String DRIVER_CLASS="com.mysql.jdbc.Driver";
    public String URL="jdbc:mysql://localhost:3306/huella?useUnicode=true&characterEncoding=utf-8";
    public String USER="root";
    public String PASS="Luis1234";
    public Driver driver =null;
    
    public Pool(){
        iniciDataSource();
    }
    
    private void iniciDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        basicDataSource.setMaxActive(50);
    
        dataSource = basicDataSource;
    }
}
