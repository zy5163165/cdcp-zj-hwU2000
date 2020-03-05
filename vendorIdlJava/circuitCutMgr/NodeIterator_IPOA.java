package circuitCutMgr;

/**
 *	Generated from IDL interface "NodeIterator_I"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */


public abstract class NodeIterator_IPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, circuitCutMgr.NodeIterator_IOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "getLength", new java.lang.Integer(0));
		m_opsHash.put ( "destroy", new java.lang.Integer(1));
		m_opsHash.put ( "next_n", new java.lang.Integer(2));
	}
	private String[] ids = {"IDL:mtnm.tmforum.org/circuitCutMgr/NodeIterator_I:1.0"};
	public circuitCutMgr.NodeIterator_I _this()
	{
		return circuitCutMgr.NodeIterator_IHelper.narrow(_this_object());
	}
	public circuitCutMgr.NodeIterator_I _this(org.omg.CORBA.ORB orb)
	{
		return circuitCutMgr.NodeIterator_IHelper.narrow(_this_object(orb));
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // getLength
			{
			try
			{
				_out = handler.createReply();
				_out.write_ulong(getLength());
			}
			catch(circuitCutMgr.ProcessingFailureException _ex0)
			{
				_out = handler.createExceptionReply();
				circuitCutMgr.ProcessingFailureExceptionHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // destroy
			{
			try
			{
				_out = handler.createReply();
				destroy();
			}
			catch(circuitCutMgr.ProcessingFailureException _ex0)
			{
				_out = handler.createExceptionReply();
				circuitCutMgr.ProcessingFailureExceptionHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // next_n
			{
			try
			{
				int _arg0=_input.read_ulong();
				circuitCutMgr.NodeList_THolder _arg1= new circuitCutMgr.NodeList_THolder();
				_out = handler.createReply();
				_out.write_boolean(next_n(_arg0,_arg1));
				circuitCutMgr.NodeList_THelper.write(_out,_arg1.value);
			}
			catch(circuitCutMgr.ProcessingFailureException _ex0)
			{
				_out = handler.createExceptionReply();
				circuitCutMgr.ProcessingFailureExceptionHelper.write(_out, _ex0);
			}
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
