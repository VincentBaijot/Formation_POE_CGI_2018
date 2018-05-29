/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VincentBaijot
 */
public class DaoBddFactory
{

    String url;
    String userName;
    String password;

    public DaoBddFactory()
    {
        try
        {
            Properties property = new Properties();

            property.load(new FileInputStream("BDD.config"));

            this.url = property.getProperty("url");
            this.userName = property.getProperty("user");
            this.password = property.getProperty("password");

        } catch (FileNotFoundException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }
        
        Logger.getLogger("log").info("Initialisation de la DAO factory reussi");
    }

    public Connection getConnection() throws DaoException
    {
        Connection connection = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(this.url, this.userName, this.password);

        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }
        
        if(connection == null)
        {
            throw new DaoException("Impossible de se connecter à la base de donnée");
        }

        return connection;
    }
    
    public ParameterBddDao getParameterBddDao()
    {
        return new ParameterBddImp(this);
    }
    
    public ActivityBddDao getActivityBddDao()
    {
        return new ActivityBddImp(this);
    }

}
