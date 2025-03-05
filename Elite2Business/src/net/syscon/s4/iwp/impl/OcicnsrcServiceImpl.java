package net.syscon.s4.iwp.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.iwp.OcicnsrcRepository;
import net.syscon.s4.iwp.OcicnsrcService;
import net.syscon.s4.pkgs.oms_utils.impl.OmsUtilsServiceImpl;

@Service
public class OcicnsrcServiceImpl implements OcicnsrcService {

	@Autowired
	private OcicnsrcRepository ocicnsrcRepository;

	@Autowired
	private OmsUtilsServiceImpl omsUtilsServiceImpl;

	private static Logger logger = LogManager.getLogger(OcicnsrcServiceImpl.class.getName());

	@Override
	public List<ReferenceCodes> getAllStaffNamesByCaseload(String caseload) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = ocicnsrcRepository.getAllStaffNamesByCaseload(caseload);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(this.getClass().getName() + " error in getAllStaffNamesByCaseload " + e);
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> getAllFacilities(String caseload) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = ocicnsrcRepository.getAllFacilities(caseload);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(this.getClass().getName() + " error in getAllFacilities " + e);
		}
		return returnList;
	}

	@Override
	public List<OffenderCaseNotes> casenoteexecuteQuery(OffenderCaseNotes offenderCaseNotes) {
		List<OffenderCaseNotes> returnList = new ArrayList<OffenderCaseNotes>();
		try {
			returnList = ocicnsrcRepository.casenoteexecuteQuery(offenderCaseNotes);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(this.getClass().getName() + " error in casenoteexecuteQuery " + e);
		}
		return returnList;
	}

	@Override
	public Integer getStaffId(String username) {
		try {
			return omsUtilsServiceImpl.getStaffId(username);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getStaffId " + e);
			return null;

		}
	}

	@Override
	public Boolean checkPermisionForLov(String username) {
		return ocicnsrcRepository.checkPermisionForLov(username);
	}

}
