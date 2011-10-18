<?php
/**
*
* @package acp
* @copyright (c) 2011 Pico
* @license http://opensource.org/licenses/gpl-license.php GNU Public License 
*
*/

/**
* @package module_install
*/
class acp_auto_backup_info
{
	function module()
	{
		return array(
			'filename'	=> 'acp_auto_backup',
			'title'		=> 'ACP_AUTO_BACKUP',
			'version'	=> '1.0.0',
			'modes'		=> array(
				'index'	=> array('title' => 'ACP_AUTO_BACKUP_INDEX_TITLE', 'auth' => 'acl_a_board', 'cat' => array('ACP_CAT_DATABASE')),
			),
		);
	}

	function install()
	{
	}

	function uninstall()
	{
	}
}

?>
