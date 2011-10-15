<?php

/**
* @package VC
* @version $Id: peoplesign_client.php,v 1.0.15 2011/04/10 15:00 hookerb Exp$
* @copyright (c) 2008-2011 Myricomp LLC
* @license http://opensource.org/licenses/gpl-license.php GNU Public License, v2
*/

/**
* WARNING: The functions in this "peoplesign client" interact directly with peoplesign servers.
*  Therfore, changing it requires intimate knowledge of the peoplesign APIs.
* In addition, this peoplesign client is designed to work with multiple frameworks, including
*  phpBB3. To that end, there may be functions that are only used by those other frameworks.
*/

/**
* @ignore
*/
if (!defined('IN_PHPBB'))
{
	exit;
}

include ($phpbb_root_path . 'includes/captcha/plugins/peoplesign_messages.' . $phpEx);
include ($phpbb_root_path . 'includes/captcha/plugins/peoplesign_utilities.' . $phpEx);

// WARNING: changing these constants may result in errors
define('PEOPLESIGN_PLUGIN_PHP_VERSION',					'psPlugPHP_2.0.0');
define('PEOPLESIGN_VERSION_ID',							'1.0.15');
define('PEOPLESIGN_HOST',								'peoplesign.com');
define('PEOPLESIGN_GET_CHALLENGE_SESSION_ID_PATH',		'/main/getChallengeSessionID');
define('PEOPLESIGN_CHALLENGE_SESSION_ID_NAME',			'challengeSessionID');
define('PEOPLESIGN_GET_CHALLENGE_SESSION_ID_URL',		'http://' . PEOPLESIGN_HOST . PEOPLESIGN_GET_CHALLENGE_SESSION_ID_PATH);
define('PEOPLESIGN_CHALLENGE_URL',						'http://' . PEOPLESIGN_HOST . '/main/challenge.html');
define('PEOPLESIGN_GET_CHALLENGE_SESSION_STATUS_PATH',	'/main/getChallengeSessionStatus_v2');
define('PEOPLESIGN_GET_CHALLENGE_SESSION_STATUS_URL',	'http://' . PEOPLESIGN_HOST . PEOPLESIGN_GET_CHALLENGE_SESSION_STATUS_PATH);
define('PEOPLESIGN_CHALLENGE_RESPONSE_NAME',			'captcha_peoplesignCRS');
define('CONNECTION_OPEN_TIMEOUT',						5);
define('CONNECTION_READ_TIMEOUT',						10);
define('PEOPLESIGN_IFRAME_WIDTH',						'335'); // change these settings if java-disabled renderings have scroll bars
define('PEOPLESIGN_IFRAME_HEIGHT',						'380'); //  Your individual CAPTCHA setting are too large to be displayed with theses settings

/**
*  Insert the return value of this function into the middle of the form that is being protected using peoplesign.
*  This is intended for use when your php server is configured to use sessions
*  Starts a new session if one is not already started,
*  calls get_peoplesign_session_id and writes the result to a session variable
*  calls get_peoplesign_html_javascript, passes return value
*
* @param	peoplesign_key					obtained by registering at peoplesign.com
* @param	peoplesign_options (optional)	option string obtained from the customize page on peoplesign.com to describe how to render the CAPTCHA.
* @param	client_location (optional)		A string that describes the page where you're placing peoplesign.
* @param	wrapper_plugin_info (optional)	If you've written a wrapper around this plugin, pass info about it in the form of name_versionNum. e.g. "myWrapper_1.0"
* @param	iframe_width (optional)			If a browser has javascript disabled, the peoplesign challenge will be sent in an iframe having the specified width
* @param	ifram_height (optional)			If a browser has javascript disabled, the peoplesign challenge will be sent in an iframe having the specified width
*
* @return	HTML string to be sent to browser
*/
function get_peoplesign_html ($peoplesign_key, $peoplesign_options, $client_location = "default", $wrapper_plugin_info = '', $iframe_width = PEOPLESIGN_IFRAME_WIDTH, $iframe_height = PEOPLESIGN_IFRAME_HEIGHT)
{
	$peoplesign_html		= "";
	$peoplesign_session_id	= "";
	$status					= "";

	if (!session_id() && !session_start())
	{
		return ("<p>" . ERROR_BAD_CONFIG . "</p>");
	}

	// If the session id is in the session, then get it from there
	if (isset($_SESSION[$client_location . "_peoplesignSessionID"]))
	{
		$peoplesign_session_id = $_SESSION[$client_location . "_peoplesignSessionID"];
	}

	// Passing the existing peoplesign_session_id will serve to re-validate it by the server.
	$peoplesign_session_id = get_peoplesign_session_id($peoplesign_key, $_SERVER['REMOTE_ADDR'], $peoplesign_options, $client_location, $wrapper_plugin_info, $peoplesign_session_id);

	$_SESSION[$client_location . "_peoplesignSessionID"] = $peoplesign_session_id;

	if (!empty($peoplesign_session_id))
	{
		// normal path to retrieve the CAPTCHA after validating or generating a new session id.
		$peoplesign_html = get_peoplesign_javascript($peoplesign_session_id, $iframe_width, $iframe_height);
		$_SESSION[$client_location . "_isDisabled"] = false;
	}
	else
	{
		// If we could not successfully obtain a challenge session id, we cannot administer peoplesign
		//  to the user.  This usually indicates a problem contacting the peoplesign server, invalid
		//  arguments passed to getChallengeSessionID (the webservice), or problems on the peoplesign
		//  server. Therefore, we must disable peoplesign at this location so users may still proceed.
		print_error(ERROR_UNAVAILABLE);
		$peoplesign_html = "<p>" . ERROR_UNAVAILABLE . "</p>";
		$_SESSION[$client_location . "_isDisabled"] = true;
	}

	return $peoplesign_html;
}

