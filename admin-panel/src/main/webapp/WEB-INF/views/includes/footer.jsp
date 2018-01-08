<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Modal -->
<div id="userstatus" class="modal fade" role="dialog">
  <div class="modal-dialog" style="transform:translate(0,100%);">

    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><h1 style="margin:0;">&times;</h1></button>
        <h3 class="modal-title text-center">Activate or De-activate a user</h3>
      </div>
      <div class="modal-body">
      
      <div class="row">
      
      <div class="col-sm-6">
      <center><button title="User is Activated" class="btn btn-success active"><span><i style="font-size:20px" class="fa fa-unlock-alt" aria-hidden="true"></i></span><br/><strong>Activate User</strong></button></center>
      </div>
      <div class="col-sm-6">
      <center><button title="User is De-activated" class="btn btn-danger"><span><i style="font-size:20px;" class="fa fa-lock" aria-hidden="true"></i></span><br/><strong>De-activate User</strong></button></center>
      </div>
      </div>
      </div>
      <!-- <div class="modal-footer">
        <button type="button"  class="btn btn-default" data-dismiss="modal">Close</button>
      </div> -->
    </div>

  </div>
</div>

<!-- Activate or De-activate brand modal -->
<div id="brandstatus" class="modal fade" role="dialog">
  <div class="modal-dialog" style="transform:translate(0,100%);">

    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><h1 style="margin:0;">&times;</h1></button>
        <h3 class="modal-title text-center">Activate or De-activate a brand</h3>
      </div>
      <div class="modal-body">
      
      <div class="row">
      
      <div class="col-sm-6">
      <center><button title="User is Activated" class="btn btn-success active"><span><i style="font-size:20px" class="fa fa-unlock-alt" aria-hidden="true"></i></span><br/><strong>Activate Brand</strong></button></center>
      </div>
      <div class="col-sm-6">
      <center><button title="User is De-activated" class="btn btn-danger"><span><i style="font-size:20px;" class="fa fa-lock" aria-hidden="true"></i></span><br/><strong>De-activate Brand</strong></button></center>
      </div>
      </div>
      </div>
      <!-- <div class="modal-footer">
        <button type="button"  class="btn btn-default" data-dismiss="modal">Close</button>
      </div> -->
    </div>

  </div>
</div>

<footer class="main-footer">
  <!-- To the right -->
  <div class="pull-right hidden-xs">
    
  </div>
  <!-- Default to the left -->
  <strong>Copyright &copy; <%= new SimpleDateFormat("yyyy").format(new Date()) %><a href="#"> Base Module</a>.</strong> 
  	All rights reserved.
</footer>
