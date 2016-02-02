<?php

/* acp_styles.html */
class __TwigTemplate_7951729f74f78ae4f510d882624e4cecfb5da1c135270c2680fb7b70b6767a68 extends Twig_Template
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
        $location = "overall_header.html";
        $namespace = false;
        if (strpos($location, '@') === 0) {
            $namespace = substr($location, 1, strpos($location, '/') - 1);
            $previous_look_up_order = $this->env->getNamespaceLookUpOrder();
            $this->env->setNamespaceLookUpOrder(array($namespace, '__main__'));
        }
        $this->loadTemplate("overall_header.html", "acp_styles.html", 1)->display($context);
        if ($namespace) {
            $this->env->setNamespaceLookUpOrder($previous_look_up_order);
        }
        // line 2
        echo "
<a id=\"maincontent\"></a>

";
        // line 5
        if ((isset($context["S_STYLE_DETAILS"]) ? $context["S_STYLE_DETAILS"] : null)) {
            // line 6
            echo "\t<a href=\"";
            echo (isset($context["U_ACTION"]) ? $context["U_ACTION"] : null);
            echo "\" style=\"float: ";
            echo (isset($context["S_CONTENT_FLOW_END"]) ? $context["S_CONTENT_FLOW_END"] : null);
            echo ";\">&laquo; ";
            echo $this->env->getExtension('phpbb')->lang("BACK");
            echo "</a>
";
        }
        // line 8
        echo "
