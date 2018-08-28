package com.care.controller.seeker;

import com.care.dto.form.JobForm;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;
import com.care.validation.FormValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostJob extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        Check if user is logged in[Utility method]
        Set error parameter to be used by the Validator.
         */
        FormBean postJobForm = FormPopulator.populate(req, JobForm.class);
        FormValidator.validate(postJobForm, req);

        /*
            SeekerService.postJob(Job)
            JobService.postJob()
            JobDAOImpl.createJob()
         */

    }
}