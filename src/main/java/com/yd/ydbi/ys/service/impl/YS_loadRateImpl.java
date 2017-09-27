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
import com.yd.ydbi.ys.dao.YS_loadRateMapper;
import com.yd.ydbi.ys.model.LR_2;
import com.yd.ydbi.ys.model.LR_Detail;
import com.yd.ydbi.ys.model.LoadRate;
import com.yd.ydbi.ys.model.SC_AloKbtrend;
@Service
public class YS_loadRateImpl implements com.yd.ydbi.ys.service.YS_loadRate {
	@Autowired
	YS_loadRateMapper yslr;

	@Override
	public LoadRate searchDate(Map<String, String> paramsMap) {
		String trend_type = paramsMap.get("trend_type");
		String car_trend_type = paramsMap.get("car_trend_type");
		LoadRate lr = new LoadRate();
		List<LR_2> flrs = yslr.findlrhead(paramsMap);
		if (!ObjectUtils.isEmpty(flrs.get(0))&&!StringUtils.isEmpty(flrs.get(0).getNum())) {
			lr.setLr(flrs.get(0).getNum());
			if (!ObjectUtils.isEmpty(flrs.get(1))&&!StringUtils.isEmpty(flrs.get(1).getNum())) {
				lr.setDlr(AnyOnAny.getDOD(flrs.get(0).getNum(), flrs.get(1).getNum()));
				lr.setYtlr(flrs.get(1).getNum());
			}
			if (!ObjectUtils.isEmpty(flrs.get(2))&&!StringUtils.isEmpty(flrs.get(2).getNum())) {
				lr.setWdr(AnyOnAny.getWOW(flrs.get(0).getNum(), flrs.get(2).getNum()));
			}
		}
		if (!ObjectUtils.isEmpty(flrs.get(4))&&!StringUtils.isEmpty(flrs.get(4).getNum())) {
			lr.setWsum(flrs.get(4).getNum());
		}
		if (!ObjectUtils.isEmpty(flrs.get(3))&&!StringUtils.isEmpty(flrs.get(3).getNum())) {
			lr.setYsum(flrs.get(3).getNum());
		}

		List<LR_2> lrzb = yslr.findlrbodyzb(paramsMap);
		if (!StringUtils.isEmpty(lrzb.get(0).getNum())) {
			lr.setZbc(lrzb.get(0).getNum());
			if (!StringUtils.isEmpty(lrzb.get(1).getNum())) {
				lr.setDzbc(AnyOnAny.getDOD(lrzb.get(0).getNum(), lrzb.get(1).getNum()));
			}
			if (!StringUtils.isEmpty(lrzb.get(2).getNum())) {
				lr.setWzbc(AnyOnAny.getWOW(lrzb.get(0).getNum(), lrzb.get(2).getNum()));
			}
		}
		if (!StringUtils.isEmpty(lrzb.get(1).getNum())) {
			lr.setLzbc(lrzb.get(1).getNum());
		}

		List<LR_2> lrkb = yslr.findlrbodykb(paramsMap);
		if (!StringUtils.isEmpty(lrkb.get(0).getNum())) {
			lr.setKbc(lrkb.get(0).getNum());
			if (!StringUtils.isEmpty(lrkb.get(1).getNum())) {
				lr.setDkbc(AnyOnAny.getDOD(lrkb.get(0).getNum(), lrkb.get(1).getNum()));
			}
			if (!StringUtils.isEmpty(lrkb.get(2).getNum())) {
				lr.setWkbc(AnyOnAny.getWOW(lrkb.get(0).getNum(), lrkb.get(2).getNum()));
			}
		}
		if (!StringUtils.isEmpty(lrkb.get(1).getNum())) {
			lr.setLkbc(lrkb.get(1).getNum());
		}

		List<LR_Detail> efb = yslr.finddetail(paramsMap);
		lr.setSlrDetail(efb);


		//计算趋势
		switch (trend_type) {
		// 日趋势
		case "1":
			// 卡班车趋势
			if (car_trend_type.equals("2")) {
				paramsMap.put("kb", "2");
				getDT(paramsMap, lr);
			} else if (car_trend_type.equals("1")) {
				paramsMap.put("al", "1");
				getDT(paramsMap, lr);
				//正班车
			} else if (car_trend_type.equals("3")){
				paramsMap.put("zb", "3");
				getDT(paramsMap, lr);
			}
			break;
		// 周趋势
		case "2":
			// 卡班车趋势
			if (car_trend_type.equals("2")) {
				paramsMap.put("kb", "2");
				getWT(paramsMap, lr);
			} else if (car_trend_type.equals("1")) {
				paramsMap.put("al", "1");
				getWT(paramsMap, lr);
			}else if (car_trend_type.equals("3")) {
				paramsMap.put("zb", "3");
				getWT(paramsMap, lr);
			}
			break;
		// 月趋势
		case "3":
			// 卡班车趋势
			if (car_trend_type.equals("2")) {
				paramsMap.put("kb", "2");
				getMT(paramsMap, lr);
			} else if (car_trend_type.equals("1")) {
				paramsMap.put("al", "1");
				getMT(paramsMap, lr);
			}else if (car_trend_type.equals("3")) {
				paramsMap.put("zb", "3");
				getMT(paramsMap, lr);
			}
			break;
		// 季度趋势
		case "4":
			// 卡班车趋势
			if (car_trend_type.equals("2")) {
				paramsMap.put("kb", "2");
				getST(paramsMap, lr);
			} else if (car_trend_type.equals("1")) {
				paramsMap.put("al", "1");
				getST(paramsMap, lr);
			}else if (car_trend_type.equals("3")) {
				paramsMap.put("zb", "3");
				getST(paramsMap, lr);
			}
			break;
		}
		return lr;
	}

