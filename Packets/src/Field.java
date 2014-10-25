public class Field 
{
	private int mByteOffset;
	private int mBitOffset;
	private int mBitSize;
	private String mName;
	
	public Field(String name, int byteOffset, int bitOffset, int bitSize)
	{
		mName = name;
		mByteOffset = byteOffset;
		mBitOffset = bitOffset;
		mBitSize = bitSize;
	}
	
	public int getByteOffset()
	{
		return mByteOffset;
	}
	
	public void setByteOffset(int byteOffset)
	{
		mByteOffset = byteOffset;
	}
	
	public int getBitSize()
	{
		return mBitSize;
	}
	
	public void setBitSize(int bitSize)
	{
		mBitSize = bitSize;
	}
	
	public int getBitOffset()
	{
		return mBitOffset;
	}
	
	public void setBitOffset(int bitOffset)
	{
		mBitOffset = bitOffset;
	}
	
	public String getName()
	{
		return mName;
	}
	
	public void setName(String name)
	{
		mName = name;
	}
}
