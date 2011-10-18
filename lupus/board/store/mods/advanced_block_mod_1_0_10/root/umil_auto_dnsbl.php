<?php
/** 
*
* @package Advanced Block Mod
* @version $Id: umil_auto_dnsbl.php, v 1.008 2011/09/14 Martin Truckenbrodt Exp$
* @copyright (c) 2009 Martin Truckenbrodt 
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

$mod_name = 'ADVANCED_BLOCK_MOD';

$version_config_name = 'dnsbl_version';

$language_file = 'mods/umil_auto_dnsbl';

$options = array(
);

$versions = array(
	// Version 0.0.1
	'0.0.1'	=> array(
		'table_add' => array(
			array(DNSBL_TABLE, array(
					'COLUMNS'	=> array(
						'dnsbl_id'				=> array('UINT', NULL, 'auto_increment'),
						'left_id'				=> array('UINT', '0'),
						'right_id'				=> array('UINT', '0'),
						'dnsbl_fqdn'			=> array('VCHAR', ''),
						'dnsbl_lookup'			=> array('VCHAR', ''),
						'dnsbl_register'		=> array('TINT:1', '0'),
						'dnsbl_weight'			=> array('TINT:1', '0'),
					),
					'PRIMARY_KEY'	=> array('dnsbl_id'),
					'KEYS'		=> array(
						'left_right_id'		=> array('INDEX', array('left_id', 'right_id')),
					),
				),
			),
		),
		'table_column_add' => array(
			array(LOG_TABLE, 'dnsbl_id', array('UINT', '0')),
		),
		'module_add' => array(
			array('acp', 'ACP_SERVER_CONFIGURATION', array(
					'module_basename'		=> 'dnsbl',
					'modes'					=> array('manage'),
				),
			),
			array('acp', 'ACP_FORUM_LOGS', array(
					'module_basename'		=> 'logs',
					'modes'					=> array('block'),
				),
			),
		),
	),
	// Version 1.0.0
	'1.0.0' => array(
		'config_add' => array(
			array('log_check_dnsbl', 1, false),
			array('log_email_check_mx', 1, false),
			array('check_tz', 1, false),
			array('log_check_tz', 1, false),
		),
	),
	// Version 1.0.1
	'1.0.1' => array(
	),
	// Version 1.0.2
	'1.0.2' => array(
		'table_row_insert'	=> array(
			array(DNSBL_TABLE, array(
				array(
					'left_id'			=> 1,
					'right_id'			=> 2,
					'dnsbl_fqdn'		=> 'sbl-xbl.spamhaus.org',
					'dnsbl_lookup'		=> 'http://www.spamhaus.org/query/bl?ip=',
					'dnsbl_register'	=> 0,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 3,
					'right_id'			=> 4,
					'dnsbl_fqdn'		=> 'bl.spamcop.net',
					'dnsbl_lookup'		=> 'http://spamcop.net/bl.shtml?',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 5,
					'right_id'			=> 6,
					'dnsbl_fqdn'		=> 'no-more-funn.moensted.dk',
					'dnsbl_lookup'		=> 'http://moensted.dk/spam/no-more-funn/?Submit=Submit&addr=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 4,
				),
				array(
					'left_id'			=> 7,
					'right_id'			=> 8,
					'dnsbl_fqdn'		=> 'blackholes.five-ten-sg.com',
					'dnsbl_lookup'		=> 'http://www.five-ten-sg.com/blackhole.php?Search=Search&ip=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 9,
					'right_id'			=> 10,
					'dnsbl_fqdn'		=> 'cbl.abuseat.org',
					'dnsbl_lookup'		=> 'http://cbl.abuseat.org/lookup.cgi?.submit=Lookup&ip=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 11,
					'right_id'			=> 12,
					'dnsbl_fqdn'		=> 'bl.spamcannibal.org',
					'dnsbl_lookup'		=> '',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 13,
					'right_id'			=> 14,
					'dnsbl_fqdn'		=> 'dnsbl-1.uceprotect.net',
					'dnsbl_lookup'		=> 'http://www.uceprotect.net/en/rblcheck.php?ipr=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 15,
					'right_id'			=> 16,
					'dnsbl_fqdn'		=> 'dnsbl-2.uceprotect.net',
					'dnsbl_lookup'		=> 'http://www.uceprotect.net/en/rblcheck.php?ipr=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 2,
				),
				array(
					'left_id'			=> 17,
					'right_id'			=> 18,
					'dnsbl_fqdn'		=> 'dnsbl-3.uceprotect.net',
					'dnsbl_lookup'		=> 'http://www.uceprotect.net/en/rblcheck.php?ipr=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 19,
					'right_id'			=> 20,
					'dnsbl_fqdn'		=> 'dnsbl.sorbs.net',
					'dnsbl_lookup'		=> '',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 2,
				),
				array(
					'left_id'			=> 21,
					'right_id'			=> 22,
					'dnsbl_fqdn'		=> 'ips.backscatterer.org',
					'dnsbl_lookup'		=> 'http://www.backscatterer.org/?ip=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 1,
				),
			)),
		),
		'config_add' => array(
			array('dnsbl_list_version', 1, false),
		),
	),
	'1.0.3' => array(
		'table_row_remove'	=> array(
			array(DNSBL_TABLE, array(
					'dnsbl_fqdn'		=> 'dnsbl.sorbs.net',
			)),
			array(DNSBL_TABLE, array(
					'dnsbl_fqdn'		=> 'ips.backscatterer.org',
			)),
		),
		'table_row_update'	=> array(
			array(DNSBL_TABLE,
				array(
					'left_id'			=> 7,
					'right_id'			=> 8,
					'dnsbl_fqdn'		=> 'blackholes.five-ten-sg.com',
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 27,
					'right_id'			=> 28,
					'dnsbl_fqdn'		=> 'blackholes.five-ten-sg.com',
					'dnsbl_weight'		=> 1,
				),
			),
			array(DNSBL_TABLE,
				array(
					'left_id'			=> 15,
					'right_id'			=> 16,
					'dnsbl_fqdn'		=> 'dnsbl-2.uceprotect.net',
					'dnsbl_weight'		=> 2,
				),
				array(
					'left_id'			=> 25,
					'right_id'			=> 26,
					'dnsbl_fqdn'		=> 'dnsbl-2.uceprotect.net',
					'dnsbl_weight'		=> 1,
				),
			),
			array(DNSBL_TABLE,
				array(
					'left_id'			=> 17,
					'right_id'			=> 18,
					'dnsbl_fqdn'		=> 'dnsbl-3.uceprotect.net',
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 23,
					'right_id'			=> 24,
					'dnsbl_fqdn'		=> 'dnsbl-3.uceprotect.net',
					'dnsbl_weight'		=> 1,
				),
			),
			array(DNSBL_TABLE,
				array(
					'left_id'			=> 5,
					'right_id'			=> 6,
					'dnsbl_fqdn'		=> 'no-more-funn.moensted.dk',
					'dnsbl_weight'		=> 4,
				),
				array(
					'left_id'			=> 21,
					'right_id'			=> 22,
					'dnsbl_fqdn'		=> 'no-more-funn.moensted.dk',
					'dnsbl_weight'		=> 1,
				),
			),
			array(DNSBL_TABLE,
				array(
					'left_id'			=> 3,
					'right_id'			=> 4,
					'dnsbl_fqdn'		=> 'bl.spamcop.net',
				),
				array(
					'left_id'			=> 19,
					'right_id'			=> 20,
					'dnsbl_fqdn'		=> 'bl.spamcop.net',
				),
			),
			array(DNSBL_TABLE,
				array(
					'left_id'			=> 13,
					'right_id'			=> 14,
					'dnsbl_fqdn'		=> 'dnsbl-1.uceprotect.net',
				),
				array(
					'left_id'			=> 17,
					'right_id'			=> 18,
					'dnsbl_fqdn'		=> 'dnsbl-1.uceprotect.net',
				),
			),
			array(DNSBL_TABLE,
				array(
					'left_id'			=> 11,
					'right_id'			=> 12,
					'dnsbl_fqdn'		=> 'bl.spamcannibal.org',
				),
				array(
					'left_id'			=> 15,
					'right_id'			=> 16,
					'dnsbl_fqdn'		=> 'bl.spamcannibal.org',
				),
			),
			array(DNSBL_TABLE,
				array(
					'left_id'			=> 9,
					'right_id'			=> 10,
					'dnsbl_fqdn'		=> 'cbl.abuseat.org',
				),
				array(
					'left_id'			=> 13,
					'right_id'			=> 14,
					'dnsbl_fqdn'		=> 'cbl.abuseat.org',
				),
			),
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'sbl-xbl.spamhaus.org',
					'dnsbl_weight'		=> 5,
				),
				array(
					'dnsbl_fqdn'		=> 'sbl-xbl.spamhaus.org',
					'dnsbl_weight'		=> 4,
				),
			),
		),
		'table_row_insert'	=> array(
			array(DNSBL_TABLE, array(
				array(
					'left_id'			=> 3,
					'right_id'			=> 4,
					'dnsbl_fqdn'		=> 'opm.tornevall.org',
					'dnsbl_lookup'		=> 'http://www.stopforumspam.com/api?ip=',
					'dnsbl_register'	=> 0,
					'dnsbl_weight'		=> 4,
				),
				array(
					'left_id'			=> 5,
					'right_id'			=> 6,
					'dnsbl_fqdn'		=> 'spam.dnsbl.sorbs.net',
					'dnsbl_lookup'		=> 'http://www.sorbs.net/lookup.shtml?',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 7,
					'right_id'			=> 8,
					'dnsbl_fqdn'		=> 'smtp.dnsbl.sorbs.net',
					'dnsbl_lookup'		=> 'http://www.sorbs.net/lookup.shtml?',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 9,
					'right_id'			=> 10,
					'dnsbl_fqdn'		=> 'socks.dnsbl.sorbs.net',
					'dnsbl_lookup'		=> 'http://www.sorbs.net/lookup.shtml?',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 11,
					'right_id'			=> 12,
					'dnsbl_fqdn'		=> 'escalations.dnsbl.sorbs.net',
					'dnsbl_lookup'		=> 'http://www.sorbs.net/lookup.shtml?',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
			)),
		),
	),
	// Version 1.0.4
	'1.0.4' => array(
		'config_remove' => array(
			array('dnsbl_list_version'),
		),
		'table_index_add' => array(
			array(DNSBL_TABLE, 'dnsbl_fqdn', 'dnsbl_fqdn')
		),
		'table_row_update'	=> array(
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'blackholes.five-ten-sg.com',
					'dnsbl_weight'		=> 1,
				),
				array(
					'dnsbl_fqdn'		=> 'blackholes.five-ten-sg.com',
					'dnsbl_weight'		=> 0,
				),
			),
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'dnsbl-2.uceprotect.net',
					'dnsbl_weight'		=> 1,
				),
				array(
					'dnsbl_fqdn'		=> 'dnsbl-2.uceprotect.net',
					'dnsbl_weight'		=> 2,
				),
			),
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'no-more-funn.moensted.dk',
					'dnsbl_weight'		=> 1,
				),
				array(
					'dnsbl_fqdn'		=> 'no-more-funn.moensted.dk',
					'dnsbl_weight'		=> 0,
				),
			),
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'bl.spamcannibal.org',
					'dnsbl_weight'		=> 5,
				),
				array(
					'dnsbl_fqdn'		=> 'bl.spamcannibal.org',
					'dnsbl_weight'		=> 4,
				),
			),
		),
		'table_row_insert'	=> array(
			array(DNSBL_TABLE, array(
				array(
					'left_id'			=> 29,
					'right_id'			=> 30,
					'dnsbl_fqdn'		=> 'b.barracudacentral.org',
					'dnsbl_lookup'		=> '',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 1,
				),
				array(
					'left_id'			=> 31,
					'right_id'			=> 32,
					'dnsbl_fqdn'		=> 'rbl.atlbl.net',
					'dnsbl_lookup'		=> 'http://search.atlbl.com/search.php?q=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
				array(
					'left_id'			=> 33,
					'right_id'			=> 34,
					'dnsbl_fqdn'		=> 'access.atlbl.net',
					'dnsbl_lookup'		=> 'http://search.atlbl.com/search.php?q=',
					'dnsbl_register'	=> 1,
					'dnsbl_weight'		=> 5,
				),
			)),
		),
	),
	// Version 1.0.6
	'1.0.6' => array(
	),
	// Version 1.0.7
	'1.0.7' => array(
		'table_column_add' => array(
			array(DNSBL_TABLE, 'dnsbl_count', array('UINT', '0')),
		),
		'table_column_remove' => array(
			array(DNSBL_TABLE, 'left_id'),
			array(DNSBL_TABLE, 'right_id'),
			array(DNSBL_TABLE, 'dnsbl_register'),
		),
		'table_row_update'	=> array(
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'opm.tornevall.org',
				),
				array(
					'dnsbl_fqdn'		=> 'dnsbl.tornevall.org',
				),
			),
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'smtp.dnsbl.sorbs.net',
					'dnsbl_weight'		=> 5,
				),
				array(
					'dnsbl_fqdn'		=> 'smtp.dnsbl.sorbs.net',
					'dnsbl_weight'		=> 0,
				),
			),
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'socks.dnsbl.sorbs.net',
					'dnsbl_weight'		=> 5,
				),
				array(
					'dnsbl_fqdn'		=> 'socks.dnsbl.sorbs.net',
					'dnsbl_weight'		=> 0,
				),
			),
		),
	),
	// Version 1.0.8
	'1.0.8' => array(
		'table_row_insert'	=> array(
			array(DNSBL_TABLE, array(
				array(
					'dnsbl_fqdn'		=> 'combined.njabl.org',
					'dnsbl_lookup'		=> 'http://dnsbl.njabl.org/lookup.html',
					'dnsbl_weight'		=> 3,
				),
			)),
		),
		'table_row_update'	=> array(
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'dnsbl.tornevall.org',
					'dnsbl_weight'		=> 4,
				),
				array(
					'dnsbl_fqdn'		=> 'dnsbl.tornevall.org',
					'dnsbl_weight'		=> 5,
				),
			),
			array(DNSBL_TABLE,
				array(
					'dnsbl_fqdn'		=> 'access.atlbl.net',
					'dnsbl_weight'		=> 5,
				),
				array(
					'dnsbl_fqdn'		=> 'access.atlbl.net',
					'dnsbl_weight'		=> 0,
				),
			),
		),
	),
	// Version 1.0.9
	'1.0.9' => array(
	),
	// Version 1.0.10
	'1.0.10' => array(
		'cache_purge' => array(),
	),
);

// Include the UMIF Auto file and everything else will be handled automatically.
include($phpbb_root_path . 'umil/umil_auto.' . $phpEx);

?>