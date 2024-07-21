package control;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;

/**
 * Servlet Filter implementation class AutenticationFilter
 */
@WebFilter("/gnegen")
public class AutenticationFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AutenticationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        UserBean user = (session != null) ? (UserBean) session.getAttribute("currentSessionUser") : null;

        if (user == null || !user.isValid()) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        } else {
            chain.doFilter(request, response); // User is authenticated, proceed to the requested resource
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
