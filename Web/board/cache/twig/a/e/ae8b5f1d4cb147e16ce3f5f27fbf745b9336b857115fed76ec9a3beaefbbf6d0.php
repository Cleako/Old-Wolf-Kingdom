<?php

/* display_options.html */
class __TwigTemplate_ae8b5f1d4cb147e16ce3f5f27fbf745b9336b857115fed76ec9a3beaefbbf6d0 extends Twig_Template
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
        echo "<div class=\"dropdown-container dropdown-container-";
        echo (isset($context["S_CONTENT_FLOW_BEGIN"]) ? $context["S_CONTENT_FLOW_BEGIN"] : null);
        echo " dropdown-button-control sort-tools\">
\t<span title=\"";
        // line 2
        echo $this->env->getExtension('phpbb')->lang("DISPLAY");
        echo "\" class=\"button icon-button sort-icon dropdown-trigger dropdown-select\"></span>
\t<div class=\"dropdown hidden\">
\t\t<div class=\"pointer\"><div class=\"pointer-inner\"></div></div>
\t\t<div class=\"dropdown-contents\">
\t\t\t<fieldset class=\"display-options\">
\t\t\t";
        // line 7
        if ((isset($context["S_SORT_OPTIONS"]) ? $context["S_SORT_OPTIONS"] : null)) {
            // line 8
            echo "\t\t\t\t<label>";
            echo $this->env->getExtension('phpbb')->lang("SORT_BY");
            echo $this->env->getExtension('phpbb')->lang("COLON");
            echo " <select name=\"sk\" id=\"sk\">";
            echo (isset($context["S_SORT_OPTIONS"]) ? $context["S_SORT_OPTIONS"] : null);
            echo "</select></label>
\t\t\t\t<label>";
            // line 9
            echo $this->env->getExtension('phpbb')->lang("LOADING");
            echo $this->env->getExtension('phpbb')->lang("COLON");
            echo " <select name=\"sd\" id=\"sd\">";
            echo (isset($context["S_ORDER_SELECT"]) ? $context["S_ORDER_SELECT"] : null);
            echo "</select></label>
\t\t\t\t<hr class=\"dashed\" />
\t\t\t\t<input type=\"submit\" class=\"button2\" name=\"sort\" value=\"";
            // line 11
            echo $this->env->getExtension('phpbb')->lang("SORT");
            echo "\" />
\t\t\t";
        } else {
            // line 13
            echo "\t\t\t\t<label>";
            echo $this->env->getExtension('phpbb')->lang("DISPLAY");
            echo $this->env->getExtension('phpbb')->lang("COLON");
            echo " ";
            echo (isset($context["S_SELECT_SORT_DAYS"]) ? $context["S_SELECT_SORT_DAYS"] : null);
            echo "</label>
\t\t\t\t";
            // line 14
            if ((isset($context["S_SELECT_SORT_KEY"]) ? $context["S_SELECT_SORT_KEY"] : null)) {
                // line 15
                echo "\t\t\t\t<label>";
                echo $this->env->getExtension('phpbb')->lang("SORT_BY");
                echo $this->env->getExtension('phpbb')->lang("COLON");
                echo " ";
                echo (isset($context["S_SELECT_SORT_KEY"]) ? $context["S_SELECT_SORT_KEY"] : null);
                echo "</label>
\t\t\t\t<label>";
                // line 16
                echo $this->env->getExtension('phpbb')->lang("LOADING");
                echo $this->env->getExtension('phpbb')->lang("COLON");
                echo " ";
                echo (isset($context["S_SELECT_SORT_DIR"]) ? $context["S_SELECT_SORT_DIR"] : null);
                echo "</label>
\t\t\t\t";
            }
            // line 18
            echo "\t\t\t\t<hr class=\"dashed\" />
\t\t\t\t<input type=\"submit\" class=\"button2\" name=\"sort\" value=\"";
            // line 19
            echo $this->env->getExtension('phpbb')->lang("GO");
            echo "\" />
\t\t\t";
        }
        // line 21
        echo "\t\t\t</fieldset>
\t\t</div>
\t</div>
</div>
";
    }

    public function getTemplateName()
    {
        return "display_options.html";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  89 => 21,  84 => 19,  81 => 18,  73 => 16,  65 => 15,  63 => 14,  55 => 13,  50 => 11,  42 => 9,  34 => 8,  32 => 7,  24 => 2,  19 => 1,);
    }
}
