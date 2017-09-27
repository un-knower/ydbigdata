package com.yd.ydbi.api.quality.model;

import java.io.Serializable;
import java.util.List;

public class ListLine implements Serializable {
    private static final long serialVersionUID = 1L;

    private List line_top;   				// 列表前十   
    private List line_after;  				// 列表后十
    private List line_all;              	// 列表全部
	
    public List getLine_top() {
		return line_top;
	}
	public void setLine_top(List line_top) {
		this.line_top = line_top;
	}
	public List getLine_after() {
		return line_after;
	}
	public void setLine_after(List line_after) {
		this.line_after = line_after;
	}
	public List getLine_all() {
		return line_all;
	}
	public void setLine_all(List line_all) {
		this.line_all = line_all;
	}
}