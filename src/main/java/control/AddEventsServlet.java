package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseUtility;
import model.EventBean;
import model.EventDao;

/**
 * Servlet implementation class AddEventsServlet
 */
@WebServlet("/AddEventsServlet")
public class AddEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
        String date = request.getParameter("date");
        String venue = request.getParameter("venue");
        String description = request.getParameter("description");
        String eventType = request.getParameter("type");
        String organizer = request.getParameter("organizer");
        String price = request.getParameter("price");
        String artistId = request.getParameter("artist_id");
        String image = request.getParameter("image");

        // Parse the date string into a LocalDateTime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime eventDate = LocalDateTime.parse(date, formatter);

        // Create a new EventBean object
        EventBean event = new EventBean();
        event.setName(name);
        event.setDate(eventDate);
        event.setVenue(venue);
        event.setDescription(description);
        event.setEventType(eventType);
        event.setOrganizer(organizer);
        event.setPrice(Float.parseFloat(price));
        event.setArtistId(Integer.parseInt(artistId));
        event.setImage(image);

        // Use the EventDao to add the event to the database
        EventDao eventDao = new EventDao();
        try {
            eventDao.addEvent(event);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error while adding event", e);
        }

        // Redirect to the index.jsp page
        response.sendRedirect("index.jsp");
	}

}
