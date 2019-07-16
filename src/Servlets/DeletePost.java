package Servlets;

import com.Db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeletePost")
public class DeletePost extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        Long id = Long.parseLong(ids);
        dbManager.deletePost(id);
        response.sendRedirect("/");

    }

}
