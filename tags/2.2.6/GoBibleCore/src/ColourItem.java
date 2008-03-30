//
//  ColourItem.java
//  Go Bible 2.0
//
//  Created by Jolon Faichney on 17/03/06.
//  Copyright 2006. All rights reserved.
//

import javax.microedition.lcdui.*;

/**
 * Essentially a StringItem that allows the text and background colours to be set.
 */
public class ColourItem extends CustomItem
{
	String text;
	int textColour;
	int backColour;
	Font font;
	int fontHeight;
	int stringWidth;
	int id;

	/**
	 * @param id a generic id that can be used for tracking.
	 */
	public ColourItem(int id, String text, int textColour, int backColour)
	{
		super("");
		this.id = id;
		this.textColour = textColour;
		this.backColour = backColour;
		font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
		setText(text);
	}
	
	public void setText(String text)
	{
		this.text = text;
		fontHeight = font.getHeight();
		stringWidth = font.stringWidth(text);		
	}
	
	public void paint(Graphics g, int width, int height)
	{
		// Fill the background
		g.setColor(backColour);
		
		g.fillRect(0, 0, width, height);
		
		// Set the font
		g.setFont(font);
		
		g.setColor(textColour);
		
		// Draw the string
		g.drawString(text, (width - stringWidth) / 2, (height - fontHeight) / 2, Graphics.LEFT | Graphics.TOP);
	}

	public int getMinContentHeight()
	{
		return fontHeight + 2;
	}
	
	public int getPrefContentHeight(int width)
	{
		return fontHeight * 2;
	}
	
	public int getMinContentWidth()
	{
		return stringWidth + 2;
	}
	
	public int getPrefContentWidth(int height)
	{
		return 128;
	}

}




