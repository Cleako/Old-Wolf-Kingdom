<?php
/**
*
* @package acp
* @version $Id: acp_dnsbl.php,v 1.005 2011/08/01 Martin Truckenbrodt Exp $
* @copyright (c) 2009 Martin Truckenbrodt
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
class acp_dnsbl
{
	var $u_action;

	function main($id, $mode)
	{
		global $config, $db, $user, $auth, $template, $cache;
		global $phpbb_root_path, $phpbb_admin_path, $phpEx, $table_prefix;

		$user->add_lang('mods/dnsbl');

		$this->tpl_name = 'acp_dnsbl';
		$this->page_title = 'ACP_DNSBL';

		$form_key = 'acp_dnsbl';
		add_form_key($form_key);

		// Check the permission setting again
		if (!$auth->acl_get('a_board'))
		{
			trigger_error($user->lang['NO_AUTH_OPERATION'] . adm_back_link($this->u_action), E_USER_WARNING);
		}

		$action		= request_var('action', '');
		$update		= (isset($_POST['update'])) ? true : false;
		$dnsbl_id	= request_var('d', 0);

		$dnsbl_data = $errors = array();
		if ($update && !check_form_key($form_key))
		{
			$update = false;
			$errors[] = $user->lang['FORM_INVALID'];
		}

		// Major routines
		if ($update)
		{
			switch ($action)
			{
				case 'delete':

					$errors = delete_dnsbl($dnsbl_id);

					if (sizeof($errors))
					{
						break;
					}

					$auth->acl_clear_prefetch();
					$cache->destroy('sql', DNSBL_TABLE);

					trigger_error($user->lang['DNSBL_DELETED'] . adm_back_link($this->u_action));

				break;

				case 'edit':
					$dnsbl_data = array(
						'dnsbl_id'		=>	$dnsbl_id
					);

					if (isset($_POST['dnsbl_reset']))
					{
						$sql = 'UPDATE ' . DNSBL_TABLE . '
							SET dnsbl_count = 0 
							WHERE dnsbl_id = ' . (int) $dnsbl_id;
						$db->sql_query($sql);
					}

				// No break here

				case 'add':

					$dnsbl_data += array(
						'dnsbl_fqdn'		=> utf8_normalize_nfc(request_var('dnsbl_fqdn', '', true)),
						'dnsbl_lookup'		=> utf8_normalize_nfc(request_var('dnsbl_lookup', '', true)),
						'dnsbl_weight'		=> request_var('dnsbl_weight', 0),
					);

					if (check_fqdn($dnsbl_data['dnsbl_fqdn']) === false)
					{
						trigger_error($user->lang['DNSBL_FQDN_NOT_VALID'] . adm_back_link($this->u_action), E_USER_WARNING);
					}
					else
					{
						$errors = update_dnsbl_data($dnsbl_data);
					}

					trigger_error($user->lang['DNSBL_ADDED_EDITED'] . adm_back_link($this->u_action));

				break;
			}
		}

		switch ($action)
		{
			case 'add':
			case 'edit':

				// Show form to create/modify a dnsbl
				if ($action == 'edit')
				{
					$this->page_title = 'DNSBL_EDIT';
					$row = get_dnsbl_info($dnsbl_id);

					if (!$update)
					{
						$dnsbl_data = $row;
					}
				}
				else
				{
					$this->page_title = 'DNSBL_CREATE';

					// Fill dnsbl data with default values
					if (!$update)
					{
						$dnsbl_data = array(
							'dnsbl_fqdn'		=> utf8_normalize_nfc(request_var('dnsbl_fqdn', '', true)),
							'dnsbl_lookup'		=> '',
							'dnsbl_weight'		=> 0,
							'dnsbl_count'		=> 0,
						);
					}
				}

				$dnsbl_weight_options = '';
				$dnsbl_weight_ary = array(WEIGHT_ZERO, WEIGHT_ONE, WEIGHT_TWO, WEIGHT_THREE, WEIGHT_FOUR, WEIGHT_FIVE);

				foreach ($dnsbl_weight_ary as $value)
				{
					$dnsbl_weight_options .= '<option value="' . $value . '"' . (($value == $dnsbl_data['dnsbl_weight']) ? ' selected="selected"' : '') . '>' . $value . '</option>';
				}

				$template->assign_vars(array(
					'S_DNSBL_EDIT'				=> true,
					'DNSBL_FQDN'				=> $dnsbl_data['dnsbl_fqdn'],
					'DNSBL_LOOKUP'				=> $dnsbl_data['dnsbl_lookup'],
					'DNSBL_COUNT'				=> $dnsbl_data['dnsbl_count'],
					'S_DNSBL_WEIGHT_OPTIONS'	=> $dnsbl_weight_options,
					'S_ERROR'					=> (sizeof($errors)) ? true : false,
					'S_ADD_ACTION'				=> ($action == 'add') ? true : false,
					'U_BACK'					=> $this->u_action,
					'U_EDIT_ACTION'				=> $this->u_action . "&amp;action=$action&amp;d=$dnsbl_id",
					'L_TITLE'					=> $user->lang[$this->page_title],
					'ERROR_MSG'					=> (sizeof($errors)) ? implode('<br />', $errors) : '',
					'S_DNS_A_RECORD'			=> (phpbb_checkdnsrr($dnsbl_data['dnsbl_fqdn'], 'A')) ? true : false,
				));

				return;

			break;

			case 'delete':

				if (!$dnsbl_id)
				{
					trigger_error($user->lang['NO_DNSBL'] . adm_back_link($this->u_action), E_USER_WARNING);
				}

				$sql = 'SELECT dnsbl_fqdn FROM ' . DNSBL_TABLE . '
					WHERE dnsbl_id= ' . $dnsbl_id;
				$result = $db->sql_query($sql);
				$dnsbl_data = $db->sql_fetchrow($result);
				$db->sql_freeresult($result);

				$template->assign_vars(array(
					'S_DNSBL_DELETE'	=> true,
					'U_ACTION'			=> $this->u_action . "&amp;action=delete&amp;d=$dnsbl_id",
					'U_BACK'			=> $this->u_action,
					'DNSBL_FQDN'		=> $dnsbl_data['dnsbl_fqdn'],
					'S_ERROR'			=> (sizeof($errors)) ? true : false,
					'ERROR_MSG'			=> (sizeof($errors)) ? implode('<br />', $errors) : '')
				);

				return;

			break;
		}

		$sql = 'SELECT dnsbl_id, dnsbl_fqdn, dnsbl_weight, dnsbl_count FROM ' . DNSBL_TABLE . '
			ORDER BY dnsbl_weight DESC, dnsbl_count DESC';
		$result = $db->sql_query($sql);

		if ($row = $db->sql_fetchrow($result))
		{
			do
			{
				$url = $this->u_action . "&amp;d={$row['dnsbl_id']}";

				$template->assign_block_vars('dnsbl', array(
					'DNSBL_FQDN'		=> $row['dnsbl_fqdn'],
					'DNSBL_WEIGHT'		=> $row['dnsbl_weight'],
					'DNSBL_COUNT'		=> $row['dnsbl_count'],

					'U_EDIT'			=> $url . '&amp;action=edit',
					'U_DELETE'			=> $url . '&amp;action=delete',
				));
			}
			while ($row = $db->sql_fetchrow($result));
		}

		$db->sql_freeresult($result);

		$template->assign_vars(array(
			'ERROR_MSG'		=> (sizeof($errors)) ? implode('<br />', $errors) : '',
			'U_ACTION'		=> $this->u_action,

		));

	}

}

/**
* Get dnsbl details
*/
function get_dnsbl_info($dnsbl_id)
{
	global $db;
		$sql = 'SELECT *
		FROM ' . DNSBL_TABLE . '
		WHERE dnsbl_id = ' . (int) $dnsbl_id;
	$result = $db->sql_query($sql);
	$row = $db->sql_fetchrow($result);
	$db->sql_freeresult($result);

	return $row;
}

