import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
 
public class Web {
 
	public static void main(String[] args) {
		int cnt = 0,cnt1 = 0,cnt2 = 0;
		
		int cnt_twi_14 = 0,cnt_twi_15 = 0,cnt_twi_16 = 0,cnt_twi_17 = 0,cnt_twi_18 = 0,cnt_twi_19 = 0;
		int cnt_kin_14 = 0,cnt_kin_15 = 0,cnt_kin_16 = 0,cnt_kin_17 = 0,cnt_kin_18 = 0,cnt_kin_19 = 0;
		int cnt_rec_14 = 0,cnt_rec_15 = 0,cnt_rec_16 = 0,cnt_rec_17 = 0,cnt_rec_18 = 0,cnt_rec_19 = 0;
		int total_hits = 0, avg_hits = 0;
		int rra = 0;
		int req_cnt = 0;
		int res_cnt = 0;
		int head_cnt = 0;
		int st_code_200 = 0;
		int st_code_206 = 0;
		int st_code_302 = 0;
		int bandwdth = 0;
		int st_code_304 = 0;
		int count = 0,twibuz_cnt = 0,kinner_cnt = 0,recipe_cnt = 0,count_status = 0 ;
		int i = 0,j = 0,k_s = 0;
		int cli_failed_req = 0;
		int ser_failed_req = 0;
		int m = 0,a = 0,b = 0,c = 0;
		String[] splitted_arr = null; 
		long diff = 1800000;
		
		int visitor_cnt = 0;
		
		BufferedReader br = null;
		String[] site_names = new String[2778];
		String[] main_array = new String[2778];
		String[] visitor_names = new String[2778];
		String[] status_code = new String[2778];
		String client_error = "404";
		String server_error = "500";
		String [] req_res_arr = new String[2778];
		
		String date_time;
		String date_time_split[];
		
		//Date[] date_array;
		
		String split_by_space[];
		String[] ip_and_rest;
		String[] ipk_and_rest;
		String[] ipr_and_rest;
		String[] ips = new String[1913];
		String[] ip_k = new String[850];
		String[] ip_r = new String[17];
		List twibuzz_line_list = new ArrayList<>();
		List Kinner_line_list = new ArrayList<>();
		List Recipe_line_list = new ArrayList<>();
		
		List date_list = new ArrayList<Date>();
		
		List twibuzz_14 = new ArrayList<>();
		List kinner_14 = new ArrayList<>();
		List Recipe_14 = new ArrayList<>();
		
		//List Recipe_line_list = new ArrayList<>();
		
		try {
 
			String sCurrentLine=null;
 
			br = new BufferedReader(new FileReader("weblog.txt"));
			
			while ((sCurrentLine = br.readLine()) != null) 
			{
				//Main Array : contains complete individual lines
				main_array[m] = sCurrentLine;
				m++;
				total_hits++;
				//Splitted array: Splitted by Space
				
				splitted_arr = sCurrentLine.split(" ");
				//bandwdth = bandwdth+Integer.parseInt(splitted_arr[9]);
				//System.out.println(splitted_arr[9]);
				if(splitted_arr[9].equals("-"))
				{
					//bandwdth = bandwdth+Integer.parseInt(splitted_arr[9]);
				}
				else
				{
					bandwdth = bandwdth+Integer.parseInt(splitted_arr[9]);
				}
				String[] splitted_name = splitted_arr[6].split("/",2);
				
				//Contains name of sites
					site_names[i] = splitted_name[0];
					i++;
					
					
				//contains names of visitors	
					visitor_names[j] = splitted_arr[0];
					j++;
				
				// Status code
					status_code[k_s] = splitted_arr[8];
					k_s++;
				//request response	
					req_res_arr[rra] = splitted_arr[5];
					rra++;
			}
			double tot_bandwdth = (double)bandwdth/(double)1024*1024;
			double roudoff = (double)Math.round(tot_bandwdth);
//Converting site List to array and printing
			//System.out.println(roudoff);
			List dist_sites = printDistinctElements(site_names);
			String[] dist_site_array = new String[dist_sites.size()];
			dist_sites.toArray(dist_site_array);
			//System.out.println(dist_site_array.length);
			avg_hits = total_hits/6;
			Iterator iterator = dist_sites.iterator();
			while(iterator.hasNext()) {
			  String element = (String) iterator.next();
			  //System.out.println(element);
			}
			
			List dist_visitors = printDistinctElements(visitor_names);
			String[] dist_visitor_array = new String[dist_visitors.size()];
			dist_visitors.toArray(dist_visitor_array);
			
			//System.out.println("\nTotal visitors are :\n");
			Iterator iterator_visitor = dist_visitors.iterator();
			while(iterator_visitor.hasNext()) {
			  String element = (String) iterator_visitor.next();
			  //System.out.println(element);
			  count++;
			}
			/*System.out.println("\nTotal Unique IPs : "+count+"\n");*/
			/*
			for(int r = 0;r<2778;r++)
			{
				if(main_array[r].contains(dist_visitor_array[s]))
				{
					
				}
			}
			*/
			for(int k = 0;k<2778;k++)
			{
				if(main_array[k].contains(dist_site_array[0])||main_array[k].contains(dist_site_array[2]))
				{
					
					if((main_array[k].contains("14/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_twi_14++;
						
						split_by_space = main_array[k].split(" ");
						date_time = split_by_space[3];
						//System.out.println(date_time);
						date_time_split = date_time.split(":",2);
						
						SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
						
						try {
							 
							Date date = formatter.parse(date_time_split[1]);
							date_list.add(date);
							//System.out.println(date);
							//System.out.println(formatter.format(date));
					 
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					
					else	if((main_array[k].contains("15/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_twi_15++;
					}	
					else	if((main_array[k].contains("16/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_twi_16++;
					}	
					else	if((main_array[k].contains("17/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_twi_17++;
					}	
					else	if((main_array[k].contains("18/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_twi_18++;
					}	
					else	if((main_array[k].contains("19/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_twi_19++;
					}	
					
					
					twibuzz_line_list.add(main_array[k]);
					ip_and_rest = main_array[k].split(" ",2);						
					ips[a] = ip_and_rest[0];
					a++;
					cnt++;
				}
			}
			
			Iterator iterator_date_list = date_list.iterator();
			while(iterator_date_list.hasNext()) {
			  Date element = (Date)  iterator_date_list.next();
			
			}
			/*
			for(int p = 1;p<date_list.size();p++)
			  {
				  //date_list.get(p) - date_list.get(p-1);
				  Date d1 = (Date) date_list.get(p);
				  Date d0 = (Date) date_list.get(p-1);
				  if(d1.getTime() - d0.getTime() > diff)
				  {
					  System.out.println(d1);
					  visitor_cnt++;
				  }
				  //System.out.println(d0);
				  
			  }
			
			System.out.println(visitor_cnt);*/
			
			//System.out.println("\nHits on 14 May 2009 to twibuzz.com "+cnt_twi_14+"\n");
			//cnt = cnt -2;
			//System.out.println("\nNumber of hits to twibuzz.com = "+cnt);
			
			for(int l = 0;l<2778;l++)
			{
				if(main_array[l].contains(dist_site_array[1])||main_array[l].contains(dist_site_array[3]))
				{
					
					if((main_array[l].contains("14/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_kin_14++;
					}
					
					else	if((main_array[l].contains("15/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_kin_15++;
					}	
					else	if((main_array[l].contains("16/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_kin_16++;
					}	
					else	if((main_array[l].contains("17/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_kin_17++;
					}	
					else	if((main_array[l].contains("18/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_kin_18++;
					}	
					else	if((main_array[l].contains("19/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_kin_19++;
					}	
					
					
					cnt1++;
					Kinner_line_list.add(main_array[l]);
					ipk_and_rest = main_array[l].split(" ",2);
					ip_k[b] = ipk_and_rest[0];
					b++;
					
				}
			}
			//System.out.println("\nNumber of hits to kinneryandrajan.com = "+cnt1+"\n");
			
			for(int n = 0;n<2778;n++)
			{
				if(main_array[n].contains(dist_site_array[4])||main_array[n].contains(dist_site_array[5]))
				{
					
					if((main_array[n].contains("14/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_rec_14++;
					}
					
					else	if((main_array[n].contains("15/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_rec_15++;
					}	
					else	if((main_array[n].contains("16/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_rec_16++;
					}	
					else	if((main_array[n].contains("17/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_rec_17++;
					}	
					else	if((main_array[n].contains("18/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_rec_18++;
					}	
					else	if((main_array[n].contains("19/May/2009")))/* && (main_array[k].contains("www.twibuzz.com/")))*/
					{
						//twibuzz_14.add(e)
						cnt_rec_19++;
					}	
					
					
					cnt2++;
					Recipe_line_list.add(main_array[n]);
					ipr_and_rest = main_array[n].split(" ",2);
					ip_r[c] = ipr_and_rest[0];
					c++;
					
				}
			}
			//System.out.println("\nNumber of hits to recipewithme.com = "+cnt2);
			
//Converting visitor List to array and printing
						
//Failed requests print
			for(int er = 0;er<2778;er++)
			{
				if(status_code[er].contains("200"))
				{
					st_code_200++;
				}
			}
			for(int er = 0;er<2778;er++)
			{
				if(status_code[er].contains("206"))
				{
					st_code_206++;
				}
			}
			for(int er = 0;er<2778;er++)
			{
				if(status_code[er].contains("302"))
				{
					st_code_302++;
				}
			}
			
			for(int er = 0;er<2778;er++)
			{
				if(status_code[er].contains("304"))
				{
					st_code_304++;
				}
			}
			for(int er = 0;er<2778;er++)
			{
				if(status_code[er].contains(client_error))
				{
					cli_failed_req++;
				}
			}
			
			
			for(int er1 = 0;er1<2778;er1++)
			{
				if(status_code[er1].contains(server_error))
				{
					ser_failed_req++;
				}
			}
			for(int rra1 = 0;rra1<2778;rra1++)
			{
				if(req_res_arr[rra1].contains("GET"))
				{
					req_cnt++;
				}
			}
			for(int rra2 = 0;rra2<2778;rra2++)
			{
				if(req_res_arr[rra2].contains("POST"))
				{
					res_cnt++;
				}
			}
			for(int rra3 = 0;rra3<2778;rra3++)
			{
				if(req_res_arr[rra3].contains("HEAD"))
				{
					head_cnt++;
				}
			}
			//System.out.println("Failed requests are : "+cli_failed_req+"\n");

//Converting status code list to array and print
			List dist_status = printDistinctElements(status_code);
			String[] status_code_array = new String[dist_status.size()];
			dist_status.toArray(status_code_array);
			
			//System.out.println("\nTotal status code are :\n");
			Iterator iterator_status = dist_status.iterator();
			while(iterator_status.hasNext()) {
			  String element = (String) iterator_status.next();
			  //System.out.println(element);
			  count_status++;
			}
			//System.out.println("\nTotal status code : "+count_status+"\n");
			
			
			//twibuzz_line_array[]
			String[] twibuzz_line_array = new String[twibuzz_line_list.size()];
			twibuzz_line_list.toArray(twibuzz_line_array);
			
			//twibuzz ips : distinct visitors
			List twibuzz_ips = printDistinctElements(ips);
			String[] twibuzz_ips_array = new String[twibuzz_ips.size()];
			twibuzz_ips.toArray(twibuzz_ips_array);
			
			//System.out.println("Twibuzz Visitors are :\n");
			Iterator iterator_ipt = twibuzz_ips.iterator();
			while(iterator_ipt.hasNext()) {
			  String element = (String) iterator_ipt.next();
			  //System.out.println(element);
			  twibuz_cnt++;
			}
			//System.out.println("Total visitors to twibuzz.com : "+twibuz_cnt+"\n");
			

			//Kinner_line_array[]
			String[] Kinner_line_array = new String[Kinner_line_list.size()];
			Kinner_line_list.toArray(Kinner_line_array);
			
			//kinner ips : distinct visitors
			List kinner_ips = printDistinctElements(ip_k);
			String[] kinner_ips_array = new String[kinner_ips.size()];
			kinner_ips.toArray(kinner_ips_array);
			
			//System.out.println("Kinner Visitors are :\n");
			Iterator iterator_ipk = kinner_ips.iterator();
			while(iterator_ipk.hasNext()) {
			  String element = (String) iterator_ipk.next();
			  //System.out.println(element);
			  kinner_cnt++;
			}
			//System.out.println("Total visitors to kinneryandrajan.com : "+kinner_cnt+"\n");
			
			//Recipe_line_array[]
			String[] Recipe_line_array = new String[Recipe_line_list.size()];
			Recipe_line_list.toArray(Recipe_line_array);
			
			//Recipe ips : distinct visitors
			List recipe_ips = printDistinctElements(ip_r);
			String[] recipe_ips_array = new String[recipe_ips.size()];
			recipe_ips.toArray(recipe_ips_array);
			
			//System.out.println("Recipe Visitors are :\n");
			Iterator iterator_ipr = recipe_ips.iterator();
			while(iterator_ipr.hasNext()) {
			  String element = (String) iterator_ipr.next();
			  //System.out.println(element);
			  recipe_cnt++;
			}
			//System.out.println("Total visitors to recipewithme.com : "+recipe_cnt+"\n");
	
			
//--------------------------------------------------------------------------------------
			System.out.println("___________________________________________________________________\n");
			System.out.println("\n                 Analysing weblog.txt file                                      \n\n");
			
			System.out.println("Total                                  \n");
			System.out.println("Total Hits                             "+total_hits);
			System.out.println("Average Hits per day                   "+total_hits/6);
			//System.out.println("Total Visitors                                   " +total_hits+"\n");
			//System.out.println("Average Visitors per day             " +avg_hits+"\n");
			System.out.println("Unique IPs                             " +count+"");
			System.out.println("Total requests                         " +req_cnt+"");
			System.out.println("Total responses                        " +res_cnt+"");
			System.out.println("Total HEAD requests                    " +head_cnt+"\n");
			System.out.println("Status Code                            Count");
			System.out.println("200                                    "+st_code_200);
			System.out.println("206                                    "+st_code_206);
			System.out.println("302                                    "+st_code_302);
			System.out.println("304                                    "+st_code_304);
			System.out.println("404(Client Failed requests)            "+cli_failed_req);
			System.out.println("500(Server Failed requests)            "+ser_failed_req);
						
			System.out.println("____________________________________________________________________\n");
			System.out.println("                  Data about twibuzz.com      \n");
			System.out.println("Total Hits                            "+cnt);
			System.out.println("Average Hits                          "+cnt/6);
			//System.out.println("Total Visitors                      " +cnt+"\n");
			//System.out.println("Average Visitors                    " +cnt/6+"\n");
			System.out.println("Unique IPs                            "+twibuz_cnt+"\n");
			
			System.out.println("Date Wise analysis \n");
			System.out.println("Date                                  Total Hits                ");
			System.out.println("14 May 2009                           "+cnt_twi_14+"            ");
			System.out.println("15 May 2009                           "+cnt_twi_15+"            ");
			System.out.println("16 May 2009                           "+cnt_twi_16+"            ");
			System.out.println("17 May 2009                           "+cnt_twi_17+"            ");
			System.out.println("18 May 2009                           "+cnt_twi_18+"            ");
			System.out.println("19 May 2009                           "+cnt_twi_19+"     \n\n       ");
			
			
			System.out.println("____________________________________________________________________\n");
			System.out.println("                 Data about kinneryandrajan.com                                \n");
			System.out.println("Total Hits                            "+cnt1);
			System.out.println("Average hits                          "+cnt1/6);
			//System.out.println("Total visitors                        " +cnt1+"\n");
			//System.out.println("Average visitors                      " +cnt1/6+"\n\n");
			System.out.println("Unique IPs                            "+kinner_cnt+"\n");
			
			System.out.println("Date Wise analysis \n");
			System.out.println("Date                                  Total hits    ");
			System.out.println("14 May 2009                           "+cnt_kin_14+"");
			System.out.println("15 May 2009                           "+cnt_kin_15+"");
			System.out.println("16 May 2009                           "+cnt_kin_16+"");
			System.out.println("17 May 2009                           "+cnt_kin_17+"");
			System.out.println("18 May 2009                           "+cnt_kin_18+"");
			System.out.println("19 May 2009                           "+cnt_kin_19+"");
			
			
			System.out.println("____________________________________________________________________\n");
			System.out.println("                   Data about recipewithme.com                                \n");
			System.out.println("Total Hits                            "+cnt2);
			System.out.println("Average hits                          "+cnt2/6);
			//System.out.println("Total visitors                                      " +cnt2+"\n");
			//System.out.println("Average visitors                                    " +cnt2/6+"\n\n");
			System.out.println("Unique ips                            "+recipe_cnt+"\n");
			System.out.println("Date Wise analysis \n");
			System.out.println("Date                                  Total Hits        ");
			System.out.println("14 May 2009                           "+cnt_rec_14+"    ");
			System.out.println("15 May 2009                           "+cnt_rec_15+"    ");
			System.out.println("16 May 2009                           "+cnt_rec_16+"    ");
			System.out.println("17 May 2009                           "+cnt_rec_17+"    ");
			System.out.println("18 May 2009                           "+cnt_rec_18+"    ");
			System.out.println("19 May 2009                           "+cnt_rec_19+"    ");
			
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
 
	}
	
	 public static List printDistinctElements(String[] arr)
	 {
         
         List list = new ArrayList<String>();
         
	        for(int i=0;i<arr.length;i++){
	            boolean isDistinct = false;
	            for(int j=0;j<i;j++){
	                if(arr[i].equals(arr[j])){
	                    isDistinct = true;
	                    break;
	                }
	            }
	            if(!isDistinct){
	            	
	            	 
	            	list.add(arr[i]);
	                
	            }
	            
	        }
	        return list;
	    }
	     
}
