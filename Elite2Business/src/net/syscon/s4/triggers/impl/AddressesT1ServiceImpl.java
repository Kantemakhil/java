package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.AddressesT1Repository;
import net.syscon.s4.triggers.AddressesT1Service;
/* =========================================================
 Below comments are copied from ADDRESSES_T1 Trigger
========================================================= */
/* MODIFICATION HISTORY
Person     	 Date      Version     	 Comments
David Ng     06/21/2005  1.0          NOMIS new addresses table
*/
@Service
public class AddressesT1ServiceImpl implements AddressesT1Service {
	@Autowired
	AddressesT1Repository addresesT1Repository;
	@Override
	public Integer addresesT1Trigger(final String ownerClass, final Long ownerId, final BigDecimal ownerSeq,
			final String ownerCode) throws CustomException {
		if ("OFF".equals(ownerClass)) {
			final Integer vNumrowsOff = addresesT1Repository.offendersRecord(ownerId);
			if (vNumrowsOff == 0) {
				throw new CustomException("Cannot INSERT offender address record due to missing offender record.");
			}
		} else if ("PER".equals(ownerClass)) {
			final Integer vNumrowsOff = addresesT1Repository.personRecord(ownerId);
			if (vNumrowsOff == 0) {
				throw new CustomException("Cannot INSERT person address record due to missing person record.");
			}
		} else if ("CORP".equals(ownerClass)) {
			final Integer vNumrowsPer = addresesT1Repository.corporateRecord(ownerId);
			if (vNumrowsPer == 0) {
				throw new CustomException("Cannot INSERT corporate address record due to missing corporate record.");
			}
		} else if ("STF".equals(ownerClass)) {
			final Integer vNumrowsStf = addresesT1Repository.staffRecord(ownerId);
			if (vNumrowsStf == 0) {
				throw new CustomException("Cannot INSERT staff address record due to missing staff record.");
			}
		} else if ("AGY".equals(ownerClass)) {
			final Integer vNumrowsAgy = addresesT1Repository.locationRecord(ownerCode);
			if (vNumrowsAgy == 0) {
				throw new CustomException("Cannot INSERT agency address record due to missing location record.");
			}
		} else if ("OFF_EDU".equals(ownerClass)) {
			final Integer vNumrowsOffEdu = addresesT1Repository.offenderEducationRecord(ownerId, ownerSeq);
			if (vNumrowsOffEdu == 0) {
				throw new CustomException(
						"Cannot INSERT offender education address due to missing offender education record");
			}
		} else if ("OFF_EMP".equals(ownerClass)) {
			final Integer vNumrowsOffEmp = addresesT1Repository.offenderEmploymentRecord(ownerId, ownerSeq);
			if (vNumrowsOffEmp == 0) {
				throw new CustomException(
						"Cannot INSERT offender education address due to missing offender employment record");
			}
		} else if ("PER_EMP".equals(ownerClass)) {
			final Integer vNumrowsPerEmp = addresesT1Repository.personEmploymentRecord(ownerId, ownerSeq);
			if (vNumrowsPerEmp == 0) {
				throw new CustomException(
						"Cannot INSERT offender education address due to missing person employment record.");
			}
		}
		return null;
	}

}
