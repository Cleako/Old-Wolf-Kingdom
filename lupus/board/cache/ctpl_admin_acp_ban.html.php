<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<a name="maincontent"></a>

<p><?php echo ((isset($this->_rootref['L_ACP_BAN_EXPLAIN'])) ? $this->_rootref['L_ACP_BAN_EXPLAIN'] : ((isset($user->lang['ACP_BAN_EXPLAIN'])) ? $user->lang['ACP_BAN_EXPLAIN'] : '{ ACP_BAN_EXPLAIN }')); ?></p>

<h1><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_EXPLAIN'])) ? $this->_rootref['L_EXPLAIN'] : ((isset($user->lang['EXPLAIN'])) ? $user->lang['EXPLAIN'] : '{ EXPLAIN }')); ?></p>

<script type="text/javascript">
// <![CDATA[

	var ban_length = new Array();
		ban_length[-1] = '';
	<?php $_ban_length_count = (isset($this->_tpldata['ban_length'])) ? sizeof($this->_tpldata['ban_length']) : 0;if ($_ban_length_count) {for ($_ban_length_i = 0; $_ban_length_i < $_ban_length_count; ++$_ban_length_i){$_ban_length_val = &$this->_tpldata['ban_length'][$_ban_length_i]; ?>

		ban_length['<?php echo $_ban_length_val['BAN_ID']; ?>'] = '<?php echo $_ban_length_val['A_LENGTH']; ?>';
	<?php }} ?>


	var ban_reason = new Array();
		ban_reason[-1] = '';
	<?php $_ban_reason_count = (isset($this->_tpldata['ban_reason'])) ? sizeof($this->_tpldata['ban_reason']) : 0;if ($_ban_reason_count) {for ($_ban_reason_i = 0; $_ban_reason_i < $_ban_reason_count; ++$_ban_reason_i){$_ban_reason_val = &$this->_tpldata['ban_reason'][$_ban_reason_i]; ?>

		ban_reason['<?php echo $_ban_reason_val['BAN_ID']; ?>'] = '<?php echo $_ban_reason_val['A_REASON']; ?>';
	<?php }} ?>


	var ban_give_reason = new Array();
		ban_give_reason[-1] = '';
	<?php $_ban_give_reason_count = (isset($this->_tpldata['ban_give_reason'])) ? sizeof($this->_tpldata['ban_give_reason']) : 0;if ($_ban_give_reason_count) {for ($_ban_give_reason_i = 0; $_ban_give_reason_i < $_ban_give_reason_count; ++$_ban_give_reason_i){$_ban_give_reason_val = &$this->_tpldata['ban_give_reason'][$_ban_give_reason_i]; ?>

		ban_give_reason['<?php echo $_ban_give_reason_val['BAN_ID']; ?>'] = '<?php echo $_ban_give_reason_val['A_REASON']; ?>';
	<?php }} ?>


	function display_details(option)
	{
		document.getElementById('acp_unban').unbangivereason.innerHTML = ban_give_reason[option];
		document.getElementById('acp_unban').unbanreason.innerHTML = ban_reason[option];
		document.getElementById('acp_unban').unbanlength.value = ban_length[option];
	}

// ]]>
</script>

<form id="acp_ban" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>
<dl>
	<dt><label for="ban"><?php echo ((isset($this->_rootref['L_BAN_CELL'])) ? $this->_rootref['L_BAN_CELL'] : ((isset($user->lang['BAN_CELL'])) ? $user->lang['BAN_CELL'] : '{ BAN_CELL }')); ?>:</label></dt>
	<dd><textarea name="ban" cols="40" rows="3" id="ban"></textarea></dd>
	<?php if ($this->_rootref['S_USERNAME_BAN']) {  ?><dd>[ <a href="<?php echo (isset($this->_rootref['U_FIND_USERNAME'])) ? $this->_rootref['U_FIND_USERNAME'] : ''; ?>" onclick="find_username(this.href); return false;"><?php echo ((isset($this->_rootref['L_FIND_USERNAME'])) ? $this->_rootref['L_FIND_USERNAME'] : ((isset($user->lang['FIND_USERNAME'])) ? $user->lang['FIND_USERNAME'] : '{ FIND_USERNAME }')); ?></a> ]</dd><?php } ?>

