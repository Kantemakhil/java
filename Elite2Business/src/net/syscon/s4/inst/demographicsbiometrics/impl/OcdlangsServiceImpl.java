package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderLanguages;
import net.syscon.s4.im.beans.OffenderLanguagesCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcdlangsRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdlangsService;

/**
 * Class OcdlangsServiceImpl
 */
@Service
public class OcdlangsServiceImpl extends BaseBusiness implements OcdlangsService {

	@Autowired
	private OcdlangsRepository ocdlangsRepos;

	/**
	 * Creates new OcdlangsServiceImpl class Object
	 */
	public OcdlangsServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> offBkgOnCheckDeleteMaster(final OffenderLanguages paramBean) {
		List<Object> returnList = new ArrayList<Object>();
		returnList = ocdlangsRepos.offBkgOnCheckDeleteMaster(paramBean);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderLanguages> offPrimLangExecuteQuery(final OffenderLanguages searchRecord) {
		return ocdlangsRepos.offPrimLangExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PRIM_LANG
	 *
	 */
	@Transactional
	public Integer offPrimLangCommit(final OffenderLanguagesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderLanguages bean :commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdlangsRepos.offPrimLangInsertOffenderLanguages(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderLanguages bean :commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdlangsRepos.offPrimLangUpdateOffenderLanguages(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdlangsRepos.offPrimLangDeleteOffenderLanguages(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderLanguages> prefLangWriteExecuteQuery(final OffenderLanguages searchRecord) {
		List<OffenderLanguages> returnList = new ArrayList<OffenderLanguages>();
		returnList = ocdlangsRepos.prefLangWriteExecuteQuery(searchRecord);
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPREF_LANG_WRITE
	 *
	 */
	@Transactional
	public Integer prefLangWriteCommit(final OffenderLanguagesCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderLanguages bean :commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdlangsRepos.prefLangWriteInsertprefLangWrite(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderLanguages bean :commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdlangsRepos.prefLangWriteUpdateprefLangWrite(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdlangsRepos.offPrimLangDeleteOffenderLanguages(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderLanguages> prefLangSpeakExecuteQuery(final OffenderLanguages searchRecord) {
		List<OffenderLanguages> returnList = new ArrayList<OffenderLanguages>();
		returnList = ocdlangsRepos.prefLangSpeakExecuteQuery(searchRecord);
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderLanguages> offSecLangExecuteQuery(final OffenderLanguages searchRecord) {
		final List<OffenderLanguages> returnList = ocdlangsRepos.offSecLangExecuteQuery(searchRecord);
		for (final OffenderLanguages obj : returnList) {
			obj.setLanguageCodeTemp(obj.getLanguageCode());
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SEC_LANG
	 *
	 */
	@Transactional
	public Integer offSecLangCommit(final OffenderLanguagesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderLanguages bean :commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdlangsRepos.offSecLangInsertOffenderLanguages(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderLanguages bean :commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdlangsRepos.offSecLangUpdateOffenderLanguages(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdlangsRepos.offSecLangDeleteOffenderLanguages(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgLangSkillsRecordGroup() {
		return ocdlangsRepos.rgLangSkillsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPrefLangRecordGroup() {
		return ocdlangsRepos.rgPrefLangRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSecLangRecordGroup(final String langCode, final String langCode1) {
		final List<ReferenceCodes> returnList = ocdlangsRepos.rgSecLangRecordGroup(langCode, langCode1);
		returnList.forEach(result -> {
			if (result.getCode().equals(langCode) || result.getCode().equals(langCode1)) {
				result.setCanDisplay(false);
			}
		});
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public ReferenceCodes getPreferredDefault() {
		return ocdlangsRepos.getPreferredDefault();

	}

}