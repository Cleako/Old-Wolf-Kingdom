<?php
#Copyright 2008-2010 Myricomp LLC All rights reserved
#
#   *****email bugs@peoplesign.com to report a problem*****
#


function getPostVar($varName, $varType) {
   if ($varName){
      if (array_key_exists($varName,$_POST)){
            return $_POST[$varName];
      }
   }
   return;
   
}
function printError ($message){
    echo ERROR_PREAMBLE . "$message\n";
    return;
}

?>
