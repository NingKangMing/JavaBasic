package net.csdn.blog.ldap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;



public class LDAPModifyAttrs {

	public static void main(String[] args) {

		String ldapHost = "192.168.1.100";
		String loginDN = "cn=Manager,dc=www,dc=im-ldap,dc=com";
		String password = "nufront";
		String modifyDN = "uid=addnew,ou=People,dc=www,dc=im-ldap,dc=com";

		int ldapPort = LDAPConnection.DEFAULT_PORT;
		int ldapVersion = LDAPConnection.LDAP_V3;
		LDAPConnection lc = new LDAPConnection();

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
			lc.connect(ldapHost, ldapPort);
			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
			lc.modify(modifyDN, mods);
			System.out.println("LDAPAttribute add¡¢replace¡¢delete all successful.");
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
