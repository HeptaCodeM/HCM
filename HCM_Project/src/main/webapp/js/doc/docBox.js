function detailBoard(docNum) {
    var url = '/doc/docBox/getDetail.do?docNum=' + encodeURIComponent(docNum);
    window.location.href = url;
}

/*function detailBoard(docNum) {
    fetch('/doc/docBox/getDetail.do?docNum=' + docNum, {
        method: 'POST', 
        data: {'docNum' : docNum}
        headers: {
            'Content-Type': 'application/json' 
        },
    })
    .then(response => response.text()) 
    .catch(error => console.error('Error:', error)); // 오류 처리
}*/



$(document).ready( function () {
	$('#myTable').DataTable({ 

    	"language": { 
            "emptyTable": "열람 가능한 결재문서가 없습니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "search": "검색: ",
            "paginate": {
                "next": "다음",
                "previous": "이전",
                "first": "처음",
                "last" : "마지막"
            },
          //https://datatables.net/reference/option/language   위를 더 디테일하게 수정하고싶으면 여기로~
        },
        
        info: true, // 좌측하단 정보 표시 
        searching: true, // 검색 기능 
   	    ordering: false, // 정렬 기능
        paging:true, // 페이징 기능 
        lengthChange: false, //  좌상단 몇 건씩 볼지 정하는 기능
   //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
   //	    autoWidth: true,    //자동 열 조정
     	columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }], //열의 너비 조절 0,1,2,3 순임
//      displayLength: 20, //처음에 몇 건을 볼지 
		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
        pagingType: "simple_numbers" 
        // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가

	});
});



//전체 문서함 Ajax
function allDocs(){
	// 기존에 있던 테이블 삭제
	$("#myTable").DataTable().destroy(); 
	var mainDiv = document.getElementById('tableOuter');
	mainDiv.innerHTML = ''; 
	
	$.ajax({url:"./allDocs.do",
	     type:"get",
	     success:function(data){ 
	    	console.log(data);
	    	var in3 = '';
	    	var in2 = '';
	    	var in1 = '<table id="myTable" class="stripe hover">'
	    	    + '    <thead>'
	    	    + '        <tr style="">'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '        </tr>'
	    	    + '    </thead>'
	    	    + '    <tbody>';

	    	for (let d of data) {
	    	        var dateString = d.sidb_doc_writedt;
	    	        var date = new Date(dateString);
	    	        var formattedDate = date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
	    	       if (d.sidt_temp_cd == 'TC000014') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-document fs-3x text-primary">'
                      + '                            <span class="path1"></span> <span class="path2"></span>'
                      + '                        </i> <br>    ' + d.sidt_temp_name
                      + '                    </td>'             
              }
               else if (d.sidt_temp_cd == 'TC000002')  {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-home fs-3x text-muted">'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }  else if (d.sidt_temp_cd == 'TC000013') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-briefcase fs-3x text-gray-900">'
                      + '                            <span class="path1"></span><span class="path2"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } else {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-bill fs-3x text-success">'
                      + '                            <span class="path1"></span> <span class="path2"></span> <span'
                      + '                                class="path3"></span> <span class="path4"></span> <span'
                      + '                                class="path5"></span> <span class="path6"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }
	    	    
	    	    // 제목 + 기안일자
	    	         in2   += '                    <td><a href="#" onclick="detailBoard(' + d.sidb_doc_num + ')"> ' + d.sidb_doc_title + '  </a> <br> <br>'
	    	            + '                     기안일자| ' + formattedDate
	    	       
	    	    //사진 + 사원이름        
	    	            + '                    <td> <img style="width:50px; height:50px; border-radius: 22px;" src="data:image/png;base64,'+d.empl_pictureStr+'">' + d.empl_name + '</td>'

	    	    //프로그래스바 시작         
	    	            + '                    <td style="text-align:center;"> ';
	    	            
	    	            if (d.sidb_doc_stat == 1 && d.appr_name1 == undefined && d.appr_flag0 == 0 ) {
	    	            	  
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">'
	    	                    + ' <br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;"> ' + d.appr_name0 + ' </span>';
	    	            	    }  
	    	          
	    	            if (d.sidb_doc_stat == 1 && d.appr_name2 == undefined && d.appr_flag0 == 0) {
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">'
	    	                    + '<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">' + d.appr_name0 +' >&nbsp; </span> <span style="display:inline-block; width:22%;"></span>' + d.appr_name1 + ' </span> ' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 == undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">'+d.appr_name0
	    	            		+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">'+d.appr_name1
	    	            		+'&nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'+d.appr_name2+'&nbsp; &nbsp;' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag1 == 1 && d.appr_flag2 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">'
	    	            	      + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: DodgerBlue;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 1 && d.appr_name2 != undefined && d.appr_flag0 == 0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name1 == undefined && d.appr_flag0 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name2 == undefined && d.appr_flag1 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if (d.sidb_doc_stat == 3 && d.appr_name2 != undefined && d.appr_flag2 == 1) {
	    	                in2 += '<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">'
	    	                    + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;';
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name1 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:22%;"></span>'+d.appr_name1
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red;font-size: large; ">'
	    	            		+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag0 == 2){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red;font-size: large;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'
	    	            		+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag2 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">'
	    	            		+'<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size:large;">'
	    	            		+d.appr_name2+'</span>&nbsp; &nbsp;'
	    	            	
	    	            }
	    	            
	    	            in2 += '                    </td>'
	    	          
	    	            
	    	            
	    	       //기안 진행 완료 반려 아이콘     
	    	            + '                    <td>';
	    	            if (d.sidb_doc_stat == 1){
	    	            in2+= '                        <img style="width:80%;" src="/image/doc/docBox/s1.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 2){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s2.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 3){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s3.png">'
	    	            }
	    	            if (d.sidb_doc_stat ==4){
	    	            in2+= '                          <img style="width:80%;" src="/image/doc/docBox/s4.png">'
	    	            }
	    	            + '                    </td>'
	    	            + '                </tr>';
	    	    
	    	}

	    	var in3 = '</tbody></table>';

	    	mainDiv.innerHTML = in1 + in2 + in3;

	    	
	     $('#myTable').DataTable({  
	    	 "language": { 
	             "lengthMenu": " _MENU_ 개씩 보기",
	             "emptyTable": "열람 가능한 결재문서가 없습니다.",
	             "search": "검색: ",
	             "info": "현재 _START_ - _END_ / _TOTAL_건",
	             "paginate": {
	                 "next": "다음",
	                 "previous": "이전",
	                 "first": "처음",
	                 "last" : "마지막"
	             },
	    	  },
	          info: true, // 좌측하단 정보 표시 
	          searching: true, // 검색 기능 
	     	    ordering: false, // 정렬 기능
	          paging:true, // 페이징 기능 
	     //   lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
	     //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
	     //	    autoWidth: true,    //자동 열 조정
	     	   columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }],
	        displayLength: 5, //처음에 몇 건을 볼지 
	  		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
	          pagingType: "simple_numbers"
	          // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
	    	  
		
			 });
		   },
		  error:function(){
			alert("Ajax 처리중 오류 발생");
			}
	})
}; 