/**
* Update dnsbl data
*/
function update_dnsbl_data(&$dnsbl_data)
{
	global $db, $user, $cache;

	$errors = array();

	if (!$dnsbl_data['dnsbl_fqdn'])
	{
		$errors[] = $user->lang['DNSBL_FQDN_EMPTY'];
	}

	// Unset data that are not database fields
	$dnsbl_data_sql = $dnsbl_data;

	// What are we going to do tonight Brain? The same thing we do everynight,
	// try to take over the world ... or decide whether to continue update
	// and if so, whether which groups and users we have to remove from which tables
	if (sizeof($errors))
	{
		return $errors;
	}

	if (!isset($dnsbl_data_sql['dnsbl_id']))
	{
		// no dnsbl_id means we're creating a new dnsbl
		unset($dnsbl_data_sql['type_action']);

		$sql = 'INSERT INTO ' . DNSBL_TABLE . ' ' . $db->sql_build_array('INSERT', $dnsbl_data_sql);
		$db->sql_query($sql);

		$dnsbl_data['dnsbl_id'] = $db->sql_nextid();

		add_log('admin', 'LOG_DNSBL_ADD', $dnsbl_data['dnsbl_fqdn']);
	}
	else
	{
		// Setting the dnsbl id to the dnsbl id is not really received well by some dbs. ;)
		$dnsbl_id = $dnsbl_data_sql['dnsbl_id'];
		unset($dnsbl_data_sql['dnsbl_id']);

		$sql = 'UPDATE ' . DNSBL_TABLE . '
			SET ' . $db->sql_build_array('UPDATE', $dnsbl_data_sql) . '
			WHERE dnsbl_id = ' . $dnsbl_id;
		$db->sql_query($sql);

		// Add it back
		$dnsbl_data['dnsbl_id'] = $dnsbl_id;

		add_log('admin', 'LOG_DNSBL_EDIT', $dnsbl_data['dnsbl_fqdn']);
	}

	return $errors;
}

