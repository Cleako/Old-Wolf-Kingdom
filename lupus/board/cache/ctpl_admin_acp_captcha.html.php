<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<a name="maincontent"></a>

<h1><?php echo ((isset($this->_rootref['L_ACP_VC_SETTINGS'])) ? $this->_rootref['L_ACP_VC_SETTINGS'] : ((isset($user->lang['ACP_VC_SETTINGS'])) ? $user->lang['ACP_VC_SETTINGS'] : '{ ACP_VC_SETTINGS }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_ACP_VC_SETTINGS_EXPLAIN'])) ? $this->_rootref['L_ACP_VC_SETTINGS_EXPLAIN'] : ((isset($user->lang['ACP_VC_SETTINGS_EXPLAIN'])) ? $user->lang['ACP_VC_SETTINGS_EXPLAIN'] : '{ ACP_VC_SETTINGS_EXPLAIN }')); ?></p>


<form id="acp_captcha" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

<fieldset>
<legend><?php echo ((isset($this->_rootref['L_GENERAL_OPTIONS'])) ? $this->_rootref['L_GENERAL_OPTIONS'] : ((isset($user->lang['GENERAL_OPTIONS'])) ? $user->lang['GENERAL_OPTIONS'] : '{ GENERAL_OPTIONS }')); ?></legend>

<dl>
	<dt><label for="enable_confirm"><?php echo ((isset($this->_rootref['L_VISUAL_CONFIRM_REG'])) ? $this->_rootref['L_VISUAL_CONFIRM_REG'] : ((isset($user->lang['VISUAL_CONFIRM_REG'])) ? $user->lang['VISUAL_CONFIRM_REG'] : '{ VISUAL_CONFIRM_REG }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_VISUAL_CONFIRM_REG_EXPLAIN'])) ? $this->_rootref['L_VISUAL_CONFIRM_REG_EXPLAIN'] : ((isset($user->lang['VISUAL_CONFIRM_REG_EXPLAIN'])) ? $user->lang['VISUAL_CONFIRM_REG_EXPLAIN'] : '{ VISUAL_CONFIRM_REG_EXPLAIN }')); ?></span></dt>
	<dd><label><input type="radio" class="radio" id="enable_confirm" name="enable_confirm" value="1"<?php if ($this->_rootref['REG_ENABLE']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_ENABLED'])) ? $this->_rootref['L_ENABLED'] : ((isset($user->lang['ENABLED'])) ? $user->lang['ENABLED'] : '{ ENABLED }')); ?></label>
		<label><input type="radio" class="radio" name="enable_confirm" value="0"<?php if (! $this->_rootref['REG_ENABLE']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_DISABLED'])) ? $this->_rootref['L_DISABLED'] : ((isset($user->lang['DISABLED'])) ? $user->lang['DISABLED'] : '{ DISABLED }')); ?></label></dd>
</dl>
<dl>
	<dt><label for="max_reg_attempts"><?php echo ((isset($this->_rootref['L_REG_LIMIT'])) ? $this->_rootref['L_REG_LIMIT'] : ((isset($user->lang['REG_LIMIT'])) ? $user->lang['REG_LIMIT'] : '{ REG_LIMIT }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_REG_LIMIT_EXPLAIN'])) ? $this->_rootref['L_REG_LIMIT_EXPLAIN'] : ((isset($user->lang['REG_LIMIT_EXPLAIN'])) ? $user->lang['REG_LIMIT_EXPLAIN'] : '{ REG_LIMIT_EXPLAIN }')); ?></span></dt>
	<dd><input id="max_reg_attempts" type="text" size="4" maxlength="4" name="max_reg_attempts" value="<?php echo (isset($this->_rootref['REG_LIMIT'])) ? $this->_rootref['REG_LIMIT'] : ''; ?>" /></dd>
