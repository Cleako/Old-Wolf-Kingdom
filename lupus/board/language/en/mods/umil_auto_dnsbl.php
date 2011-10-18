<?php
/** 
*
* @package Advanced Block Mod
* @version $Id: umil_auto_dnsbl.php, v 1.003 2011/08/01 Martin Truckenbrodt Exp$
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
	'INSTALL_ADVANCED_BLOCK_MOD'			=> 'Install Advanced Block Mod',
	'INSTALL_ADVANCED_BLOCK_MOD_CONFIRM'	=> 'Are you ready to install the Advanced Block Mod?',

	'ADVANCED_BLOCK_MOD'					=> 'Advanced Block Mod',
	'ADVANCED_BLOCK_MOD_EXPLAIN'			=> 'Advanced block functions for phpBB3',

	'UNINSTALL_ADVANCED_BLOCK_MOD'			=> 'Uninstall Advanced Block Mod',
	'UNINSTALL_ADVANCED_BLOCK_MOD_CONFIRM'	=> 'Are you ready to uninstall the Advanced Block Mod? All settings and data saved by this mod will be removed!',
	'UPDATE_ADVANCED_BLOCK_MOD'				=> 'Update Advanced Block Mod',
	'UPDATE_ADVANCED_BLOCK_MOD_CONFIRM'		=> 'Are you ready to update the Advanced Block Mod?',
	'INSTALL_ADVANCED_BLOCK_MOD'			=> 'Install Advanced Block Mod',
	'INSTALL_ADVANCED_BLOCK_MOD_CONFIRM'	=> 'Are you ready to install the Advanced Block Mod?',
	'INSTALL_ADVANCED_BLOCK_MOD'			=> 'Install Advanced Block Mod',
	'INSTALL_ADVANCED_BLOCK_MOD_CONFIRM'	=> 'Are you ready to install the Advanced Block Mod?',

	'INSTALL_ADVANCED_BLOCK_MOD_LIST'			=> 'Install Advanced Block Mod Blacklists',
	'INSTALL_ADVANCED_BLOCK_MOD_LIST_CONFIRM'	=> 'Are you ready to install the Advanced Block Mod Blacklists?',

	'ADVANCED_BLOCK_MOD_LIST'					=> 'Advanced Block Mod Blacklists',
	'ADVANCED_BLOCK_MOD_LIST_EXPLAIN'			=> 'Blacklists for Advanced Block Mod',

	'UNINSTALL_ADVANCED_BLOCK_LIST_MOD'			=> 'Uninstall Advanced Block Mod Blacklists',
	'UNINSTALL_ADVANCED_BLOCK_LIST_MOD_CONFIRM'	=> 'Are you ready to uninstall the Advanced Block Mod Blacklists?',
	'UPDATE_ADVANCED_BLOCK_MOD_LIST'			=> 'Update Advanced Block Mod Blacklists',
	'UPDATE_ADVANCED_BLOCK_MOD_LIST_CONFIRM'	=> 'Are you ready to update the Advanced Block Mod Blacklists?',
	'INSTALL_ADVANCED_BLOCK_MOD_LIST'			=> 'Install Advanced Block Mod Blacklists',
	'INSTALL_ADVANCED_BLOCK_MOD_LIST_CONFIRM'	=> 'Are you ready to install the Advanced Block Mod Blacklists?',
));

?>