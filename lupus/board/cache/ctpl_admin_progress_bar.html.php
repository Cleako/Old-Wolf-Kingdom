<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('simple_header.html'); ?>


<script type="text/javascript">
// <![CDATA[
	/**
	* Close previously opened popup
	*/
	function close_popup()
	{
		if (opener != null)
		{
			if (opener.close_waitscreen != null)
			{
				if (opener.close_waitscreen == 1)
				{
					opener.close_waitscreen = 0;
					self.close();
					return 0;
				}
			}
		}
		setTimeout("close_popup()", 1000);
		return 0;
	}
// ]]>
</script>

<div class="successbox">
	<h3><?php echo ((isset($this->_rootref['L_PROGRESS'])) ? $this->_rootref['L_PROGRESS'] : ((isset($user->lang['PROGRESS'])) ? $user->lang['PROGRESS'] : '{ PROGRESS }')); ?></h3>
	<img src="images/progress_bar.gif" alt="<?php echo ((isset($this->_rootref['L_PROGRESS'])) ? $this->_rootref['L_PROGRESS'] : ((isset($user->lang['PROGRESS'])) ? $user->lang['PROGRESS'] : '{ PROGRESS }')); ?>" />
	<p><?php echo ((isset($this->_rootref['L_PROGRESS_EXPLAIN'])) ? $this->_rootref['L_PROGRESS_EXPLAIN'] : ((isset($user->lang['PROGRESS_EXPLAIN'])) ? $user->lang['PROGRESS_EXPLAIN'] : '{ PROGRESS_EXPLAIN }')); ?></p>
</div>

<script type="text/javascript">
// <![CDATA[
	close_popup();
// ]]>
</script>

<?php $this->_tpl_include('simple_footer.html'); ?>