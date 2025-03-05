package net.syscon.s4.pkgs.oidcount;

import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;

public interface OidcountPkgService {

	Integer checkRemoveDeadJobs(final String sessionId,String modifyUserId);

	Integer cancelCount(final Long pSessionId, final String userName);
                  
	void setTempOidcount(final AgencyCountTypes paramBean);

	Integer submitCountJob(final AgencyCountTypes paramBean);

}
