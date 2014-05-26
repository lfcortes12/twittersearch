L.Control.SelectMarkers = L.Control.extend({
	options: {
		position: 'topleft',
		title: 'Puntos Seleccionados',
		keys: null,
		emptyMessage: 'No hay puntos seleccionados',
		onApply: function() {
			console.log("por aca");
		},
		buttonText: '<i class="glyphicon glyphicon-filter"></i> Filtrar',
	},
	
	initialize: function (data, options) {
		this._data = data;
		L.setOptions(this, options);
		this._initLayout();
		this.updateData(this._data);
	},
	
	onAdd: function (map) {
		this._initLayout();
		this.updateData(this._data);
		return this._container;
	},
	
	_initLayout: function() {
		var className = this._className = 'leaflet-select-markers',
	    container = this._container = L.DomUtil.create('div', className);
		
		//Makes this work on IE10 Touch devices by stopping it from firing a mouseout event when the touch is released
		container.setAttribute('aria-haspopup', true);
		
		if (!L.Browser.touch) {
			L.DomEvent.disableClickPropagation(container);
			L.DomEvent.on(container, 'mousewheel', L.DomEvent.stopPropagation);
		} else {
			L.DomEvent.on(container, 'click', L.DomEvent.stopPropagation);
		}
	},
	
	updateData: function(data, keys) {
		this.options.keys = keys || this.options.keys;
		this._data = data;
		this._container.innerHTML = '';
		
		if(this.options.title) {
			var title = L.DomUtil.create('h3', this._className + '-title');
			title.innerHTML = this.options.title;
			this._container.appendChild(title);
		}
		
		var table = L.DomUtil.create('table', 'table ' + this._className + '-table', this._container);
		if(data && data.length) {
			for(var i=0; i<data.length; i++) {
				var tr = L.DomUtil.create('tr', this._className + '-table-tr', table);
				if(!this.options.keys) {
					for(var field in data[i]) {
						var td = L.DomUtil.create('td', this._className + '-table-td', tr);
						td.innerHTML = data[i][field];
					}					
				} else {
					for(var field in this.options.keys) {
						var td = L.DomUtil.create('td', this._className + '-table-td', tr);
						td.innerHTML = data[i][this.options.keys[field]];
					}
				}
			}
			var div = L.DomUtil.create('div', this._className + '-buttons', this._container);
			var button = L.DomUtil.create('buttom', 'btn btn-xs btn-primary pull-right ' + this._className + '-buttons', this._container);
			button.innerHTML = this.options.buttonText;
			var $this = this;
			L.DomEvent.on(button, 'click', function(e) {
				$this.options.onApply.apply($this, [data]);
			});
		} else {
			var tr = L.DomUtil.create('tr', this._className + '-table-tr', table);
			var td = L.DomUtil.create('td', this._className + '-table-td', tr);
			td.innerHTML = this.options.emptyMessage;
		}
	},
});

L.control.selectmarkers = function (data, options) {
	return new L.Control.SelectMarkers(data, options);
};