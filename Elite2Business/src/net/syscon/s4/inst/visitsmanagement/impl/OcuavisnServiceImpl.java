package net.syscon.s4.inst.visitsmanagement.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.OcuavisnRepository;
import net.syscon.s4.inst.visitsmanagement.OcuavisnService;
import net.syscon.s4.inst.visitsmanagement.beans.VOffContactPersons;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;
import net.syscon.s4.triggers.OffenderContactPersonsT1Service;

/**
 * Class OcuavisnServiceImpl
 */
@Service
public class OcuavisnServiceImpl extends BaseBusiness implements OcuavisnService {

	@Autowired
	private OcuavisnRepository ocuavisnRepository;
	@Autowired
	private TagVisitsService tagVisitsService;
	@Autowired
	private OffenderContactPersonsT1Service offenderContactPersonsT1Service;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VOffContactPersons> vOffAuthVisExecuteQuery(final VOffContactPersons searchRecord) {
		final List<VOffContactPersons> returnList = ocuavisnRepository.vOffAuthVisExecuteQuery(searchRecord);
		for (final VOffContactPersons vOffContactPersons : returnList) {
			if (vOffContactPersons.getPersonId() != null) {
				Map<String, Object> mapObj  = tagVisitsService.populateVisitorDetails(searchRecord.getOffenderBookId().longValue(), vOffContactPersons.getPersonId().longValue(),
						vOffContactPersons.getOffenderContactPersonId().longValue(), searchRecord.getVisitDate());
				VOffContactPersons beanData = new VOffContactPersons();
				vOffContactPersons.setLastName(mapObj.get("P_LAST_NAME")!=null?mapObj.get("P_LAST_NAME").toString() :null );
				vOffContactPersons.setFirstName(mapObj.get("P_FIRST_NAME") != null ?mapObj.get("P_FIRST_NAME").toString() : null);
				vOffContactPersons.setMiddleName(beanData.getMiddleName());
				final String age =  mapObj.get("P_AGE")!= null ? mapObj.get("P_AGE").toString() :null;
				vOffContactPersons.setAge(age!=null ? new BigDecimal(age) : null);
				vOffContactPersons.setRestriction(
						mapObj.get("P_RESTRICTION") != null ? mapObj.get("P_RESTRICTION").toString() : null);
				vOffContactPersons.setGlobalRestriction(
						mapObj.get("P_GL_RESTRICTION") != null ? mapObj.get("P_GL_RESTRICTION").toString() : null);
				vOffContactPersons.setContactTypeDescription(ocuavisnRepository.getConatctTypeDescription(vOffContactPersons.getContactType()));
				vOffContactPersons.setRelationshipTypeDescription(ocuavisnRepository.getRelationTypeDescription(vOffContactPersons.getRelationshipType()));

			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFF_AUTH_VIS
	 *
	 * @
	 */
	@Transactional
	public Integer vOffAuthVisCommit(final OffenderContactPersonsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderContactPersons obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				offenderContactPersonsT1Service.offenderContactPersonsT1(obj);
			}
			liReturn = vOffAuthVisInsertVOffContactPersons(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderContactPersons obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				offenderContactPersonsT1Service.offenderContactPersonsT1(obj);
			}
			liReturn = vOffAuthVisUpdateVOffContactPersons(commitBean.getUpdateList());
		}
		return liReturn;
	}

	public Integer vOffAuthVisInsertVOffContactPersons(final List<OffenderContactPersons> insertList) {
		// final Integer contactPersonId = ocuavisnRepository.getContactPersonId();
//		for (final OffenderContactPersons obj : insertList) {
//			obj.setOffenderContactPersonId(contactPersonId);
//		}
		return ocuavisnRepository.vOffAuthVisInsertVOffContactPersons(insertList);
	}

	public Integer vOffAuthVisUpdateVOffContactPersons(final List<OffenderContactPersons> insertList) {
		return ocuavisnRepository.vOffAuthVisUpdateVOffContactPersons(insertList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgContactTypeRecordGroup() {

		final List<ReferenceCodes> returnList = ocuavisnRepository.rgContactTypeRecordGroup();
		returnList.forEach(result -> {
			result.setCode(result.getCode());
			result.setDescription(result.getDescription());
		});
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgRelationshipTypeRecordGroup(final String contactType) {
		final List<ReferenceCodes> returnList = ocuavisnRepository.rgRelationshipTypeRecordGroup(contactType);
		returnList.forEach(result -> {
			result.setCode(result.getCode());
			result.setDescription(result.getDescription());
		});
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;

	}

	public List<ReferenceCodes> rgRelationshipTypeTotalRecordGroup() {
		final List<ReferenceCodes> returnList = ocuavisnRepository.rgRelationshipTypeTotalRecordGroup();
		returnList.forEach(result -> {
			result.setCode(result.getCode());
			result.setDescription(result.getDescription());
		});
		return returnList;

	}

	
	
	@Override
	public VOffContactPersons getGlobalRestriction(final Integer personId, final Integer offenderBookId, final Long visitDate) {
		final Date vDate = new Date(visitDate);
		 Integer contactPersonId= 0;
		if(personId!=null){
		 contactPersonId = ocuavisnRepository.getContactPersonId();
		}		
		final Map<String, Object> returnObject = tagVisitsService.populateVisitorDetails(offenderBookId.longValue(),
				personId.longValue(), contactPersonId.longValue(), vDate);
		final VOffContactPersons bean = new VOffContactPersons();
		bean.setLastName((String) returnObject.get("P_LAST_NAME"));
		bean.setFirstName((String) returnObject.get("P_FIRST_NAME"));
		bean.setContactType((String) returnObject.get("P_CONTACT_TYPE"));
		bean.setRelationshipType((String) returnObject.get("P_RELATIONSHIP_TYPE"));
		bean.setRestriction((String) returnObject.get("P_RESTRICTION"));
		bean.setGlobalRestriction((String) returnObject.get("P_GL_RESTRICTION"));
		bean.setAge((BigDecimal) returnObject.get("P_AGE"));
		//return ocuavisnRepository.populateVisitorDetails(offenderBookId ,personId,contactPersonId,vDate);
		return bean;
	}

}