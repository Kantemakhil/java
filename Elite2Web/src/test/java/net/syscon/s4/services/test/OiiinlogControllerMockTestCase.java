package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.VAgencyIncidents;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OiiinlogControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OiiinlogControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiinlogControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vAgencyIncidentsExecuteQueryTestMethod() {
		try {
			VAgencyIncidents vAgencyIncidents = new VAgencyIncidents();
			vAgencyIncidents.setAgencyIncidentId(3926);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(vAgencyIncidents);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiiinlog/vAgyIncExecuteQuery", vAgencyIncidents)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].reportedStaffId", is(3289)));
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
	public void vAgencyIncidentsRecordGroupTestMethod1() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiiinlog/rgOccurTypeRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("DIST")));
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
	public void vAgencyIncidentsRecordGroupTestMethod2() {
		try {
			CaseloadAgencyLocations caseloadAgencyLocations = new CaseloadAgencyLocations();
			caseloadAgencyLocations.setCaseloadId("ITAG");
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(caseloadAgencyLocations);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiiinlog/rgAgyLocRecordGroup", caseloadAgencyLocations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Syscon Jail")));
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
	public void vAgencyIncidentsRecordGroupTestMethod3() {
		try {
			AgencyInternalLocations agencyInternalLocations = new AgencyInternalLocations();
			agencyInternalLocations.setAgyLocId("ITAG");
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyInternalLocations);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiiinlog/rgLevelLocRecordGroup", agencyInternalLocations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("ITAG-A&D")));
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
	public void vAgencyIncidentsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiiinlog/rgStaffRecordGroup?caseloadId=ITAG")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].staffName", is("ADAMS , ADAM")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}