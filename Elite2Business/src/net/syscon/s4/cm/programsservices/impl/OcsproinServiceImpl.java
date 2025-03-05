package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.programsservices.OcsproinRepository;
import net.syscon.s4.cm.programsservices.OcsproinService;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.pkgs.tag_prg.TagPrgService;

@Service
public class OcsproinServiceImpl extends BaseBusiness implements OcsproinService {

	@Autowired
	private OcsproinRepository ocsproinRepository;
	
	
	@Autowired
	private TagPrgService tagPrgService;

	@Override
	public List<Teams> rgTeamRecordGroup(String user) {
		return ocsproinRepository.rgTeamRecordGroup(user);

	}

	@Override
	public List<CourseActivities> rgProjectsRecordGroup(final String teamId) {

		final List<CourseActivities> courseActivitiesList = ocsproinRepository.rgProjectsRecordGroup(teamId);
		long count = 0;
		if (!courseActivitiesList.isEmpty()) {
			for (final CourseActivities teams : courseActivitiesList) {
				count = count + 1;
				teams.setListSeq(count);
			}
		}
		return courseActivitiesList;
	}

	@Override
	public List<CourseActivities> courseActExecuteQuery(CourseActivities searchBean) {
		CourseActivities[] weekDay = null;
		if (searchBean.getWeekDay() != null && searchBean.getWeekDay().size() > 0) {
			for (String obj : searchBean.getWeekDay()) {
				if ("MON".equals(obj)) {
					searchBean.setMondayFlag("Y");
				} else if ("TUE".equals(obj)) {
					searchBean.setTuesdayFlag("Y");
				} else if ("WED".equals(obj)) {
					searchBean.setWednesdayFlag("Y");
				} else if ("THU".equals(obj)) {
					searchBean.setThursdayFlag("Y");
				} else if ("FRI".equals(obj)) {
					searchBean.setThursdayFlag("Y");
				} else if ("SAT".equals(obj)) {
					searchBean.setSaturdayFlag("Y");
				} else if ("SUN".equals(obj)) {
					searchBean.setSundayFlag("Y");
				}

			}
		}
		List<CourseActivities> returnList = ocsproinRepository.courseActExecuteQuery(searchBean);
		List<CourseActivities> list = new ArrayList<CourseActivities>();
		if (searchBean.getAvlblCapacityFlag().equalsIgnoreCase("Y")) {
			for (CourseActivities obj : returnList) {
				if (obj.getAllocatedOffender() != null && obj.getCapacity() != null) {
					if (obj.getAllocatedOffender() < obj.getCapacity()) {
						list.add(obj);
					}
				}
			}
			return list;
		}
		return returnList;
		

	}

	@Override
	public List<CourseActivities> rgProjectsRgNoTeam() {

		final List<CourseActivities> courseActivitiesList = ocsproinRepository.rgProjectRgNoTeam();
		long count = 0;
		if (!courseActivitiesList.isEmpty()) {
			for (final CourseActivities teams : courseActivitiesList) {
				count = count + 1;
				teams.setListSeq(count);
			}
		}
		return courseActivitiesList;
	}

	@Override
	public List<OffenderBookings> offenderExecQuery(Integer crsActyId) {
		final List<OffenderBookings> returnList = ocsproinRepository.offenderExecQuery(crsActyId);
		
		for (final OffenderBookings offSchDate : returnList) {
			List<String> weekList =ocsproinRepository.schWeekDays(offSchDate.getOffenderBookId(),crsActyId);
			offSchDate.setWeekday(weekList);
			
			
			BigDecimal creditedHours = tagPrgService.creditHours(new BigDecimal(offSchDate.getOffenderBookId()), offSchDate.getSentenceSeq(), offSchDate.getOffenderSentConditionId());
			offSchDate.setCreditedUnits(creditedHours);
			if( offSchDate.getConditionLength()!= null) {
			if( offSchDate.getConditionLength().contains("HOURS")) {
				String str  = offSchDate.getConditionLength().substring(0,offSchDate.getConditionLength().indexOf("H"));
			offSchDate.setLength(new BigDecimal(str));
			}
			}
			
			if(offSchDate.getLength()!=null && offSchDate.getCreditedUnits()!=null) {
				offSchDate.setRemainingHours(offSchDate.getLength().subtract(offSchDate.getCreditedUnits()) );
			}
			
			if (offSchDate.getRemainingHours() != null) {
				String number = offSchDate.getRemainingHours().toString();
				if (number.startsWith("-")) {
					offSchDate.setRemainingHours(BigDecimal.ZERO);
				}
			}
		}
		return returnList;
	}
	
	@Override
	public List<OffenderBookings> refExecuteQuery(CourseActivities searchBean) {
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		try {
			returnList =ocsproinRepository.refExecuteQuery(searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}
}
