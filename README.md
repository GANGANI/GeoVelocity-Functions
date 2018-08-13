# GeoVelocity-Functions
Geo-velocity functions allows to authenticate the user by considering the distance and time gap between the last login and current login of the user.
## [](https://github.com/wso2/samples-is/tree/master/conditional-auth-sample#prerequisites)Prerequisites
-   [Maven](https://maven.apache.org/download.cgi)
-   [Java](http://www.oracle.com/technetwork/java/javase/downloads)
-   [WSO2 Identity Server](https://wso2.com/identity-and-access-management)
-   [Apache Tomcat server](https://tomcat.apache.org/download-80.cgi)
## Building the source

  - Get a clone or download source from this repository
  - Run the Maven command "mvn clean install" from within the distribution directory.

## Getting started

  1. Copy the jar file in the target directory to the <IS_HOME>/repository/components/dropins folder.
  2. Start the wso2 identity server
  3. Create a new service provider in Identity Server with the name “saml2-web-app-dispatch.com”. 
  4. You can find more information about creating new service provider [here](https://docs.wso2.com/display/IS560/Adding+and+Configuring+a+Service+Provider). 
5.  under Inbound Authentication Configuration, create a new SAML2 Web SSO configuration with following properties. 
					- Issuer - saml2-web-app-dispatch.com
					- Assertion Consumer URLs - http://localhost.com:8080/saml2-web-app-dispatch.com/consumer 
					- Keep the other default settings as it is and save the configuration.
6. Add two authentication steps .
7. Update  one of the script as follows                                                                                                        .
7.1. Script to authenticate user by geo-velocity
```sh
function onInitialRequest(context) {
  	
    executeStep(1, {
        onSuccess: function (context) {
            var user = context.steps[1].subject;
            var loginIp = context.request.ip;
		  
		  	//Get current location.
		  	var currentLocation = checkLocation("72.229.28.185").split(" ");
		  
		  	//Get last login location from location claim.
		  	var LastLocation = (user.localClaims['http://wso2.org/claims/location']).split(" ");
		  
		  	//Get distance between last and current login locations.
		  	var locationGap = distance(currentLocation[0], LastLocation[0], currentLocation[1], LastLocation[1]);

		  	//store current location in claim
		    user.localClaims['http://wso2.org/claims/location'] = checkLocation("72.229.28.185");
		  
		  	//Get login time.
		  	var currentLogonTime = Date.now();
		  
		  	//Get last login time using lastLogonTime claim.
		  	var lastLogonTime = user.localClaims['http://wso2.org/claims/identity/lastLogonTime'];
		  
		  	//Get time difference between two login locations
		  	var timeGap = Math.floor(currentLogonTime - lastLogonTime);
		  
		  	//Store current login time in LogonTime claim
		  	user.localClaims['http://wso2.org/claims/identity/lastLogonTime'] = currentLogonTime;

		  	//Get velocity between two logins
		  	var velocity = locationGap/timeGap*3600000;
		  	Log.info('\n'+'Distance = '+ locationGap+' Km'+'\n'+'Time Difference = '+timeGap+' ms'+'\n'+'Geo-velocity= '+velocity+' Km/h');
		  
		  	// Checking if the velocity is within the allowed range
            if (velocity >= 100) {
                executeStep(2);
			  	Log.info('Your login is suspicious ');

            }
		  	else{
			  	Log.info('Successfully login ');
			}
        }
    });
}
```
7.2.  Script to check the browser
```sh
function onInitialRequest(context) {

   Log.info("--------------- user-agent:." + context.request.headers["user-agent"]);
  
executeStep(1, {
      onSuccess: function (context) {

             if (checkBrowser(context.request.headers["user-agent"]) == 'chrome') {
                   Log.info("--------------- Authorized user-agent detected.");
               } else {
                   executeStep(2);
               }
           }
   });
}
```
9. Start the tomcat server.
10. Try out single sign on flow.

Note:-Please add the host names used for the applications to your etc/hosts file. You can find the needed host names through the property files. Addition to that, use the call back urls in the property files when configuring inbound protocols for each service providers

