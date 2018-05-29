/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.main;

import com.cgi.activity.controller.LoadFile;
import com.cgi.activity.dao.ActivityBddDao;
import com.cgi.activity.dao.BackupFile;
import com.cgi.activity.dao.DaoBackupFactory;
import com.cgi.activity.dao.DaoBddFactory;
import com.cgi.activity.dao.DaoException;
import com.cgi.activity.dao.ParameterBddDao;
import com.cgi.activity.pojo.Activity;
import com.cgi.activity.pojo.Parameter;
import com.cgi.activity.util.InitLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HB1
 */
public class Tp_activity
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        InitLogger.initLogger();
        List<Parameter> listParameter = LoadFile.loadParameters();
        List<Activity> listActivity = LoadFile.loadActivity();

        Activity proposedActivity;

        DaoBddFactory dao = new DaoBddFactory();

        DaoBackupFactory daoBackup = new DaoBackupFactory();
        BackupFile backup = daoBackup.createDao();

        ParameterBddDao parameterDao = dao.getParameterBddDao();

        for (Parameter parameter : listParameter)
        {
            parameterDao.add(parameter);
        }

        ActivityBddDao activityDao = dao.getActivityBddDao();
        List<Activity> listErrorActivity = new ArrayList<>();
        listErrorActivity.addAll(listActivity);

        for (Activity activity : listActivity)
        {
            try
            {
                activityDao.addActivity(activity);
                listErrorActivity.remove(activity);
            } catch (DaoException ex)
            {
                Logger.getLogger("log").log(Level.SEVERE, null, ex);
            }
        }

        if (listErrorActivity.isEmpty())
        {
            LoadFile.deleteFiles();
        }

        if (backup.exist())
        {
            System.out.println("Je te propose d'aller " + backup.load().getName());

            backup.delete();
        } else
        {
            List<Parameter> listParameterReaded = parameterDao.getAllParameters();

            System.out.println("-----------------------------------");
            System.out.println("Bienvenue");
            System.out.println("-----------------------------------");

            System.out.println("Choisie parmi les critères : ");

            for (Parameter parameter : listParameterReaded)
            {
                System.out.println(parameter);
            }

            System.out.println("Puis tape q pour quitter");

            Scanner sc = new Scanner(System.in);
            String enteredString = sc.next();
            List<Parameter> userParameters = new ArrayList<>();

            try
            {
                while (!enteredString.equals("q"))
                {
                    userParameters.add(parameterDao.getParameter(enteredString));
                    System.out.println("Encore un parametre ? Si non tape q pour quitter");
                    enteredString = sc.next();
                }

            } catch (DaoException ex)
            {
                Logger.getLogger("log").log(Level.SEVERE, null, ex);
            }

            System.out.println();
            System.out.println();

            List<Activity> listRetainedActivities = activityDao.getActivityByParameter(userParameters);

            if (listRetainedActivities.size() > 0)
            {
                Random random = new Random();

                proposedActivity = listRetainedActivities.get(random.nextInt(listRetainedActivities.size()));

                backup.save(proposedActivity);

                System.out.println("Je te propose d'aller " + proposedActivity.getName());
                
                backup.delete();

            }
        }

    }

}
