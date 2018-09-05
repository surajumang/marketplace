package com.care.controller;

import com.care.beans.Member;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtil {
    private static Logger logger = Logger.getLogger("CommonUtil");

    public static Member getCurrentUserFromSession(HttpSession httpSession){
        return (Member)httpSession.getAttribute("currentUser");
    }

    public static int getJobIdFromRequest(HttpServletRequest request){
        int jobId = -1;
        try {
            logger.info("-------- " + request.getParameter("id") + " -------");
            jobId = Integer.parseInt(request.getParameter("id"));
        }catch (IllegalArgumentException e){
            logger.log(Level.SEVERE, "Application ID", e);
        }
        return jobId;
    }
}