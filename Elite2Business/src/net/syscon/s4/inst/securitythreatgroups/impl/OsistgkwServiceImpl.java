package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgSearchV1;
import net.syscon.s4.common.beans.StgSearchV1CommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.securitythreatgroups.OsistgkwRepository;
import net.syscon.s4.inst.securitythreatgroups.OsistgkwService;

/**
 * Class OsistgkwServiceImpl
 */
@Service
public class OsistgkwServiceImpl extends BaseBusiness implements OsistgkwService {

	@Autowired
	private OsistgkwRepository osistgkwRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return List<StgSearchV1>
	 */
	public List<StgSearchV1> stgSearchV1ExecuteQuery(final StgSearchV1 searchRecord) {
		final List<StgSearchV1> returnList = osistgkwRepository.stgSearchV1ExecuteQuery(searchRecord);
		final String returnValThree = osistgkwRepository.vprofvaluestgDescription();
		if (returnValThree != null && returnList != null && !returnList.isEmpty()) {
			returnList.stream().forEach(data -> {
				String retval = null;
				if ("1".equals(returnValThree)) {
					retval = osistgkwRepository.vprofvaluestgDescriptionOne(data.getStgId());
				} else if ("2".equals(returnValThree)) {
					retval = osistgkwRepository.vprofvaluestgDescriptionTwo(data.getStgId());
				} else {
					retval = osistgkwRepository.vprofvaluestgDescriptionThree(data.getStgId());
				}
				data.setNbtStgDescp(retval);

			});
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_SEARCH_V1
	 * @return Integer
	 * 
	 */
	@Transactional
	public Integer stgSearchV1Commit(final StgSearchV1CommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = osistgkwRepository.stgSearchV1InsertStgSearchV1(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = osistgkwRepository.stgSearchV1UpdateStgSearchV1(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = osistgkwRepository.stgSearchV1DeleteStgSearchV1(commitBean.getDeleteList());
		}
		return liReturn;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStgGroupDescription
	 * @return List<SecurityThreatGroups>
	 * @param params
	 *
	 */
	public List<SecurityThreatGroups> getStgGroupDescription(final SecurityThreatGroups paramBean) {
		return osistgkwRepository.getStgGroupDescription(paramBean);
	}
}