package org.asb.mule.probe.ptn.u2000V16.nbi.task;

import java.util.List;
import java.util.Vector;

import org.asb.mule.probe.framework.entity.AlarmModel;
import org.asb.mule.probe.framework.nbi.task.CommonDataTask;
import org.asb.mule.probe.ptn.u2000V16.service.U2000Service;

import com.alcatelsbell.nms.valueobject.BObject;

public class AlarmDataTask extends CommonDataTask {

	public Vector<BObject> excute() {

		try {
			List<AlarmModel> alarmList = ((U2000Service) service).retrieveAllAlarms();
			if (alarmList.size() > 0) {
				// JPASupport sqliteJPASupport = SqliteService.getInstance().getJpaSupport();
				// sqliteJPASupport.begin();
				for (AlarmModel alarm : alarmList) {
                    insertToSqliteDB(alarm);
				}
				// sqliteJPASupport.end();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return null;

	}

	@Override
	public void insertDate(Vector<BObject> dataList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteDate(Vector<BObject> dataList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDate(Vector<BObject> dataList) {
		// TODO Auto-generated method stub

	}

}
