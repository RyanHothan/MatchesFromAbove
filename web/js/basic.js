/*
 * SimpleModal Basic Modal Dialog
 * http://simplemodal.com
 *
 * Copyright (c) 2013 Eric Martin - http://ericmmartin.com
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 */

jQuery(function ($) {
	// Load dialog on page load
	//$('#basic-modal-content').modal();

	// Load dialog on click
	$('.basic').click(function (e) {
		$('#basic-modal-content').modal({overlayClose:true});

		return false;
	});
	
	$('.basic').click(function (e) {
		$('#basic-modal-content-return').modal({overlayClose:true});
		return false;
	});
        
        $('.createAccount').click(function (e) {
		$('#basic-modal-content-account').modal({overlayClose:true});

		return false;
	});
        
        $('.createAccount').click(function (e) {
		$('#basic-modal-content-return-account').modal({overlayClose:true});
		return false;
	});

});