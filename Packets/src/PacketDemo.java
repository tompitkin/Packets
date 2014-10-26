import javax.xml.bind.DatatypeConverter;

import com.packet.Packet;
import com.packet.protocol.Protocol;

public class PacketDemo 
{
	public static void main(String[] args)
	{
		String s = "450000288ac840003906a03c6ca2e8c5c0a800bb0050e5d7a6de7b10e8636cff501100103b7e0000000000000000";	
		System.out.println("Raw Data: " + s.toUpperCase() + "\n");
		
		Packet p = new Packet(DatatypeConverter.parseHexBinary(s));
		
		Protocol prot = p.getProtocol(0);
		System.out.println("IP Header: " + DatatypeConverter.printHexBinary(prot.getHeader()));
		System.out.println("IP Data: " + DatatypeConverter.printHexBinary(prot.getData()));
		
		System.out.println("\n\n" + p);
	}
}
