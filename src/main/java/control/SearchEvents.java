package control;

import model.EventBean;
import model.EventDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/searchEvents")
public class SearchEvents extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String venue = request.getParameter("dove");
        String dateStr = request.getParameter("quando");
        String category = request.getParameter("categoria");
        String artist = request.getParameter("artista");

        LocalDate date = (dateStr != null && !dateStr.isEmpty()) ? LocalDate.parse(dateStr) : null;

        EventDao eventDao = new EventDao();
        List<EventBean> events;

        try {
            events = eventDao.searchEvents(venue, date != null ? date.atStartOfDay() : null, category, artist);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(events));
    }
}
