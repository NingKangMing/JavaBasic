package net.csdn.blog.ldap;

import java.io.UnsupportedEncodingException;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;

/** 
 * �ṩldap�û���֤���û�����Ĺ���
 * @author kangming.ning 
 * @version 2017-04-28
 */
public class LdapHelper {
	
	private String ldapHost = "192.168.1.100";//ldap��������ַ
	
	private String loginDN = "cn=Manager,dc=www,dc=im-ldap,dc=com";//����ldap��������Ա��dn
	
	private String password = "nufront";//ldap��������Ա������
	
	private String rootDn = "dc=im-ldap,dc=com";//����ldap�������ĸ��ڵ�
	
	private int ldapPort = LDAPConnection.DEFAULT_PORT;//ldap�˿ں� 389Ϊδ���ܶ˿� 636Ϊssl���ܶ˿�
	
	private int ldapVersion = LDAPConnection.LDAP_V3;//ldap�汾��
	
	private LDAPConnection lconn;
	
	private LDAPConnection getConnetion(){
		if (null!=lconn&&lconn.isConnectionAlive()) {
			return lconn;
		}else{
			lconn = new LDAPConnection();
			try {
				lconn.connect(ldapHost, ldapPort);
				lconn.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
				System.out.println("login ldap server successfully.");
			} catch (LDAPException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				System.out.println("Error: " + e.toString());
			}
			return lconn;
		}
	}
	
	/**
	 * ���ldap�˻�
	 * */
	public boolean addAccount(String account, String password) {
		
		LDAPAttributeSet attributeSet = new LDAPAttributeSet();

		attributeSet.add(new LDAPAttribute("objectclass", new String(
				"inetOrgPerson")));
		attributeSet.add(new LDAPAttribute("cn", "KangMing Ning"));
		attributeSet.add(new LDAPAttribute("sn", "Ning"));
		attributeSet.add(new LDAPAttribute("mail", "kangming.ning@nufront.com"));
		attributeSet.add(new LDAPAttribute("labeledURI",
				"http://www.nufront.com"));
		attributeSet.add(new LDAPAttribute("userPassword", "123456"));
		attributeSet.add(new LDAPAttribute("uid", "km1"));
		String dn = "uid=km,ou=People,"+rootDn;
		LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
		try {
			getConnetion().add(newEntry);
		} catch (LDAPException e) {
			e.printStackTrace();
			if (e.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
				System.err.println("Error: No such entry");
			} else if (e.getResultCode() == LDAPException.NO_SUCH_ATTRIBUTE) {
				System.err.println("Error: No such attribute");
			} else {
				System.err.println("Error: " + e.toString());
			}
			return false;
		}
		System.out.println("Added object: " + dn + " successfully.");
		return true;
	}
	
	/**
	 * ���ldap�˻�
	 * */
	public boolean deleteAccount(String account) {
		String deleteDN = "uid=km,ou=People,dc=www,dc=im-ldap,dc=com";
		try {
			getConnetion().delete(deleteDN);
		} catch (LDAPException e) {
			e.printStackTrace();
			if (e.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
				System.err.println("Error: No such entry");
			} else if (e.getResultCode() == LDAPException.NO_SUCH_ATTRIBUTE) {
				System.err.println("Error: No such attribute");
			} else {
				System.err.println("Error: " + e.toString());
			}
			return false;
		}
		System.out.println(" delete Entry: " + deleteDN + " success.");
		
		return true;
	}
	
	/**
	 * ��֤ldap�˻����û����������Ƿ�ƥ��
	 * */
	public boolean verifyAccount(String account, String password){
		
		String verifyDN = "uid=addnew,ou=People,dc=www,dc=im-ldap,dc=com";
		LDAPAttribute attr = new LDAPAttribute("userPassword",password);
		boolean correct;
		try {
			correct = getConnetion().compare(verifyDN, attr);
			System.out.println(correct ? "The password is correct.^_^": "The password is incorrect.!!!-_-");
		} catch (LDAPException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	

}
