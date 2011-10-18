<?php 
/** 
*
* @package Advanced Block Mod
* @version $Id: dnsbl.php, german, v 1.005 2011/09/14 Martin Truckenbrodt Exp$
* @copyright (c) 2009 Martin Truckenbrodt 
* @license http://opensource.org/licenses/gpl-license.php GNU Public License 
*
*/

if (!defined('IN_PHPBB'))
{
	exit;
}

if (empty($lang) || !is_array($lang))
{
	$lang = array();
}

$lang = array_merge($lang, array(
	'DNSBL'							=> 'DNS Blacklist',
	'DNSBL_ADDED_EDITED'			=> 'DNS Blacklist erfolgreich hinzugefügt oder geändert',
	'DNSBL_ADMIN'					=> 'DNS Blacklist-Verwaltung',
	'DNSBL_ADMIN_EXPLAIN'			=> 'Es gibt keine Kategorien. Du siehst nur eine Liste aller DNS Blacklist-Einträge. Die DNS Blacklist-Einträge werden in der Reihenfolge der Einträge hier verarbeitet.',
	'DNSBL_COUNT'					=> 'DNS Blacklist-Zähler',
	'DNSBL_COUNT_EXPLAIN'			=> 'Die Anzahl der Spammer, die durch diesen DNS Blacklist festgestellt wurden.',
	'DNSBL_CREATE'					=> 'Erstelle DNS Blacklist',
	'DNSBL_DELETE'					=> 'Lösche DNS Blacklist',
	'DNSBL_DELETE_EXPLAIN'			=> 'Das nachfolgende Formular erlaubt es Dir einen DNS Blacklist-Eintrag zu löschen.',
	'DNSBL_DELETED'					=> 'DNS Blacklist-Eintrag erfolgreich gelöscht',
	'DNSBL_DNS_A_RECORD'			=> 'Gibt es einen DNS A Eintrag für den FQDN?',
	'DNSBL_DNS_A_RECORD_EXPLAIN'	=> 'Falls keinen DNS A Eintrag gibt, kann der FQDN falsch sein. Jedoch besitzen viele DNS Blacklisten keinen DNS A Eintrag.',
	'DNSBL_EDIT'					=> 'Ändere DNS Blacklist',
	'DNSBL_EDIT_EXPLAIN'			=> 'Das nachfolgende Formular erlaubt es Dir den DNS Blacklist-Eintrag zu bearbeiten.',
	'DNSBL_FQDN'					=> 'DNS Blacklist FQDN',
	'DNSBL_FQDN_EXPLAIN'			=> 'Der Fully Qualified Domain Name für die DNS Blacklist.',
	'DNSBL_FQDN_NOT_VALID'			=> 'Der eingegebene FQDN ist nicht gültig.',
	'DNSBL_LOOKUP'					=> 'URL für die DNS Blacklist-Überprüfung ',
	'DNSBL_LOOKUP_EXPLAIN'			=> 'Du kannst den Link benutzen um Details zum DNS Blacklist-Eintrag und Informationen zum Grund des Eintrags zu erfahren. Die IP-Addresse wird automatisch hinzugefügt. Du mußt dem Eintrag http:// voran stellen.',
	'DNSBL_LOOK_UP'					=> 'Wähle einen DNS Blacklist-Eintrag aus',
	'DNSBL_LOOK_UP_EXPLAIN'			=> 'Es ist <strong>nicht</strong> mehr als einen DNS Blacklist-Eintrag auszuwählen.',
	'DNSBL_RESET'					=> 'DNS Blacklist-Zähler zurücksetzen',
	'DNSBL_RESET_EXPLAIN'			=> 'Durch das Zurücksetzen des Zählers wir u.a. erreicht, dass andere DNS Blacklisten mit gleicher Gewichtung gegenüber dieser Blacklist bevorzugt benutzt werden.',
	'DNSBL_SETTINGS'				=> 'DNS Blacklist-Einstellungen',
	'DNSBL_WEIGHT'					=> 'DNS Blacklist Gewichtung',
	'DNSBL_WEIGHT_EXPLAIN'			=> 'IP-Adressen werden automatisch geblockt, wenn in der Summe der Gewichtungen der Schwellwert von 5 überschritten wird. Für einzlene Einträge kann man einen niedriegen Wert auswählen, wenn man sich mit diesem Eintrag nicht sicher ist oder z.B. wenn die DNS Blacklist nicht einzlene IP-Adressen sondern ganze IP-Adressbereiche listet. So kann erreicht werden, dass die IP-Adresse erst dann geblockt wird, wenn mehrere DNS Blacklist-Einträge zusammen anschlagen. 0 deaktiviert den DNS Blacklist-Eintrag.',

	'NO_DNSBL'						=> 'Kein DNS Blacklist-Eintrag gefunden. Bitte kontaktiere einen Administrator.',
	'NO_DNSBL_SELECTED'				=> 'Kein DNS Blacklist-Eintrag ausgewählt!',
	'NO_DNSBLS'						=> 'Keine DNS Blacklist-Einträge gefunden.',

	'VIEW_DNSBL'					=> '1 DNS Blacklist-Eintrag',
	'VIEW_DNSBLS'					=> '%d DNS Blacklist-Einträge',
));

?>