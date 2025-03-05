package net.syscon.s4.pkgs.tag_off_ap_v1;

import net.syscon.s4.common.beans.OffenderActionPlans;

public interface TagOffApV1Service {
	Integer prInsProcedure(OffenderActionPlans lrOldRec, OffenderActionPlans lrNewRec);

	Integer prDelProcedure(OffenderActionPlans lrOldRec, OffenderActionPlans lrNewRec);

	Integer prUpdProcedure(OffenderActionPlans lrOldRec, OffenderActionPlans lrNewRec);
}
