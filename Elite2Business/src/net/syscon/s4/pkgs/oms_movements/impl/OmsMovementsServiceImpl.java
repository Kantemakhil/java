package net.syscon.s4.pkgs.oms_movements.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.pkgs.oms_movements.OmsMovementsRepository;
import net.syscon.s4.pkgs.oms_movements.OmsMovementsService;

/*
 * Below comments are copied from package oms_movements
 * -- Version     : 4.3.0.1
  -- Description : Gets last from city from offender movements.
  -- Change History
  -- Date       author     remarks
  -- 18/12/99   David Ng   SQL tuning
  --
  -- ==========================================================================================
  vcp_version CONSTANT VARCHAR2(60) := '2.11 05-DEC-2011';

  -- ============================================================================================
  -- MODIFICATION HISTORY
  -- Person        DATE         Version   Comments
  -- -----------   ----------   --------  ------------------------------------------------------
  -- Bahadur       05-Dec-2011      2.11  HPQC:12007 Modified FUNCTION get_next_direction_code, get_last_to_agy_loc_id  
  --                                      get_last_movement_reason_code, get_last_from_city, get_last_to_city  
  --                                      get_last_movement_code to overcome the When_No_Data found error.
  -- Steve         06-Sep-2011      2.10  HPQC:10176 Modified FUNCTION get_next_direction_code to retrieve only one record.
  --                                      Replaced SHOW_VERSION procedure with Function.
  -- Johnny        29-Sept-2008     2.9   Added trunc to all the date comparision.
  -- Rajshree      11-Sept-2006     2.8   #4397.Added get_to_address,to get the last address.
  -- Erin          03-Aug-2006      2.7   #3381: Peer review changes
  -- Erin          27-Jul-2006      2.6   #3381: Minor modifications to PROCEDURE check_for_warnings
  --                                      #3262: Changed format of returned value in  FUNCTION get_last_movement_datetime
  -- Erin          21-Jul-2006      2.5   #3381: Added FUNCTIONS check_active_sentence, get_offender_name, get_alert_details,
  --                                             disp_mov_reas, release_date_check and PROCEDURE check_for_warnings
  -- Rajshree      12-JUN-2006      2.4   Modified message ,when no data found exception is raised
  --                                      in get_next_direction_code function.Defect Id 2604
  -- Rajshree      20-APR-2006      2.3   Modified Function 'does_another_schedule_exist'  for schedule data model change.
  -- Rajshree      21-Feb-2006      2.2   Removed time portion from movement date in get_last_movement_date
  --                                      as some movement dates have time portion and some don't ,and cursur was
  --                                      looking into time portion,which was raising the error of NO_DATA_FOUND.
  --                                      Defect Id 680.
  --
  -- SURYA         09-AUG-00    4.9.0.0   Added the Procedure SHOW_VERSION and Modified the
  --                                      Modification History
  -- Krishna       09-Feb-2006    2.1     Added TRUNC to match the date checking in get_last_from_agy_loc_id func.
  --
  -- ================================================================================================
*/
@Service
public class OmsMovementsServiceImpl implements OmsMovementsService {
	@Autowired
	private OmsMovementsRepository omsMovementsRepository;

	@Override
	public String getLastToAgyLocId(final Integer pBookingId) {
		return omsMovementsRepository.getLastToAgyLocId(pBookingId);
	}

	@Override
	public String getLastToCity(final Integer pBookingId) {
		return omsMovementsRepository.getLastToCity(pBookingId);
	}

	@Override
	public String getNextDirectionCode(final Integer pBookingId) {
		String vDirection = omsMovementsRepository.getvDirection(pBookingId);
		return omsMovementsRepository.getNextDirectionCode(vDirection);
	}

	@Override
	public String getLastMovementReasonCode(final Integer pBookingId) {
		return omsMovementsRepository.getLastMovementReasonCode(pBookingId);
	}

	@Override
	public String getLastFromAgyLocId(final Integer pBookingId) {
		return omsMovementsRepository.getLastFromAgyLocId(pBookingId);
	}

	@Override
	public String getLastMovementCode(final Integer offBookId) {
		return omsMovementsRepository.getLastMovementCode(offBookId);
	}

	@Override
	public String getLastFromCity(final Integer offBookId) {
		return omsMovementsRepository.getLastFromCity(offBookId);
	}

	@Override
	public Integer checkActiveSentence(final OffenderExternalMovements searchdao) {
		return omsMovementsRepository.checkActiveSentence(searchdao.getOffenderBookId());
	}

//This PROCEDURE is used to release_date_check
	@Override
	public OffenderExternalMovements releaseDateCheck(final OffenderExternalMovements param) {
		// This PROCEDURE is used to release_date_check
		final List<Object[]> list = omsMovementsRepository.checkReleaseDateCur(param.getOffenderBookId());
		for (final Object[] obj : list) {
			Date off = param.getReportingDate();
			off = (Date) obj[0];
			String mov = param.getMovementReasonCode();
			mov = (String) obj[1];
		}
		return new OffenderExternalMovements();
	}

	@Override
	public String getToAddress(Integer pBookingId) {
		return omsMovementsRepository.getToAddress(pBookingId);
	}
}