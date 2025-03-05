package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;
import net.syscon.s4.triggers.OffenderMonBeneficiariesTjnRepository;
import net.syscon.s4.triggers.OffenderMonBeneficiariesTjnService;

@Service
public class OffenderMonBeneficiariesTjnServiceImpl implements OffenderMonBeneficiariesTjnService {
	
	private static final Logger logger = LogManager.getLogger(OffenderMonBeneficiariesTjnServiceImpl.class);
	
	@Autowired
	OffenderMonBeneficiariesTjnRepository offenderMonBeneficiariesTjnRepository;
	
	@Override
	public Integer offenderMonBeneficiariesTjn(OffenderMonDeductions newObj,
			OffenderMonDeductions oldObj, String operation) {
		Integer val = 0;
		try {
			if(operation.equalsIgnoreCase("INSERT")) {
				val = offenderMonBeneficiariesTjnRepository.insertOffenderMonBeneficiariesTjn(newObj);
				logger.info("insertOffenderMonBeneficiariesTjn reponse" + val);
			}else if (operation.equalsIgnoreCase("UPDATE")) {
				val = offenderMonBeneficiariesTjnRepository.updateOffenderMonBeneficiariesTjn(oldObj);
				logger.info("updateOffenderMonBeneficiariesTjn reponse" + val);
			} else if (operation.equalsIgnoreCase("DELETE")) {
				val = offenderMonBeneficiariesTjnRepository.deleteOffenderMonBeneficiariesTjn(oldObj);
				logger.info("deleteOffenderMonBeneficiariesTjn reponse" + val);
			}
			
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " offenderMonBeneficiariesTjn error :: " , e);
			return val = 0;
		}
		
		return val;
	}

}
