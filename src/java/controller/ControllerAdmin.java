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

        //Đoạn code sau dụng để ép kiểu sang ngày nếu có lỗi sẽ gửi về 1 lỗi errorDate thông báo errorDate
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

    //dùng để checknumberphone nếu number là 1 có chuỗi thì trả về 1 lỗi errorNumberPhone thông báo errornumberphone
    protected boolean checkPhoneNumber(HttpServletRequest request, HttpServletResponse response, String numberPhone) {
        if (NumberUtils.isCreatable(numberPhone) == false) {
            request.setAttribute("errorNumberPhone", "errornumberphone");
            return false;
        }
        return true;
    }

    //nếu fullname khác với regex fullname sẽ gửi 1 lỗi tên errorFullname với thông báo errorfullname
    protected boolean checkFullName(HttpServletRequest request, HttpServletResponse response, String fullname) {
        if (Regex.checkFullName(fullname) == false) {
            request.setAttribute("errorFullname", "errorfullname");
            return false;
        }
        return true;
    }

    //nếu username sai cú pháp sẽ có 1 lỗi tên errorUserName thông báo errorusername  gửi về client
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

    //nếu password khác với returnpassword sẽ gửi 1 lỗi tên errorRePassword thông báo errorreturnpassword
    //nếu passsword giống retrun và password khác cú pháp Regex password sẽ gửi 1 lỗi tên errorPassword thông báo errorpassword
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

    //lưu thông tin đăng ký
    protected void saveAdmin(String fullname, String username, String password) {
        new ServiceAdmin().add(new Admin(WordUtils.capitalizeFully(fullname), username, password));
    }

    //Kiểm tra trước khi thay đổi password
    //Mật khẩu cũ sài sẽ gửi 1 lỗi errorPassword thông báo errorpassword
    //mật khẩu mới không đúng yêu cầu sẽ gửi 1 lỗi errorNewPassword thông báo errornewpassword
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

    //kiểm tra mail thay đổi nếu sai quy tắc trả về 1 lỗi errorGmail với thông báo errorgmail
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
