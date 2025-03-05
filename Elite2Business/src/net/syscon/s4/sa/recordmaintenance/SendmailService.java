package net.syscon.s4.sa.recordmaintenance;

public interface SendmailService {
	
	String sendMail(SendMail detModel);
	
	String sendSMS(SendMail detMode);
}
