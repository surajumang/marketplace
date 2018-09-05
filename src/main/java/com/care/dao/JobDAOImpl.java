/*

*/
package com.care.dao;

import com.care.model.Job;
import com.care.model.Member;
import com.care.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class JobDAOImpl implements JobDAO {

    private Logger logger = Logger.getLogger("JobDAOImpl");

    public JobDAOImpl(){ }

    public Job getJob(int jobId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Get job method");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION " +
                "WHERE ID=?");
        statement.setInt(1,jobId);
        ResultSet resultSet = statement.executeQuery();
        return populateJob(resultSet);
    }
    /*
    Post a job
     */
    public int addJob(Job job) throws SQLException {
        logger.info("Adding a Job" + job);
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO JOB(TITLE, POSTED_BY, HOURLY_PAY, START_DATE, " +
                "END_DATE) VALUES (?, ?, ?, ?, ? )");
        statement.setString(1, job.getTitle());
        statement.setInt(2, job.getSeekerId());
        statement.setDouble(3, job.getHourlyPay());
        statement.setDate(4, job.getStartDate());
        statement.setDate(5, job.getEndDate());

        return statement.executeUpdate();
    }

    public int deleteJob(int jobId) throws SQLException {
        return 0;
    }

    public int editJob(Job job) throws SQLException {
        return addJob(job);
    }

    /*
    [todo]
     */
    public List<Job> getAllJobs(int postedBy) throws SQLException {
        logger.info("GetAllJobs Seeker");
        List<Job> jobs = new ArrayList<Job>();
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT ID, TITLE, STATUS, START_DATE, END_DATE FROM JOB " +
                "WHERE POSTED_BY = ?");
        statement.setInt(1, postedBy);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            logger.info("Picked one row from the result set");
            jobs.add(populateJob(resultSet));
        }
        return jobs;
    }
    /*
    [TODO]
    All active jobs only. and the ones not applied by the sitter.

     */
    public List<Job> getAllJobs() throws SQLException {
        List<Job> allJobs = new ArrayList<Job>();
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM JOB WHERE STATUS = ?");
        statement.setString(1, Status.ACTIVE.name());
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            logger.info("Picked one row from the result set");
            allJobs.add(populateJob(resultSet));
        }
        return allJobs;
    }
    /*
    Deleting a job must also delete all application corresponding to it.
     */
    public int deleteJob(Member member, int jobId) throws SQLException {
        logger.info("Close Seeker's Job");
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE JOB SET STATUS='INACTIVE'" +
                "WHERE ID=? AND POSTED_BY=?");
        logger.info(jobId + " JobId ID to create job from DB" );
        statement.setInt(1, jobId);
        return statement.executeUpdate();

    }

    public int deleteAllJobs(int postedBy) throws SQLException {
        logger.info("Close Seeker's Job");
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Acquired connection");
        PreparedStatement statement = connection.prepareStatement("UPDATE JOB SET STATUS='INACTIVE'" +
                "WHERE POSTED_BY=?");
        logger.info( " JobId ID to create job from DB" + postedBy);
        statement.setInt(1, postedBy);
        return statement.executeUpdate();
    }

    private Job populateJob(ResultSet resultSet) throws SQLException{
        Job job = new Job();

        job.setTitle(resultSet.getString("TITLE"));
        job.setId(resultSet.getInt("ID"));
        job.setHourlyPay(resultSet.getDouble("HOURLY_PAY"));
        job.setStartDate(resultSet.getDate("START_DATE"));
        job.setEndDate(resultSet.getDate("END_DATE"));
        job.setStatus(Status.valueOf(resultSet.getString("STATUS")));

        return job;
    }
}