//기안함 Ajax
function gianBox(){
	// 기존에 있던 테이블 삭제
	$("#myTable").DataTable().destroy(); 
	var mainDiv = document.getElementById('tableOuter');
	mainDiv.innerHTML = ''; 
	
	$.ajax({url:"./gianBox.do",
	     type:"get",
	     success:function(data){ 
	    	console.log(data);
	    	var in3 = '';
	    	var in2 = '';
	    	var in1 = '<table id="myTable" class="stripe hover">'
	    	    + '    <thead>'
	    	    + '        <tr style="">'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '        </tr>'
	    	    + '    </thead>'
	    	    + '    <tbody>';

	    	for (let d of data) {
	    	        var dateString = d.sidb_doc_writedt;
	    	        var date = new Date(dateString);
	    	        var formattedDate = date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
	    	     if (d.sidt_temp_cd == 'TC000014') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-document fs-3x text-primary">'
                      + '                            <span class="path1"></span> <span class="path2"></span>'
                      + '                        </i> <br>    ' + d.sidt_temp_name
                      + '                    </td>'             
              }
               else if (d.sidt_temp_cd == 'TC000002')  {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-home fs-3x text-muted">'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }  else if (d.sidt_temp_cd == 'TC000013') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-briefcase fs-3x text-gray-900">'
                      + '                            <span class="path1"></span><span class="path2"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } else {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-bill fs-3x text-success">'
                      + '                            <span class="path1"></span> <span class="path2"></span> <span'
                      + '                                class="path3"></span> <span class="path4"></span> <span'
                      + '                                class="path5"></span> <span class="path6"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }
	    	    
	    	    
	    	    // 제목 + 기안일자
	    	         in2   += '                    <td><a href="#" onclick="detailBoard(' + d.sidb_doc_num + ')"> ' + d.sidb_doc_title + '  </a> <br> <br>'
	    	            + '                     기안일자| ' + formattedDate
	    	       
	    	    //사진 + 사원이름        
	    	            + '                    <td> <img style="width:50px; height:50px; border-radius: 22px;" src="data:image/png;base64,'+d.empl_pictureStr+'">' + d.empl_name + '</td>'

	    	    //프로그래스바 시작         
	    	            + '                    <td style="text-align:center;"> ';
	    	            
	    	            if (d.sidb_doc_stat == 1 && d.appr_name1 == undefined && d.appr_flag0 == 0 ) {
	    	            	  
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">'
	    	                    + ' <br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;"> ' + d.appr_name0 + ' </span>';
	    	            	    }  
	    	          
	    	            if (d.sidb_doc_stat == 1 && d.appr_name2 == undefined && d.appr_flag0 == 0) {
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">'
	    	                    + '<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">' + d.appr_name0 +' >&nbsp; </span> <span style="display:inline-block; width:22%;"></span>' + d.appr_name1 + ' </span> ' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 == undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">'+d.appr_name0
	    	            		+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">'+d.appr_name1
	    	            		+'&nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'+d.appr_name2+'&nbsp; &nbsp;' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag1 == 1 && d.appr_flag2 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">'
	    	            	      + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: DodgerBlue;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 1 && d.appr_name2 != undefined && d.appr_flag0 == 0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name1 == undefined && d.appr_flag0 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name2 == undefined && d.appr_flag1 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if (d.sidb_doc_stat == 3 && d.appr_name2 != undefined && d.appr_flag2 == 1) {
	    	                in2 += '<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">'
	    	                    + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;';
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name1 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:22%;"></span>'+d.appr_name1
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red;font-size: large; ">'
	    	            		+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag0 == 2){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red;font-size: large;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'
	    	            		+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag2 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">'
	    	            		+'<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size:large;">'
	    	            		+d.appr_name2+'</span>&nbsp; &nbsp;'
	    	            	
	    	            }
	    	            
	    	            in2 += '                    </td>'
	    	          
	    	            
	    	            
	    	       //기안 진행 완료 반려 아이콘     
	    	            + '                    <td>';
	    	            if (d.sidb_doc_stat == 1){
	    	            in2+= '                        <img style="width:80%;" src="/image/doc/docBox/s1.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 2){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s2.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 3){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s3.png">'
	    	            }
	    	            if (d.sidb_doc_stat ==4){
	    	            in2+= '                          <img style="width:80%;" src="/image/doc/docBox/s4.png">'
	    	            }
	    	            + '                    </td>'
	    	            + '                </tr>';
	    	    
	    	}

	    	var in3 = '</tbody></table>';

	    	mainDiv.innerHTML = in1 + in2 + in3;

	    	
	     $('#myTable').DataTable({  
	    	 "language": { 
	             "lengthMenu": " _MENU_ 개씩 보기",
	             "emptyTable": "기안중인 문서가 없습니다.",
	             "search": "검색: ",
	             "info": "현재 _START_ - _END_ / _TOTAL_건",
	             "paginate": {
	                 "next": "다음",
	                 "previous": "이전",
	                 "first": "처음",
	                 "last" : "마지막"
	             },
	    	  },
	          info: true, // 좌측하단 정보 표시 
	          searching: true, // 검색 기능 
	     	    ordering: false, // 정렬 기능
	          paging:true, // 페이징 기능 
	     //   lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
	     //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
	     //	    autoWidth: true,    //자동 열 조정
	     	   columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }],
	        displayLength: 5, //처음에 몇 건을 볼지 
	  		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
	          pagingType: "simple_numbers"
	          // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
	    	  
		
			 });
		   },
		  error:function(){
			alert("Ajax 처리중 오류 발생");
			}
	})
}; 


