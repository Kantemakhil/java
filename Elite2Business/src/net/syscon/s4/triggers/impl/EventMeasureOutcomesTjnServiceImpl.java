package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.triggers.EventMeasureOutcomesTjnRepository;
import net.syscon.s4.triggers.EventMeasureOutcomesTjnService;

@Service
public class EventMeasureOutcomesTjnServiceImpl extends BaseBusiness implements EventMeasureOutcomesTjnService {

	private static Logger logger = LogManager.getLogger(EventMeasuresTjnServiceImpl.class.getName());

	@Autowired
	private EventMeasureOutcomesTjnRepository eventMeasureOutcomesTjnRepository;

	private static final String INSERTING = "INSERTING";
	private static final String UPDATING = "UPDATING";

	@Override
	public Integer eventMeasureOutcomesTjnTrigger(EventMeasureOutcomes newObj, EventMeasureOutcomes oldObj,
			String operation) {
		Integer retVal = 0;
		try {
			if (INSERTING.equals(operation)) {
				if (newObj != null) {
					retVal = eventMeasureOutcomesTjnRepository.eventMeasuresOutcomesTjnInsert(newObj);
					logger.info(this.getClass() + " EventMeasureOutcomesTjn trigger related insert opeartion");
				}
			} else if (UPDATING.equals(operation)) {
				if (oldObj != null) {
					retVal = eventMeasureOutcomesTjnRepository.eventMeasuresOutcomesTjnUpdate(oldObj);
					logger.info(this.getClass() + " EventMeasureOutcomesTjn trigger related update opeartion");
				}
			} else {
				if (oldObj != null) {
					retVal = eventMeasureOutcomesTjnRepository.eventMeasuresOutcomesTjnDelete(oldObj);
					logger.info(this.getClass() + " EventMeasureOutcomesTjn trigger related delete opeartion");
				}
			}
		} catch (Exception e) {
			logger.error("eventMeasureOutcomesTjnTrigger :", e);
		}
		return retVal;
	}

}
