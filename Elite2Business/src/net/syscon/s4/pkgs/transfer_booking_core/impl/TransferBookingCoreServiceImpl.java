package net.syscon.s4.pkgs.transfer_booking_core.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.TransferBookingTables;
import net.syscon.s4.pkgs.TransferTableRelationships;
import net.syscon.s4.pkgs.merge_booking_utils.MergeBookingUtilsRepository;
import net.syscon.s4.pkgs.merge_context.MergeContextService;
import net.syscon.s4.pkgs.merge_process.MergeProcessService;
import net.syscon.s4.pkgs.transfer_booking_core.TransferBookingCoreRepository;
import net.syscon.s4.pkgs.transfer_booking_core.TransferBookingCoreService;
import net.syscon.s4.sa.admin.beans.MergeTransactions;

@Service
public class TransferBookingCoreServiceImpl implements TransferBookingCoreService {

	@Autowired
	private MergeBookingUtilsRepository mergeBookingUtilsRepository;

	@Autowired
	private MergeContextService mergeContextService;

	@Autowired
	private MergeProcessService mergeProcessService;
	
	@Autowired
	private TransferBookingCoreRepository transferBookingCoreRepository;
	

	@Override
	public void transferBookings(Long mergeTransactionId, Long fromRootOffenderId, Long fromOffenderBookId,
			Long toRootOffenderId, Long toOffenderBookId, String mergeType) {
		
		Long offenderId = null;
		String offenderIdDisplay = null;
		String bookingStatus = null;
		String activeFlag = null;
		String communityActiveFlag = null;
		String agyLocId = null;
		String lastName = null;
		String firstName = null;

		if (mergeType != null && mergeType.equals("MANUAL")) {
			mergeType = "MANUAL";
		} else {
			mergeType = "AUTO";
		}
		
		Offenders fromOffenders = new Offenders();
		Offenders toOffenders = new Offenders();
		
		List<OffenderBookings> fromGetValues = mergeBookingUtilsRepository.getBookingVals(fromOffenderBookId);
        if(!fromGetValues.isEmpty()) { 
        	offenderId = fromGetValues.get(0).getOffenderId().longValue();
        	offenderIdDisplay = fromGetValues.get(0).getOffenderIdDisplay();
        	bookingStatus = fromGetValues.get(0).getBookingStatus();
        	activeFlag = fromGetValues.get(0).getActiveFlag();
        	communityActiveFlag= fromGetValues.get(0).getCommunityActiveFlag();
        	agyLocId = fromGetValues.get(0).getAgyLocId();
        	lastName = fromGetValues.get(0).getLastName();
        	firstName = fromGetValues.get(0).getFirstName();
        	fromOffenders = mergeContextService.setFromOffVals(fromRootOffenderId, offenderId, offenderIdDisplay, fromOffenderBookId,
        			bookingStatus, activeFlag, communityActiveFlag, agyLocId, lastName, firstName);
        }

        List<OffenderBookings> toGetValues  = mergeBookingUtilsRepository.getBookingVals(toOffenderBookId);
        if(!toGetValues.isEmpty()) { 
        	offenderId = toGetValues.get(0).getOffenderId().longValue();
        	offenderIdDisplay = toGetValues.get(0).getOffenderIdDisplay();
        	bookingStatus = toGetValues.get(0).getBookingStatus();
        	activeFlag = toGetValues.get(0).getActiveFlag();
        	communityActiveFlag= toGetValues.get(0).getCommunityActiveFlag();
        	agyLocId = toGetValues.get(0).getAgyLocId();
        	lastName = toGetValues.get(0).getLastName();
        	firstName = toGetValues.get(0).getFirstName();
        	toOffenders = mergeContextService.setToOffVals(toRootOffenderId, offenderId, offenderIdDisplay, toOffenderBookId,
				bookingStatus, activeFlag, communityActiveFlag, agyLocId, lastName, firstName);
        }
        String user=null;
		String transactionType = "MERGE";
		mergeProcessService.initProc( fromOffenders,toOffenders, mergeTransactionId, transactionType, user );
	}