//진행함 Ajax
function ingBox(){
	// 기존에 있던 테이블 삭제
	$("#myTable").DataTable().destroy(); 
	var mainDiv = document.getElementById('tableOuter');
	mainDiv.innerHTML = ''; 
	
	$.ajax({url:"./ingBox.do",
	     type:"get",
	     success:function(data){ 
	    	console.log(data);
	    	var in3 = '';
	    	var in2 = '';
	    	var in1 = '<table id="myTable" class="stripe hover">'
	    	    + '    <thead>'
	    	    + '        <tr style="">'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '        </tr>'
	    	    + '    </thead>'
	    	    + '    <tbody>';

	    	for (let d of data) {
	    	        var dateString = d.sidb_doc_writedt;
	    	        var date = new Date(dateString);
	    	        var formattedDate = date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
	    	     if (d.sidt_temp_cd == 'TC000014') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-document fs-3x text-primary">'
                      + '                            <span class="path1"></span> <span class="path2"></span>'
                      + '                        </i> <br>    ' + d.sidt_temp_name
                      + '                    </td>'             
              }
               else if (d.sidt_temp_cd == 'TC000002')  {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-home fs-3x text-muted">'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }  else if (d.sidt_temp_cd == 'TC000013') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-briefcase fs-3x text-gray-900">'
                      + '                            <span class="path1"></span><span class="path2"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } else {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-bill fs-3x text-success">'
                      + '                            <span class="path1"></span> <span class="path2"></span> <span'
                      + '                                class="path3"></span> <span class="path4"></span> <span'
                      + '                                class="path5"></span> <span class="path6"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }
	    	    
	    	    
	    	    // 제목 + 기안일자
	    	         in2   += '                    <td><a href="#" onclick="detailBoard(' + d.sidb_doc_num + ')"> ' + d.sidb_doc_title + '  </a> <br> <br>'
	    	            + '                     기안일자| ' + formattedDate
	    	       
	    	    //사진 + 사원이름        
	    	            + '                    <td> <img style="width:50px; height:50px; border-radius: 22px;" src="data:image/png;base64,'+d.empl_pictureStr+'">' + d.empl_name + '</td>'

	    	    //프로그래스바 시작         
	    	            + '                    <td style="text-align:center;"> ';
	    	            
	    	            if (d.sidb_doc_stat == 1 && d.appr_name1 == undefined && d.appr_flag0 == 0 ) {
	    	            	  
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">'
	    	                    + ' <br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;"> ' + d.appr_name0 + ' </span>';
	    	            	    }  
	    	          
	    	            if (d.sidb_doc_stat == 1 && d.appr_name2 == undefined && d.appr_flag0 == 0) {
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">'
	    	                    + '<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">' + d.appr_name0 +' >&nbsp; </span> <span style="display:inline-block; width:22%;"></span>' + d.appr_name1 + ' </span> ' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 == undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">'+d.appr_name0
	    	            		+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">'+d.appr_name1
	    	            		+'&nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'+d.appr_name2+'&nbsp; &nbsp;' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag1 == 1 && d.appr_flag2 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">'
	    	            	      + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: DodgerBlue;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 1 && d.appr_name2 != undefined && d.appr_flag0 == 0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name1 == undefined && d.appr_flag0 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name2 == undefined && d.appr_flag1 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if (d.sidb_doc_stat == 3 && d.appr_name2 != undefined && d.appr_flag2 == 1) {
	    	                in2 += '<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">'
	    	                    + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;';
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name1 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:22%;"></span>'+d.appr_name1
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red;font-size: large; ">'
	    	            		+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag0 == 2){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red;font-size: large;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'
	    	            		+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag2 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">'
	    	            		+'<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size:large;">'
	    	            		+d.appr_name2+'</span>&nbsp; &nbsp;'
	    	            	
	    	            }
	    	            
	    	            in2 += '                    </td>'
	    	          
	    	            
	    	            
	    	       //기안 진행 완료 반려 아이콘     
	    	            + '                    <td>';
	    	            if (d.sidb_doc_stat == 1){
	    	            in2+= '                        <img style="width:80%;" src="/image/doc/docBox/s1.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 2){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s2.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 3){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s3.png">'
	    	            }
	    	            if (d.sidb_doc_stat ==4){
	    	            in2+= '                          <img style="width:80%;" src="/image/doc/docBox/s4.png">'
	    	            }
	    	            + '                    </td>'
	    	            + '                </tr>';
	    	    
	    	}

	    	var in3 = '</tbody></table>';

	    	mainDiv.innerHTML = in1 + in2 + in3;

	    	
	     $('#myTable').DataTable({  
	    	 "language": { 
	             "lengthMenu": " _MENU_ 개씩 보기",
	             "emptyTable": "결재 진행중인 문서가 없습니다.",
	             "search": "검색: ",
	             "info": "현재 _START_ - _END_ / _TOTAL_건",
	             "paginate": {
	                 "next": "다음",
	                 "previous": "이전",
	                 "first": "처음",
	                 "last" : "마지막"
	             },
	    	  },
	          info: true, // 좌측하단 정보 표시 
	          searching: true, // 검색 기능 
	     	    ordering: false, // 정렬 기능
	          paging:true, // 페이징 기능 
	     //   lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
	     //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
	     //	    autoWidth: true,    //자동 열 조정
	     	   columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }],
	        displayLength: 5, //처음에 몇 건을 볼지 
	  		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
	          pagingType: "simple_numbers"
	          // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
	    	  
		
			 });
		   },
		  error:function(){
			alert("Ajax 처리중 오류 발생");
			}
	})
}; 

