import javax.xml.bind.DatatypeConverter;

import com.packet.Packet;
import com.packet.protocol.Protocol;

public class PacketDemo 
{
	public static void main(String[] args)
	{
		//String s = "450000288ac840003906a03c6ca2e8c5c0a800bb0050e5d7a6de7b10e8636cff501100103b7e0000000000000000";	//TCP Raw Data
		String s = "45000070022d00008011dd02c0a800bbd040c9a9cf04698c005cee3e56533031300006040004000000febc015a070000ed040000010000005a07000030000000507f6b3aa9da8c5e60163b4ad22b887b988d4f951ff0f1391e7c3a7d150f1763881924493dc0f593a0fb27cefd3a2509";	//UDP Raw Data
		//String s = "45000070022d000080FDdd02c0a800bbd040c9a9cf04698c005cee3e56533031300006040004000000febc015a070000ed040000010000005a07000030000000507f6b3aa9da8c5e60163b4ad22b887b988d4f951ff0f1391e7c3a7d150f1763881924493dc0f593a0fb27cefd3a2509";	//FAKE IPO Raw Data
		
		System.out.println("Raw Data: " + s.toUpperCase() + "\n");
		
		Packet p = new Packet(DatatypeConverter.parseHexBinary(s));
		
		Protocol prot = p.getProtocol(0);
		System.out.println(prot.getName() + " Header: " + DatatypeConverter.printHexBinary(prot.getHeader()));
		System.out.println(prot.getName() + " Data: " + DatatypeConverter.printHexBinary(prot.getData()));
		
		System.out.println();
		
		prot = p.getProtocol(1);
		System.out.println(prot.getName() + " Header: " + DatatypeConverter.printHexBinary(prot.getHeader()));
		System.out.println(prot.getName() + " Data: " + DatatypeConverter.printHexBinary(prot.getData()));
		
		System.out.println("\n\n" + p);
	}
}
