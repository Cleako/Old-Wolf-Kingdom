<?php
/**
*
* peoplesign [Portuguese]
*
* @package language
* @version $Id: captcha_peoplesign.php,v 1.0.15 2011/04/10 15:00 hookerb Exp$
* @copyright (c) 2008-2011 Myricomp LLC
* @license http://opensource.org/licenses/gpl-license.php GNU Public License, v2
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
	'PEOPLESIGN_LANG'				=> 'pt-br',
	'CAPTCHA_PEOPLESIGN'			=> 'peoplesign:)',
	'PEOPLESIGN_KEY'				=> 'Peoplesign Key',
	'PEOPLESIGN_KEY_EXPLAIN'		=> 'Esta � a sua chave Peoplesign. Visite <a href="http://www.peoplesign.com">peoplesign.com</a> para t�-la enviada por email',
	'PEOPLESIGN_OPTIONS'			=> 'Op��es Peoplesign',
	'PEOPLESIGN_OPTIONS_EXPLAIN'	=> 'Personalise a apar�ncia do Peoplesign obtendo suas pr�prias op��es em <a href="http://www.peoplesign.com/main/customize.html">peoplesign.com</a> (lse deixar em branco obter�s a apar�ncia padr�o).',
	'PEOPLESIGN_NO_KEY'				=> 'Pe�a que a sua chave Peoplesign venha por email em <a href="http://www.peoplesign.com">peoplesign.com</a>',
	'PEOPLESIGN_VERSION'			=> 'Vers�o da Peoplesign',

	# error messages
	'ERROR_BAD_CONFIG'		=> 'erro de configura��o: cria��o da sess�o falhou. Favor contatar o administrador desse site',
	'ERROR_UNAVAILABLE'		=> 'Peoplesign est� indispon�vel',
	'ERROR_UNEXPECTED'		=> 'status inesperado vindo de  get_peoplesign_session_status',
	'ERROR_EMPTY_KEY'		=> 'recebimento de chave privada em branco ou vazia',
	'NO_FRAMES_MESSAGE'		=> 'Parece que o seu browser n�o aceita "iframes", clique em <a href="http://www.peoplesign.com/main/challenge.html">here</a> para atestar que voc� � humano.',
	'ERROR_NO_SOCKET'		=> 'Socket para o host do Peoplesign n�o obtido',
	'ERROR_EXCESSIVE_DATA'	=> 'dados excessivos retornaram do chamado peloservi�o web do Peoplesign',
	'ERROR_PREAMBLE'		=> 'ERRO: cliente peoplesign: ',
	'ERROR_VISITOR_IP'		=> 'ip_ do_ convidado inv�lido',
	'ERROR_SERVER_STATUS'	=> 'status inesperado do servidor',
	'ERROR_BAD_RESPONSE'	=> 'resposta HTTP err�nea do servidor',
	'ERROR_WRONG_ANSWER'	=> 'uma das suas respostas abaixo n�o estava certa',

	// return codes
	'CODE_INVALID_PRIVATE_KEY'		=> 'chave_privada_ inv�lida',
	'CODE_SERVER_UNREACHABLE'		=> 'servidor_ n�o_ encontrado',
	'CODE_INVALID_SERVER_RESPONSE'	=> 'Resposta_ inv�lida_ do_ servidor'
));

?>
