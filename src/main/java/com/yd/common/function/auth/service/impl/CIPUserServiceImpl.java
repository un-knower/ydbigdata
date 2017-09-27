package com.yd.common.function.auth.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.auth.CIPUserProfileData;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.constants.CIPUserConstants;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.dao.CIP_admin_questionDao;
import com.yd.common.function.admin.dao.CIP_admin_userDao;
import com.yd.common.function.admin.dao.CIP_admin_user_2_rolesDao;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.po.CIP_admin_userPO;
import com.yd.common.function.auth.data.CIPAuthResult;
import com.yd.common.function.auth.data.CIPLoginData;
import com.yd.common.function.auth.service.CIPUserService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;

@Service(value = "userService")
public class CIPUserServiceImpl implements CIPUserService {

	private static final int USER_LOCK = 1;

	@Autowired
	private CIP_admin_userDao userDao = null;
	@Autowired
	private CIP_admin_user_2_rolesDao user2roleDao = null;
	@Autowired
	private CIP_admin_op_logDao op_logDao = null;
	@Autowired
	CIP_admin_questionDao questionDao = null;

	@Override
	public CIPUserProfileData loginUser(CIPLoginData loginData) throws CIPServiceException, CIPRuntimeException {
		if (loginData.user_id == null || loginData.user_id.trim().equals("")) {
			throw new CIPServiceException(CIPErrorCode.USER_IS_NULL);
		}
		if (!"X".equals(CIPRuntimeConfigure.cip_conf_passcode)){
			if (loginData.local_pass_code == null
					|| loginData.local_pass_code.equals("")) {
				throw new CIPServiceException(CIPErrorCode.PASS_CODE_IS_NULL);
			}

			if (loginData.pass_code == null || loginData.pass_code.equals("")) {
				throw new CIPServiceException(CIPErrorCode.PASS_CODE_IS_NULL);
			}

			if (!loginData.pass_code.equals(loginData.local_pass_code)) {
				throw new CIPServiceException(CIPErrorCode.PASS_CODE_ERROR);
			}
		}
		CIPUserProfileData userProfile = null;

		if(CIPRuntimeConfigure.cip_user_xremote){
			//远程登录
			userProfile = CIPRuntime.authManager.validateUserAndGetRoles(loginData.user_id, loginData.user_pwd);
		}else {
			//本地登录			
			CIP_admin_userPO userData = userDao.getSingle(loginData.user_id);	
			
//			CIP_admin_userPO userData = userDao.getUserById(loginData.user_id);	
			if(userData == null){

				throw new CIPServiceException(CIPErrorCode.USER_INFO_ERROR);
			}
			/*
			 * if(userData.getPwd_init_flag()>0){ throw new
			 * CIPServiceException(CIPErrorCode.PASSWORD_RESET); }
			 */
			else if (userData.getUser_status() == USER_LOCK) {
				throw new CIPServiceException(CIPErrorCode.USER_LOCKED);
			} else if (!userData.getUser_pwd().equals(loginData.user_pwd)) {
				throw new CIPServiceException(CIPErrorCode.USER_INFO_ERROR);
			}
			// 验证通过获取用户角色
			userProfile = userData.toProfileData();
			List<String> userRoles = getRoles(userProfile.user_id);
			userProfile.user_roles = userRoles;
			CIPRuntime.authManager.setUserProfile(userProfile);

		}
		if (userProfile.user_roles == null
				|| userProfile.user_roles.size() == 0) {
			if (CIPRuntimeConfigure.cip_default_role != null
					&& !"".equals(CIPRuntimeConfigure.cip_default_role)) {
				List<String> roles = new ArrayList<>();
				roles.add(CIPRuntimeConfigure.cip_default_role);
				userProfile.user_roles = roles;
			} else {
				throw new CIPServiceException(CIPErrorCode.USER_ROLE_IS_NULL);
			}
		}

		// TODO 用户登录日志
		/*
		 * CIP_admin_op_logPO op_logPO = new CIP_admin_op_logPO();
		 * op_logPO.setOp_seq_no(System.currentTimeMillis());
		 * op_logPO.setOp_table_id("login");
		 * op_logPO.setOp_obj_id(userProfile.sys_id); op_logPO.setOp_type("N");
		 * op_logPO.setRemark("用户"+userProfile.user_name+"登录系统");
		 * op_logPO.setOperator(userProfile.user_id);
		 * op_logPO.setCreate_time(new Timestamp(System.currentTimeMillis()));
		 */

		// NoteLogExecute.execute(new NoteLog(op_logDao, op_logPO));

		// op_logDao.add(op_logPO);
		return userProfile;

	}
	
