$(document).ready( function () {
	var queryString = window.location.search;
	var urlParams = new URLSearchParams(queryString);
	var vendorId = urlParams.get('vendorId');
	var table = $('#viewVLTable').DataTable({
			"sAjaxSource": "/showItems/"+vendorId,
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "batteryId"},
		          { "mData": "vendorId"}
			      /*{ "mData": null , 
			    	     "mRender" : function ( data, type, full ){ 
			    	        return "<a href='/battery'>"+click here+"/>";
			    	      }
			      }*/
			]
	 })
});