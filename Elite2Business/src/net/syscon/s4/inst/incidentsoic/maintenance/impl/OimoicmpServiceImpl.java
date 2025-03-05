package net.syscon.s4.inst.incidentsoic.maintenance.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimits;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimitsCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OimoicmpRepository;
import net.syscon.s4.inst.incidentsoic.maintenance.OimoicmpService;
/**
 * Class OimoicmpServiceImpl */
@Service
public class OimoicmpServiceImpl extends BaseBusiness implements OimoicmpService{

@Autowired
private OimoicmpRepository oimoicmpRepository;
	

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<OicSanctionLimits> oicSlExecuteQuery(OicSanctionLimits searchRecord)  {
		return oimoicmpRepository.oicSlExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstOIC_SL
 *
 * @throws SQLException
 */
@Transactional
public List<OicSanctionLimits> oicSlCommit(OicSanctionLimitsCommitBean commitBean)  {
	List<OicSanctionLimits> returnList=new ArrayList<OicSanctionLimits>();
	OicSanctionLimits retunObject=new OicSanctionLimits();
		int liReturn = 0;
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				for(OicSanctionLimits obj :commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oimoicmpRepository.oicSlInsertOicSanctionLimits(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				for(OicSanctionLimits obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oimoicmpRepository.oicSlUpdateOicSanctionLimits(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = oimoicmpRepository.oicSlDeleteOicSanctionLimits(commitBean.getDeleteList());
			}
			retunObject.setListSeq(liReturn);
			returnList.add(retunObject);
			return returnList;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord)  {
		return oimoicmpRepository.sysPflExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstSYS_PFL
 *
 * @throws SQLException
 */
@Transactional
public Integer sysPflCommit(SystemProfilesCommitBean commitBean)  {
		int liReturn = 0;
		return liReturn;
}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkOicSlOicSanctionCodeRecordGroup()  {
		return oimoicmpRepository.cgfkOicSlOicSanctionCodeRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkOicSlOicHearingTypeRecordGroup()  {
		return oimoicmpRepository.cgfkOicSlOicHearingTypeRecordGroup();

}

}