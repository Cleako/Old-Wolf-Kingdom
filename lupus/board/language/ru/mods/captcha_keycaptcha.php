<?php
/**
*
* KeyCAPTCHA [Russian]
*
* @package language
* @version $Id: phpbb_keycaptcha 2011-01-21 KeyCAPTCHA $
* @copyright (c) 2010 ITNP, Jsc (https://www.keycaptcha.com). All rights reserved.
* @license http://opensource.org/licenses/gpl-license.php GNU General Public License v2
* This software is designed to work with phpBB versions from 3.0.6 to 3.0.8
*
*/

/**
* DO NOT CHANGE
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
	'KEYCAPTCHA_LANGUAGE'		=> 'ru',
	'KEYCAPTCHA_MESSAGE_NA'		=> 'Для того чтобы использовать KeyCAPTCHA, Вам необходимо зарегестрироваться на <a href="https://www.keycaptcha.com">www.keycaptcha.com</a>',
	'KEYCAPTCHA_MESSAGE_A'		=> 'Выйдите из панели управления и проверьте все формы, защищенные KeyCAPTCHA',
	'CAPTCHA_KEYCAPTCHA'		=> 'KeyCAPTCHA',
	'KEYCAPTCHA_MESSAGE_INCORRECT'	=> 'Решение предложенное Вами неверно. Пожалуйста, прочитайте задание и попробуйте еще раз.',
	'KEYCAPTCHA_CODE_FIELD_CAPTION'	=> 'KeyCAPTCHA скрипт',
	'KEYCAPTCHA_CODE_FIELD_CAPTION_EXPLAIN'			=> 'Этот скрипт доступен в разделе "Мои сайты" на <a href="https://www.keycaptcha.com">www.keycaptcha.com</a><br />(Раздел "Мои сайты" доступен только для пользователей, вошедших в систему)',
	'KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION'		=> 'Секретный ключ',
	'KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION_EXPLAIN'	=> 'Вы можете создать этот ключ в разделе "Мои сайты" на <a href="https://www.keycaptcha.com" target="_blank">www.keycaptcha.com</a><br />(Раздел "Мои сайты" доступен только для пользователей, вошедших в систему)',
	'KEYCAPTCHA_MESSAGE_NOSCRIPT'	=> 'Для того чтобы иметь возможность отправить данную форму, Вам необходимо разрешить исполнение JavaScript в настройках Вашего браузера.',
	'KEYCAPTCHA_TASK_HEADER'	=> 'Анти-спам',
	'KEYCAPTCHA_TASK_EXPLAIN'	=> 'Выполните задание',
));
?>
