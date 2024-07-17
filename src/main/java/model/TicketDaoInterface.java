package model;

import java.sql.SQLException;
import java.util.List;

public interface TicketDaoInterface {
	void addTicket(TicketBean ticket) throws SQLException;
	TicketBean getTicketById(int id) throws SQLException;
    List<TicketBean> getAllTickets() throws SQLException;
    void updateTicket(TicketBean ticket) throws SQLException;
    void deleteTicket(int id) throws SQLException;
    List<TicketBean> getTicketByUser(int userId) throws SQLException;
    List<TicketBean> getTicketByEvent(int eventId) throws SQLException;
}
