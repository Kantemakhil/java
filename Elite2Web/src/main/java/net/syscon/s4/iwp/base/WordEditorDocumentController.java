package net.syscon.s4.iwp.base;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.syncfusion.docio.WordDocument;
import com.syncfusion.ej2.spellchecker.DictionaryData;
import com.syncfusion.ej2.spellchecker.SpellChecker;
import com.syncfusion.ej2.wordprocessor.FormatType;
import com.syncfusion.ej2.wordprocessor.MetafileImageParsedEventArgs;
import com.syncfusion.ej2.wordprocessor.MetafileImageParsedEventHandler;
import com.syncfusion.ej2.wordprocessor.WordProcessorHelper;
import com.syncfusion.javahelper.system.collections.generic.ListSupport;
import com.syncfusion.javahelper.system.io.StreamSupport;
import com.syncfusion.javahelper.system.reflection.AssemblySupport;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CustomParameter;
import net.syscon.s4.common.beans.CustomRestrictParameter;
import net.syscon.s4.common.beans.SaveParameter;
import net.syscon.s4.common.beans.SpellCheckJsonData;

@EliteController
public class WordEditorDocumentController {
	public WordEditorDocumentController() throws Exception {
		
		Resource spellCheckResource = new ClassPathResource("spellcheck.json");
		Resource dicResource = new ClassPathResource("customDict.dic");
		Resource dictionaryResource = null;
		Resource affixResource = null;
	String jsonContent = new String(Files.readAllBytes(spellCheckResource.getFile().toPath()), StandardCharsets.UTF_8);
	JsonArray spellDictionaryItems = new Gson().fromJson(jsonContent, JsonArray.class);
	String personalDictPath = dicResource.getFile().getPath();
	List<DictionaryData> spellDictionary = new ArrayList<DictionaryData>();
	for(int i = 0; i < spellDictionaryItems.size(); i++) {
	    JsonObject spellCheckerInfo = spellDictionaryItems.get(i).getAsJsonObject();
	    DictionaryData dict = new DictionaryData();
	    
	    if(spellCheckerInfo.has("LanguadeID")) 
	    	dict.setLanguadeID(spellCheckerInfo.get("LanguadeID").getAsInt());
	    if(spellCheckerInfo.has("DictionaryPath")) 
	    	 dictionaryResource = new ClassPathResource(spellCheckerInfo.get("DictionaryPath").getAsString());
	    	dict.setDictionaryPath(dictionaryResource.getFile().getPath());
	    if(spellCheckerInfo.has("AffixPath")) 
	    	affixResource=new ClassPathResource(spellCheckerInfo.get("AffixPath").getAsString());
	    	dict.setAffixPath(affixResource.getFile().getPath());
	    spellDictionary.add(dict);
	}
	SpellChecker.initializeDictionaries(spellDictionary, personalDictPath, 3);
	}

	@RequestMapping(value = "/documentEditor/test", method = RequestMethod.POST)
	public String test() {
		System.out.println("==== in test ====");
		return "{\"sections\":[{\"blocks\":[{\"inlines\":[{\"text\":\"Hello World\"}]}]}]}";
	}
	@RequestMapping(value = "/documentEditor/Import", method = RequestMethod.POST)
	public String importFile(@RequestParam("files") MultipartFile file) throws Exception {
		try {	
			String name = file.getOriginalFilename();
			if (name == null || name.isEmpty()) {
				name = "Document1.docx";
			}
			String format = retrieveFileType(name);
			
			MetafileImageParsedEventHandler metafileImageParsedEvent = new MetafileImageParsedEventHandler() {

	            ListSupport<MetafileImageParsedEventHandler> delegateList = new ListSupport<MetafileImageParsedEventHandler>(
	                    MetafileImageParsedEventHandler.class);

	            // Represents event handling for MetafileImageParsedEventHandlerCollection.
	            public void invoke(Object sender, MetafileImageParsedEventArgs args) throws Exception {
	            	onMetafileImageParsed(sender, args);
	            }

	            // Represents the method that handles MetafileImageParsed event.
	            public void dynamicInvoke(Object... args) throws Exception {
	            	onMetafileImageParsed((Object) args[0], (MetafileImageParsedEventArgs) args[1]);
	            }

	            // Represents the method that handles MetafileImageParsed event to add collection item.
	            public void add(MetafileImageParsedEventHandler delegate) throws Exception {
	                if (delegate != null)
	                    delegateList.add(delegate);
	            }

	            // Represents the method that handles MetafileImageParsed event to remove collection
	            // item.
	            public void remove(MetafileImageParsedEventHandler delegate) throws Exception {
	                if (delegate != null)
	                    delegateList.remove(delegate);
	            }
	        };
	        // Hooks MetafileImageParsed event.
	        WordProcessorHelper.MetafileImageParsed.add("OnMetafileImageParsed", metafileImageParsedEvent);
	        // Converts DocIO DOM to SFDT DOM.
	        String sfdtContent = WordProcessorHelper.load(file.getInputStream(), getFormatType(format));
	        // Unhooks MetafileImageParsed event.
	        WordProcessorHelper.MetafileImageParsed.remove("OnMetafileImageParsed", metafileImageParsedEvent);
	        return sfdtContent;
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"sections\":[{\"blocks\":[{\"inlines\":[{\"text\":" + e.getMessage() + "}]}]}]}";
		}
	}
	
