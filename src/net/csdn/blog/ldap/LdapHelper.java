package net.csdn.blog.ldap;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/** 
 * 用户登陆认证,LDAP跨域认证，通过LDAP对用户进行更新 <br>
 * DN:Distinguished Name <br>
 * dc:Domain Component <br>
 * OU:Organization Unit <br>
 * cn:Common Name<br>
 * uid:user Id<br>
 * sn:surname,姓氏
 * @author kangming.ning 
 * 
 */
public class LdapHelper {

	private static DirContext dirContext;

	// LDAP服务器端口默认为389
	private static final String LDAP_URL = "ldap://172.16.34.156:389/";
	
	private static final String DOMAIN="dc=www,dc=km,dc=com";

	// LDAP驱动
	private static final String LDAP_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

	
   public static void main(String[] args){
		
	   addUserLdap("test","test1");
		
	}

	//通过连接LDAP服务器对用户进行认证，返回LDAP对象
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DirContext InitialDirContext() {
		String account = "admin"; // 
		String password = "admin"; // 密码
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_FACTORY);
		env.put(Context.PROVIDER_URL, LDAP_URL+DOMAIN);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		// cn=属于哪个组织结构名称，ou=某个组织结构名称下等级位置编号
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + account+" ou=People");//不指定用户名和密码匿名登录
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			// 连接LDAP进行认证
			dirContext = new InitialDirContext(env);
			System.out.println("initial dircontext success");
		} catch (javax.naming.AuthenticationException e) {
			System.out.println("initial fail");
		} catch (NamingException err) {
			err.printStackTrace();
		} catch (Exception e) {
			System.out.println("认证出错：");
			e.printStackTrace();
		}
		return dirContext;
	}

	/**
	 * 判断将明文密码跟ldap中的用户密码进行匹配判断。因为ldap中的用户密码是经过SSHA散列的，因此必须将明文转换为SSHA码才能够进行匹配。
	 * */
	public static boolean verifySHA(String ldappw, String inputpw) throws Exception {

		// MessageDigest 提供了消息摘要算法，如 MD5 或 SHA，的功能，这里LDAP使用的是SHA-1
		MessageDigest md = MessageDigest.getInstance("SHA-1");

		// 取出加密字符
		if (ldappw.startsWith("{SSHA}")) {
			ldappw = ldappw.substring(6);
		} else if (ldappw.startsWith("{SHA}")) {
			ldappw = ldappw.substring(5);
		}

		// 解码BASE64
		byte[] ldappwbyte = Base64.decode(ldappw);
		byte[] shacode;
		byte[] salt;

		// 前20位是SHA-1加密段，20位后是最初加密时的随机明文
		if (ldappwbyte.length <= 20) {
			shacode = ldappwbyte;
			salt = new byte[0];
		} else {
			shacode = new byte[20];
			salt = new byte[ldappwbyte.length - 20];
			System.arraycopy(ldappwbyte, 0, shacode, 0, 20);
			System.arraycopy(ldappwbyte, 20, salt, 0, salt.length);
		}

		// 把用户输入的密码添加到摘要计算信息
		md.update(inputpw.getBytes());
		// 把随机明文添加到摘要计算信息
		md.update(salt);

		// 按SSHA把当前用户密码进行计算
		byte[] inputpwbyte = md.digest();

		// 返回校验结果
		return MessageDigest.isEqual(shacode, inputpwbyte);
	}

	// 添加用户
	public static boolean addUserLdap(String account, String password) {
		try {
			dirContext = LdapHelper.InitialDirContext();
			BasicAttributes attrsbu = new BasicAttributes();
			BasicAttribute objclassSet = new BasicAttribute("objectclass");
			objclassSet.add("person");//person对象类型要求cn（common name）和sn（surname）这两个域不能为空。
			objclassSet.add("top");
			objclassSet.add("organizationalPerson");
			objclassSet.add("inetOrgPerson");
			attrsbu.put(objclassSet);
			attrsbu.put("sn", account);
			attrsbu.put("uid", account);
			attrsbu.put("userPassword", password);
			dirContext.createSubcontext("cn=" + account + ",ou=People", attrsbu);
			dirContext.close();
			return true;
		} catch (NamingException ex) {
			try {
				if (dirContext != null) {
					dirContext.close();
				}
			} catch (NamingException namingException) {
				namingException.printStackTrace();
			}
			System.out.println("--------->>添加用户失败");
		}
		return false;
	}

	// 修改密码
	public static boolean updatePasswordLdap(String account, String password) {
		boolean success = false;
		try {
			dirContext = LdapHelper.InitialDirContext();
			ModificationItem[] modificationItem = new ModificationItem[1];
			modificationItem[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", password));
			dirContext.modifyAttributes("cn=" + account + ",ou=People", modificationItem);
			dirContext.close();
			return true;
		} catch (NamingException ex) {
			try {
				if (dirContext != null) {
					dirContext.close();
				}
			} catch (NamingException namingException) {
				namingException.printStackTrace();
			}
			System.out.println("--------->>修改密码失败");
		}
		return success;
	}

	// 删除用户
	public static boolean deleteUserLdap(String account) {
		try {
			dirContext = LdapHelper.InitialDirContext();
			dirContext.destroySubcontext("cn=" + account);
		} catch (Exception ex) {
			try {
				if (dirContext != null) {
					dirContext.close();
				}
			} catch (NamingException namingException) {
				namingException.printStackTrace();
			}
			System.out.println("--------->>删除用户失败");
			return false;
		}
		return true;
	}

	// 关闭LDAP服务器连接
	public static void closeLdapContext() {
		try {
			dirContext.close();
			System.out.println("--------->> 关闭LDAP连接");
		} catch (NamingException ex) {
			System.out.println("--------->> 关闭LDAP连接失败");
		}
	}
	
	
}
