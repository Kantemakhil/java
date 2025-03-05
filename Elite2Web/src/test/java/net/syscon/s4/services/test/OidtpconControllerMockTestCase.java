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
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.property.bean.InternalLocationUsages;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OidtpconControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidtpconControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderPptyContainersExecuteQueryTestMethod() {
		try {
			final OffenderPptyContainers offPptyCon = new OffenderPptyContainers();
			offPptyCon.setAgyLocId("ITAG");
			final String jsonInString = new ObjectMapper().writeValueAsString(offPptyCon);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtpcon/offConExecuteQuery", offPptyCon).content(jsonInString)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].propertyContainerId", is(4004)));
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
	public void offenderPptyContainersCommitTestMethod() {
		try {
			final OffenderPptyContainers offPptyCon = new OffenderPptyContainers();
			offPptyCon.setAgyLocId("OUT");
			offPptyCon.setTrnFromAgyLocId("ITAG");
			offPptyCon.setTrnToAgyLocId("SDTC");
			offPptyCon.setPropertyContainerId(3189);
			final List<OffenderPptyContainers> list = new ArrayList<OffenderPptyContainers>();
			list.add(offPptyCon);
			final OffenderPptyContainersCommitBean updateList = new OffenderPptyContainersCommitBean();
			updateList.setUpdateList(list);
			final String jsonInString = new ObjectMapper().writeValueAsString(updateList);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtpcon/offConCommit", updateList).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void offenderPptyContainersRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtpcon/cgfkOffConTrnToAgyLocIdRecordGroup?agyLocId=OUT")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("SDTC")));
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
	public void rgSelectAllRecordGroup() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtpcon/rgSelectAllRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("SDTC")));
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
	public void systemProfilesExecuteQueryTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileCode("PS_CORS_CODE");
			systemProfiles.setProfileType("LABEL");
			final String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtpcon/sysPflExecuteQuery", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Course Acticity Code Label")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void vPheadOnCheckDeleteMaster() {
		try {
			final OffenderPptyContainers paramBean = new OffenderPptyContainers();
			paramBean.setOffenderBookId(18773);
			final String jsonInString = new ObjectMapper().writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtpcon/vPheadOnCheckDeleteMaster", paramBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(BigDecimal.valueOf(1))));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oidtpconOffconpostquerycTestMethod() {
		try {
			final AgencyInternalLocations paramBean = new AgencyInternalLocations();
			paramBean.setInternalLocationId(5513);
			final String jsonInString = new ObjectMapper().writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtpcon/offConPostQuery", paramBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("SDTC-TEST200-TEST300")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/*
	 * method for query callings
	 */
	@Test
	public void oidtpconOffconwhennewrecordinstancelvgetagylocTestMethod() {
		try {
			final InternalLocationUsages paramBean = new InternalLocationUsages();
			paramBean.setInternalLocationUsageId(Long.valueOf(5345));
			final String jsonInString = new ObjectMapper().writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtpcon/offConWhenNewRecordInstance", paramBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.agyLocId", is("ITAG")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getOumapassCgwhennewforminstancecTestCaseMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtpcon/cgwhenNewFormInstance").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.user", is("OMS_OWNER")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}