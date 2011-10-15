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
	'PEOPLESIGN_KEY_EXPLAIN'		=> 'Esta é a sua chave Peoplesign. Visite <a href="http://www.peoplesign.com">peoplesign.com</a> para tê-la enviada por email',
	'PEOPLESIGN_OPTIONS'			=> 'Opções Peoplesign',
	'PEOPLESIGN_OPTIONS_EXPLAIN'	=> 'Personalise a aparência do Peoplesign obtendo suas próprias opções em <a href="http://www.peoplesign.com/main/customize.html">peoplesign.com</a> (lse deixar em branco obterás a aparência padrão).',
	'PEOPLESIGN_NO_KEY'				=> 'Peça que a sua chave Peoplesign venha por email em <a href="http://www.peoplesign.com">peoplesign.com</a>',
	'PEOPLESIGN_VERSION'			=> 'Versão da Peoplesign',

	# error messages
	'ERROR_BAD_CONFIG'		=> 'erro de configuração: criação da sessão falhou. Favor contatar o administrador desse site',
	'ERROR_UNAVAILABLE'		=> 'Peoplesign está indisponível',
	'ERROR_UNEXPECTED'		=> 'status inesperado vindo de  get_peoplesign_session_status',
	'ERROR_EMPTY_KEY'		=> 'recebimento de chave privada em branco ou vazia',
	'NO_FRAMES_MESSAGE'		=> 'Parece que o seu browser não aceita "iframes", clique em <a href="http://www.peoplesign.com/main/challenge.html">here</a> para atestar que você é humano.',
	'ERROR_NO_SOCKET'		=> 'Socket para o host do Peoplesign não obtido',
	'ERROR_EXCESSIVE_DATA'	=> 'dados excessivos retornaram do chamado peloserviço web do Peoplesign',
	'ERROR_PREAMBLE'		=> 'ERRO: cliente peoplesign: ',
	'ERROR_VISITOR_IP'		=> 'ip_ do_ convidado inválido',
	'ERROR_SERVER_STATUS'	=> 'status inesperado do servidor',
	'ERROR_BAD_RESPONSE'	=> 'resposta HTTP errônea do servidor',
	'ERROR_WRONG_ANSWER'	=> 'uma das suas respostas abaixo não estava certa',

	// return codes
	'CODE_INVALID_PRIVATE_KEY'		=> 'chave_privada_ inválida',
	'CODE_SERVER_UNREACHABLE'		=> 'servidor_ não_ encontrado',
	'CODE_INVALID_SERVER_RESPONSE'	=> 'Resposta_ inválida_ do_ servidor'
));

?>
