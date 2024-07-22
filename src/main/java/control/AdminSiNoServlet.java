package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserDao;

/**
 * Servlet implementation class AdminSiNoServlet
 */
@WebServlet("/AdminSiNoServlet")
public class AdminSiNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSiNoServlet() {
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
		int userId = Integer.parseInt(request.getParameter("userId"));
        String action = request.getParameter("action");
        UserDao userDao = new UserDao();

        try {
            UserBean user = userDao.getUserById(userId);
            if (user != null) {
                if ("addAdmin".equals(action)) {
                    user.setAdmin(true);
                } else if ("removeAdmin".equals(action)) {
                    user.setAdmin(false);
                }
                userDao.updateUser(user);
            }
            System.out.println("User ID: " + userId);
            System.out.println("Action: " + action);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("admin/users.jsp");
	}

}
