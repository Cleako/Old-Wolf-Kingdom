<?php if (!defined('IN_PHPBB')) exit; ?>Hello <?php echo (isset($this->_rootref['USERNAME'])) ? $this->_rootref['USERNAME'] : ''; ?>,

You are receiving this notification because one or more details of your
ticket #<?php echo (isset($this->_rootref['TICKET_ID'])) ? $this->_rootref['TICKET_ID'] : ''; ?> in the <?php echo (isset($this->_rootref['PROJECT_NAME'])) ? $this->_rootref['PROJECT_NAME'] : ''; ?> - <?php echo (isset($this->_rootref['TRACKER_TYPE'])) ? $this->_rootref['TRACKER_TYPE'] : ''; ?> at <?php echo (isset($this->_rootref['SITE_NAME'])) ? $this->_rootref['SITE_NAME'] : ''; ?> have been changed. Please
review the changes below and act accordingly if they require any attention.

These changes were made by "<?php echo (isset($this->_rootref['CHANGE_USERNAME'])) ? $this->_rootref['CHANGE_USERNAME'] : ''; ?>".

Field name: <?php echo (isset($this->_rootref['FIELD_NAME1'])) ? $this->_rootref['FIELD_NAME1'] : ''; ?>

Old value: <?php echo (isset($this->_rootref['OLD_VALUE1'])) ? $this->_rootref['OLD_VALUE1'] : ''; ?>

New value: <?php echo (isset($this->_rootref['NEW_VALUE1'])) ? $this->_rootref['NEW_VALUE1'] : ''; ?>

------------------
Field name: <?php echo (isset($this->_rootref['FIELD_NAME2'])) ? $this->_rootref['FIELD_NAME2'] : ''; ?>

Old value: <?php echo (isset($this->_rootref['OLD_VALUE2'])) ? $this->_rootref['OLD_VALUE2'] : ''; ?>

New value: <?php echo (isset($this->_rootref['NEW_VALUE2'])) ? $this->_rootref['NEW_VALUE2'] : ''; ?>


Ticket title: <?php echo (isset($this->_rootref['TICKET_TITLE'])) ? $this->_rootref['TICKET_TITLE'] : ''; ?>

More information:
<?php echo (isset($this->_rootref['TICKET_URL'])) ? $this->_rootref['TICKET_URL'] : ''; ?>


--
<?php echo (isset($this->_rootref['SITE_NAME'])) ? $this->_rootref['SITE_NAME'] : ''; ?> 
<?php echo (isset($this->_rootref['PROJECT_NAME'])) ? $this->_rootref['PROJECT_NAME'] : ''; ?> - <?php echo (isset($this->_rootref['TRACKER_TYPE'])) ? $this->_rootref['TRACKER_TYPE'] : ''; ?>

<?php echo (isset($this->_rootref['TRACKER_URL'])) ? $this->_rootref['TRACKER_URL'] : ''; ?>