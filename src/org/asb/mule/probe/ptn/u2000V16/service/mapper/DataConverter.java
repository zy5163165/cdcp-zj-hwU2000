package org.asb.mule.probe.ptn.u2000V16.service.mapper;

import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import equipment.HolderState_T;
import equipment.ServiceState_T;
import globaldefs.ConnectionDirection_T;
import globaldefs.NVSList_THelper;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesList_THelper;
import globaldefs.NamingAttributes_THelper;
import managedElement.CommunicationState_T;
import notifications.ObjectType_T;
import notifications.ObjectType_THelper;
import notifications.PerceivedSeverity_T;
import notifications.PerceivedSeverity_THelper;
import notifications.ServiceAffecting_T;
import notifications.ServiceAffecting_THelper;
import notifications.SpecificProblemList_THelper;
import subnetworkConnection.NetworkRouted_T;
import subnetworkConnection.Reroute_T;
import subnetworkConnection.SNCState_T;
import subnetworkConnection.SNCType_T;
import subnetworkConnection.StaticProtectionLevel_T;
import subnetworkConnection.TPData_T;
import terminationPoint.Directionality_T;
import terminationPoint.TPConnectionState_T;
import terminationPoint.TPProtectionAssociation_T;
import terminationPoint.TPType_T;
import terminationPoint.TerminationMode_T;
import transmissionParameters.LayeredParameters_T;

public final class DataConverter {

