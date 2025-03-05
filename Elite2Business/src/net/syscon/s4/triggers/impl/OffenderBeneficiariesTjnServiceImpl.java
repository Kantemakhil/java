
package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.triggers.OffenderBeneficiariesTjnRepository;
import net.syscon.s4.triggers.OffenderBeneficiariesTjnService;

@Service
public class OffenderBeneficiariesTjnServiceImpl implements OffenderBeneficiariesTjnService {

	private static final Logger logger = LogManager.getLogger(OffenderBeneficiariesTjnServiceImpl.class);

	@Autowired
	private OffenderBeneficiariesTjnRepository offenderBeneficiariesTjnRepository;
	
	@Override
	public Integer offenderBeneficiariesTjn(OffenderBeneficiaries newObj, OffenderBeneficiaries oldObj,
			String operation) {
		Integer val = 0;
		try {
			if (operation.equalsIgnoreCase("INSERT")) {
				val = offenderBeneficiariesTjnRepository.insertOffenderBeneficiariesTjn(newObj);
				logger.info("insertOffenderBeneficiariesTjn reponse" + val);
			} else if (operation.equalsIgnoreCase("UPDATE")) {
				val = offenderBeneficiariesTjnRepository.updateOffenderBeneficiariesTjn(oldObj);
				logger.info("updateOffenderBeneficiariesTjn reponse" + val);
			} else if (operation.equalsIgnoreCase("DELETE")) {
				val = offenderBeneficiariesTjnRepository.deleteOffenderBeneficiariesTjn(oldObj);
				logger.info("deleteOffenderBeneficiariesTjn reponse" + val);
			}

		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " offenderMonBeneficiariesTjn error :: ", e);
			return val = 0;
		}

		return val;

	}

}
