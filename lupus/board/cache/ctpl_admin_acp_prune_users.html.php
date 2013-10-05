<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<a name="maincontent"></a>

<h1><?php echo ((isset($this->_rootref['L_ACP_PRUNE_USERS'])) ? $this->_rootref['L_ACP_PRUNE_USERS'] : ((isset($user->lang['ACP_PRUNE_USERS'])) ? $user->lang['ACP_PRUNE_USERS'] : '{ ACP_PRUNE_USERS }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_ACP_PRUNE_USERS_EXPLAIN'])) ? $this->_rootref['L_ACP_PRUNE_USERS_EXPLAIN'] : ((isset($user->lang['ACP_PRUNE_USERS_EXPLAIN'])) ? $user->lang['ACP_PRUNE_USERS_EXPLAIN'] : '{ ACP_PRUNE_USERS_EXPLAIN }')); ?></p>

<form id="acp_prune" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">
	
<fieldset>
	<legend><?php echo ((isset($this->_rootref['L_ACP_PRUNE_USERS'])) ? $this->_rootref['L_ACP_PRUNE_USERS'] : ((isset($user->lang['ACP_PRUNE_USERS'])) ? $user->lang['ACP_PRUNE_USERS'] : '{ ACP_PRUNE_USERS }')); ?></legend>
<dl>
	<dt><label for="username"><?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>:</label></dt>
	<dd><input type="text" id="username" name="username" /></dd>
</dl>
<dl>
	<dt><label for="email"><?php echo ((isset($this->_rootref['L_EMAIL'])) ? $this->_rootref['L_EMAIL'] : ((isset($user->lang['EMAIL'])) ? $user->lang['EMAIL'] : '{ EMAIL }')); ?>:</label></dt>
	<dd><input type="text" id="email" name="email" /></dd>
</dl>
<dl>
	<dt><label for="joined"><?php echo ((isset($this->_rootref['L_JOINED'])) ? $this->_rootref['L_JOINED'] : ((isset($user->lang['JOINED'])) ? $user->lang['JOINED'] : '{ JOINED }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_JOINED_EXPLAIN'])) ? $this->_rootref['L_JOINED_EXPLAIN'] : ((isset($user->lang['JOINED_EXPLAIN'])) ? $user->lang['JOINED_EXPLAIN'] : '{ JOINED_EXPLAIN }')); ?></span></dt>
	<dd><select name="joined_select"><?php echo (isset($this->_rootref['S_JOINED_OPTIONS'])) ? $this->_rootref['S_JOINED_OPTIONS'] : ''; ?></select> <input type="text" id="joined" name="joined" /></dd>
</dl>
<dl>
	<dt><label for="active"><?php echo ((isset($this->_rootref['L_LAST_ACTIVE'])) ? $this->_rootref['L_LAST_ACTIVE'] : ((isset($user->lang['LAST_ACTIVE'])) ? $user->lang['LAST_ACTIVE'] : '{ LAST_ACTIVE }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_LAST_ACTIVE_EXPLAIN'])) ? $this->_rootref['L_LAST_ACTIVE_EXPLAIN'] : ((isset($user->lang['LAST_ACTIVE_EXPLAIN'])) ? $user->lang['LAST_ACTIVE_EXPLAIN'] : '{ LAST_ACTIVE_EXPLAIN }')); ?></span></dt>
	<dd><select name="active_select"><?php echo (isset($this->_rootref['S_ACTIVE_OPTIONS'])) ? $this->_rootref['S_ACTIVE_OPTIONS'] : ''; ?></select> <input type="text" id="active" name="active" /></dd>
</dl>
<dl>
	<dt><label for="count"><?php echo ((isset($this->_rootref['L_POSTS'])) ? $this->_rootref['L_POSTS'] : ((isset($user->lang['POSTS'])) ? $user->lang['POSTS'] : '{ POSTS }')); ?>:</label></dt>
	<dd><select name="count_select"><?php echo (isset($this->_rootref['S_COUNT_OPTIONS'])) ? $this->_rootref['S_COUNT_OPTIONS'] : ''; ?></select> <input type="text" id="count" name="count" /></dd>
