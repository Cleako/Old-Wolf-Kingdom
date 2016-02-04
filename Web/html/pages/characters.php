<?php
if(!defined('IN_PHPBB'))
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

$connector = new DarscapeDbc();

$subpage = mysql_real_escape_string($subpage);
$subpage = preg_replace("/[^A-Za-z0-9 ]/"," ",$subpage);
$skills = buildSQLArray($skill_array);
$character_result = $connector->query("SELECT ".$skills.",wk_players.* FROM wk_experience LEFT JOIN wk_players ON wk_experience.user = wk_players.user WHERE wk_players.username = '$subpage'");
$character = $connector->fetchArray($character_result);

?>
<div class="main">
	<div class="content">
		<article>
<?php
if($character) {
?>
	
				<h4><?php echo $subpage;?>'s hero plaque</h4>
				<p>View <?php echo $subpage;?>'s stats and achievements!</p>
				<div id="stats">
					<div id="character">
						<?php echo drawCharacter($character['haircolour'],$character['headsprite'],$character['skincolour'],$character['topcolour'],$character['male'],$character['trousercolour']); ?>
					</div>
					<div id="sm-skill">
						<?php foreach ($skill_array as $skill) { 
							if($skill == 'hitpoints'){
								$skillc='hits';
							} else {
								$skillc=$skill;
							}
						?>
						<span class="sm-skill"><a href="/<?php echo $script_directory; ?>highscores.php?skill=<?php echo $skill; ?>"><img src="/css/images/skill_icons/skill_<?php echo $skill; ?>.gif" alt="<?php echo $skill; ?>"/></a><?php echo experienceToLevel($character['exp_'.$skillc]); ?></span>
						<?php } ?>
					</div>
					<div id="sm-stats">
						<span class="sm-stats">Combat Level: <?php echo $character['combat']; ?></span>
						<span class="sm-stats">Skill Total: <?php echo $character['skill_total']; ?></span>
						<span class="sm-stats">Owner: <a href="/<?php echo $script_directory; ?>board/memberlist.php?mode=viewprofile&amp;u=<?php echo $character['phpbb_id']; ?>"><?php echo $character['owner_username']; ?></a></span>
						<?php if($character['online'] == 1) { echo '<span id="green">Online</span>'; } else { echo '<span id="red">Offline</span>'; } ?>
					</div>
				</div>
				<div id="pie-stats">
						<script type="text/javascript">
						$(document).ready(function() {
							var data = [
							<?php foreach ($skill_array as $skill) { 
								if($skill == 'hitpoints'){
									$skillc='hits';
								} else {
									$skillc=$skill;
								}
								if(experienceToLevel($character['exp_'.$skillc]) >= 10) {
									echo '{label: "'.ucwords($skill).'",  data: '.$character['exp_'.$skillc].'}, ';
								}
							} ?>
							];
							
							$.plot($("#donut"), data, 
							{
								series: {
									pie: { 
										
										show: true,
										combine: {
											color: '#999',
											threshold: 0.05,
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
						});
					</script>
					<div id="donut" class="graph">
					</div>
				</div>
<?php
	} else {
	
	}die(); 
?>
			</article>
		</div>
	</div>