//완료함 Ajax
function approveBox(){
	// 기존에 있던 테이블 삭제
	$("#myTable").DataTable().destroy(); 
	var mainDiv = document.getElementById('tableOuter');
	mainDiv.innerHTML = ''; 
	
	$.ajax({url:"./approveBox.do",
	     type:"get",
	     success:function(data){ 
	    	console.log(data);
	    	var in3 = '';
	    	var in2 = '';
	    	var in1 = '<table id="myTable" class="stripe hover">'
	    	    + '    <thead>'
	    	    + '        <tr style="">'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '        </tr>'
	    	    + '    </thead>'
	    	    + '    <tbody>';

	    	for (let d of data) {
	    	        var dateString = d.sidb_doc_writedt;
	    	        var date = new Date(dateString);
	    	        var formattedDate = date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
	    	     if (d.sidt_temp_cd == 'TC000014') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-document fs-3x text-primary">'
                      + '                            <span class="path1"></span> <span class="path2"></span>'
                      + '                        </i> <br>    ' + d.sidt_temp_name
                      + '                    </td>'             
              }
               else if (d.sidt_temp_cd == 'TC000002')  {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-home fs-3x text-muted">'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }  else if (d.sidt_temp_cd == 'TC000013') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-briefcase fs-3x text-gray-900">'
                      + '                            <span class="path1"></span><span class="path2"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } else {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-bill fs-3x text-success">'
                      + '                            <span class="path1"></span> <span class="path2"></span> <span'
                      + '                                class="path3"></span> <span class="path4"></span> <span'
                      + '                                class="path5"></span> <span class="path6"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } 
	    	    
	    	    
	    	    // 제목 + 기안일자
	    	         in2   += '                    <td><a href="#" onclick="detailBoard(' + d.sidb_doc_num + ')"> ' + d.sidb_doc_title + '  </a> <br> <br>'
	    	            + '                     기안일자| ' + formattedDate
	    	       
	    	    //사진 + 사원이름        
	    	            + '                    <td> <img style="width:50px; height:50px; border-radius: 22px;" src="data:image/png;base64,'+d.empl_pictureStr+'">' + d.empl_name + '</td>'

	    	    //프로그래스바 시작         
	    	            + '                    <td style="text-align:center;"> ';
	    	            
	    	            if (d.sidb_doc_stat == 1 && d.appr_name1 == undefined && d.appr_flag0 == 0 ) {
	    	            	  
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">'
	    	                    + ' <br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;"> ' + d.appr_name0 + ' </span>';
	    	            	    }  
	    	          
	    	            if (d.sidb_doc_stat == 1 && d.appr_name2 == undefined && d.appr_flag0 == 0) {
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">'
	    	                    + '<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">' + d.appr_name0 +' >&nbsp; </span> <span style="display:inline-block; width:22%;"></span>' + d.appr_name1 + ' </span> ' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 == undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">'+d.appr_name0
	    	            		+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">'+d.appr_name1
	    	            		+'&nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'+d.appr_name2+'&nbsp; &nbsp;' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag1 == 1 && d.appr_flag2 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">'
	    	            	     + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: DodgerBlue;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 1 && d.appr_name2 != undefined && d.appr_flag0 == 0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name1 == undefined && d.appr_flag0 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name2 == undefined && d.appr_flag1 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if (d.sidb_doc_stat == 3 && d.appr_name2 != undefined && d.appr_flag2 == 1) {
	    	                in2 += '<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">'
	    	                    + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;';
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name1 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:22%;"></span>'+d.appr_name1
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red;font-size: large; ">'
	    	            		+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag0 == 2){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red;font-size: large;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'
	    	            		+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag2 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">'
	    	            		+'<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size:large;">'
	    	            		+d.appr_name2+'</span>&nbsp; &nbsp;'
	    	            	
	    	            }
	    	            
	    	            in2 += '                    </td>'
	    	          
	    	            
	    	            
	    	       //기안 진행 완료 반려 아이콘     
	    	            + '                    <td>';
	    	            if (d.sidb_doc_stat == 1){
	    	            in2+= '                        <img style="width:80%;" src="/image/doc/docBox/s1.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 2){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s2.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 3){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s3.png">'
	    	            }
	    	            if (d.sidb_doc_stat ==4){
	    	            in2+= '                          <img style="width:80%;" src="/image/doc/docBox/s4.png">'
	    	            }
	    	            + '                    </td>'
	    	            + '                </tr>';
	    	    
	    	}

	    	var in3 = '</tbody></table>';

	    	mainDiv.innerHTML = in1 + in2 + in3;

	    	
	     $('#myTable').DataTable({  
	    	 "language": { 
	             "lengthMenu": " _MENU_ 개씩 보기",
	             "emptyTable": "결재 완료된 문서가 없습니다.",
	             "search": "검색: ",
	             "info": "현재 _START_ - _END_ / _TOTAL_건",
	             "paginate": {
	                 "next": "다음",
	                 "previous": "이전",
	                 "first": "처음",
	                 "last" : "마지막"
	             },
	    	  },
	          info: true, // 좌측하단 정보 표시 
	          searching: true, // 검색 기능 
	     	    ordering: false, // 정렬 기능
	          paging:true, // 페이징 기능 
	     //   lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
	     //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
	     //	    autoWidth: true,    //자동 열 조정
	     	   columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }],
	        displayLength: 5, //처음에 몇 건을 볼지 
	  		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
	          pagingType: "simple_numbers"
	          // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
	    	  
		
			 });
		   },
		  error:function(){
			alert("Ajax 처리중 오류 발생");
			}
	})
}; 



