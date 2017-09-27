package com.yd.ydbi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 远程执行shell工具
 *
 */
public class ShellUtil {
	private static Logger log = Logger.getLogger("ShellUtil");
	private Connection conn;
	private String ipAddr;
	private int port;
	private String userName;
	private String password;
	private String charset = Charset.defaultCharset().toString();

	public ShellUtil() {
		super();
	}

	public ShellUtil(String ipAddr, int port, String userName, String password,String charset) {
		this.ipAddr = ipAddr;
		this.port = port;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
	}

	/**
	 * 登录远程linux主机
	 */
	public boolean login() throws IOException {
		conn = new Connection(ipAddr, port);
		conn.connect();
		return conn.authenticateWithPassword(userName, password);
	}

	/**
	 * 远程执行并返回脚本执行的结果状态码
	 * 
	 */
	public int getRemoteExit(String cmds) throws IOException {
		int nu = -1;
		InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";
		Session session=null;
		try {
			if (this.login()) {
				session = conn.openSession();
				System.out.println("cmds  --->  "+cmds);
				session.execCommand(cmds);
				session.waitForCondition(ChannelCondition.EXIT_STATUS, 120000);
				stdOut = new StreamGobbler(session.getStdout());
	            outStr = processStream(stdOut, charset);
	            log.info("stdOut:"+outStr);
	            stdErr = new StreamGobbler(session.getStderr());
	            outErr = processStream(stdErr, charset);
	            log.info("stdErr:"+outErr);
				nu = session.getExitStatus();
			} else {
				log.info("***********远程服务器登录失败*********");
			}
		} catch (Exception e) {
			log.error("***********远程执行命令时异常*********",e);
		} finally {
			conn.close();
			if(session!=null){
				session.close();
			}
		}
		return nu;
	}
	private String processStream(InputStream in, String charset) throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }
	/**
	 * 远程拷贝文件
	 * 
	 */
	public int getRemotFile(String remoteFilePath,String localFilePath) throws IOException {
		int nu = -1;
		try {
			if (this.login()) {
				SCPClient scpClient=conn.createSCPClient();
				scpClient.get(remoteFilePath, localFilePath);
				return 0;
			} else {
				log.error("***********远程服务器登录失败*********");
			}
		} catch (Exception e) {
			log.error(e);
			log.error("**********远程拷贝文件时异常**********", e);
		} finally {
			conn.close();
		}
		return nu;
	}
	
	/**
	 * 远程删除文件
	 * 
	 */
	public int deleteRemotFile(String remoteFile) throws IOException {
		int nu = -1;
		try {
			if (this.login()) {
				 SFTPv3Client sftpClient = new SFTPv3Client(conn);
		         sftpClient.rm(remoteFile);
		         return 0;
			} else {
				log.error("***********远程服务器登录失败*********");
			}
		} catch (Exception e) {
			log.error(e);
			log.error("**********远程拷贝文件时异常**********", e);
		} finally {
			conn.close();
		}
		return nu;
	}
	/**
	* 
	* testHost:(测试主机状态).
	*
	* @author Administrator
	* @param remoteFile
	* @return
	* @throws IOException
	* @since JDK 1.7
	 */
	public int getHostStat(){
		try {
			if (this.login()) {
				 return 0;
			} 
		} catch (Exception e) {
			
		} finally {
			conn.close();
		}
		return -1;
	}

	public static void main(String args[]){
//		String timeStr = String.valueOf(System.currentTimeMillis());
		String timeStr = "1499271404972";
		String localPathStr = "D:/tmp";
		String remotrPathStr = "/tmp";
		/*File file =new File(pathStr+"/"+timeStr);    
		//如果文件夹不存在则创建    
		if (!file .exists()  && !file .isDirectory()){       
		    System.out.println("//不存在");  
		    file.mkdir();    
		} */
		ShellUtil shellUtil=new ShellUtil("10.19.105.160", 22022, "oracle", "HAHPHYANYXIW", "utf-8");
		boolean flag = false;
		try {
				String remoteFilePath = remotrPathStr+"/"+timeStr;
				String remoteFileName = remoteFilePath+"/"+timeStr+".zip";
				int mkdirStatus = shellUtil.getRemoteExit("mkdir -p "+remoteFilePath);
				if (mkdirStatus == 0) {
					int zipStatus = shellUtil.getRemoteExit("cd "+remoteFilePath+";zip -mr "+timeStr+".zip"+" *.csv");
					if (zipStatus == 0) {
						int scpStatus = shellUtil.getRemotFile(remoteFileName,localPathStr);
						if (scpStatus == 0) {
							System.out.println("exportFile----->远程运行导出命令成功,远程拷贝成功,导出成功。");
							flag = true;
						} else {
							System.out.println("exportFile----->远程拷贝文件失败,可能的原因：连接远程服务器失败，文件丢失等,导出失败。");
						}
					} else {
						System.out.println("exportFile----->远程文件zip压缩失败。");
					}
				}
				shellUtil.getRemoteExit("rm -rf "+remoteFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
