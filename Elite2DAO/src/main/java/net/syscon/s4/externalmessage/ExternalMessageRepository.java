package net.syscon.s4.externalmessage;

public interface ExternalMessageRepository {

	void saveServiceBusQueue(String msg, String queueName, String serviceBusKey,String errorMsg);
}
