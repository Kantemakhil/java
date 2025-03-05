package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcuscupsRepository;
import net.syscon.s4.cm.programsservices.OcuscupsService;
import net.syscon.s4.cm.programsservices.OffenderCourseAttendancesCommitBean;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;

/**
 * Class OcuscupsServiceImpl
 */
@Service
public class OcuscupsServiceImpl extends BaseBusiness implements OcuscupsService {

	@Autowired
	private OcuscupsRepository ocuscupsRepository;
	
	@Autowired
	private TagProgrammesService tagProgrammes ;
	
	private static Logger logger = LogManager.getLogger(OcuscupsServiceImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderCourseAttendance> offCrsAttendExecuteQuery(final OffenderCourseAttendance searchRecord) {
		final List<OffenderCourseAttendance> offcoursAttList = ocuscupsRepository
				.offCrsAttendExecuteQuery(searchRecord);
		for (final OffenderCourseAttendance offCourseAtt : offcoursAttList) {
			final Offenders details = ocuscupsRepository.getOffenderDetails(offCourseAtt);
			offCourseAtt.setOffenderName( details.getLastName() + ", " +details.getFirstName());
			offCourseAtt.setOffenderIdDisplay(details.getOffenderIdDisplay());
		}
		return offcoursAttList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CRS_ATTEND
	 *
	 */
	@Transactional
	public Integer offCrsAttendCommit(final OffenderCourseAttendancesCommitBean commitBean) {
		Integer liReturn = 0;
		
		if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderCourseAttendance bean:commitBean.getUpdateList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				try {	
             final BigDecimal crschid =tagProgrammes.insCourseSchedulesCatchup(bean);
				bean.setCrsSchId(crschid);
				liReturn =tagProgrammes.insOffCrsAttCatchup(bean);
				}catch(Exception e) {
					logger.error("offCrsAttendCommit:", e);
				}
			}
		}
		return liReturn;
	}

}