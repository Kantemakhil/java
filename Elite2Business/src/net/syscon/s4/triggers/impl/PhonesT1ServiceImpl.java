package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.PhonesT1Repository;
import net.syscon.s4.triggers.PhonesT1Service;
/* =========================================================
 Below comments are copied from PHONES_T1 Trigger
========================================================= */
/* MODIFICATION HISTORY
Person     	 Date      Version     	 Comments
David Ng     21/06/2005  1.0       NOMIS new addresses table
*/
@Service
public class PhonesT1ServiceImpl implements PhonesT1Service {
	
	@Autowired
	PhonesT1Repository phonesT1Repository;

	@Override
	public Integer phonesT1Trigger(String ownerClass, Long ownerId, Long ownerSeq, String ownerCode)
			throws CustomException {
		if ("OFF".equals(ownerClass)) {
			final Integer vNumrowsOff = phonesT1Repository.offendersRecord(ownerId);
			if (vNumrowsOff == 0) {
				throw new CustomException("Cannot INSERT phone record due to missing offender record.");
			}
		} else if ("ADDR".equals(ownerClass)) {
			final Integer vNumrowsOff = phonesT1Repository.addressRecord(ownerId);
			if (vNumrowsOff == 0) {
				throw new CustomException("Cannot INSERT phone record due to missing address record.");
			}
		}
		else if ("PER".equals(ownerClass)) {
			final Integer vNumrowsOff = phonesT1Repository.personRecord(ownerId);
			if (vNumrowsOff == 0) {
				throw new CustomException("Cannot INSERT phone record due to missing person record.");
			}
		} else if ("CORP".equals(ownerClass)) {
			final Integer vNumrowsPer = phonesT1Repository.corporateRecord(ownerId);
			if (vNumrowsPer == 0) {
				throw new CustomException("Cannot INSERT phone record due to missing corporate record.");
			}
		} else if ("STF".equals(ownerClass)) {
			final Integer vNumrowsStf = phonesT1Repository.staffRecord(ownerId);
			if (vNumrowsStf == 0) {
				throw new CustomException("Cannot INSERT phone record due to missing staff record.");
			}
		} else if ("AGY".equals(ownerClass)) {
			final Integer vNumrowsAgy = phonesT1Repository.locationRecord(ownerCode);
			if (vNumrowsAgy == 0) {
				throw new CustomException("Cannot INSERT phone record due to missing location record.");
			}
		} else if ("OFF_EDU".equals(ownerClass)) {
			final Integer vNumrowsOffEdu = phonesT1Repository.offenderEducationRecord(ownerId, ownerSeq);
			if (vNumrowsOffEdu == 0) {
				throw new CustomException(
						"Cannot INSERT phone record due to missing offender education record");
			}
		} else if ("OFF_EMP".equals(ownerClass)) {
			final Integer vNumrowsOffEmp = phonesT1Repository.offenderEmploymentRecord(ownerId, ownerSeq);
			if (vNumrowsOffEmp == 0) {
				throw new CustomException(
						"Cannot INSERT phone record due to missing offender employment record");
			}
		} else if ("PER_EMP".equals(ownerClass)) {
			final Integer vNumrowsPerEmp = phonesT1Repository.personEmploymentRecord(ownerId, ownerSeq);
			if (vNumrowsPerEmp > 0) {
				throw new CustomException(
						"Cannot INSERT phone record due to missing person employment record.");
			}
		}
		return null;
	}

}
