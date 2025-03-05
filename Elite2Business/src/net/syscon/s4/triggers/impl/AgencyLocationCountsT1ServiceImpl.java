package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.triggers.AgencyLocationCountsT1Repository;
import net.syscon.s4.triggers.AgencyLocationCountsT1Service;

@Service
public class AgencyLocationCountsT1ServiceImpl implements AgencyLocationCountsT1Service {
	@Autowired
	AgencyLocationCountsT1Repository agencyLocationCountsT1Repository;

	@Override
	public Integer agencyLocationCountsT1Trigger(final TempOidcount tempOidcoun, final String rcntInProgressFlag) {
		final TempOidcount lTempOidcount = new TempOidcount();
		Integer returnVal = 0;
		Integer recountTotal = null;
		if (rcntInProgressFlag == null || "N".equals(rcntInProgressFlag)) {
			lTempOidcount.setAgySeq(tempOidcoun.getAgySeq());
			lTempOidcount.setCountTypeId(tempOidcoun.getCountTypeId());
			lTempOidcount.setReportingLocId(tempOidcoun.getReportingLocId());
			lTempOidcount.setDateSubmitted(tempOidcoun.getDateSubmitted());
			lTempOidcount.setEnteredByUserid(tempOidcoun.getEnteredByUserid());
			lTempOidcount.setActualCount(tempOidcoun.getActualCount());
			lTempOidcount.setDateSubmitted(tempOidcoun.getDateSubmitted());
			lTempOidcount.setEnteredByUserid(tempOidcoun.getEnteredByUserid());
			lTempOidcount.setModifyUserId(tempOidcoun.getModifyUserId());
			recountTotal = agencyLocationCountsT1Repository.getRecountTotal(tempOidcoun);
			if (recountTotal != null) {
				lTempOidcount.setReportedCount(recountTotal);
			} else {
				lTempOidcount.setReportedCount(tempOidcoun.getReportedCount());
			}
			returnVal = agencyLocationCountsT1Repository.update(lTempOidcount);
		}
		return returnVal;
	}

}
