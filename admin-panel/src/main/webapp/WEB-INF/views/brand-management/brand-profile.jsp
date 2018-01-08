<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Brand Profile</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    
	<!-- Required head CSS -->
	<jsp:include page="../includes/requiredheadcss.jsp" />
	<!-- ./Required head CSS -->
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <!--
  BODY TAG OPTIONS:
  =================
  Apply one or more of the following classes to get the
  desired effect
  |---------------------------------------------------------|
  | SKINS         | skin-blue                               |
  |               | skin-black                              |
  |               | skin-purple                             |
  |               | skin-yellow                             |
  |               | skin-red                                |
  |               | skin-green                              |
  |---------------------------------------------------------|
  |LAYOUT OPTIONS | fixed                                   |
  |               | layout-boxed                            |
  |               | layout-top-nav                          |
  |               | sidebar-collapse                        |
  |               | sidebar-mini                            |
  |---------------------------------------------------------|
  -->
  <body class="hold-transition skin-blue sidebar-mini">
    
    <!-- ./wrapper -->
    <div class="wrapper">

      <!-- Start Main Header -->
      <jsp:include page="../includes/header.jsp"></jsp:include>
      <!-- End Main Header -->
      
      <!-- Sidebar Menu -->
      <jsp:include page="../includes/leftsidebarmenu.jsp"></jsp:include>
	  <!-- ./Sidebar menu -->

      <!-- Content Wrapper -->
      <div class="content-wrapper">
        
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Brand Profile
          </h1>
         <ol class="breadcrumb">
           <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
           <li class="active">Brand Profile</li>
         </ol>
        </section>

        <!-- Main content -->
        <div class="container" style="width:98%;">
        <div class="row panel">
        <div class="col-sm-12 col-md-12 panel-body">
        <img src="dist/img/spotify-cover-pic.jpg" alt="Spotify" class="img-responsive" width="100%">
        </div>
        </div>
        <div class="row panel" style="margin-bottom:0">
        <div class="col-sm-12 panel-body">
        <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-2">
        <center><img src="dist/img/spotify-profile-pic.jpg" alt="Spotify" class="img-responsive"></center>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-10">
        <div class="row hidden-sm hidden-md">
        <div class="col-sm-3">
        <ul style="list-style-type:none;">
        <li><h4>Name:</h4></li>
         <li><h4>Category:</h4></li>
        <li><h4>Created by:</h4></li>
        </ul>
        </div>
        <div class="col-sm-4">
        <ul style="list-style-type:none;padding:0">
        <li><h4><em>Spotify | Music Re-defined</em></h4></li>
         <li><h4><em>Music, Streaming</em></h4></li>
        <li><h4><em>John Doe</em></h4></li>
        </ul>
        </div>
        <div class="col-sm-3">
        <ul style="list-style-type:none;">
        <li><h4>Creation Date:</h4></li>
        <li><h4>Subscribed By: </h4></li>
        </ul>
        </div>
        <div class="col-sm-2">
        <ul style="list-style-type:none;padding:0">
        <li><h4><em>18/9/2017</em></h4></li>
        <li><h4><em>14</em></h4></li>
        </ul>
        </div>
        </div>
        <div class="row hidden-lg">
        <br/>
        <div class="col-sm-12 col-md-12">
        <table class="table table-bordered">
    
    <tbody>
      <tr>
        <td>Name</td>
        <td>Spotify | Music Re-defined</td>
        
      </tr>
      <tr>
        <td>Category</td>
        <td>Music, Streaming</td>
        
      </tr>
      <tr>
        <td>Created by</td>
        <td>John Doe</td>
        
      </tr>
      <tr>
        <td>Creation Date</td>
        <td>18/9/2017</td>
        
      </tr>
      <tr>
        <td>Subscribed By</td>
        <td>14</td>
        
      </tr>
    </tbody>
  </table>
        </div>
        </div>
        </div>
        </div>
        </div>
        </div>
        <div class="row">
        <div class="col-sm-12" style="padding-right:0;">
        <h5 class="pull-right" style="background: #fafafa;padding: 5px;border: 1px solid #000;  border-radius: 4px;"><strong>Status: </strong><span class="label bg-green">Brand Active</span><span class="label bg-red">Brand De-activated</span><br/><br/><span><a style="text-decoration:none;font-size:14px;cursor:pointer;" data-toggle="modal" data-target="#brandstatus">Activate/De-activate Brand</a></span></h5>
        </div>
        </div>
        </div>
        
        <!-- /. Main content -->
      
      </div>
      <!-- /.Content-wrapper -->

      <!-- Start Main Footer -->
      <jsp:include page="../includes/footer.jsp" />
      <!-- End Main Footer -->

    </div>
    <!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->
	<jsp:include page="../includes/requiredbodyjs.jsp" />
    <!-- ./REQUIRED JS SCRIPTS -->
    <script>
  $(function () {
    
 

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({

      checkboxClass: 'icheckbox_minimal-red',
      radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass   : 'iradio_flat-green'
    })

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
    
  })
</script>
  </body>
</html>
