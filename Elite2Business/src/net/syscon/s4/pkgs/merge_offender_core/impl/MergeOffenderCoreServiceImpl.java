package net.syscon.s4.pkgs.merge_offender_core.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.merge_booking_utils.MergeBookingUtilsRepository;
import net.syscon.s4.pkgs.merge_context.MergeContextService;
import net.syscon.s4.pkgs.merge_log.MergeLogService;
import net.syscon.s4.pkgs.merge_offender_core.MergeOffenderCoreRepository;
import net.syscon.s4.pkgs.merge_offender_core.MergeOffenderCoreService;
import net.syscon.s4.pkgs.merge_process.MergeProcessRepository;
import net.syscon.s4.pkgs.merge_process.MergeProcessService;
import net.syscon.s4.sa.recordmaintenance.MergeOffenderTables;

@Service
public class MergeOffenderCoreServiceImpl implements MergeOffenderCoreService {

	private static Logger logger = LogManager.getLogger(MergeOffenderCoreServiceImpl.class.getName());

	@Autowired
	private MergeOffenderCoreRepository mergeOffenderCoreRepository;

	@Autowired
	private MergeBookingUtilsRepository mergeBookingUtilsRepository;

	@Autowired
	private MergeContextService mergeContextService;

	@Autowired
	private MergeProcessService mergeProcessService;
	
