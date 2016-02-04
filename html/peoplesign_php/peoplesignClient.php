<?php
#Copyright 2008-2010 Myricomp LLC All rights reserved
#
#   *****email bugs@peoplesign.com to report a problem*****
#

#Note:  If you get a php host lookup error (for the peoplesign host),
#       please try explicitly stopping then staring your webserver.
#       e.g. if your webserver is Apache running on linux, login as root
#       to a command shell and execute 
#       "apachectl stop" and then "apachectl start".
#       (on some systems this is "apache2ctl stop/start")
#       "apachectl restart" won't fix the problem for some strange reason.

#WARNING:  changing these constants may result in errors
define('PEOPLESIGN_PLUGIN_PHP_VERSION', 'php_1.5.3');

define( 'PEOPLESIGN_HOST', 'peoplesign.com');

define( 'PEOPLESIGN_GET_CHALLENGE_SESSION_ID_PATH',
	'/main/getChallengeSessionID');

define( 'PEOPLESIGN_CHALLENGE_SESSION_ID_NAME',
	'challengeSessionID');

define( 'PEOPLESIGN_GET_CHALLENGE_SESSION_ID_URL',
	'http://' .PEOPLESIGN_HOST .PEOPLESIGN_GET_CHALLENGE_SESSION_ID_PATH);

define ('PEOPLESIGN_CHALLENGE_URL',
	'http://' .PEOPLESIGN_HOST.'/main/challenge.html');

define( 'PEOPLESIGN_GET_CHALLENGE_SESSION_STATUS_PATH',
	'/main/getChallengeSessionStatus_v2');

define( 'PEOPLESIGN_GET_CHALLENGE_SESSION_STATUS_URL', 'http://' 
        .PEOPLESIGN_HOST .PEOPLESIGN_GET_CHALLENGE_SESSION_STATUS_PATH);
define( 'PEOPLESIGN_CHALLENGE_RESPONSE_NAME', 'captcha_peoplesignCRS');

define( 'CONNECTION_OPEN_TIMEOUT', 5 );
define( 'CONNECTION_READ_TIMEOUT', 10 );

define( 'PEOPLESIGN_IFRAME_WIDTH', '335');
define( 'PEOPLESIGN_IFRAME_HEIGHT','335');

include ('peoplesignMessages.php');
include ('peoplesignUtilities.php');

######################
#getPeoplesignHTML
######################

#usage notes
##-insert the return value of this function in the middle of a form you
## want to protect using peoplesign AND your php server is configured to 
## use sessions

#description
##-starts a new session if one is not already started
##-calls getPeoplesignSessionID and writes the result to a session variable
##-calls getPeoplesignHTMLJavaScript, passes return value

#parameters
##1)peoplesignKey - obtained by registerin at peoplesign.com

##2)(optional)peoplesignOptions 
###-read the comments in the example file that comes with this plugin for more
### details.  Or visit the customize page on peoplesign.com.

##3)(optional)clientLocation
###-A string that describes the page where you're placing peoplesign.
### e.g. "registrationForm" or "userComment"

##4)(optional)wrapperPluginVersionInfo
###-You probably don't need to set this.
###-If you've written a wrapper around this plugin, please pass info about it
### in the form of name_versionNum. e.g. "myWrapper_1.0"

##5-6)(optional)iframeWidth/Height
###-Don't worry about this unless you're a perfectionist.  If a broswer 
### has javascript disabled (which is rare), the peoplesign challenge will be 
### sent in an iframe having the specified width/height.

#return value
##-HTML string to be sent to browser

