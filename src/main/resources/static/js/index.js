$(document).ready(function() {
  // Navbar collapse on med screens and smaller
  $(".button-collapse").sideNav();
  console.log("hello");

  var fsClient = filestack.init('ArukrJULXTZWDKklrkBmaz');
  function openPicker() {
    fsClient.pick({
      fromSources:["local_file_system","imagesearch","facebook","instagram","webcam"],
      accept:["image/*"],
      maxFiles:1,
      transformations:{
        crop:{      force:true}}
    }).then(function(response) {
      // declare this function to handle response
      function handleFilestack(response) {
        console.log(response);
      }
      handleFilestack(response);
      $("#profile-input").val(response.filesUploaded[0].url);
      console.log(response.filesUploaded[0].url);
    });
  }

  $(".add-profile-pic").on("click", openPicker);



});