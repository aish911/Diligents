#include <string>
#include <iostream>
#include <stdio.h>
#include <pcap.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netinet/if_ether.h>
#include <net/ethernet.h>
#include <netinet/ether.h>

using namespace std;
#define ARP_REQUEST 1   /* ARP Request             */ 
#define ARP_REPLY 2  
u_int16_t handle_ethernet (u_char *args,const struct pcap_pkthdr* header,const u_char* data)
{
    struct ether_header *eptr;  
    eptr = (struct ether_header *) data;

    fprintf(stdout,"Source MAC: %s",ether_ntoa((const struct ether_addr *)&eptr->ether_shost));
    fprintf(stdout,"\nDestination MAC: %s ",ether_ntoa((const struct ether_addr *)&eptr->ether_dhost));
    if (ntohs (eptr->ether_type) == ETHERTYPE_IP)
    {
        fprintf(stdout,"\nProtocol : (IP)");
    }
    else  if (ntohs (eptr->ether_type) == ETHERTYPE_ARP)
    {
        fprintf(stdout,"\nProtocol : (ARP)");
    }
    else  if (ntohs (eptr->ether_type) == ETHERTYPE_REVARP)
    {
        fprintf(stdout,"\nProtocol : (RARP)");
    	}
    else {
             fprintf(stdout,"(?)");
             exit(1);
    	  }
    		
   fprintf(stdout,"\n");

    return eptr->ether_type;
}


typedef struct a{ 
    u_int16_t htype;    /* Hardware Type           */ 
    u_int16_t ptype;    /* Protocol Type           */ 
    u_char hlen;        /* Hardware Address Length */ 
    u_char plen;        /* Protocol Address Length */ 
    u_int16_t oper;     /* Operation Code          */ 
    u_char sha[6];      /* Sender hardware address */ 
    u_char spa[4];      /* Sender IP address       */ 
    u_char tha[6];      /* Target hardware address */ 
    u_char tpa[4];      /* Target IP address       */ 
}arphdr_t; 


int main(int argc, char *argv[])
{
	
	const u_char *data;
	arphdr_t *arpheader = NULL;
	u_char a[]={0};
	u_char* args = NULL;
	string file = "/home/ash/Videos/assignments/arp-storm.pcap";
	char errbuff[PCAP_ERRBUF_SIZE];

	pcap_t * pcap = pcap_open_offline(file.c_str(), errbuff);

	struct pcap_pkthdr *header;
        u_int packetCount = 0;


	while (int returnValue = pcap_next_ex(pcap, &header, &data) >= 0)
	{
		arpheader = (struct a*)(data+14);
		printf("Packet # %i\n", ++packetCount);
		printf("Packet size: %d bytes\n", header->len);
		printf("Hardware type: %s\n", (ntohs(arpheader->htype) == 1) ? "Ethernet" : "Unknown"); 
		printf("Protocol type: %s\n", (ntohs(arpheader->ptype) == 0x0800) ? "IPv4" : "Unknown"); 
                printf("Operation: %s\n", (ntohs(arpheader->oper) == ARP_REQUEST)? "ARP Request" : "ARP Reply"); 
		printf("Hardware length: %x\n", arpheader->hlen);
		printf("Protocol length: %x\n", arpheader->plen); 
		 if (ntohs(arpheader->htype) == 1 && ntohs(arpheader->ptype) == 0x0800)
		{
			int i;
		         printf("Sender IP: "); 

    			for(i=0; i<4;i++)
        			printf("%d.", arpheader->spa[i]);

		 	printf("\nTarget IP: "); 

    			for(i=0; i<4; i++)
        			printf("%d.", arpheader->tpa[i]); 
    
   		       printf("\n"); 
		 }
		 u_int16_t type = handle_ethernet(args,header,data);

		if (header->len != header->caplen)
			printf("Warning! Capture size different than packet size: %ld bytes\n", header->len);
                printf("Epoch Time: %d:%d seconds\n", header->ts.tv_sec, header->ts.tv_usec);
		printf("\nHeader fields:");
		for (u_int i=0; (i < header->caplen ) ; i++)
		{
			if ( (i % 16) == 0) printf("\n");
			printf("%.2x ", data[i]);
			
		}
		printf("\n\n");
		printf("Payload\n");
		int i;
		for(i=0;i<header->len;i++) 
		{ 
		    if(isprint(data[i]))               
            	         printf("%c ",data[i]);          
        	    else
            		 printf(" . ",data[i]);      
        
                    if((i%16==0 && i!=0) || i==header->len-1) 
            		printf("\n"); 
    		}
		       
		

		
	}
}
