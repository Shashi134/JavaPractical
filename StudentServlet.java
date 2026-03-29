import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String roll = request.getParameter("roll");
        String course = request.getParameter("course");

        out.println("<html><body>");
        out.println("<h2>Student Details</h2>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Roll No: " + roll + "</p>");
        out.println("<p>Course: " + course + "</p>");
        out.println("</body></html>");
    }
}