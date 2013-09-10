<?php if (!defined('IN_PHPBB')) exit; if ($this->_rootref['S_DISPLAY_DETAILS']) {  if ($this->_rootref['S_NEW_FILES']) {  ?>
	<fieldset>
		<legend><?php echo ((isset($this->_rootref['L_NEW_FILES'])) ? $this->_rootref['L_NEW_FILES'] : ((isset($user->lang['NEW_FILES'])) ? $user->lang['NEW_FILES'] : '{ NEW_FILES }')); ?></legend>

		<table cellspacing="1">
			<col class="row1" /><col class="row1" /><col class="row2" />
		<thead>
		<tr>
			<th><?php echo ((isset($this->_rootref['L_SOURCE'])) ? $this->_rootref['L_SOURCE'] : ((isset($user->lang['SOURCE'])) ? $user->lang['SOURCE'] : '{ SOURCE }')); ?></th>
			<th><?php echo ((isset($this->_rootref['L_TARGET'])) ? $this->_rootref['L_TARGET'] : ((isset($user->lang['TARGET'])) ? $user->lang['TARGET'] : '{ TARGET }')); ?></th>
			<th><?php echo ((isset($this->_rootref['L_STATUS'])) ? $this->_rootref['L_STATUS'] : ((isset($user->lang['STATUS'])) ? $user->lang['STATUS'] : '{ STATUS }')); ?></th>
		</tr>
		</thead>
		<tbody>
			<?php $_new_files_count = (isset($this->_tpldata['new_files'])) ? sizeof($this->_tpldata['new_files']) : 0;if ($_new_files_count) {for ($_new_files_i = 0; $_new_files_i < $_new_files_count; ++$_new_files_i){$_new_files_val = &$this->_tpldata['new_files'][$_new_files_i]; ?>
				<tr>
					<td><strong><?php echo $_new_files_val['SOURCE']; if ($_new_files_val['S_MISSING_FILE']) {  ?>&nbsp;&nbsp;&nbsp;<strong><font color="red">(<?php echo ((isset($this->_rootref['L_FILE_MISSING'])) ? $this->_rootref['L_FILE_MISSING'] : ((isset($user->lang['FILE_MISSING'])) ? $user->lang['FILE_MISSING'] : '{ FILE_MISSING }')); ?>)</font><?php } ?></strong></td>
					<td><?php echo $_new_files_val['TARGET']; ?></td>
					<?php if ($this->_rootref['S_INSTALL']) {  ?>
						<td><?php if ($_new_files_val['S_SUCCESS']) {  ?><font color="green"><?php echo ((isset($this->_rootref['L_SUCCESS'])) ? $this->_rootref['L_SUCCESS'] : ((isset($user->lang['SUCCESS'])) ? $user->lang['SUCCESS'] : '{ SUCCESS }')); ?></font><?php } else if ($_new_files_val['S_NO_COPY_ATTEMPT']) {  echo ((isset($this->_rootref['L_MANUAL_COPY'])) ? $this->_rootref['L_MANUAL_COPY'] : ((isset($user->lang['MANUAL_COPY'])) ? $user->lang['MANUAL_COPY'] : '{ MANUAL_COPY }')); } else { ?><font color="red"><?php echo ((isset($this->_rootref['L_ERROR'])) ? $this->_rootref['L_ERROR'] : ((isset($user->lang['ERROR'])) ? $user->lang['ERROR'] : '{ ERROR }')); ?></font><?php } ?></td>
					<?php } ?>
				</tr>
			<?php }} ?>
		</tbody>
		</table>
	</fieldset>
	<?php } if ($this->_rootref['S_REMOVING_FILES']) {  ?>
	<fieldset>
		<legend><?php echo ((isset($this->_rootref['L_REMOVING_FILES'])) ? $this->_rootref['L_REMOVING_FILES'] : ((isset($user->lang['REMOVING_FILES'])) ? $user->lang['REMOVING_FILES'] : '{ REMOVING_FILES }')); ?></legend>

		<table cellspacing="1">
			<col class="row1" width="50%" /><col class="row2" />
		<thead>
		<tr>
			<th><?php echo ((isset($this->_rootref['L_SOURCE'])) ? $this->_rootref['L_SOURCE'] : ((isset($user->lang['SOURCE'])) ? $user->lang['SOURCE'] : '{ SOURCE }')); ?></th>
		</tr>
		</thead>
		<tbody>
			<?php $_removing_files_count = (isset($this->_tpldata['removing_files'])) ? sizeof($this->_tpldata['removing_files']) : 0;if ($_removing_files_count) {for ($_removing_files_i = 0; $_removing_files_i < $_removing_files_count; ++$_removing_files_i){$_removing_files_val = &$this->_tpldata['removing_files'][$_removing_files_i]; ?>
				<tr>
					<td><strong><?php echo $_removing_files_val['FILENAME']; ?></strong><?php if ($_removing_files_val['S_MISSING_FILE']) {  ?>&nbsp;&nbsp;&nbsp;<strong><font color="red">(<?php echo ((isset($this->_rootref['L_FILE_MISSING'])) ? $this->_rootref['L_FILE_MISSING'] : ((isset($user->lang['FILE_MISSING'])) ? $user->lang['FILE_MISSING'] : '{ FILE_MISSING }')); ?>)</font></strong>&nbsp;<?php } ?></td>
				</tr>
			<?php }} ?>
		</tbody>
		</table>
	</fieldset>
	<?php } if ($this->_rootref['S_SQL']) {  ?>
	<fieldset>
		<legend><?php echo ((isset($this->_rootref['L_SQL_QUERIES'])) ? $this->_rootref['L_SQL_QUERIES'] : ((isset($user->lang['SQL_QUERIES'])) ? $user->lang['SQL_QUERIES'] : '{ SQL_QUERIES }')); ?></legend>
		<?php $_sql_queries_count = (isset($this->_tpldata['sql_queries'])) ? sizeof($this->_tpldata['sql_queries']) : 0;if ($_sql_queries_count) {for ($_sql_queries_i = 0; $_sql_queries_i < $_sql_queries_count; ++$_sql_queries_i){$_sql_queries_val = &$this->_tpldata['sql_queries'][$_sql_queries_i]; if ($this->_rootref['S_PRE_UNINSTALL']) {  ?>
		<table cellspacing="1">
			<col class="row1" width="50%" /><col class="row2" />
		<thead>
		<tr>
			<th><?php echo ((isset($this->_rootref['L_ORIGINAL'])) ? $this->_rootref['L_ORIGINAL'] : ((isset($user->lang['ORIGINAL'])) ? $user->lang['ORIGINAL'] : '{ ORIGINAL }')); ?></th>
			<th><?php echo ((isset($this->_rootref['L_REVERSE'])) ? $this->_rootref['L_REVERSE'] : ((isset($user->lang['REVERSE'])) ? $user->lang['REVERSE'] : '{ REVERSE }')); ?></th>
		</tr>
		</thead>
		<tbody>
			<?php $_sql_queries_count = (isset($_sql_queries_val['sql_queries'])) ? sizeof($_sql_queries_val['sql_queries']) : 0;if ($_sql_queries_count) {for ($_sql_queries_i = 0; $_sql_queries_i < $_sql_queries_count; ++$_sql_queries_i){$_sql_queries_val = &$_sql_queries_val['sql_queries'][$_sql_queries_i]; ?>
				<tr>
					<td><strong><?php echo $_sql_queries_val['ORIGINAL_QUERY']; ?></strong></td>
					<td><strong><?php if ($_sql_queries_val['S_UNKNOWN_REVERSE']) {  ?>&nbsp;&nbsp;&nbsp;<strong><font color="red"><?php echo ((isset($this->_rootref['L_UNKNOWN_QUERY_REVERSE'])) ? $this->_rootref['L_UNKNOWN_QUERY_REVERSE'] : ((isset($user->lang['UNKNOWN_QUERY_REVERSE'])) ? $user->lang['UNKNOWN_QUERY_REVERSE'] : '{ UNKNOWN_QUERY_REVERSE }')); ?>!</font></strong>&nbsp;<?php } else { echo $_sql_queries_val['REVERSE_QUERY']; } ?></strong></td>
				</tr>
			<?php }} ?>
		</tbody>
		</table>
			<?php } else { ?>
			<fieldset>
				<strong><?php if ($this->_rootref['S_CHANGE_FILES']) {  if ($_sql_queries_val['S_SUCCESS']) {  ?><font color="green"><?php echo ((isset($this->_rootref['L_SUCCESS'])) ? $this->_rootref['L_SUCCESS'] : ((isset($user->lang['SUCCESS'])) ? $user->lang['SUCCESS'] : '{ SUCCESS }')); ?>:</font><br /><?php } else { ?><font color="red"><?php echo ((isset($this->_rootref['L_ERROR'])) ? $this->_rootref['L_ERROR'] : ((isset($user->lang['ERROR'])) ? $user->lang['ERROR'] : '{ ERROR }')); ?>:</font><?php echo $_sql_queries_val['ERROR_MSG']; ?><br /><?php } } echo $_sql_queries_val['QUERY']; ?></strong>
			</fieldset>
			<?php } }} ?>
	</fieldset>
	<?php } if ($this->_rootref['S_EDITS']) {  ?>
	<h2><?php echo ((isset($this->_rootref['L_FILE_EDITS'])) ? $this->_rootref['L_FILE_EDITS'] : ((isset($user->lang['FILE_EDITS'])) ? $user->lang['FILE_EDITS'] : '{ FILE_EDITS }')); ?></h2>
	<?php $_edit_files_count = (isset($this->_tpldata['edit_files'])) ? sizeof($this->_tpldata['edit_files']) : 0;if ($_edit_files_count) {for ($_edit_files_i = 0; $_edit_files_i < $_edit_files_count; ++$_edit_files_i){$_edit_files_val = &$this->_tpldata['edit_files'][$_edit_files_i]; ?>
	<fieldset>
		<legend><?php echo $_edit_files_val['FILENAME']; ?></legend>
		<?php if ($_edit_files_val['S_MISSING_FILE']) {  ?>
		<strong><font color="red"><?php echo ((isset($this->_rootref['L_FILE_MISSING'])) ? $this->_rootref['L_FILE_MISSING'] : ((isset($user->lang['FILE_MISSING'])) ? $user->lang['FILE_MISSING'] : '{ FILE_MISSING }')); ?></font></strong>
		<?php } else if ($_edit_files_val['INHERIT_MSG']) {  ?>
		<strong><?php echo $_edit_files_val['INHERIT_MSG']; ?></strong>
		<?php } $_finds_count = (isset($_edit_files_val['finds'])) ? sizeof($_edit_files_val['finds']) : 0;if ($_finds_count) {for ($_finds_i = 0; $_finds_i < $_finds_count; ++$_finds_i){$_finds_val = &$_edit_files_val['finds'][$_finds_i]; ?>
		<fieldset>
			<h4 style="margin: 0; padding:0;"><?php echo ((isset($this->_rootref['L_FIND'])) ? $this->_rootref['L_FIND'] : ((isset($user->lang['FIND'])) ? $user->lang['FIND'] : '{ FIND }')); ?></h4>
			<?php if ($_finds_val['COMMENT']) {  ?><p><strong><?php echo ((isset($this->_rootref['L_COMMENT'])) ? $this->_rootref['L_COMMENT'] : ((isset($user->lang['COMMENT'])) ? $user->lang['COMMENT'] : '{ COMMENT }')); ?></strong>: <?php echo $_finds_val['COMMENT']; ?></p><?php } ?>
			<pre style="overflow-x: scroll;"><?php echo $_finds_val['FIND_STRING']; ?></pre>
			<?php if ($_finds_val['S_MISSING_FIND']) {  ?>
			<strong><font color="red"><?php echo ((isset($this->_rootref['L_FIND_MISSING'])) ? $this->_rootref['L_FIND_MISSING'] : ((isset($user->lang['FIND_MISSING'])) ? $user->lang['FIND_MISSING'] : '{ FIND_MISSING }')); ?></font></strong>
			<?php } $_actions_count = (isset($_finds_val['actions'])) ? sizeof($_finds_val['actions']) : 0;if ($_actions_count) {for ($_actions_i = 0; $_actions_i < $_actions_count; ++$_actions_i){$_actions_val = &$_finds_val['actions'][$_actions_i]; ?>
				<div>
					<hr style="padding-top: 10px; margin-top: 10px;" />
					<h4 style="margin: 0; padding:0;"><?php echo $_actions_val['NAME']; ?></h4>
					<pre style="overflow-x: scroll;"><?php echo $_actions_val['COMMAND']; ?></pre>
					<?php $_inline_count = (isset($_actions_val['inline'])) ? sizeof($_actions_val['inline']) : 0;if ($_inline_count) {for ($_inline_i = 0; $_inline_i < $_inline_count; ++$_inline_i){$_inline_val = &$_actions_val['inline'][$_inline_i]; ?>
						<hr style="padding-top: 10px; margin-top: 10px;" />
						<h4 style="margin: 0; padding:0;"><?php echo $_inline_val['NAME']; ?></h4>
						<?php if ($_inline_val['COMMENT']) {  ?><p><strong><?php echo ((isset($this->_rootref['L_COMMENT'])) ? $this->_rootref['L_COMMENT'] : ((isset($user->lang['COMMENT'])) ? $user->lang['COMMENT'] : '{ COMMENT }')); ?></strong>:<p><?php echo $_inline_val['COMMENT']; ?></p><?php } ?>
						<pre style="overflow-x: scroll;"><?php echo $_inline_val['COMMAND']; ?></pre>
						<?php if (! $_inline_val['S_SUCCESS'] && $this->_rootref['S_CHANGE_FILES']) {  ?><span><?php echo ((isset($this->_rootref['L_INLINE_FIND_MISSING'])) ? $this->_rootref['L_INLINE_FIND_MISSING'] : ((isset($user->lang['INLINE_FIND_MISSING'])) ? $user->lang['INLINE_FIND_MISSING'] : '{ INLINE_FIND_MISSING }')); ?></span><?php } }} if ($this->_rootref['S_CHANGE_FILES']) {  if ($_actions_val['S_SUCCESS']) {  ?><font color="green"><?php echo ((isset($this->_rootref['L_SUCCESS'])) ? $this->_rootref['L_SUCCESS'] : ((isset($user->lang['SUCCESS'])) ? $user->lang['SUCCESS'] : '{ SUCCESS }')); ?></font><?php } else { ?><font color="red"><?php echo ((isset($this->_rootref['L_FIND_MISSING'])) ? $this->_rootref['L_FIND_MISSING'] : ((isset($user->lang['FIND_MISSING'])) ? $user->lang['FIND_MISSING'] : '{ FIND_MISSING }')); ?></font><?php } } ?>
				</div>
			<?php }} ?>
			</fieldset>
		<?php }} ?>
		</fieldset>
		<?php }} } } ?>