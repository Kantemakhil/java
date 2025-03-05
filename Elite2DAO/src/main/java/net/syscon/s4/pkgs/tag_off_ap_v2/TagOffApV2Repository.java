package net.syscon.s4.pkgs.tag_off_ap_v2;

import net.syscon.s4.common.beans.OffenderActionPlans;

public interface TagOffApV2Repository {

	Integer prIns(OffenderActionPlans targetObj);

	Integer prDel(OffenderActionPlans lrOldRec);

	Integer prUpd(OffenderActionPlans targetObj);

}
