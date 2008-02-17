//
//  BookmarksList.java
//  Go Bible 2.0
//
//  Created by Jolon Faichney on 17/03/06.
//  Copyright 2006. All rights reserved.
//

import java.util.*;
import javax.microedition.lcdui.*;

public class BookmarksList extends javax.microedition.lcdui.List implements CommandListener
{
	private GoBible goBible;
	private Command viewCommand = new Command(GoBible.getString("UI-View"), Command.ITEM, 0);
	private Command backCommand = new Command(GoBible.getString("UI-Back"), Command.CANCEL, 0);
	private Command cancelCommand = new Command(GoBible.getString("UI-Cancel"), Command.CANCEL, 0);
	private Command deleteBookmarkCommand = new Command(GoBible.getString("UI-Delete-Bookmark"), Command.SCREEN, 0);
	private Command deleteCommand = new Command(GoBible.getString("UI-Delete"), Command.SCREEN, 0);

	private Alert deleteAlert;
	
	private int indexToDelete;
	
	/**
	 * Create the bookmarks list by adding the bookmarks.
	 */
	public BookmarksList(GoBible goBible)
	{
		super(GoBible.getString("UI-Bookmarks"), Choice.IMPLICIT);
		
		if (GoBible.USE_MIDP20)
		{
			setFitPolicy(Choice.TEXT_WRAP_ON);
		}
		
		this.goBible = goBible;
		
		// Add the bookmarks
		
		for (Enumeration e = goBible.bookmarks.elements(); e.hasMoreElements(); )
		{
			BookmarkEntry bookmark = (BookmarkEntry) e.nextElement();
			
			append(goBible.bibleSource.getBookName(bookmark.bookIndex) + " " + goBible.bibleSource.getReferenceString(bookmark.bookIndex, bookmark.chapterIndex, bookmark.verseIndex) + " \"" + bookmark.excerpt + "\"", null);
		}
		
		setSelectCommand(viewCommand);
		
		addCommand(deleteBookmarkCommand);
		addCommand(backCommand);
		
		setCommandListener(this);
	}

	public void commandAction(Command command, Displayable display)
	{
		if (display == this)
		{
			if (command == viewCommand)
			{
				// View this verse
				
				// Grab the reference
				int selectedIndex = getSelectedIndex();
				
				if (selectedIndex != -1)
				{
					// Get the bookmark entry
					BookmarkEntry bookmark = (BookmarkEntry) goBible.bookmarks.elementAt(selectedIndex);
					
					goBible.currentBookIndex = bookmark.bookIndex & 0xff;
					goBible.currentChapterIndex = bookmark.chapterIndex & 0xff;
					goBible.currentVerseIndex = bookmark.verseIndex & 0xff;
					
					goBible.bibleCanvas.enterLoadingMode();
					goBible.display.setCurrent(goBible.bibleCanvas);
					goBible.loadCurrentChapter();
					goBible.bibleCanvas.update();
				}
			}
			else if (command == deleteBookmarkCommand)
			{
				// Get the selected index
				indexToDelete = getSelectedIndex();
				
				if (indexToDelete != -1)
				{
					// Confirm with the user to remove 
					deleteAlert = new Alert(GoBible.getString("UI-Delete-Bookmark"), GoBible.getString("UI-Delete-Bookmark-Alert"), null, AlertType.CONFIRMATION);
					
					deleteAlert.addCommand(cancelCommand);
					deleteAlert.addCommand(deleteCommand);
					
					deleteAlert.setCommandListener(this);
					
					goBible.display.setCurrent(deleteAlert, this);
				}
			}
			else
			{
				switch (command.getCommandType())
				{
					case Command.OK:
					case Command.BACK:
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
		else if (display == deleteAlert)
		{
			if (command == deleteCommand)
			{
				// Remove the bookmark from the List
				delete(indexToDelete);
				
				// Remove the bookmark from the Vector
				goBible.bookmarks.removeElementAt(indexToDelete);
			}
			
			// Display the BookmarksList again
			goBible.display.setCurrent(this);
		}
	}
}
