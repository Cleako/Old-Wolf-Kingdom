<?php
/**
*
* @package acp
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
* @package acp
*/
class acp_auto_backup
{
	var $u_action;
	function main($id, $mode)
	{
		global $db, $user, $auth, $template;
		global $config, $phpbb_root_path, $phpbb_admin_path, $phpEx;

		$action = request_var('action', '');
		$submit = (isset($_POST['submit'])) ? true : false;

		if ($mode != 'index')
		{
			return;
		}

		$this->tpl_name = 'acp_auto_backup';
		$this->page_title = 'ACP_AUTO_BACKUP_SETTINGS';

		$auto_backup_enable = request_var('auto_backup_enable', $config['auto_backup_enable']);
		$auto_backup_filetype = request_var('auto_backup_filetype', $config['auto_backup_filetype']);
		$auto_backup_copies = request_var('auto_backup_copies', $config['auto_backup_copies']);
		$auto_backup_gc = request_var('auto_backup_gc', $config['auto_backup_gc']);
		$auto_backup_last_gc = request_var('auto_backup_last_gc', $config['auto_backup_last_gc']);
		$auto_backup_next = $user->format_date($config['auto_backup_last_gc'] + $config['auto_backup_gc'] * 86400);
		$auto_backup_time = $user->format_date($config['auto_backup_last_gc'] + $config['auto_backup_gc'] * 86400, 'Y-m-d-H-i');
		
		$form_name = 'acp_auto_backup';
		add_form_key($form_name);

		if ($submit)
		{
			if (!check_form_key($form_name))
			{
				trigger_error($user->lang['FORM_INVALID']. adm_back_link($this->u_action), E_USER_WARNING);
			}

			if ($auto_backup_gc <= 0)
			{
				trigger_error($user->lang['AUTO_BACKUP_FREQ_ERROR']. adm_back_link($this->u_action), E_USER_WARNING);
			}

			if (!is_numeric($auto_backup_copies) || $auto_backup_copies < 0)
			{
				trigger_error($user->lang['AUTO_BACKUP_COPIES_ERROR']. adm_back_link($this->u_action), E_USER_WARNING);
			}

			$error = array();

			$message = $user->lang['AUTO_BACKUP_SETTINGS_CHANGED'];
			$log = 'AUTO_BACKUP_SETTINGS_CHANGED';

			$auto_backup_date = explode('-', $auto_backup_last_gc);
			if (sizeof($auto_backup_date) == 5  &&	(strlen($auto_backup_date[0]) == 4) && (strlen($auto_backup_date[1]) == 2) && (strlen($auto_backup_date[2]) == 2) && ((int)$auto_backup_date[3] < 24) && (strlen($auto_backup_date[3]) == 2) && ((int)$auto_backup_date[4] < 60) && (strlen($auto_backup_date[4]) == 2))
			{
				$time_offset = (isset($user->timezone) && isset($user->dst)) ? (int)$user->timezone + (int)$user->dst : 0;
				$auto_backup_last_gc = gmmktime((int)$auto_backup_date[3], (int)$auto_backup_date[4], 0, (int)$auto_backup_date[1], (int)$auto_backup_date[2], (int)$auto_backup_date[0])- $time_offset - $config['auto_backup_gc'] * 86400;
			}
			else
			{
				trigger_error($user->lang['AUTO_BACKUP_TIME_ERROR']. adm_back_link($this->u_action), E_USER_WARNING);
			}

			set_config('auto_backup_enable', $auto_backup_enable);
			set_config('auto_backup_filetype', $auto_backup_filetype);
			set_config('auto_backup_gc', $auto_backup_gc);
			set_config('auto_backup_copies', $auto_backup_copies);
			set_config('auto_backup_last_gc', $auto_backup_last_gc);
			
			add_log('admin', 'LOG_' . $log);
			trigger_error($message . adm_back_link($this->u_action));
		}

		$template->assign_vars(array(
			'U_ACTION'				=> $this->u_action,
			'AUTO_BACKUP_ENABLE'	=> $auto_backup_enable,
			'AUTO_BACKUP_FILETYPE'	=> $auto_backup_filetype,
			'AUTO_BACKUP_GC'		=> $auto_backup_gc,
			'AUTO_BACKUP_COPIES'	=> $auto_backup_copies,
			'AUTO_BACKUP_NEXT'		=> $auto_backup_next,
			'AUTO_BACKUP_TIME'		=> $auto_backup_time,
			'AB_GZIP'				=> $auto_backup_filetype == 'gzip',
			'AB_BZIP2'				=> $auto_backup_filetype == 'bzip2',
			'AB_TEXT'				=> $auto_backup_filetype == 'text',
			'GZIP'					=> @extension_loaded('zlib') ? true : false,
			'BZIP2'					=> @extension_loaded('bz2') ? true : false,
		));
	}
}

?>
