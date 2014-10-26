package com.packet.protocol;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Protocol
{	
	public enum TYPE
	{
		IPV4, 
		TCP, 
		UDP,
		IPO
	}

	protected int mHeaderOffset;
	protected int mHeaderByteSize;
	protected int mDataOffset;
	protected int mDataByteSize;
	protected byte[] mByteArray;
	protected ArrayList<Field> mFields;
	
	/*
	 * Constructs a base protocol header.
	 * 
	 * @param byteArray
	 * 				A reference to the packet's raw data.
	 * 
	 * @param headerOffset
	 * 				The starting point of the protocol's header in the array
	 */
	public Protocol(byte[] byteArray, int headerOffset)
	{
		mByteArray = byteArray;
		mHeaderOffset = headerOffset;
		mFields = new ArrayList<Field>();
	}
	
	public Protocol()
	{
		mByteArray = new byte[0];
		mFields = new ArrayList<Field>();
	}
	
	public byte[] getFieldData(int index)
	{
		if (index >= mFields.size())
			return null;
		
		return getFieldData(mFields.get(index));
	}
	
	public byte[] getFieldData(Field p)
	{	
		if (p == null)
			return null;
		
		int byteStart = mHeaderOffset + p.byteOffset;
		int byteCount = (int)Math.ceil(p.bitSize / 8.0);
		
		byte[] bytes = new byte[byteCount];
		
		for (int i = 0; i < p.bitSize; i++)
		{	
			int shift = 7 - ((i + p.bitOffset) % 8);
			bytes[i / 8] |= mByteArray[byteStart + ((i + p.bitOffset) / 8)] >> shift & 1;
			
			if (shift != 0 && i < p.bitSize - 1)
				bytes[i / 8] <<= 1;
		}
		
		return bytes;
	}
	
	public byte[] getHeader()
	{
		return Arrays.copyOfRange(mByteArray, mHeaderOffset, mHeaderOffset + mHeaderByteSize);
	}
	
	public byte[] getData()
	{
		return Arrays.copyOfRange(mByteArray, mDataOffset, mDataOffset + mDataByteSize);
	}
	
	public int getHeaderOffset() 
	{
		return mHeaderOffset;
	}

	public int getHeaderByteSize() 
	{
		return mHeaderByteSize;
	}

	public int getDataOffset() 
	{
		return mDataOffset;
	}

	public int getDataByteSize() 
	{
		return mDataByteSize;
	}

	public byte[] getByteArray() 
	{
		return mByteArray;
	}

	public ArrayList<Field> getFields() 
	{
		return mFields;
	}
	
	public abstract String getName();
	public abstract TYPE getType();
	public abstract TYPE getNextProtocol();
	
	@Override
	public String toString()
	{
		String s = "-- " + getName() + " --\n";
		
		for (Field f : mFields)
			s += FieldFormatter.formatField(f, this);
		
		return s;
	}
}
