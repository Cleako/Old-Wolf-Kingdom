<?php if (!defined('IN_PHPBB')) exit; ?></div>
    
                <div id="page-footer">
                
                	<div class="navbar">
                		<div class="inner"><span class="corners-top"><span></span></span>
                
                		<ul class="linklist">
                			<li><i class="icon-home"></i> <a href="<?php echo (isset($this->_rootref['U_INDEX'])) ? $this->_rootref['U_INDEX'] : ''; ?>" accesskey="h"><?php echo ((isset($this->_rootref['L_INDEX'])) ? $this->_rootref['L_INDEX'] : ((isset($user->lang['INDEX'])) ? $user->lang['INDEX'] : '{ INDEX }')); ?></a></li>
                				<?php if (! $this->_rootref['S_IS_BOT']) {  if ($this->_rootref['S_WATCH_FORUM_LINK']) {  ?><li><i class="<?php if ($this->_rootref['S_WATCHING_FORUM']) {  ?>icon-check-empty<?php } else { ?>icon-check<?php } ?>"></i> <a href="<?php echo (isset($this->_rootref['S_WATCH_FORUM_LINK'])) ? $this->_rootref['S_WATCH_FORUM_LINK'] : ''; ?>" title="<?php echo (isset($this->_rootref['S_WATCH_FORUM_TITLE'])) ? $this->_rootref['S_WATCH_FORUM_TITLE'] : ''; ?>"><?php echo (isset($this->_rootref['S_WATCH_FORUM_TITLE'])) ? $this->_rootref['S_WATCH_FORUM_TITLE'] : ''; ?></a></li><?php } if ($this->_rootref['U_WATCH_TOPIC']) {  ?><li><i class="<?php if ($this->_rootref['S_WATCHING_TOPIC']) {  ?>icon-check-empty<?php } else { ?>icon-check<?php } ?>"></i> <a href="<?php echo (isset($this->_rootref['U_WATCH_TOPIC'])) ? $this->_rootref['U_WATCH_TOPIC'] : ''; ?>" title="<?php echo ((isset($this->_rootref['L_WATCH_TOPIC'])) ? $this->_rootref['L_WATCH_TOPIC'] : ((isset($user->lang['WATCH_TOPIC'])) ? $user->lang['WATCH_TOPIC'] : '{ WATCH_TOPIC }')); ?>"><?php echo ((isset($this->_rootref['L_WATCH_TOPIC'])) ? $this->_rootref['L_WATCH_TOPIC'] : ((isset($user->lang['WATCH_TOPIC'])) ? $user->lang['WATCH_TOPIC'] : '{ WATCH_TOPIC }')); ?></a></li><?php } if ($this->_rootref['U_BOOKMARK_TOPIC']) {  ?><li><i class="icon-bookmark"></i> <a href="<?php echo (isset($this->_rootref['U_BOOKMARK_TOPIC'])) ? $this->_rootref['U_BOOKMARK_TOPIC'] : ''; ?>" title="<?php echo ((isset($this->_rootref['L_BOOKMARK_TOPIC'])) ? $this->_rootref['L_BOOKMARK_TOPIC'] : ((isset($user->lang['BOOKMARK_TOPIC'])) ? $user->lang['BOOKMARK_TOPIC'] : '{ BOOKMARK_TOPIC }')); ?>"><?php echo ((isset($this->_rootref['L_BOOKMARK_TOPIC'])) ? $this->_rootref['L_BOOKMARK_TOPIC'] : ((isset($user->lang['BOOKMARK_TOPIC'])) ? $user->lang['BOOKMARK_TOPIC'] : '{ BOOKMARK_TOPIC }')); ?></a></li><?php } if ($this->_rootref['U_BUMP_TOPIC']) {  ?><li><i class="icon-arrow-up"></i> <a href="<?php echo (isset($this->_rootref['U_BUMP_TOPIC'])) ? $this->_rootref['U_BUMP_TOPIC'] : ''; ?>" title="<?php echo ((isset($this->_rootref['L_BUMP_TOPIC'])) ? $this->_rootref['L_BUMP_TOPIC'] : ((isset($user->lang['BUMP_TOPIC'])) ? $user->lang['BUMP_TOPIC'] : '{ BUMP_TOPIC }')); ?>"><?php echo ((isset($this->_rootref['L_BUMP_TOPIC'])) ? $this->_rootref['L_BUMP_TOPIC'] : ((isset($user->lang['BUMP_TOPIC'])) ? $user->lang['BUMP_TOPIC'] : '{ BUMP_TOPIC }')); ?></a></li><?php } } ?>
                            <li class="rightside"><?php if ($this->_rootref['U_TEAM']) {  ?><a href="<?php echo (isset($this->_rootref['U_TEAM'])) ? $this->_rootref['U_TEAM'] : ''; ?>"><?php echo ((isset($this->_rootref['L_THE_TEAM'])) ? $this->_rootref['L_THE_TEAM'] : ((isset($user->lang['THE_TEAM'])) ? $user->lang['THE_TEAM'] : '{ THE_TEAM }')); ?></a> &bull; <?php } if (! $this->_rootref['S_IS_BOT']) {  ?><a href="<?php echo (isset($this->_rootref['U_DELETE_COOKIES'])) ? $this->_rootref['U_DELETE_COOKIES'] : ''; ?>"><?php echo ((isset($this->_rootref['L_DELETE_COOKIES'])) ? $this->_rootref['L_DELETE_COOKIES'] : ((isset($user->lang['DELETE_COOKIES'])) ? $user->lang['DELETE_COOKIES'] : '{ DELETE_COOKIES }')); ?></a> &bull; <?php } echo (isset($this->_rootref['S_TIMEZONE'])) ? $this->_rootref['S_TIMEZONE'] : ''; ?></li>
                		</ul>
                
                		<span class="corners-bottom"><span></span></span></div>
                	</div>
                </div>
                
                <?php if ($this->_tpldata['DEFINE']['.']['SIDEBAR_ON_PAGES'] == ('global') || ( $this->_tpldata['DEFINE']['.']['SIDEBAR_ON_PAGES'] == ('index') && $this->_rootref['SCRIPT_NAME'] == ('index') )) {  if ($this->_tpldata['DEFINE']['.']['SIDEBARS'] == ('left') || $this->_tpldata['DEFINE']['.']['SIDEBARS'] == ('right')) {  ?>
                        </div><!-- /.fixedfluidcontent -->
                        </div><!-- /.sidebarwrapper --><?php } else if ($this->_tpldata['DEFINE']['.']['SIDEBARS'] == ('both')) {  ?>
                        </div>
                        <div class="fixedfluidfixed-right">
                            <?php $this->_tpl_include('sidebar_right.html'); ?>
                        </div>
                        </div>
                    <?php } ?>
                    	</div>
                <?php } ?>
                
                
            </div></div>
        <div class="border-bottom"><span><span></span></span></div>
    </div></div></div></div></div>
