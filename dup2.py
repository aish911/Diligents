#!/usr/bin/python
import os
import re
import stat
import sys
import time

from optparse import OptionParser


def hash_time(size, time):
    return (size ^ time) & (MAX_HASHES - 1);
def hash_size(size):
    return (size) & (MAX_HASHES - 1);
def hash_value(size, time, notimestamp):
    if notimestamp:
        return hash_size(size)
    else:
        return hash_time(size,time)
def already_hardlinked(
    st1,     
    st2 ):  
    result = ((st1[stat.ST_INO] == st2[stat.ST_INO]) and (st1[stat.ST_DEV] == st2[stat.ST_DEV]));
    return result
def eligible_for_hardlink(
    st1,        
    st2,      
    options):
    result = ((not already_hardlinked(st1, st2)) and        
              (st1[stat.ST_SIZE] == st2[stat.ST_SIZE]) and   
               (st1[stat.ST_SIZE] != 0 ) and                   
             ((st1[stat.ST_MODE] == st2[stat.ST_MODE]) or
              (options.contentonly)) and                    
             ((st1[stat.ST_UID] == st2[stat.ST_UID]) or      
              (options.contentonly)) and                   
             ((st1[stat.ST_GID] == st2[stat.ST_GID]) or      
              (options.contentonly)) and                    
             ((st1[stat.ST_MTIME] == st2[stat.ST_MTIME]) or  
              (options.notimestamp) or                      
              (options.contentonly)) and                    
             (st1[stat.ST_DEV] == st2[stat.ST_DEV])          
        )
    if None:
   
        print "\n***\n", st1
        print st2
        print "Already hardlinked: %s" % (not already_hardlinked(st1, st2))
        print "Modes:", st1[stat.ST_MODE], st2[stat.ST_MODE]
        print "UIDs:", st1[stat.ST_UID], st2[stat.ST_UID]
        print "GIDs:", st1[stat.ST_GID], st2[stat.ST_GID]
        print "SIZE:", st1[stat.ST_SIZE], st2[stat.ST_SIZE]
        print "MTIME:", st1[stat.ST_MTIME], st2[stat.ST_MTIME]
        print "Ignore date:", options.notimestamp
        print "Device:", st1[stat.ST_DEV], st2[stat.ST_DEV]
    return result


def file_equal(filename1, filename2, options):
    file1 = open(filename1,'rb')
    file2 = open(filename2,'rb')
    if not (file1 and file2):
        print "Error opening file in file_equal"
        print "Was attempting to open:"
        print "file1: %s" % filename1
        print "file2: %s" % filename2
        result = False
    else:
        if options.verbose >= 1:
            print "Comparing: %s" % filename1
            print "     to  : %s" % filename2
        buffer_size = 1024*1024
        while 1:
            buffer1 = file1.read(buffer_size)
            buffer2 = file2.read(buffer_size)
            if buffer1 != buffer2:
                result = False
                break
            if not buffer1:
                result = True
                break
        gStats.did_comparison()
    return result

def files_hardlinkable(file_info_1, file_info_2, options):
    filename1 = file_info_1[0]
    stat_info_1 = file_info_1[1]
    filename2 = file_info_2[0]
    stat_info_2 = file_info_2[1]

    if eligible_for_hardlink(stat_info_1, stat_info_2, options):
        if not options.samename:
            result = file_equal(filename1, filename2, options)
        else:
            basename1 = os.path.basename(filename1)
            basename2 = os.path.basename(filename2)
            if basename1 == basename2:
                result = file_equal(filename1, filename2, options)
            else:
                result = False
    else:
        result = False
    return result

def hardlink_files(sourcefile, destfile, stat_info, options):
    temp_name = destfile + ".$$$___cleanit___$$$"
    try:
        if not options.dryrun:
            os.rename(destfile, temp_name)
    except OSError, error:
        print "Failed to rename: %s to %s" % (destfile, temp_name)
        print error
        result = False
    else:
        try:
            if not options.dryrun:
                os.link(sourcefile, destfile)
        except:
            print "Failed to hardlink: %s to %s" % (sourcefile, destfile)
            try:
                os.rename(temp_name, destfile)
            except:
                print "BAD BAD - failed to rename back %s to %s" (temp_name, destfile)
            result = False
        else:
            if not options.dryrun:
                os.unlink ( temp_name)
            gStats.did_hardlink(sourcefile, destfile, stat_info)
            if options.verbose >= 1:
                if options.dryrun:
                    print "Did NOT link.  Dry run"
                size = stat_info[stat.ST_SIZE]
                print "Linked: %s" % sourcefile
                print"     to: %s, saved %s" % (destfile, size)
            result = True
    return result

