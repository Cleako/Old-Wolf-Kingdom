<?php
/**
*
* peoplesign [Croatian translation by T. Uroic]
*
* @package language
* @version $Id: captcha_peoplesign.php,v 1.0.15 2011/04/10 15:00 hookerb Exp$
* @copyright (c) 2008-2011 Myricomp LLC
* @license http://opensource.org/licenses/gpl-license.php GNU Public License, v2
*
*
*/

/**
* @ignore
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
	'PEOPLESIGN_LANG'				=> 'hr',
	'CAPTCHA_PEOPLESIGN'			=> 'peoplesign:)',
	'PEOPLESIGN_KEY'				=> 'Peoplesign Kljuc',
	'PEOPLESIGN_KEY_EXPLAIN'		=> 'Ovo je va� peoplesign kljuc. Posjetite <a href="http://www.peoplesign.com">peoplesign.com</a> da bi ste ga dobili.',
	'PEOPLESIGN_OPTIONS'			=> 'Peoplesign postavke',
	'PEOPLESIGN_OPTIONS_EXPLAIN'	=> 'Modificirajte izgled va�eg Peoplesigna na stranici <a href="http://www.peoplesign.com/main/customize.html">peoplesign.com</a> (u koliko ovo polje ostavite prazno program ce koristiti podrazumijevane postavke).',
	'PEOPLESIGN_NO_KEY'				=> 'Zatra�ite da vam se po�alje peoplesign kljuc na adresi <a href="http://www.peoplesign.com">peoplesign.com</a>',
	'PEOPLESIGN_VERSION'			=> 'Peoplesign verzija',

	# error messages
	'ERROR_BAD_CONFIG'		=> 'peoplesign je pogre�no konfiguriran: neke sesije klijenat ne rade. Molimo kontaktirajte administratora ove stranice',
	'ERROR_UNAVAILABLE'		=> 'peoplesign nije dostupan',
	'ERROR_UNEXPECTED'		=> 'neocekivana gre�ka kod get_peoplesign_session_status',
	'ERROR_EMPTY_KEY'		=> 'privatni kljuc je prazan',
	'NO_FRAMES_MESSAGE'		=> 'Va� browser ne podr�ava tzv. "iframes", molimo klinkite na <a href="http://www.peoplesign.com/main/challenge.html">ovaj link</a> da biste dokazali da ste osbno pristupili stranici.',
	'ERROR_NO_SOCKET'		=> 'Ne mo�e se otvoriti "socket" prema peoplesign poslu�itelju',
	'ERROR_EXCESSIVE_DATA'	=> 'previ�e podatak je vraceno kao odziv peoplesign webservisa',
	'ERROR_PREAMBLE'		=> 'GRE�KA: peoplesign klijent: ',
	'ERROR_VISITOR_IP'		=> 'neva�eca IP adresa posjetitelja',
	'ERROR_SERVER_STATUS'	=> 'neocekivani status poslu�itelja',
	'ERROR_BAD_RESPONSE'	=> 'pogre�an HTTP odgovor od poslu�itelja',
	'ERROR_WRONG_ANSWER'	=> 'jedan od va�ih odgovora je netocan',

	// return codes
	'CODE_INVALID_PRIVATE_KEY'		=> 'pogresan_privatni_kljuc',
	'CODE_SERVER_UNREACHABLE'		=> 'posluzitelj_nedostupan',
	'CODE_INVALID_SERVER_RESPONSE'	=> 'pogresan_odgovor_posluzitelja'
));

?>
