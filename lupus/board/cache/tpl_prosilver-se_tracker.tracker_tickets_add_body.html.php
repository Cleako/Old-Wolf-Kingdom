<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>

<h2><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></h2>
<p><?php echo ((isset($this->_rootref['L_TITLE_EXPLAIN'])) ? $this->_rootref['L_TITLE_EXPLAIN'] : ((isset($user->lang['TITLE_EXPLAIN'])) ? $user->lang['TITLE_EXPLAIN'] : '{ TITLE_EXPLAIN }')); ?></p>

<?php if ($this->_rootref['S_PREVIEW']) {  ?>

<h3><?php echo ((isset($this->_rootref['L_TRACKER_PREVIEW'])) ? $this->_rootref['L_TRACKER_PREVIEW'] : ((isset($user->lang['TRACKER_PREVIEW'])) ? $user->lang['TRACKER_PREVIEW'] : '{ TRACKER_PREVIEW }')); ?></h3>
<div class="panel bg2">
	<div class="inner"><span class="corners-top"><span></span></span>
		<div class="postbody" style="width: auto; float: none; margin: 0; height: auto;">
			<div class="content"><?php echo (isset($this->_rootref['TICKET_PREVIEW'])) ? $this->_rootref['TICKET_PREVIEW'] : ''; ?></div>
			<?php if (sizeof($this->_tpldata['attachment'])) {  ?>

			<dl class="attachbox">
				<dt><?php echo ((isset($this->_rootref['L_ATTACHMENTS'])) ? $this->_rootref['L_ATTACHMENTS'] : ((isset($user->lang['ATTACHMENTS'])) ? $user->lang['ATTACHMENTS'] : '{ ATTACHMENTS }')); ?></dt>
				<?php $_attachment_count = (isset($this->_tpldata['attachment'])) ? sizeof($this->_tpldata['attachment']) : 0;if ($_attachment_count) {for ($_attachment_i = 0; $_attachment_i < $_attachment_count; ++$_attachment_i){$_attachment_val = &$this->_tpldata['attachment'][$_attachment_i]; ?>

				<dd><?php echo $_attachment_val['DISPLAY_ATTACHMENT']; ?></dd>
				<?php }} ?>

			</dl>
			<?php } ?>

		</div>
	<span class="corners-bottom"><span></span></span></div>
</div>
<?php } ?>