def hardlink_identical_files(directories, filename, options):
    for exclude in options.excludes:
        if re.search(exclude, filename):
            return
    try:
        stat_info = os.stat(filename)
    except OSError:
        print "Unable to get stat info for: %s" % filename
        print "If running Python 1.5 this could be because the file is greater than 2 Gibibytes"
        return
    if not stat_info:
        return
    if stat.S_ISDIR(stat_info[stat.ST_MODE]):
        directories.append(filename)
    elif stat.S_ISREG(stat_info[stat.ST_MODE]):
        file_hash = hash_value(stat_info[stat.ST_SIZE], stat_info[stat.ST_MTIME],
            options.notimestamp or options.contentonly)
        gStats.found_regular_file()
        if options.verbose >= 2:
            print "File: %s" % filename
        work_file_info = (filename, stat_info)
        if file_hashes.has_key(file_hash):
            for (temp_filename,temp_stat_info) in file_hashes[file_hash]:
                if already_hardlinked(stat_info,temp_stat_info):
                    gStats.found_hardlink(temp_filename,filename,temp_stat_info)
                    break
            else:
                for (temp_filename,temp_stat_info) in file_hashes[file_hash]:
                    if files_hardlinkable(work_file_info, (temp_filename, temp_stat_info),options):
                        hardlink_files(temp_filename, filename, temp_stat_info, options)
                        break
                else:
                    file_hashes[file_hash].append(work_file_info)
        else:
            file_hashes[file_hash] = [work_file_info]


class Dup:
    def __init__(self):
        self.dircount = 0L                  
        self.regularfiles = 0L             
        self.comparisons = 0L               
        self.hardlinked_thisrun = 0L       
        self.hardlinked_previously = 0L;   
        self.bytes_saved_thisrun = 0L      
        self.bytes_saved_previously = 0L    
        self.hardlinkstats = []             
        self.starttime = time.time()        
        self.previouslyhardlinked = {}     

    def found_directory(self):
        self.dircount = self.dircount + 1
    def found_regular_file(self):
        self.regularfiles = self.regularfiles + 1
    def did_comparison(self):
        self.comparisons = self.comparisons + 1
    def found_hardlink(self,sourcefile, destfile, stat_info):
        filesize = stat_info[stat.ST_SIZE]
        self.hardlinked_previously = self.hardlinked_previously + 1
        self.bytes_saved_previously = self.bytes_saved_previously + filesize
        if not self.previouslyhardlinked.has_key(sourcefile):
            self.previouslyhardlinked[sourcefile] = (stat_info,[destfile])
        else:
            self.previouslyhardlinked[sourcefile][1].append(destfile)
    def did_hardlink(self,sourcefile,destfile,stat_info):
        filesize = stat_info[stat.ST_SIZE]
        self.hardlinked_thisrun = self.hardlinked_thisrun + 1
        self.bytes_saved_thisrun = self.bytes_saved_thisrun + filesize
        self.hardlinkstats.append((sourcefile, destfile))
    def print_stats(self, options):
        print "\n"
        print "Hard linking Statistics:"
        if self.previouslyhardlinked and options.printprevious:
            keys = self.previouslyhardlinked.keys()
            keys.sort()
            print "Files Previously Hardlinked:"
            for key in keys:
                stat_info, file_list = self.previouslyhardlinked[key]
                size = stat_info[stat.ST_SIZE]
                print "Hardlinked together: %s" % key
                for filename in file_list:
                    print "                   : %s" % filename
                print "Size per file: %s  Total saved: %s" % (size,size * len(file_list))
        if self.hardlinkstats:
            if options.dryrun:
                print "Statistics reflect what would have happened if not a dry run"
            print "Files Hardlinked this run:"
            for (source,dest) in self.hardlinkstats:
                print"Hardlinked: %s" % source
                print"        to: %s" % dest
        print "Directories           : %s" % self.dircount
        print "Regular files         : %s" % self.regularfiles
        print "Comparisons           : %s" % self.comparisons
        print "Hardlinked this run   : %s" % self.hardlinked_thisrun
        print "Total hardlinks       : %s" % (self.hardlinked_previously + self.hardlinked_thisrun)
        print "Bytes saved this run  : %s (%s)" % (self.bytes_saved_thisrun, memory_no(self.bytes_saved_thisrun))
        totalbytes = self.bytes_saved_thisrun + self.bytes_saved_previously;
        print "Total bytes saved     : %s (%s)" % (totalbytes, memory_no(totalbytes))
        print "Total run time        : %s seconds" % (time.time() - self.starttime)
	#os.system("ls -liR ")

