<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>

<a name="maincontent"></a>

<?php if (! $this->_rootref['S_VERSION_CHECK']) {  ?>

<h1><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></h1>

<p><?php echo ((isset($this->_rootref['L_EXPLAIN'])) ? $this->_rootref['L_EXPLAIN'] : ((isset($user->lang['EXPLAIN'])) ? $user->lang['EXPLAIN'] : '{ EXPLAIN }')); ?></p>
<?php } if ($this->_rootref['S_VERSION_CHECK']) {  ?>


	<h1><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_CHECK'])) ? $this->_rootref['L_TRACKER_VERSION_CHECK'] : ((isset($user->lang['TRACKER_VERSION_CHECK'])) ? $user->lang['TRACKER_VERSION_CHECK'] : '{ TRACKER_VERSION_CHECK }')); ?></h1>

	<p><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_CHECK_EXPLAIN'])) ? $this->_rootref['L_TRACKER_VERSION_CHECK_EXPLAIN'] : ((isset($user->lang['TRACKER_VERSION_CHECK_EXPLAIN'])) ? $user->lang['TRACKER_VERSION_CHECK_EXPLAIN'] : '{ TRACKER_VERSION_CHECK_EXPLAIN }')); ?></p>

	<?php if ($this->_rootref['S_UP_TO_DATE']) {  ?>

		<div class="successbox">
			<p><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_UP_TO_DATE_ACP'])) ? $this->_rootref['L_TRACKER_VERSION_UP_TO_DATE_ACP'] : ((isset($user->lang['TRACKER_VERSION_UP_TO_DATE_ACP'])) ? $user->lang['TRACKER_VERSION_UP_TO_DATE_ACP'] : '{ TRACKER_VERSION_UP_TO_DATE_ACP }')); ?></p>
		</div>
	<?php } else { ?>

		<div class="errorbox">
			<p><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_NOT_UP_TO_DATE_ACP'])) ? $this->_rootref['L_TRACKER_VERSION_NOT_UP_TO_DATE_ACP'] : ((isset($user->lang['TRACKER_VERSION_NOT_UP_TO_DATE_ACP'])) ? $user->lang['TRACKER_VERSION_NOT_UP_TO_DATE_ACP'] : '{ TRACKER_VERSION_NOT_UP_TO_DATE_ACP }')); ?></p>
		</div>
	<?php } ?>


	<fieldset>
		<legend></legend>
	<dl>
		<dt><label><?php echo ((isset($this->_rootref['L_CURRENT_VERSION'])) ? $this->_rootref['L_CURRENT_VERSION'] : ((isset($user->lang['CURRENT_VERSION'])) ? $user->lang['CURRENT_VERSION'] : '{ CURRENT_VERSION }')); ?></label></dt>
		<dd><strong><?php echo (isset($this->_rootref['CURRENT_VERSION'])) ? $this->_rootref['CURRENT_VERSION'] : ''; ?></strong></dd>
	</dl>
	<dl>
		<dt><label><?php echo ((isset($this->_rootref['L_LATEST_VERSION'])) ? $this->_rootref['L_LATEST_VERSION'] : ((isset($user->lang['LATEST_VERSION'])) ? $user->lang['LATEST_VERSION'] : '{ LATEST_VERSION }')); ?></label></dt>
		<dd><strong><?php echo (isset($this->_rootref['LATEST_VERSION'])) ? $this->_rootref['LATEST_VERSION'] : ''; ?></strong></dd>
	</dl>
	</fieldset>

	<?php if (! $this->_rootref['S_UP_TO_DATE']) {  ?>

		<?php echo (isset($this->_rootref['UPDATE_INSTRUCTIONS'])) ? $this->_rootref['UPDATE_INSTRUCTIONS'] : ''; ?>

		<br /><br />
	<?php } } else if ($this->_rootref['S_IN_MANAGE_SETTINGS']) {  if ($this->_rootref['S_ERROR']) {  ?>

	<div class="errorbox">
		<h3><?php echo ((isset($this->_rootref['L_WARNING'])) ? $this->_rootref['L_WARNING'] : ((isset($user->lang['WARNING'])) ? $user->lang['WARNING'] : '{ WARNING }')); ?></h3>
		<p><?php echo (isset($this->_rootref['ERROR_MSG'])) ? $this->_rootref['ERROR_MSG'] : ''; ?></p>
	</div>
<?php } ?>


<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">
<?php $_options_count = (isset($this->_tpldata['options'])) ? sizeof($this->_tpldata['options']) : 0;if ($_options_count) {for ($_options_i = 0; $_options_i < $_options_count; ++$_options_i){$_options_val = &$this->_tpldata['options'][$_options_i]; if ($_options_val['S_LEGEND']) {  if (! $_options_val['S_FIRST_ROW']) {  ?>

			</fieldset>
		<?php } ?>

		<fieldset>
			<legend><?php echo $_options_val['LEGEND']; ?></legend>
	<?php } else { ?>


		<dl>
			<dt><label for="<?php echo $_options_val['KEY']; ?>"><?php echo $_options_val['TITLE']; ?>:</label><?php if ($_options_val['S_EXPLAIN']) {  ?><br /><span><?php echo $_options_val['TITLE_EXPLAIN']; ?></span><?php } ?></dt>
			<dd><?php echo $_options_val['CONTENT']; ?></dd>
		</dl>

	<?php } }} ?>


	<p class="submit-buttons">
		<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
		<input class="button2" type="reset" id="reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />
	</p>
	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

</fieldset>
</form>
<?php } else if ($this->_rootref['S_IN_MANAGE_ATTACHMENTS']) {  if ($this->_rootref['S_IN_MANAGE_ATTACHMENTS_DEFAULT']) {  if ($this->_rootref['S_ERROR']) {  ?>

			<div class="errorbox">
				<h3><?php echo ((isset($this->_rootref['L_WARNING'])) ? $this->_rootref['L_WARNING'] : ((isset($user->lang['WARNING'])) ? $user->lang['WARNING'] : '{ WARNING }')); ?></h3>
				<p><?php echo (isset($this->_rootref['ERROR_MSG'])) ? $this->_rootref['ERROR_MSG'] : ''; ?></p>
			</div>
		<?php } ?>

		<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">
			<fieldset class="tabulated">
				<h3><?php echo ((isset($this->_rootref['L_TRACKER_ORPHAN'])) ? $this->_rootref['L_TRACKER_ORPHAN'] : ((isset($user->lang['TRACKER_ORPHAN'])) ? $user->lang['TRACKER_ORPHAN'] : '{ TRACKER_ORPHAN }')); ?></h3>
				<table cellspacing="1">
				<thead>
				<tr>
					<th><?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?></th>
					<th><?php echo ((isset($this->_rootref['L_TRACKER_FILENAME'])) ? $this->_rootref['L_TRACKER_FILENAME'] : ((isset($user->lang['TRACKER_FILENAME'])) ? $user->lang['TRACKER_FILENAME'] : '{ TRACKER_FILENAME }')); ?></th>
					<th><?php echo ((isset($this->_rootref['L_TRACKER_FILESIZE'])) ? $this->_rootref['L_TRACKER_FILESIZE'] : ((isset($user->lang['TRACKER_FILESIZE'])) ? $user->lang['TRACKER_FILESIZE'] : '{ TRACKER_FILESIZE }')); ?></th>
					<th><?php echo ((isset($this->_rootref['L_FILEDATE'])) ? $this->_rootref['L_FILEDATE'] : ((isset($user->lang['FILEDATE'])) ? $user->lang['FILEDATE'] : '{ FILEDATE }')); ?></th>
					<th><?php echo ((isset($this->_rootref['L_MARK'])) ? $this->_rootref['L_MARK'] : ((isset($user->lang['MARK'])) ? $user->lang['MARK'] : '{ MARK }')); ?></th>
				</tr>
				</thead>
				<tbody>
					<?php $_orphan_count = (isset($this->_tpldata['orphan'])) ? sizeof($this->_tpldata['orphan']) : 0;if ($_orphan_count) {for ($_orphan_i = 0; $_orphan_i < $_orphan_count; ++$_orphan_i){$_orphan_val = &$this->_tpldata['orphan'][$_orphan_i]; if (!($_orphan_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="row1"><?php } else { ?><tr class="row2"><?php } ?>

						<td><?php echo $_orphan_val['POSTER_USERNAME']; ?></td>
						<td><a href="<?php echo $_orphan_val['U_DOWNLOAD_LINK']; ?>"><?php echo $_orphan_val['REAL_FILENAME']; ?></a></td>
						<td><?php echo $_orphan_val['FILESIZE']; ?> <?php echo $_orphan_val['SIZE_LANG']; ?></td>
						<td><?php echo $_orphan_val['FILETIME']; ?></td>
						<td style="text-align: center;"><input type="checkbox" class="radio" name="attach_ids[]" value="<?php echo $_orphan_val['ATTACH_ID']; ?>" /></td>
					</tr>
					<?php }} else { ?>

					<tr>
						<td></td>
					</tr>
					<?php } ?>

				</tbody>
				</table>
				<fieldset class="quick">
					<b class="small"><a href="#" onclick="marklist('acp_tracker', 'attach_ids', true); return false;"><?php echo ((isset($this->_rootref['L_MARK_ALL'])) ? $this->_rootref['L_MARK_ALL'] : ((isset($user->lang['MARK_ALL'])) ? $user->lang['MARK_ALL'] : '{ MARK_ALL }')); ?></a> :: <a href="#" onclick="marklist('acp_tracker', 'attach_ids', false); return false;"><?php echo ((isset($this->_rootref['L_UNMARK_ALL'])) ? $this->_rootref['L_UNMARK_ALL'] : ((isset($user->lang['UNMARK_ALL'])) ? $user->lang['UNMARK_ALL'] : '{ UNMARK_ALL }')); ?></a></b>
				</fieldset>

				<br />
				<h3><?php echo ((isset($this->_rootref['L_TRACKER_EXTRA_FILES'])) ? $this->_rootref['L_TRACKER_EXTRA_FILES'] : ((isset($user->lang['TRACKER_EXTRA_FILES'])) ? $user->lang['TRACKER_EXTRA_FILES'] : '{ TRACKER_EXTRA_FILES }')); ?></h3>
				<table cellspacing="1">
				<thead>
				<tr>
					<th><?php echo ((isset($this->_rootref['L_TRACKER_FILENAME'])) ? $this->_rootref['L_TRACKER_FILENAME'] : ((isset($user->lang['TRACKER_FILENAME'])) ? $user->lang['TRACKER_FILENAME'] : '{ TRACKER_FILENAME }')); ?></th>
					<th><?php echo ((isset($this->_rootref['L_MARK'])) ? $this->_rootref['L_MARK'] : ((isset($user->lang['MARK'])) ? $user->lang['MARK'] : '{ MARK }')); ?></th>
				</tr>
				</thead>
				<tbody>
					<?php $_extra_files_count = (isset($this->_tpldata['extra_files'])) ? sizeof($this->_tpldata['extra_files']) : 0;if ($_extra_files_count) {for ($_extra_files_i = 0; $_extra_files_i < $_extra_files_count; ++$_extra_files_i){$_extra_files_val = &$this->_tpldata['extra_files'][$_extra_files_i]; if (!($_extra_files_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="row1"><?php } else { ?><tr class="row2"><?php } ?>

						<td><a href="<?php echo $_extra_files_val['FULL_FILENAME']; ?>"><?php echo $_extra_files_val['FULL_FILENAME']; ?></a></td>
						<td style="text-align: center;"><input type="checkbox" class="radio" name="extra_files[]" value="<?php echo $_extra_files_val['FILENAME']; ?>" /></td>
					</tr>
					<?php }} else { ?>

					<tr>
						<td></td>
					</tr>
					<?php } ?>

				</tbody>
				</table>
				<fieldset class="quick">
					<b class="small"><a href="#" onclick="marklist('acp_tracker', 'extra_files', true); return false;"><?php echo ((isset($this->_rootref['L_MARK_ALL'])) ? $this->_rootref['L_MARK_ALL'] : ((isset($user->lang['MARK_ALL'])) ? $user->lang['MARK_ALL'] : '{ MARK_ALL }')); ?></a> :: <a href="#" onclick="marklist('acp_tracker', 'extra_files', false); return false;"><?php echo ((isset($this->_rootref['L_UNMARK_ALL'])) ? $this->_rootref['L_UNMARK_ALL'] : ((isset($user->lang['UNMARK_ALL'])) ? $user->lang['UNMARK_ALL'] : '{ UNMARK_ALL }')); ?></a></b>
				</fieldset>

				<p class="quick">
					<input class="button2" type="submit" id="submit" name="update" value="<?php echo ((isset($this->_rootref['L_ACP_TRACKER_DELETE_MARKED'])) ? $this->_rootref['L_ACP_TRACKER_DELETE_MARKED'] : ((isset($user->lang['ACP_TRACKER_DELETE_MARKED'])) ? $user->lang['ACP_TRACKER_DELETE_MARKED'] : '{ ACP_TRACKER_DELETE_MARKED }')); ?>" />
				</p>
			<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

			</fieldset>
		</form>
	<?php } } else if ($this->_rootref['S_IN_MANAGE_PROJECT']) {  if ($this->_rootref['S_IN_MANAGE_PROJECT_DEFAULT']) {  ?>

		<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">
		<fieldset class="tabulated">
		<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>
		<input name="project_name" type="text" class="text medium" id="project_name" value="" />&nbsp;&nbsp;<input class="button2" type="submit" id="project_cat_add" name="project_cat_add" value="<?php echo ((isset($this->_rootref['L_ACP_TRACKER_PROJECT_CAT_ADD'])) ? $this->_rootref['L_ACP_TRACKER_PROJECT_CAT_ADD'] : ((isset($user->lang['ACP_TRACKER_PROJECT_CAT_ADD'])) ? $user->lang['ACP_TRACKER_PROJECT_CAT_ADD'] : '{ ACP_TRACKER_PROJECT_CAT_ADD }')); ?>" /><br /><br />
		<?php $_cat_count = (isset($this->_tpldata['cat'])) ? sizeof($this->_tpldata['cat']) : 0;if ($_cat_count) {for ($_cat_i = 0; $_cat_i < $_cat_count; ++$_cat_i){$_cat_val = &$this->_tpldata['cat'][$_cat_i]; ?>

		<table cellspacing="1">
		<tbody>
			<tr>
				<th colspan="3"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_NAME_TITLE'])) ? $this->_rootref['L_TRACKER_PROJECT_NAME_TITLE'] : ((isset($user->lang['TRACKER_PROJECT_NAME_TITLE'])) ? $user->lang['TRACKER_PROJECT_NAME_TITLE'] : '{ TRACKER_PROJECT_NAME_TITLE }')); ?></th>
				<th><?php echo ((isset($this->_rootref['L_OPTIONS'])) ? $this->_rootref['L_OPTIONS'] : ((isset($user->lang['OPTIONS'])) ? $user->lang['OPTIONS'] : '{ OPTIONS }')); ?></th>
			</tr>
			<tr class="row1">
				<td colspan="3" ><strong><?php echo $_cat_val['PROJECT_CAT_NAME']; ?></strong></td>
				<td style="vertical-align: top; width: 100px; text-align: right; white-space: nowrap;">
					<a href="<?php echo $_cat_val['U_EDIT']; ?>"><?php echo (isset($this->_rootref['ICON_EDIT'])) ? $this->_rootref['ICON_EDIT'] : ''; ?></a>
					<a href="<?php echo $_cat_val['U_DELETE']; ?>"><?php echo (isset($this->_rootref['ICON_DELETE'])) ? $this->_rootref['ICON_DELETE'] : ''; ?></a>
				</td>
			</tr>
			<tr>
				<th><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_TYPE'])) ? $this->_rootref['L_TRACKER_PROJECT_TYPE'] : ((isset($user->lang['TRACKER_PROJECT_TYPE'])) ? $user->lang['TRACKER_PROJECT_TYPE'] : '{ TRACKER_PROJECT_TYPE }')); ?></th>
				<th><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_DESC'])) ? $this->_rootref['L_TRACKER_PROJECT_DESC'] : ((isset($user->lang['TRACKER_PROJECT_DESC'])) ? $user->lang['TRACKER_PROJECT_DESC'] : '{ TRACKER_PROJECT_DESC }')); ?></th>
				<th><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_ENABLED'])) ? $this->_rootref['L_TRACKER_PROJECT_ENABLED'] : ((isset($user->lang['TRACKER_PROJECT_ENABLED'])) ? $user->lang['TRACKER_PROJECT_ENABLED'] : '{ TRACKER_PROJECT_ENABLED }')); ?></th>
				<th><?php echo ((isset($this->_rootref['L_OPTIONS'])) ? $this->_rootref['L_OPTIONS'] : ((isset($user->lang['OPTIONS'])) ? $user->lang['OPTIONS'] : '{ OPTIONS }')); ?></th>
			</tr>
			<?php $_project_count = (isset($_cat_val['project'])) ? sizeof($_cat_val['project']) : 0;if ($_project_count) {for ($_project_i = 0; $_project_i < $_project_count; ++$_project_i){$_project_val = &$_cat_val['project'][$_project_i]; ?>

			<tr class="row2">
				<td><?php echo $_project_val['PROJECT_TYPE']; ?></td>
				<td><?php echo $_project_val['PROJECT_DESC']; ?></td>
				<td>
					<?php if ($_project_val['PROJECT_ENABLED']) {  ?>

						<a href="<?php echo $_project_val['U_DISABLE']; ?>"><?php echo ((isset($this->_rootref['L_DISABLE'])) ? $this->_rootref['L_DISABLE'] : ((isset($user->lang['DISABLE'])) ? $user->lang['DISABLE'] : '{ DISABLE }')); ?></a>
					<?php } else { ?>

						<a href="<?php echo $_project_val['U_ENABLE']; ?>"><?php echo ((isset($this->_rootref['L_ENABLE'])) ? $this->_rootref['L_ENABLE'] : ((isset($user->lang['ENABLE'])) ? $user->lang['ENABLE'] : '{ ENABLE }')); ?></a>
					<?php } ?>

				</td>
				<td style="vertical-align: top; width: 100px; text-align: right; white-space: nowrap;">
					<a href="<?php echo $_project_val['U_EDIT']; ?>"><?php echo (isset($this->_rootref['ICON_EDIT'])) ? $this->_rootref['ICON_EDIT'] : ''; ?></a>
					<a href="<?php echo $_project_val['U_DELETE']; ?>"><?php echo (isset($this->_rootref['ICON_DELETE'])) ? $this->_rootref['ICON_DELETE'] : ''; ?></a>
				</td>
			</tr>
			<?php }} ?>

		</tbody>
		</table>
		<?php if (! $_cat_val['S_LAST_ROW']) {  ?><br /><br /><?php } else { ?><br /><?php } }} ?>


		<p class="quick">
			<input class="button2" type="submit" id="project_add" name="project_add" value="<?php echo ((isset($this->_rootref['L_ACP_TRACKER_PROJECT_ADD'])) ? $this->_rootref['L_ACP_TRACKER_PROJECT_ADD'] : ((isset($user->lang['ACP_TRACKER_PROJECT_ADD'])) ? $user->lang['ACP_TRACKER_PROJECT_ADD'] : '{ ACP_TRACKER_PROJECT_ADD }')); ?>" />
		</p>

	<?php } else if ($this->_rootref['S_IN_MANAGE_PROJECT_CAT_EDIT']) {  ?>

		<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

		<fieldset>
			<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>
		<dl>
			<dt><label for="project_name"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_NAME_TITLE'])) ? $this->_rootref['L_TRACKER_PROJECT_NAME_TITLE'] : ((isset($user->lang['TRACKER_PROJECT_NAME_TITLE'])) ? $user->lang['TRACKER_PROJECT_NAME_TITLE'] : '{ TRACKER_PROJECT_NAME_TITLE }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_NAME_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_NAME_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_NAME_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_NAME_EXPLAIN'] : '{ TRACKER_PROJECT_NAME_EXPLAIN }')); ?></span></dt>
			<dd><input type="text" id="project_name" name="project_name" size="60" maxlength="255" value="<?php echo (isset($this->_rootref['PROJECT_CAT_NAME'])) ? $this->_rootref['PROJECT_CAT_NAME'] : ''; ?>" /></dd>
		</dl>
		</fieldset>

		<input type="hidden" name="c" value="<?php echo (isset($this->_rootref['PROJECT_CAT_ID'])) ? $this->_rootref['PROJECT_CAT_ID'] : ''; ?>" />
		<fieldset class="submit-buttons">
			<legend><?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?></legend>
			<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
			<input class="button2" type="reset" id="reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />
	<?php } else if ($this->_rootref['S_IN_MANAGE_PROJECT_ADD_EDIT']) {  ?>

		<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

		<fieldset>
			<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>
		<dl>
			<dt><label for="project_desc"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_DESC'])) ? $this->_rootref['L_TRACKER_PROJECT_DESC'] : ((isset($user->lang['TRACKER_PROJECT_DESC'])) ? $user->lang['TRACKER_PROJECT_DESC'] : '{ TRACKER_PROJECT_DESC }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_DESC_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_DESC_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_DESC_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_DESC_EXPLAIN'] : '{ TRACKER_PROJECT_DESC_EXPLAIN }')); ?></span></dt>
			<dd><input type="text" id="project_desc" name="project_desc" size="60" maxlength="255" value="<?php echo (isset($this->_rootref['PROJECT_DESC'])) ? $this->_rootref['PROJECT_DESC'] : ''; ?>" /></dd>
		</dl>
		<dl>
			<dt><label for="project_cat_id"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_CAT_ID'])) ? $this->_rootref['L_TRACKER_PROJECT_CAT_ID'] : ((isset($user->lang['TRACKER_PROJECT_CAT_ID'])) ? $user->lang['TRACKER_PROJECT_CAT_ID'] : '{ TRACKER_PROJECT_CAT_ID }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_CAT_ID_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_CAT_ID_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_CAT_ID_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_CAT_ID_EXPLAIN'] : '{ TRACKER_PROJECT_CAT_ID_EXPLAIN }')); ?></span></dt>
			<dd><select name="project_cat_id" id="project_cat_id"><?php echo (isset($this->_rootref['S_PROJECT_CAT_OPTIONS'])) ? $this->_rootref['S_PROJECT_CAT_OPTIONS'] : ''; ?></select></dd>
		</dl>
		<dl>
			<dt><label for="project_group"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_GROUP'])) ? $this->_rootref['L_TRACKER_PROJECT_GROUP'] : ((isset($user->lang['TRACKER_PROJECT_GROUP'])) ? $user->lang['TRACKER_PROJECT_GROUP'] : '{ TRACKER_PROJECT_GROUP }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_GROUP_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_GROUP_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_GROUP_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_GROUP_EXPLAIN'] : '{ TRACKER_PROJECT_GROUP_EXPLAIN }')); ?></span></dt>
			<dd><select name="project_group" id="project_group"><?php echo (isset($this->_rootref['S_GROUP_OPTIONS'])) ? $this->_rootref['S_GROUP_OPTIONS'] : ''; ?></select></dd>
		</dl>
		<dl>
			<dt><label for="project_type"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_TYPE'])) ? $this->_rootref['L_TRACKER_PROJECT_TYPE'] : ((isset($user->lang['TRACKER_PROJECT_TYPE'])) ? $user->lang['TRACKER_PROJECT_TYPE'] : '{ TRACKER_PROJECT_TYPE }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_TYPE_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_TYPE_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_TYPE_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_TYPE_EXPLAIN'] : '{ TRACKER_PROJECT_TYPE_EXPLAIN }')); ?></span></dt>
			<dd><select name="project_type" id="project_type"><?php echo (isset($this->_rootref['S_TYPE_OPTIONS'])) ? $this->_rootref['S_TYPE_OPTIONS'] : ''; ?></select></dd>
		</dl>
		<dl>
			<dt><label for="project_enabled"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_ENABLED'])) ? $this->_rootref['L_TRACKER_PROJECT_ENABLED'] : ((isset($user->lang['TRACKER_PROJECT_ENABLED'])) ? $user->lang['TRACKER_PROJECT_ENABLED'] : '{ TRACKER_PROJECT_ENABLED }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_ENABLED_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_ENABLED_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_ENABLED_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_ENABLED_EXPLAIN'] : '{ TRACKER_PROJECT_ENABLED_EXPLAIN }')); ?></span></dt>
			<dd><label><input type="radio" name="project_enabled" <?php if ($this->_rootref['PROJECT_ENABLED']) {  ?>checked="checked" <?php } ?>value="1" class="radio" /><?php echo ((isset($this->_rootref['L_YES'])) ? $this->_rootref['L_YES'] : ((isset($user->lang['YES'])) ? $user->lang['YES'] : '{ YES }')); ?></label><label><input type="radio" name="project_enabled" <?php if (! $this->_rootref['PROJECT_ENABLED']) {  ?>checked="checked" <?php } ?>value="0" class="radio" /><?php echo ((isset($this->_rootref['L_NO'])) ? $this->_rootref['L_NO'] : ((isset($user->lang['NO'])) ? $user->lang['NO'] : '{ NO }')); ?></label></dd>
		</dl>
		<dl>
			<dt><label for="project_security"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_SECURITY'])) ? $this->_rootref['L_TRACKER_PROJECT_SECURITY'] : ((isset($user->lang['TRACKER_PROJECT_SECURITY'])) ? $user->lang['TRACKER_PROJECT_SECURITY'] : '{ TRACKER_PROJECT_SECURITY }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_SECURITY_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_SECURITY_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_SECURITY_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_SECURITY_EXPLAIN'] : '{ TRACKER_PROJECT_SECURITY_EXPLAIN }')); ?></span></dt>
			<dd><label><input type="radio" name="project_security" <?php if ($this->_rootref['PROJECT_SECURITY']) {  ?>checked="checked" <?php } ?>value="1" class="radio" /><?php echo ((isset($this->_rootref['L_YES'])) ? $this->_rootref['L_YES'] : ((isset($user->lang['YES'])) ? $user->lang['YES'] : '{ YES }')); ?></label><label><input type="radio" name="project_security" <?php if (! $this->_rootref['PROJECT_SECURITY']) {  ?>checked="checked" <?php } ?>value="0" class="radio" /><?php echo ((isset($this->_rootref['L_NO'])) ? $this->_rootref['L_NO'] : ((isset($user->lang['NO'])) ? $user->lang['NO'] : '{ NO }')); ?></label></dd>
		</dl>
		<dl>
			<dt><label for="ticket_security"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_SECURITY'])) ? $this->_rootref['L_TRACKER_TICKET_SECURITY'] : ((isset($user->lang['TRACKER_TICKET_SECURITY'])) ? $user->lang['TRACKER_TICKET_SECURITY'] : '{ TRACKER_TICKET_SECURITY }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_SECURITY_EXPLAIN'])) ? $this->_rootref['L_TRACKER_TICKET_SECURITY_EXPLAIN'] : ((isset($user->lang['TRACKER_TICKET_SECURITY_EXPLAIN'])) ? $user->lang['TRACKER_TICKET_SECURITY_EXPLAIN'] : '{ TRACKER_TICKET_SECURITY_EXPLAIN }')); ?></span></dt>
			<dd><label><input type="radio" name="ticket_security" <?php if ($this->_rootref['TICKET_SECURITY']) {  ?>checked="checked" <?php } ?>value="1" class="radio" /><?php echo ((isset($this->_rootref['L_YES'])) ? $this->_rootref['L_YES'] : ((isset($user->lang['YES'])) ? $user->lang['YES'] : '{ YES }')); ?></label><label><input type="radio" name="ticket_security" <?php if (! $this->_rootref['TICKET_SECURITY']) {  ?>checked="checked" <?php } ?>value="0" class="radio" /><?php echo ((isset($this->_rootref['L_NO'])) ? $this->_rootref['L_NO'] : ((isset($user->lang['NO'])) ? $user->lang['NO'] : '{ NO }')); ?></label></dd>
		</dl>
		<dl>
			<dt><label for="show_php"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_SHOW_PHP'])) ? $this->_rootref['L_TRACKER_PROJECT_SHOW_PHP'] : ((isset($user->lang['TRACKER_PROJECT_SHOW_PHP'])) ? $user->lang['TRACKER_PROJECT_SHOW_PHP'] : '{ TRACKER_PROJECT_SHOW_PHP }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_SHOW_PHP_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_SHOW_PHP_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_SHOW_PHP_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_SHOW_PHP_EXPLAIN'] : '{ TRACKER_PROJECT_SHOW_PHP_EXPLAIN }')); ?></span></dt>
			<dd><label><input type="radio" name="show_php" <?php if ($this->_rootref['SHOW_PHP']) {  ?>checked="checked" <?php } ?>value="1" class="radio" /><?php echo ((isset($this->_rootref['L_YES'])) ? $this->_rootref['L_YES'] : ((isset($user->lang['YES'])) ? $user->lang['YES'] : '{ YES }')); ?></label><label><input type="radio" name="show_php" <?php if (! $this->_rootref['SHOW_PHP']) {  ?>checked="checked" <?php } ?>value="0" class="radio" /><?php echo ((isset($this->_rootref['L_NO'])) ? $this->_rootref['L_NO'] : ((isset($user->lang['NO'])) ? $user->lang['NO'] : '{ NO }')); ?></label></dd>
		</dl>
		<dl>
			<dt><label for="lang_php"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_LANG_PHP'])) ? $this->_rootref['L_TRACKER_PROJECT_LANG_PHP'] : ((isset($user->lang['TRACKER_PROJECT_LANG_PHP'])) ? $user->lang['TRACKER_PROJECT_LANG_PHP'] : '{ TRACKER_PROJECT_LANG_PHP }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_LANG_PHP_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_LANG_PHP_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_LANG_PHP_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_LANG_PHP_EXPLAIN'] : '{ TRACKER_PROJECT_LANG_PHP_EXPLAIN }')); ?></span></dt>
			<dd><input type="text" id="lang_php" name="lang_php" size="60" maxlength="255" value="<?php echo (isset($this->_rootref['LANG_PHP'])) ? $this->_rootref['LANG_PHP'] : ''; ?>" /></dd>
		</dl>
		<dl>
			<dt><label for="show_dbms"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_SHOW_DBMS'])) ? $this->_rootref['L_TRACKER_PROJECT_SHOW_DBMS'] : ((isset($user->lang['TRACKER_PROJECT_SHOW_DBMS'])) ? $user->lang['TRACKER_PROJECT_SHOW_DBMS'] : '{ TRACKER_PROJECT_SHOW_DBMS }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_SHOW_DBMS_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_SHOW_DBMS_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_SHOW_DBMS_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_SHOW_DBMS_EXPLAIN'] : '{ TRACKER_PROJECT_SHOW_DBMS_EXPLAIN }')); ?></span></dt>
			<dd><label><input type="radio" name="show_dbms" <?php if ($this->_rootref['SHOW_DBMS']) {  ?>checked="checked" <?php } ?>value="1" class="radio" /><?php echo ((isset($this->_rootref['L_YES'])) ? $this->_rootref['L_YES'] : ((isset($user->lang['YES'])) ? $user->lang['YES'] : '{ YES }')); ?></label><label><input type="radio" name="show_dbms" <?php if (! $this->_rootref['SHOW_DBMS']) {  ?>checked="checked" <?php } ?>value="0" class="radio" /><?php echo ((isset($this->_rootref['L_NO'])) ? $this->_rootref['L_NO'] : ((isset($user->lang['NO'])) ? $user->lang['NO'] : '{ NO }')); ?></label></dd>
		</dl>
		<dl>
			<dt><label for="lang_dbms"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_LANG_DBMS'])) ? $this->_rootref['L_TRACKER_PROJECT_LANG_DBMS'] : ((isset($user->lang['TRACKER_PROJECT_LANG_DBMS'])) ? $user->lang['TRACKER_PROJECT_LANG_DBMS'] : '{ TRACKER_PROJECT_LANG_DBMS }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_LANG_DBMS_EXPLAIN'])) ? $this->_rootref['L_TRACKER_PROJECT_LANG_DBMS_EXPLAIN'] : ((isset($user->lang['TRACKER_PROJECT_LANG_DBMS_EXPLAIN'])) ? $user->lang['TRACKER_PROJECT_LANG_DBMS_EXPLAIN'] : '{ TRACKER_PROJECT_LANG_DBMS_EXPLAIN }')); ?></span></dt>
			<dd><input type="text" id="lang_dbms" name="lang_dbms" size="60" maxlength="255" value="<?php echo (isset($this->_rootref['LANG_DBMS'])) ? $this->_rootref['LANG_DBMS'] : ''; ?>" /></dd>
		</dl>
		</fieldset>

		<input type="hidden" name="p" value="<?php echo (isset($this->_rootref['PROJECT_ID'])) ? $this->_rootref['PROJECT_ID'] : ''; ?>" />
		<fieldset class="submit-buttons">
			<legend><?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?></legend>
			<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
			<input class="button2" type="reset" id="reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />
	<?php } ?>

			<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

		</fieldset>
		</form>
<?php } else if ($this->_rootref['S_IN_MANAGE_COMPONENT']) {  if ($this->_rootref['S_IN_MANAGE_COMPONENT_DEFAULT']) {  ?>

		<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

		<fieldset>
			<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>
			<dl>
				<dt><label for="project_id"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_ID'])) ? $this->_rootref['L_TRACKER_PROJECT_ID'] : ((isset($user->lang['TRACKER_PROJECT_ID'])) ? $user->lang['TRACKER_PROJECT_ID'] : '{ TRACKER_PROJECT_ID }')); ?>:</label></dt>
				<dd><select name="project_id" id="project_id"><?php echo (isset($this->_rootref['S_PROJECT_OPTIONS'])) ? $this->_rootref['S_PROJECT_OPTIONS'] : ''; ?></select>&nbsp;&nbsp;<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" /></dd>
			</dl>			
	<?php } else if ($this->_rootref['S_IN_MANAGE_COMPONENT_VIEW']) {  ?>

	<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">
		<fieldset class="tabulated">
		<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>

		<table cellspacing="1">
		<thead>
		<tr>
			<th><?php echo ((isset($this->_rootref['L_TRACKER_COMPONENT_NAME'])) ? $this->_rootref['L_TRACKER_COMPONENT_NAME'] : ((isset($user->lang['TRACKER_COMPONENT_NAME'])) ? $user->lang['TRACKER_COMPONENT_NAME'] : '{ TRACKER_COMPONENT_NAME }')); ?></th>
			<th><?php echo ((isset($this->_rootref['L_OPTIONS'])) ? $this->_rootref['L_OPTIONS'] : ((isset($user->lang['OPTIONS'])) ? $user->lang['OPTIONS'] : '{ OPTIONS }')); ?></th>
		</tr>
		</thead>
		<tbody>
			<?php $_component_count = (isset($this->_tpldata['component'])) ? sizeof($this->_tpldata['component']) : 0;if ($_component_count) {for ($_component_i = 0; $_component_i < $_component_count; ++$_component_i){$_component_val = &$this->_tpldata['component'][$_component_i]; if (!($_component_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="row1"><?php } else { ?><tr class="row2"><?php } ?>

				<td><?php echo $_component_val['COMPONENT_NAME']; ?></td>
				<td style="vertical-align: top; width: 100px; text-align: right; white-space: nowrap;">
					<a href="<?php echo $_component_val['U_EDIT']; ?>"><?php echo (isset($this->_rootref['ICON_EDIT'])) ? $this->_rootref['ICON_EDIT'] : ''; ?></a>
					<a href="<?php echo $_component_val['U_DELETE']; ?>"><?php echo (isset($this->_rootref['ICON_DELETE'])) ? $this->_rootref['ICON_DELETE'] : ''; ?></a>
				</td>
			</tr>
			<?php }} ?>

		</tbody>
		</table>
		<input type="hidden" name="project_id" value="<?php echo (isset($this->_rootref['PROJECT_ID'])) ? $this->_rootref['PROJECT_ID'] : ''; ?>" />
		<p class="quick">
			<input class="button2" type="submit" id="submit" name="update" value="<?php echo ((isset($this->_rootref['L_ACP_TRACKER_COMPONENT_ADD'])) ? $this->_rootref['L_ACP_TRACKER_COMPONENT_ADD'] : ((isset($user->lang['ACP_TRACKER_COMPONENT_ADD'])) ? $user->lang['ACP_TRACKER_COMPONENT_ADD'] : '{ ACP_TRACKER_COMPONENT_ADD }')); ?>" />
		</p>
	<?php } else if ($this->_rootref['S_IN_MANAGE_COMPONENT_ADD_EDIT']) {  ?>

		<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

		<fieldset>
			<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>
		<dl>
			<dt><label for="component_name"><?php echo ((isset($this->_rootref['L_TRACKER_COMPONENT_NAME'])) ? $this->_rootref['L_TRACKER_COMPONENT_NAME'] : ((isset($user->lang['TRACKER_COMPONENT_NAME'])) ? $user->lang['TRACKER_COMPONENT_NAME'] : '{ TRACKER_COMPONENT_NAME }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_COMPONENT_NAME_EXPLAIN'])) ? $this->_rootref['L_TRACKER_COMPONENT_NAME_EXPLAIN'] : ((isset($user->lang['TRACKER_COMPONENT_NAME_EXPLAIN'])) ? $user->lang['TRACKER_COMPONENT_NAME_EXPLAIN'] : '{ TRACKER_COMPONENT_NAME_EXPLAIN }')); ?></span></dt>
			<dd><input type="text" id="component_name" name="component_name" size="60" maxlength="255" value="<?php echo (isset($this->_rootref['COMPONENT_NAME'])) ? $this->_rootref['COMPONENT_NAME'] : ''; ?>" /></dd>
		</dl>
		</fieldset>

		<input type="hidden" name="project_id" value="<?php echo (isset($this->_rootref['PROJECT_ID'])) ? $this->_rootref['PROJECT_ID'] : ''; ?>" />
		<input type="hidden" name="component_id" value="<?php echo (isset($this->_rootref['COMPONENT_ID'])) ? $this->_rootref['COMPONENT_ID'] : ''; ?>" />

		<fieldset class="submit-buttons">
			<legend><?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?></legend>
			<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
			<input class="button2" type="reset" id="reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />

	<?php } ?>

		<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

		</fieldset>
		</form>
<?php } else if ($this->_rootref['S_IN_MANAGE_VERSION']) {  if ($this->_rootref['S_IN_MANAGE_VERSION_DEFAULT']) {  ?>

		<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

		<fieldset>
			<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>
			<dl>
				<dt><label for="project_id"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_ID'])) ? $this->_rootref['L_TRACKER_PROJECT_ID'] : ((isset($user->lang['TRACKER_PROJECT_ID'])) ? $user->lang['TRACKER_PROJECT_ID'] : '{ TRACKER_PROJECT_ID }')); ?>:</label></dt>
				<dd><select name="project_id" id="project_id"><?php echo (isset($this->_rootref['S_PROJECT_OPTIONS'])) ? $this->_rootref['S_PROJECT_OPTIONS'] : ''; ?></select>&nbsp;&nbsp;<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" /></dd>
			</dl>
	<?php } else if ($this->_rootref['S_IN_MANAGE_VERSION_VIEW']) {  ?>

	<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">
		<fieldset class="tabulated">
		<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>

		<table cellspacing="1">
		<thead>
		<tr>
			<th><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_NAME'])) ? $this->_rootref['L_TRACKER_VERSION_NAME'] : ((isset($user->lang['TRACKER_VERSION_NAME'])) ? $user->lang['TRACKER_VERSION_NAME'] : '{ TRACKER_VERSION_NAME }')); ?></th>
			<th><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_ENABLED'])) ? $this->_rootref['L_TRACKER_VERSION_ENABLED'] : ((isset($user->lang['TRACKER_VERSION_ENABLED'])) ? $user->lang['TRACKER_VERSION_ENABLED'] : '{ TRACKER_VERSION_ENABLED }')); ?></th>
			<th><?php echo ((isset($this->_rootref['L_OPTIONS'])) ? $this->_rootref['L_OPTIONS'] : ((isset($user->lang['OPTIONS'])) ? $user->lang['OPTIONS'] : '{ OPTIONS }')); ?></th>
		</tr>
		</thead>
		<tbody>
			<?php $_version_count = (isset($this->_tpldata['version'])) ? sizeof($this->_tpldata['version']) : 0;if ($_version_count) {for ($_version_i = 0; $_version_i < $_version_count; ++$_version_i){$_version_val = &$this->_tpldata['version'][$_version_i]; if (!($_version_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="row1"><?php } else { ?><tr class="row2"><?php } ?>

				<td><?php echo $_version_val['VERSION_NAME']; ?></td>
				<td>
					<?php if ($_version_val['VERSION_ENABLED']) {  ?>

						<a href="<?php echo $_version_val['U_DISABLE']; ?>"><?php echo ((isset($this->_rootref['L_DISABLE'])) ? $this->_rootref['L_DISABLE'] : ((isset($user->lang['DISABLE'])) ? $user->lang['DISABLE'] : '{ DISABLE }')); ?></a>
					<?php } else { ?>

						<a href="<?php echo $_version_val['U_ENABLE']; ?>"><?php echo ((isset($this->_rootref['L_ENABLE'])) ? $this->_rootref['L_ENABLE'] : ((isset($user->lang['ENABLE'])) ? $user->lang['ENABLE'] : '{ ENABLE }')); ?></a>
					<?php } ?>

				</td>
				<td style="vertical-align: top; width: 100px; text-align: right; white-space: nowrap;">
					<a href="<?php echo $_version_val['U_EDIT']; ?>"><?php echo (isset($this->_rootref['ICON_EDIT'])) ? $this->_rootref['ICON_EDIT'] : ''; ?></a>
					<a href="<?php echo $_version_val['U_DELETE']; ?>"><?php echo (isset($this->_rootref['ICON_DELETE'])) ? $this->_rootref['ICON_DELETE'] : ''; ?></a>
				</td>
			</tr>
			<?php }} ?>

		</tbody>
		</table>
		<input type="hidden" name="project_id" value="<?php echo (isset($this->_rootref['PROJECT_ID'])) ? $this->_rootref['PROJECT_ID'] : ''; ?>" />
		<p class="quick">
			<input class="button2" type="submit" id="submit" name="update" value="<?php echo ((isset($this->_rootref['L_ACP_TRACKER_VERSION_ADD'])) ? $this->_rootref['L_ACP_TRACKER_VERSION_ADD'] : ((isset($user->lang['ACP_TRACKER_VERSION_ADD'])) ? $user->lang['ACP_TRACKER_VERSION_ADD'] : '{ ACP_TRACKER_VERSION_ADD }')); ?>" />
		</p>
	<?php } else if ($this->_rootref['S_IN_MANAGE_VERSION_ADD_EDIT']) {  ?>

		<form id="acp_tracker" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>">

		<fieldset>
			<legend><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></legend>
		<dl>
			<dt><label for="version_name"><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_NAME'])) ? $this->_rootref['L_TRACKER_VERSION_NAME'] : ((isset($user->lang['TRACKER_VERSION_NAME'])) ? $user->lang['TRACKER_VERSION_NAME'] : '{ TRACKER_VERSION_NAME }')); ?></label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_NAME_EXPLAIN'])) ? $this->_rootref['L_TRACKER_VERSION_NAME_EXPLAIN'] : ((isset($user->lang['TRACKER_VERSION_NAME_EXPLAIN'])) ? $user->lang['TRACKER_VERSION_NAME_EXPLAIN'] : '{ TRACKER_VERSION_NAME_EXPLAIN }')); ?></span></dt>
			<dd><input type="text" id="version_name" name="version_name" size="60" maxlength="255" value="<?php echo (isset($this->_rootref['VERSION_NAME'])) ? $this->_rootref['VERSION_NAME'] : ''; ?>" /></dd>
		</dl>
		</fieldset>

		<input type="hidden" name="project_id" value="<?php echo (isset($this->_rootref['PROJECT_ID'])) ? $this->_rootref['PROJECT_ID'] : ''; ?>" />
		<input type="hidden" name="version_id" value="<?php echo (isset($this->_rootref['VERSION_ID'])) ? $this->_rootref['VERSION_ID'] : ''; ?>" />

		<fieldset class="submit-buttons">
			<legend><?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?></legend>
			<input class="button1" type="submit" id="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" />&nbsp;
			<input class="button2" type="reset" id="reset" name="reset" value="<?php echo ((isset($this->_rootref['L_RESET'])) ? $this->_rootref['L_RESET'] : ((isset($user->lang['RESET'])) ? $user->lang['RESET'] : '{ RESET }')); ?>" />

	<?php } ?>

			<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

		</fieldset>
		</form>
<?php } $this->_tpl_include('overall_footer.html'); ?>