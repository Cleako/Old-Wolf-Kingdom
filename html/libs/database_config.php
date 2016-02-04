<?php
if(!defined('IN_PHPBB'))
{
	die("You do not have permission to access this file.");
}

class DarscapeDatabase {

var $settings;

function getSettings() {

// System variables
$settings['siteDir'] = $site;

// Database variables
$settings['dbhost'] = 'localhost';
$settings['dbusername'] = 'root';
$settings['dbpassword'] = '';
$settings['dbname'] = 'wolf_kingdom';

return $settings;

}
 
}
?>