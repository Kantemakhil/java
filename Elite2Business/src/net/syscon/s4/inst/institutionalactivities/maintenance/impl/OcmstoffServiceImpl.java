package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmstoffRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmstoffService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfilesCommitBean;

/**
 * Class OcmstoffServiceImpl
 */
@Service
public class OcmstoffServiceImpl extends BaseBusiness implements OcmstoffService {

	@Autowired
	private OcmstoffRepository ocmstoffRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ProgramServicesProfiles> prgPrfGdExecuteQuery(final ProgramServicesProfiles searchRecord) {
		return ocmstoffRepository.prgPrfGdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPRG_PRF_GD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<ProgramServicesProfiles> prgPrfGdCommit(final ProgramServicesProfilesCommitBean commitBean) {
		final List<ProgramServicesProfiles> liReturnData = new ArrayList<>();
		ProgramServicesProfiles returnObj = new ProgramServicesProfiles();
		int liReturn = 0;
		if  (commitBean.getInsertList().size()==0 && commitBean.getDeleteList().size() == 0) {
			ProgramServicesProfiles obj = new ProgramServicesProfiles();
			obj.setReturnValue(1);
			liReturnData.add(obj);
			return liReturnData;
		}
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (ProgramServicesProfiles bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			returnObj = ocmstoffRepository.prgPrfGdInsertProgramServicesProfiles(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ProgramServicesProfiles bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmstoffRepository.prgPrfGdUpdateProgramServicesProfiles(commitBean.getUpdateList());
			returnObj.setReturnValue(liReturn);
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			returnObj = ocmstoffRepository.prgPrfGdDeleteProgramServicesProfiles(commitBean.getDeleteList());
		}
		liReturnData.add(returnObj);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ProgramServicesProfiles> prgPrfRcExecuteQuery(final ProgramServicesProfiles searchRecord) {
		return ocmstoffRepository.prgPrfRcExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ProgramServicesProfiles> prgPrfAgExecuteQuery(final ProgramServicesProfiles searchRecord) {
		return ocmstoffRepository.prgPrfAgExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ProgramServicesProfiles> prgPrfFaExecuteQuery(final ProgramServicesProfiles searchRecord) {
		return ocmstoffRepository.prgPrfFaExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ProgramServicesProfiles> prgPrfIgExecuteQuery(final ProgramServicesProfiles searchRecord) {
		return ocmstoffRepository.prgPrfIgExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ProgramServicesProfiles> prgPrfXgExecuteQuery(final ProgramServicesProfiles searchRecord) {
		return ocmstoffRepository.prgPrfXgExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPsSexRecordGroup() {
		return ocmstoffRepository.rgPsSexRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgEthnicityRecordGroup() {
		return ocmstoffRepository.rgEthnicityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPsNeedsRecordGroup() {
		return ocmstoffRepository.rgPsNeedsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPsAgeRangeRecordGroup() {
		return ocmstoffRepository.rgPsAgeRangeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPsOffGrpsRecordGroup() {
		return ocmstoffRepository.rgPsOffGrpsRecordGroup();

	}

	public Integer getProfileExist(final ProgramServicesProfiles searchRecord) throws ParseException {
		return ocmstoffRepository.getProfileExist(searchRecord);
	}

}