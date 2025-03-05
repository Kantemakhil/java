package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.externalmessage.ExternalMessageRepository;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.OcdcgpayRepository;
import net.syscon.s4.inst.institutionalactivities.OcdcgpayService;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.OimallowRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;

@Service
public class OcdcgpayServiceImpl extends BaseBusiness implements OcdcgpayService {
	public static final String DATE_TIMESTAMP = "E MMM dd HH:mm:ss Z yyyy";
	public static final String DATE_FORMAT_TO = "yyyy-MM-dd";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	@Autowired
	private OcdcgpayRepository ocdcgpayRepository;

	@Autowired
	private OcupdetaServiceImpl ocupdetaService;
	
	@Autowired
	private ExternalMessageRepository externalMessageRepository;
	
	@Autowired
	private OimallowRepository oimallowRepository;
	
	private static Logger logger = LogManager.getLogger(OcdcgpayServiceImpl.class.getName());

	@Override
	public List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(VOffenderCourseAttendances obj) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		Long crsactId;
		List<VOffenderCourseAttendances> returnlistNew = new ArrayList<VOffenderCourseAttendances>();
		BigDecimal totalAmountAllowance = BigDecimal.ZERO;
		List<VOffenderCourseAttendances> returnlist = null;
		returnlist = ocdcgpayRepository.unpaidAttendanceExecuteQuery(obj);
		if (returnlist.size() > 0) {
			for (VOffenderCourseAttendances bean : returnlist) {
				if ("ACP".equals(bean.getEventType())) {
					crsactId = bean.getParentCrsActyId();
				} else {
					crsactId = bean.getCrsActyId();
				}
				BigDecimal sysPayRate = ocdcgpayRepository.getSystemPayRate(bean.getEventType(), bean.getProgramId(),
						crsactId, bean.getOffPrgrefId());

				bean.setPaySystemRate(sysPayRate);
				if (bean.getPayActualAmount() != null) {
					totalAmount = totalAmount.add(bean.getPayActualAmount());
				}
			}
			returnlist.get(0).setTotalAmount(totalAmount);
		}

		
		List<OffAllowPayDetails> allowUnpaidList=new ArrayList<OffAllowPayDetails>();
		allowUnpaidList = ocdcgpayRepository.getUnpaidAllowanceDetailsQry(obj);
		
