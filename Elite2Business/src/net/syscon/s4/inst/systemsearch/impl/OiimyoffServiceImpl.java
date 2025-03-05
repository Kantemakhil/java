package net.syscon.s4.inst.systemsearch.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.systemsearch.OiimyoffRepository;
import net.syscon.s4.inst.systemsearch.OiimyoffService;

@Service
public class OiimyoffServiceImpl extends BaseBusiness implements OiimyoffService{

	@Autowired
	private OiimyoffRepository oiimyoffRepository;
	private static Logger logger = LogManager.getLogger(OiimyoffServiceImpl.class.getName());

	@Override
	public List<VHeaderBlock> getMyOffenderList(VHeaderBlock paramBean) {
		List<VHeaderBlock> getMyOfeendersList = new ArrayList<VHeaderBlock>();
		try {
			getMyOfeendersList = oiimyoffRepository.getMyOffenderList(paramBean);
		} catch (Exception e) {
			logger.error("getMyOffenderList", e);
		}
		return getMyOfeendersList;
	}
}
;