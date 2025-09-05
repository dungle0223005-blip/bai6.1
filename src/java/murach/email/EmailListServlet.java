package murach.email;

import java.io.*;
import java.util.GregorianCalendar;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.http.*;

import murach.business.User;
import murach.data.UserDB;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        // Set current year for EL
        int currentYear = new GregorianCalendar().get(Calendar.YEAR);
        request.setAttribute("currentYear", currentYear);

        String url = "/index.jsp";

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        if (action.equals("join")) {
            url = "/index.jsp";
        } else if (action.equals("add")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            User user = new User(firstName, lastName, email);
            request.setAttribute("user", user);

            if (firstName == null || lastName == null || email == null ||
                firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                request.setAttribute("message", "Please fill out all three text boxes.");
                url = "/index.jsp";
            } else {
                request.setAttribute("message", null);
                UserDB.insert(user);
                url = "/thanks.jsp";
            }
        }

        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {
        doPost(request, response);
    }
}