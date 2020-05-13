import React from 'react'

const CabeleireiroTable = props => (
  <table>
    <thead>
      <tr>
        <th>Nome</th>
        <th>Cidade</th>
        <th>Imagem</th>
        <th>Serviços e calendário</th>
        <th>Ações</th>
      </tr>
    </thead>
    <tbody>
      {props.cabeleireiros.length > 0 ? (
        props.cabeleireiros.map(cabeleireiro => (
          <tr key={cabeleireiro.id}>
            <td>{cabeleireiro.nome}</td>
            <td>{cabeleireiro.cidade}</td>
            <td>link da imagem?</td>
            <td>
              <button
                onClick={() => {
                  // por fazer
                }}
                classNome="button muted-button"
              >
                Ver
              </button>
              
            </td>
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
