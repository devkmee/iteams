<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta charset="UTF-8">
    <title>Chat Room</title>
    <!-- Latest minified Bootstrap & JQuery-->
    <!-- Custom styles for this template -->
    <style>
        .btn.active {
            display: none;
        }

        .btn span:nth-of-type(1)  {
            display: none;
        }
        .btn span:last-child  {
            display: block;
        }

        .btn.active span:nth-of-type(1)  {
            display: block;
        }
        .btn.active span:last-child  {
            display: none;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${cPath }/resources/static/css/main.css"/>
<body class="text-center">

<!-- Begin page content -->
<main role="main" class="container-fluid">
    <h1>Simple WebRTC Signalling Server</h1>
    <input type="hidden" id="id" name="id" th:value="${id}"/>
    <div class="col-lg-12 mb-3">
        <div class="mb-3" th:text="'User: ' + ${uuid} + ' @ Room #' + ${id}">
            Local User Id
        </div>
        <div class="col-lg-12 mb-3">
            <div class="d-flex justify-content-around mb-3">
                <div id="buttons" class="row">
                    <div class="btn-group mr-2" role="group">
                        <div class="mr-2" data-toggle="buttons">
                            <label class="btn btn-outline-success" id="video_off">
                                <input type="radio" name="options" style="display:none" autocomplete="off">Video On
                            </label>
                            <label class="btn btn-outline-warning active" id="video_on">
                                <input type="radio" name="options" style="display:none" autocomplete="off" checked>Video Off
                            </label>
                        </div>
                        <div class="mr-2" data-toggle="buttons">
                            <label class="btn btn-outline-success" id="audio_off">
                                <input type="radio" name="options" style="display:none" autocomplete="off">Audio On
                            </label>
                            <label class="btn btn-outline-warning active" id="audio_on">
                                <input type="radio" name="options" style="display:none" autocomplete="off" checked>Audio Off
                            </label>
                        </div>
                    </div>

                    <!--<button type="button" class="btn btn-outline-success" id="audio" data-toggle="button">Audio</button>-->
                    <a th:href="@{/room/{id}/user/{uuid}/exit(id=${id},uuid=${uuid})}">
                        <button type="button" class="btn btn-outline-danger" id="exit" name="exit">
                            Exit Room
                        </button>
                    </a>
                </div>
            </div>
        </div>

        <div class="row justify-content-around mb-3">
            <div class="col-lg-6 mb-3">
                <video id="local_video" autoplay playsinline></video>
            </div>
            <div class="col-lg-6 mb-3">
                <video id="remote_video" autoplay playsinline></video>
            </div>
        </div>
    </div>
</main>

<script src="${cPath }/resources/static/js/webrtc_client.js"></script>
</body>