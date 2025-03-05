package net.syscon.s4.inst.legals.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legals.OcdsenchRepository;
import net.syscon.s4.inst.legals.OcdsenchService;

@Service
public class OcdsenchServiceImpl extends BaseBusiness implements OcdsenchService {

	@Autowired
	private OcdsenchRepository ocdsenchRepository;

	@Override
	public List<OdynfrmSubmitDataBean> getSentenceHistoryData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {		
		return ocdsenchRepository.getSentenceHistoryData(odynfrmSubmitDataBean);
	}

}
