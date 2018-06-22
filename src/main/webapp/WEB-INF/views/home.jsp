<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
	<jsp:include page="../views/common/head.jsp" />

	<body class="no-skin">
		<jsp:include page="../views/common/navbar.jsp" />

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<jsp:include page="../views/common/menu.jsp" />

			<div class="main-content">

			</div><!-- /.main-content -->

			<jsp:include page="../views/common/main-footer.jsp" />

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<jsp:include page="../views/common/tail-load-js.jsp" />


		<div class="tooltip top in" style="display: none;"><div class="tooltip-inner"></div></div><div id="server_ip_sips_hover_box_id" data-sip-state="right" style="position: fixed; top: 10px; right: 10px; padding: 4px 6px; border: 1px solid black; background-color: rgb(255, 155, 51); border-radius: 12px; font-size: 13px; font-family: arial; font-weight: bold; line-height: 14px; color: rgb(255, 255, 255); z-index: 100001;">
		
		</div>
	</body>
</html>