";
        // line 9
        if ((isset($context["S_CONFIRM_ACTION"]) ? $context["S_CONFIRM_ACTION"] : null)) {
            // line 10
            echo "<form id=\"confirm\" method=\"post\" action=\"";
            echo (isset($context["S_CONFIRM_ACTION"]) ? $context["S_CONFIRM_ACTION"] : null);
            echo "\">

<fieldset>
\t<h1>";
            // line 13
            echo (isset($context["MESSAGE_TITLE"]) ? $context["MESSAGE_TITLE"] : null);
            echo "</h1>
\t<p>";
            // line 14
            echo (isset($context["MESSAGE_TEXT"]) ? $context["MESSAGE_TEXT"] : null);
            echo "</p>
\t";
            // line 15
            if ((isset($context["S_CONFIRM_DELETE"]) ? $context["S_CONFIRM_DELETE"] : null)) {
                // line 16
                echo "\t<label><input type=\"checkbox\" class=\"checkbox\" name=\"confirm_delete_files\" /> ";
                echo $this->env->getExtension('phpbb')->lang("DELETE_FROM_FS");
                echo "</label>
\t";
            }
            // line 18
            echo "
\t";
            // line 19
            echo (isset($context["S_HIDDEN_FIELDS"]) ? $context["S_HIDDEN_FIELDS"] : null);
            echo "

\t<div style=\"text-align: center;\">
\t\t<input type=\"submit\" name=\"confirm\" value=\"";
            // line 22
            echo $this->env->getExtension('phpbb')->lang("YES");
            echo "\" class=\"button2\" />&nbsp;
\t\t<input type=\"submit\" name=\"cancel\" value=\"";
            // line 23
            echo $this->env->getExtension('phpbb')->lang("NO");
            echo "\" class=\"button2\" />
\t</div>

</fieldset>

</form>
";
        } else {
            // line 30
            echo "
";
            // line 31
            if ((isset($context["L_TITLE"]) ? $context["L_TITLE"] : null)) {
                echo "<h1>";
                echo $this->env->getExtension('phpbb')->lang("TITLE");
                echo "</h1>";
            }
            // line 32
            echo "
";
            // line 33
            if ((isset($context["L_EXPLAIN"]) ? $context["L_EXPLAIN"] : null)) {
                echo "<p>";
                echo $this->env->getExtension('phpbb')->lang("EXPLAIN");
                echo "</p>";
            }
            // line 34
            echo "
<fieldset class=\"quick\">
\t<span class=\"small\"><a href=\"https://www.phpbb.com/go/customise/styles/3.1\" target=\"_blank\">";
            // line 36
            echo $this->env->getExtension('phpbb')->lang("BROWSE_STYLES_DATABASE");
            echo "</a></span>
</fieldset>

<form id=\"acp_styles\" method=\"post\" action=\"";
            // line 39
            echo (isset($context["U_ACTION"]) ? $context["U_ACTION"] : null);
            echo "\">
";
            // line 40
            echo (isset($context["S_HIDDEN_FIELDS"]) ? $context["S_HIDDEN_FIELDS"] : null);
            echo "
";
            // line 41
            echo (isset($context["S_FORM_TOKEN"]) ? $context["S_FORM_TOKEN"] : null);
            echo "

";
            // line 43
            if ((isset($context["S_STYLE_DETAILS"]) ? $context["S_STYLE_DETAILS"] : null)) {
                // line 44
                echo "\t<input type=\"hidden\" name=\"id\" value=\"";
                echo (isset($context["STYLE_ID"]) ? $context["STYLE_ID"] : null);
                echo "\" />
\t<fieldset>
\t<dl>
\t\t<dt><label for=\"name\">";
                // line 47
                echo $this->env->getExtension('phpbb')->lang("STYLE_NAME");
                echo $this->env->getExtension('phpbb')->lang("COLON");
                echo "</label></dt>
\t\t<dd><input type=\"text\" id=\"name\" name=\"style_name\" value=\"";
                // line 48
                echo (isset($context["STYLE_NAME"]) ? $context["STYLE_NAME"] : null);
                echo "\" /></dd>
\t</dl>
\t<dl>
\t\t<dt><label>";
                // line 51
                echo $this->env->getExtension('phpbb')->lang("STYLE_PATH");
                echo $this->env->getExtension('phpbb')->lang("COLON");
                echo "</label></dt>
\t\t<dd><strong>";
                // line 52
                echo (isset($context["STYLE_PATH"]) ? $context["STYLE_PATH"] : null);
                echo "</strong></dd>
\t</dl>
\t<dl>
\t\t<dt><label for=\"name\">";
                // line 55
                echo $this->env->getExtension('phpbb')->lang("COPYRIGHT");
                echo $this->env->getExtension('phpbb')->lang("COLON");
                echo "</label></dt>
\t\t<dd><strong>";
                // line 56
                echo (isset($context["STYLE_COPYRIGHT"]) ? $context["STYLE_COPYRIGHT"] : null);
                echo "</strong></dd>
\t</dl>
\t<dl>
\t\t<dt><label for=\"style_parent\">";
                // line 59
                echo $this->env->getExtension('phpbb')->lang("INHERITING_FROM");
                echo $this->env->getExtension('phpbb')->lang("COLON");
                echo "</label></dt>
\t\t<dd><select id=\"style_parent\" name=\"style_parent\">
\t\t\t<option value=\"\"";
                // line 61
                if (((isset($context["STYLE_PARENT"]) ? $context["STYLE_PARENT"] : null) == 0)) {
                    echo " selected=\"selected\"";
                }
                echo "> - </option>
\t\t\t";
                // line 62
                $context['_parent'] = (array) $context;
                $context['_seq'] = twig_ensure_traversable($this->getAttribute((isset($context["loops"]) ? $context["loops"] : null), "parent_styles", array()));
                foreach ($context['_seq'] as $context["_key"] => $context["parent_styles"]) {
                    // line 63
                    echo "\t\t\t\t<option value=\"";
                    echo $this->getAttribute($context["parent_styles"], "STYLE_ID", array());
                    echo "\"";
                    if (($this->getAttribute($context["parent_styles"], "STYLE_ID", array()) == (isset($context["STYLE_PARENT"]) ? $context["STYLE_PARENT"] : null))) {
                        echo " selected=\"selected\"";
                    }
                    echo ">";
                    echo $this->getAttribute($context["parent_styles"], "SPACER", array());
                    echo $this->getAttribute($context["parent_styles"], "STYLE_NAME", array());
                    echo "</option>
\t\t\t";
                }
                $_parent = $context['_parent'];
                unset($context['_seq'], $context['_iterated'], $context['_key'], $context['parent_styles'], $context['_parent'], $context['loop']);
                $context = array_intersect_key($context, $_parent) + $_parent;
                // line 65
                echo "\t\t</select></dd>
\t</dl>
\t<dl>
\t\t<dt><label for=\"style_active\">";
                // line 68
                echo $this->env->getExtension('phpbb')->lang("STYLE_ACTIVE");
                echo $this->env->getExtension('phpbb')->lang("COLON");
                echo "</label></dt>
\t\t<dd><label><input type=\"radio\" class=\"radio\" name=\"style_active\" value=\"1\"";
                // line 69
                if ((isset($context["S_STYLE_ACTIVE"]) ? $context["S_STYLE_ACTIVE"] : null)) {
                    echo " id=\"style_active\" checked=\"checked\"";
                }
                echo " /> ";
                echo $this->env->getExtension('phpbb')->lang("YES");
                echo "</label>
\t\t\t<label><input type=\"radio\" class=\"radio\" name=\"style_active\" value=\"0\"";
                // line 70
                if ( !(isset($context["S_STYLE_ACTIVE"]) ? $context["S_STYLE_ACTIVE"] : null)) {
                    echo " id=\"style_active\" checked=\"checked\"";
                }
                echo " /> ";
                echo $this->env->getExtension('phpbb')->lang("NO");
                echo "</label></dd>
\t</dl>
\t";
                // line 72
                if ( !(isset($context["S_STYLE_DEFAULT"]) ? $context["S_STYLE_DEFAULT"] : null)) {
                    // line 73
                    echo "\t\t<dl>
\t\t\t<dt><label for=\"style_default\">";
                    // line 74
                    echo $this->env->getExtension('phpbb')->lang("STYLE_DEFAULT");
                    echo $this->env->getExtension('phpbb')->lang("COLON");
                    echo "</label></dt>
\t\t\t<dd><label><input type=\"radio\" class=\"radio\" name=\"style_default\" value=\"1\" /> ";
                    // line 75
                    echo $this->env->getExtension('phpbb')->lang("YES");
                    echo "</label>
\t\t\t\t<label><input type=\"radio\" class=\"radio\" id=\"style_default\" name=\"style_default\" value=\"0\" checked=\"checked\" /> ";
                    // line 76
                    echo $this->env->getExtension('phpbb')->lang("NO");
                    echo "</label></dd>
\t\t</dl>
\t";
                }
                // line 79
                echo "\t</fieldset>

\t<fieldset class=\"submit-buttons\">
\t\t<legend>";
                // line 82
                echo $this->env->getExtension('phpbb')->lang("SUBMIT");
                echo "</legend>
\t\t<input class=\"button1\" type=\"submit\" name=\"update\" value=\"";
                // line 83
                echo $this->env->getExtension('phpbb')->lang("SUBMIT");
                echo "\" />&nbsp;
\t\t<input class=\"button2\" type=\"reset\" id=\"reset\" name=\"reset\" value=\"";
                // line 84
                echo $this->env->getExtension('phpbb')->lang("RESET");
                echo "\" />
\t\t";
                // line 85
                echo (isset($context["S_FORM_TOKEN"]) ? $context["S_FORM_TOKEN"] : null);
                echo "
\t</fieldset>
";
            }
            // line 88
            echo "
";
            // line 89
            if (twig_length_filter($this->env, $this->getAttribute((isset($context["loops"]) ? $context["loops"] : null), "styles_list", array()))) {
                // line 90
                echo "\t";
                // line 91
                echo "\t<table class=\"table1 styles\">
\t<thead>
\t<tr>
\t\t<th>";
                // line 94
                echo $this->env->getExtension('phpbb')->lang("STYLE_NAME");
                echo "</th>
\t\t";
                // line 95
                if ( !(isset($context["STYLES_LIST_HIDE_COUNT"]) ? $context["STYLES_LIST_HIDE_COUNT"] : null)) {
                    echo "<th width=\"10%\" style=\"white-space: nowrap; text-align: center;\">";
                    echo $this->env->getExtension('phpbb')->lang("STYLE_USED_BY");
                    echo "</th>";
                }
                // line 96
                echo "\t\t<th width=\"25%\" style=\"white-space: nowrap; text-align: center;\">";
                echo $this->env->getExtension('phpbb')->lang("ACTIONS");
                echo "</th>
\t\t";
                // line 97
                echo (isset($context["STYLES_LIST_EXTRA"]) ? $context["STYLES_LIST_EXTRA"] : null);
                echo "
\t\t<th>&nbsp;</th>
\t</tr>
\t</thead>
\t";
                // line 101
                $context['_parent'] = (array) $context;
                $context['_seq'] = twig_ensure_traversable($this->getAttribute((isset($context["loops"]) ? $context["loops"] : null), "styles_list", array()));
                foreach ($context['_seq'] as $context["_key"] => $context["styles_list"]) {
                    // line 102
                    echo "\t<tbody id=\"styles-list-";
                    echo $this->getAttribute($context["styles_list"], "S_ROW_COUNT", array());
                    echo "\">
\t<tr class=\"row-highlight";
                    // line 103
                    if (($this->getAttribute($context["styles_list"], "STYLE_ID", array()) &&  !$this->getAttribute($context["styles_list"], "STYLE_ACTIVE", array()))) {
                        echo " row-inactive";
                    }
                    echo "\">
\t\t";
                    // line 104
                    if (($this->getAttribute($context["styles_list"], "LEVEL", array()) % 2 == 1)) {
                        // line 105
                        echo "\t\t\t";
                        if (($this->getAttribute((isset($context["definition"]) ? $context["definition"] : null), "ROW_CLASS", array()) == "row1a")) {
                            $value = "row1b";
                            $context['definition']->set('ROW_CLASS', $value);
                        } else {
                            $value = "row1a";
                            $context['definition']->set('ROW_CLASS', $value);
                        }
                        // line 106
                        echo "\t\t";
                    } else {
                        // line 107
                        echo "\t\t\t";
                        if (($this->getAttribute((isset($context["definition"]) ? $context["definition"] : null), "ROW_CLASS", array()) == "row2a")) {
                            $value = "row2b";
                            $context['definition']->set('ROW_CLASS', $value);
                        } else {
                            $value = "row2a";
                            $context['definition']->set('ROW_CLASS', $value);
                        }
                        // line 108
                        echo "\t\t";
                    }
                    // line 109
                    echo "\t\t<td class=\"";
                    echo $this->getAttribute((isset($context["definition"]) ? $context["definition"] : null), "ROW_CLASS", array());
                    echo "\" style=\"padding-";
                    echo (isset($context["S_CONTENT_FLOW_BEGIN"]) ? $context["S_CONTENT_FLOW_BEGIN"] : null);
                    echo ": ";
                    echo $this->getAttribute($context["styles_list"], "PADDING", array());
                    echo "px;\">
\t\t\t";
                    // line 110
                    if ((($this->getAttribute($context["styles_list"], "STYLE_ID", array()) && ($this->getAttribute($context["styles_list"], "COMMENT", array()) == "")) && $this->getAttribute($context["styles_list"], "STYLE_ACTIVE", array()))) {
                        // line 111
                        echo "\t\t\t\t<div class=\"default-style\" style=\"display: none; float: ";
                        echo (isset($context["S_CONTENT_FLOW_END"]) ? $context["S_CONTENT_FLOW_END"] : null);
                        echo ";\">
\t\t\t\t\t<input class=\"radio\" type=\"radio\" name=\"default\" value=\"";
                        // line 112
                        echo $this->getAttribute($context["styles_list"], "STYLE_ID", array());
                        echo "\"";
                        if ($this->getAttribute($context["styles_list"], "DEFAULT", array())) {
                            echo " checked=\"checked\"";
                        } else {
                            $value = 1;
                            $context['definition']->set('S_DEFAULT', $value);
                        }
                        echo " title=\"";
                        echo $this->env->getExtension('phpbb')->lang("STYLE_DEFAULT");
                        echo "\" />
\t\t\t\t</div>
\t\t\t";
                    }
                    // line 115
                    echo "\t\t\t";
                    if (($this->getAttribute($context["styles_list"], "DEFAULT", array()) || $this->getAttribute($context["styles_list"], "SHOW_COPYRIGHT", array()))) {
                        // line 116
                        echo "\t\t\t\t<strong>";
                        echo $this->getAttribute($context["styles_list"], "STYLE_NAME", array());
                        echo "</strong>
\t\t\t\t";
                        // line 117
                        if (($this->getAttribute($context["styles_list"], "SHOW_COPYRIGHT", array()) && ($this->getAttribute($context["styles_list"], "COMMENT", array()) == ""))) {
                            echo "<span><br />";
                            echo $this->getAttribute($context["styles_list"], "STYLE_COPYRIGHT", array());
                            echo "</span>";
                        }
                        // line 118
                        echo "\t\t\t";
                    } else {
                        // line 119
                        echo "\t\t\t\t<span>";
                        echo $this->getAttribute($context["styles_list"], "STYLE_NAME", array());
                        echo "</span>
\t\t\t";
                    }
                    // line 121
                    echo "\t\t\t";
                    if (($this->getAttribute($context["styles_list"], "COMMENT", array()) != "")) {
                        // line 122
                        echo "\t\t\t\t<span class=\"error\"><br />";
                        echo $this->getAttribute($context["styles_list"], "COMMENT", array());
                        echo "</span>
\t\t\t";
                    }
                    // line 124
                    echo "\t\t\t";
                    if (( !$this->getAttribute($context["styles_list"], "STYLE_ID", array()) && ($this->getAttribute($context["styles_list"], "COMMENT", array()) == ""))) {
                        // line 125
                        echo "\t\t\t\t<span class=\"style-path\"><br />";
                        echo $this->env->getExtension('phpbb')->lang("STYLE_PATH");
                        echo $this->env->getExtension('phpbb')->lang("COLON");
                        echo " ";
                        echo $this->getAttribute($context["styles_list"], "STYLE_PATH_FULL", array());
                        echo "</span>
\t\t\t";
                    }
                    // line 127
                    echo "\t\t</td>
\t\t";
                    // line 128
                    if ( !(isset($context["STYLES_LIST_HIDE_COUNT"]) ? $context["STYLES_LIST_HIDE_COUNT"] : null)) {
                        // line 129
                        echo "\t\t\t<td class=\"";
                        echo $this->getAttribute((isset($context["definition"]) ? $context["definition"] : null), "ROW_CLASS", array());
                        echo " users\">";
                        echo $this->getAttribute($context["styles_list"], "USERS", array());
                        echo "</td>
\t\t";
                    }
                    // line 131
                    echo "\t\t<td class=\"";
                    echo $this->getAttribute((isset($context["definition"]) ? $context["definition"] : null), "ROW_CLASS", array());
                    echo " actions\">
\t\t\t";
                    // line 132
                    $context['_parent'] = (array) $context;
                    $context['_seq'] = twig_ensure_traversable($this->getAttribute($context["styles_list"], "actions", array()));
                    foreach ($context['_seq'] as $context["_key"] => $context["actions"]) {
                        // line 133
                        echo "\t\t\t\t";
                        if (($this->getAttribute($context["actions"], "S_ROW_COUNT", array()) > 0)) {
                            echo " | ";
                        }
                        // line 134
                        echo "\t\t\t\t";
                        if ($this->getAttribute($context["actions"], "U_ACTION", array())) {
                            // line 135
                            echo "\t\t\t\t\t<a href=\"";
                            echo $this->getAttribute($context["actions"], "U_ACTION", array());
                            echo "\"";
                            echo $this->getAttribute($context["actions"], "U_ACTION_ATTR", array());
                            echo ">";
                            echo $this->getAttribute($context["actions"], "L_ACTION", array());
                            echo "</a>
\t\t\t\t";
                        }
                        // line 137
                        echo "\t\t\t\t";
                        echo $this->getAttribute($context["actions"], "HTML", array());
                        echo "
\t\t\t";
                    }
                    $_parent = $context['_parent'];
                    unset($context['_seq'], $context['_iterated'], $context['_key'], $context['actions'], $context['_parent'], $context['loop']);
                    $context = array_intersect_key($context, $_parent) + $_parent;
                    // line 139
                    echo "\t\t</td>
\t\t";
                    // line 140
                    echo $this->getAttribute($context["styles_list"], "EXTRA", array());
                    echo "
\t\t<td class=\"";
                    // line 141
                    echo $this->getAttribute((isset($context["definition"]) ? $context["definition"] : null), "ROW_CLASS", array());
                    echo " mark\" width=\"20\">
\t\t\t";
                    // line 142
                    if ($this->getAttribute($context["styles_list"], "STYLE_ID", array())) {
                        // line 143
                        echo "\t\t\t\t<input class=\"checkbox\" type=\"checkbox\" name=\"ids[]\" value=\"";
                        echo $this->getAttribute($context["styles_list"], "STYLE_ID", array());
                        echo "\" />
\t\t\t";
                    } else {
                        // line 145
                        echo "\t\t\t\t";
                        if (($this->getAttribute($context["styles_list"], "COMMENT", array()) != "")) {
                            // line 146
                            echo "\t\t\t\t\t&nbsp;
\t\t\t\t";
                        } else {
                            // line 148
                            echo "\t\t\t\t\t<input class=\"checkbox\" type=\"checkbox\" name=\"dirs[]\" value=\"";
                            echo $this->getAttribute($context["styles_list"], "STYLE_PATH", array());
                            echo "\" />
\t\t\t\t";
                        }
                        // line 150
                        echo "\t\t\t";
                    }
                    // line 151
                    echo "\t\t</td>
\t</tr>
\t</tbody>
\t";
                }
                $_parent = $context['_parent'];
                unset($context['_seq'], $context['_iterated'], $context['_key'], $context['styles_list'], $context['_parent'], $context['loop']);
                $context = array_intersect_key($context, $_parent) + $_parent;
                // line 155
                echo "\t</table>
";
            }
            // line 157
            echo "
";
            // line 158
            if (twig_length_filter($this->env, $this->getAttribute((isset($context["loops"]) ? $context["loops"] : null), "extra_actions", array()))) {
                // line 159
                echo "\t<fieldset class=\"quick\">
\t\t";
                // line 160
                $context['_parent'] = (array) $context;
                $context['_seq'] = twig_ensure_traversable($this->getAttribute((isset($context["loops"]) ? $context["loops"] : null), "extra_actions", array()));
                foreach ($context['_seq'] as $context["_key"] => $context["extra_actions"]) {
                    // line 161
                    echo "\t\t\t<input type=\"submit\" name=\"";
                    echo $this->getAttribute($context["extra_actions"], "ACTION_NAME", array());
                    echo "\" class=\"button2\" value=\"";
                    echo $this->getAttribute($context["extra_actions"], "L_ACTION", array());
                    echo "\" />
\t\t";
                }
                $_parent = $context['_parent'];
                unset($context['_seq'], $context['_iterated'], $context['_key'], $context['extra_actions'], $context['_parent'], $context['loop']);
                $context = array_intersect_key($context, $_parent) + $_parent;
                // line 163
                echo "\t</fieldset>
";
            }
            // line 165
            echo "
</form>

";
        }
        // line 169
        echo "
