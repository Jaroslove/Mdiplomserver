import com.google.gson.Gson;
import entities.User;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 1 on 08.02.2017.
 */
@WebServlet(name = "DiplomServlet", urlPatterns = {"/api", "/main/diplom"})
public class MyServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        List<User> users = new UserService().getAllUsersForLastHour();
        Gson gson = new Gson();
        String answer = gson.toJson(users);
        out.println(answer);
        out.flush();
    }
}
