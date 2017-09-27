package com.yd.ydbi.ys.service.impl;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.ys.model.CarLine;
import com.yd.ydbi.ys.service.YS_carLine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.yd.ydbi.tools.AnyOnAny;
import com.yd.ydbi.ys.dao.WD_YS_delayRateMapper;
import com.yd.ydbi.ys.dao.WD_YS_sendCarMapper;
import com.yd.ydbi.ys.dao.YS_RURateMapper;
import com.yd.ydbi.ys.dao.YS_carLoadRateMapper;
import com.yd.ydbi.ys.dao.YS_delayRateMapper;
import com.yd.ydbi.ys.dao.YS_loadRateMapper;
import com.yd.ydbi.ys.dao.YS_openLineMapper;
import com.yd.ydbi.ys.dao.YS_sendCarMapper;
import com.yd.ydbi.ys.model.DB_SendCar;
import com.yd.ydbi.ys.model.LR_2;
import com.yd.ydbi.ys.model.YSS;
import com.yd.ydbi.ys.service.YS_Summary;
@Service
public class YS_SummaryImpl implements YS_Summary {

	@Autowired
	private YS_sendCarMapper scMapper;

	@Autowired
	private WD_YS_sendCarMapper wdsc;

	@Autowired
	private YS_openLineMapper olmapper;

	@Autowired
	private YS_loadRateMapper yslr;

	@Autowired
	private YS_carLoadRateMapper cyslr;

	@Autowired
	private YS_delayRateMapper dyslr;

	@Autowired
	private WD_YS_delayRateMapper wddyslr;

	@Autowired
	YS_carLine yS_carLine;

