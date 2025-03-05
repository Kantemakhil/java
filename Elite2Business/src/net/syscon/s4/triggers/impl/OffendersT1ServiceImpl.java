package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;


import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffendersT1Repository;
import net.syscon.s4.triggers.OffendersT1Service;

/* =========================================================
   Below comments are copied from OFFENDERS_T1 Trigger
========================================================= */
/* MODIFICATION HISTORY
Person     	 Date      Version     	 Comments
---------    ------     ---------  	 ------------------------------
David Ng     06/21/2005  1.1          NOMIS new addresses table
*/
@Service
public class OffendersT1ServiceImpl implements OffendersT1Service {
	
	private static Logger logger = LogManager.getLogger(OffendersT1ServiceImpl.class.getName());
	@Autowired
	private OffendersT1Repository offendersT1Repository;

	@Override
	public void offendersT1Trigger(Long offenderId) {
	
		Integer liReturn;
          // Getting count from Addresses Table 
		  liReturn=offendersT1Repository.gettingAddressCount(offenderId);
		if(liReturn > 0) {
			logger.error("Cannot DELETE the offENDer record because offENDer address records exists");		
		}
		// Getting Count from Phones Table
		liReturn=offendersT1Repository.gettingPhoneCount(offenderId);
		if(liReturn > 0) {
			logger.error("Cannot DELETE the offENDer record because offENDer phone records exists.");		
		}
		// Getting Count from internet_addresses Table
		liReturn=offendersT1Repository.gettingInternetAddressCount(offenderId);
		if (liReturn>0) {
			logger.error("Cannot DELETE the offENDer record because offENDer internet addresses records exists");
		}
	}

}
