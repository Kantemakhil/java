package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.triggers.EventMeasuresTjnRepository;
import net.syscon.s4.triggers.EventMeasuresTjnService;

@Service
public class EventMeasuresTjnServiceImpl extends BaseBusiness implements EventMeasuresTjnService {

	private static Logger logger = LogManager.getLogger(EventMeasuresTjnServiceImpl.class.getName());

	@Autowired
	private EventMeasuresTjnRepository eventMeasuresTjnRepository;

	private static final String INSERTING = "INSERTING";
	private static final String UPDATING = "UPDATING";

	@Override
	public Integer eventMeasuresTjnTrigger(EventMeasures newObj, EventMeasures oldObj, String operation) {
		Integer retVal = 0;
		try {
			if (INSERTING.equals(operation)) {
				if (newObj != null) {
					retVal = eventMeasuresTjnRepository.eventMeasuresTjnInsert(newObj);
					logger.info(this.getClass().getName() + " EventMeasuresTjn trigger related insert opeartion");
				}
			} else if (UPDATING.equals(operation)) {
				if (oldObj != null) {
					retVal = eventMeasuresTjnRepository.eventMeasuresTjnUpdate(oldObj);
					logger.info(this.getClass().getName() + " EventMeasuresTjn trigger related update opeartion");
				}
			} else {
				if (oldObj != null) {
					retVal = eventMeasuresTjnRepository.eventMeasuresTjnDelete(oldObj);
					logger.info(this.getClass().getName() + " EventMeasuresTjn trigger related delete opeartion");
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in method eventMeasuresTjnTrigger ", e);
		}
		return retVal;
	}

}
