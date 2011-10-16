<?php if (!defined('IN_PHPBB')) exit; if ($this->_rootref['S_IN_CHANGELOG']) {  $this->_tpl_include('simple_header.html'); if ($this->_rootref['S_HAS_CHANGELOG']) {  ?>

		<script type="text/javascript" src="<?php echo (isset($this->_rootref['T_TEMPLATE_PATH'])) ? $this->_rootref['T_TEMPLATE_PATH'] : ''; ?>/forum_fn.js"></script>

		<h2><?php echo (isset($this->_rootref['PAGE_TITLE'])) ? $this->_rootref['PAGE_TITLE'] : ''; ?></h2>
		<h3><?php echo ((isset($this->_rootref['L_TRACKER_CHANGELOG_EXAMPLE'])) ? $this->_rootref['L_TRACKER_CHANGELOG_EXAMPLE'] : ((isset($user->lang['TRACKER_CHANGELOG_EXAMPLE'])) ? $user->lang['TRACKER_CHANGELOG_EXAMPLE'] : '{ TRACKER_CHANGELOG_EXAMPLE }')); ?></h3>
		<div class="post bg1">
			<div class="inner"><span class="corners-top"><span></span></span>
				<div class="postbody" style="width: auto; float: none; margin: 0; height: auto;">
					<div class="content"><?php echo (isset($this->_rootref['OUTPUT'])) ? $this->_rootref['OUTPUT'] : ''; ?></div>
				</div>
			<span class="corners-bottom"><span></span></span></div>
		</div>

		<h3><?php echo ((isset($this->_rootref['L_TRACKER_CHANGELOG_HTML'])) ? $this->_rootref['L_TRACKER_CHANGELOG_HTML'] : ((isset($user->lang['TRACKER_CHANGELOG_HTML'])) ? $user->lang['TRACKER_CHANGELOG_HTML'] : '{ TRACKER_CHANGELOG_HTML }')); ?></h3>
		<div class="post bg1">
			<div class="inner"><span class="corners-top"><span></span></span>
				<div class="postbody" style="width: auto; float: none; margin: 0; height: auto;">
					<div class="content"><?php echo (isset($this->_rootref['HTML_CHANGELOG'])) ? $this->_rootref['HTML_CHANGELOG'] : ''; ?></div>
				</div>
			<span class="corners-bottom"><span></span></span></div>
		</div>

		<h3><?php echo ((isset($this->_rootref['L_TRACKER_CHANGELOG_BBCODE'])) ? $this->_rootref['L_TRACKER_CHANGELOG_BBCODE'] : ((isset($user->lang['TRACKER_CHANGELOG_BBCODE'])) ? $user->lang['TRACKER_CHANGELOG_BBCODE'] : '{ TRACKER_CHANGELOG_BBCODE }')); ?></h3>
		<div class="post bg1">
			<div class="inner"><span class="corners-top"><span></span></span>
				<div class="postbody" style="width: auto; float: none; margin: 0; height: auto;">
					<div class="content"><?php echo (isset($this->_rootref['BBCODE_CHANGELOG'])) ? $this->_rootref['BBCODE_CHANGELOG'] : ''; ?></div>
				</div>
			<span class="corners-bottom"><span></span></span></div>
		</div>
	<?php } else { ?>

		<h3><?php echo (isset($this->_rootref['PAGE_TITLE'])) ? $this->_rootref['PAGE_TITLE'] : ''; ?></h3>
		<div class="post bg1">
			<div class="inner"><span class="corners-top"><span></span></span>
				<div class="postbody" style="width: auto; float: none; margin: 0; height: auto; ">
					<div class="content" style="text-align: center;"><?php echo ((isset($this->_rootref['L_TRACKER_NO_CHANGELOG'])) ? $this->_rootref['L_TRACKER_NO_CHANGELOG'] : ((isset($user->lang['TRACKER_NO_CHANGELOG'])) ? $user->lang['TRACKER_NO_CHANGELOG'] : '{ TRACKER_NO_CHANGELOG }')); ?></div>
				</div>
			<span class="corners-bottom"><span></span></span></div>
		</div>
	<?php } ?>

<br /><br /><div class="genmed" style="text-align: center;"><a href="#" onclick="window.close(); return false;"><?php echo ((isset($this->_rootref['L_CLOSE_WINDOW'])) ? $this->_rootref['L_CLOSE_WINDOW'] : ((isset($user->lang['CLOSE_WINDOW'])) ? $user->lang['CLOSE_WINDOW'] : '{ CLOSE_WINDOW }')); ?></a></div><br /><br />
<?php $this->_tpl_include('tracker/tracker_footer.html'); $this->_tpl_include('simple_footer.html'); } else { $this->_tpl_include('overall_header.html'); if ($this->_rootref['S_IN_PROJECT_STATS']) {  ?>

	<h2><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></h2>

	<div class="forabg">
		<div class="inner"><span class="corners-top"><span></span></span>
			<table class="table1" cellspacing="0">
			<thead>
				<tr>
					<th style="text-align: left; width: 34%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_TICKETS'])) ? $user->lang['TRACKER_TOTAL_TICKETS'] : '{ TRACKER_TOTAL_TICKETS }')); ?></th>
					<th style="text-align: left; width: 33%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_OPEN_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_OPEN_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_OPEN_TICKETS'])) ? $user->lang['TRACKER_TOTAL_OPEN_TICKETS'] : '{ TRACKER_TOTAL_OPEN_TICKETS }')); ?></th>
					<th style="text-align: left; width: 33%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_CLOSED_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_CLOSED_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_CLOSED_TICKETS'])) ? $user->lang['TRACKER_TOTAL_CLOSED_TICKETS'] : '{ TRACKER_TOTAL_CLOSED_TICKETS }')); ?></th>
				</tr>
			</thead>
			<tbody>
				<tr class="bg1">
					<td><?php if ($this->_rootref['TOTAL_TICKETS']) {  ?><a href="<?php echo (isset($this->_rootref['U_TOTAL_TICKETS'])) ? $this->_rootref['U_TOTAL_TICKETS'] : ''; ?>"><?php } echo (isset($this->_rootref['TOTAL_TICKETS'])) ? $this->_rootref['TOTAL_TICKETS'] : ''; if ($this->_rootref['TOTAL_TICKETS']) {  ?></a><?php } ?></td>
					<td style="color: red;"><?php if ($this->_rootref['TOTAL_OPENED']) {  ?><a href="<?php echo (isset($this->_rootref['U_TOTAL_OPENED'])) ? $this->_rootref['U_TOTAL_OPENED'] : ''; ?>"><?php } echo (isset($this->_rootref['TOTAL_OPENED'])) ? $this->_rootref['TOTAL_OPENED'] : ''; if ($this->_rootref['TOTAL_OPENED']) {  ?></a><?php } ?></td>
					<td><?php if ($this->_rootref['TOTAL_CLOSED']) {  ?><a href="<?php echo (isset($this->_rootref['U_TOTAL_CLOSED'])) ? $this->_rootref['U_TOTAL_CLOSED'] : ''; ?>"><?php } echo (isset($this->_rootref['TOTAL_CLOSED'])) ? $this->_rootref['TOTAL_CLOSED'] : ''; if ($this->_rootref['TOTAL_CLOSED']) {  ?></a><?php } ?></td>
				</tr>
			</tbody>
		</table>
		<span class="corners-bottom"><span></span></span></div>
	</div>

	<h3><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_STATUS_OVERVIEW'])) ? $this->_rootref['L_TRACKER_TICKET_STATUS_OVERVIEW'] : ((isset($user->lang['TRACKER_TICKET_STATUS_OVERVIEW'])) ? $user->lang['TRACKER_TICKET_STATUS_OVERVIEW'] : '{ TRACKER_TICKET_STATUS_OVERVIEW }')); ?></h3>
	<div class="forabg">
		<div class="inner"><span class="corners-top"><span></span></span>
			<table class="table1" cellspacing="0">
			<thead>
				<tr>
					<th style="text-align: left; width: 34%;"><?php echo ((isset($this->_rootref['L_TRACKER_STATUS_NAME'])) ? $this->_rootref['L_TRACKER_STATUS_NAME'] : ((isset($user->lang['TRACKER_STATUS_NAME'])) ? $user->lang['TRACKER_STATUS_NAME'] : '{ TRACKER_STATUS_NAME }')); ?></th>
					<th style="text-align: left; width: 33%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_TICKETS'])) ? $user->lang['TRACKER_TOTAL_TICKETS'] : '{ TRACKER_TOTAL_TICKETS }')); ?></th>
					<th style="text-align: left; width: 33%;"><?php echo ((isset($this->_rootref['L_TRACKER_IS_CLOSED_STATUS'])) ? $this->_rootref['L_TRACKER_IS_CLOSED_STATUS'] : ((isset($user->lang['TRACKER_IS_CLOSED_STATUS'])) ? $user->lang['TRACKER_IS_CLOSED_STATUS'] : '{ TRACKER_IS_CLOSED_STATUS }')); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php $_status_count = (isset($this->_tpldata['status'])) ? sizeof($this->_tpldata['status']) : 0;if ($_status_count) {for ($_status_i = 0; $_status_i < $_status_count; ++$_status_i){$_status_val = &$this->_tpldata['status'][$_status_i]; if (!($_status_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="bg1"><?php } else { ?><tr class="bg2"><?php } ?>

						<td><strong><?php echo $_status_val['STATUS_NAME']; ?></strong></td>
						<td><?php if ($_status_val['STATUS_TOTAL']) {  ?><a href="<?php echo $_status_val['U_STATUS_TOTAL']; ?>"><?php } echo $_status_val['STATUS_TOTAL']; if ($_status_val['STATUS_TOTAL']) {  ?></a><?php } ?></td>
						<td><?php echo $_status_val['STATUS_CLOSED']; ?></td>
					</tr>
				<?php }} else { ?>

				<tr style="height: 50px">
					<td class="bg1" colspan="3" align="center">
						<strong><?php echo ((isset($this->_rootref['L_TRACKER_NO_STATUS_EXIST'])) ? $this->_rootref['L_TRACKER_NO_STATUS_EXIST'] : ((isset($user->lang['TRACKER_NO_STATUS_EXIST'])) ? $user->lang['TRACKER_NO_STATUS_EXIST'] : '{ TRACKER_NO_STATUS_EXIST }')); ?></strong>
					</td>
				</tr>
				<?php } ?>

			</tbody>
		</table>
		<span class="corners-bottom"><span></span></span></div>
	</div>

	<?php if (sizeof($this->_tpldata['assignee'])) {  ?>

	<h3><?php echo ((isset($this->_rootref['L_TRACKER_ASSIGNEES'])) ? $this->_rootref['L_TRACKER_ASSIGNEES'] : ((isset($user->lang['TRACKER_ASSIGNEES'])) ? $user->lang['TRACKER_ASSIGNEES'] : '{ TRACKER_ASSIGNEES }')); ?></h3>
	<div class="forabg">
		<div class="inner"><span class="corners-top"><span></span></span>
			<table class="table1" cellspacing="0">
			<thead>
				<tr>
					<th style="text-align: left; width: 50%;"><?php echo ((isset($this->_rootref['L_TRACKER_USERNAME'])) ? $this->_rootref['L_TRACKER_USERNAME'] : ((isset($user->lang['TRACKER_USERNAME'])) ? $user->lang['TRACKER_USERNAME'] : '{ TRACKER_USERNAME }')); ?></th>
					<th style="text-align: left; width: 50%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_TICKETS'])) ? $user->lang['TRACKER_TOTAL_TICKETS'] : '{ TRACKER_TOTAL_TICKETS }')); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php $_assignee_count = (isset($this->_tpldata['assignee'])) ? sizeof($this->_tpldata['assignee']) : 0;if ($_assignee_count) {for ($_assignee_i = 0; $_assignee_i < $_assignee_count; ++$_assignee_i){$_assignee_val = &$this->_tpldata['assignee'][$_assignee_i]; if (!($_assignee_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="bg1"><?php } else { ?><tr class="bg2"><?php } ?>

						<td><strong><?php echo $_assignee_val['USERNAME']; ?></strong></td>
						<td><a href="<?php echo $_assignee_val['U_TOTAL']; ?>"><?php echo $_assignee_val['TOTAL']; ?></a></td>
					</tr>
				<?php }} ?>

			</tbody>
		</table>
		<span class="corners-bottom"><span></span></span></div>
	</div>
	<?php } if (sizeof($this->_tpldata['top'])) {  ?>

	<h3><?php echo (isset($this->_rootref['TRACKER_TOP_REPORTERS'])) ? $this->_rootref['TRACKER_TOP_REPORTERS'] : ''; ?></h3>
	<div class="forabg">
		<div class="inner"><span class="corners-top"><span></span></span>
			<table class="table1" cellspacing="0">
			<thead>
				<tr>
					<th style="text-align: left; width: 50%;"><?php echo ((isset($this->_rootref['L_TRACKER_USERNAME'])) ? $this->_rootref['L_TRACKER_USERNAME'] : ((isset($user->lang['TRACKER_USERNAME'])) ? $user->lang['TRACKER_USERNAME'] : '{ TRACKER_USERNAME }')); ?></th>
					<th style="text-align: left; width: 50%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_TICKETS'])) ? $user->lang['TRACKER_TOTAL_TICKETS'] : '{ TRACKER_TOTAL_TICKETS }')); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php $_top_count = (isset($this->_tpldata['top'])) ? sizeof($this->_tpldata['top']) : 0;if ($_top_count) {for ($_top_i = 0; $_top_i < $_top_count; ++$_top_i){$_top_val = &$this->_tpldata['top'][$_top_i]; if (!($_top_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="bg1"><?php } else { ?><tr class="bg2"><?php } ?>

						<td><strong><?php echo $_top_val['USERNAME']; ?></strong></td>
						<td><a href="<?php echo $_top_val['U_TOTAL']; ?>"><?php echo $_top_val['TOTAL']; ?></a></td>
					</tr>
				<?php }} ?>

			</tbody>
		</table>
		<span class="corners-bottom"><span></span></span></div>
	</div>
	<?php } if (sizeof($this->_tpldata['component'])) {  ?>

	<h3><?php echo ((isset($this->_rootref['L_TRACKER_COMPONENT_STATS'])) ? $this->_rootref['L_TRACKER_COMPONENT_STATS'] : ((isset($user->lang['TRACKER_COMPONENT_STATS'])) ? $user->lang['TRACKER_COMPONENT_STATS'] : '{ TRACKER_COMPONENT_STATS }')); ?></h3>
	<div class="forabg">
		<div class="inner"><span class="corners-top"><span></span></span>
			<table class="table1" cellspacing="0">
			<thead>
				<tr>
					<th style="text-align: left; width: 50%;"><?php echo ((isset($this->_rootref['L_TRACKER_COMPONENT_NAME'])) ? $this->_rootref['L_TRACKER_COMPONENT_NAME'] : ((isset($user->lang['TRACKER_COMPONENT_NAME'])) ? $user->lang['TRACKER_COMPONENT_NAME'] : '{ TRACKER_COMPONENT_NAME }')); ?></th>
					<th style="text-align: left; width: 50%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_TICKETS'])) ? $user->lang['TRACKER_TOTAL_TICKETS'] : '{ TRACKER_TOTAL_TICKETS }')); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php $_component_count = (isset($this->_tpldata['component'])) ? sizeof($this->_tpldata['component']) : 0;if ($_component_count) {for ($_component_i = 0; $_component_i < $_component_count; ++$_component_i){$_component_val = &$this->_tpldata['component'][$_component_i]; if (!($_component_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="bg1"><?php } else { ?><tr class="bg2"><?php } ?>

						<td><strong><?php echo $_component_val['COMPONENT_NAME']; ?></strong></td>
						<td><?php if ($_component_val['TOTAL']) {  ?><a href="<?php echo $_component_val['U_TOTAL']; ?>"><?php } echo $_component_val['TOTAL']; if ($_component_val['TOTAL']) {  ?></a><?php } ?></td>
					</tr>
				<?php }} ?>

			</tbody>
		</table>
		<span class="corners-bottom"><span></span></span></div>
	</div>
	<?php } if (sizeof($this->_tpldata['version'])) {  ?>

	<h3><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_STATS'])) ? $this->_rootref['L_TRACKER_VERSION_STATS'] : ((isset($user->lang['TRACKER_VERSION_STATS'])) ? $user->lang['TRACKER_VERSION_STATS'] : '{ TRACKER_VERSION_STATS }')); ?></h3>
	<div class="forabg">
		<div class="inner"><span class="corners-top"><span></span></span>
			<table class="table1" cellspacing="0">
			<thead>
				<tr>
					<th style="text-align: left; width: 50%;"><?php echo ((isset($this->_rootref['L_TRACKER_VERSION_NAME'])) ? $this->_rootref['L_TRACKER_VERSION_NAME'] : ((isset($user->lang['TRACKER_VERSION_NAME'])) ? $user->lang['TRACKER_VERSION_NAME'] : '{ TRACKER_VERSION_NAME }')); ?></th>
					<th style="text-align: left; width: 50%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_TICKETS'])) ? $user->lang['TRACKER_TOTAL_TICKETS'] : '{ TRACKER_TOTAL_TICKETS }')); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php $_version_count = (isset($this->_tpldata['version'])) ? sizeof($this->_tpldata['version']) : 0;if ($_version_count) {for ($_version_i = 0; $_version_i < $_version_count; ++$_version_i){$_version_val = &$this->_tpldata['version'][$_version_i]; if (!($_version_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="bg1"><?php } else { ?><tr class="bg2"><?php } ?>

						<td><strong><?php if ($_version_val['TOTAL']) {  ?><a href="#" onclick="popup('<?php echo $_version_val['U_VIEW_CHANGELOG']; ?>', 700, 500, '');return false;" title="<?php echo ((isset($this->_rootref['L_TRACKER_VERSION_VIEW_CHANGELOG'])) ? $this->_rootref['L_TRACKER_VERSION_VIEW_CHANGELOG'] : ((isset($user->lang['TRACKER_VERSION_VIEW_CHANGELOG'])) ? $user->lang['TRACKER_VERSION_VIEW_CHANGELOG'] : '{ TRACKER_VERSION_VIEW_CHANGELOG }')); ?>"><?php } echo $_version_val['VERSION_NAME']; if ($_version_val['TOTAL']) {  ?></a><?php } ?></strong></td>
						<td><?php if ($_version_val['TOTAL']) {  ?><a href="<?php echo $_version_val['U_TOTAL']; ?>"><?php } echo $_version_val['TOTAL']; if ($_version_val['TOTAL']) {  ?></a><?php } ?></td>
					</tr>
				<?php }} ?>

			</tbody>
		</table>
		<span class="corners-bottom"><span></span></span></div>
	</div>
	<?php } } else { ?>

	<h2><?php echo ((isset($this->_rootref['L_TRACKER'])) ? $this->_rootref['L_TRACKER'] : ((isset($user->lang['TRACKER'])) ? $user->lang['TRACKER'] : '{ TRACKER }')); ?></h2>
	<p><?php echo ((isset($this->_rootref['L_TRACKER_STATISTICS'])) ? $this->_rootref['L_TRACKER_STATISTICS'] : ((isset($user->lang['TRACKER_STATISTICS'])) ? $user->lang['TRACKER_STATISTICS'] : '{ TRACKER_STATISTICS }')); ?></p>
		<?php $_cat_count = (isset($this->_tpldata['cat'])) ? sizeof($this->_tpldata['cat']) : 0;if ($_cat_count) {for ($_cat_i = 0; $_cat_i < $_cat_count; ++$_cat_i){$_cat_val = &$this->_tpldata['cat'][$_cat_i]; ?>

		<div class="forabg">
			<div class="inner"><span class="corners-top"><span></span></span>
				<table class="table1" cellspacing="0">
				<thead>
					<tr>
						<th style="text-align: left; width: 28%;"><a href="<?php echo $_cat_val['U_PROJECT']; ?>"><?php echo $_cat_val['PROJECT_NAME']; ?></a></th>
						<th style="text-align: left; width: 57%;"><?php echo ((isset($this->_rootref['L_TRACKER_DESCRIPTION'])) ? $this->_rootref['L_TRACKER_DESCRIPTION'] : ((isset($user->lang['TRACKER_DESCRIPTION'])) ? $user->lang['TRACKER_DESCRIPTION'] : '{ TRACKER_DESCRIPTION }')); ?></th>
						<th style="text-align: center; width: 15%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_TICKETS'])) ? $user->lang['TRACKER_TOTAL_TICKETS'] : '{ TRACKER_TOTAL_TICKETS }')); ?></th>
					</tr>
				</thead>
				<tbody>
					<?php $_project_count = (isset($_cat_val['project'])) ? sizeof($_cat_val['project']) : 0;if ($_project_count) {for ($_project_i = 0; $_project_i < $_project_count; ++$_project_i){$_project_val = &$_cat_val['project'][$_project_i]; if (!($_project_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="bg1"><?php } else { ?><tr class="bg2"><?php } ?>

							<td style="width: 28%;"><a href="<?php echo $_project_val['U_PROJECT']; ?>"><strong><?php echo $_project_val['PROJECT_TYPE']; ?></strong></a></td>
							<td style="width: 57%;"><?php if ($_project_val['PROJECT_DESC']) {  echo $_project_val['PROJECT_DESC']; } else { ?>&nbsp;<?php } ?></td>
							<td style="width: 15%; text-align: center;"><?php echo $_project_val['TOTAL_TICKETS']; ?></td>
						</tr>
					<?php }} ?>

				</tbody>
				</table>
			<span class="corners-bottom"><span></span></span></div>
		</div>
		<?php if (! $_cat_val['S_LAST_ROW']) {  ?><br /><?php } }} else { ?>

			<div class="forabg">
			<div class="inner"><span class="corners-top"><span></span></span>
				<table class="table1" cellspacing="0">
				<thead>
					<tr>
						<th style="text-align: left; width: 28%;"><a href="<?php echo $_cat_val['U_PROJECT']; ?>"><?php echo $_cat_val['PROJECT_NAME']; ?></a></th>
						<th style="text-align: left; width: 57%;"><?php echo ((isset($this->_rootref['L_TRACKER_DESCRIPTION'])) ? $this->_rootref['L_TRACKER_DESCRIPTION'] : ((isset($user->lang['TRACKER_DESCRIPTION'])) ? $user->lang['TRACKER_DESCRIPTION'] : '{ TRACKER_DESCRIPTION }')); ?></th>
						<th style="text-align: left; width: 15%;"><?php echo ((isset($this->_rootref['L_TRACKER_TOTAL_TICKETS'])) ? $this->_rootref['L_TRACKER_TOTAL_TICKETS'] : ((isset($user->lang['TRACKER_TOTAL_TICKETS'])) ? $user->lang['TRACKER_TOTAL_TICKETS'] : '{ TRACKER_TOTAL_TICKETS }')); ?></th>
					</tr>
				</thead>
				<tbody>
						<tr style="height: 50px">
						<td class="bg1" colspan="3" style="text-align: center;">
							<strong><?php echo ((isset($this->_rootref['L_TRACKER_NO_PROJECT_EXIST'])) ? $this->_rootref['L_TRACKER_NO_PROJECT_EXIST'] : ((isset($user->lang['TRACKER_NO_PROJECT_EXIST'])) ? $user->lang['TRACKER_NO_PROJECT_EXIST'] : '{ TRACKER_NO_PROJECT_EXIST }')); ?></strong>
						</td>
					</tr>
				</tbody>
				</table>
			<span class="corners-bottom"><span></span></span></div>
		</div>
		<?php } } ?>

	<br />
	<?php $this->_tpl_include('tracker/tracker_footer.html'); $this->_tpl_include('overall_footer.html'); } ?>