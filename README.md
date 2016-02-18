# MIDANAuthenticator
MIDANAuthenticator combines Crowd SSO and JIRA local database authorization.

## Files
- MIDANAuthenticator.java combines Crowd SSO and JIRA local database authorization.
- JIAAuthenticatorWrapper.java provides a wrapper for the JIRA Seraph authenticator.
- SSOAuthenticatorWrapper.java provides a wrapper for the Crowd SSO authenticator.

## Installation
1. Copy the 3 class files to [JIRA ROOT]\atlassian-jira\WEB-INF\classes\eu\midan\
2. Enable the MIDANAuthenticator in your seraph-config.xml and comment out all other authenticators.

```xml
<!-- CROWD:START - If enabling Crowd SSO integration uncomment the following SSOSeraphAuthenticator and comment out the JiraSeraphAuthenticator below -->
<!--<authenticator class="com.atlassian.jira.security.login.SSOSeraphAuthenticator"/>-->
<!-- CROWD:END -->

<!-- CROWD:START - The authenticator below here will need to be commented out for Crowd SSO integration -->
<!--<authenticator class="com.atlassian.jira.security.login.JiraSeraphAuthenticator"/>-->
<!-- CROWD:END -->
	
<!-- CROWD:START - The authenticator below here will need to be commented out for Crowd SSO integration -->
<authenticator class="eu.midan.MIDANAuthenticator"/>
<!-- CROWD:END -->
```

## Compiling
The following Atlassian libraries and class folders need to be linked:

- WEB-INF\lib\atlassian-seraph-2.6.4.jar
- WEB-INF\classes\
- WEB-INF\lib\crowd-integration-seraph25-2.8.3.jar
- WEB-INF\lib\embedded-crowd-api-2.8.3.jar
- WEB-INF\lib\jira-api-6.4.11.jar