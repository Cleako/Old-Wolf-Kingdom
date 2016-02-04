<?php
define('IN_PHPBB', true);
$phpbb_root_path = '../board/';
$phpEx = substr(strrchr(__FILE__, '.'), 1);
include($phpbb_root_path . 'common.' . $phpEx);
include($phpbb_root_path . 'includes/bbcode.' . $phpEx);
include($phpbb_root_path . 'includes/functions_display.' . $phpEx);
$user->session_begin();
$auth->acl($user->data);
$user->setup('viewforum'); 

if(!$_POST)
{
	die("You do not have permission to access this file.");
}

$skill_array = array(attack, strength, defense, hitpoints, ranged, prayer, magic, cooking, woodcut, fletching, fishing, firemaking, crafting, smithing, mining, herblaw, agility, thieving);

function buildSQLArray($array) {
	$SQLarray = '';
	$size = sizeof($array)-1;
	$i=0;
	while($i <= $size){
		$SQLarray .= ($array[$i] == 'total_lvl') ? '' : (($array[$i] == 'hitpoints') ? 'exp_hits,' : 'exp_'.$array[$i].''.(($i == $size) ? '' : ',').'');
		$i++;
	}
	return $SQLarray;
}


require_once '../inc/database_config.php';
require_once '../inc/charfunctions.php';
include("../inc/peoplesignClient.php");

