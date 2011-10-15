<?php
/**
*
* @package VC
* @version $Id: phpbb_peoplesign_plugin.php,v 1.0.15 2011/04/10 15:00 hookerb Exp$
* @copyright (c) 2008-2011 Myricomp LLC
* @license http://opensource.org/licenses/gpl-license.php GNU Public License, v2
*/

/**
* @ignore
*/
if (!defined('IN_PHPBB'))
{
	exit;
}

if (!class_exists('phpbb_default_captcha'))
{
	// We need the classic captcha code for tracking solutions and attempts
	include($phpbb_root_path . 'includes/captcha/plugins/captcha_abstract.' . $phpEx);
}

// We need the peoplesign plugin
include($phpbb_root_path . 'includes/captcha/plugins/peoplesign_client.' . $phpEx);

/**
* @package VC
*/
class phpbb_peoplesign extends phpbb_default_captcha
{
    // seting my_peoplesign_key_override will override the value that is set in
    //  the DB and disable setting it via the ACP.
    //  use this if you really want to configure this here.
    // You must visit the ACP to adjust the challenge option string.
    var $my_peoplesign_key_override = "";

	var $peoplesign_location		= "phpBB3";
	var $peoplesign_session_id		= "";
	var $solved						= false;
	var $ps_opt_reg_len				= 250; // split challenge option string across
	var $ps_opt_num_reg				= 4; //  multiple DB entries.

	function phpbb_peoplesign()
	{
		global $user;
		$user->add_lang('mods/captcha_peoplesign');
	}

	function get_peoplesign_key()
	{
		global $config;
		if (empty($this->my_peoplesign_key_override))
		{
			if (!isset($config['peoplesign_key']))
			{
				$config['peoplesign_key'] = ""; // initialize
			}

			return $config['peoplesign_key'];
		}
		return $this->my_peoplesign_key_override;
	}

	function init($type) {}

	function get_template()
	{
		global $template;

		$this->load_code();

		$template->assign_vars( array(
			'CODE'				=> $this->code,
			'S_CONFIRM_CODE'	=> true, // required for max login attempts
		));

		return 'captcha_peoplesign.html';
	}

	function get_demo_template($id)
	{
		global $template;

		$this->load_code();

		$template->assign_vars( array(
			'CODE'  => $this->code,
		));

		return 'captcha_peoplesign_acp_demo.html';
	}

	/**
	* Check the captcha to determine if the user can pass
	* returns:
	*	false - success, user passes
	*	true  - failure, user does not pass
	**/
	function validate()
	{
		$this->request_session_id();

		if ($this->solved)
		{
			return false;
		}

		$response = process_peoplesign_response($this->peoplesign_session_id, "", $this->peoplesign_location, $this->get_peoplesign_key());

		if ($response)
		{
			$this->solved = true;
			return false;
		}
		return ERROR_WRONG_ANSWER; // evaluates to true (error), and displays a message to the user that there was a problem
	}

	/**
	* Restore the captcha to its starting state.
	**/
	function reset()
	{
		global $user;

		$key = $this->get_peoplesign_key();
		if (empty($key))
		{
			return "";
		}

		// Display the CAPTCHA to the user in the same language as the rest of the board.
		$language = request_var('lang', $user->lang_name);
		$options = $this->get_options_string();
		$options = set_captcha_language($options, $language);

		$peoplesign_wrapper_version	= "phpBB3_" . PEOPLESIGN_VERSION_ID;

		$response = get_peoplesign_session_id(
			$this->get_peoplesign_key(),
			$user->ip,
			$options,
			$this->peoplesign_location,
			$peoplesign_wrapper_version,
			$this->peoplesign_session_id
		);
		$this->peoplesign_session_id = $response;
		$this->solved = false;
	}

	/**
	* Determines weather or not the captcha is available and ready.
	* Peoplesign requires a key issued from peoplesign.com
	**/
	function is_available()
	{
		global $config;

		if (empty($config['peoplesign_key']) && empty($my_peoplesign_key_override))
		{
			return false;
		}
		return true;
	}

