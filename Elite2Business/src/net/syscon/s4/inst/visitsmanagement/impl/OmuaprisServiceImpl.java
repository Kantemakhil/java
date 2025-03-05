package net.syscon.s4.inst.visitsmanagement.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.visitsmanagement.OmuaprisRepository;
import net.syscon.s4.inst.visitsmanagement.OmuaprisService;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitorsCommitBean;
import net.syscon.s4.pkgs.osinames.OsinamesPkgService;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;

/**
 * Class OmuaprisServiceImpl
 */
@Service
public class OmuaprisServiceImpl extends BaseBusiness implements OmuaprisService {

	@Autowired
	private OmuaprisRepository omuaprisRepos;
	@Autowired
	private EliteDateRepository dateRepository;
	@Autowired
	private OsinamesPkgService osinamesPkgService;
	@Autowired
	private TagVisitsService tagVisitsService;

	/**
	 * Creates new OmuaprisServiceImpl class Object
	 */
	public OmuaprisServiceImpl() {
		// OmuaprisServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderAuthorisedVisitors> vOffAuthVisExecuteQuery(final VOffenderAuthorisedVisitors searchRecord) {
		final List<VOffenderAuthorisedVisitors> returnList = omuaprisRepos.vOffAuthVisExecuteQuery(searchRecord);
		VOffenderAuthorisedVisitors bean = new VOffenderAuthorisedVisitors();
		for (final VOffenderAuthorisedVisitors obj : returnList) {
			bean.setContactRootOffenderId(obj.getContactRootOffenderId());
			bean.setVisitDate(dateRepository.getDBTime());
		//	bean = omuaprisRepos.getContactOffenderDetails(bean);
			bean = tagVisitsService.getConatactOffenderDetails(BigDecimal.valueOf(bean.getContactRootOffenderId()), bean.getVisitDate(),searchRecord.getCreateUserId());

			if (bean.getLocation() != null) {
				obj.setLocation(bean.getLocation());
			}
			if (bean.getVisitorFirstName() != null) {
				obj.setVisitorFirstName(bean.getVisitorFirstName());;
			}
			if (bean.getVisitorLastName() != null) {
				obj.setVisitorLastName(bean.getVisitorLastName());
			}
			if (bean.getRestriction() != null) {
				obj.setRestriction(bean.getRestriction());
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public Integer vOffAuthVisCommit(final VOffenderAuthorisedVisitorsCommitBean commitBean) {
		Integer liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = omuaprisRepos.vOffAuthVisUpdateVOffenderAuthorisedVisitors(commitBean.getUpdateList());
		}
		return liReturn;
	}
	public List<OffenderRestrictions> getOffenderRestrcitions(final String vstOffIdDisplay, final Date visitdDate, String userId) {
		//final BigDecimal offenderBookId = omuaprisRepos.getOffenderBookId(vstOffIdDisplay);
		final BigDecimal offenderBookId = osinamesPkgService.getOffBookId(vstOffIdDisplay,userId);

		final List<OffenderRestrictions> returnList =  omuaprisRepos.getOffenderRestrcitions(Integer.valueOf(offenderBookId.toString()),visitdDate);
		for(final OffenderRestrictions obj:returnList){
			obj.setOffenderBookId(offenderBookId);
			
		}
		return returnList;
		
	}
	public BigDecimal getOffenderBookId(final String vstOffIdDisplay, final String userId) {
	//	return omuaprisRepos.getOffenderBookId(vstOffIdDisplay);
		return osinamesPkgService.getOffBookId(vstOffIdDisplay, userId);

		
	}
	

}