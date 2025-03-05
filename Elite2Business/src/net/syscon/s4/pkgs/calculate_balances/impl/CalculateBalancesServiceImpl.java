package net.syscon.s4.pkgs.calculate_balances.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.au.VCbSentTerms;
import net.syscon.s4.inst.legals.au.VOffBalCals;
import net.syscon.s4.inst.legalscreens.sentenceadministration.beans.OffBalanceCalcCustodyDtl;
import net.syscon.s4.pkgs.calculate_balances.CalculateBalancesRepository;
import net.syscon.s4.pkgs.calculate_balances.CalculateBalancesService;
import net.syscon.s4.pkgs.tag_legal_cases.TagLegalCasesService;

@Service
public class CalculateBalancesServiceImpl implements CalculateBalancesService {

	@Autowired
	private CalculateBalancesRepository calculateBalancesRepository;
	@Autowired
	private TagLegalCasesService tagLegalCasesService;
	private static Logger logger = LogManager.getLogger(CalculateBalancesServiceImpl.class.getName());

	private static final String ADD_DAYS_RSN = "ADD_DAYS_RSN";
	private static final String Y = "Y";
	private BigDecimal offenderId;
	private Long offenderBookId;
	private Date effectiveDate;

	@Override
	public List<VCbSentTerms> setOffenderId(final VOffBalCals param) {
		final List<VCbSentTerms> list = new ArrayList<>();

		try {
			this.offenderId = param.getOffenderId();
			this.offenderBookId = param.getOffenderBookId();
			this.effectiveDate = param.getEffectiveDate();
		} catch (final Exception e) {
			logger.error("setOffenderId :", e);
		}
		return list;
	};

	@Override
	public Map<String, Object> getCalBalDtls(final Long pOffBalCalcId, final Date pAdmDate, final Date pReleaseDate) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		OffBalanceCalcCustodyDtl offBalDtl = null;
		Long pAddDays = null;
		String pAddRsn = null;
		String pRsnDesc = null;
		String pRecFound = null;
		try {
			offBalDtl = calculateBalancesRepository.lvCalcBalCur(pOffBalCalcId, pAdmDate, pReleaseDate);
			pAddDays = offBalDtl.getAddittionalDays();
			pAddRsn = offBalDtl.getAddittionalDaysReason();

			if (!pAddRsn.isEmpty() && Optional.ofNullable(pAddRsn).isPresent()) {
				pRsnDesc = tagLegalCasesService.getRcDescription(ADD_DAYS_RSN, pAddRsn);
				pRecFound = Y;
			}
			returnMap.put("P_ADD_DAYS", pAddDays);
			returnMap.put("P_ADD_RSN", pAddRsn);
			returnMap.put("P_RSN_DESC", pRsnDesc);
			returnMap.put("P_REC_FOUND", pRecFound);
		} catch (Exception e) {
			logger.error("getCalBalDtls :", e);
		}
		return returnMap;
	}
}
