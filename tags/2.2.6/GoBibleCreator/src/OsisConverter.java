//
//  OsisConverter.java
//  GoBibleCreator
//
//  Created by Jolon Faichney on Sat Oct 30 2004.
//  For the glory of our Lord Jesus Christ and the furtherance of His Kingdom.
//  This file is placed into the public domain.
//

import java.io.*;
import java.util.*;
import java.util.jar.*;
import jolon.xml.*;

public class OsisConverter extends GoBibleCreator
{
	/** Tag to identify Osis XML data. **/
	public final static String OSIS_TAG = "osis";

	/** OSIS tag that contains all of the testaments. **/
	public final static String OSIS_BODY_TAG = "osisText";

	/** OSIS tag that contains a testament (New Testament, Old Testament, Aprocrypha, etc). **/
	public final static String TESTAMENT_TAG = "div";
	
	/** OSIS tag that contains a book (Psalms, Mark, etc). **/
	public final static String BOOK_TAG = "div";

	/** OSIS attribute within the BOOK_TAG that contains the book name. **/
	public final static String BOOK_NAME_ATTRIBUTE = "osisID";
	
	/** OSIS attribute within the BOOK_TAG that contains the short book name. 
		It is assumed that the contents of this attribute will be Latin and 
		will be converted to US-ASCII. **/
	public final static String BOOK_SHORT_NAME_ATTRIBUTE = "osisID";

	/** OSIS tag that contains one chapter. **/
	public final static String CHAPTER_TAG_CHAPTER = "chapter";
	public final static String CHAPTER_TAG_DIV = "div";

	/** OSIS attribute within the CHAPTER_TAG that contains the chapter number (eg "Gen.1"). **/
	public final static String CHAPTER_NUMBER_ATTRIBUTE = "osisID";

	/** Verse data is contained within the verse tag. **/
	public final static String VERSE_TAG = "verse";

	public HashMap parse(XMLObject osis)
	{
		// Call the GoBibleCreator convert method with the 
		// OSIS specific tags
		
		// Extract the books from the OSIS indexed by book name
		return parseBooks(osis, OSIS_BODY_TAG, TESTAMENT_TAG, BOOK_TAG, /*BOOK_NAME_ATTRIBUTE,*/ BOOK_SHORT_NAME_ATTRIBUTE, null);
	}
	
	/**
	 * OSIS chapters may be inside either a "chapter" tag or a "div" tag.
	 **/
	public boolean isChapter(XMLObject xml)
	{
		String tag = xml.getTag();
		return tag.equals(CHAPTER_TAG_CHAPTER) || tag.equals(CHAPTER_TAG_DIV);
	}

	/**
	 * OSIS chapter titles are assumed to end with a period then the chapter number.
	 **/
	public int getChapterNumber(XMLObject xml)
	{
		String title = xml.getAttribute(CHAPTER_NUMBER_ATTRIBUTE, "*Error no " + CHAPTER_NUMBER_ATTRIBUTE + " attribute*");
		
		// Find the last space
		int spaceIndex = title.lastIndexOf('.');
		
		return Integer.parseInt(title.substring(spaceIndex + 1));
	}

	public void parseChapter(XMLObject xml, Chapter chapter)
	{
		//int verse = 1;
		
		// Find each verse
		for (Enumeration e = xml.getChildren(); e.hasMoreElements(); )
		{
			XMLObject xmlVerse = (XMLObject) e.nextElement();
			
			//System.out.println("Tag: " + xml.getTag());
			
			// See if it is a verse tag
			if (xmlVerse.getTag().equals(VERSE_TAG))
			{
				//System.out.println("Parsing verse " + verse++);
				
				// Extract verse data and add verse
				String verseString = extractCDATA(xmlVerse);
				
				chapter.verses.addElement(verseString);		
				chapter.allVerses.append(verseString);
			}
		}		
	}

}

