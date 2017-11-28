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

    // debounce is used to delay search queries
    debounce = function(func, delay) {
      var inDebounce;
      inDebounce = undefined;
      return function() {
        var args, context;
        clearTimeout(inDebounce);
        context = this;
        args = arguments;
        return inDebounce = setTimeout(function() {
          return func.apply(context, arguments);
        }, delay);
      };
    };


    var request = $.ajax({'url': '/makeup.json'});
    request.done(function (products){

      const search = () => {
        var key = $("#filter").val().toLowerCase();
        var value = $.trim($("#query").val().toLowerCase());
        var results = document.getElementById("results");
        // console.log(products);
        results.innerHTML = ! value ? '' : products
          .filter(product => {

            if(key == "brand" || key == "type") {

              return product[key]['name'].toLowerCase().indexOf(value.toLowerCase()) !== -1;

            } else {

              return product[key].toLowerCase().indexOf(value.toLowerCase()) !== -1;

            }
          })
      .map(({id, name, brand, type, thumbnailUrl}) =>
        `
          <ul class="collection">
              <li class="collection-item avatar">
                <img src="${thumbnailUrl}" alt="" class="circle">
                <span class="title">${name}</span>
                <p>${brand.name} <br>
                   ${type.name}
                </p>
                <a href="/product/${id}" class="secondary-content"><i class="material-icons">grade</i></a>
              </li>
           </ul>
        `)
      .reduce((html, template) => html + template, '')
      };

      $("#query").on("input", debounce(search, 300));
      $("#filter").on("change", search);

    })
  })(jQuery);



  $('select').material_select();


});


// <p><a href="/product/${id}">${name}, ${brand.name} (${type.name})</a></p>