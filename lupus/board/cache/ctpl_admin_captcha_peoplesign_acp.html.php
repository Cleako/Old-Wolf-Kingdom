<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<a name="maincontent"></a>

<h1><?php echo ((isset($this->_rootref['L_ACP_VC_SETTINGS'])) ? $this->_rootref['L_ACP_VC_SETTINGS'] : ((isset($user->lang['ACP_VC_SETTINGS'])) ? $user->lang['ACP_VC_SETTINGS'] : '{ ACP_VC_SETTINGS }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_ACP_VC_SETTINGS_EXPLAIN'])) ? $this->_rootref['L_ACP_VC_SETTINGS_EXPLAIN'] : ((isset($user->lang['ACP_VC_SETTINGS_EXPLAIN'])) ? $user->lang['ACP_VC_SETTINGS_EXPLAIN'] : '{ ACP_VC_SETTINGS_EXPLAIN }')); ?></p>


<form id="acp_captcha" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

<fieldset>
<legend><?php echo ((isset($this->_rootref['L_GENERAL_OPTIONS'])) ? $this->_rootref['L_GENERAL_OPTIONS'] : ((isset($user->lang['GENERAL_OPTIONS'])) ? $user->lang['GENERAL_OPTIONS'] : '{ GENERAL_OPTIONS }')); ?></legend>

<dl>
	<dt><label for="peoplesign_key"><?php echo ((isset($this->_rootref['L_PEOPLESIGN_KEY'])) ? $this->_rootref['L_PEOPLESIGN_KEY'] : ((isset($user->lang['PEOPLESIGN_KEY'])) ? $user->lang['PEOPLESIGN_KEY'] : '{ PEOPLESIGN_KEY }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_PEOPLESIGN_KEY_EXPLAIN'])) ? $this->_rootref['L_PEOPLESIGN_KEY_EXPLAIN'] : ((isset($user->lang['PEOPLESIGN_KEY_EXPLAIN'])) ? $user->lang['PEOPLESIGN_KEY_EXPLAIN'] : '{ PEOPLESIGN_KEY_EXPLAIN }')); ?></span></dt>
	<dd><input id="peoplesign_key" name="peoplesign_key" value="<?php echo (isset($this->_rootref['PEOPLESIGN_KEY'])) ? $this->_rootref['PEOPLESIGN_KEY'] : ''; ?>" size="50" type="text" /></dd>
</dl>
<dl>
	<dt><label for="peoplesign_options"><?php echo ((isset($this->_rootref['L_PEOPLESIGN_OPTIONS'])) ? $this->_rootref['L_PEOPLESIGN_OPTIONS'] : ((isset($user->lang['PEOPLESIGN_OPTIONS'])) ? $user->lang['PEOPLESIGN_OPTIONS'] : '{ PEOPLESIGN_OPTIONS }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_PEOPLESIGN_OPTIONS_EXPLAIN'])) ? $this->_rootref['L_PEOPLESIGN_OPTIONS_EXPLAIN'] : ((isset($user->lang['PEOPLESIGN_OPTIONS_EXPLAIN'])) ? $user->lang['PEOPLESIGN_OPTIONS_EXPLAIN'] : '{ PEOPLESIGN_OPTIONS_EXPLAIN }')); ?></span></dt>
	<dd><textarea name="peoplesign_options" value="<?php echo (isset($this->_rootref['PEOPLESIGN_OPTIONS'])) ? $this->_rootref['PEOPLESIGN_OPTIONS'] : ''; ?>" cols="75" rows="10"/><?php echo (isset($this->_rootref['PEOPLESIGN_OPTIONS'])) ? $this->_rootref['PEOPLESIGN_OPTIONS'] : ''; ?></textarea></dd>
</dl>
<div align="right" style="font-style: italic">
	<?php echo ((isset($this->_rootref['L_PEOPLESIGN_VERSION'])) ? $this->_rootref['L_PEOPLESIGN_VERSION'] : ((isset($user->lang['PEOPLESIGN_VERSION'])) ? $user->lang['PEOPLESIGN_VERSION'] : '{ PEOPLESIGN_VERSION }')); ?>: <?php echo (isset($this->_rootref['PEOPLESIGN_VERSION_ID'])) ? $this->_rootref['PEOPLESIGN_VERSION_ID'] : ''; ?>

</div>

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
		<input class="button1" type="submit" id="submit" name="preview" value="<?php echo ((isset($this->_rootref['L_PREVIEW'])) ? $this->_rootref['L_PREVIEW'] : ((isset($user->lang['PREVIEW'])) ? $user->lang['PREVIEW'] : '{ PREVIEW }')); ?>" />&nbsp;
		<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
	</p>
	<input type="hidden" name="select_captcha" value="<?php echo (isset($this->_rootref['CAPTCHA_NAME'])) ? $this->_rootref['CAPTCHA_NAME'] : ''; ?>" />
	<input type="hidden" name="configure" value="1" />

	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</fieldset>
</form>

<?php $this->_tpl_include('overall_footer.html'); ?>