</div>
	
	<div class="copyright">
    
    	<!-- Hugs & Kisses to those that leave the following credit line in the footer. It helps keep us maintain the style in the future :) -->
    	<span>*</span>SE Gamer Dark Style by <a href="http://www.bbcustomize.com"><span>Premium phpBB Styles</span></a>
        <!-- ...or you could have cookies and milk instead. Hmmm....cookies. :) --> 
        
    <br /><?php echo (isset($this->_rootref['CREDIT_LINE'])) ? $this->_rootref['CREDIT_LINE'] : ''; ?>
		<?php if ($this->_rootref['TRANSLATION_INFO']) {  ?><br /><?php echo (isset($this->_rootref['TRANSLATION_INFO'])) ? $this->_rootref['TRANSLATION_INFO'] : ''; } if ($this->_rootref['DEBUG_OUTPUT']) {  ?><br /><?php echo (isset($this->_rootref['DEBUG_OUTPUT'])) ? $this->_rootref['DEBUG_OUTPUT'] : ''; } if ($this->_rootref['U_ACP']) {  ?><br /><strong><a href="<?php echo (isset($this->_rootref['U_ACP'])) ? $this->_rootref['U_ACP'] : ''; ?>"><?php echo ((isset($this->_rootref['L_ACP'])) ? $this->_rootref['L_ACP'] : ((isset($user->lang['ACP'])) ? $user->lang['ACP'] : '{ ACP }')); ?></a></strong><?php } ?>
	</div>

<?php if ($this->_rootref['SCRIPT_NAME'] == ('viewforum')) {  ?>
	<script type="text/javascript">
    // <![CDATA[
        $("span.topictype").each(function() {
            var text = $(this).text();
            text = text.replace(":", "");
            $(this).text(text);
        });
    // ]]>
    </script>
<?php } ?>


<div>
	<a id="bottom" name="bottom" accesskey="z"></a>
	<?php if (! $this->_rootref['S_IS_BOT']) {  echo (isset($this->_rootref['RUN_CRON_TASK'])) ? $this->_rootref['RUN_CRON_TASK'] : ''; } ?>
</div>

</body>
</html>