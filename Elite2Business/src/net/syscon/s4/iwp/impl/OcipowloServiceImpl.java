package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffendersCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocationCommitBean;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.iwp.OcipowloRepository;
import net.syscon.s4.iwp.OcipowloService;
import net.syscon.s4.pkgs.comunity_pkg.ComunityPkgService;

@Service
public class OcipowloServiceImpl extends BaseBusiness implements OcipowloService {

	@Autowired
	private OcipowloRepository ocipowloRepository;
	@Autowired
	private ComunityPkgService comunityPkgService;

	/**
	 * This method is used to execute a record group
	 *@param caseLoadId
	 * @return List<AgencyLocations>
	 *
	 */
	public List<AgencyLocations> cgfkStaffLr1DspDescriptionRecordGroup(final String caseLoadId) {
		return ocipowloRepository.cgfkStaffLr1DspDescriptionRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 *
	 */
	public List<ReferenceCodes> positionLovRecordGroup() {
		return ocipowloRepository.positionLovRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> roleLovRecordGroup() {
		return ocipowloRepository.roleLovRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> scheduleTypeLovRecordGroup() {
		return ocipowloRepository.scheduleTypeLovRecordGroup();

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VStaffLocation> vOffDetExecuteQuery(VStaffLocation searchRecord) {
		List<VStaffLocation> returnList = ocipowloRepository.vOffDetExecuteQuery(searchRecord);
		returnList.forEach(element -> {
//		long data = ocipowloRepository.populateNoOffenders(element);
		long data = comunityPkgService.getOfficerPo(element);
		element.setNoOffender(data);
		/* commented according to legecy
		*
		*/
		// if(searchRecord.getDspWorkFlowFlag().equals("Y")) {
		// BigDecimal workLoad= ocipowloRepository.populateCurrentWorkload(searchRecord);
		// element.getDspWorkFlowFlag();
		// }

		});

		return returnList;

		}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VAssignedOffenders> vAssOffExecuteQuery(VAssignedOffenders searchRecord) {
		List<VAssignedOffenders> returnList = ocipowloRepository.vAssOffExecuteQuery(searchRecord);
		returnList.forEach(element -> {
			Integer hpCount = ocipowloRepository.curGetHpCase(element);
			Integer yCount = ocipowloRepository.curGetYCase(element);
			Integer aCount = ocipowloRepository.curGetACase(element);
			if (hpCount > 0) {
				element.setCaseType("HP");
			} else {
				if (yCount > 0 & aCount > 0) {
					element.setCaseType("D");
				} else if (yCount > 0 & aCount == 0) {
					element.setCaseType("Y");
				} else if (aCount > 0 & yCount == 0) {
					element.setCaseType("A");
				} else {
					element.setCaseType(null);
				}
			}
			Images image = ocipowloRepository.imageData(element.getOffenderBookId());
			if (image != null && image.getImageThumbnail() != null) {
				element.setImageData(image.getImageThumbnail());
			}
		});
		return returnList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyLocations> CgfkchkStaffLr1StaffLrAg(AgencyLocations paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations CgfklkpStaffLr1StaffLrAg(AgencyLocations paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> CgfkchkVOffDetVOffDet(ReferenceCodes paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes CgfklkpVOffDetVOffDet(ReferenceCodes paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes CgfkchkVOffDetVOffDe2(ReferenceCodes paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes CgfklkpVOffDetVOffDe2(ReferenceCodes paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes CgfkchkVOffDetVOffDe3(ReferenceCodes paramBean) {

		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes CgfklkpVOffDetVOffDe3(ReferenceCodes paramBean) {

		return null;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFF_DET
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vOffDetCommit(VStaffLocationCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_ASS_OFF
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vAssOffCommit(VAssignedOffendersCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

}