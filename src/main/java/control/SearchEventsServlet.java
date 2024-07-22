package control;

import model.EventBean;
import model.EventDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/SearchEventsServlet")
public class SearchEventsServlet extends HttpServlet {

    private EventDao eventDao = new EventDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dove = request.getParameter("dove");
        String quando = request.getParameter("quando");
        String categoria = request.getParameter("categoria");
        String artista = request.getParameter("artista");

        System.out.println("Parameters - Dove: " + dove + ", Quando: " + quando + ", Categoria: " + categoria + ", Artista: " + artista);

        LocalDateTime quandoDate = (quando != null && !quando.isEmpty()) ? LocalDateTime.parse(quando) : null;

        List<EventBean> events;
        try {
            events = eventDao.searchEvents(dove, quandoDate, categoria, artista);
            String json = new Gson().toJson(events);
            System.out.println("Search Results: " + json);
            
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nella ricerca degli eventi.");
        }
    }
}
