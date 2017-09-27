package com.yd.common.function.auth.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yd.common.auth.CIPUserProfileData;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.auth.data.CIPAuthResult;
import com.yd.common.function.auth.data.CIPLoginData;

@Service
public interface CIPUserService {

	//1 用户登录验证号
	public String getPassCode(OutputStream os) throws IOException;
	
	//2.1 用户登陆认证
	public CIPUserProfileData loginUser(CIPLoginData loginData) throws CIPServiceException, CIPRuntimeException;
	public CIPUserProfileData wxloginUser(String code) throws CIPServiceException, CIPRuntimeException;
	
	//2.2 用户登出
	public CIPAuthResult logoutUser(String userId);
		
	//3. 重置密码
	public CIPAuthResult resetPassword(String userId, String oldP, String newP);
//	
//	//4. 锁定用户
//	public int lockUser(String userId);
//	
//	//5. 解锁用户
//	public int unlockUser(String userId);
//	
	//6. 获取当前系统 用户概要
	public CIPUserProfileData getUserProfile(String userId) throws Exception;
	
//	//7. 获取用户全信息
//	public CIPUserData getUserData(String userId);
//	
//	//8. 修改当前系统 用户信息 
//	public int updateUser(CIPUserProfileData userData);
//	
	//9. 获取用户角色信息
	public List<String> getRoles(String userId);
	
	//10.获取用户待办事宜数量
	public int getDealThingsCount(String user_id);
	
}
