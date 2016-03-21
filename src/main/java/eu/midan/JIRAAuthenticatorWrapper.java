/********************************************//**
 * \file JIRAAuthenticatorWrapper.java
 * \brief \c JIAAuthenticatorWrapper.java provides a wrapper for 
 * the JIRA Seraph authenticator.
 *
 * \author Sebastian Puschhof, MIDAN SOFTWARE GmbH
 * \version 1.0 JIRAAuthenticatorWrapper.java, v1.0 2015/09/30 13:35 SPF
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

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.atlassian.jira.security.login.JiraSeraphAuthenticator;
import com.atlassian.seraph.auth.AuthenticatorException;

public class JIRAAuthenticatorWrapper extends JiraSeraphAuthenticator {

	private static final long serialVersionUID = 1L;
	
	public boolean pubAuthenticate(Principal paramPrincipal, String paramString) {
		try {
			return super.authenticate(paramPrincipal, paramString);
		} catch (AuthenticatorException e) {
			return false;
		}
	}
	
	public Principal pubGetUser(String paramString) {
		return super.getUser(paramString);
	}
	
	public Principal pubRefreshPrincipalObtainedFromSession(HttpServletRequest httpServletRequest, Principal principal) {
		return super.refreshPrincipalObtainedFromSession(httpServletRequest, principal);
	}
}
