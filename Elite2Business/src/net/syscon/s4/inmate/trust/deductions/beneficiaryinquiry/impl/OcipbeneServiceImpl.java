package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.BankChequeBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.PersonAddressV;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcipbeneRepository;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcipbeneService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;

/**
 * Class OcipbeneServiceImpl
 */
@Service
public class OcipbeneServiceImpl extends BaseBusiness implements OcipbeneService {

	@Autowired
	private OcipbeneRepository ocipbeneRepository;

	@Autowired
	private EliteDateService dateService;

	/**
	 * Creates new OcipbeneServiceImpl class Object
	 */
	public OcipbeneServiceImpl() {
		// OcipbeneServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public PersonAddressV perPostQuery(final Persons paramBean) {

		final PersonAddressV objPersonsAdd = ocipbeneRepository.perPostQuery(paramBean);

		if (objPersonsAdd != null) {
			objPersonsAdd.setStreetInformation(
					objPersonsAdd.getStreet() != null ? objPersonsAdd.getStreet().toUpperCase() : null);
			objPersonsAdd.setCityDesc(
					objPersonsAdd.getCityDesc() != null ? objPersonsAdd.getCityDesc().toUpperCase() : null);
			objPersonsAdd.setStateDesc(
					objPersonsAdd.getStateDesc() != null ? objPersonsAdd.getStateDesc().toUpperCase() : null);
			objPersonsAdd.setCountryDesc(
					objPersonsAdd.getCountryDesc() != null ? objPersonsAdd.getCountryDesc().toUpperCase() : null);
			if (objPersonsAdd.getStreetDirection() != null) {
				objPersonsAdd.setStreetInformation(objPersonsAdd.getStreetInformation() != null
						? objPersonsAdd.getStreetInformation() + " ," + objPersonsAdd.getStreetDirection().toUpperCase()
						: objPersonsAdd.getStreetDirection().toUpperCase());
			}
		}
		return objPersonsAdd;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Persons> perExecuteQuery(final Persons searchRecord) {
		final List<Persons> lstPersons = (List<Persons>) ocipbeneRepository.perExecuteQuery(searchRecord);

		if (lstPersons != null && lstPersons.size() > 0) {
			for (final Persons objPersons : lstPersons) {
				final PersonAddressV address = perPostQuery(objPersons);

				if (address != null) {
					objPersons.setSuiteNumber(address.getSuiteNumber());
					objPersons.setStreetInformation(address.getStreetInformation());
					objPersons.setZipPostalCode(address.getZipPostalCode());
					objPersons.setCityDesc(address.getCityDesc());
					objPersons.setCountryDesc(address.getCountryDesc());
					objPersons.setStateDesc(address.getStateDesc());
				}
			}
		}
		return lstPersons;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER
	 *
	 */
	@Transactional
	public Integer perCommit(final PersonsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final Persons objPersons : commitBean.getUpdateList()) {
				if ("Y".equals(objPersons.getSuspendedFlag())) {
					objPersons.setSuspendedFlag("N");
					objPersons.setSuspendedDate(null);
				} else {
					objPersons.setSuspendedFlag("Y");
					objPersons.setSuspendedDate(dateService.getDBTime());
				}
			}
			liReturn = ocipbeneRepository.perUpdatePersons(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries searchRecord) {
		final List<OffenderBeneficiaries> lstOffBen = ocipbeneRepository.offBncExecuteQuery(searchRecord);
		for (final OffenderBeneficiaries objOffBen : lstOffBen) {
			if (objOffBen.getOffenderDeductionId() != null) {
				final OffenderDeductions objOffDed = ocipbeneRepository.cgfkchkOffBncOffBncOff(objOffBen);
				objOffBen.setDeductionType(objOffDed.getDeductionType());
				objOffBen.setMaxTotalAmount(objOffDed.getMaxTotalAmount());
				objOffBen.setMaxMontlyAmount(objOffDed.getMaxMonthlyAmount());
				objOffBen.setMaxRecursiveAmount(objOffDed.getMaxRecursiveAmount());
				BigDecimal totalOwing = null;
				final BigDecimal maxAmount = new BigDecimal("999999999.99");
				if (objOffBen.getAmount() != null) {
					if (objOffBen.getAmount().compareTo(maxAmount) == 0) {
						objOffBen.setTotalDescription("Unlimited");
						objOffBen.setTotalCollected(objOffBen.getReceivedAmount() != null
								? String.valueOf(objOffBen.getReceivedAmount()) : "0.00");
						objOffBen.setTotalOwing("Unlimited");
					} else {
						objOffBen.setTotalDescription(String.valueOf(objOffBen.getAmount()));
						if (objOffBen.getReceivedAmount() != null) {
							objOffBen.setTotalCollected(String.valueOf(objOffBen.getReceivedAmount()));
							totalOwing = objOffBen.getAmount().subtract(objOffBen.getReceivedAmount());
							objOffBen.setTotalOwing(String.valueOf(totalOwing));
						} else {
							objOffBen.setTotalCollected("0.00");
							objOffBen.setTotalOwing(objOffBen.getTotalDescription());
						}
					}
				} else if (objOffDed.getMaxMonthlyAmount() != null) {
					BigDecimal monthyPayment = ocipbeneRepository.montlyAmountRecieved(
							BigDecimal.valueOf(objOffBen.getOffenderDeductionId()), objOffBen.getPersonId());

					if (monthyPayment != null) {
						objOffBen.setTotalCollected(String.valueOf(monthyPayment));
					} else {
						monthyPayment = new BigDecimal(0);
						objOffBen.setTotalCollected("0.00");
					}
					if (objOffBen.getAmount() != null) {
						objOffBen.setTotalDescription(String.valueOf(objOffBen.getAmount()));
						totalOwing = objOffBen.getAmount().subtract(monthyPayment);
						objOffBen.setTotalOwing(String.valueOf(totalOwing));
					} else {
						objOffBen.setTotalDescription("0.00");
						objOffBen.setTotalOwing(String.valueOf(monthyPayment));
					}

				} else if (objOffDed.getMaxRecursiveAmount() != null) {
					final BigDecimal recursive = ocipbeneRepository
							.recursiveAmountRecieved(BigDecimal.valueOf(objOffBen.getOffenderDeductionId()));

					if (objOffBen.getAmount() != null && recursive != null) {
						final BigDecimal totalAmount = objOffBen.getAmount().multiply(recursive);
						objOffBen.setTotalDescription(String.valueOf(totalAmount));
						objOffBen.setTotalCollected(String.valueOf(objOffBen.getReceivedAmount()));
						totalOwing = (totalAmount).subtract(objOffBen.getReceivedAmount());
						objOffBen.setTotalOwing(String.valueOf(totalOwing));
					}
				} else {
					objOffBen.setTotalDescription("Unlimited");
					objOffBen.setTotalCollected(objOffBen.getReceivedAmount() != null
							? String.valueOf(objOffBen.getReceivedAmount()) : "0.00");
					objOffBen.setTotalOwing("Unlimited");
				}
				final Offenders objOffenders = ocipbeneRepository.offenderDetailsExecuteQuery(objOffDed);
				objOffBen.setOffenderIdDisplay(objOffenders.getOffenderIdDisplay());
				objOffBen.setLastName(objOffenders.getLastName() + ", " + objOffenders.getFirstName());
			}
		}
		return lstOffBen;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public SystemProfiles sysPflExecuteQuery(final Persons searchRecord) {
		String pTotlAmt = null;
		String pTotlPaid = null;
		String pTotlOwing = null;
		BigDecimal pTotlAmtOne = null;
		BigDecimal pTotlPaidOne = null;
		BigDecimal pTotlOwingOne = null;
		BigDecimal finalAmount = null;
		BigDecimal lvChequeSum = BigDecimal.ZERO;
		BigDecimal totalAmountOne = null;
		BigDecimal totalAmountTwo = null;
		BigDecimal pTotalAmount = null;
		BigDecimal pTotlPaidTwo = null;
		BigDecimal pTotlOwingTwo = null;
		final List<BankChequeBeneficiaries> lstCheckBen = ocipbeneRepository
				.bankChequeBeneficiariesExecuteQuery(searchRecord);
		if (lstCheckBen != null) {
			for (final BankChequeBeneficiaries obj : lstCheckBen) {
				final BigDecimal lstCheckAmount = ocipbeneRepository
						.bankChequeBeneficiariesCheckAmount(obj.getChequeTxnId());
				if (lstCheckAmount != null) {
					lvChequeSum = lvChequeSum.add(lstCheckAmount);
				}
			}
			;
		}
		final SystemProfiles objProfiles = new SystemProfiles();
		final BigDecimal bgCredit = ocipbeneRepository.offenderCreditPriorPayments(searchRecord);
		final BigDecimal totalPaidChecks = lvChequeSum.add(bgCredit);
		objProfiles.setProfileValue(String.valueOf(totalPaidChecks));

		final OffenderBeneficiaries offBene = ocipbeneRepository.procedureQueryOne(searchRecord.getPersonId());
		if (offBene != null) {
			if (offBene.getRecursiveAmount() != null) {
				final Double amount = Double.valueOf(offBene.getRecursiveAmount().toString());
				if (amount > 0) {
					totalAmountOne = ocipbeneRepository.procedureQueryTwo(searchRecord.getPersonId());
				}
			} else {
				offBene.setRecursiveAmount(BigDecimal.ZERO);
			}
			totalAmountTwo = ocipbeneRepository.procedureQueryThree(searchRecord.getPersonId());
			if (totalAmountTwo == null) {
				totalAmountTwo = BigDecimal.ZERO;
			}
			pTotalAmount = offBene.getAmount().add(offBene.getRecursiveAmount());
			objProfiles.setTotalDescriptionOne(pTotalAmount.toString());
			pTotlPaidTwo = offBene.getReceivedAmount().add(totalAmountTwo);
			objProfiles.setTotalCollectedOne(pTotlPaidTwo.toString());
			pTotlOwingTwo = pTotalAmount.subtract(pTotlPaidTwo);
			objProfiles.setTotalOwingOne(pTotlOwingTwo.toString());
			final BigDecimal value = BigDecimal.valueOf(999999999.99);

			if (pTotalAmount.compareTo(value) == 1) {
				objProfiles.setTotalDescriptionOne("Unlimited");
				objProfiles.setTotalOwingOne("Unlimited");
			}
		}
		return objProfiles;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BNC
	 *
	 */
	@Transactional
	public Integer offBncCommit(final OffenderBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

}