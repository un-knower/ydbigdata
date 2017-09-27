package com.yd.ydbi.common.aspect;

public class UlpLogBean {
	
	private String l_sys;	// 系统     系统名称，英文简写，需建索引所以必需小写（这个是环境搭好后发现）
	private String l_mod;	// 模块     模块名称，英文简写
	private String l_opt;	// 操作     菜单加操作英文简写（确保开发自己能定位功能）
	private String l_lvl;	// 日志级别 （info，debug，warn，error，fatal）
	private String l_par;	// 参数(单号，主键类)有主键之类的参数在这个字段里体现，没有就留空。（此字段为查询链路设置，如单号，不与l_input冗余）
	private String l_emp;	// 人员     登录操作人员
	private String l_cmp;	// 公司     操作人员所在公司，如取不到为空
	private String l_dev;	// 设备     与把枪手机快手对接的，如果有设备号填写设备号，没有留空
	private String l_ipx;	// IP     客户端请求IP
	private String l_beg;	// 开始时间    请求进入时间 格式 yyyy-MM-dd HH:mm:ss
	private String l_end;	// 结束时间    请求结束时间
	private String l_est;	// 耗时(ms)  l_end - l_beg 的毫秒数，搜集这边不好做计算，所以需要开发多一步操作
	private String l_input;	// 输入数据    强烈建议增加，非必填，请求数据
	private String l_output;// 输出数据    强烈建议增加，非必填，请求响应的数据，此输出针对非查询的业务功能，一般接口性质的功能需输出，请开发组长做好审查，
	private String l_inf;	// 状态（Y-成功,N-失败）
	
	public String getL_sys() {
		return l_sys;
	}
	public void setL_sys(String l_sys) {
		this.l_sys = l_sys;
	}
	public String getL_mod() {
		return l_mod;
	}
	public void setL_mod(String l_mod) {
		this.l_mod = l_mod;
	}
	public String getL_opt() {
		return l_opt;
	}
	public void setL_opt(String l_opt) {
		this.l_opt = l_opt;
	}
	public String getL_lvl() {
		return l_lvl;
	}
	public void setL_lvl(String l_lvl) {
		this.l_lvl = l_lvl;
	}
	public String getL_par() {
		return l_par;
	}
	public void setL_par(String l_par) {
		this.l_par = l_par;
	}
	public String getL_emp() {
		return l_emp;
	}
	public void setL_emp(String l_emp) {
		this.l_emp = l_emp;
	}
	public String getL_cmp() {
		return l_cmp;
	}
	public void setL_cmp(String l_cmp) {
		this.l_cmp = l_cmp;
	}
	public String getL_dev() {
		return l_dev;
	}
	public void setL_dev(String l_dev) {
		this.l_dev = l_dev;
	}
	public String getL_ipx() {
		return l_ipx;
	}
	public void setL_ipx(String l_ipx) {
		this.l_ipx = l_ipx;
	}
	public String getL_beg() {
		return l_beg;
	}
	public void setL_beg(String l_beg) {
		this.l_beg = l_beg;
	}
	public String getL_end() {
		return l_end;
	}
	public void setL_end(String l_end) {
		this.l_end = l_end;
	}
	public String getL_est() {
		return l_est;
	}
	public void setL_est(String l_est) {
		this.l_est = l_est;
	}
	public String getL_input() {
		return l_input;
	}
	public void setL_input(String l_input) {
		this.l_input = l_input;
	}
	public String getL_output() {
		return l_output;
	}
	public void setL_output(String l_output) {
		this.l_output = l_output;
	}
	public String getL_inf() {
		return l_inf;
	}
	public void setL_inf(String l_inf) {
		this.l_inf = l_inf;
	}
	
}
