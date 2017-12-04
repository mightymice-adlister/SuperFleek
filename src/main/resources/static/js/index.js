$(document).ready(function() {
  // Navbar collapse on med screens and smaller
  $(".button-collapse").sideNav();

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

      // Sends the image url to the profile input
      $("#profile-input").val(response.filesUploaded[0].url);

      // Changes the file picker box to the uploaded image
      $(".add-profile-pic").css({"padding": 0});
      $("#profile-pic-box")
        .removeClass("hidden")
        .attr("src", response.filesUploaded[0].url);

    });
  }
    function openLookPicker(event) {
    event.preventDefault();
    $(".add-look-pic").attr("Hidden", "hidden");
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
    var editTitleIsOpen= false;
    var editDescriptionIsOpen = false;
    var makeupSelectorIsShowing=false;

    $('.master-edit').on("click", function(){
        event.preventDefault();
        $('.master-edit').attr("hidden", "hidden");
        $('.made-edits').removeAttr("hidden");
        $('.hide-makeup-select').removeAttr("hidden");
    });

    function showEditTitle(event){
        event.preventDefault();
        editTitleIsOpen = true;
        $(".edit-title").removeAttr("hidden");
        $(".show-edit-title").attr("hidden", "hidden");
        $(".made-edits").removeAttr("hidden");
        $(".cancel-edit-title").removeAttr("hidden")
    }
  //function for showing the edit description field in look view
    function showEditDescription(event){
        event.preventDefault();
        editDescriptionIsOpen = true;
        $(".edit-description").removeAttr("hidden");
        $(".show-edit-description").attr("hidden", "hidden");
        $(".made-edits").removeAttr("hidden");
        $(".cancel-edit-description").removeAttr("hidden");
    }

    $("a.show-makeup-select").on("click", function(event){
        event.preventDefault();
        makeupSelectorIsShowing = true;
        $(".master-edit").attr("hidden", "hidden");
        $(".makeup-select-div").removeAttr("hidden");
        $(".show-makeup-select").attr("hidden", "hidden");
        $(".made-edits").removeAttr("hidden");
        $(".hide-makeup-select").removeAttr("hidden");
        $('select').material_select();
    });


  //click event for showing the edit title field
    $("a.show-edit-title").on("click",showEditTitle);
  //click event for showing the edit description field
    $("a.show-edit-description").on("click",showEditDescription);

    $("a.cancel-edit-title").on("click",function(event){
      event.preventDefault();
      editTitleIsOpen = false;
        $(".edit-title").attr("hidden", "hidden");
        $(".show-edit-title").removeAttr("hidden");
        if((!editDescriptionIsOpen)&&(!makeupSelectorIsShowing)) {
            $(".made-edits").attr("hidden", "hidden");
        }
        $(".cancel-edit-title").attr("hidden","hidden")
    });



    $("a.cancel-edit-description").on("click",function(event){
        event.preventDefault();
        editDescriptionIsOpen = false;
        $(".edit-description").attr("hidden", "hidden");
        $(".show-edit-description").removeAttr("hidden");
        if((!editTitleIsOpen) && (!makeupSelectorIsShowing)) {
            $(".made-edits").attr("hidden", "hidden");
        }
        $(".cancel-edit-description").attr("hidden","hidden")
    });


    $("a.hide-makeup-select").on("click",function(event){
        event.preventDefault();
        makeupSelectorIsShowing = false;
        $(".makeup-select-div").attr("hidden", "hidden");
        $(".show-makeup-select").removeAttr("hidden");
        if((!editTitleIsOpen)&&(!editDescriptionIsOpen)) {
            $(".made-edits").attr("hidden", "hidden");
        }
        $(".hide-makeup-select").attr("hidden","hidden");
        $(".master-edit").removeAttr("hidden");
    });
//this creates empty labesl after checkboxes so that they render in materialize
    $('input[type=checkbox]').each(function() {
            if(this.nextSibling.nodeName != 'label') {
                $(this).after('<label for="'+this.id+'"></label>')
            }
        }
    );

    $('select.makeup-select').each(function() {
            if(this.nextSibling.nodeName != 'label') {
                $(this).after('<label>Select Makeup From Your Collection</label>')
            }
        }
    );

  $('.title-field').blur(function(){
    var content = $('.title-field').text();
    $('.edit-title').val($.trim((content)));
  });

  $('.description-field').blur(function(){
    var content = $('.description-field').text();
    $('.edit-description').val($.trim((content)));
  });

  // Make profile pic
  $(".make-profile-link").click(function(event){
    $(".make-profile-pic-input").submit();
  });








    // Add bio text to bio input
  var bio = $("#textarea1");
  bio.on("blur", function(){
    $("#bio-input").val(bio.val());
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
      // Runs the search function on page load for the use case of searching from a different view
      $("window").on("load", search());
      // Debounce is needed to limit the number of searches as the user types
      $("#query").on("input", debounce(search, 300));
      // Filter functionality is present but not implemented
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










});


// <p><a href="/product/${id}">${name}, ${brand.name} (${type.name})</a></p>