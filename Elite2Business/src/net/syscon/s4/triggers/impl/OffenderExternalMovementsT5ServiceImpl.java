package net.syscon.s4.triggers.impl;

import java.util.Date;
/*===========================================================================================================
Below comments are copied from OFFENDER_EXTERNAL_MOVEMENTS_T5 Trigger
=============================================================================================================*/

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.triggers.OffenderExternalMovementsT5Service;
@Service
public class OffenderExternalMovementsT5ServiceImpl implements OffenderExternalMovementsT5Service {
	@Autowired
	private OffenderExternalMovementsT5RepositoryImpl offenderExternalMovementsT5Repository;
	@Transactional
	@Override
	public void offenderExternalMovementsT5(OffenderExternalMovements newOffenderExternalMovements) {
		if(Objects.nonNull(newOffenderExternalMovements)) {
			//Below method is used for grtting old OffenderExternalMovements data
			OffenderExternalMovements oldOffenderExternalMovements = offenderExternalMovementsT5Repository.getOffenderExternalMovements(newOffenderExternalMovements.getOffenderBookId(),newOffenderExternalMovements.getMovementSeq());
			if(Objects.isNull(newOffenderExternalMovements.getSealFlag()) || StringUtils.equals(oldOffenderExternalMovements.getSealFlag(), newOffenderExternalMovements.getSealFlag())) {
				String lCheckExistFlag = "N";
				 Long offenderbookid = newOffenderExternalMovements.getOffenderBookId();
				 String lmovementreasoncodeNew = newOffenderExternalMovements.getMovementReasonCode();
				 Date lmovementdateNew = newOffenderExternalMovements.getMovementDate();
				 String lmovementtypeNew = newOffenderExternalMovements.getMovementType();
				 String lmovetype = newOffenderExternalMovements.getMovementType();
				 String lmovereason = newOffenderExternalMovements.getMovementReasonCode();
				 Date lmovedate = newOffenderExternalMovements.getMovementDate();
				 String ltoprovstatcode = newOffenderExternalMovements.getToProvStatCode();
				 String ltoagylocid = newOffenderExternalMovements.getToAgyLocId();
				 Integer lTrgEventId=null; 
				if(Objects.isNull(oldOffenderExternalMovements.getMovementReasonCode()) && 
						 Objects.nonNull(newOffenderExternalMovements.getMovementReasonCode())) {
					 if(StringUtils.equals(lmovetype, "TRN") && StringUtils.equals(ltoagylocid, "OSC")) {
						 lTrgEventId = offenderExternalMovementsT5Repository.getTrgEventId();
						 // inserting record into offender_assoc_p_event_notifs
						 offenderExternalMovementsT5Repository.
					insertOffenderAssocPEventNotifs(lTrgEventId, lmovedate, offenderbookid, "IC_TRN",newOffenderExternalMovements.getCreateUserId());
					 }
					 // -- Victims Trigger 5: Discharge/Expiration
					 if(StringUtils.equals(nvlFunction(lmovementtypeNew, "X"), "REL") &&
							 !StringUtils.equals(nvlFunction(lmovementreasoncodeNew, "X"), "DEATH") ) {
						 lTrgEventId = offenderExternalMovementsT5Repository.getTrgEventId();
						 String value="DISCH"+"-"+newOffenderExternalMovements.getCreateUserId();
						 // inserting record into offender_assoc_p_event_notifs
						 offenderExternalMovementsT5Repository.
						 insertOffenderAssocPEventNotifs(lTrgEventId, lmovedate, offenderbookid, value,newOffenderExternalMovements.getCreateUserId());
						 lCheckExistFlag=offenderExternalMovementsT5Repository.getY(newOffenderExternalMovements.getOffenderBookId());;
						 // -- Victims Trigger 6: Discharge/Expiration - Sex Offender
						 if(StringUtils.equals("N", lCheckExistFlag)) {
							 lTrgEventId = offenderExternalMovementsT5Repository.getTrgEventId();
							 // inserting record into offender_assoc_p_event_notifs
							 offenderExternalMovementsT5Repository.
							 insertOffenderAssocPEventNotifs(lTrgEventId, lmovedate, offenderbookid, "DISCH_SO",newOffenderExternalMovements.getCreateUserId());
						 }
					 }
						//-- Victims Trigger 18: Death
					 if(StringUtils.equals(lmovementreasoncodeNew, "DEATH")) {
						 lTrgEventId = offenderExternalMovementsT5Repository.getTrgEventId();
						 // inserting record into offender_assoc_p_event_notifs
						 offenderExternalMovementsT5Repository.
						 insertOffenderAssocPEventNotifs(lTrgEventId, lmovedate, offenderbookid, "DTH",newOffenderExternalMovements.getCreateUserId());
					 }
				 }
				//-- Victims Trigger 18: Death
				else if(!StringUtils.equals(oldOffenderExternalMovements.getMovementReasonCode(),newOffenderExternalMovements.getMovementReasonCode())) {
				      lmovementreasoncodeNew = newOffenderExternalMovements.getMovementReasonCode();
				      Date lmovementdatenew = newOffenderExternalMovements.getMovementDate();
				      if(StringUtils.equals(lmovementreasoncodeNew, "DEATH")) {
				    	  lTrgEventId = offenderExternalMovementsT5Repository.getTrgEventId();
							 // inserting record into offender_assoc_p_event_notifs
							 offenderExternalMovementsT5Repository.
							 insertOffenderAssocPEventNotifs(lTrgEventId, lmovedate, offenderbookid, "DTH",newOffenderExternalMovements.getCreateUserId());
				      }
				 }
			}
		}
			}
	
	private String nvlFunction(String firstValue,String secondValue) {
		if(StringUtils.isBlank(firstValue)) {
			return secondValue;
		}else {
			return firstValue;
		}
	}
	

}