	@Autowired
	private MergeLogService mergeLogService;

//	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean mergeOffenders(Long mergeTransactionId, Long fromRootOffenderId, Long fromOffenderBookId,
			Long toRootOffenderId, Long toOffenderBookId, String mergeType, String transactionType, String user) {

		Long offenderId = null;
		String fromOffenderIdDisplay = null;
		String toOffenderIdDisplay = null;
		String bookingStatus = null;
		String activeFlag = null;
		String communityActiveFlag = null;
		String agyLocId = null;
		String lastName = null;
		String firstName = null;
		String cFunc = "merge_offender_core.merge_offenders";
		Boolean result = false;
		
		mergeLogService.debug("Logging Level = DEBUG", mergeTransactionId, user);
		mergeLogService.trace("In:"+cFunc, mergeTransactionId, user);
		mergeLogService.debug("From Root Offender Id = "+fromRootOffenderId, mergeTransactionId, user);
		mergeLogService.debug("To Root Offender id = "+toRootOffenderId, mergeTransactionId, user);
		mergeLogService.debug("Merge Type = "+mergeType, mergeTransactionId, user);
		

		if ("MANUAL".equals(mergeType)) {
			mergeType = "MANUAL";
		} else {
			mergeType = "AUTO";
		}
		
		mergeLogService.debug("From Offender Book Id = "+fromOffenderBookId, mergeTransactionId, user);
		

		Offenders fromOffenders = new Offenders();
		Offenders toOffenders = new Offenders();
		

		List<OffenderBookings> fromGetValues = mergeBookingUtilsRepository.getBookingVals(fromOffenderBookId);
        if(!fromGetValues.isEmpty()) { 
        	offenderId = fromGetValues.get(0).getOffenderId().longValue();
        	fromOffenderIdDisplay = fromGetValues.get(0).getOffenderIdDisplay();
        	bookingStatus = fromGetValues.get(0).getBookingStatus();
        	activeFlag = fromGetValues.get(0).getActiveFlag();
        	communityActiveFlag= fromGetValues.get(0).getCommunityActiveFlag();
        	agyLocId = fromGetValues.get(0).getAgyLocId();
        	lastName = fromGetValues.get(0).getLastName();
        	firstName = fromGetValues.get(0).getFirstName();
        	fromOffenders = mergeContextService.setFromOffVals(fromRootOffenderId, offenderId, fromOffenderIdDisplay, fromOffenderBookId,
        			bookingStatus, activeFlag, communityActiveFlag, agyLocId, lastName, firstName);
        }

        mergeLogService.debug("Offender 1 NOMIS Id ="+fromOffenderIdDisplay, mergeTransactionId, user);
        
        mergeLogService.debug("To Offender Book Id = "+toOffenderBookId, mergeTransactionId, user);
        
        List<OffenderBookings> toGetValues  = mergeBookingUtilsRepository.getBookingVals(toOffenderBookId);
        if(!toGetValues.isEmpty()) { 
        	offenderId = toGetValues.get(0).getOffenderId().longValue();
        	toOffenderIdDisplay = toGetValues.get(0).getOffenderIdDisplay();
        	bookingStatus = toGetValues.get(0).getBookingStatus();
        	activeFlag = toGetValues.get(0).getActiveFlag();
        	communityActiveFlag= toGetValues.get(0).getCommunityActiveFlag();
        	agyLocId = toGetValues.get(0).getAgyLocId();
        	lastName = toGetValues.get(0).getLastName();
        	firstName = toGetValues.get(0).getFirstName();
        	toOffenders = mergeContextService.setToOffVals(toRootOffenderId, offenderId, toOffenderIdDisplay, toOffenderBookId,
				bookingStatus, activeFlag, communityActiveFlag, agyLocId, lastName, firstName);
        }
        
        
        mergeLogService.debug("Offender 2 NOMIS ID  ="+toOffenderIdDisplay, mergeTransactionId, user);


        try {
        	mergeProcessService.initProc(fromOffenders, toOffenders, mergeTransactionId, transactionType, user);
        	result = true;
			mergeLogService.debug("From Offender =" + fromOffenderIdDisplay, mergeTransactionId, user);
			mergeLogService.debug("To Offender =" + toOffenderIdDisplay, mergeTransactionId, user);
			mergeLogService.info("SUCCESS: Merge Successful", mergeTransactionId, user);
        }catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method initProc", e);
        } 
		return result;
	}

	@Override
	public void mergeApplnRecords(String applnCode, Long lvRootOffIdFrom, Long lvRootOffIdTo) {
		List<MergeOffenderTables> retTableList=new ArrayList<>();
		retTableList = mergeOffenderCoreRepository.mergeTableListCurser(applnCode);
		
		for (MergeOffenderTables mergeOffenderTables : retTableList) {
				String tableName = mergeOffenderTables.getTableName();
				String object = null;
				String object2 = null;
				String lvPkType =null;
				String lvAddUpdRule =null;
				String lvSeqColumn=null;
				String lvOffenderIdColumn=null;
				String lvObjectClass = null;
				String lvWhereClause=null;
				String lvToWhereClause = null;
				String lvUpdateClause=null;
				Long lvSeqNo = null;
				String lvDml = null;
				String lvLockRecDml = null;
				String lvObjWhereClause = null;
				if(!(tableName.equals("MED_WAIT_LISTS"))  && !(tableName.equals("OFFENDER_MED_MS_INTERACTS"))) {
				List<MergeOffenderTables> returnData = mergeOffenderCoreRepository.mergeTableRelatedData(tableName);

				if(!returnData.isEmpty()) {
					if(returnData.get(0).getPkType()!=null) {			
						lvPkType=returnData.get(0).getPkType();
					}
					if(returnData.get(0).getAdditionalUpdateRule()!=null) {
						lvAddUpdRule = returnData.get(0).getAdditionalUpdateRule();
					}
					if(returnData.get(0).getSeqColumn()!=null) {
						lvSeqColumn = returnData.get(0).getSeqColumn();
					}
					if(returnData.get(0).getOffenderIdColumn()!=null) {
						lvOffenderIdColumn = returnData.get(0).getOffenderIdColumn();
					}
					
					if(returnData.get(0).getObjectClass()!=null) {
						lvObjectClass = returnData.get(0).getObjectClass();
					}
				}
				
				if(object2 == null) {
					lvWhereClause = lvOffenderIdColumn.concat(" = ").concat(String.valueOf(lvRootOffIdFrom));
					lvUpdateClause = lvOffenderIdColumn.concat(" = ").concat(String.valueOf(lvRootOffIdTo));
				}else {
					lvWhereClause = object2;
					lvUpdateClause = object;
				}

				lvSeqColumn = mergeOffenderCoreRepository.extraPkColumn(tableName);
				if(lvSeqColumn != null) {
					lvToWhereClause = lvOffenderIdColumn.concat(" = ").concat(String.valueOf(lvRootOffIdTo));
					lvSeqNo = maxSeq(tableName, lvSeqColumn, lvToWhereClause);
					lvUpdateClause = lvUpdateClause.concat(" , ").concat(String.valueOf(lvSeqColumn)).concat(" = ")
							.concat(String.valueOf(lvSeqColumn)).concat(" + ").concat(String.valueOf(lvSeqNo));			 
				}
				
				if (lvAddUpdRule != null) {

					lvAddUpdRule = "REPLACE".concat("(").concat(String.valueOf(lvAddUpdRule).concat(", ")).concat("##1")
							.concat(", ").concat(String.valueOf(lvRootOffIdTo)).concat(")");

					lvAddUpdRule = "REPLACE".concat("(").concat(String.valueOf(lvAddUpdRule).concat(", ")).concat("##OFF_PLUS")
							.concat(", ").concat("(").concat(String.valueOf(lvRootOffIdTo)).concat("-")
							.concat(String.valueOf(lvRootOffIdFrom)).concat(")");

					lvAddUpdRule = "REPLACE".concat("(").concat(String.valueOf(lvAddUpdRule).concat(", ")).concat("##2")
							.concat(", ").concat("NVL(lvSeqNo,0)").concat(")");
				}

				if(lvAddUpdRule != null) {
				lvDml = "UPDATE ".concat(String.valueOf(tableName)).concat(" SET ").concat(String.valueOf(lvUpdateClause)).concat(" ")
						.concat(String.valueOf(lvAddUpdRule)).concat(" WHERE ").concat(String.valueOf(lvWhereClause));
				}
				
				lvDml = "UPDATE ".concat(String.valueOf(tableName)).concat(" SET ").concat(String.valueOf(lvUpdateClause)).concat(" ")
						.concat(" WHERE ").concat(String.valueOf(lvWhereClause));

				lvLockRecDml = " SELECT * FROM ".concat(String.valueOf(tableName)).concat(" WHERE ")
						.concat(String.valueOf(lvWhereClause));
				
				runMergeSql(lvLockRecDml);
				runMergeSql(lvDml);
				
				if(lvObjectClass != null) {
					lvObjWhereClause = "OWNER_CLASS = ".concat(" \'").concat("OFF").concat("\' ").concat(" AND OWNER_ID = ")
							.concat(String.valueOf(lvRootOffIdFrom));
						
					lvSeqNo = maxSeq("ADDRESSES", "OWNER_SEQ", lvObjWhereClause);
					mergeOffenderGeneric("ADDRESSES", lvObjectClass, lvRootOffIdFrom, lvRootOffIdTo, lvSeqNo);
					lvSeqNo = maxSeq("INTERNET_ADDRESSES", "OWNER_SEQ", lvObjWhereClause);
					mergeOffenderGeneric("INTERNET_ADDRESSES", lvObjectClass, lvRootOffIdFrom, lvRootOffIdTo, lvSeqNo);
					lvSeqNo = maxSeq("PHONES", "OWNER_SEQ", lvObjWhereClause);
					mergeOffenderGeneric("PHONES", lvObjectClass, lvRootOffIdFrom, lvRootOffIdTo, lvSeqNo);
				}	
				}
		}
	}

	
	
	
	
	public Long maxSeq(String tableName, String lvSeqColumn, String lvToWhereClause ) {
		Long lvMaxSeq= null;
		String query;
		query = "SELECT COALESCE(MAX(".concat(String.valueOf(lvSeqColumn)).concat("),0) ")
		.concat(" FROM ").concat(String.valueOf(tableName)).concat(" WHERE ").concat(String.valueOf(lvToWhereClause)).concat(" ") ;
		lvMaxSeq = mergeOffenderCoreRepository.getLvMaxSeq(query); 
		return lvMaxSeq;
	}
	
	public void runMergeSql(String pSqlDml) {
		String query = pSqlDml.toLowerCase();
		Integer debugLevel = 0;
		if (debugLevel == 0) {
			if (query.contains("select")) {
				mergeOffenderCoreRepository.getOffFingerprintsDet(pSqlDml);
			}
			if (query.contains("update")) {
				mergeOffenderCoreRepository.updateOffFingrPrnt(pSqlDml);
			}
		} else {
			mergeOffenderCoreRepository.insertMergeOffenderSqls(pSqlDml);
		}
	}
	
	@Override
	public void mergeOffenderGeneric(String tableName, String objectClass, Long lvRootOffIdFrom, Long lvRootOffIdTo, Long seqNumber ) {
		
		String lvDml = null;
		String lvLockRecDml = null;
		String lvUpdateClause = null;
		
		lvUpdateClause = " SET OWNER_ID = ".concat(String.valueOf(lvRootOffIdTo));
		if(seqNumber != null) {
			lvUpdateClause = lvUpdateClause.concat(", OWNER_SEQ = OWNER_SEQ + ").concat(String.valueOf(seqNumber));
		}
		
		lvLockRecDml = "SELECT * FROM ".concat(String.valueOf(tableName)).concat(" WHERE OWNER_CLASS = \'").concat(String.valueOf(objectClass)).concat("\'")
				.concat(" AND OWNER_ID = ").concat(String.valueOf(lvRootOffIdFrom)).concat(" FOR UPDATE NOWAIT ");
		
		lvDml =  "UPDATE ".concat(String.valueOf(tableName)).concat(String.valueOf(lvUpdateClause)).concat(" WHERE OWNER_CLASS = \'")
				.concat(String.valueOf(objectClass)).concat("\'").concat("  AND OWNER_ID = ").concat(String.valueOf(lvRootOffIdFrom));
				
		runMergeSql(lvLockRecDml);
		runMergeSql(lvDml);
	}

}
