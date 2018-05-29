/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.pojo;

/**
 * Represents a criteria of choise the user can choose to select an activity
 * @author VincentBaijot
 */
public class Parameter
{
    private String label;
    private String name;
    
    public Parameter()
    {
        
    }
    
    public Parameter(String label, String name)
    {
        this.label = label;
        this.name = name;
    }
    
    public String toString()
    {
        return this.label + " : " + this.name;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    
}
