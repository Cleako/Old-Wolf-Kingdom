<?php

/**
* @package VC
* @version $Id: peoplesign_messages.php,v 1.0.15 2011/04/10 15:00 hookerb Exp$
* @copyright (c) 2008-2011 Myricomp LLC
* @license http://opensource.org/licenses/gpl-license.php GNU Public License, v2
*/

/**
* @ignore
*/
if (!defined('IN_PHPBB'))
{
	exit;
}

/**
* The purpose of this file is to abstract out the phpBB3 language mechanisms so that the main peoplesign_client
*  does not have to change between web frameworks, while still honoring each frameworks' individual language
*  mechanisms by defining the variables in this file differently.
*/

// Define error message strings
global $user;
$user->add_lang('mods/captcha_peoplesign');

define('ERROR_BAD_CONFIG',		$user->lang['ERROR_BAD_CONFIG']);
define('ERROR_UNAVAILABLE',		$user->lang['ERROR_UNAVAILABLE']);
define('ERROR_UNEXPECTED',		$user->lang['ERROR_UNEXPECTED']);
define('ERROR_EMPTY_KEY',		$user->lang['ERROR_EMPTY_KEY']);
define('NO_FRAMES_MESSAGE',		$user->lang['NO_FRAMES_MESSAGE']);
define('ERROR_NO_SOCKET',		$user->lang['ERROR_NO_SOCKET']);
define('ERROR_EXCESSIVE_DATA',	$user->lang['ERROR_EXCESSIVE_DATA']);
define('ERROR_PREAMBLE',		$user->lang['ERROR_PREAMBLE']);
define('ERROR_VISITOR_IP',		$user->lang['ERROR_VISITOR_IP']);
define('ERROR_SERVER_STATUS',	$user->lang['ERROR_SERVER_STATUS']);
define('ERROR_BAD_RESPONSE',	$user->lang['ERROR_BAD_RESPONSE']);
define('ERROR_WRONG_ANSWER',	$user->lang['ERROR_WRONG_ANSWER']);

// Return codes
define('CODE_INVALID_PRIVATE_KEY', $user->lang['CODE_INVALID_PRIVATE_KEY']);
define('CODE_SERVER_UNREACHABLE', $user->lang['CODE_SERVER_UNREACHABLE']);
define('CODE_INVALID_SERVER_RESPONSE', $user->lang['CODE_INVALID_SERVER_RESPONSE']);
?>
