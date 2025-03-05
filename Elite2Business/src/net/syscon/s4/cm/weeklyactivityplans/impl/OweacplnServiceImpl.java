package net.syscon.s4.cm.weeklyactivityplans.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.syscon.s4.cf.offendertransactions.OcdreceiRepository;
import net.syscon.s4.cm.weeklyactivityplans.OcrwapstReportBean;
import net.syscon.s4.cm.weeklyactivityplans.OffenderEmTagDetails;
import net.syscon.s4.cm.weeklyactivityplans.OffenderEmTagDetailsCommitBean;
import net.syscon.s4.cm.weeklyactivityplans.OweacplnRepository;
import net.syscon.s4.cm.weeklyactivityplans.OweacplnService;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlansCommitBean;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlansHty;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlansHtyCommitBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.demographicsbiometrics.OcdaddreRepository;
import net.syscon.s4.inst.legals.OcucalcrRepository;
import net.syscon.s4.report.EliteReportRepository;
import net.syscon.s4.report.OmsReportAsset;
import net.syscon.s4.sa.admin.OumpurgeRepository;

@Service
public class OweacplnServiceImpl implements OweacplnService {

	private static Logger logger = LogManager.getLogger(OweacplnServiceImpl.class.getName());

	@Autowired
	private OweacplnRepository oweacplnRepository;
	@Autowired
	private OcdreceiRepository ocdreceiRepository;

	@Autowired
	private OcdaddreRepository ocdaddreDao;
	

	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	OcucalcrRepository ocucalcrRepository;
	
	@Autowired
	EliteReportRepository eliteReportRepository;
	
	@Autowired
	private OumpurgeRepository oumpurgeRepository;

    private static String BACK_SLASH = "/";
	
	private static String STYLE_TEMPLATES = "StyleTemplates";
	@Override
	public List<WeeklyActivityPlans> getWeeklyActivity(WeeklyActivityPlans searchBean) {
		List<WeeklyActivityPlans> returnList = new ArrayList<WeeklyActivityPlans>();
		try {
			returnList = oweacplnRepository.getWeeklyActivity(searchBean);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " getWeeklyActivity", e);
		}
		return returnList;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OffenderEmTagDetails> saveEmDetails(OffenderEmTagDetailsCommitBean commitBean) {
		final List<OffenderEmTagDetails> liReturnData = new ArrayList<>();
		final OffenderEmTagDetails sentenceCalcTypes = new OffenderEmTagDetails();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderEmTagDetails obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oweacplnRepository.emDetailsInsertData(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderEmTagDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oweacplnRepository.emDetailsUpdateData(commitBean.getUpdateList());
		}

		sentenceCalcTypes.setLiReturn(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceCalcTypes);
		return liReturnData;
	}

