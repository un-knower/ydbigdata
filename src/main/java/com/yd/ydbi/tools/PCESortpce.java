package com.yd.ydbi.tools;

import java.util.Comparator;

import com.yd.ydbi.hp.model.PerCapitalEffectTopN;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author Administrator
 * 人均效能按人均量排序
 */
public class PCESortpce implements Comparator<PerCapitalEffectTopN> {

    @Override
    public int compare(PerCapitalEffectTopN o1, PerCapitalEffectTopN o2) {
        if(ObjectUtils.isEmpty(o1)||ObjectUtils.isEmpty(o2)){
            return 0;
        }
        if (StringUtils.isEmpty(o1.getP_num())||StringUtils.isEmpty(o2.getP_num())){
            return 0;
        }
        return Integer.valueOf(o2.getP_num())-Integer.valueOf(o1.getP_num());
    }


}
