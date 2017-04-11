<?php 
error_reporting(E_ALL);
ini_set('display_errors', 1);
include_once 'simple_html_dom.php';
header('Content-Type: text/html; charset=utf-8');
function ara($bas, $son, $yazi)
{
    @preg_match_all('/'. preg_quote($bas, '/') .
    '(.*?)'. preg_quote($son, '/').'/i', $yazi, $m);
    return @$m[1];
}
function icerikara($bas, $son, $yazi)
{
    @preg_match_all('/'. preg_quote($bas, '/') .
    '(.*?)\s*'. preg_quote($son, '/').'/i', $yazi, $m);
    return @$m[1];
}


$html = new simple_html_dom();
$html->load_file('http://bilgisayar.kocaeli.edu.tr/tumgenelduyurular.php');


$main = $html->find('div[class=modal-body]'); 
$dizi=array();
$bool=false;
	foreach ($main as $key) {
		$bool=false;
		$line_in=$key->find('div[class=duyuruMetni]',0)->plaintext;
		$link=$key->find('a',0);
		$line_mid=strip_tags($line_in);
		$line_out = str_replace(array("\n", "\r"), '', $line_mid);
		if(!empty($link)){
			$removeDot=substr($link->href, 2);
			$removeDot=$removeDot;
			$newContent=$line_out."Ek: "."http://bilgisayar.kocaeli.edu.tr".$removeDot."*";
			array_unshift($dizi, $newContent);
			$bool=true;
		}
		if($bool==false){
			array_unshift($dizi,$line_out);
		}
		
	}

	

$url = "http://bilgisayar.kocaeli.edu.tr/tumgenelduyurular.php";

$kaynak = file_get_contents($url);
$baslik = ara('<h4 class="modal-title" id="myModalLabel"><div class="duyuruBaslik">','</div>',$kaynak);
$icerik = icerikara('<div class="duyuruMetni">','</div>',$kaynak);
$yazar = ara('<div class="author">','</div>',$kaynak);
$ay = ara('<div class="month">','</div>',$kaynak);
$gun = ara('<div class="day">','</div>',$kaynak);
$arr['duyurular'] = $baslik;
$duyuruSayisi=count($arr['duyurular']);

$newArray=array_reverse($dizi);
$array=array($baslik,$newArray,$yazar,$ay,$gun);
	
$jsonn=array();
for($i=0;$i<$duyuruSayisi;$i++){
			$json["id"]=$i;
			$json["title"]= $baslik[$i];
			$json["content"] = $newArray[$i];
			$json["author"] = $yazar[$i];
			$json["date"] = $ay[$i];
			$json["day"]=$gun[$i];
			array_unshift($jsonn, $json);
		}
$revArray=array_reverse($jsonn);
echo json_encode($revArray);





?>		