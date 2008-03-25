#!
set version=2.2.5

if (! -e $version) then
	mkdir $version
endif

foreach file ( * )

	# if file is a directory and it contains a Collections.txt
	# file then process it
	if (-d "$file" && -e "$file/$file.txt") then
		set zipFile = "../../Release/$version/Go Bible $file $version.zip"
		echo $zipFile
		if (! -e "$zipFile") then
			java -Xmx128m -jar ../GoBibleCreator/src/GoBibleCreator.jar "$file/$file.txt"
			cd "$file"
			zip "$zipFile" *.jad *.jar
			cd ..
		endif
	endif
end


