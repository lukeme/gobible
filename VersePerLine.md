# Introduction #

VPL is a simple and useful format to store a Bible that is structured as Book | Chapter | Verse, rather than Book | Section | Paragraph.

## Example ##

As an example, here the first three verses from the KJV in VPL format.

```
Gen 1:1 In the beginning God created the heaven and the earth.
Gen 1:2 And the earth was without form, and void; and darkness was upon the face of the deep. And the Spirit of God moved upon the face of the waters.
Gen 1:3 And God said, Let there be light: and there was light.
```

### Abbreviations ###

The bookname abbreviations may differ between implementations from various sources, but the principles are the same.

### Markup ###

VPL format may contain markup inherited from other file formats, or it may contain none at all.

### Conversion to other formats ###

I have made some [DataMystic TextPipe](http://www.datamystic.com) filters that can convert VPL to other formats.

  * VPL to ThML - suitable for use with GoBibleCreator
  * VPL to USFM - suitable for checking with [Go Bible Creator USFM Preprocessor](http://gbcpreprocessor.codeplex.com/) - e.g. to find versification issues

## See also ##

> http://www.crosswire.org/wiki/File_Formats#VPL