//반려함 Ajax
function denyBox(){
	// 기존에 있던 테이블 삭제
	$("#myTable").DataTable().destroy(); 
	var mainDiv = document.getElementById('tableOuter');
	mainDiv.innerHTML = ''; 
	
	$.ajax({url:"./denyBox.do",
	     type:"get",
	     success:function(data){ 
	    	console.log(data);
	    	var in3 = '';
	    	var in2 = '';
	    	var in1 = '<table id="myTable" class="stripe hover">'
	    	    + '    <thead>'
	    	    + '        <tr style="">'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '        </tr>'
	    	    + '    </thead>'
	    	    + '    <tbody>';

	    	for (let d of data) {
	    	        var dateString = d.sidb_doc_writedt;
	    	        var date = new Date(dateString);
	    	        var formattedDate = date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
	    	      if (d.sidt_temp_cd == 'TC000014') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-document fs-3x text-primary">'
                      + '                            <span class="path1"></span> <span class="path2"></span>'
                      + '                        </i> <br>    ' + d.sidt_temp_name
                      + '                    </td>'             
              }
               else if (d.sidt_temp_cd == 'TC000002')  {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-home fs-3x text-muted">'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }  else if (d.sidt_temp_cd == 'TC000013') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-briefcase fs-3x text-gray-900">'
                      + '                            <span class="path1"></span><span class="path2"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } else {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-bill fs-3x text-success">'
                      + '                            <span class="path1"></span> <span class="path2"></span> <span'
                      + '                                class="path3"></span> <span class="path4"></span> <span'
                      + '                                class="path5"></span> <span class="path6"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }
	    	    
	    	    
	    	    // 제목 + 기안일자
	    	         in2   += '                    <td><a href="#" onclick="detailBoard(' + d.sidb_doc_num + ')"> ' + d.sidb_doc_title + '  </a> <br> <br>'
	    	            + '                     기안일자| ' + formattedDate
	    	       
	    	    //사진 + 사원이름        
	    	            + '                    <td> <img style="width:50px; height:50px; border-radius: 22px;" src="data:image/png;base64,'+d.empl_pictureStr+'">' + d.empl_name + '</td>'

	    	    //프로그래스바 시작         
	    	            + '                    <td style="text-align:center;"> ';
	    	            
	    	            if (d.sidb_doc_stat == 1 && d.appr_name1 == undefined && d.appr_flag0 == 0 ) {
	    	            	  
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">'
	    	                    + ' <br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;"> ' + d.appr_name0 + ' </span>';
	    	            	    }  
	    	          
	    	            if (d.sidb_doc_stat == 1 && d.appr_name2 == undefined && d.appr_flag0 == 0) {
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">'
	    	                    + '<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">' + d.appr_name0 +' >&nbsp; </span> <span style="display:inline-block; width:22%;"></span>' + d.appr_name1 + ' </span> ' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 == undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">'+d.appr_name0
	    	            		+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">'+d.appr_name1
	    	            		+'&nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'+d.appr_name2+'&nbsp; &nbsp;' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag1 == 1 && d.appr_flag2 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">'
	    	            	      + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: DodgerBlue;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 1 && d.appr_name2 != undefined && d.appr_flag0 == 0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name1 == undefined && d.appr_flag0 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name2 == undefined && d.appr_flag1 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if (d.sidb_doc_stat == 3 && d.appr_name2 != undefined && d.appr_flag2 == 1) {
	    	                in2 += '<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">'
	    	                    + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;';
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name1 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:22%;"></span>'+d.appr_name1
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red;font-size: large; ">'
	    	            		+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag0 == 2){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red;font-size: large;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'
	    	            		+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag2 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">'
	    	            		+'<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size:large;">'
	    	            		+d.appr_name2+'</span>&nbsp; &nbsp;'
	    	            	
	    	            }
	    	            
	    	            in2 += '                    </td>'
	    	          
	    	            
	    	            
	    	       //기안 진행 완료 반려 아이콘     
	    	            + '                    <td>';
	    	            if (d.sidb_doc_stat == 1){
	    	            in2+= '                        <img style="width:80%;" src="/image/doc/docBox/s1.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 2){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s2.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 3){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s3.png">'
	    	            }
	    	            if (d.sidb_doc_stat ==4){
	    	            in2+= '                          <img style="width:80%;" src="/image/doc/docBox/s4.png">'
	    	            }
	    	            + '                    </td>'
	    	            + '                </tr>';
	    	    
	    	}

	    	var in3 = '</tbody></table>';

	    	mainDiv.innerHTML = in1 + in2 + in3;

	    	
	     $('#myTable').DataTable({  
	    	 "language": { 
	             "lengthMenu": " _MENU_ 개씩 보기",
	             "emptyTable": "반려된 문서가 없습니다.",
	             "search": "검색: ",
	             "info": "현재 _START_ - _END_ / _TOTAL_건",
	             "paginate": {
	                 "next": "다음",
	                 "previous": "이전",
	                 "first": "처음",
	                 "last" : "마지막"
	             },
	    	  },
	          info: true, // 좌측하단 정보 표시 
	          searching: true, // 검색 기능 
	     	    ordering: false, // 정렬 기능
	          paging:true, // 페이징 기능 
	     //   lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
	     //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
	     //	    autoWidth: true,    //자동 열 조정
	     	   columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }],
	        displayLength: 5, //처음에 몇 건을 볼지 
	  		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
	          pagingType: "simple_numbers"
	          // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
	    	  
		
			 });
		   },
		  error:function(){
			alert("Ajax 처리중 오류 발생");
			}
	})
}; 




