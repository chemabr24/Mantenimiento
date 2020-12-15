var self;
function viewModel() {
	self = this;
	self.listaReunionesL = ko.observableArray([]);
	self.listaReunionesM = ko.observableArray([]);
	self.listaReunionesX = ko.observableArray([]);
	self.listaReunionesJ = ko.observableArray([]);
	self.listaReunionesV = ko.observableArray([]);
	self.listaReunionesS = ko.observableArray([]);
	self.listaReunionesD = ko.observableArray([]);

	if ("localhost:8080" == window.location.host) {
		var url = 'ws://' + window.location.host + '/SIGETEquipo1';
	} else {
		var url = 'wss://' + window.location.host + '/SIGETEquipo1';
	}
	self.sws = new WebSocket(url);

	self.sws.onmessage = function (event) {
		let data = event.data;
		data = JSON.parse(data);
		self.reuniones = [];
			self.reuniones = [];
			self.listaReunionesL([]);
			self.listaReunionesM([]);
			self.listaReunionesX([]);
			self.listaReunionesJ([]);
			self.listaReunionesV([]);
			self.listaReunionesS([]);
			self.listaReunionesD([]);
			self.reuniones = data.actividades;
			for (let i = 0; i < self.reuniones.length; i++) {
				const reunion = self.reuniones[i];
				const horaIn = reunion.HoraI.split(':');
				const horaFi = reunion.HoraF.split(':');
				let posTop = 0;
				let length = 0;
				const px = 50.18;
				const nmediaHora = 2;
				const mediaHora = 0.5;
				// Si los minutajes son distintos
				if (horaIn[1] !== horaFi[1]) {
					if (horaIn[1] < horaFi[1]) {
						length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10) + mediaHora) * nmediaHora * px;
					} else {
						length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10) - mediaHora) * nmediaHora * px;
					}
				} else {
					length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10)) * nmediaHora * px;
				}
				if ('30' === horaIn[1]) {
					posTop = (parseInt(horaIn[0], 10) + mediaHora) * nmediaHora * px;
				} else {
					posTop = (parseInt(horaIn[0], 10)) * nmediaHora * px;
				}
				aniadirReunion(posTop, length, reunion, horaIn, horaFi);
			}
		
	};

	function aniadirReunion(posTop, length, reunion, horaIn, horaFi) {
		switch (reunion.dia) {
			case 'LUNES':
				if (self.listaReunionesL().some(r => r.name === reunion.name) === false) {
					self.listaReunionesL.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1]));
				}
				estilizarLI(posTop, length, reunion);
				break;
			case 'MARTES':
				if (self.listaReunionesM().some(r => r.name === reunion.name) === false) {
					self.listaReunionesM.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1]));
				}
				estilizarLI(posTop, length, reunion);
				break;
			case 'MIERCOLES':
				if (self.listaReunionesX().some(r => r.name === reunion.name) === false) {
					self.listaReunionesX.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1]));
				}
				estilizarLI(posTop, length, reunion);
				break;
			case 'JUEVES':
				if (self.listaReunionesJ().some(r => r.name === reunion.name) === false) {
					self.listaReunionesJ.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1]));
				}
				estilizarLI(posTop, length, reunion);
				break;
			case 'VIERNES':
				if (self.listaReunionesV().some(r => r.name === reunion.name) === false) {
					self.listaReunionesV.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1]));
				}
				estilizarLI(posTop, length, reunion);
				break;
			case 'SABADO':
				if (self.listaReunionesS().some(r => r.name === reunion.name) === false) {
					self.listaReunionesS.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1]));
				}
				estilizarLI(posTop, length, reunion);
				break;
			case 'DOMINGO':
				if (self.listaReunionesD().some(r => r.name === reunion.name) === false) {
					self.listaReunionesD.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1]));
				}
				estilizarLI(posTop, length, reunion);
				break;
			default:
				break;
		}
	}

	function estilizarLI(posTop, length, reunion) {

		const ulL = document.getElementById(reunion.dia.toLowerCase());
		const itemsL = ulL.getElementsByTagName('li');
		for (let n = 0; n < itemsL.length; n++) {
			if (itemsL[n].innerText === reunion.name) {
				itemsL[n].style.top = posTop.toString() + 'px';
				itemsL[n].style.height = length.toString() + 'px';
				if (reunion.Reunion) {
					itemsL[n].style.background = '#3d5ce7c5';
				} else {
					itemsL[n].style.background = '#9eec8ac5';
				}
			}
		}
	}

	self.buscarPorSemana = function () {
		const info = {
			type: 'buscarPorSemana',
			nombre: sessionStorage.userName,
			semana: $('#selectSemana').val(),
			vista: "calendar"
		};
		self.sws.send(JSON.stringify(info));
	}

	class Reunion {
		constructor(name, dia, horaI, minutosI, horaF, minutosF) {
			this.name = name;
			this.dia = dia;
			this.horaI = horaI;
			this.minutosI = minutosI;
			this.horaF = horaF;
			this.minutosF = minutosF;
			
		}
	}
}

const vm = new viewModel();
ko.applyBindings(vm);