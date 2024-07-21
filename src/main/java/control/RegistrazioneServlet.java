package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CartBean;
import model.CartDao;
import model.UserBean;
import model.UserDao;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrazioneServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        CartDao cartDao = new CartDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        try {
            UserBean user = new UserBean();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setNome(name);
            user.setCognome(surname);
            user.setAdmin(false);

            userDao.addUser(user);

            int userId = userDao.getUserByUsername(username).getId();
            System.out.println("User ID: " + userId);  // Debug

            CartBean cart = new CartBean();
            cart.setUserId(userId);
            cart.setCreatedAt(java.time.LocalDateTime.now());

            cartDao.addCart(cart);
            System.out.println("Carrello creato per l'utente ID: " + userId);  // Debug

            request.setAttribute("successMessage", "Registrazione avvenuta con successo! Effettua il login.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Errore durante la registrazione: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
