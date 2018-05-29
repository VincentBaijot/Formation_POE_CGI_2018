/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.activity.dao;

import com.cgi.activity.pojo.Activity;
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
public class ActivityBddImp implements ActivityBddDao
{

    DaoBddFactory dao;

    public ActivityBddImp(DaoBddFactory dao)
    {
        this.dao = dao;
    }

    @Override
    public void addActivity(Activity activity) throws DaoException
    {
        Connection con = null;
        int lastActivityId = 0;

        Logger.getLogger("log").log(Level.INFO, "Debut ajout de lactivite {0}", activity.getName());

        try
        {
            con = this.dao.getConnection();
            con.setAutoCommit(false);

            String queryActivity = "INSERT INTO activity (ac_name) VALUES (?)";
            String queryMapping = "INSERT INTO mapping_activity_parameters (pa_label, ac_id) VALUES (?, ?)";

            PreparedStatement preparedStatementActivity = con.prepareStatement(queryActivity, Statement.RETURN_GENERATED_KEYS);
            preparedStatementActivity.setString(1, activity.getName());

            preparedStatementActivity.executeUpdate();

            Logger.getLogger("log").log(Level.INFO, "Activite {0} correctement ajoute", activity.getName());

            ResultSet result = preparedStatementActivity.getGeneratedKeys();

            if (result.next())
            {
                lastActivityId = result.getInt(1);
            }

            for (Parameter parameter : activity.getListParameter())
            {
                PreparedStatement preparedMapping = con.prepareStatement(queryMapping);
                preparedMapping.setString(1, parameter.getLabel());
                preparedMapping.setInt(2, lastActivityId);

                preparedMapping.executeUpdate();
            }

            con.commit();

        }
        catch (SQLException ex)
        {
            try
            {
                con.rollback();
            }
            catch (SQLException ex1)
            {
                Logger.getLogger("log").log(Level.SEVERE, null, ex1);
            }
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
    public List<Activity> getAllActivity()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> getActivityByParameter(List<Parameter> listParameter)
    {
        Connection con = null;
        List<List<Activity>> listPossibleActivities = new ArrayList<>();
        List<Activity> retainedActivities = new ArrayList<>();

        try
        {
            con = this.dao.getConnection();

            String query = "SELECT m.pa_label, a.ac_name name FROM activity a  JOIN parameters p JOIN mapping_activity_parameters m "
                    + "ON a.ac_id=m.ac_id AND p.pa_label=m.pa_label AND m.pa_label=?;";

            ResultSet result;

            for (Parameter parameter : listParameter)
            {
                List<Activity> listActivity = new ArrayList<>();

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, parameter.getLabel());

                result = preparedStatement.executeQuery();

                while (result.next())
                {
                    Activity activity = new Activity();
                    activity.setName(result.getString("name"));

                    listActivity.add(activity);
                }

                listPossibleActivities.add(listActivity);
            }

            if (listPossibleActivities.size() > 0)
            {
                retainedActivities = listPossibleActivities.get(0);

                for (int i = 1; i < listPossibleActivities.size(); ++i)
                {
                    List<Activity> toRemove = new ArrayList<>();

                    for (Activity activity : retainedActivities)
                    {
                        boolean found = true;

                        for (Activity activity2 : listPossibleActivities.get(i))
                        {

                            if (activity2.getName().equals(activity.getName()))
                            {
                                found = false;
                            }
                        }

                        if (found)
                        {
                            toRemove.add(activity);
                        }

                    }

                    retainedActivities.removeAll(toRemove);
                }
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

        return retainedActivities;
    }

    @Override
    public int getActivityIdentifier(Activity activity) throws DaoException
    {
        Connection con = null;
        int identifier = 0;

        try
        {
            con = this.dao.getConnection();

            String query = "Select ac_id id from activity where ac_name=?";
            ResultSet result;

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, activity.getName());

            result = preparedStatement.executeQuery();

            if (result.next())
            {
                identifier = result.getInt("id");
            }
            else
            {
                throw new DaoException("Activity Introuvable");
            }

        }
        catch (SQLException ex)
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

        return identifier;
    }

}