</dl>
<dl>
	<dt><label for="max_login_attempts"><?php echo ((isset($this->_rootref['L_MAX_LOGIN_ATTEMPTS'])) ? $this->_rootref['L_MAX_LOGIN_ATTEMPTS'] : ((isset($user->lang['MAX_LOGIN_ATTEMPTS'])) ? $user->lang['MAX_LOGIN_ATTEMPTS'] : '{ MAX_LOGIN_ATTEMPTS }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_MAX_LOGIN_ATTEMPTS_EXPLAIN'])) ? $this->_rootref['L_MAX_LOGIN_ATTEMPTS_EXPLAIN'] : ((isset($user->lang['MAX_LOGIN_ATTEMPTS_EXPLAIN'])) ? $user->lang['MAX_LOGIN_ATTEMPTS_EXPLAIN'] : '{ MAX_LOGIN_ATTEMPTS_EXPLAIN }')); ?></span></dt>
	<dd><input id="max_login_attempts" type="text" size="4" maxlength="4" name="max_login_attempts" value="<?php echo (isset($this->_rootref['MAX_LOGIN_ATTEMPTS'])) ? $this->_rootref['MAX_LOGIN_ATTEMPTS'] : ''; ?>" /></dd>
</dl>
<dl>
	<dt><label for="enable_post_confirm"><?php echo ((isset($this->_rootref['L_VISUAL_CONFIRM_POST'])) ? $this->_rootref['L_VISUAL_CONFIRM_POST'] : ((isset($user->lang['VISUAL_CONFIRM_POST'])) ? $user->lang['VISUAL_CONFIRM_POST'] : '{ VISUAL_CONFIRM_POST }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_VISUAL_CONFIRM_POST_EXPLAIN'])) ? $this->_rootref['L_VISUAL_CONFIRM_POST_EXPLAIN'] : ((isset($user->lang['VISUAL_CONFIRM_POST_EXPLAIN'])) ? $user->lang['VISUAL_CONFIRM_POST_EXPLAIN'] : '{ VISUAL_CONFIRM_POST_EXPLAIN }')); ?></span></dt>
	<dd><label><input type="radio" class="radio" id="enable_post_confirm" name="enable_post_confirm" value="1"<?php if ($this->_rootref['POST_ENABLE']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_ENABLED'])) ? $this->_rootref['L_ENABLED'] : ((isset($user->lang['ENABLED'])) ? $user->lang['ENABLED'] : '{ ENABLED }')); ?></label>
		<label><input type="radio" class="radio" name="enable_post_confirm" value="0"<?php if (! $this->_rootref['POST_ENABLE']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_DISABLED'])) ? $this->_rootref['L_DISABLED'] : ((isset($user->lang['DISABLED'])) ? $user->lang['DISABLED'] : '{ DISABLED }')); ?></label></dd>
</dl>
<dl>
	<dt><label for="confirm_refresh"><?php echo ((isset($this->_rootref['L_VISUAL_CONFIRM_REFRESH'])) ? $this->_rootref['L_VISUAL_CONFIRM_REFRESH'] : ((isset($user->lang['VISUAL_CONFIRM_REFRESH'])) ? $user->lang['VISUAL_CONFIRM_REFRESH'] : '{ VISUAL_CONFIRM_REFRESH }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_VISUAL_CONFIRM_REFRESH_EXPLAIN'])) ? $this->_rootref['L_VISUAL_CONFIRM_REFRESH_EXPLAIN'] : ((isset($user->lang['VISUAL_CONFIRM_REFRESH_EXPLAIN'])) ? $user->lang['VISUAL_CONFIRM_REFRESH_EXPLAIN'] : '{ VISUAL_CONFIRM_REFRESH_EXPLAIN }')); ?></span></dt>
	<dd><label><input type="radio" class="radio" id="confirm_refresh" name="confirm_refresh" value="1"<?php if ($this->_rootref['CONFIRM_REFRESH']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_ENABLED'])) ? $this->_rootref['L_ENABLED'] : ((isset($user->lang['ENABLED'])) ? $user->lang['ENABLED'] : '{ ENABLED }')); ?></label>
		<label><input type="radio" class="radio" name="confirm_refresh" value="0"<?php if (! $this->_rootref['CONFIRM_REFRESH']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_DISABLED'])) ? $this->_rootref['L_DISABLED'] : ((isset($user->lang['DISABLED'])) ? $user->lang['DISABLED'] : '{ DISABLED }')); ?></label></dd>
</dl>
</fieldset>

<fieldset>
<legend><?php echo ((isset($this->_rootref['L_AVAILABLE_CAPTCHAS'])) ? $this->_rootref['L_AVAILABLE_CAPTCHAS'] : ((isset($user->lang['AVAILABLE_CAPTCHAS'])) ? $user->lang['AVAILABLE_CAPTCHAS'] : '{ AVAILABLE_CAPTCHAS }')); ?></legend>
<dl>
	<dt><label for="captcha_select"><?php echo ((isset($this->_rootref['L_CAPTCHA_SELECT'])) ? $this->_rootref['L_CAPTCHA_SELECT'] : ((isset($user->lang['CAPTCHA_SELECT'])) ? $user->lang['CAPTCHA_SELECT'] : '{ CAPTCHA_SELECT }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_CAPTCHA_SELECT_EXPLAIN'])) ? $this->_rootref['L_CAPTCHA_SELECT_EXPLAIN'] : ((isset($user->lang['CAPTCHA_SELECT_EXPLAIN'])) ? $user->lang['CAPTCHA_SELECT_EXPLAIN'] : '{ CAPTCHA_SELECT_EXPLAIN }')); ?></span></dt>
	<dd><select id="captcha_select" name="select_captcha" onchange="(document.getElementById('acp_captcha')).submit()" ><?php echo (isset($this->_rootref['CAPTCHA_SELECT'])) ? $this->_rootref['CAPTCHA_SELECT'] : ''; ?></select></dd>