</dl>
<dl>
	<dt><label for="banlength"><?php echo ((isset($this->_rootref['L_BAN_LENGTH'])) ? $this->_rootref['L_BAN_LENGTH'] : ((isset($user->lang['BAN_LENGTH'])) ? $user->lang['BAN_LENGTH'] : '{ BAN_LENGTH }')); ?>:</label></dt>
	<dd><label for="banlength"><select name="banlength" id="banlength" onchange="if(this.value==-1){document.getElementById('banlengthother').style.display = 'block';}else{document.getElementById('banlengthother').style.display='none';}"><?php echo (isset($this->_rootref['S_BAN_END_OPTIONS'])) ? $this->_rootref['S_BAN_END_OPTIONS'] : ''; ?></select></label></dd>
	<dd id="banlengthother" style="display: none;"><label><input type="text" name="banlengthother" class="inputbox" /><br /><span><?php echo ((isset($this->_rootref['L_YEAR_MONTH_DAY'])) ? $this->_rootref['L_YEAR_MONTH_DAY'] : ((isset($user->lang['YEAR_MONTH_DAY'])) ? $user->lang['YEAR_MONTH_DAY'] : '{ YEAR_MONTH_DAY }')); ?></span></label></dd>
</dl>
<dl>
	<dt><label for="banexclude"><?php echo ((isset($this->_rootref['L_BAN_EXCLUDE'])) ? $this->_rootref['L_BAN_EXCLUDE'] : ((isset($user->lang['BAN_EXCLUDE'])) ? $user->lang['BAN_EXCLUDE'] : '{ BAN_EXCLUDE }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_BAN_EXCLUDE_EXPLAIN'])) ? $this->_rootref['L_BAN_EXCLUDE_EXPLAIN'] : ((isset($user->lang['BAN_EXCLUDE_EXPLAIN'])) ? $user->lang['BAN_EXCLUDE_EXPLAIN'] : '{ BAN_EXCLUDE_EXPLAIN }')); ?></span></dt>
	<dd><label><input type="radio" name="banexclude" value="1" class="radio" /> <?php echo ((isset($this->_rootref['L_YES'])) ? $this->_rootref['L_YES'] : ((isset($user->lang['YES'])) ? $user->lang['YES'] : '{ YES }')); ?></label>
		<label><input type="radio" name="banexclude" id="banexclude" value="0" checked="checked" class="radio" /> <?php echo ((isset($this->_rootref['L_NO'])) ? $this->_rootref['L_NO'] : ((isset($user->lang['NO'])) ? $user->lang['NO'] : '{ NO }')); ?></label></dd>
</dl>
<dl>
	<dt><label for="banreason"><?php echo ((isset($this->_rootref['L_BAN_REASON'])) ? $this->_rootref['L_BAN_REASON'] : ((isset($user->lang['BAN_REASON'])) ? $user->lang['BAN_REASON'] : '{ BAN_REASON }')); ?>:</label></dt>
	<dd><input name="banreason" type="text" class="text medium" maxlength="255" id="banreason" /></dd>
</dl>
<dl>
	<dt><label for="bangivereason"><?php echo ((isset($this->_rootref['L_BAN_GIVE_REASON'])) ? $this->_rootref['L_BAN_GIVE_REASON'] : ((isset($user->lang['BAN_GIVE_REASON'])) ? $user->lang['BAN_GIVE_REASON'] : '{ BAN_GIVE_REASON }')); ?>:</label></dt>
	<dd><input name="bangivereason" type="text" class="text medium" maxlength="255" id="bangivereason" /></dd>
</dl>

<p class="submit-buttons">
	<input class="button1" type="submit" id="bansubmit" name="bansubmit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
	<input class="button2" type="reset" id="banreset" name="banreset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />
</p>
<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</fieldset>
</form>

<br /><br />

