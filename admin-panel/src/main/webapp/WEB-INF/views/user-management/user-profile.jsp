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
    <title>User Profile</title>
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
            User Profile
          </h1>
         <ol class="breadcrumb">
           <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
           <li class="active">User Profile</li>
         </ol>
        </section>

        <!-- Main content -->
        <div class="container" style="width:98%;">
        <div class="row panel" style="margin-bottom:0;">
        <div class="col-sm-12 panel-body">
        <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-2">
        <img src="dist/img/profile-pic.png" alt="Profile Picture 200X200" style="margin:0 auto" class="img-responsive">
        </div>
        <div class="col-sm-12 col-md-12 col-lg-10">
        <div class="row hidden-sm hidden-md">
        <div class="hidden-sm col-md-2">
        <ul style="list-style-type:none;">
        <li><h4>Name:</h4></li>
         <li><h4>Email:</h4></li>
        <li><h4>Contact:</h4></li>
        </ul>
        </div>
        <div class="col-md-4 hidden-sm">
        <ul style="list-style-type:none;padding:0">
        <li><h4><em>John Doe</em></h4></li>
         <li><h4><em>john.doe@gmail.com</em></h4></li>
        <li><h4><em>9503158727</em></h4></li>
        </ul>
        </div>
        <div class="col-md-3 hidden-sm">
        <ul style="list-style-type:none;">
        <li><h4>Subscriptions:</h4></li>
        <li><h4>Subscribed By: </h4></li>
        </ul>
        </div>
        <div class="col-md-3 hidden-sm">
        <ul style="list-style-type:none;padding:0">
        <li><h4><em>115</em></h4></li>
        <li><h4><em>14</em></h4></li>
        </ul>
        </div>
        </div>
        <div class="row hidden-lg">
        <br/>
        <div class="col-sm-12">
        <table class="table">
    
    <tbody>
      <tr>
        <td>Name</td>
        <td>John Doe</td>
        
      </tr>
      <tr>
        <td>Email</td>
        <td>john.doe@gmail.com</td>
      </tr>
      <tr>
        <td>Contact</td>
        <td>123456789</td>
      </tr>
      <tr>
        <td>Subscriptions</td>
        <td>115</td>
      </tr>
      <tr>
        <td>Subscribed By</td>
        <td>15</td>
      </tr>
      <tr>
        <td>Address</td>
        <td>Unit No. 5, Unity Gold Complex, Deccan Gymkhana, Pune, Maharashtra, 411004</td>
      </tr>
    </tbody>
  </table>
        </div>
        </div>
        <div class="row hidden-sm hidden-md">
        <div class="col-sm-2">
        <ul style="list-style-type:none;">
        <li><h4>Address:</h4></li>
        </ul>
        </div>
        <div class="col-sm-10">
        <ul style="list-style-type:none;padding:0">
        <li><h4><em>Unit No. 5, Unity Gold Complex, Deccan Gymkhana, Pune, Maharashtra, 411004</em></h4></li>
        </ul>
        </div>
        </div>
        </div>
        </div>
        </div>
        </div>
        <div class="row">
        <div class="col-sm-12" style="padding-right:0;">
        <h5 class="pull-right" style="background: #fafafa;padding: 5px;border: 1px solid #000;  border-radius: 4px;"><strong>Status: </strong><span class="label bg-green">User Active</span><span class="label bg-red">User De-activated</span><br/><br/><span><a style="text-decoration:none;font-size:14px;cursor:pointer;" data-toggle="modal" data-target="#userstatus">Activate/De-activate Brand</a></span></h5>
        </div>
        </div>
        <!-- Brand Section (Subscribed / Created Brand) -->
        <div class="row panel">
        <div class="col-sm-12 panel-body">
        <h4>Brand Summary:</h3>
      
        <div class="nav-tabs-custom">
        <br/>
            <ul class="nav nav-tabs">
              <li class="active"><a href="#subscribed" data-toggle="tab">Subscribed Brands    <span class="label label-success" style="padding:2px 10px;">14</span></a></li>
              <li><a href="#created" data-toggle="tab">Created Brands    <span class="label label-warning" style="padding:2px 10px;">4</span></a></li>
              
             </ul>
            <div class="tab-content">
              <div class="tab-pane active" id="subscribed">
                <div class="row">
                <div class="col-sm-4 col-md-4 col-lg-2">
                <center><img src="dist/img/brands/wordpress.png" alt="wordpress"></center>
       			<h5 class="text-center"><strong>Wordpress</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/dropbox.png" alt="Dropbox"></center>
       			<h5 class="text-center"><strong>Dropbox</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/box.png" alt="Box.com"></center>
       			<h5 class="text-center"><strong>box.com</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/soundcloud.png" alt="Soundcloud"></center>
       			<h5 class="text-center"><strong>Soundcloud</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/spotify.png" alt="Spotify"></center>
       			<h5 class="text-center"><strong>Spotify</strong></h5></div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/yahoo.png" alt="Yahoo.com"></center>
       			<h5 class="text-center"><strong>Yahoo</strong></h5>
                </div>
                </div>
                <hr/>
                <div class="row">
                <div class="col-sm-4 col-md-4 col-lg-2">
                <center><img src="dist/img/brands/apple.png" alt="Apple"></center>
       			<h5 class="text-center"><strong>Apple</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/adobe-photoshop.png" alt="Adobe Photoshop"></center>
       			<h5 class="text-center"><strong>Adobe Photoshop</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/firefox.png" alt="mozilla Firefox"></center>
       			<h5 class="text-center"><strong>Mozilla Firefox</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/pinterest.png" alt="Pinterest"></center>
       			<h5 class="text-center"><strong>Pinterest</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/paypal.png" alt="PayPal"></center>
       			<h5 class="text-center"><strong>PayPal</strong></h5></div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/quora.png" alt="Quora"></center>
       			<h5 class="text-center"><strong>Quora</strong></h5>
                </div>
                </div>
              </div>
              <!-- /.tab-pane -->
              <div class="tab-pane" id="created">
              <div class="row">
                <div class="col-sm-4 col-md-4 col-lg-2">
                <center><img src="dist/img/brands/apple.png" alt="Apple"></center>
       			<h5 class="text-center"><strong>Apple</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/adobe-photoshop.png" alt="Adobe Photoshop"></center>
       			<h5 class="text-center"><strong>Adobe Photoshop</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/firefox.png" alt="Mozilla Firefox"></center>
       			<h5 class="text-center"><strong>Mozilla Firefox</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/pinterest.png" alt="Pinterest"></center>
       			<h5 class="text-center"><strong>Pinterest</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/paypal.png" alt="PayPal"></center>
       			<h5 class="text-center"><strong>PayPal</strong></h5></div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/quora.png" alt="Quora"></center>
       			<h5 class="text-center"><strong>Quora</strong></h5>
                </div>
                </div>
                <hr/>
                <div class="row">
                <div class="col-sm-4 col-md-4 col-lg-2">
                <center><img src="dist/img/brands/wordpress.png" alt="wordpress"></center>
       			<h5 class="text-center"><strong>Wordpress</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/dropbox.png" alt="Dropbox"></center>
       			<h5 class="text-center"><strong>Dropbox</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/box.png" alt="Box.com"></center>
       			<h5 class="text-center"><strong>box.com</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/soundcloud.png" alt="Soundcloud"></center>
       			<h5 class="text-center"><strong>Soundcloud</strong></h5>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/spotify.png" alt="Spotify"></center>
       			<h5 class="text-center"><strong>Spotify</strong></h5></div>
                <div class="col-sm-4 col-md-4 col-lg-2">
                 <center><img src="dist/img/brands/yahoo.png" alt="Yahoo.com"></center>
       			<h5 class="text-center"><strong>Yahoo</strong></h5>
                </div>
                </div>
            
                
              </div>
           
              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
        </div>
        </div>
        <!-- Brand Section (Subscribed / Created Brand) -->
        <!-- Post Summary (Facebook / Twitter / Instagram / Sift) -->
        <!-- <div class="row panel">
        <div class="col-sm-12 panel-body">
        <h4>Post Summary:</h3>
        <div class="nav-tabs-custom">
        <br/>
            <ul class="nav nav-tabs">
              <li class="active" style="width:20%"><a href="#tab_1" data-toggle="tab" class="btn btn-block btn-social btn-facebook">
                <span><img src="dist/img/fb.png" alt="Facebook Post"></span><strong>Facebook</strong></a></li>
                
              <li style="width:20%"><a href="#tab_2" data-toggle="tab"class="btn btn-block btn-social btn-twitter">
                <span><img src="dist/img/twitter.png" alt="Twitter Post"></span><strong>Twitter</strong></a></li>
                
              <li style="width:20%"><a href="#tab_3" data-toggle="tab"class="btn btn-block btn-social btn-instagram">
                <span><img src="dist/img/instagram.png" alt="Instagram post"></span><strong>Instagram</strong></a></li>
                
              <li style="width:20%"><a href="#tab_4" data-toggle="tab" class="btn btn-block btn-social btn-sift"><span><img src="dist/img/swift.png" alt="Sift post"></span><strong>Swift</strong></a></li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane active" id="tab_1">
                <b>How to use:</b>

                <p>Exactly like the original bootstrap tabs except you should use
                  the custom wrapper <code>.nav-tabs-custom</code> to achieve this style.</p>
                A wonderful serenity has taken possession of my entire soul,
                like these sweet mornings of spring which I enjoy with my whole heart.
                I am alone, and feel the charm of existence in this spot,
                which was created for the bliss of souls like mine. I am so happy,
                my dear friend, so absorbed in the exquisite sense of mere tranquil existence,
                that I neglect my talents. I should be incapable of drawing a single stroke
                at the present moment; and yet I feel that I never was a greater artist than now.
              </div>
              <!-- /.tab-pane 
              <div class="tab-pane" id="tab_2">
                The European languages are members of the same family. Their separate existence is a myth.
                For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ
                in their grammar, their pronunciation and their most common words. Everyone realizes why a
                new common language would be desirable: one could refuse to pay expensive translators. To
                achieve this, it would be necessary to have uniform grammar, pronunciation and more common
                words. If several languages coalesce, the grammar of the resulting language is more simple
                and regular than that of the individual languages.
              </div>
              <!-- /.tab-pane 
              <div class="tab-pane" id="tab_3">
                Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                It has survived not only five centuries, but also the leap into electronic typesetting,
                remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset
                sheets containing Lorem Ipsum passages, and more recently with desktop publishing software
                like Aldus PageMaker including versions of Lorem Ipsum.
              </div>
              <!-- /.tab-pane 
              <div class="tab-pane" id="tab_4">
                <b>How to use:</b>

                <p>Exactly like the original bootstrap tabs except you should use
                  the custom wrapper <code>.nav-tabs-custom</code> to achieve this style.</p>
                A wonderful serenity has taken possession of my entire soul,
                like these sweet mornings of spring which I enjoy with my whole heart.
                I am alone, and feel the charm of existence in this spot,
                which was created for the bliss of souls like mine. I am so happy,
                my dear friend, so absorbed in the exquisite sense of mere tranquil existence,
                that I neglect my talents. I should be incapable of drawing a single stroke
                at the present moment; and yet I feel that I never was a greater artist than now.
              </div>
              <!-- /.tab-pane 
            </div>
            <!-- /.tab-content 
          </div>
        </div>
        </div>-->
        <!-- Post Summary (Facebook / Twitter / Instagram / Sift) -->
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
