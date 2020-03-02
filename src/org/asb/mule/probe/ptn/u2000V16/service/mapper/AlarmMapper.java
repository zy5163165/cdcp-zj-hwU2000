package org.asb.mule.probe.ptn.u2000V16.service.mapper;

import org.asb.mule.probe.framework.entity.AlarmModel;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import globaldefs.NameAndStringValue_T;
import notifications.ObjectType_T;
import notifications.PerceivedSeverity_T;

public class AlarmMapper extends CommonMapper

{
	private static AlarmMapper instance;

	public static AlarmMapper instance() {
		if (instance == null) {
			instance = new AlarmMapper();
		}
		return instance;
	}

	public AlarmModel convertAlarm(StructuredEvent vendorEntity, Property[] props)

	{

		// DataConverter dataConverter = new DataConverter();

		// 初始化
		AlarmModel alarm = new AlarmModel();
		// 原始数据
		String memo = DataConverter.getString(vendorEntity);
		alarm.setMemo(memo);
		// 获取数据
		// 获取: 对象类型
		ObjectType_T objectType = ALHelper.getObjectTypeValue(props, "objectType");
		// 获取: 对象名称
		NameAndStringValue_T[] objectName = ALHelper.getNamingAttributesValue(props, "objectName");
		// 获取: 告警紧急程度
		PerceivedSeverity_T perceivedSeverity = ALHelper.getPerceivedSeverityValue(props, "perceivedSeverity");
		// 获取: 网管告警时间
		String emsTime = ALHelper.getStringValue(props, "emsTime");
		// 获取: 网元告警时间
		String neTime = ALHelper.getStringValue(props, "neTime");
		// 获取: 可能告警原因
		String probableCause = ALHelper.getStringValue(props, "probableCause");
		// 获取: 本地可能告警原因
		String nativeProbableCause = ALHelper.getStringValue(props, "nativeProbableCause");
		// 获取: 告警类型
		String eventType = ALHelper.getStringValue(props, "X.733::EventType");
		// 获取: 告警附加信息 暂不需要
		// String additionalText = HWHelper.getStringValue(props,
		// "additionalText");

		// 转化告警
		// 转化系统标示
		String emsName = objectName[0].value;
		// 转化网元标示
		String neName = objectName[1].value;
		// 初始化资源名称
		StringBuffer sb = new StringBuffer();
		// 网管资源
		sb.append("EMS=" + emsName + ";");
		// 网元资源 告警基础信息都有网元 故无需在网元告警中设置
		sb.append("ManagedElement=" + neName + ";");
		// 转化告警: 告警资源标示
		alarm.setSysNo(emsName);
		// 转化告警: 告警资源
		if (objectType == ObjectType_T.OT_MANAGED_ELEMENT) { // 网元
			// 转化网元标示
			alarm.setNeNo(ALHelper.decode(objectName[1].value));
			// 告警资源类型
			alarm.setResType("managed");
		} else if (objectType == ObjectType_T.OT_EQUIPMENT_HOLDER) { // 支撑设备
			// 获取支撑设备标识
			String ehId = ALHelper.decode(objectName[2].value);
			// 转化网元标示
			String neNo = ALHelper.decode(objectName[1].value);
			alarm.setNeNo(neNo);
			// 转化机架标示
			String rackNo = ALHelper.getRackId(ehId);
			alarm.setRackNo(rackNo);
			// 转化机框标示
			String shelfNo = ALHelper.getShelfId(ehId);
			alarm.setShelfNo(shelfNo);
			// 转化子机框标示
			alarm.setSubShelfNo(ALHelper.getSubShelfId(ehId));
			// 转化机槽标示
			String slotNo = ALHelper.getSlotId(ehId);
			alarm.setSlotNo(slotNo);
			// 转化子机槽标示
			alarm.setSubSlotNo(ALHelper.getSubSlotId(ehId));
			// 资源名称 EMS=xxxx;ManagedElement=XXXX;Equipholder=XXXX;
			sb.append("EquipmentHolder" + ehId + ";");
			// 告警资源类型
			// 机架告警
			if (rackNo != null) {
				alarm.setResType("RACK");
			}
			// 机框告警
			if (shelfNo != null) {
				// alarm.setResType(AlarmResTypeModel.SHELF);
			}
			// 机槽告警
			if (slotNo != null) {
				alarm.setResType("SLOT");
			}
		} else if (objectType == ObjectType_T.OT_EQUIPMENT) { // 机盘
			// 获取支撑设备标识
			String ehId = ALHelper.decode(objectName[2].value);
			// 转化网元标示
			alarm.setNeNo(ALHelper.decode(objectName[1].value));
			// 转化机架标示
			alarm.setRackNo(ALHelper.getRackId(ehId));
			// 转化机框标示
			alarm.setShelfNo(ALHelper.getShelfId(ehId));
			// 转化子机框标示
			alarm.setSubShelfNo(ALHelper.getSubShelfId(ehId));
			// 转化机槽标示
			alarm.setSlotNo(ALHelper.getSlotId(ehId));
			// 转化子机槽标示
			alarm.setSubSlotNo(ALHelper.getSubSlotId(ehId));
			// 转化板卡标示
			alarm.setCardNo("1");
			// 资源名称 EMS=xxxx;ManagedElement=XXXX;Equipholder=XXXX;Equipment=XXX;
			sb.append("EquipmentHolder" + ehId + ";");
			sb.append("Equipment=1;");
			// 告警资源类型
			// alarm.setResType(AlarmResTypeModel.CARD);
		} else if (objectType == ObjectType_T.OT_PHYSICAL_TERMINATION_POINT) { // 物理端口
			// 获取支撑设备标识
			String ehId = ALHelper.decode(objectName[2].value);
			// 转化网元标示
			alarm.setNeNo(ALHelper.decode(objectName[1].value));
			// 转化机架标示
			alarm.setRackNo(ALHelper.getRackId(ehId));
			// 转化机框标示
			alarm.setShelfNo(ALHelper.getShelfId(ehId));
			// 转化子机框标示
			alarm.setSubShelfNo(ALHelper.getSubShelfId(ehId));
			// 转化机槽标示
			alarm.setSlotNo(ALHelper.getSlotId(ehId));
			// 转化子机槽标示
			alarm.setSubSlotNo(ALHelper.getSubSlotId(ehId));
			// 转化板卡标示
			alarm.setCardNo("1");
			// 转化端口标示
			alarm.setPortNo(ALHelper.getPortId(ehId));
			// 资源名称 EMS=xxxx;ManagedElement=XXXX;PTP=XXX;
			sb.append("PTP=" + ehId + ";");
			// 告警资源类型
			alarm.setResType("PORT");
		} else if (objectType == ObjectType_T.OT_CONNECTION_TERMINATION_POINT) { // 逻辑端口
			// 获取支撑设备标识
			String ehId = ALHelper.decode(objectName[2].value);
			// 转化网元标示
			alarm.setNeNo(ALHelper.decode(objectName[1].value));
			// 转化机架标示
			alarm.setRackNo(ALHelper.getRackId(ehId));
			// 转化机框标示
			alarm.setShelfNo(ALHelper.getShelfId(ehId));
			// 转化子机框标示
			alarm.setSubShelfNo(ALHelper.getSubShelfId(ehId));
			// 转化机槽标示
			alarm.setSlotNo(ALHelper.getSlotId(ehId));
			// 转化子机槽标示
			alarm.setSubSlotNo(ALHelper.getSubSlotId(ehId));
			// 板卡标示
			alarm.setCardNo("1");
			// 转化标示
			alarm.setPortNo(ALHelper.getPortId(ehId));
			// 转化时隙标示
			String timSlot = ALHelper.decode(objectName[3].value);
			alarm.setCtpName(timSlot);
			// 资源名称 EMS=xxxx;ManagedElement=XXXX;PTP=XXX;CTP=XXX;
			sb.append("PTP=" + ehId + ";");
			sb.append("CTP=" + timSlot + ";");
			// 告警资源类型
			alarm.setResType("PORT");
		} else { // 其它类型, 当网管告警上报
			// 其他告警处理
		}
		// 资源标准模型
//		alarm.setSnUnit(sun);
		// 转化告警资源
		alarm.setResName(sb.toString());
		// 转化告警本地命名
		alarm.setNativeEmsName(ALHelper.getStringValue(props, "nativeEMSName"));
		// 网元告警日期
		alarm.setNeTime(String.valueOf(ALHelper.TimeConvert(neTime)));
		// 网管告警时间
		alarm.setEmsTime(String.valueOf(ALHelper.TimeConvert(emsTime)));
		// 告警描述
		alarm.setAlarmDesc(ALHelper.decode(nativeProbableCause));
		// 告警原因
		alarm.setProbableCause(probableCause);
		// 告警等级
		alarm.setPerceiverServerity(String.valueOf(ALHelper.mapAlarmSeverity(perceivedSeverity)));
		// 告警类型
		alarm.setAlarmType(String.valueOf(ALHelper.mapAlarmType(eventType)));
		// 返回告警
		return alarm;

	}

}