<h1><?php echo ((isset($this->_rootref['L_UNBAN_TITLE'])) ? $this->_rootref['L_UNBAN_TITLE'] : ((isset($user->lang['UNBAN_TITLE'])) ? $user->lang['UNBAN_TITLE'] : '{ UNBAN_TITLE }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_UNBAN_EXPLAIN'])) ? $this->_rootref['L_UNBAN_EXPLAIN'] : ((isset($user->lang['UNBAN_EXPLAIN'])) ? $user->lang['UNBAN_EXPLAIN'] : '{ UNBAN_EXPLAIN }')); ?></p>

<form id="acp_unban" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_UNBAN_TITLE'])) ? $this->_rootref['L_UNBAN_TITLE'] : ((isset($user->lang['UNBAN_TITLE'])) ? $user->lang['UNBAN_TITLE'] : '{ UNBAN_TITLE }')); ?></legend>

<?php if ($this->_rootref['S_BANNED_OPTIONS']) {  ?>

	<dl>
		<dt><label for="unban"><?php echo ((isset($this->_rootref['L_BAN_CELL'])) ? $this->_rootref['L_BAN_CELL'] : ((isset($user->lang['BAN_CELL'])) ? $user->lang['BAN_CELL'] : '{ BAN_CELL }')); ?>:</label></dt>
		<dd><select id="unban" name="unban[]" multiple="multiple" size="10" style="width: 50%" onchange="if (this.selectedIndex > -1) display_details(this.options[this.selectedIndex].value); else display_details(-1);"><?php echo (isset($this->_rootref['BANNED_OPTIONS'])) ? $this->_rootref['BANNED_OPTIONS'] : ''; ?></select></dd>
	</dl>
	<dl>
		<dt><label for="unbanlength"><?php echo ((isset($this->_rootref['L_BAN_LENGTH'])) ? $this->_rootref['L_BAN_LENGTH'] : ((isset($user->lang['BAN_LENGTH'])) ? $user->lang['BAN_LENGTH'] : '{ BAN_LENGTH }')); ?>:</label></dt>
		<dd><input style="border: 0;" type="text" class="text full" readonly="readonly" name="unbanlength" id="unbanlength" /></dd>
	</dl>
	<dl>
		<dt><label for="unbanreason"><?php echo ((isset($this->_rootref['L_BAN_REASON'])) ? $this->_rootref['L_BAN_REASON'] : ((isset($user->lang['BAN_REASON'])) ? $user->lang['BAN_REASON'] : '{ BAN_REASON }')); ?>:</label></dt>
		<dd><textarea style="border: 0;" class="text full" readonly="readonly" name="unbanreason" id="unbanreason" rows="5" cols="80">&nbsp;</textarea></dd>
	</dl>
	<dl>
		<dt><label for="unbangivereason"><?php echo ((isset($this->_rootref['L_BAN_GIVE_REASON'])) ? $this->_rootref['L_BAN_GIVE_REASON'] : ((isset($user->lang['BAN_GIVE_REASON'])) ? $user->lang['BAN_GIVE_REASON'] : '{ BAN_GIVE_REASON }')); ?>:</label></dt>
		<dd><textarea style="border: 0;" class="text full" readonly="readonly" name="unbangivereason" id="unbangivereason" rows="5" cols="80">&nbsp;</textarea></dd>
	</dl>

	<p class="submit-buttons">
		<input class="button1" type="submit" id="unbansubmit" name="unbansubmit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
		<input class="button2" type="reset" id="unbanreset" name="unbanreset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />
	</p>
	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

	</fieldset>

<?php } else { ?>


	<p><?php echo ((isset($this->_rootref['L_NO_BAN_CELL'])) ? $this->_rootref['L_NO_BAN_CELL'] : ((isset($user->lang['NO_BAN_CELL'])) ? $user->lang['NO_BAN_CELL'] : '{ NO_BAN_CELL }')); ?></p>
	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</fieldset>

<?php } ?>


</form>

<?php $this->_tpl_include('overall_footer.html'); ?>