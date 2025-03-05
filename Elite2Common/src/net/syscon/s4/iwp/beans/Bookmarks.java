package net.syscon.s4.iwp.beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.HWPFList;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Bookmarks {

	private HashMap<String, Bookmark> _bookmarks = null;

	public Bookmarks(XWPFDocument document) {

		List<XWPFTable> tableList = null;
		Iterator<XWPFTable> tableIter = null;
		List<XWPFTableRow> rowList = null;
		Iterator<XWPFTableRow> rowIter = null;
		List<XWPFTableCell> cellList = null;
		Iterator<XWPFTableCell> cellIter = null;
		XWPFTable table = null;
		XWPFTableRow row = null;
		XWPFTableCell cell = null;
		_bookmarks = new HashMap<String, Bookmark>();

		storeBookmarksInParagraphs(document.getParagraphs());

		// check to see if there are any bookmarks in table cells. To do this
		// it is necessary to get at the list of paragraphs 'stored' within the
		// individual table cell, hence this code which get the tables from the
		// document, the rows from each table, the cells from each row and the
		// paragraphs from each cell.
		tableList = document.getTables();
		tableIter = tableList.iterator();

		while (tableIter.hasNext()) {
			table = tableIter.next();
			rowList = table.getRows();
			rowIter = rowList.iterator();
			while (rowIter.hasNext()) {
				row = rowIter.next();
				cellList = row.getTableCells();
				cellIter = cellList.iterator();
				while (cellIter.hasNext()) {
					cell = cellIter.next();
					this.procParaList(cell.getParagraphs(), row);
				}
			}
		}
	}

//	public Bookmarks(HWPFDocument document) {
//
//		List<HWPFList> tableList = null;
//		Iterator<HWPFList> tableIter = null;
//		List<HWPF> rowList = null;
//		Iterator<XWPFTableRow> rowIter = null;
//		List<XWPFTableCell> cellList = null;
//		Iterator<XWPFTableCell> cellIter = null;
//		XWPFTable table = null;
//		XWPFTableRow row = null;
//		XWPFTableCell cell = null;
//		_bookmarks = new HashMap<String, Bookmark>();
//
//		storeBookmarksInParagraphs(document.getParagraphs());
//
//		// check to see if there are any bookmarks in table cells. To do this
//		// it is necessary to get at the list of paragraphs 'stored' within the
//		// individual table cell, hence this code which get the tables from the
//		// document, the rows from each table, the cells from each row and the
//		// paragraphs from each cell.
//		tableList = document.getListTables();
//		tableIter = tableList.iterator();
//
//		while (tableIter.hasNext()) {
//			table = tableIter.next();
//			rowList = table.getRows();
//			rowIter = rowList.iterator();
//			while (rowIter.hasNext()) {
//				row = rowIter.next();
//				cellList = row.getTableCells();
//				cellIter = cellList.iterator();
//				while (cellIter.hasNext()) {
//					cell = cellIter.next();
//					this.procParaList(cell.getParagraphs(), row);
//				}
//			}
//		}
//	}
	
	public Bookmark getBookmark(String bookmarkName) {
		Bookmark bookmark = null;
		if (_bookmarks.containsKey(bookmarkName)) {
			bookmark = _bookmarks.get(bookmarkName);
		}
		return (bookmark);
	}

	public HashMap<String, Bookmark> getBookmarkMap() {
		return (_bookmarks);
	}

	public Iterator<String> getNameIterator() {
		return (_bookmarks.keySet().iterator());
	}

	private Set<String> getBookmarkNameSet() {
		return (_bookmarks.keySet());
	}

	private void procParaList(List<XWPFParagraph> paraList, XWPFTableRow tableRow) {

		Iterator<XWPFParagraph> paraIter = null;
		XWPFParagraph para = null;
		List<CTBookmark> bookmarkList = null;
		Iterator<CTBookmark> bookmarkIter = null;
		CTBookmark bookmark = null;
		NamedNodeMap attributes = null;
		Node colFirstNode = null;
		Node colLastNode = null;
		int firstColIndex = 0;
		int lastColIndex = 0;

		// iterate through the list of paragraphs and check whether or not each
		// one contains a bookmark. If it does, create an instance of the
		// Bookmark class encapsulating the bookmark/paragraph pairing and
		// store
		paraIter = paraList.iterator();
		while (paraIter.hasNext()) {
			para = paraIter.next();
			bookmarkList = para.getCTP().getBookmarkStartList();
			bookmarkIter = bookmarkList.iterator();
			while (bookmarkIter.hasNext()) {
				bookmark = bookmarkIter.next();
				// With a bookmark in hand, test to see if the bookmarkStart tag
				// has w:colFirst or w:colLast attributes. If it does, we are
				// dealing with a bookmarked table cell. This will need to be
				// handled differnetly - I think by an different concrete class
				// that implements the Bookmark interface!!
				attributes = bookmark.getDomNode().getAttributes();
				if (attributes != null) {
					// Get the colFirst and colLast attributes. If both - for
					// now - are found, then we are dealing with a bookmarked
					// cell.
					colFirstNode = attributes.getNamedItem("w:colFirst");
					colLastNode = attributes.getNamedItem("w:colLast");
					if (colFirstNode != null && colLastNode != null) {
						// Get the index of the cell (or cells later) from them.
						// First convefrt the String values both return to
						// primitive
						// int value. TO DO, what happens if there is a
						// NumberFormatException.
						firstColIndex = Integer.parseInt(colFirstNode.getNodeValue());
						lastColIndex = Integer.parseInt(colLastNode.getNodeValue());
						// if the indices are equal, then we are dealing with a#
						// cell and can create the bookmark for it.
						if (firstColIndex == lastColIndex) {
							_bookmarks.put(bookmark.getName(),
									new Bookmark(bookmark, para, tableRow.getCell(firstColIndex)));
						} else {
							System.out.println(
									"This bookmark " + bookmark.getName() + " identifies a number of cells in the "
											+ "table. That condition is not handled yet.");
						}
					} else {
						_bookmarks.put(bookmark.getName(), new Bookmark(bookmark, para));
					}
				} else {
					_bookmarks.put(bookmark.getName(), new Bookmark(bookmark, para));
				}
			}
		}
	}

	private void storeBookmarksInParagraphs(List<XWPFParagraph> paragraphs) {

		Iterator<XWPFParagraph> paraIter = null;
		XWPFParagraph para = null;
		List<CTBookmark> bookmarkList = null;
		Iterator<CTBookmark> bookmarkIter = null;
		CTBookmark bookmark = null;

		// Step through the list of paragraphs and check whether or not each
		// one contains a bookmark. If it does, create an instance of the
		// Bookmark class encapsulating the bookmark/paragraph pairing and
		// store
		paraIter = paragraphs.iterator();
		while (paraIter.hasNext()) {
			para = paraIter.next();
			bookmarkList = para.getCTP().getBookmarkStartList();
			bookmarkIter = bookmarkList.iterator();
			while (bookmarkIter.hasNext()) {
				bookmark = bookmarkIter.next();
				_bookmarks.put(bookmark.getName(), new Bookmark(bookmark, para));
			}
		}
	}
}