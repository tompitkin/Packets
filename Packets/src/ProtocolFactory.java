public class ProtocolFactory 
{
	static Protocol createProtocol(ProtocolType protocolType, byte[] byteArray, int headerOffset)
	{
		Protocol p = null;
		
		switch(protocolType)
		{
		case IPv4:
			p = new IPv4(byteArray, headerOffset);
			break;
		default:
			break;
		}
		
		return p;
	}
}
