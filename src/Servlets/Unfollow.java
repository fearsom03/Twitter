package Servlets;

import com.Db.DBManager;
import com.Db.Followers;
import com.Db.User;
import com.Db.UserDATA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebServlet("/Unfollow")
public class Unfollow extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id1 = request.getParameter("id");
        Long id = Long.parseLong(id1);
        HttpSession session = request.getSession();
        User userMain =(User) session.getAttribute("user");
        User user = dbManager.getUserByID(id);
        Set<Followers> arr = dbManager.getAllFollow();
        for (Followers followers:arr) {
            if (followers.getUserMe().getId().equals(userMain.getId()) && followers.getUserToFollow().getId().equals(user.getId())){
                Long a =followers.getId();
                dbManager.deleteFollow(a);
            }
        }
        //dbManager.unFollow(userMain,user);
        response.sendRedirect("/");


    }
}
