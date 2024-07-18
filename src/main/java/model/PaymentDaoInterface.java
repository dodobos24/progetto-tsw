package model;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDaoInterface {
	void addPayment(PaymentBean payment) throws SQLException;
	PaymentBean getPaymentById(int id) throws SQLException;
    List<PaymentBean> getAllPayments() throws SQLException;
    void updatePayment(PaymentBean payment) throws SQLException;
    void deletePayment(int id) throws SQLException;
    List<PaymentBean> getPaymentByUser(int userId) throws SQLException;
    List<PaymentBean> getPaymentByTicket(int ticketId) throws SQLException;
}
