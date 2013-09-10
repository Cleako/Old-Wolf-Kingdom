<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<a name="maincontent"></a>

<h1><?php echo ((isset($this->_rootref['L_ACP_VC_SETTINGS'])) ? $this->_rootref['L_ACP_VC_SETTINGS'] : ((isset($user->lang['ACP_VC_SETTINGS'])) ? $user->lang['ACP_VC_SETTINGS'] : '{ ACP_VC_SETTINGS }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_ACP_VC_SETTINGS_EXPLAIN'])) ? $this->_rootref['L_ACP_VC_SETTINGS_EXPLAIN'] : ((isset($user->lang['ACP_VC_SETTINGS_EXPLAIN'])) ? $user->lang['ACP_VC_SETTINGS_EXPLAIN'] : '{ ACP_VC_SETTINGS_EXPLAIN }')); ?></p>


<form id="acp_captcha" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

<fieldset>
<legend><?php echo ((isset($this->_rootref['L_GENERAL_OPTIONS'])) ? $this->_rootref['L_GENERAL_OPTIONS'] : ((isset($user->lang['GENERAL_OPTIONS'])) ? $user->lang['GENERAL_OPTIONS'] : '{ GENERAL_OPTIONS }')); ?></legend>

<dl>
	<dt><label for="recaptcha_pubkey"><?php echo ((isset($this->_rootref['L_RECAPTCHA_PUBLIC'])) ? $this->_rootref['L_RECAPTCHA_PUBLIC'] : ((isset($user->lang['RECAPTCHA_PUBLIC'])) ? $user->lang['RECAPTCHA_PUBLIC'] : '{ RECAPTCHA_PUBLIC }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_RECAPTCHA_PUBLIC_EXPLAIN'])) ? $this->_rootref['L_RECAPTCHA_PUBLIC_EXPLAIN'] : ((isset($user->lang['RECAPTCHA_PUBLIC_EXPLAIN'])) ? $user->lang['RECAPTCHA_PUBLIC_EXPLAIN'] : '{ RECAPTCHA_PUBLIC_EXPLAIN }')); ?></span></dt>
	<dd><input id="recaptcha_pubkey" name="recaptcha_pubkey" value="<?php echo (isset($this->_rootref['RECAPTCHA_PUBKEY'])) ? $this->_rootref['RECAPTCHA_PUBKEY'] : ''; ?>" size="50" type="text" /></dd>
</dl>
<dl>
	<dt><label for="recaptcha_privkey"><?php echo ((isset($this->_rootref['L_RECAPTCHA_PRIVATE'])) ? $this->_rootref['L_RECAPTCHA_PRIVATE'] : ((isset($user->lang['RECAPTCHA_PRIVATE'])) ? $user->lang['RECAPTCHA_PRIVATE'] : '{ RECAPTCHA_PRIVATE }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_RECAPTCHA_PRIVATE_EXPLAIN'])) ? $this->_rootref['L_RECAPTCHA_PRIVATE_EXPLAIN'] : ((isset($user->lang['RECAPTCHA_PRIVATE_EXPLAIN'])) ? $user->lang['RECAPTCHA_PRIVATE_EXPLAIN'] : '{ RECAPTCHA_PRIVATE_EXPLAIN }')); ?></span></dt>
	<dd><input id="recaptcha_privkey" name="recaptcha_privkey" value="<?php echo (isset($this->_rootref['RECAPTCHA_PRIVKEY'])) ? $this->_rootref['RECAPTCHA_PRIVKEY'] : ''; ?>" size="50" type="text" /></dd>
</dl>


</fieldset>
<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_PREVIEW'])) ? $this->_rootref['L_PREVIEW'] : ((isset($user->lang['PREVIEW'])) ? $user->lang['PREVIEW'] : '{ PREVIEW }')); ?></legend>
<?php if ($this->_rootref['PREVIEW']) {  ?>

	<div class="successbox">
		<h3><?php echo ((isset($this->_rootref['L_WARNING'])) ? $this->_rootref['L_WARNING'] : ((isset($user->lang['WARNING'])) ? $user->lang['WARNING'] : '{ WARNING }')); ?></h3>
		<p><?php echo ((isset($this->_rootref['L_CAPTCHA_PREVIEW_MSG'])) ? $this->_rootref['L_CAPTCHA_PREVIEW_MSG'] : ((isset($user->lang['CAPTCHA_PREVIEW_MSG'])) ? $user->lang['CAPTCHA_PREVIEW_MSG'] : '{ CAPTCHA_PREVIEW_MSG }')); ?></p>
	</div>
<?php } if (isset($this->_rootref['CAPTCHA_PREVIEW'])) { $this->_tpl_include($this->_rootref['CAPTCHA_PREVIEW']); } ?>

</fieldset>

<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_ACP_SUBMIT_CHANGES'])) ? $this->_rootref['L_ACP_SUBMIT_CHANGES'] : ((isset($user->lang['ACP_SUBMIT_CHANGES'])) ? $user->lang['ACP_SUBMIT_CHANGES'] : '{ ACP_SUBMIT_CHANGES }')); ?></legend>
	<p class="submit-buttons">
		<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
		<input class="button2" type="reset" id="reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />&nbsp;
	</p>
	<input type="hidden" name="select_captcha" value="<?php echo (isset($this->_rootref['CAPTCHA_NAME'])) ? $this->_rootref['CAPTCHA_NAME'] : ''; ?>" />
	<input type="hidden" name="configure" value="1" />

	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</fieldset>
</form>

<?php $this->_tpl_include('overall_footer.html'); ?>