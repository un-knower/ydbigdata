package com.yd.ydbi.hp.service.impl;

import com.yd.ydbi.hp.dao.HomepageMapper;
import com.yd.ydbi.hp.dao.NbtrmarkMapper;
import com.yd.ydbi.hp.model.*;
import com.yd.ydbi.hp.service.Home_pageService;
import com.yd.ydbi.tools.AnyOnAny;
import com.yd.ydbi.tools.AnyTrend;
import com.yd.ydbi.tools.PCESortpce;
import com.yd.ydbi.tools.PCESortwow;
import com.yd.ydbi.ys.model.CarLine;
import com.yd.ydbi.ys.service.YS_carLine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class Home_pageServiceImpl implements Home_pageService {

    @Autowired
    private HomepageMapper homepageMapper;
    @Autowired
    private NbtrmarkMapper nbtrmarkMapper;
    @Autowired
    private YS_carLine yS_carLine;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public HomePage searchData(Map<String, String> params) {
        HomePage hp = new HomePage();
        // 查菜鸟
        findCainiao(params, hp);
        // 查询出全国
        List<HomePage2zj> homePageCountry = homepageMapper.homePageCountry(params);
        HomePage2zj tdz = homePageCountry.get(0);
        HomePage2zj ytz = homePageCountry.get(1);
        HomePage2zj lwz = homePageCountry.get(2);
        if (!ObjectUtils.isEmpty(tdz)) {
            sendBagIndex(hp, tdz, ytz, lwz);
            collectIndex(hp, tdz, ytz, lwz);
            orderNumIndex(hp, tdz, ytz, lwz);
            dispatchCarIndex(hp, tdz, ytz, lwz);
            signIndex(hp, tdz, ytz, lwz);
            loadingBagIndex(hp, tdz, ytz, lwz);
        }

        PCN(params, hp);

        //开通车线
        CarLine carLine = yS_carLine.searchDate(params);
        hp.setOpen_line(carLine.getCarLinenum());
        hp.setOi_dod(carLine.getIncreased());
        hp.setOi_wow(carLine.getCanceled());
        return hp;
    }

    /**
     * 查菜鸟
     * @param params
     * @param hp
     */
    private void findCainiao(Map<String, String> params, HomePage hp) {
        NewbeeMark nbm = nbtrmarkMapper.getNbm(params);
        if (!ObjectUtils.isEmpty(nbm)) {
            hp.setNewbee_ave(nbm.getSum_mark());
            hp.setNewbee_mark(nbm.getTotal_mark());
            hp.setNewbee_rank(nbm.getRank());
            hp.setSx_flag(nbm.getFlag());
        }
    }

    /**
     * 人均件量
     * @param params
     * @param hp
     */
    private void PCN(Map<String, String> params, HomePage hp) {
        List<Map<String, String>> pceHeader = homepageMapper.getPCEHeader(params);
        String td = getString(pceHeader.get(0).get("value"));
        String ytd = getString(pceHeader.get(1).get("value"));
        String lwk = getString(pceHeader.get(2).get("value"));
        //设置头部三个数据
        if (!StringUtils.isEmpty(td)) {
            hp.setPer_capita_num(td);
            if (!StringUtils.isEmpty(ytd)) {
                String dod = AnyOnAny.getDOD(td, ytd);
                hp.setPcn_dod(dod);
            }
            if (!StringUtils.isEmpty(lwk)) {
                String wow = AnyOnAny.getWOW(td, lwk);
                hp.setPcn_wow(wow);
            }
        }
    }

    /**
     * 差人均件量
     * @param params 时间是date_time
     * @return
     */
    public  List<String> PCN2(Map<String, String> params) {
        ArrayList<String> strings = new ArrayList<>();
        List<Map<String, String>> pceHeader = homepageMapper.getPCEHeader(params);
        String td = getString(pceHeader.get(0).get("value"));
        String ytd = getString(pceHeader.get(1).get("value"));
        String lwk = getString(pceHeader.get(2).get("value"));
        String dod="";
        String wow="";
        if (!StringUtils.isEmpty(ytd)) {
             dod = AnyOnAny.getDOD(td, ytd);
        }
        if (!StringUtils.isEmpty(lwk)) {
             wow = AnyOnAny.getWOW(td, lwk);
        }
        strings.set(0,td);
        strings.set(1,dod);
        strings.set(2,wow);

        return  strings;

    }
    /**
     * 派件量指标
     *
     * @param hp
     * @param tdz
     * @param ytz
     * @param lwz
     */
    private void sendBagIndex(HomePage hp, HomePage2zj tdz, HomePage2zj ytz, HomePage2zj lwz) {
        if (StringUtils.isNotEmpty(tdz.getSend_num())) {
            hp.setSend_num(tdz.getSend_num());
            if (!ObjectUtils.isEmpty(lwz) && StringUtils.isNotEmpty(lwz.getSend_num())) {
                hp.setSd_wow(AnyOnAny.getWOW(tdz.getSend_num(), lwz.getSend_num()));
            }
            if (!ObjectUtils.isEmpty(ytz) && StringUtils.isNotEmpty(ytz.getSend_num())) {
                hp.setSd_dod(AnyOnAny.getDOD(tdz.getSend_num(), ytz.getSend_num()));
            }
        }
    }

    /**
     * 揽件量指标
     *
     * @param hp
     * @param tdz
     * @param ytz
     * @param lwz
     */
    private void collectIndex(HomePage hp, HomePage2zj tdz, HomePage2zj ytz, HomePage2zj lwz) {
        if (StringUtils.isNotEmpty(tdz.getAll_collect())) {

            hp.setAll_collect_num(tdz.getAll_collect());
            if (!ObjectUtils.isEmpty(ytz) && StringUtils.isNotEmpty(ytz.getAll_collect())) {
                hp.setDay_on_day(AnyOnAny.getDOD(tdz.getAll_collect(), ytz.getAll_collect()));
            }
            if (!ObjectUtils.isEmpty(lwz) && StringUtils.isNotEmpty(lwz.getAll_collect())) {
                hp.setWeak_on_weak(AnyOnAny.getWOW(tdz.getAll_collect(), lwz.getAll_collect()));
            }
        }
    }

    /**
     * 签收指标
     *
     * @param hp
     * @param tdz
     * @param ytz
     * @param lwz
     * @return
     */
    private void signIndex(HomePage hp, HomePage2zj tdz, HomePage2zj ytz, HomePage2zj lwz) {
        if (StringUtils.isNotEmpty(tdz.getGoal_sign()) && StringUtils.isNotEmpty(tdz.getReal_sign())) {
            String ratesign = AnyTrend.getRate(tdz.getReal_sign(), tdz.getGoal_sign(), 2);
            hp.setSign_rate(ratesign);
            if (!ObjectUtils.isEmpty(ytz) && StringUtils.isNotEmpty(ytz.getReal_sign())
                    && StringUtils.isNotEmpty(ytz.getGoal_sign())) {
                hp.setSr_dod(
                        AnyOnAny.getDOD(ratesign, AnyTrend.getRate(ytz.getReal_sign(), ytz.getGoal_sign(), 2)));
            }
            if (!ObjectUtils.isEmpty(lwz) && StringUtils.isNotEmpty(lwz.getReal_sign())
                    && StringUtils.isNotEmpty(lwz.getGoal_sign())) {
                hp.setSr_wow(
                        AnyOnAny.getDOD(ratesign, AnyTrend.getRate(lwz.getReal_sign(), lwz.getGoal_sign(), 2)));
            }
        }
    }

    /**
     * 装载指标
     *
     * @param hp
     * @param tdz
     * @param ytz
     * @param lwz
     */
    private void loadingBagIndex(HomePage hp, HomePage2zj tdz, HomePage2zj ytz, HomePage2zj lwz) {
        if (StringUtils.isNotEmpty(tdz.getGoal_ship()) && StringUtils.isNotEmpty(tdz.getReal_ship())) {
            String freightrate = AnyTrend.getRate(tdz.getReal_ship(), tdz.getGoal_ship(), 2);
            hp.setFreight_rate(freightrate);
            if (!ObjectUtils.isEmpty(ytz) && StringUtils.isNotEmpty(ytz.getReal_ship())
                    && StringUtils.isNotEmpty(ytz.getGoal_ship())) {
                hp.setFr_dod(
                        AnyOnAny.getDOD(freightrate, AnyTrend.getRate(ytz.getReal_ship(), ytz.getGoal_ship(), 2)));
            }
            if (!ObjectUtils.isEmpty(lwz) && StringUtils.isNotEmpty(lwz.getReal_ship())
                    && StringUtils.isNotEmpty(lwz.getGoal_ship())) {
                hp.setFr_wow(
                        AnyOnAny.getDOD(freightrate, AnyTrend.getRate(lwz.getReal_ship(), lwz.getGoal_ship(), 2)));
            }
        }
    }

    /**
     * 发车指标
     *
     * @param hp
     * @param tdz
     * @param ytz
     * @param lwz
     */
    private void dispatchCarIndex(HomePage hp, HomePage2zj tdz, HomePage2zj ytz, HomePage2zj lwz) {
        if (StringUtils.isNotEmpty(tdz.getDispatch_car())) {
            hp.setSend_car(tdz.getDispatch_car());
            if (!ObjectUtils.isEmpty(ytz) && StringUtils.isNotEmpty(ytz.getDispatch_car())) {
                hp.setSc_dod(AnyOnAny.getDOD(tdz.getDispatch_car(), ytz.getDispatch_car()));
            }
            if (!ObjectUtils.isEmpty(lwz) && StringUtils.isNotEmpty(lwz.getDispatch_car())) {
                hp.setSc_wow(AnyOnAny.getWOW(tdz.getDispatch_car(), lwz.getDispatch_car()));
            }
        }
    }

    /**
     * 订单指标
     *
     * @param hp
     * @param tdz
     * @param ytz
     * @param lwz
     */
    private void orderNumIndex(HomePage hp, HomePage2zj tdz, HomePage2zj ytz, HomePage2zj lwz) {
        if (StringUtils.isNotEmpty(tdz.getOrder_num())) {
            hp.setOrder_num(tdz.getOrder_num());
            if (!ObjectUtils.isEmpty(ytz) && StringUtils.isNotEmpty(ytz.getOrder_num())) {
                hp.setOn_dod(AnyOnAny.getDOD(tdz.getOrder_num(), ytz.getOrder_num()));
            }
            if (!ObjectUtils.isEmpty(lwz) && StringUtils.isNotEmpty(lwz.getOrder_num())) {
                hp.setOn_wow(AnyOnAny.getWOW(tdz.getOrder_num(), lwz.getOrder_num()));
            }
        }
    }

    @Override
    public PerCapitaEffectHeader getPCEHeader(Map<String, String> paramsMap) {
        PerCapitaEffectHeader ph = new PerCapitaEffectHeader();
        List<Map<String, String>> pceHeader = homepageMapper.getPCEHeader(paramsMap);
        String td = getString(pceHeader.get(0).get("value"));
        String ytd = getString(pceHeader.get(1).get("value"));
        String lwk = getString(pceHeader.get(2).get("value"));
        String u1000 = getString(pceHeader.get(3).get("value"));
        String ua = getString(pceHeader.get(4).get("value"));
        String la = getString(pceHeader.get(5).get("value"));
        String al = getString(pceHeader.get(6).get("value"));
        String ac = getString(pceHeader.get(7).get("value"));


        //设置头部三个数据
        if (!StringUtils.isEmpty(td)) {
            ph.setAllcountry(td);
            if (!StringUtils.isEmpty(ytd)) {
                String dod = AnyOnAny.getDOD(td, ytd);
                ph.setAllcountryDailyChain(dod);
            }
            if (!StringUtils.isEmpty(lwk)) {
                String wow = AnyOnAny.getWOW(td, lwk);
                ph.setAllcountryCycleRing(wow);
            }
        }
        //====================================
        ph.setUpAllcountrythousand(u1000);
        ph.setUpAllcountryAverange(ua);
        ph.setLowAllcountryAverange(la);
        if (!StringUtils.isEmpty(al)) {
            ph.setLowAllcountryAverangei(AnyTrend.getRate(la, al, 2));
            ph.setUpAllcountryAverangei(AnyTrend.getRate(ua, al, 2));
            ph.setUpAllcountrythousandi(AnyTrend.getRate(u1000, al, 2));
        }
        ph.setAllcity(ac);

        return ph;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PerCaptaEffectTrend> getPCETrend(Map<String, String> paramsMap) {
        List<PerCaptaEffectTrend> list = new ArrayList<PerCaptaEffectTrend>();
        for (int i = 0; i < 15; i++) {
            if (i != 0) {
                paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
            }
            List<Map<String, String>> pcet = homepageMapper.getPCETrend(paramsMap);
            String cur = getString(pcet.get(0).get("value"));
            String samedate = getString(pcet.get(1).get("value"));
            PerCaptaEffectTrend perCaptaEffectTrend = new PerCaptaEffectTrend(paramsMap.get("date_time"), cur, samedate,
                    String.valueOf(i + 1));
            list.add(perCaptaEffectTrend);
        }

        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PerCapitaEffectFoot getPCEFoot(Map<String, String> paramsMap) {
        PerCapitaEffectFoot pcefoot = new PerCapitaEffectFoot();
        //先获取周环比 分拨中心 人均量
        HashMap<String, PerCapitalEffectTopN> hm = new HashMap<String, PerCapitalEffectTopN>();
        List<PerCapitalEffectTopN> pceFoot = homepageMapper.getPCEFoot(paramsMap);
        for (int i = 0; i < pceFoot.size(); i++) {
            hm.put(pceFoot.get(i).getFb_code(), pceFoot.get(i));
        }
        //取峰值
        findMoutainTopNum(paramsMap, pcefoot, hm);
        return pcefoot;
    }

    private void findMoutainTopNum(Map<String, String> paramsMap, PerCapitaEffectFoot pcefoot, HashMap<String, PerCapitalEffectTopN> hm) {
        String pcetype = paramsMap.get("pcetype");
        String dt = paramsMap.get("date_time");
        int i = AnyTrend.strDayOfDays(dt);
        for (int j = 0; j < i; j++) {
            if (j != 0) {
                paramsMap.put("date_time", AnyTrend.getDateDec1(paramsMap.get("date_time"), 1));
            }
            List<PerCapitalEffectTopN> pceTops = homepageMapper.getPCETop(paramsMap);
            if (pceTops != null && pceTops.size() > 0) {
                for (PerCapitalEffectTopN pcex : pceTops) {
                    String p_num = pcex.getP_num();
                    String fb_code = pcex.getFb_code();
                    PerCapitalEffectTopN pcetop = hm.get(fb_code);
                    if (!StringUtils.isEmpty(p_num) && !StringUtils.isEmpty(fb_code)) {
                        if (pcetop != null && pcetop.getTop_num() == null) {
                            pcetop.setTop_num(p_num);
                            hm.put(fb_code, pcetop);
                        } else if (pcetop != null && Integer.valueOf(pcetop.getTop_num()) < Integer.valueOf(p_num)) {
                            pcetop.setTop_num(p_num);
                            hm.put(fb_code, pcetop);
                        }
                    }
                }
            }
        }
        List<PerCapitalEffectTopN> list = new ArrayList<PerCapitalEffectTopN>();
        for (String ind : hm.keySet()) {
            list.add(hm.get(ind));
        }
        //人均量排名
        if (list != null && list.size() > 0) {
            if (pcetype.equals("1")) {
                Collections.sort(list, new PCESortpce());
                sortTopN(pcefoot, list);
            }
            //周环比排名
            else if (pcetype.equals("0")) {
                Collections.sort(list, new PCESortwow());
                sortTopN(pcefoot, list);
            }
        }
    }

    private void sortTopN(PerCapitaEffectFoot pcefoot, List<PerCapitalEffectTopN> list) {
        List<PerCapitalEffectTopN> top5 = new ArrayList<PerCapitalEffectTopN>();
        List<PerCapitalEffectTopN> low5 = new ArrayList<PerCapitalEffectTopN>();
        pcefoot.setAll(list);
        for (int j = 0; j < 5 && j < list.size(); j++) {
            top5.add(list.get(j));
        }
        pcefoot.setTop5(top5);
        for (int m = list.size() - 1; m > list.size() - 6 && m>=0; m--) {
            low5.add(list.get(m));
        }
        pcefoot.setLow5(low5);
    }

    public static String getString(String num) {
        String str = null;
        if (num != null) {
            str = String.valueOf(num);
        }
        return str;
    }
}
