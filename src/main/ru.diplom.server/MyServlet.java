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
    UserService userService = null;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    public void destroy() {
        if (eventService!=null) {
            eventService.shotDown();
        }
        if(userService!=null){
            userService.shotDown();
        }
    }

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
        userService = new UserService();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        String method = String.valueOf(request.getParameter("method"));

        switch (method){
            case "getAllEvents":{
                List<Event> events = eventService.getAllEvent();
                Gson gson = new Gson();
                String answer = gson.toJson(events);
                out.println(answer);
                out.flush();
            }break;
            case "getAllUsersForLastHour":{
                List<User> users = userService.getAllUsersForLastHour();
                Gson gson =new Gson();
                String answer = gson.toJson(users);
                out.print(answer);
                out.flush();
            }break;
            case "insertNewUser":{
                String name = String.valueOf(request.getParameter("name"));
                Double lo = Double.valueOf(request.getParameter("lo"));
                Double la = Double.valueOf(request.getParameter("la"));
                userService.insertNewUser(name,lo,la);
            }
            case "insertNewEvent":{
                String name = String.valueOf(request.getParameter("name"));
                Integer idUser = Integer.valueOf(request.getParameter("idUser"));
                eventService.insertNewEvent(name,idUser);
            }
            default:
                out.print("NOTHING TO READ");
        }
    }
}
