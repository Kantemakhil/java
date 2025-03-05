package net.syscon.s4.common;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.words.FindReplaceOptions;
import com.aspose.words.PdfCompliance;
import com.aspose.words.PdfSaveOptions;
import com.syncfusion.docio.WordDocument;
import com.syncfusion.ej2.wordprocessor.FormatType;
import com.syncfusion.ej2.wordprocessor.WordProcessorHelper;

import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.iwp.base.ManageDocService;

@EliteController
public class WordEditorController {

	@Autowired
	ManageDocService manageDocService;
	
	@Autowired
	private DocManageUtilities docManageUtilities;
	
	@PostMapping("/iwp/wordeditor/Import")
	public String uploadFile(@RequestParam("files") MultipartFile file) throws Exception {
		try {
			return WordProcessorHelper.load(file.getInputStream(), FormatType.Docx,false);
		} catch (Exception e) {
			// e.printStackTrace();
			return "{\"sections\":[{\"blocks\":[{\"inlines\":[{\"text\":\"\"}]}]}]}";
		}
	}

	/*@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/api/wordeditor/SystemClipboard")
	public String systemClipboard(@RequestBody CustomParameter param) {
		if (param.content != null && param.content != "") {
			try {
				return  WordProcessorHelper.loadString(param.content, GetFormatType(param.type.toLowerCase()));
			} catch (Exception e) {
				return "";
			}
		}
		return "";
	}
	*/
	static FormatType GetFormatType(String format)
    {
        switch (format)
        {
            case ".dotx":
            case ".docx":
            case ".docm":
            case ".dotm":
                return FormatType.Docx;
            case ".dot":
            case ".doc":
                return FormatType.Doc;
            case ".rtf":
                return FormatType.Rtf;
            case ".txt":
                return FormatType.Txt;
            case ".xml":
                return FormatType.WordML;
            case ".html":
                return FormatType.Html;
            default:
                return FormatType.Docx;
        }
    }
	
	@GetMapping("/iwp/convertToPdf")
	public ResponseEntity exportpdf(@RequestParam("uri") String uri, HttpServletRequest httpServletRequest) throws Exception {
		
		HttpHeaders headerRes = docManageUtilities.getHttpHeadersForFileUpload("application/octet-stream", null, false);
		String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		com.aspose.words.Document doc = null;
		ByteArrayOutputStream outStreamDocx = new ByteArrayOutputStream();
		ByteArrayOutputStream outStreamPdf = new ByteArrayOutputStream();
		byte[] outFile = null;
		try {
			IwpDocuments iwpDocuments = manageDocService.viewDocument(new BigDecimal(uri), savedLocation);
			String documentSfdt = new String(iwpDocuments.getDocumentContent());
			WordDocument wordDocument = WordProcessorHelper.save(documentSfdt);
			wordDocument.save(outStreamDocx, com.syncfusion.docio.FormatType.Docx);	
			doc = new com.aspose.words.Document(new ByteArrayInputStream(outStreamDocx.toByteArray()), null);
			//doc.getRange().replace( "Created with a trial version of Syncfusion Essential" , "", new FindReplaceOptions());
			//doc.getRange().replace( "Created with a trial version of Syncfusion Essential DocIO." , "", new FindReplaceOptions());
			//doc.getRange().replace( "Evaluation Only. Created with Aspose.PDF. Copyright 2002-2019 Aspose Pty Ltd." , "", new FindReplaceOptions());
			PdfSaveOptions pso = new PdfSaveOptions();
			pso.setCompliance(PdfCompliance.PDF_15);
			doc.save(outStreamPdf, pso);
			outFile = outStreamPdf.toByteArray();
		} catch (Exception e) {
			return new ResponseEntity<>(outFile, headerRes, HttpStatus.OK);
		}
		return new ResponseEntity<>(outFile, headerRes, HttpStatus.OK);
		
	}
}