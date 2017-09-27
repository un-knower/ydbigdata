package com.yd.ydbi.tools;

import java.util.Comparator;

import com.yd.ydbi.hp.model.PerCapitalEffectTopN;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author Administrator
 * 人均效能按周环比排序
 */
public class PCESortwow  implements Comparator<PerCapitalEffectTopN>{

    @Override
    public int compare(PerCapitalEffectTopN o1, PerCapitalEffectTopN o2) {
        if(ObjectUtils.isEmpty(o1)||ObjectUtils.isEmpty(o2)){
            return 0;
        }
        if (StringUtils.isEmpty(o1.getWeek_cycle())||StringUtils.isEmpty(o2.getWeek_cycle())){
            return 0;
        }
        String o1wc = removedot(o1);
        String o2wc = removedot(o2);
        return Integer.valueOf(o2wc)-Integer.valueOf(o1wc);
    }

    private String removedot(PerCapitalEffectTopN o2) {
        String w = o2.getWeek_cycle();
        return  w.replaceFirst("\\.", "");

    }


}
