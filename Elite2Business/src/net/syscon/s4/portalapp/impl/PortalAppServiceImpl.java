package net.syscon.s4.portalapp.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.beans.OffenderCaseCommitBean;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.SentenceCommitBean;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.globaloffenderrecords.OcucoffeService;
import net.syscon.s4.globaloffenderrecords.OsiosearRepository;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.inst.demographicsbiometrics.OcdaliasService;
import net.syscon.s4.inst.legals.KeyDatesService;
import net.syscon.s4.inst.legals.OcdcCasesService;
import net.syscon.s4.inst.legals.OumorcodService;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.KeyDates;
import net.syscon.s4.inst.legals.beans.OffenceOutcomeCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderCourtEventCommitBean;
import net.syscon.s4.inst.legals.beans.OffenseResultCodes;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;
import net.syscon.s4.inst.legals.beans.SentenceDate;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.legals.impl.OcusofncServiceImpl;
import net.syscon.s4.inst.schedules.OidscmovRepository;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.portalapp.PortalAppDao;
import net.syscon.s4.portalapp.PortalAppService;
import net.syscon.s4.portalapp.beans.CaseOffense;
import net.syscon.s4.portalapp.beans.CaseOffenseResponse;
import net.syscon.s4.portalapp.beans.CaseScheduleInfo;
import net.syscon.s4.portalapp.beans.CaseSentence;
import net.syscon.s4.portalapp.beans.CaseSentenceResponse;
import net.syscon.s4.portalapp.beans.CourtCaseInfo;
import net.syscon.s4.portalapp.beans.CourtCaseResponse;
import net.syscon.s4.portalapp.beans.CourtScheduleInfo;
import net.syscon.s4.portalapp.beans.CourtScheduleResponse;
import net.syscon.s4.portalapp.beans.OffenderInfo;
import net.syscon.s4.portalapp.beans.RejectOffenderInfo;
import net.syscon.s4.portalapp.beans.Term;

@Service
public class PortalAppServiceImpl implements PortalAppService {
	
	private static Logger log = LogManager.getLogger(PortalAppServiceImpl.class);
	
	@Autowired
	private OidscmovRepository oidscmovRepo;
	
	@Autowired
	private PortalAppDao portalAppDao;
	
	@Autowired
	private OsiosearRepository osiosearRepository;
	
	@Autowired
	private EliteDateService dateService;
	
	@Autowired
	private Omss40Service omss40Service;
	
	@Autowired
	private OcdcCasesService odcdCaseService;
	
	@Autowired
	private OumorcodService oumorcodService;
	
	@Autowired
	private KeyDatesService keyDatesService;
	
	@Autowired
	private OcusofncServiceImpl ocusofncServiceImpl;
	
	@Autowired
	private OcdaliasService ocdalias;
	
