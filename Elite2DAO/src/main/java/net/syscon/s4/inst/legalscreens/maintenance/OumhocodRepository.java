package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;

/**
 * Interface OumhocodRepository
 */
public interface OumhocodRepository {
	Integer hoCodesInsertHoCodes(List<HoCodes> lstHoCodes);

	Integer hoCodesUpdateHoCodes(List<HoCodes> lstHoCodes);

	List<HoCodes> hoCodesExecuteQuery(HoCodes objHoCodes);

	Integer hoCodesDeleteHoCodes(List<HoCodes> lstHoCodes);

	Integer hoCodesCheckDeleteMaster(HoCodes searchBean);

}
