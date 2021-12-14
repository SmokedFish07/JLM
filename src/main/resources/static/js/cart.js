$(function(){
	$(".checkall").change(function(){
		$(".j-checkbox").prop("checked",$(this).prop("checked"));
	})
	$(".j-checkbox").change(function(){
		if($(".j-checkbox:checked").length ===$(".j-checkbox").length){
			$(".checkall").prop("checked",true);
		}else{
			$(".checkall").prop("checked",false);
		}
	})
	// 小计
	$(document).on("click",".increment",function () {
		var n=$(this).siblings(".itxt").val();
		n++;
		$(this).siblings(".itxt").val(n)
		//小结
		var p=$(this).parent().parent().siblings(".p-price").html();
		p=p.substr(1);
		var price=(p*n).toFixed(2);
		$(this).parent().parent().siblings(".p-sum").html("￥"+price);
	})
	$(document).on("click",".decrement",function () {
		var n=$(this).siblings(".itxt").val();
		if(n==1){
			return;
		}
		n--;
		$(this).siblings(".itxt").val(n)
		//小结
		var p=$(this).parent().parent().siblings(".p-price").html();
		p=p.substr(1);
		var price=(p*n).toFixed(2);
		$(this).parent().parent().siblings(".p-sum").html("￥"+price);
	})
	
	$(".getNew").click(function(){
		getSum();
	})
	function getSum(){
		var money=0;
		var item=$(".j-checkbox:checked").parents(".cart-item");
		// console.log(item)
		// console.log(item.find("p-sum"))
		item.find(".p-sum").each(function(i,ele){
			money += parseFloat($(ele).text().substr(1));
		})
		$(".fopt em").text("￥"+money.toFixed(2));
	}
})