</dl>
 <?php if ($this->_rootref['S_CAPTCHA_HAS_CONFIG']) {  ?>

<dl>
	<dt><label for="configure"><?php echo ((isset($this->_rootref['L_CAPTCHA_CONFIGURE'])) ? $this->_rootref['L_CAPTCHA_CONFIGURE'] : ((isset($user->lang['CAPTCHA_CONFIGURE'])) ? $user->lang['CAPTCHA_CONFIGURE'] : '{ CAPTCHA_CONFIGURE }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_CAPTCHA_CONFIGURE_EXPLAIN'])) ? $this->_rootref['L_CAPTCHA_CONFIGURE_EXPLAIN'] : ((isset($user->lang['CAPTCHA_CONFIGURE_EXPLAIN'])) ? $user->lang['CAPTCHA_CONFIGURE_EXPLAIN'] : '{ CAPTCHA_CONFIGURE_EXPLAIN }')); ?></span></dt>
	<dd><input class="button2" type="submit" id="configure" name="configure" value="<?php echo ((isset($this->_rootref['L_CONFIGURE'])) ? $this->_rootref['L_CONFIGURE'] : ((isset($user->lang['CONFIGURE'])) ? $user->lang['CONFIGURE'] : '{ CONFIGURE }')); ?>" /></dd>
</dl>
<?php } ?>

</fieldset>

<?php if ($this->_rootref['CAPTCHA_PREVIEW_TPL']) {  ?>

<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_PREVIEW'])) ? $this->_rootref['L_PREVIEW'] : ((isset($user->lang['PREVIEW'])) ? $user->lang['PREVIEW'] : '{ PREVIEW }')); ?></legend>
<?php if (isset($this->_rootref['CAPTCHA_PREVIEW_TPL'])) { $this->_tpl_include($this->_rootref['CAPTCHA_PREVIEW_TPL']); } ?>

</fieldset>
<?php } ?>


<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_ACP_SUBMIT_CHANGES'])) ? $this->_rootref['L_ACP_SUBMIT_CHANGES'] : ((isset($user->lang['ACP_SUBMIT_CHANGES'])) ? $user->lang['ACP_SUBMIT_CHANGES'] : '{ ACP_SUBMIT_CHANGES }')); ?></legend>
	<p class="submit-buttons">
		<input class="button1" type="submit" id="main_submit" name="main_submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
		<input class="button2" type="reset" id="form_reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />&nbsp;
	</p>
	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</fieldset>
</form>

<?php $this->_tpl_include('overall_footer.html'); ?>