//참조함 Ajax
function chamjoBox(){
	// 기존에 있던 테이블 삭제
	$("#myTable").DataTable().destroy(); 
	var mainDiv = document.getElementById('tableOuter');
	mainDiv.innerHTML = ''; 
	
	$.ajax({url:"./chamjoBox.do",
	     type:"get",
	     success:function(data){ 
	    	console.log(data);
	    	var in3 = '';
	    	var in2 = '';
	    	var in1 = '<table id="myTable" class="stripe hover">'
	    	    + '    <thead>'
	    	    + '        <tr style="">'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '        </tr>'
	    	    + '    </thead>'
	    	    + '    <tbody>';

	    	for (let d of data) {
	    	        var dateString = d.sidb_doc_writedt;
	    	        var date = new Date(dateString);
	    	        var formattedDate = date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
	    	      if (d.sidt_temp_cd == 'TC000014') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-document fs-3x text-primary">'
                      + '                            <span class="path1"></span> <span class="path2"></span>'
                      + '                        </i> <br>    ' + d.sidt_temp_name
                      + '                    </td>'             
              }
               else if (d.sidt_temp_cd == 'TC000002')  {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-home fs-3x text-muted">'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }  else if (d.sidt_temp_cd == 'TC000013') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-briefcase fs-3x text-gray-900">'
                      + '                            <span class="path1"></span><span class="path2"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } else {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-bill fs-3x text-success">'
                      + '                            <span class="path1"></span> <span class="path2"></span> <span'
                      + '                                class="path3"></span> <span class="path4"></span> <span'
                      + '                                class="path5"></span> <span class="path6"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }
	    	    
	    	    
	    	    // 제목 + 기안일자
	    	         in2   += '                    <td><a href="#" onclick="detailBoard(' + d.sidb_doc_num + ')"> ' + d.sidb_doc_title + '  </a> <br> <br>'
	    	            + '                     기안일자| ' + formattedDate
	    	       
	    	    //사진 + 사원이름        
	    	            + '                    <td> <img style="width:50px; height:50px; border-radius: 22px;" src="data:image/png;base64,'+d.empl_pictureStr+'">' + d.empl_name + '</td>'

	    	    //프로그래스바 시작         
	    	            + '                    <td style="text-align:center;"> ';
	    	            
	    	            if (d.sidb_doc_stat == 1 && d.appr_name1 == undefined && d.appr_flag0 == 0 ) {
	    	            	  
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">'
	    	                    + ' <br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;"> ' + d.appr_name0 + ' </span>';
	    	            	    }  
	    	          
	    	            if (d.sidb_doc_stat == 1 && d.appr_name2 == undefined && d.appr_flag0 == 0) {
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">'
	    	                    + '<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">' + d.appr_name0 +' >&nbsp; </span> <span style="display:inline-block; width:22%;"></span>' + d.appr_name1 + ' </span> ' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 == undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">'+d.appr_name0
	    	            		+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">'+d.appr_name1
	    	            		+'&nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'+d.appr_name2+'&nbsp; &nbsp;' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag1 == 1 && d.appr_flag2 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">'
	    	            	      + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: DodgerBlue;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 1 && d.appr_name2 != undefined && d.appr_flag0 == 0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name1 == undefined && d.appr_flag0 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name2 == undefined && d.appr_flag1 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if (d.sidb_doc_stat == 3 && d.appr_name2 != undefined && d.appr_flag2 == 1) {
	    	                in2 += '<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">'
	    	                    + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;';
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name1 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:22%;"></span>'+d.appr_name1
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red;font-size: large; ">'
	    	            		+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag0 == 2){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red;font-size: large;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'
	    	            		+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag2 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">'
	    	            		+'<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size:large;">'
	    	            		+d.appr_name2+'</span>&nbsp; &nbsp;'
	    	            	
	    	            }
	    	            
	    	            in2 += '                    </td>'
	    	          
	    	            
	    	            
	    	       //기안 진행 완료 반려 아이콘     
	    	            + '                    <td>';
	    	            if (d.sidb_doc_stat == 1){
	    	            in2+= '                        <img style="width:80%;" src="/image/doc/docBox/s1.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 2){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s2.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 3){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s3.png">'
	    	            }
	    	            if (d.sidb_doc_stat ==4){
	    	            in2+= '                          <img style="width:80%;" src="/image/doc/docBox/s4.png">'
	    	            }
	    	            + '                    </td>'
	    	            + '                </tr>';
	    	    
	    	}

	    	var in3 = '</tbody></table>';

	    	mainDiv.innerHTML = in1 + in2 + in3;

	    	
	     $('#myTable').DataTable({  
	    	 "language": { 
	             "lengthMenu": " _MENU_ 개씩 보기",
	             "emptyTable": "참조 지정된 문서가 없습니다.",
	             "search": "검색: ",
	             "info": "현재 _START_ - _END_ / _TOTAL_건",
	             "paginate": {
	                 "next": "다음",
	                 "previous": "이전",
	                 "first": "처음",
	                 "last" : "마지막"
	             },
	    	  },
	          info: true, // 좌측하단 정보 표시 
	          searching: true, // 검색 기능 
	     	    ordering: false, // 정렬 기능
	          paging:true, // 페이징 기능 
	     //   lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
	     //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
	     //	    autoWidth: true,    //자동 열 조정
	     	   columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }],
	        displayLength: 5, //처음에 몇 건을 볼지 
	  		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
	          pagingType: "simple_numbers"
	          // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
	    	  
		
			 });
		   },
		  error:function(){
			alert("Ajax 처리중 오류 발생");
			}
	})
}; 



