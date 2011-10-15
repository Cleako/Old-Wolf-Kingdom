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
	'PEOPLESIGN_KEY_EXPLAIN'		=> 'Ovo je vaš peoplesign kljuc. Posjetite <a href="http://www.peoplesign.com">peoplesign.com</a> da bi ste ga dobili.',
	'PEOPLESIGN_OPTIONS'			=> 'Peoplesign postavke',
	'PEOPLESIGN_OPTIONS_EXPLAIN'	=> 'Modificirajte izgled vašeg Peoplesigna na stranici <a href="http://www.peoplesign.com/main/customize.html">peoplesign.com</a> (u koliko ovo polje ostavite prazno program ce koristiti podrazumijevane postavke).',
	'PEOPLESIGN_NO_KEY'				=> 'Zatražite da vam se pošalje peoplesign kljuc na adresi <a href="http://www.peoplesign.com">peoplesign.com</a>',
	'PEOPLESIGN_VERSION'			=> 'Peoplesign verzija',

	# error messages
	'ERROR_BAD_CONFIG'		=> 'peoplesign je pogrešno konfiguriran: neke sesije klijenat ne rade. Molimo kontaktirajte administratora ove stranice',
	'ERROR_UNAVAILABLE'		=> 'peoplesign nije dostupan',
	'ERROR_UNEXPECTED'		=> 'neocekivana greška kod get_peoplesign_session_status',
	'ERROR_EMPTY_KEY'		=> 'privatni kljuc je prazan',
	'NO_FRAMES_MESSAGE'		=> 'Vaš browser ne podržava tzv. "iframes", molimo klinkite na <a href="http://www.peoplesign.com/main/challenge.html">ovaj link</a> da biste dokazali da ste osbno pristupili stranici.',
	'ERROR_NO_SOCKET'		=> 'Ne može se otvoriti "socket" prema peoplesign poslužitelju',
	'ERROR_EXCESSIVE_DATA'	=> 'previše podatak je vraceno kao odziv peoplesign webservisa',
	'ERROR_PREAMBLE'		=> 'GREŠKA: peoplesign klijent: ',
	'ERROR_VISITOR_IP'		=> 'nevažeca IP adresa posjetitelja',
	'ERROR_SERVER_STATUS'	=> 'neocekivani status poslužitelja',
	'ERROR_BAD_RESPONSE'	=> 'pogrešan HTTP odgovor od poslužitelja',
	'ERROR_WRONG_ANSWER'	=> 'jedan od vaših odgovora je netocan',

	// return codes
	'CODE_INVALID_PRIVATE_KEY'		=> 'pogresan_privatni_kljuc',
	'CODE_SERVER_UNREACHABLE'		=> 'posluzitelj_nedostupan',
	'CODE_INVALID_SERVER_RESPONSE'	=> 'pogresan_odgovor_posluzitelja'
));

?>
