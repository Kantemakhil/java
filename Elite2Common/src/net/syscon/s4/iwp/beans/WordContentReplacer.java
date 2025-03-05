package net.syscon.s4.iwp.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
 
public class WordContentReplacer {

	public XWPFDocument Replace(String documentPath, Map<String,List<String>> bookmarkValueMap) {

		try {
			File file = new File(documentPath);
			InputStream inputStream = new FileInputStream(file);
			XWPFDocument document = new XWPFDocument(inputStream);
			insertBookmarkTexts(file.getName(), document , bookmarkValueMap);
			return document;

		} catch (Exception exception) {
			return null;
		}
	}
	
	public HWPFDocument ReplaceDotDocument(String documentPath, Map<String,String> bookmarkValueMap) {

		try {
			File file = new File(documentPath);
			InputStream inputStream = new FileInputStream(file);
			HWPFDocument document = new HWPFDocument(inputStream);
			insertBookmarkTexts(file.getName(), document , bookmarkValueMap);
			return document;

		} catch (Exception exception) {
			return null;
		}
	}
	

	private void insertBookmarkTexts(String documentName, XWPFDocument document, Map<String,List<String>> bookmarkValueMap) {

		Map<String, List<String>> contentMap = bookmarkValueMap;

		Bookmarks bookmarks = new Bookmarks(document);
		Map<String, Bookmark> bookmarkMap = bookmarks.getBookmarkMap();

		Iterator<Entry<String, Bookmark>> iterator = bookmarkMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Bookmark> kvPair = iterator.next();
			String bookmarkName = kvPair.getKey();
			Bookmark bookmark = kvPair.getValue();

			if (contentMap.containsKey(bookmarkName)) {
				List<String> replacementText = contentMap.get(bookmarkName);
				//TODO correct the logic
				//bookmark.insertTextAtBookmark(replacementText, Bookmark.REPLACE);
			}
		}
	}
	
	private void insertBookmarkTexts(String documentName, HWPFDocument dotDocument, Map<String,String> bookmarkValueMap) {

		Map<String, String> contentMap = bookmarkValueMap;
		org.apache.poi.hwpf.usermodel.Bookmarks bookmarks =  dotDocument.getBookmarks();
		
		for (int i = 0; i < bookmarks.getBookmarksCount(); i++) {
			final Range range = new Range(bookmarks.getBookmark(i).getStart(), bookmarks.getBookmark(i).getEnd(),
					dotDocument);
			
			String bookmarkName = bookmarks.getBookmark(i).getName();
			
			if (contentMap.containsKey(bookmarkName))
				if (range.text().length() > 0){
					range.replaceText(contentMap.get(bookmarkName), false);
				}
				
		}
		
	}
	
	
	
}
