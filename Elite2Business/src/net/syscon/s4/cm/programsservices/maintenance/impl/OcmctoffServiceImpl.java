package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.maintenance.CourseActivityProfiles;
import net.syscon.s4.cm.programsservices.maintenance.CourseActivityProfilesCommitBean;
import net.syscon.s4.cm.programsservices.maintenance.OcmctoffRepository;
import net.syscon.s4.cm.programsservices.maintenance.OcmctoffService;
import net.syscon.s4.genericservices.BaseBusiness;

/**
 * Class OcmctoffServiceImpl
 */
@Service
public class OcmctoffServiceImpl extends BaseBusiness implements OcmctoffService {

	@Autowired
	private OcmctoffRepository ocmctoffRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CourseActivityProfiles> crPrfGdExecuteQuery(final CourseActivityProfiles searchRecord) {
		return ocmctoffRepository.crPrfGdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCR_PRF_GD
	 *
	 * 
	 */
	@Transactional
	public Integer crPrfGdCommit(final CourseActivityProfilesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().isEmpty()
				&& commitBean.getDeleteList() != null && commitBean.getDeleteList().isEmpty()) {
			return 1;
		}
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (CourseActivityProfiles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}

			liReturn = ocmctoffRepository.crPrfGdInsertCourseActivityProfiles(commitBean.getInsertList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (CourseActivityProfiles obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmctoffRepository.crPrfGdDeleteCourseActivityProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}
}