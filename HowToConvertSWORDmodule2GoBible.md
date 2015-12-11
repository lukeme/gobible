# Introduction #

_Lightly edited copy of the content of comment 8 in [issue 15](https://code.google.com/p/gobible/issues/detail?id=15)._


# Details #

## Background ##

I found this thread when searching for information how to convert SWORD modules for
GoBibleCreator.

After some work, I managed to create a file that was correctly read by GoBibleCreator.

I post the steps here in case someone else is struggling with the same problems and finds this thread.

_Comment 8 was posted by [gbdjoh](http://code.google.com/u/gbdjoh/)._

## Requirements ##

The instructions are for Windows, and assumes reasonable computer skills.

You will need Perl installed (download from
http://www.activestate.com/activeperl/ )

You may also need Notepad++ (ANSI version from the zip at
http://notepad-plus.sourceforge.net ) in case the produced OSIS file needs to be
manually fixed.

(Alternatively you can use Perl or other scripting language in case
you have the needed skills).

Information about the tools:
http://www.crosswire.org/wiki/File_Formats

Some information about using the MOD2IMP can be found from:
http://lcdbible.sourceforge.net/misc/CwModuleTutorial.doc

I did not get mod2osis to work, so I did the conversion through IMP format.

## SWORD to IMP ##

Download mod2imp.zip from
http://crosswire.org/ftpmirror/pub/sword/utils/win32/

Extract to a folder. Run it from command line. It complains about a missing dll.
Download the dll zip from the same site, and unzip to the same folder.

Now place the SWORD module into this folder. The module must to be in the same folder
structure as it would be under the The SWORD Project software.

In my case, there was a `.conf` file in `mods.d` folder, and `bz*` files under
`modules\texts\ztext`.

Run from command line:

> `mod2imp modulename`

Where modulename is the name of the module (can be found from first line of the `.conf`
file, no brackets). CASE SENSITIVE!

You should see many lines of text scrolling.

In case you don't get it working, install The SWORD Project software, use it to
install the SWORD module, copy mod2imp and the dll to the SWORD Project folder, and
try there.

When it works, run:

> `mod2imp modulename > modname.imp`

Now you should have modname.imp file in SWORD IMP format.

## IMP TO OSIS ##

Save imp2osis.pl from http://crosswire.org/svn/sword-tools/trunk/modules/perlconverters/
to the same folder.

run:

> `perl imp2osis.pl modulename modulename.imp`

`modulename.osis.xml` will be generated.

## Modifying the OSIS ##
Now you can try the OSIS file with GoBibleCreator.

In case you get error messages, you may need to fix the OSIS file manually (I had to).

I used Notepad++ regular expressions for this.

For example:

> `Error: Line: 4776: End tag </verse> does not have a matching start tag (found <WTH4:2-4> instead), ignoring.`

Can be fixed with Notepad++ by replacing:

> `<WTH[0-9]*:[^ >]*>`
with
> ` ` (nothing)

(replace all, remember to set search mode: regular expression).

You could manually fix all the errors, but regexp will just do the work much faster.

In the end, GoBibleCreator should run with no errors.

In case the result does not look correct on your phone, you may need to make each verse
appear on a single line (I had to).

Unfortunately this cannot be done with Notepad++. It can be done using Perl by
running the following commands:

> `perl -w -p -i.bak -e "s/(<verse [^>]*>)\n/$1/g" modulename.osis.xml`

> `perl -w -p -i.bak -e "s/(<verse.*)\n/$1/g" modulename.osis.xml`

Hope this was helpful to someone.