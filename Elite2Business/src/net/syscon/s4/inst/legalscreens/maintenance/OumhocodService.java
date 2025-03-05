package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodesCommitBean;

/**
 * Interface OumhocodService
 */
public interface OumhocodService {
	List<HoCodes> hoCodesCommit(HoCodesCommitBean commitBean);

	List<HoCodes> hoCodesExecuteQuery(HoCodes objHoCodes);

	Integer hoCodesCheckDeleteMaster(HoCodes searchBean);

}
