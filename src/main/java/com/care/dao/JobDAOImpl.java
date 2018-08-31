/*

*/
package com.care.dao;

import com.care.beans.Job;
import com.care.beans.MemberType;
import com.care.beans.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class JobDAOImpl implements JobDAO {

    private Logger logger = Logger.getLogger("JobDAOImpl");

    private static JobDAOImpl ourInstance = new JobDAOImpl();


    public static JobDAOImpl getInstance(){
        return ourInstance;
    }
    private JobDAOImpl(){

    }

    public Job getJob(int jobId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();

        logger.info("Get job method");
        Job job = new Job();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION " +
                    "WHERE ID=?");
            statement.setInt(1,jobId);
            ResultSet resultSet = statement.executeQuery();


        }catch (SQLException e){
            e.getErrorCode();
        }
        return job;
    }
    /*
    Post a job
     */
    public int addJob(Job job) throws SQLException {
        return 0;
    }

    public boolean checkOwner(int postedBy, int jobId) throws SQLException {
        return false;
    }

    public int editJob(int jobId) throws SQLException {
        return 0;
    }
    public List<Job> getAllJobs(int postedBy) throws SQLException {
        logger.info("GetAllJobs Seeker");

        List<Job> jobs = new ArrayList<Job>();
        Connection connection = ConnectionUtil.getConnection();

        try {
            logger.info("Acquired connection");
            PreparedStatement statement = connection.prepareStatement("SELECT ID, TITLE, STATUS, START_DATE, END_DATE FROM JOB " +
                    "WHERE POSTED_BY = ?");
            statement.setInt(1, postedBy);
            logger.info(postedBy + " User ID to get job from DB");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                logger.info("Picked one row from the result set");
                Job job = new Job();
                job.setTitle(resultSet.getString("TITLE"));
                job.setId(resultSet.getInt("ID"));
                job.setStartDate(resultSet.getDate("START_DATE"));
                job.setEndDate(resultSet.getDate("END_DATE"));
                job.setStatus(Status.valueOf(resultSet.getString("STATUS")));

                jobs.add(job);
            }

        }catch (SQLException e){
            e.printStackTrace();
            logger.log(Level.SEVERE, "GetAllApplication", e.getCause());
            throw e;
        }
        return jobs;
    }
    public int deleteJob(int jobId) throws SQLException {
        return 0;
    }

    public int deleteAllJobs(int postedBy) throws SQLException {
        return 0;
    }
    
}
