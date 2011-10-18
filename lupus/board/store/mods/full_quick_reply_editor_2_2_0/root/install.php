<?php
/**
*
* @author weeb <kontakt@weeb.pl>
* @package umil
* @copyright (c) 2008 phpBB Group
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

// Start session management
$user->session_begin();
$auth->acl($user->data);
$user->setup('mods/full_quick_reply_editor');

if (!file_exists($phpbb_root_path . 'umil/umil_auto.' . $phpEx))
{
	trigger_error('Please download the latest UMIL (Unified MOD Install Library) from: <a href="http://www.phpbb.com/mods/umil/">phpBB.com/mods/umil</a>', E_USER_ERROR);
}

$mod_name = 'FULL_QUICK_REPLY_EDITOR';
$version_config_name = 'full_quick_reply_editor_version';

/*
* The array of versions and actions within each.
* You do not need to order it a specific way (it will be sorted automatically), however, you must enter every version, even if no actions are done for it.
*
* You must use correct version numbering.  Unless you know exactly what you can use, only use X.X.X (replacing X with an integer).
* The version numbering must otherwise be compatible with the version_compare function - http://php.net/manual/en/function.version-compare.php
*/
$versions = array(
	'2.2.0' => array(
		'config_add' => array(
			array('quick_reply_lastpage', 0, 0),
		),
		'custom' => 'set_user_options',
	),
);

// Include the UMIF Auto file and everything else will be handled automatically.
include($phpbb_root_path . 'umil/umil_auto.' . $phpEx);

/*
* Set options enabling quick reply for already registered users
*  
* @param string $action The action (install|update|uninstall) will be sent through this.
* @param string $version The version this is being run for will be sent through this.
*/
function set_user_options($action, $version)
{
	global $db, $dbms, $table_prefix;
	
	switch ($dbms)
	{
		case 'firebird':
		case 'oracle':
		case 'sqlite':
			trigger_error('DBMS_NOT_SUPPORTED');
		break;
		
		case 'postgres':
			
			if ($action == 'uninstall')
			{
				$sql = 'UPDATE ' . $table_prefix . 'users
					SET user_options = user_options # 2048
					WHERE user_type <> ' . USER_IGNORE;
				$db->sql_query($sql);
			}
			
		case 'mssql':
		case 'mssql_odbc':
		case 'mysql':
		case 'mysqli':
			
			if ($action == 'install')
			{
				$sql = 'UPDATE ' . $table_prefix . 'users
					SET user_options = user_options | 2048
					WHERE user_type <> ' . USER_IGNORE;
				$db->sql_query($sql);
			}
		
			if ($action == 'uninstall')
			{
				$sql = 'UPDATE ' . $table_prefix . 'users
					SET user_options = user_options ^ 2048
					WHERE user_type <> ' . USER_IGNORE;
				$db->sql_query($sql);
			}
			
		break;
	}
	
	return array(
		'command'	=> array('TABLE_COLUMN_UPDATE', $table_prefix . 'users', 'user_options'),
		'result'	=> 'SUCCESS'
	);
}

// Clear the cache
$umil->cache_purge(array('', 'auth', 'template'));

?>