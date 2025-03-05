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

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentChargesCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * OcuoicchControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcuoicchControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoicchControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void agencyIncidentChargesExecuteQueryTestMethod() {
		try {
			final AgencyIncidentCharges agencyIncidentCharges = new AgencyIncidentCharges();
			agencyIncidentCharges.setAgencyIncidentId(3845);
			agencyIncidentCharges.setChargeSeq(1);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(agencyIncidentCharges);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicch/agyInciChgExecuteQuery", agencyIncidentCharges)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].guiltyEvidenceText", is("SS")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Inserting the data into database
	 * 
	 * @Param agencyIncidentChargesCommitBean
	 */
	@Test
	public void agencyIncidentChargesCommitTestMethod() {
		try {
			final AgencyIncidentCharges agencyIncidentCharges = new AgencyIncidentCharges();
			agencyIncidentCharges.setAgencyIncidentId(6007);
			agencyIncidentCharges.setPartySeq(1);
			agencyIncidentCharges.setOicChargeId(null);
			agencyIncidentCharges.setFindingCode("A");
			agencyIncidentCharges.setGuiltyEvidenceText("B");
			agencyIncidentCharges.setReportText("C");
			agencyIncidentCharges.setEvidenceDisposeText("DD");
			agencyIncidentCharges.setCreateDateTime(new Date());
			agencyIncidentCharges.setCreateUserId("AA");
			agencyIncidentCharges.setModifyDateTime(new Date());
			agencyIncidentCharges.setModifyUserId("BB");
			agencyIncidentCharges.setLidsChargeNumber(1144);
			agencyIncidentCharges.setChargedOicOffenceId(201);
			agencyIncidentCharges.setResultOicOffenceId(201);
			agencyIncidentCharges.setSealFlag("Y");
			final List<AgencyIncidentCharges> list = new ArrayList<AgencyIncidentCharges>();
			list.add(agencyIncidentCharges);
			final AgencyIncidentChargesCommitBean agencyIncidentChargesCommitBean = new AgencyIncidentChargesCommitBean();
			agencyIncidentChargesCommitBean.setInsertList(list);

			final AgencyIncidentCharges bean = new AgencyIncidentCharges();
			bean.setPartySeq(1);
			bean.setOicChargeId("506");
			bean.setFindingCode("AA");
			bean.setGuiltyEvidenceText("BA");
			bean.setReportText("C");
			bean.setEvidenceDisposeText("DDD");
			bean.setCreateDateTime(new Date());
			bean.setCreateUserId("AAA");
			bean.setModifyDateTime(new Date());
			bean.setModifyUserId("BBB");
			bean.setLidsChargeNumber(1144);
			bean.setChargedOicOffenceId(201);
			bean.setResultOicOffenceId(201);
			bean.setSealFlag("Y");
			bean.setAgencyIncidentId(6007);
			bean.setChargeSeq(409);
			final List<AgencyIncidentCharges> updateList = new ArrayList<AgencyIncidentCharges>();
			updateList.add(bean);
			agencyIncidentChargesCommitBean.setUpdateList(updateList);

			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(agencyIncidentChargesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicch/agyInciChgCommit", agencyIncidentChargesCommitBean)
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
	 * Deleting the data from database
	 * 
	 * @Param agencyIncidentChargesCommitBean
	 */
	@Test
	public void agencyIncidentChargesDeleteTestMethod() {
		try {
			final AgencyIncidentCharges agencyIncidentCharges = new AgencyIncidentCharges();
			agencyIncidentCharges.setAgencyIncidentId(6007);
			agencyIncidentCharges.setChargeSeq(409);
			final List<AgencyIncidentCharges> list = new ArrayList<AgencyIncidentCharges>();
			list.add(agencyIncidentCharges);
			final AgencyIncidentChargesCommitBean agencyIncidentChargesCommitBean = new AgencyIncidentChargesCommitBean();
			agencyIncidentChargesCommitBean.setDeleteList(list);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(agencyIncidentChargesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicch/agyInciChgCommit", agencyIncidentChargesCommitBean)
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
	public void agencyIncidentChargesRecordGroupTestMethod() {
		try {

			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoicch/rgOffenceCodeRecordGroup").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("test")));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	/**
	 * method for query callings
	 */
	@Test
	public void agyInciChgPostQuery() {
		try {
			final OicOffences oicOffences = new OicOffences();
			oicOffences.setOicOffenceId(201);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(oicOffences);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicch/agyInciChgPostQuery", oicOffences)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("GEN")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}