	/**
	 * Get All New bookings arrived from Third Party
	 */
	@Override
	public List<OffenderInfo> getAllNewBookings(String actionCode) {
		
		List<OffenderInfo> inputPayloads = portalAppDao.getAllNewBookings(actionCode);
		
		List<OffenderInfo> offenderDetails = new ArrayList<OffenderInfo>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		for (OffenderInfo offednerInfo: inputPayloads) {
			String inputPayload = offednerInfo.getInputPayload();
			try {
				OffenderInfo offenderDetail = objectMapper.readValue(inputPayload, OffenderInfo.class);
				offenderDetail.setRequestId(offednerInfo.getRequestId());
				offenderDetail.setPersonId(buildOffednerDisplayId(offenderDetail.getPersonId()));
				offenderDetail.setLastName(offenderDetail.getLastName()!=null?offenderDetail.getLastName().toUpperCase():null);
				offenderDetail.setFirstName(offenderDetail.getFirstName()!=null?offenderDetail.getFirstName().toUpperCase():null);
				
				offenderDetails.add(offenderDetail);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return offenderDetails;
	}
	
	@Override
	public List<CourtScheduleInfo> getAllNewSchedules(String actionCode) {
		
		List<OffenderInfo> inputPayloads = portalAppDao.getAllNewBookings(actionCode);
		
		List<CourtScheduleInfo> courtSchedules = new ArrayList<CourtScheduleInfo>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		for (OffenderInfo offednerInfo: inputPayloads) {
			String inputPayload = offednerInfo.getInputPayload();
			try {
				CourtScheduleInfo courtSchedule = objectMapper.readValue(inputPayload, CourtScheduleInfo.class);
				courtSchedule.setRequestId(offednerInfo.getRequestId());
				courtSchedule.setPersonId(buildOffednerDisplayId(courtSchedule.getPersonId()));
				try {
					String[] times = courtSchedule.getMovementTime().split(":");
					Date date = courtSchedule.getMovementDate();
					Calendar calendar =  Calendar.getInstance();
					calendar.setTime(date);
					calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), Integer.parseInt(times[0]), Integer.parseInt(times[1]));
					Date startTime = calendar.getTime();
					courtSchedule.setMovementStartTime(startTime);
					
					Calendar calendarMoveDate = Calendar.getInstance();
					calendarMoveDate.setTime(date);
					calendarMoveDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
					Date movementDate = calendarMoveDate.getTime();
					courtSchedule.setMovementDate(movementDate);
					
				} catch(Exception ex) {
					log.error(ex.getMessage());
				}
				courtSchedules.add(courtSchedule);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return courtSchedules;
	}
	
	
	@Override
	public List<OffenderInfo> getAllNonPendingBookings() {
		List<OffenderInfo> inputPayloads = portalAppDao.getAllNonPendingBookings();
		
		List<OffenderInfo> offenderDetails = new ArrayList<OffenderInfo>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		for (OffenderInfo offednerInfo: inputPayloads) {
			String inputPayload = offednerInfo.getInputPayload();
			try {
				OffenderInfo offenderDetail = objectMapper.readValue(inputPayload, OffenderInfo.class);
				offenderDetail.setRequestId(offednerInfo.getRequestId());
				offenderDetail.setPersonId(buildOffednerDisplayId(offenderDetail.getPersonId()));
				offenderDetail.setLastName(offenderDetail.getLastName()!=null?offenderDetail.getLastName().toUpperCase():null);
				offenderDetail.setFirstName(offenderDetail.getFirstName()!=null?offenderDetail.getFirstName().toUpperCase():null);
				offenderDetail.setRequestStatus(offednerInfo.getRequestStatus());
				offenderDetail.setModifyDate(offednerInfo.getModifyDate());
				offenderDetail.setCreateDate(offednerInfo.getCreateDate());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return offenderDetails;
	}
	
	
	@Override
	public List<CourtCaseInfo> getAllPendingLegals() {
		List<OffenderInfo> inputPayloads = portalAppDao.getFailedLegals();
		
		List<CourtCaseInfo> offenderDetails = new ArrayList<CourtCaseInfo>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		for (OffenderInfo offednerInfo: inputPayloads) {
			String inputPayload = offednerInfo.getInputPayload();
			try {
				CourtCaseInfo courtCaseInfo = objectMapper.readValue(inputPayload, CourtCaseInfo.class);
				courtCaseInfo.setRequestId(offednerInfo.getRequestId());
				//courtCaseInfo.setPersonId(buildOffednerDisplayId(courtCaseInfo.getPersonId().toString()));
				//courtCaseInfo.setRequestStatus(offednerInfo.getRequestStatus());
				//offenderDetail.setModifyDate(offednerInfo.getModifyDate());
				//offenderDetail.setCreateDate(offednerInfo.getCreateDate());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return offenderDetails;
	}
	
	
	
	private String buildOffednerDisplayId (String personId) {
		String offenderIdDisplay = personId;
		if (personId!=null && personId.length() < 10) {
			for (int i = personId.length(); i < 10; i++) {
				offenderIdDisplay = "0" + offenderIdDisplay;
			}
		}
		return offenderIdDisplay;
	}
	/**
	 * 
	 */
	public List<VHeaderBlock2> getOffendersInfo(List<String> offenderIds,String userName) {
		
		List<VHeaderBlock2> list = osiosearRepository.searchVHeaderBlock2ByOffenderIds(offenderIds, userName);
		VHeaderBlock vhbBean = new VHeaderBlock();
		for (final VHeaderBlock2 listval : list) {	
			listval.setAge(BigDecimal.valueOf(dateService.calculateAge(listval.getBirthDate())));
			if(listval.getOffenderBookId()!=BigDecimal.ZERO){
				vhbBean = new VHeaderBlock();
				vhbBean.setOffenderBookId(listval.getOffenderBookId());
				final VHeaderBlock returnObj = osiosearRepository.getVheaderBlockData(vhbBean);
				listval.setGender(returnObj.getGender());
				listval.setOffAlerts(returnObj.getOffAlerts());
				listval.setPrisonLocation(returnObj.getPrisonLocation());
				listval.setStatus1(returnObj.getStatus1());
				listval.setMovementReason(returnObj.getMovementReason());
				listval.setOffSupLevel(returnObj.getOffSupLevel());
				listval.setStatusDisplay(returnObj.getStatusDisplay());
				/*if (listval.getOffenderIdDisplay() != null && listval.getBookingNo() != null) {
					VHeaderBlock data = new VHeaderBlock();
					data.setOffenderIdDisplay(listval.getOffenderIdDisplay());
					data.setBookingNo(listval.getBookingNo());
					data.setAgyLocId(searchBean.getAgyLocId());
					List<VHeaderBlock> gettingData = offbkgGlobalQuery(data);
					if (gettingData.size() > 0) {
						listval.setCreateAccount(true);
					} else {
						listval.setCreateAccount(false);
					}
				}*/
			} else {
				vhbBean.setOffenderId(listval.getOffenderId());
				listval.setGender(osiosearRepository.getVheaderBlockDataFromOffenderId(vhbBean).getGender());
			}
		}
		return list;
	}

	
	
	@Override
	public Integer rejectPersonToAdmit(RejectOffenderInfo rejectOffenderInfo) {
		return portalAppDao.rejectPersonToAdmit(rejectOffenderInfo);
	}
	@Transactional
	@Override
	public Integer acceptPersonToAdmit(OffenderInfo offenderInfo) {
		return portalAppDao.acceptPersonToAdmit(offenderInfo);
	}
	/*
	 * (non-Javadoc)
	 * @see net.syscon.s4.portalapp.PortalAppService#searchMatchedOffedner(net.syscon.s4.portalapp.beans.OffenderInfo)
	 */
	@Override
	public List<VHeaderBlock2> searchMatchedOffedner(OffenderInfo offenderInfo) {
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.getNumberOfExactMatchesOffeneder(offenderInfo);
		List<VHeaderBlock2> maybeMatchedOffednrs = portalAppDao.getNumberOfMaybeMatchesOffeneder(offenderInfo);
		exactMatchedOffedners.addAll(maybeMatchedOffednrs);
		List<String> offenderIds = exactMatchedOffedners.stream().map(vOffedner-> vOffedner.getOffenderId().toString()).collect(Collectors.toList());
		List<VHeaderBlock2> matchedOffedenrs = null;
		if(offenderIds!=null && offenderIds.size()>0) {
			matchedOffedenrs = getOffendersInfo(offenderIds,offenderInfo.getCreateUserId());
		}
		return matchedOffedenrs;
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.syscon.s4.portalapp.PortalAppService#createCourtSchedules(java.util.List)
	 */
	@Override
	public List<CourtScheduleResponse> createCourtSchedules(List<CourtScheduleInfo> courtScheduleInfoList) {
		
		List<String> personIds = courtScheduleInfoList.stream().map(CourtScheduleInfo::getPersonId).collect(Collectors.toList());
		List<CourtScheduleResponse> response =  new ArrayList<>();
		
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.offenderBookingAvailable(personIds);
		if(exactMatchedOffedners !=null && exactMatchedOffedners.size()>0) {
			Map<String, BigDecimal> bookingMap  = exactMatchedOffedners.stream().collect(Collectors.toMap(VHeaderBlock2:: getOffenderIdDisplay, VHeaderBlock2 :: getOffenderBookId));
			
			
			List<CourtEvents> insertCourtList = new ArrayList<>();
			List<CourtEvents> updateCourtList = new ArrayList<>();
			
			for (final CourtScheduleInfo obj : courtScheduleInfoList) {
				final CourtEvents courtEvents = new CourtEvents();
				CourtScheduleResponse courtScheduleResponse =  new CourtScheduleResponse();
				courtScheduleResponse.setPersonId(obj.getPersonId());
				courtScheduleResponse.setRequestId(obj.getRequestId());
				courtScheduleResponse.setOffenderBookId(bookingMap.get(obj.getPersonId()));
				if(obj.getScheduleId() != null) {
					//crtEveUpdateCourtEvents
					courtEvents.setOffenderBookId(bookingMap.get(obj.getPersonId()).intValue());
					courtEvents.setEventDate(obj.getMovementDate());
					courtEvents.setStartTime(obj.getMovementStartTime());
					courtEvents.setAgyLocId(obj.getToCourt());
					courtEvents.setCommentText(obj.getComment());
					courtEvents.setCourtEventType(obj.getMovementReason());
					courtEvents.setEventStatus("SCH");
					courtEvents.setJudgeName(obj.getJudgeName());
					courtEvents.setDirectionCode("OUT");
					courtEvents.setEventId(obj.getScheduleId().intValue());
					updateCourtList.add(courtEvents);
					final Integer returnValue = oidscmovRepo.crtEveUpdateCourtEvents(updateCourtList);
					if(returnValue==1) {
						courtScheduleResponse.setNotProcessingReasonCode("SUCCESS");
						courtScheduleResponse.setStatus("SUCCESS");
						courtScheduleResponse.setNotProcessingReasonDescription("Person court schedule updated");
						courtScheduleResponse.setEventScheduleId(obj.getScheduleId());
						response.add(courtScheduleResponse);
						//portalAppDao.updateScheduleOutputPayload(courtScheduleResponse);
					} else {
						courtScheduleResponse.setNotProcessingReasonCode("SCHEDULE_UPDATE_FAILED");
						courtScheduleResponse.setStatus("FAILED");
						courtScheduleResponse.setNotProcessingReasonDescription("Person court schedule failed to update");
						courtScheduleResponse.setEventScheduleId(obj.getScheduleId());
						response.add(courtScheduleResponse);
						//portalAppDao.updateScheduleOutputPayload(courtScheduleResponse);
					}
					
				} else {
					if(bookingMap.get(obj.getPersonId()) != null) {
						courtEvents.setOffenderBookId(bookingMap.get(obj.getPersonId()).intValue());
						courtEvents.setEventDate(obj.getMovementDate());
						courtEvents.setStartTime(obj.getMovementStartTime());
						courtEvents.setAgyLocId(obj.getToCourt());
						courtEvents.setCommentText(obj.getComment());
						courtEvents.setCourtEventType(obj.getMovementReason());
						courtEvents.setEventStatus("SCH");
						courtEvents.setJudgeName(obj.getJudgeName());
						courtEvents.setDirectionCode("OUT");
						//TODO Verify NBT need to set
						//final Integer returnValue = oidscmovRepo.checkScheduleConflict(courtEvents);
						/*if (returnValue > 0 ) {
							courtScheduleResponse.setNotProcessingReasonCode("PERSON_SCHEDULE_CONFLICT");
							courtScheduleResponse.setNotProcessingReasonDescription("Person scheduled for this duration");
							courtScheduleResponse.setStatus("FAILED");
							response.add(courtScheduleResponse);
							//throw new ArithmeticException("Conflict Occured");
							//Store these ids in failed response with reason why it has failed.
						} else {
						*/	BigDecimal eventScheduleId = oidscmovRepo.crtEveInsertCourtEventsPreEventId(courtEvents);
							courtScheduleResponse.setNotProcessingReasonCode("SUCCESS");
							courtScheduleResponse.setStatus("SUCCESS");
							courtScheduleResponse.setNotProcessingReasonDescription("Person court schedule created");
							courtScheduleResponse.setEventScheduleId(eventScheduleId);
							response.add(courtScheduleResponse);
							// Add these ids in processed ids
						//}
					} else {
						courtScheduleResponse.setStatus("PENDING");
						courtScheduleResponse.setNotProcessingReasonCode("PERSON_NOT_ADMITED");
						courtScheduleResponse.setNotProcessingReasonDescription("Person is not admitted");
						//courtScheduleResponse.setStatus("FAILED");
						response.add(courtScheduleResponse);
					}
				}
				//UPDATE QUEUE OUTPUT_PAYLOAD
				portalAppDao.updateScheduleOutputPayload(courtScheduleResponse);
				
			}
			
		} else {
			for(final CourtScheduleInfo obj : courtScheduleInfoList) {
				CourtScheduleResponse courtScheduleResponse =  new CourtScheduleResponse();
				courtScheduleResponse.setPersonId(obj.getPersonId());
				courtScheduleResponse.setRequestId(obj.getRequestId());
				courtScheduleResponse.setStatus("PENDING");
				courtScheduleResponse.setNotProcessingReasonCode("PERSON_NOT_ADMITED");
				courtScheduleResponse.setNotProcessingReasonDescription("Person is not admitted");
				response.add(courtScheduleResponse);
				portalAppDao.updateScheduleOutputPayload(courtScheduleResponse);
			}
			
		}
		return response;
	}
	
	
	@Override
	public CourtCaseResponse createCourtCase(CourtCaseInfo courtCaseInfo) {
		
		List<String> personIds = new ArrayList<>();
		personIds.add(buildOffednerDisplayId(courtCaseInfo.getPersonId().toString()));
		
		log.info(personIds);
		
		List<CourtScheduleResponse> response =  new ArrayList<>();
		CourtScheduleResponse courtCaseResponse =  new CourtScheduleResponse();
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.offenderBookingAvailable(personIds);
		if(exactMatchedOffedners !=null && exactMatchedOffedners.size()>0) {
			Map<String, BigDecimal> bookingMap  = exactMatchedOffedners.stream().collect(Collectors.toMap(VHeaderBlock2:: getOffenderIdDisplay, VHeaderBlock2 :: getOffenderBookId));
		    BigDecimal offenderBookId  =  bookingMap.get(buildOffednerDisplayId(courtCaseInfo.getPersonId().toString()));
			OffenderCaseCommitBean courtCaseCommitBean = new OffenderCaseCommitBean();
			List<CourtCases> courtCases = new ArrayList<>();
			CourtCases courtCase = new CourtCases();
			courtCase.setOffenderBookId(offenderBookId.longValue());
			courtCase.setAgy_loc_id(courtCaseInfo.getToCourt());
			courtCase.setBeginDate(courtCaseInfo.getCasestartDate());
			courtCase.setCaseInfoNumber(courtCaseInfo.getCaseInfoNumber());
			courtCase.setCaseType(courtCaseInfo.getCaseType());
			courtCase.setCaseStatus("A");
			//courtCase.setCaseInfoPrefix("DOCKET");
			
			courtCases.add(courtCase);
			if(courtCaseInfo.getCaseId() == null && courtCaseInfo.getCaseInfoNumber() != null) {
				courtCaseCommitBean.setInsertList(courtCases);
			} else if(courtCaseInfo.getCaseId() != null) {
				courtCase.setcaseId(courtCaseInfo.getCaseId());
				courtCaseCommitBean.setUpdateList(courtCases);
			}
			if(courtCaseInfo.getCaseId() != null || courtCaseInfo.getCaseInfoNumber() != null) {
				OffenderCaseCommitBean returnBean = odcdCaseService.insertNewCaseRCE(courtCaseCommitBean);
				if(returnBean.getInsertList() !=null && returnBean.getInsertList().size()>0) {
					CourtCases retrunCourtCases = returnBean.getInsertList().get(0);
					courtCaseResponse.setCaseId(new BigDecimal(retrunCourtCases.getcaseId()));
				} else {
					courtCaseResponse.setCaseId(new BigDecimal(courtCaseInfo.getCaseId()));
				}
				courtCaseResponse.setStatus("SUCCESS");
			} else {
				courtCaseResponse.setNotProcessingReasonCode("FAILED");
				courtCaseResponse.setStatus("FAILED");
				courtCaseResponse.setNotProcessingReasonDescription("Case Info Number or Case Id is not provided");
			}
			//TODO Set Court Case Id.
			courtCaseResponse.setPersonId(courtCaseInfo.getPersonId().toString());
			courtCaseResponse.setRequestId(courtCaseInfo.getRequestId());
			courtCaseResponse.setOffenderBookId(offenderBookId);
			portalAppDao.updateScheduleOutputPayload(courtCaseResponse);
		} else {
			courtCaseResponse.setPersonId(courtCaseInfo.getPersonId().toString());
			courtCaseResponse.setRequestId(courtCaseInfo.getRequestId());
			courtCaseResponse.setNotProcessingReasonCode("FAILED");
			courtCaseResponse.setStatus("FAILED");
			courtCaseResponse.setNotProcessingReasonDescription("Person Id does not match in system.");
			portalAppDao.updateScheduleOutputPayload(courtCaseResponse);
		}
		
		return new CourtCaseResponse();
	}
	
	@Override
	public CourtScheduleResponse insertUpdateSchedules(CourtScheduleInfo courtScheduleInfo) {
		List<String> personIds = new ArrayList<>();
		personIds.add(buildOffednerDisplayId(courtScheduleInfo.getPersonId().toString()));
		log.info(personIds);
		List<CourtScheduleResponse> response =  new ArrayList<>();
		CourtCaseResponse courtCaseResponse =  new CourtCaseResponse();
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.offenderBookingAvailable(personIds);
		CourtScheduleResponse courtScheduleResponse = new CourtScheduleResponse();
		if(exactMatchedOffedners !=null && exactMatchedOffedners.size()>0) {
			Map<String, BigDecimal> bookingMap  = exactMatchedOffedners.stream().collect(Collectors.toMap(VHeaderBlock2:: getOffenderIdDisplay, VHeaderBlock2 :: getOffenderBookId));
		    BigDecimal offenderBookId  =  bookingMap.get(buildOffednerDisplayId(courtScheduleInfo.getPersonId().toString()));
			OffenderCourtEventCommitBean courtEventCommitBean = new OffenderCourtEventCommitBean();
			List<CourtEvent> courtEventList = new ArrayList<>();
			CourtEvent courtEvent = new CourtEvent();
			courtEvent.setOffenderBookId(offenderBookId.longValue());
			String[] times = courtScheduleInfo.getMovementTime().split(":");
			Date date = courtScheduleInfo.getMovementDate();
			Calendar calendar =  Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), Integer.parseInt(times[0]), Integer.parseInt(times[1]));
			Date startTime = calendar.getTime();
			courtScheduleInfo.setMovementStartTime(startTime);
			
			Calendar calendarMoveDate = Calendar.getInstance();
			calendarMoveDate.setTime(date);
			calendarMoveDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
			Date movementDate = calendarMoveDate.getTime();
			courtScheduleInfo.setMovementDate(movementDate);
			
			courtEvent.setEventDate(courtScheduleInfo.getMovementDate());
			courtEvent.setStartTime(courtScheduleInfo.getMovementStartTime());
			courtEvent.setAgyLocId(courtScheduleInfo.getToCourt());
			courtEvent.setHearingType(courtScheduleInfo.getMovementReason());
			courtEvent.setCommentText(courtScheduleInfo.getComment());
			courtEvent.setCaseId(courtScheduleInfo.getCaseId().longValue());
			if(courtScheduleInfo.getScheduleId()!=null) {
				courtEvent.setCommitFlag("u");
				courtEvent.setEventId(courtScheduleInfo.getScheduleId().longValue());
				courtEventList.add(courtEvent);
				courtEventCommitBean.setUpdateList(courtEventList);
			} else {
				courtEvent.setCommitFlag("i");
				courtEventList.add(courtEvent);
				courtEventCommitBean.setInsertList(courtEventList);
			}
			OffenderCourtEventCommitBean returnCourtCaseCommitBean = odcdCaseService.insertNewEventREO(courtEventCommitBean);
			
			courtScheduleResponse .setPersonId(courtScheduleInfo.getPersonId().toString());
			courtScheduleResponse.setRequestId(courtScheduleInfo.getRequestId());
			courtScheduleResponse.setOffenderBookId(offenderBookId);
			if(returnCourtCaseCommitBean.getInsertList()!=null && returnCourtCaseCommitBean.getInsertList().size()>0) {
				CourtEvent returnCourtevent = returnCourtCaseCommitBean.getInsertList().get(0);
				courtScheduleResponse.setEventScheduleId(new BigDecimal(returnCourtevent.getEventId()));
				courtScheduleResponse.setCaseId(new BigDecimal(returnCourtevent.getCaseId()));
			}
			courtScheduleResponse.setStatus("SUCCESS");
			portalAppDao.updateScheduleOutputPayload(courtScheduleResponse);
		} else {
			courtScheduleResponse.setPersonId(courtScheduleInfo.getPersonId().toString());
			courtScheduleResponse.setRequestId(courtScheduleInfo.getRequestId());
			courtScheduleResponse.setNotProcessingReasonCode("FAILED");
			courtScheduleResponse.setStatus("FAILED");
			courtScheduleResponse.setNotProcessingReasonDescription("Person Id does not match in system.");
			portalAppDao.updateScheduleOutputPayload(courtScheduleResponse);
		}
		
		return new CourtScheduleResponse();
	}
	
	@Override
	public CourtCaseResponse createCourtCaseSequence(CaseScheduleInfo courtCaseInfo) {
		
		List<String> personIds = new ArrayList<>();
		personIds.add(buildOffednerDisplayId(courtCaseInfo.getPersonId().toString()));
		log.info(personIds);
		List<CourtScheduleResponse> response =  new ArrayList<>();
		CourtCaseResponse courtCaseResponse =  new CourtCaseResponse();
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.offenderBookingAvailable(personIds);
		CourtScheduleResponse courtScheduleResponse = new CourtScheduleResponse();
		if(exactMatchedOffedners !=null && exactMatchedOffedners.size()>0) {
			Map<String, BigDecimal> bookingMap  = exactMatchedOffedners.stream().collect(Collectors.toMap(VHeaderBlock2:: getOffenderIdDisplay, VHeaderBlock2 :: getOffenderBookId));
		    BigDecimal offenderBookId  =  bookingMap.get(buildOffednerDisplayId(courtCaseInfo.getPersonId().toString()));
			final CourtEvents courtEvents = new CourtEvents();
			if(courtCaseInfo.getCaseId() == null && courtCaseInfo.getCaseInfoNumber() != null) {
				OffenderCaseCommitBean courtCaseCommitBean = new OffenderCaseCommitBean();
				
				List<CourtCases> newCourtCases = new ArrayList<>();
				CourtCases courtCase = new CourtCases();
				courtCase.setOffenderBookId(offenderBookId.longValue());
				courtCase.setAgy_loc_id(courtCaseInfo.getToCourt());
				courtCase.setBeginDate(courtCaseInfo.getCasestartDate());
				courtCase.setCaseInfoNumber(courtCaseInfo.getCaseInfoNumber());
				courtCase.setCaseType(courtCaseInfo.getCaseType());
				courtCase.setCaseStatus("A");
				//courtCase.setCaseInfoPrefix("DOCKET");
				CourtScheduleInfo courtScheduleInfo = courtCaseInfo.getSchedule();
				
				if(courtScheduleInfo != null) {
					List<CourtEvent> courtEventList = new ArrayList<>();
					CourtEvent courtEvent = new CourtEvent();
					courtEvent.setOffenderBookId(offenderBookId.longValue());
					String[] times = courtScheduleInfo.getMovementTime().split(":");
					Date date = courtScheduleInfo.getMovementDate();
					Calendar calendar =  Calendar.getInstance();
					calendar.setTime(date);
					calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), Integer.parseInt(times[0]), Integer.parseInt(times[1]));
					Date startTime = calendar.getTime();
					courtScheduleInfo.setMovementStartTime(startTime);
					
					Calendar calendarMoveDate = Calendar.getInstance();
					calendarMoveDate.setTime(date);
					calendarMoveDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
					Date movementDate = calendarMoveDate.getTime();
					courtScheduleInfo.setMovementDate(movementDate);
					
					courtEvent.setEventDate(courtScheduleInfo.getMovementDate());
					courtEvent.setStartTime(courtScheduleInfo.getMovementStartTime());
					courtEvent.setAgyLocId(courtCaseInfo.getToCourt());
					courtEvent.setHearingType(courtScheduleInfo.getMovementReason());
					courtEvent.setCommentText(courtScheduleInfo.getComment());
					courtEvent.setCommitFlag("i");
					
					courtEventList.add(courtEvent);
					
					courtCase.setCourtEventList(courtEventList);
				}
				newCourtCases.add(courtCase);
				courtCaseCommitBean.setInsertList(newCourtCases);
				OffenderCaseCommitBean returnCourtCaseCommitBean = odcdCaseService.insertNewCaseRCE(courtCaseCommitBean);
				
				
				courtScheduleResponse .setPersonId(courtCaseInfo.getPersonId().toString());
				courtScheduleResponse.setRequestId(courtCaseInfo.getRequestId());
				courtScheduleResponse.setOffenderBookId(offenderBookId);
				if(returnCourtCaseCommitBean.getInsertList()!=null && returnCourtCaseCommitBean.getInsertList().size()>0) {
					CourtCases returnCourtCase = returnCourtCaseCommitBean.getInsertList().get(0);
					if(returnCourtCase.getCourtEventList()!=null && returnCourtCase.getCourtEventList().size()>0) {
						courtScheduleResponse.setEventScheduleId(new BigDecimal(returnCourtCase.getCourtEventList().get(0).getEventId()));
						courtScheduleResponse.setCaseId(new BigDecimal(returnCourtCase.getcaseId()));
					}
				}
				courtScheduleResponse.setStatus("SUCCESS");
				portalAppDao.updateScheduleOutputPayload(courtScheduleResponse);
			}
		} else {
			courtScheduleResponse.setPersonId(courtCaseInfo.getPersonId().toString());
			courtScheduleResponse.setRequestId(courtCaseInfo.getRequestId());
			courtScheduleResponse.setNotProcessingReasonCode("FAILED");
			courtScheduleResponse.setStatus("FAILED");
			courtScheduleResponse.setNotProcessingReasonDescription("Person Id does not match in system.");
			portalAppDao.updateScheduleOutputPayload(courtScheduleResponse);
		}
		
		return courtCaseResponse;
	}
	
	private String getDefaultMovementReason() {
		List<String> profileCodes = new ArrayList<String>();
		profileCodes.add("DB");

		List<ProfileCodes> profileCodesList = omss40Service.searchProfileCodes(profileCodes);
		if (profileCodesList != null && profileCodesList.size() > 0) {
			for (ProfileCodes profileObj : profileCodesList) {
				if(profileObj.getProfileCode().equalsIgnoreCase("DEF_HEAR")) {
					return profileObj.getProfileValue();
				}
			}
		}
		return "APP";
	}
	
	@Override
	public CaseOffenseResponse createCourtCaseOffense(CaseOffense caseOffense) {
		
		List<String> personIds = new ArrayList<>();
		personIds.add(buildOffednerDisplayId(caseOffense.getPersonId().toString()));
		log.info(personIds);
		List<CaseOffenseResponse> response =  new ArrayList<>();
		CaseOffenseResponse caseOffenseResponse =  new CaseOffenseResponse();
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.offenderBookingAvailable(personIds);
		CaseOffenseResponse returnCaseOffenseResponse = new CaseOffenseResponse();
		Long offenderBookId = null;
		if(exactMatchedOffedners !=null && exactMatchedOffedners.size()>0) {
			/*
			 * Check Latest Schedule of the case - CourtEvent
			 * 
			 */
			Long courtEventId = null;
			CourtEvent courtEvent =  odcdCaseService.fetchLatestCourtevent(caseOffense.getCaseId());
			if(courtEvent != null && courtEvent.getEventId() != null) {
				courtEventId = courtEvent.getEventId();
			} else {
				// TODO Create dummy event In a given Case
				CourtScheduleInfo courtScheduleInfo = new CourtScheduleInfo();
				courtScheduleInfo.setPersonId(caseOffense.getPersonId());
				courtScheduleInfo.setCaseId(new BigDecimal(caseOffense.getCaseId()));
				courtScheduleInfo.setMovementDate(new Date());
				courtScheduleInfo.setMovementTime("09:00");
				courtScheduleInfo.setMovementReason(getDefaultMovementReason());
				courtEventId  = getDefaultEvent(courtScheduleInfo);
			}
			offenderBookId = exactMatchedOffedners.get(0).getOffenderBookId().longValue();
			OffenceOutcomeCommitBean offenceCommitBean = new  OffenceOutcomeCommitBean();
			OffensesOutcome newOffenceOut = new OffensesOutcome();
			newOffenceOut.setCase_id(caseOffense.getCaseId());
			newOffenceOut.setEventId(courtEventId);
			newOffenceOut.setOffenderChargeId(caseOffense.getOffenseId());
			newOffenceOut.setOffenderBookId(offenderBookId);
			newOffenceOut.setOffenceCode(caseOffense.getOffenceCode());
			newOffenceOut.setOffenseType(caseOffense.getOffenseType());
			newOffenceOut.setOffenseDate(caseOffense.getOffenseDate());
			newOffenceOut.setPlea(caseOffense.getPlea());
			newOffenceOut.setRange(caseOffense.getRange());
			if(caseOffense.getResult() != null) {
				newOffenceOut.setResult(caseOffense.getResult());
				List<OffenseResultCodes> offencesResultsCodesData = oumorcodService.offencesResultsCodes();
				Optional<OffenseResultCodes>  optionalResultOutcome= offencesResultsCodesData.stream().filter(resultOutCome-> resultOutCome.getCode().equals(caseOffense.getResult())).findFirst();
				OffenseResultCodes resultOutcome = optionalResultOutcome.isPresent()?optionalResultOutcome.get():null;
				if(resultOutcome!=null) {
					newOffenceOut.setResultcode1(resultOutcome.getCode());
					newOffenceOut.setDisposition(resultOutcome.getDisposition());
					newOffenceOut.setResultcode1indicator(resultOutcome.getDisposition());
					newOffenceOut.setChargeStatus(resultOutcome.getOffenseStatus().equalsIgnoreCase("Active")?"A":"I");
				} else {
					newOffenceOut.setChargeStatus("A");
				}
				
				//newOffenceOut.set(resultOutcome.getCode());
			} else {
				newOffenceOut.setChargeStatus("A");
			}
			
			List<OffensesOutcome> offenseOutcomeList =  new ArrayList<>();
			offenseOutcomeList.add(newOffenceOut);
			if(newOffenceOut.getOffenderChargeId()!=null) {
				offenceCommitBean.setUpdateList(offenseOutcomeList);
			} else {
				offenceCommitBean.setInsertList(offenseOutcomeList);
			}
			
			OffenceOutcomeCommitBean  returnOffenseBean = odcdCaseService.insertUpdateOffenseDataROI(offenceCommitBean);
			
			returnCaseOffenseResponse .setPersonId(caseOffense.getPersonId().toString());
			returnCaseOffenseResponse.setRequestId(caseOffense.getRequestId());
			returnCaseOffenseResponse.setOffenderBookId(new BigDecimal(offenderBookId));
			if(returnOffenseBean.getInsertList()!=null && returnOffenseBean.getInsertList().size()>0) {
				OffensesOutcome returnOffense = returnOffenseBean.getInsertList().get(0);
				returnCaseOffenseResponse.setOffednerChargeId(new BigDecimal(returnOffense.getOffenderChargeId()));
			}
			returnCaseOffenseResponse.setStatus("SUCCESS");
			portalAppDao.updateOffenseOutputPayload(returnCaseOffenseResponse);
		} else {
			returnCaseOffenseResponse.setPersonId(caseOffense.getPersonId().toString());
			returnCaseOffenseResponse.setRequestId(caseOffense.getRequestId());
			returnCaseOffenseResponse.setNotProcessingReasonCode("FAILED");
			returnCaseOffenseResponse.setStatus("FAILED");
			returnCaseOffenseResponse.setNotProcessingReasonDescription("Person Id does not match in system.");
			portalAppDao.updateOffenseOutputPayload(returnCaseOffenseResponse);
		}
		return new CaseOffenseResponse();
				
	}
	
	
	private Long getDefaultEvent(CourtScheduleInfo courtScheduleInfo) {
		List<String> personIds = new ArrayList<>();
		personIds.add(buildOffednerDisplayId(courtScheduleInfo.getPersonId().toString()));
		log.info(personIds);
		List<CourtScheduleResponse> response =  new ArrayList<>();
		CourtCaseResponse courtCaseResponse =  new CourtCaseResponse();
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.offenderBookingAvailable(personIds);
		if(exactMatchedOffedners !=null && exactMatchedOffedners.size()>0) {
			Map<String, BigDecimal> bookingMap  = exactMatchedOffedners.stream().collect(Collectors.toMap(VHeaderBlock2:: getOffenderIdDisplay, VHeaderBlock2 :: getOffenderBookId));
		    BigDecimal offenderBookId  =  bookingMap.get(buildOffednerDisplayId(courtScheduleInfo.getPersonId().toString()));
			OffenderCourtEventCommitBean courtEventCommitBean = new OffenderCourtEventCommitBean();
			List<CourtEvent> courtEventList = new ArrayList<>();
			CourtEvent courtEvent = new CourtEvent();
			courtEvent.setOffenderBookId(offenderBookId.longValue());
			String[] times = courtScheduleInfo.getMovementTime().split(":");
			Date date = courtScheduleInfo.getMovementDate();
			Calendar calendar =  Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), Integer.parseInt(times[0]), Integer.parseInt(times[1]));
			Date startTime = calendar.getTime();
			courtScheduleInfo.setMovementStartTime(startTime);
			
			Calendar calendarMoveDate = Calendar.getInstance();
			calendarMoveDate.setTime(date);
			calendarMoveDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
			Date movementDate = calendarMoveDate.getTime();
			courtScheduleInfo.setMovementDate(movementDate);
			
			courtEvent.setEventDate(courtScheduleInfo.getMovementDate());
			courtEvent.setStartTime(courtScheduleInfo.getMovementStartTime());
			courtEvent.setAgyLocId(courtScheduleInfo.getToCourt());
			courtEvent.setHearingType(courtScheduleInfo.getMovementReason());
			courtEvent.setCommentText(courtScheduleInfo.getComment());
			courtEvent.setCaseId(courtScheduleInfo.getCaseId().longValue());
			if(courtScheduleInfo.getScheduleId()!=null) {
				courtEvent.setCommitFlag("u");
				courtEvent.setEventId(courtScheduleInfo.getScheduleId().longValue());
				courtEventList.add(courtEvent);
				courtEventCommitBean.setUpdateList(courtEventList);
			} else {
				courtEvent.setCommitFlag("i");
				courtEventList.add(courtEvent);
				courtEventCommitBean.setInsertList(courtEventList);
			}
			OffenderCourtEventCommitBean returnCourtCaseCommitBean = odcdCaseService.insertNewEventREO(courtEventCommitBean);
			return returnCourtCaseCommitBean.getInsertList().get(0).getEventId();
		}
		return null;
	}
	
	@Override
	public List<KeyDates> populateKeyDates(List<String> personIds) {
		personIds = personIds.stream().map(personId->buildOffednerDisplayId(personId)).collect(Collectors.toList());
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.offenderBookingAvailable(personIds);
		List<KeyDates> keyDates = null;
		if(exactMatchedOffedners !=null && exactMatchedOffedners.size()>0) {
			Map<String, BigDecimal> bookingMap  = exactMatchedOffedners.stream().collect(Collectors.toMap(VHeaderBlock2:: getOffenderIdDisplay, VHeaderBlock2 :: getOffenderBookId));
		    BigDecimal offenderBookId  =  bookingMap.get(buildOffednerDisplayId(personIds.get(0)));
		    keyDates = keyDatesService.populateKeyDatesApi(offenderBookId.longValue());
		}
		return keyDates;
	}
	
	
	@Override
	public CourtCaseResponse createCaseSentence(CaseSentence caseSentence) throws Exception {
		List<String> personIds = new ArrayList<>();
		personIds.add(buildOffednerDisplayId(caseSentence.getPersonId().toString()));
		log.info(personIds);
		List<CourtScheduleResponse> response =  new ArrayList<>();
		CaseSentenceResponse returnCaseOffenseResponse =  new CaseSentenceResponse();
		List<VHeaderBlock2> exactMatchedOffedners = portalAppDao.offenderBookingAvailable(personIds);
		if(exactMatchedOffedners !=null && exactMatchedOffedners.size()>0) {
			Map<String, BigDecimal> bookingMap  = exactMatchedOffedners.stream().collect(Collectors.toMap(VHeaderBlock2:: getOffenderIdDisplay, VHeaderBlock2 :: getOffenderBookId));
		    BigDecimal offenderBookId  =  bookingMap.get(buildOffednerDisplayId(caseSentence.getPersonId().toString()));
		    SentenceCommitBean sentenceCommitBean = new SentenceCommitBean();
		    
		    Sentences sentences = new Sentences();
		    sentences.setCaseId(caseSentence.getCaseId());
		    sentences.setOffenderBookId(offenderBookId.longValue());
		    sentences.setSentenceSeq(caseSentence.getSentenceId());
		    sentences.setCategory(caseSentence.getCategory());
		    sentences.setSentenceType(caseSentence.getSentenceType());
		    sentences.setSentenceCalcType(caseSentence.getSentenceType());
		    sentences.setConsecutiveToLine(caseSentence.getConsecutiveToLine());
		    sentences.setFineAmount(caseSentence.getFineAmount());
		    sentences.setSentenceDate(caseSentence.getSentenceDate());
		    sentences.setStartDate(caseSentence.getStartDate());
		    List<SentenceDate> sentencesDateList =  odcdCaseService.populateSentenceDate(caseSentence.getCaseId());
		    sentences.setOrderId(sentencesDateList!=null?sentencesDateList.get(0).getCode():null);
		    sentences.setStatus("A");
		    
		    if(caseSentence.getOffenseIds()!=null && caseSentence.getOffenseIds().size()>0) {
		    	List<OffensesOutcome> chargedInputOffences =  new ArrayList<>();
		    	OffensesOutcome selectedSentenceData =  new OffensesOutcome();
		    	selectedSentenceData.setCase_id(caseSentence.getCaseId());
		    	selectedSentenceData.setOffenderBookId(offenderBookId.longValue());
		    	selectedSentenceData.setCategory(caseSentence.getCategory());
		    	selectedSentenceData.setSentenceCalcType(caseSentence.getSentenceType());
		    	selectedSentenceData.setSentenceSeq(caseSentence.getSentenceId());
		    	List<OffensesOutcome> offensesOnSentenceList = ocusofncServiceImpl.fetechOffensesdialogData(selectedSentenceData);
		    	if(offensesOnSentenceList!=null & offensesOnSentenceList.size()>0) {
		    		for(BigDecimal offenceId: caseSentence.getOffenseIds()) {
			    		//Verify Offenses are sentenced or not. If any offense is not sentenced Throw error
			    		Optional<OffensesOutcome> outcome = offensesOnSentenceList.stream().filter(offenseOutcome->offenseOutcome.getOffenderChargeId()==offenceId.longValue()).findFirst();
			    		if(outcome.isPresent()) {
			    			OffensesOutcome offenseOutcome = outcome.get();
			    			offenseOutcome.setCommitFlag("i");
			    			offenseOutcome.setOffenderBookId(offenderBookId.longValue());
			    			offenseOutcome.setCase_id(caseSentence.getCaseId());
			    			chargedInputOffences.add(offenseOutcome);
			    		} else {
			    			returnCaseOffenseResponse.setPersonId(caseSentence.getPersonId().toString());
							returnCaseOffenseResponse.setRequestId(new BigDecimal(caseSentence.getRequestId()));
							returnCaseOffenseResponse.setNotProcessingReasonCode("CHARGES_ARE_NOT_SENTENCED");
							returnCaseOffenseResponse.setStatus("FAILED");
							returnCaseOffenseResponse.setNotProcessingReasonDescription("Offender charge "+offenceId+" are either not sentenced or not associated to case.");
							portalAppDao.updateSentenceOutputPayload(returnCaseOffenseResponse);
							throw new Exception();
			    		}
			    	}
		    		sentences.setOffensesOnSentenceList(chargedInputOffences);
		    	} else {
		    		returnCaseOffenseResponse.setPersonId(caseSentence.getPersonId().toString());
					returnCaseOffenseResponse.setRequestId(new BigDecimal(caseSentence.getRequestId()));
					returnCaseOffenseResponse.setNotProcessingReasonCode("CHARGES_ARE_NOT_IN_CASE");
					returnCaseOffenseResponse.setStatus("FAILED");
					returnCaseOffenseResponse.setNotProcessingReasonDescription("Offender charges are either not sentenced or not associated to case.");
					portalAppDao.updateSentenceOutputPayload(returnCaseOffenseResponse);
					throw new Exception();
		    	}
		    	
		    } else if(caseSentence.getSentenceId()==null) {
				returnCaseOffenseResponse.setPersonId(caseSentence.getPersonId().toString());
				returnCaseOffenseResponse.setRequestId(new BigDecimal(caseSentence.getRequestId()));
				returnCaseOffenseResponse.setNotProcessingReasonCode("CHARGES_ARE_MANDATORY");
				returnCaseOffenseResponse.setStatus("FAILED");
				returnCaseOffenseResponse.setNotProcessingReasonDescription("Offender charges does not exist.");
				portalAppDao.updateSentenceOutputPayload(returnCaseOffenseResponse);
			}
		    if(caseSentence.getSentenceTerms()!=null) {
		    	List<Terms> sentenceTerms = new ArrayList<>();
			    Terms newSentenceTerms;
		    	for(Term term : caseSentence.getSentenceTerms()) {
		    		newSentenceTerms = new Terms();
		    		newSentenceTerms.setSentenceSeq(caseSentence.getSentenceId());
		    		newSentenceTerms.setOffenderBookId(offenderBookId.longValue());
		    		newSentenceTerms.setSentenceTermCode(term.getSentenceTermCode().toUpperCase());
		    		newSentenceTerms.setStartDate(caseSentence.getStartDate());
		    		newSentenceTerms.setYears(term.getYears());
		    		newSentenceTerms.setMonths(term.getMonths());
		    		newSentenceTerms.setWeeks(term.getWeeks());
		    		newSentenceTerms.setDays(term.getDays());
		    		newSentenceTerms.setHours(term.getHours());
		    		if(term.getTermId()!=null) {
		    			newSentenceTerms.setCommitFlag("u");
		    			newSentenceTerms.setTermSeq(term.getTermId().intValue());
		    		} else {
		    			newSentenceTerms.setCommitFlag("i");
		    		}
		    		sentenceTerms.add(newSentenceTerms);
		    	}
		    	sentences.setSentenceTermList(sentenceTerms);
		    }
		    SentenceCalculation sentenceCalculation = new SentenceCalculation();
		    sentenceCalculation.setOffenderBookId(offenderBookId.longValue());
		    sentenceCalculation.setCalculationReason(caseSentence.getSentenceCalculateReason());
		    sentenceCalculation.setStaffName("OMS_OWNER");
		    sentences.setSentenceCalculation(sentenceCalculation);
		    List<Sentences> sentencesForInstUpdate = new ArrayList<>();
		    sentencesForInstUpdate.add(sentences);
		    if(caseSentence.getSentenceId()!=null) {
		    	sentences.setCommitFlag("u");
		    	sentenceCommitBean.setUpdateList(sentencesForInstUpdate);
		    } else {
		    	sentences.setCommitFlag("i");
		    	sentenceCommitBean.setInsertList(sentencesForInstUpdate);
		    }
		    
		    SentenceCommitBean  retturnSentenceCommitBean = odcdCaseService.insertOffenderSentenceDetailsRSB(sentenceCommitBean);
			
			returnCaseOffenseResponse.setPersonId(caseSentence.getPersonId().toString());
			returnCaseOffenseResponse.setRequestId(new BigDecimal(caseSentence.getRequestId()));
			returnCaseOffenseResponse.setOffenderBookId(offenderBookId);
			if(caseSentence.getSentenceId()!=null) {
				returnCaseOffenseResponse.setSentenceId(new BigDecimal(caseSentence.getSentenceId()));
				

				//Long sentenceSeq = retturnSentenceCommitBean.getUpdateList().get(0).getSentenceSeq();
				//returnCaseOffenseResponse.setSentenceId(new BigDecimal(sentenceSeq));
				List<Terms> terms = retturnSentenceCommitBean.getUpdateList().get(0).getSentenceTermList();
				List<Term> inputTerms =  new ArrayList<>();
				for(Terms sentenceTerms : terms) {
					Term term =  new Term();
					term.setTermId(sentenceTerms.getTermSeq().longValue());
					term.setSentenceTermCode(sentenceTerms.getSentenceTermCode());
					term.setYears(sentenceTerms.getYears());
					term.setMonths(sentenceTerms.getMonths());
					term.setWeeks(sentenceTerms.getWeeks());
					term.setDays(sentenceTerms.getDays());
					term.setHours(sentenceTerms.getHours());
					inputTerms.add(term);
					
				}
				returnCaseOffenseResponse.setSentenceTerms(inputTerms);
			
				
				
				
			} else {
				Long sentenceSeq = retturnSentenceCommitBean.getInsertList().get(0).getSentenceSeq();
				returnCaseOffenseResponse.setSentenceId(new BigDecimal(sentenceSeq));
				List<Terms> terms = retturnSentenceCommitBean.getInsertList().get(0).getSentenceTermList();
				List<Term> inputTerms =  new ArrayList<>();
				for(Terms sentenceTerms : terms) {
					Term term =  new Term();
					term.setTermId(sentenceTerms.getTermSeq().longValue());
					term.setSentenceTermCode(sentenceTerms.getSentenceTermCode());
					term.setYears(sentenceTerms.getYears());
					term.setMonths(sentenceTerms.getMonths());
					term.setWeeks(sentenceTerms.getWeeks());
					term.setDays(sentenceTerms.getDays());
					term.setHours(sentenceTerms.getHours());
					inputTerms.add(term);
					
				}
				returnCaseOffenseResponse.setSentenceTerms(inputTerms);
			}
			returnCaseOffenseResponse.setStatus("SUCCESS");
			portalAppDao.updateSentenceOutputPayload(returnCaseOffenseResponse);
		} else {
			returnCaseOffenseResponse.setPersonId(caseSentence.getPersonId().toString());
			returnCaseOffenseResponse.setRequestId(new BigDecimal(caseSentence.getRequestId()));
			returnCaseOffenseResponse.setNotProcessingReasonCode("PERSON_DOES_NOT_EXIST");
			returnCaseOffenseResponse.setStatus("FAILED");
			returnCaseOffenseResponse.setNotProcessingReasonDescription("Person Id does not match in system.");
			portalAppDao.updateSentenceOutputPayload(returnCaseOffenseResponse);
		}
		
		return new CourtCaseResponse();
		
	}
	
	@Override
	public OffenderIdentifier createOffenderIdentifier(OffenderIdentifier offenderIdentifier) {
		
		offenderIdentifier.setRootOffenderId(new BigDecimal(portalAppDao.getRootOfenderId(offenderIdentifier.getOffenderId())));
		offenderIdentifier.setIdentifierType("SPI");
		offenderIdentifier.setCaseloadType("INST");
		
		OffenderIdentifiersCommitBean offenderIdentifiersCommitBean = new OffenderIdentifiersCommitBean();
		List<OffenderIdentifier> offenderIdentifiers = new ArrayList<>();
		offenderIdentifiers.add(offenderIdentifier);
		offenderIdentifiersCommitBean.setInsertList(offenderIdentifiers);
		ocdalias.offIdCommit(offenderIdentifiersCommitBean);
		return offenderIdentifier;
	}
	
	
}
