package net.csdn.blog.ldap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;
import com.novell.ldap.util.Base64;

/** 
 * �ṩldap�û���֤���û�����Ĺ���
 * @author kangming.ning 
 * @version 2017-04-28
 */
public class LdapHelper {
	
	private static String ldapHost = "192.168.1.100";//ldap��������ַ
	
	private static String loginDN = "cn=Manager,dc=im-ldap,dc=com";//����ldap��������Ա��dn
	
	private static String password = "nufront";//ldap��������Ա������
	
	private static  String rootDn = "dc=im-ldap,dc=com";//����ldap�������ĸ��ڵ�
	
	private static int ldapPort = LDAPConnection.DEFAULT_PORT;//ldap�˿ں� 389Ϊδ���ܶ˿� 636Ϊssl���ܶ˿�
	
	private static int ldapVersion = LDAPConnection.LDAP_V3;//ldap�汾��
	
	private static LDAPConnection lconn;
	
	private static LdapHelper helper;
	
	private LdapHelper(){
		
	}
	public static LdapHelper getHelper(){
		if (null!=helper) {
			return helper;
		}else {
			helper=new LdapHelper();
			return helper;
		}
	}
	private static LDAPConnection getConnetion(){
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
	
	private static void closeConnection(){
		try {
			if (lconn.isConnected()) {
				lconn.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ldap�˻�
	 * */
	public static boolean addAccount(String account, String password) {
		
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
		}finally{
			closeConnection();
		}
		System.out.println("Added object: " + dn + " successfully.");
		return true;
	}
	
	public static boolean modify(){
		
		String modifyDN = "uid=addnew,ou=People,dc=im-ldap,dc=com";
		
		List<LDAPModification> modList = new ArrayList<LDAPModification>();

		// Add a new value to the description attribute
		String desc = "This object was modified at " + new Date();
		LDAPAttribute attribute = new LDAPAttribute("description", desc);
		modList.add(new LDAPModification(LDAPModification.ADD, attribute));

		attribute = new LDAPAttribute("telephoneNumber", "180-8888-xxxx");
		modList.add(new LDAPModification(LDAPModification.ADD, attribute));

		// Replace the labeledURI address with a new value
		attribute = new LDAPAttribute("labeledURI", "www.micmiu.com1");
		modList.add(new LDAPModification(LDAPModification.REPLACE, attribute));

		// delete the email attribute
		attribute = new LDAPAttribute("mail");
		modList.add(new LDAPModification(LDAPModification.DELETE, attribute));

		LDAPModification[] mods = new LDAPModification[modList.size()];
		mods = (LDAPModification[]) modList.toArray(mods);

		try {
			getConnetion().modify(modifyDN, mods);
		} catch (LDAPException e) {
			e.printStackTrace();
			return false;
		}finally{
			closeConnection();
		}
		System.out.println("LDAPAttribute add��replace��delete all successful.");
		return true;
	}
	
	/**
	 * ���ldap�˻�
	 * */
	public boolean deleteAccount(String account) {
		String deleteDN = "uid=km,ou=People,dc=im-ldap,dc=com";
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
		}finally{
			closeConnection();
		}
		System.out.println(" delete Entry: " + deleteDN + " success.");
		
		return true;
	}
	
	/**
	 * ��֤ldap�˻����û����������Ƿ�ƥ��
	 * */
	public boolean verifyAccount(String account, String password){
		
		String verifyDN = "uid=addnew,ou=People,dc=im-ldap,dc=com";
		LDAPAttribute attr = new LDAPAttribute("userPassword",password);
		boolean correct;
		try {
			correct = getConnetion().compare(verifyDN, attr);
			System.out.println(correct ? "The password is correct.^_^": "The password is incorrect.!!!-_-");
		} catch (LDAPException e) {
			e.printStackTrace();
			return false;
		}finally{
			closeConnection();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static void searchDn(){
		String searchBase = "dc=im-ldap,dc=com";
		String searchFilter = "objectClass=*";
		// ��ѯ��Χ
		// SCOPE_BASE��SCOPE_ONE��SCOPE_SUB��SCOPE_SUBORDINATESUBTREE
		int searchScope = LDAPConnection.SCOPE_SUB;
		LDAPSearchResults searchResults = null;
		try {
			searchResults = getConnetion().search(searchBase,searchScope, searchFilter, null, false);
		} catch (LDAPException e1) {
			e1.printStackTrace();
		}
        if (null==searchResults) {
			System.out.println("no result found!");
		}
		while (searchResults.hasMore()) {
			LDAPEntry nextEntry = null;
			try {
				nextEntry = searchResults.next();
			} catch (LDAPException e) {
				System.out.println("Error: " + e.toString());
				if (e.getResultCode() == LDAPException.LDAP_TIMEOUT
						|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
					break;
				} else {
					continue;
				}
			}
			System.out.println("DN =: " + nextEntry.getDN());
			System.out.println("|---- Attributes list: ");
			LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
			Iterator<LDAPAttribute> allAttributes = attributeSet.iterator();
			while (allAttributes.hasNext()) {
				LDAPAttribute attribute = allAttributes.next();
				String attributeName = attribute.getName();

				Enumeration<String> allValues = attribute.getStringValues();
				if (null == allValues) {
					continue;
				}
				while (allValues.hasMoreElements()) {
					String value = allValues.nextElement();
					if (!Base64.isLDIFSafe(value)) {
						// base64 encode and then print out
						value = Base64.encode(value.getBytes());
					}
					System.out.println("|---- ---- " + attributeName
							+ " = " + value);
				}
			}
		}
	}
	

}
