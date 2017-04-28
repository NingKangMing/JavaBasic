package net.csdn.blog.ldap;

import java.io.UnsupportedEncodingException;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;



public class LDAPAddDemo {
	
	public static void main(String[] args) {

		String ldapHost = "192.168.1.100";
		String loginDN = "cn=Manager,dc=im-ldap,dc=com";
		String password = "nufront";
		String containerName = "dc=im-ldap,dc=com";

		int ldapPort = LDAPConnection.DEFAULT_PORT;
		int ldapVersion = LDAPConnection.LDAP_V3;
		LDAPConnection lc = new LDAPConnection();
		LDAPAttributeSet attributeSet = new LDAPAttributeSet();

		attributeSet.add(new LDAPAttribute("objectclass", new String("inetOrgPerson")));
		attributeSet.add(new LDAPAttribute("cn", "KangMing Ning"));
		attributeSet.add(new LDAPAttribute("sn", "Ning"));
		attributeSet.add(new LDAPAttribute("mail", "kangming.ning@nufront.com"));
		attributeSet.add(new LDAPAttribute("labeledURI",
				"http://www.nufront.com"));
		attributeSet.add(new LDAPAttribute("userPassword", "123456"));
		attributeSet.add(new LDAPAttribute("uid", "km1"));
		String dn = "uid=km1,ou=IMgroup,"+containerName;
		LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
		try {
			lc.connect(ldapHost, ldapPort);
			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
			System.out.println("login ldap server successfully.");
			lc.add(newEntry);
			System.out.println("Added object: " + dn + " successfully.");
		} catch (LDAPException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error: " + e.toString());
		} finally {
			try {
				if (lc.isConnected()) {
					lc.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
