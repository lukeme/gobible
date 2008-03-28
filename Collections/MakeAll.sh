#!

foreach file ( * )
	# if file is a directory and it contains a Collections.txt
	# file then process it
	if (-d "$file" && -e "$file/$file.txt") then
		./MakeCollection.sh "$file"
	endif
end