<h3><?php echo ((isset($this->_rootref['L_TRACKER_SUBMIT_A_TICKET'])) ? $this->_rootref['L_TRACKER_SUBMIT_A_TICKET'] : ((isset($user->lang['TRACKER_SUBMIT_A_TICKET'])) ? $user->lang['TRACKER_SUBMIT_A_TICKET'] : '{ TRACKER_SUBMIT_A_TICKET }')); ?></h3>
<form id="postform" method="post" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>"<?php echo (isset($this->_rootref['S_FORM_ENCTYPE'])) ? $this->_rootref['S_FORM_ENCTYPE'] : ''; ?>>
	<div class="panel bg2">
		<div class="inner"><span class="corners-top"><span></span></span>
		<fieldset>
			<h3><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_INFO'])) ? $this->_rootref['L_TRACKER_PROJECT_INFO'] : ((isset($user->lang['TRACKER_PROJECT_INFO'])) ? $user->lang['TRACKER_PROJECT_INFO'] : '{ TRACKER_PROJECT_INFO }')); ?></h3>
			<?php if ($this->_rootref['ERROR']) {  ?><p class="error"><?php echo (isset($this->_rootref['ERROR'])) ? $this->_rootref['ERROR'] : ''; ?></p><?php } ?>

			<dl>
				<dt><strong><?php echo ((isset($this->_rootref['L_TRACKER'])) ? $this->_rootref['L_TRACKER'] : ((isset($user->lang['TRACKER'])) ? $user->lang['TRACKER'] : '{ TRACKER }')); ?>:</strong></dt>
				<dd><?php echo (isset($this->_rootref['PROJECT_TYPE'])) ? $this->_rootref['PROJECT_TYPE'] : ''; ?></dd>
			</dl>
			<dl>
				<dt><strong><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_NAME'])) ? $this->_rootref['L_TRACKER_PROJECT_NAME'] : ((isset($user->lang['TRACKER_PROJECT_NAME'])) ? $user->lang['TRACKER_PROJECT_NAME'] : '{ TRACKER_PROJECT_NAME }')); ?>:</strong></dt>
				<dd><?php echo (isset($this->_rootref['PROJECT_NAME'])) ? $this->_rootref['PROJECT_NAME'] : ''; ?></dd>
			</dl>
			<?php if ($this->_rootref['S_COMPONENT_OPTIONS'] && $this->_rootref['S_TICKET_COMPONENT']) {  ?>

			<dl>
				<dt><label for="component_id"><?php echo ((isset($this->_rootref['L_TRACKER_COMPONENT'])) ? $this->_rootref['L_TRACKER_COMPONENT'] : ((isset($user->lang['TRACKER_COMPONENT'])) ? $user->lang['TRACKER_COMPONENT'] : '{ TRACKER_COMPONENT }')); ?>:</label></dt>
				<dd>
					<select id="component_id" name="component_id">
						<?php echo (isset($this->_rootref['S_COMPONENT_OPTIONS'])) ? $this->_rootref['S_COMPONENT_OPTIONS'] : ''; ?>

					</select>
				</dd>
			</dl>
			<?php } if ($this->_rootref['S_VERSION_OPTIONS'] && $this->_rootref['S_TICKET_VERSION']) {  ?>

			<dl>
				<dt><label for="version_id"><?php echo ((isset($this->_rootref['L_TRACKER_VERSION'])) ? $this->_rootref['L_TRACKER_VERSION'] : ((isset($user->lang['TRACKER_VERSION'])) ? $user->lang['TRACKER_VERSION'] : '{ TRACKER_VERSION }')); ?>:</label></dt>
				<dd>
					<select id="version_id" name="version_id">
						<?php echo (isset($this->_rootref['S_VERSION_OPTIONS'])) ? $this->_rootref['S_VERSION_OPTIONS'] : ''; ?>

					</select>
				</dd>
			</dl>
			<?php } ?>

		</fieldset>
		<span class="corners-bottom"><span></span></span></div>
	</div>

	<?php if ($this->_rootref['S_TICKET_ENVIRONMENT'] && ( $this->_rootref['S_SHOW_PHP'] || $this->_rootref['S_SHOW_DBMS'] )) {  ?>

	<div class="panel bg2">
		<div class="inner"><span class="corners-top"><span></span></span>
		<fieldset>
			<h3><?php echo ((isset($this->_rootref['L_TRACKER_ENVIRONMENT'])) ? $this->_rootref['L_TRACKER_ENVIRONMENT'] : ((isset($user->lang['TRACKER_ENVIRONMENT'])) ? $user->lang['TRACKER_ENVIRONMENT'] : '{ TRACKER_ENVIRONMENT }')); ?></h3>
			<p><?php echo ((isset($this->_rootref['L_TRACKER_ENVIRONMENT_EXPLAIN'])) ? $this->_rootref['L_TRACKER_ENVIRONMENT_EXPLAIN'] : ((isset($user->lang['TRACKER_ENVIRONMENT_EXPLAIN'])) ? $user->lang['TRACKER_ENVIRONMENT_EXPLAIN'] : '{ TRACKER_ENVIRONMENT_EXPLAIN }')); ?></p>
			<?php if ($this->_rootref['S_SHOW_PHP']) {  ?>

			<dl>
				<dt><label for="ticket_php"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_PHP'])) ? $this->_rootref['L_TRACKER_TICKET_PHP'] : ((isset($user->lang['TRACKER_TICKET_PHP'])) ? $user->lang['TRACKER_TICKET_PHP'] : '{ TRACKER_TICKET_PHP }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_PHP_EXPLAIN'])) ? $this->_rootref['L_TRACKER_TICKET_PHP_EXPLAIN'] : ((isset($user->lang['TRACKER_TICKET_PHP_EXPLAIN'])) ? $user->lang['TRACKER_TICKET_PHP_EXPLAIN'] : '{ TRACKER_TICKET_PHP_EXPLAIN }')); ?></span></dt>
				<dd><input type="text" id="ticket_php" name="ticket_php" value="<?php echo (isset($this->_rootref['TICKET_PHP'])) ? $this->_rootref['TICKET_PHP'] : ''; ?>" class="inputbox narrow" /><br /><span class="small"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_PHP_EXPLAIN_BAD'])) ? $this->_rootref['L_TRACKER_TICKET_PHP_EXPLAIN_BAD'] : ((isset($user->lang['TRACKER_TICKET_PHP_EXPLAIN_BAD'])) ? $user->lang['TRACKER_TICKET_PHP_EXPLAIN_BAD'] : '{ TRACKER_TICKET_PHP_EXPLAIN_BAD }')); ?><br /><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_PHP_EXPLAIN_GOOD'])) ? $this->_rootref['L_TRACKER_TICKET_PHP_EXPLAIN_GOOD'] : ((isset($user->lang['TRACKER_TICKET_PHP_EXPLAIN_GOOD'])) ? $user->lang['TRACKER_TICKET_PHP_EXPLAIN_GOOD'] : '{ TRACKER_TICKET_PHP_EXPLAIN_GOOD }')); ?></span></dd>
			</dl>
			<?php } if ($this->_rootref['S_SHOW_DBMS']) {  ?>

			<dl>
				<dt><label for="ticket_dbms"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_DBMS'])) ? $this->_rootref['L_TRACKER_TICKET_DBMS'] : ((isset($user->lang['TRACKER_TICKET_DBMS'])) ? $user->lang['TRACKER_TICKET_DBMS'] : '{ TRACKER_TICKET_DBMS }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_DBMS_EXPLAIN'])) ? $this->_rootref['L_TRACKER_TICKET_DBMS_EXPLAIN'] : ((isset($user->lang['TRACKER_TICKET_DBMS_EXPLAIN'])) ? $user->lang['TRACKER_TICKET_DBMS_EXPLAIN'] : '{ TRACKER_TICKET_DBMS_EXPLAIN }')); ?></span></dt>
				<dd><input type="text" id="ticket_dbms" name="ticket_dbms" value="<?php echo (isset($this->_rootref['TICKET_DBMS'])) ? $this->_rootref['TICKET_DBMS'] : ''; ?>" class="inputbox narrow" /><br /><span class="small"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_DBMS_EXPLAIN_BAD'])) ? $this->_rootref['L_TRACKER_TICKET_DBMS_EXPLAIN_BAD'] : ((isset($user->lang['TRACKER_TICKET_DBMS_EXPLAIN_BAD'])) ? $user->lang['TRACKER_TICKET_DBMS_EXPLAIN_BAD'] : '{ TRACKER_TICKET_DBMS_EXPLAIN_BAD }')); ?><br /><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_DBMS_EXPLAIN_GOOD'])) ? $this->_rootref['L_TRACKER_TICKET_DBMS_EXPLAIN_GOOD'] : ((isset($user->lang['TRACKER_TICKET_DBMS_EXPLAIN_GOOD'])) ? $user->lang['TRACKER_TICKET_DBMS_EXPLAIN_GOOD'] : '{ TRACKER_TICKET_DBMS_EXPLAIN_GOOD }')); ?></span></dd>
			</dl>
			<?php } ?>

		</fieldset>
		<span class="corners-bottom"><span></span></span></div>
	</div>
	<?php } ?>

	<div class="panel bg2">
		<div class="inner"><span class="corners-top"><span></span></span>
		<fieldset>
			<h3><?php echo ((isset($this->_rootref['L_TRACKER_TICKET'])) ? $this->_rootref['L_TRACKER_TICKET'] : ((isset($user->lang['TRACKER_TICKET'])) ? $user->lang['TRACKER_TICKET'] : '{ TRACKER_TICKET }')); ?></h3>
			<?php if ($this->_rootref['S_DISPLAY_USERNAME']) {  ?>

			<dl style="clear: left;">
				<dt><label for="username"><?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>:</label></dt>
				<dd><input type="text" tabindex="1" name="username" id="username" size="25" value="<?php echo (isset($this->_rootref['USERNAME'])) ? $this->_rootref['USERNAME'] : ''; ?>" class="inputbox autowidth" /></dd>
			</dl>
			<?php } if ($this->_rootref['S_EDIT_REASON']) {  ?>

			<dl>
				<dt><label for="edit_reason"><?php echo ((isset($this->_rootref['L_TRACKER_EDIT_REASON'])) ? $this->_rootref['L_TRACKER_EDIT_REASON'] : ((isset($user->lang['TRACKER_EDIT_REASON'])) ? $user->lang['TRACKER_EDIT_REASON'] : '{ TRACKER_EDIT_REASON }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_EDIT_REASON_EXPLAIN'])) ? $this->_rootref['L_TRACKER_EDIT_REASON_EXPLAIN'] : ((isset($user->lang['TRACKER_EDIT_REASON_EXPLAIN'])) ? $user->lang['TRACKER_EDIT_REASON_EXPLAIN'] : '{ TRACKER_EDIT_REASON_EXPLAIN }')); ?></span></dt>
				<dd><input type="text" id="edit_reason" name="edit_reason" value="<?php echo (isset($this->_rootref['EDIT_REASON_TEXT'])) ? $this->_rootref['EDIT_REASON_TEXT'] : ''; ?>" class="inputbox medium" /></dd>
			</dl>
			<?php } ?>

			<dl>
				<dt><label for="ticket_title"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_TITLE'])) ? $this->_rootref['L_TRACKER_TICKET_TITLE'] : ((isset($user->lang['TRACKER_TICKET_TITLE'])) ? $user->lang['TRACKER_TICKET_TITLE'] : '{ TRACKER_TICKET_TITLE }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_TITLE_EXPLAIN'])) ? $this->_rootref['L_TRACKER_TICKET_TITLE_EXPLAIN'] : ((isset($user->lang['TRACKER_TICKET_TITLE_EXPLAIN'])) ? $user->lang['TRACKER_TICKET_TITLE_EXPLAIN'] : '{ TRACKER_TICKET_TITLE_EXPLAIN }')); ?></span></dt>
				<dd><input type="text" id="ticket_title" name="ticket_title" value="<?php echo (isset($this->_rootref['TICKET_TITLE'])) ? $this->_rootref['TICKET_TITLE'] : ''; ?>" class="inputbox medium" /><br /><span class="small"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_TITLE_EXPLAIN_BAD'])) ? $this->_rootref['L_TRACKER_TICKET_TITLE_EXPLAIN_BAD'] : ((isset($user->lang['TRACKER_TICKET_TITLE_EXPLAIN_BAD'])) ? $user->lang['TRACKER_TICKET_TITLE_EXPLAIN_BAD'] : '{ TRACKER_TICKET_TITLE_EXPLAIN_BAD }')); ?><br /><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_TITLE_EXPLAIN_GOOD'])) ? $this->_rootref['L_TRACKER_TICKET_TITLE_EXPLAIN_GOOD'] : ((isset($user->lang['TRACKER_TICKET_TITLE_EXPLAIN_GOOD'])) ? $user->lang['TRACKER_TICKET_TITLE_EXPLAIN_GOOD'] : '{ TRACKER_TICKET_TITLE_EXPLAIN_GOOD }')); ?></span></dd>
			</dl>
			<dl>
				<dt><label for="message"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_DESC'])) ? $this->_rootref['L_TRACKER_TICKET_DESC'] : ((isset($user->lang['TRACKER_TICKET_DESC'])) ? $user->lang['TRACKER_TICKET_DESC'] : '{ TRACKER_TICKET_DESC }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_DESC_EXPLAIN'])) ? $this->_rootref['L_TRACKER_TICKET_DESC_EXPLAIN'] : ((isset($user->lang['TRACKER_TICKET_DESC_EXPLAIN'])) ? $user->lang['TRACKER_TICKET_DESC_EXPLAIN'] : '{ TRACKER_TICKET_DESC_EXPLAIN }')); ?></span></dt>
			</dl>
			<?php $this->_tpl_include('posting_buttons.html'); ?>

			<div id="smiley-box">
				<?php if ($this->_rootref['S_SMILIES_ALLOWED'] && sizeof($this->_tpldata['smiley'])) {  ?>

					<strong><?php echo ((isset($this->_rootref['L_SMILIES'])) ? $this->_rootref['L_SMILIES'] : ((isset($user->lang['SMILIES'])) ? $user->lang['SMILIES'] : '{ SMILIES }')); ?></strong><br />
					<?php $_smiley_count = (isset($this->_tpldata['smiley'])) ? sizeof($this->_tpldata['smiley']) : 0;if ($_smiley_count) {for ($_smiley_i = 0; $_smiley_i < $_smiley_count; ++$_smiley_i){$_smiley_val = &$this->_tpldata['smiley'][$_smiley_i]; ?>

						<a href="#" onclick="insert_text('<?php echo $_smiley_val['A_SMILEY_CODE']; ?>', true); return false;"><img src="<?php echo $_smiley_val['SMILEY_IMG']; ?>" width="<?php echo $_smiley_val['SMILEY_WIDTH']; ?>" height="<?php echo $_smiley_val['SMILEY_HEIGHT']; ?>" alt="<?php echo $_smiley_val['SMILEY_CODE']; ?>" title="<?php echo $_smiley_val['SMILEY_DESC']; ?>" /></a>
					<?php }} } if ($this->_rootref['S_SHOW_SMILEY_LINK'] && $this->_rootref['S_SMILIES_ALLOWED']) {  ?>

					<br /><a href="<?php echo (isset($this->_rootref['U_MORE_SMILIES'])) ? $this->_rootref['U_MORE_SMILIES'] : ''; ?>" onclick="popup(this.href, 300, 350, '_phpbbsmilies'); return false;"><?php echo ((isset($this->_rootref['L_MORE_SMILIES'])) ? $this->_rootref['L_MORE_SMILIES'] : ((isset($user->lang['MORE_SMILIES'])) ? $user->lang['MORE_SMILIES'] : '{ MORE_SMILIES }')); ?></a>
				<?php } ?>

			</div>

			<div id="message-box">
				<textarea name="message" id="message" rows="15" cols="76" tabindex="4" onselect="storeCaret(this);" onclick="storeCaret(this);" onkeyup="storeCaret(this);" onfocus="initInsertions();" class="inputbox"><?php echo (isset($this->_rootref['TICKET_DESC'])) ? $this->_rootref['TICKET_DESC'] : ''; ?></textarea>
			</div>
		</fieldset>
		<span class="corners-bottom"><span></span></span></div>
	</div>

	<?php if ($this->_rootref['S_MANAGE_TICKET'] || $this->_rootref['S_TICKET_SECURITY']) {  ?>

		<div class="panel bg2">
			<div class="inner"><span class="corners-top"><span></span></span>
			<h3><?php echo ((isset($this->_rootref['L_OPTIONS'])) ? $this->_rootref['L_OPTIONS'] : ((isset($user->lang['OPTIONS'])) ? $user->lang['OPTIONS'] : '{ OPTIONS }')); ?></h3>
			<fieldset class="fields2">
				<?php if ($this->_rootref['S_TICKET_SECURITY']) {  ?>

				<dl>
					<dt><label for="ticket_security"><?php echo ((isset($this->_rootref['L_TRACKER_SECURITY_TICKET'])) ? $this->_rootref['L_TRACKER_SECURITY_TICKET'] : ((isset($user->lang['TRACKER_SECURITY_TICKET'])) ? $user->lang['TRACKER_SECURITY_TICKET'] : '{ TRACKER_SECURITY_TICKET }')); ?>:</label></dt>
					<dd><input type="checkbox" name="ticket_security" id="ticket_security" value="1"<?php if ($this->_rootref['TICKET_SECURITY']) {  ?> checked="checked"<?php } ?> /></dd>
				</dl>
				<?php } if ($this->_rootref['S_MANAGE_TICKET']) {  ?>

				<dl>
					<dt><label for="ticket_hidden"><?php echo ((isset($this->_rootref['L_TRACKER_HIDE_TICKET'])) ? $this->_rootref['L_TRACKER_HIDE_TICKET'] : ((isset($user->lang['TRACKER_HIDE_TICKET'])) ? $user->lang['TRACKER_HIDE_TICKET'] : '{ TRACKER_HIDE_TICKET }')); ?>:</label></dt>
					<dd><input type="checkbox" name="ticket_hidden" id="ticket_hidden" value="1"<?php if ($this->_rootref['TICKET_HIDDEN']) {  ?> checked="checked"<?php } ?> /></dd>
				</dl>
				<dl>
					<dt><label for="ticket_status"><?php echo ((isset($this->_rootref['L_TRACKER_LOCK_TICKET'])) ? $this->_rootref['L_TRACKER_LOCK_TICKET'] : ((isset($user->lang['TRACKER_LOCK_TICKET'])) ? $user->lang['TRACKER_LOCK_TICKET'] : '{ TRACKER_LOCK_TICKET }')); ?>:</label></dt>
					<dd><input type="checkbox" name="ticket_status" id="ticket_status" value="1"<?php if ($this->_rootref['S_IS_LOCKED']) {  ?> checked="checked"<?php } ?> /></dd>
				</dl>
				<?php } ?>

			</fieldset>
			<span class="corners-bottom"><span></span></span></div>
		</div>
		<?php } if ($this->_rootref['S_CAN_ATTACH']) {  $this->_tpl_include('posting_attach_body.html'); } if ($this->_rootref['S_HAS_ATTACHMENTS']) {  ?>

		<div class="panel bg2">
			<div class="inner"><span class="corners-top"><span></span></span>
			<h3><?php echo ((isset($this->_rootref['L_POSTED_ATTACHMENTS'])) ? $this->_rootref['L_POSTED_ATTACHMENTS'] : ((isset($user->lang['POSTED_ATTACHMENTS'])) ? $user->lang['POSTED_ATTACHMENTS'] : '{ POSTED_ATTACHMENTS }')); ?></h3>

			<fieldset class="fields2">

			<?php $_attach_row_count = (isset($this->_tpldata['attach_row'])) ? sizeof($this->_tpldata['attach_row']) : 0;if ($_attach_row_count) {for ($_attach_row_i = 0; $_attach_row_i < $_attach_row_count; ++$_attach_row_i){$_attach_row_val = &$this->_tpldata['attach_row'][$_attach_row_i]; ?>

			<dl>

				<dt><label for="comment_list[<?php echo $_attach_row_val['ASSOC_INDEX']; ?>]"><?php echo ((isset($this->_rootref['L_FILE_COMMENT'])) ? $this->_rootref['L_FILE_COMMENT'] : ((isset($user->lang['FILE_COMMENT'])) ? $user->lang['FILE_COMMENT'] : '{ FILE_COMMENT }')); ?>:</label></dt>
				<dd><textarea name="comment_list[<?php echo $_attach_row_val['ASSOC_INDEX']; ?>]" id="comment_list[<?php echo $_attach_row_val['ASSOC_INDEX']; ?>]" rows="1" cols="35" class="inputbox"><?php echo $_attach_row_val['FILE_COMMENT']; ?></textarea></dd>
				<dd><a href="<?php echo $_attach_row_val['U_VIEW_ATTACHMENT']; ?>" class="<?php echo (isset($this->_rootref['S_CONTENT_FLOW_END'])) ? $this->_rootref['S_CONTENT_FLOW_END'] : ''; ?>"><?php echo $_attach_row_val['FILENAME']; ?></a></dd>
				<dd style="margin-top: 5px;">
					<?php if ($this->_rootref['S_INLINE_ATTACHMENT_OPTIONS']) {  ?><input type="button" value="<?php echo ((isset($this->_rootref['L_PLACE_INLINE'])) ? $this->_rootref['L_PLACE_INLINE'] : ((isset($user->lang['PLACE_INLINE'])) ? $user->lang['PLACE_INLINE'] : '{ PLACE_INLINE }')); ?>" onclick="attach_inline(<?php echo $_attach_row_val['ASSOC_INDEX']; ?>, '<?php echo $_attach_row_val['A_FILENAME']; ?>');" class="button2" />&nbsp; <?php } ?>

					<input type="submit" name="delete_file[<?php echo $_attach_row_val['ASSOC_INDEX']; ?>]" value="<?php echo ((isset($this->_rootref['L_DELETE_FILE'])) ? $this->_rootref['L_DELETE_FILE'] : ((isset($user->lang['DELETE_FILE'])) ? $user->lang['DELETE_FILE'] : '{ DELETE_FILE }')); ?>" class="button2" />
				</dd>
			</dl>
			<?php echo $_attach_row_val['S_HIDDEN']; ?>

				<?php if (! $_attach_row_val['S_LAST_ROW']) {  ?><hr class="dashed" /><?php } }} ?>


			</fieldset>

			<span class="corners-bottom"><span></span></span></div>
		</div>
	<?php } ?>


	<div class="panel bg2">
		<div class="inner"><span class="corners-top"><span></span></span>
		<fieldset>
			<h3><?php echo ((isset($this->_rootref['L_TRACKER_SUBMIT_TICKET'])) ? $this->_rootref['L_TRACKER_SUBMIT_TICKET'] : ((isset($user->lang['TRACKER_SUBMIT_TICKET'])) ? $user->lang['TRACKER_SUBMIT_TICKET'] : '{ TRACKER_SUBMIT_TICKET }')); ?></h3>
			<p><?php echo ((isset($this->_rootref['L_TRACKER_SUBMIT_TICKET_EXPLAIN'])) ? $this->_rootref['L_TRACKER_SUBMIT_TICKET_EXPLAIN'] : ((isset($user->lang['TRACKER_SUBMIT_TICKET_EXPLAIN'])) ? $user->lang['TRACKER_SUBMIT_TICKET_EXPLAIN'] : '{ TRACKER_SUBMIT_TICKET_EXPLAIN }')); ?></p>
			<?php if ($this->_rootref['CAPTCHA_TEMPLATE'] && $this->_rootref['S_CONFIRM_CODE']) {  $this->_tpldata['DEFINE']['.']['CAPTCHA_TAB_INDEX'] = 3; if (isset($this->_rootref['CAPTCHA_TEMPLATE'])) { $this->_tpl_include($this->_rootref['CAPTCHA_TEMPLATE']); } } ?>

			<input type="submit" name="preview" value="<?php echo ((isset($this->_rootref['L_TRACKER_PREVIEW_TICKET'])) ? $this->_rootref['L_TRACKER_PREVIEW_TICKET'] : ((isset($user->lang['TRACKER_PREVIEW_TICKET'])) ? $user->lang['TRACKER_PREVIEW_TICKET'] : '{ TRACKER_PREVIEW_TICKET }')); ?>" class="button2" /> <input type="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_TRACKER_SUBMIT_TICKET'])) ? $this->_rootref['L_TRACKER_SUBMIT_TICKET'] : ((isset($user->lang['TRACKER_SUBMIT_TICKET'])) ? $user->lang['TRACKER_SUBMIT_TICKET'] : '{ TRACKER_SUBMIT_TICKET }')); ?>" class="button1" accesskey="s" />
			<?php echo (isset($this->_rootref['S_HIDDEN_FIELDS_CONFIRM'])) ? $this->_rootref['S_HIDDEN_FIELDS_CONFIRM'] : ''; ?>

			<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

		</fieldset>
		<span class="corners-bottom"><span></span></span></div>
	</div>
</form>

<?php $this->_tpl_include('tracker/tracker_footer.html'); $this->_tpl_include('overall_footer.html'); ?>