#!

# Version
set version = "2.2.6"

# Create a wap directory if it doesn't already exist
set wapDir = "../wap"
mkdir -p "$wapDir"

# Number each directory, numbered directories are simpler to type in the phone
# eg. http://wap.jolon.org/1
set directoryNumber = 1

echo "<title>Go Bible</title>" > "$wapDir/welcome.html"

foreach language ( * )
	# if file is a directory then it is a language directory that contains translations
	if (-d "$language") then
	
		#echo $language
	
		cd "$language"
		
		foreach translation (*)
			# if file is a directory and it contains a Collections.txt file
			# and it contains some JAR files then process it
			if (-d "$translation" && -e "$translation/$translation.txt") then
				echo $translation

				echo "<a href=$directoryNumber/welcome.html>$translation</a><br/>" >> "../$wapDir/welcome.html"

				# Create a numbered directory for this translation
				mkdir -p "../$wapDir/$directoryNumber"
				
				# Copy in JAD files modifying the MIDlet-Jar-URL property
				cd "$translation"

				# Copy in JAR files
				cp *.jar "../../$wapDir/$directoryNumber"

				foreach jadFile (*.jad)
					#echo $jadFile
					set urlBase = "http://wap.jolon.org/$directoryNumber"
					
					# Looks for a line starting with "MIDlet-Jar-URL: " followed
					# by any characters.  Replaces it with "MIDlet-Jar-URL: " followed
					# by the contents of our urlBase variable followed by what originally
					# followed the "MIDlet-Jar-URL: " which would be XXXX.jar 
					# (indicated by \2 which means the second group, where groups
					# are formed by the parentheses in the regular expression).
					sed "s|\(MIDlet-Jar-URL:\ \)\([^ ]*\)|MIDlet-Jar-URL:\ $urlBase/\2|" "$jadFile" > "../../$wapDir/$directoryNumber/$jadFile"
				end
				cd ..
				
				# Create a HTML file for each translation
				# Create links to each jad file
				cd "../$wapDir/$directoryNumber"

				set htmlFile = "welcome.html"	
				echo "<META http-equiv='Content-Type' content='text/html; charset=UTF-8'>" > "$htmlFile"
				echo "<title>$translation</title>" >> "$htmlFile"
				
				foreach jadFile (*.jad)
					#echo $jadFile
					set variant=`basename "$jadFile" ".jad"`
					set collectionName = `awk -F': ' '/MIDlet-Name/ {print $2}' "$jadFile"`
					echo "<a href=http://wap.jolon.org/$directoryNumber/$jadFile>" $collectionName "</a><br/>" >> "$htmlFile"
				end
				cd "../../Collections/$language"
				
				set directoryNumber = `expr $directoryNumber + 1`
			endif
		end
		cd ..
	endif
end


