package net.syscon.s4.globaloffenderrecords.impl;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.globaloffenderrecords.OiuimageRepository;
import net.syscon.s4.globaloffenderrecords.OiuimageService;
import net.syscon.s4.globalrbac.OumusersRepository;
import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.im.beans.ImageOriginalsCommitBean;
import net.syscon.s4.im.beans.ImageProperties;
import net.syscon.s4.im.beans.ImagePropertiesCommitBean;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.property.OidrpitmRepository;
import net.syscon.s4.triggers.ImagesT1Service;
import net.syscon.s4.triggers.OmtsmService;
import net.syscon.s4.triggers.StaffMembersT1Service;

/**
 * Class OiuimageServiceImpl
 * 
 */
@Service
public class OiuimageServiceImpl extends BaseBusiness implements OiuimageService {

	@Autowired
	private OiuimageRepository oiuimageRepository;

	@Autowired
	private OidrpitmRepository oidrpitmRepository;

	@Autowired
	private ImagesT1Service imagesT1Service;

	@Autowired
	private StaffMembersT1Service staffMembersT1Service;
	@Autowired
	private OmtsmService omtsmService;
	
	@Autowired
	private OumusersRepository oumuRepository;

	private static Logger logger = LogManager.getLogger(OiuimageServiceImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<Images> imagesExecuteQuery(final Images searchRecord) {
		List<Images> returnList = new ArrayList<Images>();
		if (searchRecord.getScreenName() != null && "OIIPTRAN".equals(searchRecord.getScreenName().trim())) {
			if (searchRecord.getImageViewType() != null) {
				final String pptyCode = oiuimageRepository.getOiiptranPptyCode(searchRecord.getImageViewType());
				searchRecord.setImageViewType(pptyCode);
			}
		}
		returnList = oiuimageRepository.imagesExecuteQuery(searchRecord);
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstIMAGES
	 *
	 * @
	 */
	@Transactional
	public Integer imagesCommit(final ImagesCommitBean commitBean) {
		Integer liReturn = 0;
		final List<ImageOriginals> imageOriginalList = new ArrayList<ImageOriginals>();
		ImageOriginalsCommitBean imgOriginalCommitBean = new ImageOriginalsCommitBean();
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (Images bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = imagesInsertImages(commitBean.getInsertList());
			// Trigger call
			if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
				for (Images bean : commitBean.getInsertList()) {
					ImageOriginals bean1 = new ImageOriginals();
					bean1.setCreateUserId(commitBean.getCreateUserId());
					bean1.setImageId(bean.getImageId().intValue());
					bean1.setImageFull(bean.getImageThumbnail());
					imageOriginalList.add(bean1);
				}
				imagesT1Service.imageOriginalsInsert(imageOriginalList);
				imgOriginalCommitBean.setUpdateList(imageOriginalList);
			}

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (Images bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = imagesUpdateImages(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			final Images tempImg = new Images();
			List<ImageOriginals> deleteImageList = new ArrayList<ImageOriginals>();
			commitBean.getDeleteList().forEach(deleteList -> {
				ImageOriginals delOriginalObj = new ImageOriginals();
				delOriginalObj.setImageId(Integer.valueOf(deleteList.getImageId().toString()));
				delOriginalObj.setModifyUserId(commitBean.getCreateUserId());
				deleteImageList.add(delOriginalObj);
			});
			final Integer deleteResult = oiuimageRepository.imageOriginalsDeleteImageOriginals(deleteImageList);
			tempImg.setImageId(Long.valueOf(deleteResult.toString()));
			imgOriginalCommitBean.setDeleteList(deleteImageList);
			liReturn = tempImg.getImageId().intValue();
		}
		if (liReturn == 1
				&& ((imgOriginalCommitBean.getUpdateList() != null && imgOriginalCommitBean.getUpdateList().size() > 0)
						|| (imgOriginalCommitBean.getDeleteList() != null
								&& imgOriginalCommitBean.getDeleteList().size() > 0))) {
			imgOriginalCommitBean.setCreateUserId(commitBean.getCreateUserId());
			liReturn = imageOriginalsUpdateImageOriginals(imgOriginalCommitBean);
		}
		return liReturn;
	}

	@Transactional
	public Integer imagesInsertImages(final List<Images> lstImages) {
		for (final Images image : lstImages) {
			image.setImageThumbnail(resizeImage(image.getImageThumbnail(), 200, 200));
		}

		final Integer result = oiuimageRepository.imagesInsertImages(lstImages);
		return result;
	}

	@Transactional
	public Integer imagesUpdateImages(final List<Images> lstImages) {
		final Integer result = oiuimageRepository.imagesUpdateImages(lstImages);
		return result;
	}

	@Transactional
	public Integer imageOriginalsUpdateImageOriginals(final ImageOriginalsCommitBean commitBean) {
		Integer result = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() != 0) {
			commitBean.getUpdateList().forEach(imageOriginal -> {
				if (imageOriginal.getImageFull() != null) {
					imageOriginal.setImageOriginal(imageOriginal.getImageFull());
					imageOriginal.setImageFull(resizeImage(imageOriginal.getImageFull(), 600, 600));
					imageOriginal.setModifyUserId(commitBean.getCreateUserId());
				}
			});
			result = oiuimageRepository.imageOriginalsUpdateImageOriginals(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() != 0) {
			final List<Images> imageList = new ArrayList<>();
			final Images image = new Images();
			final ImageOriginals tempImgOri = new ImageOriginals();
			commitBean.getDeleteList().forEach(imageOriginal -> {
				imageList.clear();
				image.setImageId(Long.valueOf(imageOriginal.getImageId().toString()));
				imageList.add(image);
				imageList.forEach(il -> {il.setModifyUserId(commitBean.getCreateUserId());});
				final Integer delteResult = oiuimageRepository.imagesDeleteImages(imageList);
				tempImgOri.setImageId(delteResult);
			});
			result = tempImgOri.getImageId();
		}
		return result;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<ImageOriginals> imageOriginalsExecuteQuery(final ImageOriginals searchRecord) {
		return oiuimageRepository.imageOriginalsExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<ImageProperties> imagePropertiesExecuteQuery(final ImageProperties searchRecord) {
		final List<ImageProperties> returnList = oiuimageRepository.imagePropertiesExecuteQuery(searchRecord);
		List<ReferenceCodes> refcodes = new ArrayList<ReferenceCodes>();
		if (returnList.size() != 0) {
			final List<String> strLst = new ArrayList<String>();
			for (final ImageProperties obj : returnList) {
				refcodes = oiuimageRepository.imageGetPropertyDecription(obj.getProperty());
				if (refcodes.size() != 0) {
					for (final ReferenceCodes codes : refcodes) {
						codes.setDescription(codes.getDescription());
						strLst.add(codes.getDescription());
					}
					obj.setImageDes(strLst);
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstIMAGE_PROPERTIES
	 *
	 * @
	 */
	@Transactional
	public Integer imagePropertiesCommit(final ImagePropertiesCommitBean commitBean) {
		Integer liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(inslist -> {
				inslist.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = imagepropertiesInsertImageProperties(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(updList -> {
				updList.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oiuimageRepository.imagePropertiesUpdateImageProperties(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(obj -> {
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oiuimageRepository.imagePropertiesDeleteImageProperties(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public Integer imagepropertiesInsertImageProperties(final List<ImageProperties> imagepropertiesList) {

		for (final ImageProperties imageprpty : imagepropertiesList) {
			final Integer propertyId = oiuimageRepository.getImagePropertyId();
			imageprpty.setImagePropertyId(propertyId);
		}

		return oiuimageRepository.imagepropertiesInsertImageProperties(imagepropertiesList);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oiuimageRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<StaffMembers> staffMembersExecuteQuery(final StaffMembers searchRecord) {
		return oiuimageRepository.staffMembersExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAFF_MEMBERS
	 *
	 * @
	 */
	@Transactional
	public Integer staffMembersCommit(final StaffMembersCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(insList -> {
				insList.setCreateUserId(commitBean.getCreateUserId());
				omtsmService.getOmtsam(insList,null,"Inserting");
			});
			liReturn = oiuimageRepository.staffMembersInsertStaffMembers(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(updList -> {
				updList.setModifyUserId(commitBean.getCreateUserId());
				StaffMembers staffMem = oumuRepository.triggerStaffExceQuery(updList.getStaffId());
				omtsmService.getOmtsam(updList,staffMem,"Updating");
			});
			liReturn = oiuimageRepository.staffMembersUpdateStaffMembers(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(obj -> {
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oiuimageRepository.staffMembersDeleteStaffMembers(commitBean.getDeleteList());
			for (StaffMembers bean : commitBean.getDeleteList()) {
				try {
					// trigger call
					staffMembersT1Service.StaffMembersT1Trigger(bean.getStaffId());
				} catch (CustomException e) {
					e.printStackTrace();
				}
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<Persons> personsExecuteQuery(final Persons searchRecord) {
		return oiuimageRepository.personsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPERSONS
	 *
	 * @
	 */
	@Transactional
	public Integer personsCommit(final PersonsCommitBean commitBean) {
		final Integer liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<OmsModules> rgReportRecordGroup() {
		return oiuimageRepository.rgReportRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgImageViewTypeRecordGroup() {
		return oiuimageRepository.rgImageViewTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgDummyImageViewTypeRecordGroup() {
		return oiuimageRepository.rgDummyImageViewTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgImagePropertiesRecordGroup() {
		return oiuimageRepository.rgImagePropertiesRecordGroup();

	}

	@Override
	public List<ReferenceCodes> getImageOicCodeDescription() {
		return oiuimageRepository.getImageOicCodeDescription();
	}

	public byte[] resizeImage(byte[] img, int width, int height) {
		double scale;
		Integer newWidth = 0;
		Integer newHeight = 0;

		try {
			final Blob imgBlob = new SerialBlob(img);
			final InputStream iImage = imgBlob.getBinaryStream();
			final BufferedImage bImage = ImageIO.read(iImage);
			final double xScale = (double) bImage.getWidth() / (double) width;
			final double yScale = (double) bImage.getWidth() / (double) height;
			if (xScale < yScale) {
				scale = xScale;
			} else {
				scale = yScale;
			}
			newWidth = (int) (bImage.getWidth() / scale);
			newHeight = (int) (bImage.getHeight() / scale);
		} catch (Exception e) {
			logger.error("resizeImage", e);
		}
		final ImageIcon imageIcon = new ImageIcon(img);
		final BufferedImage bufferImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2d = (Graphics2D) bufferImage.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(imageIcon.getImage(), 0, 0, newWidth, newHeight, null);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bufferImage, "jpeg", baos);
			final byte[] byteArray = baos.toByteArray();
			return byteArray;
		} catch (Exception e) {
			logger.error("resizeImage", e);
		}
		return null;
	}

	public Long getNextImageId() {
		return oiuimageRepository.getNextImageId();
	}

	@Override
	public String rgGetCode(final String code) {
		return oiuimageRepository.rgGetCode(code);
	}

	public String pptyGetDescription(final String code) {

		return oidrpitmRepository.getDescrption(code);

	}

	@Override
	public Integer allowDelete(String userName) {
		return oiuimageRepository.allowDelete(userName);
	}

	@Override
	public Integer checkUserRole(final String moduleName, String userName) {
		return oiuimageRepository.checkUserRole(moduleName, userName);
	}

}
