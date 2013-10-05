<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<a name="maincontent"></a>

<h1><?php echo ((isset($this->_rootref['L_ACP_DISALLOW_USERNAMES'])) ? $this->_rootref['L_ACP_DISALLOW_USERNAMES'] : ((isset($user->lang['ACP_DISALLOW_USERNAMES'])) ? $user->lang['ACP_DISALLOW_USERNAMES'] : '{ ACP_DISALLOW_USERNAMES }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_ACP_DISALLOW_EXPLAIN'])) ? $this->_rootref['L_ACP_DISALLOW_EXPLAIN'] : ((isset($user->lang['ACP_DISALLOW_EXPLAIN'])) ? $user->lang['ACP_DISALLOW_EXPLAIN'] : '{ ACP_DISALLOW_EXPLAIN }')); ?></p>

<form id="acp_disallow" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_ADD_DISALLOW_TITLE'])) ? $this->_rootref['L_ADD_DISALLOW_TITLE'] : ((isset($user->lang['ADD_DISALLOW_TITLE'])) ? $user->lang['ADD_DISALLOW_TITLE'] : '{ ADD_DISALLOW_TITLE }')); ?></legend>
<dl>
	<dt><label for="user"><?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_ADD_DISALLOW_EXPLAIN'])) ? $this->_rootref['L_ADD_DISALLOW_EXPLAIN'] : ((isset($user->lang['ADD_DISALLOW_EXPLAIN'])) ? $user->lang['ADD_DISALLOW_EXPLAIN'] : '{ ADD_DISALLOW_EXPLAIN }')); ?></span></dt>
	<dd><input id="user" type="text" class="text medium" maxlength="255" name="disallowed_user" /></dd>
</dl>

<p class="quick">
	<input class="button1" type="submit" name="disallow" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />
</p>
</fieldset>

<h1><?php echo ((isset($this->_rootref['L_DELETE_DISALLOW_TITLE'])) ? $this->_rootref['L_DELETE_DISALLOW_TITLE'] : ((isset($user->lang['DELETE_DISALLOW_TITLE'])) ? $user->lang['DELETE_DISALLOW_TITLE'] : '{ DELETE_DISALLOW_TITLE }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_DELETE_DISALLOW_EXPLAIN'])) ? $this->_rootref['L_DELETE_DISALLOW_EXPLAIN'] : ((isset($user->lang['DELETE_DISALLOW_EXPLAIN'])) ? $user->lang['DELETE_DISALLOW_EXPLAIN'] : '{ DELETE_DISALLOW_EXPLAIN }')); ?></p>

<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_DELETE_DISALLOW_TITLE'])) ? $this->_rootref['L_DELETE_DISALLOW_TITLE'] : ((isset($user->lang['DELETE_DISALLOW_TITLE'])) ? $user->lang['DELETE_DISALLOW_TITLE'] : '{ DELETE_DISALLOW_TITLE }')); ?></legend>
<?php if ($this->_rootref['S_DISALLOWED_NAMES']) {  ?>

	<dl>
		<dt><label for="disallowed"><?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>:</label></dt>
		<dd><select name="disallowed_id" id="disallowed"><?php echo (isset($this->_rootref['S_DISALLOWED_NAMES'])) ? $this->_rootref['S_DISALLOWED_NAMES'] : ''; ?></select></dd>
	</dl>

	<p class="quick">
		<input class="button1" type="submit" name="allow" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />
	</p>
<?php } else { ?>

	<p><?php echo ((isset($this->_rootref['L_NO_DISALLOWED'])) ? $this->_rootref['L_NO_DISALLOWED'] : ((isset($user->lang['NO_DISALLOWED'])) ? $user->lang['NO_DISALLOWED'] : '{ NO_DISALLOWED }')); ?></p>
<?php } ?>

	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</fieldset>
</form>

<?php $this->_tpl_include('overall_footer.html'); ?>