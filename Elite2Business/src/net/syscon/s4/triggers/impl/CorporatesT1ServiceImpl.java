package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.triggers.CorporatesT1Repository;
import net.syscon.s4.triggers.CorporatesT1Service;

@Service
public class CorporatesT1ServiceImpl implements CorporatesT1Service {
	
	private static Logger logger = LogManager.getLogger(CorporatesT1ServiceImpl.class.getName());
   @Autowired	
   private CorporatesT1Repository corporatesT1Repository;

	@Override
	public Integer corporateT1Trigger(BigDecimal corporateId) {
		Integer liReturn;

		 liReturn=corporatesT1Repository.gettingAddressCount(corporateId);
		if(liReturn > 0) {
			logger.error("Cannot DELETE the corporate record because corporate address records exists");		
		}
		liReturn=corporatesT1Repository.gettingPhoneCount(corporateId);
		if(liReturn > 0) {
			logger.error("Cannot DELETE the corporate record because corporate phone records exists");		
		}
		liReturn=corporatesT1Repository.gettingInternetAddressCount(corporateId);
		if (liReturn>0) {
			logger.error("Cannot DELETE the corporate record because corporate internet addresses records exists");
		}
	

		
		return null;
	}

}
