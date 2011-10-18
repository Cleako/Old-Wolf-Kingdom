<?php
/**
*
* @package acp
* @version $Id$
* @copyright (c) 2009 Martin Truckenbrodt
* @license http://opensource.org/licenses/gpl-license.php GNU Public License
*
*/

/**
* @package module_install
*/
class acp_dnsbl_info
{
	function module()
	{
		return array(
			'filename'	=> 'acp_dnsbl',
			'title'		=> 'ACP_DNSBL',
			'version'	=> '1.0.0',
			'modes'		=> array(
				'manage'		=> array('title' => 'ACP_DNSBL', 'auth' => 'acl_a_board', 'cat' => array('ACP_DNSBL')),
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