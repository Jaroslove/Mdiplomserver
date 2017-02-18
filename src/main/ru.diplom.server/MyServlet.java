import com.google.gson.Gson;
import entities.Event;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "DiplomServlet", urlPatterns = {"/api", "/main/diplom"})
public class MyServlet extends javax.servlet.http.HttpServlet {

    EventService eventService = null;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    public void destroy() {
        if (eventService!=null) {
            eventService.shotDown();
        }
    }

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        String method = String.valueOf(request.getParameter("method"));
        if(method.equals("getAllEvents")){
            List<Event> events = eventService.getAllEvent();
            Gson gson = new Gson();
            String answer = gson.toJson(events);
            out.println(answer);
            out.flush();
        }else {
            out.print("NO");
            out.flush();
        }
        List<Event> events = eventService.getAllEvent();
        Gson gson = new Gson();
        String answer = gson.toJson(events);
        out.println(answer);
        out.flush();
    }
}
