<?php
/**
*
* KeyCAPTCHA [English]
*
* @package language
* @version $Id: phpbb_keycaptcha 2011-01-21 KeyCAPTCHA $
* @copyright (c) 2010 ITNP, Jsc (https://www.keycaptcha.com). All rights reserved.
* @license http://opensource.org/licenses/gpl-license.php GNU General Public License v2
* This software is designed to work with phpBB versions from 3.0.6 to 3.0.8
*
*/

/**
* DO NOT CHANGE
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
	'KEYCAPTCHA_LANGUAGE'		=> 'en',
	'KEYCAPTCHA_MESSAGE_NA'		=> 'In order to use KeyCAPTCHA, you must create an account on <a href="https://www.keycaptcha.com">www.keycaptcha.com</a>',
	'KEYCAPTCHA_MESSAGE_A'		=> 'Please logout from the admin control panel and check all forms protected by KeyCAPTCHA',
	'CAPTCHA_KEYCAPTCHA'		=> 'KeyCAPTCHA',
	'KEYCAPTCHA_MESSAGE_INCORRECT'	=> 'The solution of task you submitted was incorrect. Please read the instruction and try again.',
	'KEYCAPTCHA_CODE_FIELD_CAPTION'	=> 'KeyCAPTCHA code',
	'KEYCAPTCHA_CODE_FIELD_CAPTION_EXPLAIN'			=> 'You can find this code in "My sites" section at <a href="https://www.keycaptcha.com">www.keycaptcha.com</a><br />(Please note that "My sites" section is available only for users who are logged in)',
	'KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION'		=> 'Private key',
	'KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION_EXPLAIN'	=> 'You can generate this key in "My sites" section at <a href="https://www.keycaptcha.com" target="_blank">www.keycaptcha.com</a><br />(Please note that "My sites" section is available only for users who are logged in)',
	'KEYCAPTCHA_MESSAGE_NOSCRIPT'	=> '<b>You should turn on JavaScript on your browser. After that please reload the page.<br />Otherwise you won&#039;t be able to post any information on this site</b>.',
	'KEYCAPTCHA_TASK_HEADER'	=> 'Anti-spam',
	'KEYCAPTCHA_TASK_EXPLAIN'	=> 'Complete the task',
));
?>
