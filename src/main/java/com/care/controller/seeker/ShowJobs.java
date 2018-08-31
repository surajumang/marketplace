package com.care.controller.seeker;

import com.care.beans.Member;
import com.care.dto.form.JobDTO;
import com.care.exception.IncompatibleUserTypeException;
import com.care.exception.NoUserLoggedInException;
import com.care.service.CommonUtil;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowJobs extends HttpServlet {

    Logger logger = Logger.getLogger("ShowJobs");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "ErrorPage.jsp";
        boolean userLoggedIn = false;
        Member currentMember = null;
        try {
            userLoggedIn = CommonUtil.isMemberLoggedIn(request);
            currentMember = CommonUtil.getLoggedInUserFromSession(request);
        }catch (NoUserLoggedInException e){
            logger.log(Level.SEVERE, "No user logged in", e.getCause());
        }catch (IncompatibleUserTypeException e){
            logger.log(Level.SEVERE, "Error fetching user", e.getCause());
        }

        if(! userLoggedIn || currentMember == null ){
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }
        List<JobDTO> myJobs = new ArrayList<JobDTO>();

        /*
        Db call to fetch the jobs created by this user.
        List<Job> lj = SeekerServiceImpl.getJobs();
        SeekerService will simply delegate this call to JobServiceImpl(member.getId()) -> Job form object.
        This will further be delegated to JobDAOImpl which will give a model class Job.
         */
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        myJobs = seekerService.listJobs(currentMember);

        request.setAttribute("myJobs", myJobs);
        logger.info("Dispatching to ShowMyJobs Page");
        RequestDispatcher rd = request.getRequestDispatcher("/Members/Seeker/ShowMyJobs.jsp");
        rd.forward(request, response);

    }
}