	private void getST(Map<String, String> paramsMap, LoadRate lr) {
		List<SC_AloKbtrend> sctr=new ArrayList<SC_AloKbtrend>();

		String date_time1 = paramsMap.get("date_time");
		Date date_time2 = AnyTrend.getLastDateOfMonth(AnyTrend.parseDate(paramsMap.get("date_time")));
		String date_time = AnyTrend.formatDate(date_time2);
		String dateDec1 = AnyTrend.getDateDec1(date_time, 1);

		for (int i = 0; i < 4; i++) {
            AnyTrend.SeasonTransform(paramsMap,i);
            SC_AloKbtrend finddaytrend = yslr.findseasontrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&& StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
                    finddaytrend.setNon(i + "");
                    sctr.add(finddaytrend);
            }
        }
		if (sctr.size()!=1){lr.setAltrend(sctr);}
		else if(sctr.size()==1&&date_time.equals(date_time1)){lr.setAltrend(sctr);}
		else if(sctr.size()==1&&!dateDec1.equals(date_time1)){lr.setAltrend(sctr);}
		else {lr.setAltrend(new ArrayList<SC_AloKbtrend>());}

	}

	private void getMT(Map<String, String> paramsMap, LoadRate lr) {
		List<SC_AloKbtrend> sctr=new ArrayList<SC_AloKbtrend>();

		String date_time1 = paramsMap.get("date_time");
		Date date_time2 = AnyTrend.getLastDateOfMonth(AnyTrend.parseDate(paramsMap.get("date_time")));
		String date_time = AnyTrend.formatDate(date_time2);
		String dateDec1 = AnyTrend.getDateDec1(date_time, 1);

		for (int i = 0; i < 6; i++) {
            AnyTrend.MonTransform(paramsMap,i);
            SC_AloKbtrend finddaytrend = yslr.findmonthtrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&& StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
                finddaytrend.setNon(i + "");
                sctr.add(finddaytrend);
            }
        }
		if (sctr.size()!=1){lr.setAltrend(sctr);}
		else if(sctr.size()==1&&date_time.equals(date_time1)){lr.setAltrend(sctr);}
		else if(sctr.size()==1&&!dateDec1.equals(date_time1)){lr.setAltrend(sctr);}
		else {lr.setAltrend(new ArrayList<SC_AloKbtrend>());}
	}

	private void getWT(Map<String, String> paramsMap, LoadRate lr) {
		List<SC_AloKbtrend> sctr=new ArrayList<SC_AloKbtrend>();



		for (int i = 0; i < 8; i++) {
            AnyTrend.WeekTransform(paramsMap, i);
            SC_AloKbtrend finddaytrend = yslr.findweektrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&& StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
                    finddaytrend.setNon(i+"");
                    sctr.add(finddaytrend);
            }
        }
		if (sctr.size()!=1){lr.setAltrend(sctr);}else {lr.setAltrend(new ArrayList<SC_AloKbtrend>());}
	}

	private void getDT(Map<String, String> paramsMap, LoadRate lr) {
		List<SC_AloKbtrend> sctr=new ArrayList<SC_AloKbtrend>();
		for (int i = 0; i < 15; i++) {
            if (i!=0) {
                paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
            }
            SC_AloKbtrend finddaytrend = yslr.finddaytrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&& StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
                finddaytrend.setNon(i+"");
                sctr.add(finddaytrend);
            }
        }
		lr.setAltrend(sctr);
	}

}
