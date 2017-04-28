package net.csdn.blog.ldap;

import java.io.UnsupportedEncodingException;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;


//http://blog.csdn.net/wuyupengwoaini/article/details/12856415
public class LDAPDeleteEntry {
	
	public static void main(String[] args) {

		String ldapHost = "192.168.1.100";
		String loginDN = "cn=Manager,dc=www,dc=im-ldap,dc=com";
		String password = "nufront";
		String deleteDN = "uid=km,ou=People,dc=www,dc=im-ldap,dc=com";
		//String deleteDN = "cn=nkmtest1ou=People1,dc=www,dc=im-ldap,dc=com";

		int ldapPort = LDAPConnection.DEFAULT_PORT;
		int ldapVersion = LDAPConnection.LDAP_V3;
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect(ldapHost, ldapPort);
			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));

			lc.delete(deleteDN);
			System.out.println(" delete Entry: " + deleteDN + " success.");
			lc.disconnect();
		} catch (LDAPException e) {
			if (e.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
				System.err.println("Error: No such object");
			} else if (e.getResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
				System.err.println("Error: Insufficient rights");
			} else {
				System.err.println("Error: " + e.toString());
			}
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
