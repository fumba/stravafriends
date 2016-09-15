/**
 * Functions used to map the location on friends.
 * 
 * Fumba Chibaka (fumba.me)
 * 
 */

var geocoder, map;

function initialize() {
	geocoder = new google.maps.Geocoder();
	var latlng = new google.maps.LatLng(-0.397, 5.644);
	var mapOptions = {
		zoom : 2,
		center : latlng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	var cityList = JSON.parse(cityListJSON);

	for (var i = 0; i < cityList.length; i++) {
		var address = cityList[i][0] + ', ' + cityList[i][1];
		codeAddress(address);
	}
};

function codeAddress(address) {
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			map.setCenter(results[0].geometry.location);
			var marker = new google.maps.Marker({
				map : map,
				position : results[0].geometry.location
			});
		} else {
			//alert("Geocode unsuccessful");
		}
	});
};