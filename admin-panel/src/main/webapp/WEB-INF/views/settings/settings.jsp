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
    <title>Settings</title>
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
            Settings
          </h1>
         <ol class="breadcrumb">
           <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
           <li class="active">Settings</li>
         </ol>
        </section>

        <!-- Main content -->
        <div class="container" style="width:98%">
        <div class="row panel">
        <div class="col-sm-12 col-md-12 col-lg-12 panel-body">
         <img src="http://via.placeholder.com/1000x350" alt="Spotify" class="img-responsive" width="100%">
        </div>
        <div class="col-sm-12 col-md-12 hidden-lg">
        <h4 class="text-center">Upload cover picture here <br/> (1000 X 350)</h4>
        <input type="file" style="margin:0 auto;">
        <br/>
        <br/>
        </div>
        </div>
        <div class="row panel">
        <div class="col-sm-12 panel-body">
        <div class="row">
        <div class="col-sm-6 col-md-6 col-lg-3">
        <center><img src="http://via.placeholder.com/200x200" alt="Spotify" class="img-responsive"></center>
        </div>
        <div class="col-sm-6 col-md-6 col-lg-3" style="border-right:2px dashed #ccc;">
        <h4 class="text-center">Upload Logo here <br/> (200 X 200)</h4>
        <input type="file" style="margin:0 auto">
        <br/>
        <br/>
        <br/>
        <br/>
        <center><button class="btn btn-default">Update</button></center>
        </div>
        <div class="hidden-sm hidden-md col-lg-3" style="border-right:2px dashed #ccc;">
        <h4 class="text-center">Upload cover picture here <br/> (1000 X 350)</h4>
        <input type="file">
        <br/>
        <br/>
        <br/>
        <br/>
        <center><button class="btn btn-default">Update</button></center>
        </div>
        <div class="hidden-sm hidden-md col-lg-3">
        <h4 class="text-center">Note:<br/></h4>
        <h5 class="text-center">Please use the provided resolutions for the logo and the cover pic.<br/><br/>Cover picture will be displayed above once updated.</h5>
        </div>
        </div>
        </div>
        </div>
        <div class="row panel">
        <div class="col-sm-12 panel-body">
        <form>
        <div class="row">
                <div class="col-xs-4">
                 <label>First Name</label>
                  <input type="text" class="form-control" value="John" placeholder="First Name" style="border-radius:0;">
                </div>
                <div class="col-xs-4">
                 <label>Last Name</label>
                  <input type="text" class="form-control" value="Doe" placeholder="Last Name" style="border-radius:0;">
                </div>
                <div class="col-xs-4">
                <label>Email Address</label>
			        <div class="input-group">
			        		 
			                <span class="input-group-addon">@</span>
			                <input type="text" class="form-control" value="johndoe@gmail.com" placeholder="Email address">
					</div>
        	    </div>
        </div>
        <div class="row">
        <BR/>
         <div class="col-xs-4">
         		 <label>Contact Number</label>
                  <input type="text" class="form-control" value="123456789" placeholder="Contact no." style="border-radius:0;">
                </div>
                <div class="col-xs-4">
                 <label>Alternate Contact Number</label>
                  <input type="text" class="form-control" value="987654321" placeholder="Alternate Contact No." style="border-radius:0;">
                </div>
                <div class="col-xs-4">
                 <label>Zipcode</label>
			        <input type="number" class="form-control" value="411004" placeholder="Zipcode" style="border-radius:0;">
        	    </div>
        </div>
        <div class="row">
        <br/>
        <div class="col-xs-4">
        <div class="form-group">
                  <label>City</label>
                  <select class="form-control" style="border-radius:0;">
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select>
        </div>
        </div>
        <div class="col-xs-4">
        <div class="form-group">
                  <label>State</label>
                  <select class="form-control" style="border-radius:0;">
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select>
        </div>
        </div>
        <div class="col-xs-4">
        <div class="form-group">
                  <label>Region/Province</label>
                  <select class="form-control" style="border-radius:0;">
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select>
        </div>
        </div>
        </div>
        <div class="row">
        <div class="col-sm-12">
        <div class="form-group">
                  <label>Street Address</label>
                  <textarea class="form-control"  rows="3" placeholder="Enter street address" style="border-radius:0;"></textarea>
                </div>
        </div>
        </div>
        <div class="row">
        <div class="col-sm-12">
        <button class="btn btn-primary pull-right">Save/Update</button>
        </div>
        </div>
        </form>
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
    
  </body>
</html>
