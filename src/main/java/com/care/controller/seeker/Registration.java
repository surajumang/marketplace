package com.care.controller.seeker;

import com.care.model.Member;
import com.care.dto.form.RegistrationFormDTO;
import com.care.dto.form.SeekerRegistrationDTO;
import com.care.dto.form.SitterRegistrationDTO;
import com.care.model.MemberType;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Registration extends HttpServlet {
    Logger logger = Logger.getLogger("SeekerRegistration");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("currentPage");
        logger.info(page);

        MemberType memberType = MemberType.valueOf(request.getParameter("memberType"));
        Class<? extends FormBean> detailPage;

        if (memberType == MemberType.SEEKER){
            detailPage = SeekerRegistrationDTO.class;
        }
        else {
            detailPage = SitterRegistrationDTO.class;
        }

        FormBean registrationDetails = FormPopulator.populate(request, detailPage);
        logger.info(registrationDetails + " " );
        Map<String, String> errors = new HashMap<String, String>();

        registrationDetails.validateCustom(errors);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        logger.info(errors + " ");
        request.setAttribute("errors", errors);
        if(errors.isEmpty()){
            /*
            Forward the rquest to Login or make the user logged in here and then redirect to the Home page.
             */
            page = "/index.jsp";
            request.setAttribute("registrationMessage", "Registration SuccessFul. You can now log in");
            RegistrationFormDTO registrationFormDTO = (RegistrationFormDTO)registrationDetails;

            logger.info("Without errors");
            logger.info(registrationFormDTO.getMemberType());
            accountService.enroll(registrationFormDTO);
            logger.info("Back at servlet");
        }

        request.setAttribute("formErrors", (RegistrationFormDTO)registrationDetails);
        getServletContext().getRequestDispatcher(page).forward(request, response);

    }
}
