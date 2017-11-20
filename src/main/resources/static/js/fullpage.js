$(document).ready(function() {
  $('#fullpage').fullpage({
    anchors: ['one', 'two', 'three', 'four'],
    // sectionsColor: ['tomato', 'indigo', 'blue', 'green'],
    menu: '#walkthrough',
    slidesNavPosition: 'bottom',
    afterRender: function(){
      var pluginContainer = $(this);
      var loader = $('.loading-wrapper');
      var wrapper = $('.page-wrapper');
      loader.fadeOut().remove();
      wrapper.fadeIn();
    }
  });
  $(document).on('click', '.mp-down-arrow', function(){
    $.fn.fullpage.moveSectionDown();
  });

  $("#preferences").imagepicker({
    hide_select: true,
    show_label: true
  });

});