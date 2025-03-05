package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;

public interface OcdsenchRepository {

	List<OdynfrmSubmitDataBean> getSentenceHistoryData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

}