//나한테 온 결재 요청함 Ajax
function myTurnBox(){
	// 기존에 있던 테이블 삭제
	$("#myTable").DataTable().destroy(); 
	var mainDiv = document.getElementById('tableOuter');
	mainDiv.innerHTML = ''; 
	
	$.ajax({url:"./myTurnBox.do",
	     type:"get",
	     success:function(data){ 
	    	console.log(data);
	    	var in3 = '';
	    	var in2 = '';
	    	var in1 = '<table id="myTable" class="stripe hover">'
	    	    + '    <thead>'
	    	    + '        <tr style="">'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '        </tr>'
	    	    + '    </thead>'
	    	    + '    <tbody>';

	    	for (let d of data) {
	    	        var dateString = d.sidb_doc_writedt;
	    	        var date = new Date(dateString);
	    	        var formattedDate = date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
	    	     if (d.sidt_temp_cd == 'TC000014') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-document fs-3x text-primary">'
                      + '                            <span class="path1"></span> <span class="path2"></span>'
                      + '                        </i> <br>    ' + d.sidt_temp_name
                      + '                    </td>'             
              }
               else if (d.sidt_temp_cd == 'TC000002')  {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-home fs-3x text-muted">'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }  else if (d.sidt_temp_cd == 'TC000013') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-briefcase fs-3x text-gray-900">'
                      + '                            <span class="path1"></span><span class="path2"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } else {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-bill fs-3x text-success">'
                      + '                            <span class="path1"></span> <span class="path2"></span> <span'
                      + '                                class="path3"></span> <span class="path4"></span> <span'
                      + '                                class="path5"></span> <span class="path6"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }
	    	    
	    	    
	    	    // 제목 + 기안일자
	    	         in2   += '                    <td><a href="#" onclick="detailBoard(' + d.sidb_doc_num + ')"> ' + d.sidb_doc_title + '  </a> <br> <br>'
	    	            + '                     기안일자| ' + formattedDate
	    	       
	    	    //사진 + 사원이름        
	    	            + '                    <td> <img style="width:50px; height:50px; border-radius: 22px;" src="data:image/png;base64,'+d.empl_pictureStr+'">' + d.empl_name + '</td>'

	    	    //프로그래스바 시작         
	    	            + '                    <td style="text-align:center;"> ';
	    	            
	    	            if (d.sidb_doc_stat == 1 && d.appr_name1 == undefined && d.appr_flag0 == 0 ) {
	    	            	  
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">'
	    	                    + ' <br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;"> ' + d.appr_name0 + ' </span>';
	    	            	    }  
	    	          
	    	            if (d.sidb_doc_stat == 1 && d.appr_name2 == undefined && d.appr_flag0 == 0) {
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">'
	    	                    + '<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">' + d.appr_name0 +' >&nbsp; </span> <span style="display:inline-block; width:22%;"></span>' + d.appr_name1 + ' </span> ' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 == undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">'+d.appr_name0
	    	            		+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">'+d.appr_name1
	    	            		+'&nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'+d.appr_name2+'&nbsp; &nbsp;' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag1 == 1 && d.appr_flag2 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">'
	    	            	     + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: DodgerBlue;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 1 && d.appr_name2 != undefined && d.appr_flag0 == 0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name1 == undefined && d.appr_flag0 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name2 == undefined && d.appr_flag1 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if (d.sidb_doc_stat == 3 && d.appr_name2 != undefined && d.appr_flag2 == 1) {
	    	                in2 += '<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">'
	    	                    + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;';
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name1 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:22%;"></span>'+d.appr_name1
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red;font-size: large; ">'
	    	            		+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag0 == 2){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red;font-size: large;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'
	    	            		+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag2 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">'
	    	            		+'<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size:large;">'
	    	            		+d.appr_name2+'</span>&nbsp; &nbsp;'
	    	            	
	    	            }
	    	            
	    	            in2 += '                    </td>'
	    	          
	    	            
	    	            
	    	       //기안 진행 완료 반려 아이콘     
	    	            + '                    <td>';
	    	            if (d.sidb_doc_stat == 1){
	    	            in2+= '                        <img style="width:80%;" src="/image/doc/docBox/s1.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 2){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s2.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 3){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s3.png">'
	    	            }
	    	            if (d.sidb_doc_stat ==4){
	    	            in2+= '                          <img style="width:80%;" src="/image/doc/docBox/s4.png">'
	    	            }
	    	            + '                    </td>'
	    	            + '                </tr>';
	    	    
	    	}

	    	var in3 = '</tbody></table>';

	    	mainDiv.innerHTML = in1 + in2 + in3;

	    	
	     $('#myTable').DataTable({  
	    	 "language": { 
	             "lengthMenu": " _MENU_ 개씩 보기",
	             "emptyTable": "결재 요청 중인 문서가 없습니다.",
	             "search": "검색: ",
	             "info": "현재 _START_ - _END_ / _TOTAL_건",
	             "paginate": {
	                 "next": "다음",
	                 "previous": "이전",
	                 "first": "처음",
	                 "last" : "마지막"
	             },
	    	  },
	          info: true, // 좌측하단 정보 표시 
	          searching: true, // 검색 기능 
	     	    ordering: false, // 정렬 기능
	          paging:true, // 페이징 기능 
	     //   lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
	     //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
	     //	    autoWidth: true,    //자동 열 조정
	     	   columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }],
	        displayLength: 5, //처음에 몇 건을 볼지 
	  		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
	          pagingType: "simple_numbers"
	          // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
	    	  
		
			 });
		   },
		  error:function(){
			alert("Ajax 처리중 오류 발생");
			}
	})
}; 



