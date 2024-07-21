package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EventBean;
import model.EventDao;

/**
 * Servlet implementation class TrendingEventsServlet
 */
@WebServlet("/TrendingEventsServlet")
public class TrendingEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventDao eventDao = new EventDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrendingEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            List<EventBean> trendingEvents = eventDao.getTrendingEvents();
            request.setAttribute("trendingEvents", trendingEvents);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch trending events.");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
