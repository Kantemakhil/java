package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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

import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * Class OcucoffeControllerMockTestCase
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OcucoffeControllerMockTestCase extends AbstractMockTestCase{

	@Test
	public void offendersExecuteQueryTestMethod() {
		try {
			final Offenders offenders = new Offenders();
			offenders.setRootOffenderId(BigDecimal.valueOf(1019061));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenders);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/aliasExecuteQuery", offenders).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019061)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void preinsertgetnextidentifierTestMethod() {     				     
		try {
			final OffenderIdentifier OffenderIdentifier = new OffenderIdentifier();
			OffenderIdentifier.setOffenderId(1019022);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(OffenderIdentifier);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/preinsertgetnextidentifier", OffenderIdentifier)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(2)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void checkpncexistsgetpncexTestMethod() {                       
		try {
			final OffenderIdentifier OffenderIdentifier = new OffenderIdentifier();
			OffenderIdentifier.setIdentifier("10KL9");
			OffenderIdentifier.setRootOffenderId(BigDecimal.valueOf(1019021));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(OffenderIdentifier);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/checkpncexistsgetpncex", OffenderIdentifier)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(0)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void validatealiasescheckdupnamecurTestMethod() { // ocucoffe/validatealiasescheckdupnamecur illegal argument
		try {
			final Offenders offenders = new Offenders();
			offenders.setOffenderId(Long.valueOf(1019022));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenders);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/validatealiasescheckdupnamecur", offenders)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019061)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void validatealiasescheckfordupoffcurTestMethod() { // ocucoffe/validatealiasescheckfordupoffcur illegal argument
		try {
			final Offenders offenders = new Offenders();
			offenders.setOffenderId(Long.valueOf(1019022));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenders);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/validatealiasescheckfordupoffcur", offenders)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019061)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agevalidationvsagecurTestMethod() { // ocucoffe/agevalidationvsagecur error
		try {
			final Dual paramBean = new Dual();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/agevalidationvsagecur").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019061)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agevalidationvsrangecurrTestMethod() { 
		try {
			final SystemProfiles paramBean = new SystemProfiles();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/agevalidationvsrangecur").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.profileValue", is("10")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offCommitTestMethod() { 
		try {
			Offenders offenders = new Offenders();
			//offenders.setOffenderId(127410186);
			offenders.setOffenderNameSeq(BigDecimal.valueOf(1));
			offenders.setIdSourceCode("ARKIN");
			offenders.setLastName("TESTM");
			// offenders.setNameType("def");
			//offenders.setFirstName("arkin");
			//offenders.setMiddleName("soft");
			//offenders.setBirthDate(new Date(1992, 05, 05));
			offenders.setSexCode("M");
			//offenders.setSuffix("aaa");
//			offenders.setLastName("m");
			//offenders.setBirthPlace("srcl");
			//offenders.setBirthCountryCode("sr");
			 offenders.setCreateDate(new Date(0, 0, 0, 0, 0, 0));
//			offenders.setLastName("nalgonda");
			//offenders.setAliasOffenderId(9);
			offenders.setFirstNameKey("b");
//			offenders.setMiddleName("k");
			//offenders.setOffenderIdDisplay("zzz");
			//offenders.setRootOffenderId(BigDecimal.valueOf(12));
			//offenders.setCaseloadType("ss");
			//offenders.setModifyUserId("1234");
			//offenders.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			//offenders.setAliasNameType("aaaa");
			//offenders.setParentOffenderId(BigDecimal.valueOf(13));
			//offenders.setUniqueObligationFlag("k");
			//offenders.setSuspendedFlag("s");
			//offenders.setSuspendedDate(new Date(0, 0, 0, 0, 0, 0));
			//offenders.setRaceCode("ggg");
			//offenders.setRemarkCode("ppp");
			//offenders.setAddInfoCode("ww");
			//offenders.setBirthCounty("sdasd");
			//offenders.setBirthState("ndsdn");
			//offenders.setMiddleName2("rrr");
			//offenders.setTitle("ts");
			//offenders.setAge(BigDecimal.valueOf(15));
			offenders.setCreateUserId("n");
			//offenders.setLastNameAlphaKey("y");
			offenders.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
//			offenders.setNameSequence("1");
			//offenders.setSealFlag("t");
			offenders.setLastNameKey("u");
			final List<Offenders> insertList = new ArrayList<Offenders>();
			insertList.add(offenders);
			final OffendersCommitBean commitBean = new OffendersCommitBean();
			commitBean.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(commitBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/offCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019061)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offExecuteQueryTestMethod() { 
		try {
			final Offenders offenders = new Offenders();
			offenders.setOffenderId(Long.valueOf(1019022));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenders);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/offExecuteQuery", offenders).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019061)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersCommitTestMethod() {      //success green but got zero
		try {
			final Offenders offenders = new Offenders();
			offenders.setOffenderId(Long.valueOf(10));
			offenders.setOffenderNameSeq(BigDecimal.valueOf(11));
			offenders.setIdSourceCode("12");
			offenders.setLastName("abc");
			offenders.setNameType("def");
			offenders.setFirstName("arkin");
			offenders.setMiddleName("soft");
			offenders.setBirthDate(new Date(0, 0, 0, 0, 0, 0));
			offenders.setSexCode("f");
			offenders.setSuffix("aaa");
			offenders.setLastName("m");
			offenders.setBirthPlace("srcl");
			offenders.setBirthCountryCode("sr");
			offenders.setCreateDate(new Date(0, 0, 0, 0, 0, 0));
			offenders.setLastName("nalgonda");
			offenders.setAliasOffenderId(Long.valueOf(9));
			offenders.setFirstNameKey("b");
			offenders.setMiddleName("k");
			offenders.setOffenderIdDisplay("zzz");
			offenders.setRootOffenderId(BigDecimal.valueOf(12));
			offenders.setCaseloadType("ss");
			offenders.setModifyUserId("1234");
			offenders.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			offenders.setAliasNameType("aaaa");
			offenders.setParentOffenderId(BigDecimal.valueOf(13));
			offenders.setUniqueObligationFlag("k");
			offenders.setSuspendedFlag("s");
			offenders.setSuspendedDate(new Date(0, 0, 0, 0, 0, 0));
			offenders.setRaceCode("ggg");
			offenders.setRemarkCode("ppp");
			offenders.setAddInfoCode("ww");
			offenders.setBirthCounty("sdasd");
			offenders.setBirthState("ndsdn");
			offenders.setMiddleName2("rrr");
			offenders.setTitle("ts");
			offenders.setAge(BigDecimal.valueOf(15));
			offenders.setCreateUserId("n");
			offenders.setLastNameAlphaKey("y");
			offenders.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			offenders.setNameSequence("fadf");
			offenders.setSealFlag("t");
			offenders.setLastNameKey("u");

			final List<Offenders> insertList = new ArrayList<Offenders>();
			insertList.add(offenders);
			final OffendersCommitBean offendersCommitBean = new OffendersCommitBean();
			offendersCommitBean.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offendersCommitBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/aliasCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/rgAliasNameTypeRecordGroup").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Working Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersRecordGrouprgIdentifierTypeRecordGroupTestMethod() {
		try {

			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/rgIdentifierTypeRecordGroup").contentType(MediaType.APPLICATION_JSON))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("DEA#")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersRecordGrouprgOffSuffixRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/rgOffSuffixRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("JR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersRecordGrouprgOffSexRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/rgOffSexRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("M")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersRecordGrouprgOffRaceRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/rgOffRaceRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Caucasian")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersRecordGrouprgIdSourceRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucoffe/rgIdSourceRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("System Assigned ID")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void OffenderIdentifierExecuteQueryTestMethod() {                     
		try {
			final OffenderIdentifier OffenderIdentifier = new OffenderIdentifier();
			OffenderIdentifier.setOffenderId(1019022);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(OffenderIdentifier);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/offIdExecuteQuery",OffenderIdentifier).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].caseloadType", is("INST")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void OffenderIdentifierCommitTestMethod() {                //body zero
		try {
			final OffenderIdentifier OffenderIdentifier = new OffenderIdentifier();

			OffenderIdentifier.setOffenderId(1019061);
			OffenderIdentifier.setOffenderIdSeq("aaa");
			OffenderIdentifier.setIdentifierType("hkk3");
			OffenderIdentifier.setIdentifier("Mithun13");
			OffenderIdentifier.setIssuedAuthorityText("arkin");
			OffenderIdentifier.setIssuedDate(new Date());
			OffenderIdentifier.setRootOffenderId(BigDecimal.valueOf(234135));
			OffenderIdentifier.setCaseloadType("hjjkh");
			OffenderIdentifier.setModifyUserId("GGGG");
			OffenderIdentifier.setModifyDateTime(new Date());
			OffenderIdentifier.setVerifiedFlag("Y");
			OffenderIdentifier.setCreateDateTime(new Date());
			OffenderIdentifier.setCreateUserId("MITHUN");
			OffenderIdentifier.setSealFlag("N");

			final List<OffenderIdentifier> updateList = new ArrayList<OffenderIdentifier>();
			updateList.add(OffenderIdentifier);

			final OffenderIdentifiersCommitBean offendersCommitBean = new OffenderIdentifiersCommitBean();
			offendersCommitBean.setUpdateList(updateList);

			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offendersCommitBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/offIdCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/*@Test
	public void OffenderIdentifieroffIdAllExecuteQueryTestMethod() {              //no Impl in Repo
		try {
			OffenderIdentifier OffenderIdentifier = new OffenderIdentifier();
			OffenderIdentifier.setOffenderId(1019022);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(OffenderIdentifier);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/offIdAllExecuteQuery", OffenderIdentifier)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].createUserId", is("ADMINQA")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}*/

	@Test
	public void offoncheckdeletemasteroffidallcur() {
		try {
			final OffenderIdentifier offender = new OffenderIdentifier();
			offender.setRootOffenderId(BigDecimal.valueOf(1019061));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offender);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/offoncheckdeletemasteroffidallcur", offender)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offoncheckdeletemasteraliascur() {
		try {
			final Offenders offender = new Offenders();
			offender.setOffenderId(Long.valueOf(1019081));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offender);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/offoncheckdeletemasteraliascur", offender)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void aliasoncheckdeletemasteroffidcur() {
		try {
			final OffenderIdentifier OffenderIdentifier = new OffenderIdentifier();
			OffenderIdentifier.setOffenderId(1019022);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(OffenderIdentifier);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/aliasoncheckdeletemasteroffidcur", OffenderIdentifier)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void ocucoffewhennewforminstance() {                     // illegal argument
		try {
			final Dual paramBean = new Dual();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/ocucoffe/whennewforminstance", paramBean).contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void postqueryreferencecodesc() {					
		try {
			final ReferenceCodes paramBean = new ReferenceCodes();
			paramBean.setDomain("STG_VAL_ACT");
			paramBean.setCode("DEVALIDATE");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/postqueryreferencecodesc", paramBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("De-Validated")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void postinsertgetrootoffenderid() {					
		try {
			final Offenders offender = new Offenders();
			offender.setOffenderId(Long.valueOf(1019022));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offender);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/postinsertgetrootoffenderid", offender)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.rootOffenderId", is(1019021)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	// Dynamic value generating so we can't except the value
	@Test
	public void preinsertgetnextalias() {              //success but got a illegal argument
		try {
			final Dual paramBean = new Dual();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/preInsertgetNextAlias", paramBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			// resultActions.andExpect(jsonPath("$", is(1030666)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void preinsertrecordex() {							//no result
		try {
			final OffenderIdentifier paramBean = new OffenderIdentifier();
			paramBean.setRootOffenderId(BigDecimal.valueOf(1019021));
			paramBean.setIdentifierType("SSN");
			paramBean.setIdentifier("10KL9");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(paramBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/preinsertrecordex", paramBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("X")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void createformglobals() {
		try {
			final OmsModules omsModules = new OmsModules();
			omsModules.setModuleName("OCUHGOVE");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(omsModules);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocucoffe/createformglobals", omsModules).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("Hdc Governor")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	
}