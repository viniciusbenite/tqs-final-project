import React, { useState } from 'react'

const AddCabeleireiroForm = props => {
	const initialFormState = { id: null, nome: '', cidade: '' ,imagem:''}
	const [ cabeleireiro, setCabeleireiro ] = useState(initialFormState)

	const handleInputChange = event => {
		const { name, value } = event.target

		setCabeleireiro({ ...cabeleireiro, [name]: value })
	}

	return (
		<form
			onSubmit={event => {
				event.preventDefault()
				if (!cabeleireiro.nome || !cabeleireiro.cidade || !cabeleireiro.imagem) return

				props.addCabeleireiro(cabeleireiro)
				setCabeleireiro(initialFormState)
			}}
		>
			<label>Nome</label>
			<input type="text" name="nome" value={cabeleireiro.nome} onChange={handleInputChange} />
			<label>Cidade</label>
			<input type="text" name="cidade" value={cabeleireiro.cidade} onChange={handleInputChange} />
			<label>Imagem</label>
			<input type="text" name="imagem" value={cabeleireiro.imagem} onChange={handleInputChange} />
			<button>Adicionar serviços e calendário</button>
			<button>Adicionar novo cabeleireiro</button>
		</form>
	)
}

export default AddCabeleireiroForm