	/**
	* Handle the Administration Control Panel configuration
	**/
	function acp_page($id, &$module)
	{
		global $template, $config, $user;

		$captcha_vars = array(
			'peoplesign_key'		=> 'PEOPLESIGN_KEY',
			'peoplesign_options'	=> 'PEOPLESIGN_OPTIONS',
		);

		$module->tpl_name = 'captcha_peoplesign_acp';
		$module->page_title = 'ACP_VC_SETTINGS';

		// From captcha_peoplesign.html ...
		$form_key = 'acp_captcha';
		add_form_key($form_key);

		// button options on the configure page
		$submit = request_var('submit', '');
		$preview = request_var('preview', '');

		// On preview or submit, then set the values
		if(($preview || $submit) && check_form_key($form_key))
		{
			$captcha_vars = array_keys($captcha_vars);
			foreach ($captcha_vars as $captcha_var)
			{
				$value = request_var($captcha_var, '');
				// Handle the peoplesign options specially... split them across DB entries
				if ($captcha_var == 'peoplesign_options')
				{
					$value = str_replace("&amp;", "&", $value);

					if (strlen($value) > ($this->ps_opt_reg_len * $this->ps_opt_num_reg)) {
						// Give an error to the user. value is too large for the DB
						set_config($captcha_var, $value);
					} else {
						// Store the config data across multiple DB entries, since a DB field is varchar(255)
						$value=str_pad($value, $this->ps_opt_reg_len * $this->ps_opt_num_reg);
						for ($i = 0; $i < $this->ps_opt_num_reg; $i++)
						{
							set_config($captcha_var.$i,substr($value,$i*$this->ps_opt_reg_len,$this->ps_opt_reg_len));
						}
					}

				} else {
					set_config($captcha_var, $value);
				}
			}
			if($submit)
			{
				add_log('admin', 'LOG_CONFIG_VISUAL');
				trigger_error($user->lang['CONFIG_UPDATED'] .
					adm_back_link($module->u_action));
			}
		}
		else if ($submit || $preview)
		{
			trigger_error($user->lang['FORM_INVALID'] . adm_back_link($module->u_action));
		}
		else
		{
			foreach ($captcha_vars as $captcha_var => $template_var)
			{
				$var = request_var($captcha_var, '');
				if (!$var)
				{
					$var = ((isset($config[$captcha_var])) ? $config[$captcha_var] : '');
				}
				$template->assign_var($template_var, $var);
			}
		}

		$this->reset();

		$template->assign_vars( array(
			'PEOPLESIGN_KEY' 		=> $this->get_peoplesign_key(),
			'PEOPLESIGN_OPTIONS'	=> $this->get_options_string(),
			'PEOPLESIGN_VERSION_ID' => PEOPLESIGN_VERSION_ID,
			'CAPTCHA_PREVIEW' 		=> $this->get_demo_template($id),
			'CAPTCHA_NAME'			=> $this->get_class_name(),
			'U_ACTION'				=> $module->u_action,
			'CAPTCHA_PREVIEW' 		=> $this->get_demo_template($id),
			'CAPTCHA_NAME'			=> $this->get_class_name(),
			'U_ACTION'				=> $module->u_action,
		));
	}

	/**
	* reconstitute the peoplesign options from xiple DB entries
	**/
	function get_options_string()
	{
		global $config;

		$ps_opts = "";
		// reconstitute the option string from the many DB entries
		for ($i = 0; $i < $this->ps_opt_num_reg; $i ++)
		{
			if (!isset($config['peoplesign_options' . $i]))
			{
				$config['peoplesign_options' . $i] = ""; // initialize
			}
			$ps_opts = $ps_opts . $config['peoplesign_options' . $i];
		}
		// trim off the space padding used when storing it to the DB
		$ps_opts = trim($ps_opts);
		return $ps_opts;
	}

	/**
	* populate the Peoplesign session id var
	**/
	function request_session_id()
	{
		if (!$this->peoplesign_session_id)
		{
			$this->peoplesign_session_id = request_var(PEOPLESIGN_CHALLENGE_SESSION_ID_NAME, '');
			$this->reset();
		}
	}

	function load_code()
	{
		global $user;

		if(!$this->get_peoplesign_key())
		{
			$this->code = $user->lang['PEOPLESIGN_NO_KEY'];
			return;
		}
		else if ($this->solved)
		{
			$this->reset();
		}
		$this->request_session_id();
		if ($this->peoplesign_session_id)
		{
			$this->code = get_peoplesign_javascript($this->peoplesign_session_id);
		}
	}

	/**
	* Determines whether or not to display the acp config page. Peoplesign requires it.
	**/
	function has_config()
	{
		return true;
	}

	function is_solved()
	{
		return (bool) $this->solved;
	}

	function get_name()
	{
		global $user;
		$user->add_lang('mods/captcha_peoplesign');
		return 'CAPTCHA_PEOPLESIGN';
	}

	function get_class_name()
	{
		return 'phpbb_peoplesign';
	}

	function &get_instance()
	{
		$instance =& new phpbb_peoplesign();
		return $instance;
	}

	// Not needed
	function new_attempt() {}
	function execute_demo() {}
	function execute() {}
	function generate_code() {}
	function regenerate_code() {}
	function check_code() {}
	function delete_code() {}
	function garbage_collect($type) {}
}
?>