$script_directory = '/';
$connector = new DarscapeDbc();
if($_POST['nm']){

	$username = $_POST['nm'];
	$password = $_POST['pw'];
	
	$username = mysql_real_escape_string($username);
	$username = preg_replace("/[^A-Za-z0-9 ]/"," ",$username);
	
	$user_result = $connector->query("SELECT user FROM wk_players WHERE username='$username'");
	$num_users_row = mysql_num_rows($user_result);
	
	if($num_users_row != 0){
		echo 0;
	} else {
		if(strlen($username) >= 12 || strlen($username) <= 4){}
		if(strlen($password) <= 5){}
		if(strlen($username) >= 12 || strlen($username) <= 4 || strlen($password) <= 5){} else {
			$time = time();
			$gamepass = sha1($password);
			$gamename = explode('.', encode_username($username));
			$connector->query("INSERT INTO `wk_curstats`(`user`) VALUES ('" . $gamename[0] . "')");
			$connector->query("INSERT INTO `wk_experience`(`user`) VALUES ('" . $gamename[0] . "')");
			$connector->query("INSERT INTO `wk_players`(`user`, `username`, `phpbb_id`, `owner_username`, `pass`, `creation_date`, `creation_ip`) VALUES ('" . $gamename[0] . "', '" . $username . "', '" . $user->data['user_id'] . "', '" . $user->data['username'] . "', '" . $gamepass . "', '".$time."', '". $_SERVER['REMOTE_ADDR'] ."')");
			echo 1;
		}
	}

} else if($_POST["ver"]){

	$user_i = $_POST["id"];
	$user_ui = $_POST["hash"];
	$ver = $_POST["ver"];
	
	if(strtolower($ver) != 'yes'){
		echo 0;
	} else {
		$user_check = $connector->query("SELECT phpbb_id,group_id FROM wk_players WHERE user=$user_ui");
		$check = $connector->fetchArray($user_check);
		if($user_i == $user->data['user_id']){
			if($check['group_id'] == 1){ echo 2; } else {
				$connector->query("DELETE FROM wk_players WHERE user = '" . $user_ui . "'");
				$connector->query("DELETE FROM wk_curstats WHERE user = '" . $user_ui . "'");
				$connector->query("DELETE FROM wk_experience WHERE user = '" .  $user_ui . "'");
				$connector->query("DELETE FROM wk_invitems WHERE user = '" .  $user_ui. "'");
				$connector->query("DELETE FROM wk_quests WHERE user = '" .  $user_ui . "'");
				$connector->query("DELETE FROM wk_friends WHERE user = '" .  $user_ui. "'");
				$connector->query("DELETE FROM wk_ignores WHERE user = '" .  $user_ui . "'");
				$connector->query("DELETE FROM wk_bank WHERE user = '" .  $user_ui . "'");
				$connector->query("DELETE FROM wk_kills WHERE user = '" .  $user_ui . "'");
				echo 3;
			}
		} else { echo 1; }
	}

} else if($_POST["username"]){

$username = $_POST["username"];
$userhash = $_POST["userenc"];
$combat = $_POST["combat"];
$id = $_POST["owner"];
$online = $_POST["online"];

$usernamelink = preg_replace("/[^A-Za-z0-9]/","-",$username);

$skills = buildSQLArray($skill_array);
$user_check = $connector->query("SELECT ".$skills.",wk_players.phpbb_id FROM wk_players LEFT JOIN wk_experience ON wk_players.user = wk_experience.user WHERE wk_players.user=$userhash");
$check = $connector->fetchArray($user_check);


if($check['phpbb_id'] == $user->data['user_id']){

?>
	<div id="character">
	<?php
	echo drawCharacter($_POST['hair'],$_POST['head'],$_POST['skin'],$_POST['top'],$_POST['gen'],$_POST['pants']);
	?>
	</div>
	<div id="hero-page-details">
		<span class="sm-stats"><?php echo $username; ?></span>
		<span class="sm-stats">Combat Level: <?php echo $combat; ?></span>
		<span class="sm-stats"><a href="<?php echo $script_directory; ?>characters/<?php echo $usernamelink; ?>">View Skill Levels</a></span>
		<?php if($online == 1) { echo '<span id="green">Online</span>'; } else { echo '<span id="red">Offline</span>'; } ?>
	</div>
	<div id="button-links">
		<a id="inline" href="#<?php echo ($online == 1) ? 'error' : 'pass' ;?>" class="button">Change Password</a>
		<div style="display:none">
			<div id="pass">
				<h4>Change Password</h4>
				<p>Change <?php echo $username;?>'s password</p>
				
			</div>
		</div>
		<a id="inline" href="#<?php echo ($online == 1) ? 'error' : 'delete' ;?>" class="button">Delete Account</a>
		<div style="display:none">
			<div id="delete">
				<h4>Delete character</h4>
				<p>Are you sure you want to delete <?php echo $username;?>?</p>
				<div id="verification-fails" style="display:none;">Verification Failed...</div>
				<form method="post" action="" id="character-delete-form">
					<label for="username">Verification: (Type 'yes' without quotations into the box below)</label><input type="text" name="verification" class="name" id="verification" />
					<input type="hidden" id="user-i" value="<?php echo $id; ?>"/>
					<input type="hidden" id="user-ui" value="<?php echo $userhash; ?>"/>
					<input type="submit" value="Delete" name="create" class="submit"/>
				</form> 
			</div>
		</div>
	</div>
	<div id="pie-stats">
			<div id="donut" class="graph2"></div>
			<script>
			var graph = [
			<?php foreach ($skill_array as $skill) {
					if($skill == 'hitpoints'){
					$skillc='hits';
					} else {
						$skillc=$skill;
					}
					if(experienceToLevel($check['exp_'.$skillc]) >= 10) {
						echo '{label: "'.ucwords($skill).'",  data: '.$check['exp_'.$skillc].'}, ';
					}
				} ?>
				
			];
						
							$.plot($("#donut"), graph, 
							{
								series: {
									pie: { 
										
										show: true,
										combine: {
											color: '#999',
											threshold: 0.05
										}
									}
								},
								grid: {
									hoverable: true,
									clickable: true
								},
								legend: {
									show: false
								}
							});
			</script>
	</div>		
	<div style="display:none">
		<div id="error">
			<h4>Error</h4>
			<p>You must be logged out before you can delete/modify this account</p> 
		</div>
	</div>
<?php
	} else { echo "<p>Sorry this is not your account to modify</p>"; }
	
}
?>