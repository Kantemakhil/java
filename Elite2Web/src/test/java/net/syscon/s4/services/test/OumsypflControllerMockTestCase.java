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

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OumsypflControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OumsypflControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void systemProfilesExecuteQueryTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("CLIENT");
			systemProfiles.setProfileCode("ASSIGN_WORK");
			final String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumsypfl/sysPflExecuteQuery", systemProfiles)
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
	 * Perfomring basic Oracle form functions i.e. insert in the the database
	 * table
	 * 
	 * @Param commitBean
	 */
	@Test
	public void systemProfilesCommitTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("MMM1");
			systemProfiles.setProfileCode("NNN1");
			systemProfiles.setDescription("JJJ");
			systemProfiles.setProfileValue("SS");
			systemProfiles.setProfileValue2("YYY");
			systemProfiles.setModifyUserId("MMBB");
			systemProfiles.setOldTableName("SYS");
			systemProfiles.setCreateDateTime(new Date());
			systemProfiles.setCreateUserId("LL");
			systemProfiles.setModifyDateTime(new Date());
			systemProfiles.setSealFlag("Y");

			final List<SystemProfiles> list = new ArrayList<SystemProfiles>();
			list.add(systemProfiles);
			final SystemProfilesCommitBean sysProfComBean = new SystemProfilesCommitBean();
			sysProfComBean.setInsertList(list);
			final String jsonInString = new ObjectMapper().writeValueAsString(sysProfComBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumsypfl/sysPflCommit", sysProfComBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Perfomring basic Oracle form functions i.e. update in the the database
	 * table
	 * 
	 * @Param commitBean
	 */
	@Test
	public void systemProfilesUpdateTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setDescription("JJJ1");
			systemProfiles.setProfileValue("SS1");
			systemProfiles.setProfileValue2("YYY1");
			systemProfiles.setModifyUserId("MMBB1");
			systemProfiles.setOldTableName("SYS1");
			systemProfiles.setCreateDateTime(new Date());
			systemProfiles.setCreateUserId("LL");
			systemProfiles.setModifyDateTime(new Date());
			systemProfiles.setSealFlag("N");
			systemProfiles.setProfileType("MMM1");
			systemProfiles.setProfileCode("NNN1");

			final List<SystemProfiles> list = new ArrayList<SystemProfiles>();
			list.add(systemProfiles);
			final SystemProfilesCommitBean sysProfComBean = new SystemProfilesCommitBean();
			sysProfComBean.setUpdateList(list);
			String jsonInString = new ObjectMapper().writeValueAsString(sysProfComBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumsypfl/sysPflCommit", sysProfComBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Perfomring basic Oracle form functions i.e. delete in the the database
	 * table
	 * 
	 * @Param commitBean
	 */
	@Test
	public void systemProfilesDeleteTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("MMM1");
			systemProfiles.setProfileCode("NNN1");

			final List<SystemProfiles> list = new ArrayList<SystemProfiles>();
			list.add(systemProfiles);
			final SystemProfilesCommitBean sysProfComBean = new SystemProfilesCommitBean();
			sysProfComBean.setDeleteList(list);
			final String jsonInString = new ObjectMapper().writeValueAsString(sysProfComBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumsypfl/sysPflCommit", sysProfComBean).contentType(MediaType.APPLICATION_JSON)
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
	 */
	@Test
	public void systemProfilesRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumsypfl/cgfkSysPflProfileTypeRecordGroup")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("BIO")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 *Fetching the record from database table
	 */
	@Test
	public void cgwhenNewFormInstance() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumsypfl/cgwhenNewFormInstance").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.user", is("OMS_OWNER")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 *  @Param referenceCodes 
	 */
	@Test
	public void cgfkchkSysPflSystemProfil() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes.setCode("BIO");
			final String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oumsypfl/cgfkchkSysPflSystemProfil", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("NT Biometrics Information")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}