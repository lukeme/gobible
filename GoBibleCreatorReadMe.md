# Introduction #

This is a copy of the contents of the readme text file included with Go Bible Creator.


## Details ##

_The colors are an artefact of Google wiki software._

```

===========================================================================
Go Bible Software Release Notes - History of Changes since version 2.2.6
===========================================================================

GoBibleCreator 2.4.0 Notes 
April 15, 2010

GoBibleCore updated with some bug fixes and several enhancements:
----------------------------------------------------------------
 BUG FIXES:
 - A verse with less than two characters of text (including an empty one) now displays correctly.
   i.e. Its verse number no longer gets overwritten by the one for the next verse.
   Note: An empty verse results when a USFM file contains a verse that is nothing but a footnote.
   Example: Luke 17:36 and Acts 15:34 in the World English Bible.
 - Words wider than the phone display width no longer lose a character at the word-wrap position.
 - Minor improvement to word-wrap algorithm to avoid orphaned verse number for text with a leading blank.
 - Search no longer fails to find the search term in the last verse of a collection (e.g. Rev 22:21).
 
 ENHANCEMENTS:
 - Search term matching is now restricted to within individual verses.
 - Search Results now displays the number of results found.
 - The search algorithm has been enhanced to overlook the red letter code (0x01).
   In displayed Search Results, each red letter code in the found verse is replaced with a space. 
   There is no change to the Go Bible data, but only to the displayed Search Results.
 - Extended the set of UTF-8 codepoints used as word separators.
 - Added support to display UTF-8 codepoints beyond U+FFFF (providing the phone firmware has font coverage).
   This enhancement does not yet implement entering search terms containing codes beyond U+FFFF.
 - Bookmarks enhanced to include a Delete All option (could be further improved by adding extra UI item).
 - Send MMS option has been disabled. This does not affect Send SMS.
   This is a temporary expedient for a program feature that did not work.
   It is intended that this will be fixed in a future release.
 - Added some rudimentary support for touch screen phones. This feature is still experimental.
   For phones without a virtual keypad, this provides for vertical scrolling using swipe actions.


GoBibleCreator updated:
  ===========================================================================
  NEW & UPDATED COLLECTIONS FILE PROPERTIES
  ===========================================================================
  * MIDlet-Info-URL: [optional] [ default = http://wap.jolon.org ]
  * MIDlet-Vendor:   [optional] [ default = Jolon Faichney ]
    If these optional tags are absent, then the existing defaults are used. 
    If either of these new tags are specified, then the specified values are used instead.
    This allows third parties to specify their own branding, without needing a software change.
	The URL does not have to be to a WAP enabled site. It is primarily for providing information.
    This may also help for websites that provide for WAP downloads. 
 
  * Empty-Verse-Text: [...]
    This option permits users to specify what should be inserted when an empty verse is detected.
    The default is nothing at all. This applies only to empty verses, not to missing verses.
	
  *	Language-Code: en, {prefix|suffix}
    This is an enhancement. Three letter language codes are also now permitted.
    The optional placement of the optional language code may be specified by the word after the comma.
	  'prefix' causes the language code to be added at the start of the collection filename[s]
	  'suffix' causes the language code to be added at the end   of the collection filename[s]
    The default [without the comma] is still as for suffix. 
	
	The behaviour of the language code property depends on the line order in the collections file.
	There may be even multiple language code properties specified in a collections file. 
	Collections specified before a language code do not use that property.
	Collections specified after  a language code use that property.


GoBibleCreator - other changes:
------------------------------
 - Discontinued support for MIDP 1.0 (gobiblecore1.jar removed). 
   This was an "end of life" decision.  Go Bible now supports MIDP 2.0 only.
   In future, we may look at whether any features in MIDP 3.0 could be useful.

 ENHANCEMENTS:   
 - Outputs extra lines when either the -d or -u options are invoked in the command line.
   The -u option produces the following output:
    ** The "Update-only" (-u) flag has been found: updating the JAR/JAD files.
    ** The original JAR file[s] are renamed with file extension .TMP
   The -d option produces the following output:
    ** The "Directory" (-d) flag has been found.
    ** Using <C:\Internet\Downloads\GoBible> as the base Source Directory.
       The above directory path is just an example.
   
 - An error message is now displayed if the specified Phone-Icon-Filepath cannot be found.
   
 - An error message is now displayed for each book that doesn't contain any verses. 
   Here is an example of the output:
     Error: Book (Gen) doesn't contain any verses.
     Error: Book (Exod) doesn't contain any verses.
     ...
     Error: Book (Rev) doesn't contain any verses.
   Circumstances: (Issue 28)
     This occurs when the OSIS XML file uses the 'milestone' form of [chapter &] verse tags.
     Such an OSIS file would first need to be converted to the 'container' form, by means of an XSLT script or equivalent.
     In the milestone form, the XML structure is Book | Section | Paragraph
     In the container form, the XML structure is Book | Chapter | Verse
	 
 BUG FIX:
  - Using the option-only switch -u now correctly finds the Phone-Icon-Filepath.
    This uses a path relative to the location of the collection text file.



GoBibleCreator 2.3.6 Notes 
October 1, 2009

The new version fixed the following things: (USFM source text)
- missing \d, \k, \mr tags - these are now processed correctly
- added in the new USFM version 2.2 standard tags of \xot, \xnt, and \iqt  
- fixed the hanging \q problem where this tag was on a line by itself


GoBibleCreator 2.3.5 Notes 
July 10, 2009

The new version primarily fixes one thing: (USFM source text)

- Support for the \r tag 


GoBibleCreator 2.3.4 Notes 
May 7, 2009

The new version primarily fixes these things: (USFM source text)

- Minor improvements to processing tags in USFM files.


GoBibleCreator 2.3.3 Notes 
April 27, 2009

 BUG FIXES: The new version primarily fixes these things: (USFM source text)
- Exclamation marks were being removed from the text when they appear as the final character in a chapter. 
  This frequently occurs in the Psalms. e.g. Hallelujah!
- The tag parsing was reordered to make sure that short tags (e.g., \q or \p) 
  were processed after longer tags which started with those same characters (e.g., \qa or \pi).  
  This prevents the longer tag from being truncated early by the short tag and leaving residue behind.
  For example, \pi would leave behind the "i" character because \p was previously processed.
- Modified the code to better handle spaces after tags.  
  Several of the tag parses would leave an extra space in the text.
 
 
GoBibleCreator 2.3 Notes
September 28, 2009 - minor updates February 16, 2010

This version adds in the following new capabilities:
----------------------------------------------------
- Ability to read in USFM Bible format as source text
  This is used by about 2/3 of the Bible translators in the world.
  For details, see http://ubs-icap.org/usfm

- The collections file now has the capability to allow comments or blank lines.
  Comment lines start with either the symbols '//' or 'REM' (without the quotes)

- Several new tags in the collection file:
    (most version 2.2 tag files should run without modification).
  
  ===========================================================================
  NEW COLLECTIONS FILE PROPERTIES
  ===========================================================================
    * Source-Text: [now required] enhanced to facilitate USFM files 
	    - For ThML and OSIS this is the path to the source file.
		- For USFM this is the path to the source files directory.
        
    * Source-Format: [required for USFM - optional for others] {osis|thml|usfm}
        This is a tag to specify the file format of the source text file[s].

    * Source-FileExtension: [required for USFM] this is the file extension for
        the source files that are in the directory specified in Source-Text:

    * Phone-Icon-Filepath: [optional] the path to a *.png file that will be used
        to replace the default GoBible icon.  PNG size is limited to 20 x 20

    * Application-Name: [optional] a new application name that you want for the
        collection.  This will replace the default name 'Go Bible'.

    * Codepage: [required for USFM, unless the source files are UTF-8] 
	    This is the codepage of the source files.
        It's usually a whole lot smoother if the source files are UTF-8 format.

    * RedLettering: [optional - works with USFM only] {true|false}
        Use this to specify if you want to use the red lettering for words of
        Jesus (\wj...\wj* tag in USFM).  Defaults to true.

    * USFM-TitleTag: [optional - works with USFM only] {\h | \id | \mt | \mt1 ...}
        The tag used within the source files that coorelates to the Books listed
        below.  Set to the \h tag by default

===========================================================================
USFM TAG HANDLING IN GoBibleCreator
===========================================================================
USFM files are marked by using specified tags. 
   Some tags are free standing and some have ending tags.
   The conversion process handles the tags in the following ways.

1. Throws out the whole line for the following USFM tags:
   "\cl", "\cp", "\cd", "\qa", "\sr", "\ms", 
   "\mte", "\mt", "\mr ", "\sr ", "\s", "\r ", "\d ", "\sp"
2. Removal of the USFM tags only for tags that do not have an end tag:
   "\pi", "\ph", "\pmo", "\pm", "\pmc",
   "\pmr", "\mi", "\nb", "\cls", "\pc", "\pr", 
   "\qr", "\qc", "\pb", "\b", "\m", "\p", "\z"
   "\li", "\qm", "\q"
3. Removal of the USFM tags only for tags that do not have an end tab but are
   supposed to be followed with a number (e.g., \q1):
   "\pi", "\li", "\ph", "\q", "\qm"
4. Removal of the USFM tags which have a start and end tag but we want to keep
   the text inside of the markers (i.e., formatting type tags for bold, etc):
   "\qs", "\qac", "\add", "\dc", 
   "\nd", "\ord", "\pn", "\qt", "\sig", "\sls", 
   "\tl", "\em", "\bd", "\it", "\bdit", "\no", "\sc", "\k"
5. Removal of the USFM tags which have a start and end tag and all the text
   that lies between (i.e., comment type tags):
   "\ca", "\va", "\vp", "\fe", "\bk", 
   "\xdc", "\fdc", "\fm", "\fig", "\ndx", "\pro", 
   "\wg", "\wh", "\f", "\w", "\x", "\rq", "\xot", "\xnt", "\iqt"
   
===========================================================================
FURTHER INFORMATION ABOUT GO BIBLE & GO BIBLE CREATOR SOFTWARE
===========================================================================
The source code is now maintained here: (registered users only)
   https://crosswire.org/svn/gobible/
For further information on Go Bible software development:
   http://code.google.com/p/gobible/

Compiled software availability: GoBibleCreator (as ZIP files)
Latest & previous versions can be downloaded here: (registered users only)
   http://groups.google.com/group/go-bible-dev
Latest version can also be downloaded here: (registration not required)
   http://groups.google.com/group/go-bible
   
Go Bible Forum: General support for Go Bible users (registration advised)
   http://jolon.org/vanillaforum/
===========================================================================
David Haslam, Go Bible project leader, CrossWire Bible Society
   http://crosswire.org/
===========================================================================

```