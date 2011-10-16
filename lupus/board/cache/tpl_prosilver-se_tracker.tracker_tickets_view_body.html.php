<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>


<h2><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></h2>
<?php if ($this->_rootref['S_TICKET_REPLY']) {  ?>

	<p><?php echo ((isset($this->_rootref['L_TITLE_EXPLAIN'])) ? $this->_rootref['L_TITLE_EXPLAIN'] : ((isset($user->lang['TITLE_EXPLAIN'])) ? $user->lang['TITLE_EXPLAIN'] : '{ TITLE_EXPLAIN }')); ?></p>
<?php } if ($this->_rootref['TICKET_HIDDEN'] || $this->_rootref['TICKET_SECURITY']) {  ?>

<div class="rules" align="center">
	<div class="inner"><span class="corners-top"><span></span></span>
		<?php if ($this->_rootref['TICKET_HIDDEN']) {  echo ((isset($this->_rootref['L_TRACKER_TICKET_HIDDEN_FROM_VIEW'])) ? $this->_rootref['L_TRACKER_TICKET_HIDDEN_FROM_VIEW'] : ((isset($user->lang['TRACKER_TICKET_HIDDEN_FROM_VIEW'])) ? $user->lang['TRACKER_TICKET_HIDDEN_FROM_VIEW'] : '{ TRACKER_TICKET_HIDDEN_FROM_VIEW }')); } if ($this->_rootref['TICKET_HIDDEN'] && $this->_rootref['TICKET_SECURITY']) {  ?><br /><?php } if ($this->_rootref['TICKET_SECURITY']) {  echo ((isset($this->_rootref['L_TRACKER_TICKET_SECURITY_FROM_VIEW'])) ? $this->_rootref['L_TRACKER_TICKET_SECURITY_FROM_VIEW'] : ((isset($user->lang['TRACKER_TICKET_SECURITY_FROM_VIEW'])) ? $user->lang['TRACKER_TICKET_SECURITY_FROM_VIEW'] : '{ TRACKER_TICKET_SECURITY_FROM_VIEW }')); } ?>

	<span class="corners-bottom"><span></span></span></div>
</div>
<br />
<?php } ?>


