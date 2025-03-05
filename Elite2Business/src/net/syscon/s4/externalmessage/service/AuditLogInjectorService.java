package net.syscon.s4.externalmessage.service;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.externalmessage.ExternalMessageRepository;
import net.syscon.s4.globalconfiguration.OumsysetService;

@Service
public class AuditLogInjectorService {


	private static Logger logger = LogManager.getLogger(AuditLogInjectorService.class);

	@Autowired
	OumsysetService oumsysetService;
	
	@Autowired
	private ExternalMessageRepository externalMessageRepository;

	/**
	 * This method publishes a message
	 */
	public void produceMessage(String message) {
		logger.info("Produce Message has been called : {}", message );
		String userName = "";
		String password = "";
		String url = "";
		String exchangeName = "";
		String queueName = "";
		String routingKey = "";
		URI auditLogServerUrl = null;
		ConnectionFactory factory = new ConnectionFactory();
		try {
			List<Map<String, Object>> returnList = oumsysetService.getSysData("VIEW_AUDIT_LOG", "VIEW_AUDIT_LOG");
			if (returnList != null && !returnList.isEmpty()) {
				for (Map<String, Object> object : returnList) {
					if (object.containsKey(ApplicationConstants.KEY_CODE) && object.get(ApplicationConstants.KEY_CODE) != null && object.containsKey(ApplicationConstants.VAL)
							&& object.get(ApplicationConstants.VAL) != null) {
						if (object.get(ApplicationConstants.KEY_CODE).equals("USERNAME")) {
							userName = object.get(ApplicationConstants.VAL).toString();
						}
						if (object.get(ApplicationConstants.KEY_CODE).equals("PASSWORD")) {
							password = object.get(ApplicationConstants.VAL).toString();
						}
						if (object.get(ApplicationConstants.KEY_CODE).equals("VIEW_AUDIT_SERVER_URL")) {
							url = object.get(ApplicationConstants.VAL).toString();
						}
						if (object.get(ApplicationConstants.KEY_CODE).equals("VIEW_AUDIT_EXCHANGE")) {
							exchangeName = object.get(ApplicationConstants.VAL).toString();
						}
						if (object.get(ApplicationConstants.KEY_CODE).equals("VIEW_AUDIT_QUEUE")) {
							queueName = object.get(ApplicationConstants.VAL).toString();
						}
						if (object.get(ApplicationConstants.KEY_CODE).equals("VIEW_AUDIT_ROUTING_KEY")) {
							routingKey = object.get(ApplicationConstants.VAL).toString();
						}
					}
				}
			}
			auditLogServerUrl = new URI(url);
			if(auditLogServerUrl == null || userName.equals("") || password.equals("") || exchangeName.equals("") || queueName.equals("") || routingKey.equals("")
					|| message == null || message.isEmpty()) {
				return;
			}
			factory = new ConnectionFactory();
			factory.setHost(auditLogServerUrl.getHost());
			factory.setPort(auditLogServerUrl.getPort());
			factory.setUsername(userName);
			factory.setPassword(password);
			Connection connection = factory.newConnection();
			logger.info("Connection has been created" );
			Channel channel = connection.createChannel();
			logger.info("Channel has been created" );
			try {
				channel.queueDeclare(queueName, true, false, false, null);
				channel.exchangeDeclare(exchangeName, "direct", true);
				channel.queueBind(queueName, exchangeName, routingKey);
			} catch (Exception e) {
				logger.error("Exception in produceMessage Declarations {}" , e.getMessage());
			}
			logger.info("Exchange, Queue, Bindinging has been done" );
			BasicProperties messageProperties = new BasicProperties.Builder()
					.deliveryMode(2).build();
			channel.basicPublish(exchangeName, routingKey, messageProperties, message.getBytes());
			channel.close();
			connection.close();
			logger.info("Channel and connection closed" );
		} catch (Exception ex) {
			String connectionKey = url + "://" + exchangeName + "://" + routingKey; 
			externalMessageRepository.saveServiceBusQueue(message, queueName, connectionKey, ex.getMessage());
		} 
	}
}
