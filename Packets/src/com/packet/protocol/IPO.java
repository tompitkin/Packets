package com.packet.protocol;

public class IPO extends Protocol
{
	public IPO(byte[] byteArray, int headerOffset) 
	{
		super(byteArray, headerOffset);
	}
	
	@Override
	public String getName() 
	{
		return "IP Other";
	}

	@Override
	public TYPE getType() 
	{
		return TYPE.IPO;
	}

	@Override
	public TYPE getNextProtocol() 
	{
		return null;
	}

}
