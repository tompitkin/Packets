package com.packet.protocol;

import java.util.ArrayList;

import com.packet.utilities.Utils;
import com.packet.protocol.FieldFormatter.FormatType;

public class IPv4 extends Protocol
{	
	public IPv4(byte[] byteArray, int headerOffset) 
	{
		mByteArray = byteArray;
		mHeaderOffset = headerOffset;
		mFields = new ArrayList<Field>();
		
		createFields();
		
		mHeaderByteSize = getIHL() * 4;
		mDataOffset = mHeaderOffset + mHeaderByteSize;
		mDataByteSize = getTotalLength() - mHeaderByteSize;
	}
	
	public IPv4() 
	{
		
	}
	
	private void createFields()
	{
		mFields.add(new Field("Version", 0, 0, 4, FormatType.INT));
		mFields.add(new Field("Internet Header Length", 0, 4, 4, FormatType.INT));
		mFields.add(new Field("Differentiated Services Code Point", 1, 0, 6, FormatType.HEX));
		mFields.add(new Field("Explicit Congestion Notification", 1, 6, 2, FormatType.HEX));
		mFields.add(new Field("Total Length", 2, 0, 16, FormatType.INT));
		mFields.add(new Field("Identification", 4, 0, 16, FormatType.HEX));
		mFields.add(new Field("Flags", 6, 0, 3, FormatType.HEX));
		mFields.add(new Field("Fragment Offset", 6, 3, 13, FormatType.INT));
		mFields.add(new Field("Time To Live", 8, 0, 8, FormatType.INT));
		mFields.add(new Field("Protocol", 9, 0, 8, FormatType.INT));
		mFields.add(new Field("Header Checksum", 10, 0, 16, FormatType.HEX));
		mFields.add(new Field("Source IP Address", 12, 0, 32, FormatType.IP_ADDRESS));
		mFields.add(new Field("Destination IP Address", 16, 0, 32, FormatType.IP_ADDRESS));
		
		int IHL = getIHL();
		if (IHL > 5)
			mFields.add(new Field("Options", 20, 0, (IHL - 5) * 4, FormatType.BYTE_ARRAY));
	}
	
	public int getVersion()
	{
		return Utils.getUInt(getFieldData(0));
	}
	
	public int getIHL()
	{
		return Utils.getUInt(getFieldData(1));
	}
	
	public int getDSCP()
	{
		return Utils.getUInt(getFieldData(2));
	}
	
	public int getECN()
	{
		return Utils.getUInt(getFieldData(3));
	}
	
	public int getTotalLength()
	{
		return Utils.getUInt(getFieldData(4));
	}
	
	public int getID()
	{
		return Utils.getUInt(getFieldData(5));
	}
	
	public int getFlags()
	{
		return Utils.getUInt(getFieldData(6));
	}
	
	public int getFragmentOffset()
	{
		return Utils.getUInt(getFieldData(7));
	}
	
	public int getTTL()
	{
		return Utils.getUInt(getFieldData(8));
	}
	
	public int getProtocol()
	{
		return Utils.getUInt(getFieldData(9));
	}
	
	public int getChecksum()
	{
		return Utils.getUInt(getFieldData(10));
	}
	
	public byte[] getSourceIP()
	{
		return getFieldData(11);
	}
	
	public byte[] getDestIP()
	{
		return getFieldData(12);
	}
	
	public byte[] getOptions()
	{
		return getFieldData(13);
	}
	
	@Override
	public String getName() 
	{
		return "Internet Protocol";
	}

	@Override
	public TYPE getType() 
	{
		return TYPE.IPV4;
	}
	
	@Override
	public TYPE getNextProtocol()
	{
		int type = getProtocol();
		
		switch(type)
		{
		case 6:
			return TYPE.TCP;
		case 17:
			return TYPE.UDP;
		default:
			return TYPE.IPO;
		}
	}
}