//
//  SearchingCanvas.java
//  GoBible
//
//  Created by Jolon Faichney on Mon Jul 28 2003.
//  Copyright (c) 2003. All rights reserved.
//

import java.util.*;
import javax.microedition.lcdui.*;

public class SearchingCanvas extends Canvas implements CommandListener, Runnable
{
	private final static int EXTRA_DISPLAY_CHARS = 10;
	private final static int MAX_RESULTS = 200;

	private GoBible goBible;
	private boolean stop;
	private int currentChapter, currentBook;
	private int searchFromBookIndex, searchToBookIndex;
	private String searchString;
	
	public SearchingCanvas(GoBible goBible, final int fromBookIndex, final int toBookIndex, final String searchString)
	{
		this.goBible = goBible;
		
		goBible.searchResults.removeAllElements();
		
		this.searchFromBookIndex = fromBookIndex;
		this.searchToBookIndex = toBookIndex;
		this.searchString = searchString;
		
		addCommand(new Command(GoBible.getString("UI-Stop"), Command.STOP, 0));
		setCommandListener(this);

		currentBook = fromBookIndex;
		currentChapter = 0;
			
		// Spawn a search thread
		new Thread(this).start();
	}
	
	public void commandAction(Command command, Displayable display)
	{
		switch (command.getCommandType())
		{
			case Command.STOP:
			{
				stop = true;
				
				// Show search results screen
				goBible.display.setCurrent(new SearchResultsList(goBible, goBible.searchResults));
				break;
			}
		}
	}
	
	/**
	 * This search method is called from a thread created in the constructor.
	 * Searches all books from fromBookIndex to toBookIndex.
	 */
	public void run()
	{
		//searchString = searchString.toLowerCase();
		char[] searchData = searchString.toLowerCase().toCharArray();
	
		// Search each book
		int endBook = searchToBookIndex;
		
		int numberOfBooks = goBible.bibleSource.getNumberOfBooks();
		
		int numberFound = 0;
		
		// We use a different variable to count through the books since
		// currentBook is also used in paint and the index can end up
		// past the last book due to the bookIndex++ in the for loop before
		// the bookIndex != endBook + 1 condition. currentBook is assigned to
		// bookIndex within the loop ensuring that it is always valid.
		int bookIndex = currentBook;
		
		for (; (bookIndex != endBook + 1) && (!stop) && (numberFound < MAX_RESULTS); bookIndex++)
		{
			// Check if bookIndex is greater than the number of books, 
			// if so then wrap back to the beginning.
			// eg. if searching from Jude to Exodus.
			if (bookIndex >= numberOfBooks)
			{
				bookIndex = 0;
			}
		
			currentBook = bookIndex;
			currentChapter = 0;

			// Repaint the screen for every book
			repaint();
			
			// Search each chapter
			int endChapter = goBible.bibleSource.getNumberOfChapters(currentBook) - 1;

			for (; (currentChapter <= endChapter) && (!stop) && (numberFound < MAX_RESULTS); currentChapter++)
			{
				// Repaint the screen for every chapter
				//repaint();
				//serviceRepaints();
			
				// Grab the chapter data and look for verses that contain the search text
				try
				{
					char[] verseData = goBible.bibleSource.getChapter(currentBook, currentChapter);
					int[] verseIndex = goBible.bibleSource.getChapterIndex(currentBook, currentChapter);
					
					//String verseString = new String(verseData).toLowerCase();
					
					int numberOfVerses = goBible.bibleSource.getNumberOfVerses(currentBook, currentChapter);
					
					int index = 0;
					int end = goBible.bibleSource.getVerseDataSize();
					
					// Stop when we have searched all verses or when no search string was found
					for (int currentVerse = 0; (currentVerse < numberOfVerses) && (index >= 0) && (!stop) && (numberFound < MAX_RESULTS); currentVerse++)
					{
						int verseStart = verseIndex[currentVerse << 1];
						int verseEnd = verseIndex[(currentVerse << 1) + 1];
						
						// If the next found instance was in a previous verse then look 
						// for it in this one
						if ((index < verseStart) || (index == 0))
						{
							//index = verseString.indexOf(searchString, verseStart);
							index = find(verseData, verseStart, end, searchData);
						}
							
						if ((index >= 0) && (index < verseEnd))
						{
							// We want to extract 10 characters before and after the 
							// word. However, we also want to know whether how many
							// characters we are missing from the verse before
							// and after the extra 10 characters. If one side
							// of the search result doesn't use all 10 characters
							// eg. it is at the end of the verse, then we can
							// use these values to shift characters over to the
							// other side.
							
							int extraCharsBefore = (index - verseStart) - EXTRA_DISPLAY_CHARS;
							int extraCharsAfter = verseEnd - (index + searchData.length + EXTRA_DISPLAY_CHARS);
							
							int beforeIndex = extraCharsBefore >= 0 ? index - EXTRA_DISPLAY_CHARS : verseStart;
							int afterIndex = extraCharsAfter >= 0 ? index + searchData.length + EXTRA_DISPLAY_CHARS : verseEnd;
							
							// If there are spare characters which weren't used after
							// then use them before if they are needed
							if (extraCharsAfter < 0 && beforeIndex > verseStart)
							{
								beforeIndex += extraCharsAfter;
								beforeIndex = beforeIndex < verseStart ? verseStart : beforeIndex;
							}

							// If there are spare characters which weren't used before
							// then use them after if they are needed
							if (extraCharsBefore < 0 && afterIndex < verseEnd)
							{
								afterIndex -= extraCharsBefore;
								afterIndex = afterIndex > verseEnd ? verseEnd : afterIndex;
							}							
							
							// If the first character is a letter then decrement the 
							// beforeIndex until a non-letter is found, this ensures
							// that the extracted doesn't start with half a word
							if (isLetter(verseData[beforeIndex]))
							{
								while (beforeIndex > verseStart && isLetter(verseData[beforeIndex - 1]))
								{
									beforeIndex--;
								}
							}

							// If the last character is a letter then increment the 
							// afterIndex until a non-letter is found, this ensures
							// that the extracted doesn't end with half a word
							if (isLetter(verseData[afterIndex - 1]))
							{
								while (afterIndex < verseEnd && isLetter(verseData[afterIndex]))
								{
									afterIndex++;
								}
							}
							
							// Add verse to search results replacing style red character with a space
							goBible.searchResults.addElement(new Object[]{new Integer((currentBook << 16) | (currentChapter << 8) | (currentVerse)), new String(verseData, beforeIndex, afterIndex - beforeIndex).replace('\u0001', ' ')});
							
							numberFound++;
							
							// Repaint screen when a new verse has been found
							repaint();
							serviceRepaints();
							
							// Look for next instance of verse
							//index = verseString.indexOf(searchString, index + 1);
							index = find(verseData, index + 1, end, searchData);
						}
					}
				}
				catch (Exception e)
				{
				}
			}
		}
		
		if (goBible.searchResults.size() > 0)
		{
			// Show search results screen
			goBible.display.setCurrent(new SearchResultsList(goBible, goBible.searchResults));
		}
		else
		{
			// Revert to the previously displayed verse
			goBible.bibleCanvas.enterLoadingMode();
			goBible.display.setCurrent(goBible.bibleCanvas);
			goBible.bibleCanvas.repaint();
			goBible.loadCurrentChapter();
			goBible.showMainScreen();
		}
	}
	
