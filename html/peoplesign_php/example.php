<?php
//copyright 2009 Bryan Hooker and MyriComp LLC
/*Description:  Example php  page that uses the peoplesign client to
                setup a CAPTCHA. Uses a single peoplesign client function
		call:  getPeoplesignHTML
*/

session_start();
?>

<html>
<head>
   <title>Example Usage of Peoplesign</title>
   <meta http-equiv="Content-Language" content="en-us" />
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <meta http-equiv="Cache-Control" content="no-cache" />
</head>
<body style="width: 500px; margin-left: auto; margin-right:auto;">
<div>
   <p>This page is a demonstration of the peoplesign php plugin</p>
   <form method="post" action="protectedPage.php">

<?php
include("peoplesignClient.php");

#We create a name for this location.  This name will later be used as a
# session variable name, so make sure it's not already used.

$thisPeoplesignLocation = "example";

#WARNING!!! The following key (293b6433379cf498dfa3b4f7437e17ad) is a sample
# for testing purposes only.  Eventually it will stop working.
# You can get a personal key at
# http://peoplesign.com/main/registerToHostPeoplesign.html
# It's quick, easy and free*/

$my_peoplesign_key = "293b6433379cf498dfa3b4f7437e17ad";

#We can customize the appearance of peoplesign at this location.  
# $peoplesign_options can be either an array of name/value pairs or a specially
# formated string.  If it's empty, then default values will
# be used.

$peoplesign_options = "";

#Use a specially formatted option string.  This can be obtained from 
# a customization page on peoplesign.com.  This page allows you to easily
# experiment with different options.  When you find a set of options you like,
# you can easily copy and paste your customized option string into your code.

#$peoplesign_options = "language=english&useDispersedPics=false&numPanels=2&numSmallPhotos=6&useDragAndDrop=false&challengeType=pairThePhoto&category=(all)&hideResponseAreaWhenInactive=false";

#Alternatively, use an array 
#$peoplesign_options = array("challengeType"         => "pairThePhoto",
#                         "numPanels"             => "3",
#                         "useDispersedPics"      => "false",
#                         "language"              => "french"
#);
#

print getPeoplesignHTML($my_peoplesign_key,
                        $peoplesign_options,
                        $thisPeoplesignLocation);
?>

      <input type="submit" value="submit">
   </form>

</div>

</body>

</html>
