<?php 
header('Content-Type: text/html; charset=utf-8');
function ara($bas, $son, $yazi)
{
    @preg_match_all('/' . preg_quote($bas, '/') .
    '(.*?)'. preg_quote($son, '/').'/i', $yazi, $m);
    return @$m[1];
}

$buguny= date("w");
switch($buguny){
case 0:$buguntxt="Pazar"; break;
case 1:$buguntxt="Pazartesi"; break;
case 2:$buguntxt="Salı"; break;
case 3:$buguntxt="Çarşamba"; break;
case 4:$buguntxt="Perşembe"; break;
case 5:$buguntxt="Cuma"; break;
case 6:$buguntxt="Cumartesi"; break;

}



$url = "http://sksdb.kocaeli.edu.tr/?p=yemek_listesi";
$kaynak = file_get_contents($url);
$gun = ara('class="etkinlik-tarih-gun">','</div>',$kaynak);
$ay = ara('class="etkinlik-tarih-ay">','</div>',$kaynak);
$yemek1= ara('style="padding: 2px;">','</div>',$kaynak); // 0 ve 1
$yemek2= ara('style="background-color: #F0F0F0; padding: 2px;">','</div>',$kaynak); //0 ve 1
$tarih    =  trim($gun[0]).".".$ay[0].".2016 ";

$icerik["corba"]=$yemek1[0];
$icerik["anayemek"]=$yemek2[0];
$icerik["yardimciyemek"]=$yemek1[1];
$icerik["tatli"]=$yemek2[1];
$icerik["tarih"]=$tarih;
echo json_encode($icerik);




?>	