<div id="col1">
<?php if (! $this->_rootref['S_TICKET_REPLY']) {  ?>

	<h3 style="color: #D21A4E;<?php if ($this->_rootref['TICKET_CLOSED']) {  ?> text-decoration: line-through;<?php } ?>"><?php echo (isset($this->_rootref['TICKET_TITLE'])) ? $this->_rootref['TICKET_TITLE'] : ''; ?><span style="color: black;"> <?php echo (isset($this->_rootref['TICKET_STATUS'])) ? $this->_rootref['TICKET_STATUS'] : ''; ?></span></h3>
	<div class="post bg2">
		<div class="inner"><span class="corners-top"><span></span></span>
			<div class="postbody" style="width: auto; float: none; margin: 0; height: auto;">
					<?php if (! $this->_rootref['S_IS_BOT']) {  if ($this->_rootref['S_CAN_DELETE'] || $this->_rootref['S_CAN_EDIT']) {  ?>

						<ul class="profile-icons">
							<?php if ($this->_rootref['S_CAN_EDIT']) {  ?><li class="edit-icon"><a href="<?php echo (isset($this->_rootref['U_EDIT'])) ? $this->_rootref['U_EDIT'] : ''; ?>" title="<?php echo ((isset($this->_rootref['L_EDIT_POST'])) ? $this->_rootref['L_EDIT_POST'] : ((isset($user->lang['EDIT_POST'])) ? $user->lang['EDIT_POST'] : '{ EDIT_POST }')); ?>"><span><?php echo ((isset($this->_rootref['L_EDIT_POST'])) ? $this->_rootref['L_EDIT_POST'] : ((isset($user->lang['EDIT_POST'])) ? $user->lang['EDIT_POST'] : '{ EDIT_POST }')); ?></span></a></li><?php } if ($this->_rootref['S_CAN_DELETE']) {  ?><li class="delete-icon"><a href="<?php echo (isset($this->_rootref['U_DELETE'])) ? $this->_rootref['U_DELETE'] : ''; ?>" title="<?php echo ((isset($this->_rootref['L_DELETE_POST'])) ? $this->_rootref['L_DELETE_POST'] : ((isset($user->lang['DELETE_POST'])) ? $user->lang['DELETE_POST'] : '{ DELETE_POST }')); ?>"><span><?php echo ((isset($this->_rootref['L_DELETE_POST'])) ? $this->_rootref['L_DELETE_POST'] : ((isset($user->lang['DELETE_POST'])) ? $user->lang['DELETE_POST'] : '{ DELETE_POST }')); ?></span></a></li><?php } ?>

						</ul>
						<?php } } ?>

				<p class="author">&nbsp;</p>
				<div class="content"><?php echo (isset($this->_rootref['TICKET_DESC'])) ? $this->_rootref['TICKET_DESC'] : ''; ?></div>
				<?php if ($this->_rootref['S_TICKET_HAS_ATTACHMENTS']) {  ?>

					<dl class="attachbox">
						<dt><?php echo ((isset($this->_rootref['L_ATTACHMENTS'])) ? $this->_rootref['L_ATTACHMENTS'] : ((isset($user->lang['ATTACHMENTS'])) ? $user->lang['ATTACHMENTS'] : '{ ATTACHMENTS }')); ?></dt>
						<?php $_attachment_count = (isset($this->_tpldata['attachment'])) ? sizeof($this->_tpldata['attachment']) : 0;if ($_attachment_count) {for ($_attachment_i = 0; $_attachment_i < $_attachment_count; ++$_attachment_i){$_attachment_val = &$this->_tpldata['attachment'][$_attachment_i]; ?>

							<dd><?php echo $_attachment_val['DISPLAY_ATTACHMENT']; ?></dd>
						<?php }} ?>

					</dl>
				<?php } if ($this->_rootref['S_DISPLAY_NOTICE']) {  ?><div class="rules"><?php echo ((isset($this->_rootref['L_DOWNLOAD_NOTICE'])) ? $this->_rootref['L_DOWNLOAD_NOTICE'] : ((isset($user->lang['DOWNLOAD_NOTICE'])) ? $user->lang['DOWNLOAD_NOTICE'] : '{ DOWNLOAD_NOTICE }')); ?></div><?php } if ($this->_rootref['EDITED_MESSAGE'] || $this->_rootref['EDIT_REASON']) {  ?>

					<div class="notice"><?php echo (isset($this->_rootref['EDITED_MESSAGE'])) ? $this->_rootref['EDITED_MESSAGE'] : ''; ?>

						<?php if ($this->_rootref['EDIT_REASON']) {  ?><br /><strong><?php echo ((isset($this->_rootref['L_REASON'])) ? $this->_rootref['L_REASON'] : ((isset($user->lang['REASON'])) ? $user->lang['REASON'] : '{ REASON }')); ?>:</strong> <em><?php echo (isset($this->_rootref['EDIT_REASON'])) ? $this->_rootref['EDIT_REASON'] : ''; ?></em><?php } ?>

					</div>
				<?php } ?>

			</div>
		<span class="corners-bottom"><span></span></span></div>
	</div>

	<?php if ($this->_rootref['S_DISPLAY_COMMENTS']) {  ?>

	<h3><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_COMMENTS'])) ? $this->_rootref['L_TRACKER_TICKET_COMMENTS'] : ((isset($user->lang['TRACKER_TICKET_COMMENTS'])) ? $user->lang['TRACKER_TICKET_COMMENTS'] : '{ TRACKER_TICKET_COMMENTS }')); ?></h3>
		<?php $_comments_count = (isset($this->_tpldata['comments'])) ? sizeof($this->_tpldata['comments']) : 0;if ($_comments_count) {for ($_comments_i = 0; $_comments_i < $_comments_count; ++$_comments_i){$_comments_val = &$this->_tpldata['comments'][$_comments_i]; ?>

		<div id="p<?php echo $_comments_val['POST_ID']; ?>" class="post <?php if (($_comments_val['S_ROW_COUNT'] & 1)  ) {  ?> bg1<?php } else { ?> bg2<?php } ?>">
			<div class="inner"><span class="corners-top"><span></span></span>
				<div class="postbody" style="width: auto; float: none; margin: 0; height: auto;">
					<?php if (! $this->_rootref['S_IS_BOT']) {  if ($_comments_val['S_CAN_DELETE'] || $_comments_val['S_CAN_EDIT']) {  ?>

						<ul class="profile-icons">
							<?php if ($_comments_val['S_CAN_EDIT']) {  ?><li class="edit-icon"><a href="<?php echo $_comments_val['U_EDIT']; ?>" title="<?php echo ((isset($this->_rootref['L_EDIT_POST'])) ? $this->_rootref['L_EDIT_POST'] : ((isset($user->lang['EDIT_POST'])) ? $user->lang['EDIT_POST'] : '{ EDIT_POST }')); ?>"><span><?php echo ((isset($this->_rootref['L_EDIT_POST'])) ? $this->_rootref['L_EDIT_POST'] : ((isset($user->lang['EDIT_POST'])) ? $user->lang['EDIT_POST'] : '{ EDIT_POST }')); ?></span></a></li><?php } if ($_comments_val['S_CAN_DELETE']) {  ?><li class="delete-icon"><a href="<?php echo $_comments_val['U_DELETE']; ?>" title="<?php echo ((isset($this->_rootref['L_DELETE_POST'])) ? $this->_rootref['L_DELETE_POST'] : ((isset($user->lang['DELETE_POST'])) ? $user->lang['DELETE_POST'] : '{ DELETE_POST }')); ?>"><span><?php echo ((isset($this->_rootref['L_DELETE_POST'])) ? $this->_rootref['L_DELETE_POST'] : ((isset($user->lang['DELETE_POST'])) ? $user->lang['DELETE_POST'] : '{ DELETE_POST }')); ?></span></a></li><?php } ?>

						</ul>
						<?php } } ?>

					<p class="author"><a href="#p<?php echo $_comments_val['POST_ID']; ?>"><?php echo (isset($this->_rootref['POST_IMG'])) ? $this->_rootref['POST_IMG'] : ''; ?></a> <strong><?php echo $_comments_val['COMMENT_POSTER']; ?></strong></p>
					<div class="content"><?php echo $_comments_val['COMMENT_DESC']; ?></div>
					<?php if ($_comments_val['S_HAS_ATTACHMENTS']) {  ?>

						<dl class="attachbox">
							<dt><?php echo ((isset($this->_rootref['L_ATTACHMENTS'])) ? $this->_rootref['L_ATTACHMENTS'] : ((isset($user->lang['ATTACHMENTS'])) ? $user->lang['ATTACHMENTS'] : '{ ATTACHMENTS }')); ?></dt>
							<?php $_attachment_count = (isset($_comments_val['attachment'])) ? sizeof($_comments_val['attachment']) : 0;if ($_attachment_count) {for ($_attachment_i = 0; $_attachment_i < $_attachment_count; ++$_attachment_i){$_attachment_val = &$_comments_val['attachment'][$_attachment_i]; ?>

								<dd><?php echo $_attachment_val['DISPLAY_ATTACHMENT']; ?></dd>
							<?php }} ?>

						</dl>
					<?php } if ($_comments_val['S_DISPLAY_NOTICE']) {  ?><div class="rules"><?php echo ((isset($this->_rootref['L_DOWNLOAD_NOTICE'])) ? $this->_rootref['L_DOWNLOAD_NOTICE'] : ((isset($user->lang['DOWNLOAD_NOTICE'])) ? $user->lang['DOWNLOAD_NOTICE'] : '{ DOWNLOAD_NOTICE }')); ?></div><?php } if ($_comments_val['EDITED_MESSAGE'] || $_comments_val['EDIT_REASON']) {  ?>

						<div class="notice"><?php echo $_comments_val['EDITED_MESSAGE']; ?>

							<?php if ($_comments_val['EDIT_REASON']) {  ?><br /><strong><?php echo ((isset($this->_rootref['L_REASON'])) ? $this->_rootref['L_REASON'] : ((isset($user->lang['REASON'])) ? $user->lang['REASON'] : '{ REASON }')); ?>:</strong> <em><?php echo $_comments_val['EDIT_REASON']; ?></em><?php } ?>

						</div>
					<?php } ?>

				</div>
			<span class="corners-bottom"><span></span></span></div>
		</div>
		<?php }} else { ?>

			<div class="post bg1">
			<div class="inner"><span class="corners-top"><span></span></span>
				<?php echo ((isset($this->_rootref['L_TRACKER_TICKET_NO_COMMENTS'])) ? $this->_rootref['L_TRACKER_TICKET_NO_COMMENTS'] : ((isset($user->lang['TRACKER_TICKET_NO_COMMENTS'])) ? $user->lang['TRACKER_TICKET_NO_COMMENTS'] : '{ TRACKER_TICKET_NO_COMMENTS }')); ?>

			<span class="corners-bottom"><span></span></span></div>
		</div>
		<?php } } if ($this->_rootref['S_DISPLAY_HISTORY']) {  ?>

	<h3><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_HISTORY'])) ? $this->_rootref['L_TRACKER_TICKET_HISTORY'] : ((isset($user->lang['TRACKER_TICKET_HISTORY'])) ? $user->lang['TRACKER_TICKET_HISTORY'] : '{ TRACKER_TICKET_HISTORY }')); ?></h3>
		<?php $_history_count = (isset($this->_tpldata['history'])) ? sizeof($this->_tpldata['history']) : 0;if ($_history_count) {for ($_history_i = 0; $_history_i < $_history_count; ++$_history_i){$_history_val = &$this->_tpldata['history'][$_history_i]; ?>

		<div class="post <?php if (($_history_val['S_ROW_COUNT'] & 1)  ) {  ?> bg1<?php } else { ?> bg2<?php } ?>">
			<div class="inner"><span class="corners-top"><span></span></span>
					<strong><?php echo $_history_val['HISTORY_ACTION']; ?></strong>
					<div style="margin-top: 10px; padding-top: 2px; border-top: 1px dashed #CCCCCC;"><?php echo $_history_val['HISTORY_ACTION_BY']; ?></div>
			<span class="corners-bottom"><span></span></span></div>
		</div>
		<?php }} else { ?>

			<div class="post bg1">
			<div class="inner"><span class="corners-top"><span></span></span>
				<?php echo ((isset($this->_rootref['L_TRACKER_TICKET_NO_HISTORY'])) ? $this->_rootref['L_TRACKER_TICKET_NO_HISTORY'] : ((isset($user->lang['TRACKER_TICKET_NO_HISTORY'])) ? $user->lang['TRACKER_TICKET_NO_HISTORY'] : '{ TRACKER_TICKET_NO_HISTORY }')); ?>

			<span class="corners-bottom"><span></span></span></div>
		</div>
		<?php } } if ($this->_rootref['S_CAN_POST_TRACKER'] || $this->_rootref['PAGINATION'] || $this->_rootref['TOTAL_POSTS']) {  ?>

	<div class="topic-actions">
		<?php if ($this->_rootref['S_CAN_POST_TRACKER'] && ! $this->_rootref['S_IS_BOT']) {  ?>

		<div class="buttons">
			<div class="<?php if ($this->_rootref['S_IS_LOCKED']) {  ?>locked-icon<?php } else { ?>reply-icon<?php } ?>"><a href="<?php echo (isset($this->_rootref['U_POST_REPLY_TICKET'])) ? $this->_rootref['U_POST_REPLY_TICKET'] : ''; ?>" title="<?php if ($this->_rootref['S_IS_LOCKED']) {  echo ((isset($this->_rootref['L_TOPIC_LOCKED'])) ? $this->_rootref['L_TOPIC_LOCKED'] : ((isset($user->lang['TOPIC_LOCKED'])) ? $user->lang['TOPIC_LOCKED'] : '{ TOPIC_LOCKED }')); } else { echo ((isset($this->_rootref['L_POST_REPLY'])) ? $this->_rootref['L_POST_REPLY'] : ((isset($user->lang['POST_REPLY'])) ? $user->lang['POST_REPLY'] : '{ POST_REPLY }')); } ?>"><span></span><?php if ($this->_rootref['S_IS_LOCKED']) {  echo ((isset($this->_rootref['L_TOPIC_LOCKED_SHORT'])) ? $this->_rootref['L_TOPIC_LOCKED_SHORT'] : ((isset($user->lang['TOPIC_LOCKED_SHORT'])) ? $user->lang['TOPIC_LOCKED_SHORT'] : '{ TOPIC_LOCKED_SHORT }')); } else { echo ((isset($this->_rootref['L_POST_REPLY'])) ? $this->_rootref['L_POST_REPLY'] : ((isset($user->lang['POST_REPLY'])) ? $user->lang['POST_REPLY'] : '{ POST_REPLY }')); } ?></a></div>
		</div>
		<?php } if ($this->_rootref['PAGINATION'] || $this->_rootref['TOTAL_POSTS']) {  ?>

		<div class="pagination">
			<?php echo (isset($this->_rootref['TOTAL_POSTS'])) ? $this->_rootref['TOTAL_POSTS'] : ''; ?>

			<?php if ($this->_rootref['PAGE_NUMBER']) {  if ($this->_rootref['PAGINATION']) {  ?> &bull; <a href="#" onclick="jumpto(); return false;" title="<?php echo ((isset($this->_rootref['L_JUMP_TO_PAGE'])) ? $this->_rootref['L_JUMP_TO_PAGE'] : ((isset($user->lang['JUMP_TO_PAGE'])) ? $user->lang['JUMP_TO_PAGE'] : '{ JUMP_TO_PAGE }')); ?>"><?php echo (isset($this->_rootref['PAGE_NUMBER'])) ? $this->_rootref['PAGE_NUMBER'] : ''; ?></a> &bull; <span><?php echo (isset($this->_rootref['PAGINATION'])) ? $this->_rootref['PAGINATION'] : ''; ?></span><?php } else { ?> &bull; <?php echo (isset($this->_rootref['PAGE_NUMBER'])) ? $this->_rootref['PAGE_NUMBER'] : ''; } } ?>

		</div>
		<?php } ?>

	</div>
	<?php } } else { if ($this->_rootref['S_PREVIEW']) {  ?>

	<h3><?php echo ((isset($this->_rootref['L_TRACKER_PREVIEW'])) ? $this->_rootref['L_TRACKER_PREVIEW'] : ((isset($user->lang['TRACKER_PREVIEW'])) ? $user->lang['TRACKER_PREVIEW'] : '{ TRACKER_PREVIEW }')); ?></h3>
	<div class="panel bg2">
		<div class="inner"><span class="corners-top"><span></span></span>
			<div class="postbody" style="width: auto; float: none; margin: 0; height: auto;">
				<div class="content"><?php echo (isset($this->_rootref['REPLY_PREVIEW'])) ? $this->_rootref['REPLY_PREVIEW'] : ''; ?></div>
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
	<br />
	<?php } ?>

	<form id="postform" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>" method="post"<?php echo (isset($this->_rootref['S_FORM_ENCTYPE'])) ? $this->_rootref['S_FORM_ENCTYPE'] : ''; ?>>
		<div class="panel bg2">
			<span class="corners-top"><span></span></span>
			<h3><?php echo ((isset($this->_rootref['L_MESSAGE'])) ? $this->_rootref['L_MESSAGE'] : ((isset($user->lang['MESSAGE'])) ? $user->lang['MESSAGE'] : '{ MESSAGE }')); ?></h3>
			<fieldset class="fields1">
			<?php if ($this->_rootref['ERROR']) {  ?><p class="error"><?php echo (isset($this->_rootref['ERROR'])) ? $this->_rootref['ERROR'] : ''; ?></p><?php } if ($this->_rootref['S_DISPLAY_USERNAME']) {  ?>

				<dl style="clear: left;">
					<dt><label for="username"><?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>:</label></dt>
					<dd><input type="text" tabindex="1" name="username" id="username" size="25" value="<?php echo (isset($this->_rootref['USERNAME'])) ? $this->_rootref['USERNAME'] : ''; ?>" class="inputbox autowidth" /></dd>
				</dl>
				<?php } if ($this->_rootref['S_EDIT_REASON']) {  ?>

				<dl>
					<dt><label for="edit_reason"><?php echo ((isset($this->_rootref['L_TRACKER_EDIT_REASON'])) ? $this->_rootref['L_TRACKER_EDIT_REASON'] : ((isset($user->lang['TRACKER_EDIT_REASON'])) ? $user->lang['TRACKER_EDIT_REASON'] : '{ TRACKER_EDIT_REASON }')); ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_TRACKER_EDIT_REASON_EXPLAIN'])) ? $this->_rootref['L_TRACKER_EDIT_REASON_EXPLAIN'] : ((isset($user->lang['TRACKER_EDIT_REASON_EXPLAIN'])) ? $user->lang['TRACKER_EDIT_REASON_EXPLAIN'] : '{ TRACKER_EDIT_REASON_EXPLAIN }')); ?></span></dt>
					<dd><input type="text" id="edit_reason" name="edit_reason" value="<?php echo (isset($this->_rootref['EDIT_REASON_TEXT'])) ? $this->_rootref['EDIT_REASON_TEXT'] : ''; ?>" class="inputbox medium" /></dd>
				</dl>
				<?php } $this->_tpl_include('posting_buttons.html'); ?>

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
					<textarea name="message" id="message" rows="15" cols="76" tabindex="4" onselect="storeCaret(this);" onclick="storeCaret(this);" onkeyup="storeCaret(this);" onfocus="initInsertions();" class="inputbox"><?php echo (isset($this->_rootref['REPLY_DESC'])) ? $this->_rootref['REPLY_DESC'] : ''; ?></textarea>
				</div>

			</fieldset>
			<span class="corners-bottom"><span></span></span>
		</div>

		<?php if ($this->_rootref['S_CAN_ATTACH']) {  $this->_tpl_include('posting_attach_body.html'); } if ($this->_rootref['S_HAS_ATTACHMENTS']) {  ?>

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
		<?php } if ($this->_rootref['S_MANAGE_TICKET']) {  ?>

		<div class="panel bg2">
			<div class="inner"><span class="corners-top"><span></span></span>
			<h3><?php echo ((isset($this->_rootref['L_OPTIONS'])) ? $this->_rootref['L_OPTIONS'] : ((isset($user->lang['OPTIONS'])) ? $user->lang['OPTIONS'] : '{ OPTIONS }')); ?></h3>
			<fieldset class="fields2">
			<?php if ($this->_rootref['S_MANAGE_TICKET'] && $this->_rootref['S_ASSIGN_USER_OPTIONS']) {  ?>

				<dl>
					<dt><label for="au"><strong><?php echo ((isset($this->_rootref['L_TRACKER_ASSIGN_USER'])) ? $this->_rootref['L_TRACKER_ASSIGN_USER'] : ((isset($user->lang['TRACKER_ASSIGN_USER'])) ? $user->lang['TRACKER_ASSIGN_USER'] : '{ TRACKER_ASSIGN_USER }')); ?>:</strong></label></dt>
					<dd><select id="au" name="au"><?php echo (isset($this->_rootref['S_ASSIGN_USER_OPTIONS'])) ? $this->_rootref['S_ASSIGN_USER_OPTIONS'] : ''; ?></select></dd>
				</dl>
				<?php } if ($this->_rootref['S_MANAGE_TICKET'] && $this->_rootref['S_STATUS_OPTIONS']) {  ?>

				<dl>
					<dt><label for="cs"><strong><?php echo ((isset($this->_rootref['L_TRACKER_CHANGE_STATUS'])) ? $this->_rootref['L_TRACKER_CHANGE_STATUS'] : ((isset($user->lang['TRACKER_CHANGE_STATUS'])) ? $user->lang['TRACKER_CHANGE_STATUS'] : '{ TRACKER_CHANGE_STATUS }')); ?>:</strong></label></dt>
					<dd><select id="cs" name="cs"><?php echo (isset($this->_rootref['S_STATUS_OPTIONS'])) ? $this->_rootref['S_STATUS_OPTIONS'] : ''; ?></select></dd>
				</dl>
				<?php } if ($this->_rootref['S_MANAGE_TICKET'] && $this->_rootref['S_PRIORITY_OPTIONS']) {  ?>

				<dl>
					<dt><label for="pr"><strong><?php echo ((isset($this->_rootref['L_TRACKER_CHANGE_PRIORITY'])) ? $this->_rootref['L_TRACKER_CHANGE_PRIORITY'] : ((isset($user->lang['TRACKER_CHANGE_PRIORITY'])) ? $user->lang['TRACKER_CHANGE_PRIORITY'] : '{ TRACKER_CHANGE_PRIORITY }')); ?>:</strong></label></dt>
					<dd><select id="pr" name="pr"><?php echo (isset($this->_rootref['S_PRIORITY_OPTIONS'])) ? $this->_rootref['S_PRIORITY_OPTIONS'] : ''; ?></select></dd>
				</dl>
				<?php } if ($this->_rootref['S_MANAGE_TICKET'] && $this->_rootref['S_SEVERITY_OPTIONS']) {  ?>

				<dl>
					<dt><label for="s"><strong><?php echo ((isset($this->_rootref['L_TRACKER_CHANGE_SEVERITY'])) ? $this->_rootref['L_TRACKER_CHANGE_SEVERITY'] : ((isset($user->lang['TRACKER_CHANGE_SEVERITY'])) ? $user->lang['TRACKER_CHANGE_SEVERITY'] : '{ TRACKER_CHANGE_SEVERITY }')); ?>:</strong></label></dt>
					<dd><select id="s" name="s"><?php echo (isset($this->_rootref['S_SEVERITY_OPTIONS'])) ? $this->_rootref['S_SEVERITY_OPTIONS'] : ''; ?></select></dd>
				</dl>
				<?php } if ($this->_rootref['S_TICKET_SECURITY']) {  ?>

				<dl>
					<dt><label for="ticket_security"><?php echo ((isset($this->_rootref['L_TRACKER_SECURITY_TICKET'])) ? $this->_rootref['L_TRACKER_SECURITY_TICKET'] : ((isset($user->lang['TRACKER_SECURITY_TICKET'])) ? $user->lang['TRACKER_SECURITY_TICKET'] : '{ TRACKER_SECURITY_TICKET }')); ?>:</label></dt>
					<dd><input type="checkbox" name="ticket_security" id="ticket_security" value="1"<?php if ($this->_rootref['TICKET_SECURITY']) {  ?> checked="checked"<?php } ?> /></dd>
				</dl>
				<?php } ?>

				<dl>
					<dt><label for="ticket_hidden"><?php echo ((isset($this->_rootref['L_TRACKER_HIDE_TICKET'])) ? $this->_rootref['L_TRACKER_HIDE_TICKET'] : ((isset($user->lang['TRACKER_HIDE_TICKET'])) ? $user->lang['TRACKER_HIDE_TICKET'] : '{ TRACKER_HIDE_TICKET }')); ?>:</label></dt>
					<dd><input type="checkbox" name="ticket_hidden" id="ticket_hidden" value="1"<?php if ($this->_rootref['TICKET_HIDDEN']) {  ?> checked="checked"<?php } ?> /></dd>
				</dl>
				<dl>
					<dt><label for="ticket_status"><?php echo ((isset($this->_rootref['L_TRACKER_LOCK_TICKET'])) ? $this->_rootref['L_TRACKER_LOCK_TICKET'] : ((isset($user->lang['TRACKER_LOCK_TICKET'])) ? $user->lang['TRACKER_LOCK_TICKET'] : '{ TRACKER_LOCK_TICKET }')); ?>:</label></dt>
					<dd><input type="checkbox" name="ticket_status" id="ticket_status" value="1"<?php if ($this->_rootref['S_IS_LOCKED']) {  ?> checked="checked"<?php } ?> /></dd>
				</dl>
			</fieldset>
			<span class="corners-bottom"><span></span></span></div>
		</div>
		<?php } ?>


		<div class="panel bg2">
			<span class="corners-top"><span></span></span>
			<fieldset>
				<p><?php echo (isset($this->_rootref['TRACKER_REPLY_DETAIL'])) ? $this->_rootref['TRACKER_REPLY_DETAIL'] : ''; ?></p>
				<?php if ($this->_rootref['CAPTCHA_TEMPLATE'] && $this->_rootref['S_CONFIRM_CODE']) {  $this->_tpldata['DEFINE']['.']['CAPTCHA_TAB_INDEX'] = 3; if (isset($this->_rootref['CAPTCHA_TEMPLATE'])) { $this->_tpl_include($this->_rootref['CAPTCHA_TEMPLATE']); } } ?>

				<input type="submit" name="preview" value="<?php echo ((isset($this->_rootref['L_TRACKER_PREVIEW_REPLY'])) ? $this->_rootref['L_TRACKER_PREVIEW_REPLY'] : ((isset($user->lang['TRACKER_PREVIEW_REPLY'])) ? $user->lang['TRACKER_PREVIEW_REPLY'] : '{ TRACKER_PREVIEW_REPLY }')); ?>" class="button2" /> <input type="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_TRACKER_SUBMIT_REPLY'])) ? $this->_rootref['L_TRACKER_SUBMIT_REPLY'] : ((isset($user->lang['TRACKER_SUBMIT_REPLY'])) ? $user->lang['TRACKER_SUBMIT_REPLY'] : '{ TRACKER_SUBMIT_REPLY }')); ?>" class="button1" />
				<?php echo (isset($this->_rootref['S_HIDDEN_FIELDS_CONFIRM'])) ? $this->_rootref['S_HIDDEN_FIELDS_CONFIRM'] : ''; ?>

				<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

			</fieldset>
			<span class="corners-bottom"><span></span></span>
		</div>
	</form>

	<?php if ($this->_rootref['S_DISPLAY_REVIEW']) {  ?>

	<h3><?php echo ((isset($this->_rootref['L_TRACKER_PREVIOUS_POSTS'])) ? $this->_rootref['L_TRACKER_PREVIOUS_POSTS'] : ((isset($user->lang['TRACKER_PREVIOUS_POSTS'])) ? $user->lang['TRACKER_PREVIOUS_POSTS'] : '{ TRACKER_PREVIOUS_POSTS }')); ?></h3>
		<div id="topicreview">
			<div class="post bg2">
				<div class="inner"><span class="corners-top"><span></span></span>
				<?php $_review_count = (isset($this->_tpldata['review'])) ? sizeof($this->_tpldata['review']) : 0;if ($_review_count) {for ($_review_i = 0; $_review_i < $_review_count; ++$_review_i){$_review_val = &$this->_tpldata['review'][$_review_i]; ?>

				<div class="postbody">
					<p class="author<?php if ($_review_val['S_LAST_ROW']) {  ?> current<?php } ?>"><?php echo (isset($this->_rootref['POST_IMG'])) ? $this->_rootref['POST_IMG'] : ''; ?> <strong><?php echo $_review_val['POST_USER']; ?></strong></p>
					<div class="content<?php if ($_review_val['S_LAST_ROW']) {  ?> current<?php } ?>"><?php echo $_review_val['POST_TEXT']; ?></div>
				</div>
				<?php if (! $_review_val['S_LAST_ROW']) {  ?><hr /><?php } }} ?>

				<span class="corners-bottom"><span></span></span></div>
			</div>
		</div>
	<?php } } ?>

