import java.util.ArrayList;
import java.util.Arrays;

public class Packet
{
	private byte[] mByteArray;
	private ArrayList<Protocol> mHeaders;
	private int mDataOffset;
	
	/*
	 * Constructs a packet and unpacks the byteArray into properties 
	 * in appropriate protocol layer classes.
	 * 
	 * @param byteArray
	 * 				The raw data of the packet.
	 */
	public Packet(byte[] byteArray)
	{
		mByteArray = Arrays.copyOf(byteArray, byteArray.length);
		unpackProtocols();
	}
	
	byte[] getByteArray()
	{
		return Arrays.copyOf(mByteArray, mByteArray.length);
	}
	
	byte[] getData()
	{
		return Arrays.copyOfRange(mByteArray, mDataOffset, mByteArray.length-1);
	}
	
	/*
	 * Generates appropriate protocol headers based on byteArray.
	 * Assumes byteArray starts with IPv4 protocol as specified in project.
	 */
	private void unpackProtocols()
	{
		IPv4 ip = (IPv4) ProtocolFactory.createProtocol(ProtocolType.IPv4, mByteArray, 0);
		
		mHeaders = new ArrayList<Protocol>();
		mHeaders.add(ip);
	}
	
	@Override
	public String toString()
	{
		String s = "";
		for (Protocol p : mHeaders)
			s += p + "\n";
		
		s += "-- Data --\n";
		s += getData();
		return s;
	}
}
