    import React from 'react'

const CabeleireiroTable = props => (
  <table>
    <thead>
      <tr>
        <th>Nome</th>
        <th>Cidade</th>
          <th>Tipo</th>
        <th>Ações</th>
      </tr>
    </thead>
    <tbody>
      {props.cabeleireiros.length > 0 ? (
        props.cabeleireiros.map(cabeleireiro => (
          <tr key={cabeleireiro.id}>
            <td>{cabeleireiro.name}</td>
            <td>{cabeleireiro.city}</td>
              <td>{cabeleireiro.type}</td>
            <td>
              <button
                onClick={() => {
                  props.editRow(cabeleireiro)
                }}
                classNome="button muted-button"
              >
                Editar
              </button>
              <button
                onClick={() => props.deleteCabeleireiro(cabeleireiro.id)}
                classNome="button muted-button"
              >
                Remover
              </button>
            </td>
          </tr>
        ))
      ) : (
        <tr>
          <td colSpan={3}>Sem cabeleireiros</td>
        </tr>
      )}
    </tbody>
  </table>
)

export default CabeleireiroTable
