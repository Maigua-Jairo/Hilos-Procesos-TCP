import socket 
scoe = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

sock.sendto("hello", ("127.0.0.1", 9199));

sock.close();