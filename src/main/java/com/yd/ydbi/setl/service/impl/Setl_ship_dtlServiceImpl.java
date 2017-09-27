package com.yd.ydbi.setl.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yd.common.cache.CIPCacheManager;
import com.yd.common.cache.CIPCacheService;
import com.yd.common.conf.CIPConfigureManager;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.ydbi.common.function.controller.ToolController;
import com.yd.ydbi.setl.dao.Setl_ship_dtlMapper;
import com.yd.ydbi.setl.model.Setl_ship_dtl;
import com.yd.ydbi.setl.service.Setl_ship_dtlService;
import com.yd.ydbi.utils.ShellUtil;

/**
 * 
 * 结算明细信息业务处理实现类. <br/>
 *
 * @date:2017-06-11 16:42:35 <br/>
 * @author 康元佳
 * @version:
 * @since:JDK 1.7
 */
@Service
public class Setl_ship_dtlServiceImpl implements Setl_ship_dtlService {
	private Logger logger = Logger.getLogger(ToolController.class);
	
	@Autowired
	private Setl_ship_dtlMapper setl_ship_dtlMapper;
	
	CIPCacheService cache = CIPCacheManager.getCacheService();
	
	@Override
	public List<Setl_ship_dtl> searchData(Map<String, Object> paramsMap) {
		String ship_id_str = (String) paramsMap.get("ship_id");
		String[] ship_id_array = ship_id_str.split(",");
		paramsMap.put("ship_id_array", ship_id_array);
		
		List<String> tb_list = new ArrayList<String>();
		String sel_fee_cd = (String) paramsMap.get("one_fee_cd");
		if(sel_fee_cd.isEmpty() && "".equals(sel_fee_cd)){
			tb_list.add("ods_unit_trans_smal_wgt_dtl");         // 01  中转费               中转费0300结算小件重量信息分发
			tb_list.add("ods_unit_trans_pkg_smal_wgt");         // 01  中转费               中转费0500大包重量和小件重量和导出
			tb_list.add("ods_unit_fst_trans_setl_dtl");         // 01  中转费               第一天中转费结算
			tb_list.add("ods_unit_ten_trans_setl_dtl");         // 01  中转费调整        第十天中转费结算
			tb_list.add("ods_unit_inter_exp_brch_dtl");         // 07  互带费               互带费按照网点或服务部分发
			tb_list.add("ods_unit_arv_fee_brh_dtl");            // 04  到付费               到付费网点明细报表（按网点或服务部分发）
//			tb_list.add("ods_unit_arv_fee_dist_dtl");           // 04  到付费               到付费分发数据处理
			tb_list.add("ods_unit_comp_othr_setl");             // 03  有偿有费用        有偿派费其他费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_othr_devi_setl");        // 03  有偿有费用        有偿派费其他费用按照分部分发
			tb_list.add("ods_unit_comp_no_exp_brch_setl");      // 03  有偿无费用        有偿无费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_no_exp_devi_setl");      // 03  有偿无费用        有偿无费用按照分部分发
			tb_list.add("ods_unit_comp_asur_amnt_rfnd_setl");   // 03  有偿保证金退     有偿保证金退按照结算网点或服务部分发
			tb_list.add("ods_unit_comp_viol_pnlt_setl");        // 有偿违规罚款          有偿违规罚款按照网点分发
			tb_list.add("ods_unit_area_subd_brh_dtl");          // 05  区域补贴            区域补贴分发
//			tb_list.add("ods_unit_area_subd_dist_dtl");         // 05  区域补贴            区域补贴分发
			tb_list.add("ods_unit_comp_bal_setl");              // 13  平衡派费            有偿平衡派费按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_bal_devi_setl");         // 13  平衡派费            有偿平衡派费按照分部分发
			tb_list.add("ods_unit_oper_exp_dtl");               // 12  普件操作费         普件操作费按照网点或者服务部分发
			tb_list.add("ods_unit_rtn_ship_setl");              // 退回件                     退回件结算按照网点或服务部分发
			tb_list.add("ods_unit_rtn_ship_adj_setl");          // 退回件调整              退回件调整按照网点或服务部分发
			tb_list.add("ods_unit_comp_frgn_exp_setl");         // 16  境外派费            境外派费按照网点分发
			tb_list.add("ods_unit_comp_qtr_cont_setl");         // 15  旺季续重派费      有偿旺季续重派费按照网点分发
			tb_list.add("ods_unit_gd_wb_brch_dtl");             // 广东地磅                  广东地磅网点明细报表分发
//			tb_list.add("ods_unit_gd_wb_dist_dtl");             // 广东地磅                  广东地磅分发数据处理
			tb_list.add("ods_unit_gd_wb_ppd_add");              // 广东地磅                  处理广东地磅未发送预付款补发分发数据
			tb_list.add("ods_unit_comp_asur_dect_setl");        // 03  有偿门店保证金扣    有偿派送门店扣保证金按照网点分发
			tb_list.add("ods_unit_comp_asur_rfnd_setl");        // 03  有偿门店保证金退    有偿派送门店退保证金按照网点分发
		}else if("1".equals(sel_fee_cd)){
			tb_list.add("ods_unit_trans_smal_wgt_dtl");         // 01  中转费               中转费0300结算小件重量信息分发
			tb_list.add("ods_unit_trans_pkg_smal_wgt");         // 01  中转费               中转费0500大包重量和小件重量和导出
			tb_list.add("ods_unit_fst_trans_setl_dtl");         // 01  中转费               第一天中转费结算
			tb_list.add("ods_unit_ten_trans_setl_dtl");         // 01  中转费调整        第十天中转费结算
		}else if("3".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_othr_setl");             // 03  有偿有费用        有偿派费其他费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_othr_devi_setl");        // 03  有偿有费用        有偿派费其他费用按照分部分发
			tb_list.add("ods_unit_comp_no_exp_brch_setl");      // 03  有偿无费用        有偿无费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_no_exp_devi_setl");      // 03  有偿无费用        有偿无费用按照分部分发
			tb_list.add("ods_unit_comp_asur_amnt_rfnd_setl");   // 03  有偿保证金退     有偿保证金退按照结算网点或服务部分发
			tb_list.add("ods_unit_comp_asur_dect_setl");        // 03  有偿门店保证金扣    有偿派送门店扣保证金按照网点分发
			tb_list.add("ods_unit_comp_asur_rfnd_setl");        // 03  有偿门店保证金退    有偿派送门店退保证金按照网点分发
		}else if("4".equals(sel_fee_cd)){
			tb_list.add("ods_unit_arv_fee_brh_dtl");            // 04  到付费               到付费网点明细报表（按网点或服务部分发）
//			tb_list.add("ods_unit_arv_fee_dist_dtl");           // 04  到付费               到付费分发数据处理
		}else if("5".equals(sel_fee_cd)){
			tb_list.add("ods_unit_area_subd_brh_dtl");          // 05  区域补贴            区域补贴分发
//			tb_list.add("ods_unit_area_subd_dist_dtl");         // 05  区域补贴            区域补贴分发
		}else if("7".equals(sel_fee_cd)){
			tb_list.add("ods_unit_inter_exp_brch_dtl");         // 07  互带费               互带费按照网点或服务部分发
		}else if("12".equals(sel_fee_cd)){
			tb_list.add("ods_unit_oper_exp_dtl");               // 12  普件操作费         普件操作费按照网点或者服务部分发
		}else if("13".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_bal_setl");              // 13  平衡派费            有偿平衡派费按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_bal_devi_setl");         // 13  平衡派费            有偿平衡派费按照分部分发
		}else if("15".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_qtr_cont_setl");         // 15  旺季续重派费      有偿旺季续重派费按照网点分发
		}else if("16".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_frgn_exp_setl");         // 16  境外派费            境外派费按照网点分发
		}else{
			tb_list.add("ods_unit_comp_viol_pnlt_setl");        // 有偿违规罚款          有偿违规罚款按照网点分发
			tb_list.add("ods_unit_rtn_ship_setl");              // 退回件                     退回件结算按照网点或服务部分发
			tb_list.add("ods_unit_rtn_ship_adj_setl");          // 退回件调整              退回件调整按照网点或服务部分发
			tb_list.add("ods_unit_gd_wb_brch_dtl");             // 广东地磅                  广东地磅网点明细报表分发
//			tb_list.add("ods_unit_gd_wb_dist_dtl");             // 广东地磅                  广东地磅分发数据处理
			tb_list.add("ods_unit_gd_wb_ppd_add");              // 广东地磅                  处理广东地磅未发送预付款补发分发数据
		}		
		paramsMap.put("tb_list", tb_list);
		return setl_ship_dtlMapper.searchData(paramsMap);
	}

	@Override
	public List<Setl_ship_dtl> searchFooterData(Map<String, Object> paramsMap) {
		String ship_id_str = (String) paramsMap.get("ship_id");
		String[] ship_id_array = ship_id_str.split(",");
		paramsMap.put("ship_id_array", ship_id_array);
		
		List<String> tb_list = new ArrayList<String>();
		String sel_fee_cd = (String) paramsMap.get("one_fee_cd");
		if(sel_fee_cd.isEmpty() && "".equals(sel_fee_cd)){
			tb_list.add("ods_unit_trans_smal_wgt_dtl");         // 01  中转费               中转费0300结算小件重量信息分发
			tb_list.add("ods_unit_trans_pkg_smal_wgt");         // 01  中转费               中转费0500大包重量和小件重量和导出
			tb_list.add("ods_unit_fst_trans_setl_dtl");         // 01  中转费               第一天中转费结算
			tb_list.add("ods_unit_ten_trans_setl_dtl");         // 01  中转费调整        第十天中转费结算
			tb_list.add("ods_unit_inter_exp_brch_dtl");         // 07  互带费               互带费按照网点或服务部分发
			tb_list.add("ods_unit_arv_fee_brh_dtl");            // 04  到付费               到付费网点明细报表（按网点或服务部分发）
//			tb_list.add("ods_unit_arv_fee_dist_dtl");           // 04  到付费               到付费分发数据处理
			tb_list.add("ods_unit_comp_othr_setl");             // 03  有偿有费用        有偿派费其他费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_othr_devi_setl");        // 03  有偿有费用        有偿派费其他费用按照分部分发
			tb_list.add("ods_unit_comp_no_exp_brch_setl");      // 03  有偿无费用        有偿无费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_no_exp_devi_setl");      // 03  有偿无费用        有偿无费用按照分部分发
			tb_list.add("ods_unit_comp_asur_amnt_rfnd_setl");   // 03  有偿保证金退     有偿保证金退按照结算网点或服务部分发
			tb_list.add("ods_unit_comp_viol_pnlt_setl");        // 有偿违规罚款          有偿违规罚款按照网点分发
			tb_list.add("ods_unit_area_subd_brh_dtl");          // 05  区域补贴            区域补贴分发
//			tb_list.add("ods_unit_area_subd_dist_dtl");         // 05  区域补贴            区域补贴分发
			tb_list.add("ods_unit_comp_bal_setl");              // 13  平衡派费            有偿平衡派费按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_bal_devi_setl");         // 13  平衡派费            有偿平衡派费按照分部分发
			tb_list.add("ods_unit_oper_exp_dtl");               // 12  普件操作费         普件操作费按照网点或者服务部分发
			tb_list.add("ods_unit_rtn_ship_setl");              // 退回件                     退回件结算按照网点或服务部分发
			tb_list.add("ods_unit_rtn_ship_adj_setl");          // 退回件调整              退回件调整按照网点或服务部分发
			tb_list.add("ods_unit_comp_frgn_exp_setl");         // 16  境外派费            境外派费按照网点分发
			tb_list.add("ods_unit_comp_qtr_cont_setl");         // 15  旺季续重派费      有偿旺季续重派费按照网点分发
			tb_list.add("ods_unit_gd_wb_brch_dtl");             // 广东地磅                  广东地磅网点明细报表分发
//			tb_list.add("ods_unit_gd_wb_dist_dtl");             // 广东地磅                  广东地磅分发数据处理
			tb_list.add("ods_unit_gd_wb_ppd_add");              // 广东地磅                  处理广东地磅未发送预付款补发分发数据
			tb_list.add("ods_unit_comp_asur_dect_setl");        // 03  有偿门店保证金扣    有偿派送门店扣保证金按照网点分发
			tb_list.add("ods_unit_comp_asur_rfnd_setl");        // 03  有偿门店保证金退    有偿派送门店退保证金按照网点分发
		}else if("1".equals(sel_fee_cd)){
			tb_list.add("ods_unit_trans_smal_wgt_dtl");         // 01  中转费               中转费0300结算小件重量信息分发
			tb_list.add("ods_unit_trans_pkg_smal_wgt");         // 01  中转费               中转费0500大包重量和小件重量和导出
			tb_list.add("ods_unit_fst_trans_setl_dtl");         // 01  中转费               第一天中转费结算
			tb_list.add("ods_unit_ten_trans_setl_dtl");         // 01  中转费调整        第十天中转费结算
		}else if("3".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_othr_setl");             // 03  有偿有费用        有偿派费其他费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_othr_devi_setl");        // 03  有偿有费用        有偿派费其他费用按照分部分发
			tb_list.add("ods_unit_comp_no_exp_brch_setl");      // 03  有偿无费用        有偿无费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_no_exp_devi_setl");      // 03  有偿无费用        有偿无费用按照分部分发
			tb_list.add("ods_unit_comp_asur_amnt_rfnd_setl");   // 03  有偿保证金退     有偿保证金退按照结算网点或服务部分发
			tb_list.add("ods_unit_comp_asur_dect_setl");        // 03  有偿门店保证金扣    有偿派送门店扣保证金按照网点分发
			tb_list.add("ods_unit_comp_asur_rfnd_setl");        // 03  有偿门店保证金退    有偿派送门店退保证金按照网点分发
		}else if("4".equals(sel_fee_cd)){
			tb_list.add("ods_unit_arv_fee_brh_dtl");            // 04  到付费               到付费网点明细报表（按网点或服务部分发）
//			tb_list.add("ods_unit_arv_fee_dist_dtl");           // 04  到付费               到付费分发数据处理
		}else if("5".equals(sel_fee_cd)){
			tb_list.add("ods_unit_area_subd_brh_dtl");          // 05  区域补贴            区域补贴分发
//			tb_list.add("ods_unit_area_subd_dist_dtl");         // 05  区域补贴            区域补贴分发
		}else if("7".equals(sel_fee_cd)){
			tb_list.add("ods_unit_inter_exp_brch_dtl");         // 07  互带费               互带费按照网点或服务部分发
		}else if("12".equals(sel_fee_cd)){
			tb_list.add("ods_unit_oper_exp_dtl");               // 12  普件操作费         普件操作费按照网点或者服务部分发
		}else if("13".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_bal_setl");              // 13  平衡派费            有偿平衡派费按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_bal_devi_setl");         // 13  平衡派费            有偿平衡派费按照分部分发
		}else if("15".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_qtr_cont_setl");         // 15  旺季续重派费      有偿旺季续重派费按照网点分发
		}else if("16".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_frgn_exp_setl");         // 16  境外派费            境外派费按照网点分发
		}else{
			tb_list.add("ods_unit_comp_viol_pnlt_setl");        // 有偿违规罚款          有偿违规罚款按照网点分发
			tb_list.add("ods_unit_rtn_ship_setl");              // 退回件                     退回件结算按照网点或服务部分发
			tb_list.add("ods_unit_rtn_ship_adj_setl");          // 退回件调整              退回件调整按照网点或服务部分发
			tb_list.add("ods_unit_gd_wb_brch_dtl");             // 广东地磅                  广东地磅网点明细报表分发
//			tb_list.add("ods_unit_gd_wb_dist_dtl");             // 广东地磅                  广东地磅分发数据处理
			tb_list.add("ods_unit_gd_wb_ppd_add");              // 广东地磅                  处理广东地磅未发送预付款补发分发数据
		}		
		paramsMap.put("tb_list", tb_list);
		return setl_ship_dtlMapper.searchFooterData(paramsMap);
	}

	@Override
	@Async
	public List<Setl_ship_dtl> searchData(Map<String, Object> paramsMap, CIPPageInfo page) {
		
		List<Setl_ship_dtl> list = null;
		
		//将票单号字符串转为票单数组
		String ship_id_str = (String) paramsMap.get("ship_id");
		String[] ship_id_array = ship_id_str.split(",");
		paramsMap.put("ship_id_array", ship_id_array);
		
		List<String> tb_list = new ArrayList<String>();
		String sel_fee_cd = (String) paramsMap.get("one_fee_cd");
		if(sel_fee_cd.isEmpty() && "".equals(sel_fee_cd)){
			tb_list.add("ods_unit_trans_smal_wgt_dtl");         // 01  中转费               中转费0300结算小件重量信息分发
			tb_list.add("ods_unit_trans_pkg_smal_wgt");         // 01  中转费               中转费0500大包重量和小件重量和导出
			tb_list.add("ods_unit_fst_trans_setl_dtl");         // 01  中转费               第一天中转费结算
			tb_list.add("ods_unit_ten_trans_setl_dtl");         // 01  中转费调整        第十天中转费结算
			tb_list.add("ods_unit_inter_exp_brch_dtl");         // 07  互带费               互带费按照网点或服务部分发
			tb_list.add("ods_unit_arv_fee_brh_dtl");            // 04  到付费               到付费网点明细报表（按网点或服务部分发）
//			tb_list.add("ods_unit_arv_fee_dist_dtl");           // 04  到付费               到付费分发数据处理
			tb_list.add("ods_unit_comp_othr_setl");             // 03  有偿有费用        有偿派费其他费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_othr_devi_setl");        // 03  有偿有费用        有偿派费其他费用按照分部分发
			tb_list.add("ods_unit_comp_no_exp_brch_setl");      // 03  有偿无费用        有偿无费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_no_exp_devi_setl");      // 03  有偿无费用        有偿无费用按照分部分发
			tb_list.add("ods_unit_comp_asur_amnt_rfnd_setl");   // 03  有偿保证金退     有偿保证金退按照结算网点或服务部分发
			tb_list.add("ods_unit_comp_viol_pnlt_setl");        // 有偿违规罚款          有偿违规罚款按照网点分发
			tb_list.add("ods_unit_area_subd_brh_dtl");          // 05  区域补贴            区域补贴分发
//			tb_list.add("ods_unit_area_subd_dist_dtl");         // 05  区域补贴            区域补贴分发
			tb_list.add("ods_unit_comp_bal_setl");              // 13  平衡派费            有偿平衡派费按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_bal_devi_setl");         // 13  平衡派费            有偿平衡派费按照分部分发
			tb_list.add("ods_unit_oper_exp_dtl");               // 12  普件操作费         普件操作费按照网点或者服务部分发
			tb_list.add("ods_unit_rtn_ship_setl");              // 退回件                     退回件结算按照网点或服务部分发
			tb_list.add("ods_unit_rtn_ship_adj_setl");          // 退回件调整              退回件调整按照网点或服务部分发
			tb_list.add("ods_unit_comp_frgn_exp_setl");         // 16  境外派费            境外派费按照网点分发
			tb_list.add("ods_unit_comp_qtr_cont_setl");         // 15  旺季续重派费      有偿旺季续重派费按照网点分发
			tb_list.add("ods_unit_gd_wb_brch_dtl");             // 广东地磅                  广东地磅网点明细报表分发
//			tb_list.add("ods_unit_gd_wb_dist_dtl");             // 广东地磅                  广东地磅分发数据处理
			tb_list.add("ods_unit_gd_wb_ppd_add");              // 广东地磅                  处理广东地磅未发送预付款补发分发数据
			tb_list.add("ods_unit_comp_asur_dect_setl");        // 03  有偿门店保证金扣    有偿派送门店扣保证金按照网点分发
			tb_list.add("ods_unit_comp_asur_rfnd_setl");        // 03  有偿门店保证金退    有偿派送门店退保证金按照网点分发
		}else if("1".equals(sel_fee_cd)){
			tb_list.add("ods_unit_trans_smal_wgt_dtl");         // 01  中转费               中转费0300结算小件重量信息分发
			tb_list.add("ods_unit_trans_pkg_smal_wgt");         // 01  中转费               中转费0500大包重量和小件重量和导出
			tb_list.add("ods_unit_fst_trans_setl_dtl");         // 01  中转费               第一天中转费结算
			tb_list.add("ods_unit_ten_trans_setl_dtl");         // 01  中转费调整        第十天中转费结算
		}else if("3".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_othr_setl");             // 03  有偿有费用        有偿派费其他费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_othr_devi_setl");        // 03  有偿有费用        有偿派费其他费用按照分部分发
			tb_list.add("ods_unit_comp_no_exp_brch_setl");      // 03  有偿无费用        有偿无费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_no_exp_devi_setl");      // 03  有偿无费用        有偿无费用按照分部分发
			tb_list.add("ods_unit_comp_asur_amnt_rfnd_setl");   // 03  有偿保证金退     有偿保证金退按照结算网点或服务部分发
			tb_list.add("ods_unit_comp_asur_dect_setl");        // 03  有偿门店保证金扣    有偿派送门店扣保证金按照网点分发
			tb_list.add("ods_unit_comp_asur_rfnd_setl");        // 03  有偿门店保证金退    有偿派送门店退保证金按照网点分发
		}else if("4".equals(sel_fee_cd)){
			tb_list.add("ods_unit_arv_fee_brh_dtl");            // 04  到付费               到付费网点明细报表（按网点或服务部分发）
//			tb_list.add("ods_unit_arv_fee_dist_dtl");           // 04  到付费               到付费分发数据处理
		}else if("5".equals(sel_fee_cd)){
			tb_list.add("ods_unit_area_subd_brh_dtl");          // 05  区域补贴            区域补贴分发
//			tb_list.add("ods_unit_area_subd_dist_dtl");         // 05  区域补贴            区域补贴分发
		}else if("7".equals(sel_fee_cd)){
			tb_list.add("ods_unit_inter_exp_brch_dtl");         // 07  互带费               互带费按照网点或服务部分发
		}else if("12".equals(sel_fee_cd)){
			tb_list.add("ods_unit_oper_exp_dtl");               // 12  普件操作费         普件操作费按照网点或者服务部分发
		}else if("13".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_bal_setl");              // 13  平衡派费            有偿平衡派费按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_bal_devi_setl");         // 13  平衡派费            有偿平衡派费按照分部分发
		}else if("15".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_qtr_cont_setl");         // 15  旺季续重派费      有偿旺季续重派费按照网点分发
		}else if("16".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_frgn_exp_setl");         // 16  境外派费            境外派费按照网点分发
		}else{
			tb_list.add("ods_unit_comp_viol_pnlt_setl");        // 有偿违规罚款          有偿违规罚款按照网点分发
			tb_list.add("ods_unit_rtn_ship_setl");              // 退回件                     退回件结算按照网点或服务部分发
			tb_list.add("ods_unit_rtn_ship_adj_setl");          // 退回件调整              退回件调整按照网点或服务部分发
			tb_list.add("ods_unit_gd_wb_brch_dtl");             // 广东地磅                  广东地磅网点明细报表分发
//			tb_list.add("ods_unit_gd_wb_dist_dtl");             // 广东地磅                  广东地磅分发数据处理
			tb_list.add("ods_unit_gd_wb_ppd_add");              // 广东地磅                  处理广东地磅未发送预付款补发分发数据
		}		
		paramsMap.put("tb_list", tb_list);
		
		List<Setl_ship_dtl> footer = setl_ship_dtlMapper.searchFooterData(paramsMap);
		page.setTotal(footer.get(0).getSetlOrgCd());
		paramsMap.put("start_num", page.getStartRecord());
		paramsMap.put("row_num", page.getRows());
		list = setl_ship_dtlMapper.searchData(paramsMap);
		list.add(footer.get(0));
		cache.set((String) paramsMap.get("key"), JSON.toJSONString(list), true);
		return list;
	}

	@Override
	@Async
	public List<Setl_ship_dtl> exportData(Map<String, Object> paramsMap) {
//		String timeStr = String.valueOf(System.currentTimeMillis());
		String timeStr = String.valueOf(paramsMap.get("timeStr"));
		String localPathStr = CIPRuntimeConfigure.cip_temp_file_path;
		String remotrPathStr = "/tmp";
		
		String ship_id_str = (String) paramsMap.get("ship_id");
		String[] ship_id_array = ship_id_str.split(",");
		paramsMap.put("ship_id_array", ship_id_array);
		
		List<String> tb_list = new ArrayList<String>();
		String sel_fee_cd = (String) paramsMap.get("one_fee_cd");
		if(sel_fee_cd.isEmpty() && "".equals(sel_fee_cd)){
			tb_list.add("ods_unit_trans_smal_wgt_dtl");         // 01  中转费               中转费0300结算小件重量信息分发
			tb_list.add("ods_unit_trans_pkg_smal_wgt");         // 01  中转费               中转费0500大包重量和小件重量和导出
			tb_list.add("ods_unit_fst_trans_setl_dtl");         // 01  中转费               第一天中转费结算
			tb_list.add("ods_unit_ten_trans_setl_dtl");         // 01  中转费调整        第十天中转费结算
			tb_list.add("ods_unit_inter_exp_brch_dtl");         // 07  互带费               互带费按照网点或服务部分发
			tb_list.add("ods_unit_arv_fee_brh_dtl");            // 04  到付费               到付费网点明细报表（按网点或服务部分发）
//			tb_list.add("ods_unit_arv_fee_dist_dtl");           // 04  到付费               到付费分发数据处理
			tb_list.add("ods_unit_comp_othr_setl");             // 03  有偿有费用        有偿派费其他费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_othr_devi_setl");        // 03  有偿有费用        有偿派费其他费用按照分部分发
			tb_list.add("ods_unit_comp_no_exp_brch_setl");      // 03  有偿无费用        有偿无费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_no_exp_devi_setl");      // 03  有偿无费用        有偿无费用按照分部分发
			tb_list.add("ods_unit_comp_asur_amnt_rfnd_setl");   // 03  有偿保证金退     有偿保证金退按照结算网点或服务部分发
			tb_list.add("ods_unit_comp_viol_pnlt_setl");        // 有偿违规罚款          有偿违规罚款按照网点分发
			tb_list.add("ods_unit_area_subd_brh_dtl");          // 05  区域补贴            区域补贴分发
//			tb_list.add("ods_unit_area_subd_dist_dtl");         // 05  区域补贴            区域补贴分发
			tb_list.add("ods_unit_comp_bal_setl");              // 13  平衡派费            有偿平衡派费按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_bal_devi_setl");         // 13  平衡派费            有偿平衡派费按照分部分发
			tb_list.add("ods_unit_oper_exp_dtl");               // 12  普件操作费         普件操作费按照网点或者服务部分发
			tb_list.add("ods_unit_rtn_ship_setl");              // 退回件                     退回件结算按照网点或服务部分发
			tb_list.add("ods_unit_rtn_ship_adj_setl");          // 退回件调整              退回件调整按照网点或服务部分发
			tb_list.add("ods_unit_comp_frgn_exp_setl");         // 16  境外派费            境外派费按照网点分发
			tb_list.add("ods_unit_comp_qtr_cont_setl");         // 15  旺季续重派费      有偿旺季续重派费按照网点分发
			tb_list.add("ods_unit_gd_wb_brch_dtl");             // 广东地磅                  广东地磅网点明细报表分发
//			tb_list.add("ods_unit_gd_wb_dist_dtl");             // 广东地磅                  广东地磅分发数据处理
			tb_list.add("ods_unit_gd_wb_ppd_add");              // 广东地磅                  处理广东地磅未发送预付款补发分发数据
			tb_list.add("ods_unit_comp_asur_dect_setl");        // 03  有偿门店保证金扣    有偿派送门店扣保证金按照网点分发
			tb_list.add("ods_unit_comp_asur_rfnd_setl");        // 03  有偿门店保证金退    有偿派送门店退保证金按照网点分发
		}else if("1".equals(sel_fee_cd)){
			tb_list.add("ods_unit_trans_smal_wgt_dtl");         // 01  中转费               中转费0300结算小件重量信息分发
			tb_list.add("ods_unit_trans_pkg_smal_wgt");         // 01  中转费               中转费0500大包重量和小件重量和导出
			tb_list.add("ods_unit_fst_trans_setl_dtl");         // 01  中转费               第一天中转费结算
			tb_list.add("ods_unit_ten_trans_setl_dtl");         // 01  中转费调整        第十天中转费结算
		}else if("3".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_othr_setl");             // 03  有偿有费用        有偿派费其他费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_othr_devi_setl");        // 03  有偿有费用        有偿派费其他费用按照分部分发
			tb_list.add("ods_unit_comp_no_exp_brch_setl");      // 03  有偿无费用        有偿无费用按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_no_exp_devi_setl");      // 03  有偿无费用        有偿无费用按照分部分发
			tb_list.add("ods_unit_comp_asur_amnt_rfnd_setl");   // 03  有偿保证金退     有偿保证金退按照结算网点或服务部分发
			tb_list.add("ods_unit_comp_asur_dect_setl");        // 03  有偿门店保证金扣    有偿派送门店扣保证金按照网点分发
			tb_list.add("ods_unit_comp_asur_rfnd_setl");        // 03  有偿门店保证金退    有偿派送门店退保证金按照网点分发
		}else if("4".equals(sel_fee_cd)){
			tb_list.add("ods_unit_arv_fee_brh_dtl");            // 04  到付费               到付费网点明细报表（按网点或服务部分发）
//			tb_list.add("ods_unit_arv_fee_dist_dtl");           // 04  到付费               到付费分发数据处理
		}else if("5".equals(sel_fee_cd)){
			tb_list.add("ods_unit_area_subd_brh_dtl");          // 05  区域补贴            区域补贴分发
//			tb_list.add("ods_unit_area_subd_dist_dtl");         // 05  区域补贴            区域补贴分发
		}else if("7".equals(sel_fee_cd)){
			tb_list.add("ods_unit_inter_exp_brch_dtl");         // 07  互带费               互带费按照网点或服务部分发
		}else if("12".equals(sel_fee_cd)){
			tb_list.add("ods_unit_oper_exp_dtl");               // 12  普件操作费         普件操作费按照网点或者服务部分发
		}else if("13".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_bal_setl");              // 13  平衡派费            有偿平衡派费按照结算网点或服务部分发
//			tb_list.add("ods_unit_comp_bal_devi_setl");         // 13  平衡派费            有偿平衡派费按照分部分发
		}else if("15".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_qtr_cont_setl");         // 15  旺季续重派费      有偿旺季续重派费按照网点分发
		}else if("16".equals(sel_fee_cd)){
			tb_list.add("ods_unit_comp_frgn_exp_setl");         // 16  境外派费            境外派费按照网点分发
		}else{
			tb_list.add("ods_unit_comp_viol_pnlt_setl");        // 有偿违规罚款          有偿违规罚款按照网点分发
			tb_list.add("ods_unit_rtn_ship_setl");              // 退回件                     退回件结算按照网点或服务部分发
			tb_list.add("ods_unit_rtn_ship_adj_setl");          // 退回件调整              退回件调整按照网点或服务部分发
			tb_list.add("ods_unit_gd_wb_brch_dtl");             // 广东地磅                  广东地磅网点明细报表分发
//			tb_list.add("ods_unit_gd_wb_dist_dtl");             // 广东地磅                  广东地磅分发数据处理
			tb_list.add("ods_unit_gd_wb_ppd_add");              // 广东地磅                  处理广东地磅未发送预付款补发分发数据
		}		
		paramsMap.put("tb_list", tb_list);
		
		try{
			String ipAddr =  CIPConfigureManager.getRootConf().getProperty("linux.ipAddr").trim();
			int port =Integer.parseInt(CIPConfigureManager.getRootConf().getProperty("linux.port").trim());
			String userName =  CIPConfigureManager.getRootConf().getProperty("linux.userName").trim();
			String password =  CIPConfigureManager.getRootConf().getProperty("linux.password").trim();
			ShellUtil shellUtil = new ShellUtil(ipAddr, port, userName, password, "utf-8");
			String remoteFilePath = remotrPathStr+"/"+timeStr;
			String remoteFileName = remoteFilePath+"/"+timeStr+".zip";
			int mkdirStatus = shellUtil.getRemoteExit("mkdir -p "+remoteFilePath+";chmod -R 777 "+remoteFilePath);
			if (mkdirStatus == 0) {
				int pageNum = 1;
//				int rowNum = 1000000;
				int rowNum = Integer.parseInt(CIPConfigureManager.getRootConf().getProperty("export.maxRowsSize").trim());
				CIPPageInfo page = new CIPPageInfo(pageNum, rowNum);
				List<Setl_ship_dtl> footer = setl_ship_dtlMapper.searchFooterData(paramsMap);
				int totalNum = footer.get(0).getSetlOrgCd();
				page.setTotal(totalNum);
				if(totalNum <= rowNum){
					paramsMap.put("exp_file",remotrPathStr+"/"+timeStr+"/setl_ship_dtl.csv");
					paramsMap.put("start_num", page.getStartRecord());
					paramsMap.put("row_num", page.getRows());
					setl_ship_dtlMapper.exportData(paramsMap);
				}else{
					do{
						page.setPage(pageNum);
						paramsMap.put("exp_file",remotrPathStr+"/"+timeStr+"/setl_ship_dtl-"+pageNum+".csv");
						paramsMap.put("start_num", page.getStartRecord());
						paramsMap.put("row_num", page.getRows());
						setl_ship_dtlMapper.exportData(paramsMap);
						totalNum = totalNum - rowNum;
						pageNum++;
					}while(totalNum>0);
				}
				int zipStatus = shellUtil.getRemoteExit("cd "+remoteFilePath+";zip -mr "+timeStr+".zip"+" *.csv");
				if (zipStatus == 0) {
					int scpStatus = shellUtil.getRemotFile(remoteFileName,localPathStr);
					if (scpStatus == 0) {
						logger.info("exportFile----->远程运行导出命令成功,远程拷贝成功,导出成功。");
						cache.set((String) paramsMap.get("key"), "1", true);
					} else {
						logger.info("exportFile----->远程拷贝文件失败,可能的原因：连接远程服务器失败，文件丢失等,导出失败。");
					}
				} else {
					logger.info("exportFile----->远程文件zip压缩失败。");
				}
				shellUtil.getRemoteExit("rm -rf "+remoteFilePath);
			}
		} catch (IOException e) {
			logger.error("exportFile----->io异常", e);
		} catch (Exception e) {
			logger.error("exportFile----->异常", e);
		}
		return null;
	}
}