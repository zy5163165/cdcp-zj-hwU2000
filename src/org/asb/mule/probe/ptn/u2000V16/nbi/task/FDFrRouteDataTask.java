package org.asb.mule.probe.ptn.u2000V16.nbi.task;

import java.util.List;
import java.util.Vector;

import org.asb.mule.probe.framework.entity.MatrixFlowDomainFragment;
import org.asb.mule.probe.framework.nbi.task.CommonDataTask;
import org.asb.mule.probe.framework.service.SqliteService;
import org.asb.mule.probe.ptn.u2000V16.service.U2000Service;

import com.alcatelsbell.nms.valueobject.BObject;

public class FDFrRouteDataTask extends CommonDataTask {

	public FDFrRouteDataTask() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<BObject> excute() {
		try {
			List<MatrixFlowDomainFragment> routes = ((U2000Service) service).retrieveFDFrRoute(getTask().getObjectName());
			if (routes != null) {
				for (MatrixFlowDomainFragment fDFrRoute : routes) {
					getSqliteConn().insertBObject(fDFrRoute);
				}
			}
		} catch (Exception e) {
			nbilog.error("MatrixFlowDomainFragment excute Exception:", e);
		}
		return null;
	}

	@Override
	public void insertDate(Vector<BObject> dataList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDate(Vector<BObject> dataList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteDate(Vector<BObject> dataList) {
		// TODO Auto-generated method stub

	}

}
