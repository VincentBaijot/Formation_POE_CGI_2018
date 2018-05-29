/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.controller;

import com.cgi.activity.pojo.Activity;
import com.cgi.activity.pojo.Parameter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VincentBaijot
 */
public class LoadFile
{

    public static String FILE_ACTIVITY = "InputActivity.txt";
    public static String FILE_PARAMETER = "InputParameter.txt";

    public static List<Parameter> loadParameters()
    {
        Logger.getLogger("log").info("Debut lecture fichier parameter");
        
        List<Parameter> listParameter = new ArrayList<>();

        File file = new File(FILE_PARAMETER);

        BufferedReader br = null;

        if (file.exists())
        {
            try
            {
                br = new BufferedReader(new FileReader(file));
                
                String line = br.readLine();
                        
                while(line != null)
                {
                    String[] splitedLine = line.split(";");
                    
                    Parameter parameter = new Parameter(splitedLine[0], splitedLine[1]);
                    
                    listParameter.add(parameter);
                    
                    line = br.readLine();
                }
                
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger("log").log(Level.SEVERE, "Fichier {0} introuvable", FILE_PARAMETER);
            } catch (IOException ex)
            {
                Logger.getLogger("log").log(Level.SEVERE, "Fichier {0} illisible", FILE_PARAMETER);
            }finally
            {
                try
                {
                    br.close();
                } catch (IOException ex)
                {
                    Logger.getLogger("log").log(Level.SEVERE, null, ex);
                }
            }
        }

        Logger.getLogger("log").info("Fichier parameter correctement lu");
        
        return listParameter;
    }

    public static List<Activity> loadActivity()
    {
        Logger.getLogger("log").info("Debut lecture fichier activity");
        
        List<Activity> listActivity = new ArrayList<>();
        List<Parameter> listParameter;

        File file = new File(FILE_ACTIVITY);
        
        BufferedReader br = null;

        if (file.exists())
        {
            try
            {
                br = new BufferedReader(new FileReader(file));
                
                String line = br.readLine();
                        
                while(line != null)
                {
                    String[] splitedLine = line.split(";");
                    
                    Activity activity = new Activity();
                    
                    activity.setName(splitedLine[0]);
                    
                    String[] labelParameter = splitedLine[1].split("&");
                    
                    listParameter = new ArrayList<>();
                    
                    for(String s : labelParameter)
                    {
                        
                        Parameter parameter = new Parameter();
                        
                        parameter.setLabel(s);
                        
                        listParameter.add(parameter);
                    }
                    
                    activity.setListParameter(listParameter);
                    
                    listActivity.add(activity);
                    
                    line = br.readLine();
                }
                
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger("log").log(Level.SEVERE, "Fichier {0} introuvable", FILE_PARAMETER);
            } catch (IOException ex)
            {
                Logger.getLogger("log").log(Level.SEVERE, "Fichier {0} illisible", FILE_PARAMETER);
            }finally
            {
                try
                {
                    br.close();
                } catch (IOException ex)
                {
                    Logger.getLogger("log").log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Logger.getLogger("log").info("Fichier activity correctement lu");

        return listActivity;
    }
    
    public static boolean deleteFiles()
    {
        boolean status;
        
        File fileParameter = new File(FILE_PARAMETER);
        File fileActivity = new File(FILE_ACTIVITY);
        
        status = fileParameter.delete() & fileActivity.delete();
        
        return status;
    }

}
