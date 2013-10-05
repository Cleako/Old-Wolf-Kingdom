<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>

<h2><?php echo ((isset($this->_rootref['L_TITLE'])) ? $this->_rootref['L_TITLE'] : ((isset($user->lang['TITLE'])) ? $user->lang['TITLE'] : '{ TITLE }')); ?></h2>

<?php if ($this->_rootref['S_FOUND_RESULTS']) {  ?>

	<div class="rules">
		<div class="inner"><span class="corners-top"><span></span></span>
		<?php echo (isset($this->_rootref['SEARCH_MATCHES'])) ? $this->_rootref['SEARCH_MATCHES'] : ''; ?><br />
		<?php echo ((isset($this->_rootref['L_SEARCHED_FOR'])) ? $this->_rootref['L_SEARCHED_FOR'] : ((isset($user->lang['SEARCHED_FOR'])) ? $user->lang['SEARCHED_FOR'] : '{ SEARCHED_FOR }')); ?>: <strong><?php echo (isset($this->_rootref['SEARCH_TERM'])) ? $this->_rootref['SEARCH_TERM'] : ''; ?></strong>
		<span class="corners-bottom"><span></span></span></div>
	</div>
<?php } ?>


<div class="post bg2" style="text-align: center;">
	<div class="inner"><span class="corners-top"><span></span></span>
	<?php echo (isset($this->_rootref['TRACKER_CURRENTLY_SHOWING'])) ? $this->_rootref['TRACKER_CURRENTLY_SHOWING'] : ''; ?>

	<span class="corners-bottom"><span></span></span></div>
</div>
<br />

<div class="topic-actions">
	<div class="search-box">
		<form id="st_form" action="<?php echo (isset($this->_rootref['U_ACTION'])) ? $this->_rootref['U_ACTION'] : ''; ?>" method="get">
		<fieldset>
			<label for="st">
				<strong><?php echo ((isset($this->_rootref['L_TRACKER_FILTER_TICKET'])) ? $this->_rootref['L_TRACKER_FILTER_TICKET'] : ((isset($user->lang['TRACKER_FILTER_TICKET'])) ? $user->lang['TRACKER_FILTER_TICKET'] : '{ TRACKER_FILTER_TICKET }')); ?>:</strong>
			</label>
			<select id="st" name="st" onchange="document.getElementById('st_form').submit();"><?php echo (isset($this->_rootref['S_STATUS_OPTIONS'])) ? $this->_rootref['S_STATUS_OPTIONS'] : ''; ?></select>&nbsp;
			<?php if ($this->_rootref['S_VERSION_OPTIONS']) {  ?><select id="vid" name="vid" onchange="document.getElementById('st_form').submit();"><?php echo (isset($this->_rootref['S_VERSION_OPTIONS'])) ? $this->_rootref['S_VERSION_OPTIONS'] : ''; ?></select>&nbsp;<?php } if ($this->_rootref['S_COMPONENT_OPTIONS']) {  ?><select id="cid" name="cid" onchange="document.getElementById('st_form').submit();"><?php echo (isset($this->_rootref['S_COMPONENT_OPTIONS'])) ? $this->_rootref['S_COMPONENT_OPTIONS'] : ''; ?></select><?php } ?>

			<noscript><p><input type="submit" name="submit" value="<?php echo ((isset($this->_rootref['L_SUBMIT'])) ? $this->_rootref['L_SUBMIT'] : ((isset($user->lang['SUBMIT'])) ? $user->lang['SUBMIT'] : '{ SUBMIT }')); ?>" class="button2" /></p></noscript>
			<input type="hidden" name="p" value="<?php echo (isset($this->_rootref['PROJECT_ID'])) ? $this->_rootref['PROJECT_ID'] : ''; ?>" />
			<input type="hidden" name="u" value="<?php echo (isset($this->_rootref['TRACKER_USER_ID'])) ? $this->_rootref['TRACKER_USER_ID'] : ''; ?>" />
			<input type="hidden" name="at" value="<?php echo (isset($this->_rootref['TRACKER_ASSIGNED_USER_ID'])) ? $this->_rootref['TRACKER_ASSIGNED_USER_ID'] : ''; ?>" />
			<?php echo (isset($this->_rootref['S_HIDDEN_FIELDS'])) ? $this->_rootref['S_HIDDEN_FIELDS'] : ''; ?>

		</fieldset>
		</form>
	</div>
</div>

