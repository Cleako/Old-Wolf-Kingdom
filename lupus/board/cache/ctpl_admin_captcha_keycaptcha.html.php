<?php if (!defined('IN_PHPBB')) exit; if ($this->_rootref['S_KEYCAPTCHA_AVAILABLE']) {  ?>

	<?php echo ((isset($this->_rootref['L_KEYCAPTCHA_MESSAGE_A'])) ? $this->_rootref['L_KEYCAPTCHA_MESSAGE_A'] : ((isset($user->lang['KEYCAPTCHA_MESSAGE_A'])) ? $user->lang['KEYCAPTCHA_MESSAGE_A'] : '{ KEYCAPTCHA_MESSAGE_A }')); ?>

<?php } else { ?>

	<?php echo ((isset($this->_rootref['L_KEYCAPTCHA_MESSAGE_NA'])) ? $this->_rootref['L_KEYCAPTCHA_MESSAGE_NA'] : ((isset($user->lang['KEYCAPTCHA_MESSAGE_NA'])) ? $user->lang['KEYCAPTCHA_MESSAGE_NA'] : '{ KEYCAPTCHA_MESSAGE_NA }')); ?>

<?php } ?>