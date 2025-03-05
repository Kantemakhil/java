package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import net.syscon.s4.inst.legals.beans.CaseIdentifiers;
import net.syscon.s4.inst.legals.beans.IdentifierCommitBean;
import net.syscon.s4.inst.legals.beans.IdentifierType;
import net.syscon.s4.services.config.EliteSpringConfig;

/***
 * 
 * class OcuccideControllerMockTestCase
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcuccideControllerMockTestCase extends AbstractMockTestCase{

	@Test
	public void caseIdentifiers() {
		try {
			List<CaseIdentifiers> Result = new ArrayList<CaseIdentifiers>();
			String caseId="10842";
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(caseId);
			ResultActions resultActions = this.mockMvc
					.perform(get("/ocuccide/caseIdentifiers?caseId=10842")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("Case Identifire", is(Result)));
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Test
	public void identifierType() {
		try {
			List<IdentifierType> Result = new ArrayList<IdentifierType>();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(Result);
			ResultActions resultActions = this.mockMvc
					.perform(get("/ocuccide/identifierType")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("Type", is(Result)));
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	
	@Test
	public void updateData() {
		try {
			IdentifierCommitBean identifierBeanCommit = new IdentifierCommitBean();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(identifierBeanCommit);
			ResultActions resultActions = this.mockMvc
					.perform(get("/ocuccide/updateIdentifierData")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("Type", is(identifierBeanCommit)));
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
}
