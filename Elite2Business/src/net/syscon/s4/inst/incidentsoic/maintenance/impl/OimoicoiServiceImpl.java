package net.syscon.s4.inst.incidentsoic.maintenance.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.incidentsoic.maintenance.OicOffenceIndicators;
import net.syscon.s4.inst.incidentsoic.maintenance.OicOffenceIndicatorsCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OicOffencesCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OimoicoiRepository;
import net.syscon.s4.inst.incidentsoic.maintenance.OimoicoiService;

/**
 * Class OimoicoiServiceImpl
 */
@Service
public class OimoicoiServiceImpl extends BaseBusiness implements OimoicoiService {

	@Autowired
	private OimoicoiRepository oimoicoiRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object oicOffenceIndicatorsPreInsert() {
		return oimoicoiRepository;
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OicOffences> oicOfnExecuteQuery(OicOffences searchRecord) {
		return oimoicoiRepository.oicOfnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOIC_OFN
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<OicOffences> oicOfnCommit(OicOffencesCommitBean commitBean) {
		List<OicOffences> returnList=new ArrayList<OicOffences>();
		OicOffences returnObject=new OicOffences();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OicOffences object : commitBean.getInsertList()) {
				Integer countExist=oimoicoiRepository.checkOffenseCodeExist(object);
				if(countExist > 0) {
					returnObject.setListSeq(2);
					returnList.add(returnObject);
					return returnList;
				}
				object.setOicOffenceId(oimoicoiRepository.getNextOicOffenceId());
				object.setCreateUserId(commitBean.getCreateUserId());
			}			
			liReturn = oimoicoiRepository.oicOfnInsertOicOffences(commitBean.getInsertList());
			returnObject.setListSeq(liReturn);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OicOffences object : commitBean.getUpdateList()) {
				object.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoicoiRepository.oicOfnUpdateOicOffences(commitBean.getUpdateList());
			returnObject.setListSeq(liReturn);
		}
		returnList.add(returnObject);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OicOffenceIndicators> oicOffenceIndicatorsExecuteQuery(OicOffenceIndicators searchRecord) {
		return oimoicoiRepository.oicOffenceIndicatorsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOIC_OFFENCE_INDICATORS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer oicOffenceIndicatorsCommit(OicOffenceIndicatorsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OicOffenceIndicators object : commitBean.getInsertList()) {				
				object.setOicOffenceIndicatorId(oimoicoiRepository.oicOffenceIndicatorsPreInsert());
				object.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoicoiRepository.oicOffenceIndicatorsInsertOicOffenceIndicators(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OicOffenceIndicators object : commitBean.getUpdateList()) {				
				object.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoicoiRepository.oicOffenceIndicatorsUpdateOicOffenceIndicators(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimoicoiRepository.oicOffenceIndicatorsDeleteOicOffenceIndicators(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgOicOffenceCategRecordGroup() {
		return oimoicoiRepository.rgOicOffenceCategRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgOicOffenceTypeRecordGroup() {
		return oimoicoiRepository.rgOicOffenceTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgOicOffenceIndicatorsRecordGroup() {
		return oimoicoiRepository.rgOicOffenceIndicatorsRecordGroup();

	}

	public OicOffences oicOfnCheckoverLapping(OicOffences objSearchDao){
		List<OicOffences> returnlist=new ArrayList<>();
		OicOffences returnObject=new OicOffences();
		returnlist = oimoicoiRepository.oicOfnCheckoverLapping(objSearchDao);
		if(!returnlist.isEmpty()) {		
			returnObject =returnlist.get(0);
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(returnObject.getpStartDate()!=null) {			
				returnObject.setShowStartDate(simpleDateFormat.format(returnObject.getpStartDate()));
			}
			if(returnObject.getpEndDate()!=null) {
				returnObject.setShowEndDate(simpleDateFormat.format(returnObject.getpEndDate()));
			}
		}
		 return returnObject;
	}
	
}