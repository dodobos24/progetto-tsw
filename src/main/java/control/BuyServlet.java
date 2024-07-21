package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CartBean;
import model.CartDao;
import model.CartItemBean;
import model.CartItemDao;
import model.EventBean;
import model.EventDao;
import model.TicketBean;
import model.TicketDao;
import model.UserBean;
import model.UserDao;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
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
		UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Recupera il carrello dell'utente
            CartDao cartDao = new CartDao();
            CartItemDao cartItemDao = new CartItemDao();
            TicketDao ticketDao = new TicketDao();

            CartBean cart = cartDao.getCartByUserId(user.getId());
            List<CartItemBean> cartItems = cartItemDao.getCartItemsByCartId(cart.getId());

            // Calcola il prezzo totale del carrello
            float totalPrice = 0;
            for (CartItemBean item : cartItems) {
                EventDao eventDao = new EventDao();
                EventBean event = eventDao.getEventById(item.getEventId());
                totalPrice += event.getPrice() * item.getQuantity();
                System.out.println("Prezzo totale del carrell: "+totalPrice);
            }

            // Verifica se il saldo dell'utente è sufficiente
            if (user.getSaldo() < totalPrice) {
                request.setAttribute("errorMessage", "Saldo insufficiente.");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }

            // Procedi con l'acquisto
            for (CartItemBean item : cartItems) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    TicketBean ticket = new TicketBean();
                    ticket.setEventId(item.getEventId());
                    ticket.setUserId(user.getId());
                    ticket.setPurchaseDate(LocalDateTime.now());
                    ticketDao.addTicket(ticket);
                }
            }

            // Deduce il saldo dell'utente
            user.setSaldo(user.getSaldo() - totalPrice);
            UserDao userDao = new UserDao();
            userDao.updateUser(user);

            // Svuota il carrello
            cartItemDao.clearCart(cart.getId());

            response.sendRedirect("acquistoEffettuato.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Errore durante l'elaborazione dell'acquisto.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
	}

}
