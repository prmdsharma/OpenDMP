
 <script type="text/javascript">
    (function() {
        var od = document.createElement('script');
        od.type = 'text/javascript'; od.async = true;
        var ts = new Date().getTime();
        var queryStr = 'pu=' + {{Page URL}} + '&rurl=' + {{Referrer}} + '&uid=' + cookie + '&cid=' + {{Container ID}};
        queryStr += '&ts=' + ts;
        pathStr = '/dcs/v1/visit?';
        od.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'opendmp.in' + pathStr + queryStr;
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(od, s);
    })();
 </script>