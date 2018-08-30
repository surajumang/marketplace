/*

*/
package com.care.dao;

import com.care.beans.Application;
import com.care.beans.Status;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationDAOImpl implements ApplicationDAO {

    private Logger logger = Logger.getLogger("ApplicationDAOImpl");
    // called to edit or post an Application
    private ApplicationDAOImpl(){

    }

    public static Application getApplication(int id, Connection connection){
        Application application = new Application();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION " +
                    "WHERE ID=?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

        }catch (SQLException e){
        }
        return application;
    }
    /*
    Gets all the applications corresponding to a particular JobId.
     */
    public static List<Application> getAllApplications(int JobId, Connection connection){
        List<Application> applications = new ArrayList<Application>();

        return applications;

    }
    /*
    Edits the details of the application corresponding to newApplication.getId()
    Id of the old and the new Application must remain same.
     */

    public int addApplication(Application application) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO APPLICATION" +
                    "VALUES(?,?,?,?,?,?,?)");
            statement.setInt(1,application.getId());
            statement.setInt(2,application.getJobId());

            int rowsAffected = statement.executeUpdate("insert into");
        }catch (SQLException e){
            logger.log(Level.SEVERE, "AddApplication", e.getCause());
        }
        return 0;
    }

    public boolean checkOwner(int applicationId, int memberId) throws SQLException {

        return false;
    }

    public Application getApplication(int applicationId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION WHERE ID = ?");
            statement.setInt(1,applicationId);

            ResultSet resultSet = statement.executeQuery();

        }catch (SQLException e){
            logger.log(Level.SEVERE, "AddApplication", e.getCause());
        }
        return null;
    }

    public List<Application> getAllApplications(int memberId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION WHERE MEMBER_ID = ?");
            statement.setInt(1, memberId);

            ResultSet resultSet = statement.executeQuery();

        }catch (SQLException e){
            logger.log(Level.SEVERE, "AddApplication", e.getCause());
        }
        return null;
    }

    public int deleteApplication(int applicationId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        int rowsAffected = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE ID = ?");
            statement.setString(1, Status.INACTIVE.name());
            statement.setInt(2,applicationId);

            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "One Application deleted", e.getCause());
        }
        return rowsAffected;
    }

    public int deleteAllApplications(int userId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        int rowsAffected = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE MEMBER_ID = ?");
            statement.setString(1, Status.INACTIVE.name());
            statement.setInt(2, userId);

            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "AddApplication", e.getCause());
        }
        return rowsAffected;
    }
}
