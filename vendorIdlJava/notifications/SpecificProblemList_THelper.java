package notifications;

/**
 *	Generated from IDL definition of alias "SpecificProblemList_T"
 *	@author JacORB IDL compiler 
 */

public final class SpecificProblemList_THelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, java.lang.String[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static java.lang.String[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(notifications.SpecificProblemList_THelper.id(), "SpecificProblemList_T",org.omg.CORBA.ORB.init().create_sequence_tc(0, notifications.SpecificProblem_THelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:mtnm.tmforum.org/notifications/SpecificProblemList_T:1.0";
	}
	public static java.lang.String[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		java.lang.String[] _result;
		int _l_result95 = _in.read_long();
		_result = new java.lang.String[_l_result95];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=_in.read_string();
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, java.lang.String[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			_out.write_string(_s[i]);
		}

	}
}
