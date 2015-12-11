# Introduction #

A GoBible port is available for the [Openmoko](http://wiki.openmoko.org)

# Installation #

  * If your distribution feeds contains java (ASU currently does), then use it.  If not (FSO does currently not), [add Jalimo feeds](http://wiki.evolvis.org/jalimo/index.php/Packages#OpenMoko)
  * Copy the gobibleswt package from the Downloads section to the Openmoko and run
```
    opkg install gobibleswt_2.2.6-0openmoko-2007.11-r0_armv4t.ipk
```
  * Download a bible from http://go-bible.org/ in your preferred language, unpack the zip and copy the .jar file to the Openmoko.
> _'Full bible' downloads work fine._
  * Start 'GoBible' using the launcher, go to the Preferences panel and type the path to the downloaded .jar file. Press 'Save Preferences'.
> _Now you are able to read the bible in your preferred language._

# Usage instructions #

  * Finger scroll up and down works (not perfectly though).
  * Finger tap in upper and lower right corners, scroll up and down a page.
  * Finger tap in upper and lower left corners, moves to previous and next books.

# Known issues #

  * Verse selector in Goto tab does not work yet
  * Application starts in Goto tab and the switches to Text tab to avoid keyboard popups on ASU.