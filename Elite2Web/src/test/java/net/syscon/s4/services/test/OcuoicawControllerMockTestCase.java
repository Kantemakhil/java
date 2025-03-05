package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.OffenderOicSanctionsCommitBean;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OcuoicawControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OcuoicawControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoicawControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderOicSanctionsExecuteQueryTestMethod() {
		OffenderOicSanctions offOicSancBean = new OffenderOicSanctions();
		offOicSancBean.setOicHearingId(2265);
		offOicSancBean.setResultSeq(1);
		try {

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(offOicSancBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicaw/oicSancExecuteQuery", offOicSancBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId").value(19644));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderOicSanctionsCommitTestMethod() {
		OffenderOicSanctionsCommitBean commitBean = new OffenderOicSanctionsCommitBean();
		List<OffenderOicSanctions> insertList = new ArrayList<>();
		OffenderOicSanctions offOicSancBean = new OffenderOicSanctions();
		offOicSancBean.setOffenderBookId(20311);
		offOicSancBean.setSanctionSeq(6);
		offOicSancBean.setConsecutiveSanctionSeq(6);
		offOicSancBean.setCreateUserID("ADMINQA");
		offOicSancBean.setConsecutiveOffenderBookId(null);
		offOicSancBean.setEffectiveDate(new java.util.Date());
		insertList.add(offOicSancBean);
		commitBean.setInsertList(insertList);
		try {

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicaw/oicSancCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderOicSanctionsUpdateCommitTestMethod() {
		OffenderOicSanctionsCommitBean commitBean = new OffenderOicSanctionsCommitBean();
		List<OffenderOicSanctions> updateList = new ArrayList<>();
		OffenderOicSanctions offOicSancBean = new OffenderOicSanctions();
		offOicSancBean.setOffenderBookId(20311);
		offOicSancBean.setSanctionSeq(3);
		offOicSancBean.setConsecutiveSanctionSeq(3);
		offOicSancBean.setOicSanctionCode("DC");
		offOicSancBean.setCreateUserID("ADMINQA");
		offOicSancBean.setConsecutiveOffenderBookId(null);
		offOicSancBean.setEffectiveDate(new java.util.Date());
		updateList.add(offOicSancBean);
		commitBean.setUpdateList(updateList);
		try {

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicaw/oicSancCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	// /**
	// *Fetching the record from database table
	// *@Param searchRecord
	// */
	// @Test
	// public void offenderOicSanctionsRecordGroupTestMethod() {
	// try {
	// ResultActions resultActions = this.mockMvc
	// .perform(get("/ocuoicaw/rgOtherSanctionsRecordGroup",
	// tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
	// .content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
	// .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	// .andDo(print());
	// resultActions.andExpect(jsonPath("$[0].name", is("Name")));
	// } catch (Exception e) {
	// logger.error("", e);
	// }
	// }
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderOicSanctionsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoicaw/rgSanctRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$[0].code").value("SUSP"));
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
	public void ocuoicawRgSanctStRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoicaw/rgSanctStRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$[0].description").value("Active"));
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
	public void ocuoicawOcuoicawwhenvalidateitemgetoicoffencecodecur() {
		OicOffences oicOffencesBean = new OicOffences();
		oicOffencesBean.setOicOffenceCode("111");
		try {

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicOffencesBean);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoicaw/whenvalidateitemgetoicoffencecodecur", oicOffencesBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].maxPenaltyMonths").value(10));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void ocuoicawGetprofilevaluevsprofvalcur() {
		SystemProfiles systemProfilesBean = new SystemProfiles();
		systemProfilesBean.setProfileCode("PS_CORS_CODE");
		systemProfilesBean.setProfileType("LABEL");
		try {

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(systemProfilesBean);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoicaw/getprofilevaluevsprofvalcur", systemProfilesBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.profileValue2").value("677"));
		} catch (Exception e) {
			logger.error(e);
		}

	}
}