	@Override
	public Date getBookingStartDate(Long pOffBookId) {
		Date lvInstStartDate = null;
		Date lvCommStartDate = null;
		Date lvReturnDate = null;
		if(pOffBookId != null) {
			lvInstStartDate = transferBookingCoreRepository.getAdmDate(pOffBookId);
			lvCommStartDate = transferBookingCoreRepository.getIntakeDate(pOffBookId);
			
			if(lvInstStartDate == null) {
				lvInstStartDate = lvCommStartDate;
			}
			
			if(lvCommStartDate == null) {
				lvCommStartDate = lvInstStartDate;
			}
			if(lvInstStartDate.compareTo(lvCommStartDate) > 0) {
				lvReturnDate = lvInstStartDate;
			}else if(lvInstStartDate.compareTo(lvCommStartDate) < 0) {
				lvReturnDate = lvCommStartDate;
			}else if(lvInstStartDate.compareTo(lvCommStartDate) == 0) {
				lvReturnDate = lvInstStartDate;
			}
		}

		return lvReturnDate;
	}

	@Override
	public Date getBookingEndDate(Long pOffBookId) {
		Date lvInstEndDate;
		Date lvBookingEndDate;
		Date lvCommEndDate;
		Date lvReturnDate = null;
		Integer lvCount;
		
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date currentTimeStamp = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		
		if(pOffBookId != null) {
			lvInstEndDate = transferBookingCoreRepository.getInstEndDate(pOffBookId);
			lvCount = transferBookingCoreRepository.isCommBooking(pOffBookId);
			lvCommEndDate = transferBookingCoreRepository.getCommEndDate(pOffBookId);
			
			if(lvInstEndDate == null) {
				lvInstEndDate = lvCommEndDate;
			}
			
			if(lvCommEndDate == null) {
				lvCommEndDate = lvInstEndDate;
			}
			if(lvInstEndDate !=  null) {
			if(lvInstEndDate.compareTo(lvCommEndDate) > 0) {
				lvReturnDate = lvInstEndDate;
			}else if(lvInstEndDate.compareTo(lvCommEndDate) < 0) {
				lvReturnDate = lvCommEndDate;
			}else if(lvInstEndDate.compareTo(lvCommEndDate) == 0) {
				lvReturnDate = lvInstEndDate;
			}
			}
			if(lvInstEndDate == null && lvCommEndDate == null) {
				lvReturnDate = currentTimeStamp;
			}
		
		}
		return lvReturnDate;
	}

	@Override
	public void runMergeSql(String pSqlDml) {
		Integer gDebugLevel = 0;
		if(gDebugLevel == 0) {
			transferBookingCoreRepository.execPSqlDml(pSqlDml);
		}else {
			transferBookingCoreRepository.insertMrgOffSqls( pSqlDml);
		}
	}

	@Override
	public void transferApplnRecords(Long pMergeTransactionId, String pApplnCode,String modifyUserId) {

		List<MergeTransactions> mergeTableListCur = transferBookingCoreRepository.mergeTableListCur(pMergeTransactionId,
				pApplnCode);

		for (MergeTransactions rootTable : mergeTableListCur) {
			String pTableName = rootTable.getTableName();
			String pParentTableName = null;
			Integer pParentSeqIncrement = 0;
			Long pFromOffenderId = rootTable.getRootOffenderId1().longValue();
			Long pToOffenderId = rootTable.getRootOffenderId2().longValue();
			Long pFromOffenderBookId = rootTable.getOffenderBookId1().longValue();
			Long pToOffenderBookId = rootTable.getOffenderBookId2().longValue();
			Date pBeginDate = rootTable.getBeginDate();
			Date pEndDate = rootTable.getEndDate();

			cascadeTransfer(pTableName, pParentTableName, pParentSeqIncrement, pFromOffenderId, pToOffenderId,
					pFromOffenderBookId, pToOffenderBookId, pBeginDate, pEndDate,modifyUserId);
			
		}
	}
	
	
	public Integer noOfRec(String pTableName, String pWhereClause) {
		Integer lvCount = null;
		String sql = "SELECT Count(*) FROM ".concat(String.valueOf(pTableName)).concat(" WHERE ")
				.concat(String.valueOf(pWhereClause)).concat(" ");
		lvCount = transferBookingCoreRepository.getCount(sql);
		return lvCount;
	}
	
