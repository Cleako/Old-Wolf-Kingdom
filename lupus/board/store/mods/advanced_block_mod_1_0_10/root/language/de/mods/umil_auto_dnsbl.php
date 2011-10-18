<?php
/** 
*
* @package Advanced Block Mod
* @version $Id: umil_auto_dnsbl.php, german, v 1.002 2011/08/01 Martin Truckenbrodt Exp$
* @copyright (c) 2009 Martin Truckenbrodt 
* @license http://opensource.org/licenses/gpl-license.php GNU Public License 
*
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
	'INSTALL_ADVANCED_BLOCK_MOD'			=> 'Installiere Advanced Block Mod',
	'INSTALL_ADVANCED_BLOCK_MOD_CONFIRM'	=> 'Bist Du bereit den Advanced Block Mod zu installieren?',

	'ADVANCED_BLOCK_MOD'					=> 'Advanced Block Mod',
	'ADVANCED_BLOCK_MOD_EXPLAIN'			=> 'Erweiterte Blockier-Funktionen für phpBB3',

	'UNINSTALL_ADVANCED_BLOCK_MOD'			=> 'Uninstall Advanced Block Mod',
	'UNINSTALL_ADVANCED_BLOCK_MOD_CONFIRM'	=> 'Bist Du bereit den Advanced Block Mod zu entfernen? Alle Einstellungen und Daten zu diesem Mod gehen hierbei verloren!',
	'UPDATE_ADVANCED_BLOCK_MOD'				=> 'Aktualisiere Advanced Block Mod',
	'UPDATE_ADVANCED_BLOCK_MOD_CONFIRM'		=> 'Bist Du bereit den Advanced Block Mod zu aktualisieren?',
	'INSTALL_ADVANCED_BLOCK_MOD'			=> 'Installiere Advanced Block Mod',
	'INSTALL_ADVANCED_BLOCK_MOD_CONFIRM'	=> 'Bist Du bereit den Advanced Block Mod zu installieren?',
	'INSTALL_ADVANCED_BLOCK_MOD'			=> 'Installiere Advanced Block Mod',
	'INSTALL_ADVANCED_BLOCK_MOD_CONFIRM'	=> 'Bist Du bereit den Advanced Block Mod zu installieren?',

	'INSTALL_ADVANCED_BLOCK_MOD_LIST'			=> 'Installiere Advanced Block Mod Blacklisten',
	'INSTALL_ADVANCED_BLOCK_MOD_LIST_CONFIRM'	=> 'Bist Du bereit den Advanced Block Mod Blacklisten zu installieren?',

	'ADVANCED_BLOCK_MOD_LIST'					=> 'Advanced Block Mod Blacklisten',
	'ADVANCED_BLOCK_MOD_LIST_EXPLAIN'			=> 'Blacklisten für Advanced Block Mod',

	'UNINSTALL_ADVANCED_BLOCK_MOD_LIST'			=> 'Uninstall Advanced Block Mod Blacklisten',
	'UNINSTALL_ADVANCED_BLOCK_MOD_LIST_CONFIRM'	=> 'Bist Du bereit den Advanced Block Mod Blacklisten zu entfernen? Alle Einstellungen und Daten zu diesem Mod gehen hierbei verloren!',
	'UPDATE_ADVANCED_BLOCK_MOD_LIST'				=> 'Aktualisiere Advanced Block Mod Blacklisten',
	'UPDATE_ADVANCED_BLOCK_MOD_LIST_CONFIRM'		=> 'Bist Du bereit den Advanced Block Mod Blacklisten zu aktualisieren?',
	'INSTALL_ADVANCED_BLOCK_MOD_LIST'			=> 'Installiere Advanced Block Mod Blacklisten',
	'INSTALL_ADVANCED_BLOCK_MOD_LIST_CONFIRM'	=> 'Bist Du bereit den Advanced Block Mod Blacklisten zu installieren?',
));

?>