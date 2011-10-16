<?php if (!defined('IN_PHPBB')) exit; ?>Hello <?php echo (isset($this->_rootref['USERNAME'])) ? $this->_rootref['USERNAME'] : ''; ?>,

Your report has been added successfully and will soon be acted upon. You
will receive further notifications if comments are added to this ticket, or
if its status is changed by a team member.

To view the ticket, click here:
<?php echo (isset($this->_rootref['TICKET_URL'])) ? $this->_rootref['TICKET_URL'] : ''; ?>


Ticket details
--------------------------

Ticket ID: <?php echo (isset($this->_rootref['TICKET_ID'])) ? $this->_rootref['TICKET_ID'] : ''; ?>

Project:   <?php echo (isset($this->_rootref['PROJECT_NAME'])) ? $this->_rootref['PROJECT_NAME'] : ''; ?>

Title:     <?php echo (isset($this->_rootref['TICKET_TITLE'])) ? $this->_rootref['TICKET_TITLE'] : ''; ?>


<?php echo (isset($this->_rootref['TICKET_DESC'])) ? $this->_rootref['TICKET_DESC'] : ''; ?>


--
<?php echo (isset($this->_rootref['SITE_NAME'])) ? $this->_rootref['SITE_NAME'] : ''; ?>

<?php echo (isset($this->_rootref['PROJECT_NAME'])) ? $this->_rootref['PROJECT_NAME'] : ''; ?> - <?php echo (isset($this->_rootref['TRACKER_TYPE'])) ? $this->_rootref['TRACKER_TYPE'] : ''; ?>

<?php echo (isset($this->_rootref['TRACKER_URL'])) ? $this->_rootref['TRACKER_URL'] : ''; ?>