<?php
/**
*
* peoplesign [English]
*
* @package language
* @version $Id: captcha_peoplesign.php,v 1.0.15 2011/04/10 15:00 hookerb Exp$
* @copyright (c) 2008-2011 Myricomp LLC
* @license http://opensource.org/licenses/gpl-license.php GNU Public License, v2
*
*/

/**
* @ignore
*/
if (!defined('IN_PHPBB'))
{
	exit;
}

if (empty($lang) || !is_array($lang))
{
	$lang = array();
}

$lang = array_merge($lang, array(
	'PEOPLESIGN_LANG'				=> 'en',
	'CAPTCHA_PEOPLESIGN'			=> 'peoplesign:)',
	'PEOPLESIGN_KEY'				=> 'Peoplesign Key',
	'PEOPLESIGN_KEY_EXPLAIN'		=> 'This is your Peoplesign key. Visit <a href="http://www.peoplesign.com">peoplesign.com</a> to have it emailed to you.',
	'PEOPLESIGN_OPTIONS'			=> 'Peoplesign Option String',
	'PEOPLESIGN_OPTIONS_EXPLAIN'	=> 'Customize how Peoplesign looks by obtaining your own peoplesign options from <a href="http://www.peoplesign.com/main/customize.html">peoplesign.com</a> (leaving this blank will result in the default display settings).',
	'PEOPLESIGN_NO_KEY'				=> 'Request that your peoplesign key be emailed to you from <a href="http://www.peoplesign.com">peoplesign.com</a>',
	'PEOPLESIGN_VERSION'			=> 'Peoplesign Version',

	# error messages
	'ERROR_BAD_CONFIG'		=> 'Peoplesign is not configured properly: creation of client session failed. Please contact the administrator of this site.',
	'ERROR_UNAVAILABLE'		=> 'Peoplesign is unavailble.',
	'ERROR_UNEXPECTED'		=> 'Unexpected status from get_peoplesign_session_status.',
	'ERROR_EMPTY_KEY'		=> 'Received a private key that was all whitespace or empty.',
	'NO_FRAMES_MESSAGE'		=> 'Since it appears your browser does not support "iframes", you need to click <a href="http://www.peoplesign.com/main/challenge.html">here</a> to verify you are a human.',
	'ERROR_NO_SOCKET'		=> 'Can not get socket to peoplesign host.',
	'ERROR_EXCESSIVE_DATA'	=> 'Excessive data returned from peoplesign webservice call.',
	'ERROR_PREAMBLE'		=> 'ERROR: peoplesign client: ',
	'ERROR_VISITOR_IP'		=> 'invalid visitor_ip',
	'ERROR_SERVER_STATUS'	=> 'Unexpected server status.',
	'ERROR_BAD_RESPONSE'	=> 'Bad HTTP response from server.',
	'ERROR_WRONG_ANSWER'	=> 'One of your responses below was not correct.',

	// return codes
	'CODE_INVALID_PRIVATE_KEY'		=> 'invalid_private_key',
	'CODE_SERVER_UNREACHABLE'		=> 'server_unreachable',
	'CODE_INVALID_SERVER_RESPONSE'	=> 'invalid_server_response'
));

?>
