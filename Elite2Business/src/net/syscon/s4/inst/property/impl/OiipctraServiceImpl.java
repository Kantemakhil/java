package net.syscon.s4.inst.property.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.OcdofaccRepository;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.property.OiipctraRepository;
import net.syscon.s4.inst.property.OiipctraService;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * Class OiipctraServiceImpl
 */
@Service
public class OiipctraServiceImpl extends BaseBusiness implements OiipctraService {

	@Autowired
	private OiipctraRepository oiipctraRepository;
	@Autowired
	private OcdofaccRepository ocdofaccRepository;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiipctraServiceImpl.class.getName());

	/**
	 * Creates new OiipctraServiceImpl class Object
	 */
	public OiipctraServiceImpl() {
		// OiipctraServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(final OffenderPptyContainers paramBean) {
		List<OffenderPptyContainers> offenderPptyContainersList;
		offenderPptyContainersList = oiipctraRepository.vPheadOnCheckDeleteMaster(paramBean);
		return offenderPptyContainersList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations offConPostQuery(final AgencyLocations paramBean) {
		AgencyLocations agencyLocationsList;
		agencyLocationsList = oiipctraRepository.offConPostQuery(paramBean);
		return agencyLocationsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyLocations> conTxPostQuery(final AgencyLocations paramBean) {
		List<AgencyLocations> agencyLocationsList;
		agencyLocationsList = oiipctraRepository.conTxPostQuery(paramBean);
		return agencyLocationsList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers searchRecord) {
		List<OffenderPptyContainers> offPpptyConList = new ArrayList<>();
		AgencyInternalLocations agencyObj = new AgencyInternalLocations();
		AgencyLocations agenLocObj = new AgencyLocations();
		offPpptyConList = oiipctraRepository.offConExecuteQuery(searchRecord);
		for (final OffenderPptyContainers offPpptyConDetails : offPpptyConList) {
			agencyObj.setInternalLocationId((offPpptyConDetails.getInternalLocationId() == null) ? 0
					: offPpptyConDetails.getInternalLocationId());
			agencyObj = oiipctraRepository.cgfkchkOffConOffConPpty(agencyObj);
			offPpptyConDetails.setDescription(agencyObj.getDescription());
			agenLocObj.setAgyLocId((offPpptyConDetails.getAgyLocId()));
			agenLocObj = oiipctraRepository.offConPostQuery(agenLocObj);
			offPpptyConDetails.setAgyLocId(agenLocObj.getCode());
		}
		return offPpptyConList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderPptyConTxns> conTxExecuteQuery(final OffenderPptyConTxns searchRecord) {
		List<OffenderPptyConTxns> offPptyConTxnsObj = new ArrayList<>();
		AgencyLocations agenLocObj = new AgencyLocations();
		offPptyConTxnsObj = oiipctraRepository.conTxExecuteQuery(searchRecord);
		for (final OffenderPptyConTxns offPpptyConTxnsDetails : offPptyConTxnsObj) {
			agenLocObj.setAgyLocId(offPpptyConTxnsDetails.getAgyLocId());
			agenLocObj = oiipctraRepository.offConPostQuery(agenLocObj);
			offPpptyConTxnsDetails.setAgyLocId(agenLocObj.getCode());
			agenLocObj.setAgyLocId(offPpptyConTxnsDetails.getTrnToAgyLocId());
			agenLocObj = oiipctraRepository.offConPostQuery(agenLocObj);
			offPpptyConTxnsDetails
					.setTrnToAgyLocId((agenLocObj.getCode() == null) ? null : agenLocObj.getCode().toUpperCase());
			agenLocObj.setAgyLocId(offPpptyConTxnsDetails.getTrnFromAgyLocId());
			agenLocObj = oiipctraRepository.offConPostQuery(agenLocObj);
			offPpptyConTxnsDetails
					.setTrnFromAgyLocId((agenLocObj.getCode() == null) ? null : agenLocObj.getCode().toUpperCase());
			if(offPpptyConTxnsDetails.getActionCode()!=null) {
				if(offPpptyConTxnsDetails.getActionCode().equals("REJECT")) {
					offPpptyConTxnsDetails.setActionReason(ocdofaccRepository.getDescription(offPpptyConTxnsDetails.getActionReason(), "P_TRN_REJ"));
				}
				if(offPpptyConTxnsDetails.getActionCode().equals("CANCEL")) {
					offPpptyConTxnsDetails.setActionReason(ocdofaccRepository.getDescription(offPpptyConTxnsDetails.getActionReason(), "P_TRN_CAN"));
				}
			}
		
		
		}
		return offPptyConTxnsObj;
	}
	
	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyInternalLocations> rgLocationAllRecordGroup(final String caseloadId) {
		List<AgencyInternalLocations> returnList;
		returnList = oiipctraRepository.rgLocationAllRecordGroup(caseloadId);
		return returnList;

	}

}