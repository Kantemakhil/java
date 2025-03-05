package net.syscon.s4.inst.booking.maintainence.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileCodesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.inst.booking.maintainence.OimprfcoRepository;
import net.syscon.s4.inst.booking.maintainence.OimprfcoService;

/**
 * Class OimprfcoServiceImpl
 */
@Service
public class OimprfcoServiceImpl extends BaseBusiness implements OimprfcoService {

	@Autowired
	private OimprfcoRepository oimprfcoRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<ProfileTypes> pflTypeExecuteQuery(final ProfileTypes searchRecord) {
		return oimprfcoRepository.pflTypeExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<ProfileCodes> pflCodeExecuteQuery(final ProfileCodes searchRecord) {
		return oimprfcoRepository.pflCodeExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPFL_CODE
	 */
	@Transactional
	public List<ProfileCodes> pflCodeCommit(final ProfileCodesCommitBean commitBean) {
		int liReturn = 0;
		final List<ProfileCodes> returnList = new ArrayList<>();
		final ProfileCodes returnBean = new ProfileCodes();
		// insertRecords
		final String userId = oimprfcoRepository.getUserId(commitBean.getCreateUserId());
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final ProfileCodes profileCodes : commitBean.getInsertList()) {
				profileCodes.setUserId(userId);
				profileCodes.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimprfcoRepository.pflCodeInsertProfileCodes(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (final ProfileCodes profileCodes : commitBean.getUpdateList()) {
				profileCodes.setUserId(userId);
				profileCodes.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimprfcoRepository.pflCodeUpdateProfileCodes(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimprfcoRepository.pflCodeDeleteProfileCodes(commitBean.getDeleteList());
		}
		if (liReturn > 0) {
			returnBean.setSealFlag("success");
		} else {
			returnBean.setSealFlag("fail");
		}
		returnList.add(returnBean);
		return returnList;
	}

	/**
	 * Check profile codes.
	 *
	 * @param profileCodes
	 *            the profile codes
	 * @return the integer
	 */
	@Override
	public int checkProfileCodes(final String profileCode) {
		int returnVal;
		returnVal = oimprfcoRepository.checkProfileCodes(profileCode);
		return returnVal;
	}
}