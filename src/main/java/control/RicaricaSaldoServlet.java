package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DatabaseUtility;
import model.UserBean;

/**
 * Servlet implementation class RicaricaSaldoServlet
 */
@WebServlet("/RicaricaSaldoServlet")
public class RicaricaSaldoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicaricaSaldoServlet() {
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
		HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("currentSessionUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            float amount = Float.parseFloat(request.getParameter("amount"));

            if (amount <= 0) {
                request.setAttribute("errorMessage", "L'importo deve essere positivo.");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }

            try (Connection connection = DatabaseUtility.getConnection()) {
                String updateSaldoQuery = "UPDATE users SET saldo = saldo + ? WHERE user_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(updateSaldoQuery)) {
                    statement.setFloat(1, amount);
                    statement.setInt(2, user.getId());
                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        user.setSaldo(user.getSaldo() + amount);
                        session.setAttribute("currentSessionUser", user);

                        response.sendRedirect("account.jsp");
                    } else {
                        request.setAttribute("errorMessage", "Errore durante l'aggiornamento del saldo.");
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Si è verificato un errore nel database.");
                request.getRequestDispatcher("account.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "L'importo inserito non è valido.");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }
	}

}
