package net.syscon.s4.cm.searchassign.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.searchassign.OcimyoffRepository;
import net.syscon.s4.cm.searchassign.OcimyoffService;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

@Service
public class OcimyoffServiceImpl extends BaseBusiness implements OcimyoffService{

	@Autowired
	private OcimyoffRepository ocimyoffRepository;

	@Override
	public List<VHeaderBlock> getMyOffenderList(VHeaderBlock paramBean) {
		return ocimyoffRepository.getMyOffenderList(paramBean);
	}

	@Override
	public List<OffenderSentConditions> offenderConditionExcuteQuery(VHeaderBlock paramBean) {
		// TODO Auto-generated method stub
		return ocimyoffRepository.offenderConditionExcuteQuery(paramBean);
	}
}
