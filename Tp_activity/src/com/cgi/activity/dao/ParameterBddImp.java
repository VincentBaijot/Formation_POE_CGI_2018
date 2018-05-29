/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.dao;

import com.cgi.activity.pojo.Parameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VincentBaijot
 */
public class ParameterBddImp implements ParameterBddDao
{

    DaoBddFactory dao;

    public ParameterBddImp(DaoBddFactory dao)
    {
        this.dao = dao;
    }

    @Override
    public void add(Parameter parameter)
    {
        Connection con = null;

        try
        {

            con = dao.getConnection();

            String query = "INSERT INTO parameters (pa_label, pa_name) VALUES (?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, parameter.getLabel());
            preparedStatement.setString(2, parameter.getName());

            preparedStatement.executeUpdate();

        }
        catch (SQLException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }
        catch (DaoException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                if (con != null)
                {
                    con.close();
                }

            }
            catch (SQLException ex)
            {
                Logger.getLogger("log").log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Parameter getParameter(String labelParameter) throws DaoException
    {
        Connection con = null;
        Parameter parameter = new Parameter();

        try
        {
            con = this.dao.getConnection();

            String query = "Select pa_name name from parameters where pa_label=?";
            ResultSet result;

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, labelParameter);

            result = preparedStatement.executeQuery();

            if (result.next())
            {
                parameter.setLabel(labelParameter);
                parameter.setName(result.getString("name"));
            }
            else
            {
                throw new DaoException("Valeur introuvable");
            }

        }
        catch (SQLException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }

        return parameter;
    }

    @Override
    public List<Parameter> getAllParameters()
    {
        Connection con = null;
        List<Parameter> listParameter = new ArrayList<>();
        ResultSet result;

        try
        {
            con = this.dao.getConnection();

            String query = "Select pa_label label, pa_name name from parameters";

            Statement statement = con.createStatement();

            result = statement.executeQuery(query);

            while (result.next())
            {
                listParameter.add(new Parameter(result.getString("label"), result.getString("name")));
            }

        }
        catch (SQLException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }
        catch (DaoException ex)
        {
            Logger.getLogger("log").log(Level.SEVERE, null, ex);
        }

        return listParameter;
    }

}
