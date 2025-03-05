package net.syscon.s4.cm.intakeclosure.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.intakeclosure.OcdaltccRepository;
import net.syscon.s4.cm.intakeclosure.OcdaltccService;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CommunityHeaderStatuses;
import net.syscon.s4.im.beans.OffenderBookingAgyLocsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Service;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT2Service;

/**
 * Class OcdaltccServiceImpl
 */
@Service
public class OcdaltccServiceImpl extends BaseBusiness implements OcdaltccService {

	@Autowired
	private OcdaltccRepository ocdaltccRepository;
	
	@Autowired
	private OffenderBookingAgyLocsT1Service offenderBookingAgyLocsT1Service;
	
	@Autowired
	private OffenderBookingAgyLocsT2Service offenderBookingAgyLocsT2Service;
	
	
	
	

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderBookingAgyLocs> offagyExecuteQuery(final OffenderBookingAgyLocs searchRecord) {
		final List<OffenderBookingAgyLocs> returnList = ocdaltccRepository.offagyExecuteQuery(searchRecord);

		for (final OffenderBookingAgyLocs bean : returnList) {
			final String dspDescr = ocdaltccRepository.cgfkchkOffagyAgencyLocatio(bean.getAgyLocId());
			bean.setAgyLocDescription(dspDescr);

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFAGY
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offagyCommit(final OffenderBookingAgyLocsCommitBean commitBean) {
		int liReturn = 0;

		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderBookingAgyLocs obj :commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());

			}
			liReturn = offagyInsertOffenderBookingAgyLocs(commitBean.getInsertList());
			for (OffenderBookingAgyLocs obj :commitBean.getInsertList()) {
				offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(obj, "INSERTING");
				offenderBookingAgyLocsT2Service.offenderBookingAgyLocsT2Triger(obj);
			}
			return liReturn;
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderBookingAgyLocs obj :commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdaltccRepository.offagyUpdateOffenderBookingAgyLocs(commitBean.getUpdateList());
			for (OffenderBookingAgyLocs obj :commitBean.getUpdateList()) {
				offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(obj, "UPDATING");
			}
		}
		return liReturn;
	}

	private Integer offagyInsertOffenderBookingAgyLocs(final List<OffenderBookingAgyLocs> insertList) {
		Integer listrtn = 0;
		final List<OffenderBookingAgyLocs> requestData = new ArrayList<OffenderBookingAgyLocs>();
		for (final OffenderBookingAgyLocs bean : insertList) {
			requestData.clear();
			final Long eventSeq = ocdaltccRepository.eventSeq(bean.getOffenderBookId());
			bean.setEventSeq(eventSeq);
			bean.setCreateUserId(insertList.get(0).getCreateUserId());
			requestData.add(bean);
			//adding triggers
			final Integer listRturn = ocdaltccRepository.offagyInsertOffenderBookingAgyLocsEvents(requestData);
			for(OffenderBookingAgyLocs bean1:requestData) {
			listrtn = ocdaltccRepository.offagyInsertOffenderBookingAgyLocs(bean1);
			}

		}
		return listrtn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<CommunityHeaderStatuses> offenderStatusRecordGroup() {
		return ocdaltccRepository.offenderStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> cgfkOffagy1DspDescription22RecordGroup(final Long offenderBookId) {
		final List<AgencyLocations> returnLlist = ocdaltccRepository
				.cgfkOffagy1DspDescription22RecordGroup(offenderBookId);
		returnLlist.forEach(element -> {
			if ("N".equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return returnLlist;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup() {
		return ocdaltccRepository.cgfkOffagy1DspDescriptionRecordGroup();

	}

	public OffenderBookingEvent evntDate(final OffenderBookingAgyLocs searchBean) {
		return ocdaltccRepository.evntDate(searchBean);
	}

	public List<OffenderBookingAgyLocs> offagy1ExecuteQuery(OffenderBookingAgyLocs obj) {
		return ocdaltccRepository.offagy1ExecuteQuery(obj);
	}

}