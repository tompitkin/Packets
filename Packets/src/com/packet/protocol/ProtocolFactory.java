package com.packet.protocol;

import java.util.Arrays;

public class ProtocolFactory 
{
	public static Protocol createProtocol(Protocol.TYPE type, byte[] byteArray, int headerOffset)
	{
		if (type == null)
			return null;
		
		Protocol p = null;
		switch(type)
		{
		case IPV4:
			p = new IPv4(byteArray, headerOffset);
			break;
		case TCP:
			p = new TCP(byteArray, headerOffset);
			break;
		case UDP:
			p = new UDP(byteArray, headerOffset);
			break;
		case IPO:
			p = new IPO(byteArray, headerOffset);
			break;
		default:
			break;
		}
		
		return p;
	}
	
	public static Protocol createProtocol(Protocol.TYPE type, byte[] byteArray, int headerOffset, int totalByteLength)
	{
		Protocol p = createProtocol(type, byteArray, headerOffset);
		if (p != null)
			p.mDataByteSize = totalByteLength - p.mHeaderByteSize;
		
		return p;
	}
	
	public static Protocol copyProtocol(Protocol protocol)
	{
		Protocol p = null;
		
		if (protocol instanceof IPv4)
			p = new IPv4();
		else
			return p;
		
		p.mHeaderOffset = protocol.mHeaderOffset;
		p.mHeaderByteSize = protocol.mHeaderByteSize;
		p.mDataOffset = protocol.mDataOffset;
		p.mDataByteSize = protocol.mDataByteSize;
		p.mByteArray = Arrays.copyOf(protocol.mByteArray, protocol.mByteArray.length);
		
		for (Field f : protocol.mFields)
			p.mFields.add(new Field(f));
		
		return p;
	}
}
