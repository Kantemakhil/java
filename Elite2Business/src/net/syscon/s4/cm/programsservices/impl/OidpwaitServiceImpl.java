package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.programsservices.OidpwaitRepository;
import net.syscon.s4.cm.programsservices.OidpwaitService;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligationsCommitBean;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OcmpconfService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;
import net.syscon.s4.triggers.OffenderPrgObligationsT1Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class OidpwaitServiceImpl
 */
@Service
public class OidpwaitServiceImpl extends BaseBusiness implements OidpwaitService {
	
	private static Logger logger = LogManager.getLogger(OidpwaitServiceImpl.class.getName());
	@Autowired
	private OidpwaitRepository oidpwaitRepository;

	@Autowired
	private TagProgrammesService tagProgrammesService;

	@Autowired
	private NonAssociationService nonAssociationService;

	@Autowired
	private OffenderPrgObligationsT1Service offenderPrgObligationsT1Service;

	@Autowired
	private TagEstablishmentService tagEstablishmentService;
	
	@Autowired
	private OcmpconfService ocmpconfService;

	@Override
	public void vOffPrgOblPostQuery(Offenders paramBean) {

	}

	public List<ProgramServices> rgProgramServicesRecordGroup() {
		return oidpwaitRepository.rgProgramServicesRecordGroup();
	}

	public List<Areas> rgRegionRecordGroup() {
		List<Areas> returnList = oidpwaitRepository.rgRegionRecordGroup();
		Integer count = 0;
		for (final Areas areas : returnList) {
			count = count + 1;
			areas.setListSeq(count);
		}
		return returnList;
	}

