package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
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

import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.OmsRolesCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OumrolesControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OumrolesControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void omsRolesExecuteQueryTestMethod() {
		try {
			final OmsRoles omsRoles = new OmsRoles();
			omsRoles.setRoleName("dgsg");
			final String jsonInString = new ObjectMapper().writeValueAsString(omsRoles);

			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumroles/omsRoleExecuteQuery", omsRoles).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].roleId", is(1905)));
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
	public void omsRolesCommitTestMethod() {
		try {
			final OmsRoles omsRoles = new OmsRoles();
			omsRoles.setRoleName("TestRoles1");
			omsRoles.setRoleSeq(6);
			omsRoles.setCreateDateTime(new Date());
			omsRoles.setCreateUserId("OMS_OWNER");
			omsRoles.setModifyDateTime(new Date());
			omsRoles.setModifyUserId("ADMINQA");
			omsRoles.setRoleCode("TestCode1");
			omsRoles.setParentRoleCode(null);
			omsRoles.setSealFlag(null);

			final List<OmsRoles> insertList = new ArrayList<OmsRoles>();
			insertList.add(omsRoles);

			final OmsRolesCommitBean commitBean = new OmsRolesCommitBean();
			commitBean.setInsertList(insertList);
			final String jsonInString = new ObjectMapper().writeValueAsString(commitBean);

			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumroles/omsRoleCommit", commitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}