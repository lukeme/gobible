//
//  HistoryList.java
//  Go Bible 2.0
//
//  Created by Jolon Faichney on Sat Feb 05 2005.
//  Copyright (c) 2005. All rights reserved.
//

import javax.microedition.lcdui.*;

public class HistoryList extends List implements CommandListener
{
	private GoBible goBible;

	public HistoryList(GoBible goBible)
	{
		super(GoBible.getString("UI-History"), Choice.IMPLICIT);
		
		if (GoBible.USE_MIDP20)
		{
			setFitPolicy(Choice.TEXT_WRAP_ON);
		}
		
		this.goBible = goBible;
		
		// Add the history
		int i = goBible.historyStartIndex;
		do
		{
			if (goBible.historyExcerpts[i] != null && !goBible.historyExcerpts[i].equals(""))
			{
				int bookIndex = goBible.historyVerseIndices[i * 3] & 0xFF;
				int chapterIndex = goBible.historyVerseIndices[i * 3 + 1] & 0xFF;
				int verseIndex = goBible.historyVerseIndices[i * 3 + 2] & 0xFF;
				
				append(goBible.bibleSource.getBookName(bookIndex) + " " + goBible.bibleSource.getReferenceString(bookIndex, chapterIndex, verseIndex) + " \"" + goBible.historyExcerpts[i] + "\"", null);
			}
			
			i++;
			
			if (i >= GoBible.HISTORY_SIZE)
			{
				i = 0;
			}
		}
		while (i != goBible.historyStartIndex);
		
		addCommand(new Command(GoBible.getString("UI-Back"), Command.CANCEL, 0));
		
		setCommandListener(this);
	}

	public void commandAction(Command command, Displayable display)
	{
		if (command == javax.microedition.lcdui.List.SELECT_COMMAND)
		{
			// View this verse
			
			// Grab the reference
			int selectedIndex = getSelectedIndex();
			
			if (selectedIndex != -1)
			{
				// Convert to cyclic history list
				selectedIndex += goBible.historyStartIndex;
				
				if (selectedIndex >= GoBible.HISTORY_SIZE)
				{
					selectedIndex -= GoBible.HISTORY_SIZE;
				}
				
				goBible.currentBookIndex = goBible.historyVerseIndices[selectedIndex * 3] & 0xff;
				goBible.currentChapterIndex = goBible.historyVerseIndices[selectedIndex * 3 + 1] & 0xff;
				goBible.currentVerseIndex = goBible.historyVerseIndices[selectedIndex * 3 + 2] & 0xff;
				
				goBible.bibleCanvas.enterLoadingMode();
				goBible.display.setCurrent(goBible.bibleCanvas);
				goBible.loadCurrentChapter();
				goBible.bibleCanvas.update();
			}
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
