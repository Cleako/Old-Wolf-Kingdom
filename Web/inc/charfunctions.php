<?php
	
	$statNames = array('exp_attack', 'exp_defense', 'exp_strength', 'exp_hits', 'exp_ranged', 'exp_prayer', 'exp_magic', 'exp_cooking', 'exp_woodcut', 'exp_fletching', 'exp_fishing', 'exp_firemaking', 'exp_crafting', 'exp_smithing', 'exp_mining', 'exp_herblaw', 'exp_agility', 'exp_thieving');

	$statNames2 = array('attack', 'defense', 'strength', 'hits', 'ranged', 'prayer', 'magic', 'cooking', 'woodcut', 'fletching', 'fishing', 'firemaking', 'crafting', 'smithing', 'mining', 'herblaw', 'agility', 'thieving');
	
	$experienceArray = array(0, 83, 174, 276, 388, 512, 650, 801, 969, 1154, 1358, 1584, 1833, 2107, 2411, 2746, 3115, 3523, 3973, 4470, 5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363, 14833, 16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224, 41171, 45529, 50339, 55649, 61512, 67983, 75127, 83014, 91721, 101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466, 247886, 273742, 302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032, 668051, 737627, 814445, 899257, 992895, 1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068, 2192818, 2421087, 2673114, 2951373, 3258594, 3597792, 3972294, 4385776, 4842295, 5346332, 5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629, 11805606, 13034431, 14391160, 15889109, 17542976, 19368992, 21385073, 23611006, 26068632, 28782069, 31777943, 35085654, 38737661, 42769801, 47221641, 52136869, 57563718, 63555443, 70170840, 77474828, 85539082, 94442737, 104273167);
	for($index = 0, $exp = 0;$index < 98;$index++) {
		$offset = intval(($index + 1) + 300 * pow(2, ($index + 1) / 7));
		$exp += $offset;
		$experienceArray[$index] = ($exp & 0xffffffc) / 4;
	}

	function usernameToHash($s) {
		$s = strtolower($s);
		$s1 = '';
		for ($i = 0;$i < strlen($s);$i++) {
			$c = $s{$i};
			if ($c >= 'a' && $c <= 'z')
				$s1 = $s1.$c;
			else if ($c >= '0' && $c <= '9')
				$s1 = $s1.$c;
			else
				$s1 = $s1.' ';
		}

		$s1 = trim($s1);
		if (strlen($s1) > 12)
			$s1 = substring($s1, 0, 12);

		$l = 0;
		for ($j = 0;$j < strlen($s1);$j++) {
			$c1 = $s1{$j};
			$l *= 37;
			if ($c1 >= 'a' && $c1 <= 'z')
				$l += (1 + ord($c1)) - 97;
			else if ($c1 >= '0' && $c1 <= '9')
				$l += (27 + ord($c1)) - 48;
		}
		return $l;
	}

	function hashToUsername($l) {
		if ($l < 0)
			return 'invalid_name';
		$s = '';
		while ($l != 0) {
			$i = floor(floatval($l % 37));
			$l = floor(floatval($l / 37));
			if ($i == 0)
				$s = ' '.$s;
			else if ($i < 27) {
				if ($l % 37 == 0)
					$s = chr(($i + 65) - 1).$s;
				else
					$s = chr(($i + 97) - 1).$s;
			}
			else {
				$s = chr(($i + 48) - 27).$s;
			}
		}
		return $s;
	}
	
	function experienceToLevel($exp) {
		global $experienceArray;
		for($level = 0;$level < 98;$level++) {
		if($exp >= $experienceArray[$level]) {
			continue;
		}
		return ($level + 1);
		}
	return 99;
	}
	
	function encode_username($username) {
	$username = strtolower($username);
	$clean = '';
	for($i = 0;$i < strlen($username);$i++) {
		$c = ord($username{$i});
		if($c >= 97 && $c <= 122) {
			$clean .= chr($c);
		}
		else if($c >= 48 && $c <= 57) {
			$clean .= chr($c);
		}
		else {
			$clean .= ' ';
		}
	}
	$clean = trim($clean);
	if(strlen($clean) > 12) {
		$clean = substr($clean, 0, 12);
	}
	$hash = '0';
	for($i = 0;$i < strlen($clean);$i++) {
		$c = ord($clean{$i});
		$hash = bcmul($hash, 37);
		if($c >= 97 && $c <= 122) {
			$hash = bcadd($hash, (1 + $c) - 97);
		}
		else if($c >= 48 && $c <= 57) {
			$hash = bcadd($hash, (27 + $c) - 48);
		}
	}
	$rewriteValue = explode(".", $hash);
	return $rewriteValue[0];

	#return $hash;
}

	function combatCalc($attack, $defense, $strength, $hits, $ranged, $magic, $prayer){
		$calcatkdefstr = (($attack + $defense + $strength + $hits) * .25);
		$calcmagicprayer = ($magic + $prayer) * 0.125;
						
		if(($attack + $strength) < ($ranged * 1.5)){
			$defhits = ($defense + $hits) * 0.25;
			$fixrange = $ranged * 0.375;
			$newcb = $defhits + $fixrange + $calcmagicprayer;
			$base = 0;
		} else {
			$base = 1;
			$newcb = $calcatkdefstr + $calcmagicprayer;
		}

		return $newcb;
	
	}
	
	function headSprite($num){
		
		return $head;
	}	

	function drawCharacter($hc, $hsprite, $sc, $tc, $gender, $pc){
		if($hsprite == 1) {
			$hsprite = 0;
		} else if($hsprite == 4) {
			$hsprite = 1;
		} else if($hsprite == 6) {
			$hsprite = 2;
		} else if($hsprite == 7) {
			$hsprite = 3;
		} else if($hsprite == 8) {
			$hsprite = 4;
		}
		$gender = ($gender == 1) ? 0 : 1;
		return '<img src="/css/images/character/heads/c'.$hc.'t'.$hsprite.'sc'.$sc.'.png" id="head" alt="head" />
				<img src="/css/images/character/bodies/c'.$tc.'sc'.$sc.'t'.$gender.'.png" id="body" alt="body" />
				<img src="/css/images/character/legs/c'.$pc.'.png" id="leg" alt="leg" />';
	}
	
?>
	
	