<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<a name="maincontent"></a>

<h1><?php echo ((isset($this->_rootref['L_ACP_AUTO_BACKUP_SETTINGS'])) ? $this->_rootref['L_ACP_AUTO_BACKUP_SETTINGS'] : ((isset($user->lang['ACP_AUTO_BACKUP_SETTINGS'])) ? $user->lang['ACP_AUTO_BACKUP_SETTINGS'] : '{ ACP_AUTO_BACKUP_SETTINGS }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_ACP_AUTO_BACKUP_SETTINGS_EXPLAIN'])) ? $this->_rootref['L_ACP_AUTO_BACKUP_SETTINGS_EXPLAIN'] : ((isset($user->lang['ACP_AUTO_BACKUP_SETTINGS_EXPLAIN'])) ? $user->lang['ACP_AUTO_BACKUP_SETTINGS_EXPLAIN'] : '{ ACP_AUTO_BACKUP_SETTINGS_EXPLAIN }')); ?></p>

<?php if ($this->_rootref['S_WARNING']) {  ?>

	<div class="errorbox">
		<h3><?php echo ((isset($this->_rootref['L_WARNING'])) ? $this->_rootref['L_WARNING'] : ((isset($user->lang['WARNING'])) ? $user->lang['WARNING'] : '{ WARNING }')); ?></h3>
		<p><?php echo (isset($this->_rootref['WARNING_MSG'])) ? $this->_rootref['WARNING_MSG'] : ''; ?></p>
	</div>
<?php } if ($this->_rootref['AUTO_BACKUP_ENABLE']) {  ?>

<fieldset>
	<legend></legend>
<dl>
	<dt><label><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_NEXT'])) ? $this->_rootref['L_AUTO_BACKUP_NEXT'] : ((isset($user->lang['AUTO_BACKUP_NEXT'])) ? $user->lang['AUTO_BACKUP_NEXT'] : '{ AUTO_BACKUP_NEXT }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_NEXT_EXPLAIN'])) ? $this->_rootref['L_AUTO_BACKUP_NEXT_EXPLAIN'] : ((isset($user->lang['AUTO_BACKUP_NEXT_EXPLAIN'])) ? $user->lang['AUTO_BACKUP_NEXT_EXPLAIN'] : '{ AUTO_BACKUP_NEXT_EXPLAIN }')); ?></span></dt>
	<dd><strong><?php echo (isset($this->_rootref['AUTO_BACKUP_NEXT'])) ? $this->_rootref['AUTO_BACKUP_NEXT'] : ''; ?></strong></dd>
</dl>
</fieldset>
<?php } ?>


<form id="acp_auto_backup" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_ACP_AUTO_BACKUP_SETTINGS'])) ? $this->_rootref['L_ACP_AUTO_BACKUP_SETTINGS'] : ((isset($user->lang['ACP_AUTO_BACKUP_SETTINGS'])) ? $user->lang['ACP_AUTO_BACKUP_SETTINGS'] : '{ ACP_AUTO_BACKUP_SETTINGS }')); ?></legend>
<dl>
	<dt><label for="auto_backup_enable"><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_ENABLE'])) ? $this->_rootref['L_AUTO_BACKUP_ENABLE'] : ((isset($user->lang['AUTO_BACKUP_ENABLE'])) ? $user->lang['AUTO_BACKUP_ENABLE'] : '{ AUTO_BACKUP_ENABLE }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_ENABLE_EXPLAIN'])) ? $this->_rootref['L_AUTO_BACKUP_ENABLE_EXPLAIN'] : ((isset($user->lang['AUTO_BACKUP_ENABLE_EXPLAIN'])) ? $user->lang['AUTO_BACKUP_ENABLE_EXPLAIN'] : '{ AUTO_BACKUP_ENABLE_EXPLAIN }')); ?></span></dt>
	<dd><label><input type="radio" class="radio" id="auto_backup_enable" name="auto_backup_enable" value="1"<?php if ($this->_rootref['AUTO_BACKUP_ENABLE']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_ENABLED'])) ? $this->_rootref['L_ENABLED'] : ((isset($user->lang['ENABLED'])) ? $user->lang['ENABLED'] : '{ ENABLED }')); ?></label>
		<label><input type="radio" class="radio" name="auto_backup_enable" value="0"<?php if (! $this->_rootref['AUTO_BACKUP_ENABLE']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_DISABLED'])) ? $this->_rootref['L_DISABLED'] : ((isset($user->lang['DISABLED'])) ? $user->lang['DISABLED'] : '{ DISABLED }')); ?></label></dd>
</dl>
<dl>
	<dt><label for="auto_backup_filetype"><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_FILETYPE'])) ? $this->_rootref['L_AUTO_BACKUP_FILETYPE'] : ((isset($user->lang['AUTO_BACKUP_FILETYPE'])) ? $user->lang['AUTO_BACKUP_FILETYPE'] : '{ AUTO_BACKUP_FILETYPE }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_FILETYPE_EXPLAIN'])) ? $this->_rootref['L_AUTO_BACKUP_FILETYPE_EXPLAIN'] : ((isset($user->lang['AUTO_BACKUP_FILETYPE_EXPLAIN'])) ? $user->lang['AUTO_BACKUP_FILETYPE_EXPLAIN'] : '{ AUTO_BACKUP_FILETYPE_EXPLAIN }')); ?></span></dt>
	<dd><?php if ($this->_rootref['GZIP']) {  ?><label><input type="radio" class="radio" id="auto_backup_filetype" name="auto_backup_filetype" value="gzip"<?php if ($this->_rootref['AB_GZIP']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_AUTO_BACKUP_GZIP'])) ? $this->_rootref['L_AUTO_BACKUP_GZIP'] : ((isset($user->lang['AUTO_BACKUP_GZIP'])) ? $user->lang['AUTO_BACKUP_GZIP'] : '{ AUTO_BACKUP_GZIP }')); ?></label><?php } if ($this->_rootref['BZIP2']) {  ?><label><input type="radio" class="radio" id="auto_backup_filetype" name="auto_backup_filetype" value="bzip2"<?php if ($this->_rootref['AB_BZIP2']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_AUTO_BACKUP_BZIP2'])) ? $this->_rootref['L_AUTO_BACKUP_BZIP2'] : ((isset($user->lang['AUTO_BACKUP_BZIP2'])) ? $user->lang['AUTO_BACKUP_BZIP2'] : '{ AUTO_BACKUP_BZIP2 }')); ?></label><?php } ?>

		<label><input type="radio" class="radio" id="auto_backup_filetype" name="auto_backup_filetype" value="text"<?php if ($this->_rootref['AB_TEXT']) {  ?> checked="checked"<?php } ?> /> <?php echo ((isset($this->_rootref['L_AUTO_BACKUP_TEXT'])) ? $this->_rootref['L_AUTO_BACKUP_TEXT'] : ((isset($user->lang['AUTO_BACKUP_TEXT'])) ? $user->lang['AUTO_BACKUP_TEXT'] : '{ AUTO_BACKUP_TEXT }')); ?></label></dd>
