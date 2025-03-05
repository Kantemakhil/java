package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentChargesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.inst.incidentsoic.OcuoicchRepository;
import net.syscon.s4.inst.incidentsoic.OcuoicchService;
import net.syscon.s4.triggers.AgencyIncidentChargesT1Service;

/**
 * Class OcuoicchServiceImpl
 */
@Service
public class OcuoicchServiceImpl extends BaseBusiness implements OcuoicchService {

	@Autowired
	private OcuoicchRepository ocuoicchDao;
	
	@Autowired
	private AgencyIncidentChargesT1Service agencyIncidentChargesT1Service;

	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OcuoicchServiceImpl.class.getName());

	/**
	 * Creates new OcuoicchServiceImpl class Object
	 */
	public OcuoicchServiceImpl() {
		// OcuoicchServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OicOffences agyInciChgPostQuery(final OicOffences paramBean) {
		return ocuoicchDao.agyInciChgPostQuery(paramBean);
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<AgencyIncidentCharges> agyInciChgExecuteQuery(final AgencyIncidentCharges searchRecord) {
		List<AgencyIncidentCharges> returnList = new ArrayList<>();
		final OicOffences paramBean = new OicOffences();
		try{
		returnList = ocuoicchDao.agyInciChgExecuteQuery(searchRecord);
		for (final AgencyIncidentCharges obj : returnList) {
			paramBean.setOicOffenceId(obj.getChargedOicOffenceId());
			final OicOffences bean = agyInciChgPostQuery(paramBean);
			obj.setChargedOicOffenceCode(bean.getOicOffenceCode());
			obj.setOffenceDesc(bean.getDescription());
			obj.setOffenceType(bean.getOicOffenceType());
			obj.setDspCategory(bean.getOicOffenceCategory());
		}
		}catch(Exception e){
			logger.error(e);
		}
		return returnList;
	}
    public List<OicHearingResults> oichearingSearchResults(final OicHearingResults objOicHearingResults){
		return  ocuoicchDao.oichearingSearchResults(objOicHearingResults);
    	
    }
	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<OicOffences> rgOffenceCodeRecordGroup(final Date incidentDate) {
		final String mode = "ENTER-QUERY";
		return ocuoicchDao.rgOffenceCodeRecordGroup(mode, incidentDate);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_INCI_CHG
	 *
	 * @
	 */
	@Transactional
	public Integer agyInciChgCommit(final AgencyIncidentChargesCommitBean commitBean) {
		int liReturn = 0;
		Boolean check = true;
		 Integer chargeSequence = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (final AgencyIncidentCharges agyObj : commitBean.getInsertList()) {
					agyObj.setCreateUserId(commitBean.getCreateUserId());
					if (check) {
						check = false;
						  chargeSequence = ocuoicchDao.preInsertgetChargeSeqId(agyObj);
					}
					agyObj.setChargeSeq(chargeSequence);
					chargeSequence++;
					List<AgencyIncidentCharges> dataObj = new ArrayList<AgencyIncidentCharges>();
					dataObj.add(agyObj);
					List<AgencyIncidentCharges> data = agencyIncidentChargesT1Service.agencyIncidentChargesT1Tgr(dataObj);
					for (AgencyIncidentCharges obj : data) {
						if (obj.getLidsChargeNumber() != null) {
							agyObj.setLidsChargeNumber(obj.getLidsChargeNumber());
						}
					}
				} 
				liReturn = ocuoicchDao.agyInciChgInsertAgencyIncidentCharges(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(data->data.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = ocuoicchDao.agyInciChgUpdateAgencyIncidentCharges(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = ocuoicchDao.agyInciChgDeleteAgencyIncidentCharges(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return liReturn;
	}
}