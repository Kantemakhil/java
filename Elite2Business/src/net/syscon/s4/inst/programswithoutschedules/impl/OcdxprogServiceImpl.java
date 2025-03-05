package net.syscon.s4.inst.programswithoutschedules.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderPrgObligationsCommitBean;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.OciscataRepository;
import net.syscon.s4.inst.institutionalactivities.OcupaoffRepository;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogRepository;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogService;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesService;
import net.syscon.s4.triggers.OffenderPrgObligationsT1Service;
import net.syscon.s4.triggers.OffenderPrgObligationsT2Service;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;

/**
 * Class OcdxprogServiceImpl
 */
@Service
public class OcdxprogServiceImpl extends BaseBusiness implements OcdxprogService {

	@Autowired
	private OcdxprogRepository ocdxprogRepository;
	@Autowired
	private TagPrisonActivitiesService tagPrisonActivitiesService;
	@Autowired
	private OffenderPrgObligationsT1Service offenderPrgObligationsT1Service;
	@Autowired
	private OffenderPrgObligationsT2Service offenderPrgObligationsT2Service;
	@Autowired
	private OffenderProgramProfilesTrService offenderProgramProfilesTrService;
	@Autowired
	private NonAssociationService nonAssociationService;
	@Autowired
	private OcupaoffRepository ocupaoffRepository;
	@Autowired
	private OcdprogrRepository ocdprogrRepository;
	@Autowired
	private OciscataRepository ociscataRepository;
	
	/**
	 * Creates new OcdxprogServiceImpl class Object
	 */
	public OcdxprogServiceImpl() {
		// OcdxprogServiceImpl
	}
	
