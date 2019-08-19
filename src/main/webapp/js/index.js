// Sub-menu
var submenus = document.querySelectorAll("ul.sub-menu");
if(submenus.length > 0) for(var i=0; i<submenus.length; i++) {
    var span = document.createElement('span');
    span.classList.add('expand');
    span.innerHTML = "+";

    span.addEventListener('click', function(){
        this.classList.toggle('active');
        // this.nextElementSibling.classList.toggle('active');
		  this.parentNode.classList.toggle('active');
    });
	
    submenus[i].previousElementSibling.appendChild(span);
    submenus[i].parentNode.insertBefore(span, submenus[i]);
}


/*
	<a class="anyclass" href="#" data-toggle-class="active, someotherclass" data-toggle-target=".menu, self">Menu</a>

	data-toggle-class - classes to apply to targets
	data-toggle-target - target's selectors to apply classes to

	If there is no 'data-toggle-target' attribute (only 'data-toggle-class'), classes are applyed to trigger element. 
	If classes are needed to be appled to targets including trigger element itself, use keywords 'this' or 'self'.
*/
(function() {

	function toggleClasses(classes, obj)
	{
		if(!classes) return;
		if(!obj) return;
		for(var n=0; n<classes.length; n++) obj.classList.toggle(classes[n].trim());
	}

	function applyToTargets(targetslist, dototargets, obj, dotoself, dotonext)
	{
		if(!targetslist) return;
		if(!dototargets) return;

		targetslist = targetslist.split(',');

		for(var n=0; n<targetslist.length; n++)
		{
			targetslist[n] = targetslist[n].trim();
			
			if( (targetslist[n] == 'this' || targetslist[n] == 'self') && obj && dotoself ) dotoself(obj);
			if(targetslist[n] == 'next' && obj && dotonext ) dotonext(obj.nextElementSibling);
			else 
			{
				var elems = document.querySelectorAll( targetslist[n] );
				if(elems.length > 0) 
				{
					for(var m=0; m<elems.length; m++) 
					{
						dototargets(elems[m]); 
					}
				}
			}
		}
	}

	var clickToggle = document.querySelectorAll('[data-toggle-class]');

	if(clickToggle.length > 0) 
	{
		for(var i=0; i<clickToggle.length; i++) 
		{
			clickToggle[i].addEventListener('click', function(e) {
				e.preventDefault();

				var classes = this.getAttribute('data-toggle-class');
				if(!classes) return;

				classes = classes.split(',');
				for(var n=0; n<classes.length; n++) classes[n] = classes[n].trim();

				var targets = this.getAttribute('data-toggle-target');

				if(!targets) toggleClasses(classes, this); //for(var n=0; n<classes.length; n++)  this.classList.toggle(classes[n]);
				else 
				{
					applyToTargets(targets, function(elem){ toggleClasses(classes, elem); }, this, function(elem){ toggleClasses(classes, elem); }, function(elem){ toggleClasses(classes, elem); });
				}
			});
		}
	}
})();