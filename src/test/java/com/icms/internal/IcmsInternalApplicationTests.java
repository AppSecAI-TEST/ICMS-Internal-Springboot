package com.icms.internal;


import com.icms.internal.usermanagement.repository.UserManagementRepository;
import com.unboundid.ldap.sdk.LDAPConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IcmsInternalApplicationTests {

	@Autowired
	UserManagementRepository userManagementRepository;

	@Test
	public void contextLoads() throws Exception
	{
//        LDAPConnection ldapConnection = new LDAPConnection("info-srv11.infocepts.com",389,"rragashe","Wei3d.4pps");
//
//        if(ldapConnection.isConnected()){
//            System.out.println("Connected");
//        } else {
//            System.out.println("Not connected");
//        }

	}

}
