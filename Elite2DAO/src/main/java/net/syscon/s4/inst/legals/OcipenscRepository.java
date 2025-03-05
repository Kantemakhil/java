package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;

public interface OcipenscRepository {

	List<OdynfrmSubmitDataBean> getPendingSentenceCalcEvnets();

	List<OdynfrmSubmitDataBean> getOffenderPendingCalcEvent(OdynfrmSubmitDataBean searchBean);

	Integer insertOcdLeglsPendingData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	Integer deletePendingSentenceCalcEvents(Integer id, String userName);

}