	@Autowired
	private YS_RURateMapper ryslr;
	@Override
	public YSS searchDate(Map<String, String> paramsMap) {
		YSS yys = new YSS();
		
		//计算当天发车数量
		List<DB_SendCar> scnums = scMapper.findSendCars(paramsMap);
		if (scnums!=null&&scnums.size()>0) {
			DB_SendCar tdz = scnums.get(0);
			DB_SendCar ytz = scnums.get(1);
			DB_SendCar lwz = scnums.get(2);
			if (!ObjectUtils.isEmpty(tdz)&&!StringUtils.isEmpty(tdz.getsCnum())) {
				yys.setSc(String.valueOf(tdz.getsCnum()));
				if (!ObjectUtils.isEmpty(ytz)&&!StringUtils.isEmpty(ytz.getsCnum())) {
					String dod = AnyOnAny.getDOD(String.valueOf(tdz.getsCnum()) , String.valueOf(ytz.getsCnum()));
					yys.setScd(dod);
				}
				if (!ObjectUtils.isEmpty(lwz)&&!StringUtils.isEmpty(lwz.getsCnum())) {
					String wow = AnyOnAny.getWOW(String.valueOf(tdz.getsCnum()), String.valueOf(lwz.getsCnum()));
					yys.setScw(wow);
				}
			}
		}
		//计算当天网点发车数量
		List<DB_SendCar> wdscn = wdsc.findSendCars(paramsMap);
		if (wdscn!=null&&wdscn.size()>0) {
			DB_SendCar tdz = wdscn.get(0);
			DB_SendCar ytz = wdscn.get(1);
			DB_SendCar lwz = wdscn.get(2);
			if (!ObjectUtils.isEmpty(tdz)&&!StringUtils.isEmpty(tdz.getsCnum())) {
				yys.setWdsc(String.valueOf(tdz.getsCnum()));
				if (!ObjectUtils.isEmpty(ytz)&&!StringUtils.isEmpty(ytz.getsCnum())) {
					String dod = AnyOnAny.getDOD(String.valueOf(tdz.getsCnum()) , String.valueOf(ytz.getsCnum()));
					yys.setWdscd(dod);
				}
				if (!ObjectUtils.isEmpty(lwz)&&!StringUtils.isEmpty(lwz.getsCnum())) {
					String wow = AnyOnAny.getWOW(String.valueOf(tdz.getsCnum()), String.valueOf(lwz.getsCnum()));
					yys.setWdscw(wow);
				}
			}
		}
		


        //装载率
        List<LR_2> flrs = yslr.findlrhead(paramsMap);
        if (!ObjectUtils.isEmpty(flrs.get(0))&&!StringUtils.isEmpty(flrs.get(0).getNum())) {
            yys.setLr(flrs.get(0).getNum());
            if (!ObjectUtils.isEmpty(flrs.get(1))&&!StringUtils.isEmpty(flrs.get(1).getNum())) {
                yys.setLrd(AnyOnAny.getDOD(flrs.get(0).getNum(), flrs.get(1).getNum()));
            }
            if (!ObjectUtils.isEmpty(flrs.get(2))&&!StringUtils.isEmpty(flrs.get(2).getNum())) {
                yys.setLrw(AnyOnAny.getWOW(flrs.get(0).getNum(), flrs.get(2).getNum()));
            }
        }
		//迟发率
		List<LR_2> yslrl = cyslr.findlrhead(paramsMap);
		if (yslrl!=null&&yslrl.size()>0) {
			LR_2 tdz = yslrl.get(0);
			LR_2 ytz = yslrl.get(1);
			LR_2 lwz = yslrl.get(2);
			if (!ObjectUtils.isEmpty(tdz) && !StringUtils.isEmpty(tdz.getNum())) {
				yys.setClr(tdz.getNum());
				if (!ObjectUtils.isEmpty(ytz) && !StringUtils.isEmpty(ytz.getNum())) {
					yys.setClrd(AnyOnAny.getDOD(tdz.getNum(), ytz.getNum()));
				}
				if (!ObjectUtils.isEmpty(lwz) && !StringUtils.isEmpty(lwz.getNum())) {
					yys.setClrw(AnyOnAny.getWOW(tdz.getNum(), lwz.getNum()));
				}
			}
		}
		
		//延误率
		List<LR_2> dflrs = dyslr.findlrhead(paramsMap);
		if (dflrs!=null&&dflrs.size()>0) {
			LR_2 tdz = dflrs.get(0);
			LR_2 ytz = dflrs.get(1);
			LR_2 lwz = dflrs.get(2);
			if (!ObjectUtils.isEmpty(tdz) && !StringUtils.isEmpty(tdz.getNum())) {
				yys.setDr(tdz.getNum());
				if (!ObjectUtils.isEmpty(ytz) && !StringUtils.isEmpty(ytz.getNum())) {
					yys.setDrd(AnyOnAny.getDOD(tdz.getNum(),ytz.getNum()));
				}
				if (!ObjectUtils.isEmpty(lwz) && !StringUtils.isEmpty(lwz.getNum())) {
					yys.setDrw(AnyOnAny.getWOW(tdz.getNum(), lwz.getNum()));
				}
			}
		}
		
		//网点延误率
		List<LR_2> wddflrs = wddyslr.findlrhead(paramsMap);
		if (wddflrs!=null&&wddflrs.size()>0) {
			LR_2 tdz = wddflrs.get(0);
			LR_2 ytz = wddflrs.get(1);
			LR_2 lwz = wddflrs.get(2);
			if (!ObjectUtils.isEmpty(tdz) && !StringUtils.isEmpty(tdz.getNum())) {
				yys.setWddr(tdz.getNum());
				if (!ObjectUtils.isEmpty(ytz) && !StringUtils.isEmpty(ytz.getNum())) {
					yys.setWddrd(AnyOnAny.getDOD(tdz.getNum(),ytz.getNum()));
				}
				if (!ObjectUtils.isEmpty(lwz) && !StringUtils.isEmpty(lwz.getNum())) {
					yys.setWddrw(AnyOnAny.getWOW(tdz.getNum(), lwz.getNum()));
				}
			}
		}

		//即到即卸率
		//这个功能暂时不做
//		List<LR_2> ruflr = ryslr.findlrhead(paramsMap);
//		if (ruflr!=null&&ruflr.size()>0) {
//			LR_2 tdz = ruflr.get(0);
//			LR_2 ytz = ruflr.get(1);
//			LR_2 lwz = ruflr.get(2);
//			if (!ObjectUtils.isEmpty(tdz) && !StringUtils.isEmpty(tdz.getNum())) {
//				yys.setDr(tdz.getNum());
//				if (!ObjectUtils.isEmpty(ytz) && !StringUtils.isEmpty(ytz.getNum())) {
//					yys.setDrd(AnyOnAny.getDOD(tdz.getNum(), ytz.getNum()));
//				}
//				if (!ObjectUtils.isEmpty(lwz) && !StringUtils.isEmpty(lwz.getNum())) {
//					yys.setDrw(AnyOnAny.getWOW(tdz.getNum(), lwz.getNum()));
//				}
//			}
//		}
        //开通车线
		paramsMap.put("sel_cd","0");
		paramsMap.put("sel_level","0");
		paramsMap.put("area_id","0");
        CarLine carLine = yS_carLine.searchDate(paramsMap);
        yys.setOp(carLine.getCarLinenum());
        yys.setOpd(carLine.getIncreased());
        yys.setOpw(carLine.getCanceled());
		return yys;

	}

}
