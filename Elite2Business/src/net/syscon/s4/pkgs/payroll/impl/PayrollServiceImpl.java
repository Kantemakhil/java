package net.syscon.s4.pkgs.payroll.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.pkgs.payroll.PayrollRepository;
import net.syscon.s4.pkgs.payroll.PayrollService;

/*
 * Below comments are copied from package Payroll
 * -- Version: 4.6.0.1
-- This Version contains NJ"s NEW Payroll Logic.
--
-- To modify this template, edit file PKGBODY.TXT in TEMPLATE
-- directory of SQL Navigator
--
-- Purpose: Generate records in the Offender_works table according to the assignments in
--          the work_assignments table
--

-- MODIFICATION HISTORY
-- Person      Date         Version     Comments
-- ---------   -----------  ---------   ------------------------------------------
-- Simon Tam   Aug 18,1997
--
-- Edwin Ip    25-JAN-2000              Modified GENERATE_OFFENDER_WORKS Procedure for NJDOC Payroll Enhancement.
--                                      - For the same Unconfirmed work assignment having the same Pay Range Code,
--                                      - Compensation Code and Performance Codes, just update the end date and
--                                      - number of units instead of inserting a new record.
-- Edwin Ip    26-JAN-2000              Modified GENERATE_OFFENDER_WORKS.
--                                      - Populates LAST_NAME and FIRST_NAME columns in OFFENDER_WORKS table upon
--                                      - inserting a new record.
-- Edwin Ip    31-JAN-2000              Modified to update only the offender works records for the same month.
--                                      Insert a new record for the new month.
-- Edwin Ip    03-FEB-2000              Added an IF condition before updating offender works records so that payroll
--                                      that has already been genereated for the day will not be generated again.
-- Joe Wong    07-APR-2000  4.6.0.1     Modified GENERATE_OFFENDER_WORKS. Payroll is only processed under INST caseload.
--                                      Also fixed for Single Bookings.
-- Herbert L   30-AUG-2000  4.9.0.0     vcp_version variable and SHOW_VERSION procedure added
-- Herbert L   27-NOV-2000  4.11.0.0    Minnesota Enhancement : Generate_Offender_Works modified
--                                      If the processing date is lying within an existing payroll period then update
--                                      the record in offender_works table. Otherwise inse77rt a new record in
--                                      Offender_Works table
-- Herbert L   01-DEC-2000  4.11.0.1    Generate_Offender_Works and Get_Next_Date fixed: exception handled
-- Herbert L   03-DEC-2000  4.11.0.2    Generate_Offender_Works modified:
--                                      Frequency logic can be used only after checking the frequency related fields in
--                                      work assignment is properly set up
-- Herbert L   13-DEC-2000  4.11.0.3    Generate_Offender_Works modified:
--                                      When checking if the input date has got a duplicate record for the specific
--                                      work code, check if the input date falls between payroll start date and payroll
--                                      end date instead of just comparing if input date = payroll start date
-- Herbert L   18-DEC-2000  4.11.0.4    Generate_Offender_Works modified:
--                                      When comparing the start time of the work assignment, only compare the time
--                                      portion
-- Herbert L   19-DEC-2000  4.11.0.5    New procedure Generate_Offender_Works added:
--                                      A variant of Generate_Offender_Works, which requires Work Code as one of its
--                                      parameter, is added
-- Abu         03-May-2001  4.11.0.6    Fixed bug in FUNCTION WEEK_NO_IN_CYCLE which was returning 0 value as week no.
-- Abu         16-Jan-2002  4.12.0.0    #10394: Add parameter p_movement_date to UPDATE_WORK_ASGN_STATUSES.
-- Abu         17-Jan-2002  6.1.0.0     #10394: Update work_assignments.last_status_modify_date.
-- Abu         13-Jun-2002  6.1.0.1     Bug fix on retrieval of work assignment status using max update_date.
-- Abu         23-jan-2003  1.1         #12733: modified Update_pay_range_code to correctly compute pay_range_code
--                                         and review date.
-- abu         12-aug-2003  1.2         #16811: Modified Update_pay_range_code for correctly populating next_review_date
--                                         for minnesota.
-- abu         12-aug-2003  1.3         #16811: Modified Update_pay_range_code for retrieving escalation days. If
--                                         escalation days is null then it is assumed to be zero.
-- abu         22-aug-2003  1.4         #16962: Modified query for checking last work_assignment_status if there were
--                                         several status changes on a particular day. The bug was that if there were
--                                         multiple changes within the same minute then it was picking up the earliest
--                                         status within that minute when it should be selecting the last status within
--                                         that minute.
-- Joe       01/20-21/2004  1.5         Tr#18412.  Payroll package codes cleanup. This clean up will not affect any modules or dbms_jobs.
                                        For detail changes, please go to specific procedures.
                                        -- Modified the following procedures:
                                           * GET_NEXT_DATE
                                           * GENERATE_OFFENDER_WORKS (lot of clean up and combined with the other Generate_Offender_Works that with work_code parameter)
                                           * GENERATE_BATCH
                                           *
                                        -- Deleted the following procedures:
                                           * GENERATE_OFFENDER_WORKS (the variant one)
                                           * GENERATE_FRESH_OFFENDER_WORKS
                                           * CLOCK_IN_OUT
                                           * CLOCK_OUT_REMAINS
                                           * COMPUTE_PAYROLL_AMOUNT
                                           * GENERATE_PAYROLL_SUMMARIES

-- Patrick    27-JAN-2004   1.5 (cont)  TR#18412.  Improved code after review.
                                        Modified:
                                          GET_NEXT_DATE - for fixing a bug in TWICEMONTHLY logic
                                          GENERATE_OFFENDER_WORKS - for making code simplier and added exception handlers for the loops
                                                                    too avoid stopping of the loops if exception occurs.
-- Vipul       20-AUG-2004  1.6         TR#20483: Modified the update_work_asgn_statuses procedure to update the deactivation comment
--                                      along with the actual movement_date and added call to process_work_assignment.process_off_works
--                                      procedure to process the offender_works records for a backdated release.
-- Girish      27-SEP-2004  1.7         TR# 20710 : Modified the procedure update_work_asgn_statuses to store the right date of inactivation
                                        based on the parameter passed.
-- Abu         10-Jan-2005  1.8         #21243: Removed reference to V_Payroll_Header while inserting record into
--                                         Offender_Works table.
-- Niko        28-FEB-2007  10.2.1.0      Modified the insert into TIMER_EVENTS table due to datamodel different
--Himanshu    17-Jul-2012   10.2.1.1  HPQC#3728 Added function Show version and commented procedure show version
--

*/
@Service
public class PayrollServiceImpl implements PayrollService {

