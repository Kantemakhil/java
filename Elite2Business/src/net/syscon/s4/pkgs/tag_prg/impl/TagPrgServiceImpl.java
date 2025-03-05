package net.syscon.s4.pkgs.tag_prg.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.pkgs.tag_prg.TagPrgRepository;
import net.syscon.s4.pkgs.tag_prg.TagPrgService;

@Service
public class TagPrgServiceImpl implements TagPrgService {
	@Autowired
	private TagPrgRepository tagPrgRepository;
	private static Logger logger = LogManager.getLogger(TagPrgServiceImpl.class.getName());

	private static final String TEAM = "TEAM";
	private static final String PER = "PER";
	private static final String CORP = "CORP";
	private static final String AGY = "AGY";
	private static final String OUT = "OUT";
	private static final String COMM = "COMM";
	private static final String INT_MOV = "INT_MOV";
	private static final String EXT_MOV = "EXT_MOV";

	@Override
	public String getProviderName(final String providerPartyClass, final Long providerPartyId,
			final String providerPartyCode) {
		String providerName = null;
		if (providerPartyClass != null) {
			if (providerPartyClass.equals(TEAM)) {
				providerName = tagPrgRepository.getProviderNameFromTeams(providerPartyId);
			} else if (providerPartyClass.equals(PER)) {
				providerName = tagPrgRepository.getProviderNameFromPerson(providerPartyId);
			} else if (providerPartyClass.equals(CORP)) {
				providerName = tagPrgRepository.getProviderNameFromCorporates(providerPartyId);
			} else if (providerPartyClass.equals(AGY)) {
				providerName = tagPrgRepository.getProviderNameFromAgencyLocation(providerPartyCode);
			}
		}
		return providerName;
	}

	@Override
	public Integer courseVacancy(final Long courseActivityId) {
		Long lvcapacity = null;
		Long lvRegistration = null;
		try {
			lvcapacity = tagPrgRepository.getCapacity(courseActivityId);
			if (lvcapacity == null) {
				lvcapacity = (long) 0;
			}
			lvRegistration = tagPrgRepository.getCount(courseActivityId);

		} catch (Exception e) {
			logger.error("courseVacancy" + e);
			return 0;
		}
		return (int) (lvcapacity - lvRegistration);
	}

	@Override
	public String prgApptEventClass(final Long offenderBookId, final String agyLocId) {
		String lAcpApptEventClass = null;
		OffenderBookings offBkgs = new OffenderBookings();
		try {
			offBkgs = tagPrgRepository.getRecFrmOffBkgs(offenderBookId);
			if (OUT.equalsIgnoreCase(offBkgs.getAgyLocId()) || offBkgs.getAgyLocId() == null) {
				lAcpApptEventClass = COMM;
			} else {
				if (offBkgs.getAgyLocId().equalsIgnoreCase(agyLocId))
					lAcpApptEventClass = INT_MOV;
				else
					lAcpApptEventClass = EXT_MOV;
			}
		} catch (Exception e) {
			logger.error("prgApptEventClass", e);
			lAcpApptEventClass = null;
		}
		return lAcpApptEventClass;
	}

	@Override
	public BigDecimal creditHours(BigDecimal offenderBookId, BigDecimal seq, BigDecimal offenderSentCondId) {
		BigDecimal cr = null;
		if( offenderSentCondId == null) {
			cr = tagPrgRepository.creditHoursWithoutSentCond(offenderBookId, seq);
		} else {
			cr = tagPrgRepository.creditHoursWithSentCond(offenderBookId, seq, offenderSentCondId);
		}
		return cr;
	}
}
