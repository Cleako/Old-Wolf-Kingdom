<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('overall_header.html'); ?>

<h2><?php echo ((isset($this->_rootref['L_TRACKER'])) ? $this->_rootref['L_TRACKER'] : ((isset($user->lang['TRACKER'])) ? $user->lang['TRACKER'] : '{ TRACKER }')); ?></h2>
<p><?php echo (isset($this->_rootref['TRACKER_PROJECTS'])) ? $this->_rootref['TRACKER_PROJECTS'] : ''; ?></p>

<?php if (! $this->_rootref['S_DISPLAY_PROJECT']) {  ?>

<div class="forabg">
	<div class="inner"><span class="corners-top"><span></span></span>
		<table class="table1" cellspacing="0">
		<thead>
			<tr>
				<th style="text-align: left; width: 30%;"><?php echo ((isset($this->_rootref['L_TRACKER_PROJECT_NAME_TITLE'])) ? $this->_rootref['L_TRACKER_PROJECT_NAME_TITLE'] : ((isset($user->lang['TRACKER_PROJECT_NAME_TITLE'])) ? $user->lang['TRACKER_PROJECT_NAME_TITLE'] : '{ TRACKER_PROJECT_NAME_TITLE }')); ?></th>
				<th style="text-align: left; width: 70%;"><?php echo ((isset($this->_rootref['L_TRACKER_DESCRIPTION'])) ? $this->_rootref['L_TRACKER_DESCRIPTION'] : ((isset($user->lang['TRACKER_DESCRIPTION'])) ? $user->lang['TRACKER_DESCRIPTION'] : '{ TRACKER_DESCRIPTION }')); ?></th>
			</tr>
		</thead>
		<tbody>
			<tr style="height: 50px">
				<td class="bg1" colspan="2" align="center">
					<strong><?php echo ((isset($this->_rootref['L_TRACKER_NO_PROJECT_EXIST'])) ? $this->_rootref['L_TRACKER_NO_PROJECT_EXIST'] : ((isset($user->lang['TRACKER_NO_PROJECT_EXIST'])) ? $user->lang['TRACKER_NO_PROJECT_EXIST'] : '{ TRACKER_NO_PROJECT_EXIST }')); ?></strong>
				</td>
			</tr>
		</tbody>
	</table>
	<span class="corners-bottom"><span></span></span></div>
</div>
<?php } else { $_cat_count = (isset($this->_tpldata['cat'])) ? sizeof($this->_tpldata['cat']) : 0;if ($_cat_count) {for ($_cat_i = 0; $_cat_i < $_cat_count; ++$_cat_i){$_cat_val = &$this->_tpldata['cat'][$_cat_i]; ?>

	<div class="forabg">
		<div class="inner"><span class="corners-top"><span></span></span>
			<table class="table1" cellspacing="0">
			<thead>
				<tr>
					<th style="text-align: left; width: 30%;"><a href="<?php echo $_cat_val['U_PROJECT']; ?>"><?php echo $_cat_val['PROJECT_NAME']; ?></a></th>
					<th colspan="2" style="text-align: left; width: 70%;"><?php echo ((isset($this->_rootref['L_TRACKER_DESCRIPTION'])) ? $this->_rootref['L_TRACKER_DESCRIPTION'] : ((isset($user->lang['TRACKER_DESCRIPTION'])) ? $user->lang['TRACKER_DESCRIPTION'] : '{ TRACKER_DESCRIPTION }')); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php $_project_count = (isset($_cat_val['project'])) ? sizeof($_cat_val['project']) : 0;if ($_project_count) {for ($_project_i = 0; $_project_i < $_project_count; ++$_project_i){$_project_val = &$_cat_val['project'][$_project_i]; if (!($_project_val['S_ROW_COUNT'] & 1)  ) {  ?><tr class="bg1"><?php } else { ?><tr class="bg2"><?php } ?>

						<td style="width: 28%;"><a href="<?php echo $_project_val['U_PROJECT']; ?>"><strong><?php echo $_project_val['PROJECT_TYPE']; ?></strong></a></td>
						<td style="width: 57%;"><?php if ($_project_val['PROJECT_DESC']) {  echo $_project_val['PROJECT_DESC']; } else { ?>&nbsp;<?php } ?></td>
						<td style="width: 15%; text-align: right;"><a href="<?php echo $_project_val['U_PROJECT_STATISTICS']; ?>"><?php echo ((isset($this->_rootref['L_TRACKER_VIEW_STATISTICS'])) ? $this->_rootref['L_TRACKER_VIEW_STATISTICS'] : ((isset($user->lang['TRACKER_VIEW_STATISTICS'])) ? $user->lang['TRACKER_VIEW_STATISTICS'] : '{ TRACKER_VIEW_STATISTICS }')); ?></a></td>
					</tr>
				<?php }} ?>

			</tbody>
			</table>
		<span class="corners-bottom"><span></span></span></div>
	</div>
	<?php if (! $_cat_val['S_LAST_ROW']) {  ?><br /><?php } }} } ?>

