package net.syscon.s4.globalconfiguration.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumsypflRepository;
import net.syscon.s4.globalconfiguration.OumsypflService;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.triggers.SystemPofilesTjnService;
import net.syscon.s4.triggers.SystemProfilesT1Service;

/**
 * Class OumsypflServiceImpl
 */
@Service
public class OumsypflServiceImpl extends BaseBusiness implements OumsypflService {

	@Autowired
	private OumsypflRepository oumsypflRepository;
	
	public static Map<String,String> systemProfileCache=new HashMap<>();
	

	@Autowired
	private SystemProfilesT1Service systemProfilesT1Service;

	@Autowired
	private SystemPofilesTjnService systemPofilesTjnService;

	/**
	 * Creates new OumsypflServiceImpl class Object
	 */
	public OumsypflServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkSysPflSystemProfil(final ReferenceCodes paramBean) {
		return oumsypflRepository.cgfkchkSysPflSystemProfil(paramBean);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Dual cgwhenNewFormInstance(final String createUserId) {
		return oumsypflRepository.cgwhenNewFormInstance(createUserId);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oumsypflRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public String sysPflCommit(final SystemProfilesCommitBean commitBean) {
		String liReturn = "0";
		
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (SystemProfiles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = sysPflInsertSystemProfiles(commitBean.getInsertList());
			String status = "INSERTING";
			//systemPofilesTjnService.systemProfilesForInserting(commitBean.getInsertList(), status);

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SystemProfiles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = sysPflUpdateSystemProfiles(commitBean.getUpdateList());
			for (SystemProfiles obj : commitBean.getUpdateList()) {
				if (obj.getProfileType().equals("DB")) {
					systemProfilesT1Service.insertDbPatches(commitBean.getUpdateList());
				}
			}
			String status = "UPDATING";
			//systemPofilesTjnService.systemProfilesForInserting(commitBean.getUpdateList(), status);
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (SystemProfiles obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumsypflRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList()).toString();
			String status = "DELETING";
			//systemPofilesTjnService.systemProfilesForInserting(commitBean.getDeleteList(), status);
		}
		return liReturn;
	}

	@Transactional
	public String sysPflInsertSystemProfiles(final List<SystemProfiles> systemProfiles) {
		for (SystemProfiles systemPrfles : systemProfiles) {
			if (systemPrfles.getProfileType() == "CLIENT" && systemPrfles.getProfileCode() == "SMTP_USER_PS") {
				String toStatus = null;
				try {
					toStatus = oumsypflRepository.whenNewFormInstance(systemPrfles.getProfileValue());
				} catch (Exception e) {
					toStatus = "InsertProfileError";
					return toStatus;
				}
				if (toStatus != null) {
					systemPrfles.setProfileValue(toStatus);
				}
			}
		}
		return oumsypflRepository.sysPflInsertSystemProfiles(systemProfiles).toString();
	}

	@Transactional
	public String sysPflUpdateSystemProfiles(final List<SystemProfiles> systemProfiles) {
		for (final SystemProfiles systemPrfles : systemProfiles) {
			if (systemPrfles.getProfileType().equals("CLIENT")
					&& systemPrfles.getProfileCode().equals("SMTP_USER_PS")) {
				String toStatus = null;
				try {
					toStatus = oumsypflRepository.whenNewFormInstance(systemPrfles.getProfileValue());
				} catch (Exception e) {
					toStatus = "UpdateProfileError";
					return toStatus;
				}
				if (toStatus != null) {
					systemPrfles.setProfileValue(toStatus);
				}
			}
		}
		return oumsypflRepository.sysPflUpdateSystemProfiles(systemProfiles).toString();
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkSysPflProfileTypeRecordGroup() {
		return oumsypflRepository.cgfkSysPflProfileTypeRecordGroup();

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> getSystemProfileRecords(final SystemProfiles searchRecord) {
		return oumsypflRepository.getSystemProfileRecords(searchRecord);

	}

}