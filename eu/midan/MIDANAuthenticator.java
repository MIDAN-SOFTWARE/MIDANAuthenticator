/********************************************//**
 * \file MIDANAuthenticator.java
 * \brief \c MIDANAuthenticator.java combines Crowd SSO and 
 * JIRA local database authorization.
 *
 * \author Sebastian Puschhof, MIDAN SOFTWARE GmbH
 * \version 1.0 MIDANAuthenticator.java, v1.0 2015/09/30 14:34 SPF
 *
 * \note Copyright &copy; 2015 MIDAN SOFTWARE GmbH
 *
 * This file is part of MIDANAuthenticator.
 *
 * MIDANAuthenticator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * MIDANAuthenticator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MIDANAuthenticator.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See COPYING file for the full LGPL text.
 ***********************************************/

package eu.midan;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.Principal;

import com.atlassian.seraph.config.SecurityConfig;
import com.atlassian.seraph.auth.DefaultAuthenticator;

import eu.midan.SSOAuthenticatorWrapper;
import eu.midan.JIRAAuthenticatorWrapper;

public class MIDANAuthenticator extends DefaultAuthenticator {

	private static final long serialVersionUID = 1L;
	
	private SSOAuthenticatorWrapper sso;
	private JIRAAuthenticatorWrapper jira;
	
	public MIDANAuthenticator() {
		sso = new SSOAuthenticatorWrapper();
		jira = new JIRAAuthenticatorWrapper();
	}
	
	public void init(Map<String, String> params, SecurityConfig config) {
		sso.init(params, config);
		jira.init(params, config);
		super.init(params, config);
	}
	
	public boolean login(HttpServletRequest request, HttpServletResponse response, String username, String password, boolean cookie) {
		boolean result = false;
		
		try {
			result = sso.login(request, response, username, password, cookie);
			if(result == false) {
				result = jira.login(request, response, username, password, cookie);
			}
		} catch(Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public boolean logout(HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		
		try {
			result = sso.logout(request, response);
			if(result == false) {
				result = jira.logout(request, response);
			}
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	protected Principal refreshPrincipalObtainedFromSession(HttpServletRequest httpServletRequest, Principal principal) {
		Principal user = sso.pubRefreshPrincipalObtainedFromSession(httpServletRequest, principal);
		
		if(user == null) {
			user = jira.pubRefreshPrincipalObtainedFromSession(httpServletRequest, principal);
		}
		
		return user;
	}
	
	protected boolean authenticate(Principal user, String password) {
		if(sso.pubAuthenticate(user, password)) {
			return true;
		} else if(jira.pubAuthenticate(user, password)) {
			return true;
		}
		
		return false;
	}
	
	public Principal getUser(HttpServletRequest request, HttpServletResponse response) {
		Principal user = jira.getUser(request, response);
		if(user == null) {
			user = sso.getUser(request, response); 
		}
		return user;
	}
	
	protected Principal getUser(String username) {
		Principal user = sso.pubGetUser(username);
		if(user == null) {
			user = jira.pubGetUser(username);
		}
		return user;
	}
}