</dl>
<dl>
	<dt><label for="auto_backup_gc"><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_FREQ'])) ? $this->_rootref['L_AUTO_BACKUP_FREQ'] : ((isset($user->lang['AUTO_BACKUP_FREQ'])) ? $user->lang['AUTO_BACKUP_FREQ'] : '{ AUTO_BACKUP_FREQ }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_FREQ_EXPLAIN'])) ? $this->_rootref['L_AUTO_BACKUP_FREQ_EXPLAIN'] : ((isset($user->lang['AUTO_BACKUP_FREQ_EXPLAIN'])) ? $user->lang['AUTO_BACKUP_FREQ_EXPLAIN'] : '{ AUTO_BACKUP_FREQ_EXPLAIN }')); ?></span></dt>
	<dd><input type="text" id="auto_backup_gc" name="auto_backup_gc" value="<?php echo (isset($this->_rootref['AUTO_BACKUP_GC'])) ? $this->_rootref['AUTO_BACKUP_GC'] : ''; ?>" maxlength="5" size="5" /> <span><?php echo ((isset($this->_rootref['L_DAYS'])) ? $this->_rootref['L_DAYS'] : ((isset($user->lang['DAYS'])) ? $user->lang['DAYS'] : '{ DAYS }')); ?></span></dd>
</dl>
<dl>
	<dt><label for="auto_backup_copies"><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_COPIES'])) ? $this->_rootref['L_AUTO_BACKUP_COPIES'] : ((isset($user->lang['AUTO_BACKUP_COPIES'])) ? $user->lang['AUTO_BACKUP_COPIES'] : '{ AUTO_BACKUP_COPIES }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_COPIES_EXPLAIN'])) ? $this->_rootref['L_AUTO_BACKUP_COPIES_EXPLAIN'] : ((isset($user->lang['AUTO_BACKUP_COPIES_EXPLAIN'])) ? $user->lang['AUTO_BACKUP_COPIES_EXPLAIN'] : '{ AUTO_BACKUP_COPIES_EXPLAIN }')); ?></span></dt>
	<dd><input type="text" id="auto_backup_copies" name="auto_backup_copies" value="<?php echo (isset($this->_rootref['AUTO_BACKUP_COPIES'])) ? $this->_rootref['AUTO_BACKUP_COPIES'] : ''; ?>" maxlength="3" size="3" /></dd>
</dl>
<dl>
	<dt><label for="auto_backup_last_gc"><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_TIME'])) ? $this->_rootref['L_AUTO_BACKUP_TIME'] : ((isset($user->lang['AUTO_BACKUP_TIME'])) ? $user->lang['AUTO_BACKUP_TIME'] : '{ AUTO_BACKUP_TIME }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_TIME_EXPLAIN'])) ? $this->_rootref['L_AUTO_BACKUP_TIME_EXPLAIN'] : ((isset($user->lang['AUTO_BACKUP_TIME_EXPLAIN'])) ? $user->lang['AUTO_BACKUP_TIME_EXPLAIN'] : '{ AUTO_BACKUP_TIME_EXPLAIN }')); ?></span></dt>
	<dd id="auto_backup_last_gc"><label><input type="text" name="auto_backup_last_gc" class="inputbox" value="<?php echo (isset($this->_rootref['AUTO_BACKUP_TIME'])) ? $this->_rootref['AUTO_BACKUP_TIME'] : ''; ?>" maxlength="16" size="17"/><br /><span><?php echo ((isset($this->_rootref['L_AUTO_BACKUP_DATE_TIME'])) ? $this->_rootref['L_AUTO_BACKUP_DATE_TIME'] : ((isset($user->lang['AUTO_BACKUP_DATE_TIME'])) ? $user->lang['AUTO_BACKUP_DATE_TIME'] : '{ AUTO_BACKUP_DATE_TIME }')); ?></span></label></dd>
</dl>
</fieldset>

<fieldset class="submit-buttons">
	<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
	<input class="button2" type="reset" id="reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />
	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</fieldset>
</form>

<?php $this->_tpl_include('overall_footer.html'); ?>