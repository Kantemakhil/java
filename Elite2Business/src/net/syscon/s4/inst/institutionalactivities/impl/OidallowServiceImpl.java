package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.institutionalactivities.OidallowRepository;
import net.syscon.s4.inst.institutionalactivities.OidallowService;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowances;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowancesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.OimallowRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

@Service
public class OidallowServiceImpl extends BaseBusiness implements OidallowService {

	@Autowired
	private OidallowRepository oidallowRepository;

	@Autowired
	private OimallowRepository oimallowRepository;

	private static Logger logger = LogManager.getLogger(OidallowServiceImpl.class.getName());

	@Override
	public List<OffenderAllowances> getOffenderAllowenceExecuteQuery(OffenderAllowances searchBean) {
		return oidallowRepository.getOffenderAllowenceExecuteQuery(searchBean);
	}

	@Transactional
	@Override
	public List<OffenderAllowances> offenderAllowenceDataCommit(OffenderAllowancesCommitBean commitBean) {
		final List<OffenderAllowances> liReturnData = new ArrayList<>();
		final OffenderAllowances allowanceObject = new OffenderAllowances();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderAllowances obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oidallowRepository.insertOffenderAllowances(commitBean.getInsertList());
			if (liReturn == 1) {
				try {
					saveOffAllowPayDetails(commitBean);
				} catch (Exception e) {
					logger.error(this.getClass().getName() + " error in saveOffAllowPayDetails :: " + e);
				}
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderAllowances obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidallowRepository.updateOffenderAllowances(commitBean.getUpdateList());
			if (liReturn == 1) {
				for (OffenderAllowances offenderAllowances : commitBean.getUpdateList()) {
					if (offenderAllowances.getEndDate() != null) {
						offenderAllowances.setModifyUserId(commitBean.getCreateUserId());
						Date endDate = offenderAllowances.getEndDate();
						Date currentDate = new Date();
						if (endDate.compareTo(currentDate) < 0) {
							try {
								oidallowRepository.deleteRemainingOffAllowancePayDetails(offenderAllowances);
							} catch (Exception e) {
								logger.error(this.getClass().getName()
										+ " error in deleteRemainingOffAllowancePayDetails :: " + e);
							}
						}
					}
				}
			}
			if (liReturn == 1) {
				try {
					saveOffAllowPayDetails(commitBean);
				} catch (Exception e) {
					logger.error(this.getClass().getName() + " error in saveOffAllowPayDetails :: " + e);
				}
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (OffenderAllowances obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			try {
				int liReturnNew = oidallowRepository.deleteOffAllowancePayDetails(commitBean.getDeleteList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in deleteOffAllowancePayDetails :: " + e);
			}
			liReturn = oidallowRepository.deleteOffenderAllowances(commitBean.getDeleteList());
		}
		allowanceObject.setReturnedOutput(liReturn);
		liReturnData.add(allowanceObject);
		return liReturnData;
	}

	@Override
	public List<Allowances> getAllowenceLovData() {
		List<Allowances> returnList = new ArrayList<>();
		returnList = oidallowRepository.getAllowenceLovData();
		for (Allowances allowances : returnList) {
			if ("Y".equals(allowances.getActiveFlag())) {
				allowances.setCanDisplay(true);
			} else {
				allowances.setCanDisplay(false);
			}
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> getUnitDataLov() {
		return oimallowRepository.getUnit();
	}

	@Override
	public List<Allowances> getRateVersionData(String allowanceType) {
		return oidallowRepository.getRateVersionData(allowanceType);
	}

	@Override
	public Date getLastPaidDate(BigDecimal offenderBookId, BigDecimal offAllowanceId) {
		return oidallowRepository.getLastPaidDate(offenderBookId, offAllowanceId);
	}

	@Transactional
	@Override
	public Integer saveOffAllowPayDetails(OffenderAllowancesCommitBean commitBean) {
		Integer result = 0;
		List<OffenderAllowances> list = new ArrayList<OffenderAllowances>();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			list = commitBean.getInsertList();
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			list = commitBean.getUpdateList();
		}
		BigDecimal offenderBookId = null;
		for (OffenderAllowances offenderAllowances : list) {
			offenderBookId = offenderAllowances.getOffenderBookId();
		}
		List<OffenderAllowances> offAllowancesList = new ArrayList<OffenderAllowances>();
		List<Allowances> allowancesList = new ArrayList<Allowances>();
		List<OffAllowPayDetails> offAllowPayDetailsList = new ArrayList<OffAllowPayDetails>();
		offAllowancesList = oidallowRepository.getOffenderAllowences(offenderBookId);
		allowancesList = oidallowRepository.getAllowances();
		LocalDate todayDate = LocalDate.now();
		OffAllowPayDetails offAllowPayDetails = new OffAllowPayDetails();
		if ((offAllowancesList != null && offAllowancesList.size() > 0)
				&& (allowancesList != null && allowancesList.size() > 0)) {
			for (OffenderAllowances offenderAllowanceObj : offAllowancesList) {
				LocalDate offAllowStartDate = Instant.ofEpochMilli(offenderAllowanceObj.getStartDate().getTime())
						.atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate offAllowEndDate = null;
				if (offenderAllowanceObj.getEndDate() != null) {
					offAllowEndDate = Instant.ofEpochMilli(offenderAllowanceObj.getEndDate().getTime())
							.atZone(ZoneId.systemDefault()).toLocalDate();
				}

				while ((offAllowStartDate.isBefore(todayDate) || offAllowStartDate.isEqual(todayDate))&& (offAllowEndDate == null
						|| offAllowStartDate.isBefore(offAllowEndDate) || offAllowStartDate.isEqual(offAllowEndDate))) {

					Integer checkOffAllowPay = oidallowRepository.checkOffAllowPay(
							offenderAllowanceObj.getOffenderBookId(), offenderAllowanceObj.getOffAllowanceId(),
							Date.from(offAllowStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
					if (checkOffAllowPay == 0) {
						List<Allowances> allowncesTypesList = allowancesList.stream().filter(
								e -> e.getAllowanceType().equalsIgnoreCase(offenderAllowanceObj.getAllowanceType()))
								.collect(Collectors.toList());
						if (allowncesTypesList != null && !allowncesTypesList.isEmpty()) {
							for (int i = 0; i < allowncesTypesList.size(); i++) {
								LocalDate versionStartLocalDate = Instant
										.ofEpochMilli(allowncesTypesList.get(i).getVersionStartDate().getTime())
										.atZone(ZoneId.systemDefault()).toLocalDate();
								LocalDate versionStartLocalDate1 = null;
								
								if (i < allowncesTypesList.size() - 1) {
									versionStartLocalDate1 = Instant
											.ofEpochMilli(allowncesTypesList.get(i + 1).getVersionStartDate().getTime())
											.atZone(ZoneId.systemDefault()).toLocalDate();
								} else {
									versionStartLocalDate1 = LocalDate.now();
								}
								LocalDate versionExpiryLocalDate = null;

								if (allowncesTypesList.get(i).getExpiryDate() != null) {
									versionExpiryLocalDate = Instant
											.ofEpochMilli(allowncesTypesList.get(i).getExpiryDate().getTime())
											.atZone(ZoneId.systemDefault()).toLocalDate();
								}

								if ((offAllowStartDate.isEqual(versionStartLocalDate)
										|| offAllowStartDate.isAfter(versionStartLocalDate))
										&& (versionStartLocalDate1 != null
												&& ((offAllowStartDate.isBefore(versionStartLocalDate1)) || (offAllowStartDate.isEqual(versionStartLocalDate1))))
										&& (versionExpiryLocalDate == null
												|| (offAllowStartDate.isBefore(versionExpiryLocalDate)))) {
									allowncesTypesList.get(i).setCreateUserId(commitBean.getCreateUserId());
									if (allowncesTypesList.get(i).getMondayFlag() != null
											&& allowncesTypesList.get(i).getMondayFlag()
													.equals(ApplicationConstants.YES)
											&& offAllowStartDate.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
										offAllowPayDetails = setOffAllowPayDetails(offenderAllowanceObj,
												allowncesTypesList.get(i), offAllowStartDate);
										offAllowPayDetailsList.add(offAllowPayDetails);

									} else if (allowncesTypesList.get(i).getTuesdayFlag() != null
											&& allowncesTypesList.get(i).getTuesdayFlag()
													.equals(ApplicationConstants.YES)
											&& offAllowStartDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
										offAllowPayDetails = setOffAllowPayDetails(offenderAllowanceObj,
												allowncesTypesList.get(i), offAllowStartDate);
										offAllowPayDetailsList.add(offAllowPayDetails);
									} else if (allowncesTypesList.get(i).getWednesdayFlag() != null
											&& allowncesTypesList.get(i).getWednesdayFlag()
													.equals(ApplicationConstants.YES)
											&& offAllowStartDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
										offAllowPayDetails = setOffAllowPayDetails(offenderAllowanceObj,
												allowncesTypesList.get(i), offAllowStartDate);
										offAllowPayDetailsList.add(offAllowPayDetails);
									} else if (allowncesTypesList.get(i).getThursdayFlag() != null
											&& allowncesTypesList.get(i).getThursdayFlag()
													.equals(ApplicationConstants.YES)
											&& offAllowStartDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
										offAllowPayDetails = setOffAllowPayDetails(offenderAllowanceObj,
												allowncesTypesList.get(i), offAllowStartDate);
										offAllowPayDetailsList.add(offAllowPayDetails);
									} else if (allowncesTypesList.get(i).getFridayFlag() != null
											&& allowncesTypesList.get(i).getFridayFlag()
													.equals(ApplicationConstants.YES)
											&& offAllowStartDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
										offAllowPayDetails = setOffAllowPayDetails(offenderAllowanceObj,
												allowncesTypesList.get(i), offAllowStartDate);
										offAllowPayDetailsList.add(offAllowPayDetails);
									} else if (allowncesTypesList.get(i).getSaturdayFlag() != null
											&& allowncesTypesList.get(i).getSaturdayFlag()
													.equals(ApplicationConstants.YES)
											&& offAllowStartDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
										offAllowPayDetails = setOffAllowPayDetails(offenderAllowanceObj,
												allowncesTypesList.get(i), offAllowStartDate);
										offAllowPayDetailsList.add(offAllowPayDetails);
									} else if (allowncesTypesList.get(i).getSundayFlag() != null
											&& allowncesTypesList.get(i).getSundayFlag()
													.equals(ApplicationConstants.YES)
											&& offAllowStartDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
										offAllowPayDetails = setOffAllowPayDetails(offenderAllowanceObj,
												allowncesTypesList.get(i), offAllowStartDate);
										offAllowPayDetailsList.add(offAllowPayDetails);
									}

								}

							}
						}
					}
					offAllowStartDate = offAllowStartDate.plusDays(1);
				}
			}
		}

		if (offAllowPayDetailsList != null && offAllowPayDetailsList.size() > 0) {
			try {
				result = oidallowRepository.saveOffAllowPayDetails(offAllowPayDetailsList);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveOffAllowPayDetails :: " + e);
			}
		}
		return result;
	}

	public OffAllowPayDetails setOffAllowPayDetails(OffenderAllowances offAllowlist, Allowances allowanceList,
			LocalDate OffAllowStartDt) {
		OffAllowPayDetails offAllowPayDet = new OffAllowPayDetails();
		Date allowPayDay = java.sql.Date.valueOf(OffAllowStartDt);
		offAllowPayDet.setOffenderBookId(offAllowlist.getOffenderBookId());
		offAllowPayDet.setOffAllowanceId(offAllowlist.getOffAllowanceId());
		offAllowPayDet.setAllowanceType(offAllowlist.getAllowanceType());
		offAllowPayDet.setOffAllowanceDay(allowPayDay);
		offAllowPayDet.setVersionNo(allowanceList.getVersionNo());
		offAllowPayDet.setAllowanceVersionUnit(allowanceList.getUnit());
		offAllowPayDet.setAllowanceVersionMaxUnit(allowanceList.getMaxUnit());
		offAllowPayDet.setAllowanceVersionRate(allowanceList.getRate());
		offAllowPayDet.setCreateUserId(allowanceList.getCreateUserId());
		return offAllowPayDet;
	}

}
