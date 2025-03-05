package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.visitsmanagement.OimieplvRepository;
import net.syscon.s4.inst.visitsmanagement.OimieplvService;
import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

@Service
public class OimieplvServiceImpl extends BaseBusiness implements OimieplvService {

	private static Logger logger = LogManager.getLogger(OimieplvServiceImpl.class.getName());

	@Autowired
	private OimieplvRepository oimieplvRepository;

	@Override
	@Transactional
	public Integer commitBeanIepLevels(IEPLevelCommitBean commitBean) {
		Integer result = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			try {
				commitBean.getInsertList().forEach(iep->iep.setCreateUserId(commitBean.getCreateUserId()));
				result = oimieplvRepository.insertIepLevelRecord(commitBean.getInsertList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in commitBeanIepLevels :: " + e);
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			try {
				commitBean.getUpdateList().forEach(iep->iep.setModifyUserId(commitBean.getCreateUserId()));
				result = oimieplvRepository.updateIepLevelRecord(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in commitBeanIepLevels :: " + e);
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(iep->iep.setModifyUserId(commitBean.getCreateUserId()));
			try {
				result = oimieplvRepository.deleteIepLevelRecord(commitBean.getDeleteList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in commitBeanIepLevels :: " + e);
			}
		}
		return result;
	}

	@Override
	public List<IepLevelBean> getAllIepLevelCodes() {
		return oimieplvRepository.getAllIepLevelCodes();
	}
	
	@Override
	public String getSystemProfileValue() {
		return oimieplvRepository.getSystemProfileValue();
	}

}
