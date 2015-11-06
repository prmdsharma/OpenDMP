<div id="opendmo-div">
 <script type="text/javascript">
     var opendmp_dcs = 'dcs';
     var clientID = ' TestClient';

     /* * * DON'T EDIT BELOW THIS LINE * * */
     (function() {
         var odmp = document.createElement('script'); odmp.type = 'text/javascript'; odmp.async = true;
         odmp.src = '//' + 'opendmp.in/tag/opendmp.js';
         (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
     })();

     (function() {
             var od = document.createElement('script');
             od.type = 'text/javascript'; od.async = true;
             var ts = new Date().getTime();
             var queryStr =  'uid=' + userFPHash + '&cid=' + clientID;
             queryStr += '&ts=' + ts;
             pathStr = '/dcs/v1/visit?';
             od.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'opendmp.in' + pathStr + queryStr;
             var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(od, s);
         })();
 </script>
 </div>