package net.syscon.s4.pkgs.tag_exceptions.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.pkgs.TagExceptions;
import net.syscon.s4.pkgs.tag_exceptions.TagExceptionsRepository;
import net.syscon.s4.pkgs.tag_exceptions.TagExceptionsService;

@Service
public class TagExceptionsServiceImpl implements TagExceptionsService {
	Logger logger = LogManager.getLogger(TagExceptionsServiceImpl.class);
	@Autowired
	TagExceptionsRepository tagExceptionsRepository;

	@Transactional
	@Override
	public Integer tagExceptionsTrigger(final TagExceptions tagExceptions) {
		List<TagExceptions> tagExceptionsList = new ArrayList<TagExceptions>();
		final TagExceptions targetObj = new TagExceptions();
		BeanUtils.copyProperties(tagExceptions, targetObj);
		Integer resultFlag = 0;
		final Long sidCur = tagExceptionsRepository.sidCur();
		String moduleNameCur = null;
		if (Optional.ofNullable(sidCur).isPresent()) {
			moduleNameCur = tagExceptionsRepository.moduleNameCur(sidCur);
		}
		final String systemProfilesCur = tagExceptionsRepository.systemProfilesCur();
		if (Optional.ofNullable(systemProfilesCur).isPresent() && "Y".equals(systemProfilesCur)) {
			targetObj.setTagErrorId(tagExceptionsRepository.tagErrorId());
			targetObj.setSid(sidCur);
			targetObj.setModuleName(moduleNameCur);
			targetObj.setModifyDatetime(new Date());
			tagExceptionsList.add(targetObj);
			try {
				resultFlag = tagExceptionsRepository.tagExceptionsInsert(tagExceptionsList);
			} catch (final Exception e) {
				logger.error("tagExceptionsTrigger", e);
				targetObj.setTagErrorId(tagExceptionsRepository.tagErrorId());
				targetObj.setProcedureName("TAG_EXCEPTIONS");
				targetObj.setErrorMessage(e.getMessage());
				targetObj.setErrorLocation("TAG_EXCEPTIONS PROCEDURE");
				tagExceptionsList = new ArrayList<TagExceptions>();
				tagExceptionsList.add(targetObj);
				resultFlag = tagExceptionsRepository.tagExceptionsInsert(tagExceptionsList);
			}
		}
		return resultFlag;
	}

}
