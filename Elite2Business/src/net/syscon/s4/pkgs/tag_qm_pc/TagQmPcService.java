package net.syscon.s4.pkgs.tag_qm_pc;

import net.syscon.s4.triggers.VQmPc;

public interface TagQmPcService {

	Integer prIns(VQmPc lrOldRec, VQmPc lrNewRec);

	Integer prUpd(VQmPc lrOldRec, VQmPc lrNewRec);

	Integer prDel(VQmPc lrOldRec, VQmPc lrNewRec);

}
