package net.syscon.s4.pkgs.trust_gj.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.pkgs.tag_utils.TagUtilsService;
import net.syscon.s4.pkgs.trust.impl.TrustRepositoryImpl;
import net.syscon.s4.pkgs.trust_gj.TrustGjRepository;
import net.syscon.s4.pkgs.trust_gj.TrustGjService;

@Service("TrustGjServiceImpl_pkg")
@Transactional
public class TrustGjServiceImpl implements TrustGjService {

	private static final String CLIENT = "CLIENT";

	private static final String T_OP_CL_PER = "T_OP_CL_PER";
	private static final String ONE = "1";
	private static Logger logger = LogManager.getLogger(TrustGjServiceImpl.class.getName());
	@Autowired
	private TrustGjRepository trustGjRepository;

	@Autowired
	private TagUtilsService tagUtilsService;

	@Override
	public void reopenClosedPeriod(final String caseloadId, final Date txnEntryDate, final String userName) {

		try {
			if (getAccountPeriod(txnEntryDate) < getAllowedBackDatedPeriod(caseloadId)) {
				// RAISE_APPLICATION_ERROR(-20000, 'Error: Cannot re-open account prior this
				// account period.' || GET_ALLOWED_BACK_DATED_PERIOD(P_CASELOAD_ID) || '.');
			}
			final String list = trustGjRepository.lockRecordCur(caseloadId, txnEntryDate);
			String lvDummy = null;
			//for (final Integer val : list) {
				lvDummy = list;
			//}
		} catch (Exception e) {
			logger.error("reopenClosedPeriod :", e);
		}
		trustGjRepository.caseloadAccountPeriods(caseloadId, txnEntryDate, userName);

	}

	@Override
	public Long getAccountPeriod(final Date date) {
		final Long list = trustGjRepository.accountPeriodCur(date);
		Long lvPeriodId = null;
		//for (Long l : list) {
			lvPeriodId = list;
		//}
		return lvPeriodId;
	}

	@Override
	public Integer getAllowedBackDatedPeriod(final String caseloadId) {
		Integer lvPeriod = getDefinedBackDatedPeriod();
		lvPeriod = getLastClosedPeriod(caseloadId, lvPeriod).intValue();
		if (lvPeriod == 0) {
			lvPeriod = trustGjRepository.trustgtSelectMax(caseloadId).intValue();
		}
		return lvPeriod;
	}

	@Override
	public Integer getDefinedBackDatedPeriod() {
		String value=tagUtilsService.getSysProfile(CLIENT, T_OP_CL_PER, ONE);
		return value!=null && !value.equals("")?Integer.parseInt(value):null;
		//return Integer.parseInt(tagUtilsService.getSysProfile(CLIENT, T_OP_CL_PER, ONE))
	}

	@Override
	public BigDecimal getLastClosedPeriod(final String caseloadId, final Integer pAcccountPeriodId) {
		return trustGjRepository.trustgtSelect(caseloadId, pAcccountPeriodId);

	}

	@Override
	public BigDecimal getCurrentBalance(String caseLoadId, Integer accountCode,String userName) {
		return trustGjRepository.getCurrentBalance(caseLoadId, accountCode, userName);
	}

}