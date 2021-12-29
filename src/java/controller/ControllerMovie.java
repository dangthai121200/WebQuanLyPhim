/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Admin;
import model.Category;
import model.CategoryMoive;
import model.Filmtype;
import model.Movie;
import model.MovieAdmin;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import org.hibernate.Session;

import service.ServiceCategory;
import service.ServiceCategoryMovie;
import service.ServiceFilmType;
import service.ServiceMovieAdmin;
import service.ServicelMovie;
import util.Contraints;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class ControllerMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerCategory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("search") != null) {
            if (!"".equals(request.getParameter("search").toString())) {
                String str = request.getParameter("search").toString();
                List<Movie> movies = new ServicelMovie().seachMovie(str);
                request.setAttribute("movies", movies);
                request.getRequestDispatcher("listmovie.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/ControllerPage?page=listmovie");
            }
        }
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action").toString();
            String idMovie = request.getParameter("idMovie").toString();
            ServicelMovie servicelMovie = new ServicelMovie();
            Movie movie = servicelMovie.findById(Integer.parseInt(idMovie));
            switch (action) {
                case "edit":
                    //request.setAttribute("movie", movie);
                    //request.getRequestDispatcher("editMovie.jsp").forward(request, response);
                    break;
                case "delete":                 
                    servicelMovie.delete(movie);
                    response.sendRedirect(request.getContextPath() + "/ControllerPage?page=listmovie");
                    break;
            }
        }

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
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setHeaderEncoding("UTF-8");
        String nameMovie = "";
        char[] categorys = null;
        String country = "";
        String diretor = "";
        int timeMovie = Integer.MAX_VALUE;
        String yearOfManufacture = "";
        String status = "";
        String content = "";
        String result = "";
        int idFilmtype = Integer.MAX_VALUE;
        //láy value input từ form có sử dụng multipart/form-data     
        try {
            String nameImage = StringUtils.stripAccents(nameMovie).replaceAll(" ", "").toLowerCase();
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                String name = item.getFieldName();
                InputStream stream = item.getInputStream();
                switch (name) {
                    case "nameMovie":
                        nameMovie = Streams.asString(stream, "UTF-8");
                        break;
                    case "filmType":
                        idFilmtype = Integer.parseInt(Streams.asString(stream, "UTF-8"));
                        break;
                    case "category":
                        categorys = Streams.asString(stream, "UTF-8").toCharArray();
                        break;
                    case "country":
                        country = Streams.asString(stream, "UTF-8");
                        break;
                    case "diretor":
                        diretor = Streams.asString(stream, "UTF-8");
                        break;
                    case "timeMovie":
                        timeMovie = Integer.parseInt(Streams.asString(stream, "UTF-8"));
                        break;
                    case "yearOfManufacture":
                        yearOfManufacture = Streams.asString(stream, "UTF-8");
                        break;
                    case "status":
                        status = Streams.asString(stream, "UTF-8");
                        break;
                    case "content":
                        content = Streams.asString(stream, "UTF-8");
                        break;
                    case "files":
                        String fileName = item.getName();
                        try {
                            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                            //lấy đuôi file
                            fileName = fileName.substring(fileName.lastIndexOf("."));
                        } catch (Exception e) {
                        }
                        result = nameMovie + fileName;
                        File file = new File(Contraints.locationSaveImageMoive + result);
                        item.write(file);
                        break;
                }
            }

            //Lấy thể loai và loại phim
//            //tính thời gian cho phim đổi ra tiếng
            int minute = timeMovie;
            int hours = minute / 60;
            minute = minute % 60;
            Date time = new Date();
            time.setHours(hours);
            time.setMinutes(minute);
//
//            //Tạo đối tượng movie và lưu movie
  Filmtype filmType = new ServiceFilmType().findById(idFilmtype);
            Movie movie = null;

            try {
              
                movie = new Movie(filmType, WordUtils.capitalizeFully(nameMovie), WordUtils.capitalizeFully(country), WordUtils.capitalizeFully(diretor), time, status, "/image/" + result, content, Integer.parseInt(yearOfManufacture), new Date());
                ServicelMovie serviceMovie = new ServicelMovie();
                serviceMovie.add(movie);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                //Luu thông tin lien quan toi phim
                List<Movie> afterListMovie = new ServicelMovie().findByName(movie.getNameMovie(), true);
                Movie movieAfterAdd = afterListMovie.size() > 0 ? afterListMovie.get(0) : null;
                response.getWriter().println("after" + movieAfterAdd + "<br/>");

                Set categoysSet = new ServiceCategory().getAllByListId(categorys);
                response.getWriter().println(categoysSet + "<br/>");
                ServiceCategoryMovie serviceCategoryMovie = new ServiceCategoryMovie();
                serviceCategoryMovie.addLsitCategoryMovie(categoysSet, afterListMovie.get(0));

                //Luu thông tin tác vụ
                ServiceMovieAdmin serviceMovieAdmin = new ServiceMovieAdmin();
                Admin admin = (Admin) request.getSession(true).getAttribute("admin");
                serviceMovieAdmin.add(new MovieAdmin(admin,movieAfterAdd, new Date(), "Thêm phim"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/ControllerPage?page=home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        HibernateMovie.closeSession(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
