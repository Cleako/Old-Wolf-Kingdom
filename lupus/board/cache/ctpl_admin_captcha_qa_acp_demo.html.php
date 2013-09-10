<?php if (!defined('IN_PHPBB')) exit; ?><dl>
	<dt><label for="answer"><?php if ($this->_rootref['QA_CONFIRM_QUESTION']) {  ?> <?php echo (isset($this->_rootref['QA_CONFIRM_QUESTION'])) ? $this->_rootref['QA_CONFIRM_QUESTION'] : ''; ?> <?php } else { ?> <?php echo ((isset($this->_rootref['L_CONFIRM_QUESTION'])) ? $this->_rootref['L_CONFIRM_QUESTION'] : ((isset($user->lang['CONFIRM_QUESTION'])) ? $user->lang['CONFIRM_QUESTION'] : '{ CONFIRM_QUESTION }')); ?> <?php } ?>:</label><br /><span><?php echo ((isset($this->_rootref['L_CONFIRM_QUESTION_EXPLAIN'])) ? $this->_rootref['L_CONFIRM_QUESTION_EXPLAIN'] : ((isset($user->lang['CONFIRM_QUESTION_EXPLAIN'])) ? $user->lang['CONFIRM_QUESTION_EXPLAIN'] : '{ CONFIRM_QUESTION_EXPLAIN }')); ?></span></dt>

	<dd>
		<input type="text" tabindex="10" name="answer" id="answer" size="45" class="inputbox autowidth" title="<?php echo ((isset($this->_rootref['L_ANSWER'])) ? $this->_rootref['L_ANSWER'] : ((isset($user->lang['ANSWER'])) ? $user->lang['ANSWER'] : '{ ANSWER }')); ?>"  />
	</dd>
</dl>