package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.triggers.AddressesTwfRepository;
import net.syscon.s4.triggers.AddressesTwfService;
/*===================================================================
Below comments are copied from ADDRESSES_TWF Trigger
====================================================================*/
/* MODIFICATION HISTORY
   Person      Date           Version       Comments
   ----------  ----------     -------       --------------------------
   Nasir       10/08/2011     1.5 ALBERTA   Fix HPQC# 9438.
   Nasir       08/08/2011     1.4 ALBERTA   Fix HPQC# 9438.
   Nasir       03/08/2011     1.3 ALBERTA   Add plas_status clause to cmg_cur.
   Nasir       02/08/2011     1.2 ALBERTA   Add plas_status clause to cmg_cur.
   Nasir       28/07/2011     1.1 ALBERTA   Move from south Dakota.
   Vikas Gr    22/07/2011     1.3           Defect# 7556 - Fixed
   Vikas Gr    12/07/2011     1.2           Defect# 8929 - Fixed
   Vikas Gr    05/07/2011     1.1           Added OFF_BKG_ID parameter
   Rana        24/11/2010     1.0           Initial Version.
   -------------------------------------------------------------------
*/
@Service
public class AddressesTwfServiceImpl implements AddressesTwfService{
@Autowired
private AddressesTwfRepository addressesTwfRepository;
private static Logger logger = LogManager.getLogger(AddressesTwfServiceImpl.class.getName());


	@Override
	public void addressesTwf(final Addresses newAddresses) {
		Addresses oldAddresses = addressesTwfRepository.getAddressesObject(newAddresses.getAddressId());
		if (oldAddresses != null) {
		if(newAddresses!=null && oldAddresses!=null && 
				Objects.isNull(newAddresses.getSealFlag()) || StringUtils.equals(oldAddresses.getSealFlag(), newAddresses.getSealFlag())) {
			try
			{
			BigDecimal lvOffenderBookId = addressesTwfRepository.getOffenderBookId(newAddresses.getOwnerId());
			
			if(lvOffenderBookId!=null) {
				BigDecimal lvAddId = addressesTwfRepository.getAddressId(lvOffenderBookId);
				if(Objects.nonNull(lvAddId) && lvAddId.equals(newAddresses.getAddressId())) {
				 addressesTwfRepository.getDistinct1(lvOffenderBookId, newAddresses.getModifyUserId(), newAddresses.getCreateUserId());				
				}
			}
			} 
			catch(Exception e) {
				logger.error(this.getClass().getName() + " error in cgfkOffEmDspDescriptionRecordGroup " + e);
			}
		}
		
	}
	}
}