def memory_no( number ):
    if number  > 1024 ** 3:
        return ("%.3f gibibytes" % (number / (1024.0 ** 3)))
    if number  > 1024 ** 2:
        return ("%.3f mebibytes" % (number / (1024.0 ** 2)))
    if number  > 1024:
        return ("%.3f kibibytes" % (number / 1024.0))
    return ("%d bytes" % number)

def parse_command_line():
    usage = "usage: %prog [options] directory [ directory ... ]"
    parser = OptionParser()
    parser.add_option("-f", "--filenames-equal", help="Filenames have to be identical",
        action="store_true", dest="samename", default=False,)
    parser.add_option("-n", "--dry-run", help="Do NOT actually hardlink files",
        action="store_true", dest="dryrun", default=False,)
    parser.add_option("-p", "--print-previous", help="Print previously created hardlinks",
        action="store_true", dest="printprevious", default=False,)
    parser.add_option("-q", "--no-stats", help="Do not print the statistics",
        action="store_false", dest="printstats", default=True,)
    parser.add_option("-t", "--timestamp-ignore",
        help="File modification times do NOT have to be identical",action="store_true", dest="notimestamp", default=False,)
    parser.add_option("-c", "--content-only",help="Only file contents have to match",action="store_true", dest="contentonly", default=False,)
    parser.add_option("-v", "--verbose",help="Verbosity level (default: %default)", metavar="LEVEL",action="store", dest="verbose", type="int", default=1,)
    parser.add_option("-x", "--exclude",help="Regular expression used to exclude files/dirs (may specify multiple times)", metavar="REGEX",
action="append", dest="excludes", default=[],)
    (options, args) = parser.parse_args()
    if not args:
        parser.print_help()
	#os.system("python dup2.py . ")
        print "Error: Must supply one or more directories"
        #sys.exit(1)
    args = [os.path.abspath(os.path.expanduser(dirname)) for dirname in args]
    for dirname in args:
        if not os.path.isdir(dirname):
            parser.print_help()
            print "Error: %s is NOT a directory" % dirname
            sys.exit(1)
    return options, args

debug = None
debug1 = None
MAX_HASHES = 128 * 1024
gStats = Dup()
file_hashes = {}
def main():
	top = sys.argv[1]
	d = {}

	for root, dirs, files in os.walk(top, topdown=False):
    		for name in files:
        		fn = os.path.join(root, name)
        		basename, extension = os.path.splitext(name)

        		basename = basename.lower() # ignore case

        		if d.has_key(basename):
	    			print "LIST OF DUPLICATE FILES \n"
            			print d[basename]
            			print fn
        		else:
            			d[basename] = fn

    	#os.system("ls -liR")
    	str=raw_input("Do u want to remove duplicate files?(Y/N)")
    	if str=='Y':
		os.system("ls -liR")
    		options, directories = parse_command_line()

    		while directories:
        		directory = directories[-1] + '/'
        		del directories[-1]
        		if not os.path.isdir(directory):
            			print "%s is NOT a directory!" % directory
        		else:
            			gStats.found_directory()
            			try:
                			dir_entries = os.listdir(directory)
            			except OSError:
                			print "Error: Unable to do an os.listdir on: %s  Skipping..." % directory
                			continue
            			for entry in dir_entries:
                			pathname = os.path.normpath(os.path.join(directory,entry))
                			if os.path.islink(pathname):
                	    			if debug1: print "%s: is a symbolic link, ignoring" % pathname
                	    			continue
                			if debug1 and os.path.isdir(pathname):
                	    			print "%s is a directory!" % pathname
                			hardlink_identical_files(directories, pathname, options)
    		if options.printstats:
        		gStats.print_stats(options)
    		else:
			print "Exittt !!!"	

if __name__ == '__main__':
    main()
