package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAgencyLocationsCommitBean;
import net.syscon.s4.im.beans.CaseloadsCommitBean;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OumacaseControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OumacaseControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void executeQueryTestMethod() {
		try {
			final Caseloads caseloadsRecords = new Caseloads();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(caseloadsRecords);
			ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/oumacase/executeQuery", caseloadsRecords).contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void commitTestMethod() {
		try {
			final CaseloadsCommitBean caseLoadsCommitBean = new CaseloadsCommitBean();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(caseLoadsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumacase/csldCommit", caseLoadsCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void prTrustRecordGroupTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumacase/payrollTrustRgRecordGroup", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void commTrustRecordGroupTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumacase/commissaryTrustRgRecordGroup", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void trustCommRecordGroupTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumacase/trustCommissaryRgRecordGroup", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void communityTrustRecordGroupTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumacase/communityTrustRgRecordGroup", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void typeRecordGroupTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumacase/typeRgRecordGroup", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void alAgyLocIdRecordGroupTestMethod() {
		try {
			final AgencyLocations agencyLocations = new AgencyLocations();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(agencyLocations);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumacase/alAgyLocIdRgRecordGroup", agencyLocations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void agencyLocationsExecuteQueryTestMethod() {
		try {
			final CaseloadAgencyLocations clAgencyLocations = new CaseloadAgencyLocations();
			clAgencyLocations.setCaseloadId("ITAG");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(clAgencyLocations);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumacase/alExecuteQuery", clAgencyLocations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void aencyLocationsCommitTestMethod() {
		try {
			final CaseloadAgencyLocationsCommitBean clAgyCommitBean = new CaseloadAgencyLocationsCommitBean();
			final List<CaseloadAgencyLocations> lst = new ArrayList<CaseloadAgencyLocations>();
			final CaseloadAgencyLocations clAgLocations = new CaseloadAgencyLocations();
			clAgLocations.setCaseloadId("sdfsdg");
			clAgLocations.setAgyLocId("9876");
			clAgLocations.setUpdateAllowedFlag("Y");
			clAgLocations.setCreateDateTime(new Date());
			clAgLocations.setCreateUserId("OMS_OWNER");
			clAgLocations.setSealFlag(null);
			clAgLocations.setModifyDateTime(null);
			clAgLocations.setModifyUserId(null);
			lst.add(clAgLocations);

			final CaseloadAgencyLocations clAgLocations1 = new CaseloadAgencyLocations();
			clAgLocations1.setCaseloadId("sdfsdg");
			clAgLocations1.setAgyLocId("ABCD");
			clAgLocations1.setUpdateAllowedFlag("Y");
			clAgLocations1.setCreateDateTime(new Date());
			clAgLocations1.setCreateUserId("OMS_OWNER");
			lst.add(clAgLocations1);

			final CaseloadAgencyLocations clAgLocations2 = new CaseloadAgencyLocations();
			clAgLocations2.setCaseloadId("sdfsdg");
			clAgLocations2.setAgyLocId("HHH");
			clAgLocations2.setUpdateAllowedFlag("Y");
			clAgLocations2.setCreateDateTime(new Date());
			clAgLocations2.setCreateUserId("OMS_OWNER");
			lst.add(clAgLocations2);

			clAgyCommitBean.setUpdateList(lst);

			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(clAgyCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumacase/alCommit", clAgyCommitBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void checkAgencyQueryTestMethod() {
		try {
			final CaseloadAgencyLocations clAgencyLocations = new CaseloadAgencyLocations();
			clAgencyLocations.setCaseloadId("ITAG");
			clAgencyLocations.setAgyLocId("ABCD");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(clAgencyLocations);
			ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/oumacase/checkAgency", clAgencyLocations).contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}