function getPeoplesignHTML ($peoplesignKey, $peoplesignOptions,
                            $clientLocation="default", 
                            $wrapperPluginVersionInfo='',
			    $iframeWidth=PEOPLESIGN_IFRAME_WIDTH,
			    $iframeHeight=PEOPLESIGN_IFRAME_HEIGHT) {

    $peoplesignHTML = "";
    $peoplesignSessionID = "";
    $status = "";

    if (!session_id()){
       if (!session_start()){
          return ("<p>" . ERROR_BAD_CONFIG . "</p>");
       }
    }
    
    $elapsedTime = 0;

    if (isset($_SESSION[$clientLocation ."_peoplesignSessionID"])) {
        $peoplesignSessionID = 
           $_SESSION[$clientLocation ."_peoplesignSessionID"];
    }

    $response = getPeoplesignSessionID( 
	 $peoplesignKey, 
	 $_SERVER['REMOTE_ADDR'], 
	 $peoplesignOptions,
	 $clientLocation,
	 $wrapperPluginVersionInfo,
	 $peoplesignSessionID
    );
    
    $status = $response[0];
    $peoplesignSessionID = $response[1];

    $_SESSION[$clientLocation ."_peoplesignSessionID"] = $peoplesignSessionID;

    if ($status == "success") {
	$peoplesignHTML = getPeoplesignHTMLJavaScript($peoplesignSessionID,
                                                  $iframeWidth,$iframeHeight);
	$_SESSION[$clientLocation."_isDisabled"]="false";
    } else {
      	#If we could not successfully obtain a challenge session id, we cannot
        #administer peoplesign to the user.  This usually indicates a problem
        #contacting the peoplesign server, invalid arguments passed to
        #getChallengeSessionID (the webservice),
        #or problems on the peoplesign server. Whatever
        #the reason, we must disable peoplesign at this location so users
        #may still proceed.
	printError(ERROR_UNAVAILABLE . " ($status)");
	$peoplesignHTML = "<p>" . ERROR_UNAVAILABLE . "</p>";
	$_SESSION[$clientLocation."_isDisabled"]="true";
    
    }

    return $peoplesignHTML;
}


######################
#isPeoplesignResponseCorrect
######################

#usageNotes
##-call this when processing the user's response to peoplesign if you setup 
## peoplesign with getPeoplesignHTML

#description
##-looks for peoplesignSessionID and peoplesignResponseString in the _POST
## array unless you pass them as arguments.
##-calls processPeoplesignResponse

#parameters
##1)peoplesignSessionID
###-get this from _REQUEST[PEOPLESIGN_CHALLENGE_SESSION_ID_NAME] 
### when processing the form
### submission that included the peoplesign HTML.  It should have been passed
### as a hidden input. If you pass null or empty string, the routine will
### attempt to find it. 

##2)peoplesignResponseString
###-get this from _REQUEST[PEOPLESIGN_CHALLENGE_RESPONSE_NAME] when processing
### the form submission
### that included the peoplesign HTML.  It should have been passed as a hidden
### input.
###-if you are using getPeoplesignHTMLIFrame (rare)
### _REQUEST[PEOPLESIGN_CHALLENGE_RESPONSE_NAME] won't be set, but the user's 
### browser will have already sent it to the peoplesign server.

##3)client Location
###-MUST match the argument passed to getPeoplesignHTML.

##4)peoplesignKey
###-obtain your key from peoplesign.com

#return value
##1 or 0

function isPeoplesignResponseCorrect ($peoplesignSessionID,
                      $peoplesignResponseString,
                      $clientLocation="default",
                      $peoplesignKey) {
  $status = "";
  $elapsedTime = 0;


  if (!session_id()){
     if (!session_start()){
       $status = "sessionStartFail";
       printError(ERROR_BAD_CONFIG . " ($status)");
       return 0;
     }
  }

  #The passed in value for peoplesignSessionID has highest priority.
  #Try to find it in the HTTP Post/Get if it's not present

  if (!$peoplesignSessionID){
     $peoplesignSessionID = getPostVar(PEOPLESIGN_CHALLENGE_SESSION_ID_NAME, '');
  }

  if (!$peoplesignResponseString) {
     $peoplesignResponseString = getPostVar(PEOPLESIGN_CHALLENGE_RESPONSE_NAME, '');
  }

  if ($_SESSION[$clientLocation."_isDisabled"] == "true") {
     return 1;
  }

  $allowPass = processPeoplesignResponse($peoplesignSessionID,
				 $peoplesignResponseString,
				 $clientLocation,
				 $peoplesignKey);

  return $allowPass;  
}
######################
#processPeoplesignResponse
######################

#usageNotes
##-use this (instead of isPeoplesignResponseCorrect) if your php server 
## is not setup with the standard session library.  
##-use the return value (an array) to determine if the 
## user's response is correct.

