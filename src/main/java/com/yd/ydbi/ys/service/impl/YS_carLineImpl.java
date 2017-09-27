package com.yd.ydbi.ys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.yd.ydbi.tools.AnyTrend;
import com.yd.ydbi.ys.dao.YS_openLineMapper;
import com.yd.ydbi.ys.model.CL_iocCar;
import com.yd.ydbi.ys.model.CL_iocTrend;
import com.yd.ydbi.ys.model.CanCar;
import com.yd.ydbi.ys.model.CarLine;
import com.yd.ydbi.ys.model.DB_CarLine;
import com.yd.ydbi.ys.model.IncCar;
import com.yd.ydbi.ys.service.YS_carLine;
@Service
public class YS_carLineImpl implements YS_carLine {
	@Autowired
	YS_openLineMapper olmapper;
	@Override
	public CarLine searchDate(Map<String, String> paramsMap) {
		paramsMap.put("sel_cd","0");
		paramsMap.put("sel_level","0");
		paramsMap.put("area_id","0");
		CarLine cl = new CarLine();
		CL_iocCar iocc = new CL_iocCar();
		List<DB_CarLine> fcl = olmapper.findCarLines(paramsMap);
		int inc = 0;
		int can = 0;
		if (null != fcl && fcl.size() >0 ) {
			List<IncCar> incr = new ArrayList<IncCar>();
			List<CanCar> canc = new ArrayList<CanCar>();

			for (DB_CarLine dbcl : fcl) {
				if (!ObjectUtils.isEmpty(dbcl)) {
					if (!StringUtils.isEmpty(dbcl.getIod_flag())&&dbcl.getIod_flag().equals("0")) {
						CanCar canCar = new CanCar(dbcl.getStart_dot(), dbcl.getEnd_dot());
						canc.add(canCar);
						can=can+1;
					}else if (!StringUtils.isEmpty(dbcl.getIod_flag())&&dbcl.getIod_flag().equals("1")) {
						IncCar incCar = new IncCar(dbcl.getStart_dot(), dbcl.getEnd_dot());
						incr.add(incCar);
						inc=inc+1;
					}
					if (!StringUtils.isEmpty(dbcl.getCar_line())) {
						cl.setCarLinenum(dbcl.getCar_line());
					}
				}
			}
			iocc.setCanCars(canc);
			iocc.setIncCars(incr);
			cl.setIocCar(iocc);
			cl.setIncreased(String.valueOf(inc));
			cl.setCanceled(String.valueOf(can));
		}
		String date_time2 = paramsMap.get("date_time");

		//趋势
		List<CL_iocTrend> sctr=new ArrayList<CL_iocTrend>();
		for (int i = 0; i < 8; i++) {
			if (i!=0) {
				paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
			}
			
			CL_iocTrend fct = olmapper.findCarTrend(paramsMap);
			if (!ObjectUtils.isEmpty(fct)) {
				fct.setNon(i+"");
				if (fct.getDatetime()==null){
					fct.setDatetime(paramsMap.get("date_time"));
				}
				sctr.add(fct);
			}
		}
		cl.setIocTrend(sctr);
		return cl;
	}
}
