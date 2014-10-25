import java.util.ArrayList;

public abstract class Protocol 
{	
	protected byte[] mByteArray;
	protected int mHeaderOffset;
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
		
		unpackFields();
	}
	
	protected byte[] getFieldData(int fieldIndex)
	{	
		if (fieldIndex >= mFields.size())
			return null;
		
		Field field = mFields.get(fieldIndex);
		int size = field.getBitSize();
		int byteStart = mHeaderOffset + field.getByteOffset();
		int byteCount = (int)Math.ceil(size / 8.0);
		int bitOffset = field.getBitOffset();
		
		byte[] bytes = new byte[byteCount];
		
		for (int i = 0; i < size; i++)
		{	
			int shift = 7 - ((i + bitOffset) % 8);
			bytes[i / 8] |= mByteArray[byteStart + ((i + bitOffset) / 8)] >> shift & 1;
			
			if (shift != 0 && i < size - 1)
				bytes[i / 8] <<= 1;
		}
		
		return bytes;
	}
	
	protected int getFieldDataInt(int fieldIndex)
	{
		byte[] bytes = getFieldData(fieldIndex);
		
		int data = 0;
		for (int i = 0; i < Math.min(bytes.length, 4); i++)
		{
			if (i > 0)
				data <<= 8;
			
			data |= bytes[i] & 0xFF; //Bitwise AND converts byte into unsigned int
		}
		
		return data;
	}
	
	protected abstract void unpackFields();
	public abstract String toString();
}