#description
##-calls getPeoplesignSessionStatus (returns a string)
##-decides if the user's response is correct based on the
## status string.  Note: the response is treated as "correct"
## if the peoplesign server did not respond.

#parameters
##1)peoplesignSessionID
###-get this from _REQUEST[PEOPLESIGN_CHALLENGE_SESSION_ID_NAME] 
### when processing the form
### submission that included the peoplesign HTML.  It should have been passed
### as a hidden input.

##2)peoplesignResponseString
###-get this from _REQUEST[PEOPLESIGN_CHALLENGE_RESPONSE_NAME] when processing
### the form submission
### that included the peoplesign HTML.  It should have been passed as a hidden
### input.
###-if you are using getPeoplesignHTMLIFrame (rare)
### _REQUEST[PEOPLESIGN_CHALLENGE_RESPONSE_NAME] won't be set, and you can pass
### null or empty string for this value when calling getPeoplesignSessionStatus

##3)client Location
###-MUST match the argument passed to getPeoplesignHTML.

##4)peoplesignKey
###-obtain your key from peoplesign.com

#return value
##allowPass - 1 or 0 

function processPeoplesignResponse ($peoplesignSessionID,
                      $peoplesignResponseString,
                      $clientLocation="default",
                      $peoplesignKey) {

  if (!$peoplesignSessionID){
     $peoplesignSessionID = getPostVar(PEOPLESIGN_CHALLENGE_SESSION_ID_NAME, '');
  }

  if (!$peoplesignResponseString) {
     $peoplesignResponseString = getPostVar(PEOPLESIGN_CHALLENGE_RESPONSE_NAME, '');
  }

  $status = getPeoplesignSessionStatus($peoplesignSessionID,
                                     $peoplesignResponseString,
                                     $clientLocation,
                                     $peoplesignKey);

  $allowPass = 0;

  #storePSSID is not currently used
  $storePSSID = 0;
  
  if ($status == "pass") {
     $allowPass = 1;
     $storePSSID = 0;
  } else if ($status == "fail" || ($status == "notRequested")  ||
            ($status == "awaitingResponse") ||
	    ($status == "invalidChallengeSessionID") ) {

     $allowPass = 0;
     $storePSSID = 1;
     
     #If $status is invalidChallengeSessionID we can not allow the user to pass.
     #It's highly unusual for this to occur, and probably means the
     #peoplesignSession expired and the client session was still alive.
     #We now abandon this client session.  This will trigger a new client 
     #session and a new peoplesign session.
     if ($status == "invalidChallengeSessionID") {
     	$storePSSID = 0;
     }
  } else {
     printError(ERROR_UNEXPECTED . " ($status)");
     #if we have an unexpected status from the peoplesign web server, we can
     #assume it's having trouble and allow the user to pass.
     $allowPass = 1;
     $storePSSID = 0;
  }
  
  return $allowPass;
  
}

######################
#getPeoplesignSessionStatus
######################
#Description
##Contacts the peoplesign server to validate the user's response.
#Return value
##a string, usually pass, fail or awaitingResponse
#Parameters
##identical to those of isPeoplesignResponseCorrect

function getPeoplesignSessionStatus ($peoplesignSessionID,
                                     $peoplesignResponseString,
                                     $clientLocation="default",
                                     $peoplesignKey) {

  if (!$peoplesignResponseString) {
     $peoplesignResponseString = getPostVar(PEOPLESIGN_CHALLENGE_RESPONSE_NAME, '');
  }

  $peoplesignResponseString = urlencode($peoplesignResponseString);
  $clientLocation = urlencode($clientLocation);
  $peoplesignKey = urlencode($peoplesignKey);


  $status = httpPost(80, PEOPLESIGN_HOST,
                         PEOPLESIGN_GET_CHALLENGE_SESSION_STATUS_PATH,
		         PEOPLESIGN_CHALLENGE_SESSION_ID_NAME."="
			    .$peoplesignSessionID."&"
                         .PEOPLESIGN_CHALLENGE_RESPONSE_NAME."="
                            .$peoplesignResponseString."&"
			 ."privateKey=".$peoplesignKey."&"
			 ."clientLocation=".$clientLocation );
  return $status;
}