<div class="topic-actions">
	<?php if ($this->_rootref['S_USER_LOGGED_IN'] && $this->_rootref['S_CAN_POST_TRACKER'] && ! $this->_rootref['S_IS_BOT']) {  ?>

		<div class="buttons">
			<div class="issue-icon"><a href="<?php echo (isset($this->_rootref['U_POST_NEW_TICKET'])) ? $this->_rootref['U_POST_NEW_TICKET'] : ''; ?>" title="<?php echo ((isset($this->_rootref['L_TRACKER_POST_TICKET'])) ? $this->_rootref['L_TRACKER_POST_TICKET'] : ((isset($user->lang['TRACKER_POST_TICKET'])) ? $user->lang['TRACKER_POST_TICKET'] : '{ TRACKER_POST_TICKET }')); ?>"><span></span><?php echo ((isset($this->_rootref['L_TRACKER_POST_TICKET'])) ? $this->_rootref['L_TRACKER_POST_TICKET'] : ((isset($user->lang['TRACKER_POST_TICKET'])) ? $user->lang['TRACKER_POST_TICKET'] : '{ TRACKER_POST_TICKET }')); ?></a></div>
		</div>
	<?php } ?>	

	<div class="search-box">
		<form action="<?php echo (isset($this->_rootref['S_ACTION_SEARCH'])) ? $this->_rootref['S_ACTION_SEARCH'] : ''; ?>" method="post" id="search_tracker">
			<fieldset>
				<?php echo (isset($this->_rootref['S_HIDDEN_FIELDS_SEARCH'])) ? $this->_rootref['S_HIDDEN_FIELDS_SEARCH'] : ''; ?>

				<input type="text" name="term" maxlength="128" title="<?php echo ((isset($this->_rootref['L_TRACKER_SEARCH_DESCRIPTION'])) ? $this->_rootref['L_TRACKER_SEARCH_DESCRIPTION'] : ((isset($user->lang['TRACKER_SEARCH_DESCRIPTION'])) ? $user->lang['TRACKER_SEARCH_DESCRIPTION'] : '{ TRACKER_SEARCH_DESCRIPTION }')); ?>" value="<?php if ($this->_rootref['SEARCH_TERM']) {  echo (isset($this->_rootref['SEARCH_TERM'])) ? $this->_rootref['SEARCH_TERM'] : ''; } else { echo ((isset($this->_rootref['L_SEARCH_MINI'])) ? $this->_rootref['L_SEARCH_MINI'] : ((isset($user->lang['SEARCH_MINI'])) ? $user->lang['SEARCH_MINI'] : '{ SEARCH_MINI }')); } ?>" onclick="if(this.value=='<?php echo ((isset($this->_rootref['LA_SEARCH_MINI'])) ? $this->_rootref['LA_SEARCH_MINI'] : ((isset($this->_rootref['L_SEARCH_MINI'])) ? addslashes($this->_rootref['L_SEARCH_MINI']) : ((isset($user->lang['SEARCH_MINI'])) ? addslashes($user->lang['SEARCH_MINI']) : '{ SEARCH_MINI }'))); ?>')this.value='';" onblur="if(this.value=='')this.value='<?php echo ((isset($this->_rootref['LA_SEARCH_MINI'])) ? $this->_rootref['LA_SEARCH_MINI'] : ((isset($this->_rootref['L_SEARCH_MINI'])) ? addslashes($this->_rootref['L_SEARCH_MINI']) : ((isset($user->lang['SEARCH_MINI'])) ? addslashes($user->lang['SEARCH_MINI']) : '{ SEARCH_MINI }'))); ?>';" class="inputbox autowidth search" />
				<input class="button2" type="submit" value="<?php echo ((isset($this->_rootref['L_SEARCH'])) ? $this->_rootref['L_SEARCH'] : ((isset($user->lang['SEARCH'])) ? $user->lang['SEARCH'] : '{ SEARCH }')); ?>" />
			</fieldset>
		</form>
	</div>

	<div class="pagination">
		<?php if ($this->_rootref['S_CAN_MANAGE'] && $this->_rootref['U_MY_ASSIGNED_TICKETS']) {  ?><a href="<?php echo (isset($this->_rootref['U_MY_ASSIGNED_TICKETS'])) ? $this->_rootref['U_MY_ASSIGNED_TICKETS'] : ''; ?>" title="<?php echo (isset($this->_rootref['TRACKER_MY_ASSIGNED_TICKETS'])) ? $this->_rootref['TRACKER_MY_ASSIGNED_TICKETS'] : ''; ?>"><?php echo (isset($this->_rootref['TRACKER_MY_ASSIGNED_TICKETS'])) ? $this->_rootref['TRACKER_MY_ASSIGNED_TICKETS'] : ''; ?></a>&nbsp;&bull;&nbsp;<?php } ?><a href="<?php echo (isset($this->_rootref['U_MY_TICKETS'])) ? $this->_rootref['U_MY_TICKETS'] : ''; ?>" title="<?php echo (isset($this->_rootref['TRACKER_MY_TICKETS'])) ? $this->_rootref['TRACKER_MY_TICKETS'] : ''; ?>"><?php echo (isset($this->_rootref['TRACKER_MY_TICKETS'])) ? $this->_rootref['TRACKER_MY_TICKETS'] : ''; ?></a>
	</div>
