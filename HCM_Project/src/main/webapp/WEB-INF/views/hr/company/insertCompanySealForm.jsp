<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<style type="text/css">
	.dropzone .dz-preview .dz-progress{
      display: none;
     }
	
	.sealBtn{
		margin: 2px;
	}
	
</style>
<title>회사직인입력</title>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp" %>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
		data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
		data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
		data-kt-app-sidebar-push-header="true"
		data-kt-app-sidebar-push-toolbar="true"
		data-kt-app-sidebar-push-footer="true"
		data-kt-app-toolbar-enabled="true" class="app-default">

		<div class="app-wrapper flex-column flex-row-fluid">
			<div class="app-toolbar py-3 py-lg-6">
				<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
					<!--begin::Page title-->
					<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
						<!--begin::Title-->
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">직인입력</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">직인입력창</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5 sealDropZone">
							
								<!--begin::Form-->
								<form class="form" method="post" enctype="multipart/form-data">
								    <!--begin::Input group-->
								    <div class="form-group row">
								        <!--begin::Label-->
								        <label class="col-lg-2 col-form-label text-lg-right">업로드 파일선택:</label>
								        <!--end::Label-->
								
								        <!--begin::Col-->
								        <div class="col-lg-10">
								            <!--begin::Dropzone-->
								            <div class="dropzone dropzone-queue mb-2" id="companySealUpload">
								                <!--begin::Controls-->
								                <div class="dropzone-panel mb-lg-0 mb-2">
								                    <a class="dropzone-select btn btn-sm btn-primary me-2">파일</a>
								                    <a class="dropzone-upload btn btn-sm btn-light-primary me-2">직인등록</a>
								                    <a class="dropzone-remove-all btn btn-sm btn-light-primary">직인제거</a>
								                </div>
								                <!--end::Controls-->
								
								                <!--begin::Items-->
								                <div class="dropzone-items wm-200px">
								                    <div class="dropzone-item" style="display:none">
								                        <!--begin::File-->
								                        <div class="dropzone-file">
								                            <div class="dropzone-filename" title="some_image_file_name.jpg">
								                                <span data-dz-name>some_image_file_name.jpg</span>
								                                <strong>(<span data-dz-size>340kb</span>)</strong>
								                            </div>
								
								                            <div class="dropzone-error" data-dz-errormessage></div>
								                        </div>
								                        <!--end::File-->
								
								                        <!--begin::Progress-->
								                        <div class="dropzone-progress">
								                            <div class="progress">
								                                <div
								                                    class="progress-bar bg-primary"
								                                    role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0" data-dz-uploadprogress>
								                                </div>
								                            </div>
								                        </div>
								                        <!--end::Progress-->
								
								                        <!--begin::Toolbar-->
								                        <div class="dropzone-toolbar">
								                            <span class="dropzone-start"><i class="bi bi-play-fill fs-3"></i></span>
								                            <span class="dropzone-cancel" data-dz-remove style="display: none;"><i class="bi bi-x fs-3"></i></span>
								                            <span class="dropzone-delete" data-dz-remove><i class="bi bi-x fs-1"></i></span>
								                        </div>
								                        <!--end::Toolbar-->
								                    </div>
								                </div>
								                <!--end::Items-->
								            </div>
								            <!--end::Dropzone-->
								
								            <!--begin::Hint-->
								            <span class="form-text text-muted">최대 파일사이즈는 10MB이고, JPG,PNG,JPEG파일만 업로득가 가능합니다.</span>
								            <!--end::Hint-->
								        </div>
								        <!--end::Col-->
								    </div>
								    <!--end::Input group-->
								</form>
								<!--end::Form-->
								
								<div>
									<a onclick="javascript:window.history.back(-1)" class="btn btn-primary me-10 sealBtn">취소</a>
								</div>
							</div>
							<script type="text/javascript">
								
								// set the dropzone container id
								const id = "#companySealUpload";
								const dropzone = document.querySelector(id);

								// set the preview element template
								var previewNode = dropzone.querySelector(".dropzone-item");
								previewNode.id = "";
								var previewTemplate = previewNode.parentNode.innerHTML;
								previewNode.parentNode.removeChild(previewNode);

								var myDropzone = new Dropzone(id, { // Make the whole body a dropzone
								    url: "./companySealUpload.do", // Set the url for your upload script location
								    parallelUploads: 20,
								    previewTemplate: previewTemplate,
								    maxFilesize: 5, // Max filesize in MB
								    autoQueue: false, // Make sure the files aren't queued until manually added
								    previewsContainer: id + " .dropzone-items", // Define the container to display the previews
								    clickable: id + " .dropzone-select" // Define the element that should be used as click trigger to select files.
								});

								myDropzone.on("addedfile", function (file) {
								    // Hookup the start button
								    file.previewElement.querySelector(id + " .dropzone-start").onclick = function () { myDropzone.enqueueFile(file); };
								    const dropzoneItems = dropzone.querySelectorAll('.dropzone-item');
								    dropzoneItems.forEach(dropzoneItem => {
								        dropzoneItem.style.display = '';
								    });
								    dropzone.querySelector('.dropzone-upload').style.display = "inline-block";
								    dropzone.querySelector('.dropzone-remove-all').style.display = "inline-block";
								});

								// Update the total progress bar
								myDropzone.on("totaluploadprogress", function (progress) {
								    const progressBars = dropzone.querySelectorAll('.progress-bar');
								    progressBars.forEach(progressBar => {
								        progressBar.style.width = progress + "%";
								    });
								});

								myDropzone.on("sending", function (file) {
								    // Show the total progress bar when upload starts
								    const progressBars = dropzone.querySelectorAll('.progress-bar');
								    progressBars.forEach(progressBar => {
								        progressBar.style.opacity = "1";
								    });
								    // And disable the start button
								    file.previewElement.querySelector(id + " .dropzone-start").setAttribute("disabled", "disabled");
								});

								// Hide the total progress bar when nothing's uploading anymore
								myDropzone.on("complete", function (progress) {
								    const progressBars = dropzone.querySelectorAll('.dz-complete');

								    setTimeout(function () {
								        progressBars.forEach(progressBar => {
								            progressBar.querySelector('.progress-bar').style.opacity = "0";
								            progressBar.querySelector('.progress').style.opacity = "0";
								            progressBar.querySelector('.dropzone-start').style.opacity = "0";
								        });
								    }, 300);
								});

								// Setup the buttons for all transfers
								dropzone.querySelector(".dropzone-upload").addEventListener('click', function () {
								    myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED));
								});

								// Setup the button for remove all files
								dropzone.querySelector(".dropzone-remove-all").addEventListener('click', function () {
								    dropzone.querySelector('.dropzone-upload').style.display = "none";
								    dropzone.querySelector('.dropzone-remove-all').style.display = "none";
								    myDropzone.removeAllFiles(true);
								});

								// On all files completed upload
								myDropzone.on("queuecomplete", function (progress) {
								    const uploadIcons = dropzone.querySelectorAll('.dropzone-upload');
								    uploadIcons.forEach(uploadIcon => {
								        uploadIcon.style.display = "none";
								    });
								});

								// On all files removed
								myDropzone.on("removedfile", function (file) {
								    if (myDropzone.files.length < 1) {
								        dropzone.querySelector('.dropzone-upload').style.display = "none";
								        dropzone.querySelector('.dropzone-remove-all').style.display = "none";
								    }
								});
								
								
							</script>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
				
				<!-- 내용 시작 -->
				
				<!-- 내용 끝 -->
				
				
			</div>
		</div>
		
		
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>