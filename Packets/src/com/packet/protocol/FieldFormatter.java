package com.packet.protocol;

import javax.xml.bind.DatatypeConverter;

import com.packet.utilities.Utils;

public class FieldFormatter 
{
	public enum FormatType 
	{
		INT, 
		HEX,
		BYTE_ARRAY, 
		IP_ADDRESS
	}
	
	public static String formatField(Field f, Protocol p)
	{
		String s = f + ": ";
		
		switch (f.type)
		{
		case INT:
			s += Utils.getUInt(p.getFieldData(f));
			break;
		case HEX:
			s += "0x" + String.format("%02x", Utils.getUInt(p.getFieldData(f)));
			break;
		case IP_ADDRESS:
			byte[] b = p.getFieldData(f);
			
			if (b.length != 4)
				s += b;
			else
				s += Utils.getUInt(b[0]) + "." + Utils.getUInt(b[1]) + "." + Utils.getUInt(b[2]) + "." + Utils.getUInt(b[3]);
			break;
		case BYTE_ARRAY:
			s += DatatypeConverter.printHexBinary(p.getFieldData(f));
			break;
		default:
			s += p.getFieldData(f);
			break;
		}
		
		return s + "\n";
	}
}