	// Converts Metafile to raster image.
	private static void onMetafileImageParsed(Object sender, MetafileImageParsedEventArgs args) throws Exception {
	    // You can write your own method definition for converting Metafile to raster image using any third-party image converter.
	    args.setImageStream(convertMetafileToRasterImage(args.getMetafileStream()));
	}
	
	private static StreamSupport convertMetafileToRasterImage(StreamSupport ImageStream) throws Exception {
        //Here we are loading a default raster image as fallback.
		StreamSupport imgStream = getManifestResourceStream("ImageNotFound.jpg");
        return imgStream;
        //To do : Write your own logic for converting Metafile to raster image using any third-party image converter(Syncfusion doesn't provide any image converter).
    }

    private static StreamSupport getManifestResourceStream(String fileName) throws Exception {
    	AssemblySupport assembly = AssemblySupport.getExecutingAssembly();
        return assembly.getManifestResourceStream("ImageNotFound.jpg");
    }
	
   
	@RequestMapping(value = "/documentEditor/SpellCheck", method = RequestMethod.POST)
	public String spellCheck(@RequestBody SpellCheckJsonData spellChecker) throws Exception {
		try {
			   SpellChecker spellCheck = new SpellChecker();
               String data = spellCheck.getSuggestions(spellChecker.getLanguageID(), spellChecker.getTexttoCheck(), spellChecker.isCheckSpelling(), spellChecker.isCheckSuggestion(), spellChecker.isAddWord());
              return data;
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"SpellCollection\":[],\"HasSpellingError\":false,\"Suggestions\":null}";
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	 @RequestMapping(value = "/documentEditor/SpellCheckByPage", method = RequestMethod.POST)
	public String spellCheckByPage(@RequestBody SpellCheckJsonData spellChecker) throws Exception {
		try {
			   SpellChecker spellCheck = new SpellChecker();
               String data = spellCheck.checkSpelling(spellChecker.getLanguageID(), spellChecker.getTexttoCheck());
              return data;
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"SpellCollection\":[],\"HasSpellingError\":false,\"Suggestions\":null}";
		}
	}

	@RequestMapping(value = "/documentEditor/RestrictEditing", method = RequestMethod.POST)
	public String[] restrictEditing(@RequestBody CustomRestrictParameter param) throws Exception {
		if (param.passwordBase64 == "" && param.passwordBase64 == null)
			return null;
		return WordProcessorHelper.computeHash(param.passwordBase64, param.saltBase64, param.spinCount);
	}

	@RequestMapping(value = "/documentEditor/SystemClipboard", method = RequestMethod.POST)
	public String systemClipboard(@RequestBody CustomParameter param) {
		if (param.content != null && param.content != "") {
			try {
				MetafileImageParsedEventHandler metafileImageParsedEvent = new MetafileImageParsedEventHandler() {

					ListSupport<MetafileImageParsedEventHandler> delegateList = new ListSupport<MetafileImageParsedEventHandler>(
							MetafileImageParsedEventHandler.class);
	
					// Represents event handling for MetafileImageParsedEventHandlerCollection.
					public void invoke(Object sender, MetafileImageParsedEventArgs args) throws Exception {
						onMetafileImageParsed(sender, args);
					}
	
					// Represents the method that handles MetafileImageParsed event.
					public void dynamicInvoke(Object... args) throws Exception {
						onMetafileImageParsed((Object) args[0], (MetafileImageParsedEventArgs) args[1]);
					}
	
					// Represents the method that handles MetafileImageParsed event to add collection item.
					public void add(MetafileImageParsedEventHandler delegate) throws Exception {
						if (delegate != null)
							delegateList.add(delegate);
					}
	
					// Represents the method that handles MetafileImageParsed event to remove collection
					// item.
					public void remove(MetafileImageParsedEventHandler delegate) throws Exception {
						if (delegate != null)
							delegateList.remove(delegate);
					}
				};
				// Hooks MetafileImageParsed event.
				WordProcessorHelper.MetafileImageParsed.add("OnMetafileImageParsed", metafileImageParsedEvent);
				String json = WordProcessorHelper.loadString(param.content, getFormatType(param.type.toLowerCase()));
				// Unhooks MetafileImageParsed event.
				WordProcessorHelper.MetafileImageParsed.remove("OnMetafileImageParsed", metafileImageParsedEvent);
				return json;
			} catch (Exception e) {
				return "";
			}
		}
		return "";
	}
	@RequestMapping(value = "/documentEditor/Save", method = RequestMethod.POST)
	public void save(@RequestBody SaveParameter data) throws Exception {
		try {
			String name = data.getFileName();
			String format = retrieveFileType(name);
			if (name == null || name.isEmpty()) {
				name = "Document1.docx";
			}
			WordDocument document = WordProcessorHelper.save(data.getContent());
			FileOutputStream fileStream = new FileOutputStream(name);
			document.save(fileStream, getWFormatType(format));
			fileStream.close();
            document.close();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/documentEditor/ExportSFDT", method = RequestMethod.POST)
	public ResponseEntity<Resource> exportSFDT(@RequestBody SaveParameter data) throws Exception {
		try {
			String name = data.getFileName();
			if (name == null || name.isEmpty()) {
				name = "Document1.docx";
			}
			String format = retrieveFileType(name);

			WordDocument document = WordProcessorHelper.save(data.getContent());
			return saveDocument(document, format);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	@RequestMapping(value = "/documentEditor/Export", method = RequestMethod.POST)
	public ResponseEntity<Resource> export(@RequestParam("data") MultipartFile data, String fileName) throws Exception {
		try {
			String name = fileName;
			if (name == null || name.isEmpty()) {
				name = "Document1";
			}
			String format = retrieveFileType(name);

			WordDocument document = new WordDocument(data.getInputStream(), com.syncfusion.docio.FormatType.Docx);
			return saveDocument(document, format);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	private ResponseEntity<Resource> saveDocument(WordDocument document, String format) throws Exception {
		String contentType = "";
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		com.syncfusion.docio.FormatType type = getWFormatType(format);
		switch (type.toString()) {
		case "Rtf":
			contentType = "application/rtf";
			break;
		case "WordML":
			contentType = "application/xml";
			break;
		case "Dotx":
			contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
			break;
		case "Docx":
            contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            break;
		case "Html":
			contentType = "application/html";
			break;
		}
		document.save(outStream, type);
		ByteArrayResource resource = new ByteArrayResource(outStream.toByteArray());
		outStream.close();
		document.close();
		
		return ResponseEntity.ok().contentLength(resource.contentLength())
				.contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

	private String retrieveFileType(String name) {
		int index = name.lastIndexOf('.');
		String format = index > -1 && index < name.length() - 1 ? name.substring(index) : ".docx";
		return format;
	}

	static com.syncfusion.docio.FormatType getWFormatType(String format) throws Exception {
		if (format == null || format.trim().isEmpty())
			throw new Exception("EJ2 WordProcessor does not support this file format.");
		switch (format.toLowerCase()) {
		case ".dotx":
			return com.syncfusion.docio.FormatType.Dotx;
		case ".docm":
			return com.syncfusion.docio.FormatType.Docm;
		case ".dotm":
			return com.syncfusion.docio.FormatType.Dotm;
		case ".docx":
			return com.syncfusion.docio.FormatType.Docx;
		case ".rtf":
			return com.syncfusion.docio.FormatType.Rtf;
		case ".html":
			return com.syncfusion.docio.FormatType.Html;
		case ".txt":
			return com.syncfusion.docio.FormatType.Txt;
		case ".xml":
			return com.syncfusion.docio.FormatType.WordML;
		default:
			throw new Exception("EJ2 WordProcessor does not support this file format.");
		}
	}
	
	static FormatType getFormatType(String format)
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
}