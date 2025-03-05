package net.syscon.s4.pkgs.tag_off_ap_v1;

import net.syscon.s4.common.beans.OffenderActionPlans;

public interface TagOffApV1Repository {

	Integer prIns(OffenderActionPlans targetObj);

	Integer prDel(OffenderActionPlans lrOldRec);

	Integer prUpd(OffenderActionPlans targetObj);

}
