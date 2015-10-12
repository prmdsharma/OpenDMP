

<script type="text/javascript">
 	(function() {
 		var od = document.createElement('script');
 		od.type = 'text/javascript'; od.async = true;
 		var fingerprint = new Fingerprint().get();
 		var queryStr = 'pu=' + {{Page URL}} + '&rurl=' + {{Referrer}} + '&uid='
 		od.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'opendmp.in/dcs/v1/visit?' + queryStr;
 		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(od, s);
 	})();
 </script>