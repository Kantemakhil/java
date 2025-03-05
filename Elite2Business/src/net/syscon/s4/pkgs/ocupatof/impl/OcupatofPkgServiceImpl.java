package net.syscon.s4.pkgs.ocupatof.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.pkgs.ocupatof.OcupatofPkgRepository;
import net.syscon.s4.pkgs.ocupatof.OcupatofPkgService;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfService;
import net.syscon.s4.triggers.impl.OffenderCourseAttendancesT1ServiceImpl;

@Service
public class OcupatofPkgServiceImpl implements OcupatofPkgService {

	@Autowired
	private OcupatofPkgRepository ocupatofPkgRepository;

	@Autowired
	private TagServiceService tagService;

	@Autowired
	private TagReferenceCodesService tagReferenceCodesService;

	@Autowired
	private TagProgrammesService tagProgrammesService;

	@Autowired
	private OffenderCourseAttndnsTwfService offenderCourseAttndnsTwfService;

	@Autowired
	private OffenderCourseAttendancesT1ServiceImpl offenderCourseAttendancesT1ServiceImpl;

	private static final String N = "N";
	private static final String Y = "Y";
	private static final String UPDATING = "UPDATING";
	private static Logger logger = (org.apache.logging.log4j.core.Logger) LogManager
			.getLogger(OcupatofPkgServiceImpl.class.getName());

	@Override
	public OffenderCourseAttendance getOffenderCourseDetails(final OffenderCourseAttendance offCour) {
		final OffenderCourseAttendance bean = new OffenderCourseAttendance();
		String pModule = null;
		String pcatchUp = null;
		String pAttDesc = null;
		try {
			final CourseActivities course = tagService.getCrsDetails(offCour.getCrsActyId().longValue());
			pModule = course.getDescription();
			final CourseSchedules courseSch = tagProgrammesService
					.getCourseScheduleRec(offCour.getCrsSchId().longValue());
			if (courseSch != null && courseSch.getCatchUpCrsSchId() == null) {
				pcatchUp = N;
			} else {
				pcatchUp = Y;
			}
			pAttDesc = tagReferenceCodesService.getDescCode("OUTCOMES", offCour.getEventOutcome());
			offCour.getEventId();
			bean.setModule(pModule);
			bean.setCatchUpFlag(pcatchUp);
			bean.setConfirmAttendance(pAttDesc);
		} catch (Exception e) {
			logger.error("getOffenderCourseDetails", e);
		}
		return bean;
	}

	@Override
	public Integer updateOffCrseAttend(OffenderCourseAttendance offCouAtt) {
		VOffenderCourseAttendances vOffcouAtt = new VOffenderCourseAttendances();
		BeanUtils.copyProperties(offCouAtt, vOffcouAtt);
		offenderCourseAttendancesT1ServiceImpl.offenderCourseAttendancesT1(vOffcouAtt);
		Integer retVal = ocupatofPkgRepository.updateOffCrseAttend(offCouAtt);
		OffenderCourseAttendances newObj = new OffenderCourseAttendances();
		BeanUtils.copyProperties(offCouAtt, newObj);
		offenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(newObj, UPDATING);
		return retVal;
	}

}
