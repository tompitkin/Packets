package com.packet.protocol;

import com.packet.protocol.FieldFormatter.FormatType;

public class Field
{	
	public int byteOffset;
	public int bitOffset;
	public int bitSize;
	public String name;
	public FormatType type;
	
	Field(String name, int byteOffset, int bitOffset, int bitSize, FormatType type)
	{
		this.name = name;
		this.byteOffset = byteOffset;
		this.bitOffset = bitOffset;
		this.bitSize = bitSize;
		this.type = type;
	}
	
	Field(Field field)
	{
		name = field.name;
		byteOffset = field.byteOffset;
		bitOffset = field.bitOffset;
		bitSize = field.bitSize;
		type = field.type;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
