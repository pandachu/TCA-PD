package controller;

import Data_Objects.Course;
import ToolBox.Converter;
import ToolBox.Validator;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.AccountFacade;
import session.AddHoursManager;
import session.CourseFacade;
import session.CourseSignUpManager;

public class ControllerServlet extends HttpServlet {

    //Account currentAccount = new Account();
    @EJB
    CourseFacade courseFacade;
    @EJB
    AddHoursManager addhoursmanager;
    @EJB
    AccountFacade account;
    @EJB
    CourseSignUpManager coursesignupmanager;
    Converter converter = new Converter();
    Validator validator = new Validator();
//    @Override
//    public void init() throws ServletException {
//
//        //this is called only once when application is deployed
//
//    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String temp = request.getRemoteUser();

        String userPath = request.getServletPath();

        if (userPath.equals("/teacher/viewcourselist")) {
            List<Course> courselist = courseFacade.findNextMonthCourses();
            getServletContext().setAttribute("courseList", courselist);
            userPath = "/teacher/courselist.jsp";
        }
        else if (userPath.equals("/teacher/addhours"))
        {
            userPath = "/teacher/addhours.jsp";
        }
            // use RequestDispatcher to forward request internally
            //String url = "/WEB-INF/view" + userPath + ".jsp";
            String url = userPath;

            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        
        
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        if (userPath.equals("/teacher/addhours.jsp-submit")) {
            //in case of error, send them back
            userPath = "/teacher/addhours.jsp";

            String date2 = request.getParameter("date");
            String numHours2 = request.getParameter("numHours");
            String method = request.getParameter("method");
            String hostOrg = request.getParameter("hostOrg");
            String location = request.getParameter("location");
            String type = request.getParameter("type");
            String topicName = request.getParameter("topicName");

            //check for empty
            //TODO: replace with Validator class method, validateAddHours
            if (request.getParameter("date").isEmpty() || request.getParameter("date").length() < 10) {
                request.setAttribute("errormsg", "Incorrect date format (mm/dd/yyyy).");
            } else if (request.getParameter("numHours").isEmpty()) {
                request.setAttribute("errormsg", "Number of hours cannot be blank.");
            } else if (request.getParameter("method") == null) {
                request.setAttribute("errormsg", "Please choose a method.");
            } else if (request.getParameter("hostOrg").isEmpty()) {
                request.setAttribute("errormsg", "Hosting organization cannot be blank.");
            } else if (request.getParameter("location") == null) {
                request.setAttribute("errormsg", "Please choose a location.");
            } else if (request.getParameter("type") == null) {
                request.setAttribute("errormsg", "Please choose a type.");
            } else if (request.getParameter("topicName").isEmpty()) {
                request.setAttribute("errormsg", "Topic cannot be blank.");
            } else {
                try {
                    Date date = converter.ConvertStringToDate(date2);
                    int numHours = converter.ConvertStringToInt(numHours2);
                    addhoursmanager.addHours(date, numHours, method, hostOrg, location, type, topicName);
                    userPath = "/teacher/addhoursconfirmation.jsp";
                } catch (Exception e) {
                    request.setAttribute("errormsg", e);
                    userPath = "/teacher/addhours.jsp";
                }
            }
            request.setAttribute("date", request.getParameter("date"));
            request.setAttribute("numHours", request.getParameter("numHours"));
            request.setAttribute("method", request.getParameter("method"));
            request.setAttribute("location", request.getParameter("location"));
            request.setAttribute("type", request.getParameter("type"));
            request.setAttribute("hostOrg", request.getParameter("hostOrg"));
            request.setAttribute("topicName", request.getParameter("topicName"));
        } else if (userPath.equals("/teacher/viewcourselist-signup")) {
            //TODO get account
            String courseId = request.getParameter("courseId");
            int err = 0;
            if (courseId != null && !courseId.isEmpty()) {
                Course course = courseFacade.find(Integer.parseInt(courseId));
                err = coursesignupmanager.signUpForCourse(course, null);
            }

            String msg = "ERROR: There was an error processing your request. Please try again or contact a system admin.";
            if (err == -1) {
                msg = "ERROR: the course is full.";
            } else if (err == 1) {
                msg = "You have successfully signed up.";
            }
            request.setAttribute("msg", msg);

            userPath = "/teacher/courselistconfirm.jsp";

        }

        // use RequestDispatcher to forward request internally
        //String url = "/WEB-INF/view" + userPath + ".jsp";
        String url = userPath;
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