		for (OffAllowPayDetails offAllowPayDetails : allowUnpaidList) {
			VOffenderCourseAttendances newObj=new VOffenderCourseAttendances();
			newObj.setOffenderBookId(offAllowPayDetails.getOffenderBookId().longValue());
			newObj.setOffenderIdDisplay(offAllowPayDetails.getOffenderIdDisplay());
			newObj.setLastName(offAllowPayDetails.getLastName());
			newObj.setFirstName(offAllowPayDetails.getFirstName());
			newObj.setPaySystemRate(BigDecimal.valueOf(offAllowPayDetails.getPreAllowanceVersionRate()));
			newObj.setTotalAmount(BigDecimal.valueOf(offAllowPayDetails.getAllowanceVersionRate()));
			newObj.setPayActualAmount(newObj.getTotalAmount());
			newObj.setActivityDescription(offAllowPayDetails.getAllowanceType());
			newObj.setOffAllowanceId(offAllowPayDetails.getOffAllowanceId());
			if (offAllowPayDetails.getAllowanceVersionRate() != null) {
				totalAmountAllowance = totalAmountAllowance.add(BigDecimal.valueOf(offAllowPayDetails.getAllowanceVersionRate()));
			}
			returnlistNew.add(newObj);
		}
		if(!returnlist.isEmpty() && !returnlistNew.isEmpty()) {
			returnlist.addAll(returnlistNew);
			if(returnlist.get(0)!=null && returnlist.get(0).getTotalAmount()!=null && totalAmountAllowance!=null) {
				returnlist.get(0).setTotalAmount(returnlist.get(0).getTotalAmount().add(totalAmountAllowance));
			} 
		} else if(!returnlistNew.isEmpty()) {
			returnlist.addAll(returnlistNew);
			returnlist.get(0).setTotalAmount(totalAmountAllowance);
		}
		return returnlist;
	}

	@Transactional
	public JSONObject generatePay(List<VOffenderCourseAttendances> updateList) {
		VOffenderCourseAttendances beanObj = new VOffenderCourseAttendances();
		JSONObject payRunData = null;
		beanObj = updateList.get(0);
		JSONObject returnData = new JSONObject();
		Integer liReturn = 0;
		try {
		List<VOffenderCourseAttendances> returnList = unpaidAttendanceExecuteQuery(beanObj);
		List<VOffenderCourseAttendances> offAllowList = new ArrayList<VOffenderCourseAttendances>();
		List<VOffenderCourseAttendances> courList = new ArrayList<VOffenderCourseAttendances>();
		Integer batchId = ocdcgpayRepository.getBatchId();
		if (returnList.size() > 0) {
		for (VOffenderCourseAttendances listObj : returnList) {
			if(listObj.getOffAllowanceId()!= null) {
				listObj.setPayBatchId(batchId);
				listObj.setCreateUserId(beanObj.getCreateUserId());
				listObj.setModifyUserId(beanObj.getCreateUserId());
				listObj.setFromDate(beanObj.getStartTime());
				listObj.setToDate(beanObj.getEndTime());
				beanObj.setModifyUserId(beanObj.getCreateUserId());
				beanObj.setOffAllowanceId(listObj.getOffAllowanceId());
				listObj.setAllowancePayAmount(listObj.getTotalAmount().intValue());
				offAllowList.add(listObj);
			}else {
				listObj.setPayBatchId(batchId);
				listObj.setCreateUserId(beanObj.getCreateUserId());
				listObj.setModifyUserId(beanObj.getCreateUserId());
				listObj.setFromDate(beanObj.getStartTime());
				listObj.setToDate(beanObj.getEndTime());
				beanObj.setModifyUserId(beanObj.getCreateUserId());
				courList.add(listObj);
			}
		}
		if(courList != null && courList.size() > 0) {
			liReturn = ocdcgpayRepository.insertPrgPayBatches(courList.get(0));
			liReturn = ocdcgpayRepository.insertPrgPayBatchOffCrs(courList);
			if (liReturn == 1) {
				liReturn = postUpdate(beanObj, batchId);
			      payRunData=preparePayRunData(returnList,beanObj);
			}
			if(payRunData!=null) {
				returnData.put("payRunDetails",payRunData);
			}
			if (liReturn > 1) {
				returnData.put("liReturn", batchId);
				return returnData;
			} else {
				returnData.put("liReturn", 0);
				return returnData;	
			}
		}
		if(offAllowList != null && offAllowList.size() > 0) {
			liReturn = ocdcgpayRepository.insertPrgPayBatches(offAllowList.get(0));
			liReturn = ocdcgpayRepository.insertPayBatchOffAllow(offAllowList);
			if (liReturn == 1) {
				liReturn = postUpdate(beanObj, batchId);
			      payRunData=preparePayRunData(returnList,beanObj);
			}
			if(payRunData!=null) {
				returnData.put("payRunDetails",payRunData);
			}
			if (liReturn > 1) {
				returnData.put("liReturn", batchId);
				return returnData;
			} else {
				returnData.put("liReturn", 0);
				return returnData;	
			}
		  }
		}
		} catch (Exception e) {
			logger.error("Exception in generatePay :"+e.getMessage());
		}
		returnData.put("liReturn", 0);
		return returnData;
	}
	
	private JSONObject preparePayRunData(List<VOffenderCourseAttendances> returnList,VOffenderCourseAttendances beanObj) throws ParseException {
		JSONObject payRunData = new JSONObject();
		String payRunNarration="";
		String startDate=null;
		String endDate=null;
		List<VOffenderCourseAttendances> offenderList=new ArrayList<>();
		payRunData.put("payRunId", returnList.get(0).getPayBatchId());
		if(beanObj.getStartTime()!=null) {
	    	 startDate=formatDate(beanObj.getStartTime(),DATE_FORMAT_TO);
	    	 payRunData.put("startdate",startDate);
	     }
		if(beanObj.getEndTime()!=null) {
	    	 endDate=formatDate(beanObj.getEndTime(),DATE_FORMAT_TO);
	    	 payRunData.put("endDate",endDate);
	     }
		if(beanObj.getStartTime()!=null) {
			payRunNarration="Prisoner pay: "+formatDate(beanObj.getStartTime(),DATE_FORMAT)+" - "+formatDate(beanObj.getEndTime(),DATE_FORMAT)+"  "+"Batch ID: "+ returnList.get(0).getPayBatchId();
		}else {
			payRunNarration="Prisoner pay: all unpaid records until "+formatDate(beanObj.getEndTime(),DATE_FORMAT)+"  "+"Batch ID: "+ returnList.get(0).getPayBatchId();
		}
		payRunData.put("payRunNarration",payRunNarration);
		
		for(VOffenderCourseAttendances obj:returnList) {
			BigDecimal sumAmount = BigDecimal.ZERO;
			VOffenderCourseAttendances offenderData=new VOffenderCourseAttendances();
			for(VOffenderCourseAttendances child:returnList) {
				if(child.getOffenderIdDisplay().equals(obj.getOffenderIdDisplay())) {
					sumAmount=sumAmount.add(child.getPayActualAmount());
				}
			}
			offenderData.setOffenderIdDisplay(obj.getOffenderIdDisplay());
			offenderData.setPayActualAmount(sumAmount);
			if(offenderList!=null && !offenderList.isEmpty()) {
		     VOffenderCourseAttendances payRunObj= offenderList.stream().filter(payDet -> payDet.getOffenderIdDisplay().equals(obj.getOffenderIdDisplay())).findAny().orElse(null);	
		     if(payRunObj==null) {
				offenderList.add(offenderData);
		     }
			}else {
				offenderList.add(offenderData);
			}
		}
		payRunData.put("payDetailList", offenderList);
		
		return payRunData;
	}
	
	
	private String formatDate(Date dateTime,String format) throws ParseException {
		 DateFormat formatter = new SimpleDateFormat(DATE_TIMESTAMP);
         DateFormat formatTo = new SimpleDateFormat(format);
         String formatedDate="";
         if(dateTime!=null) {
        	 Date date = (Date)formatter.parse(dateTime.toString());
        	 formatedDate=formatTo.format(date);
         }
    	 return formatedDate;
	}
	@Transactional
	private Integer postUpdate(VOffenderCourseAttendances updObject, Integer batchId) {
		List<VOffenderCourseAttendances> returnlist = ocupdetaService.postUpdateExecuteQuery(updObject);
		List<VOffenderCourseAttendances> offAllowList = new ArrayList<VOffenderCourseAttendances>();
		List<VOffenderCourseAttendances> courList = new ArrayList<VOffenderCourseAttendances>();
		Integer result = null;
		if (returnlist.size() > 0) {
			for (VOffenderCourseAttendances bean : returnlist) {
				if(bean.getType() != null) {
					bean.setModifyUserId(updObject.getModifyUserId());
					bean.setPayBatchId(batchId);
					offAllowList.add(bean);
				}else {
					bean.setPayBatchId(batchId);
					bean.setModifyUserId(updObject.getModifyUserId());
					courList.add(bean);
				}
			}
			if(!offAllowList.isEmpty()) {
				result = ocdcgpayRepository.generateAllowPay(offAllowList);
			}
			if(!courList.isEmpty()) {
				result = ocdcgpayRepository.generatePay(courList);
			}
		}
		return result;
	}

	@Override
	public List<OffenderCourseAttendance> getFromToDates() {
		return ocdcgpayRepository.getFromToDates();
	}
	
	@Override
	public Integer saveOffAllowPayDetValues(OffAllowPayDetailsCommitBean commitBean) {
		Integer result = 0;
		List<OffenderAllowances> offAllowancesList = new ArrayList<OffenderAllowances>();
		List<Allowances> allowancesList = new ArrayList<Allowances>();
		List<OffAllowPayDetails> offAllowPayDetailsList = new ArrayList<OffAllowPayDetails>();
		offAllowancesList = oimallowRepository.getOffenderAllowences();
		allowancesList = oimallowRepository.getAllowances();
		Date date = new Date();
		OffAllowPayDetails offAllowPayDetValues = new OffAllowPayDetails();
		if((offAllowancesList != null && offAllowancesList.size() > 0) &&
				(allowancesList != null && allowancesList.size() > 0)) {
		for (OffenderAllowances offAllowlist : offAllowancesList) {
			Integer checkOffAllowPay = oimallowRepository.checkOffAllowPay(offAllowlist.getOffenderBookId(), offAllowlist.getOffAllowanceId());
			if(checkOffAllowPay == 0){
			for (Allowances allowanceList : allowancesList) {
				  long versionStartDateTime = allowanceList.getVersionStartDate().getTime();
				  long offAllowStartDateTime = offAllowlist.getStartDate().getTime();
				  allowanceList.setCreateUserId(commitBean.getCreateUserId());
				
				  LocalDate allowanceExpDate = null;
				  LocalDate OffAllowEndDt = null;
				if(offAllowlist.getEndDate() != null) {
					  long offAllowEndDateTime = offAllowlist.getEndDate().getTime();
					  Instant instant2 = Instant.ofEpochMilli(offAllowEndDateTime);
					  OffAllowEndDt = instant2.atZone(ZoneId.systemDefault()).toLocalDate();
				  }
				if(allowanceList.getExpiryDate() != null) {
					  long allowExpDateTime = allowanceList.getExpiryDate().getTime();
					  Instant instant2 = Instant.ofEpochMilli(allowExpDateTime);
					  allowanceExpDate = instant2.atZone(ZoneId.systemDefault()).toLocalDate();
				  }
				  long currentDateTime = date.getTime();
				  Instant instant = Instant.ofEpochMilli(versionStartDateTime);
				  Instant instant1 = Instant.ofEpochMilli(offAllowStartDateTime);
				  Instant  instant3 = Instant.ofEpochMilli(currentDateTime);
				  
				  LocalDate OffAllowStartDt = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
			      LocalDate versionStrtDt = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			      LocalDate currentDate = instant3.atZone(ZoneId.systemDefault()).toLocalDate();
			      
			      if((OffAllowEndDt == null && (OffAllowStartDt.compareTo(currentDate) <= 0))
		    			  || (OffAllowEndDt != null && OffAllowEndDt.compareTo(currentDate) >= 0 &&
		    			  			OffAllowEndDt.compareTo(OffAllowStartDt) >= 0) ) {
			    	  
			    	  if((allowanceExpDate == null) 
			    		  && (versionStrtDt.compareTo(currentDate) <= 0)|| 
			    		  	(allowanceExpDate != null && (OffAllowStartDt.compareTo(allowanceExpDate) < 0))
			    		  	&& allowanceExpDate.compareTo(currentDate) <= 0) {
			    		  if(offAllowlist.getAllowanceType().equalsIgnoreCase(allowanceList.getAllowanceType())) {

					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(currentDateTime);
					if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
						if("Y".equalsIgnoreCase(allowanceList.getSundayFlag())) {
							offAllowPayDetValues = setOffAllowPayDetValues(offAllowlist, allowanceList);
							offAllowPayDetailsList.add(offAllowPayDetValues);
						}
					}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
						if("Y".equalsIgnoreCase(allowanceList.getMondayFlag())) {
							offAllowPayDetValues = setOffAllowPayDetValues(offAllowlist, allowanceList);
							offAllowPayDetailsList.add(offAllowPayDetValues);
						}
					}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
						if("Y".equalsIgnoreCase(allowanceList.getTuesdayFlag())) {
							offAllowPayDetValues = setOffAllowPayDetValues(offAllowlist, allowanceList);
							offAllowPayDetailsList.add(offAllowPayDetValues);
						}
					}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
						if("Y".equalsIgnoreCase(allowanceList.getWednesdayFlag())) {
							offAllowPayDetValues = setOffAllowPayDetValues(offAllowlist, allowanceList);
							offAllowPayDetailsList.add(offAllowPayDetValues);
						}
					}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
						if("Y".equalsIgnoreCase(allowanceList.getThursdayFlag())) {
							offAllowPayDetValues = setOffAllowPayDetValues(offAllowlist, allowanceList);
							offAllowPayDetailsList.add(offAllowPayDetValues);
						}
					}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
						if("Y".equalsIgnoreCase(allowanceList.getFridayFlag())) {
							offAllowPayDetValues = setOffAllowPayDetValues(offAllowlist, allowanceList);
							offAllowPayDetailsList.add(offAllowPayDetValues);
						}
					}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
						if("Y".equalsIgnoreCase(allowanceList.getSaturdayFlag())) {
							offAllowPayDetValues = setOffAllowPayDetValues(offAllowlist, allowanceList);
							offAllowPayDetailsList.add(offAllowPayDetValues);
								}
							}
			    		 }
			    	 }
			     }
			 }
		  }
		}
		}
		
		if (offAllowPayDetailsList != null && offAllowPayDetailsList.size() > 0) {
			try {
				result = oimallowRepository.saveOffAllowPayDetValues(offAllowPayDetailsList);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in saveOffAllowPayDetValues :: " + e);
			}
		}
		return result;
	}
	
	public OffAllowPayDetails setOffAllowPayDetValues(OffenderAllowances offAllowlist, Allowances allowanceList){
		OffAllowPayDetails offAllowPayDet = new OffAllowPayDetails();
		offAllowPayDet.setOffenderBookId(offAllowlist.getOffenderBookId());
		offAllowPayDet.setOffAllowanceId(offAllowlist.getOffAllowanceId());
		offAllowPayDet.setAllowanceType(offAllowlist.getAllowanceType());
		offAllowPayDet.setVersionNo(allowanceList.getVersionNo());
		offAllowPayDet.setAllowanceVersionUnit(allowanceList.getUnit());
		offAllowPayDet.setAllowanceVersionMaxUnit(allowanceList.getMaxUnit());
		offAllowPayDet.setAllowanceVersionRate(allowanceList.getRate());
		offAllowPayDet.setCreateUserId(allowanceList.getCreateUserId());
		return offAllowPayDet;
	}
}
