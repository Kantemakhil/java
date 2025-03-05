package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.OffenderIdentifyingMark;
import net.syscon.s4.common.beans.OffenderIdentifyingMarksCommitBean;
import net.syscon.s4.common.beans.OffenderPhysicalAttributesCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.OffenderProfileDetailsCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidpidenControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderPhysicalAttributesExecuteQueryTestMethod() {
		try {
			OffenderPhysicalAttributes offenderPhysicalAttributes = new OffenderPhysicalAttributes();
			offenderPhysicalAttributes.setOffenderBookId(Long.valueOf(18808));
			String jsonInString = new ObjectMapper().writeValueAsString(offenderPhysicalAttributes);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offPaExecuteQuery", offenderPhysicalAttributes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].createUserId", is("OMS_OWNER")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderPhysicalAttributesCommitTestMethod() {
		try {
			OffenderPhysicalAttributes offenderPhysicalAttributes = new OffenderPhysicalAttributes();
			offenderPhysicalAttributes.setOffenderBookId(Long.valueOf(22395));
			offenderPhysicalAttributes.setAttributeSeq(Long.valueOf(1));
			offenderPhysicalAttributes.setHeightCm(BigDecimal.valueOf(12));
			
			List<OffenderPhysicalAttributes> offenderPhysicalAttributesList = new ArrayList<OffenderPhysicalAttributes>();
			offenderPhysicalAttributesList.add(offenderPhysicalAttributes);
			
			OffenderPhysicalAttributesCommitBean commitBean = new OffenderPhysicalAttributesCommitBean();
			commitBean.setInsertList(offenderPhysicalAttributesList);
			String jsonInString = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offPaCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void offenderPhysicalAttributesUpdateTestMethod() {
		try {
			OffenderPhysicalAttributes offenderPhysicalAttributes = new OffenderPhysicalAttributes();
			offenderPhysicalAttributes.setHeightFt(BigDecimal.valueOf(5));
			
			offenderPhysicalAttributes.setOffenderBookId(Long.valueOf(22390));
			offenderPhysicalAttributes.setAttributeSeq(Long.valueOf(88888));
			
			List<OffenderPhysicalAttributes> offenderPhysicalAttributesList = new ArrayList<OffenderPhysicalAttributes>();
			offenderPhysicalAttributesList.add(offenderPhysicalAttributes);
			
			OffenderPhysicalAttributesCommitBean commitBean = new OffenderPhysicalAttributesCommitBean();
			commitBean.setUpdateList(offenderPhysicalAttributesList);
			String jsonInString = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offPaCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void offenderPhysicalAttributesDeleteTestMethod() {
		try {
			OffenderPhysicalAttributes offenderPhysicalAttributes = new OffenderPhysicalAttributes();
			
			offenderPhysicalAttributes.setOffenderBookId(Long.valueOf(20667));
			//offenderPhysicalAttributes.setAttributeSeq(88888);
			
			List<OffenderPhysicalAttributes> offenderPhysicalAttributesList = new ArrayList<OffenderPhysicalAttributes>();
			offenderPhysicalAttributesList.add(offenderPhysicalAttributes);
			
			OffenderPhysicalAttributesCommitBean commitBean = new OffenderPhysicalAttributesCommitBean();
			commitBean.setDeleteList(offenderPhysicalAttributesList);
			String jsonInString = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offPaCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderPhysicalAttributesRecordGroupTestMethod() {
		try {
//			String profileType = "EYE";
//			String jsonInString = new ObjectMapper().writeValueAsString(profileType);
//			ResultActions resultActions = this.mockMvc
//					.perform(get("/api/oidpiden/rgProfileRecordGroup" , profileType)
//							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
//							.accept(MediaType.APPLICATION_JSON_UTF8))
//					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//					.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpiden/rgProfileRecordGroup?profileType=EYE")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Blue")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void oidpidenRgMarkTypeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpiden/rgMarkTypeRecordGroup")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("CONV")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void oidpidenRgBodyPartRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpiden/rgBodyPartRecordGroup")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Conversion")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void oidpidenRgSideRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpiden/rgSideRecordGroup")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Right")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void oidpidenRgPartOrientRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpiden/rgPartOrientRecordGroup")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Conversion")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void oidpidengRaceCodeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpiden/rgRaceCodeRecordGroup")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Caucasian")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void oidpidenOffPdExecuteQueryTestMethod() {
		try {
			OffenderProfileDetails offenderProfileDetails = new OffenderProfileDetails();
			offenderProfileDetails.setOffenderBookId(Long.valueOf(18808));
			String jsonInString = new ObjectMapper().writeValueAsString(offenderProfileDetails);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offPdExecuteQuery", offenderProfileDetails)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].createUserId", is("RSTUBBS")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderProfileDetailsCommitTestMethod() {
		try {
			OffenderProfileDetails offenderProfileDetails = new OffenderProfileDetails();
			offenderProfileDetails.setProfileCode("ARKIN");
			offenderProfileDetails.setListSeq(BigDecimal.valueOf(77));
			offenderProfileDetails.setCreateDatetime(new Date());
			offenderProfileDetails.setCreateUserId("SRI");
			offenderProfileDetails.setOffenderBookId(Long.valueOf(18773));
			offenderProfileDetails.setProfileSeq(Long.valueOf(1));
			offenderProfileDetails.setProfileType("DIET");
			
			List<OffenderProfileDetails> offenderProfileDetailsList = new ArrayList<OffenderProfileDetails>();
			offenderProfileDetailsList.add(offenderProfileDetails);
			
			OffenderProfileDetailsCommitBean commitBean = new OffenderProfileDetailsCommitBean();
			commitBean.setUpdateList(offenderProfileDetailsList);
			String jsonInString = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offPdCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderIdentifyingMarksExecuteQueryTestMethod() {
		try {
			OffenderIdentifyingMark offenderIdentifyingMark = new OffenderIdentifyingMark();
			offenderIdentifyingMark.setOffenderBookId(18808);
			String jsonInString  = new ObjectMapper().writeValueAsString(offenderIdentifyingMark);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offImExecuteQuery", offenderIdentifyingMark)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].createUserId", is("ADMINQA")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderIdentifyingMarksCommitTestMethod() {
		try {
			OffenderIdentifyingMark offenderIdentifyingMark = new OffenderIdentifyingMark();
			offenderIdentifyingMark.setOffenderBookId(18773);
			//offenderIdentifyingMark.setIdMarkSeq(35);
			offenderIdentifyingMark.setBodyPartCode("TEST");
			offenderIdentifyingMark.setMarkType("ARKIN");
			
			List<OffenderIdentifyingMark> offenderIdentifyingMarkList = new ArrayList<OffenderIdentifyingMark>();
			offenderIdentifyingMarkList.add(offenderIdentifyingMark);
			
			OffenderIdentifyingMarksCommitBean commitBean = new OffenderIdentifyingMarksCommitBean();
			commitBean.setInsertList(offenderIdentifyingMarkList);
			
			String jsonInString  = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offImCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void offenderIdentifyingMarksUpdateTestMethod() {
		try {
			OffenderIdentifyingMark offenderIdentifyingMark = new OffenderIdentifyingMark();
			
			offenderIdentifyingMark.setBodyPartCode("ARKINTECH");
			offenderIdentifyingMark.setMarkType("MARK");
			offenderIdentifyingMark.setOffenderBookId(18773);
			offenderIdentifyingMark.setIdMarkSeq(32);
			offenderIdentifyingMark.setCreateDatetime(new Date());
			offenderIdentifyingMark.setCreateUserId("SRI");
			
			List<OffenderIdentifyingMark> offenderIdentifyingMarkList = new ArrayList<OffenderIdentifyingMark>();
			offenderIdentifyingMarkList.add(offenderIdentifyingMark);
			
			OffenderIdentifyingMarksCommitBean commitBean = new OffenderIdentifyingMarksCommitBean();
			commitBean.setUpdateList(offenderIdentifyingMarkList);
			
			String jsonInString  = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offImCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void offenderIdentifyingMarksDeleteTestMethod() {
		try {
			OffenderIdentifyingMark offenderIdentifyingMark = new OffenderIdentifyingMark();
			
			
			offenderIdentifyingMark.setOffenderBookId(18773);
			offenderIdentifyingMark.setIdMarkSeq(32);
			
			
			List<OffenderIdentifyingMark> offenderIdentifyingMarkList = new ArrayList<OffenderIdentifyingMark>();
			offenderIdentifyingMarkList.add(offenderIdentifyingMark);
			
			OffenderIdentifyingMarksCommitBean commitBean = new OffenderIdentifyingMarksCommitBean();
			commitBean.setDeleteList(offenderIdentifyingMarkList);
			
			String jsonInString  = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offImCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}


	

	/**
	 * method for query callings
	 */
//	@Test
//	public void oidpidenOffbkgpredelete() {
//		try {
//			Offenders offenders = new Offenders();
//			offenders.setOffenderId(22222);
//			
//			String jsonInString  = new ObjectMapper().writeValueAsString(offenders);
//			ResultActions resultActions = this.mockMvc
//					.perform(get("/oidpiden/offbkgpredelete", offenders)
//							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
//							.accept(MediaType.APPLICATION_JSON_UTF8))
//					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//					.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//	}

	/**
	 * method for query callings
	 */
	@Test
	public void oidpidenNbtdetaildescwhenvalidateitemprofiletypes() {
		try {
			ProfileTypes profileTypes = new ProfileTypes();
			profileTypes.setProfileType("BUILD");
			String jsonInString  = new ObjectMapper().writeValueAsString(profileTypes);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/nbtdetaildescwhenvalidateitemprofiletypes", profileTypes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("CODE")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

//	/**
//	 * method for query callings
//	 */
//	@Test
//	public void oidpidenNbtdetaildescwhennewiteminstanceprofiletypes() {
//		try {
//			ResultActions resultActions = this.mockMvc
//					.perform(get("/oidpidenNbtdetaildescwhennewiteminstanceprofiletypes", tagSearchGetOffenderRecords)
//							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
//							.accept(MediaType.APPLICATION_JSON_UTF8))
//					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//					.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//	}
//
//	/**
//	 * method for query callings
//	 */
//	@Test
//	public void oidpidenOffpdwhennewrecordinstanceprofiletypes() {
//		try {
//			ResultActions resultActions = this.mockMvc
//					.perform(get("/oidpidenOffpdwhennewrecordinstanceprofiletypes", tagSearchGetOffenderRecords)
//							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
//							.accept(MediaType.APPLICATION_JSON_UTF8))
//					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//					.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//	}
//
	/**
	 * method for query callings
	 */
	@Test
	public void oidpidenOffpdpostquerychardesccur() {
		try {
			ProfileTypes profileTypes = new ProfileTypes();
			profileTypes.setProfileType("BUILD");
			String jsonInString  = new ObjectMapper().writeValueAsString(profileTypes);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offpdpostquerychardesccur", profileTypes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("Build")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oidpidenCalltoshowfingerprintold() {
		try {
			SystemProfiles systemProfiles = new SystemProfiles();
			String jsonInString  = new ObjectMapper().writeValueAsString(systemProfiles); 
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpiden/calltoshowfingerprintold",systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is("")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oidpidenCgfkchkoffpdoffpdpflcodvaluetypecur() {
		try {
			ProfileTypes profileTypes = new ProfileTypes();
			profileTypes.setProfileType("BUILD");
			String jsonInString  = new ObjectMapper().writeValueAsString(profileTypes); 
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/cgfkchkoffpdoffpdpflcodvaluetypecur", profileTypes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("CODE")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void oidpidenOffRaceExecuteQueryTestMethod() {
		try {
			Offenders offenders = new Offenders();
			offenders.setOffenderId(Long.valueOf(1019592));
			String jsonInString  = new ObjectMapper().writeValueAsString(offenders); 
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offRaceExecuteQuery", offenders)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].raceCode", is("A1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void oidpidenOffRaceCommitTestMethod() {
		try {
			Offenders offenders = new Offenders();
			offenders.setRaceCode("ASIAN");
			offenders.setIdSourceCode("CODE");
			offenders.setLastName("SMITH");
			offenders.setSexCode("M");
			offenders.setCreateDate(new Date());
			offenders.setLastNameKey("MM");
			offenders.setCreateUserId("ADMIN");
			offenders.setOffenderId(Long.valueOf(1019592));
			offenders.setCreateDateTime(new Date());
			
			List<Offenders> offenderList = new ArrayList<Offenders>();
			offenderList.add(offenders);
			
			OffendersCommitBean commitBean = new OffendersCommitBean();
			commitBean.setUpdateList(offenderList);
			String jsonInString  = new ObjectMapper().writeValueAsString(commitBean); 
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpiden/offRaceCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].raceCode", is("A1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void checkImage() {
		try {
			
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpiden/checkImage?imageObjType=OFF_IDM&offenderBookId=19124&markType=MARK&bodypart=BACK&objectSeq=1")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].WORKING_CASELOAD_ID", is("ITAG")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	

}