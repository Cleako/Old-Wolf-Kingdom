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

class DarscapeDbc extends LupusDatabase {
var $theQuery;
var $link;
	function DarscapeDbc(){
		$settings = LupusDatabase::getSettings();

		$host = $settings['dbhost'];
		$db = $settings['dbname'];
		$user = $settings['dbusername'];
		$pass = $settings['dbpassword'];

		$this->link = mysql_connect($host, $user, $pass);
		mysql_select_db($db);
		register_shutdown_function(array(&$this, 'close'));
	}
	function query($query) {
		$this->theQuery = $query;
		return mysql_query($query, $this->link);
	}
	function fetchArray($result) {
		return mysql_fetch_assoc($result);
	}
	function close() {
		mysql_close($this->link);
	}
}
?>