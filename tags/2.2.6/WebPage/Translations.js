
var variants = 
{
	"Variants":
	{
		"Full": 
		{
			"Description": "Full Bible - Full Bible in one application",
			"FileName": ""
		},
		
		"512KB": 
		{
			"Description": "512KB - Bible split into 512KB applications for some Nokia and BlackBerry phones",
			"FileName": "512KB "
		},
		
		"128KB": 
		{
			"Description": "128KB - Bible split into 128KB applications for some Nokia phones",
			"FileName": "128KB "
		},

		"64KB": 
		{
			"Description": "64KB - Bible split into 64KB applications for some Nokia MIDP 1.0 phones",
			"FileName": "64KB "
		}
	}
};

var packages =
{
	"Languages":
	{
		// Language
		"Afrikaans":
		{
			// Translation
			"Afrikaans":
			{
				"Description": '<p>1933, British and Foreign Bible Society. Translated by Prof J D du Toit, Prof E E van Rooyen, Prof J D Kestell, Dr H C M Fourie and Prof B B Keet.</p><p align="right"><i>See <a href="http://www.biblesociety.co.za/resources/translations_afr.asp">Bible Society of South Africa</a></i></p>',
				"FileName": "Afrikaans",
				"Variants": ["Full", "512KB"]
			}
		},

		"Arabic":
		{
			// Translation
			"Arabic":
			{
				"Description": '<p><i>Note: Some Sony Ericsson phones require a shaped Arabic version such as the K500, Z500, Z1010, K600, K700, K750, W800. If you have one of these phones or similar then choose the Arabic Shaped translation. This version will work fine on P990, M600, and similar phones.</i></p><p><b>Smith Van Dyck Translation</b></p><p>Priest Ghubreen Jebara, a learned Greek ecclesiastic in Beirut, said in a public address, in 1865, "But for the American missionaries, the Word of God had well-nigh perished out of the language; but now, through the labors of Dr. Eli Smith and Dr. Van Dyck, they have given us a translation so pure, so exact, so clear, and so classical, as to be acceptable to all classes and all sects."</p><p align="right">From <a href="http://www.arabicbible.com/christian/work_me.htm">Arabic Bible Outreach</a></p>',
				"FileName": "Arabic",
				"Variants": ["Full", "128KB", "512KB"]
			},
			
			// Translation
			"Arabic Shaped (for some Sony Ericsson phones)":
			{
				"Description": '<p><i>This is a shaped version required by some Sony Ericsson phones such as the K500, Z500, Z1010, K600, K700, K750, W800, or similar. On other phones this version made not look as good as the unshaped Arabic version.</i></p><p><b>Smith Van Dyck Translation</b></p><p>Priest Ghubreen Jebara, a learned Greek ecclesiastic in Beirut, said in a public address, in 1865, "But for the American missionaries, the Word of God had well-nigh perished out of the language; but now, through the labors of Dr. Eli Smith and Dr. Van Dyck, they have given us a translation so pure, so exact, so clear, and so classical, as to be acceptable to all classes and all sects."</p><p align="right">From <a href="http://www.arabicbible.com/christian/work_me.htm">Arabic Bible Outreach</a></p>',
				"FileName": "Arabic Shaped",
				"Variants": ["Full"]
			},

			// Translation
			"Arabic MIDP 1.0":
			{
				"Description": '<p><i>Note: This version is for older MIDP 1.0 phones, most phones today are MIDP 2.0 and can use the standard Arabic version which includes more features such as SMS. Some Sony Ericsson phones require a shaped Arabic version such as the T610, T630, Z600. If you have one of these phones or similar then choose the Arabic Shaped translation.</i></p><p><b>Smith Van Dyck Translation</b></p><p>Priest Ghubreen Jebara, a learned Greek ecclesiastic in Beirut, said in a public address, in 1865, "But for the American missionaries, the Word of God had well-nigh perished out of the language; but now, through the labors of Dr. Eli Smith and Dr. Van Dyck, they have given us a translation so pure, so exact, so clear, and so classical, as to be acceptable to all classes and all sects."</p><p align="right">From <a href="http://www.arabicbible.com/christian/work_me.htm">Arabic Bible Outreach</a></p>',
				"FileName": "Arabic MIDP 1.0",
				"Variants": ["Full"]
			},

			// Translation
			"Arabic MIDP 1.0 Shaped (for some Sony Ericsson phones)":
			{
				"Description": '<p><i>Note: This version is for older MIDP 1.0 phones, most phones today are MIDP 2.0 and can use the standard Arabic version which includes more features such as SMS. This is a shaped version required by some Sony Ericsson phones such as the T610, T630, Z600. On other phones this version made not look as good as the unshaped Arabic version.</i></p><p><b>Smith Van Dyck Translation</b></p><p>Priest Ghubreen Jebara, a learned Greek ecclesiastic in Beirut, said in a public address, in 1865, "But for the American missionaries, the Word of God had well-nigh perished out of the language; but now, through the labors of Dr. Eli Smith and Dr. Van Dyck, they have given us a translation so pure, so exact, so clear, and so classical, as to be acceptable to all classes and all sects."</p><p align="right">From <a href="http://www.arabicbible.com/christian/work_me.htm">Arabic Bible Outreach</a></p>',
				"FileName": "Arabic MIDP 1.0 Shaped",
				"Variants": ["Full"]
			},
		},

		"Bulgarian":
		{
			// Translation
			"Bulgarian":
			{
				"FileName": "Bulgarian",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},
		
		"Chinese Simplified":
		{
			// Translation
			"Chinese Simplified":
			{
				"FileName": "Chinese Simplified",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},
		
		"Chinese Traditional":
		{
			// Translation
			"Chinese Traditional":
			{
				"FileName": "Chinese Traditional",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},
			
		"Danish":
		{
			// Translation
			"Danish":
			{
				"FileName": "Danish",
				"Variants": ["Full", "128KB"]
			}
		},

		"Dutch":
		{
			// Translation
			"Dutch":
			{
				"FileName": "Dutch",
				"Variants": ["Full", "128KB"]
			}
		},

		"English":
		{
			// Translation
			"King James Version":
			{
				"Description":
				' <p>The Authorized King James Version is an English translation of the Christian Bible begun in 1604 and first published in 1611 by the Church of England.</p>  <p align="right"><i>From the <a href="http://en.wikipedia.org/wiki/KJV">Wikipedia article on KJV</a></i></p>',
				"FileName": "KJV",
				"Variants": ["Full", "128KB", "512KB"]
			},

			// Translation
			"King James Version MIDP 1.0":
			{
				"Description":
				'<p>Note: This version is for older MIDP 1.0 phones, most newer phones are MIDP 2.0.</p><p>The Authorized King James Version is an English translation of the Christian Bible begun in 1604 and first published in 1611 by the Church of England.</p> <p align="right"><i>From the <a href="http://en.wikipedia.org/wiki/KJV">Wikipedia article on KJV</a></i></p>',
				"FileName": "KJV MIDP 1.0",
				"Variants": ["Full"]
			},

			// Translation
			"King James Version Apocrypha":
			{
				"Description": 'The Apocrypha contains books not in the standard Biblical Canon, such as Maccabbees. The Apocrypha is included in Catholic and Eastern Orthodox Bibles but generally not in Protestant Bibles. <a href="http://en.wikipedia.org/wiki/Apocrypha">Wikipedia article on the Apocrypha</a>. This version does not include books from the standard canon, you will need to download the "King James Version" as well.',
				"FileName": "KJV Apocrypha",
				"Variants": ["Full"]
			}
		},

		"Finnish":
		{
			// Translation
			"Finnish":
			{
				"FileName": "Finnish",
				"Variants": ["Full", "128KB"]
			},
			
			// Translation
			"Finnish MIDP 1.0":
			{
				"FileName": "Finnish MIDP 1.0",
				"Variants": ["Full", "64KB"]
			}

		},

		"French":
		{
			// Translation
			"Louis Segond":
			{
				"FileName": "French",
				"Variants": ["Full"]
			}
		},
		
		"German":
		{
			// Translation
			"Luther":
			{
				"FileName": "German",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},

		"Hungarian":
		{
			// Translation
			"Hungarian":
			{
				"FileName": "Hungarian",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},

		"Indonesian":
		{
			// Translation
			"Indonesian":
			{
				"FileName": "Indonesian",
				"Variants": ["Full", "128KB"]
			}
		},

		"Norwegian":
		{
			// Translation
			"Norwegian":
			{
				"FileName": "Norwegian",
				"Variants": ["Full"]
			}
		},

		"Portuguese":
		{
			// Translation
			"Portuguese":
			{
				"FileName": "Portuguese",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},
	
		"Romani (Gypsy)":
		{
			// Translation
			"Romani - New Testament only":
			{
				"FileName": "Romani",
				"Variants": ["Full"]
			}
		},

		"Romanian":
		{
			// Translation
			"Romanian":
			{
				"FileName": "Romanian",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},

		"Russian":
		{
			// Translation
			"Russian":
			{
				"FileName": "Russian",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},

		"Slovakian":
		{
			// Translation
			"Slovakian":
			{
				"FileName": "Slovakian",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},

		"Spanish":
		{
			// Translation
			"Spanish":
			{
				"FileName": "Spanish",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},

		"Swedish":
		{
			// Translation
			"Swedish":
			{
				"FileName": "Swedish",
				"Variants": ["Full"]
			}
		},

		"Tagalog (Filipino)":
		{
			// Translation
			"Tagalog":
			{
				"FileName": "Tagalog",
				"Variants": ["Full", "128KB", "512KB"]
			}
		},
		
		"Ukrainian":
		{
			// Translation
			"Ukrainian":
			{
				"FileName": "Ukrainian",
				"Variants": ["Full"]
			}
		},

		"Vietnamese":
		{
			// Translation
			"Vietnamese":
			{
				"FileName": "Vietnamese",
				"Variants": ["Full"]
			}
		}
	}
} // End of Packages

function getPackages()
{
	return packages.Languages;
}

/**
 * Gets the property names of an object
 */
function keys(o) 
{    var array = [];    for (var propertyName in o) 
    {      array.push(propertyName);    }
        return array;}

/**
 * Generates the Wap directory numbers for each translation.
 * The Wap directory numbers increase as the translation names increase
 * alphabetically however this may not be the order of the above display
 * structure (becauses alpha isn't always the best order) so we sort
 * the arrays here when generating the numbers.
 */
function generateTranslationNumbers()
{
	var translationNumber = 1;
	
	var languageNames = keys(packages.Languages);
	languageNames.sort();
	for (var languageIndex in languageNames)
	{
		var languageObject = packages.Languages[languageNames[languageIndex]];
		var translationNames = keys(languageObject);
		translationNames.sort();
		for (var translationIndex in translationNames)
		{
			var translation = languageObject[translationNames[translationIndex]];
			
			translation.WapNumber = translationNumber;
			translationNumber += translation.Variants.length;
		}
	}
}
