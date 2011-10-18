<?php
/**
 *
 * @author Pico88 () 
 * @version $Id$
 * @copyright (c) 2011 
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 *
 */

/**
 * @ignore
 */
define('UMIL_AUTO', true);
define('IN_PHPBB', true);
$phpbb_root_path = (defined('PHPBB_ROOT_PATH')) ? PHPBB_ROOT_PATH : './';
$phpEx = substr(strrchr(__FILE__, '.'), 1);

include($phpbb_root_path . 'common.' . $phpEx);
$user->session_begin();
$auth->acl($user->data);
$user->setup();


if (!file_exists($phpbb_root_path . 'umil/umil_auto.' . $phpEx))
{
	trigger_error('Please download the latest UMIL (Unified MOD Install Library) from: <a href="http://www.phpbb.com/mods/umil/">phpBB.com/mods/umil</a>', E_USER_ERROR);
}

/*
* The language file which will be included when installing
* Language entries that should exist in the language file for UMIL (replace $mod_name with the mod's name you set to $mod_name above)
* $mod_name
* 'INSTALL_' . $mod_name
* 'INSTALL_' . $mod_name . '_CONFIRM'
* 'UPDATE_' . $mod_name
* 'UPDATE_' . $mod_name . '_CONFIRM'
* 'UNINSTALL_' . $mod_name
* 'UNINSTALL_' . $mod_name . '_CONFIRM'
*/
$language_file = 'mods/info_acp_auto_backup';

// The name of the mod to be displayed during installation.
$mod_name = 'ACP_AUTO_BACKUP_INDEX_TITLE';

/*
* The name of the config variable which will hold the currently installed version
* UMIL will handle checking, setting, and updating the version itself.
*/
$version_config_name = 'auto_backup_version';

/*
* The array of versions and actions within each.
* You do not need to order it a specific way (it will be sorted automatically), however, you must enter every version, even if no actions are done for it.
*
* You must use correct version numbering.  Unless you know exactly what you can use, only use X.X.X (replacing X with an integer).
* The version numbering must otherwise be compatible with the version_compare function - http://php.net/manual/en/function.version-compare.php
*/
$versions = array(
	'1.0.0' => array(
		'config_add' => array(
			array('auto_backup_enable', '0', 0),
			array('auto_backup_copies', '7', 0),
			array('auto_backup_gc', '1', 0),
			array('auto_backup_last_gc', '0', 1),
		),

		'module_add' => array(
			array('acp', 'ACP_CAT_DATABASE',
				array(
				'module_basename'	=> 'auto_backup',
				'module_langname'   => 'ACP_AUTO_BACKUP_INDEX_TITLE',
				'module_mode'       => 'index',
				'module_auth'       => 'acl_a_board',
				),
			),
		),
	),

	'1.0.1' => array(),

	'1.0.2' => array(
		'config_add' => array(
			array('auto_backup_filetype', 'text', 0),
		),
	),
	
	'1.0.2.1' => array(),
);

// Include the UMIL Auto file, it handles the rest
include($phpbb_root_path . 'umil/umil_auto.' . $phpEx);