</div>



<div class="forabg">
	<div class="inner"><span class="corners-top"><span></span></span>
	<table class="table1 " cellspacing="0">
		<thead>
			<tr>
				<th style="text-align: left; width: 5%;">#</th>
				<th style="text-align: left; width: 45%;"><?php echo ((isset($this->_rootref['L_TRACKER_TICKET_TITLE'])) ? $this->_rootref['L_TRACKER_TICKET_TITLE'] : ((isset($user->lang['TRACKER_TICKET_TITLE'])) ? $user->lang['TRACKER_TICKET_TITLE'] : '{ TRACKER_TICKET_TITLE }')); ?></th>
				<th style="text-align: center; width: 15%;"><?php echo ((isset($this->_rootref['L_TRACKER_COMPONENT'])) ? $this->_rootref['L_TRACKER_COMPONENT'] : ((isset($user->lang['TRACKER_COMPONENT'])) ? $user->lang['TRACKER_COMPONENT'] : '{ TRACKER_COMPONENT }')); ?></th>
				<th style="text-align: center; width: 20%;"><?php echo ((isset($this->_rootref['L_TRACKER_ASSIGNED_TO'])) ? $this->_rootref['L_TRACKER_ASSIGNED_TO'] : ((isset($user->lang['TRACKER_ASSIGNED_TO'])) ? $user->lang['TRACKER_ASSIGNED_TO'] : '{ TRACKER_ASSIGNED_TO }')); ?></th>
				<th style="text-align: center; width: 15%;"><?php echo ((isset($this->_rootref['L_TRACKER_STATUS'])) ? $this->_rootref['L_TRACKER_STATUS'] : ((isset($user->lang['TRACKER_STATUS'])) ? $user->lang['TRACKER_STATUS'] : '{ TRACKER_STATUS }')); ?></th>
			</tr>
		</thead>
		<tbody>
		<?php $_tickets_count = (isset($this->_tpldata['tickets'])) ? sizeof($this->_tpldata['tickets']) : 0;if ($_tickets_count) {for ($_tickets_i = 0; $_tickets_i < $_tickets_count; ++$_tickets_i){$_tickets_val = &$this->_tpldata['tickets'][$_tickets_i]; ?>

			<tr class="<?php if (!($_tickets_val['S_ROW_COUNT'] & 1)  ) {  ?>bg1<?php } else { ?>bg2<?php } if ($_tickets_val['TICKET_HIDDEN'] || $_tickets_val['TICKET_SECURITY']) {  ?> reported<?php } ?>">
				<td><strong><?php echo $_tickets_val['TICKET_ID']; ?></strong></td>
				<td>
					<strong><a href="<?php echo $_tickets_val['U_VIEW_TICKET']; ?>"><?php echo $_tickets_val['TICKET_TITLE']; ?></a></strong><br />
					<span class="small"><?php echo $_tickets_val['TICKET_USERNAME']; if ($_tickets_val['LAST_POST_USERNAME']) {  ?><br /><?php echo $_tickets_val['LAST_POST_USERNAME']; } ?></span>
				</td>
				<td style="text-align: center;"><?php echo $_tickets_val['TICKET_COMPONENT']; ?></td>
				<td style="text-align: center;"><?php echo $_tickets_val['TICKET_ASSIGNED_TO']; ?></td>
				<td style="text-align: center; color: red;"><?php echo $_tickets_val['TICKET_STATUS']; ?></td>
			</tr>
		<?php }} else { ?>

			<tr style="height: 50px">
				<td class="bg1" colspan="5" align="center">
					<strong><?php echo ((isset($this->_rootref['L_TRACKER_NO_TICKETS'])) ? $this->_rootref['L_TRACKER_NO_TICKETS'] : ((isset($user->lang['TRACKER_NO_TICKETS'])) ? $user->lang['TRACKER_NO_TICKETS'] : '{ TRACKER_NO_TICKETS }')); ?></strong>
				</td>
			</tr>
		<?php } ?>

		</tbody>
	</table>
	<span class="corners-bottom"><span></span></span></div>
