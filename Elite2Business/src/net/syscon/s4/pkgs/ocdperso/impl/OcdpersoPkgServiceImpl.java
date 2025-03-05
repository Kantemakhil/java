package net.syscon.s4.pkgs.ocdperso.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.pkgs.ocdperso.OcdpersoPkgRepository;
import net.syscon.s4.pkgs.ocdperso.OcdpersoPkgService;
import net.syscon.s4.triggers.AddressesT1Service;
import net.syscon.s4.triggers.AddressesT3Service;
import net.syscon.s4.triggers.AddressesTjnService;
import net.syscon.s4.triggers.AddressesTwfService;
import net.syscon.s4.triggers.OffenderVisitsT1Service;

@Service
public class OcdpersoPkgServiceImpl implements OcdpersoPkgService {

	@Autowired
	private OcdpersoPkgRepository ocdpersoRepository;
	@Autowired
	private OffenderVisitsT1Service offenderVisitsT1Service;
	@Autowired
	private AddressesT1Service addressesT1Service;
	@Autowired
	private AddressesT3Service addressesT3Service;
	@Autowired
	private AddressesTwfService addressesTwfService;
	@Autowired
	private AddressesTjnService addressesTjnService;
	private static Logger logger = LogManager.getLogger(OcdpersoPkgServiceImpl.class.getName());

	private static final String INSERT = "INSERT";

	@Override
	public void cancelFutureVisits(final Long pOffenderBookId, final Long pPersonId, final String userName) {
		final List<OffenderVisitVisitors> returnList = ocdpersoRepository.getfutureVisits(pOffenderBookId, pPersonId);

		returnList.forEach(bo -> {
			final Integer lvCount = ocdpersoRepository.getCount(bo.getOffenderVisitId());
			if (lvCount > 1) {
				ocdpersoRepository.updateOffVisitVisitorsOne(bo.getOffenderVisitId(), pPersonId, userName);
			} else {
				offenderVisitsT1Service.offenderVisitsT1("CANC");
				ocdpersoRepository.updateOffenderVisitSecond(bo.getOffenderVisitId(), userName);
				ocdpersoRepository.updateOffVisitVisitorsThird(bo.getOffenderVisitId(), userName);
			}
		});
	}

	@Override
	public OffenderContactPersons getPersonNames(final Long personId) {
		final OffenderContactPersons bean = new OffenderContactPersons();
		List<Persons> namesList = null;
		// 33 get_names_cur
		namesList = ocdpersoRepository.getNamesCur(personId);

		if (namesList != null) {
			for (Persons persons : namesList) {
				bean.setLastName(persons.getLastName());
				bean.setFirstName(persons.getFirstName());
				bean.setMiddleName(persons.getMiddleName());
			}
		}
		return bean;
	}

	@Override
	public Integer copyOffAddr(final BigDecimal pRootOffId, final Long pPersonId, final String userName) {
		Integer lvAddressId = null;
		Addresses addressT1Object = null;
		Addresses addressT3Object = null;
		try {
			lvAddressId = ocdpersoRepository.getNextAddressId();

			// Calling AddressT1 Trigger
			addressT1Object = ocdpersoRepository.getAddressT1Object(pRootOffId);
			addressesT1Service.addresesT1Trigger("PER", pPersonId, addressT1Object.getOwnerSeq(),
					addressT1Object.getOwnerCode());

			ocdpersoRepository.InsertOpCopyOffAddr(lvAddressId, pRootOffId, pPersonId, userName);

			// Calling AddressT3 Trigger
			addressT3Object = ocdpersoRepository.getAddressT3Object(lvAddressId);
			addressesT3Service.AddresesT3Trigger(null, addressT3Object);

			// Calling AddressTwf Trigger
			addressesTwfService.addressesTwf(addressT3Object);

			// Calling AddressTjn Trigger
			//addressesTjnService.addressesTjn(addressT3Object, null, INSERT);

		} catch (Exception e) {
			lvAddressId = 0;
			logger.error("getNamesCur", e);
		}
		return lvAddressId;
	}

}
