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


  // User can search for products

  (function($) {
    var request = $.ajax({'url': '/makeup.json'});
    request.done(function (products){

      const search = () => {
        var key = $("#filter").val().toLowerCase();
        var value = $.trim($("#query").val().toLowerCase());
        var results = document.getElementById("results");
        // console.log(products);
        results.innerHTML = ! value ? '' : products
          .filter(product => product[key].toLowerCase().indexOf(value.toLowerCase()) !== -1)
      .map(({name, brand, type}) => `<div>${name}, ${brand.name} (${type.name})</div>`)
      .reduce((html, template) => html + template, '')
      };

      // Search functions
      //   if($("#results").html() === "" || $("#results").html() !== !value) {
      //     var search = function() {
      //       var key = $("#filter").val().toLowerCase();
      //       var value = $.trim($("#query").val().toLowerCase());
      //       // console.log(value);
      //
      //       products.filter(function (product) {
      //         return product[key].indexOf(value) !== -1;
      //       })
      //         .map(function (product) {
      //           // console.log(product);
      //           var html = " <div>" +
      //             product.name + " " + product.brand.name +
      //             "</div>";
      //           return html;
      //         })
      //         .reduce(function (html, template) {
      //           console.log(html);
      //           return html + template;
      //         }, " ");
      //     };
        // }

      $("#query").on("input", search);
      $("#filter").on("change", search);

    })
  })(jQuery);



  $('select').material_select();


});