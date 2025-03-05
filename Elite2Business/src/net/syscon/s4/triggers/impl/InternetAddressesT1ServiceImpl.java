package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.InternetAddressesT1Repository;
import net.syscon.s4.triggers.InternetAddressesT1Service;
/* =========================================================
Below comments are copied from INTERNET_ADDRESSES_T1 Trigger
========================================================= */
/* MODIFICATION HISTORY
Person     	 Date      Version     	 Comments
R.Grigoras   20-Feb-2009 10.1.02       new owner class for email workflow
David Ng     16/06/2005  10.1.01       NOMIS new addresses table
*/
@Service
public class InternetAddressesT1ServiceImpl implements InternetAddressesT1Service {

	@Autowired
	private InternetAddressesT1Repository internetAddressesT1Repository;
	@Override
	    public Integer internetAddressesT1Trigger(String ownerClass, Long ownerId, Long ownerSeq, String ownerCode)
			throws CustomException {
		if ("OFF".equals(ownerClass)) {
			final Integer vNumrowsOff = internetAddressesT1Repository.offendersRecord(ownerId);
			if (vNumrowsOff == 0) {
				throw new CustomException("Cannot INSERT offender address record due to missing offender record.");
			}
		} else if ("ADDR".equals(ownerClass)) {
			final Integer vNumrowsOff = internetAddressesT1Repository.addressRecord(ownerId);
			if (vNumrowsOff == 0) {
				throw new CustomException("Cannot INSERT phone record due to missing address records.");
			}
		} else if ("PER".equals(ownerClass)) {
			final Integer vNumrowsOff = internetAddressesT1Repository.personRecord(ownerId);
			if (vNumrowsOff == 0) {
				throw new CustomException("Cannot INSERT person address record due to missing person records.");
			}
		} else if ("CORP".equals(ownerClass)) {
			final Integer vNumrowsPer = internetAddressesT1Repository.corporateRecord(ownerId);
			if (vNumrowsPer == 0) {
				throw new CustomException("Cannot INSERT corporate address record due to missing corporate record.");
			}
		} else if ("STF".equals(ownerClass)) {
			final Integer vNumrowsStf = internetAddressesT1Repository.staffRecord(ownerId);
			if (vNumrowsStf == 0) {
				throw new CustomException("Cannot INSERT staff address record due to missing staff record.");
			}
		} else if ("AGY".equals(ownerClass)) {
			final Integer vNumrowsAgy = internetAddressesT1Repository.locationRecord(ownerCode);
			if (vNumrowsAgy == 0) {
				throw new CustomException("Cannot INSERT agency address record due to missing location record.");
			}
		} else if ("OFF_EDU".equals(ownerClass)) {
			final Integer vNumrowsOffEdu = internetAddressesT1Repository.offenderEducationRecord(ownerId, ownerSeq); 
			if (vNumrowsOffEdu == 0) {
				throw new CustomException("Cannot INSERT offender education address due to missing offender education record.");
			}
		} else if ("OFF_EMP".equals(ownerClass)) {
			final Integer vNumrowsOffEmp = internetAddressesT1Repository.offenderEmploymentRecord(ownerId, ownerSeq);
			if (vNumrowsOffEmp == 0) {
				throw new CustomException("Cannot INSERT offender education address due to missing offender employment record.");
			}
		} else if ("PER_EMP".equals(ownerClass)) {
			final Integer vNumrowsPerEmp = internetAddressesT1Repository.personEmploymentRecord(ownerId, ownerSeq);
			if (vNumrowsPerEmp == 0) {
				throw new CustomException("Cannot INSERT offender education address due to missing person employment record.");
			}
		} else if ("WORKFLOW".equals(ownerClass)) {
			final Integer vNumrowsPerEmp = internetAddressesT1Repository.workflowRecord(ownerId);
			if (vNumrowsPerEmp == 0) {
				throw new CustomException("Cannot INSERT work item address due to missing work item record.");
			}
		}
		return null;
	}

}
