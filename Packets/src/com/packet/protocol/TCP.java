package com.packet.protocol;

import com.packet.protocol.FieldFormatter.FormatType;
import com.packet.utilities.Utils;

public class TCP extends Protocol
{
	public TCP(byte[] byteArray, int headerOffset) 
	{
		super(byteArray, headerOffset);
		
		createFields();
		
		mHeaderByteSize = getDataOffset() * 4;
		mDataOffset = mHeaderOffset + mHeaderByteSize;
	}
	
	private void createFields()
	{
		mFields.add(new Field("Source Port", 0, 0, 16, FormatType.INT));
		mFields.add(new Field("Destination Port", 2, 0, 16, FormatType.INT));
		mFields.add(new Field("Sequence Number", 4, 0, 32, FormatType.HEX));
		mFields.add(new Field("Acknowledgement Number", 8, 0, 32, FormatType.HEX));
		mFields.add(new Field("Data Offset", 12, 0, 4, FormatType.INT));
		mFields.add(new Field("Reserved", 12, 4, 3, FormatType.HEX));
		mFields.add(new Field("NS", 12, 7, 1, FormatType.INT));
		mFields.add(new Field("CWR", 13, 0, 1, FormatType.INT));
		mFields.add(new Field("ECE", 13, 1, 1, FormatType.INT));
		mFields.add(new Field("URG", 13, 2, 1, FormatType.INT));
		mFields.add(new Field("ACK", 13, 3, 1, FormatType.INT));
		mFields.add(new Field("PSH", 13, 4, 1, FormatType.INT));
		mFields.add(new Field("RST", 13, 5, 1, FormatType.INT));
		mFields.add(new Field("SYN", 13, 6, 1, FormatType.INT));
		mFields.add(new Field("FIN", 13, 7, 1, FormatType.INT));
		mFields.add(new Field("Window Size", 14, 0, 16, FormatType.INT));
		mFields.add(new Field("Checksum", 16, 0, 16, FormatType.HEX));
		mFields.add(new Field("Urgent Pointer", 18, 0, 16, FormatType.INT));
		
		int dataOffset = getDataOffset();
		if (dataOffset > 5)
			mFields.add(new Field("Options", 20, 0, (dataOffset - 5) * 4, FormatType.BYTE_ARRAY));
	}
	
	public int getDataOffset()
	{
		return Utils.getUInt(getFieldData(4));
	}
	
	@Override
	public String getName() 
	{
		return "Transmission Control Protocol";
	}

	@Override
	public TYPE getType() 
	{
		return TYPE.TCP;
	}

	@Override
	public TYPE getNextProtocol() 
	{
		return null;
	}

}
