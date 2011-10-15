<?php
/**
*
* @package Activation Justification
* @version $Id: justify_lang.php 1.0.0 2010-12-16
* @copyright (c) RMcGirr83 ( http://www.rmcgirr83.org/ )
* @license http://opensource.org/licenses/gpl-license.php GNU Public License
*
**/

/**
* DO NOT CHANGE!
*/
if (!defined('IN_PHPBB'))
{
  exit;
}

if (empty($lang) || !is_array($lang))
{
  $lang = array();
}

// DEVELOPERS PLEASE NOTE
//
// All language files should use UTF-8 as their encoding and the files must not contain a BOM.
//
// Placeholders can now contain order information, e.g. instead of
// 'Page %s of %s' you can (and should) write 'Page %1$s of %2$s', this allows
// translators to re-order the output of data while ensuring it remains correct
//
// You do not need this where single placeholders are used, e.g. 'Message %d' is fine
// equally where a string contains only two placeholders which are used to wrap text
// in a url you again do not need to specify an order e.g., 'Click %sHERE%s' is fine
//
// Some characters you may want to copy&paste (Unicode characters):
// ’ » “ ” …
//

$lang = array_merge($lang, array(

	// UMIL stuff
	'JUSTIFY_TITLE'				=> 'Activation Justification',
	'JUSTIFY_FIELD_UPDATED'		=> 'User table entry updated',
	'JUSTIFY_ENTRIES_ADDED'		=> 'The database has been updated',
	'JUSTIFY_UNINSTALLED'		=> 'Activation Justification database entries have been removed',
));
?>