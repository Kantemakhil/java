package net.syscon.s4.inst.victimmanagement.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.victimmanagement.OivctmngRepository;
import net.syscon.s4.inst.victimmanagement.OivctmngService;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactLogs;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactLogsCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactPreferences;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactPreferencesCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimLinkedOffenders;
import net.syscon.s4.inst.victimmanagement.beans.VictimLinkedOffendersCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecords;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecordsCommitBean;

@Service
public class OivctmngServiceImpl implements OivctmngService {

	@Autowired
	private OivctmngRepository oivctmngRepository;

	private static Logger logger = LogManager.getLogger(OivctmngServiceImpl.class.getName());

	@Override
	public List<VictimRecords> getAllVictimRecords() {
		return oivctmngRepository.getAllVictimRecords();
	}

	@Override
	@Transactional
	public Integer saveVictimRecords(VictimRecordsCommitBean commitBean) {

		Integer result = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			try {
				result = oivctmngRepository.insertVictimRecords(commitBean.getInsertList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveVictimRecords :: " + e);
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			try {
				result = oivctmngRepository.updateVictimRecords(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveVictimRecords :: " + e);
			}
		}

		return result;
	}

	@Override
	public List<VictimLinkedOffenders> getAllVictimLinkedOffenders(Integer victimId) {

		List<VictimLinkedOffenders> returnList = new ArrayList<VictimLinkedOffenders>();
		returnList = oivctmngRepository.getAllVictimLinkedOffenders(victimId);

		for (VictimLinkedOffenders obj : returnList) {
			if (obj.getJsonData() != null)
				obj.setJsonDataString(new String(obj.getJsonData()));
		}

		return returnList;
	}

	@Override
	@Transactional
	public Integer saveVictimLinkedOffenders(VictimLinkedOffendersCommitBean commitBean) {
		Integer result = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			try {
				result = oivctmngRepository.insertVictimLinkedOffenders(commitBean.getInsertList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveVictimLinkedOffenders :: " + e);
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			try {
				result = oivctmngRepository.updateVictimLinkedOffenders(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveVictimLinkedOffenders :: " + e);
			}
		}

		return result;
	}

	@Override
	public List<VictimContactLogs> getAllVictimContactLogs(Integer victimId) {
		return oivctmngRepository.getAllinsertVictimContactLogs(victimId);
	}

	@Override
	@Transactional
	public Integer saveVictimContactLogs(VictimContactLogsCommitBean commitBean) {
		Integer result = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			try {
				result = oivctmngRepository.insertVictimContactLogs(commitBean.getInsertList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveVictimContactLogs :: " + e);
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			try {
				result = oivctmngRepository.updateVictimContactLogs(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveVictimContactLogs :: " + e);
			}
		}

		return result;
	}

	@Override
	public List<VictimContactPreferences> getAllvictimContactPreferences(Integer victimId) {

		return oivctmngRepository.getAllvictimContactPreferences(victimId);
	}

	@Override
	@Transactional
	public Integer saveVictimContactPreferences(VictimContactPreferencesCommitBean commitBean) {
		Integer result = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			try {
				result = oivctmngRepository.insertVictimContactPreferences(commitBean.getInsertList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveVictimContactPreferences :: " + e);
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			try {
				result = oivctmngRepository.updateVictimContactPreferences(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveVictimContactPreferences :: " + e);
			}
		}

		return result;
	}

	@Override
	public Integer getVictimId() {

		return oivctmngRepository.getVictimId();
	}

}
