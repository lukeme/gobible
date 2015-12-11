# Introduction #

For some parts of the world, it may be desirable to rebrand a Go Bible application. Using an application name which doesn't include the word Bible, and a phone icon that doesn't contain a Cross are now distinct possibilities. Using alternative branding may enable the application to overcome certain cultural obstacles, and thus still reach the intended readers.


## Branding properties for Go Bible Creator ##

These optional properties may be specified in the collections text file.

_The path to a new 20x20 png icon if you want to replace the default cross icon_
> `Phone-Icon-Filepath: new_logo.png`

_The application name that will show up in the phone's menu (instead of Go Bible)_
> `Application-Name: MyGBReader`

_The name of the vendor that appears in the JAD file (instead of Jolon Faichney)_
> `MIDlet-Vendor: My GB Vendor name`

_The information URL that appears in the JAD file (instead of `http://wap.go-bible.org`)_
> `MIDlet-Info-URL: http://wap.mygbdomain.org/`
_or_
> `MIDlet-Info-URL: http://www.mygbdomain.org/`