package net.syscon.s4.inst.legalscreens.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legalscreens.OcdclistRepository;
import net.syscon.s4.inst.legalscreens.OcdclistService;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQuery;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQueryCommitBean;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;
import net.syscon.s4.inst.legalscreens.bean.VCourtEventscommitBean;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;
import net.syscon.s4.pkgs.ocdclist.OcdclistPkgService;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleService;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgService;

/**
 * Class OcdclistServiceImpl
 */
@Service
public class OcdclistServiceImpl extends BaseBusiness implements OcdclistService {

	@Autowired
	private OcdclistRepository ocdclistRepository;
	
	@Autowired
	private OcdclistPkgService ocdclistPkgService;
	
	@Autowired
	private OffCourtEventVineIntfTrgService offCourtEventVineIntfTrgService;
	
	@Autowired
	
	private TagScheduleService tagScheduleService;
	
	
	

	/**
	 * Creates new OcdclistServiceImpl class Object
	 */
	public OcdclistServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<VCourtEvents> ctlLstOnCheckDeleteMaster(final VCourtEvents paramBean) {
		final List<VCourtEvents> vCourtEventsList = ocdclistRepository.ctlLstOnCheckDeleteMaster(paramBean);
		return vCourtEventsList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OcdclistCourtListQuery> ctlLstExecuteQuery(final OcdclistCourtListQuery searchRecord) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(searchRecord.getpCourtDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		searchRecord.setpCourtDate(cal.getTime());
		return ocdclistPkgService.courtListQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCTL_LST
	 *
	 * @
	 */
	@Transactional
	public Integer ctlLstCommit(final OcdclistCourtListQueryCommitBean commitBean) {
		int liReturn = 0;
		Integer value = null;
		final String operation ="UPDATING";
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OcdclistCourtListQuery obj : commitBean.getInsertList()) {
				value = tagScheduleService.checkScheduleConflict(obj.getpOffBkgId().longValue(), obj.getpEventDate(), obj.getpEventId().intValue());
			}
			if (value > 0) {
				liReturn = ocdclistRepository.ctlLstInsertOcdclistCourtListQuery(commitBean.getInsertList());
			} else {
				return 2;
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdclistRepository.ctlLstUpdateOcdclistCourtListQuery(commitBean.getUpdateList());
			for (final OcdclistCourtListQuery obj : commitBean.getUpdateList()) {
				CourtEvents ce = new CourtEvents();
				ce.setEventDate(obj.getpEventDate());
				ce.setNbtLastName(obj.getpLastName());
				ce.setNbtFirstName(obj.getpFirstName());
				ce.setNbtOffenderIdDisplay(obj.getpOffDisplay());
				ce.setCourtEventType(obj.getpCourtEventType());
				ce.setCourtEventDesc(obj.getpCourtEventTypeDesc());
				ce.setStartTime(obj.getpStartTime());
				ce.setAgyLocId(obj.getpAgyLocId());
				ce.setOffenderBookId(obj.getpOffBkgId());
				ce.setEventId(obj.getpEventId());
				ce.setEventStatus(obj.getpEventStatus());
				ce.setModifyUserId(commitBean.getCreateUserId());
			offCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(ce, operation);
			}
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
	public List<VCourtEvents> ctlUnExecuteQuery(final VCourtEvents searchRecord) {
		return ocdclistRepository.ctlUnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCTL_UN
	 *
	 * @
	 */
	@Transactional
	public Integer ctlUnCommit(final VCourtEventscommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup() {
		return ocdclistRepository.rgAgyLocRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgHearingRecordGroup() {
		final List<ReferenceCodes> returnList = ocdclistRepository.rgHearingRecordGroup();
		for (final ReferenceCodes obj : returnList) {
			if (obj.getSeqValue() == 1) {
				obj.setCanDisplay(true);
			} else {
				obj.setCanDisplay(false);
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCTL_LST
	 *
	 * @
	 */
	@Transactional
	public Integer ctlLstCommitQuery(final CourtEventsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = ocdclistRepository.ctlLstUpdateCourtListQuery(commitBean.getUpdateList());
		}
		return liReturn;
	}

}