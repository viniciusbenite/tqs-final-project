import React, { Fragment, Component} from 'react'
import AddCabeleireiroForm from '../crud/AddCabeleireiroForm';
import EditCabeleireiroForm from '../crud/EditCabeleireiroForm';
import CabeleireiroTable from '../crud/CabeleireiroTable';
import NavbarDono from "../components/NavbarDono";
import '../crud/style.css';
import api from "../services/api";
import NavbarCliente from "../components/NavbarCliente";


export default class Gestao extends Component{
	state={
		cabeleireiros: [],
		currentCabeleireiro:{},
		editing:false,
	};
	componentDidMount() {
		this.GetSaloons();
	}

	GetSaloons (){
		api.get("/saloon/").then(res => {
			let dataRes = res.data;
			for (const n in dataRes) {
				if(dataRes[n].owner.email == localStorage.getItem("user_email")){
					this.setState(prevState =>({
						cabeleireiros: [...prevState.cabeleireiros, dataRes[n]]
					}))
				}
			}
		});
	}
	render() {
		const initialFormState = {id: null, name: "",postalCode: "",city: "",country: "",status: "",type: "",contact: "",description: "",address: ""};

		// Setting state
		/*const [cabeleireiros, setCabeleireiros] = useState(cabeleireirosData);
		const [currentCabeleireiro, setCurrentCabeleireiro] = useState(initialFormState);
		const [editing, setEditing] = useState(false);*/



		// CRUD operations
		const addCabeleireiro = cabeleireiro => {
			cabeleireiro.id = this.state.cabeleireiros.length + 1
			//this.state.setCabeleireiros([...this.state.cabeleireiros, cabeleireiro])
			this.setState({
				cabeleireiros: [...this.state.cabeleireiros, cabeleireiro],
			});
		}

		const deleteCabeleireiro = id => {
			this.setState({editing: false})

			//this.state.setCabeleireiros(this.state.cabeleireiros.filter(cabeleireiro => cabeleireiro.id !== id))
			this.setState({
				cabeleireiros: this.state.cabeleireiros.filter(cabeleireiro => cabeleireiro.id !== id)
			});
			api.get('/schedule/').then(r =>{
				let datar = r.data;
				for (const i in datar) {
					if (datar[i].service.saloon.id == parseInt(id)) {
						api.delete('/schedule/'+datar[i].id).then(res => {
							api.delete('/service/'+datar[i].service.id).then(resser => {
								api.delete('/saloon/'+id).then(ressal => {
								});
							});
						});

					}
				}
			});
		}

		const updateCabeleireiro = (id, updatedCabeleireiro) => {
			this.setState({editing: false})

			//this.state.setCabeleireiros(this.state.cabeleireiros.map(cabeleireiro => (cabeleireiro.id === id ? updatedCabeleireiro : cabeleireiro)))
			this.setState({
				cabeleireiros: this.state.cabeleireiros.map(cabeleireiro => (cabeleireiro.id === id ? updatedCabeleireiro : cabeleireiro))
			})
		}

		const editRow = cabeleireiro => {
			this.setState({editing: true})

			this.setState({
				currentCabeleireiro:{
				id: cabeleireiro.id,
				name: cabeleireiro.name,
				city: cabeleireiro.city,
				postalCode: cabeleireiro.postalCode,
				country: cabeleireiro.country,
				status: cabeleireiro.status,
				type: cabeleireiro.type,
				contact: cabeleireiro.contact,
				description: cabeleireiro.description,
				address: cabeleireiro.address,
			}
		})
		}

		return (
			<>
				{localStorage.getItem("user_type")=="dono" ? <NavbarDono></NavbarDono>:<NavbarCliente></NavbarCliente> }
				<Fragment>
				<NavbarDono></NavbarDono>
				<div className="container">
					<h1>Gestão dos meus salões</h1>
					<div className="flex-row">
						<div className="flex-large">
							{this.state.editing ? (
								<Fragment>
									<h2>Editar salão</h2>
									<EditCabeleireiroForm
										editing={this.state.editing}
										setEditing={this.state.setEditing}
										currentCabeleireiro={this.state.currentCabeleireiro}
										updateCabeleireiro={updateCabeleireiro}
									/>
								</Fragment>
							) : (
								<Fragment>
									<h2>Adicionar salão</h2>
									<AddCabeleireiroForm addCabeleireiro={addCabeleireiro}/>
								</Fragment>
							)}
						</div>
						<div className="flex-large">
							<h2>Ver salões</h2>
							<CabeleireiroTable cabeleireiros={this.state.cabeleireiros} editRow={editRow}
											   deleteCabeleireiro={deleteCabeleireiro}/>
						</div>
					</div>
				</div>
			</Fragment>
				</>
		)
	}
}
