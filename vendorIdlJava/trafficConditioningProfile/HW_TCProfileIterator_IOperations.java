package trafficConditioningProfile;

/**
 *	Generated from IDL interface "HW_TCProfileIterator_I"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */


public interface HW_TCProfileIterator_IOperations
{
	/* constants */
	/* operations  */
	boolean next_n(int how_many, trafficConditioningProfile.HW_TCProfileList_THolder tcProfileList) throws globaldefs.ProcessingFailureException;
	int getLength() throws globaldefs.ProcessingFailureException;
	void destroy() throws globaldefs.ProcessingFailureException;
}
