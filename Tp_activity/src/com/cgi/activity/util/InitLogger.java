/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author VincentBaijot
 */
public class InitLogger
{

    public static void initLogger()
    {
        try
        {
            Logger logger = null;
            
            logger = Logger.getLogger("log");
            
            Handler fh = new FileHandler("applicationLog.log");
            logger.setLevel(Level.INFO);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            logger.setUseParentHandlers(false);
            
        } catch (IOException ex)
        {
            Logger.getLogger(InitLogger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex)
        {
            Logger.getLogger(InitLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Logger.getLogger("log").info("Initialisation du fichier logger");
    }
}
