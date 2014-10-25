import javax.xml.bind.DatatypeConverter;

public class PacketDemo 
{
	public static void main(String[] args)
	{
		String s = "450000288ac840003906a03c6ca2e8c5c0a800bb0050e5d7a6de7b10e8636cff501100103b7e0000000000000000";	
		Packet p = new Packet(DatatypeConverter.parseHexBinary(s));
		System.out.println(p);
	}
}