	@Override
	public CIPUserProfileData wxloginUser(String userid) throws CIPServiceException, CIPRuntimeException {
		CIPUserProfileData userProfile = null;
		if(CIPRuntimeConfigure.cip_user_xremote){
			//远程登录
			userProfile = CIPRuntime.authManager.getUserAndRoles(userid);
		}else {
			//本地登录			
			CIP_admin_userPO userData = userDao.getSingle(userid);	
			
//			CIP_admin_userPO userData = userDao.getUserById(loginData.user_id);	
			if(userData == null){
				
				throw new CIPServiceException(CIPErrorCode.USER_INFO_ERROR);
			}
			/*
			 * if(userData.getPwd_init_flag()>0){ throw new
			 * CIPServiceException(CIPErrorCode.PASSWORD_RESET); }
			 */
			else if (userData.getUser_status() == USER_LOCK) {
				throw new CIPServiceException(CIPErrorCode.USER_LOCKED);
			}
			// 验证通过获取用户角色
			userProfile = userData.toProfileData();
			List<String> userRoles = getRoles(userProfile.user_id);
			userProfile.user_roles = userRoles;
			CIPRuntime.authManager.setUserProfile(userProfile);
			
		}
		if (userProfile.user_roles == null
				|| userProfile.user_roles.size() == 0) {
			if (CIPRuntimeConfigure.cip_default_role != null
					&& !"".equals(CIPRuntimeConfigure.cip_default_role)) {
				List<String> roles = new ArrayList<>();
				roles.add(CIPRuntimeConfigure.cip_default_role);
				userProfile.user_roles = roles;
			} else {
				throw new CIPServiceException(CIPErrorCode.USER_ROLE_IS_NULL);
			}
		}
		
		// TODO 用户登录日志
		/*
		 * CIP_admin_op_logPO op_logPO = new CIP_admin_op_logPO();
		 * op_logPO.setOp_seq_no(System.currentTimeMillis());
		 * op_logPO.setOp_table_id("login");
		 * op_logPO.setOp_obj_id(userProfile.sys_id); op_logPO.setOp_type("N");
		 * op_logPO.setRemark("用户"+userProfile.user_name+"登录系统");
		 * op_logPO.setOperator(userProfile.user_id);
		 * op_logPO.setCreate_time(new Timestamp(System.currentTimeMillis()));
		 */
		
		// NoteLogExecute.execute(new NoteLog(op_logDao, op_logPO));
		
		// op_logDao.add(op_logPO);
		return userProfile;
		
	}

	@Override
	public CIPAuthResult logoutUser(String userId) {
		// TODO 用户登出日志
		CIP_admin_op_logPO op_logPO = new CIP_admin_op_logPO();
		op_logPO.setOp_seq_no(System.currentTimeMillis());
		op_logPO.setOp_table_id("login");
		// op_logPO.setOp_obj_id(userProfile.sys_id);
		op_logPO.setOp_type("T");
		op_logPO.setRemark("用户" + userId + "退出系统");
		op_logPO.setOperator(userId);
		op_logPO.setCreate_time(new Timestamp(System.currentTimeMillis()));
		op_logDao.add(op_logPO);
		CIPRuntime.authManager.logoutUserAuth(userId);
		return CIPAuthResult.LOGOUT_SUCCESS;
	}

	@Override
	public CIPAuthResult resetPassword(String user_id, String oldP, String newP) {
		if (user_id == null || user_id.trim().equals("")) {
			return CIPAuthResult.USER_IS_NULL;
		}
		if (oldP == null || newP == null || oldP.trim().equals("")
				|| newP.trim().equals("") || oldP.equals(newP)) {
			return CIPAuthResult.PASSWORD_IS_NULL;
		}
		if (CIPRuntimeConfigure.cip_user_xremote) {
			// 远程修改密码,不支持！！！
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_FUNCTION_NOT_SUPPORT);
		} else {
			CIP_admin_userPO userData = userDao.getSingle(user_id);
			if (!userData.getUser_pwd().equals(oldP)) {
				return CIPAuthResult.PASSWORD_MATCH;
			} else {
				String pwd1 = userData.getUser_his1_pwd();
				String pwd2 = userData.getUser_his2_pwd();
				String pwd3 = userData.getUser_his3_pwd();
				if (newP.equals(pwd1) || newP.equals(pwd2) || newP.equals(pwd3)) {
					return CIPAuthResult.PASSWORD_IS_USED;
				} else {
					pwd3 = pwd2;
					pwd2 = pwd1;
					pwd1 = oldP;
				}
				Map<String, Object> params = new HashMap<String, Object>(7);
				params.put("user_pwd", newP);
				params.put("pwd_init_flag", CIPUserConstants.C_USER_PWD_INIT_NO);
				params.put("user_his1_pwd", pwd1);
				params.put("user_his2_pwd", pwd2);
				params.put("user_his3_pwd", pwd3);
				params.put("pwd_set_time", new Date());
				params.put("pwd_reset_flag",
						CIPUserConstants.C_USER_PWD_RESET_YES);

				userDao.updateKV(params, user_id);
				resetUserProfile(user_id);
			}
		}

		return CIPAuthResult.SUCCESS;
	}

	private void resetUserProfile(String user_id) {
		CIP_admin_userPO userData = userDao.getSingle(user_id);
		CIPUserProfileData userProfileData = userData.toProfileData();
		CIPRuntime.authManager.setUserProfile(userProfileData);
	}

	@Override
	public CIPUserProfileData getUserProfile(String userId) throws Exception {

		CIPUserProfileData userProfile = null;
		userProfile = CIPRuntime.authManager.getUserProfile(userId);
		if (userProfile == null) {
			if (CIPRuntimeConfigure.cip_user_xremote) {
				userProfile = CIPRuntime.authManager.getUserAndRoles(userId);
			} else {
				CIP_admin_userPO userData = null;
				userData = userDao.getSingle(userId);
				userProfile = userData.toProfileData();
				List<String> userRoles = getRoles(userProfile.user_id);
				userProfile.user_roles = userRoles;
			}
			CIPRuntime.authManager.setUserProfile(userProfile);
		}

		return userProfile;
	}

	@Override
	public String getPassCode(OutputStream os) throws IOException {
		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}

		// 图象生效
		g.dispose();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", os);

		return sRand;
	}

	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@Override
	public List<String> getRoles(String userId) {

		return user2roleDao.getRoles(userId);

	}

	@Override
	public int getDealThingsCount(String user_id) {
		if(user_id.equals("root")){
			return questionDao.getAllDealThingsCount();	
		}else{
			return questionDao.getDealThingCount(user_id);
		}	
	}

}
