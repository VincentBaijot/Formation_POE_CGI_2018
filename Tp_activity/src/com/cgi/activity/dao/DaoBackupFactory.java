/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cgi.activity.dao;

/**
 *  
 * @author VincentBaijot
 */
public class DaoBackupFactory {

    public static BackupFile createDao()
    {
        return new BackupFileXmlImp();
    }
}