	public String childWhereClause(String pParentTable, String pChildTable) {
		 String lvString;
	     String lvFkPkType;
	     String lvFkOffenderIdColumn;
	     String lvFkSeqColumn;
		
		TransferTableRelationships trnsfTblRltnData = transferBookingCoreRepository.getTrnsfTblRltnData(pParentTable, pChildTable);
		lvFkPkType = trnsfTblRltnData.getFkPkType();
		lvFkOffenderIdColumn = trnsfTblRltnData.getFkOffenderIdColumn();
		lvFkSeqColumn = trnsfTblRltnData.getFkSeqColumn();
		
		if (lvFkSeqColumn == null) {
			lvString = " ( ".concat(lvFkOffenderIdColumn).concat(" ) IN ")
					.concat(" (SELECT pk_first_column FROM   merge_offender_records_tmp WHERE table_name = ")
					.concat(pParentTable).concat(" ) ");
		} else {
			lvString = " ( ".concat(lvFkOffenderIdColumn).concat(" , ").concat(lvFkSeqColumn).concat(" ) IN ").concat(
					" (SELECT pk_first_column, pk_second_column FROM   merge_offender_records_tmp WHERE table_name = ")
					.concat(pParentTable).concat(" ) ");
		}
		return lvString;
	}
	
	public Integer maxSeq(String pTableName, String pColumnName, String pWhereClause) {
		Integer lvMaxSeq;
		String query = "SELECT COALESCE(MAX( ".concat(pColumnName).concat("),0) FROM ").concat(pTableName)
				.concat(" WHERE ").concat(pWhereClause).concat(" ");
		lvMaxSeq = transferBookingCoreRepository.getMaxSeq(query);

		return lvMaxSeq;
	}
	
