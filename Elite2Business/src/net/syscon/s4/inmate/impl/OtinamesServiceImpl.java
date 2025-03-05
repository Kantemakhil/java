package net.syscon.s4.inmate.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.im.beans.VTrustHeaderCommitBean;
import net.syscon.s4.inmate.OtinamesRepository;
import net.syscon.s4.inmate.OtinamesService;
import net.syscon.s4.pkgs.trust_main.TrustMainService;

/**
 * Class OtinamesServiceImpl
 */
@Service
public class OtinamesServiceImpl extends BaseBusiness implements OtinamesService {

	@Autowired
	private OtinamesRepository otinamesRepository;
	
	@Autowired
	TrustMainService trustmainservice;

	/**
	 * Creates new OtinamesServiceImpl class Object
	 */
	public OtinamesServiceImpl() {
		// OtinamesServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderTrustAccounts cgfkchkVThaVThaOffTaF(OffenderTrustAccounts paramBean) {
		return otinamesRepository.cgfkchkVThaVThaOffTaF(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VTrustHeader> vThaExecuteQuery(VTrustHeader searchRecord) {
		List<VTrustHeader> returnList = new ArrayList<>();
		OffenderTrustAccounts trustAccounts = new OffenderTrustAccounts();
		String defWhere = null;
		if (searchRecord.getModuleName() != null && !"".equals(searchRecord.getModuleName().trim())){
			if ("OODOSALE".equals(searchRecord.getModuleName())  || "OODORETU".equals(searchRecord.getModuleName())||
					"OOIOSALE".equals(searchRecord.getModuleName()) || "OOMOCRES".equals(searchRecord.getModuleName())){
				final String returnDef = trustmainservice.trustHeaderQuery(searchRecord.getCaseloadId(), searchRecord.getModuleName().replaceFirst("/", ""), defWhere);
				if (returnDef != null && !returnDef.isEmpty()) {
					defWhere = returnDef;
				}
			} else if (otinamesRepository.vscMtCur(searchRecord.getCaseloadId()) >0) {
				final String returnDef = trustmainservice.trustHeaderQuery(searchRecord.getCaseloadId(), "OODOSALE", defWhere);
				if (returnDef != null && !returnDef.isEmpty()) {
					defWhere = returnDef;
				}
			} else {
				final String returnDef = trustmainservice.trustHeaderQuery(searchRecord.getCaseloadId(), "OTINAMES", defWhere);
				if (returnDef != null && !returnDef.isEmpty()) {
					defWhere = returnDef;
				}
			}
		}
		returnList = otinamesRepository.vThaExecuteQuery(searchRecord,defWhere);
		for (VTrustHeader vSearchData : returnList) {
			if (vSearchData.getRootOffenderId() != null) {
				trustAccounts.setOffenderId(Long.valueOf(vSearchData.getRootOffenderId().toString()));
				//trustAccounts = otinamesRepository.vThaPostQuery(vSearchData);
				if (vSearchData.getAccountClosedFlag() != null && vSearchData.getCurrentBalance() != null) {
					vSearchData.setDialogData(0);
					//vSearchData.setAccountClosedFlag(trustAccounts.getAccountClosedFlag());
					//vSearchData.setCurrentBalance(trustAccounts.getCurrentBalance());
				} else {
					vSearchData.setDialogData(1);
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_THA
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vThaCommit(VTrustHeaderCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord) {
		return otinamesRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer sysPflCommit(SystemProfilesCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

}