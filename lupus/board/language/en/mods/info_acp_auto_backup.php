<?php
/**
*
* auto_backup [English]
*
* @package language
* @copyright (c) 2005 phpBB Group
* @license http://opensource.org/licenses/gpl-license.php GNU Public License
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

$lang = array_merge($lang, array(
	'ACP_AUTO_BACKUP_INDEX_TITLE'		=> 'Auto Backup',
	'ACP_AUTO_BACKUP'					=> 'Auto Backup',
	'ACP_AUTO_BACKUP_SETTINGS'			=> 'Auto Backup settings',
	'ACP_AUTO_BACKUP_SETTINGS_EXPLAIN'	=> 'Here you can set all default settings for Auto Backup. All backups will be stored in your <samp>store/</samp> folder. Depending on your server configuration you may be able to compress the file in a number of formats. You can restore backup in <em>Restore</em> module.',
	'LOG_AUTO_BACKUP_SETTINGS_CHANGED'	=> '<strong>Altered Auto Backup settings</strong>',
	'AUTO_BACKUP_SETTINGS_CHANGED'		=> 'Auto Backup Settings changed.',
	'AUTO_BACKUP_ENABLE'				=> 'Enable Auto Backup',
	'AUTO_BACKUP_ENABLE_EXPLAIN'		=> 'You may enable/disable Auto Backup at any moment.',
	'AUTO_BACKUP_FREQ'					=> 'Backups frequency',
	'AUTO_BACKUP_FREQ_EXPLAIN'			=> 'Set backups frequency. Value must be higher than 0.',
	'AUTO_BACKUP_FREQ_ERROR'			=> 'Entered value for Auto Backup frequency is incorrect.<br />Value must be higher than <strong>0</strong>.',
	'AUTO_BACKUP_COPIES'				=> 'Stored backups',
	'AUTO_BACKUP_COPIES_EXPLAIN'		=> 'How many backups will be stored on the server. 0 means disabled and all backups will be stored on the server.',
	'AUTO_BACKUP_COPIES_ERROR'			=> 'Entered value for Store backups is incorrect.<br />Value must be equal or higher than <strong>0</strong>.',
	'AUTO_BACKUP_FILETYPE'				=> 'File type',
	'AUTO_BACKUP_FILETYPE_EXPLAIN'		=> 'Choose the file type for backups.',
	'AUTO_BACKUP_GZIP'					=> 'gzip',
	'AUTO_BACKUP_BZIP2'					=> 'bzip2',
	'AUTO_BACKUP_TEXT'					=> 'text',
	'AUTO_BACKUP_NEXT'					=> 'Next backup',
	'AUTO_BACKUP_NEXT_EXPLAIN'			=> 'The next backup will be made on',
	'AUTO_BACKUP_TIME'					=> 'Time for backup',
	'AUTO_BACKUP_TIME_EXPLAIN'			=> 'Specify when backups should be done (year-month-day-hour-minute).<br />Note: you should specify a particular point in the future',
	'AUTO_BACKUP_TIME_ERROR'			=> 'Entered value for Auto Backup time is incorrect.<br />Hour value must be lower than <strong>24</strong>.<br />Minute value must be lower than <strong>60</strong>.',
	'AUTO_BACKUP_DATE_TIME'				=> 'YYYY-MM-DD-hh-mm',
));

?>