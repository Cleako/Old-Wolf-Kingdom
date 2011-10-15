<?php

/**
* @package VC
* @version $Id: peoplesign_utilities.php,v 1.0.15 2011/04/10 15:00 hookerb Exp$
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
* The purpose of this file is to abstract out the implementation of individual mechanisms between
* web frameworks.  Specifically to take advantage of particular phpBB3 functionality that does not exist elsewhere.
*/

/**
* Set the specified language option in the challenge option string.
*
* @param	option_string	The challenge option string taken from the database, and specified from the ACP.
* @param	language		The language specifier taken directly from the user's setting.
*
* If the specified language is not supported, or recognized by the peoplesign server (highly doubtful), the CAPTCHA language will be defaulted to english by the Peoplesign server.
*/
function set_captcha_language($option_string, $language)
{
	# Do not add an empty language.
	if(empty($language))
	{
		return $option_string;
	}

	# Only add the language if it isn't already specified.
	$pos = strpos($option_string, 'language');
	if ($pos === false)
	{
		# The option string has no language setting defined.

		# Check if the string is empty.
		if(empty($option_string))
		{
			# There was no challenge option settings, create one.
			return 'language='.$language;
		}
		else
		{
			# Add the language setting to the existing challenge option
			return $option_string.'&language='.$language;
		}
	}

	# If the language setting has already been specified, then honor it.
	return $option_string;
}

function get_post_var($variable_name, $variable_type)
{

	$return = request_var($variable_name, $variable_type);
	$return = str_replace('&amp;', '&', $return);

	return $return;
}

function print_error ($message)
{
	error_log(ERROR_PREAMBLE . $message);

	return;
}

?>
