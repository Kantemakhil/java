package net.syscon.s4.inst.legals.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legals.OcipenscRepository;
import net.syscon.s4.inst.legals.OcipenscService;

@Service
public class OcipenscServiceImpl extends BaseBusiness implements OcipenscService {

	@Autowired
	private OcipenscRepository ocipenscRepository;

	@Override
	public List<OdynfrmSubmitDataBean> getPendingSentenceCalcEvents(OdynfrmSubmitDataBean searchBean) {
		if (searchBean != null && searchBean.getSearchString() != null
				&& !searchBean.getSearchString().trim().equals("")) {
			return ocipenscRepository.getOffenderPendingCalcEvent(searchBean);
		}
		return ocipenscRepository.getPendingSentenceCalcEvnets();
	}

	@Override
	public Integer insertOcdLeglsPendingData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		return ocipenscRepository.insertOcdLeglsPendingData(odynfrmSubmitDataBean);
	}

	@Override
	public Integer deletePendingSentenceCalcEvents(Integer id,String userName) {
		return ocipenscRepository.deletePendingSentenceCalcEvents(id,userName);
	}

}
