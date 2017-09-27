package com.yd.ydbi.ys.service.impl;

import com.yd.ydbi.tools.AnyOnAny;
import com.yd.ydbi.tools.AnyTrend;
import com.yd.ydbi.ys.dao.WD_YS_sendCarMapper;
import com.yd.ydbi.ys.dao.YS_sendCarMapper;
import com.yd.ydbi.ys.model.DB_SendCar;
import com.yd.ydbi.ys.model.SC_AloKbtrend;
import com.yd.ydbi.ys.model.SC_Detail;
import com.yd.ydbi.ys.model.SendCar;
import com.yd.ydbi.ys.service.YS_sendCar;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YS_sendCarImpl implements YS_sendCar {

    @Autowired
    private YS_sendCarMapper scMapper;
    @Autowired
    private WD_YS_sendCarMapper wdscMapper;

    @Override
    public SendCar searchDate(Map<String, String> paramsMap) {
        SendCar sc = new SendCar();
        String trend_type = paramsMap.get("trend_type");
        String car_trend_type = paramsMap.get("car_trend_type");
        // 计算当天发车数量
        List<DB_SendCar> scnums = scMapper.findSendCars(paramsMap);
        DB_SendCar tdz = scnums.get(0);
        DB_SendCar ytz = scnums.get(1);
        DB_SendCar lwz = scnums.get(2);
        DB_SendCar tdzk = scnums.get(3);
        DB_SendCar ytzk = scnums.get(4);
        DB_SendCar lwzk = scnums.get(5);
        pushData(sc, tdz, ytz, lwz, tdzk, ytzk, lwzk);
        // 计算当天各分拨中心发车详情
        List<SC_Detail> fbDetail = scMapper.findEachFB(paramsMap);
        if (fbDetail != null && fbDetail.size() > 0) {
            sc.setsCDetail(fbDetail);
        }

        // 计算趋势
        switch (trend_type) {
            // 日趋势
            case "1":
                // 卡班车趋势
                if (car_trend_type.equals("2")) {
                    List<SC_AloKbtrend> sctr = getKBDaytrend(paramsMap);
                    sc.setAltrend(sctr);
                } else if (car_trend_type.equals("1")) {
                    List<SC_AloKbtrend> sctr = getDaytrend(paramsMap);
                    sc.setAltrend(sctr);
                }
                break;
            // 周趋势
            case "2":
                // 卡班车趋势
                if (car_trend_type.equals("2")) {
                    List<SC_AloKbtrend> sctr = getWeektrend(paramsMap);
                    sc.setAltrend(sctr);
                } else if (car_trend_type.equals("1")) {
                    paramsMap.put("kbgflag", "1");
                    List<SC_AloKbtrend> sctr = getWeektrend(paramsMap);
                    sc.setAltrend(sctr);
                }
                break;
            // 月趋势
            case "3":
                // 卡班车趋势
                if (car_trend_type.equals("2")) {
                    List<SC_AloKbtrend> sctr = getMonthtrend(paramsMap);
                    sc.setAltrend(sctr);
                } else if (car_trend_type.equals("1")) {
                    paramsMap.put("kbgflag", "1");
                    List<SC_AloKbtrend> sctr = getMonthtrend(paramsMap);
                    sc.setAltrend(sctr);
                }
                break;
            // 季度趋势
            case "4":
                // 卡班车趋势
                if (car_trend_type.equals("2")) {
                    List<SC_AloKbtrend> sctr = get4Monthtrend(paramsMap);
                    sc.setAltrend(sctr);
                } else if (car_trend_type.equals("1")) {
                    paramsMap.put("kbgflag", "1");
                    List<SC_AloKbtrend> sctr = get4Monthtrend(paramsMap);
                    sc.setAltrend(sctr);
                }
                break;
        }
        return sc;
    }

    @Override
    public SendCar searchwdDate(Map<String, String> paramsMap) {
        SendCar sc = new SendCar();
        String trend_type = paramsMap.get("trend_type");
        // 计算当天发车数量
        List<DB_SendCar> scnums = wdscMapper.findSendCars(paramsMap);
        DB_SendCar tdz = scnums.get(0);
        DB_SendCar ytz = scnums.get(1);
        DB_SendCar lwz = scnums.get(2);
        DB_SendCar zj = scnums.get(3);
        DB_SendCar yj = scnums.get(4);
        //PUSHDATA=======
        if (!ObjectUtils.isEmpty(tdz) && !StringUtils.isEmpty(tdz.getsCnum())) {
            sc.setAllCars(String.valueOf(tdz.getsCnum()));
            if (!ObjectUtils.isEmpty(ytz) && !StringUtils.isEmpty(ytz.getsCnum())) {
                String dod = AnyOnAny.getDOD(String.valueOf(tdz.getsCnum()), String.valueOf(ytz.getsCnum()));
                sc.setAllCDoD(dod);
            }
            if (!ObjectUtils.isEmpty(lwz) && !StringUtils.isEmpty(lwz.getsCnum())) {
                String wow = AnyOnAny.getWOW(String.valueOf(tdz.getsCnum()), String.valueOf(lwz.getsCnum()));
                sc.setAllCWoW(wow);
            }
        }
        if (!ObjectUtils.isEmpty(zj) && !StringUtils.isEmpty(zj.getsCnum())) {
            sc.setWave(zj.getsCnum());
        }
        if (!ObjectUtils.isEmpty(yj) && !StringUtils.isEmpty(yj.getsCnum())) {
            sc.setMave(yj.getsCnum());
        }
        if (!ObjectUtils.isEmpty(ytz) && !StringUtils.isEmpty(ytz.getsCnum())) {
            sc.setLday(ytz.getsCnum());
        }

        // 计算当天各分拨中心发车详情
        List<SC_Detail> fbDetail = wdscMapper.findEachFB(paramsMap);
        if (fbDetail != null && fbDetail.size() > 0) {
            sc.setsCDetail(fbDetail);
        }

        // 计算趋势
        switch (trend_type) {
            // 日趋势
            case "1":
                // 卡班车趋势
                List<SC_AloKbtrend> sctr = getWDDaytrend(paramsMap);
                sc.setAltrend(sctr);

                break;
            // 周趋势
            case "2":
                List<SC_AloKbtrend> sctr2 = getWDWeektrend(paramsMap);
                sc.setAltrend(sctr2);
                break;
            // 月趋势
            case "3":
                List<SC_AloKbtrend> sctr3 = getWDMonthtrend(paramsMap);
                sc.setAltrend(sctr3);
                break;
            // 季度趋势
            case "4":
                List<SC_AloKbtrend> sctr4 = getWD4Monthtrend(paramsMap);
                sc.setAltrend(sctr4);
                break;
        }

        return sc;
    }

    private List<SC_AloKbtrend> get4Monthtrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr = new ArrayList<SC_AloKbtrend>();
        for (int i = 0; i < 4; i++) {
//            if (i != 0) {
//                paramsMap.put("date_time", AnyTrend.getDateDec1m(paramsMap.get("date_time"), 4));
//            }
            AnyTrend.SeasonTransform(paramsMap,i);
            SC_AloKbtrend finddaytrend = scMapper.findseasontrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)) {
                if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
                     finddaytrend.setNon(i + "");
                    sctr.add(finddaytrend);
                }
            }
        }
        return sctr;
    }

    private List<SC_AloKbtrend> getMonthtrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr = new ArrayList<SC_AloKbtrend>();
        for (int i = 0; i < 6; i++) {
//            if (i != 0) {
//                paramsMap.put("date_time", AnyTrend.getDateDec1m(paramsMap.get("date_time"), 1));
//            }
            AnyTrend.MonTransform(paramsMap,i);
            SC_AloKbtrend finddaytrend = scMapper.findmonthtrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)) {
                if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
                    finddaytrend.setNon(i + "");
                    sctr.add(finddaytrend);
                }
            }
        }
        return sctr;
    }

    private List<SC_AloKbtrend> getWeektrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            AnyTrend.WeekTransform(paramsMap,i);
            SC_AloKbtrend finddaytrend = scMapper.findweektrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)) {
                if (StringUtils.isNotEmpty(finddaytrend.getRealnum())) {
                    finddaytrend.setNon(i + "");
                    sctr.add(finddaytrend);
                }
            }
        }
        return sctr;
    }

    private List<SC_AloKbtrend> getDaytrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr = new ArrayList<SC_AloKbtrend>();
        for (int i = 0; i < 15; i++) {
            if (i != 0) {
                paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
            }
            SC_AloKbtrend finddaytrend = scMapper.finddaytrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&&!StringUtils.isEmpty(finddaytrend.getRealnum())) {
                finddaytrend.setNon(i + "");
                sctr.add(finddaytrend);
            }
        }
        return sctr;
    }

    private List<SC_AloKbtrend> getKBDaytrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr = new ArrayList<SC_AloKbtrend>();
        for (int i = 0; i < 15; i++) {
            if (i != 0) {
                paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
            }
            SC_AloKbtrend finddaytrend = scMapper.findkbdaytrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&&!StringUtils.isEmpty(finddaytrend.getRealnum())) {
                finddaytrend.setNon(i + "");
                sctr.add(finddaytrend);
            }
        }
        return sctr;
    }

    private List<SC_AloKbtrend> getWD4Monthtrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr4 = new ArrayList<SC_AloKbtrend>();
        for (int i = 0; i < 4; i++) {
            AnyTrend.SeasonTransform(paramsMap,i);
            SC_AloKbtrend finddaytrend = wdscMapper.findseasontrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&&!StringUtils.isEmpty(finddaytrend.getRealnum())) {
                finddaytrend.setNon(i + "");
                sctr4.add(finddaytrend);
            }
        }
        return sctr4;
    }

    private List<SC_AloKbtrend> getWDMonthtrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr3 = new ArrayList<SC_AloKbtrend>();
        for (int i = 0; i < 6; i++) {
            AnyTrend.MonTransform(paramsMap,i);
            SC_AloKbtrend finddaytrend = wdscMapper.findmonthtrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&&!StringUtils.isEmpty(finddaytrend.getRealnum())) {
                finddaytrend.setNon(i + "");
                sctr3.add(finddaytrend);
            }
        }
        return sctr3;
    }

    private List<SC_AloKbtrend> getWDWeektrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr2 = new ArrayList<SC_AloKbtrend>();
        for (int i = 0; i < 8; i++) {
            AnyTrend.WeekTransform(paramsMap, i);
            SC_AloKbtrend finddaytrend = wdscMapper.findweektrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&&!StringUtils.isEmpty(finddaytrend.getRealnum())) {
                finddaytrend.setNon(i + "");
                sctr2.add(finddaytrend);
            }
        }
        return sctr2;
    }

    private List<SC_AloKbtrend> getWDDaytrend(Map<String, String> paramsMap) {
        List<SC_AloKbtrend> sctr = new ArrayList<SC_AloKbtrend>();
        for (int i = 0; i < 15; i++) {
            if (i != 0) {
                paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
            }
            SC_AloKbtrend finddaytrend = wdscMapper.findkbdaytrend(paramsMap);
            if (!ObjectUtils.isEmpty(finddaytrend)&&!StringUtils.isEmpty(finddaytrend.getRealnum())) {
                finddaytrend.setNon(i + "");
                sctr.add(finddaytrend);
            }
        }
        return sctr;
    }

    private void pushData(SendCar sc, DB_SendCar tdz, DB_SendCar ytz, DB_SendCar lwz, DB_SendCar tdzk, DB_SendCar ytzk,
                          DB_SendCar lwzk) {
        if (!ObjectUtils.isEmpty(tdz) && !StringUtils.isEmpty(tdz.getsCnum())) {
            sc.setAllCars(String.valueOf(tdz.getsCnum()));
            if (!ObjectUtils.isEmpty(ytz) && !StringUtils.isEmpty(ytz.getsCnum())) {
                String dod = AnyOnAny.getDOD(String.valueOf(tdz.getsCnum()), String.valueOf(ytz.getsCnum()));
                sc.setAllCDoD(dod);
            }
            if (!ObjectUtils.isEmpty(lwz) && !StringUtils.isEmpty(lwz.getsCnum())) {
                String wow = AnyOnAny.getWOW(String.valueOf(tdz.getsCnum()), String.valueOf(lwz.getsCnum()));
                sc.setAllCWoW(wow);
            }
        }
        if (!ObjectUtils.isEmpty(tdzk) && !StringUtils.isEmpty(tdzk.getsCnum())) {
            sc.setKbCars(String.valueOf(tdzk.getsCnum()));
            if (!ObjectUtils.isEmpty(ytzk) && !StringUtils.isEmpty(ytzk.getsCnum())) {
                String dodk = AnyOnAny.getDOD(String.valueOf(tdzk.getsCnum()), String.valueOf(ytzk.getsCnum()));
                sc.setKbCDoD(dodk);
            }
            if (!ObjectUtils.isEmpty(lwzk) && !StringUtils.isEmpty(lwzk.getsCnum())) {
                String wowk = AnyOnAny.getWOW(String.valueOf(tdzk.getsCnum()), String.valueOf(lwzk.getsCnum()));
                sc.setKbCWoW(wowk);
            }
        }
    }
}
