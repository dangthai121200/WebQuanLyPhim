/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.text.WordUtils;
import regex.Regex;
import service.ServiceAdmin;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class ControllerAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String page = request.getParameter("form");
        switch (page) {
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "update":
        {            
            try {
                update(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            default:
                break;
        }
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean checkUsername = Regex.checkGmail(username);
        boolean checkPassword = Regex.checkPassWord(password);
        if (checkUsername == false || checkPassword == false) {
            request.setAttribute("errorLogin", "errorlogin");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            Admin admin = new ServiceAdmin().checkLogin(username, password);
            if (admin == null) {
                request.setAttribute("errorLogin", "errorlogin");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession(true);
                session.setAttribute("admin", admin);
                response.sendRedirect(request.getContextPath() + "/ControllerPage?page=home");
            }
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("returnpassword");

        boolean checkFullname = checkFullName(request, response, fullname);
        boolean checkGmail = checkGmail(request, response, username);
        boolean checkPassword = checkPassword(request, response, password, rePassword);

        if (checkFullname && checkGmail && checkPassword) {
            saveAdmin(fullname, username, password);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            if (checkFullname) {
                request.setAttribute("fullname", fullname);
            }
            if (checkGmail) {
                request.setAttribute("username", username);
            }
            request.getRequestDispatcher("createaccount.jsp").forward(request, response);
        }

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullname");
        String gmail = request.getParameter("gmail");
        String oldPassword = request.getParameter("oldpassword");
        String newPassword = request.getParameter("newpassword");
        String numberPhone = request.getParameter("numberphone");

        //??o???n code sau d???ng ????? ??p ki???u sang ng??y n???u c?? l???i s??? g???i v??? 1 l???i errorDate th??ng b??o errorDate
        Date birthday = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            birthday = formatter.parse(request.getParameter("birthday"));
        } catch (Exception ex) {
            request.setAttribute("errorDate", "errorDate");
        }
        ////

        boolean checkNumberPhone = checkPhoneNumber(request, response, numberPhone);
        boolean checkFullname = checkFullName(request, response, fullName);
        boolean checkGmail = changeMail(request, response, gmail);
        boolean checkPassword = changePassword(request, response, id, oldPassword, newPassword);

        if (request.getAttributeNames().hasMoreElements()) {
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.getWriter().write("thanh cong");
            new ServiceAdmin().update(id, fullName, newPassword, birthday, gmail, Integer.parseInt(numberPhone));
            Admin admin = new ServiceAdmin().findById(id);
            HttpSession session = request.getSession(true);
            session.removeAttribute("admin");
            session.setAttribute("admin", admin);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }

    //d??ng ????? checknumberphone n???u number l?? 1 c?? chu???i th?? tr??? v??? 1 l???i errorNumberPhone th??ng b??o errornumberphone
    protected boolean checkPhoneNumber(HttpServletRequest request, HttpServletResponse response, String numberPhone) {
        if (NumberUtils.isCreatable(numberPhone) == false) {
            request.setAttribute("errorNumberPhone", "errornumberphone");
            return false;
        }
        return true;
    }

    //n???u fullname kh??c v???i regex fullname s??? g???i 1 l???i t??n errorFullname v???i th??ng b??o errorfullname
    protected boolean checkFullName(HttpServletRequest request, HttpServletResponse response, String fullname) {
        if (Regex.checkFullName(fullname) == false) {
            request.setAttribute("errorFullname", "errorfullname");
            return false;
        }
        return true;
    }

    //n???u username sai c?? ph??p s??? c?? 1 l???i t??n errorUserName th??ng b??o errorusername  g???i v??? client
    protected boolean checkGmail(HttpServletRequest request, HttpServletResponse response, String gmail) {
        if (Regex.checkGmail(gmail) == false) {
            request.setAttribute("errorUserName", "errorusername");
            return false;
        } else {
            if (new ServiceAdmin().checkUserName(gmail)) {
                request.setAttribute("errorExistsUserName", "eixtsusername");
                return false;
            }
        }
        return true;
    }

    //n???u password kh??c v???i returnpassword s??? g???i 1 l???i t??n errorRePassword th??ng b??o errorreturnpassword
    //n???u passsword gi???ng retrun v?? password kh??c c?? ph??p Regex password s??? g???i 1 l???i t??n errorPassword th??ng b??o errorpassword
    protected boolean checkPassword(HttpServletRequest request, HttpServletResponse response, String password, String rePassword) {
        if (Regex.checkPassWord(password) == false) {
            request.setAttribute("errorPassword", "errorpassword");
            return false;
        } else {
            if (password.equals(rePassword) == false) {
                request.setAttribute("errorPassword", "errorreturnpassword");
                return false;
            }
        }
        return true;
    }

    //l??u th??ng tin ????ng k??
    protected void saveAdmin(String fullname, String username, String password) {
        new ServiceAdmin().add(new Admin(WordUtils.capitalizeFully(fullname), username, password));
    }

    //Ki???m tra tr?????c khi thay ?????i password
    //M???t kh???u c?? s??i s??? g???i 1 l???i errorPassword th??ng b??o errorpassword
    //m???t kh???u m???i kh??ng ????ng y??u c???u s??? g???i 1 l???i errorNewPassword th??ng b??o errornewpassword
    protected boolean changePassword(HttpServletRequest request, HttpServletResponse response, int id, String oldPassword, String newPassword) {
        boolean checkPassword = new ServiceAdmin().checkPassword(id, oldPassword);
        if (checkPassword) {
            if (newPassword.length() > 0) {
                if (Regex.checkPassWord(newPassword)) {
                    return true;
                } else {
                    request.setAttribute("errorNewPassword", "errornewpassword");
                    return false;
                }
            }else{
                return true;
            }
        } else {
            request.setAttribute("errorPassword", "errorpassword");
            return false;
        }
    }

    //ki???m tra mail thay ?????i n???u sai quy t???c tr??? v??? 1 l???i errorGmail v???i th??ng b??o errorgmail
    protected boolean changeMail(HttpServletRequest request, HttpServletResponse response, String gmail) {
        boolean checkMail = Regex.checkGmail(gmail);
        if (checkMail == false) {
            request.setAttribute("errorGmail", "errorgmail");
        }
        return checkMail;
    }
    @Override
    public void destroy() {
        HibernateMovie.closeSession(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
