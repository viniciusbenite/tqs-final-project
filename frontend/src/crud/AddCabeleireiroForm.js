import React, { useState } from 'react'
import api from "../services/api";
import FormGroup from "@material-ui/core/FormGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";

const AddCabeleireiroForm = props => {
	const initialFormState = { id: null, name: '', postalCode: '', city: '', country: '', status: 'Aberto', type: 'Cabeleireiro', contact: '', description: '', address: ''}
	const [ cabeleireiro, setCabeleireiro ] = useState(initialFormState)

	const [services, setServices] = React.useState({
		cortes: false,
		manicurepedicure: false,
		escovas: false,
		tratamentocapilar: false,
		serviços_especiais: false,
		depilacao: false,
		maquilhagem: false,
		quimicas: false,
	});
	const [hours, setHours] = React.useState({
		cortes: [],
		manicure_pedicure: [],
		escovas: [],
		tratamento_capilar: [],
		serviços_especiais: [],
		depilacao: [],
		maquilhagem: [],
		quimicas: [],
	});

	const handleToggle = ({ target }) =>
		setServices(s => ({ ...s, [target.name]: !s[target.name] }));

	const handleHourChange = event => {
		const { name, value } = event.target
		setHours({ ...hours, [name]: value.split(",")  });
	}

	const handleInputChange = event => {
		const { name, value } = event.target;
		console.log(
			value
		)
		setCabeleireiro({ ...cabeleireiro, [name]: value })
	}

	return (
		<form
			onSubmit={event => {
				event.preventDefault()
				if (!cabeleireiro.name || !cabeleireiro.city) return
				
				api.get("/user/").then(function (response) {
					let data = response.data;
					let res;
					for(const i in data){
						for(const n in data[i]){
							if(data[i].email === localStorage.getItem("user_email")){
								res = data[i];
							}
						}
					}
                    api.post("/saloon/",{name: cabeleireiro.name,postalCode: cabeleireiro.postalCode, city: cabeleireiro.city ,country: cabeleireiro.country, status: cabeleireiro.status, type: cabeleireiro.type, contact: cabeleireiro.contact, description: cabeleireiro.description
							,address: cabeleireiro.address, owner: res} ).then(resSal =>{

							for(const i in services){
								if (services[i]){
									api.post("/service/",{name: i, saloon: resSal.data} ).then(resSer =>{
										for(const n in hours[i]){
											api.post("/schedule/",{startTime: hours[i][n], service: resSer.data} ).then(resSche => {
											})
										}
									});
								}
							}
						});
				})
				setCabeleireiro(initialFormState)
				alert("Salão Adicionado!")
			}}
		>
			<label>Nome</label>
			<input type="text" name="name" value={cabeleireiro.name} onChange={handleInputChange} />
			<label>Código Postal</label>
			<input type="text" name="postalCode" value={cabeleireiro.postalCode} onChange={handleInputChange} />
			<label>Cidade</label>
			<input type="text" name="city" value={cabeleireiro.city} onChange={handleInputChange} />
			<label>País</label>
			<input type="text" name="country" value={cabeleireiro.country} onChange={handleInputChange} />
			<label>Estado</label>
			<select name={"status"} onChange={handleInputChange}>
				<option name="Aberto" value="Aberto">Aberto</option>
				<option name="Fechado" value="Fechado">Fechado</option>
			</select>
			<label>Tipo</label>
			<select  name={"type"} onChange={handleInputChange}>
				<option name="Cabeleireiro" value="Cabeleireiro">Cabeleireiro</option>
				<option name="Barbeiro" value="Barbeiro">Barbeiro</option>
			</select>
			<label>Contacto</label>
			<input type="text" name="contact" value={cabeleireiro.contact} onChange={handleInputChange} />
			<label>Descrição</label>
			<input type="text" name="description" value={cabeleireiro.description} onChange={handleInputChange} />
			<label>Endereço</label>
			<input type="text" name="address" value={cabeleireiro.address} onChange={handleInputChange} />
			<label>Serviços disponíveis</label>
			<FormGroup>
				<FormControlLabel
					control={<Checkbox checked={services.cortes} onChange={handleToggle} name="cortes" />}
					label="Cortes"
				/>
				<input type="text" name="cortes" value={hours.cortes} onChange={handleHourChange}  placeholder="Introduza horários disponíveis ( p.e. 09:00:00,12:30:00)"/>
				<FormControlLabel
					control={<Checkbox checked={services.manicure_pedicure} onChange={handleToggle} name="manicure_pedicure" />}
					label="Manicure Pedicure"
				/>
				<input type="text" name="manicure_pedicure" value={hours.manicure_pedicure} onChange={handleHourChange} placeholder="Introduza horários disponíveis ( p.e. 09:00:00,12:30:00)" />
				<FormControlLabel
					control={<Checkbox checked={services.escovas} onChange={handleToggle} name="escovas" />}
					label="Escovas"
				/>
				<input type="text" name="escovas" value={hours.escovas} onChange={handleHourChange} placeholder="Introduza horários disponíveis ( p.e. 09:00:00,12:30:00)" />
				<FormControlLabel
					control={<Checkbox checked={services.tratamento_capilar} onChange={handleToggle} name="tratamento_capilar" />}
					label="Tratamento Capilar"
				/>
				<input type="text" name="tratamento_capilar" value={hours.tratamento_capilar} onChange={handleHourChange} placeholder="Introduza horários disponíveis ( p.e. 09:00:00,12:30:00)" />
				<FormControlLabel
					control={<Checkbox checked={services.serviços_especiais} onChange={handleToggle} name="serviços_especiais" />}
					label="Serviços Especiais"
				/>
				<input type="text" name="serviços_especiais" value={hours.serviços_especiais} onChange={handleHourChange} placeholder="Introduza horários disponíveis ( p.e. 09:00:00,12:30:00)" />
				<FormControlLabel
					control={<Checkbox checked={services.depilacao} onChange={handleToggle} name="depilacao" />}
					label="Depilação"
				/>
				<input type="text" name="depilacao" value={hours.depilacao} onChange={handleHourChange} placeholder="Introduza horários disponíveis ( p.e. 09:00:00,12:30:00))" />
				<FormControlLabel
					control={<Checkbox checked={services.maquilhagem} onChange={handleToggle} name="maquilhagem" />}
					label="Maquilhagem"
				/>
				<input type="text" name="maquilhagem" value={hours.maquilhagem} onChange={handleHourChange} placeholder="Introduza horários disponíveis ( p.e. 09:00:00,12:30:00)" />
				<FormControlLabel
					control={<Checkbox checked={services.quimicas} onChange={handleToggle} name="quimicas" />}
					label="Quimicas"
				/>
				<input type="text" name="quimicas" value={hours.quimicas} onChange={handleHourChange} placeholder="Introduza horários disponíveis ( p.e. 09:00:00,12:30:00)" />
			</FormGroup>
			<button>Adicionar novo salão</button>
		</form>
	)
}

export default AddCabeleireiroForm;