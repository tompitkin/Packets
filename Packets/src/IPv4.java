import java.util.ArrayList;

public class IPv4 extends Protocol
{
	public IPv4(byte[] byteArray, int headerOffset) 
	{
		super(byteArray, headerOffset);
	}
	
	public int getVersion()
	{
		return getFieldDataInt(0);
	}
	
	public int getIHL()
	{
		return mByteArray[0] & 15;
	}
	
	public int getHeaderSize()
	{
		return getIHL();
	}
	
	@Override
	protected void unpackFields() 
	{
		mFields = new ArrayList<Field>();
		
		mFields.add(new Field("Version", 0, 0, 4));
		mFields.add(new Field("Internet Header Length", 0, 4, 4));
		mFields.add(new Field("Differentiated Services Code Point", 1, 0, 6));
		mFields.add(new Field("Explicit Congestion Notification", 1, 6, 2));
		mFields.add(new Field("Total Length", 2, 0, 16));
		mFields.add(new Field("Identification", 4, 0, 16));
		mFields.add(new Field("Flags", 6, 0, 3));
		mFields.add(new Field("Fragment Offset", 6, 3, 13));
		mFields.add(new Field("Time To Live", 8, 0, 8));
		mFields.add(new Field("Protocol", 9, 0, 8));
		mFields.add(new Field("Header Checksum", 10, 0, 16));
		mFields.add(new Field("Source IP Address", 12, 0, 32));
		mFields.add(new Field("Destination IP Address", 16, 0, 32));
	}

	@Override
	public String toString()
	{
		
		String s = "-- Internet Protocol --\n";
		
		int i = -1;
		s += mFields.get(++i).getName() + ": " + getFieldDataInt(i) + "\n";
		s += mFields.get(++i).getName() + ": " + getFieldDataInt(i) * 4 + " bytes\n";
		s += mFields.get(++i).getName() + ": 0x" + String.format("%02x", getFieldDataInt(i)) + "\n";
		s += mFields.get(++i).getName() + ": 0x" + String.format("%02x", getFieldDataInt(i)) + "\n";
		s += mFields.get(++i).getName() + ": " + getFieldDataInt(i) + "\n";
		s += mFields.get(++i).getName() + ": 0x" + String.format("%02x", getFieldDataInt(i)) + "\n";
		s += mFields.get(++i).getName() + ": 0x" + String.format("%02x", getFieldDataInt(i)) + "\n";
		s += mFields.get(++i).getName() + ": " + getFieldDataInt(i) + "\n";
		
		/*for (Field f : mFields)
		{
			s += f.getName() + ": " + getFieldDataInt(i) + "\n";
			i++;
		}*/
		
		return s;
	}
}