/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 */
 
 $(document).ready(function () {
	var count = 0;
	var link = "/viewPayeeInAdminDash";
	setInterval(function(){
		$.get("/getPayeeStat", function(data){
			//console.log(data);
			$.each(data,function(){
				count++;
			});
			console.log(count);
			$("#notification").html("<h1>"+count+" pending payee detail</h1><br><div><a href="+link+">Check Payee details</a></div><br><hr>");
			count = 0;
		});
	}, 3000);
	
	
	
	
});