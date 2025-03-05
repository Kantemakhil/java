package net.syscon.s4.pkgs.ocdintak.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.syscon.s4.im.beans.AccountCodes;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderResidence;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.ocdintak.OcdintakPkgRepository;
import net.syscon.s4.pkgs.ocdintak.OcdintakPkgService;
import net.syscon.s4.pkgs.trust.TrustService;

@Service
public class OcdintakPkgServiceImpl implements OcdintakPkgService {

	private static Logger logger = LogManager.getLogger(OcdintakPkgServiceImpl.class.getName());
	@Autowired
	private OcdintakPkgRepository ocdintakRepository;
	@Autowired
	private TrustService trustService;
	@Autowired
	private DeductionsService deductionsService;

	private static final String Y = "Y";
	private static final String N = "N";
	private static final String X = "X";
	private static final String D = "D";
	private static final String R = "R";
	private static final String COMM = "COMM";
	private static final String ADI = "ADI";
	private static final String BOTH = "BOTH";
	private static final String OCDINTAK = "OCDINTAK";

	@Override
	public Integer updateOffBookings(final String toAgyLocId, final OffenderBookings offbkg, final String userName, final Integer staffId, final String pCommStatus, final String lvBookingType) {
		offbkg.setModifyUserId(userName);
		return ocdintakRepository.updateOffBookings(toAgyLocId, offbkg, staffId, pCommStatus, lvBookingType);

	}

	@Override
	@Transactional
	public OffenderBookingEvent processOcdintakTrust(final OffenderBookingEvent offEve) {
		Map<String, Object> storOutParm = new HashMap<String, Object>();
		Map<String, Object> vActStatusMap = new HashMap<String, Object>();
		OffenderBookingEvent returnValue = new OffenderBookingEvent();
		final OffenderTransactions offTxn = new OffenderTransactions();
		String vTrustFlag = null;
		String vActStatus = null;
		String vReceiptNo = null;
		offEve.setpTxnSeq(1);
		offEve.setpTriggerBlk("OFF_BKGE");
		try {
			vTrustFlag = ocdintakRepository.cGetTrustActFlag(offEve.getCaseloadId());

			if (Y.equals(vTrustFlag)) {
				vActStatusMap = trustService.chkAccountStatus(offEve.getCaseloadId(),
						BigDecimal.valueOf(offEve.getRootOffenderId()));
				vActStatus = (String) vActStatusMap.get("P_OPEN_AN_ACCOUNT");
				offEve.setvReceiptNo(vReceiptNo);
				storOutParm = storGlobals(offEve);

				offEve.setpTxnId((Integer) storOutParm.get("P_TXN_ID"));
				offEve.setpTxnPtgType((String) storOutParm.get("P_TXN_PTG_TYPE"));
				offEve.setpSubacType((String) storOutParm.get("P_SUB_AC_TYPE"));
				offEve.setpTxnDesc((String) storOutParm.get("P_TXN_DESC"));
				offEve.setpReceiptNo((String) storOutParm.get("P_RECEIPT_NO"));
				
				returnValue.setCaseloadId(offEve.getCaseloadId());
				returnValue.setBookingType((String)storOutParm.get("P_RECEIPT_NO"));
				returnValue.setCreateUserId((String)storOutParm.get("P_TXN_PTG_TYPE"));
				returnValue.setEventUser((String)storOutParm.get("P_SUBAC_TYPE"));
				returnValue.setCommentText((String)storOutParm.get("P_TXN_DESC"));


				// adding properties For Update_offender_Balance Procedure
				offTxn.setCaseloadId(offEve.getCaseloadId());
				offTxn.setOffenderId(offEve.getRootOffenderId().longValue());
				offTxn.setTxnPostingType(offEve.getpTxnPtgType());
				offTxn.setTxnEntryDate(new Date());
				offTxn.setTxnId(offEve.getpTxnId());
				offTxn.setTxnType(ADI);
				offTxn.setTxnEntryAmount(0.0);
				offTxn.setSubAccountType(offEve.getpSubacType());

				if (Y.equals(vActStatus)) {
					removeClosAcct(offEve);
				} else if (N.equals(vActStatus)) {
					trustService.updateOffenderBalance(offTxn, offEve.getModifyUserId());
				} else if (X.equals(vActStatus)) {
					createNewAccount(offEve.getRootOffenderId().intValue(), offEve.getCaseloadId(), offEve.getpTxnId(),
							offEve.getCreateUserId());
					trustService.updateOffenderBalance(offTxn, offEve.getCreateUserId());
				} else {
					throw new Exception("no operation");
				}
				postTrustGl(offEve);

				if (X.equals(vActStatus)) {
					deductionsService.createDefaultDeductions(offEve.getCaseloadId(),
							offEve.getRootOffenderId().intValue(), offEve.getCreateUserId());
					populateGroupId(offEve.getRootOffenderId().intValue(), offEve.getModifyUserId());
				}
				Integer offTxnCount = insertOffTxn(offEve);
				if (offTxnCount <= 0) {
					throw new Exception("Error: Cannot insert record into Offender Transaction.");
				}
			}
		} catch (Exception e) {
			logger.error("ProcessOcdintakTrust", e);
			returnValue.setSealFlag("2");
			return returnValue;
		}
		return returnValue;
	}

