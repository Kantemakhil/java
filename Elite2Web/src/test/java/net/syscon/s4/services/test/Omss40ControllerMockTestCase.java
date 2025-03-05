package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class Omss40ControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class Omss40ControllerMockTestCase extends AbstractMockTestCase {

	@Test
	public void preFormPtypeClient() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(systemProfiles);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/omss40/preFormPtypeClient").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].profileValue", is("Y")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void preFormPtypeSys() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(systemProfiles);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/omss40/preFormPtypeSys").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].profileValue", is("2571623906878CF6")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void whennewforminstancemenunamecur() {
		try {
			final MenuSecurities menuSecurities = new MenuSecurities();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(menuSecurities);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omss40/whenNewFormInstanceMenuNamecur").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].menuItem", is("Offender Management System")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void buildworkFlowmenurgmainWorkflowCur() {
		try {
			final WorkflowScreens workflowScreens = new WorkflowScreens();
			workflowScreens.setModuleName("OCDALERT");
			workflowScreens.setCaseLoadType("INST");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(workflowScreens);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omss40/buildworkFlowmenurgmainWorkflowCur")
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].workFlowCode", is("BOOKING")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void buildworkFlowmenurgsubWorkflowCur() {
		try {
			final WorkflowScreens workflowScreens = new WorkflowScreens();
			workflowScreens.setWorkFlowCode("CA");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(workflowScreens);
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/omss40/buildworkFlowmenurgsubWorkflowCur").contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.moduleName", is("OCICVWIT")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getcurrentcaseload() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/omss40/getCurrentCaseload?pUser=MGRAHAM")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is("CTAG")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void mainpopList() {
		try {
			final MenuSecurities menuSecurities = new MenuSecurities();
			menuSecurities.setMenuId(BigDecimal.valueOf(1234));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(menuSecurities);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/omss40/mainpopList").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].menuId", is(100)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getCaseloads() {
		try {
			final Caseloads caseloads = new Caseloads();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(caseloads);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omss40/getCaseLoads").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].caseloadId", is("ITAG")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void changeWorkingCaseLoadTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omss40/changeWorkingCaseLoad?lvCaseloadId=IATG&lvReturn=FALSE")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].WORKING_CASELOAD_ID", is("ITAG")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}