</div>
<?php if ($this->_rootref['S_USER_LOGGED_IN'] && ! $this->_rootref['S_IS_BOT']) {  ?>

<div class="buttons">
	<a href="<?php echo (isset($this->_rootref['U_WATCH_PROJECT'])) ? $this->_rootref['U_WATCH_PROJECT'] : ''; ?>"><?php echo ((isset($this->_rootref['L_WATCH_PROJECT'])) ? $this->_rootref['L_WATCH_PROJECT'] : ((isset($user->lang['WATCH_PROJECT'])) ? $user->lang['WATCH_PROJECT'] : '{ WATCH_PROJECT }')); ?></a>
</div>
<?php } if ($this->_rootref['PAGINATION'] || $this->_rootref['TOTAL_TICKETS']) {  ?>

<div class="pagination">
	<?php echo (isset($this->_rootref['TOTAL_TICKETS'])) ? $this->_rootref['TOTAL_TICKETS'] : ''; ?>

	<?php if ($this->_rootref['PAGE_NUMBER']) {  if ($this->_rootref['PAGINATION']) {  ?> &bull; <a href="#" onclick="jumpto(); return false;" title="<?php echo ((isset($this->_rootref['L_JUMP_TO_PAGE'])) ? $this->_rootref['L_JUMP_TO_PAGE'] : ((isset($user->lang['JUMP_TO_PAGE'])) ? $user->lang['JUMP_TO_PAGE'] : '{ JUMP_TO_PAGE }')); ?>"><?php echo (isset($this->_rootref['PAGE_NUMBER'])) ? $this->_rootref['PAGE_NUMBER'] : ''; ?></a> &bull; <span><?php echo (isset($this->_rootref['PAGINATION'])) ? $this->_rootref['PAGINATION'] : ''; ?></span><?php } else { ?> &bull; <?php echo (isset($this->_rootref['PAGE_NUMBER'])) ? $this->_rootref['PAGE_NUMBER'] : ''; } } ?>

</div>
<?php } ?>


<br />

<?php if (! $this->_rootref['S_USER_LOGGED_IN'] && ! $this->_rootref['S_IS_BOT']) {  ?>

	<form method="post" action="<?php echo (isset($this->_rootref['S_LOGIN_ACTION'])) ? $this->_rootref['S_LOGIN_ACTION'] : ''; ?>" class="headerspace">
	<h3><a href="<?php echo (isset($this->_rootref['U_LOGIN_LOGOUT'])) ? $this->_rootref['U_LOGIN_LOGOUT'] : ''; ?>"><?php echo ((isset($this->_rootref['L_LOGIN_LOGOUT'])) ? $this->_rootref['L_LOGIN_LOGOUT'] : ((isset($user->lang['LOGIN_LOGOUT'])) ? $user->lang['LOGIN_LOGOUT'] : '{ LOGIN_LOGOUT }')); ?></a><?php if ($this->_rootref['S_REGISTER_ENABLED']) {  ?>&nbsp; &bull; &nbsp;<a href="<?php echo (isset($this->_rootref['U_REGISTER'])) ? $this->_rootref['U_REGISTER'] : ''; ?>"><?php echo ((isset($this->_rootref['L_REGISTER'])) ? $this->_rootref['L_REGISTER'] : ((isset($user->lang['REGISTER'])) ? $user->lang['REGISTER'] : '{ REGISTER }')); ?></a><?php } ?></h3>
		<fieldset class="quick-login">
			<label for="username"><?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>:</label>&nbsp;<input type="text" name="username" id="username" size="10" class="inputbox" title="<?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>" />
			<label for="password"><?php echo ((isset($this->_rootref['L_PASSWORD'])) ? $this->_rootref['L_PASSWORD'] : ((isset($user->lang['PASSWORD'])) ? $user->lang['PASSWORD'] : '{ PASSWORD }')); ?>:</label>&nbsp;<input type="password" name="password" id="password" size="10" class="inputbox" title="<?php echo ((isset($this->_rootref['L_PASSWORD'])) ? $this->_rootref['L_PASSWORD'] : ((isset($user->lang['PASSWORD'])) ? $user->lang['PASSWORD'] : '{ PASSWORD }')); ?>" />
			<?php if ($this->_rootref['S_AUTOLOGIN_ENABLED']) {  ?>

				| <label for="autologin"><?php echo ((isset($this->_rootref['L_LOG_ME_IN'])) ? $this->_rootref['L_LOG_ME_IN'] : ((isset($user->lang['LOG_ME_IN'])) ? $user->lang['LOG_ME_IN'] : '{ LOG_ME_IN }')); ?> <input type="checkbox" name="autologin" id="autologin" /></label>
			<?php } ?>

			<input type="submit" name="login" value="<?php echo ((isset($this->_rootref['L_LOGIN'])) ? $this->_rootref['L_LOGIN'] : ((isset($user->lang['LOGIN'])) ? $user->lang['LOGIN'] : '{ LOGIN }')); ?>" class="button2" />
			<?php echo (isset($this->_rootref['S_LOGIN_REDIRECT'])) ? $this->_rootref['S_LOGIN_REDIRECT'] : ''; ?>

		</fieldset>
	</form>
<?php } $this->_tpl_include('tracker/tracker_footer.html'); $this->_tpl_include('overall_footer.html'); ?>