/**
* This is not used by the phpBB3 framework.  It exists solely for cross-
*  functionality across all web-frameworks.
*
* call this when processing the user's response to peoplesign if you setup
* peoplesign with get_peoplesign_html
*
* looks for peoplesign_session_id and peoplesign_response_string in the _POST
* array unless you pass them as arguments. calls process_peoplesign_response
*
* @param	peoplesign_session_id 		the session id in the _REQUEST array.  If the caller passes null or empty string, this will attempt to find it.
* @param	peoplesign_response_string	the response in the _REQUEST array.
* @param	client_location				MUST match the argument passed to getPeoplesignHTML.
* @param	peoplesign_key This site's private key from peoplesign.com
*
* @returns boolean true or false
*/
function process_peoplesign_response_without_sessions ($peoplesign_session_id, $peoplesign_response_string, $client_location="default", $peoplesign_key)
{
	if (!session_id() && !session_start())
	{
		print_error(ERROR_BAD_CONFIG);
		return false;
	}

	if ($_SESSION[$client_location."_isDisabled"] == "true")
	{
		return true;
	}

	return process_peoplesign_response($peoplesign_session_id, $peoplesign_response_string, $client_location, $peoplesign_key);
}

/**
* use the return value to determine if the user's response is correct.
* calls get_peoplesign_session_status
* decides if the user's response is correct based on the status string.  Note: the response is treated as "correct" if the peoplesign server did not respond.
*
* @param	peoplesign_session_id	The value if PEOPLESIGN_CHALLENGE_SESSION_ID_NAME read when processing the form submission.
* @param	peoplesign_response		The value of PEOPLESIGN_CHALLENGE_RESPONSE_NAME read when processing the form submission.  if you are using get_peoplesign_iframe (rare) PEOPLESIGN_CHALLENGE_RESPONSE_NAME won't be set, and you can pass null or empty string for this value when calling get_peoplesign_session_status
* @param 	client_location 		MUST match the argument passed to get_peoplesign_html.
* @param 	peoplesign_key			obtain your key from peoplesign.com
*
* @return	boolean					true for pass, false for fail
*/
function process_peoplesign_response ($peoplesign_session_id, $peoplesign_response, $client_location = "default", $peoplesign_key)
{
	// If these variables were not supplied, attempt to retrieve them from post
	if (!$peoplesign_session_id)
	{
		$peoplesign_session_id = get_post_var(PEOPLESIGN_CHALLENGE_SESSION_ID_NAME, '');
	}

	if (!$peoplesign_response)
	{
		$peoplesign_response = get_post_var(PEOPLESIGN_CHALLENGE_RESPONSE_NAME, '');
	}

	$status = get_peoplesign_session_status($peoplesign_session_id, $peoplesign_response, $client_location, $peoplesign_key);

	$allow_pass	= false;

	if ($status == "pass")
	{
		$allow_pass	= true;
	}
	else if ( ($status == "fail") || ($status == "notRequested") || ($status == "awaitingResponse") || ($status == "invalidChallengeSessionID") )
	{
		$allow_pass	= false;
	}
	else
	{
		print_error(ERROR_SERVER_STATUS . ": {$status}");
		// if we have an unexpected status from the peoplesign web server, we can
		// assume it's having trouble and allow the user to pass.
		$allow_pass	= true;
	}

	return $allow_pass;
}

