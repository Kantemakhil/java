package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.AddressesT2Repository;
import net.syscon.s4.triggers.AddressesT2Service;
/* =========================================================
Below comments are copied from ADDRESSES_T2 Trigger
========================================================= */
/* MODIFICATION HISTORY
Person     	 Date      Version     	 Comments
---------    ------     ---------  	 ------------------------------
David Ng     06/21/2005  1.0          NOMIS new addresses table
*/
@Service
public class AddressesT2ServiceImpl implements AddressesT2Service {
	Logger logger = LogManager.getLogger(AddressesT2ServiceImpl.class.getName());

	public AddressesT2ServiceImpl() {

	}

	/*
	 * TODO create or replace TRIGGER "OMS_OWNER"."ADDRESSES_T2" AFTER DELETE ON
	 * addresses REFERENCING NEW AS NEW OLD AS OLD FOR EACH ROW DECLARE V_numrows
	 * INTEGER; BEGIN
	 */
	@Autowired
	AddressesT2Repository addresesT2Repository;

	// Below method is call after Deleting on Addresses table
	@Override
	public Integer addresesT2Trigger(final Long addressId) throws CustomException {
		//return the count of Phones records based on addressId
		final Integer vNumrowsPhone = addresesT2Repository.phoneRecordsExists(addressId);
		if (vNumrowsPhone > 0) {
			throw new CustomException("Cannot DELETE the address record because address phone records exists.");
		}
		//return the count of internet_addresses records based on addressId
		final Integer vNumrowsInternet = addresesT2Repository.internetAddrRecordsExists(addressId);
		if (vNumrowsInternet > 0) {
			throw new CustomException(
					"Cannot DELETE the address record because address internet addresses records exists.");
		}
		return null;
	}

	/*
	 * TODO EXCEPTION WHEN OTHERS THEN DBMS_OUTPUT.put_line ( SQLERRM );
	 * 
	 * END;
	 */

}
