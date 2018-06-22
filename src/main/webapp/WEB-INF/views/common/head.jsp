<%--
  Created by IntelliJ IDEA.
  User: liuxianhu
  Date: 2018/6/22
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Dashboard - Ace Admin</title>

    <meta name="description" content="overview &amp; stats">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <link  rel="icon" href="<%=request.getContextPath()%>/statics/favicon.ico" type="image/x-icon" />
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="statics/css/bootstrap.min.css">
    <link rel="stylesheet" href="statics/css/font-awesome.min.css">

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="statics/css/fonts.googleapis.com.css">

    <!-- ace styles -->
    <link rel="stylesheet" href="statics/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href="statics/css/ace-skins.min.css">
    <link rel="stylesheet" href="statics/css/ace-rtl.min.css">

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <%--<script async="" src="statics/js/analytics.js"></script>--%>
    <script type="text/javascript" async="" src="statics/js/watch.js">

    </script><script src="statics/js/ace-extra.min.js"></script>
    <style>
        @keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}
        @-moz-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}
        @-webkit-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}
        @-ms-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}
        @-o-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}
        .ace-save-state{
            animation-duration:10ms;
            -o-animation-duration:10ms;
            -ms-animation-duration:10ms;
            -moz-animation-duration:10ms;
            -webkit-animation-duration:10ms;
            animation-delay:0s;
            -o-animation-delay:0s;
            -ms-animation-delay:0s;
            -moz-animation-delay:0s;
            -webkit-animation-delay:0s;
            animation-name:nodeInserted;
            -o-animation-name:nodeInserted;
            -ms-animation-name:nodeInserted;
            -moz-animation-name:nodeInserted;
            -webkit-animation-name:nodeInserted
        }
    </style>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="assets/js/html5shiv.min.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .jqstooltip {
            position: absolute;
            left: 0px;
            top: 0px;
            visibility: hidden;
            background: rgb(0, 0, 0) transparent;
            background-color: rgba(0,0,0,0.6);
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
            color: white;
            font: 10px arial, san serif;
            text-align: left;
            white-space: nowrap;
            padding: 5px;
            border: 1px solid white;
            z-index: 10000;
        }
        .jqsfield {
            color: white;
            font: 10px arial, san serif;
            text-align: left;
        }
    </style>
</head>
