/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author karen
 */
public class Connexion {
    public static Connection getConnection() throws Exception{
        Connection con=null;
        Class.forName("org.postgresql.Driver");
        con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/avionws","postgres","karen");
        return con;
    }
}
