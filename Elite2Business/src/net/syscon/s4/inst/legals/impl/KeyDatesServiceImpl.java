package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.KeyDatesRepository;
import net.syscon.s4.inst.legals.KeyDatesService;
import net.syscon.s4.inst.legals.beans.KeyDateValueMapping;
import net.syscon.s4.inst.legals.beans.KeyDates;
import net.syscon.s4.inst.legals.beans.SentenceKeyDates;

@Service
public class KeyDatesServiceImpl implements KeyDatesService{
	
	private static Logger logger = LogManager.getLogger(KeyDatesServiceImpl.class);
	@Autowired
	KeyDatesRepository keyDatesRepository;
	
	@Override
	public List<KeyDates> fetchKeyDates() {
		return keyDatesRepository.fetchKeyDates();
	}

	@Override
	public List<KeyDates> populateKeyDates(List<KeyDates> keyDatesLablesList) {
		return keyDatesRepository.populateKeyDates(keyDatesLablesList);
	}
	
	@Override
	public List<KeyDates> populateKeyDatesApi(Long OffednerBookId) {
		List<KeyDates> keyDatesLablesList = keyDatesRepository.fetchKeyDates();
		if(keyDatesLablesList!=null) {
			keyDatesLablesList.forEach(keyDateLabel -> keyDateLabel.setOffenderBookId(OffednerBookId));
		}
		return keyDatesRepository.populateKeyDates(keyDatesLablesList);
	}

	@Override
	public Integer updateKeyDates(List<KeyDates> updatedKeyDatesList) {
		Integer validateDateFlag = 0;
		List<KeyDateValueMapping> keydateValuesList = new ArrayList<KeyDateValueMapping>();
		KeyDateValueMapping dateValue = null;
		 SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
		SentenceKeyDates updatedKeyDatesData = new SentenceKeyDates();
			for (KeyDates keyDts : updatedKeyDatesList) {
				try {
				dateValue = new KeyDateValueMapping();
				dateValue.setCalculationDateColumn(keyDts.getProfileType());
				dateValue.setCalculationDateValue(keyDts.getCalculatedDate()!=null ? sdfr.format(keyDts.getCalculatedDate()) : "");
				if(keyDts.getProfileType2().equals("N")) {
					String overrideDateColumn = keyDts.getProfileType().replace("CALCULATED","OVERRIDED");			
					dateValue.setOverrideDateColumn(overrideDateColumn);
					dateValue.setOverrideDateValue(keyDts.getCalculatedDate()!=null ? sdfr.format(keyDts.getCalculatedDate()) : "");
					dateValue.setCalculationDateValue("");
				}
				
				if(keyDts.getProfileType2().equals("Y")) {
					String overrideDateColumn = keyDts.getProfileType().replace("CALCULATED","OVERRIDED");			
					dateValue.setOverrideDateColumn(overrideDateColumn);
					dateValue.setOverrideDateValue(keyDts.getOverrideDate()!=null ? sdfr.format(keyDts.getOverrideDate()) : "");
				}
							
				if(keyDts.getCalaculationReason()!=null) {
					dateValue.setCalculatedReason(keyDts.getCalaculationReason());
				}else {
					dateValue.setCalculatedReason("OVERRIDE");	
				}
				if(keyDts.getCommentText()!=null) {
					dateValue.setCommentText(keyDts.getCommentText());
				}else {
					dateValue.setCommentText("");
				}
				if(keyDts.getStaffName()!=null) {
					dateValue.setStaffId(fetchstaffId(keyDts.getStaffName()));
				}else {
					dateValue.setStaffId(null);
				}
				
				dateValue.setCreateDateTime(sdfr.format(keyDts.getCreateDateTime()));
				dateValue.setCreateUserId(keyDts.getCreateUserId());
				dateValue.setOffenderBookId(keyDts.getOffenderBookId());
				keydateValuesList.add(dateValue);			
				}catch(Exception e) {
					logger.error("updateKeyDates"+e.getMessage());
				}
			}
			
		validateDateFlag = validateDates(updatedKeyDatesData);
		if(validateDateFlag==1) {
			validateDateFlag = keyDatesRepository.createKeyDatesHistory(keydateValuesList);
		}
		return validateDateFlag;
	}
	
	private Integer validateDates(SentenceKeyDates updatedKeyDtData) {
		Integer result = 1;
		if(updatedKeyDtData.getHDCED_Calculated_Date()!=null || updatedKeyDtData.getHDCED_Overrided_Date()!=null) {
			if(updatedKeyDtData.getCRD_Overrided_Date()!=null && updatedKeyDtData.getCRD_Calculated_Date()!=null
					&& updatedKeyDtData.getARD_Calculated_Date()!=null && updatedKeyDtData.getARD_Overrided_Date()!=null) {
							result = 2;
			}
		}else if((updatedKeyDtData.getHDCED_Calculated_Date()==null && updatedKeyDtData.getHDCED_Overrided_Date()==null && updatedKeyDtData.getHDCAD_Overrided_Date()==null)) {
					if(updatedKeyDtData.getCRD_Calculated_Date()==null || updatedKeyDtData.getCRD_Overrided_Date()==null
							 || updatedKeyDtData.getARD_Calculated_Date()==null || updatedKeyDtData.getARD_Overrided_Date()==null ) {
								boolean isCurfew = keyDatesRepository.isCurfewRecordExist(updatedKeyDtData.getOffenderBookId());
								if(isCurfew) {
									result = -1;
								}
					}
		}
		return result;
	}
	
	private Integer fetchstaffId(String staffName) {
		return keyDatesRepository.fetchstaffId(staffName);
	}

}
