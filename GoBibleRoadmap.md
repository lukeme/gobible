# Introduction #

Use this page to outline proposals for developing the **Go Bible** application.

## Guidelines for editing this page ##

  * _Please do not merely duplicate detailed enhancement issues_
  * _Just list major proposals for the way GoBible might be developed_

## Possible projects ##
_Some of these are copied from the [Go Bible News](http://gobible.jolon.org/news.html) page_
### Enhancements and improvements ###
_User Interface_
  * Provide support for [T9 input](http://en.wikipedia.org/wiki/T9_(predictive_text)) when selecting a book in the "Go to" menu. _This technique is patented, so we will not implement it._
  * Show a list of books which can be scrolled through when selecting a book in the "Go to" menu
  * Two dimensional "Go to" menu with book abbreviations in a 6x11 (or a 5x14) table?
  * Enable keys 3 & 6 to scroll up & down by 10 verses (useful for long chapters)
  * Enable joystick left/right to scroll through **History** for speedier navigation
  * Add a Help option to the Menu, to display how keys are used for navigation
  * Add a Reset option to the Menu, to clear History, Bookmarks & Search Results and set all Preferences to the as installed defaults
  * Add a Delete option for History items
  * Add scrolling & user interface support for [touchscreen](http://en.wikipedia.org/wiki/Touchscreen) only phones (e.g. Samsung SGH-F488)
  * Add landscape orientation support for slide phones with full QWERTY keyboard (e.g. LG KS360)
  * Include an abbreviation for the Bible version along with Send Verses by MMS/SMS

_Localization_
  * Add UI support for non-Latin [numerals](http://www.code2000.net/numbersutf.htm)

_Security_
  * Provide support for secure [digital signatures](http://en.wikipedia.org/wiki/Digital_signature)
  * Provide options for encryption and password protection of Go Bible applications

_Bible translation features_
  * Specify and provide support for **Psalm titles** in the [GoBibleDataFormat](GoBibleDataFormat.md)
  * Specify and provide support for **colophons** (at the end of each Pauline epistle) in the [GoBibleDataFormat](GoBibleDataFormat.md)
  * Add support for Bible cross-references, with hotlinks?
  * Add support for formatting poetry
  * Add support for displaying text as paragraphs
  * Add support for italics as a font attribute (for Bibles that use the Latin alphabet)
  * Support the title attribute in XML elements, to display titles where appropriate

_Integration_
  * Integrate a [Bible Reading Calendar](http://gobible.googlecode.com/files/M%27Cheyne%27sDailyBread2.2.4.zip), to read through the whole Bible in a year
  * Combine two Bible translations/versions into one application, with synchronized scrolling/navigation, and use the # key to switch between them

_Further features_
  * Enhancements to take advantage of MIDP 3.0 ([JSR 271](http://www.jcp.org/en/jsr/detail?id=271))?
  * Add feature to **Send verses by email**, for phones that have email capability
  * Add support for **printing** (e.g. for modern phones that can connect to a wireless printer)

### Search feature improvements ###
_Search algorithm_
  * Improve search speed by implementing some form of the [Boyer–Moore string search algorithm](http://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string_search_algorithm)
_Search scope (radio button) options are hard coded only for the complete Western canon_
  * Provide support for source text files with other than 66 books
  * Provide support for non-Western canonical ordering of books
  * Provide support for the Deuterocanonical books
  * Provide a workaround for Bibles with the General Epistles before the Pauline Epistles <br>(e.g. Slavic Bibles that follow the NT book order of the Russian Synodal Translation)<br>
<i>Search is currently only for the exact text as edited. Possible enhancements might be as follows:</i>
<ul><li>Whole word searches (as a tick option)<br>
</li><li>Case-sensitive search text (as a tick option)<br>
</li><li>Full support for cases in Cyrillic and other non-Latin scripts<br>
</li><li>Boolean operators in the search pattern<br>
</li><li>Proximity matching in the search pattern<br>
</li><li>Regular expressions in the search pattern<br>
<i>Search results</i>
</li><li><del>Restrict search results to matches within a verse.</del>  <i>This task is now complete, and available in version 2.4.0</i>
</li><li><del>Display the total number of search results at the top of the results list.</del> <i>This task is now complete, and available in version 2.4.0</i>
</li><li>Send search results by SMS</li></ul>

<h3>Go Bible preferences</h3>
<ul><li>Restructure the preferences menu to use sub-menus, to alleviate scrolling.<br>
</li><li>Provide a choice of languages for the User Interface (UI).<br>
</li><li>Provide a choice of languages for the Bible book names.<br>
</li><li>Provide a choice of either search scope (radio buttons) or search range (first to last book).<br>
</li><li>Provide a choice for maximum number of search results.<br>
</li><li>Provide a choice for maximum length of SMS & MMS messages.</li></ul>

<h3>Traceability</h3>
<ul><li>Rebuild GoBibleCore with all links to Jolon replaced by links to CrossWire</li></ul>

<h3>Source code rewrites?</h3>
<ul><li>Rewrite Go Bible using <a href='http://www.j2mepolish.org/'>J2ME Polish</a> which provides lots of nice user interface features and custom fonts<br>
</li><li>Rewrite Go Bible using <a href='http://mobile.processing.org/'>Mobile Processing</a> (this also supports custom fonts)<br>
</li><li>Adapt Go Bible to make use of <a href='http://en.wikipedia.org/wiki/Scalable_Vector_Graphics'>SVG Tiny</a> in order to provide custom font support for scripts such as Burmese<br>
</li><li>Rewrite Go Bible to become a <a href='http://crosswire.org/sword/'>SWORD</a> frontend, separating the Java ME application from the data files holding the Bible text.</li></ul>

<h3>Ports to other mobile platforms?</h3>
<ul><li>A version of Go Bible for the <a href='http://code.google.com/android/'>Android</a> platform, possibly by means of <a href='http://www.assembla.com/wiki/show/j2ab'>J2ME Android Bridge</a>
</li><li>A version of Go Bible for the <a href='http://en.wikipedia.org/wiki/BlackBerry'>BlackBerry</a> platform, which uses a proprietary application framework derived from Java, but not identical to Java ME.<br>
</li><li>A version of Go Bible for the <a href='http://developer.apple.com/iphone/'>iPhone</a>. <i>Not required. We recommend <a href='http://crosswire.org/pocketsword/'>PocketSword</a> instead.</i>
</li><li>A version of Go Bible for the <a href='http://wiki.openmoko.org/wiki/Main_Page'>OpenMoko</a> platform. <i>Was done in 2008, though OpenMoko is now dead in the water</i></li></ul>

<h3>Preprocessing</h3>
<ul><li>An <a href='http://en.wikipedia.org/wiki/XLST'>XSL Transformation</a> which converts any valid OSIS Bible source text file to become usable by GoBibleCreator, no matter what was used to generate the original OSIS file<br>
</li><li>A <a href='http://en.wikipedia.org/wiki/Graphical_user_interface'>GUI</a> for GoBibleCreator, possibly making use of <a href='http://en.wikipedia.org/wiki/Swing_(Java)'>Swing</a>?<br>
</li><li>A <a href='http://en.wikipedia.org/wiki/PHP'>PHP</a> script which converts source text into GoBibleDataFormat<br>
</li><li>A utility to convert a CrossWire SWORD module directly to a Go Bible application<br>
</li><li>Document how to make the shaped Arabic Go Bible applications for Sony Ericsson phones</li></ul>

<h3>Go Bible Creator</h3>
<h4>Implemented</h4>
<ul><li><del>Enhance GoBibleCreator to build directly from USFM files, in addition to the existing ThML and OSIS formats.</del> <i>This task is now complete, and available in version 2.3.2</i>
</li><li><del>Provide support for alternative icon and application title, where the existing ones may present a cultural stumbling block.</del> <i>This task is now complete, and available in version 2.3.2</i>
</li><li><del>Update GoBibleCreator to build directly from USFM files that conform to version 2.2 of usfm.sty</del> <i>This task is now complete, and available in version 2.3.6</i>
</li><li><del>Provide support for specifying JAD properties other than existing defaults.</del> <i>This task is now complete, and available in version 2.4.0</i>
</li><li><del>Permit the Book-Name-Map to map an unused book, without causing an error message.</del> <i>This task is now complete, and available in version 2.4.0</i>
</li><li><del>Permit three letter ISO language codes to be used with Language-Code: in a collections text file. GoBibleCreator currently accepts only two letters.</del> <i>This task is now complete, and available in version 2.4.0</i></li></ul>


<h4>Proposals</h4>
<ul><li>Enhance GoBibleCreator to be capable of processing SIL Fieldworks Translation Editor files, with source text format XHTML-TE. <i>This has been implemented by SIL, yet requires thorough testing</i>
</li><li>Enhance GoBibleCreator to be capable of processing SWORD modules directly, making use of the <a href='http://www.crosswire.org/jsword/'>JSword</a> API to extract the Biblical text<br>
</li><li>Enhance GoBibleCreator to be capable of processing OSIS files in which chapter and verse XML tags are in the milestone form. Currently it supports only the container form for chapter and verse tags<br>
</li><li>Add option for GoBibleCreator to supply a specified MissingVerseText marker.<br>
</li><li>Extend the alternative icon support to have a separate icon for each collection<br>
</li><li>Make RedLetterMarkup for Words of Christ a build option in the collections text syntax. This is now done for USFM, but not for ThML source text format.<br>
</li><li>Enhance GoBibleCreator to facilitate RedLetterMarkup for Words of Christ (WoC) tags in OSIS files. <i>RedLetterMarkup is currently supported only in ThML and USFM source text format.</i>
</li><li>Allow a user interface translation to be specified as a path to a separate text file<br>
</li><li>Allow a book-name-map to be specified as a path to a separate text file</li></ul>


<h3>Build automation</h3>
<ul><li>Develop an <a href='http://en.wikipedia.org/wiki/Apache_Ant'>Apache Ant</a> utility to automate the build of multiple Go Bibles from source text files on any platform<br>
</li><li>Facilitate the compilation of both GoBible and GoBibleCreator together using a single make command