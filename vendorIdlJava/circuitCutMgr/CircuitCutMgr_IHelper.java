package circuitCutMgr;


/**
 *	Generated from IDL interface "CircuitCutMgr_I"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */

public final class CircuitCutMgr_IHelper
{
	public static void insert (final org.omg.CORBA.Any any, final circuitCutMgr.CircuitCutMgr_I s)
	{
			any.insert_Object(s);
	}
	public static circuitCutMgr.CircuitCutMgr_I extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:mtnm.tmforum.org/circuitCutMgr/CircuitCutMgr_I:1.0", "CircuitCutMgr_I");
	}
	public static String id()
	{
		return "IDL:mtnm.tmforum.org/circuitCutMgr/CircuitCutMgr_I:1.0";
	}
	public static CircuitCutMgr_I read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final circuitCutMgr.CircuitCutMgr_I s)
	{
		_out.write_Object(s);
	}
	public static circuitCutMgr.CircuitCutMgr_I narrow(final java.lang.Object obj)
	{
		if (obj instanceof circuitCutMgr.CircuitCutMgr_I)
		{
			return (circuitCutMgr.CircuitCutMgr_I)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static circuitCutMgr.CircuitCutMgr_I narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (circuitCutMgr.CircuitCutMgr_I)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:mtnm.tmforum.org/circuitCutMgr/CircuitCutMgr_I:1.0"))
			{
				circuitCutMgr._CircuitCutMgr_IStub stub;
				stub = new circuitCutMgr._CircuitCutMgr_IStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static circuitCutMgr.CircuitCutMgr_I unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (circuitCutMgr.CircuitCutMgr_I)obj;
		}
		catch (ClassCastException c)
		{
				circuitCutMgr._CircuitCutMgr_IStub stub;
				stub = new circuitCutMgr._CircuitCutMgr_IStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
