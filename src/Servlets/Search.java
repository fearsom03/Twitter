package Servlets;

import com.Db.DBManager;
import com.Db.User;
import com.Db.UserDATA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/Search")
public class Search extends HttpServlet {

    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("nameOfPeople");
        Set<UserDATA> arr = new HashSet<>();
        arr = dbManager.searchByName(name);
        HttpSession ses = request.getSession();
        ses.setAttribute("people",arr);
        request.setAttribute("people",arr);
        response.sendRedirect("/");
    }
}

