package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EventDao;

/**
 * Servlet implementation class DeleteEventServlet
 */
@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String returnUrl = request.getParameter("returnUrl");
        if (returnUrl == null || returnUrl.isEmpty()) {
            returnUrl = "defaultPage.jsp";
        }
        
        System.out.println("Received returnUrl: " + returnUrl); // Debug

        int eventId = Integer.parseInt(request.getParameter("eventId"));
        EventDao eventDao = new EventDao();

        try {
            eventDao.deleteEvent(eventId);
            response.sendRedirect(returnUrl);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
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
