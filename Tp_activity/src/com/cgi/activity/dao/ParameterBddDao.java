/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.dao;

import com.cgi.activity.pojo.Parameter;
import java.util.List;

/**
 *
 * @author HB1
 */
public interface ParameterBddDao
{
    /**
     * Add a parameter in the database
     * @param parameter 
     */
    public void add(Parameter parameter);
    
    /**
     * Get the {@link Parameter} with the label labelParameter
     * @param labelParameter
     * @return a parameter from the database
     */
    public Parameter getParameter(String labelParameter) throws DaoException;
    
    /**
     * Get all the values of parameter in the database
     * @return the list of all {@link Parameter}
     */
    public List<Parameter> getAllParameters();
}