/**
* Contacts the peoplesign server to validate the user's response.
*
* @param	peoplesign_session_id	The value if PEOPLESIGN_CHALLENGE_SESSION_ID_NAME read when processsing the form submission.
* @param	peoplesign_response		The value of PEOPLESIGN_CHALLENGE_RESPONSE_NAME read when processing the form submission.  if you are using get_peoplesign_iframe (rare) PEOPLESIGN_CHALLENGE_RESPONSE_NAME won't be set, and you can pass null or empty string for this value when calling get_peoplesign_session_status
* @param 	client_location 		MUST match the argument passed to get_peoplesign_html.
* @param 	peoplesign_key			obtain your key from peoplesign.com
*
* @return	string					pass, fail or awaitingResponse
*/
function get_peoplesign_session_status ($peoplesign_session_id, $peoplesign_response, $client_location = "default", $peoplesign_key)
{
	if (!$peoplesign_response)
	{
		$peoplesign_response = get_post_var(PEOPLESIGN_CHALLENGE_RESPONSE_NAME, '');
	}

	$peoplesign_response	= urlencode($peoplesign_response);
	$client_location		= urlencode($client_location);
	$peoplesign_key			= urlencode($peoplesign_key);

	$status = http_post(80, PEOPLESIGN_HOST, PEOPLESIGN_GET_CHALLENGE_SESSION_STATUS_PATH, PEOPLESIGN_CHALLENGE_SESSION_ID_NAME . "={$peoplesign_session_id}&". PEOPLESIGN_CHALLENGE_RESPONSE_NAME . "={$peoplesign_response}&privateKey={$peoplesign_key}&clientLocation={$client_location}");

	return $status;
}

/**
* makes a web service call to PEOPLESIGN_HOST, returns csid
* No need to call this if you call get_peoplesign_html()
* If you don't want to use get_peoplesign_html, call this and then pass the return value to get_peoplesign_javascript or get_peoplesign_iframe
* A peoplesign session id is assigned to a given visitor and is valid until he/she passes a challenge
*
* @param	peoplesign_key								The site's private key obtained from peoplesign.com
* @param	visitor_ip									The ipaddress of the visitor, this value can be set by the user.
* @param	peoplesign_options (optional)				The challenge option string obtained from the customize page at peoplesign.com
* @param	client_location								The location id on the webiste where the CAPTCHA appears.
* @param	wrapper_plugin_info (optional)				The version of the web framework wrapper used to call this function.
* @param	current_peoplesign_session_id  (optional)	The existing session id if one exists.  It is validated again in this function.
*
* @return	array	status, session_id
*/
function get_peoplesign_session_id ($peoplesign_key, $visitor_ip, $peoplesign_options, $client_location = "default", $wrapper_plugin_info, $current_peoplesign_session_id='')
{
	// ensure private key is not the empty string
	if ($peoplesign_key == "")
	{
		print_error(ERROR_EMPTY_KEY);
		return ("");
	}

	if ($visitor_ip == "")
	{
		$visitor_ip = $_SERVER['REMOTE_ADDR'];
	}

	// challenge option string - accept a string or an array for flexibility to the user
	if (is_string($peoplesign_options))
	{
		parse_str($peoplesign_options, $peoplesign_options);
	}

	$plugin_info					= urlencode(trim($wrapper_plugin_info . " " . PEOPLESIGN_PLUGIN_PHP_VERSION));
	$peoplesign_key					= urlencode(trim($peoplesign_key));
	$visitor_ip						= urlencode(trim($visitor_ip));
	$client_location				= urlencode(trim($client_location));
	$current_peoplesign_session_id	= urlencode(trim($current_peoplesign_session_id));
	$peoplesign_args				= "";

	// create an encoded string containing peoplesign_options
	if (is_array($peoplesign_options))
	{
		foreach ($peoplesign_options as $name => $value)
		{
			$peoplesign_args = $peoplesign_args . "&" . urlencode($name) . "=" . urlencode($value);
		}
	}

	$response = http_post(80, PEOPLESIGN_HOST, PEOPLESIGN_GET_CHALLENGE_SESSION_ID_PATH, "privateKey={$peoplesign_key}&visitorIP={$visitor_ip}&clientLocation={$client_location}&pluginInfo={$plugin_info}&" . PEOPLESIGN_CHALLENGE_SESSION_ID_NAME . "={$current_peoplesign_session_id}{$peoplesign_args}");

	// default value to return the empty string
	$peoplesign_session_id = "";

	if ($response)
	{
		// inspect the response for a status string and the session id
		$peoplesign_session_id = "";
		$status = CODE_SERVER_UNREACHABLE;

		$tmp = explode ("\n", $response, 2);

		if (sizeof($tmp) == 1)
		{
			// pass the status message through to the error message.
			$status = $tmp[0];
		}
		else if (sizeof($tmp) >= 2)
		{
			$status = $tmp[0];
			$peoplesign_session_id = $tmp[1];
		}

		// The server will respond with "success"
		if ($status != "success")
		{
			print_error(ERROR_SERVER_STATUS . " ($status)");
		}
	}
	else
	{
		print_error(ERROR_BAD_RESPONSE);
	}

	return ($peoplesign_session_id);
}