	private final static boolean isLetter(char c)
	{
		return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
	}
	
	/**
	 * Finds the first occurance of the searchData string within
	 * the data string starting at the offset specified.
	 */
	private final static int find(char[] data, int offset, int end, char[] searchData)
	{
		int result = -1;
		int searchStringLength = searchData.length;
		int totalEnd = end - searchStringLength;
		char lowerDiff = (char) ('a' - 'A');
		
		for (int i = offset; i < totalEnd && result == -1; i++)
		{
			char currentChar = data[i];
			
			// Convert to lower case
			if (currentChar >= 'A' && currentChar <= 'Z')
			{
				currentChar += lowerDiff;
			}
			
			// Quickly test the first character, if it doesn't match then we
			// can quickly move onto the next one
			if (currentChar == searchData[0])
			{
				boolean matches = true;
				
				for (int j = 1; j < searchStringLength && matches; j++)
				{
					currentChar = data[i + j];
				
					// Convert to lower case
					if (currentChar >= 'A' && currentChar <= 'Z')
					{
						currentChar += lowerDiff;
					}
				
					if (currentChar != searchData[j])
					{
						matches = false;
					}
				}
				
				if (matches)
				{
					result = i;
				}
			}
		}
		
		return result;
	}
	
	public void paint(Graphics g)
	{
		int width = getWidth();
		
		g.setColor(0xffffff);
		g.fillRect(0, 0, width, getHeight());

		Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

		g.setFont(font);
		
		g.setColor(0);
		int halfWidth = width >> 1;
		
		g.drawString(GoBible.getString("UI-Searching") + ":", halfWidth, 20, Graphics.HCENTER | Graphics.TOP);
		
		// Grab the book name
		String bookName = goBible.bibleSource.getBookName(currentBook);
		//String chapterString = " " + (currentChapter + goBible.bibleSource.getStartChapter(goBible.currentBookIndex));
		
		// Grab the width of the book name
		int bookNameWidth = font.stringWidth(bookName);
		int totalWidth = font.stringWidth(bookName/* + chapterString*/);
		
		// Draw the text centred so determine starting position
		int startX = (width - totalWidth) >> 1;
		
		// Draw bookname separately so that it can be drawn reversed if
		// necessary
		BibleCanvas.drawString(g, bookName, startX, 40, Graphics.LEFT | Graphics.TOP);
		
		//g.drawString(chapterString, startX + bookNameWidth, 40, Graphics.LEFT | Graphics.TOP);
		
		g.drawString(GoBible.getString("UI-Found") + " " + goBible.searchResults.size(), halfWidth, 60, Graphics.HCENTER | Graphics.TOP);
		
		g.drawString(GoBible.getString("UI-Please-Wait") + "...", halfWidth, 80, Graphics.HCENTER | Graphics.TOP);
	}
}