";
        // line 170
        $location = "overall_footer.html";
        $namespace = false;
        if (strpos($location, '@') === 0) {
            $namespace = substr($location, 1, strpos($location, '/') - 1);
            $previous_look_up_order = $this->env->getNamespaceLookUpOrder();
            $this->env->setNamespaceLookUpOrder(array($namespace, '__main__'));
        }
        $this->loadTemplate("overall_footer.html", "acp_styles.html", 170)->display($context);
        if ($namespace) {
            $this->env->setNamespaceLookUpOrder($previous_look_up_order);
        }
    }

    public function getTemplateName()
    {
        return "acp_styles.html";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  567 => 170,  564 => 169,  558 => 165,  554 => 163,  543 => 161,  539 => 160,  536 => 159,  534 => 158,  531 => 157,  527 => 155,  518 => 151,  515 => 150,  509 => 148,  505 => 146,  502 => 145,  496 => 143,  494 => 142,  490 => 141,  486 => 140,  483 => 139,  474 => 137,  464 => 135,  461 => 134,  456 => 133,  452 => 132,  447 => 131,  439 => 129,  437 => 128,  434 => 127,  425 => 125,  422 => 124,  416 => 122,  413 => 121,  407 => 119,  404 => 118,  398 => 117,  393 => 116,  390 => 115,  375 => 112,  370 => 111,  368 => 110,  359 => 109,  356 => 108,  347 => 107,  344 => 106,  335 => 105,  333 => 104,  327 => 103,  322 => 102,  318 => 101,  311 => 97,  306 => 96,  300 => 95,  296 => 94,  291 => 91,  289 => 90,  287 => 89,  284 => 88,  278 => 85,  274 => 84,  270 => 83,  266 => 82,  261 => 79,  255 => 76,  251 => 75,  246 => 74,  243 => 73,  241 => 72,  232 => 70,  224 => 69,  219 => 68,  214 => 65,  198 => 63,  194 => 62,  188 => 61,  182 => 59,  176 => 56,  171 => 55,  165 => 52,  160 => 51,  154 => 48,  149 => 47,  142 => 44,  140 => 43,  135 => 41,  131 => 40,  127 => 39,  121 => 36,  117 => 34,  111 => 33,  108 => 32,  102 => 31,  99 => 30,  89 => 23,  85 => 22,  79 => 19,  76 => 18,  70 => 16,  68 => 15,  64 => 14,  60 => 13,  53 => 10,  51 => 9,  48 => 8,  38 => 6,  36 => 5,  31 => 2,  19 => 1,);
    }
}
