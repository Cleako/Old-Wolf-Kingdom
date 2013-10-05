<?php if (!defined('IN_PHPBB')) exit; $this->_tpl_include('simple_header.html'); ?><!-- MSNM info from http://www.cdolive.net/ - doesn't seem to work with MSN Messenger -->
<h2 class="solo"><?php echo ((isset($this->_rootref['L_SEND_IM'])) ? $this->_rootref['L_SEND_IM'] : ((isset($user->lang['SEND_IM'])) ? $user->lang['SEND_IM'] : '{ SEND_IM }')); ?></h2>

<form method="post" action="<?php echo (isset($this->_rootref['S_IM_ACTION'])) ? $this->_rootref['S_IM_ACTION'] : ''; ?>">

<div class="panel bg2">
	<div class="inner"><span class="corners-top"><span></span></span>

	<p><?php echo ((isset($this->_rootref['L_SEND_IM_EXPLAIN'])) ? $this->_rootref['L_SEND_IM_EXPLAIN'] : ((isset($user->lang['SEND_IM_EXPLAIN'])) ? $user->lang['SEND_IM_EXPLAIN'] : '{ SEND_IM_EXPLAIN }')); ?></p>

	<fieldset>
	<dl class="fields2">
		<dt><label><?php echo ((isset($this->_rootref['L_IM_RECIPIENT'])) ? $this->_rootref['L_IM_RECIPIENT'] : ((isset($user->lang['IM_RECIPIENT'])) ? $user->lang['IM_RECIPIENT'] : '{ IM_RECIPIENT }')); ?>:</label></dt>
		<dd><strong><?php echo (isset($this->_rootref['USERNAME'])) ? $this->_rootref['USERNAME'] : ''; ?></strong><?php if ($this->_rootref['S_SEND_ICQ'] || $this->_rootref['S_SEND_AIM'] || $this->_rootref['S_SEND_MSNM'] || $this->_rootref['S_NO_SEND_JABBER']) {  ?> [ <?php echo (isset($this->_rootref['IM_CONTACT'])) ? $this->_rootref['IM_CONTACT'] : ''; ?> ]<?php } if ($this->_rootref['PRESENCE_IMG']) {  ?> <?php echo (isset($this->_rootref['PRESENCE_IMG'])) ? $this->_rootref['PRESENCE_IMG'] : ''; } ?></dd>
	</dl>

	<?php if ($this->_rootref['S_SEND_ICQ']) {  ?>

		<dl class="fields2">
			<dt><label for="from"><?php echo ((isset($this->_rootref['L_IM_NAME'])) ? $this->_rootref['L_IM_NAME'] : ((isset($user->lang['IM_NAME'])) ? $user->lang['IM_NAME'] : '{ IM_NAME }')); ?>:</label></dt>
			<dd><input class="inputbox autowidth" type="text" name="from" id="from" size="20" /></dd>
		</dl>
		<dl class="fields2">
			<dt><label for="body"><?php echo ((isset($this->_rootref['L_IM_MESSAGE'])) ? $this->_rootref['L_IM_MESSAGE'] : ((isset($user->lang['IM_MESSAGE'])) ? $user->lang['IM_MESSAGE'] : '{ IM_MESSAGE }')); ?>:</label></dt>
			<dd><textarea class="inputbox autowidth" name="body" id="body" rows="5" cols="45"></textarea></dd>
		</dl>
		<dl class="fields2">
			<dt>&nbsp;</dt>
			<dd><input class="button1" name="submit" type="submit" value="<?php echo ((isset($this->_rootref['L_IM_SEND'])) ? $this->_rootref['L_IM_SEND'] : ((isset($user->lang['IM_SEND'])) ? $user->lang['IM_SEND'] : '{ IM_SEND }')); ?>" /></dd>
		</dl>
		<input type="hidden" name="fromemail" value="<?php echo (isset($this->_rootref['EMAIL'])) ? $this->_rootref['EMAIL'] : ''; ?>" />
		<input type="hidden" name="subject" value="<?php echo (isset($this->_rootref['SITENAME'])) ? $this->_rootref['SITENAME'] : ''; ?>" />
		<input type="hidden" name="to" value="<?php echo (isset($this->_rootref['IM_CONTACT'])) ? $this->_rootref['IM_CONTACT'] : ''; ?>" />
	<?php } if ($this->_rootref['S_SEND_AIM']) {  ?>

		<dl class="fields2">
			<dt>&nbsp;</dt>
			<dd><a href="<?php echo (isset($this->_rootref['U_AIM_CONTACT'])) ? $this->_rootref['U_AIM_CONTACT'] : ''; ?>"><?php echo ((isset($this->_rootref['L_IM_ADD_CONTACT'])) ? $this->_rootref['L_IM_ADD_CONTACT'] : ((isset($user->lang['IM_ADD_CONTACT'])) ? $user->lang['IM_ADD_CONTACT'] : '{ IM_ADD_CONTACT }')); ?></a></dd>
			<dd><a href="<?php echo (isset($this->_rootref['U_AIM_MESSAGE'])) ? $this->_rootref['U_AIM_MESSAGE'] : ''; ?>"><?php echo ((isset($this->_rootref['L_IM_SEND_MESSAGE'])) ? $this->_rootref['L_IM_SEND_MESSAGE'] : ((isset($user->lang['IM_SEND_MESSAGE'])) ? $user->lang['IM_SEND_MESSAGE'] : '{ IM_SEND_MESSAGE }')); ?></a></dd>
			<dd><a href="http://www.aim.com"><?php echo ((isset($this->_rootref['L_IM_DOWNLOAD_APP'])) ? $this->_rootref['L_IM_DOWNLOAD_APP'] : ((isset($user->lang['IM_DOWNLOAD_APP'])) ? $user->lang['IM_DOWNLOAD_APP'] : '{ IM_DOWNLOAD_APP }')); ?></a> | <a href="http://www.aim.com/products/express"><?php echo ((isset($this->_rootref['L_IM_AIM_EXPRESS'])) ? $this->_rootref['L_IM_AIM_EXPRESS'] : ((isset($user->lang['IM_AIM_EXPRESS'])) ? $user->lang['IM_AIM_EXPRESS'] : '{ IM_AIM_EXPRESS }')); ?></a></dd>
		</dl>
	<?php } if ($this->_rootref['S_SEND_MSNM']) {  ?>

		<dl class="fields2">
			<dt>&nbsp;</dt>
			<dd><object classid="clsid:B69003B3-C55E-4B48-836C-BC5946FC3B28" codetype="application/x-oleobject" id="objMessengerApp" width="0" height="0"></object></dd>
			<dd><a href="#" onclick="add_contact('<?php echo (isset($this->_rootref['A_IM_CONTACT'])) ? $this->_rootref['A_IM_CONTACT'] : ''; ?>'); return false;"><?php echo ((isset($this->_rootref['L_IM_ADD_CONTACT'])) ? $this->_rootref['L_IM_ADD_CONTACT'] : ((isset($user->lang['IM_ADD_CONTACT'])) ? $user->lang['IM_ADD_CONTACT'] : '{ IM_ADD_CONTACT }')); ?></a></dd>
			<dd><a href="#" onclick="im_contact('<?php echo (isset($this->_rootref['A_IM_CONTACT'])) ? $this->_rootref['A_IM_CONTACT'] : ''; ?>'); return false;"><?php echo ((isset($this->_rootref['L_IM_SEND_MESSAGE'])) ? $this->_rootref['L_IM_SEND_MESSAGE'] : ((isset($user->lang['IM_SEND_MESSAGE'])) ? $user->lang['IM_SEND_MESSAGE'] : '{ IM_SEND_MESSAGE }')); ?></a></dd>
		</dl>
	<?php } if ($this->_rootref['S_SEND_JABBER']) {  ?>

		<dl class="fields2">
			<dt><label for="message"><?php echo ((isset($this->_rootref['L_IM_MESSAGE'])) ? $this->_rootref['L_IM_MESSAGE'] : ((isset($user->lang['IM_MESSAGE'])) ? $user->lang['IM_MESSAGE'] : '{ IM_MESSAGE }')); ?>:</label></dt>
			<dd><textarea class="inputbox autowidth" name="message" id="message" rows="5" cols="45"></textarea></dd>
		</dl>
		<dl class="fields2">
			<dt>&nbsp;</dt>
			<dd><input class="button1" name="submit" type="submit" value="<?php echo ((isset($this->_rootref['L_IM_SEND'])) ? $this->_rootref['L_IM_SEND'] : ((isset($user->lang['IM_SEND'])) ? $user->lang['IM_SEND'] : '{ IM_SEND }')); ?>" /></dd>
		</dl>
	<?php } if ($this->_rootref['S_NO_SEND_JABBER']) {  ?>

		<dl class="fields2">
			<dt>&nbsp;</dt>
			<dd><?php echo ((isset($this->_rootref['L_IM_NO_JABBER'])) ? $this->_rootref['L_IM_NO_JABBER'] : ((isset($user->lang['IM_NO_JABBER'])) ? $user->lang['IM_NO_JABBER'] : '{ IM_NO_JABBER }')); ?></dd>
		</dl>
	<?php } if ($this->_rootref['S_SENT_JABBER']) {  ?>

		<dl class="fields2">
			<dt>&nbsp;</dt>
			<dd><?php echo ((isset($this->_rootref['L_IM_SENT_JABBER'])) ? $this->_rootref['L_IM_SENT_JABBER'] : ((isset($user->lang['IM_SENT_JABBER'])) ? $user->lang['IM_SENT_JABBER'] : '{ IM_SENT_JABBER }')); ?></dd>
		</dl>
	<?php } ?>

	<?php echo (isset($this->_rootref['S_FORM_TOKEN'])) ? $this->_rootref['S_FORM_TOKEN'] : ''; ?>

	</fieldset>

	<span class="corners-bottom"><span></span></span></div>
