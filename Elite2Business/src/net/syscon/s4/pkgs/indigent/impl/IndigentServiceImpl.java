package net.syscon.s4.pkgs.indigent.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.pkgs.indigent.IndigentRepository;
import net.syscon.s4.pkgs.indigent.IndigentService;
import net.syscon.s4.pkgs.trust.TrustService;

@Service
public class IndigentServiceImpl implements IndigentService {

	@Autowired
	private IndigentRepository indigentRepository;

	@Autowired
	private TrustService trustService;

	private static Logger logger = LogManager.getLogger(IndigentRepositoryImpl.class.getName());

	private static final String YES = "Y";
	private static final String NO = "N";

	@Override
	public String indFlagformula(final BigDecimal pOffId, final String pCsldId, final String pAgyLocId) {
		String csldType;
		Integer lvTrustAcc;
		String lvCsldIdExists;
		String lvIndigencyLevel;
		String lvIndDay;
		Double lvSubAcctBalance;
		Date lvIndigentDate;
		Date indDate;
		BigDecimal indDays;
		String indFlag = null;
		try {
			csldType = trustService.getCaseloadType(pCsldId);
			lvCsldIdExists = indigentRepository.getChkCaseloadC(pCsldId);
			final List<OffenderSubAccounts> retList = indigentRepository.getChkIndAcCTrstAccCode(pOffId, pCsldId,
					csldType);
			if (lvCsldIdExists != null) {

				for (final OffenderSubAccounts bo : retList) {
					OffenderSubAccounts indDateNDays = indigentRepository.getCurChkIndIndDateNDays(pOffId, pCsldId,
							pAgyLocId, bo.getTrustAccountCode());
					indDate = indDateNDays.getIndDate();

					indDays = indDateNDays.getIndDays();
					final Integer diff = indigentRepository.getDiffBtSysNIndDate(indDate);
					if (indDate == null || (indDays.intValue() - diff) > 0) {
						indFlag = NO;
						break;
					} else {
						indFlag = YES;
					}
				}
			} else {
				final SystemProfiles obj = indigentRepository.getSystemProfileC();
				lvIndigencyLevel = obj.getProfileValue();
				lvIndDay = obj.getProfileValue2();
				lvTrustAcc = indigentRepository.getAccCode();

				lvSubAcctBalance = indigentRepository.getSumOffSubActBalC(pOffId, lvTrustAcc);

				if (lvSubAcctBalance <= Double.valueOf(lvIndigencyLevel)) {
					lvIndigentDate = indigentRepository.getMaxIndDateC(pOffId, lvTrustAcc);
					indDays = indigentRepository.getMaxIndDays(pOffId, lvTrustAcc, lvIndigentDate);

					if (lvIndigentDate == null) {
						lvIndigentDate = new Date();
					}

					indDate = lvIndigentDate;
					if (indDays.intValue() != 0) {
						indDays = new BigDecimal(lvIndDay);
					}
				} else {
					indDate = null;
					indDays = BigDecimal.valueOf(0);
				}

				final Integer getDateDiff = indigentRepository.getDiffBtSysNIndDate(indDate);
				if (indDate == null || getDateDiff < indDays.intValue() && indDays.intValue() != 0) {
					indFlag = NO;
				} else if (getDateDiff >= indDays.intValue()) {
					indFlag = YES;
				}

			}
		} catch (Exception e) {
			logger.error("indFlagformula: ", e);
		}
		if (indFlag != null)
			return indFlag;
		else
			return NO;
	}
}
