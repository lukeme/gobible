This page describes the data files that are produced by GoBibleCreator and packaged into the final JAR. It also describes their format.

There are three types of data files produced by GoBibleCreator:
  * Global Index File
  * Book Index Files
  * Verse Data Files

These appear in the JAR file as follows:

  * `Bible Data/Index` (Global Index File)
  * `Bible Data/[Book Name]/Index` (Book Index File)
  * `Bible Data/[Book Name]/[Book Name] [File Number]` (Verse Data File)

As an example, here are the files produced for a collection which only contains the book of Matthew:

  * Bible Data/Index
  * Bible Data/Matthew/Index
  * Bible Data/Matthew/Matthew 0
  * Bible Data/Matthew/Matthew 1
  * Bible Data/Matthew/Matthew 2
  * Bible Data/Matthew/Matthew 3
  * Bible Data/Matthew/Matthew 4
  * Bible Data/Matthew/Matthew 5

The files named "Matthew n" contain the actual verse data. GoBibleCreator splits the verse data into multiple files to speed up file loading and also to limit the memory used . GoBibleCreator currently has a maximum file size limit set to 24KB, therefore if a book exceeds this size it will result in multiple verse data files. When a user searches or navigates in Go Bible, the GoBibleCore will load each verse data file as it is needed releasing the previous one. This optimisation may not be needed on modern devices which may load entire books into memory very quickly (this hasn't been tested).

The following sections describe the format of each data file type.

### The Global Index File: `Bible Data/Index` ###

The file stored at `Bible Data/Index` is the Global Index File and contains the information needed by GoBibleCore to locate a chapter quickly.

It has the following format:

  * `byte` - Number of books
  * `[For each book]`
    * `utf` - Book display name
    * `utf` - Book's file name
      * Used for both the book directory and verse data files, this an ASCII safe version of the file name as opposed to the display name which can contain any unicode characters. Note: Book file names can have spaces in them, eg. "1 Corinthians" and by default GoBibleCreator will output file names with spaces.
    * `short` - Start chapter (version 2.2.3 and earlier used `byte` instead of `short`)
      * Chapter numbers start at 1 or whatever was read in from the source ThML or OSIS file, so theoretically a chapter could start at 0 but I haven't seen this yet, and it would result in the user seeing a chapter 0 on screen. The only time when this field would be larger than 1 is if a book must be split across different collections (ie. JAR files), this was a requirement when devices had JAR size restrictions smaller than the largest book (Psalms). As far as I am aware no MIDP 2.0 phone has a memory limit which requires books to be split.
    * `short` - Number of chapters (version 2.2.3 and earlier used `byte` instead of `short`)
      * If the book has been split then this will contain only the number of chapters in this collection, but normally it will be the total number of chapters in the book.
    * `[For each chapter]`
      * `byte` - The file number which contains this chapter (so that GoBibleCore knows where to look for it)
      * `int` - Number of characters in the chapter (this is required so that GoBibleCore knows where in the verse data file a chapter begins)
      * `byte` - Number of verses in the chapter

Note: the data types used above are the standard Java data types. GoBibleCreator uses [java.io.DataOutputStream](http://java.sun.com/javase/6/docs/api/java/io/DataOutputStream.html) to create the file. This means that the utf strings are not standard UTF-8 but are Java's UTF-8 format. Also all primitive types are signed. See the [java.io.DataOutputStream](http://java.sun.com/javase/6/docs/api/java/io/DataOutputStream.html) class reference in the Java API documentation for more information about it's format.

### The Book Index File: `Bible Data/[Book Name]/Index` ###

The Book Index File simply contains the length of each verse of every chapter. This is used to quickly locate a verse.

  * `[For each chapter]`
    * `[For each verse]`
      * short - Number of characters in the verse

### The Verse Data File: `Bible Data/[Book Name]/[Book Name] [File Number]` ###

Unlike the Java utf strings stored in the Global Index File, the verse data is stored as true UTF-8. The file has the following format:

  * `int` - Total number of bytes of verse data (not characters)
  * `byte[]` - The raw UTF-8 bytes of the verse data

Note that the verse data consists of all verses from all chapters for this particular verse data file appended together. There is no separation between verses. ie. There is no end-of-line delimiter or null terminator. The only way to know the length of the verse is to use the verse lengths from the Book Index File.

The only unusual aspect of the verse data is that style markup can be inserted. Currently this has only been used to indicate Christ's Words in red. However, it could also be used for italics, however the code is not there in GoBibleCreator or GoBibleCore, even though it would be relatively simple to add.

The red style is turned on with the UTF-8 character 0x1 and turned off again with the same character. Since Go Bible has very little need for standard ASCII control characters I envisage that any UTF-8 character below 10 could probably be used without an issue.