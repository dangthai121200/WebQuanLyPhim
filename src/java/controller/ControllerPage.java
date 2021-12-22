/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Admin;
import model.Category;
import model.Filmtype;
import model.Movie;
import model.MovieAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ServiceAdmin;
import service.ServiceCategory;
import service.ServiceFilmType;
import service.ServicelMovie;
import service.ServiceMovieAdmin;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class ControllerPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        String page = request.getParameter("page");
        if (admin != null && page != null) {
            switch (page) {
                case "home":
                    home(request, response);
                    break;
                case "listmovie":
                    listMovie(request, response);
                    break;
                case "addmovie":
                    addMovie(request, response);
                    break;
                case "logout":
                    logOut(request, response);
                    break;
                case "profile":
                    profile(request, response);
                    break;
                case "createaccount":
                    createAccount(request, response);
                    break;
                default:
                    request.getRequestDispatcher("404.jsp").forward(request, response);
                    break;
            }
        } else if (page != null) {
            switch (page) {
                case "createaccount":
                    createAccount(request, response);
                    break;
                default:
                    request.getRequestDispatcher("404.jsp").forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MovieAdmin> list = new ServiceMovieAdmin().getAll();
        int countMovie = new ServicelMovie().countMovie();
        int countNewMovie = new ServicelMovie().countNewMovieAdd();
        int countAdmin = new ServiceAdmin().countAdmin();
        request.setAttribute("movieAdmins", list);
        request.setAttribute("countMovie", countMovie);
        request.setAttribute("countAdmin", countAdmin);
        request.setAttribute("countNewMovie", countNewMovie);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private void listMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies = new ServicelMovie().getAll();

        request.setAttribute("movies", movies);
        request.getRequestDispatcher("listmovie.jsp").forward(request, response);
    }

    private void addMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Filmtype> filmTypes = new ServiceFilmType().getAll();
        List<Category> categorys = new ServiceCategory().getAll();
        request.setAttribute("filmTypes", filmTypes);
        request.setAttribute("categorys", categorys);
        request.getRequestDispatcher("addmovie.jsp").forward(request, response);
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("createaccount.jsp").forward(request, response);
    }
@Override
    public void destroy() {
        HibernateMovie.closeSession(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
