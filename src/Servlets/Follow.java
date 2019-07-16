package Servlets;

import com.Db.DBManager;
import com.Db.Followers;
import com.Db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/Follow")
public class Follow extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idOtherUser;
        Long idMe;
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");
        idMe=user.getId();
        idOtherUser=request.getParameter("id");
        Long idOther=Long.parseLong(idOtherUser);
        System.out.println(idMe+" ==it's IDD =="+idOther);
        Set <Followers> arr = new HashSet<Followers>();
        arr = dbManager.getAllFollow();
        boolean check =dbManager.checkFollow(arr,idMe,idOther);
        if (!idMe.equals(idOther)){
        if (!check) {
            dbManager.follow(idMe, idOther);
        }}
        arr = dbManager.getAllFollow();
        ses.setAttribute("followers",arr);
        request.setAttribute("followers",arr);
        response.sendRedirect("/");
    }
}
