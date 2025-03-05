package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
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

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;
import net.syscon.s4.common.beans.ReferenceDomains;
import net.syscon.s4.common.beans.ReferenceDomainsCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OumrcodeControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OumrcodeControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumrcodeControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void referenceDomainsExecuteQueryTestMethod() {
		try {
			ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes .setDomain("STG_VAL_ACT");
			String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumrcode/refDmnExecuteQuery").contentType(MediaType.APPLICATION_JSON)
					.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Proceeding")));
		} catch (Exception e) {
			logger.error("refDmnExecuteQuery", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void referenceDomainsCommitTestMethod() {
		try {
			ReferenceDomains referenceDomains = new ReferenceDomains();
			referenceDomains.setDomain("BLINK_VIEW");
			referenceDomains.setDescription("Arkin view details");
			referenceDomains.setDomainStatus("Active");
			referenceDomains.setOwnerCode("jj");
			referenceDomains.setApplnCode("ll");
			referenceDomains.setOldCodeTable("");
			referenceDomains.setParentDomain("PLACE_TYPE");
			referenceDomains.setCodeLength(2);
			referenceDomains.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			referenceDomains.setCreateUserId("PP");
			referenceDomains.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			referenceDomains.setModifyUserId("lll");
			referenceDomains.setSuperSetDomain("BLINK_VIEW");
			referenceDomains.setSealFlag("N");
			List<ReferenceDomains> insertList = new ArrayList<ReferenceDomains>();
			insertList.add(referenceDomains);
			ReferenceDomainsCommitBean referenceDomainsCommitBean = new ReferenceDomainsCommitBean(); 
			referenceDomainsCommitBean.setInsertList(insertList);
			String jsonInString = new ObjectMapper().writeValueAsString(referenceDomainsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumrcode/refDmnCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("refDmnCommits", e);
		}
	}
	
	@Test
	public void referenceCodesCommitTestMethodupdate() {
	try {
		ReferenceCodesCommitBean referenceCodesCommitBean=new ReferenceCodesCommitBean();
		ReferenceCodes referenceCodes=new ReferenceCodes();
		referenceCodes.setDomain("IMAGE_VIEW");
		referenceCodes.setCode("CITE");
		referenceCodes.setDescription("TESTING");
		referenceCodes.setListSeq(null);
		referenceCodes.setActiveFlag("Y");
		referenceCodes.setSystemDataFlag("N");
		referenceCodes.setModifyUserId(null);
		referenceCodes.setExpiredDate(null);
		referenceCodes.setNewCode(null);
		referenceCodes.setParentCode("");
		referenceCodes.setParentDomain("105");
		referenceCodes.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
		referenceCodes.setCreateUserId("OMS_OWNER");
		referenceCodes.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
		referenceCodes.setSealFlag(null);
		List<ReferenceCodes> insertList = new ArrayList<ReferenceCodes>();
		insertList.add(referenceCodes);
		referenceCodesCommitBean.setInsertList(insertList);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(referenceCodesCommitBean);
		 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oumrcode/refCodeCommit").contentType(MediaType.APPLICATION_JSON)
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
	public void referenceCodesExecuteQueryTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumrcode/refCodeExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("refCodeExecuteQuery", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void referenceCodesCommitTestMethod() {
		try {
			ReferenceCodesCommitBean referenceCodesCommitBean=new ReferenceCodesCommitBean();
			ReferenceCodes referenceCodes=new ReferenceCodes();
			referenceCodes.setDomain("IMAGE_VIEW");
			referenceCodes.setCode("CITE");
			referenceCodes.setDescription("Glue");
			referenceCodes.setListSeq(null);
			referenceCodes.setActiveFlag("Y");
			referenceCodes.setSystemDataFlag("N");
			referenceCodes.setModifyUserId(null);
			referenceCodes.setExpiredDate(null);
			referenceCodes.setNewCode(null);
			referenceCodes.setParentCode("");
			referenceCodes.setParentDomain("105");
			referenceCodes.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			referenceCodes.setCreateUserId("OMS_OWNER");
			referenceCodes.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			referenceCodes.setSealFlag(null);
			List<ReferenceCodes> updateList = new ArrayList<ReferenceCodes>();
			updateList.add(referenceCodes);
			referenceCodesCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(referenceCodesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumrcode/refCodeCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
						.andDo(print());
						resultActions.andExpect(jsonPath("$", is(1)));
					} catch (Exception e) {
						logger.error("", e);
					}
	}
}