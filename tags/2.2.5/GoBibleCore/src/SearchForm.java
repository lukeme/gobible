//
//  SearchForm.java
//  GoBible
//
//  Created by Jolon Faichney on Mon Jul 28 2003.
//  Copyright (c) 2003. All rights reserved.
//

import javax.microedition.lcdui.*;

public class SearchForm extends Form implements CommandListener
{
	private GoBible goBible;
	
	private TextField searchTextField;
	private ChoiceGroup bookChoiceGroup;
	
	private Command searchCommand = new Command(GoBible.getString("UI-Search"), Command.OK, 0);
	private Command cancelCommand = new Command(GoBible.getString("UI-Cancel"), Command.CANCEL, 0);
	
	// Book Index Constants
	private final static int GENESIS = 0;
	private final static int DEUTERONOMY = 4;
	private final static int JOSHUA = 5;
	private final static int ESTHER = 16;
	private final static int JOB = 17;
	private final static int SONG_OF_SOLOMON = 21;	
	private final static int ISAIAH = 22;	
	private final static int DANIEL = 26;	
	private final static int HOSEA = 27;
	private final static int MALACHI = 38;
	private final static int MATTHEW = 39;
	private final static int JOHN = 42;
	private final static int ROMANS = 44;
	private final static int HEBREWS = 57;
	private final static int JAMES = 58;
	private final static int JUDE = 64;
	private final static int REVELATION = 65;
	
	private final static int[][] BOOK_RANGES = new int[][]
	{
		{GENESIS, REVELATION}, // All
		{-1, -1}, // Current book
		{MATTHEW, REVELATION}, // New Testament
		{GENESIS, MALACHI}, // Old Testament
		{MATTHEW, JOHN}, // Gospels
		{ROMANS, HEBREWS}, // Paul's Letters
		{JAMES, JUDE}, // Other Letters
		{GENESIS, DEUTERONOMY}, // Books of Moses
		{JOSHUA, ESTHER}, // Historical
		{JOB, SONG_OF_SOLOMON}, // Poetic
		{ISAIAH, DANIEL}, // Major Prophets
		{HOSEA, MALACHI}, // Minor Prophets
	};
	
	private String[] bookNames;
	
	/**
	 * Creates a search for with search criteria and from and to books to search in.
	 */
	public SearchForm(GoBible goBible)
	{
		super("Search");
	
		this.goBible = goBible;
		
		/*fromBookChoiceGroup = new ChoiceGroup(GoBible.getString("UI-From-Book") + ":", Choice.POPUP);
		toBookChoiceGroup = new ChoiceGroup(GoBible.getString("UI-To-Book") + ":", Choice.POPUP);

		// Add all of the books to the from and to book popups
		String[] bookNames = goBible.bibleSource.getBookNames();
		
		for (int i = 0; i < bookNames.length; i++)
		{
			fromBookChoiceGroup.append(bookNames[i], null);
			toBookChoiceGroup.append(bookNames[i], null);
		}
		
		// If no last from and to books are set then set them to the first and last books
		if (goBible.lastFromBook == -1)
		{
			goBible.lastFromBook = 0;
			goBible.lastToBook = bookNames.length - 1;
		}
		
		fromBookChoiceGroup.setSelectedIndex(goBible.lastFromBook, true);
		toBookChoiceGroup.setSelectedIndex(goBible.lastToBook, true);*/

		searchTextField = new TextField(GoBible.getString("UI-Search-Text") + ":", goBible.lastSearchString, 30, TextField.ANY);

		bookNames = goBible.bibleSource.getBookNames();

		bookChoiceGroup = new ChoiceGroup(GoBible.getString("UI-Books") + ":", Choice.EXCLUSIVE);
		bookChoiceGroup.append(GoBible.getString("UI-All"), null);
		bookChoiceGroup.append(bookNames[goBible.currentBookIndex], null);
		
		// If this collection contains the entire Bible then add options for
		// the testaments and other combinations
		if (bookNames.length == 66)
		{
			bookChoiceGroup.append(GoBible.getString("UI-New-Testament"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Old-Testament"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Gospels"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Pauls-Letters"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Other-Letters"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Books-Of-Moses"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Historical"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Poetic"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Major-Prophets"), null);
			bookChoiceGroup.append(GoBible.getString("UI-Minor-Prophets"), null);
		}

		// Default to All
		bookChoiceGroup.setSelectedIndex(0, true);
		
		append(searchTextField);
		append(bookChoiceGroup);
		/*append(fromBookChoiceGroup);
		append(toBookChoiceGroup);*/
		
		addCommand(searchCommand);
		addCommand(cancelCommand);
		
		setCommandListener(this);
	}
	
	public void commandAction(Command command, Displayable display)
	{
		switch (command.getCommandType())
		{
			case Command.OK:
			{
				if (!searchTextField.getString().equals(""))
				{
					goBible.lastSearchString = searchTextField.getString();
					
					int selectedIndex = bookChoiceGroup.getSelectedIndex();
					int from = 0, to = 0;
					
					if (selectedIndex == 0)
					{
						// All
						to = bookNames.length - 1;
					}
					else if (selectedIndex == 1)
					{
						// Current Book
						from = to = goBible.currentBookIndex;
					}
					else if (selectedIndex > 1)
					{
						// Look up the range
						from = BOOK_RANGES[selectedIndex][0];
						to = BOOK_RANGES[selectedIndex][1];
					}
					
					/*goBible.lastFromBook = fromBookChoiceGroup.getSelectedIndex();
					goBible.lastToBook = toBookChoiceGroup.getSelectedIndex();*/
					
					goBible.display.setCurrent(new SearchingCanvas(goBible, from, to, goBible.lastSearchString));
				}
				break;
			}
			
			case Command.BACK:
			case Command.CANCEL:
			{
				goBible.showMainScreen();
				break;
			}
		}
	}
}