/**
* Remove complete dnsbl
*/
function delete_dnsbl($dnsbl_id)
{
	global $db, $user, $cache;

	$sql = 'SELECT dnsbl_fqdn FROM ' . DNSBL_TABLE . '
		WHERE dnsbl_id= ' . (int) $dnsbl_id;
	$result = $db->sql_query($sql);
	$dnsbl_data = $db->sql_fetchrow($result);
	$db->sql_freeresult($result);

	$errors = array();

	$sql = 'DELETE FROM ' . DNSBL_TABLE . "
		WHERE dnsbl_id = $dnsbl_id";
	$db->sql_query($sql);

	add_log('admin', 'LOG_DNSBL_DELETE', $dnsbl_data['dnsbl_fqdn']);

	return $errors;
}

/**
* check FQDN for a valid format
* based on http://blogchuck.googlecode.com/svn/trunk/domains.php
*/
function check_fqdn($fqdn)
{
	// domain name length
	if(strlen($fqdn) > 256 or strlen($fqdn) < 4)
	{
		// FQDN too long or too short
		return false;
	}
	
	// domain name must contain at least two dots
	if(substr_count($fqdn, '.') < 2)
	{
		// FQDN too long or too short
		return false;
	}
	
	// check to see if this might be an IP address
	if(ip2long($fqdn))
	{
		// is IP
		return true;
	}
	else
	{
		// split on each . to get the nodes
		$nodes = split('\.', $fqdn);
		
		// process each node
		foreach($nodes as $node)
		{
			// each node is limited to 63 characters
			if(strlen($node) > 63)
			{
				//node too long
				return false;
			}
			
			// each node is limited to specific characters and structure
			if(!preg_match('/^[a-z\d]*(?:([a-z\d-]*[a-z\d]))$/i', $node))
			{
				//node contains invalid characters
				return false;
			}
		}
		
		// made it this far, it must be valid
		return true;
	}
}

?>