import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 1 on 08.02.2017.
 */
@WebServlet(name = "DiplomServlet", urlPatterns = {"/a/b/c","/main/diplom"})
public class MyServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String foo = new Connect().getCon();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>"+foo+"</h1>");
        out.flush();
    }
}
