package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EventBean;
import model.EventDao;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EventDao dao = new EventDao();
        String eventType = request.getParameter("eventType");
        String redirectedPage = request.getParameter("page");
        
        try {
            if (eventType != null && !eventType.isEmpty()) {
                ArrayList<EventBean> events = (ArrayList<EventBean>) dao.getEventsByType(eventType);
                request.setAttribute("events", events);
                request.setAttribute("eventType", eventType);
            } else {
                ArrayList<ArrayList<EventBean>> eventTypes = new ArrayList<>();
                
                eventTypes.add((ArrayList<EventBean>) dao.getEventsByType("Musica"));
                eventTypes.add((ArrayList<EventBean>) dao.getEventsByType("Festival"));
                eventTypes.add((ArrayList<EventBean>) dao.getEventsByType("Teatro"));
                eventTypes.add((ArrayList<EventBean>) dao.getEventsByType("Arte"));
                eventTypes.add((ArrayList<EventBean>) dao.getEventsByType("Sport"));

                request.getSession().setAttribute("eventTypes", eventTypes); 
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/events.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