	public static String getStringExt(String strIn) {
		StringBuffer sb = new StringBuffer();
		if ((strIn == null) || (strIn.trim().equals(""))) {
			return strIn;
		}

		try {
			sb.append("I2G:" + new String(strIn.getBytes("UTF8"), "UTF8"));
			sb.append("G2I:" + new String(strIn.getBytes("UTF8"), "UTF8"));

			sb.append("I2U8:" + new String(strIn.getBytes("UTF8"), "UTF8"));
			sb.append("U82I:" + new String(strIn.getBytes("UTF8"), "UTF8"));

			sb.append("I2U-8:" + new String(strIn.getBytes("UTF8"), "UTF-8"));
			sb.append("U-82I:" + new String(strIn.getBytes("UTF-8"), "UTF8"));

			sb.append("G2U8:" + new String(strIn.getBytes("UTF8"), "UTF8"));
			sb.append("U82G:" + new String(strIn.getBytes("UTF8"), "UTF8"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getString(String strIn) {
		String strOut = null;
		if ((strIn == null) || (strIn.trim().equals(""))) {
			return strIn;
		}

		try {
			byte[] b = strIn.getBytes("UTF8");
			strOut = new String(b, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strOut;
	}

	// EMS:QUZ-T2000-3-P@ManagedElement:591080@PTP:/rack=1/shelf=1/slot=5/domain=sdh/port=1@CTP:/sts3c_au4-j=1/vt2_tu12-k=1-l=3-m=2
	public static String getString(NameAndStringValue_T[] name) {
		if (name == null || name.length == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < name.length; i++) {
			if (i >= 1) {
				sb.append('@');
			}

			sb.append(name[i].name);
			sb.append(':');
			sb.append(name[i].value);
		}

		return sb.toString();
	}

	public static String getString(CommunicationState_T comState) {
		if (comState == null) {
			return "";
		}

		switch (comState.value()) {
		case CommunicationState_T._CS_AVAILABLE:
			return "CS_AVAILABLE";
		case CommunicationState_T._CS_UNAVAILABLE:
			return "CS_UNAVAILABLE";
		}

		return comState.toString();
	}

	public static String getString(boolean val) {
		return val ? "TRUE" : "FALSE";
	}

	public static String getString(short s) {
		return String.valueOf(s);
	}

	public static String getString(int i) {
		return String.valueOf(i);
	}

	public static String getString(short[] shorts) {
		if (shorts == null || shorts.length == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < shorts.length; i++) {
			if (i >= 1) {
				sb.append(',');
			}
			sb.append(shorts[i]);
		}

		return sb.toString();
	}

	public static String getString(int[] ints) {
		if (ints == null || ints.length == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ints.length; i++) {
			if (i >= 1) {
				sb.append(',');
			}
			sb.append(ints[i]);
		}

		return sb.toString();
	}

	public static String getString(String[] strings) {
		if (strings == null || strings.length == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strings.length; i++) {
			if (i >= 1) {
				sb.append(',');
			}
			sb.append(strings[i]);
		}

		return sb.toString();
	}

	public static String getString(HolderState_T hs) {
		if (hs == null) {
			return "";
		}

		switch (hs.value()) {
		case HolderState_T._EMPTY:
			return "EMPTY";
		case HolderState_T._INSTALLED_AND_EXPECTED:
			return "INSTALLED_AND_EXPECTED";
		case HolderState_T._EXPECTED_AND_NOT_INSTALLED:
			return "EXPECTED_AND_NOT_INSTALLED";
		case HolderState_T._INSTALLED_AND_NOT_EXPECTED:
			return "INSTALLED_AND_NOT_EXPECTED";
		case HolderState_T._MISMATCH_OF_INSTALLED_AND_EXPECTED:
			return "MISMATCH_OF_INSTALLED_AND_EXPECTED";
		case HolderState_T._UNAVAILABLE:
			return "UNAVAILABLE";
		case HolderState_T._UNKNOWN:
			return "UNKNOWN";
		}

		return hs.toString();
	}

	public static String getString(ServiceState_T servtate) {
		if (servtate == null) {
			return "";
		}

		switch (servtate.value()) {
		case ServiceState_T._IN_SERVICE:
			return "IN_SERVICE";
		case ServiceState_T._OUT_OF_SERVICE:
			return "OUT_OF_SERVICE";
		case ServiceState_T._OUT_OF_SERVICE_BY_MAINTENANCE:
			return "OUT_OF_SERVICE_BY_MAINTENANCE";
		case ServiceState_T._SERV_NA:
			return "SERV_NA";
		}

		return servtate.toString();
	}

	public static String getString(TPType_T tptype) {
		if (tptype == null) {
			return "";
		}

		switch (tptype.value()) {
		case TPType_T._TPT_PTP:
			return "TPT_PTP";
		case TPType_T._TPT_CTP:
			return "TPT_CTP";
		case TPType_T._TPT_TPPool:
			return "TPT_TPPool";
		}

		return tptype.toString();
	}

	public static String getString(TPConnectionState_T cs) {
		if (cs == null) {
			return "";
		}

		switch (cs.value()) {
		case TPConnectionState_T._TPCS_NA:
			return "TPCS_NA";
		case TPConnectionState_T._TPCS_SOURCE_CONNECTED:
			return "TPCS_SOURCE_CONNECTED";
		case TPConnectionState_T._TPCS_SINK_CONNECTED:
			return "TPCS_SINK_CONNECTED";
		case TPConnectionState_T._TPCS_BI_CONNECTED:
			return "TPCS_BI_CONNECTED";
		case TPConnectionState_T._TPCS_NOT_CONNECTED:
			return "TPCS_NOT_CONNECTED";
		}

		return cs.toString();
	}

	public static String getString(TerminationMode_T tm) {
		if (tm == null) {
			return "";
		}

		switch (tm.value()) {
		case TerminationMode_T._TM_NA:
			return "TM_NA";
		case TerminationMode_T._TM_NEITHER_TERMINATED_NOR_AVAILABLE_FOR_MAPPING:
			return "TM_NEITHER_TERMINATED_NOR_AVAILABLE_FOR_MAPPING";
		case TerminationMode_T._TM_TERMINATED_AND_AVAILABLE_FOR_MAPPING:
			return "TM_TERMINATED_AND_AVAILABLE_FOR_MAPPING";
		}

		return tm.toString();
	}

	public static String getString(Directionality_T d) {
		if (d == null) {
			return "";
		}

		switch (d.value()) {
		case Directionality_T._D_NA:
			return "D_NA";
		case Directionality_T._D_BIDIRECTIONAL:
			return "D_BIDIRECTIONAL";
		case Directionality_T._D_SOURCE:
			return "D_SOURCE";
		case Directionality_T._D_SINK:
			return "D_SINK";
		}

		return d.toString();
	}

	public static String getString(LayeredParameters_T[] lps) {
		if (lps == null || lps.length == 0) {
			return "";
		}

		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for (int i = 0; i < lps.length; i++) {
			if (i >= 1) {
				sb1.append(',');
				sb2.append("$");
			}
			sb1.append(lps[i].layer);
			sb2.append(getString(lps[i].transmissionParams));
		}
		return sb1.toString() + "$" + sb2.toString();
	}

	public static String getString(TPProtectionAssociation_T pa) {
		if (pa == null) {
			return "";
		}

		switch (pa.value()) {
		case TPProtectionAssociation_T._TPPA_NA:
			return "TPPA_NA";
		case TPProtectionAssociation_T._TPPA_PSR_RELATED:
			return "TPPA_PSR_RELATED";
		}

		return pa.toString();
	}

	public static String getString(SNCType_T snctype) {
		if (snctype == null) {
			return "";
		}

		switch (snctype.value()) {
		case SNCType_T._ST_SIMPLE:
			return "ST_SIMPLE";
		case SNCType_T._ST_ADD_DROP_A:
			return "ST_ADD_DROP_A";
		case SNCType_T._ST_ADD_DROP_Z:
			return "ST_ADD_DROP_Z";
		case SNCType_T._ST_INTERCONNECT:
			return "ST_INTERCONNECT";
		case SNCType_T._ST_DOUBLE_INTERCONNECT:
			return "ST_DOUBLE_INTERCONNECT";
		case SNCType_T._ST_DOUBLE_ADD_DROP:
			return "ST_DOUBLE_ADD_DROP";
		case SNCType_T._ST_OPEN_ADD_DROP:
			return "ST_OPEN_ADD_DROP";
		case SNCType_T._ST_EXPLICIT:
			return "ST_EXPLICIT";
		}

		return snctype.toString();
	}

	public static String getString(ConnectionDirection_T cd) {
		if (cd == null) {
			return "";
		}

		switch (cd.value()) {
		case ConnectionDirection_T._CD_UNI:
			return "CD_UNI";
		case ConnectionDirection_T._CD_BI:
			return "CD_BI";
		}

		return cd.toString();
	}

	public static String getString(NameAndStringValue_T[][] names) {
		if (names == null || names.length == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < names.length; i++) {
			if (i >= 1) {
				sb.append('$');
			}
			sb.append(getString(names[i]));
		}

		return sb.toString();
	}

	public static String getString(SNCState_T sncState) {
		if (sncState == null) {
			return "";
		}

		switch (sncState.value()) {
		case SNCState_T._SNCS_NONEXISTENT:
			return "SNCS_NONEXISTENT";
		case SNCState_T._SNCS_PENDING:
			return "SNCS_PENDING";
		case SNCState_T._SNCS_ACTIVE:
			return "SNCS_ACTIVE";
		case SNCState_T._SNCS_PARTIAL:
			return "SNCS_PARTIAL";
		}

		return sncState.toString();
	}

	public static String getString(StaticProtectionLevel_T spl) {
		if (spl == null) {
			return "";
		}

		switch (spl.value()) {
		case StaticProtectionLevel_T._PREEMPTIBLE:
			return "PREEMPTIBLE";
		case StaticProtectionLevel_T._UNPROTECTED:
			return "UNPROTECTED";
		case StaticProtectionLevel_T._PARTIALLY_PROTECTED:
			return "PARTIALLY_PROTECTED";
		case StaticProtectionLevel_T._FULLY_PROTECTED:
			return "FULLY_PROTECTED";
		case StaticProtectionLevel_T._HIGHLY_PROTECTED:
			return "HIGHLY_PROTECTED";
		}

		return spl.toString();
	}

	public static String getString(TPData_T tpData) {
		if (tpData == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("<tpName>");
		sb.append(getString(tpData.tpName));
		sb.append('$');
		sb.append("<tpMappingMode>");
		sb.append(getString(tpData.tpMappingMode));
		sb.append('$');
		sb.append("<transmissionParams>");
		sb.append(getString(tpData.transmissionParams));
		sb.append('$');
		sb.append("<ingressTrafficDescriptorName>");
		sb.append(getString(tpData.ingressTrafficDescriptorName));
		sb.append('$');
		sb.append("<egressTrafficDescriptorName>");
		sb.append(getString(tpData.egressTrafficDescriptorName));

		return sb.toString();
	}

	public static String getString(TPData_T[] tpDatas) {
		if (tpDatas == null || tpDatas.length == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tpDatas.length; i++) {
			if (i >= 1) {
				sb.append('#');
			}
			sb.append(getString(tpDatas[i]));
		}

		return sb.toString();
	}

	public static String getString(Reroute_T reRoute) {
		if (reRoute == null) {
			return "";
		}

		switch (reRoute.value()) {
		case Reroute_T._RR_NA:
			return "RR_NA";
		case Reroute_T._RR_NO:
			return "RR_NO";
		case Reroute_T._RR_YES:
			return "RR_YES";
		}

		return reRoute.toString();
	}

	public static String getString(NetworkRouted_T networkRouted) {
		if (networkRouted == null) {
			return "";
		}

		switch (networkRouted.value()) {
		case NetworkRouted_T._NR_NA:
			return "NR_NA";
		case NetworkRouted_T._NR_NO:
			return "NR_NO";
		case NetworkRouted_T._NR_YES:
			return "NR_YES";
		}

		return networkRouted.toString();
	}

	public static String getString(ObjectType_T objectType) {
		return objectType.toString();
	}

	public static String getString(PerceivedSeverity_T perceivedSeverity) {
		return perceivedSeverity.toString();
	}

	public static String getString(ServiceAffecting_T serviceAffecting) {
		return serviceAffecting.toString();
	}

	public static String getString(StructuredEvent event) {
		if (event == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < event.filterable_data.length; i++) {
			Property property = event.filterable_data[i];
			String name = property.name;
			org.omg.CORBA.Any value = property.value;
			if (i >= 1) {
				sb.append('$');
			}
			sb.append(name);
			sb.append(":");

			if (name.equals("notificationId")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("objectName")) {
				sb.append(getString(NamingAttributes_THelper.extract(value)));
			} else if (name.equals("nativeEMSName")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("nativeEMSName")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("nativeProbableCause")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("objectType")) {
				sb.append(getString(ObjectType_THelper.extract(value)));
			} else if (name.equals("emsTime")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("neTime")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("isClearable")) {
				sb.append(getString(value.extract_boolean()));
			} else if (name.equals("layerRate")) {
				sb.append(getString(value.extract_short()));
			} else if (name.equals("probableCause")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("probableCauseQualifier")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("perceivedSeverity")) {
				sb.append(getString(PerceivedSeverity_THelper.extract(value)));
			} else if (name.equals("serviceAffecting")) {
				sb.append(getString(ServiceAffecting_THelper.extract(value)));
			} else if (name.equals("affectedTPList")) {
				sb.append(getString(NamingAttributesList_THelper.extract(value)));
			} else if (name.equals("additionalText")) {
				sb.append(getString(value.extract_string()));
			} else if (name.equals("additionalInfo")) {
				sb.append(getString(NVSList_THelper.extract(value)));
			}/* else if (name.equals("acknowledgeIndication")) {
				sb.append(getString(AcknowledgeIndication_THelper.extract(value)));
			}*/ else if (name.equals("X.733::SpecificProblems")) {
				sb.append(getString(SpecificProblemList_THelper.extract(value)));
			} else {
				sb.append(value);
			}
		}

		return sb.toString();
	}

//	public static String getString(AcknowledgeIndication_T ackIndication) {
//		return ackIndication.toString();
//	}

	public static String getString(StructuredEvent[] events) {
		if (events == null || events.length == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < events.length; i++) {
			if (i >= 1) {
				sb.append('$');
			}

			sb.append(getString(events[i]));
		}

		return sb.toString();
	}
}
