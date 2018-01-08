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
    <title>Dashboard</title>
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
    <div class="wrapper">

      <!-- Start Main Header -->
      <jsp:include page="../includes/header.jsp"></jsp:include>
      <!-- End Main Header -->
      
      <!-- Start Left Sidebar Menu -->
      <jsp:include page="../includes/leftsidebarmenu.jsp"></jsp:include>
	  <!-- End Left sidebar menu -->

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Dashboard
            <small>An Overview of the entire system</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
            <li class="active">Dashboard</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
	        <div class="row">
	        <div class="col-md-8">
			<h4 style="margin: 0.4em 0em;background-color: #00c0ef !important;padding: 0.2em;color: #fff;">
				User Summary
			</h4>
				<div class="col-md-6 col-sm-6 col-xs-12" style="padding-left:0;">
				  <a href="user" >
					  <div class="info-box">
						<span class="info-box-icon bg-aqua"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Total Registered Users</span>
						  <span class="info-box-number">1</span>
						</div><!-- /.info-box-content -->
					  </div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				<div class="col-md-6 col-sm-6 col-xs-12" style="padding-right:0;">
					<a href="user" >
					  <div class="info-box">
						<span class="info-box-icon bg-green"><i class="fa fa-users"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Todays User Count</span>
						  
						  <span class="info-box-number">1</span>
						</div><!-- /.info-box-content -->
					  </div><!-- /.info-box -->
					</a>
				</div><!-- /.col -->
				<div class="col-md-6 col-sm-6 col-xs-12" style="padding-left:0;">
				  <a href="user" >
				  	<div class="info-box">
						<span class="info-box-icon bg-yellow"><i class="fa fa-users"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Active Users</span>
						  <span class="info-box-number">1</span>
						</div><!-- /.info-box-content -->
				  	</div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				<div class="col-md-6 col-sm-6 col-xs-12" style="padding-right:0;">
				  <a href="user" >
				  	<div class="info-box">
						<span class="info-box-icon bg-yellow"><i class="fa fa-users"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">In-active Users</span>
						  <span class="info-box-number">1</span>
						</div><!-- /.info-box-content -->
				  	</div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				</div>
				<div class="col-md-4">
				<h4 style="margin: 0.4em 0;background-color: #00c0ef !important;padding: 0.2em;color: #fff;">
				Notifications
			    </h4>
			    <div class="panel">
			    <div class="panel-body" style="height:100%;max-height:195px; overflow-y:scroll;">
			    <ul class="timeline">
            <!-- timeline time label -->
            <li class="time-label">
                  <span class="bg-red">
                    16 Sept. 2017
                  </span>
            </li>
            <!-- /.timeline-label -->
            
            <!-- timeline item -->
            <li>
              <i class="fa fa-user bg-aqua"></i>

              <div class="timeline-item">
                <h5 class="timeline-header no-border" style="font-size:12px;"><a href="#">Sarah Young</a> accepted your friend request</h5>
                <span class="time"><i class="fa fa-clock-o"></i> 5 mins ago</span>
              </div>
            </li>
            <!-- END timeline item -->
            <!-- timeline item -->
            <li>
              <i class="fa fa-comments bg-yellow"></i>

              <div class="timeline-item">
                <h3 class="timeline-header" style="font-size:12px;"><a href="#">Jay White</a> commented on your post</h3>
				<span class="time"><i class="fa fa-clock-o"></i> 27 mins ago</span>
               </div>
            </li>
            <!-- END timeline item -->
            <li>
              <i class="fa fa-ellipsis-h bg-gray"></i>
              <div class="timeline-item" style="box-shadow:none;webkit-box-shadow: none;">
                <h3 class="timeline-header" style="font-size:10px;border:none;padding:8px 5px;"><a href="#" data-toggle="modal" data-target="#notifications">Show More</a></h3>
				
               </div>
            </li>
          </ul>
			    </div>
			    </div>
				</div>
			  </div><!-- /.row -->
			  <!-- USER SUMMARY ENDS -->
			  <div class="row">
			  <h4 style="margin: 0.4em 0.9em;background-color: #00c0ef !important;padding: 0.2em;color: #fff;">
				Brand Summary
			  </h4>
			  	<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order" >
					  <div class="info-box">
						<span class="info-box-icon bg-aqua"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Total Registered brands</span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
					  </div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order">
					  <div class="info-box">
						<span class="info-box-icon bg-aqua"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Top rated brand count</span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
					  </div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order">
				  	<div class="info-box">
						<span class="info-box-icon bg-green"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Active brand count</span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
					  </div><!-- /.info-box -->
					</a>
				</div><!-- /.col -->
				<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order">
				  	<div class="info-box">
						<span class="info-box-icon bg-yellow"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Inactive brand count</span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
				  	</div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order">
				  	<div class="info-box">
						<span class="info-box-icon bg-yellow"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Most subscribed brands</span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
				  	</div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
			  </div><!-- /.row -->
			  <!-- BRAND SUMMERY ENDS -->
			  <div class="row">
			  <h4 style="margin: 0.4em 0.9em;background-color: #00c0ef !important;padding: 0.2em;color: #fff;">
				Post Summary
			  </h4>
			  	<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order" >
					  <div class="info-box">
						<span class="info-box-icon bg-aqua"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Total post shared on sift.</span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
					  </div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order">
					  <div class="info-box">
						<span class="info-box-icon bg-aqua"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Total post shared on</span>
						  <span class="info-box-text">facebook. </span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
					  </div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order">
				  	<div class="info-box">
						<span class="info-box-icon bg-green"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Total post shared on</span>
						  <span class="info-box-text">twitter. </span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
					  </div><!-- /.info-box -->
					</a>
				</div><!-- /.col -->
				<div class="col-md-4 col-sm-6 col-xs-12">
				  <a href="order">
				  	<div class="info-box">
						<span class="info-box-icon bg-yellow"><i class="fa fa-inbox"></i></span>
						<div class="info-box-content">
						  <span class="info-box-text">Total post shared on</span>
						  <span class="info-box-text">instagram.</span>
						  <span class="info-box-number">2</span>
						</div><!-- /.info-box-content -->
				  	</div><!-- /.info-box -->
				  </a>
				</div><!-- /.col -->
				
			  </div><!-- /.row -->
			  <!-- POST SUMMARY ENDS -->
			</section><!-- /.content -->
      </div><!-- /.content-wrapper -->

      <!-- Start Main Footer -->
      <jsp:include page="../includes/footer.jsp" />
      <!-- End Main Footer -->

    </div>
    <!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->
	<jsp:include page="../includes/requiredbodyjs.jsp" />
    <!-- ./REQUIRED JS SCRIPTS -->
    
    <!-- MODAL -->
    <div id="notifications" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-lg">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Notifications</h4>
	      </div>
	      <div class="modal-body">
	       <div class="row">
	       <div class="col-sm-12">
	       <ul class="timeline">
            <!-- timeline time label -->
            <li class="time-label">
                  <span class="bg-red">
                    10 Feb. 2014
                  </span>
            </li>
            <!-- /.timeline-label -->
            <!-- timeline item -->
            <li>
              <i class="fa fa-envelope bg-blue"></i>

              <div class="timeline-item">
                <span class="time"><i class="fa fa-clock-o"></i> 12:05</span>

                <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>

                <div class="timeline-body">
                  Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                  weebly ning heekya handango imeem plugg dopplr jibjab, movity
                  jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                  quora plaxo ideeli hulu weebly balihoo...
                </div>
                <div class="timeline-footer">
                  <a class="btn btn-primary btn-xs">Read more</a>
                  <a class="btn btn-danger btn-xs">Delete</a>
                </div>
              </div>
            </li>
            <!-- END timeline item -->
            <!-- timeline item -->
            <li>
              <i class="fa fa-user bg-aqua"></i>

              <div class="timeline-item">
                <span class="time"><i class="fa fa-clock-o"></i> 5 mins ago</span>

                <h3 class="timeline-header no-border"><a href="#">Sarah Young</a> accepted your friend request</h3>
              </div>
            </li>
            <!-- END timeline item -->
            <!-- timeline item -->
            <li>
              <i class="fa fa-comments bg-yellow"></i>

              <div class="timeline-item">
                <span class="time"><i class="fa fa-clock-o"></i> 27 mins ago</span>

                <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                <div class="timeline-body">
                  Take me to your leader!
                  Switzerland is small and neutral!
                  We are more like Germany, ambitious and misunderstood!
                </div>
                <div class="timeline-footer">
                  <a class="btn btn-warning btn-flat btn-xs">View comment</a>
                </div>
              </div>
            </li>
            <!-- END timeline item -->
            <!-- timeline time label -->
            <li class="time-label">
                  <span class="bg-green">
                    3 Jan. 2014
                  </span>
            </li>
            <!-- /.timeline-label -->
            <!-- timeline item -->
            <li>
              <i class="fa fa-camera bg-purple"></i>

              <div class="timeline-item">
                <span class="time"><i class="fa fa-clock-o"></i> 2 days ago</span>

                <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                <div class="timeline-body">
                  <img src="http://placehold.it/150x100" alt="..." class="margin">
                  <img src="http://placehold.it/150x100" alt="..." class="margin">
                  <img src="http://placehold.it/150x100" alt="..." class="margin">
                  <img src="http://placehold.it/150x100" alt="..." class="margin">
                </div>
              </div>
            </li>
            <!-- END timeline item -->
            <!-- timeline item -->
            <li>
              <i class="fa fa-video-camera bg-maroon"></i>

              <div class="timeline-item">
                <span class="time"><i class="fa fa-clock-o"></i> 5 days ago</span>

                <h3 class="timeline-header"><a href="#">Mr. Doe</a> shared a video</h3>

                <div class="timeline-body">
                  <div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/tMWkeBIohBs" frameborder="0" allowfullscreen=""></iframe>
                  </div>
                </div>
                <div class="timeline-footer">
                  <a href="#" class="btn btn-xs bg-maroon">See comments</a>
                </div>
              </div>
            </li>
            <!-- END timeline item -->
            <li>
              <i class="fa fa-clock-o bg-gray"></i>
            </li>
          </ul>
	       </div>
	       </div> 
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
  </body>
</html>