</dl>
<dl>
	<dt><label for="users"><?php echo ((isset($this->_rootref['L_ACP_PRUNE_USERS'])) ? $this->_rootref['L_ACP_PRUNE_USERS'] : ((isset($user->lang['ACP_PRUNE_USERS'])) ? $user->lang['ACP_PRUNE_USERS'] : '{ ACP_PRUNE_USERS }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_SELECT_USERS_EXPLAIN'])) ? $this->_rootref['L_SELECT_USERS_EXPLAIN'] : ((isset($user->lang['SELECT_USERS_EXPLAIN'])) ? $user->lang['SELECT_USERS_EXPLAIN'] : '{ SELECT_USERS_EXPLAIN }')); ?></span></dt>
	<dd><textarea id="users" name="users" cols="40" rows="5"></textarea></dd>
	<dd>[ <a href="<?php echo (isset($this->_rootref['U_FIND_USERNAME'])) ? $this->_rootref['U_FIND_USERNAME'] : ''; ?>" onclick="find_username(this.href); return false;"><?php echo ((isset($this->_rootref['L_FIND_USERNAME'])) ? $this->_rootref['L_FIND_USERNAME'] : ((isset($user->lang['FIND_USERNAME'])) ? $user->lang['FIND_USERNAME'] : '{ FIND_USERNAME }')); ?></a> ]</dd>
</dl>
<dl>
	<dt><label for="deleteposts"><?php echo ((isset($this->_rootref['L_DELETE_USER_POSTS'])) ? $this->_rootref['L_DELETE_USER_POSTS'] : ((isset($user->lang['DELETE_USER_POSTS'])) ? $user->lang['DELETE_USER_POSTS'] : '{ DELETE_USER_POSTS }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_DELETE_USER_POSTS_EXPLAIN'])) ? $this->_rootref['L_DELETE_USER_POSTS_EXPLAIN'] : ((isset($user->lang['DELETE_USER_POSTS_EXPLAIN'])) ? $user->lang['DELETE_USER_POSTS_EXPLAIN'] : '{ DELETE_USER_POSTS_EXPLAIN }')); ?></span></dt>
	<dd><label><input type="radio" class="radio" name="deleteposts" value="1" /> <?php echo ((isset($this->_rootref['L_YES'])) ? $this->_rootref['L_YES'] : ((isset($user->lang['YES'])) ? $user->lang['YES'] : '{ YES }')); ?></label>
		<label><input type="radio" class="radio" id="deleteposts" name="deleteposts" value="0" checked="checked" /> <?php echo ((isset($this->_rootref['L_NO'])) ? $this->_rootref['L_NO'] : ((isset($user->lang['NO'])) ? $user->lang['NO'] : '{ NO }')); ?></label></dd>
</dl>
<dl>
	<dt><label for="deactivate"><?php echo ((isset($this->_rootref['L_DEACTIVATE_DELETE'])) ? $this->_rootref['L_DEACTIVATE_DELETE'] : ((isset($user->lang['DEACTIVATE_DELETE'])) ? $user->lang['DEACTIVATE_DELETE'] : '{ DEACTIVATE_DELETE }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_DEACTIVATE_DELETE_EXPLAIN'])) ? $this->_rootref['L_DEACTIVATE_DELETE_EXPLAIN'] : ((isset($user->lang['DEACTIVATE_DELETE_EXPLAIN'])) ? $user->lang['DEACTIVATE_DELETE_EXPLAIN'] : '{ DEACTIVATE_DELETE_EXPLAIN }')); ?></span></dt>
	<dd><label><input type="radio" class="radio" name="action" value="delete" /> <?php echo ((isset($this->_rootref['L_DELETE_USERS'])) ? $this->_rootref['L_DELETE_USERS'] : ((isset($user->lang['DELETE_USERS'])) ? $user->lang['DELETE_USERS'] : '{ DELETE_USERS }')); ?></label>
		<label><input type="radio" class="radio" id="deactivate" name="action" value="deactivate" checked="checked" /> <?php echo ((isset($this->_rootref['L_DEACTIVATE'])) ? $this->_rootref['L_DEACTIVATE'] : ((isset($user->lang['DEACTIVATE'])) ? $user->lang['DEACTIVATE'] : '{ DEACTIVATE }')); ?></label></dd>
</dl>

<p class="submit-buttons">
	<input type="hidden" name="prune" value="1" />

	<input class="button1" type="submit" id="update" name="update" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
	<input class="button2" type="reset" id="reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />
	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</p>
</fieldset>
</form>

<?php $this->_tpl_include('overall_footer.html'); ?>