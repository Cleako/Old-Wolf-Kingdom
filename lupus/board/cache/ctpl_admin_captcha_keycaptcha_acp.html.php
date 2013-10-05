<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<a name="maincontent"></a>

<h1><?php echo ((isset($this->_rootref['L_ACP_VC_SETTINGS'])) ? $this->_rootref['L_ACP_VC_SETTINGS'] : ((isset($user->lang['ACP_VC_SETTINGS'])) ? $user->lang['ACP_VC_SETTINGS'] : '{ ACP_VC_SETTINGS }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_ACP_VC_SETTINGS_EXPLAIN'])) ? $this->_rootref['L_ACP_VC_SETTINGS_EXPLAIN'] : ((isset($user->lang['ACP_VC_SETTINGS_EXPLAIN'])) ? $user->lang['ACP_VC_SETTINGS_EXPLAIN'] : '{ ACP_VC_SETTINGS_EXPLAIN }')); ?></p>


<form id="acp_captcha" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

<fieldset>
<legend><?php echo ((isset($this->_rootref['L_GENERAL_OPTIONS'])) ? $this->_rootref['L_GENERAL_OPTIONS'] : ((isset($user->lang['GENERAL_OPTIONS'])) ? $user->lang['GENERAL_OPTIONS'] : '{ GENERAL_OPTIONS }')); ?></legend>

<dl>
	<dt><label for="keycaptcha_site_private_key"><?php echo ((isset($this->_rootref['L_KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION'])) ? $this->_rootref['L_KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION'] : ((isset($user->lang['KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION'])) ? $user->lang['KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION'] : '{ KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION_EXPLAIN'])) ? $this->_rootref['L_KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION_EXPLAIN'] : ((isset($user->lang['KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION_EXPLAIN'])) ? $user->lang['KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION_EXPLAIN'] : '{ KEYCAPTCHA_SITE_PRIVATE_KEY_FIELD_CAPTION_EXPLAIN }')); ?></span></dt>
	<dd><input id="keycaptcha_site_private_key" name="keycaptcha_site_private_key" value="<?php echo (isset($this->_rootref['KEYCAPTCHA_SITE_PRIVATE_KEY'])) ? $this->_rootref['KEYCAPTCHA_SITE_PRIVATE_KEY'] : ''; ?>" size="50" type="text" /></dd>
</dl>

<dl>
	<dt><label for="keycaptcha_code"><?php echo ((isset($this->_rootref['L_KEYCAPTCHA_CODE_FIELD_CAPTION'])) ? $this->_rootref['L_KEYCAPTCHA_CODE_FIELD_CAPTION'] : ((isset($user->lang['KEYCAPTCHA_CODE_FIELD_CAPTION'])) ? $user->lang['KEYCAPTCHA_CODE_FIELD_CAPTION'] : '{ KEYCAPTCHA_CODE_FIELD_CAPTION }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_KEYCAPTCHA_CODE_FIELD_CAPTION_EXPLAIN'])) ? $this->_rootref['L_KEYCAPTCHA_CODE_FIELD_CAPTION_EXPLAIN'] : ((isset($user->lang['KEYCAPTCHA_CODE_FIELD_CAPTION_EXPLAIN'])) ? $user->lang['KEYCAPTCHA_CODE_FIELD_CAPTION_EXPLAIN'] : '{ KEYCAPTCHA_CODE_FIELD_CAPTION_EXPLAIN }')); ?></span></dt>
	<dd><textarea id="keycaptcha_code" name="keycaptcha_code" cols="50" rows="10"><?php echo (isset($this->_rootref['KEYCAPTCHA_STATIC_CODE'])) ? $this->_rootref['KEYCAPTCHA_STATIC_CODE'] : ''; ?></textarea></dd>
</dl>

</fieldset>
<input type="hidden" id="kc_response_field" name="kc_response_field" value="false"/>
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