package net.syscon.s4.inst.schedules.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.OiidmoveRepository;
import net.syscon.s4.inst.schedules.OiidmoveService;
import net.syscon.s4.inst.schedules.bean.VOffExtMovements;
import net.syscon.s4.inst.schedules.bean.VOffExtMovementsCommitBean;

@Service
public class OiidmoveServiceImpl extends BaseBusiness implements OiidmoveService{

@Autowired
private OiidmoveRepository oiidmoveRepository;

@Autowired
private OsiosearService osiosearServiceImpl;
/**
 * Logger object used to print the log in the file
 */


/**
 *Creates new OiidmoveServiceImpl class Object 
 */
public OiidmoveServiceImpl(){
}

/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public ReferenceCodes CgfkchkOffEmOffEmRefMov(ReferenceCodes paramBean) {
			ReferenceCodes referenceCodes = oiidmoveRepository.cgfkchkOffEmOffEmRefMov(paramBean);
return referenceCodes;
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public MovementReasons CgfkchkOffEmOffEmMoveRs(MovementReasons paramBean) {
			MovementReasons movementReasons = oiidmoveRepository.cgfkchkOffEmOffEmMoveRs(paramBean);
return movementReasons;
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public ReferenceCodes CgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean) {
		ReferenceCodes referenceCodes = null;
		if ((paramBean.getCode() != null) ){
			referenceCodes = oiidmoveRepository.cgfkchkOffEmOffEmDirecti(paramBean);
}
		return referenceCodes;
	}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public ReferenceCodes CgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean) {
		ReferenceCodes referenceCodes = null;
		if ((paramBean.getCode() != null) ){
			referenceCodes = oiidmoveRepository.cgfkchkOffEmOffEmMoveRe(paramBean);
}
		return referenceCodes;
	}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public List<Object> CgfkchkOffEmOffEmVHeade(Offenders paramBean) {
		return oiidmoveRepository.cgfkchkOffEmOffEmVHeade(paramBean);
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public VHeaderBlock CgfklkpOffEmOffEmVHeade(VHeaderBlock paramBean) {
		VHeaderBlock vHeaderBlock = null;
		if ((    paramBean.getOffenderIdDisplay() != null ||  paramBean.getLastName() != null) ){
			vHeaderBlock = (VHeaderBlock) oiidmoveRepository.cgfklkpOffEmOffEmVHeade(paramBean);
		}
return vHeaderBlock;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<VOffExtMovements> offEmExecuteQuery(VOffExtMovements searchRecord) {
	if(searchRecord.getOffenderIdDisplay() != null ){
			searchRecord.setOffenderIdDisplay(searchRecord.getOffenderIdDisplay());
	}
	List<VOffExtMovements> returnList=new ArrayList<VOffExtMovements>();
	List<VOffExtMovements> returnFinalList=new ArrayList<VOffExtMovements>();
	returnList=oiidmoveRepository.offEmExecuteQuery(searchRecord);		
	returnFinalList= removeSealOffExternalMovements(returnList,searchRecord.getCreateUserId());
	return returnFinalList;
}



/**Insert the records from database table
 *
 * @param lstOFF_EM
 *
 * @throws SQLException
 */
@Transactional
public Integer offEmCommit(VOffExtMovementsCommitBean commitBean) {
		int liReturn = 0;
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				liReturn = oiidmoveRepository.offEmInsertVOffExtMovements(commitBean.getInsertList());
			}
			return liReturn;
}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup() {
	List<ReferenceCodes> resultList = oiidmoveRepository.cgfkOffEmMovementReasonCoRecordGroup();
	String tempCodeDef = null;
	for(ReferenceCodes result: resultList) {
		tempCodeDef = result.getCode();
		result.setCode(result.getDescription());
		result.setDescription(tempCodeDef);
		tempCodeDef = null;
	}
		return resultList;

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
	List<ReferenceCodes> resultList = oiidmoveRepository.cgfkOffEmMovementTypeRecordGroup();
	String tempCodeDef = null;
	for(ReferenceCodes result: resultList) {
		tempCodeDef = result.getCode();
		result.setCode(result.getDescription());
		result.setDescription(tempCodeDef);
		tempCodeDef = null;
	}
		return resultList;

}

private List<VOffExtMovements> removeSealOffExternalMovements(List<VOffExtMovements> updatedList, String createUserId) {
	List<VOffExtMovements> resultList = new ArrayList<VOffExtMovements>();
	Integer returnValue=osiosearServiceImpl.getSystemProfileUserAccValue(createUserId);	
	if (returnValue == 0) {
		resultList = updatedList.stream().filter(object -> !object.getSealFlag().equals("Y"))
				.collect(Collectors.toList());
	} else {
		resultList.addAll(updatedList);
		}
	return resultList;
}

}