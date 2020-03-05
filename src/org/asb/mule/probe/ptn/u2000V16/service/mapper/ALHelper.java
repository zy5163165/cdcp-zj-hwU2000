package org.asb.mule.probe.ptn.u2000V16.service.mapper;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CosNotification.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributes_THelper;
import notifications.ObjectType_T;
import notifications.ObjectType_THelper;
import notifications.PerceivedSeverity_T;
import notifications.PerceivedSeverity_THelper;

public class ALHelper {

	/** 数据编码方式 */
	public final static String CHARSET = "ISO8859-1";

	/** 错误 */
	public static final String ERROR = "(ERROR)";

	/** 保存数据最大条数 */
	public static final int MAXNUMBER = 200;

	/** 日志对象 */
	private static final Logger logger = LoggerFactory.getLogger(ALHelper.class);

	/**
	 * 解码字符串
	 * 
	 * @param source
	 *            源字符串
	 * @return 解码后字符串
	 */
	public static String decode(String source) {
		// 检查空
		if (source == null) {
			return null;
		}

		// 解码字符串
		try {
			return new String(source.getBytes(CHARSET), "gbk");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 获取字符串属性值
	 * 
	 * @param props
	 *            属性列表
	 * @param name
	 *            属性名称
	 * @return 字符串属性值
	 */
	public static String getStringValue(Property[] props, String name) {
		// 依次查找属性
		for (Property prop : props) {
			// 找到指定属性
			if (prop.name.equals(name)) {
				// 返回属性值
				return decode(prop.value.extract_string());
			}
		}
		// 返回空
		return null;
	}

	/**
	 * 获取机架标识
	 * 
	 * @param source
	 *            原字符串
	 * @return 机架标识
	 */
	public static String getRackId(String source) {
		Pattern pattern = Pattern.compile("^.*/rack=(\\d+).*$");
		Matcher matcher = pattern.matcher(source);
		if (!matcher.find()) {
			return "1";
		}
		return matcher.group(1);
	}

	/**
	 * 获取机框标识
	 * 
	 * @param source
	 *            原字符串
	 * @return 机框标识
	 */
	public static String getShelfId(String source) {
		Pattern pattern = Pattern.compile("^.*/shelf=(\\d+).*$");
		Matcher matcher = pattern.matcher(source);
		if (!matcher.find()) {
			return null;
		}
		return matcher.group(1);
	}

	/**
	 * 获取子机框标识
	 * 
	 * @param source
	 *            原字符串
	 * @return 子机框标识
	 */
	public static String getSubShelfId(String source) {
		Pattern pattern = Pattern.compile("^.*/sub_shelf=(\\d+).*$");
		Matcher matcher = pattern.matcher(source);
		if (!matcher.find()) {
			return null;
		}
		return matcher.group(1);
	}

	/**
	 * 获取机槽标识
	 * 
	 * @param source
	 *            原字符串
	 * @return 机槽标识
	 */
	public static String getSlotId(String source) {
		Pattern pattern = Pattern.compile("^.*/slot=(\\d+).*$");
		Matcher matcher = pattern.matcher(source);
		if (!matcher.find()) {
			return null;
		}
		return matcher.group(1);
	}

	/**
	 * 获取子机槽标识
	 * 
	 * @param source
	 *            原字符串
	 * @return 机槽标识
	 */
	public static String getSubSlotId(String source) {
		Pattern pattern = Pattern.compile("^.*/sub_slot=(\\d+).*$");
		Matcher matcher = pattern.matcher(source);
		if (!matcher.find()) {
			return null;
		}
		return matcher.group(1);
	}

	/**
	 * 获取端口标识
	 * 
	 * @param source
	 *            原字符串
	 * @return 端口标识
	 */
	public static String getPortId(String source) {
		// 定义变量
		Pattern pattern;
		Matcher matcher;
		String domain, port, portId;

		// 获取主域
		pattern = Pattern.compile("^.*/domain=(\\w+)/.*$");
		matcher = pattern.matcher(source);
		if (matcher.find()) {
			domain = matcher.group(1);
		} else {
			domain = null;
		}

		// 获取端口
		pattern = Pattern.compile("^.*/port=(\\d+).*$");
		matcher = pattern.matcher(source);
		if (!matcher.find()) {
			return null;
		}
		port = matcher.group(1);

		// 组装标识
		if (domain == null || domain.equals("") || domain.equals("sdh")) {
			portId = port;
		} else {
			portId = domain + "-" + port;
		}

		// 返回数据
		return portId;
	}

	/**
	 * 获取对象类型属性值
	 * 
	 * @param props
	 *            属性列表
	 * @param name
	 *            属性名称
	 * @return 对象类型属性值
	 */
	public static ObjectType_T getObjectTypeValue(Property[] props, String name) {
		// 依次查找属性
		for (Property prop : props) {
			// 找到指定属性
			if (prop.name.equals(name)) {
				// 返回属性值
				return ObjectType_THelper.extract(prop.value);
			}
		}
		// 返回空
		return null;
	}

	/**
	 * 获取名值对属性值
	 * 
	 * @param props
	 *            属性列表
	 * @param name
	 *            属性名称
	 * @return 名值对属性值
	 */
	public static NameAndStringValue_T[] getNamingAttributesValue(Property[] props, String name) {
		// 依次查找属性
		for (Property prop : props) {
			// 找到指定属性
			if (prop.name.equals(name)) {
				// 返回属性值
				return NamingAttributes_THelper.extract(prop.value);
			}
		}
		// 返回空
		return null;
	}

	/**
	 * 获取告警紧急程度属性值
	 * 
	 * @param props
	 *            属性列表
	 * @param name
	 *            属性名称
	 * @return 告警紧急程度属性值
	 */
	public static PerceivedSeverity_T getPerceivedSeverityValue(Property[] props, String name) {
		// 依次查找属性
		for (Property prop : props) {
			// 找到指定属性
			if (prop.name.equals(name)) {
				// 返回属性值
				return PerceivedSeverity_THelper.extract(prop.value);
			}
		}
		// 返回空
		return null;
	}

	/**
	 * 转化告警时间
	 * 
	 * @param time_input
	 * @return
	 */
	public static long TimeConvert(String time_input) {
		// 初始化日期
		long longTime;
		// 日期格式转化
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 格式转换： 03/26/2011 07:30 ---> yyyy-MM-dd HH:mm:ss
		String time_out = time_input.substring(0, 4) + "-" + time_input.substring(4, 6) + "-"
				+ time_input.substring(6, 8) + " " + time_input.substring(8, 10) + ":" + time_input.substring(10, 12)
				+ ":" + time_input.substring(12, 14);
		// 转换日期值为long
		try {
			longTime = format.parse(time_out).getTime();
		} catch (ParseException e) {
			// 出错 返回0;
			longTime = 0;
		}
		return longTime;
	}

	/**
	 * 映射告警级别
	 * 
	 * @param severity
	 *            告警紧急程度
	 * @return 告警级别
	 */
	public static int mapAlarmSeverity(PerceivedSeverity_T severity) {

		// 主要告警
		if (severity == PerceivedSeverity_T.PS_CRITICAL) {
			return 161;
		}
		// 主要告警
		if (severity == PerceivedSeverity_T.PS_MAJOR) {
			return 162;
		}
		// 次要告警
		if (severity == PerceivedSeverity_T.PS_MINOR) {
			return 163;
		}
		// 提示告警
		if (severity == PerceivedSeverity_T.PS_WARNING) {
			return 164;
		}
		// 清楚告警
		if (severity == PerceivedSeverity_T.PS_CLEARED) {
			return 165;
		}
		// 默认
		return 160;
	}

	/**
	 * 映射告警类型
	 * 
	 * @param eventType
	 *            告警时间类型
	 * @return
	 */
	public static int mapAlarmType(String eventType) {
		// communicationsAlarm 通讯告警类型
		if ("communicationsAlarm".equalsIgnoreCase(eventType)) {
			return 1;
		}
		// qualityOfServiceAlarm QOS告警类型
		if ("qualityOfServiceAlarm".equalsIgnoreCase(eventType)) {
			return 2;
		}
		// processingErrorAlarm 处理出错告警类型
		if ("processingErrorAlarm".equalsIgnoreCase(eventType)) {
			return 3;
		}
		// equipmentAlarm 设备告警类型
		if ("equipmentAlarm".equalsIgnoreCase(eventType)) {
			return 4;
		}
		// environmentalAlarm 环境告警类型
		if ("environmentalAlarm".equalsIgnoreCase(eventType)) {
			return 5;
		}
		return 0;
	}

	/*
	 * public static MeModel AdjustHolder(String me_name, List<RackModel>
	 * rack_list, List<ShelfModel> shelf_list, List<SlotModel> slot_list,
	 * List<CardModel> card_list, List<ShelfModel> sub_shelf_list,
	 * List<SlotModel> sub_slot_list) { MeModel medel = new MeModel(); RackModel
	 * rackModel = null; ShelfModel shelfModel = null; SlotModel slotModel =
	 * null; if (rack_list.size() > 0) { for (int k = 0; k < rack_list.size();
	 * k++) { if (null == medel.getRackList()) { medel.setRackList(new
	 * ArrayList<RackModel>()); } medel.getRackList().add(rack_list.get(k)); } }
	 * else { if (null == medel.getRackList()) { medel.setRackList(new
	 * ArrayList<RackModel>()); } RackModel rack = new RackModel();
	 * rack.setEmsName(me_name + "EquipmentHolder=/rack=1;");
	 * rack.setEmsSn("1"); rack_list.add(rack); medel.setRackList(rack_list); }
	 * for (int k = 0; k < shelf_list.size(); k++) { ShelfModel shelf =
	 * shelf_list.get(k); rackModel = FindRackModel(shelf, rack_list); if (null
	 * != rackModel) { if (null == rackModel.getShelfList()) {
	 * rackModel.setShelfList(new ArrayList<ShelfModel>()); }
	 * rackModel.getShelfList().add(shelf); } } for (int k = 0; k <
	 * sub_shelf_list.size(); k++) { ShelfModel shelf = sub_shelf_list.get(k);
	 * shelfModel = FindSubShelfModel(shelf, shelf_list); if (null !=
	 * shelfModel) { if (null == shelfModel.getSubShelfList()) {
	 * shelfModel.setSubShelfList(new ArrayList<ShelfModel>()); }
	 * shelfModel.getSubShelfList().add(shelf); } }
	 * 
	 * for (int k = 0; k < slot_list.size(); k++) { SlotModel slot =
	 * slot_list.get(k); shelfModel = FindShelfModel(slot, shelf_list,
	 * sub_shelf_list); if (null != shelfModel) { if (null ==
	 * shelfModel.getSlotList()) { shelfModel.setSlotList(new
	 * ArrayList<SlotModel>()); } shelfModel.getSlotList().add(slot); } }
	 * 
	 * for (int k = 0; k < sub_slot_list.size(); k++) { SlotModel slot =
	 * sub_slot_list.get(k); slotModel = FindSubSlotModel(slot, slot_list); if
	 * (null != slotModel) { if (null == slotModel.getSubSlotList()) {
	 * slotModel.setSubSlotList(new ArrayList<SlotModel>()); }
	 * slotModel.getSubSlotList().add(slot); } }
	 * 
	 * for (int k = 0; k < card_list.size(); k++) { CardModel card =
	 * card_list.get(k); slotModel = FindSlotModel(card, slot_list,
	 * sub_slot_list); if (null != slotModel) { if (null ==
	 * slotModel.getCardList()) { slotModel.setCardList(new
	 * ArrayList<CardModel>()); } slotModel.getCardList().add(card); } } return
	 * medel; }
	 */
	/**
	 * 根据LR编码，获取网络带宽
	 * 
	 * @param _rate
	 * @return
	 */
	public static int getRate(short _rate) {
		int out = 0;
		if (_rate == 5 || _rate == 11 || _rate == 79) { // 2M
			out = 5;
		} else if (_rate == 7 || _rate == 13 || _rate == 14 || _rate == 83) { // 34
			// M
			out = 1116;
		} else
			if (_rate == 8 || _rate == 15 || _rate == 20 || _rate == 25 || _rate == 51 || _rate == 73 || _rate == 85) { // 115
			// M
			out = 4;
		} else if (_rate == 9 || _rate == 16 || _rate == 21 || _rate == 26 || _rate == 54 || _rate == 74
				|| _rate == 86) { // 622
			// M
			out = 3;
		} else if (_rate == 12 || _rate == 17 || _rate == 27 || _rate == 75) { // 2.5
			// G
			out = 2;
		} else if (_rate == 18 || _rate == 23 || _rate == 28 || _rate == 77) { // 10
			// G
			out = 1;
		}
		return out;
	}

	/**
	 * 根据名称得到slotModel
	 * 
	 * @param h
	 * @return
	 */
	/*
	 * public static SlotModel FindSlotModel(CardModel cardModel,
	 * List<SlotModel> slot_list, List<SlotModel> sub_slot_list) { for (int k =
	 * 0; k < slot_list.size(); k++) { SlotModel h = slot_list.get(k); if
	 * (cardModel.getEmsName().contains(h.getEmsName() + "/")) { return h; } }
	 * for (int k = 0; k < sub_slot_list.size(); k++) { SlotModel h =
	 * sub_slot_list.get(k); if (cardModel.getEmsName().contains(h.getEmsName()
	 * + "/")) { return h; } } return null; }
	 * 
	 * public static SlotModel FindSubSlotModel(SlotModel slotModel,
	 * List<SlotModel> slot_list) { for (int k = 0; k < slot_list.size(); k++) {
	 * SlotModel h = slot_list.get(k); if
	 * (slotModel.getEmsName().contains(h.getEmsName().substring(0,
	 * h.getEmsName().length() - 1) + "/")) { return h; } } return null; }
	 * 
	 * public static ShelfModel FindSubShelfModel(ShelfModel shelfModel,
	 * List<ShelfModel> shelf_list) { for (int k = 0; k < shelf_list.size();
	 * k++) { ShelfModel h = shelf_list.get(k); if
	 * (shelfModel.getEmsName().contains(h.getEmsName().substring(0,
	 * h.getEmsName().length() - 1) + "/")) { return h; } } return null; }
	 */

	/*
	 * public static ShelfModel FindShelfModel(SlotModel slotModel,
	 * List<ShelfModel> shelf_list, List<ShelfModel> sub_shelf_list) { for (int
	 * k = 0; k < shelf_list.size(); k++) { ShelfModel shelf =
	 * shelf_list.get(k); if (slotModel.getEmsName()
	 * .contains(shelf.getEmsName().substring(0, shelf.getEmsName().length() -
	 * 1) + "/")) { return shelf; } }
	 * 
	 * for (int k = 0; k < sub_shelf_list.size(); k++) { ShelfModel shelf =
	 * sub_shelf_list.get(k); if (slotModel.getEmsName()
	 * .contains(shelf.getEmsName().substring(0, shelf.getEmsName().length() -
	 * 1) + "/")) { return shelf; } } return null; }
	 * 
	 * public static RackModel FindRackModel(ShelfModel shelfModel,
	 * List<RackModel> rack_list) { for (RackModel rack : rack_list) { if
	 * (shelfModel.getEmsName() .contains(rack.getEmsName().substring(0,
	 * rack.getEmsName().length() - 1) + "/")) { return rack; }
	 * 
	 * } return null; }
	 */
	/**
	 * 格式化名称
	 * 
	 * @param oldname
	 *            NameAndStringValue_T[]
	 * @return String
	 */
	public static String formateName(NameAndStringValue_T[] oldname) {
		String name = "";
		for (int k = 0; k < oldname.length; k++) {
			name += oldname[k].name + "=" + oldname[k].value + ";";
		}
		return name;
	}

	public static NameAndStringValue_T[] formateName(String name) { // 名称的格式为EMS:
		// LSN/
		// EMS_XDM_125
		// ;
		if (name == null || name.indexOf(";") == -1) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(name, ";");
		String nameT; // = (String) st.nextElement();
		ArrayList list = new ArrayList();
		for (int i = 0; st.hasMoreElements(); i++) {
			nameT = (String) st.nextElement();
			NameAndStringValue_T t = new NameAndStringValue_T();
			t.name = nameT.substring(0, nameT.indexOf("="));
			t.value = nameT.substring(nameT.indexOf("=") + 1, nameT.length());
			list.add(t);
		}
		NameAndStringValue_T[] ts = new NameAndStringValue_T[list.size()];
		for (int k = 0; k < list.size(); k++) {
			ts[k] = (NameAndStringValue_T) list.get(k);
		}
		return ts;
	}
}