	public String pkInsertString(String pTableName) {
		String lvString;
		String lvPkType;
		String lvOffenderIdColumn;
		String lvSeqColumn;
		String lvSurrogateKeyColumn;
		String lvFilterColumn;
		String lvBookingIdColumn;
		
		TransferBookingTables transferBkngTablesData = transferBookingCoreRepository.getTransferBkngTablesData(pTableName);
		
		lvPkType = transferBkngTablesData.getPkType();
		lvOffenderIdColumn = transferBkngTablesData.getOffenderIdColumn();
		lvSeqColumn = transferBkngTablesData.getSeqColumn();
		lvSurrogateKeyColumn = transferBkngTablesData.getSurrogateKeyColumn();
		lvFilterColumn = transferBkngTablesData.getFilterColumn();
		lvBookingIdColumn = transferBkngTablesData.getBookingIdColumn();
		
		if ("SURROGATE".equalsIgnoreCase(lvPkType)) {
			lvString = lvSurrogateKeyColumn.concat(", NULL");
		} else {
			if ("OFFENDER_ID".equalsIgnoreCase(lvFilterColumn)) {
				lvString = "COALESCE (".concat(lvOffenderIdColumn).concat(", NULL )");
			} else {
				lvString = "COALESCE (".concat(lvBookingIdColumn).concat(", NULL )");
			}
			if (lvSeqColumn != null) {
				lvString = lvString.concat(" , ").concat(lvSeqColumn);
			} else {
				lvString = lvString.concat(" , NULL");
			}
		}
		return lvString;
	}
	
	
	public void transferBookingObjects(String pParentTable, String pObjectClass, Long pFromOffenderId,
			Long pToOffenderId) {

		String lvObjWhereClause;
		Integer lvSeqNo;
		lvObjWhereClause = "OWNER_CLASS = OFF AND OWNER_ID = ".concat(String.valueOf(pFromOffenderId));
		lvSeqNo = maxSeq("ADDRESSES", "OWNER_SEQ", lvObjWhereClause);
		transferBookingGeneric(pParentTable, "ADDRESSES", pObjectClass, pToOffenderId, lvSeqNo);
		
		lvObjWhereClause = "OWNER_CLASS = OFF AND OWNER_ID = ".concat(String.valueOf(pFromOffenderId));
		lvSeqNo = maxSeq("INTERNET_ADDRESSES", "OWNER_SEQ", lvObjWhereClause); 
		transferBookingGeneric(pParentTable, "INTERNET_ADDRESSES", pObjectClass, pToOffenderId, lvSeqNo);
		
		
		lvObjWhereClause = "OWNER_CLASS = OFF AND OWNER_ID = ".concat(String.valueOf(pFromOffenderId));
		lvSeqNo = maxSeq("PHONES", "OWNER_SEQ", lvObjWhereClause); 
		transferBookingGeneric(pParentTable, "PHONES", pObjectClass, pToOffenderId, lvSeqNo);
		
	}
	
	
	public void transferBookingGeneric(String pParentTable, String pTableName,String pObjectClass,Long pToOffenderId, Integer pSeqNumber ) {
		
		String lvDml;
		String lvLockRecordDml;
		String lvWhereClause;
		String lvUpdateClause;
		
		if (pSeqNumber != null) {
			lvUpdateClause = " SET OWNER_ID = ".concat(String.valueOf(pToOffenderId))
					.concat(", OWNER_SEQ = OWNER_SEQ + ").concat(String.valueOf(pSeqNumber));
			lvWhereClause = " WHERE OWNER_CLASS = ".concat(pObjectClass).concat(" AND (owner_id, owner_seq) IN")
					.concat(" (SELECT pk_first_column, pk_first_column ");
		} else {
			lvUpdateClause = " SET OWNER_ID = ".concat(String.valueOf(pToOffenderId));
			lvWhereClause = " WHERE OWNER_CLASS = ".concat(pObjectClass).concat(" AND (owner_id) IN")
					.concat(" (SELECT pk_first_column ");
		}
		
		lvWhereClause = lvWhereClause.concat(" FROM  merge_offender_records_tmp  WHERE table_name =  ").concat(pParentTable).concat(" )");
		
		lvLockRecordDml = "SELECT * FROM ".concat(pTableName).concat(lvWhereClause);
		
		lvDml = "UPDATE ".concat(pTableName).concat(lvUpdateClause).concat(lvWhereClause);
		
		runMergeSql(lvLockRecordDml);
		runMergeSql(lvDml);
		
	}
	
