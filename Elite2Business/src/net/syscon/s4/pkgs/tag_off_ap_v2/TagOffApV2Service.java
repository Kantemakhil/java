package net.syscon.s4.pkgs.tag_off_ap_v2;

import net.syscon.s4.common.beans.OffenderActionPlans;

public interface TagOffApV2Service {

	Integer prInsProcedure(OffenderActionPlans lrOldRec, OffenderActionPlans lrNewRec);

	Integer prDelProcedure(OffenderActionPlans lrOldRec, OffenderActionPlans lrNewRec);

	Integer prUpdProcedure(OffenderActionPlans lrOldRec, OffenderActionPlans lrNewRec);

	String prLck(String ptPkColumnValues);

	String prTableLock(String pvTableName, String ptPkColumnNames, String ptPkColumnValues);

}
