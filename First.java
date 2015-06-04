package isha;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.lang.String;
import java.io.*;

public class First {
	 public void removeLineFromFile(String file, String lineToRemove) {
		 
		    try {
		 
		      File inFile = new File(file);
		      
		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }
		       
		      //Construct the new file that will later be renamed to the original filename. 
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		      
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		      
		      String line = null;
		 
		      //Read from the original file and write to the new 
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {
		        
		        if (!line.trim().equals(lineToRemove)) {
		 
		          pw.println(line);
		          pw.flush();
		        }
		      }
		      pw.close();
		      br.close();
		      
		      //Delete the original file
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file");
		        return;
		      } 
		      
		      //Rename the new file to the filename the original file had.
		      if (!tempFile.renameTo(inFile))
		        System.out.println("Could not rename file");
		      
		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
		  }
		 
	public static void main(String[] args)
	{
		
				
			 Scanner sc = new Scanner(System.in);
			 int ch;
			 while(true)
			 {
			 System.out.println("Enter choice ...\n1)append \n2)delete \n3)Which version to display\n4)exit");
			 ch=sc.nextInt();
			
			 switch(ch)
			 {
			 case 1 :
				 try{
					 
					 File cnt_file =new File("/home/ash/git/isharepository/isha/src/isha/cnt_file.txt");
						BufferedReader new_br = null;
						
						if(!cnt_file.exists()){
					    	   try {
								cnt_file.createNewFile();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    }
						BufferedWriter cnt_file_bw = null;
						String file_str;
						String cnt_str = "";
						int cnt_cnt = 0;
						try {
							BufferedReader file_br = new BufferedReader(new FileReader(cnt_file));
							try {
								while((file_str = file_br.readLine())!=null)
								{
									cnt_str = file_str;
									cnt_cnt = Integer.parseInt(cnt_str);
									cnt_cnt++;
									System.out.println(cnt_cnt);
									//System.out.println(cnt_str);
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					 
					 BufferedWriter bw =null;
					 
					 	
					 	
					 	FileReader fr=null;
					 	//Specify the file name and path here
					 	LineNumberReader lnr=null;
					 						 	
					 	System.out.println("Enter line ...\n");
					 	//char content[] =new char[100]; 
					 	
					 	String a = sc.next();
					 	
					 	//strcpy(content,a);
					 	
					 	char content[] = a.toCharArray();
					 	int len = content.length;
					 	
				    	File file =new File("/home/ash/git/isharepository/isha/src/isha/a.txt");
				    	fr = new FileReader("/home/ash/git/isharepository/isha/src/isha/a.txt");
				    	if(!file.exists()){
					    	   file.createNewFile();
					    }
			                
				        lnr = new LineNumberReader(fr);		    	
				    	
				    	 
				        int cnt=0;
				        while((a=lnr.readLine())!=null)
				         {
				            cnt=lnr.getLineNumber();
				            //System.out.println("count "+ cnt);
				           
					  }
				        try{
				        if(cnt<20)
				    	{
				        if(len>10){
				    	for(int i=0;i<10;i++)
				    	{
				    	FileWriter fw = new FileWriter(file,true);
				    	bw = new BufferedWriter(fw);
				    	
				    	/*if(content[i] == '\0')
				    	{
				    		break;
				    	}*/
				    	bw.write(content[i]);
				    	
				    	
				    
				    	//Closing BufferedWriter Stream
				    
				    	bw.close();
				    	
				    	
				    	
				    	}
				    	
				    	new_br = new BufferedReader(new FileReader(file));
						try {
							while((file_str = new_br.readLine())!=null)
							{
								cnt_str = cnt_str+file_str+"\n";
								//cnt_cnt = Integer.parseInt(cnt_str);
								//cnt_cnt++;
								//System.out.println(cnt_cnt);
								//System.out.println(cnt_str);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						System.out.println(cnt_str);
				    	
				    	File file_point =new File("/home/ash/git/isharepository/isha/src/isha/version"+cnt_cnt+".txt");
				    	
				    	if(!file_point.exists()){
					    	   file_point.createNewFile();
					    }
				    	
				    	FileWriter cnt_file_fw = new FileWriter(file_point,true);
				    	cnt_file_bw = new BufferedWriter(cnt_file_fw);
				    	cnt_file_bw.write(cnt_str);
				    	cnt_file_bw.close();
				    	
				    	FileWriter last_fw = new FileWriter(cnt_file,false);
				    	BufferedWriter last_file_bw = new BufferedWriter(last_fw);
				    	String x = Integer.toString(cnt_cnt);
				    	last_file_bw.write(x);
				    	last_file_bw.close();
				    					    	
				        }
				        else
				        {
				        	
				        	
				        		for(int m = 0;m<len;m++)
				        		{
				        			FileWriter fw = new FileWriter(file,true);
							    	bw = new BufferedWriter(fw);
							    	
							    	bw.write(content[m]);
								    
							    	//Closing BufferedWriter Stream
							    
							    	bw.close();
				        		}
				        		
				        			new_br = new BufferedReader(new FileReader(file));
								try {
									while((file_str = new_br.readLine())!=null)
									{
										cnt_str = cnt_str+file_str+"\n";
										//cnt_cnt = Integer.parseInt(cnt_str);
										//cnt_cnt++;
										//System.out.println(cnt_cnt);
										//System.out.println(cnt_str);
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								System.out.println(cnt_str);
						    	
						    	File file_point =new File("/home/ash/git/isharepository/isha/src/isha/version"+cnt_cnt+".txt");
						    	
						    	if(!file_point.exists()){
							    	   file_point.createNewFile();
							    }
						    	
						    	FileWriter cnt_file_fw = new FileWriter(file_point,true);
						    	cnt_file_bw = new BufferedWriter(cnt_file_fw);
						    	cnt_file_bw.write(cnt_str);
						    	cnt_file_bw.close();
						    	
						    	FileWriter last_fw = new FileWriter(cnt_file,false);
						    	BufferedWriter last_file_bw = new BufferedWriter(last_fw);
						    	String x = Integer.toString(cnt_cnt);
						    	last_file_bw.write(x);
						    	last_file_bw.close();
						    
				        			
				        	}
				        
				        FileWriter fw = new FileWriter(file,true);
				    	bw = new BufferedWriter(fw);
				    	
				    	bw.write("\n");
				    	
				    	//Closing BufferedWriter Stream
				    
				    	bw.close();
				    	
				    	cnt++;
				    	System.out.println("Data successfully appended at the end of file");
				    	
				    
				    	   	
				    	}
			            else 
			            {
			            	System.out.println("Max limit is 20");
			            }
				        }catch(Exception e)
				        {
				        	e.printStackTrace();
				        }

				      }catch(IOException ioe){
				         System.out.println("Exception occurred:");
				    	 ioe.printStackTrace();
				       }
				
				 break;
			 case 2:
				 First util=new First();
				 System.out.println("Enter string that u want to remove from file");
		    	   //System.out.println("Enter line number");
		    	   String b=sc.next();
		    	   //int i=sc.nextInt();
				 util.removeLineFromFile("/home/ash/git/isharepository/isha/src/isha/a.txt", b);
				 break;
				 
			 case 3:
				 System.out.println("Which version to display(1,2,3..)");
				 String v_no = sc.next();
				 String read_line;
				 String whole_line="";
				 File v_to_open =new File("/home/ash/git/isharepository/isha/src/isha/version"+v_no+".txt");
			    	try{
			    	if(!v_to_open.exists()){
				    	   //file_point.createNewFile();
			    		System.out.println("File does not exist");
				    }
			    	}
			    	catch(Exception e)
			    	{
			    		
			    	}
			    	
				BufferedReader v_br = null;
				try {
					v_br = new BufferedReader(new FileReader(v_to_open));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					try {
						while((read_line = v_br.readLine())!=null)
						{
							whole_line = whole_line+read_line+"\n";
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(whole_line);
				 
				 break;
			 case 4:
				 System.out.println("Operation Finished");
				 System.exit(0);
				
			}  
			 
	}
				 
	}	
			 
}

