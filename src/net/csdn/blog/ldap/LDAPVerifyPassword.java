package net.csdn.blog.ldap;

import java.io.UnsupportedEncodingException;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;


public class LDAPVerifyPassword {
	
	public static void main(String[] args) {

		String ldapHost = "192.168.1.100";
		String loginDN = "cn=Manager,dc=www,dc=im-ldap,dc=com";
		String password = "nufront";
		String verifyDN = "uid=addnew,ou=People,dc=www,dc=im-ldap,dc=com";
		String verifyPassword = "111111";

		int ldapPort = LDAPConnection.DEFAULT_PORT;

		int ldapVersion = LDAPConnection.LDAP_V3;
		LDAPConnection lc = new LDAPConnection();

		try {
			
			lc.connect(ldapHost, ldapPort);
			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
			LDAPAttribute attr = new LDAPAttribute("userPassword",
					verifyPassword);
			boolean correct = lc.compare(verifyDN, attr);
			System.out.println(correct ? "The password is correct.^_^"
					: "The password is incorrect.!!!");
		} catch (LDAPException e) {
			e.printStackTrace();
			if (e.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
				System.err.println("Error: No such entry");
			} else if (e.getResultCode() == LDAPException.NO_SUCH_ATTRIBUTE) {
				System.err.println("Error: No such attribute");
			} else {
				System.err.println("Error: " + e.toString());
			}
		} catch (UnsupportedEncodingException e) {
			System.err.println("Error: " + e.toString());
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
