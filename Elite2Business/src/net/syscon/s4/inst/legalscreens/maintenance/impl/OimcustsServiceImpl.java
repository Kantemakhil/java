package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.legalscreens.maintenance.OimcustsRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OimcustsService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatuses;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatusesCommitBean;
@Service
public class OimcustsServiceImpl implements OimcustsService {
	@Autowired
	private OimcustsRepository oimcustasRepository;

	@Override
	@Transactional
	public Integer saveCustodyData(LegalCustodyStatusesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oimcustasRepository.saveCustodyData(commitBean.getInsertList());
		}
		
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimcustasRepository.updateCustodyData(commitBean.getUpdateList());
		}
		return liReturn;
	}
	
	@Override
	public List<LegalCustodyStatuses> getLegalsData(){
		return oimcustasRepository.getLegalsData();
	}
	

}
