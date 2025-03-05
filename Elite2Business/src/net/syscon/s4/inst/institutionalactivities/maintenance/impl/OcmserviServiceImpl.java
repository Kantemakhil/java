package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.ProgramServicesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmserviRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmserviService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;

/**
 * Class OcmserviServiceImpl
 */
@Service
public class OcmserviServiceImpl extends BaseBusiness implements OcmserviService {

	@Autowired
	private OcmserviRepository ocmserviRepository;
	@Autowired
	private TagServiceService tagServiceService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<ProgramServices> prgSrvExecuteQuery(final ProgramServices searchRecord) {
		return ocmserviRepository.prgSrvExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPRG_SRV
	 *
	 */
	@Transactional
	public ProgramServices prgSrvCommit(final ProgramServicesCommitBean commitBean) {
		int liReturn = 0;
		ProgramServices returnData = new ProgramServices();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final ProgramServices obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				final Integer returnObj = tagServiceService.checkProgramCode(obj.getProgramCode());
				if (returnObj == 1) {
					returnData.setSealFlag("5");
					return returnData;
				}
    	final Long returnValue =tagServiceService.PreInsertProgramService(obj);
				if (returnValue == null) {
					if ("UW".equals(obj.getProgramCategory())) {
						returnData.setSealFlag("3");
						return returnData;
					} else {
						returnData.setSealFlag("4");
						return returnData;
					}
				} else {
					obj.setProgramId(returnValue.intValue());
				}
			}
			String dateReturn = ocmserviRepository.prgSrvInsertProgramServices(commitBean.getInsertList());
			if (dateReturn == "0") {
				returnData.setSealFlag("0");
				return returnData;
			} else if (dateReturn == "1") {
				returnData.setSealFlag("1");
				return returnData;
			} else {
				returnData.setSealFlag(dateReturn);
				returnData.setActiveFlag("S");
				return returnData;
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final ProgramServices obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmserviRepository.prgSrvUpdateProgramServices(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			String dataReturn = ocmserviRepository.prgSrvDeleteProgramServices(commitBean.getDeleteList());
			if (dataReturn == "0") {
				returnData.setSealFlag("0");
				return returnData;
			} else if (dataReturn == "1") {
				returnData.setSealFlag("1");
				return returnData;
			} else {
				returnData.setSealFlag(dataReturn);
				returnData.setActiveFlag("D");
				return returnData;
			}
		}
		if (liReturn == 1) {
			returnData.setSealFlag("1");
		} else {
			returnData.setSealFlag("2");
		}
		return returnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsCategoryRecordGroup() {
		return ocmserviRepository.rgPsCategoryRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFunctionTypeRecordGroup() {
		return ocmserviRepository.rgFunctionTypeRecordGroup();

	}

	@Override
	public String getTableName(final String sealFlag) {
		return ocmserviRepository.getTableName(sealFlag);
	}

}