	private static Logger logger = LogManager.getLogger(OcdxprogServiceImpl.class.getName());


	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderPrgObligations> offBkgOnCheckDeleteMaster(final OffenderPrgObligations paramBean) {
		return ocdxprogRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderProgramProfiles> offPrgObligationsOnCheckDeleteMaster(final OffenderProgramProfiles paramBean) {
		return ocdxprogRepository.offPrgObligationsOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Dual> offPrgObligationsWhenNewRecordInstance(final Dual paramBean) {
		return ocdxprogRepository.offPrgObligationsWhenNewRecordInstance(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public String offProgramProfilesPostQuery(final VCourseActivities paramBean) {
		return ocdxprogRepository.offProgramProfilesPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderProgramProfiles> offProgramProfilesWhenValidateRecord(final OffenderProgramProfiles paramBean) {
		return ocdxprogRepository.offProgramProfilesWhenValidateRecord(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CourseActivities> callOciscata(final CourseActivities paramBean) {
		return ocdxprogRepository.callOciscata(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderPrgObligations> offPrgObligationsExecuteQuery(final OffenderPrgObligations searchRecord) {
		List<OffenderPrgObligations> returnList = new ArrayList<>();
		List<OffenderSentences> offenderSentencesList = new ArrayList<OffenderSentences>();
		returnList = ocdxprogRepository.offPrgObligationsExecuteQuery(searchRecord);
		if( !returnList.isEmpty() && returnList !=null) {
			offenderSentencesList = rgOffenderSentencesRecordGroup( searchRecord.getOffenderBookId().intValue());
		}
		for (OffenderPrgObligations offPrgObligaions : returnList) {
			if (offPrgObligaions.getObligationSource().equals("INST")) {
				offPrgObligaions.setObligationSource("Institution");
			} else if (offPrgObligaions.getObligationSource().equals("COMM")) {
				offPrgObligaions.setObligationSource("Community");
			}
			if (offPrgObligaions.getStatus() != null) {
				String status = ocdxprogRepository.obligationStatus(offPrgObligaions.getStatus());
				offPrgObligaions.setStatusDescription(status);
			}
			if (offPrgObligaions.getProgramId() != null) {
				ProgramServices programServices = ocdxprogRepository.offPrgObligationsProgramId(offPrgObligaions);
				if (programServices != null) {
					offPrgObligaions.setDescription(programServices.getProgramCode());
				}
			}
			if(offenderSentencesList!= null && !offenderSentencesList.isEmpty() && offPrgObligaions.getSentenceSeq()!= null){
				for( OffenderSentences obj :offenderSentencesList) {
					if(offPrgObligaions.getSentenceSeq().equals(obj.getSentenceSeq())) {
						offPrgObligaions.setSentenceStartDate(obj.getStartDate());
						offPrgObligaions.setSentenceEndDate(obj.getEndDate());
						offPrgObligaions.setSentenceDesc(obj.getDescription());
					}
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PRG_OBLIGATIONS
	 *
	 * 
	 */
	@Transactional
	public Integer offPrgObligationsCommit(final OffenderPrgObligationsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderPrgObligations> recordSavingList = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (final OffenderPrgObligations offenderPropertyItemObj : commitBean.getInsertList()) {
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					Long offOblId = ocdxprogRepository.offenderPrgObligationId();
					offenderPropertyItemObj.setOffenderPrgObligationId(offOblId);
					if (offenderPropertyItemObj.getDescription() != null) {
						Long programId = ocdxprogRepository
								.offenderProramCode(offenderPropertyItemObj.getDescription());
						if (programId != null) {
							offenderPropertyItemObj.setProgramId(programId);
						}
					}
					if (offenderPropertyItemObj.getSpecialNeedFlag() != null) {
						offenderPropertyItemObj.setSpecialNeedFlag("Y");
					} else {
						offenderPropertyItemObj.setSpecialNeedFlag("N");
					}
					recordSavingList.clear();
					recordSavingList.add(offenderPropertyItemObj);
					liReturn = ocdxprogRepository.offPrgObligationsInsertOffenderPrgObligations(recordSavingList);
					// Trigger OFFENDER_PRG_OBLIGATIONS_T2
					Integer triRetVal = offenderPrgObligationsT2Service
							.offenderPrgObligationsT2(offenderPropertyItemObj);
					// Trigger OFFENDER_PRG_OBLIGATIONS_T1
					OffenderPrgObligations oldRec = ocdxprogRepository
							.getOffenderPrgObligationsOldRecord(offenderPropertyItemObj.getOffenderPrgObligationId());
					Integer triVal = offenderPrgObligationsT1Service.offenderPrgObligationsT1(offenderPropertyItemObj,
							oldRec);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			List<OffenderPrgObligations> recordSavingList = new ArrayList<>();
			if (commitBean.getUpdateList().size() > 0) {
				for (final OffenderPrgObligations offenderPropertyItemObj : commitBean.getUpdateList()) {
					offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
					if (offenderPropertyItemObj.getSpecialNeedFlag() != null) {
						offenderPropertyItemObj.setSpecialNeedFlag("Y");
					} else {
						offenderPropertyItemObj.setSpecialNeedFlag("N");
					}
					if (offenderPropertyItemObj.getObligationSource().equals("Institution")) {
						offenderPropertyItemObj.setObligationSource("INST");
					} else if (offenderPropertyItemObj.getObligationSource().equals("Community")) {
						offenderPropertyItemObj.setObligationSource("COMM");
					}
					if (offenderPropertyItemObj.getDescription() != null) {
						Long programId = ocdxprogRepository
								.offenderProramCode(offenderPropertyItemObj.getDescription());
						if (programId != null) {
							offenderPropertyItemObj.setProgramId(programId);
						}
					}
					OffenderPrgObligations oldRec = ocdxprogRepository
							.getOffenderPrgObligationsOldRecord(offenderPropertyItemObj.getOffenderPrgObligationId());
					recordSavingList.add(offenderPropertyItemObj);
					liReturn = ocdxprogRepository.offPrgObligationsUpdateOffenderPrgObligations(recordSavingList);
					// Trigger OFFENDER_PRG_OBLIGATIONS_T1
					Integer trigVal = offenderPrgObligationsT1Service.offenderPrgObligationsT1(offenderPropertyItemObj,
							oldRec);
				}
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			Integer returnId = 0;
			List<OffenderPrgObligations> recordSavingList = new ArrayList<>();
			if (commitBean.getDeleteList().size() > 0) {
				for (final OffenderPrgObligations offenderPropertyItemObj : commitBean.getDeleteList()) {
					offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
					returnId = ocdxprogRepository
							.checkDeleteOffenderPrgObligations(offenderPropertyItemObj.getOffenderPrgObligationId());
					if (returnId != 0) {
						liReturn = 2;
						return liReturn;
					}
					recordSavingList.add(offenderPropertyItemObj);
					liReturn = ocdxprogRepository
							.offPrgObligationsDeleteOffenderPrgObligations(commitBean.getDeleteList());
				}
			}
		}
		// if (commitBean.getDeleteList() != null &&
		// commitBean.getDeleteList().size() > 0) {
		// liReturn =
		// ocdxprogRepository.offPrgObligationsDeleteOffenderPrgObligations(commitBean.getDeleteList());
		// }
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(final OffenderProgramProfiles searchRecord) {
		List<OffenderProgramProfiles> returnList = new ArrayList<>();
		returnList = ocdxprogRepository.offProgramProfilesExecuteQuery(searchRecord);
		for (OffenderProgramProfiles offProgramProfiles : returnList) {
			if (offProgramProfiles.getCrsActyId() != null) {
				VCourseActivities vCourseActivities = new VCourseActivities();
				vCourseActivities.setCrsActyId(offProgramProfiles.getCrsActyId());
				String programName = ocdxprogRepository.offProgramProfilesPostQuery(vCourseActivities);
				if (programName != null) {
					offProgramProfiles.setProviderName(programName);
				}
				CourseActivities programServices = ocdxprogRepository
						.offPrgPrflesProjectDescription(offProgramProfiles.getCrsActyId());
				if (programServices != null) {
					offProgramProfiles.setProgramDescription(programServices.getDescription());
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PROGRAM_PROFILES
	 *
	 * 
	 */
	@Transactional
	public Integer offProgramProfilesCommit(final OffenderProgramProfilesCommitBean commitBean) {
		int liReturn = 0;
		String returnString = null;
		Long returnId = 0l;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			if (commitBean.getInsertList().size() > 0) {
				for (final OffenderProgramProfiles offenderPropertyItemObj : commitBean.getInsertList()) {
					List<OffenderProgramProfiles> recordSavingList = new ArrayList<>();
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					returnId = tagPrisonActivitiesService.chkAllocated(offenderPropertyItemObj.getCrsActyId(),
							offenderPropertyItemObj.getOffenderBookId(),
							offenderPropertyItemObj.getOffenderStartDate());
					// returnId =
					// ocdxprogRepository.offProgramProfilePreInsert(offenderPropertyItemObj);
					if (returnId != 0) {
						liReturn = 3;
						return liReturn;
					}
					returnString = ocdxprogRepository.offProgramPrflesSameValidation(offenderPropertyItemObj);
					if (returnString != null) {
						liReturn = 2;
						return liReturn;
					}
					Long offPrgrefId = ocdxprogRepository.ocdxprogOffPrgrefId();
					offenderPropertyItemObj.setOffPrgrefId(offPrgrefId);
					recordSavingList.add(offenderPropertyItemObj);
					// Trigger call OFFENDER_PROGRAM_PROFILES_TR
					Integer triVal = offenderProgramProfilesTrService
							.offenderProgramProfilesTr(offenderPropertyItemObj);
					liReturn = ocdxprogRepository.offProgramProfilesInsertOffenderProgramProfiles(recordSavingList);
					OffenderPrgObligations oldRec = ocdxprogRepository
							.getOffenderPrgObligationsOldRecord(offenderPropertyItemObj.getOffenderPrgObligationId());
					ocdxprogRepository.updateOffObligationStatus(offenderPropertyItemObj.getOffenderPrgObligationId(),
							offenderPropertyItemObj.getCreateUserId());
					OffenderPrgObligations newRec = new OffenderPrgObligations();
					BeanUtils.copyProperties(offenderPropertyItemObj, newRec);
					// Trigger OFFENDER_PRG_OBLIGATIONS_T1
					newRec.setStatus(offenderPropertyItemObj.getOffenderProgramStatus());
					Integer triRetVal = offenderPrgObligationsT1Service.offenderPrgObligationsT1(newRec, oldRec);
				}
				//liReturn = ocdxprogRepository.offProgramProfilesInsertOffenderProgramProfiles(recordSavingList);
				for (OffenderProgramProfiles bean : commitBean.getInsertList()) {
					ocdxprogRepository.updateOffObligationStatus(bean.getOffenderPrgObligationId(),bean.getCreateUserId());
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderProgramProfiles offenderPropertyItemObj : commitBean.getUpdateList()) {
				offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
				List<OffenderProgramProfiles> recordSavingList = new ArrayList<>();
				// Trigger call OFFENDER_PROGRAM_PROFILES_TR
				Integer trigRetVal = offenderProgramProfilesTrService
						.offenderProgramProfilesTr(offenderPropertyItemObj);
				recordSavingList.add(offenderPropertyItemObj);
				liReturn = ocdxprogRepository.offProgramProfilesUpdateOffenderProgramProfiles(recordSavingList);

			}
			// liReturn =
			// ocdxprogRepository.offProgramProfilesUpdateOffenderProgramProfiles(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgAvailabilityCodeRecordGroup() {
		return ocdxprogRepository.rgAvailabilityCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ProgramServices> rgProgramRecordGroup() {
		List<ProgramServices> returnList = new ArrayList<ProgramServices>();
		returnList = ocdxprogRepository.rgProgramRecordGroup();
//		for (ProgramServices programServices : returnList) {
//			if("Y".equals(programServices.getActiveFlag())) {
//				programServices.setCanDisplay(true);
//			} else {
//				programServices.setCanDisplay(false);
//			}
//			
//		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		return ocdxprogRepository.rgEndReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public String currentCaseloadType(final String caseloadId) {
		return ocdxprogRepository.currentCaseloadType(caseloadId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public Integer offProgramPrflesUpdatePrgStatus(final Long offenderPrgObligationId, final Long offenderBookId) {
		return ocdxprogRepository.offProgramPrflesUpdatePrgStatus(offenderPrgObligationId, offenderBookId);
	}

	@Override
	public int checkPrivilegeExists() {
		return ocdxprogRepository.checkPrivilegeExists();
	}
	
	public List<OffenderSentences> rgOffenderSentencesRecordGroup(final Integer offenderBookId) {
		List<VOffenderSentCondActs> sentenceRecordGrp = new ArrayList<VOffenderSentCondActs>();
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();
		try {
			sentenceRecordGrp = ocdxprogRepository.rgOffenderSentencesRecordGroup(offenderBookId);

			for (VOffenderSentCondActs obj : sentenceRecordGrp) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
				ObjectMapper mapper = new ObjectMapper();
				try {
					ObjectMapper orderMapper = new ObjectMapper();
					Map<String, String> orderMap = orderMapper.readValue(obj.getFormIdentifier(), Map.class);
					if(orderMap.get("orderType")!= null && orderMap.get("orderType").equalsIgnoreCase("NONCUST") ) {
						Map<String, Object> map = mapper.readValue(obj.getFormInfoJson(), Map.class);
						if (map != null && map.get("myJsonRowData") != null) {
							List<SentenceCalcTypes> sentenceTypes = new ArrayList<SentenceCalcTypes>();
							sentenceTypes = ocdxprogRepository.getSentenceData();
							List<Map<String, Object>> Sentencelist = new ArrayList();
							Sentencelist = (List<Map<String, Object>>) map.get("myJsonRowData");
							for (Map<String, Object> sentenceObj : Sentencelist) {
								Integer sentenceSeq = null;
								try {
									sentenceSeq = (Integer) sentenceObj.get("orderNo");
								} catch (Exception e) {
									logger.error("Exception in " + this.getClass().getName() +" rgOffenderSentencesRecordGroup method while converting OrderNo to Integer", e.getMessage());
								}
								if(sentenceSeq == null) {
									continue;
								}
								obj.setOffenderBookId(new BigDecimal(orderMap.get("offenderBookId").toString()));

								if (sentenceSeq != null/* && sentenceSeq == obj.getSentenceSeq() */) {
									List<OffenderSentences> filteredList = returnList.stream()
											.filter(e -> obj.getOffenderBookId().equals(new BigDecimal(e.getOffenderBookId()))
													&& e.getSentenceSeq() == (Integer) sentenceObj.get("orderNo")
													)
											.collect(Collectors.toList());

									if (filteredList != null && filteredList.size() == 0) {
										OffenderSentences offenderConditions = new OffenderSentences();
										offenderConditions.setOffenderBookId(offenderBookId);
										offenderConditions.setSentenceSeq( new Long(sentenceObj.get("orderNo").toString()));
										offenderConditions.setOrderType(sentenceObj.get("orderType").toString());
										offenderConditions.setSentenceCategory(sentenceObj.get("displayNo").toString());
										String court = ocdxprogRepository.getCourtData(sentenceObj.get("court")!=null?sentenceObj.get("court").toString():null);
										offenderConditions.setJurisCode(court);
										for(SentenceCalcTypes obj1 :sentenceTypes) {
											if(sentenceObj.get("sentenceCalcType")!=null && (obj1.getSentenceCalcType().equalsIgnoreCase(sentenceObj.get("sentenceCalcType").toString()))){
												offenderConditions.setDescription(obj1.getDescription());
											}
										}
										if(sentenceObj.get("commenceDate")!=null && sentenceObj.get("commenceDate").toString().trim()!= "") {
											LocalDateTime ldt=LocalDateTime.parse(sentenceObj.get("commenceDate").toString());
											offenderConditions.setStartDate(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
										}
										if(sentenceObj.get("expiryDate")!=null && sentenceObj.get("expiryDate").toString().trim()!= "") {
											LocalDateTime ldt=LocalDateTime.parse(sentenceObj.get("expiryDate").toString());
											offenderConditions.setEndDate(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
										} 
										returnList.add(offenderConditions);
									}
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error("Exception in OcduprojServiceImpl class getSentenceData method", e.getMessage());
				}

			}

		} catch (Exception e) {
			logger.error("Error in rgOffenderSentencesRecordGroup class : ", e.getMessage());
		}
		return returnList;
	}

	@Override
	public String checkNonAssociations(OffenderProgramProfilesCommitBean commitBean) {
		String returnMessage = "";
		try {
			List<OffenderProgramProfiles> offenderProgramProfilesFinalList = new ArrayList<OffenderProgramProfiles>();
			if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				offenderProgramProfilesFinalList.addAll(commitBean.getInsertList());
			}
			if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				offenderProgramProfilesFinalList.addAll(commitBean.getUpdateList());
			}
			returnMessage = getNonAssociationsData(offenderProgramProfilesFinalList, commitBean.getCreateUserId());
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in checkNonAssociations", e);
		}
		return returnMessage;
	}

	public String getFinalMessageString(final String messageData) {
		String returnMessage = "";
		try {
			returnMessage =  messageData + " \n ociscata.doyouwanttoproceed ";
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in getFinalMessageString ", e);
		}
		return returnMessage;
	}
	
	public String getNonAssociationsData(List<OffenderProgramProfiles> offenderProgramProfiles,String createUserId) {
		String returnMessage = "";
		String individualNonAssociationMessages = "";
		String gangNonAssociationMessages = "";
		try {
			List<VOffenderProgramProfiles> listOfAllocOffenders = new ArrayList<VOffenderProgramProfiles>();
			if(offenderProgramProfiles != null && offenderProgramProfiles.size() > 0 && offenderProgramProfiles.get(0) != null&& offenderProgramProfiles.get(0).getOffenderBookId() != null) {
			for (OffenderProgramProfiles vOffPrgm : offenderProgramProfiles) {
				VOffenderProgramProfiles objSearchDao = new VOffenderProgramProfiles();
				objSearchDao.setCrsActyId(vOffPrgm.getCrsActyId());
				List<VOffenderProgramProfiles> listOfAllocOffendersTwo = ocupaoffRepository.vOffPrgProfilesExecuteQuery(objSearchDao);
				listOfAllocOffenders.addAll(listOfAllocOffendersTwo);
			}
		}
		if(!listOfAllocOffenders.isEmpty()) {
			if(offenderProgramProfiles != null && offenderProgramProfiles.size() > 0 && offenderProgramProfiles.get(0) != null&& offenderProgramProfiles.get(0).getOffenderBookId() != null) {
				for (OffenderProgramProfiles vOffPrgm : offenderProgramProfiles) {
					VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(new BigDecimal(vOffPrgm.getOffenderBookId()), createUserId);
					int offenderCount = 1;
					for(VOffenderProgramProfiles activeOffenderObj : listOfAllocOffenders) {
					Integer count = ociscataRepository.checkNonAssociationConflict(new BigDecimal(vOffPrgm.getOffenderBookId()),new BigDecimal(activeOffenderObj.getOffenderBookId()));
					if(count > 0) {
						String Msg = null;
						Msg = "ociscata.offender " + bean.getLastName() + "," + bean.getFirstName() + " , " + bean.getOffenderId() + " ociscata.hasNonAssociation " + " \n"
						      + " " + offenderCount + ") " + activeOffenderObj.getOffenderName() + " , " + activeOffenderObj.getOffenderId()  + " \n";
						offenderCount++;
						individualNonAssociationMessages = individualNonAssociationMessages + Msg + "\n";
					}
				}
				if (individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")) {
					individualNonAssociationMessages = "ociscata.indinonassocconflict:\n\n" + individualNonAssociationMessages;
				}
				for (VOffenderProgramProfiles activeOffenderObj : listOfAllocOffenders) {
					Integer count = ocdxprogRepository.checkNonAssociationGroupConflict(new BigDecimal(activeOffenderObj.getOffenderBookId()), new BigDecimal(vOffPrgm.getOffenderBookId()));
					if (count > 0) {
						String Msg = null;
							Msg = "ociscata.offender " + bean.getLastName() + "," + bean.getFirstName() + " , " + bean.getOffenderId() + " ociscata.hasNonAssociation " + " \n" + " "
									+ offenderCount + ") " + activeOffenderObj.getOffenderName() + " , " + activeOffenderObj.getOffenderId() + " \n";
							offenderCount++;
							gangNonAssociationMessages = gangNonAssociationMessages + Msg + "\n";
					}
				}
				if (gangNonAssociationMessages != null && !gangNonAssociationMessages.equalsIgnoreCase("")) {
					gangNonAssociationMessages = "ociscata.gangnonassocconflict:\n\n" + gangNonAssociationMessages;
				}
//					if(Msg != null) {
//						Msg = Msg + " \n\n " + "ociscata.doyouwanttoproceed";
//						returnMessage = Msg;
//						vOffPrgm.setConflictMsg(Msg);
//					}else {
//						vOffPrgm.setConflictMsg(null);
//					}
				}
			}
			if((individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")) && (gangNonAssociationMessages != null && !gangNonAssociationMessages.equalsIgnoreCase(""))) {
				returnMessage = getFinalMessageString(individualNonAssociationMessages + "\n" + gangNonAssociationMessages);
			} else if(individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")){
				returnMessage = getFinalMessageString(individualNonAssociationMessages);
			} else if (gangNonAssociationMessages != null && !gangNonAssociationMessages.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(gangNonAssociationMessages);
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}

		} else {
			returnMessage = ApplicationConstants.EMPTYDATA;
		}
	} catch (Exception e) {
		logger.error(this.getClass().getName() + "Error in getNonAssociationsData", e);
	}
	return returnMessage;
}

@Override
public List<OffenderSentences> rgOffenderSentencesRecordGroupBothCustAndNonCust(Integer offenderBookId) {
	List<VOffenderSentCondActs> sentenceRecordGrp = new ArrayList<VOffenderSentCondActs>();
	List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();
	try {
		sentenceRecordGrp = ocdxprogRepository.rgOffenderSentencesRecordGroup(offenderBookId);

		for (VOffenderSentCondActs obj : sentenceRecordGrp) {
			obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
			ObjectMapper mapper = new ObjectMapper();
			try {
				ObjectMapper orderMapper = new ObjectMapper();
				Map<String, String> orderMap = orderMapper.readValue(obj.getFormIdentifier(), Map.class);
				if (orderMap.get("orderType") != null && (orderMap.get("orderType").equalsIgnoreCase("NONCUST")
						|| orderMap.get("orderType").equalsIgnoreCase("CUST"))) {
					Map<String, Object> map = mapper.readValue(obj.getFormInfoJson(), Map.class);
					if (map != null && map.get("myJsonRowData") != null) {
						List<SentenceCalcTypes> sentenceTypes = new ArrayList<SentenceCalcTypes>();
						sentenceTypes = ocdxprogRepository.getSentenceData();
						List<Map<String, Object>> Sentencelist = new ArrayList();
						Sentencelist = (List<Map<String, Object>>) map.get("myJsonRowData");
						for (Map<String, Object> sentenceObj : Sentencelist) {
							Integer sentenceSeq = null;
							try {
								sentenceSeq = (Integer) sentenceObj.get("orderNo");
							} catch (Exception e) {
								logger.error("Exception in " + this.getClass().getName()
										+ " rgOffenderSentencesRecordGroup method while converting OrderNo to Integer",
										e.getMessage());
							}
							if (sentenceSeq == null) {
								continue;
							}
							obj.setOffenderBookId(new BigDecimal(orderMap.get("offenderBookId").toString()));

							if (sentenceSeq != null/* && sentenceSeq == obj.getSentenceSeq() */) {
								List<OffenderSentences> filteredList = returnList.stream().filter(
										e -> obj.getOffenderBookId().equals(new BigDecimal(e.getOffenderBookId()))
												&& e.getSentenceSeq() == (Integer) sentenceObj.get("orderNo"))
										.collect(Collectors.toList());

								if (filteredList != null && filteredList.size() >= 0) {
									OffenderSentences offenderConditions = new OffenderSentences();
									offenderConditions
											.setOffenderBookId(new Long(offenderBookId));
									offenderConditions.setSentenceSeq(new Long(sentenceObj.get("orderNo").toString()));
									offenderConditions.setOrderType(sentenceObj.get("orderType").toString());
									offenderConditions.setSentenceCategory(sentenceObj.get("displayNo").toString());
									String court = ocdxprogRepository.getCourtData(
											sentenceObj.get("court") != null ? sentenceObj.get("court").toString()
													: null);
									offenderConditions.setJurisCode(court);
									for (SentenceCalcTypes obj1 : sentenceTypes) {
										if (sentenceObj.get("sentenceCalcType") != null && (obj1.getSentenceCalcType()
												.equalsIgnoreCase(sentenceObj.get("sentenceCalcType").toString()))) {
											offenderConditions.setDescription(obj1.getDescription());
										}
									}
									if (sentenceObj.get("commenceDate") != null
											&& sentenceObj.get("commenceDate").toString().trim() != "") {
										LocalDateTime ldt = LocalDateTime
												.parse(sentenceObj.get("commenceDate").toString());
										offenderConditions.setStartDate(
												Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
									}
									if (sentenceObj.get("expiryDate") != null
											&& sentenceObj.get("expiryDate").toString().trim() != "") {
										LocalDateTime ldt = LocalDateTime
												.parse(sentenceObj.get("expiryDate").toString());
										offenderConditions
												.setEndDate(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
									}
									returnList.add(offenderConditions);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception in OcduprojServiceImpl class getSentenceData method", e.getMessage());
			}

		}

	} catch (Exception e) {
		logger.error("Error in rgOffenderSentencesRecordGroup class : ", e.getMessage());
	}
	return returnList;
}
}