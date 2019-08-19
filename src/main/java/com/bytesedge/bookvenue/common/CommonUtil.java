package com.bytesedge.bookvenue.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.UserRole;

public class CommonUtil {
	public static final String SCOPE_PREFIX = "ROLE_SCOPE_";
	
	public static Collection<GrantedAuthority> getGrantedAuthorityUserScopeByOrgId(Organization organization) throws Exception {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		if(organization != null) {
			list.add(new SimpleGrantedAuthority(SCOPE_PREFIX + organization.getName()));
		}
		return (Collection<GrantedAuthority>) list;
	}	
	
	public static Collection<GrantedAuthority> getGrantedAuthorityByUserRole(UserRole role) throws Exception {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		if(role != null) {
			if(role.getAdmin().equals(Boolean.TRUE)) {
				list.add(new SimpleGrantedAuthority(UserRole.ADMIN));
			}
		}
		return (Collection<GrantedAuthority>) list;
	}	
	
}