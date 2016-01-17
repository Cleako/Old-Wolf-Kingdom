<?php 
function curPageURL() {
	//$pageUrl = $_SERVER["SERVER_NAME"].$_SERVER["REQUEST_URI"];
	$page = explode("/",$pageUrl);
	$pos = strpos($page[2],'index.php');
	if($pos !== false){
		$return = 'index.php';
	} else if($page[3]){
		$return = array($page[2],$page[3]);
	} else {
		$return = $page[2];
	}
	return $return;
}
$script_directory = '/';
define('IN_PHPBB', true);
$phpbb_root_path = './board/';
$phpEx = substr(strrchr(__FILE__, '.'), 1);
include($phpbb_root_path . 'common.' . $phpEx);
include($phpbb_root_path . 'includes/bbcode.' . $phpEx);
include($phpbb_root_path . 'includes/functions_display.' . $phpEx);
$user->session_begin();
$auth->acl($user->data);
$user->setup('viewforum'); 

require_once './inc/database_config.php';
require_once './inc/charfunctions.php';

/* create_where_clauses( int[] gen_id, String type )
* This function outputs an SQL WHERE statement for use when grabbing
* posts and topics */

function create_where_clauses($gen_id, $type)
{
global $db, $auth;

$size_gen_id = sizeof($gen_id);

switch($type)
{
case 'forum':
$type = 'forum_id';
break;
case 'topic':
$type = 'topic_id';
break;
default:
trigger_error('No type defined');
}

// Set $out_where to nothing, this will be used of the gen_id
// size is empty, in other words "grab from anywhere" with
// no restrictions
$out_where = '';

if ($size_gen_id > 0)
{
// Get a list of all forums the user has permissions to read
$auth_f_read = array_keys($auth->acl_getf('f_read', true));

if ($type == 'topic_id')
{
$sql = 'SELECT topic_id FROM ' . TOPICS_TABLE . '
WHERE ' . $db->sql_in_set('topic_id', $gen_id) . '
AND ' . $db->sql_in_set('forum_id', $auth_f_read);

$result = $db->sql_query($sql);

while ($row = $db->sql_fetchrow($result))
{
// Create an array with all acceptable topic ids
$topic_id_list[] = $row['topic_id'];
}

unset($gen_id);

$gen_id = $topic_id_list;
$size_gen_id = sizeof($gen_id);
}

$j = 0; 

for ($i = 0; $i < $size_gen_id; $i++)
 {
$id_check = (int) $gen_id[$i]; // If the type is topic, all checks have been made and the query can start to be built if( $type == 'topic_id' ) { $out_where .= ($j == 0) ? 'WHERE ' . $type . ' = ' . $id_check . ' ' : 'OR ' . $type . ' = ' . $id_check . ' '; } // If the type is forum, do the check to make sure the user has read permissions else if( $type == 'forum_id' && $auth->acl_get('f_read', $id_check) )
{
$out_where .= ($j == 0) ? 'WHERE ' . $type . ' = ' . $id_check . ' ' : 'OR ' . $type . ' = ' . $id_check . ' ';
} 

$j++;
}
}

if ($out_where == '' && $size_gen_id > 0)
{
trigger_error('A list of topics/forums has not been created');
}

return $out_where;
}

/*     News Code     */

$search_limit = 5;
$forum_id = array(7);
$forum_id_where = create_where_clauses($forum_id, 'forum');
$topic_id = array(0);
$topic_id_where = create_where_clauses($topic_id, 'topic');
$posts_ary = array(
'SELECT' => 'p.*, t.*',
'FROM' => array(
POSTS_TABLE => 'p',
),
'LEFT_JOIN' => array(
array(
'FROM' => array(TOPICS_TABLE => 't'),
'ON' => 't.topic_first_post_id = p.post_id'
)
),
'WHERE' => str_replace( array('WHERE ', 'forum_id'), array('', 't.forum_id'), $forum_id_where) . '',
'ORDER_BY' => 'p.post_id DESC',
);
$posts = $db->sql_build_query('SELECT', $posts_ary);
$posts_result = $db->sql_query_limit($posts, $search_limit);

