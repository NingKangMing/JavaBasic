package net.csdn.blog.ldap;

import java.security.MessageDigest;
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
 * �û���½��֤,LDAP������֤��ͨ��LDAP���û����и��� <br>
 * DN:Distinguished Name <br>
 * dc:Domain Component <br>
 * OU:Organization Unit <br>
 * cn:Common Name<br>
 * uid:user Id<br>
 * sn:surname,����
 * @author kangming.ning 
 * 
 */
public class LdapHelperDemo {

	private static DirContext dirContext;

	// LDAP�������˿�Ĭ��Ϊ389
	private static final String LDAP_URL = "ldap://192.168.1.100:389/";
	
	private static final String DOMAIN="dc=im-ldap,dc=com";

	// LDAP����
	private static final String LDAP_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	
	private static String account = "Manager"; // ldap����Ա�˺ţ��б༭�û�Ȩ�ޣ���ͨ�û�û�����Ȩ��
	private static String password = "nufront"; //����
	
   public static void main(String[] args){
		
	   //addUserLdap("nkmtest1","123456");//ok
	  // deleteUserLdap("cn=nkmtest"); //
	  InitialDirContext(); //ok
	  // verifyUser("nkmtest","123456");//ok
	}

	//ͨ������LDAP���������û�������֤������LDAP����
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DirContext InitialDirContext() {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_FACTORY);
		env.put(Context.PROVIDER_URL, LDAP_URL+DOMAIN);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn="+account+",dc=im-ldap,dc=com");//����ldap����Ա�����Զ��û����б༭
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			// ����LDAP������֤
			dirContext = new InitialDirContext(env);
			System.out.println("initial dircontext success");
		} catch (javax.naming.AuthenticationException e) {
			System.out.println("initial fail");
		} catch (NamingException err) {
			err.printStackTrace();
		} catch (Exception e) {
			System.out.println("��������Ա��֤����");
			e.printStackTrace();
		}
		return dirContext;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean verifyUser(String account1,String pwd1) {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_FACTORY);
		env.put(Context.PROVIDER_URL, LDAP_URL+DOMAIN);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn="+account1+",ou=People,dc=www,dc=im-ldap,dc=com");//������ͨ�û������ܶ��û���Ϣ���б༭��
		env.put(Context.SECURITY_CREDENTIALS, pwd1);

		try {
			// ����LDAP������֤
			dirContext = new InitialDirContext(env);
			System.out.println("verifyUser success");
			return true;
		} catch (javax.naming.AuthenticationException e) {
			System.out.println("initial fail");
		} catch (NamingException err) {
			err.printStackTrace();
		} catch (Exception e) {
			System.out.println("��֤����");
			e.printStackTrace();
		}
		
		return false;
		
		
	}
	
	/**
	 * �жϽ����������ldap�е��û��������ƥ���жϡ���Ϊldap�е��û������Ǿ���SSHAɢ�еģ���˱��뽫����ת��ΪSSHA����ܹ�����ƥ�䡣
	 * */
	public static boolean verifySHA(String ldappw, String inputpw) throws Exception {

		// MessageDigest �ṩ����ϢժҪ�㷨���� MD5 �� SHA���Ĺ��ܣ�����LDAPʹ�õ���SHA-1
		MessageDigest md = MessageDigest.getInstance("SHA-1");

		// ȡ�������ַ�
		if (ldappw.startsWith("{SSHA}")) {
			ldappw = ldappw.substring(6);
		} else if (ldappw.startsWith("{SHA}")) {
			ldappw = ldappw.substring(5);
		}

		// ����BASE64
		byte[] ldappwbyte = Base64.decode(ldappw);
		byte[] shacode;
		byte[] salt;

		// ǰ20λ��SHA-1���ܶΣ�20λ�����������ʱ���������
		if (ldappwbyte.length <= 20) {
			shacode = ldappwbyte;
			salt = new byte[0];
		} else {
			shacode = new byte[20];
			salt = new byte[ldappwbyte.length - 20];
			System.arraycopy(ldappwbyte, 0, shacode, 0, 20);
			System.arraycopy(ldappwbyte, 20, salt, 0, salt.length);
		}

		// ���û������������ӵ�ժҪ������Ϣ
		md.update(inputpw.getBytes());
		// �����������ӵ�ժҪ������Ϣ
		md.update(salt);

		// ��SSHA�ѵ�ǰ�û�������м���
		byte[] inputpwbyte = md.digest();

		// ����У����
		return MessageDigest.isEqual(shacode, inputpwbyte);
	}

	// ����û�
	public static boolean addUserLdap(String account, String password) {
		try {
			dirContext = LdapHelperDemo.InitialDirContext();
			BasicAttributes attrsbu = new BasicAttributes();
			BasicAttribute objclassSet = new BasicAttribute("objectclass");
			objclassSet.add("person");//person��������Ҫ��cn��common name����sn��surname������������Ϊ�ա�
			objclassSet.add("top");
			objclassSet.add("organizationalPerson");
			objclassSet.add("inetOrgPerson");
			attrsbu.put(objclassSet);
			attrsbu.put("sn", account);
			attrsbu.put("uid", account);
			attrsbu.put("ou", "People1");
			attrsbu.put("userPassword", password);
			dirContext.createSubcontext("cn=" + account + ",ou=People", attrsbu);
			dirContext.close();
			System.out.println("ok");
			return true;
		} catch (NamingException ex) {
			try {
				if (dirContext != null) {
					dirContext.close();
				}
			} catch (NamingException namingException) {
				namingException.printStackTrace();
			}
			ex.printStackTrace();
			System.out.println("--------->>����û�ʧ��");
		}
		return false;
	}

	// �޸�����
	public static boolean updatePasswordLdap(String account, String password) {
		boolean success = false;
		try {
			dirContext = LdapHelperDemo.InitialDirContext();
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
			System.out.println("--------->>�޸�����ʧ��");
		}
		return success;
	}

	// ɾ���û�
	public static boolean deleteUserLdap(String dn) {
		try {
			dirContext = LdapHelperDemo.InitialDirContext();
			dirContext.destroySubcontext(dn);
			System.out.println("--------->>ɾ��ok");
		}
		catch (javax.naming.NameNotFoundException e) {
			System.out.println("LDAP: error code 32 - No Such Object");
			e.printStackTrace();
		}
		catch (Exception ex) {
			try {
				if (dirContext != null) {
					dirContext.close();
				}
			} catch (NamingException namingException) {
				namingException.printStackTrace();
			}
			System.out.println("--------->>ɾ���û�ʧ��");
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	// �ر�LDAP����������
	public static void closeLdapContext() {
		try {
			dirContext.close();
			System.out.println("--------->> �ر�LDAP����");
		} catch (NamingException ex) {
			System.out.println("--------->> �ر�LDAP����ʧ��");
		}
	}
	
	
}
