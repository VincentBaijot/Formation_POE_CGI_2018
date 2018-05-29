/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.dao;

import com.cgi.activity.pojo.Activity;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author Vincent
 */
public class BackupFileXmlImp implements BackupFile
{
    public static String FILE_XML = "activity_backup.xml";

    @Override
    public void save(Activity activity)
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(Activity.class);
            File file = new File(FILE_XML);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            m.marshal(activity, file);
            
        } catch (JAXBException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Activity load()
    {
        Activity activity = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(Activity.class);
            File file = new File(FILE_XML);
            Unmarshaller um = context.createUnmarshaller();
            
            activity = (Activity) um.unmarshal(file);
            
        } catch (JAXBException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }
        
        return activity;
    }
    
    @Override
    public boolean exist()
    {
        File file = new File(FILE_XML);
        
        return file.exists();
    }
    
    @Override
    public void delete()
    {
        File file = new File(FILE_XML);
        
        file.delete();
    }
    
}
