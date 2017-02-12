import com.google.gson.Gson;
import entities.Event;
import entities.User;

import javax.servlet.annotation.WebServlet;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "DiplomServlet", urlPatterns = {"/api", "/main/diplom"})
public class MyServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        EventService eventService = new EventService();
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        List<Event> events = eventService.getAllEvent();
        Gson gson = new Gson();
        String answer = gson.toJson(events);
        out.println(answer);
        out.flush();
        eventService.shotDown();
    }
}
