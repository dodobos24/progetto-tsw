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
		
		ArrayList<ArrayList<EventBean>> eventTypes = new ArrayList<>();
		String redirectedPage = request.getParameter("page");
		
		try {
			ArrayList<EventBean> Musica = (ArrayList<EventBean>) dao.getEventsByType("Musica");
			ArrayList<EventBean> Festival = (ArrayList<EventBean>) dao.getEventsByType("Festival");
			ArrayList<EventBean> Teatro = (ArrayList<EventBean>) dao.getEventsByType("Teatro");
			ArrayList<EventBean> Arte = (ArrayList<EventBean>) dao.getEventsByType("Arte");
			ArrayList<EventBean> Sport = (ArrayList<EventBean>) dao.getEventsByType("Sport");
			
			eventTypes.add(Musica);
			eventTypes.add(Festival);
			eventTypes.add(Teatro);
			eventTypes.add(Arte);
			eventTypes.add(Sport);

			request.getSession().setAttribute("categorie", eventTypes);		

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + redirectedPage);
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