######################
#getPeoplesignSessionID
######################

#usage notes
##-no need to call this if you call getPeoplesignHTML()
##-If you don't want to use getPeoplesignHTML, call this and then
## pass return value to getPeoplesignHTMLJavaScript or getPeoplesignHTMLIFrame

#description
##-makes a web service call to PEOPLESIGN_HOST, returns csid

#parameters
##peoplesignKey
##visitorIP
##(optional)peoplesignOptions
##(optional)wrapperPluginVersionInfo

#return value
##array with 2 elements
###status - should be success
####success - webservice call was successfull
####serverUnreachable - time to debug
####(other values) - time to debug
###peoplesignSessionID
####a peoplesign session id is assigned to a given visitor and is valid until
####he/she passes a challenge

function getPeoplesignSessionID ($peoplesignKey,
	 			 $visitorIP,
				 $peoplesignOptions,
				 $clientLocation = "default",
                                 $wrapperPluginVersionInfo,
				 $currentPeoplesignSessionID=''
				 ){
    if ($visitorIP == "") {
       $visitorIP = $_SERVER['REMOTE_ADDR'];
    }

    # challenge option string - accept a string or an array
    if (is_string($peoplesignOptions)) {
	parse_str($peoplesignOptions, $peoplesignOptions);
    }

    $pluginInfo = $wrapperPluginVersionInfo ." " .PEOPLESIGN_PLUGIN_PHP_VERSION;
    $pluginInfo = urlencode(trim($pluginInfo));
    $peoplesignKey = urlencode(trim($peoplesignKey));
    $visitorIP  = urlencode(trim($visitorIP));
    $clientLocation  = urlencode(trim($clientLocation));
    $currentPeoplesignSessionID = urlencode(trim($currentPeoplesignSessionID));
    $peoplesignArgString = "";

    #create an encoded string containing peoplesignOptions
    if (is_array($peoplesignOptions) ){
       foreach ($peoplesignOptions as $name => $value) {
          $peoplesignArgString = $peoplesignArgString ."&"
	                    .urlencode($name)   ."="
	                    .urlencode($value);
       }
    }

    #ensure private key is not the empty string
    if ($peoplesignKey == "") {
	#get suspect info
	printError(ERROR_EMPTY_KEY);
	return array("invalidPrivateKey", "");
    }

    #ensure visitorIP is ipv4
    #if ( ($tmp = ip2long($visitorIP))== false ) {
    #	printError(ERROR_VISITOR_IP . " ($visitorIP)");
    #	return array("invalidVisitorIP", "");
    #}

    $response = httpPost(80, PEOPLESIGN_HOST,
                         PEOPLESIGN_GET_CHALLENGE_SESSION_ID_PATH,
                         "privateKey=$peoplesignKey"
		           ."&visitorIP=$visitorIP"
			   ."&clientLocation=$clientLocation"
		           ."&pluginInfo=$pluginInfo"
			   ."&".PEOPLESIGN_CHALLENGE_SESSION_ID_NAME
			       ."=$currentPeoplesignSessionID"
		           .$peoplesignArgString);
    if ($response) {
      $tmp = explode ("\n", $response, 2);
      if ( $tmp == null || sizeof($tmp) == 0 ) {
	$status = "serverUnreachable";
	$peoplesignSessionID = "";
      } else if (sizeof($tmp) == 1) {
	$status = $tmp[0];
	$peoplesignSessionID = "";
      } else {
	$status = $tmp[0];
	$peoplesignSessionID = $tmp[1];
      }

      if ($status == "success" ) {
	#do nothing
      } else if ($status == "serverUnreachable") {
	$peoplesignSessionID = "";
      } else {
        printError(ERROR_SERVER_STATUS . " ($status)");
	$peoplesignSessionID = "";
      }
    } else {
	printError(ERROR_BAD_RESPONSE);
	$status = "invalidServerResponse";
	$peoplesignSessionID = "";
    }
    return array($status, $peoplesignSessionID);
}