	@Override
	public List<OffenderEmTagDetails> retrieveEmDetails(OffenderEmTagDetails searchBean) {
		List<OffenderEmTagDetails> returnList = new ArrayList<OffenderEmTagDetails>();
		try {
			returnList = oweacplnRepository.retrieveEmDetails(searchBean);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " retrieveEmDetails", e);
		}
		return returnList;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<WeeklyActivityPlans> weeklyActivityCommit(WeeklyActivityPlansCommitBean commitBean) {
		final List<WeeklyActivityPlans> liReturnData = new ArrayList<>();
		final WeeklyActivityPlans sentenceCalcTypes = new WeeklyActivityPlans();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (WeeklyActivityPlans obj : commitBean.getInsertList()) {
				if (obj.getEventId() == null
						&& (obj.getRecordSource() == null || !obj.getRecordSource().equalsIgnoreCase("CS"))) {
					obj.setActivity(obj.getActivityNew());
				}
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oweacplnRepository.weeklyActivityCommitInsertData(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (WeeklyActivityPlans obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oweacplnRepository.weeklyActivityCommitUpdateData(commitBean.getUpdateList());
		}

		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (WeeklyActivityPlans obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				List<WeeklyActivityPlans> saveList = new ArrayList<WeeklyActivityPlans>();
				List<WeeklyActivityPlans> deleteList = new ArrayList<WeeklyActivityPlans>();
				if ("Y".equals(obj.getFinalized())) {
					List<WeeklyActivityPlans> returnList = new ArrayList<WeeklyActivityPlans>();
					WeeklyActivityPlans searchBean = new WeeklyActivityPlans();
					searchBean.setOffenderBookId(commitBean.getDeleteList().get(0).getOffenderBookId());
					searchBean.setFromDate(commitBean.getWapStartDate());
					searchBean.setToDate(commitBean.getWapEndDate());
					try {
						returnList = oweacplnRepository.getWeeklyActivity(searchBean);
					} catch (Exception e) {
						logger.error("Exception in " + this.getClass().getName() + " getWeeklyActivity", e);
					}

					if (!returnList.isEmpty()) {
						for (WeeklyActivityPlans weeklyActivityPlans : returnList) {
							if (weeklyActivityPlans.getWeeklyActivityPlanId().compareTo(obj.getWeeklyActivityPlanId()) != 0) {
								saveList.add(obj);
							} else {
								deleteList.add(obj);
							}
						}
						for (WeeklyActivityPlans saveObj : saveList) {
							saveObj.setVersionNo(obj.getHtyVersionNo().add(BigDecimal.ONE));
							saveObj.setCreateUserId(commitBean.getCreateUserId());
							saveObj.setFinalized("N");
							obj.setActivity(obj.getActivityNew());
						}
						if (!saveList.isEmpty()) {
							oweacplnRepository.weeklyActivityCommitUpdateData(saveList);
						}
						
						  if (!deleteList.isEmpty()) {
						 oweacplnRepository.getWeeklyActivityHtyDelete(deleteList);
						 liReturn = oweacplnRepository.weeklyActivityDeleteData(deleteList); }
						

					}
				} else {
					deleteList.add(obj);
					liReturn = oweacplnRepository.weeklyActivityDeleteData(deleteList);
				}
			}

		}
		sentenceCalcTypes.setLiReturn(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceCalcTypes);
		return liReturnData;
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<WeeklyActivityPlansHty> weeklyActivityHtyCommit(WeeklyActivityPlansHtyCommitBean commitBean) {
		final List<WeeklyActivityPlansHty> liReturnData = new ArrayList<>();
		final WeeklyActivityPlansHty sentenceCalcTypes = new WeeklyActivityPlansHty();
		Integer liReturn = 0;
		Integer parentUpdate =0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (WeeklyActivityPlansHty obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oweacplnRepository.weeklyActivityHtyCommitInsertData(commitBean.getInsertList());
		}
		commitBean.getInsertList().forEach(ob->ob.setModifyUserId(commitBean.getCreateUserId()));
		parentUpdate = oweacplnRepository.weeklyActivityCommitUpdateParentData(commitBean.getInsertList());
		
		sentenceCalcTypes.setLiReturn(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceCalcTypes);
		return liReturnData;
	}

	@Override
	public List<WeeklyActivityPlansHty> getWeeklyActivityHty(WeeklyActivityPlansHty searchBean) {
		List<WeeklyActivityPlansHty> returnList = new ArrayList<WeeklyActivityPlansHty>();
		try {
			returnList = oweacplnRepository.getWeeklyActivityHty(searchBean);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " getWeeklyActivity", e);
		}
		return returnList;
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer weeklyActivityCommentCommit(WeeklyActivityPlansHtyCommitBean commitBean) {
		final List<WeeklyActivityPlans> liReturnData = new ArrayList<>();
		final WeeklyActivityPlans sentenceCalcTypes = new WeeklyActivityPlans();
		Integer liReturn = 0;
		if (commitBean.getUpdateList() != null) {
			String comment = oweacplnRepository.getWapComment(commitBean.getUpdateList().get(0));
			if (comment == null || "notfound".equals(comment)) {
				if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
					for (WeeklyActivityPlansHty obj : commitBean.getUpdateList()) {
						obj.setCreateUserId(commitBean.getCreateUserId());
					}
					liReturn = oweacplnRepository.weeklyActivityCommentInsertList(commitBean.getUpdateList());
				}
			} else {
				if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
					for (WeeklyActivityPlansHty obj : commitBean.getUpdateList()) {
						obj.setModifyUserId(commitBean.getCreateUserId());
					}
					liReturn = oweacplnRepository.weeklyActivityCommentUpdateList(commitBean.getUpdateList());
				}
			}
		}

		return liReturn;
	}

	
	@Override
	public List<OcrwapstReportBean> printReportForStaff(OcrwapstReportBean returnReport) {
		byte[] pdfReport = null;
		final List<OcrwapstReportBean> returnData = new ArrayList<>();
		final OcrwapstReportBean report = new OcrwapstReportBean();
		List<OcrwapstReportBean> runreport = new ArrayList<OcrwapstReportBean>();
		OcrwapstReportBean obj = new OcrwapstReportBean();

		final Map<String, Object> param = new HashMap<>();
		List<OcrwapstReportBean> fields = new ArrayList<OcrwapstReportBean>();

		obj.setCreateUserId(returnReport.getCreateUserId());

		List<StaffMembers> staffMemebers = oweacplnRepository.userDetails(obj.getCreateUserId());
		if(staffMemebers != null && staffMemebers.size() > 0) {
			obj.setUserName(staffMemebers.get(0).getFirstName() + ", " + staffMemebers.get(0).getLastName());
		} else {
			obj.setUserName(returnReport.getCreateUserId());
		}
		
		VHeaderBlock clientDetails = oweacplnRepository.getClientDetails(returnReport.getOffenderBookId(),
				returnReport.getCreateUserId());
		obj.setOffenderName(clientDetails.getLastName());
		obj.setOffenderIdDisplay(clientDetails.getOffenderIdDisplay());
		obj.setEmTagId(returnReport.getEmTagId());
		obj.setEmTagData(returnReport.getEmTagData());
		obj.setEmStrapSize(returnReport.getEmStrapSize());
		obj.setWapVersion(returnReport.getWapVersion());
		
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
		if(returnReport.getEmTagStartTime() != null && returnReport.getEmTagEndTime() != null) {
			String cStartTime = null;
			String cEndTime =null;
			if(returnReport.getEmTagStartTime() != null)
				cStartTime = localDateFormat.format(returnReport.getEmTagStartTime());
			if(returnReport.getEmTagEndTime() != null)
				cEndTime = localDateFormat.format(returnReport.getEmTagEndTime());
			obj.setEmDailyChargingPeriod(cStartTime + " - " + cEndTime);
		}
		
		VAddresses vAddresses = new VAddresses();
		String finalAddress=null;
		vAddresses.setOwnerId(clientDetails.getRootOffenderId());
		List<VAddresses> addressClientList = ocdaddreDao.vAddSearchVAddresses(vAddresses);
		logger.info("vAddSearchVAddresses reponse" + addressClientList);
		List<Phones> phoneList = new ArrayList<Phones>();
		if (!addressClientList.isEmpty()) {
			for (VAddresses vAddresses2 : addressClientList) {
				if ("Y".equals(vAddresses2.getPrimaryFlag())) {
					Phones phonesObj = new Phones();
					StringBuilder addressClient = new StringBuilder();
					
					
					if (vAddresses2.getSuiteNumber() != null) {
						addressClient = addressClient.append(vAddresses2.getSuiteNumber().concat(","));
					}

					if (vAddresses2.getStreetAddress() != null) {
						addressClient =addressClient.append(" ").append(vAddresses2.getStreetAddress().trim().concat(","));
					}

					/*if (vAddresses2.getStreet() != null) {
						addressClient = addressClient.append(vAddresses2.getStreet().concat(","));
					}

					if (vAddresses2.getStreetDirectionDesc() != null) {
						addressClient = addressClient.append(vAddresses2.getStreetDirectionDesc().concat(","));
					}
*/
					if (vAddresses2.getCityName() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getCityName().concat(","));
					}
					if (vAddresses2.getProvStateDesc() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getProvStateDesc().concat(","));
					}
					if (vAddresses2.getCountryDesc() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getCountryDesc().concat(","));
					}
					if (vAddresses2.getZipPostalCode() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getZipPostalCode().concat(","));
					}
					if (vAddresses2.getAddressId() != null) {
						phonesObj.setOwnerId(vAddresses2.getOwnerId());
						phoneList.add(phonesObj);
					}

