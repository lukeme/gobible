# Introduction #

The latest version of GoBibleCreator is v2.4.0.

This page is not fully up to date. Please refer to GoBibleCreatorReadMe.

_For version history up to 2.2.3, refer to [Go Bible News](http://gobible.jolon.org/news.html)_

## 2.3.2 (beta) - 24 September 2008 ##
_This build is currently available by download from the Go Bible Google Dev Group._
  * GoBibleCreator now supports USFM as source text format, refer to [Go Bible Dev](http://groups.google.com/group/go-bible-dev/browse_thread/thread/d981b332a952c285) for details
  * Several additional options in GoBibleCreator
  * [Issue 36](https://code.google.com/p/gobible/issues/detail?id=36) - Java NullPointerException error solved by prior recompile of 2.2.6

## 2.2.6 - 31 March 2008 ##

  * [Issue 17](https://code.google.com/p/gobible/issues/detail?id=17) - GoBibleCore should now work on BlackBerry phones now that there is no longer any reference to the Nokia UI API in the created JAR files. BlackBerry phones may require JAR files smaller than 512KB.
  * [Issue 2](https://code.google.com/p/gobible/issues/detail?id=2) - GoBibleCreator now ignores tags `<span class="chap">` and their contents which was causing a problem specifically with the ASV text where additional chapter numbers were being displayed in Go Bible. GoBibleCreator now also requires that Christ's words in red have the "red" class, eg. `<span class="red">`
  * [Issue 30](https://code.google.com/p/gobible/issues/detail?id=30) - "of" has been added to the ui.properties file and is correctly utilised by GoBibleCore (thanks to Erik Lundun for fixing this)
  * [Issue 3](https://code.google.com/p/gobible/issues/detail?id=3) - The Search form title now correctly uses the UI-Search property.
  * Indonesian translation source should now have the correct translation for James 3.
  * Slovakian translation has been removed from svn and the website as David Haslam pointed out that it may be copyright.
  * Fix for Vector Enumeration exception when processing Croatian hr.thm file.
  * GoBibleCreator now has two optional command line arguments "-u" and "-d", see the GoBibleCreator documentation for more information on these.


## 2.2.5 - 26 March 2008 ##

  * GoBibleCreator no longer takes ThML/OSIS files in as command line arguments. Instead the source text is specified in the Source-Text property in the Collections.txt file. However, if a ThML/OSIS file specified on the command line then GoBibleCreator will generate a Collections.txt file for it (but won't generate the actual collection JARs/JADs).
  * GoBibleCreator can now be run from a different directory and GoBibleCreator will still be able to locate the GoBibleCore directory which is in the same directory as the GoBibleCreator.jar.
  * Have added all Collections.txt files used to generate the collections on the Go Bible website to the repository.
  * Have begun adding source texts to the repository but have only got up to Hungarian as svn commit seems to be temperamental with large files.
  * Go Bible releases are now being tagged. The tag for this release is 2.2.5.

## 2.2.4 - 5 August 2007 ##
> _This build was available only by download from the Go Bible Google Group._

  * GoBibleDataFormat now uses shorts instead of bytes for start chapter and number of chapters.
  * GoBibleCreator will automatically generate a Collections.txt file if only a ThML/OSIS file is passed in as a parameter.