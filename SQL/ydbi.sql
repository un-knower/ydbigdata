/*
Navicat MySQL Data Transfer

Source Server         : ydbi
Source Server Version : 50618
Source Host           : 10.19.106.115:3306
Source Database       : ydbi

Target Server Type    : MYSQL
Target Server Version : 50618
File Encoding         : 65001

Date: 2017-09-27 10:46:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bas_o_bigdata_odr
-- ----------------------------
DROP TABLE IF EXISTS `bas_o_bigdata_odr`;
CREATE TABLE `bas_o_bigdata_odr` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计日期',
  `dbct_cd` int(11) NOT NULL DEFAULT '0' COMMENT '分拨编码',
  `odr_cnt` bigint(20) DEFAULT NULL COMMENT '订单量',
  PRIMARY KEY (`stats_dt`,`dbct_cd`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化首页订单表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_arrive_op_tl
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_arrive_op_tl`;
CREATE TABLE `bas_s_arrive_op_tl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `arrive_op_date` datetime DEFAULT NULL COMMENT '到达操作时间',
  `org_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `arrive_op_tl` bigint(20) DEFAULT NULL COMMENT '到达操作时效',
  `days_from` bigint(20) DEFAULT NULL COMMENT '日环比',
  `Week_compared_to_same` bigint(20) DEFAULT NULL COMMENT '周同比',
  `week_avg_tl` bigint(20) DEFAULT NULL COMMENT '周平均时效',
  `month_avg_tl` bigint(20) DEFAULT NULL COMMENT '月平均时效',
  `last_day_tl` bigint(20) DEFAULT NULL COMMENT '上一日时效',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  `rank_lift` bigint(20) DEFAULT NULL COMMENT '排名升降',
  `sameTime_val` bigint(20) DEFAULT NULL COMMENT '同期值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='到达操作时效表';

-- ----------------------------
-- Table structure for bas_s_bigdata_base
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_base`;
CREATE TABLE `bas_s_bigdata_base` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计日期',
  `dbct_cd` int(11) NOT NULL DEFAULT '0' COMMENT '分拨编码',
  `pop_cnt` int(11) DEFAULT NULL COMMENT '人数',
  `input_dep` int(11) DEFAULT NULL COMMENT '录入部门',
  `input_emp` int(11) DEFAULT NULL COMMENT '录入人员',
  `input_tm` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  PRIMARY KEY (`stats_dt`,`dbct_cd`,`input_tm`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化分拨中心人数信息表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_busi
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_busi`;
CREATE TABLE `bas_s_bigdata_busi` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计日期',
  `dbct_cd` int(11) NOT NULL DEFAULT '0' COMMENT '分拨编码',
  `pick_cnt` bigint(20) DEFAULT NULL COMMENT '揽件量',
  `delv_cnt` bigint(20) DEFAULT NULL COMMENT '派件量',
  `cfm_rcv_cnt` bigint(20) DEFAULT NULL COMMENT '单日签收量',
  `cfm_rcv_rt` decimal(5,4) DEFAULT NULL COMMENT '单日派件成功率',
  `pick_cnt3` bigint(20) DEFAULT NULL COMMENT '3日揽件量',
  `cfm_rcv_cnt3` bigint(20) DEFAULT NULL COMMENT '3日签收量',
  `cfm_rcv_rt3` decimal(5,4) DEFAULT NULL COMMENT '3日签收率',
  `pick_cnt7` bigint(20) DEFAULT NULL COMMENT '7日揽件量',
  `cfm_rcv_cnt7` bigint(20) DEFAULT NULL COMMENT '7日未签收量',
  `cfm_rcv_rt7` decimal(5,4) DEFAULT NULL COMMENT '7日未签收率',
  `dbct_cnt1_low` bigint(20) DEFAULT NULL COMMENT '单日派件成功率<80%的网点数量',
  `dbct_cnt1_mid` bigint(20) DEFAULT NULL COMMENT '80%<=单日派件成功率<95%的网点数量',
  `dbct_cnt1_up` bigint(20) DEFAULT NULL COMMENT '单日派件成功率>=95%的网点数量',
  `dbct_cnt3_low` bigint(20) DEFAULT NULL COMMENT '3日签收率<80%的网点数量',
  `dbct_cnt3_mid` bigint(20) DEFAULT NULL COMMENT '80%<=3日签收率<95%的网点数量',
  `dbct_cnt3_up` bigint(20) DEFAULT NULL COMMENT '3日签收率>=95%的网点数量',
  `dbct_cnt7_up` bigint(20) DEFAULT NULL COMMENT '7日未签收率>20%的网点数量',
  `dbct_cnt7_mid` bigint(20) DEFAULT NULL COMMENT '5%<=7日未签收率<20%的网点数量',
  `dbct_cnt7_low` bigint(20) DEFAULT NULL COMMENT '7日未签收率<=5%的网点数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bas_s_bigdata_busi_bak
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_busi_bak`;
CREATE TABLE `bas_s_bigdata_busi_bak` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计日期',
  `dbct_cd` int(11) NOT NULL DEFAULT '0' COMMENT '分拨编码',
  `pick_cnt` bigint(20) DEFAULT NULL COMMENT '揽件量',
  `delv_cnt` bigint(20) DEFAULT NULL COMMENT '派件量',
  `cfm_rcv_cnt` bigint(20) DEFAULT NULL COMMENT '单日签收量',
  `cfm_rcv_rt` decimal(5,4) DEFAULT NULL COMMENT '单日派件成功率',
  `pick_cnt3` bigint(20) DEFAULT NULL COMMENT '3日揽件量',
  `cfm_rcv_cnt3` bigint(20) DEFAULT NULL COMMENT '3日签收量',
  `cfm_rcv_rt3` decimal(5,4) DEFAULT NULL COMMENT '3日签收率',
  `pick_cnt7` bigint(20) DEFAULT NULL COMMENT '7日揽件量',
  `cfm_rcv_cnt7` bigint(20) DEFAULT NULL COMMENT '7日未签收量',
  `cfm_rcv_rt7` decimal(5,4) DEFAULT NULL COMMENT '7日未签收率',
  `dbct_cnt1_low` bigint(20) DEFAULT NULL COMMENT '单日派件成功率<80%的网点数量',
  `dbct_cnt1_mid` bigint(20) DEFAULT NULL COMMENT '80%<=单日派件成功率<95%的网点数量',
  `dbct_cnt1_up` bigint(20) DEFAULT NULL COMMENT '单日派件成功率>=95%的网点数量',
  `dbct_cnt3_low` bigint(20) DEFAULT NULL COMMENT '3日签收率<80%的网点数量',
  `dbct_cnt3_mid` bigint(20) DEFAULT NULL COMMENT '80%<=3日签收率<95%的网点数量',
  `dbct_cnt3_up` bigint(20) DEFAULT NULL COMMENT '3日签收率>=95%的网点数量',
  `dbct_cnt7_up` bigint(20) DEFAULT NULL COMMENT '7日未签收率>20%的网点数量',
  `dbct_cnt7_mid` bigint(20) DEFAULT NULL COMMENT '5%<=7日未签收率<20%的网点数量',
  `dbct_cnt7_low` bigint(20) DEFAULT NULL COMMENT '7日未签收率<=5%的网点数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bas_s_bigdata_busi_bk1
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_busi_bk1`;
CREATE TABLE `bas_s_bigdata_busi_bk1` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计日期',
  `dbct_cd` int(11) NOT NULL DEFAULT '0' COMMENT '分拨编码',
  `pick_cnt` bigint(20) DEFAULT NULL COMMENT '揽件量',
  `delv_cnt` bigint(20) DEFAULT NULL COMMENT '派件量',
  `cfm_rcv_cnt` bigint(20) DEFAULT NULL COMMENT '单日签收量',
  `cfm_rcv_rt` decimal(5,4) DEFAULT NULL COMMENT '单日派件成功率',
  `pick_cnt3` bigint(20) DEFAULT NULL COMMENT '3日揽件量',
  `cfm_rcv_cnt3` bigint(20) DEFAULT NULL COMMENT '3日签收量',
  `cfm_rcv_rt3` decimal(5,4) DEFAULT NULL COMMENT '3日签收率',
  `pick_cnt7` bigint(20) DEFAULT NULL COMMENT '7日揽件量',
  `cfm_rcv_cnt7` bigint(20) DEFAULT NULL COMMENT '7日未签收量',
  `cfm_rcv_rt7` decimal(5,4) DEFAULT NULL COMMENT '7日未签收率',
  `dbct_cnt3_low` bigint(20) DEFAULT NULL COMMENT '3日签收率<80%的网点数量',
  `dbct_cnt3_mid` bigint(20) DEFAULT NULL COMMENT '80%<=3日签收率<95%的网点数量',
  `dbct_cnt3_up` bigint(20) DEFAULT NULL COMMENT '3日签收率>=95%的网点数量',
  `dbct_cnt7_up` bigint(20) DEFAULT NULL COMMENT '7日未签收率>20%的网点数量',
  `dbct_cnt7_mid` bigint(20) DEFAULT NULL COMMENT '5%<=7日未签收率<20%的网点数量',
  `dbct_cnt7_low` bigint(20) DEFAULT NULL COMMENT '7日未签收率<=5%的网点数量',
  PRIMARY KEY (`stats_dt`,`dbct_cd`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化业务量表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_all
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_all`;
CREATE TABLE `bas_s_bigdata_effect_all` (
  `pick_delv_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间（揽件签收时间）',
  `pick_delv_dbct` int(11) NOT NULL DEFAULT '0' COMMENT '目的分拨',
  `pick_delv_tm` bigint(20) DEFAULT NULL COMMENT '揽件至签收时总和',
  `pick_delv_cnt` bigint(20) DEFAULT NULL COMMENT '揽件签收件量',
  PRIMARY KEY (`pick_delv_dt`,`pick_delv_dbct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化票件时效全程时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_city
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_city`;
CREATE TABLE `bas_s_bigdata_effect_city` (
  `stats_dt` date NOT NULL COMMENT '统计时间',
  `dbct_site` int(11) NOT NULL COMMENT '分拨编码',
  `start_city_id` int(11) NOT NULL COMMENT '发出城市',
  `end_city_id` int(11) NOT NULL COMMENT '到达城市',
  `start_prov_id` int(11) NOT NULL COMMENT '发出省份',
  `end_prov_id` int(11) NOT NULL COMMENT '到达省份',
  `ship_cnt` int(11) NOT NULL COMMENT '票件数量',
  `all_len` decimal(18,2) NOT NULL COMMENT '全程总时长',
  `trans_len` decimal(18,2) NOT NULL COMMENT '中转总时长',
  `all_len_city` decimal(6,2) NOT NULL COMMENT '城市全程时效',
  `trans_len_city` decimal(6,2) NOT NULL COMMENT '城市中转时效',
  `all_len_rn` int(11) NOT NULL COMMENT '城市全程时效排名',
  `trans_len_rn` int(11) NOT NULL COMMENT '城市中转时效排名',
  KEY `stats_dt_index` (`stats_dt`) USING HASH,
  KEY `dbct_site_index` (`dbct_site`) USING HASH,
  KEY `start_city_id_index` (`start_city_id`) USING BTREE,
  KEY `end_city_id_index` (`end_city_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件临时表2 --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件临时表2 --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_delv
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_delv`;
CREATE TABLE `bas_s_bigdata_effect_delv` (
  `snd_delv_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间（分发签收时间）',
  `snd_delv_dbct` int(11) NOT NULL DEFAULT '0' COMMENT '目的分拨',
  `snd_delv_tm` bigint(20) DEFAULT NULL COMMENT '分发至签收时总和',
  `snd_delv_cnt` bigint(20) DEFAULT NULL COMMENT '分发签收件量',
  PRIMARY KEY (`snd_delv_dt`,`snd_delv_dbct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化票件时效签收时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_end
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_end`;
CREATE TABLE `bas_s_bigdata_effect_end` (
  `stats_dt` date NOT NULL COMMENT '统计时间',
  `dbct_site` int(11) NOT NULL COMMENT '分拨编码',
  `ship_cnt` int(11) NOT NULL COMMENT '票件量：量',
  `delv_len` bigint(20) NOT NULL COMMENT '派件总时长',
  `delv_effect` int(11) NOT NULL COMMENT '派件时效',
  KEY `dbct_site` (`dbct_site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件时效表2 --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件时效表2 --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:'';';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_end_bk
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_end_bk`;
CREATE TABLE `bas_s_bigdata_effect_end_bk` (
  `uld_lod_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间（卸车装车时间）',
  `uld_lod_dbct` int(11) NOT NULL DEFAULT '0' COMMENT '目的分拨',
  `uld_lod_tm` bigint(20) DEFAULT NULL COMMENT '目的分拨卸车装车总时长',
  `uld_lod_cnt` bigint(20) DEFAULT NULL COMMENT '目的分拨卸车装车件量',
  PRIMARY KEY (`uld_lod_dt`,`uld_lod_dbct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化票件时效到达操作时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_end_bk1
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_end_bk1`;
CREATE TABLE `bas_s_bigdata_effect_end_bk1` (
  `stats_dt` date NOT NULL COMMENT '统计日期',
  `end_site` int(11) NOT NULL COMMENT '目的分拨',
  `ship_cnt` bigint(20) NOT NULL COMMENT '件量',
  `all_len` decimal(10,2) NOT NULL COMMENT '全程时长（小时）',
  `delv_len` decimal(10,2) NOT NULL COMMENT '派件时长（小时）',
  KEY `AK_Key_1` (`end_site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件时效表B --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_give
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_give`;
CREATE TABLE `bas_s_bigdata_effect_give` (
  `pick_uld_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间（卸车揽件时间）',
  `pick_uld_dbct` int(11) NOT NULL DEFAULT '0' COMMENT '始发分拨',
  `pick_uld_tm` bigint(20) DEFAULT NULL COMMENT '揽件至卸车时总和',
  `pick_uld_cnt` bigint(20) DEFAULT NULL COMMENT '卸车揽件量',
  PRIMARY KEY (`pick_uld_dt`,`pick_uld_dbct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化票件时效交件时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_pick
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_pick`;
CREATE TABLE `bas_s_bigdata_effect_pick` (
  `odr_pick_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '下单揽件时间',
  `odr_pick_dbct` int(11) NOT NULL DEFAULT '0' COMMENT '下单揽件分拨',
  `odr_pick_tm` bigint(20) DEFAULT NULL COMMENT '下单至揽件时总和（分钟）',
  `odr_pick_cnt` bigint(20) DEFAULT NULL COMMENT '订单揽件量',
  PRIMARY KEY (`odr_pick_dt`,`odr_pick_dbct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化票件时效揽收时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_prov
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_prov`;
CREATE TABLE `bas_s_bigdata_effect_prov` (
  `stats_dt` date NOT NULL COMMENT '统计时间',
  `dbct_site` int(11) NOT NULL COMMENT '分拨编码',
  `start_prov_id` int(11) NOT NULL COMMENT '发出省份',
  `end_prov_id` int(11) NOT NULL COMMENT '到达省份',
  `ship_cnt` int(11) NOT NULL COMMENT '票件数量',
  `all_len` decimal(18,2) NOT NULL COMMENT '全程总时长',
  `trans_len` decimal(18,2) NOT NULL COMMENT '中转总时长',
  `all_len_prov` decimal(6,2) NOT NULL COMMENT '省份全程时效',
  `trans_len_prov` decimal(6,2) NOT NULL COMMENT '省份中转时效',
  `all_len_rn` int(11) NOT NULL COMMENT '省份全程时效排名',
  `trans_len_rn` int(11) NOT NULL COMMENT '省份中转时效排名'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件临时表3 --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件临时表3 --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:'';';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_rn
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_rn`;
CREATE TABLE `bas_s_bigdata_effect_rn` (
  `stats_dt` date NOT NULL COMMENT '统计时间',
  `dbct_site` int(11) NOT NULL COMMENT '始发分拨',
  `start_city_id` int(11) NOT NULL COMMENT '发出城市',
  `end_city_id` int(11) NOT NULL COMMENT '到达城市',
  `start_prov_id` int(11) NOT NULL COMMENT '发出省份',
  `end_prov_id` int(11) NOT NULL COMMENT '到达省份',
  `ship_cnt` int(11) NOT NULL COMMENT '票件数量',
  `all_len` bigint(20) NOT NULL COMMENT '全程总时长',
  `trans_len` bigint(20) NOT NULL COMMENT '中转总时长',
  `all_len_level` int(11) NOT NULL COMMENT '全程时效',
  `trans_len_level` int(11) NOT NULL COMMENT '中转时效',
  `rn` int(11) NOT NULL COMMENT '时效排名（最慢前10）',
  `effect_type` int(11) NOT NULL COMMENT '时效类型（1：城市全程时效排名 2：城市中转时效排名 3：省份全程时效排名 4：省份中转时效排名）',
  KEY `dbct_site` (`dbct_site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件城市省份时效排名 --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件城市省份时效排名 --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:'';';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_snd
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_snd`;
CREATE TABLE `bas_s_bigdata_effect_snd` (
  `lod_snd_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间（装车分发时间）',
  `lod_snd_dbct` int(11) NOT NULL DEFAULT '0' COMMENT '始发分拨',
  `lod_snd_tm` bigint(20) DEFAULT NULL COMMENT '装车至分发时总和',
  `lod_snd_cnt` bigint(20) DEFAULT NULL COMMENT '装车分发件量',
  PRIMARY KEY (`lod_snd_dt`,`lod_snd_dbct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化票件时效分发时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_start
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_start`;
CREATE TABLE `bas_s_bigdata_effect_start` (
  `stats_dt` date NOT NULL COMMENT '统计时间',
  `dbct_site` int(11) NOT NULL COMMENT '分拨编码',
  `ship_cnt` int(11) NOT NULL COMMENT '票件量：量',
  `pick_len` bigint(20) NOT NULL COMMENT '交件总时长',
  `all_len` bigint(20) NOT NULL COMMENT '全程总时长',
  `trans_len` bigint(20) NOT NULL COMMENT '中转总时长',
  `pick_effect` int(11) NOT NULL COMMENT '交件时效',
  `all_effect` int(11) NOT NULL COMMENT '全程时效',
  `trans_effect` int(11) NOT NULL COMMENT '中转时效',
  `all_len_low_12` int(11) NOT NULL COMMENT ' 票件量：全程时长<12小时',
  `all_len_low_24` int(11) NOT NULL COMMENT ' 票件量：全程时长>=12小时，<24小时',
  `all_len_low_36` int(11) NOT NULL COMMENT ' 票件量：全程时长>=24小时，<36小时',
  `all_len_low_48` int(11) NOT NULL COMMENT ' 票件量：全程时长>=36小时，<48小时',
  `all_len_low_60` int(11) NOT NULL COMMENT ' 票件量：全程时长>=48小时，<60小时',
  `all_len_low_72` int(11) NOT NULL COMMENT ' 票件量：全程时长>=60小时，<72小时',
  `all_len_up_72` int(11) NOT NULL COMMENT ' 票件量：全程时长>=72小时，',
  `trans_len_low_12` int(11) NOT NULL COMMENT ' 票件量：中转时长<12小时',
  `trans_len_low_24` int(11) NOT NULL COMMENT ' 票件量：中转时长>=12小时，<24小时',
  `trans_len_low_36` int(11) NOT NULL COMMENT ' 票件量：中转时长>=24小时，<36小时',
  `trans_len_low_48` int(11) NOT NULL COMMENT ' 票件量：中转时长>=36小时，<48小时',
  `trans_len_low_60` int(11) NOT NULL COMMENT ' 票件量：中转时长>=48小时，<60小时',
  `trans_len_low_72` int(11) NOT NULL COMMENT ' 票件量：中转时长>=60小时，<72小时',
  `trans_len_up_72` int(11) NOT NULL COMMENT ' 票件量：中转时长>=72小时',
  KEY `dbct_site` (`dbct_site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件时效表1 --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件时效表1 --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:'';';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_start_bk
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_start_bk`;
CREATE TABLE `bas_s_bigdata_effect_start_bk` (
  `uld_lod_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间（卸车装车时间）',
  `uld_lod_dbct` int(11) NOT NULL DEFAULT '0' COMMENT '始发分拨',
  `uld_lod_tm` bigint(20) DEFAULT NULL COMMENT '下单至揽件时总和',
  `uld_lod_cnt` bigint(20) DEFAULT NULL COMMENT '订单揽件量',
  PRIMARY KEY (`uld_lod_dt`,`uld_lod_dbct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化票件时效始发操作时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_start_bk1
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_start_bk1`;
CREATE TABLE `bas_s_bigdata_effect_start_bk1` (
  `stats_dt` date NOT NULL COMMENT '统计日期',
  `start_site` int(11) NOT NULL COMMENT '始发分拨',
  `ship_cnt` bigint(20) NOT NULL COMMENT '件量',
  `pick_len` decimal(10,2) NOT NULL COMMENT '揽件时长（小时）',
  `trans_len` decimal(10,2) NOT NULL COMMENT '中转时长（小时）',
  KEY `start_site` (`start_site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件时效表A --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件时效表A --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:'';';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_tmp1
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_tmp1`;
CREATE TABLE `bas_s_bigdata_effect_tmp1` (
  `end_date` date NOT NULL COMMENT '统计日期',
  `ticket_no` bigint(20) NOT NULL COMMENT '票件代码',
  `start_prov_id` int(11) NOT NULL COMMENT '发出省份',
  `start_city_id` int(11) NOT NULL COMMENT '发出城市',
  `end_prov_id` int(11) NOT NULL COMMENT '到达省份',
  `end_city_id` int(11) NOT NULL COMMENT '到达城市',
  `start_site` int(11) NOT NULL COMMENT '始发分拨',
  `end_site` int(11) NOT NULL COMMENT '目的分拨',
  `all_len` decimal(10,2) NOT NULL COMMENT '全程时长（小时）',
  `pick_len` decimal(10,2) NOT NULL COMMENT '交件时长（小时）',
  `trans_len` decimal(10,2) NOT NULL COMMENT '中转时长（小时）',
  `delv_len` decimal(10,2) NOT NULL COMMENT '派件时长（小时）',
  `all_len_low_12` int(11) NOT NULL COMMENT '票件是否全程时长<12小时',
  `all_len_low_24` int(11) NOT NULL COMMENT '票件是否全程时长>=12小时且<24小时',
  `all_len_low_36` int(11) NOT NULL COMMENT '票件是否全程时长>=24小时且<36小时',
  `all_len_low_48` int(11) NOT NULL COMMENT '票件是否全程时长>=36小时且<48小时',
  `all_len_low_60` int(11) NOT NULL COMMENT '票件是否全程时长>=48小时且<60小时',
  `all_len_low_72` int(11) NOT NULL COMMENT '票件是否全程时长>=60小时且72小时',
  `all_len_up_72` int(11) NOT NULL COMMENT '票件是否全程时长>=72小时',
  `trans_len_low_12` int(11) NOT NULL COMMENT '票件是否中转时长<12小时',
  `trans_len_low_24` int(11) NOT NULL COMMENT '票件是否中转时长>=12小时且<24小时',
  `trans_len_low_36` int(11) NOT NULL COMMENT '票件是否中转时长>=24小时且<36小时',
  `trans_len_low_48` int(11) NOT NULL COMMENT '票件是否中转时长>=36小时且<48小时',
  `trans_len_low_60` int(11) NOT NULL COMMENT '票件是否中转时长>=48小时且<60小时',
  `trans_len_low_72` int(11) NOT NULL COMMENT '票件是否中转时长>=60小时且72小时',
  `trans_len_up_72` int(11) NOT NULL COMMENT '票件是否中转时长>=72小时',
  KEY `ticket_no` (`ticket_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件临时表1 --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件临时表1 --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:'';';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_tmp2
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_tmp2`;
CREATE TABLE `bas_s_bigdata_effect_tmp2` (
  `stats_dt` date NOT NULL COMMENT '统计时间',
  `dbct_site` int(11) NOT NULL COMMENT '分拨编码',
  `start_city_id` int(11) NOT NULL COMMENT '发出城市',
  `end_city_id` int(11) NOT NULL COMMENT '到达城市',
  `start_prov_id` int(11) NOT NULL COMMENT '发出省份',
  `end_prov_id` int(11) NOT NULL COMMENT '到达省份',
  `ship_cnt` int(11) NOT NULL COMMENT '票件量：量',
  `all_len` bigint(20) NOT NULL COMMENT '全程总时长',
  `trans_len` bigint(20) NOT NULL COMMENT '中转总时长',
  `all_len_city` int(11) NOT NULL COMMENT '城市全程时效',
  `trans_len_city` int(11) NOT NULL COMMENT '城市中转时效',
  `all_len_rn` int(11) NOT NULL COMMENT '城市全程时效排名',
  `trans_len_rn` int(11) NOT NULL COMMENT '城市中转时效排名',
  KEY `dbct_site` (`dbct_site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件临时表2 --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件临时表2 --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:'';';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_tmp3
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_tmp3`;
CREATE TABLE `bas_s_bigdata_effect_tmp3` (
  `stats_dt` date NOT NULL COMMENT '统计时间',
  `dbct_site` int(11) NOT NULL COMMENT '分拨编码',
  `start_prov_id` int(11) NOT NULL COMMENT '发出省份',
  `end_prov_id` int(11) NOT NULL COMMENT '到达省份',
  `ship_cnt` int(11) NOT NULL COMMENT '票件量：量',
  `all_len` bigint(20) NOT NULL COMMENT '全程总时长',
  `trans_len` bigint(20) NOT NULL COMMENT '中转总时长',
  `all_len_prov` int(11) NOT NULL COMMENT '省份全程时效',
  `trans_len_prov` int(11) NOT NULL COMMENT '省份中转时效',
  `all_len_rn` int(11) NOT NULL COMMENT '省份全程时效排名',
  `trans_len_rn` int(11) NOT NULL COMMENT '省份中转时效排名',
  KEY `dbct_site` (`dbct_site`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='%@NAME:大数据移动化票件临时表3 --%@CREATOR: 张冰--%@CREATED_TIME:2017-07-24--%@COMMENT:大数据移动化票件临时表3 --%@LEVEL:ODS--%@MASTER_FIELD:运单域--%@SUB_FIELD:'';';

-- ----------------------------
-- Table structure for bas_s_bigdata_effect_trans
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_effect_trans`;
CREATE TABLE `bas_s_bigdata_effect_trans` (
  `trans_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间（中转时间）',
  `trans_dbct` int(11) NOT NULL DEFAULT '0' COMMENT '目的分拨',
  `trans_tm` bigint(20) DEFAULT NULL COMMENT '中转总时长',
  `trans_cnt` bigint(20) DEFAULT NULL COMMENT '订单揽件量',
  PRIMARY KEY (`trans_dt`,`trans_dbct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化票件时效中转运输时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_s_bigdata_qut_ctl
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_bigdata_qut_ctl`;
CREATE TABLE `bas_s_bigdata_qut_ctl` (
  `stats_dt` date DEFAULT NULL COMMENT '统计日期',
  `dbct_cd` int(11) DEFAULT NULL COMMENT '分拨编码',
  `dly_tot_cnt` int(11) DEFAULT NULL COMMENT '总发出票数',
  `dly_snd_cnt` int(11) DEFAULT NULL COMMENT '延误发出票数',
  `issue_sign_sum` int(11) DEFAULT NULL COMMENT '签收总票数',
  `issue_err_sum` int(11) DEFAULT NULL COMMENT '错分票数',
  `oper_cnt` int(11) DEFAULT NULL COMMENT '操作量（入分拨票数）',
  `err_snd_cnt` int(11) DEFAULT NULL COMMENT '错发量',
  `pk_tot_num` int(11) DEFAULT NULL COMMENT '已集包数',
  `pk_error_num` int(11) DEFAULT NULL COMMENT '错集包数',
  `dob_tot_cnt` int(11) DEFAULT NULL COMMENT '遗失操作总票数',
  `dob_lost_cnt` int(11) DEFAULT NULL COMMENT '疑似遗失票数',
  `lost_cnt` int(11) DEFAULT NULL COMMENT '遗失票数',
  `trans_len` decimal(4,2) DEFAULT NULL COMMENT '中转操作时效',
  `trans_cnt` int(11) DEFAULT NULL COMMENT '票件量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大数据移动化质控表:kangyj:20170816';

-- ----------------------------
-- Table structure for bas_s_busi_delv
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_delv`;
CREATE TABLE `bas_s_busi_delv` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `delv_dt_10` date DEFAULT NULL COMMENT '昨日签收时间',
  `delv_dbct_10` int(11) DEFAULT NULL COMMENT '昨日签收站点',
  `delv_cnt` bigint(20) DEFAULT NULL COMMENT '昨日签收量',
  `delv_cnt_yd` bigint(20) DEFAULT NULL COMMENT '上一日签收量',
  `delv_cnt_wd` bigint(20) DEFAULT NULL COMMENT '周同期签收量',
  `delv_cnt_md` bigint(20) DEFAULT NULL COMMENT '月同期签收量',
  `delv_cnt_q` bigint(20) DEFAULT NULL COMMENT '去年同季度签收量',
  `delv_cnt_w` bigint(20) DEFAULT NULL COMMENT '上周签收量',
  `delv_cnt_m` bigint(20) DEFAULT NULL COMMENT '本月1号到昨日的签收量',
  `delv_dt_m` int(11) DEFAULT NULL COMMENT '本月1号到昨日的天数',
  `delv_cnt_lm` bigint(20) DEFAULT NULL COMMENT '上月签收总量',
  `delv_cnt_llm` bigint(20) DEFAULT NULL COMMENT '上上月签收总量',
  `delv_cnt_lllm` bigint(20) DEFAULT NULL COMMENT '上上上月签收总量',
  `org_cd` int(10) unsigned DEFAULT NULL COMMENT '机构代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='昨日签收表';

-- ----------------------------
-- Table structure for bas_s_busi_delv_3
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_delv_3`;
CREATE TABLE `bas_s_busi_delv_3` (
  `org_cd` int(10) unsigned DEFAULT NULL,
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `delv_dt_3` date DEFAULT NULL COMMENT '昨日揽件时间',
  `pick_dbct_14` int(11) DEFAULT NULL COMMENT '昨日揽件站点',
  `pick_cnt` bigint(20) DEFAULT NULL COMMENT '昨日揽件量',
  `delv_cnt` bigint(20) DEFAULT NULL COMMENT '昨日3日内签收数量',
  `pick_cnt_yd` bigint(20) DEFAULT NULL COMMENT '上一日揽件量',
  `delv_cnt_yd` bigint(20) DEFAULT NULL COMMENT '上一日3日内签收数量',
  `pick_cnt_wd` bigint(20) DEFAULT NULL COMMENT '周同期揽件量',
  `delv_cnt_wd` bigint(20) DEFAULT NULL COMMENT '周同期3日内签收数量',
  `pick_cnt_w` bigint(20) DEFAULT NULL COMMENT '上周揽件量',
  `delv_cnt_w` bigint(20) DEFAULT NULL COMMENT '上周3日内签收数量',
  `pick_cnt_m` bigint(20) DEFAULT NULL COMMENT '本月1号到昨日的揽件量',
  `delv_cnt_m` bigint(20) DEFAULT NULL COMMENT '本月1号到昨日3日内签收数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='昨日3日内签收表';

-- ----------------------------
-- Table structure for bas_s_busi_delv_3_tmp0
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_delv_3_tmp0`;
CREATE TABLE `bas_s_busi_delv_3_tmp0` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `delv_dt_3` date DEFAULT NULL COMMENT '统计时间',
  `pick_site_14` int(11) DEFAULT NULL COMMENT '揽件网点',
  `pick_dbct_14` int(11) DEFAULT NULL COMMENT '揽件分拨',
  `pick_cnt` bigint(20) DEFAULT NULL COMMENT '揽件数量',
  `delv_cnt` bigint(20) DEFAULT NULL COMMENT '3日内签收数量',
  `delv_3_week` date DEFAULT NULL COMMENT '昨日对应周一',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='签收，揽件 中间表吗';

-- ----------------------------
-- Table structure for bas_s_busi_delv_tmp0
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_delv_tmp0`;
CREATE TABLE `bas_s_busi_delv_tmp0` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `delv_dt_10` date DEFAULT NULL COMMENT '昨日签收时间',
  `delv_dbct_10` int(11) DEFAULT NULL COMMENT '昨日签收分拨',
  `delv_site_10` int(11) DEFAULT NULL COMMENT '昨日签收网点',
  `delv_cnt` bigint(20) DEFAULT NULL COMMENT '昨日签收量',
  `delv_quart` int(11) DEFAULT NULL COMMENT '昨日签收对应季度',
  `delv_week` date DEFAULT NULL COMMENT '昨日对应周一',
  `org_cd` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='单日派件成功率\r\n';

-- ----------------------------
-- Table structure for bas_s_busi_nodelv_7
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_nodelv_7`;
CREATE TABLE `bas_s_busi_nodelv_7` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nodelv_dt_7` date DEFAULT NULL COMMENT '昨日揽件时间',
  `pick_dbct_14` int(11) DEFAULT NULL COMMENT '昨日揽件站点',
  `pick_cnt` bigint(20) DEFAULT NULL COMMENT '昨日揽件量',
  `nodelv_cnt` bigint(20) DEFAULT NULL COMMENT '昨日7日还未签收数量',
  `pick_cnt_yd` bigint(20) DEFAULT NULL COMMENT '上一日揽件量',
  `delv_cnt_yd` bigint(20) DEFAULT NULL COMMENT '上一日7日还未签收数量',
  `pick_cnt_wd` bigint(20) DEFAULT NULL COMMENT '周同期揽件量',
  `delv_cnt_wd` bigint(20) DEFAULT NULL COMMENT '周同期7日还未签收数量',
  `pick_cnt_w` bigint(20) DEFAULT NULL COMMENT '上周揽件量',
  `delv_cnt_w` bigint(20) DEFAULT NULL COMMENT '上周7日还未签收数量',
  `pick_cnt_m` bigint(20) DEFAULT NULL COMMENT '本月1号到昨日的揽件量',
  `delv_cnt_m` bigint(20) DEFAULT NULL COMMENT '本月1号到昨日7日还未签收数量',
  `org_cd` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='未签收表\r\n';

-- ----------------------------
-- Table structure for bas_s_busi_nodelv_7_tmp0
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_nodelv_7_tmp0`;
CREATE TABLE `bas_s_busi_nodelv_7_tmp0` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nodelv_dt_7` date DEFAULT NULL COMMENT '统计时间',
  `pick_site_14` int(11) DEFAULT NULL COMMENT '揽件站点',
  `pick_dbct_14` int(11) DEFAULT NULL COMMENT '揽件分拨',
  `pick_cnt` bigint(20) DEFAULT NULL COMMENT '揽件数量',
  `nodelv_cnt3` bigint(20) DEFAULT NULL COMMENT '日内签收数量',
  `nodelv_7_week` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '昨日对应周一',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='未签收表临时表';

-- ----------------------------
-- Table structure for bas_s_busi_pick
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_pick`;
CREATE TABLE `bas_s_busi_pick` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pick_dt_14` date DEFAULT NULL COMMENT '昨日揽件时间',
  `pick_site_14` bigint(20) DEFAULT NULL COMMENT '昨日揽件站点',
  `pick_cnt` bigint(20) DEFAULT NULL COMMENT '昨日揽件量',
  `pick_cnt_yd` bigint(20) DEFAULT NULL COMMENT '上一日揽件量',
  `pick_cnt_wd` bigint(20) DEFAULT NULL COMMENT '周同期揽件量',
  `pick_cnt_md` bigint(20) DEFAULT NULL COMMENT '月同期揽件量',
  `pick_cnt_q` bigint(20) DEFAULT NULL COMMENT '去年同季度揽件量',
  `pick_cnt_w` bigint(20) DEFAULT NULL COMMENT '上周揽件量',
  `pick_cnt_m` bigint(20) DEFAULT NULL COMMENT '本月1号到昨日的揽件量',
  `pick_dt_m` int(11) DEFAULT NULL COMMENT '本月1号到昨日的天数',
  `pick_cnt_lm` bigint(20) DEFAULT NULL COMMENT '上月揽件总量',
  `pick_cnt_llm` bigint(20) DEFAULT NULL COMMENT '上上月揽件总量',
  `pick_cnt_lllm` bigint(20) DEFAULT NULL COMMENT '上上上月揽件总量',
  `org_cd` int(10) unsigned DEFAULT NULL,
  `target_val` int(10) unsigned DEFAULT NULL COMMENT '目标值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='揽件表';

-- ----------------------------
-- Table structure for bas_s_busi_snd
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_snd`;
CREATE TABLE `bas_s_busi_snd` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `snd_dt_24` date DEFAULT NULL COMMENT '昨日派件时间',
  `snd_dbct_24` int(11) DEFAULT NULL COMMENT '昨日派件站点',
  `snd_cnt` bigint(20) DEFAULT NULL COMMENT '昨日派件量',
  `snd_cnt_yd` bigint(20) DEFAULT NULL COMMENT '上一日派件量',
  `snd_cnt_wd` bigint(20) DEFAULT NULL COMMENT '周同期派件量',
  `snd_cnt_md` bigint(20) DEFAULT NULL COMMENT '月同期派件量',
  `snd_cnt_q` bigint(20) DEFAULT NULL COMMENT '去年同季度派件量',
  `snd_cnt_w` bigint(20) DEFAULT NULL COMMENT '上周派件量',
  `snd_cnt_m` bigint(20) DEFAULT NULL COMMENT '本月1号到昨日的派件量',
  `snd_dt_m` int(11) DEFAULT NULL COMMENT '本月1号到昨日的天数',
  `snd_cnt_lm` bigint(20) DEFAULT NULL COMMENT '上月派件总量',
  `snd_cnt_llm` bigint(20) DEFAULT NULL COMMENT '上上月派件总量',
  `snd_cnt_lllm` bigint(20) DEFAULT NULL COMMENT '上上上月派件总量',
  `org_cd` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='派件表';

-- ----------------------------
-- Table structure for bas_s_busi_snd_tmp0
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_busi_snd_tmp0`;
CREATE TABLE `bas_s_busi_snd_tmp0` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `snd_dt_24` date DEFAULT NULL COMMENT '昨日派件时间',
  `snd_dbct_24` int(11) DEFAULT NULL COMMENT '昨日派件站点(所属分拨)',
  `snd_site_24` int(11) DEFAULT NULL COMMENT '昨日派件站点(所属一级网点)',
  `snd_cnt` bigint(20) DEFAULT NULL COMMENT '昨日派件量',
  `snd_quart` int(11) DEFAULT NULL COMMENT '昨日派件对应季度',
  `snd_week` date DEFAULT NULL COMMENT '昨日对应周一',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bas_s_deliver_await_tl
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_deliver_await_tl`;
CREATE TABLE `bas_s_deliver_await_tl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `deliver_await_date` datetime DEFAULT NULL COMMENT '交件等候时间',
  `org_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `deliver_await_tl` bigint(20) DEFAULT NULL COMMENT '交件等候时效',
  `days_from` bigint(20) DEFAULT NULL COMMENT '日环比',
  `Week_compared_to_same` bigint(20) DEFAULT NULL COMMENT '周同比',
  `week_avg_tl` bigint(20) DEFAULT NULL COMMENT '周平均时效',
  `month_avg_tl` bigint(20) DEFAULT NULL COMMENT '月平均时效',
  `last_day_tl` bigint(20) DEFAULT NULL COMMENT '上一日时效',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  `rank_lift` bigint(20) DEFAULT NULL COMMENT '排名升降',
  `sameTime_val` bigint(20) DEFAULT NULL COMMENT '同期值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交件等候时效表';

-- ----------------------------
-- Table structure for bas_s_deliver_tl
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_deliver_tl`;
CREATE TABLE `bas_s_deliver_tl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `deliver_date` datetime DEFAULT NULL COMMENT '交件时间',
  `org_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `deliver_await_tl` bigint(20) DEFAULT NULL COMMENT '交件时效',
  `days_from` bigint(20) DEFAULT NULL COMMENT '日环比',
  `Week_compared_to_same` bigint(20) DEFAULT NULL COMMENT '周同比',
  `week_avg_tl` bigint(20) DEFAULT NULL COMMENT '周平均时效',
  `month_avg_tl` bigint(20) DEFAULT NULL COMMENT '月平均时效',
  `last_day_tl` bigint(20) DEFAULT NULL COMMENT '上一日时效',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  `rank_lift` bigint(20) DEFAULT NULL COMMENT '排名升降',
  `sameTime_val` bigint(20) DEFAULT NULL COMMENT '同期值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交件时效表';

-- ----------------------------
-- Table structure for bas_s_deliver_unloadtl
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_deliver_unloadtl`;
CREATE TABLE `bas_s_deliver_unloadtl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `deliver_date` datetime DEFAULT NULL COMMENT '交件卸车日期',
  `org_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `deliver_unload_tl` bigint(20) DEFAULT NULL COMMENT '交件卸车时效',
  `days_from` bigint(20) DEFAULT NULL COMMENT '日环比',
  `Week_compared_to_same` bigint(20) DEFAULT NULL COMMENT '周同比',
  `week_avg_tl` bigint(20) DEFAULT NULL COMMENT '周平均时效',
  `month_avg_tl` bigint(20) DEFAULT NULL COMMENT '月平均时效',
  `last_day_tl` bigint(20) DEFAULT NULL COMMENT '上一日时效',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  `rank_lift` bigint(20) DEFAULT NULL COMMENT '排名升降',
  `sameTime_val` bigint(20) DEFAULT NULL COMMENT '同期值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交件卸车时效表';

-- ----------------------------
-- Table structure for bas_s_deliver_unload_tl
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_deliver_unload_tl`;
CREATE TABLE `bas_s_deliver_unload_tl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `deliver_date` datetime DEFAULT NULL COMMENT '交件卸车日期',
  `org_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `deliver_unload_tl` bigint(20) DEFAULT NULL COMMENT '交件卸车时效',
  `days_from` bigint(20) DEFAULT NULL COMMENT '日环比',
  `Week_compared_to_same` bigint(20) DEFAULT NULL COMMENT '周同比',
  `week_avg_tl` bigint(20) DEFAULT NULL COMMENT '周平均时效',
  `month_avg_tl` bigint(20) DEFAULT NULL COMMENT '月平均时效',
  `last_day_tl` bigint(20) DEFAULT NULL COMMENT '上一日时效',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  `rank_lift` bigint(20) DEFAULT NULL COMMENT '排名升降',
  `sameTime_val` bigint(20) DEFAULT NULL COMMENT '同期值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交件卸车时效表';

-- ----------------------------
-- Table structure for bas_s_distribute_tl
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_distribute_tl`;
CREATE TABLE `bas_s_distribute_tl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `distribute_date` datetime DEFAULT NULL COMMENT '分发时间',
  `org_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `distribute_tl` bigint(20) DEFAULT NULL COMMENT '分发时效',
  `days_from` bigint(20) DEFAULT NULL COMMENT '日环比',
  `Week_compared_to_same` bigint(20) DEFAULT NULL COMMENT '周同比',
  `week_avg_tl` bigint(20) DEFAULT NULL COMMENT '周平均时效',
  `month_avg_tl` bigint(20) DEFAULT NULL COMMENT '月平均时效',
  `last_day_tl` bigint(20) DEFAULT NULL COMMENT '上一日时效',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  `rank_lift` bigint(20) DEFAULT NULL COMMENT '排名升降',
  `sameTime_val` bigint(20) DEFAULT NULL COMMENT '同期值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分发时效表';

-- ----------------------------
-- Table structure for bas_s_pick_tl
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_pick_tl`;
CREATE TABLE `bas_s_pick_tl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pick_date` datetime DEFAULT NULL COMMENT '揽件时间',
  `org_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `take_tl` bigint(20) DEFAULT NULL COMMENT '揽收时效',
  `days_from` bigint(20) DEFAULT NULL COMMENT '日环比',
  `Week_compared_to_same` bigint(20) DEFAULT NULL COMMENT '周同比',
  `business_avg_tl` bigint(20) DEFAULT NULL COMMENT '行业平均时效',
  `week_avg_tl` bigint(20) DEFAULT NULL COMMENT '周平均时效',
  `month_avg_tl` bigint(20) DEFAULT NULL COMMENT '月平均时效',
  `last_day_tl` bigint(20) DEFAULT NULL COMMENT '上一日时效',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  `rank_lift` bigint(20) DEFAULT NULL COMMENT '排名升降',
  `last_week_sameTime_val` bigint(20) DEFAULT NULL COMMENT '上周同期值',
  `day_avg_val` bigint(20) DEFAULT NULL COMMENT '日行业均值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='揽件时效表';

-- ----------------------------
-- Table structure for bas_s_ticket_timeline
-- ----------------------------
DROP TABLE IF EXISTS `bas_s_ticket_timeline`;
CREATE TABLE `bas_s_ticket_timeline` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ticket_date` date DEFAULT NULL COMMENT '票件日期',
  `org_code` varchar(255) DEFAULT NULL COMMENT '机构代码',
  `pick_tl` varchar(255) DEFAULT NULL COMMENT '揽件时效',
  `deliver_tl` varchar(255) DEFAULT NULL COMMENT '交件时效',
  `start_op_tl` varchar(255) DEFAULT NULL COMMENT '始发操作时效',
  `trans_post_tl` varchar(255) DEFAULT NULL COMMENT '中转运输时效',
  `arrive_op_tl` varchar(255) DEFAULT NULL COMMENT '到达操作时效',
  `distribu_tl` varchar(255) DEFAULT NULL COMMENT '分发时效',
  `sign_tl` varchar(255) DEFAULT NULL COMMENT '签收时效',
  `omnidist_tl` varchar(255) DEFAULT NULL COMMENT '全程时效',
  `distribut_center` varchar(255) DEFAULT NULL COMMENT '分拨中心',
  `rank` varchar(255) DEFAULT NULL COMMENT '排名',
  `rank_lift` varchar(255) DEFAULT NULL COMMENT '排名升降',
  `mdustry_avg` varchar(255) DEFAULT NULL COMMENT '行业平均',
  `same_tm_val` varchar(255) DEFAULT NULL COMMENT '同期值',
  `last_week_tl` varchar(255) DEFAULT NULL COMMENT '上周时效',
  `cur_mon_to_yd_tl` varchar(255) DEFAULT NULL COMMENT '本月1号到昨日时效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bas_t_bigdata_effect_load
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_effect_load`;
CREATE TABLE `bas_t_bigdata_effect_load` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '装车时间',
  `scan_site` int(11) NOT NULL DEFAULT '0' COMMENT '装车站点',
  `lod_wt_tot` bigint(20) DEFAULT NULL COMMENT '待发总时长',
  `lod_tm_tot` bigint(20) DEFAULT NULL COMMENT '装车总时长',
  `lod_vchr_cnt` int(11) DEFAULT NULL COMMENT '装车数',
  `lod_ship_cnt` bigint(20) DEFAULT NULL COMMENT '装车件量',
  PRIMARY KEY (`stats_dt`,`scan_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化车辆时效装车、待发时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_effect_pick
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_effect_pick`;
CREATE TABLE `bas_t_bigdata_effect_pick` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '卸车时间',
  `scan_site` int(11) NOT NULL DEFAULT '0' COMMENT '交件站点',
  `uld_wt_tot` bigint(20) DEFAULT NULL COMMENT '交件等候总时长',
  `uld_tm_tot` bigint(20) DEFAULT NULL COMMENT '交件卸车总时长',
  `uld_vchr_cnt` int(11) DEFAULT NULL COMMENT '卸车数',
  `uld_ship_cnt` bigint(20) DEFAULT NULL COMMENT '卸车件量',
  PRIMARY KEY (`stats_dt`,`scan_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化车辆时效交件等候、交件卸车时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_effect_trans
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_effect_trans`;
CREATE TABLE `bas_t_bigdata_effect_trans` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间',
  `scan_site` int(11) NOT NULL DEFAULT '0' COMMENT '目的分拨',
  `trans_tm_tot` bigint(20) DEFAULT NULL COMMENT '在途运输总时长',
  `vchr_cnt` int(11) DEFAULT NULL COMMENT '车辆数',
  PRIMARY KEY (`stats_dt`,`scan_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化车辆时效在途运输时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_effect_uload
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_effect_uload`;
CREATE TABLE `bas_t_bigdata_effect_uload` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '卸车时间',
  `scan_site` bigint(20) NOT NULL DEFAULT '0' COMMENT '卸车站点',
  `uld_wt_tot` bigint(20) DEFAULT NULL COMMENT '待卸总时长',
  `uld_tm_tot` bigint(20) DEFAULT NULL COMMENT '卸车总时长',
  `uld_vchr_cnt` int(11) DEFAULT NULL COMMENT '卸车数',
  `uld_ship_cnt` bigint(20) DEFAULT NULL COMMENT '卸车件量',
  PRIMARY KEY (`stats_dt`,`scan_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化车辆时效卸车、待卸时效表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_trans_arve
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_trans_arve`;
CREATE TABLE `bas_t_bigdata_trans_arve` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间',
  `dest_site` int(11) NOT NULL DEFAULT '0' COMMENT '到达站点',
  `arv_cnt` int(11) DEFAULT NULL COMMENT '到达总数',
  `arv_cnt_dly` int(11) DEFAULT NULL COMMENT '延误到达数',
  PRIMARY KEY (`stats_dt`,`dest_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化运输装延误率表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_trans_arve_wd
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_trans_arve_wd`;
CREATE TABLE `bas_t_bigdata_trans_arve_wd` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间',
  `dest_site` int(11) NOT NULL DEFAULT '0' COMMENT '到达分拨',
  `arv_cnt` int(11) DEFAULT NULL COMMENT '到达总数',
  `arv_cnt_dly` int(11) DEFAULT NULL COMMENT '延误到达数',
  PRIMARY KEY (`stats_dt`,`dest_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化运输装网点发车数表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_trans_cl
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_trans_cl`;
CREATE TABLE `bas_t_bigdata_trans_cl` (
  `stats_dt` date DEFAULT NULL COMMENT '统计时间',
  `fst_scan_site` int(11) DEFAULT NULL COMMENT '始发站点',
  `dest_site` int(11) DEFAULT NULL COMMENT '目的站点',
  `vld_tm` datetime DEFAULT NULL COMMENT '生效时间',
  `end_dt` date DEFAULT NULL COMMENT '截止日期',
  `car_ln_cd` varchar(40) DEFAULT NULL COMMENT '1有效，0无效'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大数据移动化运输车线表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_trans_dept
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_trans_dept`;
CREATE TABLE `bas_t_bigdata_trans_dept` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间',
  `strt_site` int(11) NOT NULL DEFAULT '0' COMMENT '发车站点',
  `dept_cnt` int(11) DEFAULT NULL COMMENT '发车总数',
  `dept_cnt_tl` int(11) DEFAULT NULL COMMENT '正班发车数',
  `dept_cnt_cb` int(11) DEFAULT NULL COMMENT '卡班发车数',
  `dept_cnt_dly` int(11) DEFAULT NULL COMMENT '延迟发车数',
  PRIMARY KEY (`stats_dt`,`strt_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化运输装发车数迟发率表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_trans_dept_wd
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_trans_dept_wd`;
CREATE TABLE `bas_t_bigdata_trans_dept_wd` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间',
  `strt_site` int(11) NOT NULL DEFAULT '0' COMMENT '发车分拨',
  `dept_cnt` int(11) DEFAULT NULL COMMENT '发车总数',
  PRIMARY KEY (`stats_dt`,`strt_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化运输装网点发车数表:kangyj:20170726';

-- ----------------------------
-- Table structure for bas_t_bigdata_trans_load
-- ----------------------------
DROP TABLE IF EXISTS `bas_t_bigdata_trans_load`;
CREATE TABLE `bas_t_bigdata_trans_load` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计时间',
  `strt_site` int(11) NOT NULL DEFAULT '0' COMMENT '发车站点',
  `std_ldcp` bigint(20) DEFAULT NULL COMMENT '核定装载总量',
  `act_ldcp` bigint(20) DEFAULT NULL COMMENT '实际装载总量',
  `std_ldcp_tl` bigint(20) DEFAULT NULL COMMENT '正班车核定装载总量',
  `act_ldcp_tl` bigint(20) DEFAULT NULL COMMENT '正班车实际装载总量',
  `std_ldcp_cb` bigint(20) DEFAULT NULL COMMENT '卡班车核定装载总量',
  `act_ldcp_cb` bigint(20) DEFAULT NULL COMMENT '卡班车实际装载总量',
  PRIMARY KEY (`stats_dt`,`strt_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大数据移动化运输装载率表:kangyj:20170726';

-- ----------------------------
-- Table structure for brch_full_quo_maint
-- ----------------------------
DROP TABLE IF EXISTS `brch_full_quo_maint`;
CREATE TABLE `brch_full_quo_maint` (
  `province` int(10) NOT NULL,
  `ordnry_cust_fst_wgt` decimal(10,4) DEFAULT NULL COMMENT '普通客户首重重量',
  `ordnry_cust_fst_prc` decimal(10,4) DEFAULT NULL COMMENT '普通客户首重价格',
  `ordnry_cust_cont_prc` decimal(10,4) DEFAULT NULL COMMENT '普通客户续重价格',
  `grtr_cust_fst_wgt` decimal(10,4) DEFAULT NULL COMMENT '大客户首重重量',
  `grtr_cust_fst_prc` decimal(10,4) DEFAULT NULL COMMENT '大客户首重价格',
  `grtr_cust_cont_prc` decimal(10,4) DEFAULT NULL COMMENT '大客户续重价格',
  `devi_oper_` decimal(10,4) DEFAULT NULL COMMENT '分部操作费',
  `devi_ship_` decimal(10,4) DEFAULT NULL COMMENT '分部面单费',
  `devi_fst_wgt` decimal(10,4) DEFAULT NULL COMMENT '分部首重重量',
  `devi_fst_prc` decimal(10,4) DEFAULT NULL COMMENT '分部首重价格',
  `devi_cont_prc` decimal(10,4) DEFAULT NULL COMMENT '分部续重价格',
  `emp_oper_` decimal(10,4) DEFAULT NULL COMMENT '业务员操作费',
  `emp_ship_` decimal(10,4) DEFAULT NULL COMMENT '业务员面单费',
  `emp_fst_wgt` decimal(10,4) DEFAULT NULL COMMENT '业务员首重重量',
  `emp_fst_prc` decimal(10,4) DEFAULT NULL COMMENT '业务员首重价格',
  `emp_cont_prc` decimal(10,4) DEFAULT NULL COMMENT '业务员续重价格',
  PRIMARY KEY (`province`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网点全国报价维护';

-- ----------------------------
-- Table structure for brch_sr_cs_pz
-- ----------------------------
DROP TABLE IF EXISTS `brch_sr_cs_pz`;
CREATE TABLE `brch_sr_cs_pz` (
  `brch` int(10) NOT NULL COMMENT '网点',
  `tckt_pick_incm` decimal(5,4) DEFAULT NULL COMMENT '每票揽件收入',
  `tckt_delv_incm` decimal(5,4) DEFAULT NULL COMMENT '每票派件收入',
  `min_pick_incm` decimal(5,4) DEFAULT NULL COMMENT '最低揽件收入',
  `min_delv_incm` decimal(5,4) DEFAULT NULL COMMENT '最低派件收入',
  `max_pick_incm` decimal(5,4) DEFAULT NULL COMMENT '最高揽件收入',
  `max_delv_incm` decimal(5,4) DEFAULT NULL COMMENT '最高派件收入',
  `fst_add_tm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最初增加时间',
  `chk_stat` int(1) DEFAULT '1' COMMENT '审核状态(1未审核2审核通过3驳回)',
  `dismisd_rsn` varchar(100) DEFAULT NULL COMMENT '驳回原因',
  PRIMARY KEY (`brch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网点收入参数配置';

-- ----------------------------
-- Table structure for brch_yj_cs_pz
-- ----------------------------
DROP TABLE IF EXISTS `brch_yj_cs_pz`;
CREATE TABLE `brch_yj_cs_pz` (
  `prov` int(10) NOT NULL DEFAULT '0' COMMENT '省份',
  `city` int(10) NOT NULL DEFAULT '0' COMMENT '城市',
  `pick_cnt_parm` decimal(6,4) DEFAULT NULL COMMENT '揽件量降幅参数',
  `cfm_rcv_rt_parm` decimal(6,4) DEFAULT NULL COMMENT '签收率参数',
  `rt_parm` decimal(6,4) DEFAULT NULL COMMENT '客诉率参数',
  `ppd_val` decimal(20,2) DEFAULT NULL COMMENT '预付款警戒值',
  `unit_tckt_pnlt_val` decimal(10,2) DEFAULT NULL COMMENT '单票罚款警戒值',
  `fst_add_tm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最初增加时间',
  PRIMARY KEY (`prov`,`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网点预警参数配置';

-- ----------------------------
-- Table structure for cainiao_data
-- ----------------------------
DROP TABLE IF EXISTS `cainiao_data`;
CREATE TABLE `cainiao_data` (
  `stats_dt` date NOT NULL COMMENT '统计日期',
  `full_path_nm` int(10) NOT NULL COMMENT '全链路排名',
  `full_path_tlns` decimal(5,2) NOT NULL COMMENT '全链路时效',
  `indust_avg_tlns` decimal(5,2) NOT NULL COMMENT '行业平均时效',
  `entr_pers` varchar(20) NOT NULL COMMENT '录入人',
  `entr_tm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `rmk` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`stats_dt`),
  KEY `stats_dt` (`stats_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cainiao_dxl
-- ----------------------------
DROP TABLE IF EXISTS `cainiao_dxl`;
CREATE TABLE `cainiao_dxl` (
  `line_Name` varchar(255) DEFAULT NULL COMMENT '线路名称',
  `end_place` varchar(40) DEFAULT NULL COMMENT '目的地',
  `start_place` varchar(40) DEFAULT NULL COMMENT '始发地',
  `lev_unit_cnt` int(10) DEFAULT NULL COMMENT '去程单量',
  `rtn_unit_cnt` int(10) DEFAULT NULL COMMENT '返程单量',
  `lev_rtn_rate` decimal(10,2) DEFAULT NULL COMMENT '去返比',
  `lev_tlns` decimal(10,2) DEFAULT NULL COMMENT '去程时效',
  `lev_indust_nm` int(10) DEFAULT NULL COMMENT '去程行业排名',
  `lev_indust_val` decimal(10,2) DEFAULT NULL COMMENT '去程行业均值',
  `rtn_tlns` decimal(10,2) DEFAULT NULL COMMENT '返程时效',
  `rtn_indust_nm` int(10) DEFAULT NULL COMMENT '返程行业排名',
  `rtn_indust_val` decimal(10,2) DEFAULT NULL COMMENT '返程行业均值',
  `start_dt` date NOT NULL,
  `line_type` int(11) DEFAULT NULL,
  KEY `a` (`start_dt`),
  KEY `b` (`start_dt`,`line_Name`) USING BTREE,
  KEY `c` (`start_place`,`end_place`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CREATOR:何飞虎 CREATED_TIME:20160901 COMMENT:线路日明细表';

-- ----------------------------
-- Table structure for cainiao_tool_line_type
-- ----------------------------
DROP TABLE IF EXISTS `cainiao_tool_line_type`;
CREATE TABLE `cainiao_tool_line_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(255) DEFAULT NULL COMMENT '线路名称',
  `line_type` int(11) DEFAULT NULL COMMENT '1: 2860线路 2：50城市线路',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a` (`line_name`,`line_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5358 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_cst
-- ----------------------------
DROP TABLE IF EXISTS `car_cst`;
CREATE TABLE `car_cst` (
  `brch` bigint(10) NOT NULL DEFAULT '0',
  `car_typ` int(1) DEFAULT NULL COMMENT '类型(1公司2分部3服务部)',
  `car_cd` varchar(40) NOT NULL COMMENT '车牌号码',
  `cd` varchar(40) DEFAULT NULL COMMENT '行驶证号码',
  `car_model` varchar(40) DEFAULT NULL COMMENT '车型',
  `vol` decimal(10,4) DEFAULT NULL COMMENT '体积',
  `car_volume` int(1) DEFAULT NULL COMMENT '车辆性质(自有,租赁)',
  `mo_exp` decimal(10,4) DEFAULT NULL COMMENT '月费用',
  `insr_amnt` decimal(10,4) DEFAULT NULL COMMENT '保险金额',
  `insr_lmt` date DEFAULT NULL COMMENT '保险期限',
  `car_purpose` int(1) DEFAULT NULL COMMENT '车辆用途(1短波2派送3短波派送4其他)',
  PRIMARY KEY (`brch`,`car_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆成本';

-- ----------------------------
-- Table structure for cip_admin_access_ctrl
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_access_ctrl`;
CREATE TABLE `cip_admin_access_ctrl` (
  `sys_id` varchar(20) NOT NULL COMMENT '系统id',
  `access_flag` char(1) NOT NULL COMMENT '访问标识',
  `access_type` varchar(6) DEFAULT NULL COMMENT '访问类型',
  `access_ip` varchar(32) DEFAULT NULL COMMENT '访问ip',
  `auth_code` varchar(50) DEFAULT NULL COMMENT '访问授权码',
  `other_params` varchar(200) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL COMMENT '操作备注',
  `create_time` datetime DEFAULT NULL COMMENT '系统时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`sys_id`,`access_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统访问控制';

-- ----------------------------
-- Table structure for cip_admin_access_rel
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_access_rel`;
CREATE TABLE `cip_admin_access_rel` (
  `sys_id` varchar(20) NOT NULL COMMENT '系统id',
  `resource_id` varchar(50) NOT NULL COMMENT '资源id',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`sys_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统与资源关系配置';

-- ----------------------------
-- Table structure for cip_admin_access_res
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_access_res`;
CREATE TABLE `cip_admin_access_res` (
  `resource_id` varchar(50) NOT NULL COMMENT '资源id',
  `access_flag` char(1) NOT NULL COMMENT '访问标识',
  `resource_name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `resource_desc` varchar(300) DEFAULT NULL COMMENT '资源描述',
  `sys_uri` varchar(150) DEFAULT NULL COMMENT '访问URI',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  `icon_id` varchar(20) DEFAULT NULL COMMENT '图标id',
  PRIMARY KEY (`resource_id`,`access_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统访问资源配置';

-- ----------------------------
-- Table structure for cip_admin_auth_act2obj
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_auth_act2obj`;
CREATE TABLE `cip_admin_auth_act2obj` (
  `root_node_id` varchar(50) NOT NULL COMMENT '资源根节点',
  `resource_id` varchar(50) NOT NULL COMMENT '行为资源id',
  `obj_id` varchar(32) NOT NULL COMMENT '权限对象id',
  `obj_attr_id` varchar(32) NOT NULL COMMENT '权限对象属性id',
  `attr_value` varchar(150) DEFAULT NULL COMMENT '权限对象属性值',
  `val_src_type` char(1) DEFAULT NULL COMMENT '属性值来源',
  `incl_sub_flag` char(1) DEFAULT NULL COMMENT '包含属性下级标识',
  `field_id` varchar(50) DEFAULT NULL COMMENT '表字段id',
  PRIMARY KEY (`root_node_id`,`resource_id`,`obj_id`,`obj_attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/权限对象授权配置';

-- ----------------------------
-- Table structure for cip_admin_auth_attr
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_auth_attr`;
CREATE TABLE `cip_admin_auth_attr` (
  `obj_id` varchar(32) NOT NULL COMMENT '权限对象id',
  `obj_attr_id` varchar(32) NOT NULL COMMENT '权限对象属性id',
  `obj_attr_name` varchar(50) DEFAULT NULL COMMENT '权限对象属性描述',
  `attr_val_table` varchar(50) DEFAULT NULL COMMENT '属性取值表',
  `sup_col_id` varchar(32) DEFAULT NULL COMMENT '上级字段id',
  `sub_col_id` varchar(32) DEFAULT NULL COMMENT '下级字段id',
  `rel_col_id` varchar(255) DEFAULT NULL COMMENT '层级关联字段id',
  PRIMARY KEY (`obj_id`,`obj_attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/权限对象属性信息';

-- ----------------------------
-- Table structure for cip_admin_auth_obj
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_auth_obj`;
CREATE TABLE `cip_admin_auth_obj` (
  `auth_obj_id` varchar(32) NOT NULL COMMENT '权限对象id',
  `auth_obj_name` varchar(50) DEFAULT NULL COMMENT '权限对象描述',
  PRIMARY KEY (`auth_obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/权限对象信息';

-- ----------------------------
-- Table structure for cip_admin_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_auth_role`;
CREATE TABLE `cip_admin_auth_role` (
  `auth_role_id` varchar(32) NOT NULL COMMENT '角色Id',
  `auth_attr_id` varchar(32) NOT NULL COMMENT '权限属性id',
  `auth_attr_val` varchar(32) DEFAULT NULL COMMENT '权限属性值',
  PRIMARY KEY (`auth_role_id`,`auth_attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/角色权限值配置';

-- ----------------------------
-- Table structure for cip_admin_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_auth_user`;
CREATE TABLE `cip_admin_auth_user` (
  `user_attr_id` varchar(32) NOT NULL COMMENT '权限属性id',
  `auth_attr_name` varchar(32) DEFAULT NULL COMMENT '权限属性名称',
  `auth_attr_id` varchar(32) DEFAULT NULL COMMENT '权限字段id',
  PRIMARY KEY (`user_attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/用户权限字段配置';

-- ----------------------------
-- Table structure for cip_admin_codes
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_codes`;
CREATE TABLE `cip_admin_codes` (
  `domain_id` varchar(32) NOT NULL COMMENT '数据域id',
  `code_type` varchar(32) NOT NULL COMMENT '编码类型',
  `code_name` varchar(50) DEFAULT NULL COMMENT '编码名称',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`domain_id`,`code_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/数据编码';

-- ----------------------------
-- Table structure for cip_admin_commonquery
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_commonquery`;
CREATE TABLE `cip_admin_commonquery` (
  `queryId` varchar(50) NOT NULL COMMENT '查询id',
  `description` varchar(100) DEFAULT NULL COMMENT '查询说明',
  `paramlist` varchar(300) DEFAULT NULL COMMENT '查询参数列表',
  `statement` text NOT NULL COMMENT '查询语句',
  `count_statement` text COMMENT '统计语句',
  `pagination` char(1) DEFAULT NULL COMMENT '是否分页',
  `singleRec` char(1) NOT NULL COMMENT '是否多条',
  PRIMARY KEY (`queryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/通用查询配置表';

-- ----------------------------
-- Table structure for cip_admin_domain
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_domain`;
CREATE TABLE `cip_admin_domain` (
  `domain_id` varchar(32) NOT NULL COMMENT '数据域id',
  `domain_name` varchar(50) DEFAULT NULL COMMENT '数据域名称',
  `domain_type` int(1) DEFAULT NULL COMMENT '数据域类型',
  `is_null_flag` int(1) DEFAULT NULL COMMENT '允许为空标识',
  `data_length` int(2) DEFAULT NULL COMMENT '数据长度',
  `default_value` varchar(32) DEFAULT NULL COMMENT '默认值',
  `float_length` int(1) DEFAULT NULL COMMENT '小数位长度',
  `data_type` varchar(18) DEFAULT NULL COMMENT '数据类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  `resp_id` varchar(20) DEFAULT NULL COMMENT '责任人',
  `domain_desc` varchar(300) DEFAULT NULL COMMENT '数据域描述',
  `ref_table_id` varchar(32) DEFAULT NULL COMMENT '参考引用数据表',
  `ref_domain_id` varchar(32) DEFAULT NULL COMMENT '引用数据域id',
  PRIMARY KEY (`domain_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/数据域信息';

-- ----------------------------
-- Table structure for cip_admin_log_access
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_log_access`;
CREATE TABLE `cip_admin_log_access` (
  `log_id` bigint(19) NOT NULL COMMENT '日志id',
  `resource_id` varchar(50) DEFAULT NULL COMMENT '系统资源id',
  `visitor_id` varchar(11) DEFAULT NULL COMMENT '访问对象id',
  `visitor_type` char(1) DEFAULT NULL COMMENT '访问对象类型',
  `occur_time` datetime DEFAULT NULL COMMENT '记录时间',
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip',
  `remark` varchar(300) DEFAULT NULL COMMENT '访问备注',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/本地日志-系统访问';

-- ----------------------------
-- Table structure for cip_admin_log_job
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_log_job`;
CREATE TABLE `cip_admin_log_job` (
  `log_id` bigint(19) NOT NULL COMMENT '日志id',
  `task_id` varchar(32) DEFAULT NULL COMMENT '批量任务id',
  `step_id` int(3) DEFAULT NULL COMMENT '步骤id',
  `step_msg` varchar(300) DEFAULT NULL COMMENT '步骤信息',
  `error_code` int(11) DEFAULT NULL COMMENT '错误代码',
  `occur_time` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/本地日志-作业日志';

-- ----------------------------
-- Table structure for cip_admin_log_mdm
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_log_mdm`;
CREATE TABLE `cip_admin_log_mdm` (
  `log_id` bigint(19) NOT NULL COMMENT '日志id',
  `table_id` varchar(32) DEFAULT NULL COMMENT '数据表id',
  `record_id` varchar(50) DEFAULT NULL COMMENT '记录id',
  `old_values` varchar(300) DEFAULT NULL COMMENT '原始值',
  `new_values` varchar(300) DEFAULT NULL COMMENT '新设置值',
  `user_id` varchar(20) DEFAULT NULL COMMENT '变更人',
  `operate_type` char(1) DEFAULT NULL COMMENT '操作类型',
  `occur_time` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/本地日志-主数据变更';

-- ----------------------------
-- Table structure for cip_admin_meta_str
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_meta_str`;
CREATE TABLE `cip_admin_meta_str` (
  `str_id` varchar(32) NOT NULL COMMENT '数据结构id',
  `str_name` varchar(50) DEFAULT NULL COMMENT '数据结构名称',
  `str_memo` varchar(100) DEFAULT NULL COMMENT '数据结构备注',
  `str_type` varchar(6) DEFAULT NULL COMMENT '数据结构类型',
  `str_prefix` varchar(100) DEFAULT NULL COMMENT '结构前缀',
  `str_module` varchar(10) DEFAULT NULL COMMENT '结构模块id',
  `func_set_id` varchar(32) DEFAULT NULL COMMENT '功能集id',
  `ref_str_id` varchar(32) DEFAULT NULL COMMENT '参考结构id',
  `str_data_type` varchar(6) DEFAULT NULL COMMENT '存储数据类型',
  `prd_date` date DEFAULT NULL COMMENT '落地时间',
  `form_template` varchar(32) DEFAULT NULL COMMENT '表单模板',
  `list_template` varchar(32) DEFAULT NULL COMMENT '列表模板',
  `src_sys_id` varchar(6) DEFAULT NULL COMMENT '源系统id',
  `resp_id` varchar(11) DEFAULT NULL COMMENT '责任人',
  `resp_org` varchar(15) DEFAULT NULL COMMENT '责任部门id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`str_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据管理/系统数据结构表';

-- ----------------------------
-- Table structure for cip_admin_op_log
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_op_log`;
CREATE TABLE `cip_admin_op_log` (
  `op_seq_no` bigint(19) NOT NULL COMMENT '操作流水号',
  `op_table_id` varchar(32) DEFAULT NULL COMMENT '操作对象类型',
  `op_obj_id` varchar(32) DEFAULT NULL COMMENT '操作对象id',
  `op_type` char(1) DEFAULT NULL COMMENT '操作类型',
  `remark` varchar(100) DEFAULT NULL COMMENT '操作备注',
  `create_time` datetime DEFAULT NULL COMMENT '系统时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`op_seq_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统操作日志';

-- ----------------------------
-- Table structure for cip_admin_question
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_question`;
CREATE TABLE `cip_admin_question` (
  `question_id` varchar(20) NOT NULL COMMENT '问题ID',
  `question_title` varchar(20) DEFAULT NULL COMMENT '问题标题',
  `queston_type` char(1) DEFAULT NULL COMMENT '问题类型',
  `question_desc` varchar(500) DEFAULT NULL COMMENT '问题描述',
  `creater` varchar(10) DEFAULT NULL COMMENT '提出人',
  `question_reply` varchar(500) DEFAULT NULL COMMENT '问题答复',
  `handle_flag` char(1) DEFAULT NULL COMMENT '处理标志',
  `update_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operater` varchar(10) DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统问题反馈';

-- ----------------------------
-- Table structure for cip_admin_queue
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_queue`;
CREATE TABLE `cip_admin_queue` (
  `queue_type` varchar(6) NOT NULL COMMENT '队列类型',
  `queue_id` bigint(19) NOT NULL COMMENT '队列流水号',
  `queue_data` varchar(1000) DEFAULT NULL COMMENT '队列数据',
  `queue_flag` char(1) DEFAULT NULL COMMENT '数据提取标识',
  `create_time` datetime DEFAULT NULL COMMENT '系统时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`queue_type`,`queue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统队列信息';

-- ----------------------------
-- Table structure for cip_admin_queue_type_dm
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_queue_type_dm`;
CREATE TABLE `cip_admin_queue_type_dm` (
  `queue_type` varchar(6) NOT NULL COMMENT '队列类型',
  `queue_type_name` varchar(50) DEFAULT NULL COMMENT '队列名称',
  `pojo_class` varchar(50) DEFAULT NULL COMMENT '队列类',
  PRIMARY KEY (`queue_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统队列配置';

-- ----------------------------
-- Table structure for cip_admin_resource
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_resource`;
CREATE TABLE `cip_admin_resource` (
  `resource_id` varchar(50) NOT NULL COMMENT '资源id',
  `resource_name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `resource_desc` varchar(300) DEFAULT NULL COMMENT '资源描述',
  `sys_uri` varchar(150) DEFAULT NULL COMMENT '访问URI',
  `resource_type` char(1) DEFAULT NULL COMMENT '资源类型',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  `icon_id` varchar(20) DEFAULT NULL COMMENT '图标id',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统资源信息';

-- ----------------------------
-- Table structure for cip_admin_resource_2_resource
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_resource_2_resource`;
CREATE TABLE `cip_admin_resource_2_resource` (
  `root_node_id` varchar(50) NOT NULL COMMENT '根节点',
  `res_node_id` varchar(50) NOT NULL COMMENT '资源节点id',
  `res_node_sup` varchar(50) NOT NULL COMMENT '资源上级节点id',
  `node_order` int(11) DEFAULT NULL COMMENT '同级节点序列',
  `root_flag` int(11) DEFAULT NULL COMMENT '根节点标识',
  `res_level` int(11) DEFAULT NULL COMMENT '层级数',
  `leaf_flag` int(11) DEFAULT NULL COMMENT '叶节点标识',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`res_node_id`,`res_node_sup`,`root_node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/资源与资源关系';

-- ----------------------------
-- Table structure for cip_admin_roles
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_roles`;
CREATE TABLE `cip_admin_roles` (
  `role_id` varchar(30) NOT NULL COMMENT '角色id',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `root_resource_id` varchar(32) DEFAULT NULL COMMENT '资源根节点id',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/角色信息';

-- ----------------------------
-- Table structure for cip_admin_text
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_text`;
CREATE TABLE `cip_admin_text` (
  `lang_type` varchar(6) NOT NULL COMMENT '语言类型',
  `msg_no` int(11) NOT NULL COMMENT '文本id',
  `msg_txt` varchar(100) DEFAULT NULL COMMENT '文本内容',
  `create_time` datetime DEFAULT NULL COMMENT '系统时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`lang_type`,`msg_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/系统文本管理';

-- ----------------------------
-- Table structure for cip_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_user`;
CREATE TABLE `cip_admin_user` (
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名称',
  `start_date` date DEFAULT NULL COMMENT '开始时间',
  `end_date` date DEFAULT NULL COMMENT '结束时间',
  `user_pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `pwd_init_flag` int(1) DEFAULT NULL COMMENT '初始密码标识',
  `user_his1_pwd` varchar(50) DEFAULT NULL COMMENT '历史密码1',
  `user_his2_pwd` varchar(50) DEFAULT NULL COMMENT '历史密码2',
  `user_his3_pwd` varchar(50) DEFAULT NULL COMMENT '历史密码3',
  `pwd_set_time` datetime DEFAULT NULL COMMENT '密码设置时间',
  `pwd_reset_days` int(11) DEFAULT NULL COMMENT '密码重置天数',
  `pwd_reset_flag` int(1) DEFAULT NULL COMMENT '重置密码标识',
  `user_status` int(1) DEFAULT NULL COMMENT '用户状态',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `person_id` varchar(30) DEFAULT NULL COMMENT '使用人id',
  `org_id` varchar(30) DEFAULT NULL COMMENT '使用机构id',
  `position_id` varchar(30) DEFAULT NULL COMMENT '岗位id',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人',
  `lang` varchar(20) DEFAULT NULL COMMENT '语言',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/用户信息';

-- ----------------------------
-- Table structure for cip_admin_user2res
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_user2res`;
CREATE TABLE `cip_admin_user2res` (
  `resource_id` varchar(50) NOT NULL COMMENT '资源id',
  `resource_name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `resource_image` varchar(255) DEFAULT NULL COMMENT '资源背景图',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`resource_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/主页快速工具表';

-- ----------------------------
-- Table structure for cip_admin_user_2_roles
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_user_2_roles`;
CREATE TABLE `cip_admin_user_2_roles` (
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `role_id` varchar(30) NOT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人',
  `default_flag` int(11) DEFAULT NULL COMMENT '默认角色标识',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/用户角色关联';

-- ----------------------------
-- Table structure for cip_admin_user_setting
-- ----------------------------
DROP TABLE IF EXISTS `cip_admin_user_setting`;
CREATE TABLE `cip_admin_user_setting` (
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `user_key` varchar(32) NOT NULL COMMENT '属性id',
  `user_value` varchar(255) DEFAULT NULL COMMENT '属性配置值',
  `remark` varchar(255) DEFAULT NULL COMMENT '属性说明',
  `create_time` datetime DEFAULT NULL COMMENT '系统生成时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`user_id`,`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理/用户信息设置';

-- ----------------------------
-- Table structure for cip_meta_fields
-- ----------------------------
DROP TABLE IF EXISTS `cip_meta_fields`;
CREATE TABLE `cip_meta_fields` (
  `str_id` varchar(32) NOT NULL COMMENT '数据结构id',
  `field_id` varchar(32) NOT NULL COMMENT '字段id',
  `field_memo` varchar(100) DEFAULT NULL COMMENT '字段备注',
  `field_name` varchar(32) DEFAULT NULL COMMENT '字段名称',
  `search_flag` char(1) DEFAULT NULL COMMENT '查询位标识',
  `domain_type` varchar(32) DEFAULT NULL COMMENT '数据域类型',
  `java_type` varchar(50) DEFAULT NULL COMMENT '字段java类型',
  `jdbc_type` varchar(50) DEFAULT NULL COMMENT '字段JDBC类型',
  `field_length` int(11) DEFAULT NULL COMMENT '字段长度',
  `fprecision` int(11) DEFAULT NULL COMMENT '字段浮点长',
  `field_default` varchar(50) DEFAULT NULL COMMENT '默认值',
  `key_flag` char(1) DEFAULT NULL COMMENT 'id标识',
  `null_flag` char(1) DEFAULT NULL COMMENT '空值标识',
  `field_use_type` char(1) DEFAULT NULL COMMENT '字段应用类型',
  `ui_control` varchar(32) DEFAULT NULL COMMENT '字段UI控件',
  `ui_ctrl_clazz` varchar(32) DEFAULT NULL COMMENT 'easy-ui控件',
  `ui_length` int(11) DEFAULT NULL COMMENT 'UI允许长度',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`str_id`,`field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据管理/数据字段定义';

-- ----------------------------
-- Table structure for cip_meta_field_log
-- ----------------------------
DROP TABLE IF EXISTS `cip_meta_field_log`;
CREATE TABLE `cip_meta_field_log` (
  `str_id` varchar(32) NOT NULL COMMENT '数据结构id',
  `field_id` varchar(32) NOT NULL COMMENT '字段id',
  `oper_time` datetime NOT NULL COMMENT '操作时间',
  `op_field` varchar(32) NOT NULL COMMENT '操作字段',
  `op_type` char(1) NOT NULL DEFAULT '' COMMENT '操作类型',
  `old_value` text COMMENT '原始值',
  `new_value` text COMMENT '新值',
  `operator` varchar(11) DEFAULT NULL COMMENT '操作人',
  `op_flag` varchar(1) DEFAULT NULL COMMENT '操作状态',
  PRIMARY KEY (`str_id`,`field_id`,`op_field`,`op_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据管理/数据结构字段变更';

-- ----------------------------
-- Table structure for cip_meta_module
-- ----------------------------
DROP TABLE IF EXISTS `cip_meta_module`;
CREATE TABLE `cip_meta_module` (
  `module_id` varchar(16) NOT NULL COMMENT '模块id',
  `module_name` varchar(20) DEFAULT NULL COMMENT '模块名称',
  `module_memo` varchar(300) DEFAULT NULL COMMENT '模块描述',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据管理/应用模块信息';

-- ----------------------------
-- Table structure for cip_meta_str
-- ----------------------------
DROP TABLE IF EXISTS `cip_meta_str`;
CREATE TABLE `cip_meta_str` (
  `str_id` varchar(32) NOT NULL COMMENT '数据结构id',
  `str_name` varchar(32) DEFAULT NULL COMMENT '数据结构名称',
  `str_memo` varchar(100) DEFAULT NULL COMMENT '数据结构备注',
  `str_type` varchar(6) DEFAULT NULL COMMENT '数据结构类型',
  `str_prefix` varchar(100) DEFAULT NULL COMMENT '结构前缀',
  `str_module` varchar(10) DEFAULT NULL COMMENT '结构模块id',
  `func_set_id` varchar(32) DEFAULT NULL COMMENT '功能集id',
  `ref_str_id` varchar(32) DEFAULT NULL COMMENT '参考结构id',
  `str_data_type` varchar(6) DEFAULT NULL COMMENT '存储数据类型',
  `prd_date` date DEFAULT NULL COMMENT '落地时间',
  `form_template` varchar(32) DEFAULT NULL COMMENT '表单模板',
  `list_template` varchar(32) DEFAULT NULL COMMENT '列表模板',
  `src_sys_id` varchar(6) DEFAULT NULL COMMENT '源系统id',
  `resp_id` varchar(11) DEFAULT NULL COMMENT '责任人',
  `resp_org` varchar(15) DEFAULT NULL COMMENT '责任部门id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`str_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据管理/系统数据结构表';

-- ----------------------------
-- Table structure for cip_meta_str_log
-- ----------------------------
DROP TABLE IF EXISTS `cip_meta_str_log`;
CREATE TABLE `cip_meta_str_log` (
  `str_id` varchar(32) NOT NULL COMMENT '数据结构id',
  `oper_time` datetime NOT NULL COMMENT '操作时间',
  `op_field` varchar(32) NOT NULL COMMENT '操作字段',
  `op_type` char(1) NOT NULL DEFAULT '' COMMENT '操作类型',
  `old_value` text COMMENT '原始值',
  `new_value` text COMMENT '新值',
  `operator` varchar(11) DEFAULT NULL COMMENT '操作人',
  `op_flag` varchar(1) DEFAULT NULL COMMENT '操作状态',
  PRIMARY KEY (`str_id`,`op_field`,`op_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据管理/元数据结构变更';

-- ----------------------------
-- Table structure for cn_compst_scor
-- ----------------------------
DROP TABLE IF EXISTS `cn_compst_scor`;
CREATE TABLE `cn_compst_scor` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计日期',
  `scor` decimal(10,2) DEFAULT NULL COMMENT '评分分数',
  `indust_avg_scor` decimal(10,3) DEFAULT NULL COMMENT '行业平均分',
  `indust_nm` int(2) DEFAULT NULL COMMENT '行业排名',
  `nm_cnt` int(2) DEFAULT NULL COMMENT '名次升降数',
  `entr_pers_cd` int(8) DEFAULT NULL COMMENT '录入人编码',
  `entr_tm` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '录入时间',
  PRIMARY KEY (`stats_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜鸟综合评分表:kangyj:20170726';

-- ----------------------------
-- Table structure for daw_o_bigdata_fb_pepo
-- ----------------------------
DROP TABLE IF EXISTS `daw_o_bigdata_fb_pepo`;
CREATE TABLE `daw_o_bigdata_fb_pepo` (
  `stats_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '统计日期',
  `dbct_cd` int(8) NOT NULL DEFAULT '0' COMMENT '分拨编码',
  `dbct_pepo` int(8) DEFAULT NULL COMMENT '分拨人数',
  PRIMARY KEY (`stats_dt`,`dbct_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分拨人数表:kangyj:20170808';

-- ----------------------------
-- Table structure for dbct_info
-- ----------------------------
DROP TABLE IF EXISTS `dbct_info`;
CREATE TABLE `dbct_info` (
  `dbct_id` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '分拨中心id',
  `dbct_cd` int(8) DEFAULT NULL COMMENT '分拨中心代码',
  `dbct_nm` varchar(20) DEFAULT NULL COMMENT '分拨中心名称',
  `dbct_shrt` varchar(10) DEFAULT NULL COMMENT '分拨中心简称',
  `prov_cd` int(8) DEFAULT NULL COMMENT '省代码',
  `prov_nm` varchar(20) DEFAULT NULL COMMENT '省名称',
  `grtr_dist_cd` varchar(20) DEFAULT NULL COMMENT '大区代码',
  `grtr_dist_nm` varchar(20) DEFAULT NULL COMMENT '大区名称',
  `crt_tm` datetime DEFAULT NULL COMMENT '创建时间',
  `crt_pers` varchar(20) DEFAULT NULL COMMENT '创建人',
  `mod_tm` datetime DEFAULT NULL COMMENT '修改时间',
  `mod_pers` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dbct_id`),
  KEY `dbct_id` (`dbct_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='CREATOR:wangshengtao CREATED_TIME:20170420 COMMENT:分拨中心信息表';

-- ----------------------------
-- Table structure for dbct_pers
-- ----------------------------
DROP TABLE IF EXISTS `dbct_pers`;
CREATE TABLE `dbct_pers` (
  `dbct_cd` int(8) NOT NULL COMMENT '分拨中心代码',
  `sap_oper_j2` int(8) DEFAULT '0' COMMENT 'SAP操作(J2)',
  `sap_oper_k2` int(8) DEFAULT '0' COMMENT 'SAP操作(K2)',
  `un_setl_pers_cnt` int(8) DEFAULT '0' COMMENT '离职未结算人数',
  `pkg_w1` int(8) DEFAULT '0' COMMENT '计重外包(W1)',
  `pkg_w2` int(8) DEFAULT '0' COMMENT '计时外包(W2)',
  `ship_pkg_x2` int(8) DEFAULT '0' COMMENT '计件外包(X2)',
  `temp` int(8) DEFAULT '0' COMMENT '临时工',
  `crt_tm` date NOT NULL COMMENT '创建时间',
  `crt_pers` varchar(20) DEFAULT NULL COMMENT '创建人',
  `mod_tm` datetime DEFAULT NULL COMMENT '修改时间',
  `mod_pers` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dbct_cd`,`crt_tm`),
  KEY `dbct_cd` (`dbct_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CREATOR:wangshengtao CREATED_TIME:20170919 COMMENT:分拨中心人数表';

-- ----------------------------
-- Table structure for dbct_trgt_val_qc_tbl
-- ----------------------------
DROP TABLE IF EXISTS `dbct_trgt_val_qc_tbl`;
CREATE TABLE `dbct_trgt_val_qc_tbl` (
  `pick_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '揽件日期',
  `dbct_cd` int(8) NOT NULL DEFAULT '0' COMMENT '分拨编码',
  `trgt_val` decimal(6,4) DEFAULT NULL COMMENT '目标值',
  `trgt_val_typ` int(2) DEFAULT NULL COMMENT '目标值类型',
  `entr_pers_cd` int(8) DEFAULT NULL COMMENT '录入人编码',
  `entr_tm` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  PRIMARY KEY (`pick_dt`,`dbct_cd`,`entr_tm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分拨质控目标值设置表:kangyj:20170726';

-- ----------------------------
-- Table structure for dbct_trgt_val_tbl
-- ----------------------------
DROP TABLE IF EXISTS `dbct_trgt_val_tbl`;
CREATE TABLE `dbct_trgt_val_tbl` (
  `pick_dt` date NOT NULL DEFAULT '0000-00-00' COMMENT '揽件日期',
  `dbct_cd` int(8) NOT NULL DEFAULT '0' COMMENT '分拨编码',
  `trgt_val` int(11) DEFAULT NULL COMMENT '目标值',
  `entr_pers_cd` int(8) DEFAULT NULL COMMENT '录入人编码',
  `entr_tm` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  PRIMARY KEY (`pick_dt`,`dbct_cd`,`entr_tm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分拨目标值设置表:kangyj:20170726';

-- ----------------------------
-- Table structure for echart_map1_test
-- ----------------------------
DROP TABLE IF EXISTS `echart_map1_test`;
CREATE TABLE `echart_map1_test` (
  `iphone_type` varchar(255) DEFAULT NULL,
  `prov_name` varchar(255) DEFAULT NULL,
  `sale_total` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fisc_tbl
-- ----------------------------
DROP TABLE IF EXISTS `fisc_tbl`;
CREATE TABLE `fisc_tbl` (
  `brch` int(10) NOT NULL COMMENT '网点',
  `fld_loc_cst` decimal(10,4) DEFAULT NULL COMMENT '场地成本',
  `pers_cst` decimal(10,4) DEFAULT NULL COMMENT '人员成本',
  `car_cst` decimal(10,4) DEFAULT NULL COMMENT '车辆成本',
  `pick_incm` decimal(10,4) DEFAULT NULL COMMENT '揽件收入',
  `delv_incm` decimal(10,4) DEFAULT NULL COMMENT '派件收入',
  `pnlt` decimal(10,4) DEFAULT NULL COMMENT '罚款',
  `subd_incm` decimal(10,4) DEFAULT NULL COMMENT '补贴收入',
  `prft` decimal(10,4) DEFAULT NULL COMMENT '利润',
  `pick_tm` date NOT NULL COMMENT '揽件时间',
  PRIMARY KEY (`brch`,`pick_tm`),
  KEY `brch` (`brch`,`pick_tm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务报表';

-- ----------------------------
-- Table structure for fld_loc_eqpt_cst_inf
-- ----------------------------
DROP TABLE IF EXISTS `fld_loc_eqpt_cst_inf`;
CREATE TABLE `fld_loc_eqpt_cst_inf` (
  `brch` bigint(10) NOT NULL,
  `oper_fld_loc` decimal(20,4) DEFAULT NULL COMMENT '操作场地面积',
  `crit_cnt` int(10) DEFAULT NULL COMMENT '标准门店数量',
  `seq_ln_cnt` int(10) DEFAULT NULL COMMENT '流水线数量',
  `prtr_cnt` int(10) DEFAULT NULL COMMENT '便携式打印机数量',
  `fld_loc` decimal(20,4) DEFAULT NULL COMMENT '办公场地面积',
  `crit_tot` decimal(20,4) DEFAULT NULL COMMENT '标准门店总面积',
  `seq_ln_amnt` decimal(20,4) DEFAULT NULL COMMENT '流水线金额',
  `hld_term_cnt` int(10) DEFAULT NULL COMMENT '手持终端数量',
  `mo_amnt` decimal(20,4) DEFAULT NULL COMMENT '月租金',
  `crit_tot_amnt` decimal(20,4) DEFAULT NULL COMMENT '标准门店总租金',
  `cnt` int(10) DEFAULT NULL COMMENT '快手数量',
  PRIMARY KEY (`brch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场地、设备成本';

-- ----------------------------
-- Table structure for home_page
-- ----------------------------
DROP TABLE IF EXISTS `home_page`;
CREATE TABLE `home_page` (
  `fb_code` int(8) NOT NULL COMMENT '分拨中心编号',
  `cy_flag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '参与考核标识1.参与 0,不参与',
  `date_time` date NOT NULL COMMENT '日期',
  `all_collect` bigint(255) NOT NULL COMMENT '揽件总量',
  `order_num` bigint(20) NOT NULL COMMENT '订单量（件）',
  `soa_person_num` int(11) NOT NULL COMMENT 'soa系统人数',
  `send_num` bigint(20) NOT NULL COMMENT '派件量（件）',
  `real_sign` bigint(255) NOT NULL COMMENT '实际签收',
  `goal_sign` bigint(255) NOT NULL COMMENT '核定签收',
  `open_line` int(8) DEFAULT NULL COMMENT '开通线路（条）',
  `dispatch_car` int(8) NOT NULL COMMENT '发车数（辆）',
  `real_ship` bigint(255) NOT NULL COMMENT '实际装载',
  `goal_ship` bigint(255) NOT NULL COMMENT '核定装载',
  PRIMARY KEY (`fb_code`,`date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for newbee_total_remark
-- ----------------------------
DROP TABLE IF EXISTS `newbee_total_remark`;
CREATE TABLE `newbee_total_remark` (
  `rank` int(2) NOT NULL,
  `total_mark` decimal(5,2) NOT NULL,
  `sum_mark` decimal(5,2) NOT NULL,
  `date_time` date NOT NULL,
  PRIMARY KEY (`date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for org_coord
-- ----------------------------
DROP TABLE IF EXISTS `org_coord`;
CREATE TABLE `org_coord` (
  `ORGCODE` varchar(50) NOT NULL COMMENT '分拨编码',
  `ORGNAME` varchar(50) DEFAULT NULL COMMENT '分拨名称',
  `LONGITUDE` varchar(50) DEFAULT NULL COMMENT '坐标经度',
  `LATITUDE` varchar(50) DEFAULT NULL COMMENT '坐标纬度',
  `ORGTYPE` varchar(50) DEFAULT NULL COMMENT '机构类型',
  PRIMARY KEY (`ORGCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构坐标';

-- ----------------------------
-- Table structure for org_inf
-- ----------------------------
DROP TABLE IF EXISTS `org_inf`;
CREATE TABLE `org_inf` (
  `org_cd` int(8) NOT NULL COMMENT '机构编码',
  `org_nm` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `org_typ` smallint(2) NOT NULL DEFAULT '0' COMMENT '机构类型',
  `atrs_typ` smallint(2) DEFAULT NULL COMMENT '航空类别',
  `supr_org_cd` int(8) DEFAULT NULL COMMENT '上级机构编码',
  `supr_org_nm` varchar(50) DEFAULT NULL COMMENT '上级机构名称',
  `mstr_dbct_cd` int(8) DEFAULT NULL COMMENT '主分拨中心编码',
  `mstr_dbct_nm` varchar(50) DEFAULT NULL COMMENT '主分拨中心名称',
  `fisc_dbct_cd` int(8) DEFAULT NULL COMMENT '财务分拨中心编码',
  `fisc_dbct_nm` varchar(50) DEFAULT NULL COMMENT '财务分拨中心名称',
  `cnty_cd` int(8) DEFAULT NULL COMMENT '县编码',
  `cnty_nm` varchar(50) DEFAULT NULL COMMENT '县名称',
  `city_cd` int(8) DEFAULT NULL COMMENT '城市编码',
  `city_nm` varchar(50) DEFAULT NULL COMMENT '城市名称',
  `prov_cd` int(8) DEFAULT NULL COMMENT '省份编码',
  `prov_nm` varchar(50) DEFAULT NULL COMMENT '省份名称',
  `grtr_dist_cd` int(2) DEFAULT NULL COMMENT '大区编码',
  `grtr_dist_nm` varchar(50) DEFAULT NULL COMMENT '大区名称',
  `start_tm` datetime NOT NULL COMMENT '生效时间',
  `end_tm` datetime NOT NULL COMMENT '失效时间',
  `ins_db_tm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入库时间',
  PRIMARY KEY (`start_tm`,`end_tm`,`org_cd`,`org_typ`),
  KEY `org_cd` (`org_cd`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='机构信息表:kangyj:20170505';

-- ----------------------------
-- Table structure for outlets_distribution_monitoring
-- ----------------------------
DROP TABLE IF EXISTS `outlets_distribution_monitoring`;
CREATE TABLE `outlets_distribution_monitoring` (
  `stats_dt` datetime NOT NULL COMMENT '统计日期',
  `dbct_cd` varchar(50) NOT NULL COMMENT '分拨编码',
  `f_un_time` varchar(50) DEFAULT NULL COMMENT '第一票卸车时间',
  `st_sc_time` varchar(50) DEFAULT NULL COMMENT '进站刷卡时间或进站过地磅时间',
  `car_num` int(11) DEFAULT NULL COMMENT '车数量',
  `dist_sc_time` varchar(50) DEFAULT NULL COMMENT '出分拨的装车扫描时间',
  `into_time` varchar(50) DEFAULT NULL COMMENT '进分拨的卸车扫描时间',
  `ope_qua` int(11) DEFAULT NULL COMMENT '操作件量',
  `out_time` varchar(50) DEFAULT NULL COMMENT '出站刷卡时间或出站过地磅时间',
  `last_time` varchar(50) DEFAULT NULL COMMENT '最后一票装车时间',
  `clear_total` int(11) DEFAULT NULL COMMENT '清场票件总量',
  `ti_st` int(11) DEFAULT NULL COMMENT '当天进站票件总量',
  `pick_cnt` int(11) DEFAULT NULL COMMENT '揽件量',
  `ord_qua` int(11) DEFAULT NULL COMMENT '订单量',
  `ha_qua` int(11) DEFAULT NULL COMMENT '交件量',
  `rcv_cnt` int(11) DEFAULT NULL COMMENT '签收票件量',
  `dist_amount` int(11) DEFAULT NULL COMMENT '当天分发量',
  `amount_issue` int(11) DEFAULT NULL COMMENT '中转发出的总量',
  PRIMARY KEY (`stats_dt`,`dbct_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网点/分拨大屏监控';

-- ----------------------------
-- Table structure for pers_cst_inf_tbl
-- ----------------------------
DROP TABLE IF EXISTS `pers_cst_inf_tbl`;
CREATE TABLE `pers_cst_inf_tbl` (
  `brch` bigint(10) NOT NULL COMMENT '网点',
  `typ` int(1) DEFAULT NULL COMMENT '类型(1公司2分部3服务部)',
  `nm` varchar(20) DEFAULT NULL COMMENT '姓名',
  `gendr` int(1) DEFAULT NULL COMMENT '性别(1男2女)',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月',
  `cult_deg` int(1) DEFAULT NULL COMMENT '文化程度(1高中2初中3小学4中专5大专6本科7其他)',
  `pos` varchar(40) DEFAULT NULL COMMENT '岗位',
  `ent_co_tm` datetime DEFAULT NULL COMMENT '加入公司时间',
  `fmly_adds` varchar(40) DEFAULT NULL COMMENT '家庭住址',
  `idcd_cd` varchar(40) NOT NULL DEFAULT '0' COMMENT '身份证号码',
  `tel_cd` bigint(11) DEFAULT NULL COMMENT '电话号码',
  `emp_d` varchar(20) DEFAULT NULL COMMENT '员工ID',
  `salary_form` varchar(40) DEFAULT NULL COMMENT '工资构成',
  `mo_crit` varchar(40) DEFAULT NULL COMMENT '月工资标准',
  `if_insr` varchar(40) DEFAULT NULL COMMENT '是否买保险(如有保存保险类型)',
  PRIMARY KEY (`brch`,`idcd_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员成本';

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sjtj
-- ----------------------------
DROP TABLE IF EXISTS `sjtj`;
CREATE TABLE `sjtj` (
  `brch` int(10) NOT NULL DEFAULT '0' COMMENT '网点',
  `pick_cnt` int(10) DEFAULT NULL COMMENT '揽件量',
  `cfm_rcv_rt` decimal(5,4) DEFAULT NULL COMMENT '签收率',
  `rt` decimal(5,4) DEFAULT NULL COMMENT '客诉率',
  `ppd` decimal(10,4) DEFAULT NULL COMMENT '预付款',
  `unit_tckt_pnlt` decimal(10,2) DEFAULT NULL COMMENT '单票罚款',
  `dt` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`brch`,`dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据统计';

-- ----------------------------
-- Table structure for wdcbxxwh
-- ----------------------------
DROP TABLE IF EXISTS `wdcbxxwh`;
CREATE TABLE `wdcbxxwh` (
  `brch` bigint(10) NOT NULL,
  `place` decimal(10,4) DEFAULT '0.0000' COMMENT '场地',
  `place_price` decimal(10,4) DEFAULT '0.0000' COMMENT '场地成本',
  `lsx_price` decimal(10,4) DEFAULT '0.0000' COMMENT '流水线成本',
  `emp_num` int(10) DEFAULT '0' COMMENT '业务员人数',
  `peo_num` int(10) DEFAULT '0' COMMENT '其他人员人数',
  `rygz` decimal(10,4) DEFAULT '0.0000' COMMENT '人员工资总额',
  `xsc` int(10) DEFAULT '0' COMMENT '厢式车',
  `mbc` int(10) DEFAULT '0' COMMENT '面包车',
  `slddc` int(10) DEFAULT '0' COMMENT '三轮车/电动车',
  `clyycb` decimal(10,4) DEFAULT '0.0000' COMMENT '车辆运营成本',
  `wh_date` date DEFAULT NULL,
  `chk_stat` int(1) DEFAULT '1' COMMENT '审核状态(1未审核2审核通过3驳回)',
  `dismisd_rsn` varchar(100) DEFAULT NULL COMMENT '驳回原因',
  `contract` varchar(50) DEFAULT NULL COMMENT '合同信息',
  PRIMARY KEY (`brch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网点成本信息维护';

-- ----------------------------
-- Table structure for wdyj_wd
-- ----------------------------
DROP TABLE IF EXISTS `wdyj_wd`;
CREATE TABLE `wdyj_wd` (
  `brch` bigint(10) NOT NULL,
  `pick_cnt` bigint(20) DEFAULT '0' COMMENT '揽件量',
  `send_cnt` bigint(20) DEFAULT '0' COMMENT '派件量',
  `cfm_rate` decimal(5,4) DEFAULT NULL COMMENT '签收率',
  `cust_rate` decimal(5,4) DEFAULT NULL COMMENT '客诉率',
  `yfk_qk` int(10) DEFAULT '0' COMMENT '预付款欠款',
  `dpfk` int(10) DEFAULT NULL,
  `rq` date NOT NULL,
  PRIMARY KEY (`rq`,`brch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网点预警';

-- ----------------------------
-- Table structure for wstwst
-- ----------------------------
DROP TABLE IF EXISTS `wstwst`;
CREATE TABLE `wstwst` (
  `FunctionID` int(11) NOT NULL AUTO_INCREMENT,
  `FunctionName` varchar(30) NOT NULL DEFAULT '',
  `FunctionType` int(11) NOT NULL DEFAULT '1',
  `CTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UTIME` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FunctionID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydbi_business_target
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_business_target`;
CREATE TABLE `ydbi_business_target` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `actual_values` decimal(12,2) DEFAULT NULL COMMENT '实际值',
  `actual_residual_quantity` decimal(12,2) DEFAULT NULL COMMENT '较上日实际值的差量',
  `residual_quantity` decimal(12,2) DEFAULT NULL COMMENT '差量',
  `residual_quantity_percent` decimal(12,2) DEFAULT NULL COMMENT '差量百分比',
  `period_finish_percent` decimal(12,2) DEFAULT NULL COMMENT '上期完成率',
  `finish_percent` decimal(12,2) DEFAULT NULL COMMENT '完成率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `volatility_values` decimal(12,2) DEFAULT NULL COMMENT '波动率',
  `superior_org_name` varchar(50) DEFAULT NULL COMMENT '上级机构',
  `week_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `month_even` decimal(12,2) DEFAULT NULL COMMENT '月均',
  `week_even` decimal(12,2) DEFAULT NULL COMMENT '周均',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='要点/业务目标值';

-- ----------------------------
-- Table structure for ydbi_business_target_month
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_business_target_month`;
CREATE TABLE `ydbi_business_target_month` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `actual_values` decimal(12,2) DEFAULT NULL COMMENT '实际值',
  `actual_residual_quantity` decimal(12,2) DEFAULT NULL COMMENT '较上月实际值的差量',
  `residual_quantity` decimal(12,2) DEFAULT NULL COMMENT '差量',
  `residual_quantity_percent` decimal(12,2) DEFAULT NULL COMMENT '差量百分比',
  `period_finish_percent` decimal(12,2) DEFAULT NULL COMMENT '上期完成率',
  `finish_percent` decimal(12,2) DEFAULT NULL COMMENT '完成率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `volatility_values` decimal(12,2) DEFAULT NULL COMMENT '波动率',
  `superior_org_name` varchar(50) DEFAULT NULL COMMENT '上级机构',
  `week_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `month_even` decimal(12,2) DEFAULT NULL COMMENT '月均',
  `week_even` decimal(12,2) DEFAULT NULL COMMENT '周均',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='要点/月趋势表';

-- ----------------------------
-- Table structure for ydbi_business_target_quarter
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_business_target_quarter`;
CREATE TABLE `ydbi_business_target_quarter` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `quarter_time` varchar(50) NOT NULL COMMENT '季度',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `actual_values` decimal(12,2) DEFAULT NULL COMMENT '实际值',
  `actual_residual_quantity` decimal(12,2) DEFAULT NULL COMMENT '较上季度实际值的差量',
  `residual_quantity` decimal(12,2) DEFAULT NULL COMMENT '差量',
  `residual_quantity_percent` decimal(12,2) DEFAULT NULL COMMENT '差量百分比',
  `period_finish_percent` decimal(12,2) DEFAULT NULL COMMENT '上期完成率',
  `finish_percent` decimal(12,2) DEFAULT NULL COMMENT '完成率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `volatility_values` decimal(12,2) DEFAULT NULL COMMENT '波动率',
  `superior_org_name` varchar(50) DEFAULT NULL COMMENT '上级机构',
  `week_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `month_even` decimal(12,2) DEFAULT NULL COMMENT '月均',
  `week_even` decimal(12,2) DEFAULT NULL COMMENT '周均',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='要点/季度趋势表';

-- ----------------------------
-- Table structure for ydbi_business_target_week
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_business_target_week`;
CREATE TABLE `ydbi_business_target_week` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `actual_values` decimal(12,2) DEFAULT NULL COMMENT '实际值',
  `actual_residual_quantity` decimal(12,2) DEFAULT NULL COMMENT '较上周实际值的差量',
  `residual_quantity` decimal(12,2) DEFAULT NULL COMMENT '差量',
  `residual_quantity_percent` decimal(12,2) DEFAULT NULL COMMENT '差量百分比',
  `period_finish_percent` decimal(12,2) DEFAULT NULL COMMENT '上期完成率',
  `finish_percent` decimal(12,2) DEFAULT NULL COMMENT '完成率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `volatility_values` decimal(12,2) DEFAULT NULL COMMENT '波动率',
  `superior_org_name` varchar(50) DEFAULT NULL COMMENT '上级机构',
  `week_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `month_even` decimal(12,2) DEFAULT NULL COMMENT '月均',
  `week_even` decimal(12,2) DEFAULT NULL COMMENT '周均',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='要点/周趋势表';

-- ----------------------------
-- Table structure for ydbi_delay_rate
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_delay_rate`;
CREATE TABLE `ydbi_delay_rate` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `delay_num` decimal(12,2) DEFAULT NULL COMMENT '延误发出的票件数量',
  `total_num` decimal(12,2) DEFAULT NULL COMMENT '总发出的票件数量',
  `delay_rate` decimal(12,2) DEFAULT NULL COMMENT '延误率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `week_with_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `day_time` decimal(12,2) DEFAULT NULL COMMENT '上一日',
  `week_delay_rate` decimal(12,2) DEFAULT NULL COMMENT '周延误率',
  `month_delay_rate` decimal(12,2) DEFAULT NULL COMMENT '月延误率',
  `week_delay_num` decimal(12,2) DEFAULT NULL COMMENT '周日均延误量',
  `month_delay_num` decimal(12,2) DEFAULT NULL COMMENT '月日均延误量',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `week_same_term` decimal(12,2) DEFAULT NULL COMMENT '周同期',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控/延误率';

-- ----------------------------
-- Table structure for ydbi_loss_rate
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_loss_rate`;
CREATE TABLE `ydbi_loss_rate` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `loss_num` decimal(12,2) DEFAULT NULL COMMENT '遗失的票件数量',
  `total_num` decimal(12,2) DEFAULT NULL COMMENT '总发出的票件数量',
  `loss_rate` decimal(12,2) DEFAULT NULL COMMENT '遗失率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `week_with_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `day_time` decimal(12,2) DEFAULT NULL COMMENT '上一日',
  `week_loss_rate` decimal(12,2) DEFAULT NULL COMMENT '周遗失率',
  `month_loss_rate` decimal(12,2) DEFAULT NULL COMMENT '月遗失率',
  `week_loss_num` decimal(12,2) DEFAULT NULL COMMENT '周日均遗失量',
  `month_loss_num` decimal(12,2) DEFAULT NULL COMMENT '月日均遗失量',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `week_same_term` decimal(12,2) DEFAULT NULL COMMENT '周同期',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控/遗失率';

-- ----------------------------
-- Table structure for ydbi_operation_amount
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_operation_amount`;
CREATE TABLE `ydbi_operation_amount` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `day_amount` decimal(12,2) DEFAULT NULL COMMENT '日均操作量',
  `day_per_amount` decimal(12,2) DEFAULT NULL COMMENT '日人均件量',
  `national_per_amount` decimal(12,2) DEFAULT NULL COMMENT '全国人均操作量',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `week_with_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `below_national_avg` decimal(12,2) DEFAULT NULL COMMENT '低于全国平均',
  `below_national_percent` decimal(12,2) DEFAULT NULL COMMENT '占总分拨%',
  `more_than` decimal(12,2) DEFAULT NULL COMMENT '超过1000',
  `more_than_percent` decimal(12,2) DEFAULT NULL COMMENT '占总分拨%',
  `more_than_avg` decimal(12,2) DEFAULT NULL COMMENT '超过全国平均',
  `more_avg_percent` decimal(12,2) DEFAULT NULL COMMENT '占总分拨%',
  `week_avg` decimal(12,2) DEFAULT NULL COMMENT '周日均数量',
  `month_avg` decimal(12,2) DEFAULT NULL COMMENT '月日均数量',
  `amount_peak` decimal(12,2) DEFAULT NULL COMMENT '峰值',
  `week_percent` decimal(12,2) DEFAULT NULL COMMENT '周环比',
  `week_sametime` decimal(12,2) DEFAULT NULL COMMENT '周同期',
  `people_num` decimal(12,2) DEFAULT NULL COMMENT '人数',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控/人均操作件量';

-- ----------------------------
-- Table structure for ydbi_retention_rate
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_retention_rate`;
CREATE TABLE `ydbi_retention_rate` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `Retention_num` decimal(12,2) DEFAULT NULL COMMENT '滞留的票件数量',
  `total_num` decimal(12,2) DEFAULT NULL COMMENT '总发出的票件数量',
  `Retention_rate` decimal(12,2) DEFAULT NULL COMMENT '滞留率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `week_with_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `day_time` decimal(12,2) DEFAULT NULL COMMENT '上一日',
  `week_Retention_rate` decimal(12,2) DEFAULT NULL COMMENT '周滞留率',
  `month_Retention_rate` decimal(12,2) DEFAULT NULL COMMENT '月滞留率',
  `week_Retention_num` decimal(12,2) DEFAULT NULL COMMENT '周日均滞留量',
  `month_Retention_num` decimal(12,2) DEFAULT NULL COMMENT '月日均滞留量',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `week_same_term` decimal(12,2) DEFAULT NULL COMMENT '周同期',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控/滞留率';

-- ----------------------------
-- Table structure for ydbi_trend_tab
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_trend_tab`;
CREATE TABLE `ydbi_trend_tab` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `week_num` decimal(12,2) DEFAULT NULL COMMENT '周量',
  `week_rate` decimal(12,2) DEFAULT NULL COMMENT '周利率',
  `week_day` decimal(12,2) DEFAULT NULL COMMENT '周日比',
  `total_num` decimal(12,2) DEFAULT NULL COMMENT '周目标值',
  `month_num` decimal(12,2) DEFAULT NULL COMMENT '月量',
  `month_avg` decimal(12,2) DEFAULT NULL COMMENT '月均期趋势',
  `month_rate` decimal(12,2) DEFAULT NULL COMMENT '月利率',
  `month_day` decimal(12,2) DEFAULT NULL COMMENT '月日比',
  `month_goal` decimal(12,2) DEFAULT NULL COMMENT '月目标值',
  `quarte_num` decimal(12,2) DEFAULT NULL COMMENT '季度量',
  `quarte_time` decimal(12,2) DEFAULT NULL COMMENT '季度同期趋势',
  `quarte_rate` decimal(12,2) DEFAULT NULL COMMENT '季度利率',
  `quarte_day` decimal(12,2) DEFAULT NULL COMMENT '季度日比',
  `quarte_goal` decimal(12,2) DEFAULT NULL COMMENT '季度目标值',
  `rate_flag` decimal(12,2) DEFAULT NULL COMMENT '标识1-6代表各个利率的数据',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控/趋势表';

-- ----------------------------
-- Table structure for ydbi_wrongpackage_rate
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_wrongpackage_rate`;
CREATE TABLE `ydbi_wrongpackage_rate` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `wrongpackage_num` decimal(12,2) DEFAULT NULL COMMENT '集错包的票件数量',
  `total_num` decimal(12,2) DEFAULT NULL COMMENT '总发出的票件数量',
  `wrongpackage_rate` decimal(12,2) DEFAULT NULL COMMENT '集错包率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `week_with_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `day_time` decimal(12,2) DEFAULT NULL COMMENT '上一日',
  `week_wrongpackage_rate` decimal(12,2) DEFAULT NULL COMMENT '周集错包率',
  `month_wrongpackage_rate` decimal(12,2) DEFAULT NULL COMMENT '月集错包率',
  `week_wrongpackage_num` decimal(12,2) DEFAULT NULL COMMENT '周日均集错包量',
  `month_wrongpackage_num` decimal(12,2) DEFAULT NULL COMMENT '月日均集错包量',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `week_same_term` decimal(12,2) DEFAULT NULL COMMENT '周同期',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控/集错包率';

-- ----------------------------
-- Table structure for ydbi_wrongpoints_rate
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_wrongpoints_rate`;
CREATE TABLE `ydbi_wrongpoints_rate` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `wrongpoints_num` decimal(12,2) DEFAULT NULL COMMENT '错分发出的票件数量',
  `total_num` decimal(12,2) DEFAULT NULL COMMENT '总发出的票件数量',
  `wrongpoints_rate` decimal(12,2) DEFAULT NULL COMMENT '错分率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `week_with_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `day_time` decimal(12,2) DEFAULT NULL COMMENT '上一日',
  `week_wrongpoints_rate` decimal(12,2) DEFAULT NULL COMMENT '周错分率',
  `month_wrongpoints_rate` decimal(12,2) DEFAULT NULL COMMENT '月错分率',
  `week_wrongpoints_num` decimal(12,2) DEFAULT NULL COMMENT '周日均错分量',
  `month_wrongpoints_num` decimal(12,2) DEFAULT NULL COMMENT '月日均错分量',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `week_same_term` decimal(12,2) DEFAULT NULL COMMENT '周同期',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控/错分率';

-- ----------------------------
-- Table structure for ydbi_wrong_rate
-- ----------------------------
DROP TABLE IF EXISTS `ydbi_wrong_rate`;
CREATE TABLE `ydbi_wrong_rate` (
  `data_date` date NOT NULL COMMENT '日期',
  `org_id` varchar(50) NOT NULL COMMENT '机构id',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `wrong_num` decimal(12,2) DEFAULT NULL COMMENT '错发发出的票件数量',
  `total_num` decimal(12,2) DEFAULT NULL COMMENT '总发出的票件数量',
  `wrong_rate` decimal(12,2) DEFAULT NULL COMMENT '错发率',
  `day_percent` decimal(12,2) DEFAULT NULL COMMENT '日环比',
  `week_with_percent` decimal(12,2) DEFAULT NULL COMMENT '周同比',
  `day_time` decimal(12,2) DEFAULT NULL COMMENT '上一日',
  `week_wrong_rate` decimal(12,2) DEFAULT NULL COMMENT '周错发率',
  `month_wrong_rate` decimal(12,2) DEFAULT NULL COMMENT '月错发率',
  `week_wrong_num` decimal(12,2) DEFAULT NULL COMMENT '周日均错发量',
  `month_wrong_num` decimal(12,2) DEFAULT NULL COMMENT '月日均错发量',
  `goal_values` decimal(12,2) DEFAULT NULL COMMENT '目标值',
  `week_same_term` decimal(12,2) DEFAULT NULL COMMENT '周同期',
  PRIMARY KEY (`data_date`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控/错发率';

-- ----------------------------
-- Table structure for ydfbop_base_staf
-- ----------------------------
DROP TABLE IF EXISTS `ydfbop_base_staf`;
CREATE TABLE `ydfbop_base_staf` (
  `fb_code` int(8) NOT NULL COMMENT '分拨中心',
  `staf_code` varchar(8) NOT NULL COMMENT '员工编号',
  `staf_type` tinyint(2) DEFAULT NULL COMMENT '人员类型',
  `staf_name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `staf_id` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `staf_age` tinyint(2) DEFAULT NULL COMMENT '年龄',
  `staf_sex` tinyint(2) DEFAULT NULL COMMENT '性别',
  `staf_edu` tinyint(2) DEFAULT NULL COMMENT '学历',
  `staf_status` tinyint(2) DEFAULT NULL COMMENT '人员状态',
  `staf_no` bigint(16) NOT NULL DEFAULT '0',
  `staf_scan` tinyint(2) DEFAULT '0' COMMENT '扫描枪权限',
  `set_peop_num` varchar(32) DEFAULT NULL COMMENT '创建人工号',
  `set_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updt_peop_num` varchar(32) DEFAULT NULL COMMENT '修改人工号',
  `updt_time` datetime DEFAULT NULL COMMENT '修改时间',
  `staf_birt` date DEFAULT NULL COMMENT '出生日期',
  `belong_area` int(8) DEFAULT NULL COMMENT '归属片区',
  `regular_dis_mon` decimal(8,2) DEFAULT NULL COMMENT '固定分配金额',
  `suggest_ability` double DEFAULT NULL COMMENT '建议能力系数',
  PRIMARY KEY (`fb_code`,`staf_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础维护/员工信息维护';

-- ----------------------------
-- Table structure for ydfbop_base_staf_cent_codemap
-- ----------------------------
DROP TABLE IF EXISTS `ydfbop_base_staf_cent_codemap`;
CREATE TABLE `ydfbop_base_staf_cent_codemap` (
  `administrationcode` varchar(32) NOT NULL COMMENT '行政机构编码',
  `administrationname` varchar(124) DEFAULT NULL COMMENT '行政机构名称',
  `businesscode` varchar(32) DEFAULT NULL COMMENT '业务机构编码',
  `businessname` varchar(124) DEFAULT NULL COMMENT '业务机构名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`administrationcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='韵达数据/韵达行政业务编码表';

-- ----------------------------
-- Table structure for ydfbop_base_staf_emps
-- ----------------------------
DROP TABLE IF EXISTS `ydfbop_base_staf_emps`;
CREATE TABLE `ydfbop_base_staf_emps` (
  `birthdate` date DEFAULT NULL COMMENT '出生日期',
  `bqq` varchar(50) DEFAULT NULL COMMENT 'BQQ',
  `cardno` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `cardtype` varchar(255) DEFAULT NULL COMMENT '证件类型',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `degree` varchar(255) DEFAULT NULL COMMENT '职级',
  `empcode` varchar(30) DEFAULT NULL COMMENT '员工编码',
  `empid` bigint(12) NOT NULL COMMENT '员工ID',
  `empname` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `empstatus` varchar(255) DEFAULT NULL COMMENT '员工状态',
  `emp_group` varchar(20) DEFAULT NULL COMMENT '员工组',
  `emp_group_name` varchar(128) DEFAULT NULL COMMENT '员工组的名称',
  `faxno` varchar(128) DEFAULT NULL COMMENT '传真号码',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `haddress` varchar(128) DEFAULT NULL COMMENT '个人地址',
  `hasbqq` varchar(1) DEFAULT NULL COMMENT '是否有BQQ',
  `hasmobile` varchar(1) DEFAULT NULL COMMENT '是否有手机号码',
  `htel` varchar(30) DEFAULT NULL COMMENT '个人电话',
  `hzipcode` varchar(10) DEFAULT NULL COMMENT '个人邮编',
  `indate` date DEFAULT NULL COMMENT '入职日期',
  `ismsgwarn` varchar(32) DEFAULT NULL COMMENT '短信提醒',
  `isrtx` varchar(1) DEFAULT NULL COMMENT '是否导入RTX',
  `lastmodytime` datetime DEFAULT NULL COMMENT '最后修改时间',
  `major` bigint(12) DEFAULT NULL COMMENT '上级人员ID',
  `majorcode` varchar(30) DEFAULT NULL COMMENT '上级主管',
  `mobileno` varchar(50) DEFAULT NULL COMMENT '手机',
  `msn` varchar(16) DEFAULT NULL COMMENT 'MSN',
  `oaddress` varchar(255) DEFAULT NULL COMMENT '办公地址',
  `oemail` varchar(128) DEFAULT NULL COMMENT '办公email',
  `oldempcode` varchar(50) DEFAULT NULL COMMENT '旧的员工代码',
  `operater_status` varchar(255) DEFAULT NULL COMMENT '状态',
  `operatorid` bigint(18) DEFAULT NULL COMMENT '操作员ID',
  `orgid` bigint(12) DEFAULT NULL COMMENT '机构编号',
  `orgidlist` varchar(128) DEFAULT NULL COMMENT '管理机构列表',
  `otel` varchar(30) DEFAULT NULL COMMENT '办公电话',
  `outdate` date DEFAULT NULL COMMENT '离职日期',
  `ozipcode` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `party` varchar(255) DEFAULT NULL COMMENT '政治面貌',
  `pemail` varchar(128) DEFAULT NULL COMMENT '个人email',
  `position` int(10) DEFAULT NULL COMMENT '岗位ID',
  `realname` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `regdate` date DEFAULT NULL COMMENT '注册日期',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `soundorder` varchar(32) DEFAULT NULL COMMENT '拼音排序',
  `specialty` varchar(1024) DEFAULT NULL COMMENT '特长',
  `tempgangwei` varchar(100) DEFAULT NULL COMMENT '临时岗位',
  `userid` varchar(30) DEFAULT NULL COMMENT '登录帐号',
  `userpass` varchar(100) DEFAULT NULL COMMENT '密码',
  `wdaccount` varchar(64) DEFAULT NULL COMMENT '网点个人账户',
  `workexp` varchar(512) DEFAULT NULL COMMENT '工作经历',
  `yd_account` varchar(128) DEFAULT NULL COMMENT '户口',
  `yd_address` varchar(512) DEFAULT NULL COMMENT '现居住地',
  `yd_assistant` varchar(128) DEFAULT NULL COMMENT '助理',
  `yd_birthplace` varchar(128) DEFAULT NULL COMMENT '籍贯',
  `yd_contractdate` date DEFAULT NULL COMMENT '合同签订日期',
  `yd_contractdateend` date DEFAULT NULL COMMENT '合同续签终止日期',
  `yd_degree` varchar(128) DEFAULT NULL COMMENT '学位',
  `yd_duties` varchar(128) DEFAULT NULL COMMENT '职务',
  `yd_dutiestype` varchar(128) DEFAULT NULL COMMENT '职务类型',
  `yd_education` varchar(128) DEFAULT NULL COMMENT '学历',
  `yd_emergency` varchar(128) DEFAULT NULL COMMENT '紧急联系人',
  `yd_emergencytel` varchar(128) DEFAULT NULL COMMENT '紧急联系人电话',
  `yd_ghhy` varchar(128) DEFAULT NULL COMMENT '工会会员',
  `yd_healthstate` varchar(128) DEFAULT NULL COMMENT '健康状况',
  `yd_introducer` varchar(128) DEFAULT NULL COMMENT '招聘渠道或介绍人',
  `yd_jobtitle` varchar(128) DEFAULT NULL COMMENT '职称',
  `yd_marriage` varchar(128) DEFAULT NULL COMMENT '婚姻状况',
  `yd_master` int(10) DEFAULT NULL COMMENT '辅导员empid',
  `yd_mastercode` varchar(32) DEFAULT NULL COMMENT '辅导员userid',
  `yd_mastername` varchar(32) DEFAULT NULL COMMENT '辅导员姓名',
  `yd_national` varchar(128) DEFAULT NULL COMMENT '民族',
  `yd_office` varchar(128) DEFAULT NULL COMMENT '办公室',
  `yd_otel` varchar(128) DEFAULT NULL COMMENT '其他电话',
  `yd_photopath` varchar(1000) DEFAULT NULL COMMENT '大头贴地址',
  `yd_professional` varchar(128) DEFAULT NULL COMMENT '专业',
  `yd_rddate` date DEFAULT NULL COMMENT '入党日期',
  `yd_rtdate` date DEFAULT NULL COMMENT '入团日期',
  `yd_schools` varchar(512) DEFAULT NULL COMMENT '毕业院校',
  `yd_securitylevel` varchar(128) DEFAULT NULL COMMENT '安全级别',
  `yd_xindeng` varchar(128) DEFAULT NULL COMMENT '薪等',
  `yd_xinji` varchar(128) DEFAULT NULL COMMENT '薪级',
  `yd_xinzi` varchar(128) DEFAULT NULL COMMENT '薪资',
  `yd_zzzno` varchar(128) DEFAULT NULL COMMENT '暂住证号码',
  `ys_syslanguage` varchar(128) DEFAULT NULL COMMENT '系统语言',
  `zkostl` varchar(64) DEFAULT NULL COMMENT '成本中心',
  `zkostl_name` varchar(128) DEFAULT NULL COMMENT '成本中心描述',
  `zzhr_nbtjr` varchar(128) DEFAULT NULL COMMENT '内部推荐人',
  `belong_area` int(8) DEFAULT NULL COMMENT '归属片区',
  `regular_dis_mon` decimal(8,2) DEFAULT NULL COMMENT '固定分配金额',
  `suggest_ability` double DEFAULT NULL COMMENT '建议能力系数',
  PRIMARY KEY (`empid`),
  KEY `index_empcode` (`empcode`),
  KEY `index_orgid_status` (`orgid`,`empstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础维护/员工信息对接';

-- ----------------------------
-- Table structure for ydfbop_base_staf_orgs
-- ----------------------------
DROP TABLE IF EXISTS `ydfbop_base_staf_orgs`;
CREATE TABLE `ydfbop_base_staf_orgs` (
  `adid` varchar(128) DEFAULT NULL COMMENT '在EXCHANGE中组织机构编号',
  `area` varchar(30) DEFAULT NULL COMMENT '所属地域',
  `bcode` varchar(32) DEFAULT NULL COMMENT '业务系统编码',
  `belongtranscode` varchar(64) DEFAULT NULL COMMENT '所属中转站编码',
  `belongtransid` int(10) DEFAULT NULL COMMENT '所属中转站ID',
  `belongtransname` varchar(64) DEFAULT NULL COMMENT '所属中转站',
  `branchtype` varchar(128) DEFAULT NULL COMMENT '网点类别',
  `companyid` int(10) DEFAULT NULL COMMENT '单位ID 为没有单位',
  `companyname` varchar(124) DEFAULT NULL COMMENT '单位/公司名称 0为没有单位',
  `companyseq` varchar(512) DEFAULT NULL COMMENT '单位SEQ 为没有单位',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `depart_charge` varchar(50) DEFAULT NULL COMMENT '部门费用',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮件',
  `enddate` date DEFAULT NULL COMMENT '失效日期',
  `hr_range` varchar(64) DEFAULT NULL COMMENT '公司人事范围',
  `isleaf` varchar(1) DEFAULT NULL COMMENT '是否叶子节点',
  `lastupdate` datetime DEFAULT NULL COMMENT '最近更新时间',
  `linkman` varchar(30) DEFAULT NULL COMMENT '联系人',
  `linktel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `managerid` int(10) DEFAULT NULL COMMENT '机构主管人员',
  `manaposition` int(10) DEFAULT NULL COMMENT '机构主管岗位',
  `orgaddr` varchar(256) DEFAULT NULL COMMENT '机构地址',
  `orgcode` varchar(32) NOT NULL COMMENT '机构代码',
  `orgdegree` varchar(255) DEFAULT NULL COMMENT '机构等级',
  `orgfullname` varchar(2000) DEFAULT NULL COMMENT '机构全名称',
  `orgid` bigint(12) NOT NULL COMMENT '机构编号',
  `orglevel` int(9) DEFAULT NULL COMMENT '机构层次',
  `orgmanager` varchar(128) DEFAULT NULL COMMENT '机构管理员',
  `orgname` varchar(124) DEFAULT NULL COMMENT '机构名称',
  `orgnamefull` varchar(248) DEFAULT NULL COMMENT '机构名称全称',
  `orgseq` varchar(512) DEFAULT NULL COMMENT '机构序列',
  `orgtype` varchar(12) DEFAULT NULL COMMENT '机构类型',
  `parentadid` varchar(128) DEFAULT NULL COMMENT '在EXCHANGE中父组织机构编号',
  `parentorgid` int(10) DEFAULT NULL COMMENT '父机构编号',
  `parentrtxid` int(9) DEFAULT NULL COMMENT '在RTX中父组织机构编号',
  `qylx` varchar(2) DEFAULT NULL COMMENT '机构对应的区域类型',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `rtxid` int(9) DEFAULT NULL COMMENT '在RTX中组织机构编号',
  `salary_range` varchar(64) DEFAULT NULL COMMENT '单位薪资范围',
  `sortno` int(10) DEFAULT NULL COMMENT '排列顺序编号',
  `startdate` date DEFAULT NULL COMMENT '生效日期',
  `status` varchar(255) DEFAULT NULL COMMENT '机构状态',
  `subcount` int(10) DEFAULT NULL COMMENT '子节点数',
  `updator` int(10) DEFAULT NULL COMMENT '最近更新人员',
  `wdstatus` varchar(32) DEFAULT NULL COMMENT '网点状态',
  `weburl` varchar(512) DEFAULT NULL COMMENT '网站地址',
  `zipcode` varchar(10) DEFAULT NULL COMMENT '邮编',
  `zkostl` varchar(64) DEFAULT NULL COMMENT '成本中心',
  `zkostl_name` varchar(128) DEFAULT NULL COMMENT '成本中心描述',
  PRIMARY KEY (`orgid`),
  UNIQUE KEY `index1` (`orgid`) USING BTREE,
  UNIQUE KEY `orgcode` (`orgcode`) USING BTREE,
  KEY `parentorgid` (`parentorgid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='韵达数据/韵达机构信息';

-- ----------------------------
-- Table structure for ys_car_line
-- ----------------------------
DROP TABLE IF EXISTS `ys_car_line`;
CREATE TABLE `ys_car_line` (
  `date_time` date DEFAULT NULL COMMENT '日期',
  `start_dot` varchar(255) DEFAULT NULL COMMENT '起始点',
  `end_dot` varchar(255) DEFAULT NULL COMMENT '终点',
  `car_line` varchar(255) DEFAULT NULL COMMENT '车线条数',
  `iod_flag` tinyint(2) DEFAULT NULL COMMENT '新增或取消标识1,增0,消'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ys_delay_rate
-- ----------------------------
DROP TABLE IF EXISTS `ys_delay_rate`;
CREATE TABLE `ys_delay_rate` (
  `fb_code` int(8) DEFAULT NULL COMMENT '分拨中心代码',
  `date_time` date DEFAULT NULL COMMENT '日期',
  `ex_arr` int(8) DEFAULT NULL COMMENT '预期到达',
  `goal_arr` int(8) DEFAULT NULL COMMENT '实际到达'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ys_late_car_rate
-- ----------------------------
DROP TABLE IF EXISTS `ys_late_car_rate`;
CREATE TABLE `ys_late_car_rate` (
  `fb_code` int(8) NOT NULL COMMENT '分拨中心代号',
  `ex_send_car` int(8) DEFAULT NULL COMMENT '预期发车',
  `real_send_car` int(8) DEFAULT NULL COMMENT '实际发车',
  `date_time` date NOT NULL COMMENT '日期',
  PRIMARY KEY (`fb_code`,`date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ys_load_rate
-- ----------------------------
DROP TABLE IF EXISTS `ys_load_rate`;
CREATE TABLE `ys_load_rate` (
  `fb_code` int(8) NOT NULL COMMENT '分拨中心代号',
  `zb_goal` int(8) DEFAULT NULL COMMENT '正班车预期装载',
  `zb_real` int(8) DEFAULT NULL COMMENT '正班车实际装载',
  `kb_goal` int(8) DEFAULT NULL COMMENT '卡班车预期装载',
  `kb_real` int(8) DEFAULT NULL COMMENT '卡班车实际装载',
  `date_time` date NOT NULL COMMENT '日期',
  PRIMARY KEY (`fb_code`,`date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ys_right_unload_rate
-- ----------------------------
DROP TABLE IF EXISTS `ys_right_unload_rate`;
CREATE TABLE `ys_right_unload_rate` (
  `fb_code` int(8) NOT NULL COMMENT '分拨中心代码',
  `arr_car` int(8) DEFAULT NULL COMMENT '总车',
  `arr_right_unload` int(8) DEFAULT NULL COMMENT '到了就卸车',
  `date_time` date NOT NULL COMMENT '日期',
  PRIMARY KEY (`date_time`,`fb_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ys_send_car
-- ----------------------------
DROP TABLE IF EXISTS `ys_send_car`;
CREATE TABLE `ys_send_car` (
  `zbc_num` int(8) DEFAULT NULL COMMENT '正班车数量',
  `kbc_num` int(8) DEFAULT NULL COMMENT '卡班车数量',
  `fb_code` int(8) NOT NULL COMMENT '分拨中心编号',
  `date_time` date NOT NULL COMMENT '时间',
  PRIMARY KEY (`fb_code`,`date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for cip_admin_access_ctrl_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_access_ctrl_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_access_ctrl_v` AS select `m`.`sys_id` AS `sys_id`,`m`.`access_flag` AS `access_flag`,`m`.`access_type` AS `access_type`,`m`.`access_ip` AS `access_ip`,`m`.`auth_code` AS `auth_code`,`m`.`other_params` AS `other_params`,`m`.`remark` AS `remark`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`access_flag`.`code_name` AS `access_flag_name`,`access_type`.`code_name` AS `access_type_name` from ((`cip_admin_access_ctrl` `m` left join `cip_admin_codes` `access_flag` on(((`m`.`access_flag` = `access_flag`.`code_type`) and (`access_flag`.`domain_id` = 'access_flag')))) left join `cip_admin_codes` `access_type` on(((`m`.`access_type` = `access_type`.`code_type`) and (`access_type`.`domain_id` = 'access_type')))) ;

-- ----------------------------
-- View structure for cip_admin_access_rel_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_access_rel_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_access_rel_v` AS select `m`.`sys_id` AS `sys_id`,`m`.`resource_id` AS `resource_id`,`m`.`create_time` AS `create_time`,`m`.`operator` AS `operator` from `cip_admin_access_rel` `m` ;

-- ----------------------------
-- View structure for cip_admin_access_res_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_access_res_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_access_res_v` AS select `m`.`resource_id` AS `resource_id`,`m`.`access_flag` AS `access_flag`,`m`.`resource_name` AS `resource_name`,`m`.`resource_desc` AS `resource_desc`,`m`.`sys_uri` AS `sys_uri`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`m`.`icon_id` AS `icon_id`,`access_flag`.`code_name` AS `access_flag_name` from (`cip_admin_access_res` `m` left join `cip_admin_codes` `access_flag` on(((`m`.`access_flag` = `access_flag`.`code_type`) and (`access_flag`.`domain_id` = 'access_flag')))) ;

-- ----------------------------
-- View structure for cip_admin_auth_act2obj_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_auth_act2obj_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_auth_act2obj_v` AS select `m`.`root_node_id` AS `root_node_id`,`m`.`resource_id` AS `resource_id`,`m`.`obj_id` AS `obj_id`,`m`.`obj_attr_id` AS `obj_attr_id`,`m`.`attr_value` AS `attr_value`,`m`.`val_src_type` AS `val_src_type`,`m`.`incl_sub_flag` AS `incl_sub_flag`,`c1`.`code_name` AS `val_src_type_name`,`m`.`field_id` AS `field_id`,`c2`.`code_name` AS `incl_sub_flag_name` from ((`cip_admin_auth_act2obj` `m` left join `cip_admin_codes` `c1` on(((`m`.`val_src_type` = `c1`.`code_type`) and (`c1`.`domain_id` = 'val_src_type')))) left join `cip_admin_codes` `c2` on(((`m`.`incl_sub_flag` = `c2`.`code_type`) and (`c2`.`domain_id` = 'incl_sub_flag')))) ;

-- ----------------------------
-- View structure for cip_admin_auth_attr_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_auth_attr_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_auth_attr_v` AS select `m`.`obj_id` AS `obj_id`,`m`.`obj_attr_id` AS `obj_attr_id`,`m`.`obj_attr_name` AS `obj_attr_name`,`m`.`attr_val_table` AS `attr_val_table`,`m`.`sup_col_id` AS `sup_col_id`,`m`.`sub_col_id` AS `sub_col_id`,`m`.`rel_col_id` AS `rel_col_id` from `cip_admin_auth_attr` `m` ;

-- ----------------------------
-- View structure for cip_admin_auth_obj_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_auth_obj_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_auth_obj_v` AS select `m`.`auth_obj_id` AS `auth_obj_id`,`m`.`auth_obj_name` AS `auth_obj_name` from `cip_admin_auth_obj` `m` ;

-- ----------------------------
-- View structure for cip_admin_auth_role_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_auth_role_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_auth_role_v` AS select `m`.`auth_role_id` AS `auth_role_id`,`m`.`auth_attr_id` AS `auth_attr_id`,`m`.`auth_attr_val` AS `auth_attr_val` from `cip_admin_auth_role` `m` ;

-- ----------------------------
-- View structure for cip_admin_auth_user_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_auth_user_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_auth_user_v` AS select `m`.`user_attr_id` AS `user_attr_id`,`m`.`auth_attr_name` AS `auth_attr_name`,`m`.`auth_attr_id` AS `auth_attr_id` from `cip_admin_auth_user` `m` ;

-- ----------------------------
-- View structure for cip_admin_codes_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_codes_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_codes_v` AS select `m`.`domain_id` AS `domain_id`,`m`.`code_type` AS `code_type`,`m`.`code_name` AS `code_name`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator` from `cip_admin_codes` `m` ;

-- ----------------------------
-- View structure for cip_admin_commonquery_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_commonquery_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_commonquery_v` AS select `m`.`queryId` AS `queryId`,`m`.`description` AS `description`,`m`.`paramlist` AS `paramlist`,`m`.`statement` AS `statement`,`m`.`count_statement` AS `count_statement`,`pagination`.`code_name` AS `is_pagination`,`singlerec`.`code_name` AS `is_singleRec`,`m`.`pagination` AS `pagination`,`m`.`singleRec` AS `singleRec` from ((`cip_admin_commonquery` `m` left join `cip_admin_codes` `pagination` on(((`m`.`pagination` = `pagination`.`code_type`) and (`pagination`.`domain_id` = 'pagination')))) left join `cip_admin_codes` `singlerec` on(((`m`.`singleRec` = `singlerec`.`code_type`) and (`singlerec`.`domain_id` = 'singleRec')))) ;

-- ----------------------------
-- View structure for cip_admin_domain_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_domain_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_domain_v` AS select `m`.`domain_id` AS `domain_id`,`m`.`domain_name` AS `domain_name`,`m`.`domain_type` AS `domain_type`,`m`.`is_null_flag` AS `is_null_flag`,`m`.`data_length` AS `data_length`,`m`.`default_value` AS `default_value`,`m`.`float_length` AS `float_length`,`m`.`data_type` AS `data_type`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`m`.`resp_id` AS `resp_id`,`m`.`domain_desc` AS `domain_desc`,`m`.`ref_table_id` AS `ref_table_id`,`m`.`ref_domain_id` AS `ref_domain_id`,`domain_type`.`code_name` AS `domain_type_name`,`is_null_flag`.`code_name` AS `is_null_flag_name`,`data_type`.`code_name` AS `data_type_name` from (((`cip_admin_domain` `m` left join `cip_admin_codes` `domain_type` on(((`m`.`domain_type` = `domain_type`.`code_type`) and (`domain_type`.`domain_id` = 'domain_type')))) left join `cip_admin_codes` `is_null_flag` on(((`m`.`is_null_flag` = `is_null_flag`.`code_type`) and (`is_null_flag`.`domain_id` = 'is_null_flag')))) left join `cip_admin_codes` `data_type` on(((`m`.`data_type` = `data_type`.`code_type`) and (`data_type`.`domain_id` = 'data_type')))) ;

-- ----------------------------
-- View structure for cip_admin_log_access_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_log_access_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_log_access_v` AS select `m`.`log_id` AS `log_id`,`m`.`resource_id` AS `resource_id`,`m`.`visitor_id` AS `visitor_id`,`m`.`visitor_type` AS `visitor_type`,`m`.`occur_time` AS `occur_time`,`m`.`ip` AS `ip`,`m`.`remark` AS `remark`,`visitor_type`.`code_name` AS `visitor_type_name` from (`cip_admin_log_access` `m` left join `cip_admin_codes` `visitor_type` on(((`m`.`visitor_type` = `visitor_type`.`code_type`) and (`visitor_type`.`domain_id` = 'visitor_type')))) ;

-- ----------------------------
-- View structure for cip_admin_log_job_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_log_job_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_log_job_v` AS select `m`.`log_id` AS `log_id`,`m`.`task_id` AS `task_id`,`m`.`step_id` AS `step_id`,`m`.`step_msg` AS `step_msg`,`m`.`error_code` AS `error_code`,`m`.`occur_time` AS `occur_time`,`error_code`.`code_name` AS `error_code_name` from (`cip_admin_log_job` `m` left join `cip_admin_codes` `error_code` on(((`m`.`error_code` = `error_code`.`code_type`) and (`error_code`.`domain_id` = 'error_code')))) ;

-- ----------------------------
-- View structure for cip_admin_log_mdm_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_log_mdm_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_log_mdm_v` AS select `m`.`log_id` AS `log_id`,`m`.`table_id` AS `table_id`,`m`.`record_id` AS `record_id`,`m`.`old_values` AS `old_values`,`m`.`new_values` AS `new_values`,`m`.`user_id` AS `user_id`,`m`.`operate_type` AS `operate_type`,`m`.`occur_time` AS `occur_time` from `cip_admin_log_mdm` `m` ;

-- ----------------------------
-- View structure for cip_admin_op_log_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_op_log_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_op_log_v` AS select `m`.`op_seq_no` AS `op_seq_no`,`m`.`op_table_id` AS `op_table_id`,`m`.`op_obj_id` AS `op_obj_id`,`m`.`op_type` AS `op_type`,`m`.`remark` AS `remark`,`m`.`create_time` AS `create_time`,`m`.`operator` AS `operator`,`op_type`.`code_name` AS `op_type_name` from (`cip_admin_op_log` `m` left join `cip_admin_codes` `op_type` on(((`m`.`op_type` = `op_type`.`code_type`) and (`op_type`.`domain_id` = 'op_type')))) ;

-- ----------------------------
-- View structure for cip_admin_question_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_question_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_question_v` AS select `m`.`question_id` AS `question_id`,`m`.`question_title` AS `question_title`,`m`.`queston_type` AS `queston_type`,`m`.`question_desc` AS `question_desc`,`m`.`creater` AS `creater`,`m`.`question_reply` AS `question_reply`,`m`.`handle_flag` AS `handle_flag`,`m`.`update_time` AS `update_time`,`m`.`operater` AS `operater`,`m`.`create_time` AS `create_time`,`queston_type`.`code_name` AS `queston_type_name`,`handle_flag`.`code_name` AS `handle_flag_name` from ((`cip_admin_question` `m` left join `cip_admin_codes` `queston_type` on(((`m`.`queston_type` = `queston_type`.`code_type`) and (`queston_type`.`domain_id` = 'queston_type')))) left join `cip_admin_codes` `handle_flag` on(((`m`.`handle_flag` = `handle_flag`.`code_type`) and (`handle_flag`.`domain_id` = 'handle_flag')))) ;

-- ----------------------------
-- View structure for cip_admin_queue_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_queue_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_queue_v` AS select `m`.`queue_type` AS `queue_type`,`m`.`queue_id` AS `queue_id`,`m`.`queue_data` AS `queue_data`,`m`.`queue_flag` AS `queue_flag`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`queue_type`.`queue_type_name` AS `queue_type_name`,`queue_flag`.`code_name` AS `queue_flag_name` from ((`cip_admin_queue` `m` left join `cip_admin_queue_type_dm` `queue_type` on((`m`.`queue_type` = `queue_type`.`queue_type`))) left join `cip_admin_codes` `queue_flag` on(((`m`.`queue_flag` = `queue_flag`.`code_type`) and (`queue_flag`.`domain_id` = 'queue_flag')))) ;

-- ----------------------------
-- View structure for cip_admin_resource_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_resource_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_resource_v` AS select `m`.`resource_id` AS `resource_id`,`m`.`resource_name` AS `resource_name`,`m`.`resource_desc` AS `resource_desc`,`m`.`sys_uri` AS `sys_uri`,`m`.`resource_type` AS `resource_type`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`m`.`icon_id` AS `icon_id`,`resource_type`.`code_name` AS `resource_type_name` from (`cip_admin_resource` `m` left join `cip_admin_codes` `resource_type` on(((`m`.`resource_type` = `resource_type`.`code_type`) and (`resource_type`.`domain_id` = 'resource_type')))) ;

-- ----------------------------
-- View structure for cip_admin_res_2_res_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_res_2_res_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_res_2_res_v` AS select `m`.`root_node_id` AS `root_node_id`,`m`.`res_node_id` AS `res_node_id`,`m`.`res_node_sup` AS `res_node_sup`,`m`.`node_order` AS `node_order`,`m`.`root_flag` AS `root_flag`,`m`.`res_level` AS `res_level`,`m`.`leaf_flag` AS `leaf_flag`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`root_flag`.`code_name` AS `root_flag_name`,`leaf_flag`.`code_name` AS `leaf_flag_name` from ((`cip_admin_resource_2_resource` `m` left join `cip_admin_codes` `root_flag` on(((`m`.`root_flag` = `root_flag`.`code_type`) and (`root_flag`.`domain_id` = 'root_flag')))) left join `cip_admin_codes` `leaf_flag` on(((`m`.`leaf_flag` = `leaf_flag`.`code_type`) and (`leaf_flag`.`domain_id` = 'leaf_flag')))) ;

-- ----------------------------
-- View structure for cip_admin_roles_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_roles_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_roles_v` AS select `m`.`role_id` AS `role_id`,`m`.`role_name` AS `role_name`,`m`.`root_resource_id` AS `root_resource_id`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator` from `cip_admin_roles` `m` ;

-- ----------------------------
-- View structure for cip_admin_text_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_text_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_text_v` AS select `m`.`lang_type` AS `lang_type`,`m`.`msg_no` AS `msg_no`,`m`.`msg_txt` AS `msg_txt`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`lang_type`.`code_name` AS `lang_type_name` from (`cip_admin_text` `m` left join `cip_admin_codes` `lang_type` on(((`m`.`lang_type` = `lang_type`.`code_type`) and (`lang_type`.`domain_id` = 'lang_type')))) ;

-- ----------------------------
-- View structure for cip_admin_user2res_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_user2res_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_user2res_v` AS select `cip_admin_user2res`.`resource_id` AS `resource_id`,`cip_admin_user2res`.`resource_name` AS `resource_name`,`cip_admin_user2res`.`resource_image` AS `resource_image`,`cip_admin_user2res`.`user_id` AS `user_id` from `cip_admin_user2res` ;

-- ----------------------------
-- View structure for cip_admin_user_2_roles_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_user_2_roles_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_user_2_roles_v` AS select `m`.`user_id` AS `user_id`,`m`.`role_id` AS `role_id`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`m`.`default_flag` AS `default_flag`,`default_flag`.`code_name` AS `default_flag_name` from (`cip_admin_user_2_roles` `m` left join `cip_admin_codes` `default_flag` on(((`m`.`default_flag` = `default_flag`.`code_type`) and (`default_flag`.`domain_id` = 'default_flag')))) ;

-- ----------------------------
-- View structure for cip_admin_user_setting_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_user_setting_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_user_setting_v` AS select `m`.`user_id` AS `user_id`,`m`.`user_key` AS `user_key`,`m`.`user_value` AS `user_value`,`m`.`remark` AS `remark`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator` from `cip_admin_user_setting` `m` ;

-- ----------------------------
-- View structure for cip_admin_user_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_user_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_user_v` AS select `m`.`user_id` AS `user_id`,`m`.`user_name` AS `user_name`,`m`.`start_date` AS `start_date`,`m`.`end_date` AS `end_date`,`m`.`user_pwd` AS `user_pwd`,`m`.`pwd_init_flag` AS `pwd_init_flag`,`m`.`user_his1_pwd` AS `user_his1_pwd`,`m`.`user_his2_pwd` AS `user_his2_pwd`,`m`.`user_his3_pwd` AS `user_his3_pwd`,`m`.`pwd_set_time` AS `pwd_set_time`,`m`.`pwd_reset_days` AS `pwd_reset_days`,`m`.`pwd_reset_flag` AS `pwd_reset_flag`,`m`.`user_status` AS `user_status`,`m`.`user_type` AS `user_type`,`m`.`person_id` AS `person_id`,`m`.`org_id` AS `org_id`,`m`.`position_id` AS `position_id`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`pwd_reset_flag`.`code_name` AS `pwd_reset_flag_name`,`user_status`.`code_name` AS `user_status_name`,`user_type`.`code_name` AS `user_type_name`,`m`.`lang` AS `lang` from (((`cip_admin_user` `m` left join `cip_admin_codes` `pwd_reset_flag` on(((`m`.`pwd_reset_flag` = `pwd_reset_flag`.`code_type`) and (`pwd_reset_flag`.`domain_id` = 'pwd_reset_flag')))) left join `cip_admin_codes` `user_status` on(((`m`.`user_status` = `user_status`.`code_type`) and (`user_status`.`domain_id` = 'user_status')))) left join `cip_admin_codes` `user_type` on(((`m`.`user_type` = `user_type`.`code_type`) and (`user_type`.`domain_id` = 'user_type')))) ;

-- ----------------------------
-- View structure for cip_admin_view_resources
-- ----------------------------
DROP VIEW IF EXISTS `cip_admin_view_resources`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_admin_view_resources` AS select `res`.`resource_id` AS `resource_id`,`res`.`resource_name` AS `resource_name`,`res`.`sys_uri` AS `sys_uri`,`res`.`resource_type` AS `resource_type`,`rel`.`res_node_sup` AS `res_node_sup`,`rel`.`root_node_id` AS `root_node_id`,`rel`.`root_flag` AS `root_flag`,`rel`.`leaf_flag` AS `leaf_flag`,`rel`.`res_level` AS `res_level`,`res`.`icon_id` AS `icon` from (`cip_admin_resource_2_resource` `rel` left join `cip_admin_resource` `res` on((`rel`.`res_node_id` = `res`.`resource_id`))) order by `rel`.`res_level`,`rel`.`node_order` ;

-- ----------------------------
-- View structure for cip_meta_fields_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_meta_fields_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_meta_fields_v` AS select `m`.`str_id` AS `str_id`,`m`.`field_id` AS `field_id`,`m`.`field_memo` AS `field_memo`,`m`.`field_name` AS `field_name`,`m`.`search_flag` AS `search_flag`,`m`.`domain_type` AS `domain_type`,`m`.`java_type` AS `java_type`,`m`.`jdbc_type` AS `jdbc_type`,`m`.`field_length` AS `field_length`,`m`.`fprecision` AS `fprecision`,`m`.`field_default` AS `field_default`,`m`.`key_flag` AS `key_flag`,`m`.`null_flag` AS `null_flag`,`m`.`field_use_type` AS `field_use_type`,`m`.`ui_control` AS `ui_control`,`m`.`ui_ctrl_clazz` AS `ui_ctrl_clazz`,`m`.`ui_length` AS `ui_length`,`m`.`create_time` AS `create_time`,`m`.`update_time` AS `update_time`,`m`.`operator` AS `operator`,`search_flag`.`code_name` AS `search_flag_name`,`domain_type`.`code_name` AS `domain_type_name`,`key_flag`.`code_name` AS `key_flag_name`,`null_flag`.`code_name` AS `null_flag_name`,`field_use_type`.`code_name` AS `field_use_type_name` from (((((`cip_meta_fields` `m` left join `cip_admin_codes` `search_flag` on(((`m`.`search_flag` = `search_flag`.`code_type`) and (`search_flag`.`domain_id` = 'search_flag')))) left join `cip_admin_codes` `domain_type` on(((`m`.`domain_type` = `domain_type`.`code_type`) and (`domain_type`.`domain_id` = 'domain_type')))) left join `cip_admin_codes` `key_flag` on(((`m`.`key_flag` = `key_flag`.`code_type`) and (`key_flag`.`domain_id` = 'key_flag')))) left join `cip_admin_codes` `null_flag` on(((`m`.`null_flag` = `null_flag`.`code_type`) and (`null_flag`.`domain_id` = 'null_flag')))) left join `cip_admin_codes` `field_use_type` on(((`m`.`field_use_type` = `field_use_type`.`code_type`) and (`field_use_type`.`domain_id` = 'field_use_type')))) ;

-- ----------------------------
-- View structure for cip_meta_field_log_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_meta_field_log_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_meta_field_log_v` AS select `m`.`str_id` AS `str_id`,`m`.`field_id` AS `field_id`,`m`.`oper_time` AS `oper_time`,`m`.`op_field` AS `op_field`,`m`.`op_type` AS `op_type`,`m`.`old_value` AS `old_value`,`m`.`new_value` AS `new_value`,`m`.`operator` AS `operator`,`m`.`op_flag` AS `op_flag`,`op_type`.`code_name` AS `op_type_name` from (`cip_meta_field_log` `m` left join `cip_admin_codes` `op_type` on(((`m`.`op_type` = `op_type`.`code_type`) and (`op_type`.`domain_id` = 'op_type')))) ;

-- ----------------------------
-- View structure for cip_meta_module_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_meta_module_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_meta_module_v` AS select `m`.`module_id` AS `module_id`,`m`.`module_name` AS `module_name`,`m`.`module_memo` AS `module_memo`,`m`.`update_time` AS `update_time`,`m`.`create_time` AS `create_time`,`m`.`operator` AS `operator` from `cip_meta_module` `m` ;

-- ----------------------------
-- View structure for cip_meta_str_log_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_meta_str_log_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_meta_str_log_v` AS select `m`.`str_id` AS `str_id`,`m`.`oper_time` AS `oper_time`,`m`.`op_field` AS `op_field`,`m`.`op_type` AS `op_type`,`m`.`old_value` AS `old_value`,`m`.`new_value` AS `new_value`,`m`.`operator` AS `operator`,`m`.`op_flag` AS `op_flag`,`op_type`.`code_name` AS `op_type_name` from (`cip_meta_str_log` `m` left join `cip_admin_codes` `op_type` on(((`m`.`op_type` = `op_type`.`code_type`) and (`op_type`.`domain_id` = 'op_type')))) ;

-- ----------------------------
-- View structure for cip_meta_str_v
-- ----------------------------
DROP VIEW IF EXISTS `cip_meta_str_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ydc9f`@`%` SQL SECURITY DEFINER VIEW `cip_meta_str_v` AS select `m`.`str_id` AS `str_id`,`m`.`str_name` AS `str_name`,`m`.`str_memo` AS `str_memo`,`m`.`str_type` AS `str_type`,`m`.`str_prefix` AS `str_prefix`,`m`.`str_module` AS `str_module`,`m`.`func_set_id` AS `func_set_id`,`m`.`ref_str_id` AS `ref_str_id`,`m`.`str_data_type` AS `str_data_type`,`m`.`prd_date` AS `prd_date`,`m`.`form_template` AS `form_template`,`m`.`list_template` AS `list_template`,`m`.`src_sys_id` AS `src_sys_id`,`m`.`resp_id` AS `resp_id`,`m`.`resp_org` AS `resp_org`,`m`.`update_time` AS `update_time`,`m`.`create_time` AS `create_time`,`m`.`operator` AS `operator`,`str_type`.`code_name` AS `str_type_name`,`str_data_type`.`code_name` AS `str_data_type_name`,`module`.`module_name` AS `str_module_name` from (((`cip_meta_str` `m` left join `cip_admin_codes` `str_type` on(((`m`.`str_type` = `str_type`.`code_type`) and (`str_type`.`domain_id` = 'str_type')))) left join `cip_admin_codes` `str_data_type` on(((`m`.`str_data_type` = `str_data_type`.`code_type`) and (`str_data_type`.`domain_id` = 'str_data_type')))) left join `cip_meta_module` `module` on((`m`.`str_module` = `module`.`module_id`))) ;

-- ----------------------------
-- View structure for org_info_view
-- ----------------------------
DROP VIEW IF EXISTS `org_info_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ggs`@`%` SQL SECURITY DEFINER VIEW `org_info_view` AS select `a`.`bm` AS `Org_Cd`,`a`.`mc` AS `Org_Name`,`a`.`lb` AS `Org_Type`,`a`.`sjdw` AS `Super_Org_Cd`,`e`.`mc` AS `Super_Org_Name`,`f`.`zzz` AS `Tran_Cd`,`g`.`mc` AS `Tran_Name`,`b`.`CountyID` AS `County_Id`,`b`.`CountyName` AS `County_Name`,`c`.`CityID` AS `City_Id`,`c`.`CityName` AS `City_Name`,`d`.`ProvinceID` AS `Prov_Id`,`d`.`ProvinceName` AS `Prov_Name`,(case when (convert(`d`.`bigarea` using utf8) = '大区一') then 1 when (convert(`d`.`bigarea` using utf8) = '大区二') then 2 when (convert(`d`.`bigarea` using utf8) = '大区三') then 3 when (convert(`d`.`bigarea` using utf8) = '大区四') then 4 when (convert(`d`.`bigarea` using utf8) = '大区五') then 5 when (convert(`d`.`bigarea` using utf8) = '港澳台') then 6 end) AS `Area_Id`,`d`.`bigarea` AS `Area_Name` from ((((((`ydserver`.`gs` `a` left join `ydserver`.`county` `b` on((`a`.`szd` = `b`.`CountyID`))) left join `ydserver`.`city` `c` on((`b`.`CityID` = `c`.`CityID`))) left join `ydserver`.`province` `d` on((`c`.`ProvinceID` = `d`.`ProvinceID`))) left join `ydserver`.`gs` `e` on((`a`.`sjdw` = `e`.`bm`))) left join `ydserver`.`wdzzz_cw` `f` on(((`a`.`bm` = `f`.`wdbm`) and (`f`.`yxx` = 0)))) left join `ydserver`.`gs` `g` on((`f`.`zzz` = `g`.`bm`))) where (`a`.`lb` in (21,22,2,3,50)) ;

-- ----------------------------
-- View structure for org_region_view
-- ----------------------------
DROP VIEW IF EXISTS `org_region_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ggs`@`%` SQL SECURITY DEFINER VIEW `org_region_view` AS select `a`.`bm` AS `Org_Cd`,`a`.`mc` AS `Org_Name`,`a`.`lb` AS `Org_Type`,`b`.`CountyID` AS `County_Id`,`b`.`CountyName` AS `County_Name`,`c`.`CityID` AS `City_Id`,`c`.`CityName` AS `City_Name`,`d`.`ProvinceID` AS `Prov_Id`,`d`.`ProvinceName` AS `Prov_Name`,`d`.`rg_id` AS `Area_Id`,`d`.`bigarea` AS `Area_Name` from (((`ydserver`.`gs` `a` left join `ydserver`.`county` `b` on((`a`.`szd` = `b`.`CountyID`))) left join `ydserver`.`city` `c` on((`b`.`CityID` = `c`.`CityID`))) left join `ydserver`.`province` `d` on((`c`.`ProvinceID` = `d`.`ProvinceID`))) where (`a`.`lb` = 3) ;
