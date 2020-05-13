
import React, { useState, Fragment } from 'react'
import AddCabeleireiroForm from '../crud/AddCabeleireiroForm';
import EditCabeleireiroForm from '../crud/EditCabeleireiroForm';
import CabeleireiroTable from '../crud/CabeleireiroTable';
import '../crud/style.css';

const Gestao = () => {
	// Data
	const cabeleireirosData = [
		{ id: 1, nome: 'Cabeleireiro 1 ', cidade: 'Lisboa' ,imagem:''},
		{ id: 2, nome: 'Cabeleireiro 2', cidade: 'Aveiro' ,imagem:''},
		
	]

	const initialFormState = { id: null, nome: '', cidade: '' ,imagem:''}

	// Setting state
	const [ cabeleireiros, setCabeleireiros ] = useState(cabeleireirosData)
	const [ currentCabeleireiro, setCurrentCabeleireiro ] = useState(initialFormState)
	const [ editing, setEditing ] = useState(false)

	// CRUD operations
	const addCabeleireiro = cabeleireiro => {
		cabeleireiro.id = cabeleireiros.length + 1
		setCabeleireiros([ ...cabeleireiros, cabeleireiro ])
	}

	const deleteCabeleireiro = id => {
		setEditing(false)

		setCabeleireiros(cabeleireiros.filter(cabeleireiro => cabeleireiro.id !== id))
	}

	const updateCabeleireiro = (id, updatedCabeleireiro) => {
		setEditing(false)

		setCabeleireiros(cabeleireiros.map(cabeleireiro => (cabeleireiro.id === id ? updatedCabeleireiro : cabeleireiro)))
	}

	const editRow = cabeleireiro => {
		setEditing(true)

		setCurrentCabeleireiro({ id: cabeleireiro.id, nome: cabeleireiro.nome, cidade: cabeleireiro.cidade ,imagem:cabeleireiro.imagem})
	}

	return (
		<div className="container">
			<h1>CRUD dos cabeleireiros no Web Site</h1>
			<div className="flex-row">
				<div className="flex-large">
					{editing ? (
						<Fragment>
							<h2>Editar cabeleireiro</h2>
							<EditCabeleireiroForm
								editing={editing}
								setEditing={setEditing}
								currentCabeleireiro={currentCabeleireiro}
								updateCabeleireiro={updateCabeleireiro}
							/>
						</Fragment>
					) : (
						<Fragment>
							<h2>Adicionar cabeleireiro</h2>
							<AddCabeleireiroForm addCabeleireiro={addCabeleireiro} />
						</Fragment>
					)}
				</div>
				<div className="flex-large">
					<h2>Ver cabeleireiros</h2>
					<CabeleireiroTable cabeleireiros={cabeleireiros} editRow={editRow} deleteCabeleireiro={deleteCabeleireiro} />
				</div>
			</div>
		</div>
	)
}

export default Gestao