</div>
<div id="col2">
	<div class="post bg2">
		<div class="inner"><span class="corners-top"><span></span></span>
		<h3 style="border-bottom: none; margin-top: 5px;"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_DETAILS'])) ? $this->_rootref['L_TRACKER_TICKET_DETAILS'] : ((isset($user->lang['TRACKER_TICKET_DETAILS'])) ? $user->lang['TRACKER_TICKET_DETAILS'] : '{ TRACKER_TICKET_DETAILS }')); ?></h3>
			<ul class="menu">
				<li><strong><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_ID'])) ? $this->_rootref['L_TRACKER_TICKET_ID'] : ((isset($user->lang['TRACKER_TICKET_ID'])) ? $user->lang['TRACKER_TICKET_ID'] : '{ TRACKER_TICKET_ID }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_ID'])) ? $this->_rootref['TICKET_ID'] : ''; ?></li>
				<li><strong><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_NAME'])) ? $this->_rootref['L_TRACKER_PROJECT_NAME'] : ((isset($user->lang['TRACKER_PROJECT_NAME'])) ? $user->lang['TRACKER_PROJECT_NAME'] : '{ TRACKER_PROJECT_NAME }')); ?>:</strong> <?php echo (isset($this->_rootref['PROJECT_NAME'])) ? $this->_rootref['PROJECT_NAME'] : ''; ?></li>
				<li><strong><?php echo ((isset($this->_rootref['L_TRACKER_STATUS'])) ? $this->_rootref['L_TRACKER_STATUS'] : ((isset($user->lang['TRACKER_STATUS'])) ? $user->lang['TRACKER_STATUS'] : '{ TRACKER_STATUS }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_STATUS_DETAILS'])) ? $this->_rootref['TICKET_STATUS_DETAILS'] : ''; ?></li>
				<?php if ($this->_rootref['S_TICKET_COMPONENT']) {  ?><li><strong><?php echo ((isset($this->_rootref['L_TRACKER_COMPONENT'])) ? $this->_rootref['L_TRACKER_COMPONENT'] : ((isset($user->lang['TRACKER_COMPONENT'])) ? $user->lang['TRACKER_COMPONENT'] : '{ TRACKER_COMPONENT }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_COMPONENT'])) ? $this->_rootref['TICKET_COMPONENT'] : ''; ?></li><?php } if ($this->_rootref['S_TICKET_VERSION']) {  ?><li><strong><?php echo ((isset($this->_rootref['L_TRACKER_DETAILS_VERSION'])) ? $this->_rootref['L_TRACKER_DETAILS_VERSION'] : ((isset($user->lang['TRACKER_DETAILS_VERSION'])) ? $user->lang['TRACKER_DETAILS_VERSION'] : '{ TRACKER_DETAILS_VERSION }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_VERSION'])) ? $this->_rootref['TICKET_VERSION'] : ''; ?></li><?php } if ($this->_rootref['S_TICKET_PRIORITY']) {  ?><li><strong><?php echo ((isset($this->_rootref['L_TRACKER_PRIORITY'])) ? $this->_rootref['L_TRACKER_PRIORITY'] : ((isset($user->lang['TRACKER_PRIORITY'])) ? $user->lang['TRACKER_PRIORITY'] : '{ TRACKER_PRIORITY }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_PRIORITY'])) ? $this->_rootref['TICKET_PRIORITY'] : ''; ?></li><?php } if ($this->_rootref['S_TICKET_SEVERITY']) {  ?><li><strong><?php echo ((isset($this->_rootref['L_TRACKER_SEVERITY'])) ? $this->_rootref['L_TRACKER_SEVERITY'] : ((isset($user->lang['TRACKER_SEVERITY'])) ? $user->lang['TRACKER_SEVERITY'] : '{ TRACKER_SEVERITY }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_SEVERITY'])) ? $this->_rootref['TICKET_SEVERITY'] : ''; ?></li><?php } if ($this->_rootref['S_TICKET_ENVIRONMENT']) {  if ($this->_rootref['S_SHOW_PHP']) {  ?><li><strong><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_PHP_DETAIL'])) ? $this->_rootref['L_TRACKER_TICKET_PHP_DETAIL'] : ((isset($user->lang['TRACKER_TICKET_PHP_DETAIL'])) ? $user->lang['TRACKER_TICKET_PHP_DETAIL'] : '{ TRACKER_TICKET_PHP_DETAIL }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_PHP'])) ? $this->_rootref['TICKET_PHP'] : ''; ?></li><?php } if ($this->_rootref['S_SHOW_DBMS']) {  ?><li><strong><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_DBMS_DETAIL'])) ? $this->_rootref['L_TRACKER_TICKET_DBMS_DETAIL'] : ((isset($user->lang['TRACKER_TICKET_DBMS_DETAIL'])) ? $user->lang['TRACKER_TICKET_DBMS_DETAIL'] : '{ TRACKER_TICKET_DBMS_DETAIL }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_DBMS'])) ? $this->_rootref['TICKET_DBMS'] : ''; ?></li><?php } } ?>

				<li><strong><?php echo ((isset($this->_rootref['L_TRACKER_ASSIGNED_TO'])) ? $this->_rootref['L_TRACKER_ASSIGNED_TO'] : ((isset($user->lang['TRACKER_ASSIGNED_TO'])) ? $user->lang['TRACKER_ASSIGNED_TO'] : '{ TRACKER_ASSIGNED_TO }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_ASSIGNED_TO'])) ? $this->_rootref['TICKET_ASSIGNED_TO'] : ''; ?></li>
				<li><strong><?php echo ((isset($this->_rootref['L_TRACKER_REPORTED_BY'])) ? $this->_rootref['L_TRACKER_REPORTED_BY'] : ((isset($user->lang['TRACKER_REPORTED_BY'])) ? $user->lang['TRACKER_REPORTED_BY'] : '{ TRACKER_REPORTED_BY }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_REPORTED_BY'])) ? $this->_rootref['TICKET_REPORTED_BY'] : ''; ?> <?php if ($this->_rootref['S_SEND_PM']) {  ?>(<a href="<?php echo (isset($this->_rootref['U_SEND_PM'])) ? $this->_rootref['U_SEND_PM'] : ''; ?>"><?php echo ((isset($this->_rootref['L_TRACKER_SEND_PM'])) ? $this->_rootref['L_TRACKER_SEND_PM'] : ((isset($user->lang['TRACKER_SEND_PM'])) ? $user->lang['TRACKER_SEND_PM'] : '{ TRACKER_SEND_PM }')); ?></a>)<?php } ?></li>
				<li><strong><?php echo ((isset($this->_rootref['L_TRACKER_REPORTERS_TICKETS'])) ? $this->_rootref['L_TRACKER_REPORTERS_TICKETS'] : ((isset($user->lang['TRACKER_REPORTERS_TICKETS'])) ? $user->lang['TRACKER_REPORTERS_TICKETS'] : '{ TRACKER_REPORTERS_TICKETS }')); ?>:</strong> <a href="<?php echo (isset($this->_rootref['U_REPORTERS_TICKETS'])) ? $this->_rootref['U_REPORTERS_TICKETS'] : ''; ?>"><?php echo ((isset($this->_rootref['L_TRACKER_LIST_ALL_TICKETS'])) ? $this->_rootref['L_TRACKER_LIST_ALL_TICKETS'] : ((isset($user->lang['TRACKER_LIST_ALL_TICKETS'])) ? $user->lang['TRACKER_LIST_ALL_TICKETS'] : '{ TRACKER_LIST_ALL_TICKETS }')); ?></a></li>
				<li<?php if (! $this->_rootref['TICKET_LAST_VISIT']) {  ?> class="last"<?php } ?>><strong><?php echo ((isset($this->_rootref['L_TRACKER_REPORTED_ON'])) ? $this->_rootref['L_TRACKER_REPORTED_ON'] : ((isset($user->lang['TRACKER_REPORTED_ON'])) ? $user->lang['TRACKER_REPORTED_ON'] : '{ TRACKER_REPORTED_ON }')); ?>:</strong> <?php echo (isset($this->_rootref['TICKET_TIME'])) ? $this->_rootref['TICKET_TIME'] : ''; ?></li>
				<?php if ($this->_rootref['TICKET_LAST_VISIT']) {  ?><li class="last"><?php echo (isset($this->_rootref['TICKET_LAST_VISIT'])) ? $this->_rootref['TICKET_LAST_VISIT'] : ''; ?></li><?php } ?>

			</ul>
		<span class="corners-bottom"><span></span></span></div>
	</div>

	<?php if ($this->_rootref['S_MANAGE_TICKET']) {  ?><form id="assign_user_form" action="<?php echo (isset($this->_rootref['U_UPDATE_ACTION'])) ? $this->_rootref['U_UPDATE_ACTION'] : ''; ?>" method="post"><?php } ?>

	<div class="post bg2">
		<div class="inner"><span class="corners-top"><span></span></span>
		<h3 style="border-bottom: none; margin-top: 5px;"><?php echo ((isset($this->_rootref['L_OPTIONS'])) ? $this->_rootref['L_OPTIONS'] : ((isset($user->lang['OPTIONS'])) ? $user->lang['OPTIONS'] : '{ OPTIONS }')); ?></h3>
		<ul class="menu">
				<li<?php if (! $this->_rootref['S_MANAGE_TICKET'] || $this->_rootref['S_TICKET_REPLY'] && ( ! $this->_rootref['S_USER_LOGGED_IN'] && $this->_rootref['S_IS_BOT'] )) {  ?> class="last"<?php } ?>><a href="<?php echo (isset($this->_rootref['U_VIEW_TICKET_HISTORY'])) ? $this->_rootref['U_VIEW_TICKET_HISTORY'] : ''; ?>"><?php echo ((isset($this->_rootref['L_TICKET_HISTORY'])) ? $this->_rootref['L_TICKET_HISTORY'] : ((isset($user->lang['TICKET_HISTORY'])) ? $user->lang['TICKET_HISTORY'] : '{ TICKET_HISTORY }')); ?></a></li>
				<?php if ($this->_rootref['S_USER_LOGGED_IN'] && ! $this->_rootref['S_IS_BOT']) {  ?>

					<li<?php if (! $this->_rootref['S_MANAGE_TICKET'] || $this->_rootref['S_TICKET_REPLY']) {  ?> class="last"<?php } ?>><a href="<?php echo (isset($this->_rootref['U_WATCH_TICKET'])) ? $this->_rootref['U_WATCH_TICKET'] : ''; ?>"><?php echo ((isset($this->_rootref['L_WATCH_TICKET'])) ? $this->_rootref['L_WATCH_TICKET'] : ((isset($user->lang['WATCH_TICKET'])) ? $user->lang['WATCH_TICKET'] : '{ WATCH_TICKET }')); ?></a></li>
				<?php } if (! $this->_rootref['S_TICKET_REPLY']) {  if ($this->_rootref['S_MANAGE_TICKET'] && $this->_rootref['S_ASSIGN_USER_OPTIONS']) {  ?><li><label for="au"><strong><?php echo ((isset($this->_rootref['L_TRACKER_ASSIGN_USER'])) ? $this->_rootref['L_TRACKER_ASSIGN_USER'] : ((isset($user->lang['TRACKER_ASSIGN_USER'])) ? $user->lang['TRACKER_ASSIGN_USER'] : '{ TRACKER_ASSIGN_USER }')); ?>:</strong></label><select id="au" name="au" style="width: 150px;"><?php echo (isset($this->_rootref['S_ASSIGN_USER_OPTIONS'])) ? $this->_rootref['S_ASSIGN_USER_OPTIONS'] : ''; ?></select></li><?php } if ($this->_rootref['S_MANAGE_TICKET'] && $this->_rootref['S_STATUS_OPTIONS']) {  ?><li><label for="cs"><strong><?php echo ((isset($this->_rootref['L_TRACKER_CHANGE_STATUS'])) ? $this->_rootref['L_TRACKER_CHANGE_STATUS'] : ((isset($user->lang['TRACKER_CHANGE_STATUS'])) ? $user->lang['TRACKER_CHANGE_STATUS'] : '{ TRACKER_CHANGE_STATUS }')); ?>:</strong></label><select id="cs" name="cs" style="width: 125px;"><?php echo (isset($this->_rootref['S_STATUS_OPTIONS'])) ? $this->_rootref['S_STATUS_OPTIONS'] : ''; ?></select></li><?php } if ($this->_rootref['S_MANAGE_TICKET'] && $this->_rootref['S_PRIORITY_OPTIONS']) {  ?><li><label for="pr"><strong><?php echo ((isset($this->_rootref['L_TRACKER_CHANGE_PRIORITY'])) ? $this->_rootref['L_TRACKER_CHANGE_PRIORITY'] : ((isset($user->lang['TRACKER_CHANGE_PRIORITY'])) ? $user->lang['TRACKER_CHANGE_PRIORITY'] : '{ TRACKER_CHANGE_PRIORITY }')); ?>:</strong></label><select id="pr" name="pr" style="width: 125px;"><?php echo (isset($this->_rootref['S_PRIORITY_OPTIONS'])) ? $this->_rootref['S_PRIORITY_OPTIONS'] : ''; ?></select></li><?php } if ($this->_rootref['S_MANAGE_TICKET'] && $this->_rootref['S_SEVERITY_OPTIONS']) {  ?><li><label for="s"><strong><?php echo ((isset($this->_rootref['L_TRACKER_CHANGE_SEVERITY'])) ? $this->_rootref['L_TRACKER_CHANGE_SEVERITY'] : ((isset($user->lang['TRACKER_CHANGE_SEVERITY'])) ? $user->lang['TRACKER_CHANGE_SEVERITY'] : '{ TRACKER_CHANGE_SEVERITY }')); ?>:</strong></label><select id="s" name="s" style="width: 125px;"><?php echo (isset($this->_rootref['S_SEVERITY_OPTIONS'])) ? $this->_rootref['S_SEVERITY_OPTIONS'] : ''; ?></select></li><?php } if ($this->_rootref['S_MANAGE_TICKET']) {  ?><li class="last"><input type="submit" name="update" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" class="button2" /></li><?php } } ?>

			</ul>
		<span class="corners-bottom"><span></span></span></div>
		<?php if ($this->_rootref['S_MANAGE_TICKET']) {  echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; } ?>

	</div>
	<?php if ($this->_rootref['S_MANAGE_TICKET']) {  ?></form><?php } ?>

</div>
<div class="cleaner">&nbsp;</div>
<?php if ($this->_rootref['S_MANAGE_TICKET_MOD'] && ! $this->_rootref['S_TICKET_REPLY']) {  ?>

	<br />
	<form method="post" action="<?php echo (isset($this->_rootref['S_MOD_ACTION'])) ? $this->_rootref['S_MOD_ACTION'] : ''; ?>">
	<fieldset class="quickmod">
		<label><?php echo ((isset($this->_rootref['L_QUICK_MOD'])) ? $this->_rootref['L_QUICK_MOD'] : ((isset($user->lang['QUICK_MOD'])) ? $user->lang['QUICK_MOD'] : '{ QUICK_MOD }')); ?>: <?php echo (isset($this->_rootref['S_TICKET_MOD'])) ? $this->_rootref['S_TICKET_MOD'] : ''; ?></label> <input name="submit_mod" type="submit" value="<?php echo ((isset($this->_rootref['L_GO'])) ? $this->_rootref['L_GO'] : ((isset($user->lang['GO'])) ? $user->lang['GO'] : '{ GO }')); ?>" class="button2" />
		<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

	</fieldset>
	</form>
	<div class="cleaner">&nbsp;</div>
<?php } $this->_tpl_include('tracker/tracker_footer.html'); $this->_tpl_include('overall_footer.html'); ?>