/**
* Display the CAPTCHA using javascript
*
* @param	peoplesign_session_id	The session id obtained from get_peoplesign_session_id used to identify this session.
* @param	iframe_width			Optional. If a browser has javascript disabled, the peoplesign challenge will be sent in an iframe having the specified width.
* @param	iframe_height			Optional. If a browser has javascript disabled, the peoplesign challenge will be sent in an iframe having the specified height.
*
* @return	string					The HTML that will display the CAPTCHA.
*/
function get_peoplesign_javascript ($peoplesign_session_id, $iframe_width = PEOPLESIGN_IFRAME_WIDTH, $iframe_height = PEOPLESIGN_IFRAME_HEIGHT)
{

	// Prevent the browser from doing any caching
	$peoplesign_html =
		"<script type=text/javascript> " .
		"document.write('<script type=\"text/javascript\" src=\"" .
		PEOPLESIGN_CHALLENGE_URL .
		"?" . PEOPLESIGN_CHALLENGE_SESSION_ID_NAME .
		"=$peoplesign_session_id&addJSWrapper=true" .
		"&ts=' +(new Date()).getTime() +'\" " .
		"id=\"yeOldePeopleSignJS\"><\/script>'); </script> " .
		"<noscript>" .
		get_peoplesign_iframe($peoplesign_session_id, $iframe_width, $iframe_height) .
		"</noscript>";

	return $peoplesign_html;
}

/**
* Display the CAPTCHA using iframes.
*
* @param	peoplesign_session_id	The session id obtained from get_peoplesign_session_id used to identify this session.
* @param	iframe_width			Optional. If a browser has javascript disabled, the peoplesign challenge will be sent in an iframe having the specified width.
* @param	iframe_height			Optional. If a browser has javascript disabled, the peoplesign challenge will be sent in an iframe having the specified height.
*
* @return	string					The HTML that will display the CAPTCHA.
*/
function get_peoplesign_iframe ($peoplesign_session_id, $iframe_width = PEOPLESIGN_IFRAME_WIDTH, $iframe_height = PEOPLESIGN_IFRAME_HEIGHT)
{

	$peoplesign_html =
		"<iframe src=\"" . PEOPLESIGN_CHALLENGE_URL .
		"?" . PEOPLESIGN_CHALLENGE_SESSION_ID_NAME .
		"=$peoplesign_session_id\" " .
		"width=\"$iframe_width\" height=\"$iframe_height\" frameborder=\"0\" " .
		"allowTransparency=\"true\"> " .
		"<p>" . NO_FRAMES_MESSAGE . "</p>" .
		"</iframe>" .
		"<input name=\"" . PEOPLESIGN_CHALLENGE_SESSION_ID_NAME . "\" type=\"hidden\" " .
		"value=\"$peoplesign_session_id\"/>";

	return $peoplesign_html;
}

/**
*
* private functions
*
*/

/**
* Submit the request to the server
*/
function http_post ($port, $host, $path, $encoded_payload)
{
	$http_request =
		"POST $path HTTP/1.0\n" .
		"Host: $host\n" .
		"Content-Type: application/x-www-form-urlencoded\n" .
		"Content-Length: " . strlen($encoded_payload) . "\n" .
		"User-Agent: peoplesignClient-PHP\n\n" .
		$encoded_payload;

	$http_response = "";

	// apparently default_socket_timeout does not effect dnslookups
	// It's probably unnecessary to set this because 2 specific timeouts are set
	// below: (1) timeout parameter to fsockopen (2) stream_set_timeout() for fgets() calls
	ini_set('default_socket_timeout', CONNECTION_OPEN_TIMEOUT);

	// WARNING:  timeout will not be respected if gethostbyname hangs
	//(gethostbyname may be called to resolve $host in fsockopen)

	$socket = @fsockopen($host, $port, $errno, $errstr, CONNECTION_OPEN_TIMEOUT);

	if ( !$socket ) {
		print_error(ERROR_NO_SOCKET . " ($errstr: [$errno])");
		return CODE_SERVER_UNREACHABLE;
	}

	fwrite($socket, $http_request);

	$blockSize	= 1160;
	$block		= 0;
	$maxBlocks	= 1024;

	stream_set_timeout( $socket, CONNECTION_READ_TIMEOUT );
	while ( !feof($socket) )
	{
		$http_response .= fgets($socket, $blockSize);
		if ($block >= $maxBlocks)
		{
			print_error(ERROR_EXCESSIVE_DATA);
			fclose($socket);
			return CODE_INVALID_SERVER_RESPONSE;
		}
	$block++;
	}
	fclose($socket);

	$return_value = explode("\r\n\r\n", $http_response, 2);

	return $return_value[1];
}

?>
