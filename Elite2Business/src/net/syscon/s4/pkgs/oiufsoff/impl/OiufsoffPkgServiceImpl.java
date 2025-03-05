package net.syscon.s4.pkgs.oiufsoff.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.pkgs.oiufsoff.OiufsoffPkgRepository;
import net.syscon.s4.pkgs.oiufsoff.OiufsoffPkgService;

@Service
public class OiufsoffPkgServiceImpl implements OiufsoffPkgService {
	private static final Logger logger = LogManager.getLogger(OiufsoffPkgServiceImpl.class.getName());
	@Autowired
	private OiufsoffPkgRepository oiufsoffRepository;

	private static final String N = "N";
	private static final String Y = "Y";
	private static final String A = "A";

	@Override
	public String getCaseloadType(final String caseLodId) {
		String caseLoadId = oiufsoffRepository.getCaseloadType(caseLodId);
		if (caseLoadId == null) {
			caseLoadId = "INST";
		}
		return caseLoadId;
	}

	@Override
	public String detmLivUnitId(final BigDecimal pLv1Id, final BigDecimal pLv2Id, final BigDecimal pLv3Id) {
		String detmLivUnitId = null;
		if (pLv1Id != null) {
			detmLivUnitId = pLv1Id.toString();
		} else if (pLv2Id != null) {
			detmLivUnitId = pLv2Id.toString();
		} else if (pLv3Id != null) {
			detmLivUnitId = pLv3Id.toString();
		} else {
			detmLivUnitId = null;
		}
		return detmLivUnitId;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getInstitutionOffenders(final OiufsoffGetGeneralOffenders bean) {
		String lvLivingUnitId = null;
		String lvActiveFlag = null;
		String lvcaseLoadType = null;
		List<OiufsoffGetGeneralOffenders> returnInstitutionOffenders=new ArrayList<OiufsoffGetGeneralOffenders>();
		lvLivingUnitId = detmLivUnitId(bean.getpLv1Id(), bean.getpLv2Id(), bean.getpLv3Id());
		lvcaseLoadType = getCaseloadType(bean.getpCaseloadId());
		if (bean.getpActiveFlag().equals(A)) {
			lvActiveFlag = Y;
		} else if (bean.getpActiveFlag().equals(N)) {
			lvActiveFlag = N;
		}
		bean.setLvActiveFlag(lvActiveFlag);
		bean.setLvLivingUnitId(lvLivingUnitId);
		bean.setLvcaseLoadType(lvcaseLoadType);
		try {
			returnInstitutionOffenders=oiufsoffRepository.getInstitutionOffenders(bean);
		}catch (Exception e) {
			logger.error("getInstitutionOffenders :" + e);
		}
		return returnInstitutionOffenders;

	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getCommunityOffenders(final OiufsoffGetGeneralOffenders bean) {
		String lvCaseLoadType = null;
		String lvActivFlag = null;
		if (bean.getpActiveFlag().equals(A)) {
			lvActivFlag = Y;
		} else if (bean.getpActiveFlag().equals(N)) {
			lvActivFlag = N;
		} else {
			lvActivFlag = null;
		}
		lvCaseLoadType = getCaseloadType(bean.getpCaseloadId());
		bean.setLvcaseLoadType(lvCaseLoadType);
		bean.setLvActiveFlag(lvActivFlag);
		return oiufsoffRepository.getCommunityOffenders(bean);

	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getTrustOffenders(final OiufsoffGetGeneralOffenders bean) {
		String lvLivingUnitId = null;
		String lvActiveFlag = null;
		String lvcaseLoadType = null;
		lvLivingUnitId = detmLivUnitId(bean.getpLv1Id(), bean.getpLv2Id(), bean.getpLv3Id());
		lvcaseLoadType = getCaseloadType(bean.getpCaseloadId());
		if (bean.getpActiveFlag().equals(A)) {
			lvActiveFlag = Y;
		} else if (bean.getpActiveFlag().equals(N)) {
			lvActiveFlag = N;
		}
		bean.setLvcaseLoadType(lvcaseLoadType);
		bean.setLvActiveFlag(lvActiveFlag);
		bean.setLvLivingUnitId(lvLivingUnitId);
		return oiufsoffRepository.getTrustOffenders(bean);

	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getPayrollOffenders(final OiufsoffGetGeneralOffenders bean) {
		String lvLivingUnitId = null;
		String lvActiveFlag = null;
		String lvcaseLoadType = null;
		lvLivingUnitId = detmLivUnitId(bean.getpLv1Id(), bean.getpLv2Id(), bean.getpLv3Id());
		lvcaseLoadType = getCaseloadType(bean.getpCaseloadId());
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<>();
		
		bean.setLvActiveFlag(lvActiveFlag);
		bean.setLvLivingUnitId(lvLivingUnitId);
		bean.setLvcaseLoadType(lvcaseLoadType);
		if (bean.getpActiveFlag().equals(A)) {
			lvActiveFlag = Y;
			list = oiufsoffRepository.getPayrollOffenders(bean);
		} else if (bean.getpActiveFlag().equals(N)) {
			lvActiveFlag = N;
			list = oiufsoffRepository.getPayrollOffendersOne(bean);
		} else {
			list = oiufsoffRepository.getPayrollOffendersTwo(bean);
		}
		return list;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getCommissaryOffenders(final OiufsoffGetGeneralOffenders bean) {
		// String lvLivingUnitId = detmLivUnitId(pLv1Id, pLv2Id, pLv3Id);
		String lvActiveFlag = null;
		String lvcaseLoadType = null;
		lvcaseLoadType = getCaseloadType(bean.getpCaseloadId());
		if (bean.getpActiveFlag().equals(A)) {
			lvActiveFlag = Y;
		} else if (bean.getpActiveFlag().equals(N)) {
			lvActiveFlag = N;
		} else {
			lvActiveFlag = null;
		}
		bean.setLvActiveFlag(lvActiveFlag);
		bean.setLvcaseLoadType(lvcaseLoadType);
		return oiufsoffRepository.getCommissaryOffenders(bean);
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getGeneralOffenders(final OiufsoffGetGeneralOffenders bean) {
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<>();
		if (bean.getpReportApplnCode()!=null && bean.getpReportApplnCode().equals("INST")) {
			list = getInstitutionOffenders(bean);
		} else if (bean.getpReportApplnCode()!=null && bean.getpReportApplnCode().equals("COMM")) {
			list = getCommunityOffenders(bean);
		} else if (bean.getpReportApplnCode()!=null && bean.getpReportApplnCode().equals("TRUST")) {
			list = getTrustOffenders(bean);
		} else if (bean.getpReportApplnCode()!=null && bean.getpReportApplnCode().equals("PAY")) {
			list = getPayrollOffenders(bean);
		} else if (bean.getpReportApplnCode()!=null && bean.getpReportApplnCode().equals("COMMISSARY")) {
			list = getCommissaryOffenders(bean);
		} else {
			list = getInstitutionOffenders(bean);
		}
		return list;
	}

}