<br />

<?php if (! $this->_rootref['S_USER_LOGGED_IN'] && ! $this->_rootref['S_IS_BOT']) {  ?>

<form method="post" action="<?php echo (isset($this->_rootref['S_LOGIN_ACTION'])) ? $this->_rootref['S_LOGIN_ACTION'] : ''; ?>" class="headerspace">
	<h3><a href="<?php echo (isset($this->_rootref['U_LOGIN_LOGOUT'])) ? $this->_rootref['U_LOGIN_LOGOUT'] : ''; ?>"><?php echo ((isset($this->_rootref['L_LOGIN_LOGOUT'])) ? $this->_rootref['L_LOGIN_LOGOUT'] : ((isset($user->lang['LOGIN_LOGOUT'])) ? $user->lang['LOGIN_LOGOUT'] : '{ LOGIN_LOGOUT }')); ?></a>&nbsp; &bull; &nbsp;<a href="<?php echo (isset($this->_rootref['U_REGISTER'])) ? $this->_rootref['U_REGISTER'] : ''; ?>"><?php echo ((isset($this->_rootref['L_REGISTER'])) ? $this->_rootref['L_REGISTER'] : ((isset($user->lang['REGISTER'])) ? $user->lang['REGISTER'] : '{ REGISTER }')); ?></a></h3>
	<fieldset class="quick-login">
		<label for="username"><?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>:</label>&nbsp;<input type="text" name="username" id="username" size="10" class="inputbox" title="<?php echo ((isset($this->_rootref['L_USERNAME'])) ? $this->_rootref['L_USERNAME'] : ((isset($user->lang['USERNAME'])) ? $user->lang['USERNAME'] : '{ USERNAME }')); ?>" />
		<label for="password"><?php echo ((isset($this->_rootref['L_PASSWORD'])) ? $this->_rootref['L_PASSWORD'] : ((isset($user->lang['PASSWORD'])) ? $user->lang['PASSWORD'] : '{ PASSWORD }')); ?>:</label>&nbsp;<input type="password" name="password" id="password" size="10" class="inputbox" title="<?php echo ((isset($this->_rootref['L_PASSWORD'])) ? $this->_rootref['L_PASSWORD'] : ((isset($user->lang['PASSWORD'])) ? $user->lang['PASSWORD'] : '{ PASSWORD }')); ?>" /> |
		<label for="autologin"><?php echo ((isset($this->_rootref['L_LOG_ME_IN'])) ? $this->_rootref['L_LOG_ME_IN'] : ((isset($user->lang['LOG_ME_IN'])) ? $user->lang['LOG_ME_IN'] : '{ LOG_ME_IN }')); ?> <input type="checkbox" name="autologin" id="autologin" class="checkbox" /></label> <input type="submit" name="login" value="<?php echo ((isset($this->_rootref['L_LOGIN'])) ? $this->_rootref['L_LOGIN'] : ((isset($user->lang['LOGIN'])) ? $user->lang['LOGIN'] : '{ LOGIN }')); ?>" class="button2" />
	</fieldset>
</form>
<?php } $this->_tpl_include('tracker/tracker_footer.html'); $this->_tpl_include('overall_footer.html'); ?>