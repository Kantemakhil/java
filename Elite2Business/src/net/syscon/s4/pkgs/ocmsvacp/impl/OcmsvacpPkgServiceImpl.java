package net.syscon.s4.pkgs.ocmsvacp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.pkgs.ocmsvacp.OcmsvacpPkgRepository;
import net.syscon.s4.pkgs.ocmsvacp.OcmsvacpPkgService;
import net.syscon.s4.triggers.CourseActivitiesT1Service;
import net.syscon.s4.triggers.CourseActivitiesT2Service;
import net.syscon.s4.triggers.CourseActivitiesT3Service;
import net.syscon.s4.triggers.VCourseModules;
import net.syscon.s4.triggers.VCourseModulesTiService;
import net.syscon.s4.triggers.VProgramModules;
/*  Below comments are copied from get_default_address function
 * --
-- Purpose: Used by online module OCMSVACP
--
-- MODIFICATION HISTORY
-- Person     Version  Date         Comments
-- ---------  -------  -----------  --------------------------------------------------------------
--  Krishna       2.20 06-Jul-2007  #6928: SQL change in default_team proc; replaced staff_members with staff_user_accounts for username
--  Rajshree      2.19 10-May-2007  #6709: Added parameter p_func_type in default team so as to
--                                  utilize it more than one place.
--  Erin          2.18 26-Mar-2007  #6306: Modified code in function get_sch_date
--  Rajshree      2.17 12-Mar-2007  Added function get_sch_date.#6306
--  Johnson       2.16 01-Nov-2006  Removed Functions - delete_course_activities,is_course_schedule_exists
--  Johnson       2.15 25-Oct-2006  Added Functions - delete_course_activities,is_course_schedule_exists
--  GJC           2.14 14 Oct 2006  SHOW_VERSION changed from procedure to function
--  Johnson       2.13 07-Sep-2006  Tr#4504 -- Renamed deactivate_acc_program to update_acc_program and
--                                  modification made to the function.
--  Johnson       2.12 05-Sep-2006  Removed the function is_offender_ended.
--                                  Minor modification done in is_offender_exists, get_max_enddate
--  Johnson       2.11 30-Aug-2006  Assinged a new version label -- B003488 (common version lable for ACP3 Development).
--  Johnson       2.10 29-Aug-2006  Added the following functions
--                                  is_offender_exists, get_max_enddate, is_offender_ended.
--  Johnson       2.9  15-Aug-2006  Tr#3960 -- Modified execption handling on default_team.
--  Neil          2.8  09-Aug-2006  D#3411 ACP Iteration 2. Added services_address_id
--                                  and internal_location_id to create_course_modules.
--  Claus         2.6  08-Aug-2006  Added default_team.
--  Krishna       2.5  01-Aug-2006  Added default parameter p_category to check_code_exists function
--  Neil          2.4  26-JUL-2006  D#3570 Added insert of no_of_sessions and session_length
--                                  to create_course_modules.
--  GJC           2.3  23-JAN-2006  Defect 288
--  GJC           2.2  18-JAN-2006  Add check_code_exists
--  Patrick       2.1  10-JAN-2006  Fixed bugs after peer review.
--  Patrick       2.0  04-JAN-2006  Initial Version
--
 */
@Service
public class OcmsvacpPkgServiceImpl implements OcmsvacpPkgService {

	@Autowired
	private OcmsvacpPkgRepository ocmsvacpRepository;

	@Autowired
	private CourseActivitiesT1Service courseActivitiesT1Service;

	@Autowired
	private CourseActivitiesT2Service courseActivitiesT2Service;

	@Autowired
	private CourseActivitiesT3Service courseActivitiesT3Service;
	
	@Autowired
	private VCourseModulesTiService vCourseModulesTiService;

	@Override
	public String getAccProgram(final BigDecimal programId) {
		return ocmsvacpRepository.getAccProgram(programId);
	}
   //This function gets the default address of a provider.  Either an agency or a corporate.
	public VAddresses getDefaultaddress(final CourseActivities list) {
		VAddresses addressList = new VAddresses();
		if (list.getProviderPartyCode() != null) {
			addressList = ocmsvacpRepository.ocmsvacpDefaultAddrWAgy(list.getProviderPartyCode());
		} else {
			addressList = ocmsvacpRepository.ocmsvacpDefaultAddrWCorp(list.getProviderPartyId());
		}
		return addressList;

	}

	public Boolean checkCodeExists(final Long providerPartyId, final String providerPartyCode, final String pCode,
			final String programCategory) {
		Long lvCount = ocmsvacpRepository.ocmsvacpGetCodeUniqueCntCur(providerPartyId, providerPartyCode, pCode,
				programCategory);
		if (lvCount > 0) {
			return (true);
		} else {
			return (false);
		}
	}

	@Override
	public Integer createCourseModules(final VCourseModules vCourseModules) {
		List<VProgramModules> courseModules = new ArrayList<VProgramModules>();
		Integer liReturn = 0;
		courseModules = ocmsvacpRepository.getVprogramModulesCrsMod(vCourseModules.getProgramPhaseId());
		for(VProgramModules obj:courseModules){
			VCourseModules insObj = new VCourseModules();
			BeanUtils.copyProperties(vCourseModules, insObj);
			insObj.setProgramModuleId(obj.getProgramModuleId());
			insObj.setListSeq(obj.getListSeq());
			insObj.setNoOfSessions(obj.getNoOfSessions());
			insObj.setSessionLength(obj.getSessionLength());
			insObj.setCourseModuleId(obj.getCrsActyId());
			insObj.setScheduleStartDate(vCourseModules.getScheduleStartDate());
			liReturn =  vCourseModulesTiService.vCourseModulesTi(insObj);
		}		
		return liReturn;
	}
}
