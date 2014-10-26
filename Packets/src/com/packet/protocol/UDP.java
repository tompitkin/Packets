package com.packet.protocol;

import com.packet.protocol.FieldFormatter.FormatType;
import com.packet.utilities.Utils;

public class UDP extends Protocol
{
	public UDP(byte[] byteArray, int headerOffset) 
	{
		super(byteArray, headerOffset);
		
		createFields();
		
		mHeaderByteSize = 8;
		mDataOffset = mHeaderOffset + mHeaderByteSize;
		mDataByteSize = getLength() - mHeaderByteSize;
	}
	
	private void createFields()
	{
		mFields.add(new Field("Source Port", 0, 0, 16, FormatType.INT));
		mFields.add(new Field("Destination Port", 2, 0, 16, FormatType.INT));
		mFields.add(new Field("Length", 4, 0, 16, FormatType.INT));
		mFields.add(new Field("Checksum", 6, 0, 16, FormatType.HEX));
	}
	
	public int getLength()
	{
		return Utils.getUInt(getFieldData(2));
	}

	@Override
	public String getName()
	{
		return "User Datagram Protocol";
	}

	@Override
	public TYPE getType() 
	{
		return TYPE.UDP;
	}

	@Override
	public TYPE getNextProtocol() 
	{
		return null;
	}
}
