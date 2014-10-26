package com.packet.utilities;

public class Utils 
{
	public static int getUInt(byte[] bytes)
	{		
		int data = 0;
		for (int i = 0; i < Math.min(bytes.length, 4); i++)
		{
			if (i > 0)
				data <<= 8;
			
			data |= bytes[i] & 0xFF; //Bitwise AND converts byte into unsigned int
		}
		
		return data;
	}
	
	public static int getUInt(byte b)
	{
		int data = 0;
		data |= b & 0xFF;
		return data;
	}
	
	public static boolean getBool(byte b)
	{
		return (b != 0);
	}
}
