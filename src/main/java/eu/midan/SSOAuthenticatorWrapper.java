/********************************************//**
 * \file SSOAuthenticatorWrapper.java
 * \brief \c SSOAuthenticatorWrapper.java provides a wrapper for 
 * the Crowd SSO authenticator.
 *
 * \author Sebastian Puschhof, MIDAN SOFTWARE GmbH
 * \version 1.0 SSOAuthenticatorWrapper.java, v1.0 2015/09/30 13:49 SPF
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

import com.atlassian.jira.security.login.SSOSeraphAuthenticator;

public class SSOAuthenticatorWrapper extends SSOSeraphAuthenticator {

	private static final long serialVersionUID = 1L;
	
	public boolean pubAuthenticate(Principal paramPrincipal, String paramString) {
		return super.authenticate(paramPrincipal, paramString);
	}
	
	public Principal pubGetUser(String paramString) {
		return super.getUser(paramString);
	}
	
	public Principal pubRefreshPrincipalObtainedFromSession(HttpServletRequest httpServletRequest, Principal principal) {
		return super.refreshPrincipalObtainedFromSession(httpServletRequest, principal);
	}
}
