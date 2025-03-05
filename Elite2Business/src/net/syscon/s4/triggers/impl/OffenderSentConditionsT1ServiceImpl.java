package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderSentConditions;
import net.syscon.s4.triggers.OffenderSentConditionsT1Repository;
import net.syscon.s4.triggers.OffenderSentConditionsT1Service;

@Service
public class OffenderSentConditionsT1ServiceImpl implements OffenderSentConditionsT1Service {
	Logger logger = LogManager.getLogger(OffenderSentConditionsT1ServiceImpl.class.getName());
	@Autowired
	OffenderSentConditionsT1Repository offenderSentConditionsT1Repository;

	@Override
	public Integer offenderSentConditionsT1Trigger(final List<OffenderSentConditions> offenderSentConditionsList) {
		Integer result = 0;
		final OffenderSentConditions targetObj = new OffenderSentConditions();
		if (!offenderSentConditionsList.isEmpty()) {
			try {
				for (final OffenderSentConditions newObj : offenderSentConditionsList) {
					final OffenderSentConditions old = offenderSentConditionsT1Repository.getOffenderSentConditions(newObj);
					if (newObj.getSealFlag() == null
							|| (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
						if (!newObj.getStatusUpdateReason().equals(old.getStatusUpdateReason())) {
//						targetObj = new OffenderSentConditions();
							BeanUtils.copyProperties(newObj, targetObj);
							targetObj.setOffenderSentConditionId(old.getOffenderSentConditionId());
							targetObj.setStatusUpdateReason(old.getStatusUpdateReason());
							targetObj.setStatusUpdateComment(old.getStatusUpdateComment());
							targetObj.setStatusUpdateDate(old.getStatusUpdateDate());
							targetObj.setStatusUpdateStaffId(old.getStatusUpdateStaffId());
							result = offenderSentConditionsT1Repository.insert(targetObj);
						}
					}
				}
			} catch (final Exception e) {
				logger.error("offenderSentConditionsT1Trigger", e);
			}
		}
		return result;
	}
}