	@Autowired
	PayrollRepository payrollRepository;

	// This PROCEDURE update_work_asgn_statuses is migrated from oracle
	// This method is used to insert workAssignmentStatusesInsert table
	@Transactional
	@Override
	public void updateWorkAsgnStatuses(final BigDecimal offId, final String csldId, final Date pMovementDate,
			final String userName) {
		// BigDecimal dwrkAsgnId;
		BigDecimal nextStsSeq;
		// This method is used to GET_WORK_ASSIGNMENT_ID list
		final List<BigDecimal> WorkAssignmentIdList = payrollRepository.getWorkAssignmentIdList(offId, csldId);
		if (Objects.nonNull(WorkAssignmentIdList) && !WorkAssignmentIdList.isEmpty()) {
			for (final BigDecimal dwrkAsgnId : WorkAssignmentIdList) {
				// This method to GET_WORK_ASSIGNMENT_STATUSES_SEQ
				nextStsSeq = new BigDecimal(payrollRepository.getWorkAssignmentStatusesSeq(dwrkAsgnId));
				// This method to Insert WORK_ASSIGNMENT_STATUSES
				payrollRepository.workAssignmentStatusesInsert(dwrkAsgnId, nextStsSeq, pMovementDate, userName);
			}
		}

		// PROCESS_WORK_ASSIGNMENT.PROCESS_OFF_WORKS (
		// OFF_ID,
		// CSLD_ID,
		// P_MOVEMENT_DATE
		// );
		// This method to updateWorkAssignments
		payrollRepository.updateWorkAssignments(offId, csldId, pMovementDate, userName);

	}

}