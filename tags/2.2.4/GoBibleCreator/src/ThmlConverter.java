//
//  ThmlConverter.java
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

public class ThmlConverter extends GoBibleCreator
{
	/** Tag to identify ThML XML data. **/
	public final static String THML_TAG = "ThML";

	/** ThML tag that contains all of the testaments. **/
	public final static String THML_BODY_TAG = "ThML.body";

	/** ThML tag that contains a testament (New Testament, Old Testament, Aprocrypha, etc). **/
	public final static String TESTAMENT_TAG = "div1";
	
	/** ThML tag that contains a book (Psalms, Mark, etc). **/
	public final static String BOOK_TAG = "div2";

	/** ThML attribute within the BOOK_TAG that contains the book name. **/
	public final static String BOOK_NAME_ATTRIBUTE = "title";
	
	/** ThML attribute within the BOOK_TAG that contains the short book name. 
		It is assumed that the contents of this attribute will be Latin and 
		will be converted to US-ASCII. **/
	public final static String BOOK_SHORT_NAME_ATTRIBUTE = "id";

	/** ThML tag that contains one chapter. **/
	public final static String CHAPTER_TAG = "div3";

	/** ThML attribute within the CHAPTER_TAG that contains the chapter number (eg "Chapter 1"). **/
	public final static String CHAPTER_NUMBER_ATTRIBUTE = "title";

	/** All verses appear within the paragraph tag and are separated by the VERSE_TAG. **/
	public final static String PARAGRAPH_TAG = "p";	
	
	/** ThML tag that separates two scriptures. **/
	public final static String VERSE_TAG = "scripture";

	/** ThML tag used to change the colour of the text to red. **/
	public final static String RED_TAG = "span";
	
	/** Style changes are written out as flags in a single byte. **/
	public final static char STYLE_RED = 1;

	public HashMap parse(XMLObject thml)
	{
		// Call the GoBibleCreator parseBooks method with the 
		// ThML specific tags
		
		// Extract the books from the ThML indexed by book name
		return parseBooks(thml, THML_BODY_TAG, TESTAMENT_TAG, BOOK_TAG, BOOK_NAME_ATTRIBUTE, BOOK_SHORT_NAME_ATTRIBUTE);
	}
	
	public boolean isChapter(XMLObject xml)
	{
		return xml.getTag().equals(CHAPTER_TAG);
	}
	
	/**
	 * ThML chapter titles are assumed to end with a space then the chapter number.
	 **/
	public int getChapterNumber(XMLObject xml)
	{
		String title = xml.getAttribute(CHAPTER_NUMBER_ATTRIBUTE, "*Error no " + CHAPTER_NUMBER_ATTRIBUTE + " attribute*");
		
		// Find the last space
		int spaceIndex = title.lastIndexOf(' ');
		
		return Integer.parseInt(title.substring(spaceIndex + 1));
	}
	
	public void parseChapter(XMLObject xml, Chapter chapter)
	{
		//int paragraph = 1;
		
		// Find each paragraph
		for (Enumeration e = xml.getChildren(); e.hasMoreElements(); )
		{
			XMLObject xmlParagraph = (XMLObject) e.nextElement();
			
			//System.out.println("Tag: " + xml.getTag());
			
			// See if it is a paragraph tag
			if (xmlParagraph.getTag().equals(PARAGRAPH_TAG))
			{
				//System.out.println("Parsing paragraph " + paragraph++);
				parseParagraphs(xmlParagraph, chapter);
			}
		}		
	}
	
	private void parseParagraphs(XMLObject paragraph, Chapter chapter)
	{
		String verse = null;
		
		Enumeration e = paragraph.getChildren();
		
		do
		{
			// Get the next xml child
			XMLObject xml = (XMLObject) e.nextElement();
			
			// Test if this is a new verse
			if (xml.getTag().equals(VERSE_TAG))
			{
				// If the last verse isn't null then add it
				if (verse != null)
				{
					// Before adding the verse trim and replace all new line characters with spaces
					String verseString = trim(verse).replace('\n', ' ');

					// Convert HTML ampersand characters if the verse data contains one
					if (verseString.indexOf('&') >= 0)
					{
						verseString = convertAmpersands(verseString);
					}

					//System.out.println(verseString);
					chapter.verses.addElement(verseString);
					chapter.allVerses.append(verseString);
				}
				
				// Re-initialise the verse
				verse = "";
			}
			else if (verse != null)
			{
				if (xml instanceof CDATA)
				{
					// This xml object is actually CDATA so append it to the verse
					verse += xml.getTag();
				}
				// Only extract the CDATA if this tag isn't a 'sup' tag which merely indicates the verse number
				else if (!xml.getTag().equals("sup"))
				{
					String newVerseData = extractCDATA(xml);

					// Only add the new verse data if it is non-empty
					if (newVerseData.length() > 0)
					{
						// If the tag is a span tag then we want to indicate that
						// Christ's words are in red
						if (xml.getTag().equals(RED_TAG))
						{
							verse += STYLE_RED + newVerseData + STYLE_RED;
						}
						else
						{
							verse += newVerseData;
						}
					}
				}
			}
		}
		while(e.hasMoreElements());
		
		// If there was a last verse then add it
		if (verse != null && !verse.equals(""))
		{
			// Before adding the verse trim and replace all new line characters with spaces
			String verseString = trim(verse).replace('\n', ' ');
			
			// Convert HTML ampersand characters if the verse data contains one
			if (verseString.indexOf('&') >= 0)
			{
				verseString = convertAmpersands(verseString);
			}
			
			chapter.verses.addElement(verseString);		
			chapter.allVerses.append(verseString);	
		}
	}
}