//내가 결재한 문서 조회함 Ajax
function iDidBox(){
	// 기존에 있던 테이블 삭제
	$("#myTable").DataTable().destroy(); 
	var mainDiv = document.getElementById('tableOuter');
	mainDiv.innerHTML = ''; 
	
	$.ajax({url:"./iDidBox.do",
	     type:"get",
	     success:function(data){ 
	    	console.log(data);
	    	var in3 = '';
	    	var in2 = '';
	    	var in1 = '<table id="myTable" class="stripe hover">'
	    	    + '    <thead>'
	    	    + '        <tr style="">'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '            <th></th>'
	    	    + '        </tr>'
	    	    + '    </thead>'
	    	    + '    <tbody>';

	    	for (let d of data) {
	    	        var dateString = d.sidb_doc_writedt;
	    	        var date = new Date(dateString);
	    	        var formattedDate = date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
	    	      if (d.sidt_temp_cd == 'TC000014') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-document fs-3x text-primary">'
                      + '                            <span class="path1"></span> <span class="path2"></span>'
                      + '                        </i> <br>    ' + d.sidt_temp_name
                      + '                    </td>'             
              }
               else if (d.sidt_temp_cd == 'TC000002')  {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-home fs-3x text-muted">'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              }  else if (d.sidt_temp_cd == 'TC000013') {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-briefcase fs-3x text-gray-900">'
                      + '                            <span class="path1"></span><span class="path2"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } else {
                  in2 += '                <tr style="min-height:200px; text-align:center; ">'
                      + '                    <td>'
                      + '                        <i class="ki-duotone ki-bill fs-3x text-success">'
                      + '                            <span class="path1"></span> <span class="path2"></span> <span'
                      + '                                class="path3"></span> <span class="path4"></span> <span'
                      + '                                class="path5"></span> <span class="path6"></span>'
                      + '                        </i> <br>       ' + d.sidt_temp_name
                      + '                    </td>'
              } 
	    	    
	    	    
	    	    // 제목 + 기안일자
	    	         in2   += '                    <td><a href="#" onclick="detailBoard(' + d.sidb_doc_num + ')"> ' + d.sidb_doc_title + '  </a> <br> <br>'
	    	            + '                     기안일자| ' + formattedDate
	    	       
	    	    //사진 + 사원이름        
	    	            + '                    <td> <img style="width:50px; height:50px; border-radius: 22px;" src="data:image/png;base64,'+d.empl_pictureStr+'">' + d.empl_name + '</td>'

	    	    //프로그래스바 시작         
	    	            + '                    <td style="text-align:center;"> ';
	    	            
	    	            if (d.sidb_doc_stat == 1 && d.appr_name1 == undefined && d.appr_flag0 == 0 ) {
	    	            	  
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">'
	    	                    + ' <br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;"> ' + d.appr_name0 + ' </span>';
	    	            	    }  
	    	          
	    	            if (d.sidb_doc_stat == 1 && d.appr_name2 == undefined && d.appr_flag0 == 0) {
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">'
	    	                    + '<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">' + d.appr_name0 +' >&nbsp; </span> <span style="display:inline-block; width:22%;"></span>' + d.appr_name1 + ' </span> ' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 == undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag0 == 1 && d.appr_flag1 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">'+d.appr_name0
	    	            		+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">'+d.appr_name1
	    	            		+'&nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'+d.appr_name2+'&nbsp; &nbsp;' 
	    	            }
	    	            if(d.sidb_doc_stat == 2 && d.appr_name2 != undefined && d.appr_flag1 == 1 && d.appr_flag2 ==0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">'
	    	            	      + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: DodgerBlue;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 1 && d.appr_name2 != undefined && d.appr_flag0 == 0){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name1 == undefined && d.appr_flag0 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 3 && d.appr_name2 == undefined && d.appr_flag1 == 1){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">'
	    	            		+'<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	           			+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">'
	    	           			+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if (d.sidb_doc_stat == 3 && d.appr_name2 != undefined && d.appr_flag2 == 1) {
	    	                in2 += '<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">'
	    	                    + '<br>&nbsp;&nbsp;&nbsp;기안<span style="display:inline-block; width:14%;"></span><span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name0 + ' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: MediumSeaGreen;">'
	    	                    + d.appr_name1 + '&nbsp;></span>&nbsp;&nbsp;<span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;font-size: large;">'
	    	                    + d.appr_name2 + '</span>&nbsp;&nbsp;';
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name1 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> &nbsp;&nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag0 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:22%;"></span>'+d.appr_name1
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 == undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">'
	    	            		+'<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">'
	    	            		+d.appr_name0+' > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red;font-size: large; ">'
	    	            		+d.appr_name1+'&nbsp; &nbsp;</span>'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag0 == 2){
	    	            	in2 += ' <img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">'
	    	            		+'<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">'
	    	            		+d.appr_name0+' ></span> <span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;'+d.appr_name1
	    	            		+'&nbsp;>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>'+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag1 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">'
	    	            		+'<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red;font-size: large;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>'
	    	            		+d.appr_name2+'&nbsp; &nbsp;'
	    	            }
	    	            if(d.sidb_doc_stat == 4 && d.appr_name2 != undefined && d.appr_flag2 == 2){
	    	            	in2 += '<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">'
	    	            		+'<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name0+' ></span><span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color:MediumSeaGreen;">'
	    	            		+d.appr_name1+' &nbsp;></span>&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size:large;">'
	    	            		+d.appr_name2+'</span>&nbsp; &nbsp;'
	    	            	
	    	            }
	    	            
	    	            in2 += '                    </td>'
	    	          
	    	            
	    	            
	    	       //기안 진행 완료 반려 아이콘     
	    	            + '                    <td>';
	    	            if (d.sidb_doc_stat == 1){
	    	            in2+= '                        <img style="width:80%;" src="/image/doc/docBox/s1.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 2){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s2.png">'
	    	            }
	    	            if (d.sidb_doc_stat == 3){
	    	            in2+= '                         <img style="width:80%;" src="/image/doc/docBox/s3.png">'
	    	            }
	    	            if (d.sidb_doc_stat ==4){
	    	            in2+= '                          <img style="width:80%;" src="/image/doc/docBox/s4.png">'
	    	            }
	    	            + '                    </td>'
	    	            + '                </tr>';
	    	    
	    	}

	    	var in3 = '</tbody></table>';

	    	mainDiv.innerHTML = in1 + in2 + in3;

	    	
	     $('#myTable').DataTable({  
	    	 "language": { 
	             "lengthMenu": " _MENU_ 개씩 보기",
	             "emptyTable": "승인 및 반려한 문서가 없습니다.",
	             "search": "검색: ",
	             "info": "현재 _START_ - _END_ / _TOTAL_건",
	             "paginate": {
	                 "next": "다음",
	                 "previous": "이전",
	                 "first": "처음",
	                 "last" : "마지막"
	             },
	    	  },
	          info: true, // 좌측하단 정보 표시 
	          searching: true, // 검색 기능 
	     	    ordering: false, // 정렬 기능
	          paging:true, // 페이징 기능 
	     //   lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
	     //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
	     //	    autoWidth: true,    //자동 열 조정
	     	   columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }],
	        displayLength: 5, //처음에 몇 건을 볼지 
	  		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
	          pagingType: "simple_numbers"
	          // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
	    	  
		
			 });
		   },
		  error:function(){
			alert("Ajax 처리중 오류 발생");
			}
	})
}; 





//임시보관함
$(document).ready( function () {
	$('#tempBox').DataTable({ 

    	"language": { 
            "emptyTable": "열람 가능한 결재문서가 없습니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "search": "검색: ",
            "paginate": {
                "next": "다음",
                "previous": "이전",
                "first": "처음",
                "last" : "마지막"
            },
          //https://datatables.net/reference/option/language   위를 더 디테일하게 수정하고싶으면 여기로~
        },
        
        info: true, // 좌측하단 정보 표시 
        searching: true, // 검색 기능 
   	    ordering: false, // 정렬 기능
        paging:true, // 페이징 기능 
        lengthChange: false, //  좌상단 몇 건씩 볼지 정하는 기능
   //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
   	    autoWidth: true,    //자동 열 조정
   //  	columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 150 },{ targets: 2, width: 30 },{ targets: 3, width: 230 },{ targets: 4, width: 30 }], //열의 너비 조절 0,1,2,3 순임
//      displayLength: 20, //처음에 몇 건을 볼지 
		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
        pagingType: "simple_numbers" 
        // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가

	});
});
