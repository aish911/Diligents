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
#define SIZE_ETHERNET 14

#define TCPHSIZE  20

using namespace std;
#define ARP_REQUEST 1   
#define ARP_REPLY 2  
u_int16_t handle_ethernet (u_char *args,const struct pcap_pkthdr* header,const u_char* data)
{
    struct ether_header *eptr;  
    eptr = (struct ether_header *) data;

    fprintf(stdout,"Source MAC: %s",ether_ntoa((const struct ether_addr *)&eptr->ether_shost));
    fprintf(stdout,"\nDestination MAC: %s ",ether_ntoa((const struct ether_addr *)&eptr->ether_dhost));
    		
   fprintf(stdout,"\n");

    return eptr->ether_type;
}
typedef struct tcpheader {

 u_int   tcph_srcport;
 u_int   tcph_destport;
 u_int32_t th_seq;
 u_int32_t th_ack;  
 unsigned char  tcph_reserved:4, tcph_offset:4;
 unsigned char    tcph_flags;
 u_int   tcph_win;
 u_int   tcph_chksum;
 u_int   tcph_urgptr;

}tcp_h;

typedef struct sniff_ip {
        u_char iph_ver;      
        u_char ip_tos;      
        u_short ip_len;     
        u_short ip_id;      
        u_short ip_off;     
 	u_char ip_ttl;      
        u_char ip_p;        
        u_short ip_sum;     
        struct in_addr ip_src;
        struct in_addr ip_dst;
}ip_p;
#define TCP_HL(ip)               (((ip)->iph_ver) & 0x0f)
#define IP_HL(ip)               (((ip)->iph_ver) & 0x0f)
#define IP_V(ip)                (((ip)->ip_vhl) >> 4)
typedef struct a{ 
    u_int16_t htype;     
    u_int16_t ptype;     
    u_char hlen;         
    u_char plen;         
    u_int16_t oper;      
    u_char sha[6];       
    u_char spa[4];       
    u_char tha[6];       
    u_char tpa[4];       
}arphdr_t; 


int main(int argc, char *argv[])
{
	    
 	u_int sourcePort, destPort;
	const u_char *data;
	arphdr_t *arpheader = NULL;
	ip_p *ip = NULL;
	tcp_h *tph,*tph1 = NULL;
	u_char a[]={0};
	u_char* args = NULL;
	string file = "/home/ash/Videos/assignments/tcp-ecn-sample.pcap";
	char errbuff[PCAP_ERRBUF_SIZE];

	pcap_t * pcap = pcap_open_offline(file.c_str(), errbuff);
        int size_ip;
	struct pcap_pkthdr *header;
        u_int packetCount = 0;


	while (int returnValue = pcap_next_ex(pcap, &header, &data) >= 0)
	{
		ip = (struct sniff_ip*)(data + SIZE_ETHERNET);
		size_ip = IP_HL(ip)*4;	
		arpheader = (struct a*)(data+14);
		tph=(struct tcpheader*)(data +14+size_ip);
		tph1=(struct tcpheader*)(data +28);
		
		printf("\nPacket # %i\n", ++packetCount);
	
		printf("src address: %s \n",  inet_ntoa(ip->ip_src));
		printf("dest address: %s \n",  inet_ntoa(ip->ip_dst));
		printf("Header Length: %d\n", (ip->ip_len)/16);
		printf("Frag offset: %d\n", ip->ip_off);
		printf("TTL: %u \n",ip->ip_ttl);
		printf("Sequence number: %u \nAcknowledgment number: %u \n", tph-> th_seq, tph->th_ack);
		 
		switch(ip->ip_p) {
		case IPPROTO_TCP:
			printf("Protocol: TCP\n");
			break;
		case IPPROTO_UDP:
			printf("Protocol: UDP\n");
			break;
		case IPPROTO_ICMP:
			printf("Protocol: ICMP\n");
			break;
		
		case IPPROTO_IP:
			printf("Protocol: IP\n");
			break;
		default:
			printf("Protocol: unknown\n");
			break;
	}	
		printf("Packet size: %d bytes\n", header->len);
		 
 
		
		u_int16_t type = handle_ethernet(args,header,data);
	       	printf("Source port: %d\n", ntohs(tph->tcph_srcport));
		printf("Destination port: %d\n", ntohs(tph->tcph_destport));
		printf("Window Size: %d\n", ntohs(tph1->tcph_win));
		printf("Urgent Pointer: %d\n", ntohs(tph1->tcph_urgptr));
   		
		if (header->len != header->caplen)
			printf("Warning! Capture size different than packet size: %ld bytes\n", header->len);
                printf("\nHeader Fields :");
		for (u_int i=0; (i < header->caplen ) ; i++)
		{
			if ( (i % 16) == 0) printf("\n");
			printf("%.2x ", data[i]);
			
		}
		printf("\n\n");
		printf("Payload :\n");
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
