/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.dao.test;

import com.cgi.activity.dao.DaoBddFactory;
import com.cgi.activity.dao.DaoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HB1
 */
public class DaoTest
{
    Connection c;
    
    @Before
    public void setUp()
    {
        DaoBddFactory dao = new DaoBddFactory();
        try
        {
            c = dao.getConnection();
        } catch (DaoException ex)
        {
            Logger.getLogger(DaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown()
    {
        try
        {
            c.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(DaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testConnection()
    {
        Assert.assertNotNull("La connexion est null", c);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
