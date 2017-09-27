package com.yd.ydbi.ys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.yd.ydbi.tools.AnyOnAny;
import com.yd.ydbi.tools.AnyTrend;
import com.yd.ydbi.ys.dao.WD_YS_delayRateMapper;
import com.yd.ydbi.ys.dao.YS_delayRateMapper;
import com.yd.ydbi.ys.model.CLR_Detail;
import com.yd.ydbi.ys.model.CarLateRate;
import com.yd.ydbi.ys.model.LR_2;
import com.yd.ydbi.ys.model.SC_AloKbtrend;
import com.yd.ydbi.ys.service.YS_delayRate;
@Service
public class YS_delayRateImpl implements YS_delayRate {

	@Autowired
	YS_delayRateMapper yslr;
	@Autowired
	WD_YS_delayRateMapper wdyslr;

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
			lr.setAltrend(sctr);

			break;
		// 周趋势
		case "2":

			List<SC_AloKbtrend> sct2 = new ArrayList<SC_AloKbtrend>();
			for (int i = 0; i < 8; i++) {
				AnyTrend.WeekTransform(paramsMap,i);
				SC_AloKbtrend finddaytrend = yslr.findweektrend(paramsMap);
				if (!ObjectUtils.isEmpty(finddaytrend)) {
					if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
						finddaytrend.setNon(i + "");
						sct2.add(finddaytrend);
					}
				}
			}
			lr.setAltrend(sct2);

			break;
		// 月趋势
		case "3":

			List<SC_AloKbtrend> sctr3 = new ArrayList<SC_AloKbtrend>();
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
			lr.setAltrend(sctr3);

			break;
		// 季度趋势
		case "4":

			List<SC_AloKbtrend> sctr4 = new ArrayList<SC_AloKbtrend>();
			for (int i = 0; i < 4; i++) {
				AnyTrend.SeasonTransform(paramsMap,i);
				SC_AloKbtrend finddaytrend = yslr.findseasontrend(paramsMap);
				if (!ObjectUtils.isEmpty(finddaytrend)) {
					if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
						finddaytrend.setNon(i + "");
						sctr4.add(finddaytrend);
					}
				}
			}
			lr.setAltrend(sctr4);
			break;
		}
		return lr;
	}

	@Override
	public CarLateRate searchWDDate(Map<String, String> paramsMap) {
		CarLateRate lr = new CarLateRate();
		String trend_type = paramsMap.get("trend_type");
		List<LR_2> flrs = wdyslr.findlrhead(paramsMap);
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

		List<CLR_Detail> efb = wdyslr.finddetail(paramsMap);
		lr.setClrDetail(efb);

		// 计算趋势
		switch (trend_type) {
		// 日趋势
		case "1":
			List<SC_AloKbtrend> sctr = new ArrayList<SC_AloKbtrend>();
			for (int i = 0; i < 15; i++) {
				if (i != 0) {
					paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
				}
				SC_AloKbtrend finddaytrend = wdyslr.finddaytrend(paramsMap);
				if (!ObjectUtils.isEmpty(finddaytrend)&&StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
					finddaytrend.setNon(i + "");
					sctr.add(finddaytrend);
				}
			}
			lr.setAltrend(sctr);
			break;
		// 周趋势
		case "2":
			List<SC_AloKbtrend> sct2 = new ArrayList<SC_AloKbtrend>();
			for (int i = 0; i < 8; i++) {
				AnyTrend.WeekTransform(paramsMap,i);
				SC_AloKbtrend finddaytrend = wdyslr.findweektrend(paramsMap);
				if (!ObjectUtils.isEmpty(finddaytrend)) {
					if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
						finddaytrend.setNon(i + "");
						sct2.add(finddaytrend);
					}
				}
			}
			lr.setAltrend(sct2);
			break;
		// 月趋势
		case "3":
			List<SC_AloKbtrend> sctr3 = new ArrayList<SC_AloKbtrend>();
			for (int i = 0; i < 6; i++) {
				AnyTrend.MonTransform(paramsMap,i);
				SC_AloKbtrend finddaytrend = wdyslr.findmonthtrend(paramsMap);
				if (!ObjectUtils.isEmpty(finddaytrend)) {
					if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
						finddaytrend.setNon(i + "");
						sctr3.add(finddaytrend);
					}
				}
			}
			lr.setAltrend(sctr3);
			break;
		// 季度趋势
		case "4":
			List<SC_AloKbtrend> sctr4 = new ArrayList<SC_AloKbtrend>();
			for (int i = 0; i < 4; i++) {
				AnyTrend.SeasonTransform(paramsMap,i);
				SC_AloKbtrend finddaytrend = wdyslr.findseasontrend(paramsMap);
				if (!ObjectUtils.isEmpty(finddaytrend)) {
					if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
						finddaytrend.setNon(i + "");
						sctr4.add(finddaytrend);
					}
				}
			}
			lr.setAltrend(sctr4);
			break;
		}
		return lr;
	}
}
