/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.pojo;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an activity proposed by the application to the user
 *
 * @author VincentBaijot
 */
@XmlRootElement
public class Activity
{

    private int identifier;
    private String name;
    private List<Parameter> listParameter;

    public Activity()
    {

    }

    public Activity(String name, List<Parameter> listParameter)
    {
        this.name = name;
        this.listParameter = listParameter;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name);
        sb.append(" : ");

        for (Parameter parameter : this.listParameter)
        {
            sb.append(parameter.getLabel());
            sb.append(" ");
        }

        return sb.toString();
    }

    public String getName()
    {
        return name;
    }

    public List<Parameter> getListParameter()
    {
        return listParameter;
    }

    public int getIdentifier()
    {
        return identifier;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setListParameter(List<Parameter> listParameter)
    {
        this.listParameter = listParameter;
    }

    public void setIdentifier(int identifier)
    {
        this.identifier = identifier;
    }

}
