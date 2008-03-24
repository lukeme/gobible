//
//  BibleSource.java
//  GoBible
//
//  Created by Jolon Faichney on Thu Jul 24 2003.
//  Copyright (c) 2003. All rights reserved.
//

import java.io.*;

public abstract class BibleSource 
{
	/**
	 * Returns the entire contents of the chapter as one string.
	 * Use getChapterIndex to get the indices of each verse in the chapter string.
	 */
	abstract public char[] getChapter(int bookIndex, int chapterIndex) throws IOException;
	
	/**
	 * Returns the size of the current verse data in chars. This should be used
	 * instead of the length of the array returned by getChapter() because the
	 * array returned by getChapter() may be larger than the actual verse data.
	 */
	abstract public int getVerseDataSize();
	
	/**
	 * Returns an index into the specified chapter. Two integers are
	 * used for every verse in the chapter. The first integer is the
	 * offset in characters to the verse and the second integer
	 * is the offset to the end of the verse in characters.
	 */
	abstract public int[] getChapterIndex(int bookIndex, int chapterIndex) throws IOException;
	
	/**
	 * Most books will start at Chapter 1 but some may have been split up
	 * so their chapters may start at larger numbers. Either way this
	 * method can be used to convert a chapter index that starts at
	 * zero to the proper chapter number.
	 */
	abstract public int getStartChapter(int bookIndex);
	
	abstract public String[] getBookNames();
	
	abstract public String getBookName(int bookIndex);
	
	abstract public int getNumberOfBooks();
	
	abstract public int getNumberOfChapters(int bookIndex);
	
	abstract public int getNumberOfVerses(int bookIndex, int chapterIndex);
	
	public String getReferenceString(int bookIndex, int chapterIndex, int verseIndex)
	{
		return (chapterIndex + getStartChapter(bookIndex)) + ":" + (verseIndex + 1);
	}
}
