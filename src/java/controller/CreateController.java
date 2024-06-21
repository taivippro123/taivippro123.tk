/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user_watch.DAO;
import user_watch.userDTO;
import user_watch.UserError;

/**
 *
 * @author huuda
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserError userError = new UserError();
        String url = ERROR;
        try {
            boolean checkValidation = true;
            DAO dao = new DAO();
            String userId = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            int role = Integer.parseInt(request.getParameter("role"));
            int password = Integer.parseInt(request.getParameter("password"));
            int confirm = Integer.parseInt(request.getParameter("confirm"));
            if (userId.length() < 2 || userId.length() > 10) {
                checkValidation = false;
                userError.setUserID("UserID must be in [2,10] characters");
            }
//            boolean checkDuplicate = dao.checkDuplicate(userID);
//            if (checkDuplicate) {
//                checkValidation = false;
//                userError.setUserIDError("Duplicate userID");
//            }
            if (fullName.length() < 5 || fullName.length() > 50) {
                checkValidation = false;
                userError.setFullName("Full Name must be in [5,50] characters");
            }
            if (confirm != password) {
                checkValidation = false;
                userError.setConfirm("The confirm password is not the same");
            }
            if (checkValidation) {
               boolean checkInsert = dao.createUser(new userDTO(userId, password, fullName, role));
                if (checkInsert) {
                    url = SUCCESS;
                } else {
//                    userError.setError("Unknow error!");
                    request.setAttribute("USER_ERROR", userError);

                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
            if(e.toString().contains("duplicate")){
                userError.setUserID("User ID has already exist !!!");
                request.setAttribute("USER_ERROR", userError);
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
