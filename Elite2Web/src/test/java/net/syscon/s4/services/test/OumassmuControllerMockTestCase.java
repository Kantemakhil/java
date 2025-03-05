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

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.ModulePrivilegesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OumassmuControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OumassmuControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void omsRolesExecuteQueryTestMethod() {
		try {
			final OmsRoles bean = new OmsRoles();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumassmu/omsRoleExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].roleId", is(1986)));
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
	public void rgStaffMemberRolesRoleRecordGroupTestMethod() {
		try {
			final OmsRoles omsRoles = new OmsRoles();
			final String jsonInString = new ObjectMapper().writeValueAsString(omsRoles);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumassmu/rgStaffMemberRolesRoleRecordGroup", omsRoles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void cgfkModPrivAccessPrivilegeRecordGroupTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumassmu/cgfkModPrivAccessPrivilegeRecordGroup", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("All Privileges")));
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
	public void cgfkModPrivModuleNameRecordGroup() {
		try {
			final OmsModules omsModules = new OmsModules();
			final String jsonInString = new ObjectMapper().writeValueAsString(omsModules);

			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumassmu/cgfkModPrivModuleNameRecordGroup", omsModules)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].moduleName", is("AGINTLOC")));
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
	public void modulePrivilegesExecuteQueryTestMethod() {
		try {
			final ModulePrivileges modulePrivileges = new ModulePrivileges();
			final long roleId = 1987;
			modulePrivileges.setRoleId(roleId);
			final String jsonInString = new ObjectMapper().writeValueAsString(modulePrivileges);

			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumassmu/modPrivExecuteQuery", modulePrivileges)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].moduleName", is("CICONNEC")));
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
	public void modulePrivilegesCommitTestMethod() {
		try {
			final ModulePrivileges modulePrivileges = new ModulePrivileges();
			modulePrivileges.setModuleName("OIMLEGST");
			final Long roleId = (long) 1882;
			modulePrivileges.setRoleId(roleId);
			modulePrivileges.setAccessPrivilege("Q");
			modulePrivileges.setVerificationFlag("Y");
			modulePrivileges.setCreateDatetime(new Date());
			modulePrivileges.setCreateUserId("OMS_OWNER");
			modulePrivileges.setModifyDateTime(new Date());
			modulePrivileges.setModifyUserId("ADMINQA");
			modulePrivileges.setSealFlag(null);

			final List<ModulePrivileges> insertList = new ArrayList<ModulePrivileges>();
			insertList.add(modulePrivileges);

			final ModulePrivilegesCommitBean modulePrivilegesCommitBean = new ModulePrivilegesCommitBean();
			modulePrivilegesCommitBean.setUpdateList(insertList);
			final String jsonInString = new ObjectMapper().writeValueAsString(modulePrivilegesCommitBean);

			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumassmu/modPrivCommit", modulePrivilegesCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}