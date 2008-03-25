//
//  SearchResultsList.java
//  GoBible
//
//  Created by Jolon Faichney on Mon Jul 28 2003.
//  Copyright (c) 2003. All rights reserved.
//

import java.util.*;
import javax.microedition.lcdui.*;

public class SearchResultsList extends javax.microedition.lcdui.List implements CommandListener
{
	private GoBible goBible;
	private Vector searchResults;

	public SearchResultsList(GoBible goBible, Vector searchResults)
	{
		super(GoBible.getString("UI-Results"), Choice.IMPLICIT);
		
		if (GoBible.USE_MIDP20)
		{
			setFitPolicy(Choice.TEXT_WRAP_ON);
		}
		
		this.goBible = goBible;
		this.searchResults = searchResults;
		
		// Add the results
		
		for (Enumeration e = searchResults.elements(); e.hasMoreElements(); )
		{
			Object[] entry = (Object[]) e.nextElement();
			int index = ((Integer) entry[0]).intValue();
			
			int bookIndex = (index >> 16) & 0xff;
			int chapterIndex = (index >> 8) & 0xff;
			int verseIndex = (index) & 0xff;
			
			append(goBible.bibleSource.getBookName(bookIndex) + " " + goBible.bibleSource.getReferenceString(bookIndex, chapterIndex, verseIndex) + " \"" + ((String) entry[1]) + "\"", null);
		}
		
		addCommand(new Command(GoBible.getString("UI-Cancel"), Command.CANCEL, 0));
		
		setCommandListener(this);
	}
	
	public void commandAction(Command command, Displayable display)
	{
		if (command == javax.microedition.lcdui.List.SELECT_COMMAND)
		{
			// View this verse
			
			// Grab the reference
			int selectedIndex = getSelectedIndex();
			
			int index = ((Integer) ((Object[]) searchResults.elementAt(selectedIndex))[0]).intValue();
			
			goBible.currentBookIndex = (index >> 16) & 0xff;
			goBible.currentChapterIndex = (index >> 8) & 0xff;
			goBible.currentVerseIndex = (index) & 0xff;
			
			goBible.bibleCanvas.enterLoadingMode();
			goBible.display.setCurrent(goBible.bibleCanvas);
			goBible.loadCurrentChapter();
			goBible.bibleCanvas.update();
		}
		else
		{
			switch (command.getCommandType())
			{
				case Command.OK:
				case Command.CANCEL:
				{
					// Revert to the previously displayed verse
					goBible.bibleCanvas.enterLoadingMode();
					goBible.display.setCurrent(goBible.bibleCanvas);
					goBible.bibleCanvas.repaint();
					goBible.loadCurrentChapter();
					goBible.showMainScreen();
					break;
				}
			}
		}
	}
}
