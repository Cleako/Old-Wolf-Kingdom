<?php
/**
*
* @package Auto Backup
* @copyright (c) 2011 Pico
* @license http://opensource.org/licenses/gpl-license.php GNU Public License
*
*/

/**
* @ignore
*/
if (!defined('IN_PHPBB'))
{
	exit;
}

/**
* Auto Backup
*/
function auto_backup()
{
	global $config, $db, $phpbb_root_path, $phpEx, $table_prefix;

	include($phpbb_root_path . 'includes/functions_install.' . $phpEx);
	include($phpbb_root_path . 'includes/acp/acp_database.' . $phpEx);

	$time = time();
	$name = 'backup_' . $time . '_' . unique_id();
	$format = $config['auto_backup_filetype'];

	@set_time_limit(1200);
	@set_time_limit(0);

	switch ($db->sql_layer)
	{
		case 'mysqli':
		case 'mysql4':
		case 'mysql':
			$extractor = new mysql_extractor(false, true, $format, $name, $time);
		break;

		case 'sqlite':
			$extractor = new sqlite_extractor(false, true, $format, $name, $time);
		break;

		case 'postgres':
			$extractor = new postgres_extractor(false, true, $format, $name, $time);
		break;

		case 'oracle':
			$extractor = new oracle_extractor(false, true, $format, $name, $time);
		break;

		case 'mssql':
		case 'mssql_odbc':
			$extractor = new mssql_extractor(false, true, $format, $name, $time);
		break;

		case 'firebird':
			$extractor = new firebird_extractor(false, true, $format, $name, $time);
		break;
	}

	$extractor->write_start($table_prefix);

	foreach (get_tables($db) as $table_name)
	{
		$extractor->write_table($table_name);
		$extractor->write_data($table_name);
	}

	// Delete backup
	if ($config['auto_backup_copies'])
	{
		$rep = $phpbb_root_path . '/store/';
		$dir = opendir($rep);
		$files = array();
		while (($file = readdir($dir)) !== false)
		{
			if (is_file($rep . $file) && (substr($file, -3) == '.gz' || substr($file, -4) == '.bz2' || substr($file, -4) == '.sql' ))
			{
				$files[$file] = fileatime($rep . $file);
			}
		}
		closedir($dir);

		arsort($files);
		reset($files);

		if (sizeof($files) > $config['auto_backup_copies'])
		{
			$i = 0;
			while (list($key, $val) = each($files))
			{
				$i++;
				if ($i > $config['auto_backup_copies'])
				{
					@unlink($rep . $key);
				}
			}
		}
	}

	$extractor->write_end();

	add_log('admin', 'LOG_DB_BACKUP');

	set_config('auto_backup_last_gc', $time, true);

	return true;
}

?>