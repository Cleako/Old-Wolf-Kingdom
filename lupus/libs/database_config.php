<?php
if(!defined('IN_PHPBB'))
{
	die("You do not have permission to access this file.");
}

class LupusDatabase {

var $settings;

function getSettings() {

// System variables
$settings['siteDir'] = $site;

// Database variables
$settings['dbhost'] = 'localhost';
$settings['dbusername'] = 'lupus';
$settings['dbpassword'] = 'Wolfbite5';
$settings['dbname'] = 'lupus_regnum';

return $settings;

}
 
}
?>