function getPeoplesignHTMLJavaScript ($peoplesignSessionID,
                                  $iframeWidth=PEOPLESIGN_IFRAME_WIDTH,
			          $iframeHeight=PEOPLESIGN_IFRAME_HEIGHT){
    #if ( $peoplesignSessionID == "") {return "";}

    $peoplesignHTML =
        "<script type=text/javascript> "
        ."document.write('<script type=\"text/javascript\" src=\""
        .PEOPLESIGN_CHALLENGE_URL
          ."?" .PEOPLESIGN_CHALLENGE_SESSION_ID_NAME
	       ."=$peoplesignSessionID&addJSWrapper=true"
               ."&ts=' +(new Date()).getTime() +'\" "
      ."id=\"yeOldePeopleSignJS\"><\/script>'); </script> "
      ."<noscript>"
      .getPeoplesignHTMLIFrame($peoplesignSessionID,$iframeWidth,$iframeHeight)
      ."</noscript>";

    return $peoplesignHTML;
}

function getPeoplesignHTMLIFrame ($peoplesignSessionID,
                                  $iframeWidth=PEOPLESIGN_IFRAME_WIDTH,
			          $iframeHeight=PEOPLESIGN_IFRAME_HEIGHT ){
    #if ( $peoplesignSessionID == "" ) {return "";}

    $currentChallengeURL = PEOPLESIGN_CHALLENGE_URL.'?'
              .PEOPLESIGN_CHALLENGE_SESSION_ID_NAME ."=$peoplesignSessionID";
    $challengeLink = '<a href="'.$currentChallengeURL.'" '
                       .'target="_blank">'
                       .$currentChallengeURL
                    .'</a>';

    $peoplesignHTML = "<iframe src=\"$currentChallengeURL\" " 
                  ."width=\"$iframeWidth\" height=\"$iframeHeight\" frameborder=\"0\" "
                  ."allowTransparency=\"true\"> "
                  ."<p>".NO_FRAMES_MESSAGE.$challengeLink."</p>"
                  ."</iframe>"
."<input name=\"" .PEOPLESIGN_CHALLENGE_SESSION_ID_NAME ."\" type=\"hidden\" "
."value=\"$peoplesignSessionID\"/>";
    return $peoplesignHTML;
}

################################################
#private functions
################################################

function httpPost ($port, $host, $path, $encodedPayload) {
  $httpRequest = "POST $path HTTP/1.0\n"
                ."Host: $host\n"
                ."Content-Type: application/x-www-form-urlencoded\n"
                ."Content-Length: " .strlen($encodedPayload) ."\n"
                ."User-Agent: peoplesignClient-PHP\n\n"
                .$encodedPayload;

  $httpResponse = "";

  #apparently default_socket_timeout does not effect dnslookups
  #It's probably unnecessary to set this because 2 specific timeouts are set
  #below: (1) timeout parameter to fsockopen (2) stream_set_timeout() for
  #fgets() calls
  ini_set('default_socket_timeout', CONNECTION_OPEN_TIMEOUT);

  #WARNING:  timeout will not be respected if gethostbyname hangs
  #(gethostbyname may be called to resolve $host)

  #$start = time();
  $socket = @fsockopen($host, $port, $errno, $errstr, CONNECTION_OPEN_TIMEOUT);
  #$end = time();
  #$duration = $end - $start; echo $duration;exit;

  if ( !$socket  ) {
    printError(ERROR_NO_SOCKET . " ($errstr: [$errno])");
    return "serverUnreachable";
  }

  fwrite($socket, $httpRequest);

  $blockSize = 1160;
  $i = 0;
  $maxBlocks = 1024;

  stream_set_timeout( $socket, CONNECTION_READ_TIMEOUT );
  while ( !feof($socket) ) {
    $httpResponse .= fgets($socket, $blockSize);
    if ($i>=$maxBlocks) {
      printError(ERROR_EXCESSIVE_DATA);
      fclose($socket);
      return "invalidServerResponse";
    }
    $i++;
  }
  fclose($socket);

  $retVal = explode("\r\n\r\n", $httpResponse, 2);

  return $retVal[1];
}

function printErrorAndExit ($message) {
    printError($message);
    exit(1);
}

?>
