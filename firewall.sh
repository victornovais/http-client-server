#!/bin/bash
#http://www.tccamargo.com/linux/tutoriais/iptables.html
#http://pt.scribd.com/doc/51992595/Tutorial-Iptables
#http://wiki.ubuntu-br.org/Iptables
#limpando tabelas
iptables -F
iptables -X

#libera conxoes tcp a porta
iptables -A INPUT -i eth0 -p tcp --dport 80 -j ACCEPT
iptables -A INPUT -i eth0 -p tcp --dport 443 -j ACCEPT
iptables -A INPUT -i eth0 -p tcp --dport 9998 -j ACCEPT


#Boqueia um endereço ip
iptables -A INPUT -s -m limit --limit 500/s -j DROP
iptables -A INPUT -i eth0 -p tcp --tcp-flags SYN,FIN SYN,FIN -j DROP

