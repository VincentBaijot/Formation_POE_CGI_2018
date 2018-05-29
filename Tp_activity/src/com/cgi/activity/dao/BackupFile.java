/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.dao;

import com.cgi.activity.pojo.Activity;

/**
 *
 * @author Vincent Baijot
 */
public interface BackupFile
{
    /**
     * Temporary save the activity to send to the user
     */
    public void save(Activity activity);
    
    /**
     * If a temporary file exist loads the activity in this file
     * @return 
     */
    public Activity load();
    
    /**
     * Tells if an XML backup file exist
     * @return TRUE if a file exist and FALSE if not
     */
    public boolean exist();
    
    /**
     * Delete the XML file
     */
    public void delete();
    
}
