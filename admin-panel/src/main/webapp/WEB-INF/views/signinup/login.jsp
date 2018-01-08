<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	
	<title>Base Module | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition login-page">
  <div class="row" style="position: absolute;width: 100%;top: 2%;">
  <div class="col-sm-3"></div>
  <div class="col-sm-6">
  <div class="row">
 <div class="alert alert-success fade in alert-dismissable" style="margin-top:18px;">
    <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
    <strong>Success!</strong> This alert box indicates a successful or positive action.
</div>
  </div>
  <div class="row">
  <div class="alert alert-danger fade in alert-dismissable">
    <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
    <strong>Danger!</strong> This alert box indicates a dangerous or potentially negative action.
</div>
  </div>
  </div>
  <div class="col-sm-3"></div>
  </div>
    <div class="login-box">
      <div class="login-logo">
        <a href="#"><b>Base</b>Module</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>
        <form action="login" method="post">
          <div class="form-group has-feedback">
            <input type="email" class="form-control" placeholder="Email" name="username">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password" name="password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-12">
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              	<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div><!-- /.col -->
          </div>
        </form>

        <a data-toggle="modal" data-target="#fogpass" class="pull-right" style="padding:5px;cursor:pointer;">I forgot my password</a><br>
        <!-- <a href="register.html" class="text-center">Register a new membership</a> -->

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
	
	<!-- Forgot Password Modal -->
	<div id="fogpass" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-sm">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Forgot Password?</h4>
	      </div>
	      <div class="modal-body">
	        <div class="row">
	        <h4 class="text-center">Enter your email address below. You'll receive instructions to reset your password on your registered email address.</h4>
	        <form>
	        <div class="form-group " style="padding:0 20px;">
            <input type="email" class="form-control" placeholder="Email" name="username">
            </div>
           <center><button id="fog-pass-submit" type="button"  class="btn btn-default">Submit</button></center>
            </form>
             
	        </div>
	      </div>
	      <!-- <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div> -->
	    </div>
	
	  </div>
	</div>
	
	<!-- Forgot password Email Submit thankyou message Modal -->
	<div id="fogpass-thankyou" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-sm">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title text-center">Thank you</h4>
	      </div>
	      <div class="modal-body">
	        <div class="row">
	        <h4 class="text-center">
	        Thank you. Your email has been sent.
	        </h4>
	        </div>
	      </div>
	      <!-- <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div> -->
	    </div>
	
	  </div>
	</div>
	
	
    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="plugins/iCheck/icheck.min.js"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
        
        
      });
    </script>
    <script>
    $(function (){
		
    	$("#fog-pass-submit").click(function(){
            $("#fogpass").modal('hide');
            $("#fogpass-thankyou").modal('show');
        });
    })
    
    </script>
  </body>
</html>
