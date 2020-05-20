import React, { useState, useEffect } from 'react'

const EditCabeleireiroForm = props => {
  const [ cabeleireiro, setCabeleireiro ] = useState(props.currentCabeleireiro)

  useEffect(
    () => {
      setCabeleireiro(props.currentCabeleireiro)
    },
    [ props ]
  )
  // You can tell React to skip applying an effect if certain values haven’t changed between re-renders. [ props ]

  const handleInputChange = event => {
    const { name, value } = event.target

    setCabeleireiro({ ...cabeleireiro, [name]: value })
  }

  return (
    <form
      onSubmit={event => {
        event.preventDefault()

        props.updateCabeleireiro(cabeleireiro.id, cabeleireiro)
      }}
    >
      <label>Nome</label>
      <input type="text" name="nome" value={cabeleireiro.nome} onChange={handleInputChange} />
      <label>Cidade</label>
      <input type="text" name="cidade" value={cabeleireiro.cidade} onChange={handleInputChange} />
      <label>Imagem</label>
      <input type="text" name="imagem" value={cabeleireiro.imagem} onChange={handleInputChange} />
      <button  classNome="button muted-button">
        Ver serviços e calendário configurados
      </button>
      <button>Update cabeleireiro</button>
      <button onClick={() => props.setEditing(false)} classNome="button muted-button">
        Cancelar
      </button>
    </form>
  )
}

export default EditCabeleireiroForm