?>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8"/>
		<title>Wolf Kingdom</title>
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="/js/flot/excanvas.min.js"></script><![endif]-->
		<link rel="stylesheet" media="all" href="/css/style.css"/>
		<link rel="stylesheet" href="/js/fancybox/jquery.fancybox-1.3.4.css" type="text/css" media="screen" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
		<script src="/js/cufon.js" type="text/javascript"></script>
		<script src="/js/Aurulent_Sans.font.js" type="text/javascript"></script>
		<script type="text/javascript" src="/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
		<script type="text/javascript" src="/js/flot/jquery.flot.js"></script>
		<script type="text/javascript" src="/js/flot/jquery.flot.pie.js"></script>
		<script type="text/javascript">
			function loadContent(user, userhash, id, hc, hsprite, sc, tc, gender, pc, lvl, on) {
				var url = "/js/account.php";
					$.post(url, {username: user, userenc: userhash, owner: id, hair: hc, head: hsprite, skin: sc, top: tc, gen: gender, pants: pc, combat: lvl, online: on} ,function(data) {
						$("#character-details").html(data).show();
						$("a#inline").fancybox({
						'hideOnContentClick': false,
						'hideOnOverlayClick': false,
						'overlayColor': '#000000',
						'padding': 0,
						});
						$("#character-delete-form").bind("submit", function() {
						$("#verification-fails").hide();
						if ($("#verification").val() != 'yes') {
							$("#verification-fails").show();
							$.fancybox.resize();
							return false;
						}
						$.fancybox.showActivity();
						var i = $("#user-i").val();
						var ui = $("#user-ui").val();
						var y = $("#verification").val();
						setTimeout(function(){
						
							$.post("/js/account.php", {id: i, hash: ui, ver: y} ,function(data) {
								$.fancybox.hideActivity();
								$("#character-delete-form").hide();
								window.location.reload()
							});
							
						},5000);

						return false;
						});
					});
			}
			
			$(document).ready(function() {
				$("#toggle:first").addClass('active');
				$('#toggle').click(function(){
					$('#toggle').removeClass('active');
					$(this).toggleClass('active');
				});
				
				$("a#single_image").fancybox();
				
				/* Using custom settings */
				
				$("a#inline").fancybox({
					'hideOnContentClick': false,
					'overlayColor': '#000000',
					'padding': 0,
					'onClosed': function() {
						$("#name-fails").hide();
						$("#pass-fails").hide();
						$("#user-fails").hide();
						$("#user-passes").hide();
						$("#character-creation-form").show();
					} 
				});

				$("#character-creation-form").bind("submit", function() {
					$("#name-fails").hide();
					$("#pass-fails").hide();
					if ($("#name").val().length >= 11 || $("#name").val().length <= 3) {
						$("#name-fails").show();
						$.fancybox.resize();
						return false;
					}
					if ($("#password").val().length <= 4) {
						$("#pass-fails").show();
						$.fancybox.resize();
						return false;
					}
	
					$.fancybox.showActivity();
					var n = $("#name").val();
				    var p = $("#password").val();
					
					setTimeout(function(){
					
						$.post("/js/account.php", {nm: n, pw: p} ,function(data) {
							if(data == 0){
								$("#user-fails").show();
							} else if(data == 1){
								$("#user-passes").show();
								$("#character-creation-form").hide();
								window.location.reload()
							}	
							$.fancybox.hideActivity();
						});
						
					},3000);

					return false;
				});
				
			});
		</script>
		

		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<!-- Adding "maximum-scale=1" fixes the Mobile Safari auto-zoom bug: http://filamentgroup.com/examples/iosScaleBug/ -->
	</head>
	
	<body lang="en">
	
		<header>
			<div class="large">Wolf Kingdom</div>
		</header>
		<div class="body-wrapper">	
			<div class="navigation">
				<div class="navbar">
					<ul>
						<li><a href="<?php echo $script_directory; ?>">Home</a></li>
						<li><a href="<?php echo $script_directory; ?>board/index.php">Forum</a></li>
						<li><a href="<?php echo $script_directory; ?>playnow">Play Now</a></li>
						<li><a href="<?php echo $script_directory; ?>clans">Clans</a></li>
						<li><a href="<?php echo $script_directory; ?>highscores">Highscores</a></li>
						<li><a href="<?php echo $script_directory; ?>media">Media</a></li>
					</ul>
				</div>
				<div class="account-panel">
					<div class="avatar-box">
					<?php if($user->data['is_registered']){ ?>
							<a href="<?php echo $script_directory; ?>board/ucp.php?i=profile&mode=avatar"><img src="/board/download/file.php?avatar=<?php print $user->data['user_avatar']; ?>" /></a>
					<?php } ?>
					</div>
					<div class="account-text">
					<?php if($user->data['is_registered']){ ?>
						<span class="welcome-message block">Welcome back, <?php print $user->data['username']; ?></span>
						<span class="welcome-text"><a href="<?php echo $script_directory; ?>accounts">Account Management</a></span>
						<span class="welcome-text"> | (<a href="<?php echo $script_directory; ?>board/ucp.php?i=pm&folder=inbox"><?php print $user->data['user_unread_privmsg']; ?></a>) | </span>
						<span class="welcome-text">
							<a href='<?php echo $script_directory; ?>board/ucp.php?mode=logout&amp;sid=<?php print $user->data['session_id'];?>'>Logout</a>
						</span>
						
					<?php } else { ?>
						<a id="inline" href="#data">
							<span class="welcome-message">Initiate Command Module</span>
							<span class="welcome-text">to gain access to member only features!</span>
						</a>
		
					<?php } ?>
						<div style="display:none">
							<div id="data">
								<h4>Login Module</h4>
								<p>Use the form below to login!</p>
								<form method="post" action="<?php echo $script_directory; ?>board/ucp.php?mode=login">
								<label for="loginname">Username: </label><input type="text" name="username" class="name" id="loginname"/>
								<label for="loginpass">Password: </label><input type="password" name="password" class="password" id="loginpass"/>
								<label for="autologin">Remember Me?: </label><input type="checkbox" name="autologin" class="autologin"  id="autologin"/>
								<input type="submit" value="Log In" name="login" class="submit"/>
								<input type="hidden" name="redirect" value="<?php echo $script_directory; ?>index.php" />
								</form> 
								<a class="submit" href="<?php echo $script_directory; ?>board/ucp.php?mode=register">Register</a> 
							</div>
						</div>
					</div>
				</div>
			</div>
			
		<?php
		
			if(curPageURL() != "" && !is_array(curPageURL()) && curPageURL() != 'index.php'){
				if(file_exists("pages/".curPageURL().".php")) {
					include("pages/".curPageURL().".php");
				} else {
					include("pages/error.php");
				}
			} else if(is_array(curPageURL()) && curPageURL() != 'index.php' ){
				$page = curPageURL();
				$subpage = $page[1];
				$page = $page[0];
				if(file_exists("pages/".$page.".php")) {
					include("pages/".$page.".php");
				} else {
					include("pages/error.php");
				}
			} else {
		?>
		<div class="main">
			<div class="content">
				<article>
				<?php
					while( $posts_row = $db->sql_fetchrow($posts_result) ){
						$topic_title = $posts_row['topic_title'];
						$post_author = get_username_string('full', $posts_row['poster_id'], $posts_row['topic_first_poster_name'], $posts_row['topic_first_poster_colour']);
						$post_date = $user->format_date($posts_row['post_time']);
						$post_link = append_sid("{$phpbb_root_path}viewtopic.$phpEx", "p=" . $posts_row['post_id'] . "#p" . $posts_row['post_id']);

						$post_text = nl2br($posts_row['post_text']);

						$bbcode = new bbcode(base64_encode($bbcode_bitfield)); 
						$bbcode->bbcode_second_pass($post_text, $posts_row['bbcode_uid'], $posts_row['bbcode_bitfield']);

						$post_text = smiley_text($post_text);

						echo '<h4><a href="'.$post_link.'">'.$topic_title.'</a></h4>';
						echo '<div class="meta">posted by '.$post_author.' // '.$post_date.'</div>';
						echo '<p>'.smiley_text($post_text).'</p>';
						
					}
				?>
				</article>
			</div>
			
		</div>
		
		<?php
		
			}
		
		?>
		<aside>
			<div class="box">
				<div class="widget">
					<h3><a href="playnow">Play now!</a></h3>
				</div>
			</div>
			<div class="box">
				<div class="widget">
					<h3><a href="/board">Visit Forum</a></h3>
				</div>
			</div>
		</aside>
		</div>
		
	</body>
	
</html>