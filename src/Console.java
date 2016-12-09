import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "Console", urlPatterns = "/console/*")
public class Console extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String command = request.getHeader("command");
        String responseText;

        // Read command response from properties file
        Properties responses = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("responses.properties");
        responses.load(in);

        switch (command) {
            case "help":
                responseText = responses.getProperty("help");
                break;
            case "resume":
                responseText = responses.getProperty("resume");
                break;
            case "name":
                responseText = ASCII.draw("Jacob Greenway", 120, 12).toString();
                break;
            case "contact":
                responseText = responses.getProperty("contact");
                break;
            default:
                responseText = responses.getProperty("unknown");
                break;
        }

        responseText += "<br>";
        response.getWriter().write(responseText);
    }
}
