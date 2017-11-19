$(document).ready(function() {
  $('#fullpage').fullpage({
    anchors: ['one', 'two', 'three', 'four'],
    sectionsColor: ['tomato', 'indigo', 'blue', 'green'],
    menu: '#walkthrough'
  });
  // $('#fullPage').fullpage({
    // controlArrows: true,
    // verticalCentered: true,
    // anchors: ['one', 'two', 'three'],
    // sectionColor: ['indigo', 'tomato', 'apple']
    // scrollingSpeed: 700
  //   // fadingEffect: true,
  //   // menu items must use the data-attr data-menuanchor and the value must match the slide's anchor tag
  //   menu: '#walkthrough',
  //   navigation: true,
  //   slidesNavigation: true,
  //   slidesNavPosition: 'bottom',
  //
  //   afterRender: function(){
  //     var pluginContainer = $(this);
  //     console.log("Locked and loaded");
  //   },
  //
  //   afterLoad: function(anchorLink, index){
  //     var loadedSection = $(this);
  //
  //     //using index
  //     if(index == 3){
  //       alert("Section 3 ended loading");
  //     }
  //
  //     //using anchorLink
  //     if(anchorLink == 'two'){
  //       alert("Section 2 ended loading");
  //     }
  //   },
  //
  //   afterResponsive: function(isResponsive){
  //     alert("Is responsive: " + isResponsive);
  //   },
    //
    // onLeave: function(index, nextIndex, direction){
    //   var leavingSection = $(this);
    //
    //   //after leaving section 2
    //   if(index == 2 && direction =='right'){
    //     alert("Going to section 3!");
    //   }
    //
    //   else if(index == 2 && direction == 'left'){
    //     alert("Going to section 1!");
    //   }
    // },
    //
    // afterSlideLoad: function( anchorLink, index, slideAnchor, slideIndex){
    //   var loadedSlide = $(this);
    //
    //   //first slide of the second section
    //   if(anchorLink == 'secondPage' && slideIndex == 1){
    //     alert("First slide loaded");
    //   }
    //
    //   //second slide of the second section (supposing #secondSlide is the
    //   //anchor for the second slide
    //   if(index == 2 && slideIndex == 'secondSlide'){
    //     alert("Second slide loaded");
    //   }
    // },
    //
    // onSlideLeave: function( anchorLink, index, slideIndex, direction, nextSlideIndex){
    //   var leavingSlide = $(this);
    //
    //   //leaving the first slide of the 2nd Section to the right
    //   if(index == 2 && slideIndex == 0 && direction == 'right'){
    //     alert("Leaving the fist slide!!");
    //   }
    //
    //   //leaving the 3rd slide of the 2nd Section to the left
    //   if(index == 2 && slideIndex == 2 && direction == 'left'){
    //     alert("Going to slide 2! ");
    //   }
    // }


  // });


  // $.fn.fullpage.moveSlideRight();
  // $.fn.fullpage.moveSlideLeft();
});