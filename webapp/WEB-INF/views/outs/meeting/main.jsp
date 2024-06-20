<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<script src='https://8x8.vc/external_api.js' async></script>
<style>
html, body, #jaas-container {
	height: 100%;
}
</style>
<script type="text/javascript">
          window.onload = () => {
            const api = new JitsiMeetExternalAPI("8x8.vc", {
              roomName: "vpaas-magic-cookie-fc0805ff32f94a94bfe7429486de55aa/SampleAppMeaningfulAcquisitionsLastRespectively",
              parentNode: document.querySelector('#jaas-container')
            });
          }
        </script>
</head>
<body>
<div id="jaas-container"></div>

</body>
