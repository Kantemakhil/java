package net.syscon.s4.inst.visitsmanagement.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.im.beans.VisitorRestrictionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.OcuvwarnRepository;
import net.syscon.s4.inst.visitsmanagement.OcuvwarnService;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictionsCommitBean;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;

/**
 * Class OcuvwarnServiceImpl
 */
@Service
public class OcuvwarnServiceImpl extends BaseBusiness implements OcuvwarnService {

	@Autowired
	private OcuvwarnRepository ocuvwarnRepository;

	@Autowired
	TagVisitsService tagVisitsService;
	
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderRestrictions> offenderRestrictionExecuteQuery(final OffenderRestrictions searchRecord) {
		String warnData = searchRecord.getSealFlag();
		String data = null;
		List<OffenderRestrictions> returnList = new ArrayList<>();
		returnList = ocuvwarnRepository.offenderRestrictionExecuteQuery(searchRecord);
		for (final OffenderRestrictions returnObj : returnList) {
			returnObj
					.setRestrictionDesc(ocuvwarnRepository.getDescCode("VST_RST_TYPE", returnObj.getRestrictionType()));
			data = data + "\n";
			Date date = returnObj.getEffectiveDate();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = dateFormat.format(date);
			data = strDate + "       " + returnObj.getRestrictionDesc();
			returnObj.setSealFlag(data);
		}
		for (final OffenderRestrictions returnObj : returnList) {
			warnData = warnData + "\n" + "\n";
			warnData = warnData + returnObj.getSealFlag();
			returnObj.setSealFlag(warnData);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_RESTRICTION
	 *
	 * @
	 */
	@Transactional
	public Integer offenderRestrictionCommit(final OffenderRestrictionsCommitBean commitBean) {
		Integer liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = ocuvwarnRepository
					.offenderRestrictionInsertTagVisitsGetOffenderRestrictions(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VisitorRestrictions> visitorRestrictionsExecuteQuery(final VisitorRestrictions searchRecord) {
		return ocuvwarnRepository.visitorRestrictionsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstVISITOR_RESTRICTIONS
	 *
	 * @
	 */
	@Transactional
	public Integer visitorRestrictionsCommit(final VisitorRestrictionsCommitBean commitBean) {
		Integer liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = ocuvwarnRepository
					.visitorRestrictionsInsertTagVisitsGetVisitorRestrictions(commitBean.getInsertList());
		}
		return liReturn;
	}

	@Override
	public List<VisitorRestrictions> populateVisitorDetailsExecuteQuery(final OffenderRestrictions searchBean) {
		List<VisitorRestrictions> returnList = new ArrayList<>();
		returnList = ocuvwarnRepository.populateVisitorDetailsExecuteQuery(searchBean);
		for (final VisitorRestrictions returnObj : returnList) {
			returnObj.setVisitRestrictionType(
					ocuvwarnRepository.getDescCode("VST_RST_TYPE", returnObj.getVisitRestrictionType()));
		}
		return returnList;
	}

	@Override
	public Persons getPersonNames(final OffenderRestrictions searchBean) {
		Persons obj = new Persons();
		Map<String, Object> map = tagVisitsService.getPersonNames(searchBean.getPersonId().longValue(), searchBean.getVisitDate());
		obj.setFirstName((String)map.get("p_first_name"));
		obj.setAge(map.get("p_age").toString());
		obj.setLastName((String)map.get("p_last_name"));
		return  obj;
	}

	@Override
	public SystemProfiles getProfileValues(final String profileType, final String profileCode) {
		return ocuvwarnRepository.getProfileValues(profileType, profileCode);
	}

	@Override
	public Offenders getOffenderNames(final Long offenderId) {
		return ocuvwarnRepository.getOffenderNames(offenderId);
	}

}