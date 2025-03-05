package net.syscon.s4.pkgs.otdclina.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;
import net.syscon.s4.pkgs.otdclina.OtdclinaPkgRepository;
import net.syscon.s4.pkgs.otdclina.OtdclinaPkgService;

@Service
public class OtdclinaPkgServiceImpl implements OtdclinaPkgService {

	@Autowired
	private OtdclinaPkgRepository otdclinaRepository;

	private BigDecimal totalBalance = new BigDecimal(0);

	private static final String lvInsertRecord = "N";
	private static final String ALL = "ALL";
	private static final String Y = "Y";
	private static final String N = "N";
	private static final String OUT = "OUT";
	private static final String TRN = "TRN";
	private static final String NOTI = "NOTI";
	private static final String ZERO = "ZERO";
	private Integer lvExists;

	private static Logger logger = LogManager.getLogger(OtdclinaPkgServiceImpl.class.getName());

	@Override
	@Transactional
	public BigDecimal populateTable(final OffenderTrustAccountsTemp searchRecord, final String userName) {
		 List<OffenderTrustAccounts> list  = new ArrayList<OffenderTrustAccounts>();
			Date lvMovementDate = null;
			Long lvOffenderBookId = null;
			String lvAgyLocId = null;
			String lvActiveFlag = null;
			String inOutStatus = null;
			String bookingStatus = null;
		try {
			searchRecord.setCreateUserId(userName);
			otdclinaRepository.offenderTrustAccountsTemp(searchRecord.getSessionId(), searchRecord.getCaseloadId());
			if (searchRecord.getSelectionMethod() != null && searchRecord.getSelectionMethod().equals(ALL)) {

				 list = otdclinaRepository.allC(searchRecord);
				for (final OffenderTrustAccounts obj : list) {
					final OffenderTrustAccountsTemp temp = new OffenderTrustAccountsTemp();
					BeanUtils.copyProperties(obj, temp);
					temp.setCreateDateTime(searchRecord.getCreateDateTime());
					temp.setModifyDateTime(searchRecord.getModifyDateTime());
					temp.setCaseloadId(searchRecord.getCaseloadId());
					final List<OffenderExternalMovements> mvlist = otdclinaRepository.maxMovementC(temp);
					for (final OffenderExternalMovements max : mvlist) {
						lvOffenderBookId = max.getOffenderBookId();
						lvAgyLocId = max.getToAgyLocId();
						lvMovementDate = max.getMovementDate();
						lvActiveFlag = max.getActiveFlag();
						inOutStatus = max.getInOutStatus();
						bookingStatus = max.getBookingStatus();
					} // for loop
					if (lvMovementDate != null) {
						if (searchRecord.getSealFlag() != null && searchRecord.getSealFlag().equals(Y)) {
							if (temp.getHoldBalance() == null) {
								java.math.BigDecimal i1 = java.math.BigDecimal.ZERO;
								temp.getHoldBalance();
								i1 = BigDecimal.ZERO;
								if (i1 == java.math.BigDecimal.ZERO) {

								} else {
									totalBalance = totalBalance.add(temp.getCurrentBalance());
								}

							}

						}
						if (lvActiveFlag != null && inOutStatus !=null && lvActiveFlag.equals(Y) && inOutStatus.equals(OUT)){

						} else {
							searchRecord.setAgyLocId(lvAgyLocId);
							searchRecord.setMovementDate(lvMovementDate);
							searchRecord.setOffenderBookId(lvOffenderBookId!=null? BigDecimal.valueOf(lvOffenderBookId) : null);
							searchRecord.setOffenderId(obj.getOffenderId());
							searchRecord.setAccountClosedFlag(obj.getAccountClosedFlag());
							searchRecord.setOffenderIdDisplay(obj.getOffenderIdDisplay());
							searchRecord.setHoldBalance(BigDecimal.valueOf(obj.getHoldBalance()));
							searchRecord.setCurrentBalance(BigDecimal.valueOf(obj.getCurrentBalance()));
							searchRecord.setNotifyDate(obj.getNotifyDate());
							searchRecord.setLastName(obj.getLastName());
							searchRecord.setFirstName(obj.getFirstName());
							otdclinaRepository.offenderTrustAccountsTemp(searchRecord);
						}
					}
				}
			} // if
			if (searchRecord.getSelectionMethod() != null && searchRecord.getSelectionMethod().equals(ZERO)) {
				list = otdclinaRepository.zeroC(searchRecord);
				for (OffenderTrustAccounts zeroObj : list) {
					searchRecord.setOffenderId(zeroObj.getOffenderId());
					final List<OffenderExternalMovements> mvlist = otdclinaRepository.maxMovementC(searchRecord);
					for (final OffenderExternalMovements max : mvlist) {
						lvOffenderBookId = max.getOffenderBookId();
						lvAgyLocId = max.getToAgyLocId();
						lvMovementDate = max.getMovementDate();
						lvActiveFlag = max.getActiveFlag();
						inOutStatus = max.getInOutStatus();
						bookingStatus = max.getBookingStatus();
					}

					if (lvMovementDate != null) {
						if (searchRecord.getSealFlag() != null && searchRecord.getSealFlag().equals(Y)) {
							if (searchRecord.getHoldBalance() == null) {
								java.math.BigDecimal i1 = java.math.BigDecimal.ZERO;
								searchRecord.getHoldBalance();
								i1 = BigDecimal.ZERO;
								if (i1 == java.math.BigDecimal.ZERO) {

								} else {
									totalBalance = totalBalance.add(searchRecord.getCurrentBalance());
								}
							}
						}

						if (lvActiveFlag != null && inOutStatus != null && lvActiveFlag.equals(Y)
								&& inOutStatus.equals(OUT)) {
						} else {
							searchRecord.setAgyLocId(lvAgyLocId);
							searchRecord.setMovementDate(lvMovementDate);
							searchRecord.setOffenderBookId(lvOffenderBookId != null ? BigDecimal.valueOf(lvOffenderBookId) : null);
							searchRecord.setOffenderId(zeroObj.getOffenderId());
							searchRecord.setAccountClosedFlag(zeroObj.getAccountClosedFlag());
							searchRecord.setOffenderIdDisplay(zeroObj.getOffenderIdDisplay());
							searchRecord.setHoldBalance(BigDecimal.valueOf(zeroObj.getHoldBalance()));
							searchRecord.setCurrentBalance(BigDecimal.valueOf(zeroObj.getCurrentBalance()));
							searchRecord.setNotifyDate(zeroObj.getNotifyDate());
							searchRecord.setLastName(zeroObj.getLastName());
							searchRecord.setFirstName(zeroObj.getFirstName());
							otdclinaRepository.offenderTrustAccountsTemp(searchRecord);
						}
					}
				}
			} // if zero
					
			if (searchRecord.getSelectionMethod() != null && searchRecord.getSelectionMethod().equals(NOTI)) {
				list = otdclinaRepository.notifyC(searchRecord);
				for (OffenderTrustAccounts notifyObj : list) {
					searchRecord.setOffenderId(notifyObj.getOffenderId());
					final List<OffenderExternalMovements> mvlist = otdclinaRepository.maxMovementC(searchRecord);
					for (final OffenderExternalMovements max : mvlist) {
					lvOffenderBookId = max.getOffenderBookId();
					lvAgyLocId = max.getToAgyLocId();
					lvMovementDate = max.getMovementDate();
					lvActiveFlag = max.getActiveFlag();
					inOutStatus = max.getInOutStatus();
					bookingStatus = max.getBookingStatus();
				}

				if (lvMovementDate != null) {
					if (searchRecord.getSealFlag() != null && searchRecord.getSealFlag().equals(Y)) {
						if (searchRecord.getHoldBalance() == null) {
							java.math.BigDecimal i1 = java.math.BigDecimal.ZERO;
							searchRecord.getHoldBalance();
							i1 = BigDecimal.ZERO;
							if (i1 == java.math.BigDecimal.ZERO) {

							} else {
								totalBalance = totalBalance.add(searchRecord.getCurrentBalance());
							}
						}
					}
					
				    if (lvActiveFlag.equals(Y) && inOutStatus.equals(OUT)) {
					} else {
						searchRecord.setAgyLocId(lvAgyLocId);
						searchRecord.setMovementDate(lvMovementDate);
						searchRecord.setOffenderBookId(lvOffenderBookId != null ? BigDecimal.valueOf(lvOffenderBookId) : null);
						searchRecord.setOffenderId(notifyObj.getOffenderId());
						searchRecord.setAccountClosedFlag(notifyObj.getAccountClosedFlag());
						searchRecord.setOffenderIdDisplay(notifyObj.getOffenderIdDisplay());
						searchRecord.setHoldBalance(BigDecimal.valueOf(notifyObj.getHoldBalance()));
						searchRecord.setCurrentBalance(BigDecimal.valueOf(notifyObj.getCurrentBalance()));
						searchRecord.setNotifyDate(notifyObj.getNotifyDate());
						searchRecord.setLastName(notifyObj.getLastName());
						searchRecord.setFirstName(notifyObj.getFirstName());
						otdclinaRepository.offenderTrustAccountsTemp(searchRecord);
					}
				}
			}//end loop
		} // if NOTI
				
		} catch (Exception e) {
			logger.error("Exception in OtdclinaServiceImpl", e);
		}
		return totalBalance;
	}// method
}// class
