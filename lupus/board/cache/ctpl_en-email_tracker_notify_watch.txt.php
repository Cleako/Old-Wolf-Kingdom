<?php if (!defined('IN_PHPBB')) exit; ?>Hello <?php echo (isset($this->_rootref['USERNAME'])) ? $this->_rootref['USERNAME'] : ''; ?>,

You are receiving this notification because you are subscribed to this project
or ticket. An update to the ticket has occured.  You will receive these notifications
when a new ticket is posted, a comment is posted to the ticket and any details
about the ticket have changed.

To view the ticket, click here:
<?php echo (isset($this->_rootref['TICKET_URL'])) ? $this->_rootref['TICKET_URL'] : ''; ?>


To unsubscribe to the project, click here:
<?php echo (isset($this->_rootref['PROJECT_UNSUBSCRIBE_URL'])) ? $this->_rootref['PROJECT_UNSUBSCRIBE_URL'] : ''; ?>


To unsubscribe to the ticket, click here:
<?php echo (isset($this->_rootref['TICKET_UNSUBSCRIBE_URL'])) ? $this->_rootref['TICKET_UNSUBSCRIBE_URL'] : ''; ?>


Ticket details
--------------------------

Ticket ID: <?php echo (isset($this->_rootref['TICKET_ID'])) ? $this->_rootref['TICKET_ID'] : ''; ?>

Title:     <?php echo (isset($this->_rootref['TICKET_TITLE'])) ? $this->_rootref['TICKET_TITLE'] : ''; ?>


--
<?php echo (isset($this->_rootref['SITE_NAME'])) ? $this->_rootref['SITE_NAME'] : ''; ?>

<?php echo (isset($this->_rootref['PROJECT_NAME'])) ? $this->_rootref['PROJECT_NAME'] : ''; ?> - <?php echo (isset($this->_rootref['TRACKER_TYPE'])) ? $this->_rootref['TRACKER_TYPE'] : ''; ?>

<?php echo (isset($this->_rootref['TRACKER_URL'])) ? $this->_rootref['TRACKER_URL'] : ''; ?>