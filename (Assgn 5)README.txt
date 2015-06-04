Steps to run the 5th assignment : Write a program to list duplicate files from hard drive

1. Run the program as :
 		python dup2.py <directory_name>
		eg: python dup2.py .   ("." represents root directory)
		    python dup2.py a1 a2

2. Output is as follows:
	i) List of duplicate files
	ii) User option to remove the file
	iii) If Yes, inode numbers of the duplicate files will be displayed as equal. Check the output,the inode numbers will be same.
	iv) It will also show the amount of memory saved.
	v) If No, Exit!!

3. Run the "ls -liR" command to verify the inode numbers of the duplicate files.
