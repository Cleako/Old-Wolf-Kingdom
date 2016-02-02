<?php

// Hook by Skinny Vinny of svmods
// Mod request for logout redirection for integrated site pages, etc.

function svmods_logout_anywhere_with_redirect_option(&$hook){
    global $user, $auth;
    if (($user->data['user_id']!=ANONYMOUS) && (!$user->data['is_bot'])){
        $mode=request_var('mode','');
        if ($mode==='logout'){
            $user->session_kill();
            $user->session_begin();
            if ($redirect=request_var('redirect','')){
                header('location: '.append_sid($redirect));
                die();
            }
            $auth->acl($user->data);
            $user->setup();
        }
    }
}

function svmods_logout_url_replacement(&$hook){
    global $user, $template, $phpEx;
    if (($user->data['user_id']!=ANONYMOUS) && (!$user->data['is_bot'])){
        $url=str_replace('&','&amp;',$GLOBALS['HTTP_SERVER_VARS']['REQUEST_URI']);
        $url=str_replace("ucp.$phpEx","index.$phpEx",$url);
        $url=str_replace("mcp.$phpEx","index.$phpEx",$url);
        if (strpos($url,"posting.$phpEx")){
            $forum_id=request_var('f',0);
            if ($topic_id=request_var('t',0)){
                $url="viewtopic.$phpEx?f=$forum_id&amp;t=$topic_id";
            }
            else{
                $url="viewforum.$phpEx?f=$forum_id";
            }
        }
        $delim=(strpos($url,'?') === false) ? '?' : '&amp;';
        $template->_rootref['U_LOGIN_LOGOUT']=append_sid($url.$delim.'mode=logout');
    }
}

$phpbb_hook->register('phpbb_user_session_handler', 'svmods_logout_anywhere_with_redirect_option');
$phpbb_hook->register(array('template','display'), 'svmods_logout_url_replacement'); 