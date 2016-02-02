<?php

/* forumlist_body.html */
class __TwigTemplate_0b2ad8b9a0a069cf8d8fdef1543e4b579055291c9726ec9d575ba2e341adc561 extends Twig_Template
{
    public function __construct(Twig_Environment $env)
    {
        parent::__construct($env);

        $this->parent = false;

        $this->blocks = array(
        );
    }

    protected function doDisplay(array $context, array $blocks = array())
    {
        // line 1
        $context['_parent'] = (array) $context;
        $context['_seq'] = twig_ensure_traversable($this->getAttribute((isset($context["loops"]) ? $context["loops"] : null), "forumrow", array()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["forumrow"]) {
            // line 2
            echo "\t";
            if ((($this->getAttribute($context["forumrow"], "S_IS_CAT", array()) &&  !$this->getAttribute($context["forumrow"], "S_FIRST_ROW", array())) || $this->getAttribute($context["forumrow"], "S_NO_CAT", array()))) {
                // line 3
                echo "\t\t\t</ul>

\t\t\t</div>
\t\t</div>
\t";
                // line 7
                if ((isset($context["S_INDEX"]) ? $context["S_INDEX"] : null)) {
                    // line 8
                    echo "\t</div>
\t";
                }
                // line 10
                echo "\t";
            }
            // line 11
            echo "
\t";
            // line 12
            // line 13
            echo "
";
            // line 14
            if ((($this->getAttribute($context["forumrow"], "S_IS_CAT", array()) || $this->getAttribute($context["forumrow"], "S_FIRST_ROW", array())) || $this->getAttribute($context["forumrow"], "S_NO_CAT", array()))) {
                // line 15
                echo "
\t";
                // line 16
                if (((isset($context["S_INDEX"]) ? $context["S_INDEX"] : null) || ((isset($context["S_VIEWFORUM"]) ? $context["S_VIEWFORUM"] : null) &&  !((isset($context["S_DISPLAY_ACTIVE"]) ? $context["S_DISPLAY_ACTIVE"] : null) || (isset($context["TOTAL_TOPICS"]) ? $context["TOTAL_TOPICS"] : null))))) {
                    // line 17
                    echo "\t<div id=\"category-";
                    echo $this->getAttribute($context["forumrow"], "FORUM_ID", array());
                    echo "\" class=\"collapse-box\">
\t\t<h2>";
                    // line 18
                    if ($this->getAttribute($context["forumrow"], "S_IS_CAT", array())) {
                        echo "<a href=\"";
                        echo $this->getAttribute($context["forumrow"], "U_VIEWFORUM", array());
                        echo "\">";
                        echo $this->getAttribute($context["forumrow"], "FORUM_NAME", array());
                        echo "</a>";
                    } else {
                        echo $this->env->getExtension('phpbb')->lang("FORUMS");
                    }
                    echo "</h2>
\t";
                }
                // line 20
                echo "
\t<div class=\"forabg forum-blocks\">
\t\t<div class=\"inner\">
\t\t\t<ul class=\"forums\">
";
            }
            // line 25
            // line 26
            echo "
\t";
            // line 27
            if ( !$this->getAttribute($context["forumrow"], "S_IS_CAT", array())) {
                // line 28
                echo "\t\t";
                // line 29
                echo "\t\t<li class=\"row\">
\t\t\t";
                // line 30
                // line 31
                echo "\t\t\t<dl class=\"icon ";
                echo $this->getAttribute($context["forumrow"], "FORUM_IMG_STYLE", array());
                echo "\">
\t\t\t\t<dt title=\"";
                // line 32
                echo $this->getAttribute($context["forumrow"], "FORUM_FOLDER_IMG_ALT", array());
                echo "\">
\t\t\t\t\t";
                // line 33
                if ($this->getAttribute($context["forumrow"], "FORUM_IMAGE", array())) {
                    echo "<div class=\"forum-image\">";
                    echo $this->getAttribute($context["forumrow"], "FORUM_IMAGE", array());
                    echo "</div>";
                }
                // line 34
                echo "\t\t\t\t\t<a href=\"";
                echo $this->getAttribute($context["forumrow"], "U_VIEWFORUM", array());
                echo "\" class=\"icon-link\"></a>

\t\t\t\t\t";
                // line 36
                if ((twig_length_filter($this->env, $this->getAttribute($context["forumrow"], "subforum", array())) && $this->getAttribute($context["forumrow"], "S_LIST_SUBFORUMS", array()))) {
                    // line 37
                    echo "\t\t\t\t\t<div class=\"dropdown-container dropdown-button-control\">
\t\t\t\t\t\t<span title=\"";
                    // line 38
                    echo $this->getAttribute($context["forumrow"], "L_SUBFORUM_STR", array());
                    echo "\" class=\"dropdown-trigger\"></span>
\t\t\t\t\t\t<div class=\"dropdown hidden\">
\t\t\t\t\t\t\t<div class=\"dropdown-contents\">
\t\t\t\t\t\t\t";
                    // line 41
                    // line 42
                    echo "\t\t\t\t\t\t\t<strong>";
                    echo $this->getAttribute($context["forumrow"], "L_SUBFORUM_STR", array());
                    echo $this->env->getExtension('phpbb')->lang("COLON");
                    echo "</strong>
\t\t\t\t\t\t\t";
                    // line 43
                    $context['_parent'] = (array) $context;
                    $context['_seq'] = twig_ensure_traversable($this->getAttribute($context["forumrow"], "subforum", array()));
                    foreach ($context['_seq'] as $context["_key"] => $context["subforum"]) {
                        // line 44
                        echo "\t\t\t\t\t\t\t\t<a href=\"";
                        echo $this->getAttribute($context["subforum"], "U_SUBFORUM", array());
                        echo "\" class=\"subforum";
                        if ($this->getAttribute($context["subforum"], "S_UNREAD", array())) {
                            echo " unread";
                        } else {
                            echo " read";
                        }
                        echo "\" title=\"";
                        if ($this->getAttribute($context["subforum"], "UNREAD", array())) {
                            echo $this->env->getExtension('phpbb')->lang("UNREAD_POSTS");
                        } else {
                            echo $this->env->getExtension('phpbb')->lang("NO_UNREAD_POSTS");
                        }
                        echo "\">";
                        echo $this->getAttribute($context["subforum"], "SUBFORUM_NAME", array());
                        echo "</a>
\t\t\t\t\t\t\t";
                    }
                    $_parent = $context['_parent'];
                    unset($context['_seq'], $context['_iterated'], $context['_key'], $context['subforum'], $context['_parent'], $context['loop']);
                    $context = array_intersect_key($context, $_parent) + $_parent;
                    // line 46
                    echo "\t\t\t\t\t\t\t";
                    // line 47
                    echo "\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t</div>
\t\t\t\t\t</div>
\t\t\t\t\t";
                }
                // line 51
                echo "\t\t\t\t\t<div class=\"list-inner\">
\t\t\t\t\t\t<a href=\"";
                // line 52
                echo $this->getAttribute($context["forumrow"], "U_VIEWFORUM", array());
                echo "\" class=\"forumtitle\">";
                echo $this->getAttribute($context["forumrow"], "FORUM_NAME", array());
                echo "</a>
\t\t\t\t\t\t<span class=\"forum-description\">";
                // line 53
                echo $this->getAttribute($context["forumrow"], "FORUM_DESC", array());
                echo "</span>
\t\t\t\t\t\t";
                // line 54
                if ($this->getAttribute($context["forumrow"], "MODERATORS", array())) {
                    // line 55
                    echo "\t\t\t\t\t\t\t<!--<br /><span class=\"forum-moderators\"><strong>";
                    echo $this->getAttribute($context["forumrow"], "L_MODERATOR_STR", array());
                    echo $this->env->getExtension('phpbb')->lang("COLON");
                    echo "</strong> ";
                    echo $this->getAttribute($context["forumrow"], "MODERATORS", array());
                    echo "</span>-->
\t\t\t\t\t\t";
                }
                // line 57
                echo "\t\t\t\t\t</div>
\t\t\t\t</dt>

\t\t\t\t";
                // line 60
                if ($this->getAttribute($context["forumrow"], "CLICKS", array())) {
                    // line 61
                    echo "\t\t\t\t\t<dd class=\"redirect\"><span>";
                    echo $this->env->getExtension('phpbb')->lang("REDIRECTS");
                    echo $this->env->getExtension('phpbb')->lang("COLON");
                    echo " ";
                    echo $this->getAttribute($context["forumrow"], "CLICKS", array());
                    echo "</span></dd>
\t\t\t\t";
                } elseif ( !$this->getAttribute(                // line 62
$context["forumrow"], "S_IS_LINK", array())) {
                    // line 63
                    echo "\t\t\t\t\t<dd class=\"forum-stats";
                    if ($this->getAttribute($context["forumrow"], "S_UNREAD_FORUM", array())) {
                        echo " unread";
                    }
                    echo "\"><span>
\t\t\t\t\t\t";
                    // line 64
                    if ($this->getAttribute($context["forumrow"], "LAST_POST_TIME", array())) {
                        echo "(<dfn>";
                        echo $this->env->getExtension('phpbb')->lang("TOPICS");
                        echo $this->env->getExtension('phpbb')->lang("COLON");
                        echo "</dfn> ";
                        echo $this->getAttribute($context["forumrow"], "TOPICS", array());
                        echo " | <dfn>";
                        echo $this->env->getExtension('phpbb')->lang("POSTS");
                        echo $this->env->getExtension('phpbb')->lang("COLON");
                        echo "</dfn>";
                        echo $this->getAttribute($context["forumrow"], "POSTS", array());
                        echo ")
\t\t\t\t\t\t";
                        // line 65
                        if ( !(isset($context["S_IS_BOT"]) ? $context["S_IS_BOT"] : null)) {
                            echo "<a href=\"";
                            echo $this->getAttribute($context["forumrow"], "U_LAST_POST", array());
                            echo "\" title=\"";
                            if ($this->getAttribute($context["forumrow"], "S_UNREAD_FORUM", array())) {
                                echo $this->env->getExtension('phpbb')->lang("UNREAD_POSTS");
                            } else {
                                echo $this->env->getExtension('phpbb')->lang("NO_UNREAD_POSTS");
                            }
                            echo "\">";
                            echo (isset($context["LAST_POST_IMG"]) ? $context["LAST_POST_IMG"] : null);
                            echo "</a>";
                        }
                    } else {
                        echo $this->env->getExtension('phpbb')->lang("NO_POSTS");
                    }
                    // line 66
                    echo "\t\t\t\t\t\t";
                    // line 67
                    echo "\t\t\t\t\t</span></dd>

\t\t\t\t\t<dd class=\"mcp-status\"><span>
\t\t\t\t\t\t";
                    // line 70
                    if ($this->getAttribute($context["forumrow"], "U_UNAPPROVED_TOPICS", array())) {
                        // line 71
                        echo "\t\t\t\t\t\t\t<a href=\"";
                        echo $this->getAttribute($context["forumrow"], "U_UNAPPROVED_TOPICS", array());
                        echo "\">";
                        echo (isset($context["UNAPPROVED_IMG"]) ? $context["UNAPPROVED_IMG"] : null);
                        echo "</a>
\t\t\t\t\t\t";
                    } elseif ($this->getAttribute(                    // line 72
$context["forumrow"], "U_UNAPPROVED_POSTS", array())) {
                        // line 73
                        echo "\t\t\t\t\t\t\t<a href=\"";
                        echo $this->getAttribute($context["forumrow"], "U_UNAPPROVED_POSTS", array());
                        echo "\">";
                        echo (isset($context["UNAPPROVED_POST_IMG"]) ? $context["UNAPPROVED_POST_IMG"] : null);
                        echo "</a>
\t\t\t\t\t\t";
                    }
                    // line 75
                    echo "\t\t\t\t\t\t</span>
\t\t\t\t\t</dd>
\t\t\t\t";
                }
                // line 78
                echo "\t\t\t</dl>
\t\t\t";
                // line 79
                // line 80
                echo "\t\t</li>
\t\t";
                // line 81
                // line 82
                echo "\t";
            }
            // line 83
            echo "
";
            // line 84
            if ($this->getAttribute($context["forumrow"], "S_LAST_ROW", array())) {
                // line 85
                echo "\t\t\t</ul>
\t\t</div>
\t</div>
\t";
                // line 88
                if (((isset($context["S_INDEX"]) ? $context["S_INDEX"] : null) || ((isset($context["S_VIEWFORUM"]) ? $context["S_VIEWFORUM"] : null) &&  !((isset($context["S_DISPLAY_ACTIVE"]) ? $context["S_DISPLAY_ACTIVE"] : null) || (isset($context["TOTAL_TOPICS"]) ? $context["TOTAL_TOPICS"] : null))))) {
                    // line 89
                    echo "\t</div>
\t";
                }
                // line 91
                echo "
";
                // line 92
            }
            // line 94
            echo "
";
            $context['_iterated'] = true;
        }
        if (!$context['_iterated']) {
            // line 96
            echo "\t<div class=\"panel\">
\t\t<div class=\"inner\">
\t\t<strong>";
            // line 98
            echo $this->env->getExtension('phpbb')->lang("NO_FORUMS");
            echo "</strong>
\t\t</div>
\t</div>
";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['forumrow'], $context['_parent'], $context['loop']);
        $context = array_intersect_key($context, $_parent) + $_parent;
    }

    public function getTemplateName()
    {
        return "forumlist_body.html";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  319 => 98,  315 => 96,  309 => 94,  307 => 92,  304 => 91,  300 => 89,  298 => 88,  293 => 85,  291 => 84,  288 => 83,  285 => 82,  284 => 81,  281 => 80,  280 => 79,  277 => 78,  272 => 75,  264 => 73,  262 => 72,  255 => 71,  253 => 70,  248 => 67,  246 => 66,  229 => 65,  215 => 64,  208 => 63,  206 => 62,  198 => 61,  196 => 60,  191 => 57,  182 => 55,  180 => 54,  176 => 53,  170 => 52,  167 => 51,  161 => 47,  159 => 46,  136 => 44,  132 => 43,  126 => 42,  125 => 41,  119 => 38,  116 => 37,  114 => 36,  108 => 34,  102 => 33,  98 => 32,  93 => 31,  92 => 30,  89 => 29,  87 => 28,  85 => 27,  82 => 26,  81 => 25,  74 => 20,  61 => 18,  56 => 17,  54 => 16,  51 => 15,  49 => 14,  46 => 13,  45 => 12,  42 => 11,  39 => 10,  35 => 8,  33 => 7,  27 => 3,  24 => 2,  19 => 1,);
    }
}
