package maintenanceOps;

/**
 *	Generated from IDL definition of struct "HW_MaintenanceDomain_T"
 *	@author JacORB IDL compiler 
 */

public final class HW_MaintenanceDomain_THolder
	implements org.omg.CORBA.portable.Streamable
{
	public maintenanceOps.HW_MaintenanceDomain_T value;

	public HW_MaintenanceDomain_THolder ()
	{
	}
	public HW_MaintenanceDomain_THolder(final maintenanceOps.HW_MaintenanceDomain_T initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return maintenanceOps.HW_MaintenanceDomain_THelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = maintenanceOps.HW_MaintenanceDomain_THelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		maintenanceOps.HW_MaintenanceDomain_THelper.write(_out, value);
	}
}
