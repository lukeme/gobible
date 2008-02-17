//
//  BookmarkEntry.java
//  Go Bible 2.0
//
//  Created by Jolon Faichney on 17/03/06.
//  Copyright 2006. All rights reserved.
//

import java.io.*;

public class BookmarkEntry 
{
	int bookIndex, chapterIndex, verseIndex;
	String excerpt;
	
	/**
	 * Create a new bookmark.
	 */
	public BookmarkEntry(int bookIndex, int chapterIndex, int verseIndex, String excerpt)
	{
		this.bookIndex = bookIndex;
		this.chapterIndex = chapterIndex;
		this.verseIndex = verseIndex;
		this.excerpt = excerpt;
	}
	
	/**
	 *  Read in the bookmark from the record store.
	 */
	public BookmarkEntry(DataInputStream input) throws IOException
	{
		bookIndex = input.read();
		chapterIndex = input.read();
		verseIndex = input.read();
		excerpt = input.readUTF();
	}
	
	/**
	 * Write the bookmark to the record store.
	 */
	public void write(DataOutputStream output) throws IOException
	{
		output.writeByte(bookIndex);
		output.writeByte(chapterIndex);
		output.writeByte(verseIndex);
		output.writeUTF(excerpt);
	}
}
