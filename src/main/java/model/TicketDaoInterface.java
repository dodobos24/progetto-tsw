package model;

import java.util.List;

public interface TicketDaoInterface {
	void addTicket(TicketBean ticket);
	TicketBean getTicketById(int id);
    List<TicketBean> getAllTickets();
    void updateTicket(TicketBean ticket);
    void deleteTicket(int id);
    List<TicketBean> getTicketByUser(int userId);
    List<TicketBean> getTicketByEvent(int eventId);
}
