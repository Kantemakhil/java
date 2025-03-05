package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.im.beans.LegalUpdateUsagesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.OcmstatsRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OcmstatsService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasonsCommitBean;
@Service
public class OcmstatsServiceImpl implements OcmstatsService {
	
	@Autowired
	private OcmstatsRepository ocmstatsRepository;

	@Override
	public List<LegalUpdateReasons> getOrdersData() {
		return ocmstatsRepository.getOrdersData();
	}
	
	@Override
	public List<LegalUpdateUsages> getStatuesData(String updateReasonCode) {
		return ocmstatsRepository.getStatuesData(updateReasonCode);
	}
	
	@Override
	@Transactional
	public Integer saveOrdersData(LegalUpdateReasonsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (LegalUpdateReasons obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmstatsRepository.saveOrdersData(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (LegalUpdateReasons obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmstatsRepository.updateOrdersData(commitBean.getUpdateList());
		}
		
		return liReturn;
	}
	
	@Override
	@Transactional
	public Integer saveStautesData(LegalUpdateUsagesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (LegalUpdateUsages obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmstatsRepository.saveStautesData(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (LegalUpdateUsages obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmstatsRepository.updateStautesData(commitBean.getUpdateList());
		}
		
		return liReturn;
	}
	
	
	

}