</div>
</form>

<a href="#" onclick="window.close(); return false;"><?php echo ((isset($this->_rootref['L_CLOSE_WINDOW'])) ? $this->_rootref['L_CLOSE_WINDOW'] : ((isset($user->lang['CLOSE_WINDOW'])) ? $user->lang['CLOSE_WINDOW'] : '{ CLOSE_WINDOW }')); ?></a>

<script type="text/javascript">
// <![CDATA[

	/** The following will not work with Windows Vista **/

	var app = document.getElementById('objMessengerApp');

	/**
	* Check whether the browser supports this and whether MSNM is connected
	*/
	function msn_supported()
	{
		// Does the browser support the MSNM object?
		if (app.MyStatus)
		{
			// Is MSNM connected?
			if (app.MyStatus == 1)
			{
				alert('<?php echo ((isset($this->_rootref['LA_IM_MSNM_CONNECT'])) ? $this->_rootref['LA_IM_MSNM_CONNECT'] : ((isset($this->_rootref['L_IM_MSNM_CONNECT'])) ? addslashes($this->_rootref['L_IM_MSNM_CONNECT']) : ((isset($user->lang['IM_MSNM_CONNECT'])) ? addslashes($user->lang['IM_MSNM_CONNECT']) : '{ IM_MSNM_CONNECT }'))); ?>');
				return false;
			}
		}
		else
		{
			alert('<?php echo ((isset($this->_rootref['LA_IM_MSNM_BROWSER'])) ? $this->_rootref['LA_IM_MSNM_BROWSER'] : ((isset($this->_rootref['L_IM_MSNM_BROWSER'])) ? addslashes($this->_rootref['L_IM_MSNM_BROWSER']) : ((isset($user->lang['IM_MSNM_BROWSER'])) ? addslashes($user->lang['IM_MSNM_BROWSER']) : '{ IM_MSNM_BROWSER }'))); ?>');
			return false;
		}
		return true;
	}

	/**
	* Add to your contact list
	*/
	function add_contact(address) 
	{
		if (msn_supported()) 
		{
			// Could return an error while MSNM is connecting, don't want that
			try
			{
				app.AddContact(0, address);
			}
			catch (e)
			{
				return;
			}
	}
}

/**
* Write IM to contact
*/
function im_contact(address)
{
	if (msn_supported())
	{
		// Could return an error while MSNM is connecting, don't want that
		try
		{
			app.InstantMessage(address);
		}
		catch (e)
		{
			return;
		}
	}
}
// ]]>
</script>

<?php $this->_tpl_include('simple_footer.html'); ?>