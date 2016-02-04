<?php
#Copyright 2008-2010 Myricomp LLC All rights reserved
#
#   *****email bugs@peoplesign.com to report a problem*****
#

# Define error message strings
define('ERROR_BAD_CONFIG', 'peoplesign is not configured properly: '
	     . 'createClientSessionFail. Please contact the '
	     . 'administrator of this site.');
define('ERROR_UNAVAILABLE', 'peoplesign is unavailble');
define('ERROR_UNEXPECTED', 'unexpected status from getPeoplesignSessionStatus');
define('ERROR_EMPTY_KEY', 'received a private key that was all '
        . 'whitespace or empty');

define('NO_FRAMES_MESSAGE', 'This site requires a security check to help stop spam.  Return to this page after completing the security check at this link: ');

define('ERROR_NO_SOCKET', 'Can\'t get socket to peoplesign host');
define('ERROR_EXCESSIVE_DATA', 'excessive data returned from '
        . 'peoplesign webservice call');
define('ERROR_PREAMBLE', 'ERROR: peoplesign client');
define('ERROR_VISITOR_IP', 'invalid visitorIP');
define('ERROR_SERVER_STATUS', 'unexpected server status');
define('ERROR_BAD_RESPONSE', 'bad HTTP response from server');

?>
