
ClassicEditor
	.create( document.querySelector( '#editor' ), {

		toolbar: {
			items: [
				'undo',
				'redo',
				'|',
				'heading',
				'fontFamily',
				'fontSize',
				'|',
				'fontColor',
				'fontBackgroundColor',
				'highlight',
				'|',				
				'bold',
				'underline',
				'italic',
				'|',
				'bulletedList',
				'todoList',
				'numberedList',
				'|',
				'outdent',
				'indent',
				'|',
				'link',
				'imageUpload',
				'insertTable',
				'|',
				'sourceEditing',
				'findAndReplace'
			]
		},
		language: 'ko',
		image: {
			toolbar: [
				'imageTextAlternative',
				'toggleImageCaption',
				'imageStyle:inline',
				'imageStyle:block',
				'imageStyle:side'
			]
		},
		table: {
			contentToolbar: [
				'tableColumn',
				'tableRow',
				'mergeTableCells',
				'tableCellProperties',
				'tableProperties'
			]
		},
		/* 이미지 업로드 */
        simpleUpload: {
            uploadUrl: './uploadImage.do',
            withCredentials: true,
        }, 
        /* 문서 자동저장 */
		autosave: {
            save( editor ) {
                return saveData( editor.getData() );
        }
        }
	} )
	.then( editor => {
		window.editor = editor;
	} )
	.catch( error => {
		console.log( error );
	})
	
	/* 문서 자동저장 */		
	const HTTP_SERVER_LAG = Math.floor(Math.random() * (2000 - 500 + 1)) + 500;	
	function saveData( data ) {
	    return new Promise( resolve => {
	        setTimeout( () => {
	            console.log( 'Saved', data );
	            
	            resolve();
	        }, HTTP_SERVER_LAG );
		} );
	}
	
