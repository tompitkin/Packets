package com.packet;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import com.packet.protocol.Protocol;
import com.packet.protocol.ProtocolFactory;

public class Packet
{
	private byte[] mByteArray;
	private ArrayList<Protocol> mProtocols;
	private int mPayloadOffset;
	private int mPayloadByteSize;
	
	/*
	 * Constructs a packet and unpacks the byteArray into properties 
	 * in appropriate protocol layer classes.
	 * 
	 * @param byteArray
	 * 				The raw data of the packet.
	 */
	public Packet(byte[] byteArray)
	{
		mProtocols = new ArrayList<Protocol>();
		mByteArray = Arrays.copyOf(byteArray, byteArray.length);
		mPayloadByteSize = mByteArray.length;
		unpackProtocols();
	}
	
	/*
	 * Gets the raw data of the packet
	 * 
	 * @return The raw data of the packet.
	 */
	public byte[] getByteArray()
	{
		return mByteArray;
	}
	
	/*
	 * Gets a copy of the data section of the packet
	 * 
	 * @return The data section of the packet.
	 */
	public byte[] getPayload()
	{
		return Arrays.copyOfRange(mByteArray, mPayloadOffset, mPayloadOffset + mPayloadByteSize);
	}
	
	/*
	 * Gets a protocol in the packet
	 * Protocols are stored in left to right order (same as in packet)
	 * 
	 * @param protocolIndex
	 * 				The index of the protocol
	 * 
	 * @return The protocol
	 */
	public Protocol getProtocol(int protocolIndex)
	{
		if (protocolIndex > mProtocols.size())
			return null;
		
		return mProtocols.get(protocolIndex);
	}
	
	/*
	 * Generates appropriate protocol headers based on byteArray.
	 * Assumes byteArray starts with IPv4 protocol as specified in project.
	 */
	private void unpackProtocols()
	{
		Protocol p = ProtocolFactory.createProtocol(Protocol.TYPE.IPV4, mByteArray, 0);
		
		while(p != null)
		{
			mProtocols.add(p);
			
			mPayloadOffset = p.getDataOffset();
			mPayloadByteSize = p.getDataByteSize();
			
			p = ProtocolFactory.createProtocol(p.getNextProtocol(), mByteArray, mPayloadOffset, mPayloadByteSize);	
		}
	}
	
	@Override
	public String toString()
	{
		String s = "";
		for (Protocol p : mProtocols)
			s += p + "\n";
		
		s += "-- Payload --\n";
		s += DatatypeConverter.printHexBinary(getPayload());
		return s;
	}
}