					if (addressClient != null) {
							finalAddress = addressClient.toString().trim();

							if (finalAddress.endsWith(",")) {
								finalAddress = finalAddress.trim().substring(0, finalAddress.length() - 1);
							}
							if (finalAddress != null)
								{							
								obj.setAddress(finalAddress);
								}						
					}
				}
			}
		}
		List<String> phoneNoFinalString = new ArrayList<String>();
		for (Phones phones : phoneList) {
			Phones phonesGetObj = new Phones();
			phonesGetObj.setOwnerId(phones.getOwnerId());
			phonesGetObj.setOwnerClass("OFF");
			List<Phones> phoneNoList = ocdaddreDao.phoneAddrSearchPhones(phonesGetObj);
			for (Phones phoneNo : phoneNoList) {
				phoneNoFinalString.add(phoneNo.getPhoneNo());
			}
		}

		if (!phoneNoFinalString.isEmpty()) {
			String finalPhoneNo = null;
			for (String phonNo : phoneNoFinalString) {
				StringBuilder phoneClient = new StringBuilder();
				if (phonNo != null) {
					phoneClient = phoneClient.append(phonNo.concat(","));
				}
				if (phoneClient != null) {
					finalPhoneNo = phoneClient.toString().trim();

					if (finalPhoneNo.endsWith(",")) {
						finalPhoneNo = finalPhoneNo.trim().substring(0, finalPhoneNo.length() - 1);
					}
					if (finalPhoneNo != null)
						obj.setPhone(finalPhoneNo);
				}
			}

		}
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy");
		String dateOne = formatter.format(returnReport.getWapStartDate());
		String dateTwo = formatter.format(returnReport.getWapEndDate());
		obj.setWeek(dateOne.concat(" - ").concat(dateTwo));
		
		for (WeeklyActivityPlansHty schdeuleActivity : returnReport.getScheduledActivitiesList()) {
			if (schdeuleActivity.getActivityStart() != null) {
				String timeStart = localDateFormat.format(schdeuleActivity.getActivityStart());
				schdeuleActivity.setActivityStartTimeString(timeStart);
			}
			if (schdeuleActivity.getActivityFinish() != null) {
				String timeFinish = localDateFormat.format(schdeuleActivity.getActivityFinish());
				schdeuleActivity.setActivityFinishTimeString(timeFinish);
			}
			if (schdeuleActivity.getDepartStartTime() != null) {
				String timeDepart = localDateFormat.format(schdeuleActivity.getDepartStartTime());
				schdeuleActivity.setDepartStartTimeString(timeDepart);
			}
			if (schdeuleActivity.getReturnTime() != null) {
				String timeReturn = localDateFormat.format(schdeuleActivity.getReturnTime());
				schdeuleActivity.setReturnTimeString(timeReturn);
			}
			
			if("false".equals(schdeuleActivity.getSystemGenerated())) {
				schdeuleActivity.setActivity(oweacplnRepository.getActivityDescriptionValue(schdeuleActivity.getActivityNew()));
			}
		}
		for (WeeklyActivityPlansHty object : returnReport.getScheduledActivitiesList()) {
			if (object.getModeOfTransport() != null) {
				object.setModeOfTransportDesc(oweacplnRepository.getModeOfTransportDesc(object.getModeOfTransport()));
			}
		}
		obj.setScheduledActivitiesList(returnReport.getScheduledActivitiesList());
		obj.setComment(returnReport.getComment());

		final String fCaseloadDesc = ocdreceiRepository.getfcaseloadDesc(returnReport.getCaseloadId());
		logger.info("getfcaseloadDesc reponse" + fCaseloadDesc);
		if (fCaseloadDesc != null) {
			obj.setCaseloadDesc(fCaseloadDesc);
		}
		
		List<SystemProfiles> systemProfileConfData = new ArrayList<SystemProfiles>();
		SystemProfiles searchRecord = new SystemProfiles();
		searchRecord.setProfileType("CLIENT");
		searchRecord.setProfileCode("WAP_FOO_LBL");
		systemProfileConfData = oumpurgeRepository.sysPflSearchSystemProfiles(searchRecord);
		
		if(!systemProfileConfData.isEmpty() && systemProfileConfData.get(0).getProfileValue()!=null) {		
			obj.setConfSytPrfValue(systemProfileConfData.get(0).getProfileValue());
		}
		obj.setPidLabel(returnReport.getPidLabel());
		obj.setEmtagLabel(returnReport.getEmtagLabel());
		obj.setEmtagStrapSizeLabel(returnReport.getEmtagStrapSizeLabel());
		obj.setCommentLabel(returnReport.getCommentLabel());
		obj.setTitleLabel(returnReport.getTitleLabel());
		obj.setReportCreatedLabel(returnReport.getReportCreatedLabel());
		obj.setCreatedLabel(returnReport.getCreatedLabel());
		obj.setOffenderAddressLabel(returnReport.getOffenderAddressLabel());
		obj.setOffenderPhoneLabel(returnReport.getOffenderPhoneLabel());
		obj.setOffenderNameLabel(returnReport.getOffenderNameLabel());
		obj.setWeekLabel(returnReport.getWeekLabel());
		obj.setWapVersionLabel(returnReport.getWapVersionLabel());
		obj.setEmDailyChargingLabel(returnReport.getEmDailyChargingLabel());
		fields.add(obj);
		String basePath = this.getClass().getResource(BACK_SLASH + STYLE_TEMPLATES).getPath();
		param.put("templateLocation", basePath); 
		pdfReport = generateReport("OCRWAPST", param, fields);
		report.setReport(pdfReport);
		report.setSealFlag("1");
		returnData.clear();
		returnData.add(report);
		runreport.add(report);
		return runreport;
	}

	public byte[] generateReport(final String reportName, final Map<String, Object> parameteres, final List<?> fields) {
		byte[] returnReport = null;
		JasperPrint jasperPrint = null;
		try {
			final InputStream reportInStream = this.getClass().getClassLoader().getResourceAsStream("resource/jasperreports/" + reportName + ".jrxml");
			ArrayList<String> paramNames = new ArrayList<String>();
			paramNames.add("logo");
			  List<OmsReportAsset> omsAssetsByCodes = eliteReportRepository.getOmsAssetsByCodes(paramNames);
			 if(!omsAssetsByCodes.isEmpty()) {			 
				 for (OmsReportAsset asset : omsAssetsByCodes) {
					 InputStream targetStream = new ByteArrayInputStream(asset.getAssetBody());
					 parameteres.put(asset.getAssetCode(), targetStream); 
				 }
			 }
			 
			final JasperReport jasperReport = JasperCompileManager.compileReport(reportInStream);
			if ((fields != null && !fields.isEmpty())) {
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres,
						new JRBeanCollectionDataSource(fields));
			} else {
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres, new JREmptyDataSource());
			}
			returnReport = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (final Exception e) {

			logger.error("Error in Class " + this.getClass().getName() + " generateReport error :: ", e);
		}
		return returnReport;
	}

	@Override
	public String getWapComment(WeeklyActivityPlansHty bean) {
		return oweacplnRepository.getWapComment(bean);
	}

	@Override
	public List<OcrwapstReportBean> printReportForOffender(OcrwapstReportBean returnReport) {
		byte[] pdfReport = null;
		final List<OcrwapstReportBean> returnData = new ArrayList<>();
		final OcrwapstReportBean report = new OcrwapstReportBean();
		List<OcrwapstReportBean> runreport = new ArrayList<OcrwapstReportBean>();
		OcrwapstReportBean obj = new OcrwapstReportBean();

		final Map<String, Object> param = new HashMap<>();
		List<OcrwapstReportBean> fields = new ArrayList<OcrwapstReportBean>();

		obj.setCreateUserId(returnReport.getCreateUserId());
		
		List<StaffMembers> staffMemebers = oweacplnRepository.userDetails(obj.getCreateUserId());
		if(staffMemebers != null && staffMemebers.size() > 0) {
			obj.setUserName(staffMemebers.get(0).getLastName() + ", " + staffMemebers.get(0).getFirstName());
		} else {
			obj.setUserName(returnReport.getCreateUserId());
		}
		
		VHeaderBlock clientDetails = oweacplnRepository.getClientDetails(returnReport.getOffenderBookId(),
				returnReport.getCreateUserId());
		obj.setOffenderName(clientDetails.getLastName());
		obj.setOffenderIdDisplay(clientDetails.getOffenderIdDisplay());
		obj.setEmTagId(returnReport.getEmTagId());
		obj.setEmStrapSize(returnReport.getEmStrapSize());
		obj.setWapVersion(returnReport.getWapVersion());
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
		String cStartTime = null;
		String cEndTime =null;
		if(returnReport.getEmTagStartTime() != null)
			cStartTime = localDateFormat.format(returnReport.getEmTagStartTime());
		if(returnReport.getEmTagEndTime() != null)
			cEndTime = localDateFormat.format(returnReport.getEmTagEndTime());
		obj.setEmDailyChargingPeriod(cStartTime + " - " + cEndTime);
		
		VAddresses vAddresses = new VAddresses();
		String finalAddress=null;
		vAddresses.setOwnerId(clientDetails.getRootOffenderId());
		List<VAddresses> addressClientList = ocdaddreDao.vAddSearchVAddresses(vAddresses);
		logger.info("vAddSearchVAddresses reponse" + addressClientList);
		List<Phones> phoneList = new ArrayList<Phones>();
		if (!addressClientList.isEmpty()) {
			for (VAddresses vAddresses2 : addressClientList) {
				if ("Y".equals(vAddresses2.getPrimaryFlag())) {
					Phones phonesObj = new Phones();
					StringBuilder addressClient = new StringBuilder();
					if (vAddresses2.getSuiteNumber() != null) {
						addressClient = addressClient.append(vAddresses2.getSuiteNumber().concat(","));
					}

					if (vAddresses2.getStreetAddress() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getStreetAddress().trim().concat(","));
					}

					/*if (vAddresses2.getStreet() != null) {
						addressClient = addressClient.append(vAddresses2.getStreet().concat(","));
					}

					if (vAddresses2.getStreetDirectionDesc() != null) {
						addressClient = addressClient.append(vAddresses2.getStreetDirectionDesc().concat(","));
					}*/

					if (vAddresses2.getCityName() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getCityName().concat(","));
					}
					if (vAddresses2.getProvStateDesc() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getProvStateDesc().concat(","));
					}
					if (vAddresses2.getCountryDesc() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getCountryDesc().concat(","));
					}
					if (vAddresses2.getZipPostalCode() != null) {
						addressClient = addressClient.append(" ").append(vAddresses2.getZipPostalCode().concat(","));
					}
					if (vAddresses2.getAddressId() != null) {
						phonesObj.setOwnerId(vAddresses2.getOwnerId());
						phoneList.add(phonesObj);
					}
					if (addressClient != null) {
						finalAddress = addressClient.toString().trim();

						if (finalAddress.endsWith(",")) {
							finalAddress = finalAddress.trim().substring(0, finalAddress.length() - 1);
						}
						if (finalAddress != null)
							{							
							obj.setAddress(finalAddress);
							}						
				}
				}
			}
		}

		List<String> phoneNoFinalString = new ArrayList<String>();
		for (Phones phones : phoneList) {
			Phones phonesGetObj = new Phones();
			phonesGetObj.setOwnerId(phones.getOwnerId());
			phonesGetObj.setOwnerClass("OFF");
			List<Phones> phoneNoList = ocdaddreDao.phoneAddrSearchPhones(phonesGetObj);
			for (Phones phoneNo : phoneNoList) {
				phoneNoFinalString.add(phoneNo.getPhoneNo());
			}
		}

		if (!phoneNoFinalString.isEmpty()) {
			String finalPhoneNo = null;
			for (String phonNo : phoneNoFinalString) {
				StringBuilder phoneClient = new StringBuilder();
				if (phonNo != null) {
					phoneClient = phoneClient.append(phonNo.concat(","));
				}
				if (phoneClient != null) {
					finalPhoneNo = phoneClient.toString().trim();

					if (finalPhoneNo.endsWith(",")) {
						finalPhoneNo = finalPhoneNo.trim().substring(0, finalPhoneNo.length() - 1);
					}
					if (finalPhoneNo != null)
						obj.setPhone(finalPhoneNo);
				}
			}
		}
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy");
		String dateOne = formatter.format(returnReport.getWapStartDate());
		String dateTwo = formatter.format(returnReport.getWapEndDate());
		obj.setWeek(dateOne.concat(" - ").concat(dateTwo));
		int count = 1;
		  try {
		for (WeeklyActivityPlansHty schdeuleActivity : returnReport.getScheduledActivitiesList()) {
			schdeuleActivity.setSerialNumber(count);
			count++;
			SimpleDateFormat twentyFourHourFormat = new SimpleDateFormat("HH:mm");
			SimpleDateFormat twelveHourFormat = new SimpleDateFormat("hh:mm a");
			if (schdeuleActivity.getActivityStart() != null) {			 
				 schdeuleActivity.setActivityStartTimeString(twelveHourFormat.format(twentyFourHourFormat.parse(localDateFormat.format(schdeuleActivity.getActivityStart()))));
			}
			if (schdeuleActivity.getActivityFinish() != null) {
				schdeuleActivity.setActivityFinishTimeString(twelveHourFormat.format(twentyFourHourFormat.parse(localDateFormat.format(schdeuleActivity.getActivityFinish()))));
			}
			if (schdeuleActivity.getDepartStartTime() != null) {
				schdeuleActivity.setDepartStartTimeString(twelveHourFormat.format(twentyFourHourFormat.parse(localDateFormat.format(schdeuleActivity.getDepartStartTime()))));
			}
			if (schdeuleActivity.getReturnTime() != null) {
				schdeuleActivity.setReturnTimeString(twelveHourFormat.format(twentyFourHourFormat.parse(localDateFormat.format(schdeuleActivity.getReturnTime()))));
			}
			if("false".equals(schdeuleActivity.getSystemGenerated())) {
				schdeuleActivity.setActivity(oweacplnRepository.getActivityDescriptionValue(schdeuleActivity.getActivityNew()));
			}
		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (WeeklyActivityPlansHty object : returnReport.getScheduledActivitiesList()) {
			if (object.getModeOfTransport() != null) {
				object.setModeOfTransportDesc(oweacplnRepository.getModeOfTransportDesc(object.getModeOfTransport()));
			}
		}
		obj.setScheduledActivitiesList(returnReport.getScheduledActivitiesList());

		final String fCaseloadDesc = ocdreceiRepository.getfcaseloadDesc(returnReport.getCaseloadId());
		logger.info("getfcaseloadDesc reponse" + fCaseloadDesc);
		if (fCaseloadDesc != null) {
			obj.setCaseloadDesc(fCaseloadDesc);
		}
		obj.setPidLabel(returnReport.getPidLabel());
		obj.setTitleLabel(returnReport.getTitleLabel());
		obj.setReportCreatedLabel(returnReport.getReportCreatedLabel());
		obj.setOffenderNameLabel(returnReport.getOffenderNameLabel());
		obj.setWeekLabel(returnReport.getWeekLabel());
		obj.setWapVersionLabel(returnReport.getWapVersionLabel());
		obj.setEmDailyChargingLabel(returnReport.getEmDailyChargingLabel());
		obj.setScheduledActivitiesLabel(returnReport.getScheduledActivitiesLabel());
		fields.add(obj);
		String basePath = this.getClass().getResource(BACK_SLASH + STYLE_TEMPLATES).getPath();
		param.put("templateLocation", basePath); 
		pdfReport = generateReport("OCRWAPOF", param, fields);
		report.setReport(pdfReport);
		report.setSealFlag("1");
		returnData.clear();
		returnData.add(report);
		runreport.add(report);
		return runreport;
	}

	@Override
	public List<WeeklyActivityPlans> getWeeklyActivityHtyMaxData(WeeklyActivityPlansHty searchBean) {

		return oweacplnRepository.getWeeklyActivityHtyMaxData(searchBean);
	}

	@Override
	public BigDecimal getMaxHtyVersion(WeeklyActivityPlans searchBean) {
		return oweacplnRepository.getMaxHtyVersion(searchBean);
	}
	
	@Override
	public Integer copyOverPreviousWeekData(WeeklyActivityPlans searchBean) {
		Integer returnVal = 0;
		try {
			Date startDate = searchBean.getFromDate();
			LocalDateTime localDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			LocalDateTime currentWeekStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			localDate = localDate.minusDays(7);
			Date previousWeekStartDate = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
			List<WeeklyActivityPlans> prvsWeekData = oweacplnRepository.getPreviousWeekData(previousWeekStartDate,
					startDate, searchBean.getOffenderBookId());
			if (prvsWeekData != null && !prvsWeekData.isEmpty()) {
				for (WeeklyActivityPlans obj : prvsWeekData) {
					LocalDateTime temp = null;
					int dayNum = obj.getActivityDate().getDay();
					temp = currentWeekStartDate
							.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(getDayName(dayNum))));
					obj.setActivityDate(Date.from(temp.atZone(ZoneId.systemDefault()).toInstant()));
					obj.setWapStartDate(startDate);
					obj.setWapEndDate(searchBean.getToDate());
					obj.setWeeklyActivityPlanId(null);
					obj.setFinalized("N");
					obj.setCreateUserId(searchBean.getCreateUserId());
					obj.setModifyUserId(null);
				}
				returnVal = oweacplnRepository.copyOverCommit(prvsWeekData);
			}else {
				returnVal = 2;
			}

		} catch (Exception e) {
			logger.error("Exception : copyOverPreviousWeekData:", e);
		}
		return returnVal;
	}
	
	String getDayName(int dayNum){
		String dayName = null;
		switch (dayNum) {
		case 0:
			dayName = "SUNDAY" ;
			break;
		case 1:
			dayName = "MONDAY";
			break;
		case 2:
			dayName = "TUESDAY";
			break;
		case 3:
			dayName = "WEDNESDAY";
			break;
		case 4:
			dayName = "THURSDAY";
			break;
		case 5:
			dayName = "FRIDAY";
			break;
		case 6:
			dayName = "SATURDAY";
			break;
	}
		return dayName;
	}

	@Override
	public String populateLoggedStaffName(String userName) {
		final Integer staffId=generateStaffId(userName);
		final String staffName = ocucalcrRepository.populateStaffName(staffId);
		return staffName;
	}
	
	private Integer generateStaffId(String username) {
		return ocucalcrRepository.generateStaffId(username);
	}
	
	@Override
	public BigDecimal getMaxHtyVersionData(WeeklyActivityPlans searchBean) {
		// TODO Auto-generated method stub
		BigDecimal maxVersionData=BigDecimal.ZERO;
		maxVersionData = oweacplnRepository.getMaxHtyVersionData(searchBean);
		if(maxVersionData.compareTo(BigDecimal.ZERO) == 0) {
			maxVersionData = oweacplnRepository.getHistoryTableMaxVersion(searchBean);
		}
		return maxVersionData;
	}
}
