<?php if (!defined('IN_PHPBB')) exit; if ($this->_rootref['S_RECAPTCHA_AVAILABLE']) {  ?>

	<dl>
	<dd>
		<script type="text/javascript">
		// <![CDATA[
		var RecaptchaOptions = {
			lang : '<?php echo ((isset($this->_rootref['LA_RECAPTCHA_LANG'])) ? $this->_rootref['LA_RECAPTCHA_LANG'] : ((isset($this->_rootref['L_RECAPTCHA_LANG'])) ? addslashes($this->_rootref['L_RECAPTCHA_LANG']) : ((isset($user->lang['RECAPTCHA_LANG'])) ? addslashes($user->lang['RECAPTCHA_LANG']) : '{ RECAPTCHA_LANG }'))); ?>',
			theme : 'clean'
		};
		// ]]>
		</script>
		<script type="text/javascript" src="<?php echo (isset($this->_rootref['RECAPTCHA_SERVER'])) ? $this->_rootref['RECAPTCHA_SERVER'] : ''; ?>/challenge?k=<?php echo (isset($this->_rootref['RECAPTCHA_PUBKEY'])) ? $this->_rootref['RECAPTCHA_PUBKEY'] : ''; echo (isset($this->_rootref['RECAPTCHA_ERRORGET'])) ? $this->_rootref['RECAPTCHA_ERRORGET'] : ''; ?>"></script>
		<script type="text/javascript">
		// <![CDATA[
		<?php if ($this->_rootref['S_CONTENT_DIRECTION'] == ('rtl')) {  ?>

			document.getElementById('recaptcha_table').style.direction = 'ltr';
		<?php } ?>

		// ]]>
		</script>


	<noscript>
	<div>
		<object data="<?php echo (isset($this->_rootref['RECAPTCHA_SERVER'])) ? $this->_rootref['RECAPTCHA_SERVER'] : ''; ?>/noscript?k=<?php echo (isset($this->_rootref['RECAPTCHA_PUBKEY'])) ? $this->_rootref['RECAPTCHA_PUBKEY'] : ''; echo (isset($this->_rootref['RECAPTCHA_ERRORGET'])) ? $this->_rootref['RECAPTCHA_ERRORGET'] : ''; ?>" type="text/html" height="300" width="500"></object><br />
		<textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea>
		<input type="hidden" name="recaptcha_response_field" value="manual_challenge" />
	</div>
	</noscript>

	</dd>
	</dl>
<?php } else { ?>

<?php echo ((isset($this->_rootref['L_RECAPTCHA_NOT_AVAILABLE'])) ? $this->_rootref['L_RECAPTCHA_NOT_AVAILABLE'] : ((isset($user->lang['RECAPTCHA_NOT_AVAILABLE'])) ? $user->lang['RECAPTCHA_NOT_AVAILABLE'] : '{ RECAPTCHA_NOT_AVAILABLE }')); ?>

<?php } ?>