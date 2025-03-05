package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderPaymentSchedules;
import net.syscon.s4.triggers.OffenderPaymentSchedulesTjnRepository;
import net.syscon.s4.triggers.OffenderPaymentSchedulesTjnService;

@Service
public class OffenderPaymentSchedulesTjnServiceImpl implements OffenderPaymentSchedulesTjnService {

	private static final Logger logger = LogManager.getLogger(OffenderPaymentSchedulesTjnServiceImpl.class);

	@Autowired
	OffenderPaymentSchedulesTjnRepository offenderPaymentSchedulesTjnRepository;

	@Override
	public Integer OffenderPaymentSchedulesTjn(OffenderPaymentSchedules newObj, OffenderPaymentSchedules oldObj,
			String operation) {
		Integer val = 0;
		try {
			if (operation.equalsIgnoreCase("INSERT")) {
				val = offenderPaymentSchedulesTjnRepository.insertOffenderPaymentSchedulesTjn(newObj);
				logger.info("insertOffenderBeneficiariesTjn reponse" + val);
			} else if (operation.equalsIgnoreCase("UPDATE")) {
				val = offenderPaymentSchedulesTjnRepository.updateOffenderPaymentSchedulesTjn(oldObj);
				logger.info("updateOffenderBeneficiariesTjn reponse" + val);
			} else if (operation.equalsIgnoreCase("DELETE")) {
				val = offenderPaymentSchedulesTjnRepository.deleteOffenderPaymentSchedulesTjn(oldObj);
				logger.info("deleteOffenderBeneficiariesTjn reponse" + val);
			}

		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " OffenderPaymentSchedulesTjn error :: ", e);
			return val = 0;
		}

		return val;

	}

}
