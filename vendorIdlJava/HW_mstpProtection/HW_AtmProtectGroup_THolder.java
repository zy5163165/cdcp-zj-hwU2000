package HW_mstpProtection;

/**
 *	Generated from IDL definition of struct "HW_AtmProtectGroup_T"
 *	@author JacORB IDL compiler 
 */

public final class HW_AtmProtectGroup_THolder
	implements org.omg.CORBA.portable.Streamable
{
	public HW_mstpProtection.HW_AtmProtectGroup_T value;

	public HW_AtmProtectGroup_THolder ()
	{
	}
	public HW_AtmProtectGroup_THolder(final HW_mstpProtection.HW_AtmProtectGroup_T initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return HW_mstpProtection.HW_AtmProtectGroup_THelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = HW_mstpProtection.HW_AtmProtectGroup_THelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		HW_mstpProtection.HW_AtmProtectGroup_THelper.write(_out, value);
	}
}