	public void unlinkRecords(String pTableName, Long pFromOffenderId, Long pToOffenderId, Integer pSeqNo) {
		
		List<TransferBookingTables> mergeTableListCur = transferBookingCoreRepository
				.cascadeTrnsMergeTableListCur(pTableName);

		String lvDml;
		String lvLockRecDml;
		String lvWhereClause;
		String lvPkType;
		String lvUpdateClause;
		String lvSeqColumn;
		String lvOffenderIdColumn;
		String lvSurrogateKeyColumn;
		String lvParentOffIdColumn;
		String lvParentSeqColumn;

		TransferBookingTables transfBkngTblsVals = transferBookingCoreRepository
				.getTransferBookingTablesVals(pTableName);
		lvPkType = transfBkngTblsVals.getPkType();
		lvSeqColumn = transfBkngTblsVals.getSeqColumn();
		lvParentOffIdColumn = transfBkngTblsVals.getParentOffIdColumn();
		lvParentSeqColumn = transfBkngTblsVals.getParentSeqColumn();
		lvSurrogateKeyColumn = transfBkngTblsVals.getSurrogateKeyColumn();
		lvUpdateClause = transfBkngTblsVals.getUpdateClause();
		lvOffenderIdColumn = transfBkngTblsVals.getOffenderIdColumn();

		lvLockRecDml = "SELECT * FROM ".concat(pTableName).concat(" WHERE ").concat(lvOffenderIdColumn).concat(" IN (")
				.concat(String.valueOf(pFromOffenderId)).concat(", ").concat(String.valueOf(pToOffenderId))
				.concat(") ");
		runMergeSql(lvLockRecDml);

		if(lvParentSeqColumn != null) {
			lvWhereClause = "( ".concat(lvParentOffIdColumn).concat(", ").concat(lvParentSeqColumn).concat(") IN").concat("( SELECT PK_first_column, PK_second_column ")
					.concat(" FROM merge_offender_records_tmp WHERE table_name = ").concat(pTableName).concat(")");
		}else {
			lvWhereClause = "( ".concat(lvParentOffIdColumn).concat(") IN").concat("( SELECT PK_first_column")
					.concat(" FROM merge_offender_records_tmp WHERE table_name = ").concat(pTableName).concat(")");
		}
		
		if (!("SURROGATE".equalsIgnoreCase(lvPkType))) {
			lvDml = "UPDATE ".concat(pTableName).concat(" SET ").concat(lvParentOffIdColumn).concat(" = ")
					.concat(String.valueOf(pToOffenderId));

			if (lvParentSeqColumn != null) {
				lvDml = lvDml.concat(" , ").concat(lvParentSeqColumn).concat(" = ").concat(lvParentSeqColumn)
						.concat(" + ").concat(String.valueOf(pSeqNo));
			}
			lvDml = lvDml.concat(" WHERE ").concat(lvWhereClause);
			runMergeSql(lvDml);
		}   
		
		lvDml = "UPDATE ".concat(pTableName).concat(" A SET ").concat(lvParentOffIdColumn).concat(" = NULL");
				
		if(lvParentSeqColumn != null) {
			lvDml = lvDml.concat(" , ").concat(lvParentSeqColumn).concat(" = NULL ");
		}
		
		lvDml = lvDml.concat(" WHERE ").concat(lvOffenderIdColumn).concat(" IN (").concat(String.valueOf(pFromOffenderId)).concat(" , ")
				.concat(String.valueOf(pToOffenderId)).concat(") AND").concat(lvParentOffIdColumn).concat(" IS NOT NULL AND").concat(lvOffenderIdColumn)
				.concat(" != ( SELECT").concat(lvOffenderIdColumn).concat(" FROM ").concat(pTableName).concat(" B  WHERE  A.").concat(lvParentOffIdColumn)
				.concat(" = B.").concat("COALESCE (").concat(lvSurrogateKeyColumn).concat(" , ").concat(lvOffenderIdColumn);
		
		if(lvSeqColumn != null) {
			lvDml = lvDml.concat(" AND  A.").concat(lvParentSeqColumn).concat(" = B.").concat(lvSeqColumn);
		}
		
		lvDml = lvDml.concat(")");		
		runMergeSql(lvDml);
	}
	
	
	
	
	public void cascadeTransfer(String pTableName, String pParentTableName, Integer pParentSeqIncrement,Long pFromOffenderId, Long pToOffenderId,
			Long pFromOffenderBookId, Long pToOffenderBookId, Date pBeginDate,Date pEndDate, String modifyUserId) {

			String lvDml;
			String lvInsertRecDml;
			String lvLockRecDml;
			String lvPkType = null;
			Integer lvMaxSeq;
			String lvInsertClause;
			String lvWhereClause;
			String lvToWhereClause;
			String lvObjWhereClause;
			String lvFkOffenderIdColumn;
			String lvFkSeqColumn;
			Integer lvSeqIncrement;
			String lvSeqColumn;
			String lvRootFlag;
			String lvPkFirstColumn;
			String lvPkSecondColumn;
			String lvOffenderIdColumn;
			String lvUpdateClause;
			String lvChildUpdateClause;
			String lvBookingWhereClause;
			String lvObjectClass;
			String lvSurrogateKeyColumn;
			String lvTransferDateColumn;
			String lvParentOffIdColumn;
			String lvParentSeqColumn;
			String lvFilterColumn;
			String lvFkPkType;
			Integer lvSeqNo = null;

			TransferBookingTables transfBkngTblsVals = transferBookingCoreRepository.getTransferBookingTablesVals(pTableName);

			lvPkType = transfBkngTblsVals.getPkType();
			lvSeqColumn = transfBkngTblsVals.getSeqColumn();
			lvFilterColumn = transfBkngTblsVals.getFilterColumn();
			lvParentOffIdColumn = transfBkngTblsVals.getParentOffIdColumn();
			lvParentSeqColumn = transfBkngTblsVals.getParentSeqColumn();
			lvSurrogateKeyColumn = transfBkngTblsVals.getSurrogateKeyColumn();
			lvUpdateClause = transfBkngTblsVals.getUpdateClause();
			lvBookingWhereClause = transfBkngTblsVals.getBookingWhereClause();
			lvPkFirstColumn = transfBkngTblsVals.getPkFirstColumn();
			lvPkSecondColumn = transfBkngTblsVals.getPkSecondColumn();
			lvRootFlag = transfBkngTblsVals.getRootFlag();
			lvOffenderIdColumn = transfBkngTblsVals.getOffenderIdColumn();
			lvObjectClass = transfBkngTblsVals.getObjectClass();
			lvTransferDateColumn = transfBkngTblsVals.getTransferDateColumn();

			String pWhereClause = lvOffenderIdColumn.concat(" = ").concat(String.valueOf(pFromOffenderId)).concat(" ");
			Integer noOfRec = noOfRec(pTableName, pWhereClause);
			if (noOfRec == 0) {
				// AND g_turbo_mode = 'Y'
				return;
			}

			lvWhereClause = lvBookingWhereClause;
			if (pBeginDate != null && lvTransferDateColumn != null) {
				lvWhereClause = lvWhereClause.concat(" AND ").concat(lvTransferDateColumn).concat(" >= ")
						.concat(String.valueOf(pBeginDate));
			}

			if (pEndDate != null && lvTransferDateColumn != null) {
				lvWhereClause = lvWhereClause.concat(" AND ").concat(lvTransferDateColumn).concat(" <= ")
						.concat(String.valueOf(pEndDate));
			}

			if (pParentTableName != null) {
				TransferTableRelationships trTabRelData = transferBookingCoreRepository.getTrTabRelData(pParentTableName, pTableName);

				lvFkOffenderIdColumn = trTabRelData.getFkOffenderIdColumn();
				lvFkSeqColumn = trTabRelData.getFkSeqColumn();
				lvFkPkType = trTabRelData.getFkPkType();
				lvChildUpdateClause = trTabRelData.getChildUpdateClause();

				lvWhereClause = childWhereClause(pParentTableName, pTableName);
				lvUpdateClause = lvChildUpdateClause;
			}

			if (lvSeqColumn != null && lvPkFirstColumn.contains("OFFENDER_ID")) {
				lvToWhereClause = lvPkFirstColumn.concat(" = ").concat(String.valueOf(pToOffenderId));
				lvSeqNo = maxSeq(pTableName, lvSeqColumn, lvToWhereClause);
				lvUpdateClause = lvUpdateClause.concat(" , ").concat(lvSeqColumn).concat(" = ").concat(lvSeqColumn)
						.concat(" + ").concat(String.valueOf(lvSeqNo));
			}
            if(lvWhereClause!=null && lvWhereClause.contains("##FROM_OFF_ID") && pFromOffenderId!=null) {      	
            	lvWhereClause = lvWhereClause.replace("##FROM_OFF_ID", String.valueOf(pFromOffenderId));
            }
            if(lvWhereClause!=null && lvWhereClause.contains("##FROM_BOOK_ID") && pFromOffenderBookId!=null) {
            	lvWhereClause = lvWhereClause.replace("##FROM_BOOK_ID", String.valueOf(pFromOffenderBookId));
            }
            if(lvWhereClause!=null && lvWhereClause.contains("##PARENT_TABLE") && pParentTableName!=null) {      	
            	lvWhereClause = lvWhereClause.replace("##PARENT_TABLE", String.valueOf(pParentTableName));
            }
           
            if(lvUpdateClause!=null && lvUpdateClause.contains("##TO_OFF_ID") && pToOffenderId!=null) {          	
            	lvUpdateClause = lvUpdateClause.replace("##TO_OFF_ID", String.valueOf(pToOffenderId));
            }
            if(lvUpdateClause!=null && lvUpdateClause.contains("##TO_BOOK_ID") && pToOffenderBookId!=null) {         	
            	lvUpdateClause = lvUpdateClause.replace("##TO_BOOK_ID", String.valueOf(pToOffenderBookId));
            }
            if(lvUpdateClause!=null && lvUpdateClause.contains("##PARENT_SEQ_NO") && pParentSeqIncrement!=null) {          	
            	lvUpdateClause = lvUpdateClause.replace("##PARENT_SEQ_NO", String.valueOf(pParentSeqIncrement));
            }
            if(lvUpdateClause!=null) {
            	lvUpdateClause = lvUpdateClause.concat(",".concat("MODIFY_DATETIME = current_timestamp , modify_user_id = "+"'"+modifyUserId+"'"));
            }
			lvLockRecDml = "SELECT * FROM ".concat(pTableName).concat(" WHERE ").concat(lvWhereClause);

			if (lvSeqColumn != null) {

				lvToWhereClause = lvPkFirstColumn.concat(" = ").concat(String.valueOf(pToOffenderId));
				lvSeqNo = maxSeq(pTableName, lvSeqColumn, lvToWhereClause);
				lvInsertClause = pkInsertString(pTableName).concat(" , ").concat(String.valueOf(lvSeqNo));
			} else {
				lvInsertClause = pkInsertString(pTableName).concat(" , NULL ");
			}

			runMergeSql(lvLockRecDml);

			lvInsertRecDml = "INSERT INTO MERGE_OFFENDER_RECORDS_TMP (TABLE_NAME, PK_first_column, PK_second_column, SEQ_INCREMENT) SELECT "
					.concat(pTableName).concat(" , ").concat(lvInsertClause).concat(" FROM ").concat(pTableName)
					.concat(" WHERE ").concat(lvWhereClause);
			//runMergeSql(lvInsertRecDml);

			lvDml = "UPDATE ".concat(pTableName).concat(" SET ").concat(lvUpdateClause).concat(" WHERE ")
					.concat(lvWhereClause);
			runMergeSql(lvDml);

			if (lvObjectClass != null) {
				transferBookingObjects(pParentTableName, lvObjectClass, pFromOffenderId, pToOffenderId);
			}

			if (lvParentOffIdColumn != null) {
				unlinkRecords(pTableName, pFromOffenderId, pToOffenderId, lvSeqNo);
			}

			List<TransferBookingTables> cascadeTrnsMergeTableListCur = transferBookingCoreRepository
					.cascadeTrnsMergeTableListCur(pTableName);
			for (TransferBookingTables childTable : cascadeTrnsMergeTableListCur) {
				
				String preProcessingRule = childTable.getPreProcessingRule();
				if (preProcessingRule != null) {
					lvDml = "REPLACE (".concat(preProcessingRule).concat(", ##FROM_OFF_ID ,")
							.concat(String.valueOf(pFromOffenderId));
					lvDml = "REPLACE (".concat(lvDml).concat(", ##TO_OFF_ID ,").concat(String.valueOf(pToOffenderId));
				}

				pParentTableName = pTableName;
				pTableName = childTable.getTableName();
				pBeginDate = null;
				pEndDate = null;
				if(lvSeqNo == null && pParentSeqIncrement!=null) {
					lvSeqNo = pParentSeqIncrement;
				}
				
				cascadeTransfer(pTableName, pParentTableName, lvSeqNo, pFromOffenderId, pToOffenderId,pFromOffenderBookId, pToOffenderBookId,
						pBeginDate, pEndDate,modifyUserId);
				
		} 
	}

	@Override
	public Long getPrvBookId(Long pOffBookId, Long pRootOffId) {
		return transferBookingCoreRepository.getPrvBookIdCur(pOffBookId, pRootOffId);	
	}
	
	@Override
	public Long getNextBookId(Long pOffBookId, Long pRootOffId) {
		return transferBookingCoreRepository.getNextBookIdCur(pOffBookId, pRootOffId);	
	}
	
}












