/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.dao;

import com.cgi.activity.pojo.Activity;
import com.cgi.activity.pojo.Parameter;
import java.util.List;

/**
 *
 * @author HB1
 */
public interface ActivityBddDao
{
    /**
     * Add an {@link Activity} in the database
     * @param activity an {@link Activity}
     * @throws DaoException
     */
    public void addActivity(Activity activity) throws DaoException;
    
    /**
     * Get the list of all {@link Activity} in the database
     * @return 
     */
    public List<Activity> getAllActivity();
    
    /**
     * Get the list of all {@link Activity} corresponding to a list of {@link Parameter}
     * @param listParameter 
     * @return a list of {@link Activity}
     */
    public List<Activity> getActivityByParameter(List<Parameter> listParameter);
    
    /**
     * Get the identifier of an {@link Activity}
     * @param activity
     * @return the indentifier
     * @throws DaoException if the activity is not found
     */
    public int getActivityIdentifier(Activity activity) throws DaoException;

}
