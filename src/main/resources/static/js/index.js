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
    function openLookPicker(event) {
    event.preventDefault();
    $(".add-look-pic").attr("Hidden", "hidden");
    $()
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
            $("#look-input").val(response.filesUploaded[0].url);
            console.log(response.filesUploaded[0].url);
            $(".submit-look-pic").removeAttr("Hidden")
        });
    }
//click event for opening the profile setup picture picker
  $(".add-profile-pic").on("click", openPicker);
  //click event for opening the general look picture picker
  $(".add-look-pic").on("click", openLookPicker);
  //function for showing the edit title field in the look view
    function showEditTitle(event){
        event.preventDefault();
        $(".edit-title").removeAttr("hidden");
        $(".show-edit-title").attr("hidden", "hidden");
        $(".made-edits").removeAttr("hidden");
        $(".cancel-edit-title").removeAttr("hidden")
    }
  //function for showing the edit description field in look view
    function showEditDescription(event){
        event.preventDefault();
        $(".edit-description").removeAttr("hidden");
        $(".show-edit-description").attr("hidden", "hidden");
        $(".made-edits").removeAttr("hidden");
        $(".cancel-edit-description").removeAttr("hidden");
    }
  //click event for showing the edit title field
    $(".show-edit-title").on("click",showEditTitle);
  //click event for showing the edit description field
    $(".show-edit-description").on("click",showEditDescription);

    $(".cancel-edit-title").on("click",function(event){
      event.preventDefault();
        $(".edit-title").attr("hidden", "hidden");
        $(".show-edit-title").removeAttr("hidden");
        $(".made-edits").attr("hidden", "hidden");
        $(".cancel-edit-title").attr("hidden","hidden")
    });

    $(".cancel-edit-description").on("click",function(event){
        event.preventDefault();
        $(".edit-description").attr("hidden", "hidden");
        $(".show-edit-description").removeAttr("hidden");
        $(".made-edits").attr("hidden", "hidden");
        $(".cancel-edit-description").attr("hidden","hidden")
    });

  // User can search for products
  var results = document.getElementById("results");
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


    var request = $.ajax({'url': '/js/makeup.json'});
    request.done(function (products){

      const search = () => {
        var key = $("#filter").val();
        var value = $.trim($("#query").val());

        displayEmpty();


        results.innerHTML = ! value ? '' : products
          .filter(product => {
            if(key == "brand" || key == "type") {

              return product[key]['name'].toLowerCase().indexOf(value.toLowerCase()) !== -1;

            } else {

              return product["name"].toLowerCase().indexOf(value.toLowerCase()) !== -1;

            }
          })
      .map(({id, name, brand, type, thumbnailUrl}) =>
        `
          <ul class="collection">
              <a href="/product/${id}"><li class="collection-item avatar">
                <img src="${thumbnailUrl}" alt="" class="circle">
                <span class="title">${name}</span>
                <p>${brand.name} <br>
                   ${type.name}
                </p>
                <a href="/product/${id}" class="secondary-content"><i class="material-icons">send</i></a>
              </li></a>
           </ul>
        `)
      .reduce((html, template) => html + template, '');
      };


      $("#query").on("input", debounce(search, 300));
      $("#filter").on("change", search);

    })
  })(jQuery);

  // Displays a no search results message when results div is empty
  function displayEmpty() {
    if(results.childNodes.length === 1) {
      console.log(results.childNodes.length);
      var noResults = `
            <div class="no-results">
            <h4>No results</h4>
            <h6>Try searching for a product</h6>
            </div>`;

    }
    return noResults;

  }

  results.innerHTML = displayEmpty();




  $('select').material_select();


});


// <p><a href="/product/${id}">${name}, ${brand.name} (${type.name})</a></p>