	@Override
	public Integer insertOffTxn(final OffenderBookingEvent offEve) {
		return ocdintakRepository.insertOffTxn(offEve);
	}

	@Override
	public void populateGroupId(final Integer pOffId, final String userName) {
		String vCsldCode;
		Long vGroupId;
		List<OffenderDeductions> offDedList = new ArrayList<OffenderDeductions>();

		offDedList = ocdintakRepository.dedTypeCursor(pOffId);
		for (final OffenderDeductions offDed : offDedList) {
			// get caseLoadCode 477
			vCsldCode = ocdintakRepository.getCaseLoadCode(offDed.getDeductionType());

			// get vGroupId 482
			vGroupId = ocdintakRepository.getGroupId(offDed.getDeductionType());
			if (vCsldCode.contains(BOTH) || vCsldCode.contains(COMM)) {
				// update offender_deductions 489
				ocdintakRepository.updateOffenderDeductions(vGroupId, offDed.getDeductionType(), pOffId, userName);
			}
		}
	}

	@Override
	public void postTrustGl(final OffenderBookingEvent offEve) {

		Integer glSqnc = 0;
		glSqnc = trustService.processGlTransNew(offEve.getCaseloadId(), ADI, null, 0.0, offEve.getpTxnId(), new Date(),
				offEve.getpTxnDesc(), offEve.getpTxnSeq(), OCDINTAK, offEve.getRootOffenderId().intValue(), null, null,
				offEve.getpSubacType(), "", "", "", glSqnc, "",offEve.getCreateUserId());

	}

	// create_new_account Impl 400
	@Override
	public void createNewAccount(final Integer pOffId, final String pCsldId, final Integer pTxnId,
			final String userName) {
		Integer count1 = 0;
		Integer count2 = 0;
		try {
			// Insert offenderTrustAccounts 408
			count1 = ocdintakRepository.insertOffenderTrustAccounts(pOffId, pCsldId, userName);
			if (count1 < 0) {
				throw new Exception("Error: Unable to insert record into table OFFENDER_TRUST_ACCOUNTS.");
			}
			// Insert offender_sub_accounts 429
			count2 = ocdintakRepository.insertOffenderSubAccounts(pOffId, pCsldId, pTxnId, userName);
			if (count2 < 0) {
				throw new Exception("Error: Unable to insert record into table OFFENDER_SUB_ACCOUNTS.");
			}
		} catch (Exception e) {
			logger.error("createNewAccount", e);
		}
	}

	// remove_clos_acct 112
	@Override
	public void removeClosAcct(final OffenderBookingEvent offEve) {
		Integer count1 = 0;
		Integer count2 = 0;
		try {
			// Update Query 121
			count1 = ocdintakRepository.updateOffenSubAcc(offEve);
			if (count1 == 0) {
				String errMsg = "Error: Crediting offender Sub-Account. DOC#:\""
						.concat(offEve.getRootOffenderId().toString()).concat("\" Inst.:\"")
						.concat(offEve.getCaseloadId()).concat("\" Sub-Acnt:\"").concat(offEve.getpSubacType())
						.concat("\"");
				throw new Exception(errMsg);
			}
			// Update OFFENDER_TRUST_ACCOUNTS 149
			count2 = ocdintakRepository.updateOffenderTrustAccounts(offEve.getCaseloadId(),
					offEve.getRootOffenderId().intValue(), offEve.getModifyUserId());
			if (count2 == 0) {
				String errMsg1 = "Error: Crediting offender Balance. DOC#:\""
						.concat(offEve.getRootOffenderId().toString()).concat("\" Inst.:\"")
						.concat(offEve.getCaseloadId());
				throw new Exception(errMsg1);
			}

		} catch (Exception e) {
			logger.error("removeClosAcct", e);
		}

	}

