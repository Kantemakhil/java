package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.OimallowRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OimallowService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.AllowancesCommitBean;

@Service
public class OimallowServiceImpl implements OimallowService {
	
	@Autowired
	private OimallowRepository oimallowRepository;
	
	private static Logger logger = LogManager.getLogger(OimallowServiceImpl.class.getName());

	@Override
	public List<Allowances> getAllAllowances() {
		return oimallowRepository.getAllAllowances();
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer saveAllowances(AllowancesCommitBean commitBean) {
		Integer result = 0;
		List<Allowances> allowancesList = new ArrayList<Allowances>();
		
		Date currentDate = new Date();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0 ) {
			commitBean.getInsertList().forEach(bo -> {bo.setCreateUserId(commitBean.getCreateUserId());
				bo.setVersionStartDate(currentDate);
			});
			allowancesList.addAll(commitBean.getInsertList());
		}
	
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
					bo.setCreateUserId(commitBean.getCreateUserId());
					bo.setModifyUserId(commitBean.getCreateUserId());
					allowancesList.add(bo);
			});
			try {
				result = oimallowRepository.updateAllowances(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveAllowances :: " + e);
			}
		}
		if (allowancesList != null && allowancesList.size() > 0) {
			try {
				result = oimallowRepository.insertAllowances(allowancesList);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveAllowances :: " + e);
			}
		}
		return result;
	}

	@Override
	public List<ReferenceCodes> getUnit() {
		return oimallowRepository.getUnit();
	}
	
}