	public List<ProgramServices> rgAreasRecordGroup(String areaInputs) {
		String caseLoadType = null;
		String regionCode = null;

		final String[] inputArray = areaInputs.split(",");
		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				caseLoadType = inputArray[0];
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals(""))
				regionCode = inputArray[1];
		}
		return oidpwaitRepository.rgAreasRecordGroup(caseLoadType, regionCode);
	}

	public List<AgencyLocations> rgAgyLocsRecordGroup(String facilityInputs) {
		String caseLoadType = null;
		String regionCode = null;
		String areaCode = null;

		final String[] inputArray = facilityInputs.split(",");
		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				caseLoadType = inputArray[0];
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals(""))
				regionCode = inputArray[1];
			if (inputArray.length > 2 && inputArray[2] != null && !inputArray[2].equals(""))
				areaCode = inputArray[2];
		}
		List<AgencyLocations> returnList = oidpwaitRepository.rgAgyLocsRecordGroup(caseLoadType, regionCode, areaCode);

		Integer count = 0;
		for (final AgencyLocations areas : returnList) {
			count = count + 1;
			areas.setListSeq(count);
		}
		return returnList;
	}

	public List<Areas> rgAllTeamsRecordGroup() {
		final List<Areas> returnList = oidpwaitRepository.rgAllTeamsRecordGroup();
		Integer count = 0;
		for (final Areas areas : returnList) {
			count = count + 1;
			areas.setListSeq(count);
		}
		return returnList;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(VOffenderPrgObligations searchRecord) {
		List<VOffenderPrgObligations> returnList = new ArrayList<VOffenderPrgObligations>();

		returnList = oidpwaitRepository.vOffPrgOblExecuteQuery(searchRecord);

		for (VOffenderPrgObligations vOff : returnList) {
			BigDecimal getOffenderIdCur = oidpwaitRepository.getOffenderIdCur(vOff.getOffenderBookId());
			vOff.setOffenderId(getOffenderIdCur);
			vOff.setLegalEndDateTemp(vOff.getLegalEndDate() != null ? vOff.getLegalEndDate().toString() : null);
			if (vOff.getLegalEndDateTemp() == null) {
				OdynfrmSubmitDataBean keyDatesData = new OdynfrmSubmitDataBean();
				keyDatesData.setSearchString("\"offenderBookId\":\"" + vOff.getOffenderBookId() + "\"");
				keyDatesData.setFormName("OCDLEGLS");
				keyDatesData = ocmpconfService.getFormData(keyDatesData);
				if (keyDatesData.getFormInfoJson() != null) {

					ObjectMapper objectMapper = new ObjectMapper();
					Date bookingLRDdate = null;
					Date bookingHEDdate = null;
					Date overrideDateValue = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

					try {

						Map<String, Object> map = objectMapper.readValue(keyDatesData.getFormInfoJson(), Map.class);
						List<Map<String, Object>> listBookingDates = (List<Map<String, Object>>) map
								.get("bookingDates");
						for (Map<String, Object> objMap : listBookingDates) {
							if (ApplicationConstants.INST_CASELOAD.equalsIgnoreCase(searchRecord.getCaseLoadType())) {
								if (objMap.get("dateType").equals("booking_LRD")) {
									Boolean indefiniteDate = (Boolean) objMap.get("overrideIndefinite");
									if (indefiniteDate) {
										vOff.setLegalEndDateTemp(ApplicationConstants.INDEFINITE);
										break;
									}
									String date = (String) objMap.get("dateValue");
									if (date != null) {
										if (!date.isEmpty()) {
											bookingLRDdate = sdf.parse(date);
											vOff.setLegalEndDateTemp(date);
										}
									}
									String overrideDate = (String) objMap.get("overrideDateValue");
									if (overrideDate != null) {
										if (!overrideDate.isEmpty()) {
											overrideDateValue = sdf.parse(overrideDate);
										}
									}
								}
								if (objMap.get("dateType").equals("booking_HED")) {
									String date = (String) objMap.get("dateValue");
									if (date != null) {
										bookingHEDdate = sdf.parse(date);
										vOff.setLegalEndDateTemp(date);
									}
								}
								if (overrideDateValue != null) {
									String strDate = sdf.format(overrideDateValue);
									vOff.setLegalEndDateTemp(strDate);
								}

								if (overrideDateValue == null) {
									if (bookingLRDdate != null && bookingHEDdate != null) {
										if (bookingLRDdate.compareTo(bookingHEDdate) == 1) {
											String bookingLrd = sdf.format(bookingLRDdate);
											vOff.setLegalEndDateTemp(bookingLrd);
										} else if (bookingLRDdate.compareTo(bookingHEDdate) == -1) {
											String bookingHed = sdf.format(bookingHEDdate);
											vOff.setLegalEndDateTemp(bookingHed);
										}
									}
								} else {
									if (overrideDateValue != null && bookingHEDdate != null) {
										if (overrideDateValue.compareTo(bookingHEDdate) == 1) {
											String dateValue = sdf.format(overrideDateValue);
											vOff.setLegalEndDateTemp(dateValue);
										} else if (overrideDateValue.compareTo(bookingHEDdate) == -1) {
											String bookingHed = sdf.format(bookingHEDdate);
											vOff.setLegalEndDateTemp(bookingHed);
										}
									}
								}
							} else {
								if (objMap.get("dateType").equals("booking_expiry_date")) {
									String date = (String) objMap.get("dateValue");
									if (date != null) {
										vOff.setLegalEndDateTemp(date);
									}
								}
							}
						}
					} catch (Exception e) {
						logger.error("vOffPrgOblExecuteQuery :", e);
					}
				}
			}
		}
		return returnList;
	}

	@Transactional
	public List<VOffenderPrgObligations> vOffPrgOblCommit(VOffenderPrgObligationsCommitBean commitBean) {
		List<VOffenderPrgObligations> liReturn = new ArrayList<VOffenderPrgObligations>();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VOffenderPrgObligations obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = vOffPrgUpdate(commitBean.getUpdateList());
		}
		return liReturn;
	}

	public List<VOffenderPrgObligations> vOffPrgUpdate(List<VOffenderPrgObligations> updateList) {
		List<VOffenderPrgObligations> liReturn = new ArrayList<VOffenderPrgObligations>();
		List<OffenderPrgObligations> trigList = new ArrayList<OffenderPrgObligations>();
		List<OffenderPrgObligations> oldList = new ArrayList<OffenderPrgObligations>();
		VOffenderPrgObligations vOffPrgObli = new VOffenderPrgObligations();
		for (VOffenderPrgObligations obj : updateList) {
			oldList = oidpwaitRepository.getOldRecOffenderPrgObligations(obj.getOffenderPrgObligationId());
			OffenderPrgObligations newRec = new OffenderPrgObligations();
			newRec.setStatus(obj.getStatus());
			newRec.setOffenderPrgObligationId(obj.getOffenderPrgObligationId().longValue());
			newRec.setStatusChangeReason(obj.getStatusChangeReason());
			newRec.setCreateUserId(obj.getCreateUserId());
			trigList.add(newRec);
		}
		vOffPrgObli = oidpwaitRepository.vOffPrgOblUpdateVOffenderPrgObligations(updateList);
		for (OffenderPrgObligations obj : trigList) {
			for (OffenderPrgObligations oldRec : oldList) {
				offenderPrgObligationsT1Service.offenderPrgObligationsT1(obj, oldRec);
			}
		}

		for (VOffenderPrgObligations vOff : updateList) {
			BigDecimal CheckSum = oidpwaitRepository.postUpdate(vOff);
			vOff.setCheckSum(CheckSum);
			tagProgrammesService.lockOffPrgObligationId(vOff.getOffenderPrgObligationId().longValue(),
					vOff.getCheckSum().longValue());
			if ("Y".equals(vOff.getAssignFlag())) {
				vOff.setCreateUserId(vOff.getModifyUserId());
				assignServicesToOffenders(vOff);
			}
		}
		liReturn.add(vOffPrgObli);
		return liReturn;
	}

	public List<ReferenceCodes> rgPsPrgAvailRecordGroup() {
		return oidpwaitRepository.rgPsPrgAvailRecordGroup();
	}

	public List<ReferenceCodes> rgRestrictTeamsRecordGroup(String teamInputs) {
		String regionCode = null;
		String areaCode = null;

		final String[] inputArray = teamInputs.split(",");
		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				regionCode = inputArray[0];
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals(""))
				areaCode = inputArray[1];
		}
		return oidpwaitRepository.rgRestrictTeamsRecordGroup(regionCode, areaCode);
	}

	public Integer clearTempList(String modifyUserId) {
		return nonAssociationService.clearTempList( modifyUserId);
	}

	@Transactional
	public Integer nonAssociation(VOffenderPrgObligations searchRecord) {
		if ("true".equals(searchRecord.getFirstFlag())) {
			return nonAssociationService.addToProgramList(searchRecord, searchRecord.getCreateUserId());
		} else {
			return nonAssociationService.removeFromProgramList(searchRecord);
		}
	}

	@Transactional
	public Integer assignServicesToOffenders(VOffenderPrgObligations searchRecord) {
		int count = 0;
		if ("true".equals(searchRecord.getNbtBulkAssign())) {
			searchRecord.setStatus("ALLOC");
			tagProgrammesService.allocateCourseToOffender(searchRecord, searchRecord.getCoursePhaseId(),
					searchRecord.getCreateUserId());
			count = oidpwaitRepository.asnSerToOffUpdate(searchRecord);

			List<OffenderPrgObligations> oldList = oidpwaitRepository
					.getOldRecOffenderPrgObligations(searchRecord.getOffenderPrgObligationId());
			OffenderPrgObligations newRec = new OffenderPrgObligations();
			OffenderPrgObligations oldRec = oldList.get(0);
			newRec.setStatus(searchRecord.getStatus());
			newRec.setOffenderPrgObligationId(searchRecord.getOffenderPrgObligationId().longValue());
			newRec.setStatusChangeReason(searchRecord.getStatusChangeReason());
			newRec.setCreateUserId(searchRecord.getCreateUserId());
			offenderPrgObligationsT1Service.offenderPrgObligationsT1(newRec, oldRec);

		}
		return count;
	}

	public List<ReferenceCodes> whenNewFormInstance(final String curCaseLoadId) {
		List<ReferenceCodes> list = new ArrayList<ReferenceCodes>();
		ReferenceCodes obj = new ReferenceCodes();
		obj.setCode(tagEstablishmentService.defaultAgency(curCaseLoadId));
		list.add(obj);
		return list;

	}

	public List<ReferenceCodes> getcommareadefault(final String curCaseLoadId) {
		return oidpwaitRepository.getcommareadefault(curCaseLoadId);
	}

}