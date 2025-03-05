package net.syscon.s4.pkgs.tag_qm_pai;

import net.syscon.s4.triggers.VQmPai;

public interface TagQmPaiService {

	Integer prIns(VQmPai lrOldRec, VQmPai lrNewRec);

	Integer prUpd(VQmPai lrOldRec, VQmPai lrNewRec);

	Integer prDel(VQmPai lrOldRec, VQmPai lrNewRec);

}
