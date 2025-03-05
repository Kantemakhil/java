package net.syscon.s4.inst.correspondencetracking.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.correspondencetracking.OidomailRepository;
import net.syscon.s4.inst.correspondencetracking.OidomailService;
import net.syscon.s4.inst.correspondencetracking.beans.OffMailRestrictions;
import net.syscon.s4.inst.correspondencetracking.beans.OffMailRestrictionsCommitBean;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLog;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLogCommitBean;
import net.syscon.s4.inst.correspondencetracking.beans.OidomailCommonCommitBean;

@Service
public class OidomailServiceImpl extends BaseBusiness implements OidomailService {

	@Autowired
	private OidomailRepository oidomailRepository;

	private static Logger logger = LogManager.getLogger(OidomailServiceImpl.class.getName());

	@Override
	public List<ReferenceCodes> getContactTypeLov() {
		List<ReferenceCodes> contactList = new ArrayList<ReferenceCodes>();
		ReferenceCodes rf1 = new ReferenceCodes();
		rf1.setCode("PER");
		rf1.setDescription("Person");
		contactList.add(rf1);
		ReferenceCodes rf2 = new ReferenceCodes();
		rf2.setCode("CORP");
		rf2.setDescription("Corporate");
		contactList.add(rf2);
		return contactList;
	}

	@Override
	public List<OffenderMailLog> mailLogExecuteQuery(Long offenderBookId) {
		List<OffenderMailLog> mailLogsList = new ArrayList<OffenderMailLog>();
		try {
			mailLogsList = oidomailRepository.mailLogExecuteQuery(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mailLogExecuteQuery", e);
		}
		return mailLogsList;

	}

	@Override
	public List<OffMailRestrictions> mailRestrictionExecuteQuery(Long offenderBookId) {
		List<OffMailRestrictions> ordersList = null;
		ordersList = oidomailRepository.mailRestrictionExecuteQuery(offenderBookId);
		return ordersList;
	}

	@Override
	public Integer offenderMailLogsCommit(OffenderMailLogCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
		
			commitBean.getInsertList().forEach(OffenderMailLog -> {
				OffenderMailLog.setCreateUserId(commitBean.getCreateUserId());
				List<OffenderMailLog> list = new ArrayList<OffenderMailLog>();
				list.add(OffenderMailLog);
			});
			liReturn = oidomailRepository.offenderMailLogInsert(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidomailRepository.offenderMailLogInsertUpdate(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidomailRepository.offenderMailLogInsertDelete(commitBean.getDeleteList());
		}

		return liReturn;
	}

	@Override
	public Integer mailLogAndResrtrictionsCommonSave(OidomailCommonCommitBean commitBean) {

		Integer returnVal = 0;
		if (commitBean != null) {
			if (commitBean.getOffenderMailLogCommitBean() != null) {
				if ((commitBean.getOffenderMailLogCommitBean().getInsertList() != null
						&& commitBean.getOffenderMailLogCommitBean().getInsertList().size() > 0)
						|| (commitBean.getOffenderMailLogCommitBean().getUpdateList() != null
								&& commitBean.getOffenderMailLogCommitBean().getUpdateList().size() > 0)
						|| (commitBean.getOffenderMailLogCommitBean().getDeleteList() != null
								&& commitBean.getOffenderMailLogCommitBean().getDeleteList().size() > 0)) {
					commitBean.getOffenderMailLogCommitBean().setCreateUserId(commitBean.getCreateUserId());
					returnVal = offenderMailLogsCommit(commitBean.getOffenderMailLogCommitBean());
				}

			}
			if (commitBean.getOffenderMailRestrictionCommitBean() != null) {
				if ((commitBean.getOffenderMailRestrictionCommitBean().getInsertList() != null
						&& commitBean.getOffenderMailRestrictionCommitBean().getInsertList().size() > 0)
						|| (commitBean.getOffenderMailRestrictionCommitBean().getUpdateList() != null
								&& commitBean.getOffenderMailRestrictionCommitBean().getUpdateList().size() > 0)
						|| (commitBean.getOffenderMailRestrictionCommitBean().getDeleteList() != null
								&& commitBean.getOffenderMailRestrictionCommitBean().getDeleteList().size() > 0)) {
					commitBean.getOffenderMailRestrictionCommitBean().setCreateUserId(commitBean.getCreateUserId());
					returnVal = offMailRestrictionsCommit(commitBean.getOffenderMailRestrictionCommitBean());
				}
			}

		}
		return returnVal;
	}

	@Override
	public Integer offMailRestrictionsCommit(OffMailRestrictionsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(OffenderMailRestrictions -> {
				OffenderMailRestrictions.setCreateUserId(commitBean.getCreateUserId());


			});
			
			liReturn = oidomailRepository.offMailRestrictionsInsert(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidomailRepository.offMailRestrictionsUpdate(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidomailRepository.offMailRestrictionsDelete(commitBean.getDeleteList());
		}

		return liReturn;
	}

}