	@Override
	public Map<String, Object> storGlobals(final OffenderBookingEvent offEve) {
		final Map<String, Object> outParams = new HashedMap<String, Object>();
		Double crAc = null;
		final String pTxnType = ADI;
		String vReceiptProdFlag = N;
		List<AccountCodes> crFlagList = new ArrayList<AccountCodes>();
		List<AccountCodes> txnPtgSubList = new ArrayList<AccountCodes>();

		try {
			// crAc and vReceiptProdFlag select operation
			crFlagList = ocdintakRepository.getCrAcAndRecFlag(offEve.getCaseloadId());
			for (final AccountCodes data : crFlagList) {
				crAc = data.getAccountCode().doubleValue();
				vReceiptProdFlag = data.getAccountName();
			}
			// pTxnPtgType and pSubacType select operation
			txnPtgSubList = ocdintakRepository.getPTxnPtgTypeAndSubacType(crAc);
			for (final AccountCodes data : txnPtgSubList) {
				offEve.setpTxnPtgType(data.getTxnPostingType());
				offEve.setpSubacType(data.getSubAccountType());
			}
			// getting p_txn_desc 245
			offEve.setpTxnDesc(ocdintakRepository.getPTxnDesc(pTxnType));
			if (offEve.getpTxnDesc() == null) {
				throw new Exception("No Description found for the Transaction Type ");
			}
			if (Y.equals(vReceiptProdFlag)) {
				// ocdintak.gen_receipt_no 263
				offEve.setpReceiptNo(genReceiptNo(offEve.getCaseloadId(), R));
			} else {
				offEve.setpReceiptNo(null);
			}
			// GENERATE TXN ID 269
			offEve.setpTxnId(ocdintakRepository.getTxnId());

			outParams.put("P_TXN_ID", offEve.getpTxnId());
			outParams.put("P_TXN_PTG_TYPE", offEve.getpTxnPtgType());
			outParams.put("P_SUB_AC_TYPE", offEve.getpSubacType());
			outParams.put("P_TXN_DESC", offEve.getpTxnDesc());
			outParams.put("P_RECEIPT_NO", offEve.getpReceiptNo());
		} catch (Exception e) {
			logger.error("storGlobals ", e);
		}
		return outParams;
	}

	@Override
	public String genReceiptNo(final String pCsldId, final String pTxnusage) {
		Integer vReceiptNo = null;
		Integer vExistFlag = null;
		String retTrimVal = null;
		String returnVal = null;

		try {
			if (R.equals(pTxnusage)) {
				// CHECK IF SEQUENCE EXISTS 310
				final String inputStg = "SEQUENCE_".concat(pCsldId);
				vExistFlag = ocdintakRepository.checkIfSequenceExists(inputStg);
				if (vExistFlag == null || vExistFlag == 0) {
					throw new Exception("Internal Error: No row in table Caseload Sequence Numbers");
				}
				// GENERATE RECEIPT NO FROM SEQ 330
				final String trimCsLdId = pCsldId.trim();
				vReceiptNo = ocdintakRepository.generateReceiptNoFromSeq(trimCsLdId);
				if (vReceiptNo == null) {
					throw new Exception("Internal Error: Unable to get seqence no");
				}
			} else if (D.equals(pTxnusage)) {
				// CHECK IF SEQUENCE EXISTS 352
				final String inputStg = "SEQUENCE_".concat(pCsldId).concat("_D");
				vExistFlag = ocdintakRepository.checkIfSequenceExists(inputStg);
				if (vExistFlag == null || vExistFlag == 0) {
					throw new Exception("Internal Error: No row in table Caseload Sequence Numbers");
				}
				// -- GENERATE RECEIPT NO FROM SEQ -- 372
				final String trimCsLdId = pCsldId.trim();
				vReceiptNo = ocdintakRepository.generateReceiptNoFromSeqElBlock(trimCsLdId);
				if (vReceiptNo == null) {
					throw new Exception("Internal Error: Unable to get seqence no");
				}
			}
			retTrimVal = vReceiptNo.toString().trim();
			returnVal = pCsldId.concat(retTrimVal);
		} catch (Exception e) {
			logger.error("genReceiptNo ", e);
		}
		return returnVal;
	}

	@Override
	public Integer instOffBooking(final OffenderBookingEvent object, final String userName) {
		object.setCreateUserId(userName);
		return ocdintakRepository.instOffBooking(object);
	}

	@Override
	public Integer instOffBookAgyLoc(final OffenderBookingEvent object, final String userName) {
		object.setCreateUserId(userName);
		return ocdintakRepository.instOffBookAgyLoc(object);
	}

	@Override
	public Integer insOffSchedule(final OffenderResidence object) {
		try {
			Integer offBookId = null;
			OffenderIndSchedules schedule=new OffenderIndSchedules();
			BeanUtils.copyProperties(object, schedule);
			if(object.getOffenderBookId()!=null) {
				schedule.setOffenderBookId(object.getOffenderBookId().intValue());
			}else {
				
				OffenderBookingEvent obj = new OffenderBookingEvent();
				BigDecimal offId = null;
				if (object.getRootOffenderId() != null) {
					offId = object.getRootOffenderId();
					obj.setRootOffenderId(Long.valueOf(offId.toString()));
					offBookId = ocdintakRepository.getLatestBooking(obj);
					schedule.setOffenderBookId(offBookId);
				}
			}
			schedule.setEventClass("COMM");
			schedule.setEventStatus("SCH");
			schedule.setEventType("APP");
			schedule.setEventSubType("REPORT_IN");
			schedule.setStartTime(object.getEventTime());
			ocdintakRepository.vOffenderAllSchedules(schedule);
		} catch (Exception e) {
			logger.error("insOffSchedule", e);
		}
		return 1;
	}

}
