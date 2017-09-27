package com.yd.ydbi.ys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.yd.ydbi.tools.AnyOnAny;
import com.yd.ydbi.tools.AnyTrend;
import com.yd.ydbi.ys.dao.YS_carLoadRateMapper;
import com.yd.ydbi.ys.model.CLR_Detail;
import com.yd.ydbi.ys.model.CarLateRate;
import com.yd.ydbi.ys.model.LR_2;
import com.yd.ydbi.ys.model.SC_AloKbtrend;
import com.yd.ydbi.ys.service.YS_carLateRate;

import javassist.bytecode.annotation.IntegerMemberValue;

@Service
public class YS_carLateRateImpl implements YS_carLateRate {
	@Autowired
	YS_carLoadRateMapper yslr;

	@Override
	public CarLateRate searchDate(Map<String, String> paramsMap) {
		CarLateRate lr = new CarLateRate();
		String trend_type = paramsMap.get("trend_type");
		List<LR_2> flrs = yslr.findlrhead(paramsMap);
		if (!ObjectUtils.isEmpty(flrs.get(0)) && !StringUtils.isEmpty(flrs.get(0).getNum())) {
			lr.setLr(flrs.get(0).getNum());
			if (!ObjectUtils.isEmpty(flrs.get(1)) && !StringUtils.isEmpty(flrs.get(1).getNum())) {
				lr.setDlr(AnyOnAny.getDOD(flrs.get(0).getNum(), flrs.get(1).getNum()));
				lr.setYtlr(flrs.get(1).getNum());
			}
			if (!ObjectUtils.isEmpty(flrs.get(2)) && !StringUtils.isEmpty(flrs.get(2).getNum())) {
				lr.setWdr(AnyOnAny.getWOW(flrs.get(0).getNum(), flrs.get(2).getNum()));
			}
		}
		if (!ObjectUtils.isEmpty(flrs.get(4)) && !StringUtils.isEmpty(flrs.get(4).getNum())) {
			lr.setWsum(flrs.get(4).getNum());
		}
		if (!ObjectUtils.isEmpty(flrs.get(3)) && !StringUtils.isEmpty(flrs.get(3).getNum())) {
			lr.setYsum(flrs.get(3).getNum());
		}

		List<CLR_Detail> efb = yslr.finddetail(paramsMap);
		lr.setClrDetail(efb);

		// 趋势
		// 计算趋势
		switch (trend_type) {
		// 日趋势
		case "1":
			List<SC_AloKbtrend> sctr = new ArrayList<SC_AloKbtrend>();
			getDayTrend(paramsMap, sctr);
			lr.setAltrend(sctr);
			break;
		// 周趋势
		case "2":
			List<SC_AloKbtrend> sct2 = new ArrayList<SC_AloKbtrend>();
			getWeekTrend(paramsMap, sct2);
			if (sct2.size()!=1){lr.setAltrend(sct2);}else {lr.setAltrend(new ArrayList<SC_AloKbtrend>());}
			break;
		// 月趋势
		case "3":
			List<SC_AloKbtrend> sctr3 = new ArrayList<SC_AloKbtrend>();

			getMonthTrend(lr,paramsMap, sctr3);

			break;
		// 季度趋势
		case "4":

			List<SC_AloKbtrend> sctr4 = new ArrayList<SC_AloKbtrend>();
			get4MonthTrend(lr,paramsMap, sctr4);
			//if (sctr4.size()!=1){lr.setAltrend(sctr4);}else {lr.setAltrend(new ArrayList<SC_AloKbtrend>());}
			break;
		}
		return lr;

	}

	private void get4MonthTrend(CarLateRate lr,Map<String, String> paramsMap, List<SC_AloKbtrend> sctr4) {
		String date_time1 = paramsMap.get("date_time");
		Date date_time2 = AnyTrend.getLastDateOfMonth(AnyTrend.parseDate(paramsMap.get("date_time")));
		String date_time = AnyTrend.formatDate(date_time2);
		String dateDec1 = AnyTrend.getDateDec1(date_time, 1);
		for (int i = 0; i < 4; i++) {
			AnyTrend.SeasonTransform(paramsMap,i);
			SC_AloKbtrend finddaytrend = yslr.findseasontrend(paramsMap);
			if (!ObjectUtils.isEmpty(finddaytrend)) {
				finddaytrend.setNon(i + "");
				if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {

					sctr4.add(finddaytrend);
				}
			}
		}
		if (sctr4.size()!=1){lr.setAltrend(sctr4);}
		else if(sctr4.size()==1&&date_time.equals(date_time1)){lr.setAltrend(sctr4);}
		else if(sctr4.size()==1&&!dateDec1.equals(date_time1)){lr.setAltrend(sctr4);}
		else {lr.setAltrend(new ArrayList<SC_AloKbtrend>());}
	}

	private void getMonthTrend(CarLateRate lr,Map<String, String> paramsMap, List<SC_AloKbtrend> sctr3) {
		String date_time1 = paramsMap.get("date_time");
		Date date_time2 = AnyTrend.getLastDateOfMonth(AnyTrend.parseDate(paramsMap.get("date_time")));
		String date_time = AnyTrend.formatDate(date_time2);
		String dateDec1 = AnyTrend.getDateDec1(date_time, 1);
		for (int i = 0; i < 6; i++) {
			AnyTrend.MonTransform(paramsMap,i);
			SC_AloKbtrend finddaytrend = yslr.findmonthtrend(paramsMap);
			if (!ObjectUtils.isEmpty(finddaytrend)) {
				if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
					finddaytrend.setNon(i + "");
					sctr3.add(finddaytrend);
				}
			}
		}
		if (sctr3.size()!=1){lr.setAltrend(sctr3);}
		else if(sctr3.size()==1&&date_time.equals(date_time1)){lr.setAltrend(sctr3);}
		else if(sctr3.size()==1&&!dateDec1.equals(date_time1)){lr.setAltrend(sctr3);}
		else {lr.setAltrend(new ArrayList<SC_AloKbtrend>());}
	}

	private void getWeekTrend(Map<String, String> paramsMap, List<SC_AloKbtrend> sct2) {
		for (int i = 0; i < 8; i++) {
			AnyTrend.WeekTransform(paramsMap,i);
			SC_AloKbtrend finddaytrend = yslr.findweektrend(paramsMap);
			if (!ObjectUtils.isEmpty(finddaytrend)) {
				finddaytrend.setNon(i + "");
				if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
					sct2.add(finddaytrend);
				}
			}
		}
	}

	private void getDayTrend(Map<String, String> paramsMap, List<SC_AloKbtrend> sctr) {
		for (int i = 0; i < 15; i++) {
			if (i != 0) {
				paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
			}
			SC_AloKbtrend finddaytrend = yslr.finddaytrend(paramsMap);
			if (!ObjectUtils.isEmpty(finddaytrend)&&StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
				finddaytrend.setNon(i + "");
				sctr.add(finddaytrend);
			}
		}
	}
}
