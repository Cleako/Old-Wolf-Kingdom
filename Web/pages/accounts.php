<?php
if(!defined('IN_PHPBB'))
{
	die("You do not have permission to access this file.");
}
if($user->data['is_registered']) {
	$phpbb_user_id = $user->data['user_id'];
	
	$connector = new DarscapeDbc();
	$characters_result = $connector->query("SELECT * FROM wk_players WHERE phpbb_id=$phpbb_user_id");
?>
	<div class="main">
		<div class="content">
			<article>	
				<h4>Account Management</h4>
				<p>Manage your hero accounts, view their statistics, and change up your gear!</p>
				<a id="inline" href="#create" class="button">Create Hero</a>
				<?php if(mysql_num_rows($characters_result) > 0){ ?>
					<div id="sm-list">
						<ul>
					<?php 
						$i=0;
						while($row = $connector->fetchArray($characters_result)){ 
					?>
							<a href="#" onClick="javascript:loadContent('<?php echo $row['username']; ?>','<?php echo $row['user']; ?>','<?php echo $row['phpbb_id']; ?>','<?php echo $row['haircolour']; ?>','<?php echo $row['headsprite']; ?>','<?php echo $row['skincolour']; ?>','<?php echo $row['topcolour']; ?>','<?php echo $row['male']; ?>','<?php echo $row['trousercolour']; ?>','<?php echo $row['combat']; ?>','<?php echo $row['online']; ?>');"><li id="toggle"><?php echo $row['username']; ?></li></a>
					<?php
						if($i==0){ 
							$username=$row['username'];
							$user=$row['user'];
							$id=$row['phpbb_id'];
							
							$hc = $row['haircolour'];
							$hsprite = $row['headsprite'];
							$sc = $row['skincolour'];
							$tc = $row['topcolour'];
							$gender = $row['male'];
							$pc = $row['trousercolour'];
							
							$combat = $row['combat'];
							$online = $row['online'];
						}
						$i++;
						}
					?>
						</ul>
					</div>
					<script type="text/javascript" language="JavaScript">
						$(document).ready(function() {
							$.post("/js/account.php", {username: '<?php echo $username; ?>', userenc: '<?php echo $user; ?>', owner: '<?php echo $id; ?>', hair: <?php echo $hc; ?>, head: <?php echo $hsprite; ?>, skin: <?php echo $sc; ?>, top: <?php echo $tc; ?>, gen: <?php echo $gender; ?>, pants: <?php echo $pc; ?>, combat: <?php echo $combat; ?>, online: <?php echo $online; ?>} ,function(data) {
								$("#character-details").html(data).show();
								$("a#inline").fancybox({
									'hideOnContentClick': false,
									'overlayColor': '#000000',
									'padding': 0,
								});
							});
						});
					</script>
					<div id="character-details">
					</div>
				<?php } ?>
			</article>
		</div>
	</div>
	<div style="display:none">
		<div id="create">
			<h4>Create a new character</h4>
			<p>Fill in the blanks below to create a new hero!</p>
			<div id="create-form">
				<div id="name-fails" style="display:none;">Username must be between 4 and 12 characters!</div>
				<div id="pass-fails" style="display:none;">Password must be longer than 5 characters</div>
				<div id="user-fails" style="display:none;">Username already taken!</div>
				<div id="user-passes" style="display:none;">Character created successfully!</div>
				<form method="post" action="" id="character-creation-form">
					<label for="username">Username: </label><input type="text" name="username" class="name" id="name" />
					<label for="password">Password: </label><input type="password" name="password" class="password" id="password" />
					<input type="submit" value="Create" name="create" class="submit"/>
				</form> 
			</div>
		</div>
	</div>
<?php
	}
?>