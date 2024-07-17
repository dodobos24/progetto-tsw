package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;
import model.CartDao;
import model.CartDaoInterface;
import model.CartItemBean;
import model.CartItemDao;
import model.CartItemDaoInterface;

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartDaoInterface cartDao;
    private CartItemDaoInterface cartItemDao;

    public CarrelloServlet() {
        super();
        this.cartDao = new CartDao();
        this.cartItemDao = new CartItemDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "addToCart":
                        addToCart(request, cart);
                        break;
                    case "removeFromCart":
                        removeFromCart(request, cart);
                        break;
                    case "updateCartItem":
                        updateCartItem(request, cart);
                        break;
                    default:
                        break;
                }
            }
            response.sendRedirect(request.getParameter("page"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error", e);
        }
    }

    private void addToCart(HttpServletRequest request, CartBean cart) throws SQLException {
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String seatNumber = request.getParameter("seatNumber");
        float price = Float.parseFloat(request.getParameter("price"));

        CartItemBean item = new CartItemBean();
        item.setCartId(cart.getId());
        item.setEventId(eventId);
        item.setQuantity(quantity);
        item.setSeatNumber(seatNumber);
        item.setPrice(price);

        cartItemDao.addCartItem(item);
    }

    private void removeFromCart(HttpServletRequest request, CartBean cart) throws SQLException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        cartItemDao.deleteCartItem(itemId);
    }

    private void updateCartItem(HttpServletRequest request, CartBean cart) throws SQLException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartItemBean item = cartItemDao.getCartItemById(itemId);
        if (item != null) {
            item.setQuantity(quantity);
            cartItemDao.updateCartItem(item);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
