package model;

import java.util.List;

public interface PaymentDaoInterface {
	void addPayment(PaymentBean payment);
	PaymentBean getPaymentById(int id);
    List<PaymentBean> getAllPayments();
    void updatePayment(PaymentBean payment);
    void deletePayment(int id);
    List<PaymentBean> getPaymentByUser(int userId);
    List<PaymentBean> getPaymentByTicket(int ticketId);
}
