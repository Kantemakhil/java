package net.syscon.s4.pkgs.tag_qm_at;

import net.syscon.s4.triggers.VQmAt;

public interface TagQmAtService {
	Integer prIns(VQmAt lrOldRec, VQmAt lrNewRec);

	Integer prUpd(VQmAt lrOldRec, VQmAt lrNewRec);

	Integer prDel(VQmAt lrOldRec, VQmAt lrNewRec);

}
