package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TicketBean;
import model.TicketDao;

/**
 * Servlet implementation class AcquistiServlet
 */
@WebServlet("/AcquistiServlet")
public class AcquistiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TicketDao ticketDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcquistiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<TicketBean> userTickets = ticketDao.getTicketByUser(userId);
            request.setAttribute("userTickets", userTickets);
            request.getRequestDispatcher("userPurchases.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Errore nel recupero dei biglietti dell'utente", e);
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
