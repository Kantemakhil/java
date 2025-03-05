package net.syscon.s4.externalmessage.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.externalmessage.ExternalMessageRepository;
@Repository
public class ExternalMessageRepositoryImpl extends RepositoryBase implements ExternalMessageRepository{
	private static Logger logger = LogManager.getLogger(ExternalMessageRepositoryImpl.class.getName());
	public ExternalMessageRepositoryImpl() {
		//ExternalMessageRepositoryImpl
	}
	@Override
	public void saveServiceBusQueue(String message, String queueName, String serviceBusKey,String errorMsg) {
		final String sql = getQuery("SAVE_SERVICE_BUS_QUEUE");
		Map<String, Object> inputMap  = new HashMap<>();
		logger.info("In saveServiceBusQueue Method ::");
		logger.info("Saving message:: ",message+"queueName",queueName+"serviceBusKey",serviceBusKey);
		try {
			inputMap.put("message", message.getBytes());	
			inputMap.put("queueName", queueName);
			inputMap.put("serviceBusKey", serviceBusKey);
			inputMap.put("errorMsg", errorMsg.getBytes());
			namedParameterJdbcTemplate.update(sql, inputMap);	
			logger.info("In saveServiceBusQueue Method :: record is inserted");
		} catch (Exception ex) {
			logger.error("Error in saveServiceBusQueue Method :: {}", ex.getMessage());
			ex.printStackTrace();
		}
		
	}

}
